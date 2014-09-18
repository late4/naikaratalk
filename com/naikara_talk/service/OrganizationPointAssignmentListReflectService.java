package com.naikara_talk.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.OrganizationPointAssignmentListDto;
import com.naikara_talk.dto.StudentMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.OrganizationPointAssignmentListLogic;
import com.naikara_talk.model.OrganizationPointAssignmentListModel;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>ポイント割振り<br>
 * <b>クラス名称　　　:</b>ポイント割振り初期処理クラス。<br>
 * <b>クラス概要　　　:</b>ポイント割振り初期処理Service。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴　　　　:</b>2013/07/17 TECS 新規作成。
 */
public class OrganizationPointAssignmentListReflectService implements ActionService {

	// 100件
	public static final int HUNDERD = 100;

	/**
	 * 画面一覧のデータ取得処理。
	 * @param model ポイント割振り初期処理Model
	 * @param allocatePoint 今回割振ポイント
	 * @param ownershipId 所有ID
	 * @return list
	 * @throws NaiException
	 */
	public List<OrganizationPointAssignmentListDto> searchStudent(
			OrganizationPointAssignmentListModel model, String allocatePoint[], String ownershipId[]) throws NaiException {

		// コネクション変数
		Connection conn = null;

		try {
			// コネクションを取得
			conn = DbUtil.getConnection();

			// Model値をDTOにセット
			OrganizationPointAssignmentListDto dto = modelToDto(model);

			OrganizationPointAssignmentListLogic logic = new OrganizationPointAssignmentListLogic(conn);
			// 受講者マスタデータ取得
			List<StudentMstDto> list = logic.searchStudent(dto);
			ArrayList<OrganizationPointAssignmentListDto> orgList = new ArrayList<OrganizationPointAssignmentListDto>();
			String studentId = null;
			StudentMstDto smDto;
			OrganizationPointAssignmentListDto orgDto;
			OrganizationPointAssignmentListSearchService service = new OrganizationPointAssignmentListSearchService();

			// 100件以上の場合
			if (list.size() > HUNDERD || list.size() != allocatePoint.length) {
				return orgList;
			}

			int j = 0;

			for(int i = 0; list.size() > i; i++){
				smDto=list.get(i);

				// 受講者マスタデータ取得してない場合
				if (smDto.getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_NO) {
					return orgList;
				}

				// 画面一覧のデータを設定
				orgDto = new OrganizationPointAssignmentListDto();
				orgDto.setStudentPosition(smDto.getStudentPosition());
				orgDto.setPositionOrganizationId(smDto.getPositionOrganizationId());
				orgDto.setStudentName(NaikaraStringUtil.unionName(smDto.getFamilyJnm(), smDto.getFirstJnm()));
				orgDto.setAllocatePoint("0");
				orgDto.setNewbalancePoint(new BigDecimal(0));

				// 購入／無償ポイントと残高ポイントの取得用の受講者ID
				studentId = smDto.getStudentId();
				orgDto.setStudentId(studentId);
				dto.setStudentId(studentId);

				// 利用停止日
				String useEndDt = smDto.getUseEndDt();
				// サーバーのシステム日付を取得
				String sysDate = DateUtil.getSysDate();
				// 受講者マスタの｢利用停止フラグ｣＝”1”(利用不可) 受講者マスタの｢利用終了日｣ < サーバーのシステム日付 の場合
				if (StringUtils.equals("1", smDto.getUseStopFlg()) && DateUtil.dateCompare2(sysDate, useEndDt)) {
					orgDto.setUseStopFlag(true);
				} else {
					orgDto.setUseStopFlag(false);
				}

				OrganizationPointAssignmentListDto returnDto = logic.getPointOwnershipTrn(dto);
				if (returnDto.getPurchaseFreePoint() == null && returnDto.getBalancePoint() == null) {
					orgDto.setUsedPoint(new BigDecimal(0));
					orgDto.setOldbalancePoint(new BigDecimal(0));
				} else {
					orgDto.setUsedPoint(returnDto.getPurchaseFreePoint().subtract(returnDto.getBalancePoint()));
					orgDto.setOldbalancePoint(returnDto.getBalancePoint());
				}
				if (StringUtils.equals("", allocatePoint[j].trim()) || !service.isHalfNum(allocatePoint[j])) {
					// 画面の今回割振ポイントはnullの場合、そのままで設定する
					orgDto.setAllocatePoint(allocatePoint[j].trim());
					orgDto.setNewbalancePoint(orgDto.getOldbalancePoint());
				} else {
					// 画面の今回割振ポイントはnullではない場合
					// コンマ削除してから追加
					orgDto.setAllocatePoint(NaikaraStringUtil.addComma(NaikaraStringUtil.delComma(allocatePoint[j].trim())));
					orgDto.setNewbalancePoint(new BigDecimal(NaikaraStringUtil.delComma(allocatePoint[j].trim())).add(orgDto.getOldbalancePoint()));
				}

				j ++;

				// 所有ID
				orgDto.setOwnershipId(returnDto.getOwnershipId());
				// レコードバージョン番号
				orgDto.setRecordVerNoM(String.valueOf(returnDto.getRecordVerNo()));
				orgList.add(orgDto);
	    	}

			return orgList;

		} catch ( SQLException se ) {
			se.printStackTrace();
			throw new NaiException(se);
		} finally {
			try {
				conn.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}

	/**
	 * Model値をDTOにセット。
     * @param model ポイント割振り初期処理Model
     * @return OrganizationPointAssignmentListDto
	 */
	private OrganizationPointAssignmentListDto modelToDto(
			OrganizationPointAssignmentListModel model) throws NaiException {

		// DTOの初期化
		OrganizationPointAssignmentListDto prmDto = new OrganizationPointAssignmentListDto();

		String contract= model.getContract();
		String split[] = StringUtils.split(contract, "-");

		prmDto.setOrganizationId(model.getOrganizationId());
		// 契約開始日
		prmDto.setContractFrom(StringUtils.replaceChars(split[0], "/", ""));
		// 契約終了日
		prmDto.setContractTo(StringUtils.replaceChars(split[1], "/", ""));
		// 受講者所属部署
		prmDto.setStudentPosition(model.getStudentPosition());
		// 所属組織内ID(From)
		prmDto.setPositionOrganizationIdFrom(model
				.getPositionOrganizationIdFrom());
		// 所属組織内ID(To)
		prmDto.setPositionOrganizationIdTo(model.getPositionOrganizationIdTo());

		return prmDto;

	}

}
