package com.naikara_talk.service;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.DirectMailSendModel;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守<br>
 * <b>クラス名称　　　:</b>受講者マスタメンテナンス ダイレクトメール送信ThreadServiceクラス。<br>
 * <b>クラス概要　　　:</b>受講者マスタメンテナンス ダイレクトメール送信をおこなう。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/09/23 TECS 新規作成。
 */
public class DirectMailSendThreadService implements Runnable {

    /** ダイレクトメール送信Model */
    private DirectMailSendModel model;

    /** コンストラクタ */
    public DirectMailSendThreadService(DirectMailSendModel model) {
        this.model = model;
    }

    /**
     * メール送信処理<br>
     * <br>
     * メール送信処理を行う。<br>
     * <br>
     * @return なし <br>
     */
    @Override
    public void run() {
        DirectMailSendService service = new DirectMailSendService();
        try {
            service.sendMail(this.model);
        } catch (NaiException e) {
            e.printStackTrace();
        }
    }

}
