package com.naikara_talk.dto;


/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DTOクラス<br>
 * <b>クラス名称　　　:</b>日付曜日祝日クラス<br>
 * <b>クラス概要　　　:</b>日付曜日祝日DTO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/05/27 TECS 新規作成
 */
public class HolidayListDto extends AbstractDto{

	private String day;        //日付
	private String dayOfWeek;  //曜日
	private int holiday;       //祝日
	private int returnCode;    //リターンコード

	/**
	 * 日付取得<br>
	 * <br>
	 * 日付を戻り値で返却する<br>
	 * <br>
	 * @return day
	 */
	public String getDay(){
		return day;
	}

	/**
	 * 日付設定<br>
	 * <br>
	 * 日付を引数で設定する<br>
	 * <br>
	 * @param day
	 */
	public void setDay(String day){
		this.day = day;
	}

	/**
	 * 曜日取得<br>
	 * <br>
	 * 曜日を戻り値で返却する<br>
	 * <br>
	 * @return dayOfWeek
	 */
	public String getDayOfWeek(){
		return dayOfWeek;
	}

	/**
	 * 曜日設定<br>
	 * <br>
	 * 曜日を引数で設定する<br>
	 * <br>
	 * @param dayOfWeek
	 */
	public void setDayOfWeek(String dayOfWeek){
		this.dayOfWeek = dayOfWeek;
	}

	/**
	 * 祝日取得<br>
	 * <br>
	 * 祝日を戻り値で返却する<br>
	 * <br>
	 * @return holiday
	 */
	public int getHoliday(){
		return holiday;
	}

	/**
	 * 祝日設定<br>
	 * <br>
	 * 祝日を引数で設定する<br>
	 * <br>
	 * @param holiday
	 */
	public void setHoliDay(int holiday){
		this.holiday = holiday;
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