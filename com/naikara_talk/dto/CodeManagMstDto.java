package com.naikara_talk.dto;

import java.sql.Timestamp;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DTOクラス<br>
 * <b>クラス名称　　　:</b>コード管理マスタクラス<br>
 * <b>クラス概要　　　:</b>コード管理マスタDTO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/06/03 TECS 新規作成
 */
public class CodeManagMstDto extends AbstractDto {

    private String cdCategory;      // コード種別
    private String managerCd;       // 汎用コード
    private String managerNm;       // 汎用フィールド
    private int orderBy;            // 並び順
    private String remark;          // 備考
    private String studentMstEnm;   // 受講者マスタ英語項目名
    private String mark;            // 表示用点数
    private String systemDelNgFlg;  // システム削除不可フラグ
    private int recordVerNo;        // レコードバージョン番号
    private Timestamp insertDttm;   // 登録日時
    private String insertCd;        // 登録者コード
    private Timestamp updateDttm;   // 更新日時
    private String updateCd;        // 更新者コード
    private int returnCode;         // リターンコード

    /**
     * コード種別取得<br>
     * <br>
     * コード種別を戻り値で返却する<br>
     * <br>
     * @return cdCategory
     */
    public String getCdCategory() {
        return cdCategory;
    }

    /**
     * コード種別設定<br>
     * <br>
     * コード種別を引数で設定する<br>
     * <br>
     * @param cdCategory
     */
    public void setCdCategory(String cdCategory) {
        this.cdCategory = cdCategory;
    }

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
     * 並び順取得<br>
     * <br>
     * 並び順を戻り値で返却する<br>
     * <br>
     * @return orderBy
     */
    public int getOrderBy() {
        return orderBy;
    }

    /**
     * 並び順設定<br>
     * <br>
     * 並び順を引数で設定する<br>
     * <br>
     * @param orderBy
     */
    public void setOrderBy(int orderBy) {
        this.orderBy = orderBy;
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
     * 受講者マスタ英語項目名取得<br>
     * <br>
     * 受講者マスタ英語項目名を戻り値で返却する<br>
     * <br>
     * @return studentMstEnm
     */
    public String getStudentMstEnm() {
        return studentMstEnm;
    }

    /**
     * 受講者マスタ英語項目名設定<br>
     * <br>
     * 受講者マスタ英語項目名を引数で設定する<br>
     * <br>
     * @param studentMstEnm
     */
    public void setStudentMstEnm(String studentMstEnm) {
        this.studentMstEnm = studentMstEnm;
    }

    /**
     * 表示用点数取得<br>
     * <br>
     * 表示用点数を戻り値で返却する<br>
     * <br>
     * @return mark
     */
    public String getMark() {
        return mark;
    }

    /**
     * 表示用点数設定<br>
     * <br>
     * 表示用点数を引数で設定する<br>
     * <br>
     * @param mark
     */
    public void setMark(String mark) {
        this.mark = mark;
    }

    /**
     * システム削除不可フラグ取得<br>
     * <br>
     * システム削除不可フラグを戻り値で返却する<br>
     * <br>
     * @return systemDelNgFlg
     */
    public String getSystemDelNgFlg() {
        return systemDelNgFlg;
    }

    /**
     * システム削除不可フラグ設定<br>
     * <br>
     * システム削除不可フラグを引数で設定する<br>
     * <br>
     * @param systemDelNgFlg
     */
    public void setSystemDelNgFlg(String systemDelNgFlg) {
        this.systemDelNgFlg = systemDelNgFlg;
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
