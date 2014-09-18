package com.naikara_talk.sessiondata;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.naikara_talk.model.GoodsListModel;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称 :</b>お客様利用画面<br>
 * <b>クラス名称       :</b>商品購入ページ-講師への購入メール連絡付き区分のセッション情報クラス<br>
 * <b>クラス概要       :</b>商品購入ページ-講師への購入メール連絡付き区分用のセッション情報<br>
 * <br>
 * <b>著作権           :</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴         :</b>2014/02/18 TECS 新規作成
 */

public class SessionPurchaseGoodsTContactInfo implements SessionData {

	/** ヘッダの戻るリンク用のSessionKey */
	private static final String HEADER_RETURN_KEY = "SessionPurchaseGoodsTContactInfo";

	/** 講師への商品購入確認通知メール用 */
	List<GoodsListModel> resultList = new ArrayList<GoodsListModel>();


	/**
	 * このセッションデータのキーを返却する
	 */
	public static String getKey() {
		return HEADER_RETURN_KEY;
	}

	/**
	 * @return resultList
	 */
	public List<GoodsListModel> getResultList() {
		return resultList;
	}

	/**
	 * @param resultList セットする resultList
	 */
	public void setResultList(List<GoodsListModel> resultList) {
		this.resultList = resultList;
	}


}