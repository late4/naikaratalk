package com.naikara_talk.model;

import java.util.List;

import com.naikara_talk.dto.SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDto;
import com.naikara_talk.dto.SchResTLesResPerTDto;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_予約処理<br>
 * <b>クラス名称       :</b>予約申込ページModelクラス<br>
 * <b>クラス概要       :</b>受講者がレッスンを予約登録、取消ができる。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/18 TECS 新規作成
 */
public class ReservationCancellationCourseListModel implements Model {

    private static final long serialVersionUID = 1L;

    /** ログインID */
    private String loginId;

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

    /** 検索結果一覧 */
    private List<SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDto> resultList;

    /** 講師ID */
    private String teacherId;

    /** 講師予定予約テーブル、レッスン予実テーブルDTO */
    SchResTLesResPerTDto schResTLesResPerTDto = new SchResTLesResPerTDto();

    /**
     * @return loginId
     */
    public String getLoginId() {
        return loginId;
    }

    /**
     * @param loginId セットする loginId
     */
    public void setLoginId(String loginId) {
        this.loginId = loginId;
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
     * @return resultList
     */
    public List<SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDto> getResultList() {
        return resultList;
    }

    /**
     * @param resultList セットする resultList
     */
    public void setResultList(List<SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDto> resultList) {
        this.resultList = resultList;
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
     * @return schResTLesResPerTDto
     */
    public SchResTLesResPerTDto getSchResTLesResPerTDto() {
        return schResTLesResPerTDto;
    }

    /**
     * @param schResTLesResPerTDto セットする schResTLesResPerTDto
     */
    public void setSchResTLesResPerTDto(SchResTLesResPerTDto schResTLesResPerTDto) {
        this.schResTLesResPerTDto = schResTLesResPerTDto;
    }

}