package com.naikara_talk.dto;

import java.sql.Timestamp;
import java.math.BigDecimal;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DTOクラス<br>
 * <b>クラス名称　　　:</b>売上明細テーブルクラス<br>
 * <b>クラス概要　　　:</b>売上明細テーブルDTO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/01 TECS 新規作成
 */
public class SalesDetailsTrnDto extends AbstractDto{

	private String yyyyMm;                        //年月
	private String purchaseId;                    //購入ID(所有ID／予約No)
	private String dataKbn;                       //データ区分
	private String purchaseUseDt;                 //購入日／利用日
	private String studentId;                     //受講者ID
	private String salesKbn;                      //売上区分
	private String lessonTmCd;                    //レッスン時刻コード
	private String lessonTmNm;                    //レッスン時刻名
	private String courseGoodsCd;                 //コースコード／商品コード
	private String purchaseNm;                    //コース名／商品名
	private BigDecimal compensationBeforePoint;   //有償：前受有償ポイント
	private BigDecimal compensationPurchasePoint; //有償：購入有償ポイント
	private BigDecimal compensationUsePoint;      //有償：利用有償ポイント
	private BigDecimal goodsPurchaseYen;          //有償：ポイント外商品購入
	private BigDecimal freeBeforePoint;           //無償：前受無償ポイント
	private BigDecimal freePurchasePoint;         //無償：購入無償ポイント
	private BigDecimal freeUsePoint;              //無償：利用無償ポイント
	private String dailyBatchFlg;                 //日次作成フラグ
	private String billNo;                        //請求書番号
	private String billStudentId;                 //請求書受講者番号
	private String billDetailsNo;                 //明細番号
	private int recordVerNo;                      //レコードバージョン番号
	private Timestamp insertDttm;                 //登録日時
	private String insertCd;                      //登録者コード
	private Timestamp updateDttm;                 //更新日時
	private String updateCd;                      //更新者コード
	private int returnCode;                       //リターンコード

	/**
	 * 年月取得<br>
	 * <br>
	 * 年月を戻り値で返却する<br>
	 * <br>
	 * @return yyyyMm
	 */
	public String getYyyyMm(){
		return yyyyMm;
	}

	/**
	 * 年月設定<br>
	 * <br>
	 * 年月を引数で設定する<br>
	 * <br>
	 * @param yyyyMm
	 */
	public void setYyyyMm(String yyyyMm){
		this.yyyyMm = yyyyMm;
	}

	/**
	 * 購入ID(所有ID／予約No)取得<br>
	 * <br>
	 * 購入ID(所有ID／予約No)を戻り値で返却する<br>
	 * <br>
	 * @return purchaseId
	 */
	public String getPurchaseId(){
		return purchaseId;
	}

	/**
	 * 購入ID(所有ID／予約No)設定<br>
	 * <br>
	 * 購入ID(所有ID／予約No)を引数で設定する<br>
	 * <br>
	 * @param purchaseId
	 */
	public void setPurchaseId(String purchaseId){
		this.purchaseId = purchaseId;
	}

	/**
	 * データ区分取得<br>
	 * <br>
	 * データ区分を戻り値で返却する<br>
	 * <br>
	 * @return dataKbn
	 */
	public String getDataKbn(){
		return dataKbn;
	}

	/**
	 * データ区分設定<br>
	 * <br>
	 * データ区分を引数で設定する<br>
	 * <br>
	 * @param dataKbn
	 */
	public void setDataKbn(String dataKbn){
		this.dataKbn = dataKbn;
	}

	/**
	 * 購入日／利用日取得<br>
	 * <br>
	 * 購入日／利用日を戻り値で返却する<br>
	 * <br>
	 * @return purchaseUseDt
	 */
	public String getPurchaseUseDt(){
		return purchaseUseDt;
	}

	/**
	 * 購入日／利用日設定<br>
	 * <br>
	 * 購入日／利用日を引数で設定する<br>
	 * <br>
	 * @param purchaseUseDt
	 */
	public void setPurchaseUseDt(String purchaseUseDt){
		this.purchaseUseDt = purchaseUseDt;
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
	 * 売上区分取得<br>
	 * <br>
	 * 売上区分を戻り値で返却する<br>
	 * <br>
	 * @return salesKbn
	 */
	public String getSalesKbn(){
		return salesKbn;
	}

	/**
	 * 売上区分設定<br>
	 * <br>
	 * 売上区分を引数で設定する<br>
	 * <br>
	 * @param salesKbn
	 */
	public void setSalesKbn(String salesKbn){
		this.salesKbn = salesKbn;
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
	 * コースコード／商品コード取得<br>
	 * <br>
	 * コースコード／商品コードを戻り値で返却する<br>
	 * <br>
	 * @return courseGoodsCd
	 */
	public String getCourseGoodsCd(){
		return courseGoodsCd;
	}

	/**
	 * コースコード／商品コード設定<br>
	 * <br>
	 * コースコード／商品コードを引数で設定する<br>
	 * <br>
	 * @param courseGoodsCd
	 */
	public void setCourseGoodsCd(String courseGoodsCd){
		this.courseGoodsCd = courseGoodsCd;
	}

	/**
	 * コース名／商品名取得<br>
	 * <br>
	 * コース名／商品名を戻り値で返却する<br>
	 * <br>
	 * @return purchaseNm
	 */
	public String getPurchaseNm(){
		return purchaseNm;
	}

	/**
	 * コース名／商品名設定<br>
	 * <br>
	 * コース名／商品名を引数で設定する<br>
	 * <br>
	 * @param purchaseNm
	 */
	public void setPurchaseNm(String purchaseNm){
		this.purchaseNm = purchaseNm;
	}

	/**
	 * 有償：前受有償ポイント取得<br>
	 * <br>
	 * 有償：前受有償ポイントを戻り値で返却する<br>
	 * <br>
	 * @return compensationBeforePoint
	 */
	public BigDecimal getCompensationBeforePoint(){
		return compensationBeforePoint;
	}

	/**
	 * 有償：前受有償ポイント設定<br>
	 * <br>
	 * 有償：前受有償ポイントを引数で設定する<br>
	 * <br>
	 * @param compensationBeforePoint
	 */
	public void setCompensationBeforePoint(BigDecimal compensationBeforePoint){
		this.compensationBeforePoint = compensationBeforePoint;
	}

	/**
	 * 有償：購入有償ポイント取得<br>
	 * <br>
	 * 有償：購入有償ポイントを戻り値で返却する<br>
	 * <br>
	 * @return compensationPurchasePoint
	 */
	public BigDecimal getCompensationPurchasePoint(){
		return compensationPurchasePoint;
	}

	/**
	 * 有償：購入有償ポイント設定<br>
	 * <br>
	 * 有償：購入有償ポイントを引数で設定する<br>
	 * <br>
	 * @param compensationPurchasePoint
	 */
	public void setCompensationPurchasePoint(BigDecimal compensationPurchasePoint){
		this.compensationPurchasePoint = compensationPurchasePoint;
	}

	/**
	 * 有償：利用有償ポイント取得<br>
	 * <br>
	 * 有償：利用有償ポイントを戻り値で返却する<br>
	 * <br>
	 * @return compensationUsePoint
	 */
	public BigDecimal getCompensationUsePoint(){
		return compensationUsePoint;
	}

	/**
	 * 有償：利用有償ポイント設定<br>
	 * <br>
	 * 有償：利用有償ポイントを引数で設定する<br>
	 * <br>
	 * @param compensationUsePoint
	 */
	public void setCompensationUsePoint(BigDecimal compensationUsePoint){
		this.compensationUsePoint = compensationUsePoint;
	}

	/**
	 * 有償：ポイント外商品購入取得<br>
	 * <br>
	 * 有償：ポイント外商品購入を戻り値で返却する<br>
	 * <br>
	 * @return goodsPurchaseYen
	 */
	public BigDecimal getGoodsPurchaseYen(){
		return goodsPurchaseYen;
	}

	/**
	 * 有償：ポイント外商品購入設定<br>
	 * <br>
	 * 有償：ポイント外商品購入を引数で設定する<br>
	 * <br>
	 * @param goodsPurchaseYen
	 */
	public void setGoodsPurchaseYen(BigDecimal goodsPurchaseYen){
		this.goodsPurchaseYen = goodsPurchaseYen;
	}

	/**
	 * 無償：前受無償ポイント取得<br>
	 * <br>
	 * 無償：前受無償ポイントを戻り値で返却する<br>
	 * <br>
	 * @return freeBeforePoint
	 */
	public BigDecimal getFreeBeforePoint(){
		return freeBeforePoint;
	}

	/**
	 * 無償：前受無償ポイント設定<br>
	 * <br>
	 * 無償：前受無償ポイントを引数で設定する<br>
	 * <br>
	 * @param freeBeforePoint
	 */
	public void setFreeBeforePoint(BigDecimal freeBeforePoint){
		this.freeBeforePoint = freeBeforePoint;
	}

	/**
	 * 無償：購入無償ポイント取得<br>
	 * <br>
	 * 無償：購入無償ポイントを戻り値で返却する<br>
	 * <br>
	 * @return freePurchasePoint
	 */
	public BigDecimal getFreePurchasePoint(){
		return freePurchasePoint;
	}

	/**
	 * 無償：購入無償ポイント設定<br>
	 * <br>
	 * 無償：購入無償ポイントを引数で設定する<br>
	 * <br>
	 * @param freePurchasePoint
	 */
	public void setFreePurchasePoint(BigDecimal freePurchasePoint){
		this.freePurchasePoint = freePurchasePoint;
	}

	/**
	 * 無償：利用無償ポイント取得<br>
	 * <br>
	 * 無償：利用無償ポイントを戻り値で返却する<br>
	 * <br>
	 * @return freeUsePoint
	 */
	public BigDecimal getFreeUsePoint(){
		return freeUsePoint;
	}

	/**
	 * 無償：利用無償ポイント設定<br>
	 * <br>
	 * 無償：利用無償ポイントを引数で設定する<br>
	 * <br>
	 * @param freeUsePoint
	 */
	public void setFreeUsePoint(BigDecimal freeUsePoint){
		this.freeUsePoint = freeUsePoint;
	}


	/**
	 * 日次作成フラグ<br>
	 * <br>
	 * 日次作成フラグを戻り値で返却する<br>
	 * <br>
	 * @return dailyBatchFlg
	 */
	public String getDailyBatchFlg(){
		return dailyBatchFlg;
	}

	/**
	 * 日次作成フラグ設定<br>
	 * <br>
	 * 日次作成フラグを引数で設定する<br>
	 * <br>
	 * @param dailyBatchFlg
	 */
	public void setDailyBatchFlg(String dailyBatchFlg){
		this.dailyBatchFlg = dailyBatchFlg;
	}



	/**
	 * 請求書番号取得<br>
	 * <br>
	 * 請求書番号を戻り値で返却する<br>
	 * <br>
	 * @return billNo
	 */
	public String getBillNo(){
		return billNo;
	}

	/**
	 * 請求書番号設定<br>
	 * <br>
	 * 請求書番号を引数で設定する<br>
	 * <br>
	 * @param billNo
	 */
	public void setBillNo(String billNo){
		this.billNo = billNo;
	}

	/**
	 * 請求書受講者番号取得<br>
	 * <br>
	 * 請求書受講者番号を戻り値で返却する<br>
	 * <br>
	 * @return billStudentId
	 */
	public String getBillStudentId(){
		return billStudentId;
	}

	/**
	 * 請求書受講者番号設定<br>
	 * <br>
	 * 請求書受講者番号を引数で設定する<br>
	 * <br>
	 * @param billStudentId
	 */
	public void setBillStudentId(String billStudentId){
		this.billStudentId = billStudentId;
	}

	/**
	 * 明細番号取得<br>
	 * <br>
	 * 明細番号を戻り値で返却する<br>
	 * <br>
	 * @return billDetailsNo
	 */
	public String getBillDetailsNo(){
		return billDetailsNo;
	}

	/**
	 * 明細番号設定<br>
	 * <br>
	 * 明細番号を引数で設定する<br>
	 * <br>
	 * @param billDetailsNo
	 */
	public void setBillDetailsNo(String billDetailsNo){
		this.billDetailsNo = billDetailsNo;
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
