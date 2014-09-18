package com.naikara_talk.dto;

import java.math.BigDecimal;


/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様利用画面<br>
 * <b>クラス名称       :</b>商品購入ページ確認（ポイント購入）<br>
 * <b>クラス概要       :</b>商品購入ページ確認（ポイント購入）処理DTO<br>
 * <br>
 * <b>著作権           :</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/15 TECS 新規作成
 */
public class PurchaseGoodsPointPaymentDto extends AbstractDto{

	private String studentId;         // 受講者ID
	private String[] goodsCd;         // 購入商品コード
	private BigDecimal[] usePoint;    // 購入商品利用ポイント

	/**
	 * 受講者ＩＤ取得<br>
	 * <br>
	 * 受講者ＩＤを戻り値で返却する<br>
	 * <br>
	 * @return studentId
	 */
	public String getStudentId() {
		return studentId;
	}

	/**
	 * 受講者ＩＤ設定<br>
	 * <br>
	 * 受講者ＩＤを引数で設定する<br>
	 * <br>
	 * @param studentId
	 */
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	/**
	 * 購入商品コード取得<br>
	 * <br>
	 * 購入商品コードを戻り値で返却する<br>
	 * <br>
	 * @return goodsCd
	 */
	public String[] getGoodsCd() {
		return goodsCd;
	}

	/**
	 * 購入商品コード設定<br>
	 * <br>
	 * 購入商品コードを引数で設定する<br>
	 * <br>
	 * @param goodsCd
	 */
	public void setGoodsCd(String[] goodsCd) {
		this.goodsCd = goodsCd;
	}

	/**
	 * 購入商品利用ポイント取得<br>
	 * <br>
	 * 購入商品利用ポイントを戻り値で返却する<br>
	 * <br>
	 * @return usePoint
	 */
	public BigDecimal[] getUsePoint() {
		return usePoint;
	}

	/**
	 * 購入商品利用ポイント設定<br>
	 * <br>
	 * 購入商品利用ポイントを引数で設定する<br>
	 * <br>
	 * @param usePoint
	 */
	public void setUsePoint(BigDecimal[] usePoint) {
		this.usePoint = usePoint;
	}

}