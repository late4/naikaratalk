package com.naikara_talk.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DTOクラス<br>
 * <b>クラス名称　　　:</b>商品購入テーブルクラス<br>
 * <b>クラス概要　　　:</b>商品購入テーブルDTO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/06/03 TECS 新規作成
 */
public class GoodsPurchaseTrnDto extends AbstractDto {

    private String purchaseId;           // 購入ID
    private String paymentMethodKbn;     // 支払方法区分
    private String studentId;            // 受講者ID (購入者ID)
    private String purchaseDt;           // 購入日
    private String purchaseTm;           // 購入時刻
    private String goodsCd;              // 商品コード
    private String goodsNm;              // 商品名
    private String saleKbn;              // 受取方法区分
    private String saleNm;               // 受取方法
    private BigDecimal purchaseYen;      // 購入金額(税込)
    private BigDecimal paymentUsePoint;  // 有償利用ポイント
    private BigDecimal freeUsePoint;     // 無償利用ポイント
    private int recordVerNo;             // レコードバージョン番号
    private Timestamp insertDttm;        // 登録日時
    private String insertCd;             // 登録者コード
    private Timestamp updateDttm;        // 更新日時
    private String updateCd;             // 更新者コード
    private int returnCode;              // リターンコード

    /**
     * 購入ID取得<br>
     * <br>
     * 購入IDを戻り値で返却する<br>
     * <br>
     * @return purchaseId
     */
    public String getPurchaseId() {
        return purchaseId;
    }

    /**
     * 購入ID設定<br>
     * <br>
     * 購入IDを引数で設定する<br>
     * <br>
     * @param purchaseId
     */
    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
    }

    /**
     * 支払方法区分取得<br>
     * <br>
     * 支払方法区分を戻り値で返却する<br>
     * <br>
     * @return paymentMethodKbn
     */
    public String getPaymentMethodKbn() {
        return paymentMethodKbn;
    }

    /**
     * 支払方法区分設定<br>
     * <br>
     * 支払方法区分を引数で設定する<br>
     * <br>
     * @param paymentMethodKbn
     */
    public void setPaymentMethodKbn(String paymentMethodKbn) {
        this.paymentMethodKbn = paymentMethodKbn;
    }

    /**
     * 受講者ID (購入者ID)取得<br>
     * <br>
     * 受講者ID (購入者ID)を戻り値で返却する<br>
     * <br>
     * @return studentId
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * 受講者ID (購入者ID)設定<br>
     * <br>
     * 受講者ID (購入者ID)を引数で設定する<br>
     * <br>
     * @param studentId
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * 購入日取得<br>
     * <br>
     * 購入日を戻り値で返却する<br>
     * <br>
     * @return purchaseDt
     */
    public String getPurchaseDt() {
        return purchaseDt;
    }

    /**
     * 購入日設定<br>
     * <br>
     * 購入日を引数で設定する<br>
     * <br>
     * @param purchaseDt
     */
    public void setPurchaseDt(String purchaseDt) {
        this.purchaseDt = purchaseDt;
    }

    /**
     * 購入時刻取得<br>
     * <br>
     * 購入時刻を戻り値で返却する<br>
     * <br>
     * @return purchaseTm
     */
    public String getPurchaseTm() {
        return purchaseTm;
    }

    /**
     * 購入時刻設定<br>
     * <br>
     * 購入時刻を引数で設定する<br>
     * <br>
     * @param purchaseTm
     */
    public void setPurchaseTm(String purchaseTm) {
        this.purchaseTm = purchaseTm;
    }

    /**
     * 商品コード取得<br>
     * <br>
     * 商品コードを戻り値で返却する<br>
     * <br>
     * @return goodsCd
     */
    public String getGoodsCd() {
        return goodsCd;
    }

    /**
     * 商品コード設定<br>
     * <br>
     * 商品コードを引数で設定する<br>
     * <br>
     * @param goodsCd
     */
    public void setGoodsCd(String goodsCd) {
        this.goodsCd = goodsCd;
    }

    /**
     * 商品名取得<br>
     * <br>
     * 商品名を戻り値で返却する<br>
     * <br>
     * @return goodsNm
     */
    public String getGoodsNm() {
        return goodsNm;
    }

    /**
     * 商品名設定<br>
     * <br>
     * 商品名を引数で設定する<br>
     * <br>
     * @param goodsNm
     */
    public void setGoodsNm(String goodsNm) {
        this.goodsNm = goodsNm;
    }

    /**
     * 受取方法区分取得<br>
     * <br>
     * 受取方法区分を戻り値で返却する<br>
     * <br>
     * @return saleKbn
     */
    public String getSaleKbn() {
        return saleKbn;
    }

    /**
     * 受取方法区分設定<br>
     * <br>
     * 受取方法区分を引数で設定する<br>
     * <br>
     * @param saleKbn
     */
    public void setSaleKbn(String saleKbn) {
        this.saleKbn = saleKbn;
    }

    /**
     * 受取方法取得<br>
     * <br>
     * 受取方法を戻り値で返却する<br>
     * <br>
     * @return saleNm
     */
    public String getSaleNm() {
        return saleNm;
    }

    /**
     * 受取方法設定<br>
     * <br>
     * 受取方法を引数で設定する<br>
     * <br>
     * @param saleNm
     */
    public void setSaleNm(String saleNm) {
        this.saleNm = saleNm;
    }

    /**
     * 購入金額(税込)取得<br>
     * <br>
     * 購入金額(税込)を戻り値で返却する<br>
     * <br>
     * @return purchaseYen
     */
    public BigDecimal getPurchaseYen() {
        return purchaseYen;
    }

    /**
     * 購入金額(税込)設定<br>
     * <br>
     * 購入金額(税込)を引数で設定する<br>
     * <br>
     * @param purchaseYen
     */
    public void setPurchaseYen(BigDecimal purchaseYen) {
        this.purchaseYen = purchaseYen;
    }

    /**
     * 有償利用ポイント取得<br>
     * <br>
     * 有償利用ポイントを戻り値で返却する<br>
     * <br>
     * @return paymentUsePoint
     */
    public BigDecimal getPaymentUsePoint() {
        return paymentUsePoint;
    }

    /**
     * 有償利用ポイント設定<br>
     * <br>
     * 有償利用ポイントを引数で設定する<br>
     * <br>
     * @param paymentUsePoint
     */
    public void setPaymentUsePoint(BigDecimal paymentUsePoint) {
        this.paymentUsePoint = paymentUsePoint;
    }

    /**
     * 無償利用ポイント取得<br>
     * <br>
     * 無償利用ポイントを戻り値で返却する<br>
     * <br>
     * @return freeUsePoint
     */
    public BigDecimal getFreeUsePoint() {
        return freeUsePoint;
    }

    /**
     * 無償利用ポイント設定<br>
     * <br>
     * 無償利用ポイントを引数で設定する<br>
     * <br>
     * @param freeUsePoint
     */
    public void setFreeUsePoint(BigDecimal freeUsePoint) {
        this.freeUsePoint = freeUsePoint;
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
