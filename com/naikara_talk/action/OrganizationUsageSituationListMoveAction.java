package com.naikara_talk.action;

import java.text.ParseException;
import java.util.List;

import com.naikara_talk.dto.OrganizationUsageSituationListDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.OrganizationUsageSituationListMoveService;
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
 * <b>クラス概要　　　:</b>利用状況照会moveAction。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴　　　　:</b>2013/08/02 TECS 新規作成。
 */

public class OrganizationUsageSituationListMoveAction extends OrganizationUsageSituationListActionSupport {

	private static final long serialVersionUID = 1L;

	/**
	 * 画面の初期表示。<br>
	 * <br>
	 *
	 * @param なし
	 * @return String
	 * @throws NaiException
	 * @throws ParseException
	 */
	public String requestService() throws NaiException {

		// 開始ログ
		log.info(NaikaraStringUtil.unionProcesslog("Start"));

		// ロゴイン情報の組織IDの取得
		this.organizationId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();

		// 画面のパラメータをモデルにセット
		setupModel();

		OrganizationUsageSituationListMoveService moveService = new OrganizationUsageSituationListMoveService();
		if (!moveService.checkNull(model)) {

			// 一覧部の「選択」が必須入力チェック エラーの場合
			OrganizationUsageSituationListSearchService searchService = new OrganizationUsageSituationListSearchService();

	        int chkResult;
	        try {
	        	chkResult = searchService.checkPreSelect(model);
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

	        // 100件以下の場合、処理を続行する

			// 画面一覧のデータ取得処理
			List<OrganizationUsageSituationListDto> list = searchService.search(model);

			// 画面一覧のlistを設定
			this.model.setResultList(list);

			// 一覧部の「選択」が必須入力チェック エラーのメッセージ情報を設定する
			this.addActionMessage(getMessage("EB0015", new String[] {"一覧部の左の選択", "" }));

	        } catch (Exception e) {
	            throw new NaiException(e);
	        }

			return SUCCESS;

		} else {

			// 一覧部の「選択」が必須入力チェック 正常の場合
			int chkResult;
	        try {
	        	chkResult = moveService.checkPreSelect(model);
	            //エラーの場合、メッセージ設定
	        	switch (chkResult) {
	        	case OrganizationUsageSituationListMoveService.OBJECT_YYYYMM_NULL:
	        		// 対象年月が必須チェック
	        		this.addActionMessage(getMessage("EB0001",new String[] { "対象年月" }));
	                return SUCCESS;
	        	case OrganizationUsageSituationListMoveService.OBJECT_YYYYMM_DATE:
	        		// 対象年月の日付チェック
	        		this.addActionMessage(getMessage("EN0035", new String[] { "対象年月" }));
	                return SUCCESS;
	            }
	        } catch (Exception e) {
	            throw new NaiException(e);
	        }
		}

		try {
			// 戻る用に必要な情報を取得/格納
	        this.modelToSessionPaymentManaged();

			// ヘッダの戻るリンク用
	        setCurrentActionName(NaikaraTalkConstants.ORGANIZATION_USAGE_SITUATION_LIST_SEARCH);

        } catch (Exception e) {
            throw new NaiException(e);
        }

		// 利用状況照会【詳細】画面へ遷移する
		return NEXTPAGE;

	}

	/**
	 * 戻る用に必要な情報を取得/格納。
	 * Model値・SessionPaymentManaged値をSessionPaymentManagedにセット。
	 * @param なし
	 * @throws Exception
	 */
	private void modelToSessionPaymentManaged() throws Exception {

        // 戻る用に必要な情報を格納
		SessionOrganizationUsageSituation sessionData = new SessionOrganizationUsageSituation();

		sessionData.setSelect_rdl(select_rdl);                                             // 修正選択
        sessionData.setObjectYyyyMm_txt(objectYyyyMm_txt);                                 // 対象年月
        sessionData.setStudentPosition_txt(studentPosition_txt);                           // 受講者所属部署
        sessionData.setPositionOrganizationIdFrom_txt(positionOrganizationIdFrom_txt);     // 所属組織内ID From
        sessionData.setPositionOrganizationIdTo_txt(positionOrganizationIdTo_txt);         // 所属組織内ID To
        sessionData.setFamilyFirstKnm_txt(familyFirstKnm_txt);                             // 受講者名(フリガナ)
        sessionData.setReturnOnFlg(true);                                                  // 戻る判定Onフラグ
        SessionDataUtil.setSessionData(sessionData);

    }

}
