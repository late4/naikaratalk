package com.naikara_talk.dto;

import java.math.BigDecimal;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DTOクラス<br>
 * <b>クラス名称　　　:</b>ポイント引当テーブルクラス<br>
 * <b>クラス概要　　　:</b>ポイント引当テーブルDTO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/01 TECS 新規作成
 */
public class PointProvisionTrnListDto extends AbstractDto{

	private String rsvNoPurchaseId;      //予約No／購入ID
	private int consSeq;                 //連番
	private String teacherId;            //講師ID
	private String lessonDt;             //レッスン日
	private String lessonTmCd;           //レッスン時刻コード
	private String courseCd;             //コースコード
	private String ownId;                //所有ID
	private String compensationFreeKbn;  //有償無償区分
	private BigDecimal usePoint;         //利用ポイント

	private int returnCode;              //リターンコード

	/**
	 * 予約No／購入ID取得<br>
	 * <br>
	 * 予約No／購入IDを戻り値で返却する<br>
	 * <br>
	 * @return rsvNoPurchaseId
	 */
	public String getRsvNoPurchaseId(){
		return rsvNoPurchaseId;
	}

	/**
	 * 予約No／購入ID設定<br>
	 * <br>
	 * 予約No／購入IDを引数で設定する<br>
	 * <br>
	 * @param rsvNoPurchaseId
	 */
	public void setRsvNoPurchaseId(String rsvNoPurchaseId){
		this.rsvNoPurchaseId = rsvNoPurchaseId;
	}

	/**
	 * 連番取得<br>
	 * <br>
	 * 連番を戻り値で返却する<br>
	 * <br>
	 * @return consSeq
	 */
	public int getConsSeq(){
		return consSeq;
	}

	/**
	 * 連番設定<br>
	 * <br>
	 * 連番を引数で設定する<br>
	 * <br>
	 * @param consSeq
	 */
	public void setConsSeq(int consSeq){
		this.consSeq = consSeq;
	}

	/**
	 * 講師ID取得<br>
	 * <br>
	 * 講師IDを戻り値で返却する<br>
	 * <br>
	 * @return teacherId
	 */
	public String getTeacherId(){
		return teacherId;
	}

	/**
	 * 講師ID設定<br>
	 * <br>
	 * 講師IDを引数で設定する<br>
	 * <br>
	 * @param teacherId
	 */
	public void setTeacherId(String teacherId){
		this.teacherId = teacherId;
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
	 * コースコード取得<br>
	 * <br>
	 * コースコードを戻り値で返却する<br>
	 * <br>
	 * @return courseCd
	 */
	public String getCourseCd(){
		return courseCd;
	}

	/**
	 * コースコード設定<br>
	 * <br>
	 * コースコードを引数で設定する<br>
	 * <br>
	 * @param courseCd
	 */
	public void setCourseCd(String courseCd){
		this.courseCd = courseCd;
	}

	/**
	 * 所有ID取得<br>
	 * <br>
	 * 所有IDを戻り値で返却する<br>
	 * <br>
	 * @return ownId
	 */
	public String getOwnId(){
		return ownId;
	}

	/**
	 * 所有ID設定<br>
	 * <br>
	 * 所有IDを引数で設定する<br>
	 * <br>
	 * @param ownId
	 */
	public void setOwnId(String ownId){
		this.ownId = ownId;
	}

	/**
	 * 有償無償区分取得<br>
	 * <br>
	 * 有償無償区分を戻り値で返却する<br>
	 * <br>
	 * @return compensationFreeKbn
	 */
	public String getCompensationFreeKbn(){
		return compensationFreeKbn;
	}

	/**
	 * 有償無償区分設定<br>
	 * <br>
	 * 有償無償区分を引数で設定する<br>
	 * <br>
	 * @param compensationFreeKbn
	 */
	public void setCompensationFreeKbn(String compensationFreeKbn){
		this.compensationFreeKbn = compensationFreeKbn;
	}

	/**
	 * 利用ポイント取得<br>
	 * <br>
	 * 利用ポイントを戻り値で返却する<br>
	 * <br>
	 * @return usePoint
	 */
	public BigDecimal getUsePoint(){
		return usePoint;
	}

	/**
	 * 利用ポイント設定<br>
	 * <br>
	 * 利用ポイントを引数で設定する<br>
	 * <br>
	 * @param usePoint
	 */
	public void setUsePoint(BigDecimal usePoint){
		this.usePoint = usePoint;
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
