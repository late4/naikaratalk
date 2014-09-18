package com.naikara_talk.dto;

import java.io.InputStream;
import java.sql.Timestamp;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DTOクラス<br>
 * <b>クラス名称　　　:</b>請求書格納テーブルクラス<br>
 * <b>クラス概要　　　:</b>請求書格納テーブルDTO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/06/03 MIRA 新規作成
 */
public class ApplicationsDto extends AbstractDto {

    private String billIssueYm;       // 請求年月
    private InputStream applications;      // 請求書データ
    private int applicationsSize;      // 請求書データファイルサイズ
    private Timestamp insertDttm;   // 登録日時
    private String insertCd;        // 登録者コード
    private Timestamp updateDttm;   // 更新日時
    private String updateCd;        // 更新者コード
    private int returnCode;         // リターンコード

    /**
     * 請求年月取得<br>
     * <br>
     * 請求年月を戻り値で返却する<br>
     * <br>
     * @return billIssueYm
     */
    public String getBillIssueYm() {
        return billIssueYm;
    }

    /**
     * 請求年月設定<br>
     * <br>
     * 請求年月を引数で設定する<br>
     * <br>
     * @param billIssueYm
     */
    public void setBillIssueYm(String billIssueYm) {
        this.billIssueYm = billIssueYm;
    }

    /**
     * 請求書データ取得<br>
     * <br>
     * 請求書データを戻り値で返却する<br>
     * <br>
     * @return applications
     */
    public InputStream getApplications() {
        return applications;
    }

    /**
     * 請求書データ設定<br>
     * <br>
     * 請求書データを引数で設定する<br>
     * <br>
     * @param applications
     */
    public void setApplications(InputStream applications) {
        this.applications = applications;
    }

    /**
     * 請求書データファイルサイズ取得<br>
     * <br>
     * 請求書データファイルサイズを戻り値で返却する<br>
     * <br>
     * @return applicationsSize
     */
    public int getApplicationsSize() {
        return applicationsSize;
    }

    /**
     * 請求書データファイルサイズ設定<br>
     * <br>
     * 請求書データファイルサイズを引数で設定する<br>
     * <br>
     * @param applicationsSize
     */
    public void setApplicationsSize(int applicationsSize) {
        this.applicationsSize = applicationsSize;
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
