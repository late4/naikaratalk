package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.sessiondata.SessionStudentMst;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>マスタ保守<br>
 * <b>クラス名称　　　:</b>特別無償ポイント設定初期処理Actionクラス。<br>
 * <b>クラス概要　　　:</b>特別無償ポイント設定初期処理Action。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/30 TECS 新規作成
 */
public class StudentSpecialFreePointMstLoadAction extends StudentSpecialFreePointMstActionSupport {

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
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // Modelクラス設定
        setupModel();

        SessionStudentMst sessionData = (SessionStudentMst) SessionDataUtil.getSessionData(SessionStudentMst.class
                .toString());
        this.model.setResultList(sessionData.getSpecialFreeReturnList());
        sessionData.getTempSpecialFreeReturnList().clear();
        sessionData.getTempSpecialFreeReturnList().addAll(this.model.getResultList());

        // 画面を返す
        return SUCCESS;
    }

}
