package com.naikara_talk.dto;


/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DTOクラス<br>
 * <b>クラス名称　　　:</b>ローカル・サマータイムクラス<br>
 * <b>クラス概要　　　:</b>ローカル・サマータイムDTO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/05/27 TECS 新規作成
 */
public class LocSumTimeDto extends AbstractDto{

	private String localTimeFromTo;  //ローカルタイム
	private String sumTimeFromTo;    //サマータイム
	private int returnCode;          //リターンコード

	/**
	 * ローカルタイム取得<br>
	 * <br>
	 * ローカルタイムを戻り値で返却する<br>
	 * <br>
	 * @return localTimeFromTo
	 */
	public String getLocalTimeFromTo(){
		return localTimeFromTo;
	}

	/**
	 * ローカルタイム設定<br>
	 * <br>
	 * ローカルタイムを引数で設定する<br>
	 * <br>
	 * @param localTimeFromTo
	 */
	public void setLocalTimeFromTo(String localTimeFromTo){
		this.localTimeFromTo = localTimeFromTo;
	}

	/**
	 * サマータイム取得<br>
	 * <br>
	 * サマータイムを戻り値で返却する<br>
	 * <br>
	 * @return sumTimeFromTo
	 */
	public String getSumTimeFromTo(){
		return sumTimeFromTo;
	}

	/**
	 * サマータイム設定<br>
	 * <br>
	 * サマータイムを引数で設定する<br>
	 * <br>
	 * @param sumTimeFromTo
	 */
	public void setSumTimeFromTo(String sumTimeFromTo){
		this.sumTimeFromTo = sumTimeFromTo;
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
