package com.naikara_talk.model;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.naikara_talk.dto.HolidayListDto;
import com.naikara_talk.dto.SchResTCodeManagMDto;
import com.naikara_talk.dto.ScheduleReservationTrnDto;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>講師スケジュール。<br>
 * <b>クラス名称       :</b>講師スケジュールModelクラス。<br>
 * <b>クラス概要       :</b>講師スケジュールModel。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/09 TECS 新規作成
 * <b>                 :</b>2014/06/02 TECS レッスン予約に関する「応相談」対応
 */
public class TeacherScheduleModel implements Model {

    private static final long serialVersionUID = 1L;

    /** 利用者ID */
    private String userId;
    /** 時間（符号） */
    private String timeMark;
    /** 時間(分) */
    private BigDecimal timeMinutes;
    /** サマータイム(時間)(符号) */
    private String sumTimeMark;
    /** サマータイム(分) */
    private BigDecimal sumTimeMinutes;
    /** 備考 */
    private String remark;
    /** 講師ID */
    private String teacherId;
    /** 基点日フラグ */
    private int dateFlg;


    // 2014/06/02 レッスン予約に関する「応相談」対応 Upd Start
    /** ｢00：00 - 06：00｣部分のチェックボックス */
    //private String[] Midnight_chkn;
    private String midnight;
    /** ｢06：00 - 12：00｣部分のチェックボックス */
    //private String[] Morning_chkn;
    private String morning;
    /** ｢12：00 - 18：00｣部分のチェックボックス */
    //private String[] Daytime_chkn;
    private String daytime;
    /** ｢18：00 - 24：00｣部分のチェックボックス */
    //private String[] Night_chkn;
    private String night;
    // 2014/06/02 レッスン予約に関する「応相談」対応 Upd End


    /** 日付曜日祝日クラス */
    private ArrayList<HolidayListDto> holidayListDto;
    /** 講師予定予約テーブルの データ0 */
    private ArrayList<SchResTCodeManagMDto> schResTCodeManagMDto0;
    /** 講師予定予約テーブルの データ1 */
    private ArrayList<SchResTCodeManagMDto> schResTCodeManagMDto1;
    /** 講師予定予約テーブルの データ2 */
    private ArrayList<SchResTCodeManagMDto> schResTCodeManagMDto2;
    /** 講師予定予約テーブルの データ3 */
    private ArrayList<SchResTCodeManagMDto> schResTCodeManagMDto3;
    /** 講師予定予約テーブルDTO */
    private ArrayList<ScheduleReservationTrnDto> scheduleReservationTrnDto;

    /**
     * @return userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId セットする userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return timeMark
     */
    public String getTimeMark() {
        return timeMark;
    }

    /**
     * @param timeMark セットする timeMark
     */
    public void setTimeMark(String timeMark) {
        this.timeMark = timeMark;
    }

    /**
     * @return sumTimeMark
     */
    public String getSumTimeMark() {
        return sumTimeMark;
    }

    /**
     * @param sumTimeMark セットする sumTimeMark
     */
    public void setSumTimeMark(String sumTimeMark) {
        this.sumTimeMark = sumTimeMark;
    }

    /**
     * @return sumTimeMinutes
     */
    public BigDecimal getSumTimeMinutes() {
        return sumTimeMinutes;
    }

    /**
     * @param sumTimeMinutes セットする sumTimeMinutes
     */
    public void setSumTimeMinutes(BigDecimal sumTimeMinutes) {
        this.sumTimeMinutes = sumTimeMinutes;
    }

    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark セットする remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
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

    // 2014/06/02 レッスン予約に関する「応相談」対応 Upd Start
    /**
     * @return midnight_chkn
     */
    //public String[] getMidnight_chkn() {
    //    return Midnight_chkn;
    //}

    /**
     * @param midnight_chkn セットする midnight_chkn
     */
    //public void setMidnight_chkn(String[] midnight_chkn) {
    //    Midnight_chkn = midnight_chkn;
    //}


    ///**
    // * @return morning_chkn
    // */
    //public String[] getMorning_chkn() {
    //    return Morning_chkn;
    //}

    ///**
    // * @param morning_chkn セットする morning_chkn
    // */
    //public void setMorning_chkn(String[] morning_chkn) {
    //    Morning_chkn = morning_chkn;
    //}

    ///**
    // * @return daytime_chkn
    // */
    //public String[] getDaytime_chkn() {
    //    return Daytime_chkn;
    //}

    ///**
    // * @param daytime_chkn セットする daytime_chkn
    // */
    //public void setDaytime_chkn(String[] daytime_chkn) {
    //    Daytime_chkn = daytime_chkn;
    //}

    ///**
    // * @return night_chkn
    // */
    //public String[] getNight_chkn() {
    //    return Night_chkn;
    //}

    ///**
    // * @param night_chkn セットする night_chkn
    // */
    //public void setNight_chkn(String[] night_chkn) {
    //    Night_chkn = night_chkn;
    //}

    /**
     * @return midnight
     */
    public String getMidnight() {
        return midnight;
    }

    /**
     * @param midnight セットする ｢00：00 - 06：00｣部分の9コマ×2週間
     */
    public void setMidnight(String midnight) {
        this.midnight = midnight;
    }

    /**
     * @return morning
     */
    public String getMorning() {
        return morning;
    }

    /**
     * @param morning セットする ｢06：00 - 12：00｣部分の9コマ×2週間
     */
    public void setMorning(String morning) {
        this.morning = morning;
    }

    /**
     * @return daytime
     */
    public String getDaytime() {
        return daytime;
    }

    /**
     * @param daytime セットする ｢12：00 - 18：00｣部分の9コマ×2週間
     */
    public void setDaytime(String daytime) {
        this.daytime = daytime;
    }

    /**
     * @return night
     */
    public String getNight() {
        return night;
    }

    /**
     * @param night セットする ｢18：00 - 24：00｣部分の9コマ×2週間
     */
    public void setNight(String night) {
        this.night = night;
    }
    // 2014/06/02 レッスン予約に関する「応相談」対応 Upd End

    /**
     * @return holidayListDto
     */
    public ArrayList<HolidayListDto> getHolidayListDto() {
        return holidayListDto;
    }

    /**
     * @param holidayListDto セットする holidayListDto
     */
    public void setHolidayListDto(ArrayList<HolidayListDto> holidayListDto) {
        this.holidayListDto = holidayListDto;
    }

    /**
     * @return schResTCodeManagMDto0
     */
    public ArrayList<SchResTCodeManagMDto> getSchResTCodeManagMDto0() {
        return schResTCodeManagMDto0;
    }

    /**
     * @param schResTCodeManagMDto0 セットする schResTCodeManagMDto0
     */
    public void setSchResTCodeManagMDto0(ArrayList<SchResTCodeManagMDto> schResTCodeManagMDto0) {
        this.schResTCodeManagMDto0 = schResTCodeManagMDto0;
    }

    /**
     * @return schResTCodeManagMDto1
     */
    public ArrayList<SchResTCodeManagMDto> getSchResTCodeManagMDto1() {
        return schResTCodeManagMDto1;
    }

    /**
     * @param schResTCodeManagMDto1 セットする schResTCodeManagMDto1
     */
    public void setSchResTCodeManagMDto1(ArrayList<SchResTCodeManagMDto> schResTCodeManagMDto1) {
        this.schResTCodeManagMDto1 = schResTCodeManagMDto1;
    }

    /**
     * @return schResTCodeManagMDto2
     */
    public ArrayList<SchResTCodeManagMDto> getSchResTCodeManagMDto2() {
        return schResTCodeManagMDto2;
    }

    /**
     * @param schResTCodeManagMDto2 セットする schResTCodeManagMDto2
     */
    public void setSchResTCodeManagMDto2(ArrayList<SchResTCodeManagMDto> schResTCodeManagMDto2) {
        this.schResTCodeManagMDto2 = schResTCodeManagMDto2;
    }

    /**
     * @return schResTCodeManagMDto3
     */
    public ArrayList<SchResTCodeManagMDto> getSchResTCodeManagMDto3() {
        return schResTCodeManagMDto3;
    }

    /**
     * @param schResTCodeManagMDto3 セットする schResTCodeManagMDto3
     */
    public void setSchResTCodeManagMDto3(ArrayList<SchResTCodeManagMDto> schResTCodeManagMDto3) {
        this.schResTCodeManagMDto3 = schResTCodeManagMDto3;
    }

    /**
     * @return dateFlg
     */
    public int getDateFlg() {
        return dateFlg;
    }

    /**
     * @param dateFlg セットする dateFlg
     */
    public void setDateFlg(int dateFlg) {
        this.dateFlg = dateFlg;
    }

    /**
     * @return timeMinutes
     */
    public BigDecimal getTimeMinutes() {
        return timeMinutes;
    }

    /**
     * @param timeMinutes セットする timeMinutes
     */
    public void setTimeMinutes(BigDecimal timeMinutes) {
        this.timeMinutes = timeMinutes;
    }

    /**
     * @return scheduleReservationTrnDto
     */
    public ArrayList<ScheduleReservationTrnDto> getScheduleReservationTrnDto() {
        return scheduleReservationTrnDto;
    }

    /**
     * @param scheduleReservationTrnDto セットする scheduleReservationTrnDto
     */
    public void setScheduleReservationTrnDto(ArrayList<ScheduleReservationTrnDto> scheduleReservationTrnDto) {
        this.scheduleReservationTrnDto = scheduleReservationTrnDto;
    }

}