package com.naikara_talk.sessiondata;

import java.util.List;

import com.naikara_talk.dto.SchResTLesResPerTDto;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称 :</b>お客様(個人)_予約処理<br>
 * <b>クラス名称　　　:</b>コース名選択ページのセッション情報クラス。<br>
 * <b>クラス概要　　　:</b>予約／取消確認ページの戻る用のセッション情報。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b> 2013/07/29 TECS 新規作成。
 */
public class SessionReservationCancellationCourseSelection implements SessionData {

    /** ヘッダの戻るリンク用のSessionKey */
    private static final String HEADER_RETURN_KEY = "ReservationCancellationCourseSelection";

    /** 講師ID */
    private String teacherId;

    /** 講師名 */
    private String teacherNm;

    /** コース */
    private String[] course;

    /** 講師予定予約テーブル、レッスン予実テーブルDTOリスト */
    private List<SchResTLesResPerTDto> schResTLesResPerTDtoList;

    /**
     * このセッションデータのキーを返却する
     */
    public static String getKey() {
        return HEADER_RETURN_KEY;
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
     * @return course
     */
    public String[] getCourse() {
        return course;
    }

    /**
     * @param course セットする course
     */
    public void setCourse(String[] course) {
        this.course = course;
    }

    /**
     * @return schResTLesResPerTDtoList
     */
    public List<SchResTLesResPerTDto> getSchResTLesResPerTDtoList() {
        return schResTLesResPerTDtoList;
    }

    /**
     * @param schResTLesResPerTDtoList セットする schResTLesResPerTDtoList
     */
    public void setSchResTLesResPerTDtoList(List<SchResTLesResPerTDto> schResTLesResPerTDtoList) {
        this.schResTLesResPerTDtoList = schResTLesResPerTDtoList;
    }

}