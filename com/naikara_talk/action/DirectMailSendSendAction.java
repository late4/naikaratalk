package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.DirectMailSendThreadService;
import com.naikara_talk.sessiondata.SessionStudentMst;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>ダイレクトメール送信するActionクラス<br>
 * <b>クラス概要       :</b>ダイレクトメール送信をおこなう。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/30 TECS 新規作成
 */
public class DirectMailSendSendAction extends DirectMailSendActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 画面送信処理。<br>
     * <br>
     * 画面送信処理を行う。<br>
     * <br>
     * @param なし<br>
     * @return String<br>
     * @throws NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // Modelクラス設定
        setupModel();

        // セッション情報セット
        SessionStudentMst sessionData = (SessionStudentMst) SessionDataUtil.getSessionData(SessionStudentMst.class
                .toString());

        this.model.setStudentIdLst(sessionData.getStudentIdLst());
        this.model.setMailAddrLst(sessionData.getMailAddrLst());

        // ユーザID
        this.model.setUserId(((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId());
        // メール送信START
        Thread directMailSendThread = new Thread(new DirectMailSendThreadService(this.model));
        directMailSendThread.start();

        this.closeFlg = NaikaraTalkConstants.TRUE;
        this.resultList.clear();
        this.mailCheck_lst_tmp = NaikaraTalkConstants.BRANK;
        return SUCCESS;
    }
}
