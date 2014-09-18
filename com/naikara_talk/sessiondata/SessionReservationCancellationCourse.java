package com.naikara_talk.sessiondata;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>予約申込ページ（お客様向け）<br>
 * <b>クラス名称　　　:</b>予約申込ページのセッション情報クラス。<br>
 * <b>クラス概要　　　:</b>予約／取消確認ページの戻る用のセッション情報。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b> 2013/07/18 TECS 新規作成。
 */
public class SessionReservationCancellationCourse implements SessionData {

    /** ヘッダの戻るリンク用のSessionKey */
    private static final String HEADER_RETURN_KEY = "ReservationCancellationCourseList";

    /** 戻る判定Onフラグ */
    private boolean returnOnFlg;

    /** 検索判断フラグ */
    private String hasSearchFlg;

    /** 希望日 */
    private String hopeDt;

    /** 希望時刻(From) */
    private String hopeTimeFr;

    /** 希望時刻(To) */
    private String hopeTimeTo;

    /** 予約状況 */
    private String reservationKbn;

    /** 性別 */
    private String sexKbn;

    /** コースコード */
    private String courseCode;

    /** コース名：大分類 */
    private String courseLarge;

    /** コース名：中分類 */
    private String courseMedium;

    /** コース名：小分類 */
    private String courseSmall;

    /** コース名：キーワード */
    private String courseName;

    /** 検索Key：希望日 */
    private String searchHopeDt;

    /** 検索Key：希望時刻(From) */
    private String searchHopeTimeFr;

    /** 検索Key：希望時刻(To) */
    private String searchHopeTimeTo;

    /** 検索Key：予約状況 */
    private String searchReservationKbn;

    /** 検索Key：性別 */
    private String searchSexKbn;

    /** 検索Key：コースコード */
    private String searchCourseCode;

    /** 検索Key：コース名：大分類 */
    private String searchCourseLarge;

    /** 検索Key：コース名：中分類 */
    private String searchCourseMedium;

    /** 検索Key：コース名：小分類 */
    private String searchCourseSmall;

    /** 検索Key：コース名：キーワード */
    private String searchCourseName;

    /** 検索Key：選択された明細のKey-講師ID */
    private String searchTeacherIdKey;

    /**
     * このセッションデータのキーを返却する
     */
    public static String getKey() {
        return HEADER_RETURN_KEY;
    }

    /**
     * @return returnOnFlg
     */
    public boolean getReturnOnFlg() {
        return returnOnFlg;
    }

    /**
     * @param returnOnFlg セットする returnOnFlg
     */
    public void setReturnOnFlg(boolean returnOnFlg) {
        this.returnOnFlg = returnOnFlg;
    }

    /**
     * @return hasSearchFlg
     */
    public String getHasSearchFlg() {
        return hasSearchFlg;
    }

    /**
     * @param hasSearchFlg セットする hasSearchFlg
     */
    public void setHasSearchFlg(String hasSearchFlg) {
        this.hasSearchFlg = hasSearchFlg;
    }

    /**
     * @return hopeDt
     */
    public String getHopeDt() {
        return hopeDt;
    }

    /**
     * @param hopeDt セットする hopeDt
     */
    public void setHopeDt(String hopeDt) {
        this.hopeDt = hopeDt;
    }

    /**
     * @return hopeTimeFr
     */
    public String getHopeTimeFr() {
        return hopeTimeFr;
    }

    /**
     * @param hopeTimeFr セットする hopeTimeFr
     */
    public void setHopeTimeFr(String hopeTimeFr) {
        this.hopeTimeFr = hopeTimeFr;
    }

    /**
     * @return hopeTimeTo
     */
    public String getHopeTimeTo() {
        return hopeTimeTo;
    }

    /**
     * @param hopeTimeTo セットする hopeTimeTo
     */
    public void setHopeTimeTo(String hopeTimeTo) {
        this.hopeTimeTo = hopeTimeTo;
    }

    /**
     * @return reservationKbn
     */
    public String getReservationKbn() {
        return reservationKbn;
    }

    /**
     * @param reservationKbn セットする reservationKbn
     */
    public void setReservationKbn(String reservationKbn) {
        this.reservationKbn = reservationKbn;
    }

    /**
     * @return sexKbn
     */
    public String getSexKbn() {
        return sexKbn;
    }

    /**
     * @param sexKbn セットする sexKbn
     */
    public void setSexKbn(String sexKbn) {
        this.sexKbn = sexKbn;
    }

    /**
     * @return courseCode
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * @param courseCode セットする courseCode
     */
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    /**
     * @return courseLarge
     */
    public String getCourseLarge() {
        return courseLarge;
    }

    /**
     * @param courseLarge セットする courseLarge
     */
    public void setCourseLarge(String courseLarge) {
        this.courseLarge = courseLarge;
    }

    /**
     * @return courseMedium
     */
    public String getCourseMedium() {
        return courseMedium;
    }

    /**
     * @param courseMedium セットする courseMedium
     */
    public void setCourseMedium(String courseMedium) {
        this.courseMedium = courseMedium;
    }

    /**
     * @return courseSmall
     */
    public String getCourseSmall() {
        return courseSmall;
    }

    /**
     * @param courseSmall セットする courseSmall
     */
    public void setCourseSmall(String courseSmall) {
        this.courseSmall = courseSmall;
    }

    /**
     * @return courseName
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * @param courseName セットする courseName
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * @return searchHopeDt
     */
    public String getSearchHopeDt() {
        return searchHopeDt;
    }

    /**
     * @param searchHopeDt セットする searchHopeDt
     */
    public void setSearchHopeDt(String searchHopeDt) {
        this.searchHopeDt = searchHopeDt;
    }

    /**
     * @return searchHopeTimeFr
     */
    public String getSearchHopeTimeFr() {
        return searchHopeTimeFr;
    }

    /**
     * @param searchHopeTimeFr セットする searchHopeTimeFr
     */
    public void setSearchHopeTimeFr(String searchHopeTimeFr) {
        this.searchHopeTimeFr = searchHopeTimeFr;
    }

    /**
     * @return searchHopeTimeTo
     */
    public String getSearchHopeTimeTo() {
        return searchHopeTimeTo;
    }

    /**
     * @param searchHopeTimeTo セットする searchHopeTimeTo
     */
    public void setSearchHopeTimeTo(String searchHopeTimeTo) {
        this.searchHopeTimeTo = searchHopeTimeTo;
    }

    /**
     * @return searchReservationKbn
     */
    public String getSearchReservationKbn() {
        return searchReservationKbn;
    }

    /**
     * @param searchReservationKbn セットする searchReservationKbn
     */
    public void setSearchReservationKbn(String searchReservationKbn) {
        this.searchReservationKbn = searchReservationKbn;
    }

    /**
     * @return searchSexKbn
     */
    public String getSearchSexKbn() {
        return searchSexKbn;
    }

    /**
     * @param searchSexKbn セットする searchSexKbn
     */
    public void setSearchSexKbn(String searchSexKbn) {
        this.searchSexKbn = searchSexKbn;
    }

    /**
     * @return searchCourseCode
     */
    public String getSearchCourseCode() {
        return searchCourseCode;
    }

    /**
     * @param searchCourseCode セットする searchCourseCode
     */
    public void setSearchCourseCode(String searchCourseCode) {
        this.searchCourseCode = searchCourseCode;
    }

    /**
     * @return searchCourseLarge
     */
    public String getSearchCourseLarge() {
        return searchCourseLarge;
    }

    /**
     * @param searchCourseLarge セットする searchCourseLarge
     */
    public void setSearchCourseLarge(String searchCourseLarge) {
        this.searchCourseLarge = searchCourseLarge;
    }

    /**
     * @return searchCourseMedium
     */
    public String getSearchCourseMedium() {
        return searchCourseMedium;
    }

    /**
     * @param searchCourseMedium セットする searchCourseMedium
     */
    public void setSearchCourseMedium(String searchCourseMedium) {
        this.searchCourseMedium = searchCourseMedium;
    }

    /**
     * @return searchCourseSmall
     */
    public String getSearchCourseSmall() {
        return searchCourseSmall;
    }

    /**
     * @param searchCourseSmall セットする searchCourseSmall
     */
    public void setSearchCourseSmall(String searchCourseSmall) {
        this.searchCourseSmall = searchCourseSmall;
    }

    /**
     * @return searchCourseName
     */
    public String getSearchCourseName() {
        return searchCourseName;
    }

    /**
     * @param searchCourseName セットする searchCourseName
     */
    public void setSearchCourseName(String searchCourseName) {
        this.searchCourseName = searchCourseName;
    }

    /**
     * @return searchTeacherIdKey
     */
    public String getSearchTeacherIdKey() {
        return searchTeacherIdKey;
    }

    /**
     * @param searchTeacherIdKey セットする searchTeacherIdKey
     */
    public void setSearchTeacherIdKey(String searchTeacherIdKey) {
        this.searchTeacherIdKey = searchTeacherIdKey;
    }

}
