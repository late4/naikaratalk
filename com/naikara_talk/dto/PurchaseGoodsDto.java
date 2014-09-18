package com.naikara_talk.dto;

import java.math.BigDecimal;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様利用画面<br>
 * <b>クラス名称       :</b>商品購入ページ一覧<br>
 * <b>クラス概要       :</b>商品購入ページ一覧処理DTO<br>
 * <br>
 * <b>著作権           :</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/12 TECS 新規作成
 *                     :</b>2014/02/18 TECS 商品購入ページからの呼出追加に伴う修正
 */
public class PurchaseGoodsDto extends AbstractDto{

	private String goodsCd;                 // 商品コード
	private String goodsNm;                 // 商品名
	private String explanation;             // 詳細説明
	private String saleKbn;                 // 受取方法区分
	private String productKbn;              // 商品形態区分
	private BigDecimal usePoint;            // 利用ポイント
	private String purchaseDescription;     // 購入先説明
	private String purchaseUrl;             // 購入先

	private String tContactKbn;             // 講師への購入メール連絡付き区分  2014.02.18 商品購入ページからの呼出追加に伴う修正
	private String tMailKbn;                // 講師への購入メール連絡付き区分  2014.02.18 商品購入ページからの呼出追加に伴う修正

	private String bigClassificationCd;     // 大分類コード
	private String middleClassificationCd;  // 中分類コード
	private String smallClassificationCd;   // 小分類コード
	private String courseCd;                // コースコード
	private String courseJnm;               // コース名
	private int returnCode;                 // リターンコード


	 /**
	 * 商品コード取得<br>
	 * <br>
	 * 商品コードを戻り値で返却する<br>
	 * <br>
	 *
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
	 *
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
	 *
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
	 *
	 * @param goodsNm
	 */
	public void setGoodsNm(String goodsNm) {
		this.goodsNm = goodsNm;
	}

	/**
	 * 詳細説明取得<br>
	 * <br>
	 * 詳細説明を戻り値で返却する<br>
	 * <br>
	 *
	 * @return explanation
	 */
	public String getExplanation() {
		return explanation;
	}

	/**
	 * 詳細説明設定<br>
	 * <br>
	 * 詳細説明を引数で設定する<br>
	 * <br>
	 *
	 * @param explanation
	 */
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	/**
	 * 受取方法区分取得<br>
	 * <br>
	 * 受取方法区分を戻り値で返却する<br>
	 * <br>
	 *
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
	 *
	 * @param saleKbn
	 */
	public void setSaleKbn(String saleKbn) {
		this.saleKbn = saleKbn;
	}

	// 2014/02/18 Add Start 商品購入ページからの呼出追加に伴う修正
	/**
	 * 講師への商品購入確認通知メール取得<br>
	 * <br>
	 * 講師への商品購入確認通知メールを戻り値で返却する<br>
	 * <br>
	 *
	 * @return tMailKbn
	 */
	public String getTMailKbn() {
		return tMailKbn;
	}

	/**
	 * 講師への商品購入確認通知メール設定<br>
	 * <br>
	 * 講師への商品購入確認通知メールを引数で設定する<br>
	 * <br>
	 *
	 * @param tMailKbn
	 */
	public void setTMailKbn(String tMailKbn) {
		this.tMailKbn = tMailKbn;
	}
	// 2014/02/18 Add End   商品購入ページからの呼出追加に伴う修正

	/**
	 * 商品形態区分取得<br>
	 * <br>
	 * 商品形態区分を戻り値で返却する<br>
	 * <br>
	 *
	 * @return productKbn
	 */
	public String getProductKbn() {
		return productKbn;
	}

	/**
	 * 商品形態区分設定<br>
	 * <br>
	 * 商品形態区分を引数で設定する<br>
	 * <br>
	 *
	 * @param productKbn
	 */
	public void setProductKbn(String productKbn) {
		this.productKbn = productKbn;
	}

	/**
	 * 利用ポイント取得<br>
	 * <br>
	 * 利用ポイントを戻り値で返却する<br>
	 * <br>
	 *
	 * @return usePoint
	 */
	public BigDecimal getUsePoint() {
		return usePoint;
	}

	/**
	 * 利用ポイント設定<br>
	 * <br>
	 * 利用ポイントを引数で設定する<br>
	 * <br>
	 *
	 * @param usePoint
	 */
	public void setUsePoint(BigDecimal usePoint) {
		this.usePoint = usePoint;
	}

	/**
	 * 購入先説明取得<br>
	 * <br>
	 * 購入先説明を戻り値で返却する<br>
	 * <br>
	 *
	 * @return purchaseDescription
	 */
	public String getPurchaseDescription() {
		return purchaseDescription;
	}

	/**
	 * 購入先説明設定<br>
	 * <br>
	 * 購入先説明を引数で設定する<br>
	 * <br>
	 *
	 * @param purchaseDescription
	 */
	public void setPurchaseDescription(String purchaseDescription) {
		this.purchaseDescription = purchaseDescription;
	}

	/**
	 * 購入先URL取得<br>
	 * <br>
	 * 購入先URLを戻り値で返却する<br>
	 * <br>
	 *
	 * @return purchaseUrl
	 */
	public String getPurchaseUrl() {
		return purchaseUrl;
	}

	/**
	 * 購入先URL設定<br>
	 * <br>
	 * 購入先URLを引数で設定する<br>
	 * <br>
	 *
	 * @param purchaseUrl
	 */
	public void setPurchaseUrl(String purchaseUrl) {
		this.purchaseUrl = purchaseUrl;
	}


	// 2014.02.18 Add Start 商品購入ページからの呼出追加に伴う修正
	/**
	 * 講師への購入メール連絡付き区分取得<br>
	 * <br>
	 * 講師への購入メール連絡付き区分を戻り値で返却する<br>
	 * <br>
	 *
	 * @return tContactKbn
	 */
	public String getTContactKbn() {
		return tContactKbn;
	}

	/**
	 * 講師への購入メール連絡付き区分設定<br>
	 * <br>
	 * 講師への購入メール連絡付き区分を引数で設定する<br>
	 * <br>
	 *
	 * @param tContactKbn
	 */
	public void setTContactKbn(String tContactKbn) {
		this.tContactKbn = tContactKbn;
	}
	// 2014.02.18 Add End   商品購入ページからの呼出追加に伴う修正


	/**
	 * 大分類コード取得<br>
	 * <br>
	 * 大分類コードを戻り値で返却する<br>
	 * <br>
	 * @return bigClassificationCd
	 */
	public String getBigClassificationCd(){
		return bigClassificationCd;
	}

	/**
	 * 大分類コード設定<br>
	 * <br>
	 * 大分類コードを引数で設定する<br>
	 * <br>
	 * @param bigClassificationCd
	 */
	public void setBigClassificationCd(String bigClassificationCd){
		this.bigClassificationCd = bigClassificationCd;
	}

	/**
	 * 中分類コード取得<br>
	 * <br>
	 * 中分類コードを戻り値で返却する<br>
	 * <br>
	 * @return middleClassificationCd
	 */
	public String getMiddleClassificationCd(){
		return middleClassificationCd;
	}

	/**
	 * 中分類コード設定<br>
	 * <br>
	 * 中分類コードを引数で設定する<br>
	 * <br>
	 * @param middleClassificationCd
	 */
	public void setMiddleClassificationCd(String middleClassificationCd){
		this.middleClassificationCd = middleClassificationCd;
	}

	/**
	 * 小分類コード取得<br>
	 * <br>
	 * 小分類コードを戻り値で返却する<br>
	 * <br>
	 * @return smallClassificationCd
	 */
	public String getSmallClassificationCd(){
		return smallClassificationCd;
	}

	/**
	 * 小分類コード設定<br>
	 * <br>
	 * 小分類コードを引数で設定する<br>
	 * <br>
	 * @param smallClassificationCd
	 */
	public void setSmallClassificationCd(String smallClassificationCd){
		this.smallClassificationCd = smallClassificationCd;
	}


	 /**
	 * コースコード取得<br>
	 * <br>
	 * コースコードを戻り値で返却する<br>
	 * <br>
	 *
	 * @return courseCd
	 */
	public String getCourseCd() {
		return courseCd;
	}

	/**
	 * コースコード設定<br>
	 * <br>
	 * コースコードを引数で設定する<br>
	 * <br>
	 *
	 * @param courseCd
	 */
	public void setCourseCd(String courseCd) {
		this.courseCd = courseCd;
	}


	/**
	 * コース名取得<br>
	 * <br>
	 * コース名を戻り値で返却する<br>
	 * <br>
	 * @return courseJnm
	 */
	public String getCourseJnm(){
		return courseJnm;
	}

	/**
	 * コース名設定<br>
	 * <br>
	 * コース名を引数で設定する<br>
	 * <br>
	 * @param courseJnm
	 */
	public void setCourseJnm(String courseJnm){
		this.courseJnm = courseJnm;
	}

	/**
	 * リターンコード取得<br>
	 * <br>
	 * リターンコードを戻り値で返却する<br>
	 * <br>
	 * @return returnCode
	 */
	public int getReturnCode(){
		return returnCode;
	}

	/**
	 * リターンコード設定<br>
	 * <br>
	 * リターンコードを引数で設定する<br>
	 * <br>
	 * @param returnCode
	 */
	public void setReturnCode(int returnCode){
		this.returnCode = returnCode;
	}

}