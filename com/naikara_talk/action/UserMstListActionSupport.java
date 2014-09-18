package com.naikara_talk.action;

import java.util.LinkedHashMap;
import java.util.Map;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.UserMstListModel;
import com.naikara_talk.service.UserMstListLoadService;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守。<br>
 * <b>クラス名称　　　:</b>利用者マスタメンテナンスActionスーパークラス。<br>
 * <b>クラス概要　　　:</b>利用者マスタメンテナンスAction。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/04 TECS 新規作成。
 */
public abstract class UserMstListActionSupport extends NaikaraActionSupport {

    private static final long serialVersionUID = 1L;

    /** 画面表示のタイトル */
    protected String title = "利用者マスタメンテナンス";

    /** Help画面名 */
    protected String helpPageId = "HelpUserMstList.html";

    /**
     * ラジオを初期化<br>
     * <br>
     * 画面ラジオを初期化する<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception なし
     */
    @Override
    public void validate() {

        try {

            initRadio();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    /**
     * サービスの呼び出しの実装。
     */
    abstract protected String requestService() throws NaiException;

    /**
     * ラジオを取得<br>
     * <br>
     * 利用状態を取得する<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    public void initRadio() throws Exception {

        UserMstListLoadService service = new UserMstListLoadService();

        // 利用状態を取得する
        this.useKbn_rdll = service
                .selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_USE_STATE);
    }

    /**
     * 画面のパラメータセット<br>
     * <br>
     * 画面のパラメータをモデルにセット<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception なし
     */
    public void setupModel() {

        // 処理区分(jsp)
        this.model.setProcessKbn(this.processKbn_rdl);
        // 利用者ID(jsp)
        this.model.setUserId(this.userId_txt);
        // 利用者名(フリガナ)(jsp)
        this.model.setUserKnm(this.userKnm_txt);
        // 利用状態(jsp)
        this.model.setUseKbn(this.useKbn_rdl);
    }

    /** メッセージ */
    protected String message;

    /** 処理区分(jsp) */
    protected String processKbn_rdl;

    /** 利用者ID(jsp) */
    protected String userId_txt;

    /** 利用者名(フリガナ)(jsp) */
    protected String userKnm_txt;

    /** 利用状態(jsp) */
    protected String useKbn_rdl;

    /** 利用状態(jsp)リスト */
    protected Map<String, String> useKbn_rdll = new LinkedHashMap<String, String>();

    /** 検索結果 */
    protected UserMstListModel model = new UserMstListModel();

    /** 一覧から選択された明細データ(jsp) */
    protected String select_rdl;

    /** チェックエラー場合、検索判断フラグ */
    protected String hasSearchFlg;

    /**
     * チェックエラー場合、検索判断フラグ取得<br>
     * <br>
     * チェックエラー場合、検索判断フラグ取得を行う<br>
     * <br>
     * @param なし<br>
     * @return hasSearchFlg チェックエラー場合、検索判断フラグ<br>
     * @exception なし
     */
    public String getHasSearchFlg() {
        return hasSearchFlg;
    }

    /**
     * チェックエラー場合、検索判断フラグ設定<br>
     * <br>
     * チェックエラー場合、検索判断フラグ設定を行う<br>
     * <br>
     * @param hasSearchFlg チェックエラー場合、検索判断フラグ<br>
     * @return なし<br>
     * @exception なし
     */
    public void setHasSearchFlg(String hasSearchFlg) {
        this.hasSearchFlg = hasSearchFlg;
    }

    /**
     * Help画面名取得<br>
     * <br>
     * Help画面名取得を行う<br>
     * <br>
     * @param なし<br>
     * @return helpPageId Help画面名<br>
     * @exception なし
     */
    public String getHelpPageId() {
        return helpPageId;
    }

    /**
     * Help画面名設定<br>
     * <br>
     * Help画面名設定を行う<br>
     * <br>
     * @param helpPageId Help画面名<br>
     * @return なし<br>
     * @exception なし
     */
    public void setHelpPageId(String helpPageId) {
        this.helpPageId = helpPageId;
    }

    /**
     * 画面表示のタイトル取得<br>
     * <br>
     * 画面表示のタイトル取得を行う<br>
     * <br>
     * @param なし<br>
     * @return title 画面表示のタイトル<br>
     * @exception なし
     */
    public String getTitle() {
        return title;
    }

    /**
     * 画面表示のタイトル設定<br>
     * <br>
     * 画面表示のタイトル設定を行う<br>
     * <br>
     * @param title 画面表示のタイトル<br>
     * @return なし<br>
     * @exception なし
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * メッセージ取得<br>
     * <br>
     * メッセージ取得を行う<br>
     * <br>
     * @param なし<br>
     * @return message メッセージ<br>
     * @exception なし
     */
    public String getMessage() {
        return message;
    }

    /**
     * メッセージ設定<br>
     * <br>
     * メッセージ設定を行う<br>
     * <br>
     * @param message メッセージ<br>
     * @return なし<br>
     * @exception なし
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 処理区分(jsp)取得<br>
     * <br>
     * 処理区分(jsp)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return processKbn_rdl 処理区分(jsp)<br>
     * @exception なし
     */
    public String getProcessKbn_rdl() {
        return processKbn_rdl;
    }

    /**
     * 処理区分(jsp)設定<br>
     * <br>
     * 処理区分(jsp)設定を行う<br>
     * <br>
     * @param processKbn_rdl 処理区分(jsp)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setProcessKbn_rdl(String processKbn_rdl) {
        this.processKbn_rdl = processKbn_rdl;
    }

    /**
     * 利用者ID(jsp)取得<br>
     * <br>
     * 利用者ID(jsp)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return userId_txt 利用者ID(jsp)<br>
     * @exception なし
     */
    public String getUserId_txt() {
        return userId_txt;
    }

    /**
     * 利用者ID(jsp)設定<br>
     * <br>
     * 利用者ID(jsp)設定を行う<br>
     * <br>
     * @param userId_txt 利用者ID(jsp)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setUserId_txt(String userId_txt) {
        this.userId_txt = userId_txt;
    }

    /**
     * 利用者名(フリガナ)(jsp)取得<br>
     * <br>
     * 利用者名(フリガナ)(jsp)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return userKnm_txt 利用者名(フリガナ)(jsp)<br>
     * @exception なし
     */
    public String getUserKnm_txt() {
        return userKnm_txt;
    }

    /**
     * 利用者名(フリガナ)(jsp)設定<br>
     * <br>
     * 利用者名(フリガナ)(jsp)設定を行う<br>
     * <br>
     * @param userKnm_txt 利用者名(フリガナ)(jsp)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setUserKnm_txt(String userKnm_txt) {
        this.userKnm_txt = userKnm_txt;
    }

    /**
     * 利用状態(jsp)取得<br>
     * <br>
     * 利用状態(jsp)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return useKbn_rdl 利用状態(jsp)<br>
     * @exception なし
     */
    public String getUseKbn_rdl() {
        return useKbn_rdl;
    }

    /**
     * 利用状態(jsp)設定<br>
     * <br>
     * 利用状態(jsp)設定を行う<br>
     * <br>
     * @param useKbn_rdl 利用状態(jsp)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setUseKbn_rdl(String useKbn_rdl) {
        this.useKbn_rdl = useKbn_rdl;
    }

    /**
     * 利用状態(jsp)リスト取得<br>
     * <br>
     * 利用状態(jsp)リスト取得を行う<br>
     * <br>
     * @param なし<br>
     * @return useKbn_rdll 利用状態(jsp)リスト<br>
     * @exception なし
     */
    public Map<String, String> getUseKbn_rdll() {
        return useKbn_rdll;
    }

    /**
     * 利用状態(jsp)リスト設定<br>
     * <br>
     * 利用状態(jsp)リスト設定を行う<br>
     * <br>
     * @param useKbn_rdll 利用状態(jsp)リスト<br>
     * @return なし<br>
     * @exception なし
     */
    public void setUseKbn_rdll(Map<String, String> useKbn_rdll) {
        this.useKbn_rdll = useKbn_rdll;
    }

    /**
     * 画面モデル取得<br>
     * <br>
     * 画面モデル取得を行う<br>
     * <br>
     * @param なし<br>
     * @return model 画面モデル<br>
     * @exception なし
     */
    public UserMstListModel getModel() {
        return model;
    }

    /**
     * 画面モデル設定<br>
     * <br>
     * 画面モデル設定を行う<br>
     * <br>
     * @param model 画面モデル<br>
     * @return なし<br>
     * @exception なし
     */
    public void setModel(UserMstListModel model) {
        this.model = model;
    }

    /**
     * 一覧から選択された明細データ(jsp)取得<br>
     * <br>
     * 一覧から選択された明細データ(jsp)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return select_rdl 一覧から選択された明細データ(jsp)<br>
     * @exception なし
     */
    public String getSelect_rdl() {
        return select_rdl;
    }

    /**
     * 一覧から選択された明細データ(jsp)設定<br>
     * <br>
     * 一覧から選択された明細データ(jsp)設定を行う<br>
     * <br>
     * @param select_rdl 一覧から選択された明細データ(jsp)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setSelect_rdl(String select_rdl) {
        this.select_rdl = select_rdl;
    }
}