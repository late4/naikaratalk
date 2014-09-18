package com.naikara_talk.dto;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DTOクラス<br>
 * <b>クラス名称　　　:</b>講師予定予約テーブル、レッスン予実テーブルクラス<br>
 * <b>クラス概要　　　:</b>講師予定予約テーブル、レッスン予実テーブルDTO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/30 TECS 新規作成
 */
public class SchResTLesResPerTDto extends AbstractDto {

    private String teacherId;       // 講師ID
    private String lessonDt;        // レッスン日
    private String lessonTmCd;      // レッスン時刻コード
    private String lessonTm;        // レッスン時刻
    private String reservationKbn;  // 予約区分(予約状況)
    private String reservationNo;   // 予約No
    private String studentId;       // 受講者ID
    private String courseCd;        // コースコード
    private String courseNm;        // コース名
    private int recordVerNo1;       // レコードバージョン番号1
    private int recordVerNo2;       // レコードバージョン番号2
    private String lessonDtFr;      // レッスン日（FROM）
    private String lessonDtTo;      // レッスン日（TO）
    private String usePoint;        // 利用ポイント
    private String cancelPoint;     // 取消ポイント
    private int returnCode;         // リターンコード

    /**
     * 講師ID取得<br>
     * <br>
     * 講師IDを戻り値で返却する<br>
     * <br>
     * @return teacherId
     */
    public String getTeacherId() {
        return teacherId;
    }

    /**
     * 講師ID設定<br>
     * <br>
     * 講師IDを引数で設定する<br>
     * <br>
     * @param teacherId
     */
    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * レッスン日取得<br>
     * <br>
     * レッスン日を戻り値で返却する<br>
     * <br>
     * @return lessonDt
     */
    public String getLessonDt() {
        return lessonDt;
    }

    /**
     * レッスン日設定<br>
     * <br>
     * レッスン日を引数で設定する<br>
     * <br>
     * @param lessonDt
     */
    public void setLessonDt(String lessonDt) {
        this.lessonDt = lessonDt;
    }

    /**
     * レッスン時刻コード取得<br>
     * <br>
     * レッスン時刻コードを戻り値で返却する<br>
     * <br>
     * @return lessonTmCd
     */
    public String getLessonTmCd() {
        return lessonTmCd;
    }

    /**
     * レッスン時刻コード設定<br>
     * <br>
     * レッスン時刻コードを引数で設定する<br>
     * <br>
     * @param lessonTmCd
     */
    public void setLessonTmCd(String lessonTmCd) {
        this.lessonTmCd = lessonTmCd;
    }

    /**
     * レッスン時刻取得<br>
     * <br>
     * レッスン時刻を戻り値で返却する<br>
     * <br>
     * @return lessonTm
     */
    public String getLessonTm() {
        return lessonTm;
    }

    /**
     * レッスン時刻設定<br>
     * <br>
     * レッスン時刻を引数で設定する<br>
     * <br>
     * @param lessonTm
     */
    public void setLessonTm(String lessonTm) {
        this.lessonTm = lessonTm;
    }

    /**
     * 予約区分(予約状況)取得<br>
     * <br>
     * 予約区分(予約状況)を戻り値で返却する<br>
     * <br>
     * @return reservationKbn
     */
    public String getReservationKbn() {
        return reservationKbn;
    }

    /**
     * 予約区分(予約状況)設定<br>
     * <br>
     * 予約区分(予約状況)を引数で設定する<br>
     * <br>
     * @param reservationKbn
     */
    public void setReservationKbn(String reservationKbn) {
        this.reservationKbn = reservationKbn;
    }

    /**
     * 予約No取得<br>
     * <br>
     * 予約Noを戻り値で返却する<br>
     * <br>
     * @return reservationNo
     */
    public String getReservationNo() {
        return reservationNo;
    }

    /**
     * 予約No設定<br>
     * <br>
     * 予約Noを引数で設定する<br>
     * <br>
     * @param reservationNo
     */
    public void setReservationNo(String reservationNo) {
        this.reservationNo = reservationNo;
    }

    /**
     * 受講者ID取得<br>
     * <br>
     * 受講者IDを戻り値で返却する<br>
     * <br>
     * @return studentId
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * 受講者ID設定<br>
     * <br>
     * 受講者IDを引数で設定する<br>
     * <br>
     * @param studentId
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * コースコード取得<br>
     * <br>
     * コースコードを戻り値で返却する<br>
     * <br>
     * @return courseCd
     */
    public String getCourseCd() {
        return courseCd;
    }

    /**
     * コースコード設定<br>
     * <br>
     * コースコードを引数で設定する<br>
     * <br>
     * @param courseCd
     */
    public void setCourseCd(String courseCd) {
        this.courseCd = courseCd;
    }

    /**
     * コース名取得<br>
     * <br>
     * コース名を戻り値で返却する<br>
     * <br>
     * @return courseNm
     */
    public String getCourseNm() {
        return courseNm;
    }

    /**
     * コース名設定<br>
     * <br>
     * コース名を引数で設定する<br>
     * <br>
     * @param courseNm
     */
    public void setCourseNm(String courseNm) {
        this.courseNm = courseNm;
    }

    /**
     * レコードバージョン番号1取得<br>
     * <br>
     * レコードバージョン番号1を戻り値で返却する<br>
     * <br>
     * @return recordVerNo1
     */
    public int getRecordVerNo1() {
        return recordVerNo1;
    }

    /**
     * レコードバージョン番号1設定<br>
     * <br>
     * レコードバージョン番号1を引数で設定する<br>
     * <br>
     * @param recordVerNo1
     */
    public void setRecordVerNo1(int recordVerNo1) {
        this.recordVerNo1 = recordVerNo1;
    }

    /**
     * レコードバージョン番号2取得<br>
     * <br>
     * レコードバージョン番号2を戻り値で返却する<br>
     * <br>
     * @return recordVerNo2
     */
    public int getRecordVerNo2() {
        return recordVerNo2;
    }

    /**
     * レコードバージョン番号2設定<br>
     * <br>
     * レコードバージョン番号2を引数で設定する<br>
     * <br>
     * @param recordVerNo2
     */
    public void setRecordVerNo2(int recordVerNo2) {
        this.recordVerNo2 = recordVerNo2;
    }

    /**
     * レッスン日（FROM）取得<br>
     * <br>
     * レッスン日（FROM）を戻り値で返却する<br>
     * <br>
     * @return lessonDtFr
     */
    public String getLessonDtFr() {
        return lessonDtFr;
    }

    /**
     * レッスン日（FROM）設定<br>
     * <br>
     * レッスン日（FROM）を引数で設定する<br>
     * <br>
     * @param lessonDtFr
     */
    public void setLessonDtFr(String lessonDtFr) {
        this.lessonDtFr = lessonDtFr;
    }

    /**
     * レッスン日（TO）取得<br>
     * <br>
     * レッスン日（TO）を戻り値で返却する<br>
     * <br>
     * @return lessonDtTo
     */
    public String getLessonDtTo() {
        return lessonDtTo;
    }

    /**
     * レッスン日（TO）設定<br>
     * <br>
     * レッスン日（TO）を引数で設定する<br>
     * <br>
     * @param lessonDtTo
     */
    public void setLessonDtTo(String lessonDtTo) {
        this.lessonDtTo = lessonDtTo;
    }

    /**
     * 利用ポイント取得<br>
     * <br>
     * 利用ポイントを戻り値で返却する<br>
     * <br>
     * @return usePoint
     */
    public String getUsePoint() {
        return usePoint;
    }

    /**
     * 利用ポイント設定<br>
     * <br>
     * 利用ポイントを引数で設定する<br>
     * <br>
     * @param usePoint
     */
    public void setUsePoint(String usePoint) {
        this.usePoint = usePoint;
    }

    /**
     * 取消ポイント取得<br>
     * <br>
     * 取消ポイントを戻り値で返却する<br>
     * <br>
     * @return cancelPoint
     */
    public String getCancelPoint() {
        return cancelPoint;
    }

    /**
     * 取消ポイント設定<br>
     * <br>
     * 取消ポイントを引数で設定する<br>
     * <br>
     * @param cancelPoint
     */
    public void setCancelPoint(String cancelPoint) {
        this.cancelPoint = cancelPoint;
    }

    /**
     * リターンコード取得<br>
     * <br>
     * リターンコードを戻り値で返却する<br>
     * <br>
     * @return returnCode
     */
    public int getReturnCode() {
        return returnCode;
    }

    /**
     * リターンコード設定<br>
     * <br>
     * リターンコードを引数で設定する<br>
     * <br>
     * @param returnCode
     */
    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }
}
