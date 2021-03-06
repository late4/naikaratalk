﻿package com.naikara_talk.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DTOクラス<br>
 * <b>クラス名称　　　:</b>コース別利用ポイントマスタクラス<br>
 * <b>クラス概要　　　:</b>コース別利用ポイントマスタDTO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/06/03 TECS 新規作成
 */
public class CourseUsePointMstDto extends AbstractDto {

    private String courseCd;       // コースコード
    private String usePointStrDt;  // 利用ポイント開始日
    private String usePointEndDt;  // 利用ポイント終了日
    private BigDecimal usePoint;   // 利用ポイント
    private String information;    // お知らせ
    private int recordVerNo;       // レコードバージョン番号
    private Timestamp insertDttm;  // 登録日時
    private String insertCd;       // 登録者コード
    private Timestamp updateDttm;  // 更新日時
    private String updateCd;       // 更新者コード
    private int returnCode;        // リターンコード


    /**
     * コースコード取得<br>
     * <br>
     * コースコードを戻り値で返却する<br>
     * <br>
     * @return courseCd
     */
    public String getCourseCd() {
        return courseCd;
    }

    /**
     * コースコード設定<br>
     * <br>
     * コースコードを引数で設定する<br>
     * <br>
     * @param courseCd
     */
    public void setCourseCd(String courseCd) {
        this.courseCd = courseCd;
    }

    /**
     * 利用ポイント開始日取得<br>
     * <br>
     * 利用ポイント開始日を戻り値で返却する<br>
     * <br>
     * @return usePointStrDt
     */
    public String getUsePointStrDt() {
        return usePointStrDt;
    }

    /**
     * 利用ポイント開始日設定<br>
     * <br>
     * 利用ポイント開始日を引数で設定する<br>
     * <br>
     * @param usePointStrDt
     */
    public void setUsePointStrDt(String usePointStrDt) {
        this.usePointStrDt = usePointStrDt;
    }

    /**
     * 利用ポイント終了日取得<br>
     * <br>
     * 利用ポイント終了日を戻り値で返却する<br>
     * <br>
     * @return usePointEndDt
     */
    public String getUsePointEndDt() {
        return usePointEndDt;
    }

    /**
     * 利用ポイント終了日設定<br>
     * <br>
     * 利用ポイント終了日を引数で設定する<br>
     * <br>
     * @param usePointEndDt
     */
    public void setUsePointEndDt(String usePointEndDt) {
        this.usePointEndDt = usePointEndDt;
    }

    /**
     * 利用ポイント取得<br>
     * <br>
     * 利用ポイントを戻り値で返却する<br>
     * <br>
     * @return usePoint
     */
    public BigDecimal getUsePoint() {
        return usePoint;
    }

    /**
     * 利用ポイント設定<br>
     * <br>
     * 利用ポイントを引数で設定する<br>
     * <br>
     * @param usePoint
     */
    public void setUsePoint(BigDecimal usePoint) {
        this.usePoint = usePoint;
    }

    /**
     * お知らせ取得<br>
     * <br>
     * お知らせを戻り値で返却する<br>
     * <br>
     * @return information
     */
    public String getInformation() {
        return information;
    }

    /**
     * お知らせ設定<br>
     * <br>
     * お知らせを引数で設定する<br>
     * <br>
     * @param information
     */
    public void setInformation(String information) {
        this.information = information;
    }

    /**
     * レコードバージョン番号取得<br>
     * <br>
     * レコードバージョン番号を戻り値で返却する<br>
     * <br>
     * @return recordVerNo
     */
    public int getRecordVerNo() {
        return recordVerNo;
    }

    /**
     * レコードバージョン番号設定<br>
     * <br>
     * レコードバージョン番号を引数で設定する<br>
     * <br>
     * @param recordVerNo
     */
    public void setRecordVerNo(int recordVerNo) {
        this.recordVerNo = recordVerNo;
    }

    /**
     * 登録日時取得<br>
     * <br>
     * 登録日時を戻り値で返却する<br>
     * <br>
     * @return insertDttm
     */
    public Timestamp getInsertDttm() {
        return insertDttm;
    }

    /**
     * 登録日時設定<br>
     * <br>
     * 登録日時を引数で設定する<br>
     * <br>
     * @param insertDttm
     */
    public void setInsertDttm(Timestamp insertDttm) {
        this.insertDttm = insertDttm;
    }

    /**
     * 登録者コード取得<br>
     * <br>
     * 登録者コードを戻り値で返却する<br>
     * <br>
     * @return insertCd
     */
    public String getInsertCd() {
        return insertCd;
    }

    /**
     * 登録者コード設定<br>
     * <br>
     * 登録者コードを引数で設定する<br>
     * <br>
     * @param insertCd
     */
    public void setInsertCd(String insertCd) {
        this.insertCd = insertCd;
    }

    /**
     * 更新日時取得<br>
     * <br>
     * 更新日時を戻り値で返却する<br>
     * <br>
     * @return updateDttm
     */
    public Timestamp getUpdateDttm() {
        return updateDttm;
    }

    /**
     * 更新日時設定<br>
     * <br>
     * 更新日時を引数で設定する<br>
     * <br>
     * @param updateDttm
     */
    public void setUpdateDttm(Timestamp updateDttm) {
        this.updateDttm = updateDttm;
    }

    /**
     * 更新者コード取得<br>
     * <br>
     * 更新者コードを戻り値で返却する<br>
     * <br>
     * @return updateCd
     */
    public String getUpdateCd() {
        return updateCd;
    }

    /**
     * 更新者コード設定<br>
     * <br>
     * 更新者コードを引数で設定する<br>
     * <br>
     * @param updateCd
     */
    public void setUpdateCd(String updateCd) {
        this.updateCd = updateCd;
    }

    /**
     * リターンコード取得<br>
     * <br>
     * リターンコードを戻り値で返却する<br>
     * <br>
     * @return returnCode
     */
    public int getReturnCode() {
        return returnCode;
    }

    /**
     * リターンコード設定<br>
     * <br>
     * リターンコードを引数で設定する<br>
     * <br>
     * @param returnCode
     */
    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

}
