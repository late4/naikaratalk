package com.naikara_talk.action;

import java.util.List;

import com.naikara_talk.dto.OrganizationUsageSituationListDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.OrganizationUsageSituationListSearchService;
import com.naikara_talk.sessiondata.SessionOrganizationUsageSituation;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>利用状況照会<br>
 * <b>クラス名称　　　:</b>利用状況照会Actionクラス。<br>
 * <b>クラス概要　　　:</b>利用状況照会検索Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴　　　　:</b>2013/08/06 TECS 新規作成。
 */

public class OrganizationUsageSituationListSearchAction extends OrganizationUsageSituationListActionSupport {

	private static final long serialVersionUID = 1L;

	public String requestService() throws NaiException {

		// 開始ログ
		log.info(NaikaraStringUtil.unionProcesslog("Start"));

		// 選択したラジオボタンをクリアする
		this.select_rdl = NaikaraTalkConstants.BRANK;

		// 戻る判定Onフラグ
        boolean returnOnFlg = false;
        try {
			if ((SessionOrganizationUsageSituation) SessionDataUtil
					.getSessionData(SessionOrganizationUsageSituation.class.toString()) != null) {

                // 戻る判定Onフラグ
                returnOnFlg = ((SessionOrganizationUsageSituation) SessionDataUtil
                		.getSessionData(SessionOrganizationUsageSituation.class.toString())).isReturnOnFlg();
            }

			if (returnOnFlg == true) {
				// 対象年月
				this.objectYyyyMm_txt = ((SessionOrganizationUsageSituation) SessionDataUtil
						.getSessionData(SessionOrganizationUsageSituation.class.toString()))
						.getObjectYyyyMm_txt();
				// 所属組織内ID From
				this.positionOrganizationIdFrom_txt = ((SessionOrganizationUsageSituation) SessionDataUtil
						.getSessionData(SessionOrganizationUsageSituation.class.toString()))
						.getPositionOrganizationIdFrom_txt();
				// 所属組織内ID To
				this.positionOrganizationIdTo_txt = ((SessionOrganizationUsageSituation) SessionDataUtil
						.getSessionData(SessionOrganizationUsageSituation.class.toString()))
						.getPositionOrganizationIdTo_txt();
				// 受講者所属部署
				this.studentPosition_txt = ((SessionOrganizationUsageSituation) SessionDataUtil
						.getSessionData(SessionOrganizationUsageSituation.class.toString()))
						.getStudentPosition_txt();
				// 受講者名(フリガナ)
				this.familyFirstKnm_txt = ((SessionOrganizationUsageSituation) SessionDataUtil
						.getSessionData(SessionOrganizationUsageSituation.class.toString()))
						.getFamilyFirstKnm_txt();
				// 選択されたデータ
				this.select_rdl = ((SessionOrganizationUsageSituation) SessionDataUtil
						.getSessionData(SessionOrganizationUsageSituation.class.toString()))
						.getSelect_rdl();

				// SessionPaymentManagedのクリア
		        SessionDataUtil.clearSessionData(SessionOrganizationUsageSituation.class.toString());
			}
		} catch (Exception e) {
            throw new NaiException(e);
        }


		// ロゴイン情報の組織IDの取得
		this.organizationId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();

		// 画面のパラメータをモデルにセット
		setupModel();

		OrganizationUsageSituationListSearchService service = new OrganizationUsageSituationListSearchService();

		// 検索前チェック
        int chkResult;
        try {
        	chkResult = service.checkPreSelect(model);
            //エラーの場合、メッセージ設定
        	switch (chkResult) {
        	case OrganizationUsageSituationListSearchService.OBJECT_YYYYMM_NULL:
        		// 対象年月が必須チェック
        		this.addActionMessage(getMessage("EB0001",new String[] { "対象年月" }));
                return SUCCESS;
        	case OrganizationUsageSituationListSearchService.OBJECT_YYYYMM_DATE:
        		// 対象年月の日付チェック
        		this.addActionMessage(getMessage("EN0035", new String[] { "対象年月" }));
                return SUCCESS;
            case OrganizationUsageSituationListSearchService.LIST_ZERO_CNT:
            	// 取得したデータ件数がZero件の場合
                this.addActionMessage(getMessage("EB0020", new String[] { "", "" }));
                return SUCCESS;
            case OrganizationUsageSituationListSearchService.LIST_HUNDRED_CNT:
            	// 取得したデータ件数が表示の上限値を超える場合
            	this.addActionMessage(getMessage("EB0023", new String[] { "101" }));
                return SUCCESS;
            }
        } catch (Exception e) {
            throw new NaiException(e);
        }

        // 100件以下の場合、処理を続行する

		// 画面一覧のデータ取得処理
		List<OrganizationUsageSituationListDto> list = service.search(model);

		// 画面一覧のlistを設定
		this.model.setResultList(list);
		this.setObjectYyyyMm_txt(NaikaraStringUtil.converToYYYY_MM(this.model.getObjectYyyyMm()));
		// 画面を返す
		return SUCCESS;

	}

}
