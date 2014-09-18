package com.naikara_talk.action;

import java.util.HashMap;
import java.util.Map;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.lessonservice.util.NaikaraLessonConstants;
import com.naikara_talk.model.LessonLauncherModel;
import com.naikara_talk.service.LessonLauncherService;
import com.naikara_talk.sessiondata.SessionLesson;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称 :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>レッスン画面起動<br>
 * <b>クラス名称 :</b>レッスン講師<br>
 * <b>クラス概要 :</b>レッスン講師NEXT処理Action<br>
 * <br>
 * <b>著作権 :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴 :</b>2014/01/15 TECS 新規作成
 */
public class LessonNextClickAction extends LessonActionSupport {

	private static final long serialVersionUID = 1L;

	/**
	 * NEXTボタン押下。<br>
	 * <br>
	 * NEXTボタン押下。<br>
	 * <br>
	 *
	 * @param なし
	 * <br>
	 * @return String TEACHER OR STUDENT <br>
	 * @exception NaiException
	 */
	public String requestService() throws NaiException {

		log.info(NaikaraStringUtil.unionProcesslog("レッスン講師NEXT処理"));

		// ユーザ情報を取得する
		String role = ((SessionUser)SessionDataUtil.getSessionData(SessionUser.class.toString())).getRole();
		String userId = ((SessionUser)SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();

		// レッスン情報を取得する
		// 予約番号
		String reservationNo = ((SessionLesson) SessionDataUtil.getSessionData(SessionLesson.class.toString())).getReservationNo();

		// セッションIDを取得
    	setSessionId(SessionDataUtil.getSessionId());

		LessonLauncherModel model = new LessonLauncherModel();

    	// 予約番号
    	model.setReservationNo(reservationNo);
    	// ユーザID
    	model.setUserId(userId);
		model.setRole(role);
		model.setTeacherId(userId);
		model.setTeacherSessionId(sessionId);

		setModel(model);

		// 画面に渡す
		// 予約番号
		this.reservationNo = reservationNo;
		// 予約番号
		this.reservationNo_hid = reservationNo;

		// 講師レッスンNEXTを設定する
		this.setTeacherNextDttm();

        // SessionLessonのクリア
        SessionDataUtil.clearSessionData(SessionLesson.class.toString());

		// 遷移先画面を判断する
		return NaikaraActionSupport.TEACHER;
	}


	/**
	 * 講師レッスンNEXTを設定する<br>
	 * <br>
	 *
	 * @param 講師レッスン終了
	 * <br>
	 * @exception NaiException
	 */
	private void setTeacherNextDttm() throws NaiException {
		// レッスン開始のサービスを起動します。
		LessonLauncherService service = new LessonLauncherService();
		Map<String, Object> statusMap = new HashMap<String, Object>();
		statusMap.put("event", NaikaraLessonConstants.EVENT_NEXT);
		statusMap.put("reservationNo", model.getReservationNo());
		statusMap.put("userId", model.getUserId());
		// レッスンステータス更新講師用
		service.setStatusTeacher(statusMap);
	}

	/** レッスン実施のモデル */
	private LessonLauncherModel model;

	/** セッションID */
	private String sessionId;

	public LessonLauncherModel getModel() {
		return model;
	}
	public void setModel(LessonLauncherModel model) {
		this.model = model;
	}

	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
}