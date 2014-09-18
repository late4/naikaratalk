package com.naikara_talk.dto;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DTOクラス<br>
 * <b>クラス名称　　　:</b>ポイント有効期限クラス<br>
 * <b>クラス概要　　　:</b>ポイント有効期限DTO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/06/03 TECS 新規作成
 */
public class PointExpirationDto extends AbstractDto{

	String expDate;          //(ポイント有効期限)
	private int returnCode;  //リターンコード


	/**
	 * ポイント有効期限取得<br>
	 * <br>
	 * ポイント有効期限を戻り値で返却する<br>
	 * <br>
	 * @return expDate
	 */
	public String getAge(){
		return expDate;
	}

	/**
	 * ポイント有効期限設定<br>
	 * <br>
	 * ポイント有効期限を引数で設定する<br>
	 * <br>
	 * @param expDate
	 */
	public void setAge(String expDate){
		this.expDate = expDate;
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
