package com.naikara_talk.dto;

import java.math.BigDecimal;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>売上入金・支払管理。<br>
 * <b>クラス名称　　　:</b>支払入力・修正クラス。<br>
 * <b>クラス概要　　　:</b>支払入力・修正DTO。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴　　　　:</b>2013/07/30 TECS 新規作成。
 */

public class PaymentManagedDto extends AbstractDto{

	private String paymentPlanYyyyMm;     // 支払予定年月
	private String dataYyyyMm;            // データ発生年月
	private String userId;                // 講師ID (利用者ID)
	private String lessonDt;              // レッスン日
	private String lessonTmCd;            // レッスン時刻コード
	private String lessonTmNm;            // レッスン時刻名
	private String courseJnm;             // コース名
	private String studentId;             // 受講者ID
	private String familyJnm;             // 名前(姓)
	private String firstJnm;              // 名前(名）
	private String studentName;           // 受講者ID 名前の編集(名前(姓)、名前(名))
	private String evaluationKbn;         // 受講者：講師への評価区分
	private String evaluationJnm;         // 受講者：講師への評価
	private String onTeacherCmt;          // スクール：スクールから講師へのコメント
	private BigDecimal batPaymentYen;     // バッチ計算支払予定額
	private String adjustmentYen;         // 調整額
	private BigDecimal endPaymentYen;     // 現支払予定額
	private BigDecimal newPaymentYen;     // 新支払予定額
	private String recordVerNoM;          // レコードバージョン番号 一覧用
	private int returnCode;               // リターンコード
	private BigDecimal sourceYen;         // 源泉額(円)
	private BigDecimal adjustmentYenSum;  // 調整額合計
	private BigDecimal newPaymentYenSum;  // 新支払予定額合計
	private String recordVerNo;           // レコードバージョン番号
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
	/**
	 * @return adjustmentYenSum
	 */
	public BigDecimal getAdjustmentYenSum() {
		return adjustmentYenSum;
	}
	/**
	 * @param adjustmentYenSum セットする adjustmentYenSum
	 */
	public void setAdjustmentYenSum(BigDecimal adjustmentYenSum) {
		this.adjustmentYenSum = adjustmentYenSum;
	}
	/**
	 * @return newPaymentYenSum
	 */
	public BigDecimal getNewPaymentYenSum() {
		return newPaymentYenSum;
	}
	/**
	 * @param newPaymentYenSum セットする newPaymentYenSum
	 */
	public void setNewPaymentYenSum(BigDecimal newPaymentYenSum) {
		this.newPaymentYenSum = newPaymentYenSum;
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
	 * @return lessonTmCd
	 */
	public String getLessonTmCd() {
		return lessonTmCd;
	}
	/**
	 * @param lessonTmCd セットする lessonTmCd
	 */
	public void setLessonTmCd(String lessonTmCd) {
		this.lessonTmCd = lessonTmCd;
	}
	/**
	 * @return lessonTmNm
	 */
	public String getLessonTmNm() {
		return lessonTmNm;
	}
	/**
	 * @param lessonTmNm セットする lessonTmNm
	 */
	public void setLessonTmNm(String lessonTmNm) {
		this.lessonTmNm = lessonTmNm;
	}
	/**
	 * @return courseJnm
	 */
	public String getCourseJnm() {
		return courseJnm;
	}
	/**
	 * @param courseJnm セットする courseJnm
	 */
	public void setCourseJnm(String courseJnm) {
		this.courseJnm = courseJnm;
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
	 * @return evaluationKbn
	 */
	public String getEvaluationKbn() {
		return evaluationKbn;
	}
	/**
	 * @param evaluationKbn セットする evaluationKbn
	 */
	public void setEvaluationKbn(String evaluationKbn) {
		this.evaluationKbn = evaluationKbn;
	}
	/**
	 * @return evaluationJnm
	 */
	public String getEvaluationJnm() {
		return evaluationJnm;
	}
	/**
	 * @param evaluationJnm セットする evaluationJnm
	 */
	public void setEvaluationJnm(String evaluationJnm) {
		this.evaluationJnm = evaluationJnm;
	}
	/**
	 * @return onTeacherCmt
	 */
	public String getOnTeacherCmt() {
		return onTeacherCmt;
	}
	/**
	 * @param onTeacherCmt セットする onTeacherCmt
	 */
	public void setOnTeacherCmt(String onTeacherCmt) {
		this.onTeacherCmt = onTeacherCmt;
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
	 * @return adjustmentYen
	 */
	public String getAdjustmentYen() {
		return adjustmentYen;
	}
	/**
	 * @param adjustmentYen セットする adjustmentYen
	 */
	public void setAdjustmentYen(String adjustmentYen) {
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
	 * @return newPaymentYen
	 */
	public BigDecimal getNewPaymentYen() {
		return newPaymentYen;
	}
	/**
	 * @param newPaymentYen セットする newPaymentYen
	 */
	public void setNewPaymentYen(BigDecimal newPaymentYen) {
		this.newPaymentYen = newPaymentYen;
	}
	/**
	 * @return recordVerNoM
	 */
	public String getRecordVerNoM() {
		return recordVerNoM;
	}
	/**
	 * @param recordVerNoM セットする recordVerNoM
	 */
	public void setRecordVerNoM(String recordVerNoM) {
		this.recordVerNoM = recordVerNoM;
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
