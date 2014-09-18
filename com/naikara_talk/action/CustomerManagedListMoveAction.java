package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.CustomerManagedListModel;
import com.naikara_talk.service.CustomerManagedListMoveService;
import com.naikara_talk.service.CustomerManagedListSearchService;
import com.naikara_talk.sessiondata.SessionCustomerManaged;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様管理ページ<br>
 * <b>クラス名称　　　:</b>お客様管理ページクラス。<br>
 * <b>クラス概要　　　:</b>お客様管理ページ選択Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/20 TECS 新規作成。
 */

public class CustomerManagedListMoveAction extends CustomerManagedListActionSupport{

	 private static final long serialVersionUID = 1L;

	 /**
     * 選択ボタンの処理。<br>
     * <br>
     * @param なし
     * @return String
     * @throws NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // 画面のパラメータをモデルに設定
        this.setupModel();

        //Serviceクラス生成
        CustomerManagedListMoveService service = new CustomerManagedListMoveService();

        // 次画面へ遷移する前のチェック
        int chkResult = service.nextPageRequest(this.model, this.getSelect_rdl(), this.getHasSearchFlg());

        //エラーの場合、メッセージ設定
        try {

        	SessionCustomerManaged sessionData = new SessionCustomerManaged();
            sessionData = (SessionCustomerManaged)SessionDataUtil.getSessionData(SessionCustomerManaged.class.toString());
            if ( sessionData != null ) {
                // ◆エラーが発生した場合でも、一覧が表示されている状態であれば表示する
                this.hasSearchFlg = NaikaraStringUtil.nvlString(sessionData.getHasSearchFlg()).toString();

                if (StringUtils.equals(NaikaraTalkConstants.FALSE, this.hasSearchFlg)) {
                    // 表示データの取得と設定
                	CustomerManagedListSearchService searchService = new CustomerManagedListSearchService();
                    this.model.setResultList(searchService.selectList(this.model));
                }
            }

            switch (chkResult) {
            case CustomerManagedListModel.ERR_NO_SELECT:
                // 一覧の選択なしエラー
                this.addActionMessage(getMessage("EN0015", new String[] { "一覧部の左の選択" }));
                return SUCCESS;
            case CustomerManagedListModel.ERR_LIST_BACK:
                // 一覧の選択なしエラー
                this.addActionMessage(getMessage("EN0015", new String[] { "一覧部の左の選択" }));
                return SUCCESS;
            }

            // 正常の場合
            // 戻る用に必要な情報を取得/格納
            this.modelToCustomerManaged();

        } catch (Exception e) {
            throw new NaiException(e);
        }

        // ヘッダの戻るリンク用
        setCurrentActionName(NaikaraTalkConstants.CUSTOMER_MANAGED_LIST_SEARCH);

        // 詳細画面遷移
        return NEXTPAGE;
    }

    /**
     * Model値・SessionCustomerManaged値をSessionCustomerManagedにセット。<br>
     * <br>
     * @throws Exception
     */
    private void modelToCustomerManaged() throws NaiException {

        // 戻る用に必要な情報を格納
    	SessionCustomerManaged sessionData = new SessionCustomerManaged();

        if ((SessionCustomerManaged) SessionDataUtil.getSessionData(SessionCustomerManaged.class.toString()) != null) {
            // 戻る用に必要な情報を取得
        	String searchCostomerKbn = ((SessionCustomerManaged) SessionDataUtil.getSessionData(SessionCustomerManaged.class
                    .toString())).getSearchCostomerKbn();
            String searchStudentId = ((SessionCustomerManaged) SessionDataUtil.getSessionData(SessionCustomerManaged.class
                    .toString())).getSearchStudentId();
            String searchFamilyFirstJnm = ((SessionCustomerManaged) SessionDataUtil.getSessionData(SessionCustomerManaged.class
                    .toString())).getSearchFamilyFirstJnm();
            String searchFamilyFirstKnm = ((SessionCustomerManaged) SessionDataUtil.getSessionData(SessionCustomerManaged.class
                    .toString())).getSearchFamilyFirstKnm();
            String searchFamilyFirstRomaji = ((SessionCustomerManaged) SessionDataUtil.getSessionData(SessionCustomerManaged.class
                    .toString())).getSearchFamilyFirstRomaji();
            String searchOrganizationJnm = ((SessionCustomerManaged) SessionDataUtil.getSessionData(SessionCustomerManaged.class
                    .toString())).getSearchOrganizationJnm();
            String searchNikeNm = ((SessionCustomerManaged) SessionDataUtil.getSessionData(SessionCustomerManaged.class
                    .toString())).getSearchNikeNm();
            String searchObjectPeriod = ((SessionCustomerManaged) SessionDataUtil.getSessionData(SessionCustomerManaged.class
                    .toString())).getSearchObjectPeriod();
            String searchPeriodFrom = ((SessionCustomerManaged) SessionDataUtil.getSessionData(SessionCustomerManaged.class
                    .toString())).getSearchPeriodFrom();
            String searchPeriodTo = ((SessionCustomerManaged) SessionDataUtil.getSessionData(SessionCustomerManaged.class
                    .toString())).getSearchPeriodTo();

            sessionData.setSearchCostomerKbn(searchCostomerKbn);              // 検索Key：顧客区分
            sessionData.setSearchStudentId(searchStudentId);                  // 検索Key：受講者ID
            sessionData.setSearchFamilyFirstJnm(searchFamilyFirstJnm);        // 検索Key：受講者名(漢字)
            sessionData.setSearchFamilyFirstKnm(searchFamilyFirstKnm);        // 検索Key：受講者名(フリガナ)
            sessionData.setSearchFamilyFirstRomaji(searchFamilyFirstRomaji);  // 検索Key：受講者名(ローマ字)
            sessionData.setSearchOrganizationJnm(searchOrganizationJnm);      // 検索Key：組織名
            sessionData.setSearchNikeNm(searchNikeNm);                        // 検索Key：受講者名(ニックネーム)
            sessionData.setSearchObjectPeriod(searchObjectPeriod);            // 検索Key：対象期間
            sessionData.setSearchPeriodFrom(searchPeriodFrom);                // 検索Key：期間指定 from
            sessionData.setSearchPeriodTo(searchPeriodTo);                    // 検索Key：期間指定 to

        }

        sessionData.setReturnOnFlg(true);                                     // 画面遷移前に保持:戻る判定Onフラグ
        sessionData.setCostomerKbn(this.model.getCostomerKbn());              // 画面遷移前に保持：顧客区分
        sessionData.setStudentId(this.model.getStudentId());                  // 画面遷移前に保持：受講者ID
        sessionData.setFamilyFirstJnm(this.model.getFamilyFirstJnm());        // 画面遷移前に保持：受講者名(漢字)
        sessionData.setFamilyFirstKnm(this.model.getFamilyFirstKnm());        // 画面遷移前に保持：受講者名(フリガナ)
        sessionData.setFamilyFirstRomaji(this.model.getFamilyFirstRomaji());  // 画面遷移前に保持：受講者名(ローマ字)
        sessionData.setOrganizationJnm(this.model.getOrganizationJnm());      // 画面遷移前に保持：組織名
        sessionData.setNikeNm(this.model.getNikeNm());                        // 画面遷移前に保持：受講者名(ニックネーム)
        sessionData.setObjectPeriod(this.model.getObjectPeriod());            // 画面遷移前に保持：対象期間
        sessionData.setPeriodFrom(this.model.getPeriodFrom());                // 画面遷移前に保持：期間指定 from
        sessionData.setPeriodTo(this.model.getPeriodTo());                    // 画面遷移前に保持：期間指定 to



        sessionData.setSearchKey(this.getSelect_rdl());  // 検索Key：選択された明細のKey

        this.setHasSearchFlg(NaikaraTalkConstants.TRUE);           // クリア(一覧なし)
        if (this.model.getResultList() != null ) {
            if (this.model.getResultList().size() > 0) {
                this.setHasSearchFlg(NaikaraTalkConstants.FALSE);  // 初期状態ではない(一覧あり)
            }
        }
        sessionData.setHasSearchFlg(this.getHasSearchFlg());    // 画面遷移前に保持:画面状態フラグ

        SessionDataUtil.setSessionData(sessionData);

    }

}
