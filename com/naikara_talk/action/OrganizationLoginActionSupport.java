package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.OrganizationLoginModel;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>法人<br>
 * <b>クラス名称　　　:</b>ログイン(組織)初期処理Actionスーパークラス。<br>
 * <b>クラス概要　　　:</b>組織責任者がログインID、パスワードの入力によりシステムの認証を行う。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/05 TECS 新規作成。
 */
public abstract class OrganizationLoginActionSupport extends CertificationActionSupport {

    private static final long serialVersionUID = 1L;

    // Help画面名
    protected String helpPageId = "HelpOrganizationLogin.html";

    // 画面表示のタイトル
    protected String title = "Organization Login Page";

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
        this.model.setLoginId(this.loginId_txt);
        this.model.setPassword(this.password_txt);
    }

    /** ログインＩＤ(jsp) */
    protected String loginId_txt;

    /** パスワード(jsp) */
    protected String password_txt;

    /** 登録結果 */
    protected OrganizationLoginModel model = new OrganizationLoginModel();

    /**
     * @return helpPageId
     */
    public String getHelpPageId() {
        return helpPageId;
    }

    /**
     * @param helpPageId
     *            セットする helpPageId
     */
    public void setHelpPageId(String helpPageId) {
        this.helpPageId = helpPageId;
    }

    /**
     * @return loginId_txt
     */
    public String getLoginId_txt() {
        return loginId_txt;
    }

    /**
     * @param loginId_txt
     *            セットする loginId_txt
     */
    public void setLoginId_txt(String login_txt) {
        this.loginId_txt = login_txt;
    }

    /**
     * @return password_txt
     */
    public String getPassword_txt() {
        return password_txt;
    }

    /**
     * @param password_txt
     *            セットする password_txt
     */
    public void setPassword_txt(String password_txt) {
        this.password_txt = password_txt;
    }

    /**
     * @return model
     */
    public OrganizationLoginModel getModel() {
        return model;
    }

    /**
     * @param model
     *            セットする model
     */
    public void setModel(OrganizationLoginModel model) {
        this.model = model;
    }

}
