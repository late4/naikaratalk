package com.naikara_talk.dto;

import java.sql.Timestamp;

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
public class CodeClassMstDto extends AbstractDto {

    private String cdCategory;          // コード種別
    private String cdCategoryJnm;       // コード種別名
    private int orderBy;                // 並び順
    private String remark;              // 備考
    private String managerInsertNgFlg;  // 管理ｺｰﾄﾞ登録不可ﾌﾗｸﾞ
    private int recordVerNo;            // レコードバージョン番号
    private Timestamp insertDttm;       // 登録日時
    private String insertCd;            // 登録者コード
    private Timestamp updateDttm;       // 更新日時
    private String updateCd;            // 更新者コード
    private int returnCode;             // リターンコード

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
     * 管理ｺｰﾄﾞ登録不可ﾌﾗｸﾞ取得<br>
     * <br>
     * 管理ｺｰﾄﾞ登録不可ﾌﾗｸﾞを戻り値で返却する<br>
     * <br>
     * @return managerInsertNgFlg
     */
    public String getManagerInsertNgFlg() {
        return managerInsertNgFlg;
    }

    /**
     * 管理ｺｰﾄﾞ登録不可ﾌﾗｸﾞ設定<br>
     * <br>
     * 管理ｺｰﾄﾞ登録不可ﾌﾗｸﾞを引数で設定する<br>
     * <br>
     * @param managerInsertNgFlg
     */
    public void setManagerInsertNgFlg(String managerInsertNgFlg) {
        this.managerInsertNgFlg = managerInsertNgFlg;
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
