package com.naikara_talk.dto;

import java.math.BigDecimal;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>入金管理ページ<br>
 * <b>クラス名称　　　:</b>入金管理ページクラス。<br>
 * <b>クラス概要　　　:</b>入金管理ページDTO。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/09 TECS 新規作成。
 */

public class SalesManagedListDto extends AbstractDto{

	private String objectYyyyMm;                       // 対象年月
	private String costomerKbn;                        // 顧客区分
	private String studentOrganizationId;              // 受講者ID／組織ID
	private String familyFirstKnm;                     // 受講者名(フリガナ)
	private String organizationJnm;                    // 組織名
	private String studentOrganizationJnm;             // 受講者名/組織名
	private BigDecimal compensationBeforePoint;        // 前受有償ポイント（合計）
	private BigDecimal compensationPurchasePoint;      // 購入有償ポイント（合計）
	private BigDecimal compensationUsePoint;           // 利用有償ポイント（合計）
	private BigDecimal goodsPurchaseYen;               // ポイント外商品購入（合計）
	private BigDecimal freeBeforePoint;                // 前受無償ポイント（合計）
	private BigDecimal freePurchasePoint;              // 購入無償ポイント（合計）
	private BigDecimal freeUsePoint;                   // 利用無償ポイント（合計）
	private String billNo;                             // 請求書番号
	private String studentId;                          // 受講者ID
	private String familyFirstJnm;                     // 受講者名
	private String purchaseUseDt;                      // 購入日／利用日
	private String purchaseId;                         // 購入ID(所有ID／予約No)
	private String purchaseNm;                         // コース名／商品名
	private String radioValue;                         // radioValue値
	private int returnCode;                            // returnCode

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
	 * @return familyFirstJnm
	 */
	public String getFamilyFirstJnm() {
		return familyFirstJnm;
	}
	/**
	 * @param familyFirstJnm セットする familyFirstJnm
	 */
	public void setFamilyFirstJnm(String familyFirstJnm) {
		this.familyFirstJnm = familyFirstJnm;
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
	/**
	 * @return billNo
	 */
	public String getBillNo() {
		return billNo;
	}
	/**
	 * @param billNo セットする billNo
	 */
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	/**
	 * @return objectYyyyMm
	 */
	public String getObjectYyyyMm() {
		return objectYyyyMm;
	}
	/**
	 * @param objectYyyyMm セットする objectYyyyMm
	 */
	public void setObjectYyyyMm(String objectYyyyMm) {
		this.objectYyyyMm = objectYyyyMm;
	}
	/**
	 * @return costomerKbn
	 */
	public String getCostomerKbn() {
		return costomerKbn;
	}
	/**
	 * @param costomerKbn セットする costomerKbn
	 */
	public void setCostomerKbn(String costomerKbn) {
		this.costomerKbn = costomerKbn;
	}
	/**
	 * @return studentOrganizationId
	 */
	public String getStudentOrganizationId() {
		return studentOrganizationId;
	}
	/**
	 * @param studentOrganizationId セットする studentOrganizationId
	 */
	public void setStudentOrganizationId(String studentOrganizationId) {
		this.studentOrganizationId = studentOrganizationId;
	}
	/**
	 * @return familyFirstKnm
	 */
	public String getFamilyFirstKnm() {
		return familyFirstKnm;
	}
	/**
	 * @param familyFirstKnm セットする familyFirstKnm
	 */
	public void setFamilyFirstKnm(String familyFirstKnm) {
		this.familyFirstKnm = familyFirstKnm;
	}
	/**
	 * @return organizationJnm
	 */
	public String getOrganizationJnm() {
		return organizationJnm;
	}
	/**
	 * @param organizationJnm セットする organizationJnm
	 */
	public void setOrganizationJnm(String organizationJnm) {
		this.organizationJnm = organizationJnm;
	}
	/**
	 * @return studentOrganizationJnm
	 */
	public String getStudentOrganizationJnm() {
		return studentOrganizationJnm;
	}
	/**
	 * @param studentOrganizationJnm セットする studentOrganizationJnm
	 */
	public void setStudentOrganizationJnm(String studentOrganizationJnm) {
		this.studentOrganizationJnm = studentOrganizationJnm;
	}
	/**
	 * @return compensationBeforePoint
	 */
	public BigDecimal getCompensationBeforePoint() {
		return compensationBeforePoint;
	}
	/**
	 * @param compensationBeforePoint セットする compensationBeforePoint
	 */
	public void setCompensationBeforePoint(BigDecimal compensationBeforePoint) {
		this.compensationBeforePoint = compensationBeforePoint;
	}
	/**
	 * @return compensationPurchasePoint
	 */
	public BigDecimal getCompensationPurchasePoint() {
		return compensationPurchasePoint;
	}
	/**
	 * @param compensationPurchasePoint セットする compensationPurchasePoint
	 */
	public void setCompensationPurchasePoint(BigDecimal compensationPurchasePoint) {
		this.compensationPurchasePoint = compensationPurchasePoint;
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
	 * @return freeBeforePoint
	 */
	public BigDecimal getFreeBeforePoint() {
		return freeBeforePoint;
	}
	/**
	 * @param freeBeforePoint セットする freeBeforePoint
	 */
	public void setFreeBeforePoint(BigDecimal freeBeforePoint) {
		this.freeBeforePoint = freeBeforePoint;
	}
	/**
	 * @return freePurchasePoint
	 */
	public BigDecimal getFreePurchasePoint() {
		return freePurchasePoint;
	}
	/**
	 * @param freePurchasePoint セットする freePurchasePoint
	 */
	public void setFreePurchasePoint(BigDecimal freePurchasePoint) {
		this.freePurchasePoint = freePurchasePoint;
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
	 * @return radioValue
	 */
	public String getRadioValue() {
		return radioValue;
	}
	/**
	 * @param radioValue セットする radioValue
	 */
	public void setRadioValue(String radioValue) {
		this.radioValue = radioValue;
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

}
