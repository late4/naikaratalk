package com.naikara_talk.service;

import java.util.List;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.SchResTLesResPerTDto;
import com.naikara_talk.dto.ScheduleReservationTrnDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.ReservationCancellationTeacherScheduleLogic;
import com.naikara_talk.model.ReservationCancellationTeacherScheduleModel;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.sessiondata.SessionUser;
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
public class ReservationCancellationTeacherScheduleMoveService implements ActionService {

    /** チェック：レッスン時刻(レッスン開始の2min前(予約)かどうか) */
    public static final int ERR_LESSON_TM = 1;

    /** チェック：レッスン時刻(レッスン開始の24h前(予約取消期間)かどうか) */
    public static final int ERR_LESSON_TM2 = 2;

    /** チェック：予約受付ＮＧ(講師が予約受付不可へ変更した) */
    public static final int ERR_RESERV_KBN_NG = 3;

    /** チェック：予約済 (他受講者が予約した) */
    public static final int ERR_RESERV_KBN_ALREADY = 4;

    /** チェック：データが存在しない */
    public static final int ERR_RETURN_CD_DATA_NO = 5;

    /** チェック：同一人物の既に登録されている日時 */
    public static final int ERR_RESERV_SAME_DT_AND_TM = 6;

    // 2014/06/02 レッスン予約に関する「応相談」対応 Upd Start
    /** チェック：レッスン時刻(レッスン開始の3h前(応相談予約)かどうか) */
    public static final int ERR_LESSON_TM_CON = 7;
    // 2014/06/02 レッスン予約に関する「応相談」対応 Upd End


    /** チェック：問題なし(正常) */
    public static final int CHECK_OK = 0;

    /**
     * 必須チェック。<br>
     * <br>
     * 必須チェックを行う。<br>
     * <br>
     * @param model モデル<br>
     * @return boolean 判定フラグ<br>
     * @throws NaiException
     * @exception なし
     */
    public boolean requiredCheck(ReservationCancellationTeacherScheduleModel model) throws NaiException {

        // 現在表示している日付、時刻の範囲で更新すべき対象が存在するかどうかのチェック
        if (model.getDetails0() == null && model.getDetails1() == null && model.getDetails2() == null
                && model.getDetails3() == null) {
            // 1つも存在しない場合
            return false;
        }

        return true;
    }

    /**
     * 関連チェック。<br>
     * <br>
     * 関連チェックを行う。<br>
     * <br>
     * @param dto 講師予定予約テーブル、レッスン予実テーブルDTO<br>
     * @param teacherId 講師ID<br>
     * @return int チェック結果<br>
     * @throws NaiException
     */
    public int relCheck(SchResTLesResPerTDto dto, String teacherId) throws NaiException {

        Connection conn = null;

        try {
            conn = DbUtil.getConnection();

            // 予約可能／予約取消可能かどうかのチェック
            String lessonDateTm = this.getLessonDateTime(NaikaraStringUtil.converToYYYY_MM_DD(dto.getLessonDt()),
                    dto.getLessonTmCd(), true);
            // （隠し項目）予約No ＝ ”” の場合
            if (StringUtils.isEmpty(dto.getReservationNo())
                    || StringUtils.equals(NaikaraTalkConstants.RESERVATION_NO_NULL, dto.getReservationNo())) {
                // 2014/06/02 レッスン予約に関する「応相談」対応 Upd Start
                if (StringUtils.equals(NaikaraTalkConstants.RESERV_KBN_CON_YES, dto.getReservationKbn())) {
                    // 応相談-予約の場合 (RESERV_KBN_CON_YES:応相談可)
                    // サーバーのシステム日時 + 180 minute (3h)
                    String sysDate = DateUtil.getSysDateAddMinute(180);
                    // サーバーのシステム日時 + 180 minute ≧ 画面の｢日付｣ & 画面の｢レッスン時刻｣の開始時刻 の場合
                    if (!DateUtil.dateCompare4(lessonDateTm, sysDate)) {
                        return ERR_LESSON_TM_CON;
                    }
                } else {
                    // 予約の場合 (RESERV_KBN_YES:予約可)
                    // サーバーのシステム日時 + 20 minute
                    String sysDate = DateUtil.getSysDateAddMinute(20);
                    // サーバーのシステム日時 + 20 minute ≧ 画面の｢日付｣ & 画面の｢レッスン時刻｣の開始時刻 の場合
                    if (!DateUtil.dateCompare4(lessonDateTm, sysDate)) {
                        return ERR_LESSON_TM;
                    }
                }
                // 2014/06/02 レッスン予約に関する「応相談」対応 Upd End
            } else {
                // ◆予約-取消、応相談予約-取消 両方取消可能時間は同じ (RESERV_KBN_ALREADY:予約済, RESERV_KBN_CON_ALREADY:応相談-予約済)
                // サーバーのシステム日時 + 24 hour（24 hour：60*24 minute）
                String sysDate = DateUtil.getSysDateAddMinute(1440);
                // サーバーのシステム日時 + 24 hour ≧ 画面の｢日付｣ & 画面の｢レッスン時刻｣の開始時刻 の場合
                if (!DateUtil.dateCompare4(lessonDateTm, sysDate)) {
                    return ERR_LESSON_TM2;
                }
            }


            //予約スケジュールLogic
            ReservationCancellationTeacherScheduleLogic logic = new ReservationCancellationTeacherScheduleLogic(conn);


            // 2013/09/25-自分が既に、同一日時の別の講師の予約をしていないかどうかチェック-Start
            // 講師予定予約テーブルのデータ取得処理
            List<ScheduleReservationTrnDto> retDtoNoSelectTList = this.selectScheduleReservationTrnNoSelectTList(teacherId,
                    dto.getLessonDt(), dto.getLessonTmCd(), logic);

            // データが存在する場合
            ScheduleReservationTrnDto retDtoNoSelectT = retDtoNoSelectTList.get(0);
            if (retDtoNoSelectT.getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_YES) {
                return ERR_RESERV_SAME_DT_AND_TM;
            }
            // 2013/09/25-自分が既に、同一日時の別の講師の予約をしていないかどうかチェック-End


            // 講師予定予約テーブルのデータ取得処理
            ScheduleReservationTrnDto retDto = logic.selectScheduleReservationTrn(teacherId, dto.getLessonDt(),
                    dto.getLessonTmCd());

            // データが存在しない場合
            if (retDto.getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_YES) {
                // 講師予定予約テーブル．予約区分 ＝ ”0” (予約受付ＮＧ) の場合
                if (StringUtils.equals(retDto.getReservationKbn(), NaikaraTalkConstants.RESERV_KBN_NG)) {
                    return ERR_RESERV_KBN_NG;
                }

                // 講師予定予約テーブル．予約区分 ＝ ”2” (予約済み)
                // 又は  "5"(応相談-予約済(仮予約)) 又は  "6"(応相談-予約確定)の場合
                if (StringUtils.equals(retDto.getReservationKbn(), NaikaraTalkConstants.RESERV_KBN_ALREADY)
                        || StringUtils.equals(retDto.getReservationKbn(), NaikaraTalkConstants.RESERV_KBN_CON_PROVISIONAL_RESERV)
                        || StringUtils.equals(retDto.getReservationKbn(), NaikaraTalkConstants.RESERV_KBN_CON_ALREADY)) {
                    // ユーザIDを取得
                    String userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString()))
                            .getUserId();
                    // 講師予定予約テーブル．受講者ID ≠ ログイン情報：受講者ID の場合
                    if (!StringUtils.equals(retDto.getStudentId(), userId)) {
                        return ERR_RESERV_KBN_ALREADY;
                    }
                }
            } else {

                // データが存在しない場合
                return ERR_RETURN_CD_DATA_NO;
            }

            return CHECK_OK;

        } catch (SQLException se) {
            se.printStackTrace();
            throw new NaiException(se);
        } finally {
            try {
                conn.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * ｢レッスン日｣ & ｢レッスン時刻｣の取得<br>
     * <br>
     * ｢レッスン日｣ & ｢レッスン時刻｣の取得処理<br>
     * <br>
     * @param lessonDt レッスン日<br>
     * @param lessonTmCd レッスン時刻コード<br>
     * @param startTmFlg 開始時刻フラグ<br>
     * @return String ｢レッスン日｣ & ｢レッスン時刻｣<br>
     * @throws NaiException
     */
    public String getLessonDateTime(String lessonDt, String lessonTmCd, boolean startTmFlg) throws NaiException {

        // 汎用フィールド名の取得
        CodeManagMstCache cache = CodeManagMstCache.getInstance();
        // レッスン時刻の名称一覧取得
        LinkedHashMap<String, CodeManagMstDto> lessonTmList = cache
                .getList(NaikaraTalkConstants.CODE_CATEGORY_LESSON_TM_S);

        // ｢レッスン時刻｣
        String lessonTmStart = lessonTmList.get(lessonTmCd).getManagerNm();

        // ｢レッスン時刻｣の開始時刻
        if (startTmFlg) {
            lessonTmStart = lessonTmStart.substring(0, 5);
        }

        // 画面の｢日付｣ & 画面の｢レッスン時刻｣の開始時刻
        String lessonDateTime = NaikaraStringUtil.unionName(lessonDt, lessonTmStart);

        return lessonDateTime;
    }



    /**
     * 講師予定予約テーブルのデータ取得処理（選択されていない講師）。<br>
     * <br>
     * 講師予定予約テーブルのデータ取得処理を行う。<br>
     * <br>
     * @param String teacherId<br>
     * @param String lessonDt<br>
     * @param String lessonTmCd<br>
     * @param logic ReservationCancellationTeacherScheduleLogic<br>
     * @return List<ScheduleReservationTrnDto> 講師予定予約テーブルDTOリスト<br>
     * @throws NaiException
     */
    public List<ScheduleReservationTrnDto> selectScheduleReservationTrnNoSelectTList(
        String teacherId, String lessonDt, String lessonTmCd,
        ReservationCancellationTeacherScheduleLogic logic) throws NaiException {

        Connection conn = null;

        try {
            conn = DbUtil.getConnection();


            ScheduleReservationTrnDto srdto = new ScheduleReservationTrnDto();
            // 講師ID
            srdto.setTeacherId(teacherId);
            // レッスン日：YYYYMMDD形式とする
            srdto.setLessonDt(NaikaraStringUtil.converToYYYYMMDD(lessonDt));
            // レッスン時刻
            srdto.setLessonTmCd(lessonTmCd);

            String studentId = "";
            SessionUser SessionUserData = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString()));
            if (SessionUserData != null) {
                // ユーザIDを取得
            	studentId = SessionUserData.getUserId();
            }
            // 受講者ID
            srdto.setStudentId(studentId);

            return logic.selectScheduleReservationTrnNoSelectTList(srdto);

        } catch (SQLException se) {
            se.printStackTrace();
            throw new NaiException(se);
        } finally {
            try {
                conn.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }


}
