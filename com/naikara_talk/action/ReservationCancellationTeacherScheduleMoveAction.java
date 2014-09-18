package com.naikara_talk.action;

import java.util.List;

import com.naikara_talk.dto.SchResTLesResPerTDto;
import com.naikara_talk.dto.TeacherCourseDto;
import com.naikara_talk.exception.NaiException;
//import com.naikara_talk.service.ReservationCancellationCourseListMoveService;
import com.naikara_talk.service.ReservationCancellationTeacherScheduleLoadService;
import com.naikara_talk.service.ReservationCancellationTeacherScheduleMoveService;
import com.naikara_talk.sessiondata.SessionReservationCancellationTeacherSchedule;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_予約処理<br>
 * <b>クラス名称       :</b>予約スケジュール画面遷移Actionクラス<br>
 * <b>クラス概要       :</b>受講者が指定した講師の予約スケジュールを表示して、レッスン予約の登録／取消ができる。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/25 TECS 新規作成
 * <b>                 :</b>2014/06/02 TECS レッスン予約に関する「応相談」対応
 */
public class ReservationCancellationTeacherScheduleMoveAction extends
        ReservationCancellationTeacherScheduleActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 画面遷移前処理。<br>
     * <br>
     * 画面遷移前処理を行う。<br>
     * <br>
     * @param なし<br>
     * @return String<br>
     * @throws NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        try {
            // 一覧選択のデータの取得
            // 明細0チェックBOX
            this.load(this.details0_chkn);
            // 明細1チェックBOX
            this.load(this.details1_chkn);
            // 明細2チェックBOX
            this.load(this.details2_chkn);
            // 明細3チェックBOX
            this.load(this.details3_chkn);

            this.setupModel();

            ReservationCancellationTeacherScheduleMoveService service = new ReservationCancellationTeacherScheduleMoveService();

            // エラーフラグ
            boolean errFlg = false;

            // 必須チェック
            if (!service.requiredCheck(this.model)) {
                errFlg = true;
                // メッセージ情報を設定する
                this.addActionMessage(getMessage("EC0050", new String[] { "予約/予約取消", "コース名選択へ" }));

            } else {
                // 関連チェック
                for (SchResTLesResPerTDto dto : this.schResTLesResPerTDtoList) {

                    int chkResult = service.relCheck(dto, this.teacherId);
                    switch (chkResult) {
                    case ReservationCancellationTeacherScheduleMoveService.ERR_LESSON_TM:

                        errFlg = true;
                        // ◆◆◆10-2.予約可能/予約取消可能かどうかのチェック (1) 予約の場合 ◆◆◆
                        // メッセージ情報を設定する
                        this.addActionMessage(getMessage(
                                "EC0067",
                                new String[] { service.getLessonDateTime(
                                        NaikaraStringUtil.converToM_D(dto.getLessonDt()), dto.getLessonTmCd(), false) }));
                        break;


                        // 2014/06/02 レッスン予約に関する「応相談」対応 Add Start
                    case ReservationCancellationTeacherScheduleMoveService.ERR_LESSON_TM_CON:

                        errFlg = true;
                        // ◆◆◆10-XX.予約可能/予約取消可能かどうかのチェック 応相談予約の場合 ◆◆◆
                        // メッセージ情報を設定する
                        // %1分の予約受付は終了しております。応相談のご予約はレッスン開始３時間前にお願いいたします。
                        this.addActionMessage(getMessage(
                                "EC0079",
                                new String[] { service.getLessonDateTime(
                                        NaikaraStringUtil.converToM_D(dto.getLessonDt()), dto.getLessonTmCd(), false) }));
                        break;
                        // 2014/06/02 レッスン予約に関する「応相談」対応 Add Start


                    case ReservationCancellationTeacherScheduleMoveService.ERR_LESSON_TM2:

                        errFlg = true;
                        // ◆◆◆10-2.予約可能/予約取消可能かどうかのチェック (2) 取消の場合 ◆◆◆
                        // メッセージ情報を設定する
                        this.addActionMessage(getMessage(
                                "EC0047",
                                new String[] { service.getLessonDateTime(
                                        NaikaraStringUtil.converToM_D(dto.getLessonDt()), dto.getLessonTmCd(), false) }));
                        break;

                    case ReservationCancellationTeacherScheduleMoveService.ERR_RESERV_KBN_NG:

                        errFlg = true;
                        // ◆◆◆10-2.予約可能/予約取消可能かどうかのチェック (3)a-1 ◆◆◆
                        // メッセージ情報を設定する
                        this.addActionMessage(getMessage(
                                "EC0048",
                                new String[] { service.getLessonDateTime(
                                        NaikaraStringUtil.converToM_D(dto.getLessonDt()), dto.getLessonTmCd(), false) }));
                        break;

                    case ReservationCancellationTeacherScheduleMoveService.ERR_RESERV_KBN_ALREADY:

                        errFlg = true;
                        // ◆◆◆10-2.予約可能/予約取消可能かどうかのチェック (3)a-3 ◆◆◆
                        // メッセージ情報を設定する
                        this.addActionMessage(getMessage(
                                "EC0068",
                                new String[] { service.getLessonDateTime(
                                        NaikaraStringUtil.converToM_D(dto.getLessonDt()), dto.getLessonTmCd(), false) }));
                        break;

                    case ReservationCancellationTeacherScheduleMoveService.ERR_RETURN_CD_DATA_NO:

                        errFlg = true;
                        // メッセージ情報を設定する (対象データが存在しません。)
                        this.addActionMessage(getMessage("EC0020", new String[] {}));
                        break;

                    case ReservationCancellationTeacherScheduleMoveService.ERR_RESERV_SAME_DT_AND_TM:

                        errFlg = true;
                        StringBuffer sbMsg = new StringBuffer();
                        sbMsg.append("予約日時");
                        // メッセージ情報を設定する(既に登録されている%1です。%2を変更してください。)
                        this.addActionMessage(getMessage("EC0059", new String[] {sbMsg.toString(), sbMsg.toString()}));
                        break;

                    }

                    if (errFlg) {
                        break;
                    }
                }
            }

            if (!errFlg) {

                ReservationCancellationTeacherScheduleLoadService loadService = new ReservationCancellationTeacherScheduleLoadService();

                // 講師別コースマスタ(+コースマスタ)リスト取得
                List<TeacherCourseDto> retDtoList = loadService.selectTeacherCourseList(this.teacherId,
                        DateUtil.getSysDate(), loadService.getMaxLessonDt(this.schResTLesResPerTDtoList));

                // 返却されたデータが存在しない場合
                if (retDtoList.get(0).getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_NO) {
                    errFlg = true;
                    // メッセージ情報を設定する
                    this.addActionMessage(getMessage("EC0042", new String[] { "提供可能コースの取得処理" }));
                }

            }

            if (errFlg) {
                // 表示データの取得
                this.load();
                // 画面を返す
                return SUCCESS;
            }

            // 戻る用に必要な情報を取得/格納
            this.modelToSessionReservationCancellationTeacherSchedule();

        } catch (Exception e) {
            throw new NaiException(e);
        }
        // ヘッダの戻るリンク用
        setCurrentActionName(NaikaraTalkConstants.RESERVATION_CANCELLATION_TEACHER_SCHEDULE_LOAD);

        // 画面を返す
        return NEXTPAGE;
    }

    /**
     * 一覧選択のデータの取得。<br>
     * <br>
     * 一覧選択のデータを取得する。<br>
     * <br>
     * @param details 明細チェックBOX<br>
     * @return なし<br>
     * @throws Exception
     */
    private void load(String[] details) throws Exception {

        if (details != null) {
            for (String selected : details) {
                // 明細チェックBOXのデータを取得
                String[] values = selected.substring(1, selected.length() - 1)
                        .replaceAll(NaikaraTalkConstants.HALF_SPACE, NaikaraTalkConstants.BRANK)
                        .split(String.valueOf(NaikaraTalkConstants.COMMA));

                // 一覧から選択されたチェックBOXのデータの設定
                SchResTLesResPerTDto dto = new SchResTLesResPerTDto();
                // レッスン日
                dto.setLessonDt(values[0]);
                // レッスン時刻コード
                dto.setLessonTmCd(values[1]);
                // 予約No
                dto.setReservationNo(values[2]);
                // コースコード
                dto.setCourseCd(values[3]);
                // レコードバージョン番号1
                dto.setRecordVerNo1(Integer.parseInt(values[4]));
                // レコードバージョン番号2
                dto.setRecordVerNo2(Integer.parseInt(values[5]));

                // 2014/06/02 レッスン予約に関する「応相談」対応 Add Start
                dto.setReservationKbn(values[6]);
                // 2014/06/02 レッスン予約に関する「応相談」対応 Add End

                this.schResTLesResPerTDtoList.add(dto);
            }
        }
    }

    /**
     * Model値 To Session<br>
     * <br>
     * Model値をSessionReservationCancellationTeacherScheduleにセット。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    private void modelToSessionReservationCancellationTeacherSchedule() throws Exception {

        // 戻る用に必要な情報を格納
        SessionReservationCancellationTeacherSchedule sessionData = new SessionReservationCancellationTeacherSchedule();

        // 講師ID
        sessionData.setTeacherId(this.teacherId);
        // 日数
        sessionData.setDayCnt(this.dayCnt);
        // 深夜・早朝のレッスンから選択されたチェックBOX
        sessionData.setDetails0(this.details0_chkn);
        // 朝・午前のレッスンから選択されたチェックBOX
        sessionData.setDetails1(this.details1_chkn);
        // 午後・夕方のレッスンから選択されたチェックBOX
        sessionData.setDetails2(this.details2_chkn);
        // 夕方・夜のレッスンから選択されたチェックBOX
        sessionData.setDetails3(this.details3_chkn);
        // 深夜・早朝のレッスン表示フラグ
        sessionData.setDisplayFlg0(this.displayFlg0);
        // 朝・午前のレッスン表示フラグ
        sessionData.setDisplayFlg1(this.displayFlg1);
        // 午後・夕方のレッスン表示フラグ
        sessionData.setDisplayFlg2(this.displayFlg2);
        // 夕方・夜のレッスン表示フラグ
        sessionData.setDisplayFlg3(this.displayFlg3);

        SessionDataUtil.setSessionData(sessionData);
    }
}
