package com.naikara_talk.dto;

import java.math.BigDecimal;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>利用状況照会<br>
 * <b>クラス名称　　　:</b>利用状況照会クラス。<br>
 * <b>クラス概要　　　:</b>利用状況照会DTO。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴　　　　:</b>2013/08/06 TECS 新規作成。
 */

public class OrganizationUsageSituationListDto extends AbstractDto{

	private String organizationId;                     // 組織ID
    private String positionOrganizationIdFrom;         // 所属組織内ID From
    private String positionOrganizationIdTo;           // 所属組織内ID To
    private int returnCode;                            // returnCode
    private String studentPosition;                    // 受講者所属部署
    private String positionOrganizationId;             // 所属組織内ID
    private String studentName;                        // 名前 名前(姓) + 名前(名)
    private BigDecimal purchaseYen;                    // ポイント外商品購入（合計）
    private BigDecimal userPoint;                      // 利用有償ポイント（合計）
    private int burdenNum;                             // 自己負担率
    private BigDecimal burdenPoint;                    // 自己負担ポイント（合計） 利用有償ポイント(合計) * 自己負担率
    private BigDecimal organizationPoint;              // 組織負担ポイント 利用ポイント(合計) - 自己負担ポイント
    private int lessonNum;                             // 受講済みレッスン回数
    private String lastUseDt;                          // 最終利用日
    private String studentId;                          // 受講者ID
    private String familyKnm;                          // フリガナ(姓)
    private String firstKnm;                           // フリガナ(名)
    private String objectYyyyMm;                       // 対象年月
    private String familyFirstKnm;                     // 受講者名(フリガナ)
    private String sysdateYyyymm01;                    // システム日付の年月 & '01'
    private String sysdateYyyymmdd;                    // システム日付
    private String familyJnm;                          // 名前(姓)
    private String firstJnm;                           // 名前(名)
    private String lessonDt;                           // レッスン日
    private String purchaseDt1;                        // 購入日1
    private String purchaseDt2;                        // 購入日2
    private boolean radioFlag;                         // 選択の使用不可flag
    private String radioValue;                         // radioValue値

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
	 * @return radioFlag
	 */
	public boolean isRadioFlag() {
		return radioFlag;
	}
	/**
	 * @param radioFlag セットする radioFlag
	 */
	public void setRadioFlag(boolean radioFlag) {
		this.radioFlag = radioFlag;
	}
	/**
	 * @return lessonDt
	 */
	public String getLessonDt() {
		return lessonDt;
	}
	/**
	 * @param lessonDt セットする lessonDt
	 */
	public void setLessonDt(String lessonDt) {
		this.lessonDt = lessonDt;
	}
	/**
	 * @return purchaseDt1
	 */
	public String getPurchaseDt1() {
		return purchaseDt1;
	}
	/**
	 * @param purchaseDt1 セットする purchaseDt1
	 */
	public void setPurchaseDt1(String purchaseDt1) {
		this.purchaseDt1 = purchaseDt1;
	}
	/**
	 * @return purchaseDt2
	 */
	public String getPurchaseDt2() {
		return purchaseDt2;
	}
	/**
	 * @param purchaseDt2 セットする purchaseDt2
	 */
	public void setPurchaseDt2(String purchaseDt2) {
		this.purchaseDt2 = purchaseDt2;
	}
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
	 * @return sysdateYyyymm01
	 */
	public String getSysdateYyyymm01() {
		return sysdateYyyymm01;
	}
	/**
	 * @param sysdateYyyymm01 セットする sysdateYyyymm01
	 */
	public void setSysdateYyyymm01(String sysdateYyyymm01) {
		this.sysdateYyyymm01 = sysdateYyyymm01;
	}
	/**
	 * @return sysdateYyyymmdd
	 */
	public String getSysdateYyyymmdd() {
		return sysdateYyyymmdd;
	}
	/**
	 * @param sysdateYyyymmdd セットする sysdateYyyymmdd
	 */
	public void setSysdateYyyymmdd(String sysdateYyyymmdd) {
		this.sysdateYyyymmdd = sysdateYyyymmdd;
	}
	/**
	 * @return organizationId
	 */
	public String getOrganizationId() {
		return organizationId;
	}
	/**
	 * @param organizationId セットする organizationId
	 */
	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}
	/**
	 * @return positionOrganizationIdFrom
	 */
	public String getPositionOrganizationIdFrom() {
		return positionOrganizationIdFrom;
	}
	/**
	 * @param positionOrganizationIdFrom セットする positionOrganizationIdFrom
	 */
	public void setPositionOrganizationIdFrom(String positionOrganizationIdFrom) {
		this.positionOrganizationIdFrom = positionOrganizationIdFrom;
	}
	/**
	 * @return positionOrganizationIdTo
	 */
	public String getPositionOrganizationIdTo() {
		return positionOrganizationIdTo;
	}
	/**
	 * @param positionOrganizationIdTo セットする positionOrganizationIdTo
	 */
	public void setPositionOrganizationIdTo(String positionOrganizationIdTo) {
		this.positionOrganizationIdTo = positionOrganizationIdTo;
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
	 * @return studentPosition
	 */
	public String getStudentPosition() {
		return studentPosition;
	}
	/**
	 * @param studentPosition セットする studentPosition
	 */
	public void setStudentPosition(String studentPosition) {
		this.studentPosition = studentPosition;
	}
	/**
	 * @return positionOrganizationId
	 */
	public String getPositionOrganizationId() {
		return positionOrganizationId;
	}
	/**
	 * @param positionOrganizationId セットする positionOrganizationId
	 */
	public void setPositionOrganizationId(String positionOrganizationId) {
		this.positionOrganizationId = positionOrganizationId;
	}
	/**
	 * @return studentName
	 */
	public String getStudentName() {
		return studentName;
	}
	/**
	 * @param studentName セットする studentName
	 */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	/**
	 * @return purchaseYen
	 */
	public BigDecimal getPurchaseYen() {
		return purchaseYen;
	}
	/**
	 * @param purchaseYen セットする purchaseYen
	 */
	public void setPurchaseYen(BigDecimal purchaseYen) {
		this.purchaseYen = purchaseYen;
	}
	/**
	 * @return userPoint
	 */
	public BigDecimal getUserPoint() {
		return userPoint;
	}
	/**
	 * @param userPoint セットする userPoint
	 */
	public void setUserPoint(BigDecimal userPoint) {
		this.userPoint = userPoint;
	}
	/**
	 * @return burdenNum
	 */
	public int getBurdenNum() {
		return burdenNum;
	}
	/**
	 * @param burdenNum セットする burdenNum
	 */
	public void setBurdenNum(int burdenNum) {
		this.burdenNum = burdenNum;
	}
	/**
	 * @return burdenPoint
	 */
	public BigDecimal getBurdenPoint() {
		return burdenPoint;
	}
	/**
	 * @param burdenPoint セットする burdenPoint
	 */
	public void setBurdenPoint(BigDecimal burdenPoint) {
		this.burdenPoint = burdenPoint;
	}
	/**
	 * @return organizationPoint
	 */
	public BigDecimal getOrganizationPoint() {
		return organizationPoint;
	}
	/**
	 * @param organizationPoint セットする organizationPoint
	 */
	public void setOrganizationPoint(BigDecimal organizationPoint) {
		this.organizationPoint = organizationPoint;
	}
	/**
	 * @return lessonNum
	 */
	public int getLessonNum() {
		return lessonNum;
	}
	/**
	 * @param lessonNum セットする lessonNum
	 */
	public void setLessonNum(int lessonNum) {
		this.lessonNum = lessonNum;
	}
	/**
	 * @return lastUseDt
	 */
	public String getLastUseDt() {
		return lastUseDt;
	}
	/**
	 * @param lastUseDt セットする lastUseDt
	 */
	public void setLastUseDt(String lastUseDt) {
		this.lastUseDt = lastUseDt;
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
	 * @return familyKnm
	 */
	public String getFamilyKnm() {
		return familyKnm;
	}
	/**
	 * @param familyKnm セットする familyKnm
	 */
	public void setFamilyKnm(String familyKnm) {
		this.familyKnm = familyKnm;
	}
	/**
	 * @return firstKnm
	 */
	public String getFirstKnm() {
		return firstKnm;
	}
	/**
	 * @param firstKnm セットする firstKnm
	 */
	public void setFirstKnm(String firstKnm) {
		this.firstKnm = firstKnm;
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
}
