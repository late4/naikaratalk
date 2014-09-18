package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.CsvFileDeleteService;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>ログイン・メニュー<br>
 * <b>クラス名称　　　:</b>管理ページ初期処理Actionクラス。<br>
 * <b>クラス概要　　　:</b>管理ページ初期処理Action。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/1 TECS 新規作成
 */
public class SystemMenuLoadAction extends NaikaraActionSupport {

    private static final long serialVersionUID = 1L;

    // 画面表示のタイトル
    protected String title = "システムメニュー";

    // Help画面名
    protected String helpPageId = "HelpSystemMenu.html";

    // ユーザロール
    protected String userRole;

    /**
     * 管理ページのサービスの呼び出しの実装。<br>
     * <br>
     * 管理ページのサービスの呼び出しの実装<br>
     * <br>
     * @param なし <br>
     * @return String <br>
     * @exception NaiException
     */
    protected String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // 過去に作成したCSVファイルの削除処理
        CsvFileDeleteService.removeCsvFile();

        // ユーザ権限を取得
        this.userRole = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getRole();

        // ヘッダの戻るリンク用
        setCurrentActionName(NaikaraTalkConstants.SYSTEM_MENU_LOAD);

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

}
