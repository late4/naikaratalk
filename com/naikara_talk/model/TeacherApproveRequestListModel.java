package com.naikara_talk.model;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.mapper.ActionMapping;


/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>講師<br>
 * <b>クラス名称       :</b>応相談回答ページ初期処理(一覧部)Modelクラス。<br>
 * <b>クラス概要       :</b>応相談回答ページ初期処理(一覧部)Model。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2014/06/02 TECS 新規作成
 */
public class TeacherApproveRequestListModel implements Model {

    private static final long serialVersionUID = 1L;

    private boolean selectChk;         // 選択
    private String requestedDttm;      // 応相談予約登録日時
    private String lessonDt;           // 日本日付 YYYYMMDD ※編集前
    private String lessonDtDsp;        // 日本日付 YY/MM/DD ※編集後
    private String lessonTmCd;         // 日本時刻コード
    private String lessonTmNm;         // 日本時刻名
    private String localTimeFromTo;    // ローカルタイム
    private String sumTimeFromTo;      // サマータイム
    private String courseCd;           // コースコード
    private String courseEnm;          // コース名(英語)
    private String courseJnm;          // コース名(日本語)
    private String studentId;          // 受講者ID
    private String studentNickNm;      // 受講者ニックネーム
    private String reservationNo;      // 予約No
    private String reservationKbn;     // 予約区分(予約状況)


    /**
     * 選択 取得<br>
     * <br>
     * 選択を戻り値で返却する<br>
     * <br>
     * @return selectChk
     */
    public boolean getSelectChk() {
        return selectChk;
    }

    /**
     * 選択 設定<br>
     * <br>
     * 選択を引数で設定する<br>
     * <br>
     * @param selectChk
     */
    public void setSelectChk(boolean selectChk) {
        this.selectChk = selectChk;
    }

    /**
     * 選択リセット（プロパティを初期化するためのメソッド「reset」）<br>
     * <br>
     * チェックBOXリセット<br>
     * <br>
     * @return selectChk
     */
    public void reset(ActionMapping mapping, HttpServletRequest request){
        this.selectChk = false;
    }


    /**
     * 応相談予約登録日時 取得<br>
     * <br>
     * 応相談予約登録日時を戻り値で返却する<br>
     * <br>
     * @return requestedDttm
     */
    public String getRequestedDttm() {
        return requestedDttm;
    }

    /**
     * 応相談予約登録日時 設定<br>
     * <br>
     * 応相談予約登録日時を引数で設定する<br>
     * <br>
     * @param requestedDttm
     */
    public void setRequestedDttm(String requestedDttm) {
        this.requestedDttm = requestedDttm;
    }

    /**
     * (画面表示用編集)レッスン日取得<br>
     * <br>
     * (画面表示用編集)レッスン日を戻り値で返却する<br>
     * <br>
     * @return lessonDtDsp
     */
    public String getLessonDtDsp() {
        return lessonDtDsp;
    }

    /**
     * (画面表示用編集)レッスン日設定<br>
     * <br>
     * (画面表示用編集)レッスン日を引数で設定する<br>
     * <br>
     * @param lessonDtDsp
     */
    public void setLessonDtDsp(String lessonDtDsp) {
        this.lessonDtDsp = lessonDtDsp;
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
     * レッスン時刻名取得<br>
     * <br>
     * レッスン時刻名を戻り値で返却する<br>
     * <br>
     * @return lessonTmNm
     */
    public String getLessonTmNm() {
        return lessonTmNm;
    }

    /**
     * レッスン時刻名設定<br>
     * <br>
     * レッスン時刻名を引数で設定する<br>
     * <br>
     * @param lessonTmNm
     */
    public void setLessonTmNm(String lessonTmNm) {
        this.lessonTmNm = lessonTmNm;
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
     * コース名(日本語)取得<br>
     * <br>
     * コース名(日本語)を戻り値で返却する<br>
     * <br>
     * @return courseJnm
     */
    public String getCourseJnm() {
        return courseJnm;
    }

    /**
     * コース名(日本語)設定<br>
     * <br>
     * コース名(日本語)を引数で設定する<br>
     * <br>
     * @param courseJnm
     */
    public void setCourseJnm(String courseJnm) {
        this.courseJnm = courseJnm;
    }

    /**
     * 受講者ID 取得<br>
     * <br>
     * 受講者IDを戻り値で返却する<br>
     * <br>
     * @return studentId
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * 受講者ID 設定<br>
     * <br>
     * 受講者IDを引数で設定する<br>
     * <br>
     * @param studentId
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * 受講者ニックネーム 取得<br>
     * <br>
     * 受講者ニックネームを戻り値で返却する<br>
     * <br>
     * @return studentNickNm
     */
    public String getStudentNickNm() {
        return studentNickNm;
    }

    /**
     * 受講者ニックネーム 設定<br>
     * <br>
     * 受講者ニックネームを引数で設定する<br>
     * <br>
     * @param studentNickNm
     */
    public void setStudentNickNm(String studentNickNm) {
        this.studentNickNm = studentNickNm;
    }

    /**
     * 予約No 取得<br>
     * <br>
     * 予約Noを戻り値で返却する<br>
     * <br>
     * @return reservationNo
     */
    public String getReservationNo() {
        return reservationNo;
    }

    /**
     * 予約No 設定<br>
     * <br>
     * 予約Noを引数で設定する<br>
     * <br>
     * @param reservationNo
     */
    public void setReservationNo(String reservationNo) {
        this.reservationNo = reservationNo;
    }

    /**
     * 予約区分(予約状況) 取得<br>
     * <br>
     * 予約区分(予約状況)を戻り値で返却する<br>
     * <br>
     * @return reservationKbn
     */
    public String getReservationKbn() {
        return reservationKbn;
    }

    /**
     * 予約区分(予約状況) 設定<br>
     * <br>
     * 予約区分(予約状況)を引数で設定する<br>
     * <br>
     * @param reservationKbn
     */
    public void setReservationKbn(String reservationKbn) {
        this.reservationKbn = reservationKbn;
    }

}