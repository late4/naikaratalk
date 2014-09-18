package com.naikara_talk.dto;

import java.math.BigDecimal;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様管理ページ<br>
 * <b>クラス名称　　　:</b>お客様管理ページクラス。<br>
 * <b>クラス概要　　　:</b>お客様管理ページDTO。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/20 TECS 新規作成。
 */

public class CustomerManagedListDto extends AbstractDto{

	private String costomerKbn;                        // 顧客区分
	private String organizationJnm;                    // 組織名
	private String studentId;                          // 受講者ID
	private String familyFirstJnm;                     // 名前(姓)
	private String familyJnm;                          // 名前(名)
	private String firstJnm;                           // 受講者名
	private BigDecimal freeUsePoint;                   // 利用無償ポイント(合計)
	private BigDecimal compensationUsePoint;           // 利用有償ポイント(合計)
	private BigDecimal balancePoint;                   // ポイント残高(合計)
	private BigDecimal goodsPurchaseYen;               // ポイント外商品購入
	private String familyFirstKnm;                     // 受講者名(フリガナ)
	private String familyFirstRomaji;                  // 受講者名(ローマ字)
	private String nickNm;                             // 受講者名(ニックネーム)
	private String mailAddress1;                       // 受講者(メールアドレス)
	private String objectPeriodFrom;                   // 対象期間from
	private String objectPeriodTo;                     // 対象期間to
	private String periodFrom;                         // 期間指定from
	private String periodTo;                           // 期間指定to
	private String radioValue;                         // radioValue値
	private String objectPeriod;                       // 対象期間
	private String systemDate;                         // サーバーのシステム日付
	private int returnCode;                            // returnCoder

	/**
	 * @return familyJnm
	 */
	public String getFamilyJnm() {
		return familyJnm;
	}
	/**
	 * @param familyJnm セットする familyJnm
	 */
	public void setFamilyJnm(String familyJnm) {
		this.familyJnm = familyJnm;
	}
	/**
	 * @return firstJnm
	 */
	public String getFirstJnm() {
		return firstJnm;
	}
	/**
	 * @param firstJnm セットする firstJnm
	 */
	public void setFirstJnm(String firstJnm) {
		this.firstJnm = firstJnm;
	}
	/**
	 * @return systemDate
	 */
	public String getSystemDate() {
		return systemDate;
	}
	/**
	 * @param systemDate セットする systemDate
	 */
	public void setSystemDate(String systemDate) {
		this.systemDate = systemDate;
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
	 * @return balancePoint
	 */
	public BigDecimal getBalancePoint() {
		return balancePoint;
	}
	/**
	 * @param balancePoint セットする balancePoint
	 */
	public void setBalancePoint(BigDecimal balancePoint) {
		this.balancePoint = balancePoint;
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
	 * @return familyFirstRomaji
	 */
	public String getFamilyFirstRomaji() {
		return familyFirstRomaji;
	}
	/**
	 * @param familyFirstRomaji セットする familyFirstRomaji
	 */
	public void setFamilyFirstRomaji(String familyFirstRomaji) {
		this.familyFirstRomaji = familyFirstRomaji;
	}
	/**
	 * @return nickNm
	 */
	public String getNickNm() {
		return nickNm;
	}
	/**
	 * @param nickNm セットする nickNm
	 */
	public void setNickNm(String nickNm) {
		this.nickNm = nickNm;
	}
	/**
	 * @return mailAddress1
	 */
	public String getMailAddress1() {
		return mailAddress1;
	}
	/**
	 * @param mailAddress1 セットする mailAddress1
	 */
	public void setMailAddress1(String mailAddress1) {
		this.mailAddress1 = mailAddress1;
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
