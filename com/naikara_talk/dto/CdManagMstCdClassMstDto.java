package com.naikara_talk.dto;

import java.sql.Timestamp;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DTOクラス<br>
 * <b>クラス名称　　　:</b>コード管理マスタ、コード種別マスタクラス<br>
 * <b>クラス概要　　　:</b>コード管理マスタ、コード種別マスタDTO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/06/03 TECS 新規作成
 */
public class CdManagMstCdClassMstDto extends AbstractDto {

    // コード管理マスタ データ領域
    private String cdCategory;           // コード種別
    private String managerCd;            // 汎用コード
    private String managerNm;            // 汎用フィールド
    private int orderBy;                 // 並び順
    private String remark;               // 備考
    private String studentMstEnm;        // 受講者マスタ英語項目名
    private String mark;                 // 表示用点数
    private String systemDelNgFlg;       // システム削除不可フラグ
    private int recordVerNoM;            // レコードバージョン番号
    private Timestamp insertDttmM;       // 登録日時
    private String insertCdM;            // 登録者コード
    private Timestamp updateDttmM;       // 更新日時
    private String updateCdM;            // 更新者コード

    // コード種別マスタ データ領域
    private String cdCategoryC;          // コード種別
    private String cdCategoryJnm;        // コード種別名
    private int orderByC;                // 並び順
    private String remarkC;              // 備考
    private String managerInsertNgFlgC;  // 管理ｺｰﾄﾞ登録不可ﾌﾗｸﾞ
    private int recordVerNoC;            // レコードバージョン番号
    private Timestamp insertDttmC;       // 登録日時
    private String insertCdC;            // 登録者コード
    private Timestamp updateDttmC;       // 更新日時
    private String updateCdC;            // 更新者コード

    private int returnCode;              // リターンコード

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
     * @return recordVerNoM
     */
    public int getRecordVerNoM() {
        return recordVerNoM;
    }

    /**
     * レコードバージョン番号設定<br>
     * <br>
     * レコードバージョン番号を引数で設定する<br>
     * <br>
     * @param recordVerNoM
     */
    public void setRecordVerNoM(int recordVerNoM) {
        this.recordVerNoM = recordVerNoM;
    }

    /**
     * 登録日時取得<br>
     * <br>
     * 登録日時を戻り値で返却する<br>
     * <br>
     * @return insertDttmM
     */
    public Timestamp getInsertDttmM() {
        return insertDttmM;
    }

    /**
     * 登録日時設定<br>
     * <br>
     * 登録日時を引数で設定する<br>
     * <br>
     * @param insertDttmM
     */
    public void setInsertDttmM(Timestamp insertDttmM) {
        this.insertDttmM = insertDttmM;
    }

    /**
     * 登録者コード取得<br>
     * <br>
     * 登録者コードを戻り値で返却する<br>
     * <br>
     * @return insertCdM
     */
    public String getInsertCdM() {
        return insertCdM;
    }

    /**
     * 登録者コード設定<br>
     * <br>
     * 登録者コードを引数で設定する<br>
     * <br>
     * @param insertCdM
     */
    public void setInsertCdM(String insertCdM) {
        this.insertCdM = insertCdM;
    }

    /**
     * 更新日時取得<br>
     * <br>
     * 更新日時を戻り値で返却する<br>
     * <br>
     * @return updateDttmM
     */
    public Timestamp getUpdateDttmM() {
        return updateDttmM;
    }

    /**
     * 更新日時設定<br>
     * <br>
     * 更新日時を引数で設定する<br>
     * <br>
     * @param updateDttmM
     */
    public void setUpdateDttmM(Timestamp updateDttmM) {
        this.updateDttmM = updateDttmM;
    }

    /**
     * 更新者コード取得<br>
     * <br>
     * 更新者コードを戻り値で返却する<br>
     * <br>
     * @return updateCdM
     */
    public String getUpdateCdM() {
        return updateCdM;
    }

    /**
     * 更新者コード設定<br>
     * <br>
     * 更新者コードを引数で設定する<br>
     * <br>
     * @param updateCdM
     */
    public void setUpdateCdM(String updateCdM) {
        this.updateCdM = updateCdM;
    }


    /**
     * コード種別取得<br>
     * <br>
     * コード種別を戻り値で返却する<br>
     * <br>
     * @return cdCategoryC
     */
    public String getCdCategoryC() {
        return cdCategoryC;
    }

    /**
     * コード種別設定<br>
     * <br>
     * コード種別を引数で設定する<br>
     * <br>
     * @param cdCategoryC
     */
    public void setCdCategoryC(String cdCategoryC) {
        this.cdCategoryC = cdCategoryC;
    }

    /**
     * コード種別名取得<br>
     * <br>
     * コード種別名を戻り値で返却する<br>
     * <br>
     * @return cdCategoryJnm
     */
    public String getCdCategoryJnm() {
        return cdCategoryJnm;
    }

    /**
     * コード種別名設定<br>
     * <br>
     * コード種別名を引数で設定する<br>
     * <br>
     * @param cdCategoryJnm
     */
    public void setCdCategoryJnm(String cdCategoryJnm) {
        this.cdCategoryJnm = cdCategoryJnm;
    }

    /**
     * 並び順取得<br>
     * <br>
     * 並び順を戻り値で返却する<br>
     * <br>
     * @return orderByC
     */
    public int getOrderByC() {
        return orderByC;
    }

    /**
     * 並び順設定<br>
     * <br>
     * 並び順を引数で設定する<br>
     * <br>
     * @param orderByC
     */
    public void setOrderByC(int orderByC) {
        this.orderByC = orderByC;
    }

    /**
     * 備考取得<br>
     * <br>
     * 備考を戻り値で返却する<br>
     * <br>
     * @return remarkC
     */
    public String getRemarkC() {
        return remarkC;
    }

    /**
     * 備考設定<br>
     * <br>
     * 備考を引数で設定する<br>
     * <br>
     * @param remarkC
     */
    public void setRemarkC(String remarkC) {
        this.remarkC = remarkC;
    }

    /**
     * 管理ｺｰﾄﾞ登録不可ﾌﾗｸﾞ取得<br>
     * <br>
     * 管理ｺｰﾄﾞ登録不可ﾌﾗｸﾞを戻り値で返却する<br>
     * <br>
     * @return managerInsertNgFlgC
     */
    public String getManagerInsertNgFlgC() {
        return managerInsertNgFlgC;
    }

    /**
     * 管理ｺｰﾄﾞ登録不可ﾌﾗｸﾞ設定<br>
     * <br>
     * 管理ｺｰﾄﾞ登録不可ﾌﾗｸﾞを引数で設定する<br>
     * <br>
     * @param managerInsertNgFlgC
     */
    public void setManagerInsertNgFlgC(String managerInsertNgFlgC) {
        this.managerInsertNgFlgC = managerInsertNgFlgC;
    }

    /**
     * レコードバージョン番号取得<br>
     * <br>
     * レコードバージョン番号を戻り値で返却する<br>
     * <br>
     * @return recordVerNoC
     */
    public int getRecordVerNoC() {
        return recordVerNoC;
    }

    /**
     * レコードバージョン番号設定<br>
     * <br>
     * レコードバージョン番号を引数で設定する<br>
     * <br>
     * @param recordVerNoC
     */
    public void setRecordVerNoC(int recordVerNoC) {
        this.recordVerNoC = recordVerNoC;
    }

    /**
     * 登録日時取得<br>
     * <br>
     * 登録日時を戻り値で返却する<br>
     * <br>
     * @return insertDttmC
     */
    public Timestamp getInsertDttmC() {
        return insertDttmC;
    }

    /**
     * 登録日時設定<br>
     * <br>
     * 登録日時を引数で設定する<br>
     * <br>
     * @param insertDttmC
     */
    public void setInsertDttmC(Timestamp insertDttmC) {
        this.insertDttmC = insertDttmC;
    }

    /**
     * 登録者コード取得<br>
     * <br>
     * 登録者コードを戻り値で返却する<br>
     * <br>
     * @return insertCdC
     */
    public String getInsertCdC() {
        return insertCdC;
    }

    /**
     * 登録者コード設定<br>
     * <br>
     * 登録者コードを引数で設定する<br>
     * <br>
     * @param insertCdC
     */
    public void setInsertCdC(String insertCdC) {
        this.insertCdC = insertCdC;
    }

    /**
     * 更新日時取得<br>
     * <br>
     * 更新日時を戻り値で返却する<br>
     * <br>
     * @return updateDttmC
     */
    public Timestamp getUpdateDttmC() {
        return updateDttmC;
    }

    /**
     * 更新日時設定<br>
     * <br>
     * 更新日時を引数で設定する<br>
     * <br>
     * @param updateDttmC
     */
    public void setUpdateDttmC(Timestamp updateDttmC) {
        this.updateDttmC = updateDttmC;
    }

    /**
     * 更新者コード取得<br>
     * <br>
     * 更新者コードを戻り値で返却する<br>
     * <br>
     * @return updateCdC
     */
    public String getUpdateCdC() {
        return updateCdC;
    }

    /**
     * 更新者コード設定<br>
     * <br>
     * 更新者コードを引数で設定する<br>
     * <br>
     * @param updateCdC
     */
    public void setUpdateCdC(String updateCdC) {
        this.updateCdC = updateCdC;
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
