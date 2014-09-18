package com.naikara_talk.dto;

import java.math.BigDecimal;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様管理ページ詳細<br>
 * <b>クラス名称　　　:</b>お客様管理ページ詳細クラス。<br>
 * <b>クラス概要　　　:</b>お客様管理ページ詳細DTO。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/20 TECS 新規作成。
 */

public class CustomerManagedDto extends AbstractDto{

	private String studentId;                          // 受講者ID
	private String objectPeriod;                       // 対象期間
	private String objectPeriodFrom;                   // 対象期間from
	private String objectPeriodTo;                     // 対象期間to
	private String periodFrom;                         // 期間指定from
	private String periodTo;                           // 期間指定to
	private int returnCode;                            // returnCoder

	private BigDecimal freeUsePoint;                   // 利用無償ポイント
	private BigDecimal compensationUsePoint;           // 利用有償ポイント
	private BigDecimal goodsPurchaseYen;               // ポイント外商品購入
	private String purchaseUseDt;                      // レッスン日/購入日
	private String purchaseId;                         // 予約NO/購入ID
	private String purchaseNm;                         // コース名/購入商品
	/**
	 * @return objectPeriod
	 */
	public String getObjectPeriod() {
		return objectPeriod;
	}
	/**
	 * @param objectPeriod セットする objectPeriod
	 */
	public void setObjectPeriod(String objectPeriod) {
		this.objectPeriod = objectPeriod;
	}
	/**
	 * @return studentId
	 */
	public String getStudentId() {
		return studentId;
	}
	/**
	 * @param studentId セットする studentId
	 */
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	/**
	 * @return objectPeriodFrom
	 */
	public String getObjectPeriodFrom() {
		return objectPeriodFrom;
	}
	/**
	 * @param objectPeriodFrom セットする objectPeriodFrom
	 */
	public void setObjectPeriodFrom(String objectPeriodFrom) {
		this.objectPeriodFrom = objectPeriodFrom;
	}
	/**
	 * @return objectPeriodTo
	 */
	public String getObjectPeriodTo() {
		return objectPeriodTo;
	}
	/**
	 * @param objectPeriodTo セットする objectPeriodTo
	 */
	public void setObjectPeriodTo(String objectPeriodTo) {
		this.objectPeriodTo = objectPeriodTo;
	}
	/**
	 * @return periodFrom
	 */
	public String getPeriodFrom() {
		return periodFrom;
	}
	/**
	 * @param periodFrom セットする periodFrom
	 */
	public void setPeriodFrom(String periodFrom) {
		this.periodFrom = periodFrom;
	}
	/**
	 * @return periodTo
	 */
	public String getPeriodTo() {
		return periodTo;
	}
	/**
	 * @param periodTo セットする periodTo
	 */
	public void setPeriodTo(String periodTo) {
		this.periodTo = periodTo;
	}
	/**
	 * @return returnCode
	 */
	public int getReturnCode() {
		return returnCode;
	}
	/**
	 * @param returnCode セットする returnCode
	 */
	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}
	/**
	 * @return freeUsePoint
	 */
	public BigDecimal getFreeUsePoint() {
		return freeUsePoint;
	}
	/**
	 * @param freeUsePoint セットする freeUsePoint
	 */
	public void setFreeUsePoint(BigDecimal freeUsePoint) {
		this.freeUsePoint = freeUsePoint;
	}
	/**
	 * @return compensationUsePoint
	 */
	public BigDecimal getCompensationUsePoint() {
		return compensationUsePoint;
	}
	/**
	 * @param compensationUsePoint セットする compensationUsePoint
	 */
	public void setCompensationUsePoint(BigDecimal compensationUsePoint) {
		this.compensationUsePoint = compensationUsePoint;
	}
	/**
	 * @return goodsPurchaseYen
	 */
	public BigDecimal getGoodsPurchaseYen() {
		return goodsPurchaseYen;
	}
	/**
	 * @param goodsPurchaseYen セットする goodsPurchaseYen
	 */
	public void setGoodsPurchaseYen(BigDecimal goodsPurchaseYen) {
		this.goodsPurchaseYen = goodsPurchaseYen;
	}
	/**
	 * @return purchaseUseDt
	 */
	public String getPurchaseUseDt() {
		return purchaseUseDt;
	}
	/**
	 * @param purchaseUseDt セットする purchaseUseDt
	 */
	public void setPurchaseUseDt(String purchaseUseDt) {
		this.purchaseUseDt = purchaseUseDt;
	}
	/**
	 * @return purchaseId
	 */
	public String getPurchaseId() {
		return purchaseId;
	}
	/**
	 * @param purchaseId セットする purchaseId
	 */
	public void setPurchaseId(String purchaseId) {
		this.purchaseId = purchaseId;
	}
	/**
	 * @return purchaseNm
	 */
	public String getPurchaseNm() {
		return purchaseNm;
	}
	/**
	 * @param purchaseNm セットする purchaseNm
	 */
	public void setPurchaseNm(String purchaseNm) {
		this.purchaseNm = purchaseNm;
	}

}
