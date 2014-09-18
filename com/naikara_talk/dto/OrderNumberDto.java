package com.naikara_talk.dto;


/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DTOクラス<br>
 * <b>クラス名称　　　:</b>採番シーケンスクラス<br>
 * <b>クラス概要　　　:</b>採番シーケンスDTO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/06/03 TECS 新規作成
 */
public class OrderNumberDto extends AbstractDto{

	private String orderNumber; //採番シーケンス
	private int returnCode;     //リターンコード

	/**
	 * 採番シーケンス取得<br>
	 * <br>
	 * 採番シーケンスを戻り値で返却する<br>
	 * <br>
	 * @return orderNumber
	 */
	public String getOrderNumber(){
		return orderNumber;
	}

	/**
	 * 採番シーケンス設定<br>
	 * <br>
	 * 採番シーケンスを引数で設定する<br>
	 * <br>
	 * @param orderNumber
	 */
	public void setOrderNumber(String orderNumber){
		this.orderNumber = orderNumber;
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
