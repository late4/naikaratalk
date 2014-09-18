package com.naikara_talk.dto;

import java.sql.Timestamp;
import java.math.BigDecimal;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DTOクラス<br>
 * <b>クラス名称　　　:</b>支払明細テーブルクラス<br>
 * <b>クラス概要　　　:</b>支払明細テーブルDTO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/01 TECS 新規作成
 */
public class PaymentDetailsTrnDto extends AbstractDto{

	private String paymentPlanYyyyMm;  //支払予定年月
	private String dataYyyyMm;         //データ発生年月
	private String userId;             //講師ID (利用者ID)
	private String lessonDt;           //レッスン日
	private String lessonTmCd;         //レッスン時刻コード
	private String lessonTmNm;         //レッスン時刻名
	private String courseJnm;          //コース名
	private String studentId;          //受講者ID
	private String familyJnm;          //名前(姓)
	private String firstJnm;           //名前(名）
	private String cEvaluationKbn;     //受講者：講師への評価区分
	private String cEvaluationJnm;     //受講者：講師への評価
	private String sOnTeacherCmt;      //スクール：スクールから講師へのコメント
	private BigDecimal batPaymentYen;  //バッチ計算支払予定額
	private BigDecimal adjustmentYen;  //調整額
	private BigDecimal endPaymentYen;  //新支払予定額
	private int recordVerNo;           //レコードバージョン番号
	private Timestamp insertDttm;      //登録日時
	private String insertCd;           //登録者コード
	private Timestamp updateDttm;      //更新日時
	private String updateCd;           //更新者コード
	private int returnCode;            //リターンコード

	/**
	 * 支払予定年月取得<br>
	 * <br>
	 * 支払予定年月を戻り値で返却する<br>
	 * <br>
	 * @return paymentPlanYyyyMm
	 */
	public String getPaymentPlanYyyyMm(){
		return paymentPlanYyyyMm;
	}

	/**
	 * 支払予定年月設定<br>
	 * <br>
	 * 支払予定年月を引数で設定する<br>
	 * <br>
	 * @param paymentPlanYyyyMm
	 */
	public void setPaymentPlanYyyyMm(String paymentPlanYyyyMm){
		this.paymentPlanYyyyMm = paymentPlanYyyyMm;
	}

	/**
	 * データ発生年月取得<br>
	 * <br>
	 * データ発生年月を戻り値で返却する<br>
	 * <br>
	 * @return dataYyyyMm
	 */
	public String getDataYyyyMm(){
		return dataYyyyMm;
	}

	/**
	 * データ発生年月設定<br>
	 * <br>
	 * データ発生年月を引数で設定する<br>
	 * <br>
	 * @param dataYyyyMm
	 */
	public void setDataYyyyMm(String dataYyyyMm){
		this.dataYyyyMm = dataYyyyMm;
	}

	/**
	 * 講師ID (利用者ID)取得<br>
	 * <br>
	 * 講師ID (利用者ID)を戻り値で返却する<br>
	 * <br>
	 * @return userId
	 */
	public String getUserId(){
		return userId;
	}

	/**
	 * 講師ID (利用者ID)設定<br>
	 * <br>
	 * 講師ID (利用者ID)を引数で設定する<br>
	 * <br>
	 * @param userId
	 */
	public void setUserId(String userId){
		this.userId = userId;
	}

	/**
	 * レッスン日取得<br>
	 * <br>
	 * レッスン日を戻り値で返却する<br>
	 * <br>
	 * @return lessonDt
	 */
	public String getLessonDt(){
		return lessonDt;
	}

	/**
	 * レッスン日設定<br>
	 * <br>
	 * レッスン日を引数で設定する<br>
	 * <br>
	 * @param lessonDt
	 */
	public void setLessonDt(String lessonDt){
		this.lessonDt = lessonDt;
	}

	/**
	 * レッスン時刻コード取得<br>
	 * <br>
	 * レッスン時刻コードを戻り値で返却する<br>
	 * <br>
	 * @return lessonTmCd
	 */
	public String getLessonTmCd(){
		return lessonTmCd;
	}

	/**
	 * レッスン時刻コード設定<br>
	 * <br>
	 * レッスン時刻コードを引数で設定する<br>
	 * <br>
	 * @param lessonTmCd
	 */
	public void setLessonTmCd(String lessonTmCd){
		this.lessonTmCd = lessonTmCd;
	}

	/**
	 * レッスン時刻名取得<br>
	 * <br>
	 * レッスン時刻名を戻り値で返却する<br>
	 * <br>
	 * @return lessonTmNm
	 */
	public String getLessonTmNm(){
		return lessonTmNm;
	}

	/**
	 * レッスン時刻名設定<br>
	 * <br>
	 * レッスン時刻名を引数で設定する<br>
	 * <br>
	 * @param lessonTmNm
	 */
	public void setLessonTmNm(String lessonTmNm){
		this.lessonTmNm = lessonTmNm;
	}

	/**
	 * コース名取得<br>
	 * <br>
	 * コース名を戻り値で返却する<br>
	 * <br>
	 * @return courseJnm
	 */
	public String getCourseJnm(){
		return courseJnm;
	}

	/**
	 * コース名設定<br>
	 * <br>
	 * コース名を引数で設定する<br>
	 * <br>
	 * @param courseJnm
	 */
	public void setCourseJnm(String courseJnm){
		this.courseJnm = courseJnm;
	}

	/**
	 * 受講者ID取得<br>
	 * <br>
	 * 受講者IDを戻り値で返却する<br>
	 * <br>
	 * @return studentId
	 */
	public String getStudentId(){
		return studentId;
	}

	/**
	 * 受講者ID設定<br>
	 * <br>
	 * 受講者IDを引数で設定する<br>
	 * <br>
	 * @param studentId
	 */
	public void setStudentId(String studentId){
		this.studentId = studentId;
	}

	/**
	 * 名前(姓)取得<br>
	 * <br>
	 * 名前(姓)を戻り値で返却する<br>
	 * <br>
	 * @return familyJnm
	 */
	public String getFamilyJnm(){
		return familyJnm;
	}

	/**
	 * 名前(姓)設定<br>
	 * <br>
	 * 名前(姓)を引数で設定する<br>
	 * <br>
	 * @param familyJnm
	 */
	public void setFamilyJnm(String familyJnm){
		this.familyJnm = familyJnm;
	}

	/**
	 * 名前(名）取得<br>
	 * <br>
	 * 名前(名）を戻り値で返却する<br>
	 * <br>
	 * @return firstJnm
	 */
	public String getFirstJnm(){
		return firstJnm;
	}

	/**
	 * 名前(名）設定<br>
	 * <br>
	 * 名前(名）を引数で設定する<br>
	 * <br>
	 * @param firstJnm
	 */
	public void setFirstJnm(String firstJnm){
		this.firstJnm = firstJnm;
	}

	/**
	 * 受講者：講師への評価区分取得<br>
	 * <br>
	 * 受講者：講師への評価区分を戻り値で返却する<br>
	 * <br>
	 * @return cEvaluationKbn
	 */
	public String getCEvaluationKbn(){
		return cEvaluationKbn;
	}

	/**
	 * 受講者：講師への評価区分設定<br>
	 * <br>
	 * 受講者：講師への評価区分を引数で設定する<br>
	 * <br>
	 * @param cEvaluationKbn
	 */
	public void setCEvaluationKbn(String cEvaluationKbn){
		this.cEvaluationKbn = cEvaluationKbn;
	}

	/**
	 * 受講者：講師への評価取得<br>
	 * <br>
	 * 受講者：講師への評価を戻り値で返却する<br>
	 * <br>
	 * @return cEvaluationJnm
	 */
	public String getCEvaluationJnm(){
		return cEvaluationJnm;
	}

	/**
	 * 受講者：講師への評価設定<br>
	 * <br>
	 * 受講者：講師への評価を引数で設定する<br>
	 * <br>
	 * @param cEvaluationJnm
	 */
	public void setCEvaluationJnm(String cEvaluationJnm){
		this.cEvaluationJnm = cEvaluationJnm;
	}

	/**
	 * スクール：スクールから講師へのコメント取得<br>
	 * <br>
	 * スクール：スクールから講師へのコメントを戻り値で返却する<br>
	 * <br>
	 * @return sOnTeacherCmt
	 */
	public String getSOnTeacherCmt(){
		return sOnTeacherCmt;
	}

	/**
	 * スクール：スクールから講師へのコメント設定<br>
	 * <br>
	 * スクール：スクールから講師へのコメントを引数で設定する<br>
	 * <br>
	 * @param sOnTeacherCmt
	 */
	public void setSOnTeacherCmt(String sOnTeacherCmt){
		this.sOnTeacherCmt = sOnTeacherCmt;
	}

	/**
	 * バッチ計算支払予定額取得<br>
	 * <br>
	 * バッチ計算支払予定額を戻り値で返却する<br>
	 * <br>
	 * @return batPaymentYen
	 */
	public BigDecimal getBatPaymentYen(){
		return batPaymentYen;
	}

	/**
	 * バッチ計算支払予定額設定<br>
	 * <br>
	 * バッチ計算支払予定額を引数で設定する<br>
	 * <br>
	 * @param batPaymentYen
	 */
	public void setBatPaymentYen(BigDecimal batPaymentYen){
		this.batPaymentYen = batPaymentYen;
	}

	/**
	 * 調整額取得<br>
	 * <br>
	 * 調整額を戻り値で返却する<br>
	 * <br>
	 * @return adjustmentYen
	 */
	public BigDecimal getAdjustmentYen(){
		return adjustmentYen;
	}

	/**
	 * 調整額設定<br>
	 * <br>
	 * 調整額を引数で設定する<br>
	 * <br>
	 * @param adjustmentYen
	 */
	public void setAdjustmentYen(BigDecimal adjustmentYen){
		this.adjustmentYen = adjustmentYen;
	}

	/**
	 * 新支払予定額取得<br>
	 * <br>
	 * 新支払予定額を戻り値で返却する<br>
	 * <br>
	 * @return endPaymentYen
	 */
	public BigDecimal getEndPaymentYen(){
		return endPaymentYen;
	}

	/**
	 * 新支払予定額設定<br>
	 * <br>
	 * 新支払予定額を引数で設定する<br>
	 * <br>
	 * @param endPaymentYen
	 */
	public void setEndPaymentYen(BigDecimal endPaymentYen){
		this.endPaymentYen = endPaymentYen;
	}

	/**
	 * レコードバージョン番号取得<br>
	 * <br>
	 * レコードバージョン番号を戻り値で返却する<br>
	 * <br>
	 * @return recordVerNo
	 */
	public int getRecordVerNo(){
		return recordVerNo;
	}

	/**
	 * レコードバージョン番号設定<br>
	 * <br>
	 * レコードバージョン番号を引数で設定する<br>
	 * <br>
	 * @param recordVerNo
	 */
	public void setRecordVerNo(int recordVerNo){
		this.recordVerNo = recordVerNo;
	}

	/**
	 * 登録日時取得<br>
	 * <br>
	 * 登録日時を戻り値で返却する<br>
	 * <br>
	 * @return insertDttm
	 */
	public Timestamp getInsertDttm(){
		return insertDttm;
	}

	/**
	 * 登録日時設定<br>
	 * <br>
	 * 登録日時を引数で設定する<br>
	 * <br>
	 * @param insertDttm
	 */
	public void setInsertDttm(Timestamp insertDttm){
		this.insertDttm = insertDttm;
	}

	/**
	 * 登録者コード取得<br>
	 * <br>
	 * 登録者コードを戻り値で返却する<br>
	 * <br>
	 * @return insertCd
	 */
	public String getInsertCd(){
		return insertCd;
	}

	/**
	 * 登録者コード設定<br>
	 * <br>
	 * 登録者コードを引数で設定する<br>
	 * <br>
	 * @param insertCd
	 */
	public void setInsertCd(String insertCd){
		this.insertCd = insertCd;
	}

	/**
	 * 更新日時取得<br>
	 * <br>
	 * 更新日時を戻り値で返却する<br>
	 * <br>
	 * @return updateDttm
	 */
	public Timestamp getUpdateDttm(){
		return updateDttm;
	}

	/**
	 * 更新日時設定<br>
	 * <br>
	 * 更新日時を引数で設定する<br>
	 * <br>
	 * @param updateDttm
	 */
	public void setUpdateDttm(Timestamp updateDttm){
		this.updateDttm = updateDttm;
	}

	/**
	 * 更新者コード取得<br>
	 * <br>
	 * 更新者コードを戻り値で返却する<br>
	 * <br>
	 * @return updateCd
	 */
	public String getUpdateCd(){
		return updateCd;
	}

	/**
	 * 更新者コード設定<br>
	 * <br>
	 * 更新者コードを引数で設定する<br>
	 * <br>
	 * @param updateCd
	 */
	public void setUpdateCd(String updateCd){
		this.updateCd = updateCd;
	}

	/**
	 * リターンコード取得<br>
	 * <br>
	 * リターンコードを戻り値で返却する<br>
	 * <br>
	 * @return returnCode
	 */
	public int getReturnCode(){
		return returnCode;
	}

	/**
	 * リターンコード設定<br>
	 * <br>
	 * リターンコードを引数で設定する<br>
	 * <br>
	 * @param returnCode
	 */
	public void setReturnCode(int returnCode){
		this.returnCode = returnCode;
	}

}
