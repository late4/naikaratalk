package com.naikara_talk.dto;

import java.math.BigDecimal;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>利用状況照会詳細<br>
 * <b>クラス名称　　　:</b>利用状況照会詳細クラス。<br>
 * <b>クラス概要　　　:</b>利用状況照会詳細DTO。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴　　　　:</b>2013/08/06 TECS 新規作成。
 */

public class OrganizationUsageSituationDto extends AbstractDto{

	private String lessonDt;                           // レッスン日
	private int returnCode;                            // リターンコード
	private String lessonTm;                           // レッスン時刻名
	private String purchaseNm;                         // コース名／商品名
	private BigDecimal goodsPurchaseYen;               // 有償：ポイント外商品購入
	private BigDecimal compensationUsePoint;           // 有償：利用有償ポイント
	private String objectYyyyMm;                       // 対象年月
	private String studentId;                          // 受講者ID
	private String sysdateYyyymm01;                    // システム日付の年月 & '01'
    private String sysdateYyyymmdd;                    // システム日付
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
	 * @return lessonTm
	 */
	public String getLessonTm() {
		return lessonTm;
	}
	/**
	 * @param lessonTm セットする lessonTm
	 */
	public void setLessonTm(String lessonTm) {
		this.lessonTm = lessonTm;
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


}
