package com.naikara_talk.dto;

import java.util.ArrayList;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DTOクラス<br>
 * <b>クラス名称　　　:</b>コード種別マスタクラス<br>
 * <b>クラス概要　　　:</b>コード種別マスタDTO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/06/03 TECS 新規作成
 */
public class SchResTCodeManagMDto extends AbstractDto {

    private String managerCd;                                               // 汎用コード
    private String managerNm;                                               // 汎用フィールド
    private String localTimeFromTo;                                         // ローカルタイム
    private String sumTimeFromTo;                                           // サマータイム
    private ArrayList<ScheduleReservationTrnDto> scheduleReservationTrnDto; // 講師予定予約テーブルDTOリスト
    private ArrayList<SchResTLesResPerTDto> schResTLesResPerTDtoList;       // 講師予定予約テーブル、レッスン予実テーブルDTOリスト

    /**
     * 汎用コード取得<br>
     * <br>
     * 汎用コードを戻り値で返却する<br>
     * <br>
     * @return managerCd
     */
    public String getManagerCd() {
        return managerCd;
    }

    /**
     * 汎用コード設定<br>
     * <br>
     * 汎用コードを引数で設定する<br>
     * <br>
     * @param managerCd
     */
    public void setManagerCd(String managerCd) {
        this.managerCd = managerCd;
    }

    /**
     * 汎用フィールド取得<br>
     * <br>
     * 汎用フィールドを戻り値で返却する<br>
     * <br>
     * @return managerNm
     */
    public String getManagerNm() {
        return managerNm;
    }

    /**
     * 汎用フィールド設定<br>
     * <br>
     * 汎用フィールドを引数で設定する<br>
     * <br>
     * @param managerNm
     */
    public void setManagerNm(String managerNm) {
        this.managerNm = managerNm;
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
     * 講師予定予約テーブルDTOリスト取得<br>
     * <br>
     * 講師予定予約テーブルDTOリストを戻り値で返却する<br>
     * <br>
     * @return scheduleReservationTrnDto
     */
    public ArrayList<ScheduleReservationTrnDto> getScheduleReservationTrnDto() {
        return scheduleReservationTrnDto;
    }

    /**
     * 講師予定予約テーブルDTOリスト設定<br>
     * <br>
     * 講師予定予約テーブルDTOリストを引数で設定する<br>
     * <br>
     * @param scheduleReservationTrnDto
     */
    public void setScheduleReservationTrnDto(ArrayList<ScheduleReservationTrnDto> scheduleReservationTrnDto) {
        this.scheduleReservationTrnDto = scheduleReservationTrnDto;
    }

    /**
     * 講師予定予約テーブル、レッスン予実テーブルDTOリスト取得<br>
     * <br>
     * 講師予定予約テーブル、レッスン予実テーブルDTOリストを戻り値で返却する<br>
     * <br>
     * @return schResTLesResPerTDtoList
     */
    public ArrayList<SchResTLesResPerTDto> getSchResTLesResPerTDtoList() {
        return schResTLesResPerTDtoList;
    }

    /**
     * 講師予定予約テーブル、レッスン予実テーブルDTOリスト設定<br>
     * <br>
     * 講師予定予約テーブル、レッスン予実テーブルDTOリストを引数で設定する<br>
     * <br>
     * @param schResTLesResPerTDtoList
     */
    public void setSchResTLesResPerTDtoList(ArrayList<SchResTLesResPerTDto> schResTLesResPerTDtoList) {
        this.schResTLesResPerTDtoList = schResTLesResPerTDtoList;
    }

}
