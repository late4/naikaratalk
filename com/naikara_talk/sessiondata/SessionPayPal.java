package com.naikara_talk.sessiondata;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称 :</b>ペイパル<br>
 * <b>クラス名称       :</b>ペイパルのセッション情報クラス<br>
 * <b>クラス概要       :</b>ペイパルのセッション情報<br>
 * <br>
 * <b>著作権           :</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/16 TECS 新規作成
 */

public class SessionPayPal implements SessionData {

    /** SessionKey */
    private static final String SESSION_KEY = "PayPal";

    /** 商品説明 */
    private String goodsDescription;

	/** PayPal TOKEN */
    private String paypalToken;

	/** PayPal PayerID */
    private String payerId;

	/** PayPal ProfileID or UsePoint*/
    private String profileId;

    /**
     * このセッションデータのキーを返却する
     */
    public static String getKey() {
        return SESSION_KEY;
    }

    /**
     * @return goodsDescription
     */
    public String getGoodsDescription() {
        return goodsDescription;
    }

    /**
     * @param goodsDescription セットする goodsDescription
     */
    public void setGoodsDescription(String goodsDescription) {
        this.goodsDescription = goodsDescription;
    }

    /**
     * @return paypalToken
     */
    public String getPaypalToken() {
        return paypalToken;
    }

    /**
     * @param paypalToken セットする paypalToken
     */
    public void setPaypalToken(String paypalToken) {
        this.paypalToken = paypalToken;
    }

    /**
     * @return payerId
     */
    public String getPayerId() {
        return payerId;
    }

    /**
     * @param payerId セットする payerId
     */
    public void setPayerId(String payerId) {
        this.payerId = payerId;
    }

    /**
     * @return profileId
     */
    public String getProfileId() {
        return profileId;
    }

    /**
     * @param profileId セットする profileId
     */
    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

}
