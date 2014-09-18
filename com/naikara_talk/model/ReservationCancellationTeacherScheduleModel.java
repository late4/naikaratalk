package com.naikara_talk.model;

import java.util.List;

import com.naikara_talk.dto.HolidayListDto;
import com.naikara_talk.dto.SchResTCodeManagMDto;
import com.naikara_talk.dto.TeacherCourseDto;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_予約処理<br>
 * <b>クラス名称       :</b>予約スケジュール初期化Modelクラス<br>
 * <b>クラス概要       :</b>受講者が指定した講師の予約スケジュールを表示して、レッスン予約の登録／取消ができる。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/25 TECS 新規作成
 */
public class ReservationCancellationTeacherScheduleModel implements Model {

    private static final long serialVersionUID = 1L;

    /** 講師ID */
    private String teacherId;

    /** 講師名（ニックネーム） */
    private String teacherNm;

    /** 性別 */
    private String genderKbnNm;

    /** 母国語 */
    private String nativeLang;

    /** 国籍 */
    private String nationality;

    /** 講師別コースマスタ(+コースマスタ)DTOリスト */
    private List<TeacherCourseDto> teacherCourseDtoList;

    /** 日数 */
    private int dayCnt;

    /** ログインID */
    private String loginId;

    /** 明細0チェックBOX */
    private String[] details0;

    /** 明細1チェックBOX */
    private String[] details1;

    /** 明細2チェックBOX */
    private String[] details2;

    /** 明細3チェックBOX */
    private String[] details3;

    /** 日付曜日祝日クラス */
    private List<HolidayListDto> holidayList;

    /** 講師予定予約テーブルの明細データ0 */
    private List<SchResTCodeManagMDto> schResTCodeManagMDtoList0;

    /** 講師予定予約テーブルの明細データ1 */
    private List<SchResTCodeManagMDto> schResTCodeManagMDtoList1;

    /** 講師予定予約テーブルの明細データ2 */
    private List<SchResTCodeManagMDto> schResTCodeManagMDtoList2;

    /** 講師予定予約テーブルの明細データ3 */
    private List<SchResTCodeManagMDto> schResTCodeManagMDtoList3;

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
     * @return genderKbnNm
     */
    public String getGenderKbnNm() {
        return genderKbnNm;
    }

    /**
     * @param genderKbnNm セットする genderKbnNm
     */
    public void setGenderKbnNm(String genderKbnNm) {
        this.genderKbnNm = genderKbnNm;
    }

    /**
     * @return nativeLang
     */
    public String getNativeLang() {
        return nativeLang;
    }

    /**
     * @param nativeLang セットする nativeLang
     */
    public void setNativeLang(String nativeLang) {
        this.nativeLang = nativeLang;
    }

    /**
     * @return nationality
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * @param nationality セットする nationality
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    /**
     * @return teacherCourseDtoList
     */
    public List<TeacherCourseDto> getTeacherCourseDtoList() {
        return teacherCourseDtoList;
    }

    /**
     * @param teacherCourseDtoList セットする teacherCourseDtoList
     */
    public void setTeacherCourseDtoList(List<TeacherCourseDto> teacherCourseDtoList) {
        this.teacherCourseDtoList = teacherCourseDtoList;
    }

    /**
     * @return dayCnt
     */
    public int getDayCnt() {
        return dayCnt;
    }

    /**
     * @param dayCnt セットする dayCnt
     */
    public void setDayCnt(int dayCnt) {
        this.dayCnt = dayCnt;
    }

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
     * @return details0
     */
    public String[] getDetails0() {
        return details0;
    }

    /**
     * @param details0 セットする details0
     */
    public void setDetails0(String[] details0) {
        this.details0 = details0;
    }

    /**
     * @return details1
     */
    public String[] getDetails1() {
        return details1;
    }

    /**
     * @param details1 セットする details1
     */
    public void setDetails1(String[] details1) {
        this.details1 = details1;
    }

    /**
     * @return details2
     */
    public String[] getDetails2() {
        return details2;
    }

    /**
     * @param details2 セットする details2
     */
    public void setDetails2(String[] details2) {
        this.details2 = details2;
    }

    /**
     * @return details3
     */
    public String[] getDetails3() {
        return details3;
    }

    /**
     * @param details3 セットする details3
     */
    public void setDetails3(String[] details3) {
        this.details3 = details3;
    }

    /**
     * @return holidayList
     */
    public List<HolidayListDto> getHolidayList() {
        return holidayList;
    }

    /**
     * @param holidayList セットする holidayList
     */
    public void setHolidayList(List<HolidayListDto> holidayList) {
        this.holidayList = holidayList;
    }

    /**
     * @return schResTCodeManagMDtoList0
     */
    public List<SchResTCodeManagMDto> getSchResTCodeManagMDtoList0() {
        return schResTCodeManagMDtoList0;
    }

    /**
     * @param schResTCodeManagMDtoList0 セットする schResTCodeManagMDtoList0
     */
    public void setSchResTCodeManagMDtoList0(List<SchResTCodeManagMDto> schResTCodeManagMDtoList0) {
        this.schResTCodeManagMDtoList0 = schResTCodeManagMDtoList0;
    }

    /**
     * @return schResTCodeManagMDtoList1
     */
    public List<SchResTCodeManagMDto> getSchResTCodeManagMDtoList1() {
        return schResTCodeManagMDtoList1;
    }

    /**
     * @param schResTCodeManagMDtoList1 セットする schResTCodeManagMDtoList1
     */
    public void setSchResTCodeManagMDtoList1(List<SchResTCodeManagMDto> schResTCodeManagMDtoList1) {
        this.schResTCodeManagMDtoList1 = schResTCodeManagMDtoList1;
    }

    /**
     * @return schResTCodeManagMDtoList2
     */
    public List<SchResTCodeManagMDto> getSchResTCodeManagMDtoList2() {
        return schResTCodeManagMDtoList2;
    }

    /**
     * @param schResTCodeManagMDtoList2 セットする schResTCodeManagMDtoList2
     */
    public void setSchResTCodeManagMDtoList2(List<SchResTCodeManagMDto> schResTCodeManagMDtoList2) {
        this.schResTCodeManagMDtoList2 = schResTCodeManagMDtoList2;
    }

    /**
     * @return schResTCodeManagMDtoList3
     */
    public List<SchResTCodeManagMDto> getSchResTCodeManagMDtoList3() {
        return schResTCodeManagMDtoList3;
    }

    /**
     * @param schResTCodeManagMDtoList3 セットする schResTCodeManagMDtoList3
     */
    public void setSchResTCodeManagMDtoList3(List<SchResTCodeManagMDto> schResTCodeManagMDtoList3) {
        this.schResTCodeManagMDtoList3 = schResTCodeManagMDtoList3;
    }
}