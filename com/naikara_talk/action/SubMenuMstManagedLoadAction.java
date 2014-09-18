package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>マスタ保守<br>
 * <b>クラス名称　　　:</b>マスタ保守サブメニュー初期処理Actionクラス。<br>
 * <b>クラス概要　　　:</b>マスタ保守サブメニュー初期処理Action。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/1 TECS 新規作成
 */
public class SubMenuMstManagedLoadAction extends NaikaraActionSupport {

    private static final long serialVersionUID = 1L;

    // 画面表示のタイトル
    protected String title = "マスタ保守サブメニュー";

    // Help画面名
    protected String helpPageId = "HelpSubMenuMstManaged.html";

    // ユーザロール
    protected String userRole;

    // メッセージ
    protected String message;

    /**
     * マスタ保守サブメニューのサービスの呼び出しの実装。<br>
     * <br>
     * マスタ保守サブメニューのサービスの呼び出しの実装<br>
     * <br>
     * @param なし <br>
     * @return String <br>
     * @exception NaiException
     */
    protected String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // ユーザ権限を取得
        this.userRole = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getRole();

        if (!StringUtils.isEmpty(this.message)) {
            this.addActionMessage(this.message);
        }

        // 画面を返す
        return SUCCESS;
    }

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title セットする title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return helpPageId
     */
    public String getHelpPageId() {
        return helpPageId;
    }

    /**
     * @param helpPageId セットする helpPageId
     */
    public void setHelpPageId(String helpPageId) {
        this.helpPageId = helpPageId;
    }

    /**
     * @return userRole
     */
    public String getUserRole() {
        return userRole;
    }

    /**
     * @param userRole セットする userRole
     */
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    /**
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param  message
     *            セットする message
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
