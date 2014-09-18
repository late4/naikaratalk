package com.naikara_talk.logic;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.naikara_talk.dao.StudentMailExistChkDao;
import com.naikara_talk.exception.NaiException;


/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DAOクラス<br>
 * <b>クラス名称　　　:</b>メール重複チェッククラス<br>
 * <b>クラス概要　　　:</b>メール重複チェックDAO<br>
 * <br>
 * <b>著作権　　　　　:</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/05/24 TECS 新規作成
 */
public class StudentMailExistChkLogic {

	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public StudentMailExistChkLogic(Connection con) {
		this.conn = con;
	}

	/**
	 * 共通部品：受講者マスタのメール存在チェック<br>
	 * <br>
	 * 受講者マスタにメールが存在しているかチェックする<br>
	 * <br>
	 * @param mailAddress
	 * @param yyyyMMdd
	 * @param studentId
	 * @return int:result
	 * @throws NaiException
	 */
	public int getExist(String mailAddress, String yyyyMMdd, String studentId) throws NaiException {

		//戻り値とリターンコードの初期化
		int result = -1; //判定結果

		//引数のパラメータチェック
		//メールアドレスの書式
		String mailAddPattern =
				"([a-zA-Z0-9][a-zA-Z0-9_.+\\-]*)@(([a-zA-Z0-9][a-zA-Z0-9_\\-]+\\.)+[a-zA-Z]{2,6})";
		if(!mailAddress.matches(mailAddPattern)){
			return result;
		}
		//日付の書式
		SimpleDateFormat df = (SimpleDateFormat)DateFormat.getDateInstance();
		df.applyPattern("yyyyMMdd");
		df.setLenient(false);
		try{
			df.parse(yyyyMMdd);
		}catch(Exception ex) {
			return result;
		}

		//データ取得処理
		StudentMailExistChkDao tcDao = new StudentMailExistChkDao(this.conn);

//        // ログインユーザID
//		String userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();

		return result = tcDao.count(mailAddress, yyyyMMdd, studentId);

	}

}
