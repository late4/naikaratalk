package com.naikara_talk.action;

import java.math.BigDecimal;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.PurchaseGoodsModel;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様利用画面<br>
 * <b>クラス名称       :</b>商品購入ページ（確認、支払）<br>
 * <b>クラス概要       :</b>商品購入ページ（確認、支払）ActionSupport<br>
 * <br>
 * <b>著作権           :</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/13 TECS 新規作成
 */
public abstract class PurchaseGoodsActionSupport extends NaikaraActionSupport {

	private static final long serialVersionUID = 1L;

	// Help画面名
	protected String helpPageId = "HelpPurchaseGoods.html";


	/**
	 * サービスの呼び出しの実装。
	 */
	abstract protected String requestService() throws NaiException;

	/** メッセージ */
	protected String message;

	/** 商品コード */
	protected String[] goodsCd;

	/** 商品名 */
	protected String[] goodsNm;

	/** 利用ポイント */
	protected BigDecimal[] bdUsePoint;

	/** ポイント購入一覧 */
	protected PurchaseGoodsModel model = new PurchaseGoodsModel();

    /** ポイント残高 */
	protected String pointBalance;

    /** 利用ポイント */
	protected String usePointTotal;

    /** ポイント残高 */
	protected BigDecimal pointBalanceB;

    /** 利用ポイント */
	protected BigDecimal usePointTotalB;

    /** PayPal token */
	protected String token;

    /** PayPal PayerID */
	protected String PayerID;


	/**
	* @return helpPageId
	*/
	public String getHelpPageId() {
		return helpPageId;
	}

	/**
	* @param helpPageId セットする helpPageId
	*/
	public void setHelpPageId(String helpPageId) {
		this.helpPageId = helpPageId;
	}

	/**
	* @return message
	*/
	public String getMessage() {
		return message;
	}

	/**
	* @param message セットする message
	*/
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return model
	 */
	public PurchaseGoodsModel getModel() {
		return model;
	}

	/**
	 * @param model セットする model
	 */
	public void setModel(PurchaseGoodsModel model) {
		this.model = model;
	}

	/**
	 * @return goodsCd
	 */
	public String[] getGoodsCd() {
		return goodsCd;
	}

	/**
	 * @param goodsCd セットする goodsCd
	 */
	public void setGoodsCd(String[] goodsCd) {
		this.goodsCd = goodsCd;
	}

	/**
	 * @return goodsNm
	 */
	public String[] getGoodsNm() {
		return goodsNm;
	}

	/**
	 * @param goodsNm セットする goodsNm
	 */
	public void setGoodsNm(String[] goodsNm) {
		this.goodsNm = goodsNm;
	}

	/**
	 * @return bdUsePoint
	 */
	public BigDecimal[] getBdUsePoint() {
		return bdUsePoint;
	}

	/**
	 * @param bdUsePoint セットする bdUsePoint
	 */
	public void setBdUsePoint(BigDecimal[] bdUsePoint) {
		this.bdUsePoint = bdUsePoint;
	}

	/**
	 * @return pointBalance
	 */
	public String getPointBalance() {
		return pointBalance;
	}

	/**
	 * @param pointBalance セットする pointBalance
	 */
	public void setPointBalance(String pointBalance) {
		this.pointBalance = pointBalance;
	}

	/**
	 * @return usePointTotal
	 */
	public String getUsePointTotal() {
		return usePointTotal;
	}

	/**
	 * @param usePointTotal セットする usePointTotal
	 */
	public void setUsePointTotal(String usePointTotal) {
		this.usePointTotal = usePointTotal;
	}

	/**
	 * @return pointBalanceB
	 */
	public BigDecimal getPointBalanceB() {
		return pointBalanceB;
	}

	/**
	 * @param pointBalanceB セットする pointBalanceB
	 */
	public void setPointBalanceB(BigDecimal pointBalanceB) {
		this.pointBalanceB = pointBalanceB;
	}

	/**
	 * @return usePointTotalB
	 */
	public BigDecimal getUsePointTotalB() {
		return usePointTotalB;
	}

	/**
	 * @param usePointTotalB セットする usePointTotalB
	 */
	public void setUsePointTotalB(BigDecimal usePointTotalB) {
		this.usePointTotalB = usePointTotalB;
	}

	/**
	 * @return token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token セットする token
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return PayerID
	 */
	public String getPayerID() {
		return PayerID;
	}

	/**
	 * @param PayerID セットする PayerID
	 */
	public void setPayerID(String PayerID) {
		this.PayerID = PayerID;
	}

}