package com.naikara_talk.dto;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DTOクラス<br>
 * <b>クラス名称　　　:</b>契約期間クラス<br>
 * <b>クラス概要　　　:</b>契約期間DTO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/05/30 TECS 新規作成
 */
public class OrganizationContractListDto extends AbstractDto{

	private String contract; //契約期間
	private int returnCode;  //リターンコード

	/**
	 * 契約期間取得<br>
	 * <br>
	 * 契約期間を戻り値で返却する<br>
	 * <br>
	 * @return contract
	 */
	public String getContract(){
		return contract;
	}

	/**
	 * 契約期間設定<br>
	 * <br>
	 * 契約期間を引数で設定する<br>
	 * <br>
	 * @param contract
	 */
	public void setContract(String contract){
		this.contract = contract;
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