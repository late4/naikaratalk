package com.naikara_talk.dto;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DTOクラス<br>
 * <b>クラス名称　　　:</b>講師用個別マイページクラス<br>
 * <b>クラス概要　　　:</b>講師用個別マイページDTO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/01 TECS 新規作成
 * <b>                :</b>2014/06/02 TECS レッスン予約に関する「応相談」対応
 */
public class SchResTLesResPerTStuMLocSumTMDto extends AbstractDto {

    private String teacherId;          //講師ID
    private String lessonDt;           //日本日付
    private String lessonTm;           //日本時刻
    private String studentId;          //受講者ID
    private String reservationNo;      //予約No

    private String localTimeFromTo;    //ローカルタイム
    private String sumTimeFromTo;      //サマータイム

    private String courseJnm;          //コース名
    private String courseEnm;          //コース名(英語)
    private String mailKbnJnm;         //添付メール送付済
    private String mailKbnEnm;         //添付メール送付済(英語)
    private String mailDt;             //添付メール送付日

    private String nickNm;             //ニックネーム

    // 2014/06/02 レッスン予約に関する「応相談」対応 Add Start
    private String reservationKbn;     //予約区分(予約状況)
    private String reservationKbnEnm;  //予約区分(予約状況)英語表示
    private String reservationKbnJnm;  //予約区分(予約状況)日本語表示
    // 2014/06/02 レッスン予約に関する「応相談」対応 Add End


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
     * @return lessonTm
     */
    public String getLessonTm() {
        return lessonTm;
    }

    /**
     * レッスン時刻コード設定<br>
     * <br>
     * レッスン時刻コードを引数で設定する<br>
     * <br>
     * @param lessonTm
     */
    public void setLessonTm(String lessonTm) {
        this.lessonTm = lessonTm;
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
     * ローカルタイム取得<br>
     * <br>
     * ローカルタイムを戻り値で返却する<br>
     * <br>
     * @return localTimeFromTo
     */
    public String getLocalTimeFromTo() {
        return localTimeFromTo;
    }

    /**
     * ローカルタイム設定<br>
     * <br>
     * ローカルタイムを引数で設定する<br>
     * <br>
     * @param localTimeFromTo
     */
    public void setLocalTimeFromTo(String localTimeFromTo) {
        this.localTimeFromTo = localTimeFromTo;
    }

    /**
     * サマータイム取得<br>
     * <br>
     * サマータイムを戻り値で返却する<br>
     * <br>
     * @return sumTimeFromTo
     */
    public String getSumTimeFromTo() {
        return sumTimeFromTo;
    }

    /**
     * サマータイム設定<br>
     * <br>
     * サマータイムを引数で設定する<br>
     * <br>
     * @param sumTimeFromTo
     */
    public void setSumTimeFromTo(String sumTimeFromTo) {
        this.sumTimeFromTo = sumTimeFromTo;
    }

    /**
     * コース名取得<br>
     * <br>
     * コース名を戻り値で返却する<br>
     * <br>
     * @return courseJnm
     */
    public String getCourseJnm() {
        return courseJnm;
    }

    /**
     * コース名設定<br>
     * <br>
     * コース名を引数で設定する<br>
     * <br>
     * @param courseJnm
     */
    public void setCourseJnm(String courseJnm) {
        this.courseJnm = courseJnm;
    }

    /**
     * コース名(英語)取得<br>
     * <br>
     * コース名(英語)を戻り値で返却する<br>
     * <br>
     * @return courseEnm
     */
    public String getCourseEnm() {
        return courseEnm;
    }

    /**
     * コース名(英語)設定<br>
     * <br>
     * コース名(英語)を引数で設定する<br>
     * <br>
     * @param courseEnm
     */
    public void setCourseEnm(String courseEnm) {
        this.courseEnm = courseEnm;
    }

    /**
     * 添付メール送付済区分取得<br>
     * <br>
     * 添付メール送付済区分を戻り値で返却する<br>
     * <br>
     * @return mailKbn
     */
    public String getMailKbnJnm() {
        return mailKbnJnm;
    }

    /**
     * 添付メール送付済区分設定<br>
     * <br>
     * 添付メール送付済区分を引数で設定する<br>
     * <br>
     * @param mailKbn
     */
    public void setMailKbnJnm(String mailKbnJnm) {
        this.mailKbnJnm = mailKbnJnm;
    }

    /**
     * 添付メール送付済区分取得<br>
     * <br>
     * 添付メール送付済区分を戻り値で返却する<br>
     * <br>
     * @return mailKbn
     */
    public String getMailKbnEnm() {
        return mailKbnEnm;
    }

    /**
     * 添付メール送付済区分設定<br>
     * <br>
     * 添付メール送付済区分を引数で設定する<br>
     * <br>
     * @param mailKbn
     */
    public void setMailKbnEnm(String mailKbnEnm) {
        this.mailKbnEnm = mailKbnEnm;
    }

    /**
     * 添付メール送付日取得<br>
     * <br>
     * 添付メール送付日を戻り値で返却する<br>
     * <br>
     * @return mailDt
     */
    public String getMailDt() {
        return mailDt;
    }

    /**
     * 添付メール送付日設定<br>
     * <br>
     * 添付メール送付日を引数で設定する<br>
     * <br>
     * @param mailDt
     */
    public void setMailDt(String mailDt) {
        this.mailDt = mailDt;
    }

    /**
     * ニックネーム取得<br>
     * <br>
     * ニックネームを戻り値で返却する<br>
     * <br>
     * @return nickNm
     */
    public String getNickNm() {
        return nickNm;
    }

    /**
     * ニックネーム設定<br>
     * <br>
     * ニックネームを引数で設定する<br>
     * <br>
     * @param nickNm
     */
    public void setNickNm(String nickNm) {
        this.nickNm = nickNm;
    }

    // 2014/06/02 レッスン予約に関する「応相談」対応 Add Start
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
     * 予約区分(予約状況)英語表示取得<br>
     * <br>
     * 予約区分(予約状況)英語表示を戻り値で返却する<br>
     * <br>
     * @return reservationKbnEnm
     */
    public String getReservationKbnEnm() {
        return reservationKbnEnm;
    }

    /**
     * 予約区分(予約状況)英語表示設定<br>
     * <br>
     * 予約区分(予約状況)英語表示を引数で設定する<br>
     * <br>
     * @param reservationKbnEnm
     */
    public void setReservationKbnEnm(String reservationKbnEnm) {
        this.reservationKbnEnm = reservationKbnEnm;
    }

    /**
     * 予約区分(予約状況)日本語表示取得<br>
     * <br>
     * 予約区分(予約状況)日本語表示を戻り値で返却する<br>
     * <br>
     * @return reservationKbnJnm
     */
    public String getReservationKbnJnm() {
        return reservationKbnJnm;
    }

    /**
     * 予約区分(予約状況)日本語表示設定<br>
     * <br>
     * 予約区分(予約状況)日本語表示を引数で設定する<br>
     * <br>
     * @param reservationKbnJnm
     */
    public void setReservationKbnJnm(String reservationKbnJnm) {
        this.reservationKbnJnm = reservationKbnJnm;
    }
    // 2014/06/02 レッスン予約に関する「応相談」対応 Add End


}