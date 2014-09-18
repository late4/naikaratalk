package com.naikara_talk.logic;

import com.naikara_talk.exception.NaiException;


/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 Logicクラス<br>
 * <b>クラス名称　　　:</b>パスワードチェッククラス<br>
 * <b>クラス概要　　　:</b>パスワードチェックLogic<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/05/24 TECS 新規作成
 */
public class PasswordChkLogic {

	/**
	 * パスワード複雑性チェック<br>
	 * <br>
	 * パスワードの複雑性チェックを行う<br>
	 * <br>
	 * @param password
	 * @param yyyyMMdd
	 * @return int:result
	 * @throws NaiException
	 */
	public int passwordCheck(String password) throws NaiException {

		//戻り値とリターンコードの初期化
		int result = -1; //複雑性の判定結果


		//引数のパラメータチェック
		if(password==null || "".equals(password)){
			return result;
		}
		if(!password.matches("[0-9a-zA-Z]+")){
			return result;
		}


		//パスワードの複雑性チェック
		//桁数チェック(7文字以下はNG)
		if(password.length()<=7){
			return result;
		}
		//桁数チェック(17文字以上はNG)
		if(password.length()>=17){
			return result;
		}
		//半角英字チェック(大文字、又は小文字が1つもない場合はNG)
		if(!password.matches(".*[a-zA-Z]+.*")){
			return result;
		}
		//半角数字が１つもない場合はNG
		if(!password.matches(".*[0-9]+.*")){
			return result;
		}

		result = 0;
		return result;


	}
}
