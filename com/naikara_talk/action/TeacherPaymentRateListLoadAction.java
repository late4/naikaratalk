package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.TeacherPaymentRateListLoadService;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>支払単価表。<br>
 * <b>クラス名称       :</b>支払単価表初期処理Actionクラス。<br>
 * <b>クラス概要       :</b>支払比率で、コース単位の支払単価の一覧表示を行う<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/09 TECS 新規作成
 */
public class TeacherPaymentRateListLoadAction extends TeacherPaymentRateListActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 画面の初期表示。<br>
     * <br>
     *
     * @param なし<br>
     * @return String SUCCESS <br>
     * @exception NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // 表示データを取得する
        TeacherPaymentRateListLoadService service = new TeacherPaymentRateListLoadService();
        // 前画面の情報
        this.model.setUserId(((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId());
        // 講師予定予約テーブルデータの取得処理
        try {
            this.model = service.select(this.model);
        } catch (Exception e) {
            throw new NaiException(e);
        }
        /** 利用者ID */
        this.userId = model.getUserId();
        /** 時間  */
        this.today = DateUtil.getSysDate();

        return SUCCESS;
    }

}
