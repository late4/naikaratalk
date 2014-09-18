package com.naikara_talk.dto;

import java.sql.Timestamp;
import java.math.BigDecimal;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DTOクラス<br>
 * <b>クラス名称　　　:</b>講師別支払比率マスタクラス<br>
 * <b>クラス概要　　　:</b>講師別支払比率マスタDTO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/01 TECS 新規作成
 *                         2013/11/14 TECS 要望対応 No1022-6(講師支払比率99⇒99.999)
 */
public class TeacherRateMstDto extends AbstractDto {

    private String userId;             // 講師ID (利用者ID)
    private String startDt;            // 適用期間：開始日
    private String endDt;              // 適用期間：終了日
    //No1022-6 講師支払比率99⇒99.999へ変更対応-Start
    //private int paymentRate;           // 支払比率
    private BigDecimal paymentRate;    // 支払比率
    //No1022-6 講師支払比率99⇒99.999へ変更対応-End

    private String paymentCycleCd;     // 支払サイクルコード
    private String withholdingTaxKbn;  // 源泉税有無区分
    private int recordVerNo;           // レコードバージョン番号
    private Timestamp insertDttm;      // 登録日時
    private String insertCd;           // 登録者コード
    private Timestamp updateDttm;      // 更新日時
    private String updateCd;           // 更新者コード
    private int returnCode;            // リターンコード

    private String paymentCycleCdNm;     // 支払サイクルコード名
    private String withholdingTaxKbnNm;  // 源泉税有無区分名

    /**
     * 講師ID (利用者ID)取得<br>
     * <br>
     * 講師ID (利用者ID)を戻り値で返却する<br>
     * <br>
     * @return userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 講師ID (利用者ID)設定<br>
     * <br>
     * 講師ID (利用者ID)を引数で設定する<br>
     * <br>
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 適用期間：開始日取得<br>
     * <br>
     * 適用期間：開始日を戻り値で返却する<br>
     * <br>
     * @return startDt
     */
    public String getStartDt() {
        return startDt;
    }

    /**
     * 適用期間：開始日設定<br>
     * <br>
     * 適用期間：開始日を引数で設定する<br>
     * <br>
     * @param startDt
     */
    public void setStartDt(String startDt) {
        this.startDt = startDt;
    }

    /**
     * 適用期間：終了日取得<br>
     * <br>
     * 適用期間：終了日を戻り値で返却する<br>
     * <br>
     * @return endDt
     */
    public String getEndDt() {
        return endDt;
    }

    /**
     * 適用期間：終了日設定<br>
     * <br>
     * 適用期間：終了日を引数で設定する<br>
     * <br>
     * @param endDt
     */
    public void setEndDt(String endDt) {
        this.endDt = endDt;
    }

    /**
     * 支払比率取得<br>
     * <br>
     * 支払比率を戻り値で返却する<br>
     * <br>
     * @return paymentRate
     */
    public BigDecimal getPaymentRate() {
        return paymentRate;
    }

    /**
     * 支払比率設定<br>
     * <br>
     * 支払比率を引数で設定する<br>
     * <br>
     * @param paymentRate
     */
    public void setPaymentRate(BigDecimal paymentRate) {
        this.paymentRate = paymentRate;
    }

    /**
     * 支払サイクルコード取得<br>
     * <br>
     * 支払サイクルコードを戻り値で返却する<br>
     * <br>
     * @return paymentCycleCd
     */
    public String getPaymentCycleCd() {
        return paymentCycleCd;
    }

    /**
     * 支払サイクルコード設定<br>
     * <br>
     * 支払サイクルコードを引数で設定する<br>
     * <br>
     * @param paymentCycleCd
     */
    public void setPaymentCycleCd(String paymentCycleCd) {
        this.paymentCycleCd = paymentCycleCd;
    }

    /**
     * 源泉税有無区分取得<br>
     * <br>
     * 源泉税有無区分を戻り値で返却する<br>
     * <br>
     * @return withholdingTaxKbn
     */
    public String getWithholdingTaxKbn() {
        return withholdingTaxKbn;
    }

    /**
     * 源泉税有無区分設定<br>
     * <br>
     * 源泉税有無区分を引数で設定する<br>
     * <br>
     * @param withholdingTaxKbn
     */
    public void setWithholdingTaxKbn(String withholdingTaxKbn) {
        this.withholdingTaxKbn = withholdingTaxKbn;
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
     * 支払サイクルコード名取得<br>
     * <br>
     * 支払サイクルコード名を戻り値で返却する<br>
     * <br>
     * @return paymentCycleCdNm
     */
    public String getPaymentCycleCdNm() {
        return paymentCycleCdNm;
    }

    /**
     * 支払サイクルコード名設定<br>
     * <br>
     * 支払サイクルコード名を引数で設定する<br>
     * <br>
     * @param paymentCycleCdNm
     */
    public void setPaymentCycleCdNm(String paymentCycleCdNm) {
        this.paymentCycleCdNm = paymentCycleCdNm;
    }

    /**
     * 源泉税有無区分名取得<br>
     * <br>
     * 源泉税有無区分名を戻り値で返却する<br>
     * <br>
     * @return withholdingTaxKbnNm
     */
    public String getWithholdingTaxKbnNm() {
        return withholdingTaxKbnNm;
    }

    /**
     * 源泉税有無区分名設定<br>
     * <br>
     * 源泉税有無区分名を引数で設定する<br>
     * <br>
     * @param withholdingTaxKbnNm
     */
    public void setWithholdingTaxKbnNm(String withholdingTaxKbnNm) {
        this.withholdingTaxKbnNm = withholdingTaxKbnNm;
    }

}
