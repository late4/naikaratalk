package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_初期登録<br>
 * <b>クラス名称       :</b>問合せ画面送信確認Actionクラス。<br>
 * <b>クラス概要       :</b>メール送信をおこなう。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/11 TECS 新規作成
 */
public class ContactMailSendConfirmAction extends ContactMailSendActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 送信確認ボタンの処理。<br>
     * <br>
     * 送信確認ボタンの処理。<br>
     * <br>
     * @param なし<br>
     * @return String NEXTPAGE<br>
     * @exception NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        this.pageFlg = NaikaraTalkConstants.TRUE;

        // 詳細画面遷移
        return NEXTPAGE;
    }

}
