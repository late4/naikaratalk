package com.naikara_talk.sessiondata;

import java.util.List;

import com.naikara_talk.model.GoodsListModel;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称 :</b>お客様利用画面<br>
 * <b>クラス名称       :</b>商品購入ページのセッション情報クラス<br>
 * <b>クラス概要       :</b>商品購入ページの戻る用のセッション情報<br>
 * <br>
 * <b>著作権           :</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/09 TECS 新規作成
 *                     :</b>2014/02/18 TECS 商品購入ページからの呼出追加に伴う修正
 */

public class SessionPurchaseGoods implements SessionData {

	/** ヘッダの戻るリンク用のSessionKey */
	private static final String HEADER_RETURN_KEY = "PurchaseGoods";

	/** 戻る判定Onフラグ */
	private boolean returnOnFlg;

	/** コース名：大分類コード(表示用) */
	private String bigClassificationCdD;

	/** コース名：中分類コード(表示用) */
	private String middleClassificationCdD;

	/** コース名：小分類コード(表示用) */
	private String smallClassificationCdD;

	/** コース名(表示用) */
	private String courseJnmD;

	/** 商品名(表示用) */
	private String goodsNmD;

	/** 商品形態(表示用) */
	private String productKbnD;

	/** 受取方法(表示用) */
	private String saleKbnD;

	//2014.02.18 Add Start 商品購入ページからの呼出追加に伴う修正
	/** 講師への商品購入確認通知メール(表示用) */
	private String tMailKbnD;
	/** 講師への商品購入確認通知メール(検索用) */
	private String tMailKbnS;
	//2014.02.18 Add End   商品購入ページからの呼出追加に伴う修正

	/** コース名：大分類コード(検索用) */
	private String bigClassificationCdS;

	/** コース名：中分類コード(検索用) */
	private String middleClassificationCdS;

	/** コース名：小分類コード(検索用) */
	private String smallClassificationCdS;

	/** コース名(検索用) */
	private String courseJnmS;

	/** 商品名(検索用) */
	private String goodsNmS;

	/** 商品形態(検索用) */
	private String productKbnS;

	/** 受取方法(検索用) */
	private String saleKbnS;

	/** 一覧から選択された明細 */
	private String[] selectChk;

	//2014.02.18 Add Start 商品購入ページからの呼出追加に伴う修正
	private List<GoodsListModel> resultList;

	/** 一覧から選択された明細-講師ID */
	private String[] teacherId;

	/** 一覧から選択された明細-講師ニックネーム */
	private String[] teacherNickNm;

	/** 一覧から選択された明細-講師-削除チェックBOX */
	private String[] teacherChk;
	//2014.02.18 Add End 商品購入ページからの呼出追加に伴う修正



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
	 * @return bigClassificationCdD
	 */
	public String getBigClassificationCdD() {
		return bigClassificationCdD;
	}

	/**
	 * @param bigClassificationCdD セットする bigClassificationCdD
	 */
	public void setBigClassificationCdD(String bigClassificationCdD) {
		this.bigClassificationCdD = bigClassificationCdD;
	}

	/**
	 * @return middleClassificationCdD
	 */
	public String getMiddleClassificationCdD() {
		return middleClassificationCdD;
	}

	/**
	 * @param middleClassificationCdD セットする middleClassificationCdD
	 */
	public void setMiddleClassificationCdD(String middleClassificationCdD) {
		this.middleClassificationCdD = middleClassificationCdD;
	}

	/**
	 * @return smallClassificationCdD
	 */
	public String getSmallClassificationCdD() {
		return smallClassificationCdD;
	}

	/**
	 * @param smallClassificationCdD セットする smallClassificationCdD
	 */
	public void setSmallClassificationCdD(String smallClassificationCdD) {
		this.smallClassificationCdD = smallClassificationCdD;
	}

	/**
	 * @return courseJnmD
	 */
	public String getCourseJnmD() {
		return courseJnmD;
	}

	/**
	 * @param courseJnmD セットする courseJnmD
	 */
	public void setCourseJnmD(String courseJnmD) {
		this.courseJnmD = courseJnmD;
	}

	/**
	 * @return goodsNmD
	 */
	public String getGoodsNmD() {
		return goodsNmD;
	}

	/**
	 * @param goodsNmD セットする goodsNmD
	 */
	public void setGoodsNmD(String goodsNmD) {
		this.goodsNmD = goodsNmD;
	}

	/**
	 * @return productKbnD
	 */
	public String getProductKbnD() {
		return productKbnD;
	}

	/**
	 * @param productKbnD セットする productKbnD
	 */
	public void setProductKbnD(String productKbnD) {
		this.productKbnD = productKbnD;
	}

	/**
	 * @return saleKbnD
	 */
	public String getSaleKbnD() {
		return saleKbnD;
	}

	/**
	 * @param saleKbnD セットする saleKbnD
	 */
	public void setSaleKbnD(String saleKbnD) {
		this.saleKbnD = saleKbnD;
	}


	//2014.02.18 Add Start 商品購入ページからの呼出追加に伴う修正
	/**
	 * @return tMailKbnD
	 */
	public String getTMailKbnD() {
		return tMailKbnD;
	}

	/**
	 * @param tMailKbnD セットする tMailKbnD
	 */
	public void setTMailKbnD(String tMailKbnD) {
		this.tMailKbnD = tMailKbnD;
	}

	/**
	 * @return tMailKbnS
	 */
	public String getTMailKbnS() {
		return tMailKbnS;
	}

	/**
	 * @param tMailKbnS セットする tMailKbnS
	 */
	public void setTMailKbnS(String tMailKbnS) {
		this.tMailKbnS = tMailKbnS;
	}
	//2014.02.18 Add Start 商品購入ページからの呼出追加に伴う修正

	/**
	 * @return bigClassificationCdS
	 */
	public String getBigClassificationCdS() {
		return bigClassificationCdS;
	}

	/**
	 * @param bigClassificationCdS セットする bigClassificationCdS
	 */
	public void setBigClassificationCdS(String bigClassificationCdS) {
		this.bigClassificationCdS = bigClassificationCdS;
	}

	/**
	 * @return middleClassificationCdS
	 */
	public String getMiddleClassificationCdS() {
		return middleClassificationCdS;
	}

	/**
	 * @param middleClassificationCdS セットする middleClassificationCdS
	 */
	public void setMiddleClassificationCdS(String middleClassificationCdS) {
		this.middleClassificationCdS = middleClassificationCdS;
	}

	/**
	 * @return smallClassificationCdS
	 */
	public String getSmallClassificationCdS() {
		return smallClassificationCdS;
	}

	/**
	 * @param smallClassificationCdS セットする smallClassificationCdS
	 */
	public void setSmallClassificationCdS(String smallClassificationCdS) {
		this.smallClassificationCdS = smallClassificationCdS;
	}

	/**
	 * @return courseJnmS
	 */
	public String getCourseJnmS() {
		return courseJnmS;
	}

	/**
	 * @param courseJnmSearch セットする courseJnmSearch
	 */
	public void setCourseJnmS(String courseJnmS) {
		this.courseJnmS = courseJnmS;
	}

	/**
	 * @return goodsNmS
	 */
	public String getGoodsNmS() {
		return goodsNmS;
	}

	/**
	 * @param goodsNmS セットする goodsNmS
	 */
	public void setGoodsNmS(String goodsNmS) {
		this.goodsNmS = goodsNmS;
	}

	/**
	 * @return productKbnS
	 */
	public String getProductKbnS() {
		return productKbnS;
	}

	/**
	 * @param productKbnS セットする productKbnS
	 */
	public void setProductKbnS(String productKbnS) {
		this.productKbnS = productKbnS;
	}

	/**
	 * @return saleKbnS
	 */
	public String getSaleKbnS() {
		return saleKbnS;
	}

	/**
	 * @param saleKbnS セットする saleKbnS
	 */
	public void setSaleKbnS(String saleKbnS) {
		this.saleKbnS = saleKbnS;
	}

	/**
	 * @return selectChk
	 */
	public String[] getSelectChk() {
		return selectChk;
	}

	/**
	 * @param selectChk セットする selectChk
	 */
	public void setSelect_chk(String[] selectChk) {
		this.selectChk = selectChk;
	}

	//2014.02.18 Add Start 商品購入ページからの呼出追加に伴う修正
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

	/**
	 * @return teacherId
	 */
	public String[] getTeacherId() {
		return teacherId;
	}

	/**
	 * @param teacherId セットする teacherId
	 */
	public void setTeacherId(String[] teacherId) {
		this.teacherId = teacherId;
	}

	/**
	 * @return teacherNickNm
	 */
	public String[] getTeacherNickNm() {
		return teacherNickNm;
	}

	/**
	 * @param teacherNickNm セットする teacherNickNm
	 */
	public void setTeacherNickNm(String[] teacherNickNm) {
		this.teacherNickNm = teacherNickNm;
	}

	/**
	 * @return teacherChk
	 */
	public String[] getTeacherChk() {
		return teacherChk;
	}

	/**
	 * @param teacherChk セットする teacherChk
	 */
	public void setTeacherChk(String[] teacherChk) {
		this.teacherChk = teacherChk;
	}
	//2014.02.18 Add End   商品購入ページからの呼出追加に伴う修正


}