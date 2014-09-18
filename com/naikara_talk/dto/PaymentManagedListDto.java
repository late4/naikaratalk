package com.naikara_talk.dto;

import java.math.BigDecimal;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>売上入金・支払管理。<br>
 * <b>クラス名称　　　:</b>支払管理ページクラス。<br>
 * <b>クラス概要　　　:</b>支払管理ページDTO。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴　　　　:</b>2013/07/23 TECS 新規作成。
 */

public class PaymentManagedListDto extends AbstractDto{

	private String paymentPlanYyyyMm;        // 支払予定年月
	private String dataYyyyMm;               // データ発生年月
	private String userId;                   // 講師ID (利用者ID)
	private String userName;                 // 名前(姓) + 名前(名)
	private int lessonNum;                   // レッスン回数(回)
	private BigDecimal batPaymentYen;        // バッチ計算支払予定額
	private BigDecimal sourceYen;            // 源泉額(円)
	private BigDecimal adjustmentYen;        // 調整額(円)
	private BigDecimal endPaymentYen;        // 支払予定額：調整後金額(円)
	private String paymentKbn;               // 支払対象区分
	private boolean radioFlag;               // 修正選択の使用不可flag
	private String checkboxBaseValue;        // checkbox値 base
	private String checkboxAddValue;         // checkbox値 add
	private String recordVerNo;              //レコードバージョン番号

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
	 * @return paymentPlanYyyyMm
	 */
	public String getPaymentPlanYyyyMm() {
		return paymentPlanYyyyMm;
	}
	/**
	 * @param paymentPlanYyyyMm セットする paymentPlanYyyyMm
	 */
	public void setPaymentPlanYyyyMm(String paymentPlanYyyyMm) {
		this.paymentPlanYyyyMm = paymentPlanYyyyMm;
	}
	/**
	 * @return dataYyyyMm
	 */
	public String getDataYyyyMm() {
		return dataYyyyMm;
	}
	/**
	 * @param dataYyyyMm セットする dataYyyyMm
	 */
	public void setDataYyyyMm(String dataYyyyMm) {
		this.dataYyyyMm = dataYyyyMm;
	}
	/**
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId セットする userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName セットする userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
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
	 * @return batPaymentYen
	 */
	public BigDecimal getBatPaymentYen() {
		return batPaymentYen;
	}
	/**
	 * @param batPaymentYen セットする batPaymentYen
	 */
	public void setBatPaymentYen(BigDecimal batPaymentYen) {
		this.batPaymentYen = batPaymentYen;
	}
	/**
	 * @return sourceYen
	 */
	public BigDecimal getSourceYen() {
		return sourceYen;
	}
	/**
	 * @param sourceYen セットする sourceYen
	 */
	public void setSourceYen(BigDecimal sourceYen) {
		this.sourceYen = sourceYen;
	}
	/**
	 * @return adjustmentYen
	 */
	public BigDecimal getAdjustmentYen() {
		return adjustmentYen;
	}
	/**
	 * @param adjustmentYen セットする adjustmentYen
	 */
	public void setAdjustmentYen(BigDecimal adjustmentYen) {
		this.adjustmentYen = adjustmentYen;
	}
	/**
	 * @return endPaymentYen
	 */
	public BigDecimal getEndPaymentYen() {
		return endPaymentYen;
	}
	/**
	 * @param endPaymentYen セットする endPaymentYen
	 */
	public void setEndPaymentYen(BigDecimal endPaymentYen) {
		this.endPaymentYen = endPaymentYen;
	}
	/**
	 * @return paymentKbn
	 */
	public String getPaymentKbn() {
		return paymentKbn;
	}
	/**
	 * @param paymentKbn セットする paymentKbn
	 */
	public void setPaymentKbn(String paymentKbn) {
		this.paymentKbn = paymentKbn;
	}
	/**
	 * @return checkboxBaseValue
	 */
	public String getCheckboxBaseValue() {
		return checkboxBaseValue;
	}
	/**
	 * @param checkboxBaseValue セットする checkboxBaseValue
	 */
	public void setCheckboxBaseValue(String checkboxBaseValue) {
		this.checkboxBaseValue = checkboxBaseValue;
	}
	/**
	 * @return checkboxAddValue
	 */
	public String getCheckboxAddValue() {
		return checkboxAddValue;
	}
	/**
	 * @param checkboxAddValue セットする checkboxAddValue
	 */
	public void setCheckboxAddValue(String checkboxAddValue) {
		this.checkboxAddValue = checkboxAddValue;
	}
	/**
	 * @return recordVerNo
	 */
	public String getRecordVerNo() {
		return recordVerNo;
	}
	/**
	 * @param recordVerNo セットする recordVerNo
	 */
	public void setRecordVerNo(String recordVerNo) {
		this.recordVerNo = recordVerNo;
	}

}
