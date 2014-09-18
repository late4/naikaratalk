package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.SchoolLoginModel;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>ログイン・メニュー。<br>
 * <b>クラス名称　　　:</b>会社側のログイン処理Actionスーパークラス。<br>
 * <b>クラス概要　　　:</b>会社側のログイン処理Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/02 TECS 新規作成。
 */
public abstract class SchoolLoginActionSupport extends CertificationActionSupport {

    private static final long serialVersionUID = 1L;

    // 画面表示のタイトル
    protected String title = "システムログインページ";

    // Help画面名
    protected String helpPageId = "HelpSchoolLogin.html";

    /**
     * サービスの呼び出しの実装。
     */
    abstract protected String requestService() throws NaiException;

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

        // ログインID
        this.model.setLoginId(this.loginId_txt);

        // パスワード
        this.model.setPassword(this.password_txt);
    }

    /** ログインID(jsp) */
    protected String loginId_txt;

    /** パスワード(jsp) */
    protected String password_txt;

    /** 画面モデル */
    protected SchoolLoginModel model = new SchoolLoginModel();

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
     * ログインID(jsp)取得<br>
     * <br>
     * ログインID(jsp)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return loginId_txt ログインID(jsp)<br>
     * @exception なし
     */
    public String getLoginId_txt() {
        return loginId_txt;
    }

    /**
     * ログインID(jsp)設定<br>
     * <br>
     * ログインID(jsp)設定を行う<br>
     * <br>
     * @param loginId_txt ログインID(jsp)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setLoginId_txt(String loginId_txt) {
        this.loginId_txt = loginId_txt;
    }

    /**
     * パスワード(jsp)取得<br>
     * <br>
     * パスワード(jsp)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return password_txt パスワード(jsp)<br>
     * @exception なし
     */
    public String getPassword_txt() {
        return password_txt;
    }

    /**
     * パスワード(jsp)設定<br>
     * <br>
     * パスワード(jsp)設定を行う<br>
     * <br>
     * @param password_txt パスワード(jsp)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setPassword_txt(String password_txt) {
        this.password_txt = password_txt;
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
    public SchoolLoginModel getModel() {
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
    public void setModel(SchoolLoginModel model) {
        this.model = model;
    }
}