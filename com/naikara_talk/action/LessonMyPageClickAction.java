package com.naikara_talk.action;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.sessiondata.SessionLesson;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称 :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>レッスン画面起動<br>
 * <b>クラス名称 :</b>レッスン受講者<br>
 * <b>クラス概要 :</b>レッスン受講者マイページボタン押下Action<br>
 * <br>
 * <b>著作権 :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴 :</b>2014/01/15 TECS 新規作成
 */
public class LessonMyPageClickAction extends LessonActionSupport {

	private static final long serialVersionUID = 1L;
	/**
	 * マイページボタン押下。<br>
	 * <br>
	 * マイページボタン押下。<br>
	 * <br>
	 *
	 * @param なし
	 * <br>
	 * @return String TEACHER OR STUDENT <br>
	 * @exception NaiException
	 */
	public String requestService() throws NaiException {

		// ユーザ情報を取得する
		String role = ((SessionUser) SessionDataUtil
				.getSessionData(SessionUser.class.toString())).getRole();

		// SessionLessonのクリア
		//SessionDataUtil.clearSessionData(SessionLesson.class.toString());

		// チャット履歴
		if (this.chatHistoryMyPage != null) {
			Pattern p1 = Pattern.compile("\t|\r|\n");
			Matcher m1 = p1.matcher(this.chatHistoryMyPage);
			String str1 = m1.replaceAll("");

			Pattern p2 = Pattern.compile("\"sdt02\">");
			Matcher m2 = p2.matcher(str1);
			String str2 = m2.replaceAll("'sdt02'>");

			Pattern p3 = Pattern.compile("\"sdt01\">");
			Matcher m3 = p3.matcher(str2);
			String str3 = m3.replaceAll("'sdt01'>");

			Pattern p4 = Pattern.compile("\"tcr\">");
			Matcher m4 = p4.matcher(str3);
			String str4 = m4.replaceAll("'tcr'>");

			// パラメータをセッションに渡す
			SessionLesson sessionData = null;
			if (SessionDataUtil.getSessionData(SessionLesson.class.toString()) != null) {
				sessionData = ((SessionLesson)SessionDataUtil.getSessionData(SessionLesson.class.toString()));
			} else {
				sessionData = new SessionLesson();
			}
			// チャット履歴（マイページ）
			sessionData.setChatHistoryMyPage(str4);
			// 内容をセッションに反映する
	        SessionDataUtil.setSessionData(sessionData);
		}

		if (StringUtils.equals(role, SessionUser.ROLE_TEACHER)) {
			// 開始ログ
			log.info(NaikaraStringUtil.unionProcesslog("レッスン講師マイページボタン押下処理"));
			return NaikaraActionSupport.TEACHER;
		} else {
			// 開始ログ
			log.info(NaikaraStringUtil.unionProcesslog("レッスン受講者マイページボタン押下処理"));
			return NaikaraActionSupport.STUDENT;
		}
	}
}