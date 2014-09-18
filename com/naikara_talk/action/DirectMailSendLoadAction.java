package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.sessiondata.SessionStudentMst;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>マスタ保守<br>
 * <b>クラス名称　　　:</b>ダイレクトメール送信初期処理Actionクラス。<br>
 * <b>クラス概要　　　:</b>ダイレクトメール送信初期処理Action。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/30 TECS 新規作成
 */
public class DirectMailSendLoadAction extends DirectMailSendActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * ダイレクトメール送信のサービスの呼び出しの実装。<br>
     * <br>
     * ダイレクトメール送信のサービスの呼び出しの実装<br>
     * <br>
     * @param なし <br>
     * @return String <br>
     * @exception NaiException
     */
    protected String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));
        String[] mailList = this.mailCheck_lst_tmp.split(NaikaraTalkConstants.LOG_COMMA);

        // 宛先IDリスト、宛先リスト、宛先名リストを取得
        for (int i = 0; i < mailList.length; i++) {
            int index = Integer.valueOf(mailList[i]);
            this.studentIdLst.add(this.getResultList().get(index).getStudentId());
            this.mailAddrLst.add(this.getResultList().get(index).getMailAddress1());
        }

        // セッション情報セット
        SessionStudentMst sessionData;
        if ((SessionStudentMst) SessionDataUtil.getSessionData(SessionStudentMst.class.toString()) != null) {
            sessionData = (SessionStudentMst) SessionDataUtil.getSessionData(SessionStudentMst.class.toString());
        } else {
            sessionData = new SessionStudentMst();
        }
        sessionData.setMailAddrLst(mailAddrLst);
        sessionData.setStudentIdLst(studentIdLst);
        SessionDataUtil.setSessionData(sessionData);

        // 画面を返す
        return SUCCESS;
    }
}
