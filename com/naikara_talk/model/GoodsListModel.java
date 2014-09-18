package com.naikara_talk.model;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.mapper.ActionMapping;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様利用画面<br>
 * <b>クラス名称       :</b>ポイント購入ページ一覧<br>
 * <b>クラス概要       :</b>ポイント購入ページ一覧Model<br>
 * <br>
 * <b>著作権           :</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/19 TECS 新規作成
 *                     :</b>2014/02/18 TECS 商品購入ページからの呼出追加に伴う修正
 */

public class GoodsListModel implements Model {

    private static final long serialVersionUID = 1L;

	private boolean checkboxFlg;              // 選択の活性／非活性制御
	private String goodsCdcourseCd;           // 商品コード+コースコード
	private String goodsCd;                   // 商品コード
	private String goodsNm;                   // 商品名
	private String explanation;               // 詳細説明
	private String productKbn;                // 商品形態
	private String strUsePoint;               // 利用ポイント
	private BigDecimal bdUsePoint;            // 利用ポイント
	private String saleKbn;                   // 受取方法

	// 2014.02.18 Add Start 商品購入ページからの呼出追加に伴う修正
	private String tMailKbn;                  // 講師への商品購入確認通知メール
	// 2014.02.18 Add End   商品購入ページからの呼出追加に伴う修正

	private String purchaseDescription;       // 備考
	private String purchaseUrl;               // URL
	private String bigClassificationCd;       // 大分類コード
	private String middleClassificationCd;    // 中分類コード
	private String smallClassificationCd;     // 小分類コード
	private String courseJnm;                 // コース名

	// 2014.02.18 Add Start 商品購入ページからの呼出追加に伴う修正
	private String tContactKbn;               // 講師への購入メール連絡付区分
	private String teacherId;                 // 講師ID
	private String teacherNickNm;             // 講師ニックネーム
	private boolean teacherChk;               // 選択した講師の削除チェック区分
	private String teacherInfo;               // 講師ID + 講師ニックネーム

	private String hdnTeacherId;              // (隠し)講師ID
	// 2014.02.18 Add End   商品購入ページからの呼出追加に伴う修正


	/**
	 * @return checkboxFlg
	 */
	public boolean isCheckboxFlg() {
		return checkboxFlg;
	}

	/**
	 * @param checkboxFlg セットする checkboxFlg
	 */
	public void setCheckboxFlg(boolean checkboxFlg) {
		this.checkboxFlg = checkboxFlg;
	}

	/**
	 * @return goodsCdcourseCd
	 */
	public String getGoodsCdcourseCd() {
		return goodsCdcourseCd;
	}

	/**
	 * @param goodsCdcourseCd セットする goodsCdcourseCd
	 */
	public void setGoodsCdcourseCd(String goodsCdcourseCd) {
		this.goodsCdcourseCd = goodsCdcourseCd;
	}


	/**
	 * @return goodsCd
	 */
	public String getGoodsCd() {
		return goodsCd;
	}

	/**
	 * @param goodsCd セットする goodsCd
	 */
	public void setGoodsCd(String goodsCd) {
		this.goodsCd = goodsCd;
	}

	/**
	 * @return goodsNm
	 */
	public String getGoodsNm() {
		return goodsNm;
	}

	/**
	 * @param goodsNm セットする goodsNm
	 */
	public void setGoodsNm(String goodsNm) {
		this.goodsNm = goodsNm;
	}

	/**
	 * @return explanation
	 */
	public String getExplanation() {
		return explanation;
	}

	/**
	 * @param explanation セットする explanation
	 */
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	/**
	 * @return productKbn
	 */
	public String getProductKbn() {
		return productKbn;
	}

	/**
	 * @param productKbn セットする productKbn
	 */
	public void setProductKbn(String productKbn) {
		this.productKbn = productKbn;
	}

	/**
	 * @return strUsePoint
	 */
	public String getStrUsePoint() {
		return strUsePoint;
	}

	/**
	 * @param strUsePoint セットする strUsePoint
	 */
	public void setStrUsePoint(String strUsePoint) {
		this.strUsePoint = strUsePoint;
	}

	/**
	 * @return bdUsePoint
	 */
	public BigDecimal getBdUsePoint() {
		return bdUsePoint;
	}

	/**
	 * @param bdUsePoint セットする bdUsePoint
	 */
	public void setBdUsePoint(BigDecimal bdUsePoint) {
		this.bdUsePoint = bdUsePoint;
	}

	/**
	 * @return saleKbn
	 */
	public String getSaleKbn() {
		return saleKbn;
	}

	/**
	 * @param saleKbn セットする saleKbn
	 */
	public void setSaleKbn(String saleKbn) {
		this.saleKbn = saleKbn;
	}

	// 2014.02.18 Add Start 商品購入ページからの呼出追加に伴う修正
	/**
	 * @return tMailKbn 講師への商品購入確認通知メール
	 */
	public String getTMailKbn() {
		return tMailKbn;
	}

	/**
	 * @param tMailKbn セットする tMailKbn
	 */
	public void setTMailKbn(String tMailKbn) {
		this.tMailKbn = tMailKbn;
	}
	// 2014.02.18 Add End   商品購入ページからの呼出追加に伴う修正


	/**
	 * @return purchaseDescription
	 */
	public String getPurchaseDescription() {
		return purchaseDescription;
	}

	/**
	 * @param purchaseDescription セットする purchaseDescription
	 */
	public void setPurchaseDescription(String purchaseDescription) {
		this.purchaseDescription = purchaseDescription;
	}

	/**
	 * @return purchaseUrl
	 */
	public String getPurchaseUrl() {
		return purchaseUrl;
	}

	/**
	 * @param purchaseUrl セットする purchaseUrl
	 */
	public void setPurchaseUrl(String purchaseUrl) {
		this.purchaseUrl = purchaseUrl;
	}

	/**
	 * @return bigClassificationCd
	 */
	public String getBigClassificationCd() {
		return bigClassificationCd;
	}

	/**
	 * @param bigClassificationCd セットする bigClassificationCd
	 */
	public void setBigClassificationCd(String bigClassificationCd) {
		this.bigClassificationCd = bigClassificationCd;
	}

	/**
	 * @return middleClassificationCd
	 */
	public String getMiddleClassificationCd() {
		return middleClassificationCd;
	}

	/**
	 * @param middleClassificationCd セットする middleClassificationCd
	 */
	public void setMiddleClassificationCd(String middleClassificationCd) {
		this.middleClassificationCd = middleClassificationCd;
	}

	/**
	 * @return smallClassificationCd
	 */
	public String getSmallClassificationCd() {
		return smallClassificationCd;
	}

	/**
	 * @param smallClassificationCd セットする smallClassificationCd
	 */
	public void setSmallClassificationCd(String smallClassificationCd) {
		this.smallClassificationCd = smallClassificationCd;
	}

	/**
	 * @return courseJnm
	 */
	public String getCourseJnm() {
		return courseJnm;
	}

	/**
	 * @param courseJnm セットする courseJnm
	 */
	public void setCourseJnm(String courseJnm) {
		this.courseJnm = courseJnm;
	}


	// 2014.02.18 Add Start 商品購入ページからの呼出追加に伴う修正
	/**
	 * @return tContactKbn  講師への購入メール連絡付区分
	 */
	public String getTContactKbn() {
		return tContactKbn;
	}

	/**
	 * @param tContactKbn セットする  講師への購入メール連絡付区分
	 */
	public void setTContactKbn(String tContactKbn) {
		this.tContactKbn = tContactKbn;
	}


	/**
	 * @return hdnTeacherId (隠し)講師ID
	 */
	public String getHdnTeacherId() {
		return hdnTeacherId;
	}

	/**
	 * @param hdnTeacherId セットする (隠し)講師ID
	 */
	public void setHdnTeacherId(String hdnTeacherId) {
		this.hdnTeacherId = hdnTeacherId;
	}

	/**
	 * @return teacherId 講師ID
	 */
	public String getTeacherId() {
		return teacherId;
	}

	/**
	 * @param teacherId セットする 講師ID
	 */
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	/**
	 * @return teacherNickNm 講師ニックネーム
	 */
	public String getTeacherNickNm() {
		return teacherNickNm;
	}

	/**
	 * @param teacherNickNm セットする 講師ニックネーム
	 */
	public void setTeacherNickNm(String teacherNickNm) {
		this.teacherNickNm = teacherNickNm;
	}


	/**
	 * @return teacherInfo 講師ID + 講師ニックネーム(商品購入の確認画面/PayPal支払確認画面 表示用)
	 */
	public String getTeacherInfo() {
		return teacherInfo;
	}

	/**
	 * @param teacherInfo セットする 講師ID + 講師ニックネーム(商品購入の確認画面/PayPal支払確認画面 表示用)
	 */
	public void setTeacherInfo(String teacherInfo) {
		this.teacherInfo = teacherInfo;
	}


	/**
	 * 削除リセット（プロパティを初期化するためのメソッド「reset」）<br>
	 * <br>
	 * チェックBOXリセット<br>
	 * <br>
	 * @return teacherChk
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request){
		System.out.print("test getTeacherId=" + getTeacherId());
		this.teacherChk=false;
	}


	/**
	 * 削除取得<br>
	 * <br>
	 * 選択した講師の削除チェック区分を戻り値で返却する<br>
	 * <br>
	 * @return teacherChk
	 */
	public boolean getTeacherChk() {
		return teacherChk;
	}

	/**
	 * 削除設定<br>
	 * <br>
	 * 選択した講師の削除チェック区分を引数で設定する<br>
	 * <br>
	 * @param teacherChk
	 */
	public void setTeacherChk(boolean teacherChk) {
		this.teacherChk = teacherChk;
	}
	// 2014.02.18 Add End   商品購入ページからの呼出追加に伴う修正


}
