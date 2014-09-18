package com.naikara_talk.model;

import java.util.List;

import com.naikara_talk.dto.LessonReservationPerformanceTrnDto;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>講師。<br>
 * <b>クラス名称　　　:</b>講師側の視点から_1-1_レッスン実績Modelクラス。<br>
 * <b>クラス概要　　　:</b>講師側の視点から_1-1_レッスン実績Model。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/12 TECS 新規作成。
 */
public class TeacherLessonManagementModel implements Model {

    private static final long serialVersionUID = 1L;

    /** 処理区分：新規 */
    public static final String PROS_KBN_ADD = "0";

    /** 処理区分：修正 */
    public static final String PROS_KBN_UPD = "1";

    /** 処理区分：照会 */
    public static final String PROS_KBN_REF = "2";

    /** チェック：問題なし(正常) */
    public static final int CHECK_OK = 0;

    /** 抽出開始日 */
    private String periodDtFr;

    /** 抽出終了日 */
    private String periodDtTo;

    /** 講師ID */
    private String teacherId;

    /** 講師ニックネーム */
    private String teacherNickname;

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

    /** 検索結果一覧 */
    private List<LessonReservationPerformanceTrnDto> resultList;

    /** 画面状態フラグ */
    private String searchFlg;

    /**
     * 画面状態フラグ取得<br>
     * <br>
     * 画面状態フラグ取得を行う<br>
     * <br>
     * @param なし<br>
     * @return searchFlg 画面状態フラグ<br>
     * @exception なし
     */
    public String getSearchFlg() {
        return searchFlg;
    }

    /**
     * 画面状態フラグ設定<br>
     * <br>
     * 画面状態フラグ設定を行う<br>
     * <br>
     * @param searchFlg 画面状態フラグ<br>
     * @return なし<br>
     * @exception なし
     */
    public void setSearchFlg(String searchFlg) {
        this.searchFlg = searchFlg;
    }

    /**
     * 抽出開始日取得<br>
     * <br>
     * 抽出開始日取得を行う<br>
     * <br>
     * @param なし<br>
     * @return periodDtFr 抽出開始日<br>
     * @exception なし
     */
    public String getPeriodDtFr() {
        return periodDtFr;
    }

    /**
     * 抽出開始日設定<br>
     * <br>
     * 抽出開始日設定を行う<br>
     * <br>
     * @param periodDtFr 抽出開始日<br>
     * @return なし<br>
     * @exception なし
     */
    public void setPeriodDtFr(String periodDtFr) {
        this.periodDtFr = periodDtFr;
    }

    /**
     * 抽出終了日取得<br>
     * <br>
     * 抽出終了日取得を行う<br>
     * <br>
     * @param なし<br>
     * @return periodDtTo 抽出終了日<br>
     * @exception なし
     */
    public String getPeriodDtTo() {
        return periodDtTo;
    }

    /**
     * 抽出終了日設定<br>
     * <br>
     * 抽出終了日設定を行う<br>
     * <br>
     * @param periodDtTo 抽出終了日<br>
     * @return なし<br>
     * @exception なし
     */
    public void setPeriodDtTo(String periodDtTo) {
        this.periodDtTo = periodDtTo;
    }

    /**
     * 講師ID取得<br>
     * <br>
     * 講師ID取得を行う<br>
     * <br>
     * @param なし<br>
     * @return teacherId 講師ID<br>
     * @exception なし
     */
    public String getTeacherId() {
        return teacherId;
    }

    /**
     * 講師ID設定<br>
     * <br>
     * 講師ID設定を行う<br>
     * <br>
     * @param teacherId 講師ID<br>
     * @return なし<br>
     * @exception なし
     */
    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * 講師ニックネーム取得<br>
     * <br>
     * 講師ニックネーム取得を行う<br>
     * <br>
     * @param なし<br>
     * @return teacherNickname 講師ニックネーム<br>
     * @exception なし
     */
    public String getTeacherNickname() {
        return teacherNickname;
    }

    /**
     * 講師ニックネーム設定<br>
     * <br>
     * 講師ニックネーム設定を行う<br>
     * <br>
     * @param teacherNickname 講師ニックネーム<br>
     * @return なし<br>
     * @exception なし
     */
    public void setTeacherNickname(String teacherNickname) {
        this.teacherNickname = teacherNickname;
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
     * 検索結果一覧取得<br>
     * <br>
     * 検索結果一覧取得を行う<br>
     * <br>
     * @param なし<br>
     * @return resultList 検索結果一覧<br>
     * @exception なし
     */
    public List<LessonReservationPerformanceTrnDto> getResultList() {
        return resultList;
    }

    /**
     * 検索結果一覧設定<br>
     * <br>
     * 検索結果一覧設定を行う<br>
     * <br>
     * @param resultList 検索結果一覧<br>
     * @return なし<br>
     * @exception なし
     */
    public void setResultList(List<LessonReservationPerformanceTrnDto> resultList) {
        this.resultList = resultList;
    }
}