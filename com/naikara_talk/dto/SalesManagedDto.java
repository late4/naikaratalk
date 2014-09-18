package com.naikara_talk.dto;

import java.math.BigDecimal;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>入金管理ページ詳細<br>
 * <b>クラス名称　　　:</b>入金管理ページ詳細クラス。<br>
 * <b>クラス概要　　　:</b>入金管理ページ詳細DTO。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/09 TECS 新規作成。
 */

public class SalesManagedDto extends AbstractDto{

	private String objectYyyyMm;                    // 対象年月
	private String costomerKbn;                     // 顧客区分
	private String studentOrganizationId;           // 受講者ID／組織ID
	private int returnCode;                         // returnCode
	private String studentId;                       // 受講者ID
	private String familyFirstJnm;                  // 受講者名
	private String purchaseUseDt;                   // 購入日／利用日
	private String purchaseId;                      // 購入ID(所有ID／予約No)
	private String purchaseNm;                      // コース名／商品名
	private BigDecimal compensationPurchasePoint;   // 有償：購入有償ポイント
	private BigDecimal freeUsePoint;                // 無償：利用無償ポイント
	private BigDecimal compensationUsePoint;        // 有償：利用有償ポイント
	private BigDecimal goodsPurchaseYen;            // 有償：ポイント外商品購入
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

}
