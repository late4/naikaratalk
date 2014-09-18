package com.naikara_talk.dto;

import java.math.BigDecimal;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DTOクラス<br>
 * <b>クラス名称　　　:</b>ポイント引当・解除クラス<br>
 * <b>クラス概要　　　:</b>ポイント引当・解除DTO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/09 TECS 新規作成
 */
public class LessonReserveCancelResultListDto extends AbstractDto{

	private String rsvNoPurchaseId;      //予約No／購入ID
	private String teacherId;            //講師ID
	private String lessonDt;             //レッスン日
	private String lessonTmCd;           //レッスン時刻コード
	private String courseCd;             //コースコード
	private BigDecimal paymentUsePoint;  //有償利用ポイント
	private BigDecimal freeUsePoint;     //無償利用ポイント
	private int returnCode;              //リターンコード
	private String reserveCancelFlg;     //予約又はキャンセルを判断するフラグ


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
	 * レッスン日／商品の購入日取得<br>
	 * <br>
	 * レッスン日／商品の購入日を戻り値で返却する<br>
	 * <br>
	 * @return lessonDt
	 */
	public String getLessonDt(){
		return lessonDt;
	}

	/**
	 * レッスン日／商品の購入日設定<br>
	 * <br>
	 * レッスン日／商品の購入日を引数で設定する<br>
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
	 * 有償利用ポイント取得<br>
	 * <br>
	 * 有償利用ポイントを戻り値で返却する<br>
	 * <br>
	 * @return paymentUsePoint
	 */
	public BigDecimal getPaymentUsePoint(){
		return paymentUsePoint;
	}

	/**
	 * 有償利用ポイント設定<br>
	 * <br>
	 * 有償利用ポイントを引数で設定する<br>
	 * <br>
	 * @param paymentUsePoint
	 */
	public void setPaymentUsePoint(BigDecimal paymentUsePoint){
		this.paymentUsePoint = paymentUsePoint;
	}

	/**
	 * 無償利用ポイント取得<br>
	 * <br>
	 * 無償利用ポイントを戻り値で返却する<br>
	 * <br>
	 * @return freeUsePoint
	 */
	public BigDecimal getFreeUsePoint(){
		return freeUsePoint;
	}

	/**
	 * 無償利用ポイント設定<br>
	 * <br>
	 * 無償利用ポイントを引数で設定する<br>
	 * <br>
	 * @param freeUsePoint
	 */
	public void setFreeUsePoint(BigDecimal freeUsePoint){
		this.freeUsePoint = freeUsePoint;
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

	/**
	 * 予約又はキャンセルを判断するフラグ取得<br>
	 * <br>
	 * 予約又はキャンセルを判断するフラグを戻り値で返却する<br>
	 * <br>
	 * @return reserveCancelFlg
	 */
	public String getReserveCancelFlg(){
		return reserveCancelFlg;
	}

	/**
	 * 予約又はキャンセルを判断するフラグ設定<br>
	 * <br>
	 * 予約又はキャンセルを判断するフラグを引数で設定する<br>
	 * <br>
	 * @param reserveCancelFlg
	 */
	public void setReserveCancelFlg(String reserveCancelFlg){
		this.reserveCancelFlg = reserveCancelFlg;
	}

}
