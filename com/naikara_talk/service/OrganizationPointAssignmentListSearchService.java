package com.naikara_talk.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.OrganizationMstDto;
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
public class OrganizationPointAssignmentListSearchService implements ActionService {

	// 100件
	public static final int HUNDERD = 100;

	/**
	 * 契約期間が必須チェック
	 * @param model ポイント割振り初期処理Model
	 * @return boolean
	 */
	public boolean checkContract(OrganizationPointAssignmentListModel model) throws NaiException {
		if (StringUtils.equals(NaikaraTalkConstants.CHOICE_ALL_ZERO, model.getContract())) {
			return false;
		}
		return true;

	}

	/**
	 * 画面一覧のデータ取得処理。
	 * @param model ポイント割振り初期処理Model
	 * @return list
	 * @throws NaiException
	 */
	public List<OrganizationPointAssignmentListDto> searchStudent(
			OrganizationPointAssignmentListModel model) throws NaiException {

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

			// 取得した受講者ID単位に購入／無償ポイントと残高ポイントの取得
			for(int i = 0 ;list.size() > i; i++){
				smDto = list.get(i);
				// 受講者マスタデータ取得してない場合
				if (smDto.getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_NO) {
					return orgList;
				}

				// 受講者マスタデータ取得した場合

				// 画面一覧のデータを設定
				orgDto = new OrganizationPointAssignmentListDto();
				orgDto.setStudentPosition(smDto.getStudentPosition());
				orgDto.setPositionOrganizationId(smDto.getPositionOrganizationId());
				// 名前の編集(名前(姓)、名前(名))
				orgDto.setStudentName(NaikaraStringUtil.unionName(smDto.getFamilyJnm(), smDto.getFirstJnm()));
				// 今回割振ポイント
				orgDto.setAllocatePoint("0");
				// 新残高ポイント
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

				// 購入／無償ポイントと残高ポイントの取得
				OrganizationPointAssignmentListDto returnDto = logic.getPointOwnershipTrn(dto);
				if (returnDto.getPurchaseFreePoint() == null && returnDto.getBalancePoint() == null) {
					// データが存在しない場合
					orgDto.setUsedPoint(new BigDecimal(0));
					orgDto.setOldbalancePoint(new BigDecimal(0));
				} else {
					// データが存在する場合
					// 画面の｢ご利用済ポイント｣    購入／無償ポイント（合計）－ 残高ポイント（合計）
					orgDto.setUsedPoint(returnDto.getPurchaseFreePoint().subtract(returnDto.getBalancePoint()));
					orgDto.setOldbalancePoint(returnDto.getBalancePoint());
				}
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
	private OrganizationPointAssignmentListDto modelToDto(OrganizationPointAssignmentListModel model) throws NaiException {

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

	/**
	 * 未割振りポイントの取得。
	 * @param model ポイント割振り初期処理Model
	 * @return OrganizationMstDto
	 * @throws NaiException
	 */
	public OrganizationMstDto searchOrganization(OrganizationPointAssignmentListModel model) throws NaiException {

		// コネクション変数
		Connection conn = null;

		try {
			// コネクションを取得
			conn = DbUtil.getConnection();

			// Model値をDTOにセット
			OrganizationPointAssignmentListDto dto = modelToDto(model);

			OrganizationPointAssignmentListLogic logic = new OrganizationPointAssignmentListLogic(conn);
			// 組織マスタデータ取得
			List<OrganizationMstDto> list = logic.searchOrganization(dto);
			OrganizationMstDto returnDto = null;
			for (int i = 0; list.size() > i; i++) {
				// データがありの場合
				returnDto = list.get(i);
			}
			return returnDto;

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
	 * 現残高ポイント(合計)の取得。
	 * @param model ポイント割振り初期処理Model
	 * @return BigDecimal
	 * @throws NaiException
	 */
	public BigDecimal getBalancePoint(OrganizationPointAssignmentListModel model) throws NaiException {

		// コネクション変数
		Connection conn = null;

		try {
			// コネクションを取得
			conn = DbUtil.getConnection();

			// Model値をDTOにセット
			OrganizationPointAssignmentListDto dto = modelToDto(model);

			OrganizationPointAssignmentListLogic logic = new OrganizationPointAssignmentListLogic(conn);
			OrganizationPointAssignmentListDto returnDto = logic.getBalancePoint(dto);
			// ポイント所有テーブルより残高ポイントの取得を行う (集計値)。
			BigDecimal point = returnDto.getBalancePoint();
			if (point == null) {
				return new BigDecimal(0);
			} else {
				return point;
			}
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
     * 半角数字８桁チェック
     * @param str
     * @return boolean
     * @throws NaiException
     */
    public boolean isHalfNum(String str) throws NaiException {
    	// トリムを行う場合はトリムを実行
        if (str != null) {
        	str = str.trim();
        }

        // 入力されていない場合は処理を抜ける
        if (StringUtils.isEmpty(str)) {
            return true;
        }

        // 少数入力不可
        if(str.contains(".")) {
        	return false;
        }

        str = NaikaraStringUtil.delComma(str);

        if (str.contains("-")) {
        	// マイナスの場合9桁数以内
            if(str.length() > 9) {
            	return false;
            }
        } else if(str.length() > 8) {
        	// 8桁数以内
        	return false;
        }

        String expression = "[ -~｡-ﾟ]*";
        Pattern pattern;
        pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(str);
        if (!matcher.matches()) {
        	return false;
        }

        // 数字
        try {
        	new BigDecimal(str);

        } catch (Exception e) {
			return false;
		}

        return true;
    }

}
