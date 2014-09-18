package com.naikara_talk.sessiondata;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様(個人)_予約処理<br>
 * <b>クラス名称　　　:</b>講師一覧のセッション情報クラス。<br>
 * <b>クラス概要　　　:</b>予約スケジュールの戻る用のセッション情報。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b> 2013/07/19 TECS 新規作成。
 * <b>　　　　　　　　:</b> 2014/04/22 TECS 検索条件の追加(コース名)対応
 */
public class SessionTeacher implements SessionData {

    /** ヘッダの戻るリンク用のSessionKey */
    private static final String HEADER_RETURN_KEY = "TeacherList";

    /** 戻る判定Onフラグ */
    private boolean returnOnFlg;

    /** 検索判断フラグ */
    private String hasSearchFlg;

    /** 講師ID */
    private String teacherId;

    /** 講師名(ニックネーム) */
    private String teacherNm;

    /** 性別 */
    private String sexKbn;

    /** 検索Key：講師ID */
    private String searchTeacherId;

    /** 検索Key：講師名(ニックネーム) */
    private String searchTeacherNm;

    /** 検索Key：性別 */
    private String searchSexKbn;

    /** 検索Key：選択された明細のKey-講師ID */
    private String searchTeacherIdKey;

    // 2014/04/22 Add Start 検索条件の追加(コース名)対応
    /** 検索Key：大分類 */
    private String searchCourseLargeCd;

    /** 検索Key：中分類 */
    private String searchCourseMediumCd;

    /** 検索Key：小分類 */
    private String searchCourseSmallCd;

    /** 検索Key：コース名(テキスト欄) */
    private String searchCourseJNm;

    /** 大分類 */
    private String courseLargeCd;

    /** 中分類 */
    private String courseMediumCd;

    /** 小分類 */
    private String courseSmallCd;

    /** コース名(テキスト欄) */
    private String courseJNm;
// 2014/04/22 Add End   検索条件の追加(コース名)対応


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
     * @return teacherId
     */
    public String getTeacherId() {
        return teacherId;
    }

    /**
     * @param teacherId セットする teacherId
     */
    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * @return teacherNm
     */
    public String getTeacherNm() {
        return teacherNm;
    }

    /**
     * @param teacherNm セットする teacherNm
     */
    public void setTeacherNm(String teacherNm) {
        this.teacherNm = teacherNm;
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
     * @return searchTeacherId
     */
    public String getSearchTeacherId() {
        return searchTeacherId;
    }

    /**
     * @param searchTeacherId セットする searchTeacherId
     */
    public void setSearchTeacherId(String searchTeacherId) {
        this.searchTeacherId = searchTeacherId;
    }

    /**
     * @return searchTeacherNm
     */
    public String getSearchTeacherNm() {
        return searchTeacherNm;
    }

    /**
     * @param searchTeacherNm セットする searchTeacherNm
     */
    public void setSearchTeacherNm(String searchTeacherNm) {
        this.searchTeacherNm = searchTeacherNm;
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

    // 2014/04/22 Add Start 検索条件の追加(コース名)対応
    /**
     * @return searchCourseLargeCd
     */
    public String getSearchCourseLargeCd() {
        return searchCourseLargeCd;
    }

    /**
     * @param searchCourseLargeCd セットする searchCourseLargeCd
     */
    public void setSearchCourseLargeCd(String searchCourseLargeCd) {
        this.searchCourseLargeCd = searchCourseLargeCd;
    }

    /**
     * @return searchCourseMediumCd
     */
    public String getSearchCourseMediumCd() {
        return searchCourseMediumCd;
    }

    /**
     * @param searchCourseMediumCd セットする searchCourseMediumCd
     */
    public void setSearchCourseMediumCd(String searchCourseMediumCd) {
        this.searchCourseMediumCd = searchCourseMediumCd;
    }

    /**
     * @return searchCourseSmallCd
     */
    public String getSearchCourseSmallCd() {
        return searchCourseSmallCd;
    }

    /**
     * @param searchCourseSmallCd セットする searchCourseSmallCd
     */
    public void setSearchCourseSmallCd(String searchCourseSmallCd) {
        this.searchCourseSmallCd = searchCourseSmallCd;
    }

    /**
     * @return searchCourseJNm
     */
    public String getSearchCourseJNm() {
        return searchCourseJNm;
    }

    /**
     * @param searchCourseJNm セットする searchCourseJNm
     */
    public void setSearchCourseJNm(String searchCourseJNm) {
        this.searchCourseJNm = searchCourseJNm;
    }


    /**
     * @return courseLargeCd
     */
    public String getCourseLargeCd() {
        return courseLargeCd;
    }

    /**
     * @param courseLargeCd セットする courseLargeCd
     */
    public void setCourseLargeCd(String courseLargeCd) {
        this.courseLargeCd = courseLargeCd;
    }

    /**
     * @return courseMediumCd
     */
    public String getCourseMediumCd() {
        return courseMediumCd;
    }

    /**
     * @param courseMediumCd セットする courseMediumCd
     */
    public void setCourseMediumCd(String courseMediumCd) {
        this.courseMediumCd = courseMediumCd;
    }

    /**
     * @return courseSmallCd
     */
    public String getCourseSmallCd() {
        return courseSmallCd;
    }

    /**
     * @param courseSmallCd セットする courseSmallCd
     */
    public void setCourseSmallCd(String courseSmallCd) {
        this.courseSmallCd = courseSmallCd;
    }

    /**
     * @return courseJNm
     */
    public String getCourseJNm() {
        return courseJNm;
    }

    /**
     * @param courseJNm セットする courseJNm
     */
    public void setCourseJNm(String courseJNm) {
        this.courseJNm = courseJNm;
    }

    // 2014/04/22 Add End   検索条件の追加(コース名)対応


}
