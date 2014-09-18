package com.naikara_talk.util;

import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.naikara_talk.sessiondata.SessionData;
import com.naikara_talk.sessiondata.SessionUser;

/**
 * セッション情報操作ユーティリティ
 * @author nos
 *
 */
public class SessionDataUtil {

	/**
	 * 現在のセッションIDを取得する
	 * @return セッションID
	 */
	public static String getSessionId() {
		// セッションIDを返却
		return ServletActionContext.getRequest().getSession().getId();
	}

	/**
	 * 指定のSessionData型でセッションデータを登録する
	 * @param sessionData
	 */
	public static void setSessionData(SessionData sessionData){
		ServletActionContext.getRequest().getSession()
			.setAttribute(sessionData.getClass().toString(), (Object)sessionData);
	}

	/**
	 * 指定のSessionData型のセッションデータを取り出す
	 * @param key セッションの（このユーティリティではClass.class.toString()を設定）
	 * @return
	 */
	public static SessionData getSessionData(String key){
		return (SessionData)ServletActionContext.getRequest().getSession().getAttribute(key);
	}

	/**
	 * 指定のSessionData型のセッションデータをクリアする
	 * @param key セッションの（このユーティリティではClass.class.toString()を設定）
	 */
	public static void clearSessionData(String key){
		ServletActionContext.getRequest().getSession().setAttribute(key, null);
	}

}
