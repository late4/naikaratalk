package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.UserMstListSearchService;
import com.naikara_talk.sessiondata.SessionUserMst;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守。<br>
 * <b>クラス名称　　　:</b>利用者マスタメンテナンス検索Actionクラス。<br>
 * <b>クラス概要　　　:</b>利用者マスタメンテナンス検索Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/04 TECS 新規作成。
 */
public class UserMstListSearchAction extends UserMstListActionSupport {

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
        UserMstListSearchService service = new UserMstListSearchService();

        // 戻る判定Onフラグ
        boolean returnOnFlg = false;

        if ((SessionUserMst) SessionDataUtil.getSessionData(SessionUserMst.class.toString()) != null) {

            returnOnFlg = ((SessionUserMst) SessionDataUtil.getSessionData(SessionUserMst.class.toString()))
                    .getReturnOnFlg();

            // チェックエラー場合、検索判断フラグ
            this.hasSearchFlg = ((SessionUserMst) SessionDataUtil.getSessionData(SessionUserMst.class.toString()))
                    .getHasSearchFlg();
        }

        if (!returnOnFlg) {

            int chkResult;

            try {

                // データ有無をチェック
                chkResult = service.checkPreSelect(model);

                switch (chkResult) {

                case UserMstListSearchService.ERR_PROS_BTN_MISMATCH:

                    this.addActionMessage(getMessage("EN0036", new String[] { "新規", "登録／選択ボタン" }));

                    return SUCCESS;

                case UserMstListSearchService.ERR_ZERO_DATA:

                    this.addActionMessage(getMessage("EN0020", new String[] { "利用者マスタ", "" }));

                    return SUCCESS;

                case UserMstListSearchService.ERR_MAXOVER_DATA:

                    this.addActionMessage(getMessage("EN0023", new String[] { "101" }));

                    return SUCCESS;

                }
            } catch (Exception e) {

                throw new NaiException(e);

            }
        }

        try {

            // ヘッダ部の戻るボタン押下の場合は値の入れ替え処理
            this.sessionUserMstToModelBefore();

            if (!returnOnFlg || StringUtils.equals(NaikaraTalkConstants.TRUE, this.hasSearchFlg)) {

                // 検索結果
                this.model.setResultList(service.selectList(this.model));

                if (NaikaraTalkConstants.RETURN_CD_DATA_NO == this.model.getResultList().get(0).getReturnCode()) {
                    this.model.getResultList().clear();
                }

                // チェックエラー場合、検索判断フラグ
                this.hasSearchFlg = Boolean.toString(Boolean.TRUE);
            }

        } catch (Exception e) {

            throw new NaiException(e);

        }

        // 一覧から選択された明細データ(jsp)
        this.select_rdl = NaikaraTalkConstants.BRANK;

        try {

            // ヘッダ部の戻るボタン押下の場合は値の入れ替え処理
            this.sessionUserMstToModelAfter();

            // 戻る用に必要な情報を格納
            this.modelToSessionUserMst();

        } catch (Exception e) {

            throw new NaiException(e);

        }

        // メッセージの設定
        if (!StringUtils.isEmpty(this.message)) {
            this.addActionMessage(this.message);
        }

        return SUCCESS;
    }

    /**
     * SessionUserMst値をセット<br>
     * <br>
     * SessionUserMst値をModelにセット<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    private void sessionUserMstToModelBefore() throws Exception {

        if ((SessionUserMst) SessionDataUtil.getSessionData(SessionUserMst.class.toString()) != null) {

            boolean returnOnFlg = ((SessionUserMst) SessionDataUtil.getSessionData(SessionUserMst.class.toString()))
                    .getReturnOnFlg();

            if (returnOnFlg == true) {

                String searchUserId = ((SessionUserMst) SessionDataUtil.getSessionData(SessionUserMst.class.toString()))
                        .getSearchUserId();

                String searchUserKnm = ((SessionUserMst) SessionDataUtil
                        .getSessionData(SessionUserMst.class.toString())).getSearchUserKnm();

                String searchUseKbn = ((SessionUserMst) SessionDataUtil.getSessionData(SessionUserMst.class.toString()))
                        .getSearchUseKbn();

                // 利用者ID
                this.model.setUserId(searchUserId);
                // 利用者名(フリガナ)
                this.model.setUserKnm(searchUserKnm);
                // 利用状態
                this.model.setUseKbn(searchUseKbn);
            }
        }
    }

    /**
     * SessionUserMst値をセット<br>
     * <br>
     * SessionUserMst値をModelにセット<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    private void sessionUserMstToModelAfter() throws Exception {

        if ((SessionUserMst) SessionDataUtil.getSessionData(SessionUserMst.class.toString()) != null) {

            boolean returnOnFlg = ((SessionUserMst) SessionDataUtil.getSessionData(SessionUserMst.class.toString()))
                    .getReturnOnFlg();

            if (returnOnFlg == true) {

                String processKbn = ((SessionUserMst) SessionDataUtil.getSessionData(SessionUserMst.class.toString()))
                        .getProcessKbn();

                String userId = ((SessionUserMst) SessionDataUtil.getSessionData(SessionUserMst.class.toString()))
                        .getUserId();

                String userKnm = ((SessionUserMst) SessionDataUtil.getSessionData(SessionUserMst.class.toString()))
                        .getUserKnm();

                String useKbn = ((SessionUserMst) SessionDataUtil.getSessionData(SessionUserMst.class.toString()))
                        .getUseKbn();

                String select_rdl = ((SessionUserMst) SessionDataUtil.getSessionData(SessionUserMst.class.toString()))
                        .getSearchUserIdKey();

                // 処理区分(jsp)
                this.processKbn_rdl = processKbn;
                // 利用者ID(jsp)
                this.userId_txt = userId;
                // 利用者名(フリガナ)(jsp)
                this.userKnm_txt = userKnm;
                // 利用状態(jsp)
                this.useKbn_rdl = useKbn;
                // 一覧から選択された明細データ(jsp)
                this.select_rdl = select_rdl;
            }

            // sessionSessionUserMstのクリア
            SessionDataUtil.clearSessionData(SessionUserMst.class.toString());
        }
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
        // 戻る判定Onフラグ
        sessionData.setReturnOnFlg(false);
        // 検索Key：利用者ID
        sessionData.setSearchUserId(this.model.getUserId());
        // 検索Key：利用者名(フリガナ)
        sessionData.setSearchUserKnm(this.model.getUserKnm());
        // 検索Key：利用状態
        sessionData.setSearchUseKbn(this.model.getUseKbn());
        // データをセット
        SessionDataUtil.setSessionData(sessionData);
    }
}