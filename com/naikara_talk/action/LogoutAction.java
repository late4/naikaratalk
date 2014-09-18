package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.sessiondata.SessionUser;
import org.apache.commons.lang3.StringUtils;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>ログイン・メニュー<br>
 * <b>クラス名称       :</b>管理ページの画面遷移Actionクラス。<br>
 * <b>クラス概要       :</b>管理ページの画面遷移を行う。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/30 TECS 新規作成
 */
public class LogoutAction extends NaikaraActionSupport {

    private static final long serialVersionUID = 1L;

    /** 画面遷移用(法人責任者、受講者) */
    public static final String NEXTPAGE_TOP = "nextTop";

    /**
     * 画面の初期表示。<br>
     * <br>
     * 画面の初期表示を設定する。<br>
     * <br>
     * @param なし
     * @return String
     * @throws NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // セッションの無効化
        request.getSession().invalidate();

        if (StringUtils.equals(pageId, SessionUser.ROLE_ORGANIZATION)
                || StringUtils.equals(pageId, SessionUser.ROLE_STUDENT)) {
            // 組織責任者 又は 受講者はTOPページ
            return NEXTPAGE_TOP;
        }

        // 画面を返す
        return NEXTPAGE;
    }

    protected String pageId;

    /**
     * @return pageId
     */
    public String getPageId() {
        return pageId;
    }

    /**
     * @param pageId セットする pageId
     */
    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

}
