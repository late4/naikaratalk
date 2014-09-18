package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.sessiondata.SessionReservationCancellationTeacherSchedule;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_予約処理<br>
 * <b>クラス名称       :</b>予約スケジュール初期化Actionクラス<br>
 * <b>クラス概要       :</b>受講者が指定した講師の予約スケジュールを表示して、レッスン予約の登録／取消ができる。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/25 TECS 新規作成
 */
public class ReservationCancellationTeacherScheduleLoadAction extends
        ReservationCancellationTeacherScheduleActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 画面の初期表示。<br>
     * <br>
     * 画面の初期表示を設定する。<br>
     * <br>
     * @param なし<br>
     * @return String<br>
     * @throws NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        try {

            // ヘッダ部の戻るボタン押下の場合は値の入れ替え処理
            this.SessionReservationCancellationTeacherScheduleToModelBefore();

            // 初期処理、表示データの取得
            this.load();

        } catch (Exception e) {
            throw new NaiException(e);
        }

        return SUCCESS;
    }

    /**
     * Session値 To Model<br>
     * <br>
     * SessionReservationCancellationTeacherSchedule値をModelにセット。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    private void SessionReservationCancellationTeacherScheduleToModelBefore() throws Exception {
        if (StringUtils.isEmpty(this.teacherId)) {
            if ((SessionReservationCancellationTeacherSchedule) SessionDataUtil
                    .getSessionData(SessionReservationCancellationTeacherSchedule.class.toString()) != null) {
                // 講師ID
                String teacherId = ((SessionReservationCancellationTeacherSchedule) SessionDataUtil
                        .getSessionData(SessionReservationCancellationTeacherSchedule.class.toString())).getTeacherId();
                // 日数
                int dayCnt = ((SessionReservationCancellationTeacherSchedule) SessionDataUtil
                        .getSessionData(SessionReservationCancellationTeacherSchedule.class.toString())).getDayCnt();
                // 朝・午前のレッスンから選択されたチェックBOX
                String[] details0 = ((SessionReservationCancellationTeacherSchedule) SessionDataUtil
                        .getSessionData(SessionReservationCancellationTeacherSchedule.class.toString())).getDetails0();
                // 午後・夕方のレッスンから選択されたチェックBOX
                String[] details1 = ((SessionReservationCancellationTeacherSchedule) SessionDataUtil
                        .getSessionData(SessionReservationCancellationTeacherSchedule.class.toString())).getDetails1();
                // 夕方・夜のレッスンから選択されたチェックBOX
                String[] details2 = ((SessionReservationCancellationTeacherSchedule) SessionDataUtil
                        .getSessionData(SessionReservationCancellationTeacherSchedule.class.toString())).getDetails2();
                // 夕方・夜のレッスンから選択されたチェックBOX
                String[] details3 = ((SessionReservationCancellationTeacherSchedule) SessionDataUtil
                        .getSessionData(SessionReservationCancellationTeacherSchedule.class.toString())).getDetails3();
                // 深夜・早朝のレッスン表示フラグ
                boolean displayFlg0 = ((SessionReservationCancellationTeacherSchedule) SessionDataUtil
                        .getSessionData(SessionReservationCancellationTeacherSchedule.class.toString()))
                        .getDisplayFlg0();
                // 朝・午前のレッスン表示フラグ
                boolean displayFlg1 = ((SessionReservationCancellationTeacherSchedule) SessionDataUtil
                        .getSessionData(SessionReservationCancellationTeacherSchedule.class.toString()))
                        .getDisplayFlg1();
                // 午後・夕方のレッスン表示フラグ
                boolean displayFlg2 = ((SessionReservationCancellationTeacherSchedule) SessionDataUtil
                        .getSessionData(SessionReservationCancellationTeacherSchedule.class.toString()))
                        .getDisplayFlg2();
                // 夕方・夜のレッスン表示フラグ
                boolean displayFlg3 = ((SessionReservationCancellationTeacherSchedule) SessionDataUtil
                        .getSessionData(SessionReservationCancellationTeacherSchedule.class.toString()))
                        .getDisplayFlg3();

                // 講師ID
                this.teacherId = teacherId;
                // 日数
                this.dayCnt = dayCnt;
                // 朝・午前のレッスンチェックBOX
                this.details0_chkn = details0;
                // 朝・午前のレッスンチェックBOX
                this.details1_chkn = details1;
                // 午後・夕方のレッスンチェックBOX
                this.details2_chkn = details2;
                // 夕方・夜のレッスンチェックBOX
                this.details3_chkn = details3;
                // 深夜・早朝のレッスン表示フラグ
                this.displayFlg0 = displayFlg0;
                // 朝・午前のレッスン表示フラグ
                this.displayFlg1 = displayFlg1;
                // 午後・夕方のレッスン表示フラグ
                this.displayFlg2 = displayFlg2;
                // 夕方・夜のレッスン表示フラグ
                this.displayFlg3 = displayFlg3;

                // SessionReservationCancellationTeacherScheduleのクリア
                SessionDataUtil.clearSessionData(SessionReservationCancellationTeacherSchedule.class.toString());
            }
        }
    }
}
