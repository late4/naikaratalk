package com.naikara_talk.action;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dto.OrganizationMstDto;
import com.naikara_talk.dto.OrganizationPointAssignmentListDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.OrganizationPointAssignmentListReflectService;
import com.naikara_talk.service.OrganizationPointAssignmentListSearchService;
import com.naikara_talk.util.NaikaraStringUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>ポイント割振り<br>
 * <b>クラス名称　　　:</b>ポイント割振りActionクラス。<br>
 * <b>クラス概要　　　:</b>ポイント割振り新残高に反映Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/17 TECS 新規作成。
 */
public class OrganizationPointAssignmentListReflectAction extends OrganizationPointAssignmentListActionSupport {

	private static final long serialVersionUID = 1L;

	// 0件
	private static final int ZERO = 0;

	// 合計
	private static final int SUM_ZERO = 0;

	/**
	 * 検索処理。<br>
	 * <br>
	 *
	 * @param なし
	 * @return String
	 * @throws NaiException
	 */
	public String requestService() throws NaiException {

		// 開始ログ
		log.info(NaikaraStringUtil.unionProcesslog("Start"));

		// Modelクラス設定
		setupModel();

		// 表示データの取得処理
		try {
			load();
		} catch (Exception e) {
			throw new NaiException(e);
		}

		OrganizationPointAssignmentListSearchService service = new OrganizationPointAssignmentListSearchService();

		try {
			// 検索前チェック
			if (!service.checkContract(model)) {
				this.addActionMessage(getMessage("EB0001", new String[] { "契約期間" }));
				// 画面を返す
				return SUCCESS;
			}

			// 未割振りポイントの取得
			OrganizationMstDto returnDto = service.searchOrganization(model);
			BigDecimal point = returnDto.getBalancePointNum();
			this.setBalancePointNum(point);

			// 隠しﾌｨｰﾙﾄﾞの設定
			this.setOrganizationId(returnDto.getOrganizationId());    // 組織ID
			this.setConsSeq(returnDto.getConsSeq());                  // 連番
			this.setRecordVerNo(returnDto.getRecordVerNo());          // レコードバージョン番号

			// 現残高ポイント(合計)の取得
			this.setBalancePoint(service.getBalancePoint(model));

			// 受講者マスタのデータ取得処理
			String allocatePoint[] = this.allocatePoint;

			// 半角数字８桁チェック
			for (int i = 0;i < allocatePoint.length; i++) {
				if (allocatePoint[i] != null || !StringUtils.equals("", allocatePoint[i])) {
					 if (!service.isHalfNum(allocatePoint[i])) {
						 this.message = getMessage("EB0004", new String[] { "今回割振ポイント","8"});
					 }
				}
			}
			String ownershipId[] = (String[]) this.request.getParameterValues("ownershipId");

			// (一覧全体) 一覧上の｢今回割振ポイント｣が全て、未入力の場合は、エラーメッセージを設定する
			boolean nullFlag = true;
			for (int i = 0 ; i < allocatePoint.length ; i++) {
				if (!StringUtils.equals("", allocatePoint[i].trim())) {
					nullFlag = false;
				}
			}
			if (nullFlag) {
				this.message = getMessage("EB0044", new String[] { "今回割振ポイント" });
			}

			OrganizationPointAssignmentListReflectService serviceR = new OrganizationPointAssignmentListReflectService();
			// 画面一覧のデータ取得処理
			List<OrganizationPointAssignmentListDto> list = serviceR.searchStudent(model, allocatePoint, ownershipId);
			// 0件の場合メッセージ情報を設定する
			if (list == null || list.size() == ZERO) {
				// 画面を返す
				return ERROR;
			}
			// 画面一覧のlistを設定
			this.model.setResultList(list);

			// 合計
			this.sum(list);

			// 一覧下の項目の合計欄(未割振りポイント)
			BigDecimal pointDown = point.subtract(this.sumAllocatePoint);
			this.setBalancePointNumDown(pointDown);

			// (一覧全体) 一覧上の｢今回割振ポイント｣の合計と｢未割振ポイント｣の整合性チェック
			if (point.compareTo(this.sumAllocatePoint) < SUM_ZERO) {
				this.message = getMessage("EB0045", new String[] {});
			}

			// (明細単位) 一覧上の｢今回割振ポイント｣がマイナス入力の場合の整合性チェック
			OrganizationPointAssignmentListDto dto ;
			for(int i=0;list.size()>i;i++){
	    		dto=list.get(i);
	    		if (dto.getNewbalancePoint().compareTo(new BigDecimal(0)) < SUM_ZERO) {
	    			this.message = getMessage("EB0046", new String[] { "今回割振ポイント","ご利用可能ポイント" });
	    			break;
	    		}
	    	}
			if (!StringUtils.isEmpty(this.message)) {
	            this.addActionMessage(this.message);
	        }

		} catch (Exception e) {
			throw new NaiException(e);
		}
		// 画面を返す
		return SUCCESS;
	}
}

