package com.naikara_talk.sessiondata;

import java.math.BigDecimal;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称 :</b>お客様利用画面<br>
 * <b>クラス名称       :</b>ポイント購入ページのセッション情報クラス<br>
 * <b>クラス概要       :</b>ポイント購入ページの戻る用のセッション情報<br>
 * <br>
 * <b>著作権           :</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/22 TECS 新規作成
 */

public class SessionPurchasePoints implements SessionData {

    /** ヘッダの戻るリンク用のSessionKey */
    private static final String HEADER_RETURN_KEY = "PurchasePoints";

    /** 戻る判定Onフラグ */
    private boolean returnOnFlg;

    /** 選択ポイントコード */
    private String selectPointCd;

    /** 金額 */
    private BigDecimal moneyYen;

    /** 通常月謝区分 */
    private String feeKbn;

    /** 有償ポイント */
    private BigDecimal paymentPoint;

    /** 有償ポイント期限 */
    private int paymentPointTim;

	/** 無償ポイント */
    private BigDecimal freePoint;

    /** 無償ポイント期限 */
    private int freePointTim;

    /** 商品説明 */
//    private String goodsDescription;

	/** PayPal TOKEN */
//    private String paypalToken;

	/** PayPal PayerID */
//    private String payerId;

	/** PayPal ProfileID */
//    private String profileId;

    /**
     * このセッションデータのキーを返却する
     */
    public static String getKey() {
        return HEADER_RETURN_KEY;
    }

    /**
     * @return processKbn
     */
    public boolean getReturnOnFlg() {
        return returnOnFlg;
    }

    /**
     * @param returnOnFlg セットする returnOnFlg
     */
    public void setReturnOnFlg(boolean returnOnFlg) {
        this.returnOnFlg = returnOnFlg;
    }

    /**
     * @return selectPointCd
     */
    public String getSelectPointCd() {
        return selectPointCd;
    }

    /**
     * @param selectPointCd セットする selectPointCd
     */
    public void setSelectPointCd(String selectPointCd) {
        this.selectPointCd = selectPointCd;
    }

    /**
     * @return moneyYen
     */
    public BigDecimal getMoneyYen() {
        return moneyYen;
    }

    /**
     * @param moneyYen セットする moneyYen
     */
    public void setMoneyYen(BigDecimal moneyYen) {
        this.moneyYen = moneyYen;
    }

    /**
     * @return feeKbn
     */
    public String getFeeKbn() {
        return feeKbn;
    }

    /**
     * @param feeKbn セットする feeKbn
     */
    public void setFeeKbn(String feeKbn) {
        this.feeKbn = feeKbn;
    }

    /**
     * @return paymentPoint
     */
    public BigDecimal getPaymentPoint() {
        return paymentPoint;
    }

    /**
     * @param paymentPoint セットする paymentPoint
     */
    public void setPaymentPoint(BigDecimal paymentPoint) {
        this.paymentPoint = paymentPoint;
    }

    /**
     * @return paymentPointTim
     */
    public int getPaymentPointTim() {
        return paymentPointTim;
    }

    /**
     * @param paymentPointTim セットする paymentPointTim
     */
    public void setPaymentPointTim(int paymentPointTim) {
        this.paymentPointTim = paymentPointTim;
    }

    /**
     * @return freePoint
     */
    public BigDecimal getFreePoint() {
        return freePoint;
    }

    /**
     * @param freePoint セットする freePoint
     */
    public void setFreePoint(BigDecimal freePoint) {
        this.freePoint = freePoint;
    }

    /**
     * @return freePointTim
     */
    public int getFreePointTim() {
        return freePointTim;
    }

    /**
     * @param freePointTim セットする freePointTim
     */
    public void setFreePointTim(int freePointTim) {
        this.freePointTim = freePointTim;
    }

    /**
     * @return goodsDescription
     */
//    public String getGoodsDescription() {
//        return goodsDescription;
//    }

    /**
     * @param goodsDescription セットする goodsDescription
     */
//    public void setGoodsDescription(String goodsDescription) {
//        this.goodsDescription = goodsDescription;
//    }

    /**
     * @return paypalToken
     */
//    public String getPaypalToken() {
//        return paypalToken;
//    }

    /**
     * @param paypalToken セットする paypalToken
     */
//    public void setPaypalToken(String paypalToken) {
//        this.paypalToken = paypalToken;
//    }

    /**
     * @return payerId
     */
//    public String getPayerId() {
//        return payerId;
//    }

    /**
     * @param payerId セットする payerId
     */
//    public void setPayerId(String payerId) {
//        this.payerId = payerId;
//    }

    /**
     * @return profileId
     */
//    public String getProfileId() {
//        return profileId;
//    }

    /**
     * @param profileId セットする profileId
     */
//    public void setProfileId(String profileId) {
//        this.profileId = profileId;
//    }

}
