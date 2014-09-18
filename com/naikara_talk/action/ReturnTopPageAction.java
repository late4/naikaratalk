package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraStringUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b><br>
 * <b>クラス名称       :</b>受講者用マイページにある「トップページへ戻る」の画面遷移Actionクラス。<br>
 * <b>クラス概要       :</b>actionList(戻るリスト)の削除とトップページの画面遷移を行う。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/11/15 TECS 新規作成
 */
public class ReturnTopPageAction extends NaikaraActionSupport {

    private static final long serialVersionUID = 1L;

    private static final String ACTION_RIST = "actionList";

    /**
     * actionList(戻るリスト)の削除とトップページの画面遷移。<br>
     * <br>
     * actionList(戻るリスト)の削除とトップページの画面遷移を行う。<br>
     * <br>
     * @param なし
     * @return String
     * @throws NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        if (this.session.containsKey(ACTION_RIST)) {
            // actionList(戻るリスト)がhashTableのKeyかどうかを判定=Trueの場合
            // actionList(戻るリスト)をhashTableから削除
            this.session.remove(ACTION_RIST);
        }

        // TOPページへ遷移
        return NEXTPAGE;

    }


}
