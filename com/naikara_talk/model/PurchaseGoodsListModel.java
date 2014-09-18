package com.naikara_talk.model;

import java.util.List;

import com.naikara_talk.model.GoodsListModel;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称 :</b>お客様利用画面<br>
 * <b>クラス名称       :</b>ポイント購入ページ（一覧）<br>
 * <b>クラス概要       :</b>ポイント購入ページ（一覧）Model<br>
 * <br>
 * <b>著作権           :</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴         :</b>2013/06/18 TECS 新規作成。
 *                     :</b>2014/02/18 TECS 商品購入ページからの呼出追加に伴う修正
 */
public class PurchaseGoodsListModel implements Model {

    private static final long serialVersionUID = 1L;

    /* チェック：問題なし(正常) */
    public static final int CHECK_OK = 0;

    /* データ件数ZERO件 */
    public static final int ERR_ZERO_DATA = 1;

    /* データ件数上限(100)以上の件数 */
    public static final int ERR_MAXOVER_DATA = 2;

    /* 選択項目なし */
    public static final int ERR_NOT_SELECT = 3;


    /** コース名：大分類コード(選択条件) */
    private String bigClassificationCdS;

    /** コース名：中分類コード(選択条件) */
    private String middleClassificationCdS;

    /** コース名：小分類コード(選択条件) */
    private String smallClassificationCdS;

    /** コース名(選択条件) */
    private String courseJnmS;

    /** 商品名(選択条件) */
    private String goodsNmS;

    /** 商品形態(選択条件) */
    private String productKbnS;

    /** 受取方法(選択条件) */
    private String saleKbnS;

    //2014.02.18 Add Start 商品購入ページからの呼出追加に伴う修正
    /** 講師への商品購入確認通知メール */
    private String tMailKbnS;
    //2014.02.18 Add End   商品購入ページからの呼出追加に伴う修正


    /** 一覧から選択された明細 */
    private String[] select_chk;

    /** 検索結果一覧 */
    private List<GoodsListModel> resultList;

    /** 確認押下可否フラグ */
    private boolean initFlg;


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
     * @param courseJnmS セットする courseJnmS
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

    //2014.02.18 Add Start 商品購入ページからの呼出追加に伴う修正
    // 講師への商品購入確認通知メール
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
    //2014.02.18 Add End   商品購入ページからの呼出追加に伴う修正


	/**
	 * @return select_chk
	 */
	public String[] getSelect_chk() {
		return select_chk;
	}

	/**
	 * @param select_chk セットする select_chk
	 */
	public void setSelect_chk(String[] select_chk) {
		this.select_chk = select_chk;
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

    /**
     * 画面状態フラグを取得する
     * @return initFlg
     */
    public boolean isInitFlg() {
        return initFlg;
    }

    /**
     * 画面状態フラグをセットする
     * @param initFlg セットする initFlg
     */
    public void setInitFlg(boolean initFlg) {
        this.initFlg = initFlg;
    }

}