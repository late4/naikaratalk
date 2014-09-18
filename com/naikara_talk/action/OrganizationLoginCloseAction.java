package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.sessiondata.ScreenComInfo;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>法人<br>
 * <b>クラス名称　　　:</b>ログイン(組織)登録Actionクラス。<br>
 * <b>クラス概要　　　:</b>組織責任者の登録処理 TOPページ画面遷移を行う。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/05 TECS 新規作成。
 */
public class OrganizationLoginCloseAction extends OrganizationLoginActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 終了ボタンの処理<br>
     * <br>
     * 終了ボタンの処理を行う<br>
     * <br>
     * @param なし<br>
     * @return SUCCESS 画面遷移フラグ<br>
     * @exception NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));
        // 指定のSessionData型のセッションデータをクリアする
        SessionDataUtil.clearSessionData(SessionUser.class.toString());
        SessionDataUtil.clearSessionData(ScreenComInfo.class.toString());

        return NEXTPAGE;
    }
}
