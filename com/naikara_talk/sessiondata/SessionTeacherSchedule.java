package com.naikara_talk.sessiondata;

//import java.util.List;

import com.naikara_talk.model.TeacherScheduleModel;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称 :</b>講師<br>
 * <b>クラス名称       :</b>講師スケジュールのセッション情報クラス<br>
 * <b>クラス概要       :</b>講師スケジュールのセッション情報<br>
 * <br>
 * <b>著作権           :</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴         :</b>2014/06/02 TECS 新規作成
 */

public class SessionTeacherSchedule implements SessionData {

	/** ヘッダの戻るリンク用のSessionKey */
	private static final String HEADER_RETURN_KEY = "SessionTeacherSchedule";

	/** 戻る判定Onフラグ */
	private boolean returnOnFlg;

	private TeacherScheduleModel result;


	/**
	 * このセッションデータのキーを返却する
	 */
	public static String getKey() {
		return HEADER_RETURN_KEY;
	}

	/**
	 * @return processKbn
	 */
	public boolean getReturnOnFlg() {
		return returnOnFlg;
	}

	/**
	 * @param returnOnFlg セットする returnOnFlg
	 */
	public void setReturnOnFlg(boolean returnOnFlg) {
		this.returnOnFlg = returnOnFlg;
	}

	/**
	 * @return result
	 */
	public TeacherScheduleModel getResult() {
		return result;
	}

	/**
	 * @param result セットする result
	 */
	public void setResult(TeacherScheduleModel result) {
		this.result = result;
	}



}