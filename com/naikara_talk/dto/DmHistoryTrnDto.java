package com.naikara_talk.dto;

import java.sql.Timestamp;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DTOクラス<br>
 * <b>クラス名称　　　:</b>DM履歴テーブルクラス<br>
 * <b>クラス概要　　　:</b>DM履歴テーブルDTO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/01 TECS 新規作成
 */
public class DmHistoryTrnDto extends AbstractDto {

    private String dmCd;           // DMコード
    private String sendId;         // 送信者ID
    private String sendNm;         // 送信者名
    private String subjectTitle;   // 件名
    private String mailText;       // メール本文255バイト分
    private int recordVerNo;       // レコードバージョン番号
    private Timestamp insertDttm;  // 登録日時
    private String insertCd;       // 登録者コード
    private Timestamp updateDttm;  // 更新日時
    private String updateCd;       // 更新者コード
    private int returnCode;        // リターンコード

    private String insertDt;       // 登録日

    /**
     * DMコード取得<br>
     * <br>
     * DMコードを戻り値で返却する<br>
     * <br>
     * @return dmCd
     */
    public String getDmCd() {
        return dmCd;
    }

    /**
     * DMコード設定<br>
     * <br>
     * DMコードを引数で設定する<br>
     * <br>
     * @param dmCd
     */
    public void setDmCd(String dmCd) {
        this.dmCd = dmCd;
    }

    /**
     * 送信者ID取得<br>
     * <br>
     * 送信者IDを戻り値で返却する<br>
     * <br>
     * @return sendId
     */
    public String getSendId() {
        return sendId;
    }

    /**
     * 送信者ID設定<br>
     * <br>
     * 送信者IDを引数で設定する<br>
     * <br>
     * @param sendId
     */
    public void setSendId(String sendId) {
        this.sendId = sendId;
    }

    /**
     * 送信者名取得<br>
     * <br>
     * 送信者名を戻り値で返却する<br>
     * <br>
     * @return sendNm
     */
    public String getSendNm() {
        return sendNm;
    }

    /**
     * 送信者名設定<br>
     * <br>
     * 送信者名を引数で設定する<br>
     * <br>
     * @param sendNm
     */
    public void setSendNm(String sendNm) {
        this.sendNm = sendNm;
    }

    /**
     * 件名取得<br>
     * <br>
     * 件名を戻り値で返却する<br>
     * <br>
     * @return subjectTitle
     */
    public String getSubjectTitle() {
        return subjectTitle;
    }

    /**
     * 件名設定<br>
     * <br>
     * 件名を引数で設定する<br>
     * <br>
     * @param subjectTitle
     */
    public void setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle;
    }

    /**
     * メール本文255バイト分取得<br>
     * <br>
     * メール本文255バイト分を戻り値で返却する<br>
     * <br>
     * @return mailText
     */
    public String getMailText() {
        return mailText;
    }

    /**
     * メール本文255バイト分設定<br>
     * <br>
     * メール本文255バイト分を引数で設定する<br>
     * <br>
     * @param mailText
     */
    public void setMailText(String mailText) {
        this.mailText = mailText;
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
     * 登録日取得<br>
     * <br>
     * 登録日を戻り値で返却する<br>
     * <br>
     * @return insertDt
     */
    public String getInsertDt() {
        return insertDt;
    }

    /**
     * 登録日設定<br>
     * <br>
     * 登録日を引数で設定する<br>
     * <br>
     * @param insertDt
     */
    public void setInsertDt(String insertDt) {
        this.insertDt = insertDt;
    }

}
