package com.naikara_talk.dto;

import java.sql.Timestamp;
import java.math.BigDecimal;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DTOクラス<br>
 * <b>クラス名称　　　:</b>支払集計テーブルクラス<br>
 * <b>クラス概要　　　:</b>支払集計テーブルDTO<br>
 * <br>
 * <b>著作権　　　　　:</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/01 TECS 新規作成
 */
public class PaymentTrnSingleDto extends AbstractDto{

	// 支払集計テーブル
	private String paymentPlanYyyyMm;  //支払予定年月
	private String dataYyyyMm;         //データ発生年月
	private String userId;             //講師ID (利用者ID)
	private String teacherNickNm;      //講師ニックネーム
	private String familyJnm;         //名前(姓)
	private String firstJnm;          //名前(名)
	private int lessonNum;             //レッスン回数(回)
	private BigDecimal batPaymentYen;  //バッチ計算支払予定額
	private BigDecimal sourceYen;      //源泉額(円)
	private BigDecimal adjustmentYen;  //調整額(円)
	private BigDecimal endPaymentYen;  //支払予定額：調整後金額(円)
	private String paymentKbn;         //支払対象区分
	private String paymentNm;          //支払対象区分名
	private int recordVerNo;           //レコードバージョン番号
	private Timestamp insertDttm;      //登録日時
	private String insertCd;           //登録者コード
	private Timestamp updateDttm;      //更新日時
	private String updateCd;           //更新者コード

    private int returnCode;            // リターンコード


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
	 * 講師ニックネーム取得<br>
	 * <br>
	 * 講師ニックネームを戻り値で返却する<br>
	 * <br>
	 * @return teacherNickNm
	 */
	public String getTeacherNickNm(){
		return teacherNickNm;
	}

	/**
	 * 講師ニックネーム設定<br>
	 * <br>
	 * 講師ニックネームを引数で設定する<br>
	 * <br>
	 * @param teacherNickNm
	 */
	public void setTeacherNickNm(String teacherNickNm){
		this.teacherNickNm = teacherNickNm;
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
	 * 名前(名)取得<br>
	 * <br>
	 * 名前(名)を戻り値で返却する<br>
	 * <br>
	 * @return firstJnm
	 */
	public String getFirstJnm(){
		return firstJnm;
	}

	/**
	 * 名前(名)設定<br>
	 * <br>
	 * 名前(名)を引数で設定する<br>
	 * <br>
	 * @param firstJnm
	 */
	public void setFirstJnm(String firstJnm){
		this.firstJnm = firstJnm;
	}

	/**
	 * レッスン回数(回)取得<br>
	 * <br>
	 * レッスン回数(回)を戻り値で返却する<br>
	 * <br>
	 * @return lessonNum
	 */
	public int getLessonNum(){
		return lessonNum;
	}

	/**
	 * レッスン回数(回)設定<br>
	 * <br>
	 * レッスン回数(回)を引数で設定する<br>
	 * <br>
	 * @param lessonNum
	 */
	public void setLessonNum(int lessonNum){
		this.lessonNum = lessonNum;
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
	 * 源泉額(円)取得<br>
	 * <br>
	 * 源泉額(円)を戻り値で返却する<br>
	 * <br>
	 * @return sourceYen
	 */
	public BigDecimal getSourceYen(){
		return sourceYen;
	}

	/**
	 * 源泉額(円)設定<br>
	 * <br>
	 * 源泉額(円)を引数で設定する<br>
	 * <br>
	 * @param sourceYen
	 */
	public void setSourceYen(BigDecimal sourceYen){
		this.sourceYen = sourceYen;
	}

	/**
	 * 調整額(円)取得<br>
	 * <br>
	 * 調整額(円)を戻り値で返却する<br>
	 * <br>
	 * @return adjustmentYen
	 */
	public BigDecimal getAdjustmentYen(){
		return adjustmentYen;
	}

	/**
	 * 調整額(円)設定<br>
	 * <br>
	 * 調整額(円)を引数で設定する<br>
	 * <br>
	 * @param adjustmentYen
	 */
	public void setAdjustmentYen(BigDecimal adjustmentYen){
		this.adjustmentYen = adjustmentYen;
	}

	/**
	 * 支払予定額：調整後金額(円)取得<br>
	 * <br>
	 * 支払予定額：調整後金額(円)を戻り値で返却する<br>
	 * <br>
	 * @return endPaymentYen
	 */
	public BigDecimal getEndPaymentYen(){
		return endPaymentYen;
	}

	/**
	 * 支払予定額：調整後金額(円)設定<br>
	 * <br>
	 * 支払予定額：調整後金額(円)を引数で設定する<br>
	 * <br>
	 * @param endPaymentYen
	 */
	public void setEndPaymentYen(BigDecimal endPaymentYen){
		this.endPaymentYen = endPaymentYen;
	}

	/**
	 * 支払対象区分取得<br>
	 * <br>
	 * 支払対象区分を戻り値で返却する<br>
	 * <br>
	 * @return paymentKbn
	 */
	public String getPaymentKbn(){
		return paymentKbn;
	}

	/**
	 * 支払対象区分設定<br>
	 * <br>
	 * 支払対象区分を引数で設定する<br>
	 * <br>
	 * @param paymentKbn
	 */
	public void setPaymentKbn(String paymentKbn){
		this.paymentKbn = paymentKbn;
	}

	/**
	 * 支払対象区分名取得<br>
	 * <br>
	 * 支払対象区分名を戻り値で返却する<br>
	 * <br>
	 * @return paymentNm
	 */
	public String getPaymentNm(){
		return paymentNm;
	}

	/**
	 * 支払対象区分名設定<br>
	 * <br>
	 * 支払対象区分名を引数で設定する<br>
	 * <br>
	 * @param paymentNm
	 */
	public void setPaymentNm(String paymentNm){
		this.paymentNm = paymentNm;
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
    public int getReturnCode() {
        return returnCode;
    }

    /**
     * リターンコード設定<br>
     * <br>
     * リターンコードを引数で設定する<br>
     * <br>
     * @param returnCode
     */
    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

}
