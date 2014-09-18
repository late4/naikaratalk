package com.naikara_talk.dto;

import java.sql.Timestamp;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>マスタ保守<br>
 * <b>クラス名称　　　:</b>時差管理マスタクラス<br>
 * <b>クラス概要　　　:</b>時差管理マスタDTO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/09 TECS 新規作成
 */
public class TimeManagMstDto extends AbstractDto {

    private String countryCd;                   // 国コード
    private String areaNoCd;                    // 時差地域NO
    private String timeMarkKbn;                 // 時間（符号）
    private int timeMinutes;                    // 時間(分)
    private String sumTimeFlg;                  // サマータイムフ
    private String sumTimeStrDt;                // サマータイム
    private String sumTimeEndDt;                // サマータイム
    private String sumTimeMarkKbn;              // サマータイム(時間)(符号)
    private int sumTimeMinutes;                 // サマータイム(
    private String remark;                      // 備考
    private int recordVerNo;                    // レコードバージョ
    private Timestamp insertDttm;               // 登録日時
    private String insertCd;                    // 登録者コード
    private Timestamp updateDttm;               // 更新日時
    private String updateCd;                    // 更新者コード
    private int returnCode;                     // リターンコード
    private String countryNm;                   // 国名
    private String areaNoNm;                    // 時差地域名
    private String timeMarkKbnNm;               // 時間（符号）名
    private String sumTimeMarkKbnNm;            // サマータイム(時間)(符号)名

    /**
     * 国コード取得<br>
     * <br>
     * 国コードを戻り値で返却する<br>
     * <br>
     * @return countryCd
     */
    public String getCountryCd() {
        return countryCd;
    }

    /**
     * 国コード設定<br>
     * <br>
     * 国コードを引数で設定する<br>
     * <br>
     * @param countryCd
     */
    public void setCountryCd(String countryCd) {
        this.countryCd = countryCd;
    }

    /**
     * 時差地域NO取得<br>
     * <br>
     * 時差地域NOを戻り値で返却する<br>
     * <br>
     * @return areaNoCd
     */
    public String getAreaNoCd() {
        return areaNoCd;
    }

    /**
     * 時差地域NO設定<br>
     * <br>
     * 時差地域NOを引数で設定する<br>
     * <br>
     * @param areaNoCd
     */
    public void setAreaNoCd(String areaNoCd) {
        this.areaNoCd = areaNoCd;
    }

    /**
     * 時間（符号）取得<br>
     * <br>
     * 時間（符号）を戻り値で返却する<br>
     * <br>
     * @return timeMarkKbn
     */
    public String getTimeMarkKbn() {
        return timeMarkKbn;
    }

    /**
     * 時間（符号）設定<br>
     * <br>
     * 時間（符号）を引数で設定する<br>
     * <br>
     * @param timeMarkKbn
     */
    public void setTimeMarkKbn(String timeMarkKbn) {
        this.timeMarkKbn = timeMarkKbn;
    }

    /**
     * 時間(分)取得<br>
     * <br>
     * 時間(分)を戻り値で返却する<br>
     * <br>
     * @return timeMinutes
     */
    public int getTimeMinutes() {
        return timeMinutes;
    }

    /**
     * 時間(分)設定<br>
     * <br>
     * 時間(分)を引数で設定する<br>
     * <br>
     * @param timeMinutes
     */
    public void setTimeMinutes(int timeMinutes) {
        this.timeMinutes = timeMinutes;
    }

    /**
     * サマータイムフラグ取得<br>
     * <br>
     * サマータイムフラグを戻り値で返却する<br>
     * <br>
     * @return sumTimeFlg
     */
    public String getSumTimeFlg() {
        return sumTimeFlg;
    }

    /**
     * サマータイムフラグ設定<br>
     * <br>
     * サマータイムフラグを引数で設定する<br>
     * <br>
     * @param sumTimeFlg
     */
    public void setSumTimeFlg(String sumTimeFlg) {
        this.sumTimeFlg = sumTimeFlg;
    }

    /**
     * サマータイム開始日取得<br>
     * <br>
     * サマータイム開始日を戻り値で返却する<br>
     * <br>
     * @return sumTimeStrDt
     */
    public String getSumTimeStrDt() {
        return sumTimeStrDt;
    }

    /**
     * サマータイム開始日設定<br>
     * <br>
     * サマータイム開始日を引数で設定する<br>
     * <br>
     * @param sumTimeStrDt
     */
    public void setSumTimeStrDt(String sumTimeStrDt) {
        this.sumTimeStrDt = sumTimeStrDt;
    }

    /**
     * サマータイム終了日取得<br>
     * <br>
     * サマータイム終了日を戻り値で返却する<br>
     * <br>
     * @return sumTimeEndDt
     */
    public String getSumTimeEndDt() {
        return sumTimeEndDt;
    }

    /**
     * サマータイム終了日設定<br>
     * <br>
     * サマータイム終了日を引数で設定する<br>
     * <br>
     * @param sumTimeEndDt
     */
    public void setSumTimeEndDt(String sumTimeEndDt) {
        this.sumTimeEndDt = sumTimeEndDt;
    }

    /**
     * サマータイム(時間)(符号)取得<br>
     * <br>
     * サマータイム(時間)(符号)を戻り値で返却する<br>
     * <br>
     * @return sumTimeMarkKbn
     */
    public String getSumTimeMarkKbn() {
        return sumTimeMarkKbn;
    }

    /**
     * サマータイム(時間)(符号)設定<br>
     * <br>
     * サマータイム(時間)(符号)を引数で設定する<br>
     * <br>
     * @param sumTimeMarkKbn
     */
    public void setSumTimeMarkKbn(String sumTimeMarkKbn) {
        this.sumTimeMarkKbn = sumTimeMarkKbn;
    }

    /**
     * サマータイム(時間)(分)取得<br>
     * <br>
     * サマータイム(時間)(分)を戻り値で返却する<br>
     * <br>
     * @return sumTimeMinutes
     */
    public int getSumTimeMinutes() {
        return sumTimeMinutes;
    }

    /**
     * サマータイム(時間)(分)設定<br>
     * <br>
     * サマータイム(時間)(分)を引数で設定する<br>
     * <br>
     * @param sumTimeMinutes
     */
    public void setSumTimeMinutes(int sumTimeMinutes) {
        this.sumTimeMinutes = sumTimeMinutes;
    }

    /**
     * 備考取得<br>
     * <br>
     * 備考を戻り値で返却する<br>
     * <br>
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 備考設定<br>
     * <br>
     * 備考を引数で設定する<br>
     * <br>
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
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

    /**
     * 国名取得<br>
     * <br>
     * 国名を戻り値で返却する<br>
     * <br>
     * @return countryNm
     */
    public String getCountryNm() {
        return countryNm;
    }

    /**
     * 国名設定<br>
     * <br>
     * 国名を引数で設定する<br>
     * <br>
     * @param countryNm セットする countryNm
     */
    public void setCountryNm(String countryNm) {
        this.countryNm = countryNm;
    }

    /**
     * 時差地域名取得<br>
     * <br>
     * 時差地域名を戻り値で返却する<br>
     * <br>
     * @return areaNoNm
     */
    public String getAreaNoNm() {
        return areaNoNm;
    }

    /**
     * 時差地域名設定<br>
     * <br>
     * 時差地域名を引数で設定する<br>
     * <br>
     * @param areaNoNm セットする areaNoNm
     */
    public void setAreaNoNm(String areaNoNm) {
        this.areaNoNm = areaNoNm;
    }

    /**
     * 時間（符号）名取得<br>
     * <br>
     * 時間（符号）名を戻り値で返却する<br>
     * <br>
     * @return timeMarkKbnNm
     */
    public String getTimeMarkKbnNm() {
        return timeMarkKbnNm;
    }

    /**
     * 時間（符号）名設定<br>
     * <br>
     * 時間（符号）名を引数で設定する<br>
     * <br>
     * @param timeMarkKbnNm セットする timeMarkKbnNm
     */
    public void setTimeMarkKbnNm(String timeMarkKbnNm) {
        this.timeMarkKbnNm = timeMarkKbnNm;
    }

    /**
     * サマータイム(時間)(符号)名取得<br>
     * <br>
     * サマータイム(時間)(符号)名を戻り値で返却する<br>
     * <br>
     * @return sumTimeMarkKbnNm
     */
    public String getSumTimeMarkKbnNm() {
        return sumTimeMarkKbnNm;
    }

    /**
     * サマータイム(時間)(符号)名設定<br>
     * <br>
     * サマータイム(時間)(符号)名を引数で設定する<br>
     * <br>
     * @param sumTimeMarkKbnNm セットする sumTimeMarkKbnNm
     */
    public void setSumTimeMarkKbnNm(String sumTimeMarkKbnNm) {
        this.sumTimeMarkKbnNm = sumTimeMarkKbnNm;
    }

}
