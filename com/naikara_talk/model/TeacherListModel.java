package com.naikara_talk.model;

import java.util.List;

import com.naikara_talk.dto.UserMstTeacherMstDto;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_予約処理<br>
 * <b>クラス名称       :</b>講師一覧（Pop）Modelクラス<br>
 * <b>クラス概要       :</b>登録済みの講師情報の検索処理を行い。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/19 TECS 新規作成
 * <b>                 :</b>2014/04/22 TECS 検索条件の追加(コース名)対応
 */
public class TeacherListModel implements Model {

    private static final long serialVersionUID = 1L;

    /** コース名：大分類 */
    private String courseLarge;

    /** コース名：中分類 */
    private String courseMedium;

    /** コース名：小分類 */
    private String courseSmall;

    /** 講師ID */
    protected String teacherId;

    /** 講師名(ニックネーム) */
    protected String teacherNm;

    /** 性別 */
    protected String sexKbn;

    // 2014/04/22 Add Start 検索条件の追加(短コース名)対応
    /** 検索条件部：大分類 */
    private String courseLargeCd;

    /** 検索条件部：中分類 */
    private String courseMediumCd;

    /** 検索条件部：小分類 */
    private String courseSmallCd;

    /** 検索条件部：コース名(テキスト欄) */
    private String courseJNm;

    /** 一覧部：短コース名 */
    protected String courseNmShort;
    // 2014/04/22 Add End   検索条件の追加(短コース名)対応

    /** 検索結果一覧 */
    private List<UserMstTeacherMstDto> resultList;

    /** 一覧の講師ID */
    protected String teacherIdSel;

    /** 一覧の講師名(ニックネーム) */
    protected String teacherNmSel;

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


    // 2014/04/22 Add Start 検索条件の追加(コース名)対応
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
     * @param courseNm セットする courseNm
     */
    public void setCourseJNm(String courseJNm) {
        this.courseJNm = courseJNm;
    }


    /**
     * @return courseNmShort
     */
    public String getCourseNmShort() {
        return courseNmShort;
    }

    /**
     * @param courseNmShort セットする courseNmShort
     */
    public void setCourseNmShort(String courseNmShort) {
        this.courseNmShort = courseNmShort;
    }
    // 2014/04/22 Add End   検索条件の追加(コース名)対応


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
     * @return resultList
     */
    public List<UserMstTeacherMstDto> getResultList() {
        return resultList;
    }

    /**
     * @param resultList セットする resultList
     */
    public void setResultList(List<UserMstTeacherMstDto> resultList) {
        this.resultList = resultList;
    }

    /**
     * @return teacherIdSel
     */
    public String getTeacherIdSel() {
        return teacherIdSel;
    }

    /**
     * @param teacherIdSel セットする teacherIdSel
     */
    public void setTeacherIdSel(String teacherIdSel) {
        this.teacherIdSel = teacherIdSel;
    }

    /**
     * @return teacherNmSel
     */
    public String getTeacherNmSel() {
        return teacherNmSel;
    }

    /**
     * @param teacherNmSel セットする teacherNmSel
     */
    public void setTeacherNmSel(String teacherNmSel) {
        this.teacherNmSel = teacherNmSel;
    }

}