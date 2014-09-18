package com.naikara_talk.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.lessonservice.util.NaikaraLessonConstants;
import com.naikara_talk.model.LessonLauncherModel;
import com.naikara_talk.service.LessonLauncherService;
import com.naikara_talk.sessiondata.SessionLesson;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称 :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>レッスン画面起動<br>
 * <b>クラス名称 :</b>レッスン受講者<br>
 * <b>クラス概要 :</b>レッスン受講者END処理Action<br>
 * <br>
 * <b>著作権 :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴 :</b>2014/01/15 TECS 新規作成
 */
public class LessonEndClickAction extends LessonActionSupport {

	private static final long serialVersionUID = 1L;

	private String jst = "(JST)";
	private String local = "(Local)";

	/**
	 * ENDボタン押下。<br>
	 * <br>
	 * ENDボタン押下。<br>
	 * <br>
	 *
	 * @param なし
	 * <br>
	 * @return String TEACHER OR STUDENT <br>
	 * @exception NaiException
	 */
	public String requestService() throws NaiException {

		// ユーザ情報を取得する
		String role = ((SessionUser)SessionDataUtil.getSessionData(SessionUser.class.toString())).getRole();
		String userId = ((SessionUser)SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();

		if (StringUtils.equals(role,  SessionUser.ROLE_TEACHER)) {
			// 開始ログ
			log.info(NaikaraStringUtil.unionProcesslog("レッスン講師END処理"));
		} else {
			// 開始ログ
			log.info(NaikaraStringUtil.unionProcesslog("レッスン受講者END処理"));
		}

		// レッスン情報を取得する
		// 予約番号
		String reservationNo = ((SessionLesson) SessionDataUtil.getSessionData(SessionLesson.class.toString())).getReservationNo();
		// レッスン日
		String lessonDt = ((SessionLesson) SessionDataUtil.getSessionData(SessionLesson.class.toString())).getLessonDt();
		// レッスン時刻名
		String lessonTmNm = ((SessionLesson) SessionDataUtil.getSessionData(SessionLesson.class.toString())).getLessonTmNm();

		// セッションIDを取得
    	setSessionId(SessionDataUtil.getSessionId());

		LessonLauncherModel model = new LessonLauncherModel();

    	// 予約番号
    	model.setReservationNo(reservationNo);
    	// ユーザID
    	model.setUserId(userId);
    	if (StringUtils.equals(role,  SessionUser.ROLE_TEACHER)) {
    		// 講師の場合のモデルデータを設定
    		this.setTeacherParameter(model);
			model.setRole(role);
			model.setTeacherId(userId);
			model.setTeacherSessionId(sessionId);
		} else {
			// 生徒の場合のモデルデータを設定
			model.setRole(role);
			model.setStudentId(userId);
			model.setStudentSessionId(sessionId);
		}

		setModel(model);

		// 画面に渡す
		// 予約番号
		this.reservationNo = reservationNo;

		Map<String, Object> statusMap = new HashMap<String, Object>();
		// レッスン日
		statusMap.put("lessonDt", lessonDt);
		// レッスン時刻名
		statusMap.put("lessonTmNm", lessonTmNm);
		if (StringUtils.equals(role,  SessionUser.ROLE_TEACHER)) {
			// 講師の場合
			// 講師レッスン終了を設定する
			this.setTeacherEndDttm(statusMap);
			return NaikaraActionSupport.TEACHER;
		} else {
			// 生徒の場合
			// 受講者レッスン終了を設定する
			this.setStudentEndDttm(statusMap);
	        // SessionLessonのクリア
	        SessionDataUtil.clearSessionData(SessionLesson.class.toString());
			return NaikaraActionSupport.STUDENT;
		}
	}

	/**
	 * 各種のパラメータを作成する（講師）<br>
	 * <br>
	 *
	 * @param レッスン開始処理モデル
	 * <br>
	 * @return なし<br>
	 * @exception NaiException
	 */
	private void setTeacherParameter(LessonLauncherModel model) throws NaiException {

		// 各パラメータを用意する
		// TOKBOXのAPIキー
		int apiKey = 0;
		// TOKBOXセッションID
		String tokboxSessionId = null;
		// TOKBOXトークンID
		String tokboxTokenId = null;
		// 担当講師ニックネーム
		String teacherNickNm = ((SessionLesson) SessionDataUtil.getSessionData(SessionLesson.class.toString())).getTeacherNickNm();
		// 受講者ニックネーム
		String studentNickNm = ((SessionLesson) SessionDataUtil.getSessionData(SessionLesson.class.toString())).getStudentNickNm();
		// コースネーム（日本語）
		String courseJnm = ((SessionLesson) SessionDataUtil.getSessionData(SessionLesson.class.toString())).getCourseJnm();
		// コースネーム（英語）
		String courseEnm = ((SessionLesson) SessionDataUtil.getSessionData(SessionLesson.class.toString())).getCourseEnm();
		// レッスン日程
		String lessonSchedule = ((SessionLesson) SessionDataUtil.getSessionData(SessionLesson.class.toString())).getLessonSchedule();
		// 現在時刻
		String nowTime = DateUtil.getSysDateTimeNoSplit();
		// 添付付き有無フラグ
		String attachmentFlg = ((SessionLesson) SessionDataUtil.getSessionData(SessionLesson.class.toString())).getAttachmentFlg();
		// レッスン時刻名
		String lessonTmNm = ((SessionLesson) SessionDataUtil.getSessionData(SessionLesson.class.toString())).getLessonTmNm();
		// レッスン現地時刻名
		String localLessonTmNm = ((SessionLesson) SessionDataUtil.getSessionData(SessionLesson.class.toString())).getLocalLessonTmNm();
		// コメント
		String commentTo = ((SessionLesson) SessionDataUtil.getSessionData(SessionLesson.class.toString())).getCommentTo();
		// 受講者ID
		String studentId = ((SessionLesson) SessionDataUtil.getSessionData(SessionLesson.class.toString())).getStudentId();

		// パラメータをモデルに渡す
		// TOKBOXのAPIキー
		model.setTokboxApiKey(apiKey);
		// TOKBOXセッションID
		model.setTokboxSessionId(tokboxSessionId);
		// TOKBOXトークンID
		model.setTokboxTokenId(tokboxTokenId);
		// 担当講師ニックネーム
		model.setTeacherNickNm(teacherNickNm);
		// 受講者ニックネーム
		model.setStudentNickNm(studentNickNm);
		// コースネーム（日本語）
		model.setCourseJnm(courseJnm);
		// コースネーム（英語）
		model.setCourseEnm(courseEnm);
		// レッスン日程
		model.setLessonSchedule(lessonSchedule);
        // レッスン時刻名
		model.setLessonTmNm(new StringBuffer(lessonTmNm).append(this.jst).toString());
        // レッスン現地時刻名
		model.setLocalLessonTmNm(new StringBuffer(localLessonTmNm).append(this.local).toString());
		// コメント
		model.setCommentTo(commentTo);
		// 現在時刻
		model.setNowTime(nowTime);
		// 添付付き有無フラグ
		model.setAttachmentFlg(attachmentFlg);
		// レッスンファイル有無：無
		model.setHasDuringLessons(false);
		// 講師START押下時刻
		model.setTeacherStartDttm(nowTime);
		// 講師END押下時刻
		model.setTeacherEndDttm(nowTime);
		// 受講者ID
		model.setStudentId(studentId);
	}

	/**
	 * 受講者レッスン終了を設定する<br>
	 * <br>
	 *
	 * @param 受講者レッスン終了
	 * <br>
	 * @exception NaiException
	 */
	private void setStudentEndDttm(Map<String, Object> map) throws NaiException {
		// レッスン開始のサービスを起動します。
		LessonLauncherService service = new LessonLauncherService();
		Map<String, Object> statusMap = new HashMap<String, Object>();
		statusMap.put("event", NaikaraLessonConstants.EVENT_END);
		statusMap.put("reservationNo", model.getReservationNo());
		statusMap.put("userId", model.getUserId());
		statusMap.put("lessonTmNm", map.get("lessonTmNm"));
		statusMap.put("lessonDt", map.get("lessonDt"));
		// レッスンステータス更新受講者用
		service.setStatusStudent(statusMap);
	}

	/**
	 * 講師レッスン終了を設定する<br>
	 * <br>
	 *
	 * @param 講師レッスン終了
	 * <br>
	 * @exception NaiException
	 */
	private void setTeacherEndDttm(Map<String, Object> map) throws NaiException {
		// レッスン開始のサービスを起動します。
		LessonLauncherService service = new LessonLauncherService();
		Map<String, Object> statusMap = new HashMap<String, Object>();
		statusMap.put("event", NaikaraLessonConstants.EVENT_END);
		statusMap.put("reservationNo", model.getReservationNo());
		statusMap.put("userId", model.getUserId());
		statusMap.put("lessonTmNm", map.get("lessonTmNm"));
		statusMap.put("lessonDt", map.get("lessonDt"));
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