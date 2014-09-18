package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.UserMstListMoveService;
import com.naikara_talk.service.UserMstListSearchService;
import com.naikara_talk.sessiondata.SessionUserMst;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守。<br>
 * <b>クラス名称　　　:</b>利用者マスタメンテナンス登録選択Actionクラス。<br>
 * <b>クラス概要　　　:</b>利用者マスタメンテナンス登録選択Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/04 TECS 新規作成。
 */
public class UserMstListMoveAction extends UserMstListActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 初期処理<br>
     * <br>
     * 画面項目の初期処理を行う<br>
     * <br>
     * @param なし<br>
     * @return SUCCESS 画面遷移フラグ<br>
     * @exception NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // 画面のパラメータセット
        setupModel();

        // サービスの初期化
        UserMstListMoveService service = new UserMstListMoveService();

        // 単票をチェック
        int chkResult = service.nextPageRequest(this.model,
                this.getSelect_rdl(), this.getHasSearchFlg());

        try {

            switch (chkResult) {

            case UserMstListMoveService.ERR_NO_SELECT:

                this.addActionMessage(getMessage("EN0015",
                        new String[] { "一覧部の左の選択" }));

                // サービスの初期化
                UserMstListSearchService searchService = new UserMstListSearchService();

                // データ有無をチェック
                chkResult = searchService.checkPreSelect(model);

                switch (chkResult) {

                case UserMstListSearchService.ERR_PROS_BTN_MISMATCH:

                    return SUCCESS;

                case UserMstListSearchService.ERR_ZERO_DATA:

                    return SUCCESS;

                case UserMstListSearchService.ERR_MAXOVER_DATA:

                    return SUCCESS;

                }

                // 検索結果一覧
                this.model.setResultList(searchService.selectList(this.model));

                // 一覧から選択された明細データ(jsp)
                this.select_rdl = NaikaraTalkConstants.BRANK;

                // チェックエラー場合、検索判断フラグ
                this.hasSearchFlg = NaikaraTalkConstants.TRUE;

                return SUCCESS;

            case UserMstListMoveService.ERR_LIST_BACK:

                this.addActionMessage(getMessage("EN0015",
                        new String[] { "一覧部の左の選択" }));

                return SUCCESS;
            }

            // 戻る用に必要な情報を取得/格納
            this.modelToSessionUserMst();

        } catch (Exception e) {

            throw new NaiException(e);

        }

        // ヘッダの戻るリンク用
        setCurrentActionName(NaikaraTalkConstants.USER_MST_LIST_SEARCH);

        return NEXTPAGE;
    }

    /**
     * Model値をセット<br>
     * <br>
     * Model値・SessionUserMst値をSessionUserMstにセット<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    private void modelToSessionUserMst() throws Exception {

        // セッション情報の初期化
        SessionUserMst sessionData = new SessionUserMst();

        if ((SessionUserMst) SessionDataUtil
                .getSessionData(SessionUserMst.class.toString()) != null) {

            String searchUserId = ((SessionUserMst) SessionDataUtil
                    .getSessionData(SessionUserMst.class.toString()))
                    .getSearchUserId();

            String searchUserKnm = ((SessionUserMst) SessionDataUtil
                    .getSessionData(SessionUserMst.class.toString()))
                    .getSearchUserKnm();

            String searchUseKbn = ((SessionUserMst) SessionDataUtil
                    .getSessionData(SessionUserMst.class.toString()))
                    .getSearchUseKbn();

            // 検索Key：利用者ID
            sessionData.setSearchUserId(searchUserId);
            // 検索Key：利用者名(フリガナ)
            sessionData.setSearchUserKnm(searchUserKnm);
            // 検索Key：利用状態
            sessionData.setSearchUseKbn(searchUseKbn);
        }

        // 戻る判定Onフラグ
        sessionData.setReturnOnFlg(true);
        // 処理区分
        sessionData.setProcessKbn(this.model.getProcessKbn());
        // 利用者ID
        sessionData.setUserId(this.model.getUserId());
        // 利用者名(フリガナ)
        sessionData.setUserKnm(this.model.getUserKnm());
        // 利用状態
        sessionData.setUseKbn(this.model.getUseKbn());
        // 検索Key：選択された明細のKey-利用者ID
        sessionData.setSearchUserIdKey(this.getSelect_rdl());
        // 検索判断フラグ
        sessionData.setHasSearchFlg(this.getHasSearchFlg());
        // データをセット
        SessionDataUtil.setSessionData(sessionData);
    }
}