package com.naikara_talk.sessiondata;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様(個人)_実績照会<br>
 * <b>クラス名称　　　:</b>受講者側の視点から_1-2_レッスン実績のセッション情報クラス。<br>
 * <b>クラス概要　　　:</b>受講者側の視点から_1-2_レッスン実績の戻る用のセッション情報。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/05 TECS 新規作成。
 */

public class SessionStudentLessonHistoryUsesList implements SessionData {

    /** ヘッダの戻るリンク用のSessionKey */
    private static final String HEADER_RETURN_KEY = "StudentLessonHistoryTeacherUsesList";

    /** 戻る判定Onフラグ */
    private boolean returnOnFlg;

    /** レッスン開始日 */
    private String lessonDtFr;

    /** レッスン終了日 */
    private String lessonDtTo;

    /** 受講者ID */
    private String studentId;

    /** 受講者ニックネーム */
    private String studentNickname;

    /** 大分類 */
    private String courseLarge;

    /** 中分類 */
    private String courseMedium;

    /** 小分類 */
    private String courseSmall;

    /** コース名 */
    private String courseName;

    /** 検索Key：レッスン開始日 */
    private String searchLessonDtFr;

    /** 検索Key：レッスン終了日 */
    private String searchLessonDtTo;

    /** 検索Key：受講者ID */
    private String searchStudentId;

    /** 検索Key：受講者ニックネーム */
    private String searchStudentNickname;

    /** 検索Key：大分類 */
    private String searchCourseLarge;

    /** 検索Key：中分類 */
    private String searchCourseMedium;

    /** 検索Key：小分類 */
    private String searchCourseSmall;

    /** 検索Key：コース名 */
    private String searchCourseName;

    /** 検索Key：選択された明細のKey-予約No */
    private String searchReservationNoKey;

    /** 検索判断フラグ */
    private String hasSearchFlg;

    /**
     * このセッションデータのキーを返却する
     */
    public static String getKey() {
        return HEADER_RETURN_KEY;
    }

    /**
     * 検索判断フラグ取得<br>
     * <br>
     * 検索判断フラグ取得を行う<br>
     * <br>
     * @param なし<br>
     * @return hasSearchFlg 検索判断フラグ<br>
     * @exception なし
     */
    public String getHasSearchFlg() {
        return hasSearchFlg;
    }

    /**
     * 検索判断フラグ設定<br>
     * <br>
     * 検索判断フラグ設定を行う<br>
     * <br>
     * @param hasSearchFlg 検索判断フラグ<br>
     * @return なし<br>
     * @exception なし
     */
    public void setHasSearchFlg(String hasSearchFlg) {
        this.hasSearchFlg = hasSearchFlg;
    }

    /**
     * 戻る判定Onフラグ取得<br>
     * <br>
     * 戻る判定Onフラグ取得を行う<br>
     * <br>
     * @param なし<br>
     * @return returnOnFlg 戻る判定Onフラグ<br>
     * @exception なし
     */
    public boolean getReturnOnFlg() {
        return returnOnFlg;
    }

    /**
     * 戻る判定Onフラグ設定<br>
     * <br>
     * 戻る判定Onフラグ設定を行う<br>
     * <br>
     * @param returnOnFlg 戻る判定Onフラグ<br>
     * @return なし<br>
     * @exception なし
     */
    public void setReturnOnFlg(boolean returnOnFlg) {
        this.returnOnFlg = returnOnFlg;
    }

    /**
     * レッスン開始日取得<br>
     * <br>
     * レッスン開始日取得を行う<br>
     * <br>
     * @param なし<br>
     * @return lessonDtFr レッスン開始日<br>
     * @exception なし
     */
    public String getLessonDtFr() {
        return lessonDtFr;
    }

    /**
     * レッスン開始日設定<br>
     * <br>
     * レッスン開始日設定を行う<br>
     * <br>
     * @param lessonDtFr レッスン開始日<br>
     * @return なし<br>
     * @exception なし
     */
    public void setLessonDtFr(String lessonDtFr) {
        this.lessonDtFr = lessonDtFr;
    }

    /**
     * レッスン終了日取得<br>
     * <br>
     * レッスン終了日取得を行う<br>
     * <br>
     * @param なし<br>
     * @return lessonDtTo レッスン終了日<br>
     * @exception なし
     */
    public String getLessonDtTo() {
        return lessonDtTo;
    }

    /**
     * レッスン終了日設定<br>
     * <br>
     * レッスン終了日設定を行う<br>
     * <br>
     * @param lessonDtTo レッスン終了日<br>
     * @return なし<br>
     * @exception なし
     */
    public void setLessonDtTo(String lessonDtTo) {
        this.lessonDtTo = lessonDtTo;
    }

    /**
     * コース名取得<br>
     * <br>
     * コース名取得を行う<br>
     * <br>
     * @param なし<br>
     * @return courseName コース名<br>
     * @exception なし
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * コース名設定<br>
     * <br>
     * コース名設定を行う<br>
     * <br>
     * @param courseName コース名<br>
     * @return なし<br>
     * @exception なし
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * 小分類取得<br>
     * <br>
     * 小分類取得を行う<br>
     * <br>
     * @param なし<br>
     * @return courseSmall 小分類<br>
     * @exception なし
     */
    public String getCourseSmall() {
        return courseSmall;
    }

    /**
     * 小分類設定<br>
     * <br>
     * 小分類設定を行う<br>
     * <br>
     * @param courseSmall 小分類<br>
     * @return なし<br>
     * @exception なし
     */
    public void setCourseSmall(String courseSmall) {
        this.courseSmall = courseSmall;
    }

    /**
     * 中分類取得<br>
     * <br>
     * 中分類取得を行う<br>
     * <br>
     * @param なし<br>
     * @return courseMedium 中分類<br>
     * @exception なし
     */
    public String getCourseMedium() {
        return courseMedium;
    }

    /**
     * 中分類設定<br>
     * <br>
     * 中分類設定を行う<br>
     * <br>
     * @param courseMedium 中分類<br>
     * @return なし<br>
     * @exception なし
     */
    public void setCourseMedium(String courseMedium) {
        this.courseMedium = courseMedium;
    }

    /**
     * 大分類取得<br>
     * <br>
     * 大分類取得を行う<br>
     * <br>
     * @param なし<br>
     * @return courseLarge 大分類<br>
     * @exception なし
     */
    public String getCourseLarge() {
        return courseLarge;
    }

    /**
     * 大分類設定<br>
     * <br>
     * 大分類設定を行う<br>
     * <br>
     * @param courseLarge 大分類<br>
     * @return なし<br>
     * @exception なし
     */
    public void setCourseLarge(String courseLarge) {
        this.courseLarge = courseLarge;
    }

    /**
     * 受講者ニックネーム取得<br>
     * <br>
     * 受講者ニックネーム取得を行う<br>
     * <br>
     * @param なし<br>
     * @return studentNickname 受講者ニックネーム<br>
     * @exception なし
     */
    public String getStudentNickname() {
        return studentNickname;
    }

    /**
     * 受講者ニックネーム設定<br>
     * <br>
     * 受講者ニックネーム設定を行う<br>
     * <br>
     * @param studentNickname 受講者ニックネーム<br>
     * @return なし<br>
     * @exception なし
     */
    public void setStudentNickname(String studentNickname) {
        this.studentNickname = studentNickname;
    }

    /**
     * 受講者ID取得<br>
     * <br>
     * 受講者ID取得を行う<br>
     * <br>
     * @param なし<br>
     * @return studentId 受講者ID<br>
     * @exception なし
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * 受講者ID設定<br>
     * <br>
     * 受講者ID設定を行う<br>
     * <br>
     * @param studentId 受講者ID<br>
     * @return なし<br>
     * @exception なし
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * 検索Key：選択された明細のKey-予約No取得<br>
     * <br>
     * 検索Key：選択された明細のKey-予約No取得を行う<br>
     * <br>
     * @param なし<br>
     * @return searchReservationNoKey 検索Key：選択された明細のKey-予約No<br>
     * @exception なし
     */
    public String getSearchReservationNoKey() {
        return searchReservationNoKey;
    }

    /**
     * 検索Key：選択された明細のKey-予約No設定<br>
     * <br>
     * 検索Key：選択された明細のKey-予約No設定を行う<br>
     * <br>
     * @param searchReservationNoKey 検索Key：選択された明細のKey-予約No<br>
     * @return なし<br>
     * @exception なし
     */
    public void setSearchReservationNoKey(String searchReservationNoKey) {
        this.searchReservationNoKey = searchReservationNoKey;
    }

    /**
     * 検索Key：レッスン開始日取得<br>
     * <br>
     * 検索Key：レッスン開始日取得を行う<br>
     * <br>
     * @param なし<br>
     * @return searchLessonDtFr 検索Key：レッスン開始日<br>
     * @exception なし
     */
    public String getSearchLessonDtFr() {
        return searchLessonDtFr;
    }

    /**
     * 検索Key：レッスン開始日設定<br>
     * <br>
     * 検索Key：レッスン開始日設定を行う<br>
     * <br>
     * @param searchLessonDtFr 検索Key：レッスン開始日<br>
     * @return なし<br>
     * @exception なし
     */
    public void setSearchLessonDtFr(String searchLessonDtFr) {
        this.searchLessonDtFr = searchLessonDtFr;
    }

    /**
     * 検索Key：レッスン終了日取得<br>
     * <br>
     * 検索Key：レッスン終了日取得を行う<br>
     * <br>
     * @param なし<br>
     * @return searchLessonDtTo 検索Key：レッスン終了日<br>
     * @exception なし
     */
    public String getSearchLessonDtTo() {
        return searchLessonDtTo;
    }

    /**
     * 検索Key：レッスン終了日設定<br>
     * <br>
     * 検索Key：レッスン終了日設定を行う<br>
     * <br>
     * @param searchLessonDtTo 検索Key：レッスン終了日<br>
     * @return なし<br>
     * @exception なし
     */
    public void setSearchLessonDtTo(String searchLessonDtTo) {
        this.searchLessonDtTo = searchLessonDtTo;
    }

    /**
     * 検索Key：コース名取得<br>
     * <br>
     * 検索Key：コース名取得を行う<br>
     * <br>
     * @param なし<br>
     * @return searchCourseName 検索Key：コース名<br>
     * @exception なし
     */
    public String getSearchCourseName() {
        return searchCourseName;
    }

    /**
     * 検索Key：コース名設定<br>
     * <br>
     * 検索Key：コース名設定を行う<br>
     * <br>
     * @param searchCourseName 検索Key：コース名<br>
     * @return なし<br>
     * @exception なし
     */
    public void setSearchCourseName(String searchCourseName) {
        this.searchCourseName = searchCourseName;
    }

    /**
     * 検索Key：小分類取得<br>
     * <br>
     * 検索Key：小分類取得を行う<br>
     * <br>
     * @param なし<br>
     * @return searchCourseSmall 検索Key：小分類<br>
     * @exception なし
     */
    public String getSearchCourseSmall() {
        return searchCourseSmall;
    }

    /**
     * 検索Key：小分類設定<br>
     * <br>
     * 検索Key：小分類設定を行う<br>
     * <br>
     * @param searchCourseSmall 検索Key：小分類<br>
     * @return なし<br>
     * @exception なし
     */
    public void setSearchCourseSmall(String searchCourseSmall) {
        this.searchCourseSmall = searchCourseSmall;
    }

    /**
     * 検索Key：中分類取得<br>
     * <br>
     * 検索Key：中分類取得を行う<br>
     * <br>
     * @param なし<br>
     * @return searchCourseMedium 検索Key：中分類<br>
     * @exception なし
     */
    public String getSearchCourseMedium() {
        return searchCourseMedium;
    }

    /**
     * 検索Key：中分類設定<br>
     * <br>
     * 検索Key：中分類設定を行う<br>
     * <br>
     * @param searchCourseMedium 検索Key：中分類<br>
     * @return なし<br>
     * @exception なし
     */
    public void setSearchCourseMedium(String searchCourseMedium) {
        this.searchCourseMedium = searchCourseMedium;
    }

    /**
     * 検索Key：大分類取得<br>
     * <br>
     * 検索Key：大分類取得を行う<br>
     * <br>
     * @param なし<br>
     * @return searchCourseLarge 検索Key：大分類<br>
     * @exception なし
     */
    public String getSearchCourseLarge() {
        return searchCourseLarge;
    }

    /**
     * 検索Key：大分類設定<br>
     * <br>
     * 検索Key：大分類設定を行う<br>
     * <br>
     * @param searchCourseLarge 検索Key：大分類<br>
     * @return なし<br>
     * @exception なし
     */
    public void setSearchCourseLarge(String searchCourseLarge) {
        this.searchCourseLarge = searchCourseLarge;
    }

    /**
     * 検索Key：受講者ニックネーム取得<br>
     * <br>
     * 検索Key：受講者ニックネーム取得を行う<br>
     * <br>
     * @param なし<br>
     * @return searchStudentNickname 検索Key：受講者ニックネーム<br>
     * @exception なし
     */
    public String getSearchStudentNickname() {
        return searchStudentNickname;
    }

    /**
     * 検索Key：受講者ニックネーム設定<br>
     * <br>
     * 検索Key：受講者ニックネーム設定を行う<br>
     * <br>
     * @param searchStudentNickname 検索Key：受講者ニックネーム<br>
     * @return なし<br>
     * @exception なし
     */
    public void setSearchStudentNickname(String searchStudentNickname) {
        this.searchStudentNickname = searchStudentNickname;
    }

    /**
     * 検索Key：受講者ID取得<br>
     * <br>
     * 検索Key：受講者ID取得を行う<br>
     * <br>
     * @param なし<br>
     * @return searchStudentId 検索Key：受講者ID<br>
     * @exception なし
     */
    public String getSearchStudentId() {
        return searchStudentId;
    }

    /**
     * 検索Key：受講者ID設定<br>
     * <br>
     * 検索Key：受講者ID設定を行う<br>
     * <br>
     * @param searchStudentId 検索Key：受講者ID<br>
     * @return なし<br>
     * @exception なし
     */
    public void setSearchStudentId(String searchStudentId) {
        this.searchStudentId = searchStudentId;
    }
}