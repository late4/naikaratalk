package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.OrganizationStudentMstListMoveService;
import com.naikara_talk.service.OrganizationStudentMstListSearchService;
import com.naikara_talk.sessiondata.SessionOrganizationStudentMst;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>組織_初期処理<br>
 * <b>クラス名称       :</b>システム受講者登録(一覧)登録選択Actionクラス。<br>
 * <b>クラス概要       :</b>組織の社員情報(受講者)の新規アカウント(一覧)。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/11 TECS 新規作成
 */
public class OrganizationStudentMstListMoveAction extends OrganizationStudentMstListActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 登録/選択ボタンの処理。<br>
     * <br>
     * 登録/選択ボタンの処理。<br>
     * <br>
     * @param なし<br>
     * @return String SUCCESS or NEXTPAGE<br>
     * @exception NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // Modelクラス設定
        setupModel();
        // Serviceクラス生成
        OrganizationStudentMstListMoveService service = new OrganizationStudentMstListMoveService();

        int chkResult = service.nextPageRequest(this.model, this.getSelect_rdl(), this.getHasSearchFlg());

        // エラーの場合、メッセージ設定
        try {
            switch (chkResult) {
            case OrganizationStudentMstListMoveService.ERR_NO_SELECT:

                this.addActionMessage(getMessage("EB0015", new String[] { "一覧部の左の選択" }));

                OrganizationStudentMstListSearchService searchService = new OrganizationStudentMstListSearchService();

                // 検索前チェック
                chkResult = searchService.checkPreSelect(model);

                // エラーの場合、メッセージ設定
                switch (chkResult) {
                case OrganizationStudentMstListSearchService.ERR_PROS_BTN_MISMATCH:
                    return SUCCESS;
                case OrganizationStudentMstListSearchService.ERR_ZERO_DATA:
                    return SUCCESS;
                case OrganizationStudentMstListSearchService.ERR_MAXOVER_DATA:
                    return SUCCESS;
                }
                // 表示データの取得
                this.model.setResultList(searchService.selectList(this.model));
                // 選択したラジオボタンをクリアする
                this.select_rdl = NaikaraTalkConstants.BRANK;
                this.hasSearchFlg = NaikaraTalkConstants.TRUE;

                return SUCCESS;

            case OrganizationStudentMstListMoveService.ERR_LIST_BACK:
                this.addActionMessage(getMessage("EB0015", new String[] { "一覧部の左の選択" }));
                return SUCCESS;
            }

            // 戻る用に必要な情報を取得/格納
            this.modelToSessionOrganizationStudentMst();

        } catch (Exception e) {
            throw new NaiException(e);
        }

        // ヘッダの戻るリンク用
        setCurrentActionName(NaikaraTalkConstants.ORGANIZATION_STUDENT_MST_LIST_SEARCH);

        // 詳細画面遷移
        return NEXTPAGE;
    }

    /**
     * Model値 To Session<br>
     * <br>
     * Model値・SessionOrganizationStudentMst値をSessionOrganizationStudentMstにセット。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    private void modelToSessionOrganizationStudentMst() throws Exception {

        // 戻る用に必要な情報を格納
        SessionOrganizationStudentMst sessionData = null;

        if ((SessionOrganizationStudentMst) SessionDataUtil.getSessionData(SessionOrganizationStudentMst.class
                .toString()) != null) {
            sessionData = (SessionOrganizationStudentMst) SessionDataUtil
                    .getSessionData(SessionOrganizationStudentMst.class.toString());
            // 戻る用に必要な情報を取得
            String searchStudentPosition = ((SessionOrganizationStudentMst) SessionDataUtil
                    .getSessionData(SessionOrganizationStudentMst.class.toString())).getSearchStudentPosition();
            String searchPositionOrganizationId = ((SessionOrganizationStudentMst) SessionDataUtil
                    .getSessionData(SessionOrganizationStudentMst.class.toString())).getSearchPositionOrganizationId();
            String searchStudentId = ((SessionOrganizationStudentMst) SessionDataUtil
                    .getSessionData(SessionOrganizationStudentMst.class.toString())).getSearchStudentId();
            String searchStudentNm = ((SessionOrganizationStudentMst) SessionDataUtil
                    .getSessionData(SessionOrganizationStudentMst.class.toString())).getSearchStudentNm();
            String searchUseKbn = ((SessionOrganizationStudentMst) SessionDataUtil
                    .getSessionData(SessionOrganizationStudentMst.class.toString())).getSearchUseKbn();

            sessionData.setSearchStudentPosition(searchStudentPosition);                // 検索Key：受講者所属部署
            sessionData.setSearchPositionOrganizationId(searchPositionOrganizationId);  // 検索Key：所属組織内ID
            sessionData.setSearchStudentId(searchStudentId);                            // 検索Key：受講者ID
            sessionData.setSearchStudentNm(searchStudentNm);                            // 検索Key：受講者名(フリガナ)
            sessionData.setSearchUseKbn(searchUseKbn);                                  // 検索Key：利用状態
        } else {
            sessionData = new SessionOrganizationStudentMst();
        }

        sessionData.setReturnOnFlg(true);                                                   // 戻る判定Onフラグ
        sessionData.setProcessKbn(this.model.getProcessKbn());                              // 処理区分
        sessionData.setStudentPosition(this.model.getStudentPosition());                    // 受講者所属部署
        sessionData.setPositionOrganizationId(this.model.getPositionOrganizationId());      // 所属組織内ID
        sessionData.setStudentId(this.model.getStudentId());                                // 受講者ID
        sessionData.setStudentNm(this.model.getStudentNm());                                // 受講者名(フリガナ)
        sessionData.setUseKbn(this.model.getUseKbn());                                      // 利用状態
        sessionData.setOrganizationId(this.model.getOrganizationId());                                      // 利用状態
        sessionData.setOrganizationNm(this.model.getOrganizationNm());                                      // 利用状態
        sessionData.setSearchStudentIdKey(this.getSelect_rdl());                            // 検索Key：選択された明細のKey-商品コード
        sessionData.setHasSearchFlg(this.getHasSearchFlg());                                // 検索判断フラグ
        SessionDataUtil.setSessionData(sessionData);

    }

}
