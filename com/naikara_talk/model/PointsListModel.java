package com.naikara_talk.model;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様利用画面<br>
 * <b>クラス名称       :</b>ポイント購入一覧<br>
 * <b>クラス概要       :</b>ポイント購入一覧 Model<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/19 TECS 新規作成
 */
public class PointsListModel implements Model {

    private static final long serialVersionUID = 1L;

	private String pointCd;          // ポイントコード
	private String moneyYen;         // 金額(税込)
	private String sumPoint;         // 有償無償合計ポイント
	private String paymentPoint;     // 有償ポイント
	private String paymentUseEndDt;  // 有償ポイント期限
	private String freePoint;        // 無償ポイント
	private String freeUseEndDt;     // 無償ポイント期限

	/**
     * @return pointCd 取得
     */
    public String getPointCd() {
        return pointCd;
    }

    /**
     * @param pointCd セット
     */
    public void setPointCd(String pointCd) {
        this.pointCd = pointCd;
    }

	/**
     * @return moneyYen 取得
     */
    public String getMoneyYen() {
        return moneyYen;
    }

    /**
     * @param moneyYen セット
     */
    public void setMoneyYen(String moneyYen) {
        this.moneyYen = moneyYen;
    }

	/**
     * @return sumPoint 取得
     */
    public String getSumPoint() {
        return sumPoint;
    }

    /**
     * @param sumPoint セット
     */
    public void setSumPoint(String sumPoint) {
        this.sumPoint = sumPoint;
    }

	/**
     * @return paymentPoint 取得
     */
    public String getPaymentPoint() {
        return paymentPoint;
    }

    /**
     * @param paymentPoint セット
     */
    public void setPaymentPoint(String paymentPoint) {
        this.paymentPoint = paymentPoint;
    }

	/**
     * @return paymentUseEndDt 取得
     */
    public String getPaymentUseEndDt() {
        return paymentUseEndDt;
    }

    /**
     * @param paymentUseEndDt セット
     */
    public void setPaymentUseEndDt(String paymentUseEndDt) {
        this.paymentUseEndDt = paymentUseEndDt;
    }

	/**
     * @return freePoint 取得
     */
    public String getFreePoint() {
        return freePoint;
    }

    /**
     * @param freePoint セット
     */
    public void setFreePoint(String freePoint) {
        this.freePoint = freePoint;
    }

	/**
     * @return freeUseEndDt 取得
     */
    public String getFreeUseEndDt() {
        return freeUseEndDt;
    }

    /**
     * @param freeUseEndDt セット
     */
    public void setFreeUseEndDt(String freeUseEndDt) {
        this.freeUseEndDt = freeUseEndDt;
    }

}