package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.TeacherMstListMoveService;
import com.naikara_talk.service.TeacherMstListSearchService;
import com.naikara_talk.sessiondata.SessionTeacherMst;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>講師マスタメンテナンス(一覧)<br>
 * <b>クラス概要       :</b>講師マスタメンテナンス(一覧)選択Action<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/26 TECS 新規作成
 */
public class TeacherMstListMoveAction extends TeacherMstListActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 選択ボタンの処理。<br>
     * <br>
     * 選択ボタンの処理。<br>
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
        TeacherMstListMoveService service = new TeacherMstListMoveService();

        int chkResult = service.nextPageRequest(this.getSelect_rdl(), this.getHasSearchFlg());

        // エラーの場合、メッセージ設定
        try {
            switch (chkResult) {
            case TeacherMstListMoveService.ERR_NO_SELECT:

                this.addActionMessage(getMessage("EN0015", new String[] { "一覧部の左の選択" }));

                TeacherMstListSearchService searchService = new TeacherMstListSearchService();

                // 検索前チェック
                chkResult = searchService.checkPreSelect(this.model);

                // エラーの場合、メッセージ設定
                switch (chkResult) {
                case TeacherMstListSearchService.ERR_ZERO_DATA:
                    return SUCCESS;
                case TeacherMstListSearchService.ERR_MAXOVER_DATA:
                    return SUCCESS;
                }
                // 表示データの取得
                this.model.setResultList(searchService.selectList(this.model));
                // 選択したラジオボタンをクリアする
                this.select_rdl = NaikaraTalkConstants.BRANK;
                this.hasSearchFlg = NaikaraTalkConstants.TRUE;

                return SUCCESS;

            case TeacherMstListMoveService.ERR_LIST_BACK:
                this.addActionMessage(getMessage("EN0015", new String[] { "一覧部の左の選択" }));
                return SUCCESS;
            }

            // 戻る用に必要な情報を取得/格納
            this.modelToSessionTeacherMst();

        } catch (Exception e) {
            throw new NaiException(e);
        }

        // ヘッダの戻るリンク用
        setCurrentActionName(NaikaraTalkConstants.TEACHER_MST_LIST_SEARCH);

        // 詳細画面遷移
        return NEXTPAGE;
    }

    /**
     * Model値 To Session<br>
     * <br>
     * Model値・SessionTeacherMst値をSessionTeacherMstにセット。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    private void modelToSessionTeacherMst() throws Exception {

        // 戻る用に必要な情報を格納
        SessionTeacherMst sessionData = new SessionTeacherMst();

        if ((SessionTeacherMst) SessionDataUtil.getSessionData(SessionTeacherMst.class.toString()) != null) {
            // 戻る用に必要な情報を取得
            String searchUserId = ((SessionTeacherMst) SessionDataUtil.getSessionData(SessionTeacherMst.class
                    .toString())).getSearchUserId();
            String searchUserKnm = ((SessionTeacherMst) SessionDataUtil.getSessionData(SessionTeacherMst.class
                    .toString())).getSearchUserKnm();

            sessionData.setSearchUserId(searchUserId);          // 検索Key：講師ID
            sessionData.setSearchUserKnm(searchUserKnm);        // 検索Key：講師名(フリガナ)
        }

        sessionData.setReturnOnFlg(true);                       // 戻る判定Onフラグ
        sessionData.setProcessKbn(this.model.getProcessKbn());  // 処理区分
        sessionData.setUserId(this.model.getUserId());          // 講師ID
        sessionData.setUserKnm(this.model.getUserKnm());        // 講師名(フリガナ)
        sessionData.setSearchUserIdKey(this.getSelect_rdl());   // 検索Key：選択された明細のKey-講師ID
        sessionData.setHasSearchFlg(this.getHasSearchFlg());    // 検索判断フラグ
        SessionDataUtil.setSessionData(sessionData);

    }

}
