package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称 :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>レッスン画面起動<br>
 * <b>クラス名称 :</b>レッスン受講者<br>
 * <b>クラス概要 :</b>レッスン受講者初期処理Action<br>
 * <br>
 * <b>著作権 :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴 :</b>2014/01/15 TECS 新規作成
 */
public class LessonLoadAction extends LessonActionSupport {

	private static final long serialVersionUID = 1L;

	/**
	 * 画面の初期表示。<br>
	 * <br>
	 * 画面の初期表示。<br>
	 * <br>
	 *
	 * @param なし
	 * <br>
	 * @return String TEACHER OR STUDENT <br>
	 * @exception NaiException
	 */
	public String requestService() throws NaiException {

		// ユーザのセッション情報を取得する
		String role = ((SessionUser)SessionDataUtil.getSessionData(SessionUser.class.toString())).getRole();

		if (StringUtils.equals(role,  SessionUser.ROLE_TEACHER)) {
			// 開始ログ
			log.info(NaikaraStringUtil.unionProcesslog("レッスン講師初期処理"));
			return NaikaraActionSupport.TEACHER;
		} else {
			// 開始ログ
			log.info(NaikaraStringUtil.unionProcesslog("レッスン受講者初期処理"));
			return NaikaraActionSupport.STUDENT;
		}
	}
}