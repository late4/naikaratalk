package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraStringUtil;
//import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>講師スケジュールページ。<br>
 * <b>クラス名称       :</b>講師スケジュールページ初期処理Actionクラス。<br>
 * <b>クラス概要       :</b>講師スケジュールが下記の情報照会ができる<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/09 TECS 新規作成
 * <b>                 :</b>2014/06/02 TECS レッスン予約に関する「応相談」対応
 */
public class TeacherScheduleLoadAction extends TeacherScheduleActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 画面の初期表示。<br>
     * <br>
     *
     * @param なし<br>
     * @return String SUCCESS<br>
     * @exception NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));
        this.displayFlg0 = false;    // ｢00：00 - 06：00｣部分 TeacherScheduleMidnightSearchAction
        this.displayFlg1 = false;    // ｢06：00 - 12：00｣部分 TeacherScheduleMorningSearchAction
        this.displayFlg2 = false;    // ｢12：00 - 18：00｣部分 TeacherScheduleDaytimeSearchAction
        this.displayFlg3 = false;    // ｢18：00 - 24：00｣部分 TeacherScheduleNightSearchAction

        // 2014/06/02 レッスン予約に関する「応相談」対応 Upd Start
        /** ｢00：00 - 06：00｣部分のチェックBOX⇒ドロップダウン */
        //this.Midnight_chkn = null;
        this.midnight_sel = null;                                      // 予約受付不可
        /** ｢06：00 - 12：00｣部分のチェックBOX⇒ドロップダウン */
        //this.Morning_chkn = null;
        this.morning_sel = null;                                       // 予約受付不可
        /** ｢12：00 - 18：00｣部分のチェックBOX⇒ドロップダウン */
        //this.Daytime_chkn = null;
        this.daytime_sel = null;                                       // 予約受付不可
        /** ｢18：00 - 24：00｣部分のチェックBOX⇒ドロップダウン */
        //this.Night_chkn = null;
        this.night_sel = null;                                         // 予約受付不可
        // 2014/06/02 レッスン予約に関する「応相談」対応 Upd End

        this.dateFlg = 0;
        // 表示データの取得
        String resultUrl = this.load();

        if (!StringUtils.isEmpty(this.message)) {
            this.addActionMessage(this.message);
        }
        return resultUrl;
    }

}
