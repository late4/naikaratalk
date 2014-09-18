package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.PurchasePointsModel;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様利用画面<br>
 * <b>クラス名称       :</b>ポイント購入ページ<br>
 * <b>クラス概要       :</b>ポイント購入ページ共通Action<br>
 * <br>
 * <b>著作権           :</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/19 TECS 新規作成
 */
public abstract class PurchasePointsActionSupport extends NaikaraActionSupport {

	private static final long serialVersionUID = 1L;

	// Help画面名
	protected String helpPageId = "HelpPurchasePoints.html";

	/**
	 * サービスの呼び出しの実装。
	 */
	abstract protected String requestService() throws NaiException;

	/** メッセージ */
	protected String message;

	/** ポイント購入一覧 */
	protected PurchasePointsModel model = new PurchasePointsModel();

	/** 一覧から選択された明細データ(jsp) */
	protected String select_rdl;

	/** ポイントコード(jsp:hidden) */
	protected String pointCd;

	/** ユーザＩＤ(jsp:hidden) */
	protected String userId;

	/** PayPal token */
	protected String token;

	/** PayPal payerID */
	protected String payerID;

	/**
	 * @return helpPageId
	 */
	public String getHelpPageId() {
		return helpPageId;
	}

	/**
	 * @param helpPageId
	 *			セットする helpPageId
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
	 * @param message
	 *			セットする message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return model
	 */
	public PurchasePointsModel getModel() {
		return model;
	}

	/**
	 * @param model
	 *			セットする model
	 */
	public void setModel(PurchasePointsModel model) {
		this.model = model;
	}

	/**
	 * @return select_rdl
	 */
	public String getSelect_rdl() {
		return select_rdl;
	}

	/**
	 * @param select_rdl
	 *			セットする select_rdl
	 */
	public void setSelect_rdl(String select_rdl) {
		this.select_rdl = select_rdl;
	}

	/**
	 * @return pointCd
	 */
	public String getPointCd() {
		return pointCd;
	}

	/**
	 * @param pointCd
	 *			セットする pointCd
	 */
	public void setPointCd(String pointCd) {
		this.pointCd = pointCd;
	}

	/**
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *			セットする userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token
	 *			セットする token
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return payerID
	 */
	public String getPayerID() {
		return payerID;
	}

	/**
	 * @param payerID
	 *			セットする payerID
	 */
	public void setPayerID(String payerID) {
		this.payerID = payerID;
	}

}
