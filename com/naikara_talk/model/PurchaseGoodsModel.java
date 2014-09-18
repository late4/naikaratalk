package com.naikara_talk.model;

import java.math.BigDecimal;
import java.util.List;

import com.naikara_talk.model.GoodsListModel;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称 :</b>お客様利用画面<br>
 * <b>クラス名称       :</b>ポイント購入ページ（確認、支払）<br>
 * <b>クラス概要       :</b>ポイント購入ページ（確認、支払）Model<br>
 * <br>
 * <b>著作権           :</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/13 TECS 新規作成。
 */
public class PurchaseGoodsModel implements Model {

    private static final long serialVersionUID = 1L;

    /* チェック：問題なし(正常) */
    public static final int CHECK_OK = 0;


    /** 一覧から選択された明細（商品コード） */
    private String[] goodsCd;

    /** 検索結果一覧 */
    private List<GoodsListModel> resultList;

    /** ポイント残高 */
    private String pointBalance;

    /** ポイント残高 */
    private String usePointTotal;

    /** ポイント残高 */
    private BigDecimal pointBalanceB;

    /** ポイント残高 */
    private BigDecimal usePointTotalB;


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

}