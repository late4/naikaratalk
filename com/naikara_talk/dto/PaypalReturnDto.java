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
public class PaypalReturnDto extends AbstractDto{

	private int returnCode;     // リターンコード
	private String errorMessage; // エラーメッセージ

	/**
	 * エラーメッセージ取得<br>
	 * <br>
	 * エラーメッセージを戻り値で返却する<br>
	 * <br>
	 * @return errorMessage
	 */
	public String getErrorMessage(){
		return errorMessage;
	}

	/**
	 * エラーメッセージ設定<br>
	 * <br>
	 * エラーメッセージを引数で設定する<br>
	 * <br>
	 * @param errorMessage
	 */
	public void setErrorMessage(String errorMessage){
		this.errorMessage = errorMessage;
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