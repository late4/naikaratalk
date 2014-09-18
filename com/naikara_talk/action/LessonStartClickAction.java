package com.naikara_talk.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.lessonservice.util.NaikaraLessonConstants;
import com.naikara_talk.model.LessonLauncherModel;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.service.LessonLauncherService;
import com.naikara_talk.sessiondata.SessionLesson;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称 :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>レッスン画面起動<br>
 * <b>クラス名称 :</b>レッスン開始<br>
 * <b>クラス概要 :</b>レッスン開始ボタン押下Action<br>
 * <br>
 * <b>著作権 :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴 :</b>2014/01/15 TECS 新規作成
 */
public class LessonStartClickAction extends LessonActionSupport {

	private static final long serialVersionUID = 1L;

	private String jst = "(JST)";
	private String local = "(Local)";

	/**
	 * STARTボタンを押下する<br>
	 * <br>
	 *
	 * @param なし
	 * <br>
	 * @return String TEACHER OR STUDENT<br>
	 * @exception NaiException
	 */
	protected String requestService() throws NaiException {

		// ユーザ情報を取得する
		String role = ((SessionUser)SessionDataUtil.getSessionData(SessionUser.class.toString())).getRole();
		String userId = ((SessionUser)SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();

		if (StringUtils.equals(role,  SessionUser.ROLE_TEACHER)) {
			// 開始ログ
			log.info(NaikaraStringUtil.unionProcesslog("レッスン講師START処理"));
		} else {
			// 開始ログ
			log.info(NaikaraStringUtil.unionProcesslog("レッスン受講者START処理"));
		}

		// セッションIDを取得
    	setSessionId(SessionDataUtil.getSessionId());

    	LessonLauncherModel model = new LessonLauncherModel();

    	if (StringUtils.equals(role,  SessionUser.ROLE_TEACHER)) {
    		// 講師の場合のモデルデータを設定
			model.setRole(role);
			model.setTeacherId(userId);
			model.setTeacherSessionId(sessionId);
		} else {
			// 生徒の場合のモデルデータを設定
			model.setRole(role);
			model.setStudentId(userId);
			model.setStudentSessionId(sessionId);
		}

		// レッスン開始のサービスを起動します。
		LessonLauncherService service = new LessonLauncherService();

		List<LessonLauncherModel> list = service.getExecutableLesson(model);

		// レッスン可能なものがあるかチェック
		if(list.isEmpty()){
			// 受講可能なレッスンはありません。
			try{
		    	if (StringUtils.equals(role,  SessionUser.ROLE_TEACHER)) {
		    		this.message = getMessage("ET0057", new String[] { "",  "" });
		    		setActionName(NaikaraTalkConstants.TEACHER_MY_PAGE_LOAD);
		    	} else {
					this.message = getMessage("EC0057", new String[] { "",  "" });
					setActionName(NaikaraTalkConstants.STUDENT_MY_PAGE_LOAD);
		    	}
		    	// SessionLessonのクリア
				SessionDataUtil.clearSessionData(SessionLesson.class.toString());
				return "notexist";
			} catch (Exception e) {
				throw new NaiException(e);
			}
		}

		/** レッスン対象のモデルを設定(複数件取得されたときは先の時間のもの) */
		list.get(0).setRole(role);
		list.get(0).setUserId(userId);
		setModel(list.get(0));

		// パラメータを画面に渡す
		// 予約番号
		this.reservationNo = list.get(0).getReservationNo();

		if (StringUtils.equals(role,  SessionUser.ROLE_TEACHER)) {
			// 講師の場合
			// 各パラメータを用意する
			Map<String, Object> map = this.setTeacherParameter(list.get(0));
			// 講師レッスン状態設定
			list.get(0).setTeacherStartDttm(this.setTeacherStartDttm(map));
			// 遷移先画面を指定する
			return NaikaraActionSupport.TEACHER;
		} else {
			// 生徒の場合
			// 各パラメータを用意する
			Map<String, Object> map = this.setStudentParameter(list.get(0));
			// 受講者レッスン状態設定
			list.get(0).setStudentStartDttm(this.setStudentStartDttm(map));
			// 遷移先画面を指定する
			return NaikaraActionSupport.STUDENT;
		}
	}

	/**
	 * 各種のパラメータを作成する（受講者）<br>
	 * <br>
	 *
	 * @param レッスン開始処理モデル
	 * <br>
	 * @return Map レッスン情報とコース情報<br>
	 * @exception NaiException
	 */
	@SuppressWarnings("unchecked")
	private Map<String, Object> setStudentParameter(LessonLauncherModel model) throws NaiException {
		// レッスン開始のサービスを起動します。
		LessonLauncherService service = new LessonLauncherService();

		// 予約番号でレッスン情報とコース情報を取得する
		Map<String, Object> lessonInfoMap = new HashMap<String, Object>();
		// 予約番号
		lessonInfoMap.put("reservationNo", model.getReservationNo());
		// レッスン情報とコース情報を取得する
		lessonInfoMap = (Map<String, Object>)service.getLessonInfoStudent(lessonInfoMap);

		// 各パラメータを用意する
		// TOKBOXのAPIキーを取得する
		CodeManagMstCache cache = CodeManagMstCache.getInstance();
		// TOKBOXのAPIキー
		int apiKey = Integer.parseInt(cache.decode(NaikaraTalkConstants.CODE_CATEGORY_TOXBOX_INFO, NaikaraTalkConstants.TOXBOX_APIKEY));
		// TOKBOXセッションID
		String tokboxSessionId = (String)lessonInfoMap.get("tokboxSessionId");
		// TOKBOXトークンID
		String tokboxTokenId = (String)lessonInfoMap.get("tokboxTokenId");
		// 担当講師ニックネーム
		String teacherNickNm = (String)lessonInfoMap.get("teacherNickNm");
		// 受講者ニックネーム
		String studentNickNm = (String)lessonInfoMap.get("studentNickNm");
		// コースネーム（日本語）
		String courseJnm = NaikaraStringUtil.unionString4((String)lessonInfoMap.get("bigClassificationJnm"),
							(String)lessonInfoMap.get("middleClassificationJnm"),(String)lessonInfoMap.get("smallClassificationJnm"), (String)lessonInfoMap.get("courseJnm"));
		// レッスン日程
		String lessonSchedule = NaikaraStringUtil.unionName(NaikaraStringUtil.converToYYYY_MM_DD((String)lessonInfoMap.get("lessonDt")),(String)lessonInfoMap.get("lessonTmNm"));
		// 現在時刻
		String nowTime = DateUtil.getSysDateTimeNoSplit();
		// 簡易説明
		String courseSimpleExplanation = (String)lessonInfoMap.get("courseSimpleExplanation");
		// 添付付き有無フラグ
		String attachmentFlg = (String)lessonInfoMap.get("attachmentFlg");
		// 講師ID
		String teacherId = (String)lessonInfoMap.get("teacherId");
		// 受講者ID
		String studentId = (String)lessonInfoMap.get("studentId");
		// レッスン日
		String lessonDt = (String)lessonInfoMap.get("lessonDt");
		// レッスン時刻名
		String lessonTmNm = (String)lessonInfoMap.get("lessonTmNm");

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
		// レッスン日程
		model.setLessonSchedule(lessonSchedule);
		// 現在時刻
		model.setNowTime(nowTime);
		// 簡易説明
		model.setCourseMemo(courseSimpleExplanation);
		// 添付付き有無フラグ
		model.setAttachmentFlg(attachmentFlg);

		if (lessonInfoMap.get("lessonPDF") != null) {
			// レッスンファイル有無：あり
			model.setHasDuringLessons(true);
		} else {
			// レッスンファイル有無：なし
			model.setHasDuringLessons(false);
		}

        // パラメータをセッションに渡す
		SessionLesson sessionData = null;
		if (SessionDataUtil.getSessionData(SessionLesson.class.toString()) != null) {
			if (model.getReservationNo().equals(((SessionLesson)SessionDataUtil.getSessionData(SessionLesson.class.toString())).getReservationNo())) {
				sessionData = ((SessionLesson)SessionDataUtil.getSessionData(SessionLesson.class.toString()));
			} else {
				// SessionLessonのクリア
				SessionDataUtil.clearSessionData(SessionLesson.class.toString());
				sessionData = new SessionLesson();
			}
		} else {
			sessionData = new SessionLesson();
		}
        // 予約番号
        sessionData.setReservationNo(model.getReservationNo());
        // TOKBOXのAPIキー
        sessionData.setTokboxApiKey(apiKey);
        // TOKBOXセッションID
        sessionData.setTokboxSessionId(tokboxSessionId);
        // TOKBOXトークンID
        sessionData.setTokboxTokenId(tokboxTokenId);
        // 担当講師ニックネーム
        sessionData.setTeacherNickNm(teacherNickNm);
        // 受講者ニックネーム
        sessionData.setStudentNickNm(studentNickNm);
        // コースネーム（日本語）
        sessionData.setCourseJnm(courseJnm);
        // レッスン日程
        sessionData.setLessonSchedule(lessonSchedule);
        // 簡易説明
        sessionData.setCourseSimpleExplanation(courseSimpleExplanation);
        // 添付付き有無フラグ
        sessionData.setAttachmentFlg(attachmentFlg);
        // 講師ID
        sessionData.setTeacherId(teacherId);
        // 受講者ID
        sessionData.setStudentId(studentId);
        // レッスン日
        sessionData.setLessonDt(lessonDt);
        // レッスン時刻名
        sessionData.setLessonTmNm(lessonTmNm);
        // 内容をセッションに反映する
        SessionDataUtil.setSessionData(sessionData);

		// チャット履歴（マイページ）
		if (sessionData.getChatHistoryMyPage() != null && !NaikaraTalkConstants.BRANK.equals(sessionData.getChatHistoryMyPage())) {
			model.setChatHistory(sessionData.getChatHistoryMyPage());
		}

		lessonInfoMap.put("nowTime", nowTime);
		return lessonInfoMap;
	}


	/**
	 * 各種のパラメータを作成する（講師）<br>
	 * <br>
	 *
	 * @param レッスン開始処理モデル
	 * <br>
	 * @return Map レッスン情報とコース情報<br>
	 * @exception NaiException
	 */
	@SuppressWarnings("unchecked")
	private Map<String, Object> setTeacherParameter(LessonLauncherModel model) throws NaiException {
		// レッスン開始のサービスを起動します。
		LessonLauncherService service = new LessonLauncherService();

		// 予約番号でレッスン情報とコース情報を取得する
		Map<String, Object> lessonInfoMap = new HashMap<String, Object>();
		// 予約番号
		lessonInfoMap.put("reservationNo", model.getReservationNo());
		// レッスン情報とコース情報を取得する
		lessonInfoMap = (Map<String, Object>)service.getLessonInfoTeacher(lessonInfoMap);

		// 各パラメータを用意する
		// TOKBOXのAPIキーを取得する
		CodeManagMstCache cache = CodeManagMstCache.getInstance();
		// TOKBOXのAPIキー
		int apiKey = Integer.parseInt(cache.decode(NaikaraTalkConstants.CODE_CATEGORY_TOXBOX_INFO, NaikaraTalkConstants.TOXBOX_APIKEY));
		// TOKBOXセッションID
		String tokboxSessionId = (String)lessonInfoMap.get("tokboxSessionId");
		// TOKBOXトークンID
		String tokboxTokenId = (String)lessonInfoMap.get("tokboxTokenId");
		// 担当講師ニックネーム
		String teacherNickNm = (String)lessonInfoMap.get("teacherNickNm");
		// 受講者ニックネーム
		String studentNickNm = (String)lessonInfoMap.get("studentNickNm");
		// コースネーム（日本語）
		String courseJnm = NaikaraStringUtil.unionString4((String)lessonInfoMap.get("bigClassificationJnm"),
							(String)lessonInfoMap.get("middleClassificationJnm"),(String)lessonInfoMap.get("smallClassificationJnm"), (String)lessonInfoMap.get("courseJnm"));
		// コースネーム（英語）
		String courseEnm = NaikaraStringUtil.unionString4((String)lessonInfoMap.get("bigClassificationEnm"),
							(String)lessonInfoMap.get("middleClassificationEnm"),(String)lessonInfoMap.get("smallClassificationEnm"), (String)lessonInfoMap.get("courseEnm"));
		// レッスン日程
		String lessonSchedule = NaikaraStringUtil.converToYYYY_MM_DD((String)lessonInfoMap.get("lessonDt"));
		// 現在時刻
		String nowTime = DateUtil.getSysDateTimeNoSplit();
		// 添付付き有無フラグ
		String attachmentFlg = (String)lessonInfoMap.get("attachmentFlg");
		// 講師ID
		String teacherId = (String)lessonInfoMap.get("teacherId");
		// 受講者ID
		String studentId = (String)lessonInfoMap.get("studentId");
		// レッスン日
		String lessonDt = (String)lessonInfoMap.get("lessonDt");
		// レッスン時刻名
		String lessonTmNm = (String)lessonInfoMap.get("lessonTmNm");
		// レッスン現地時刻名
		String localLessonTmNm = (String)lessonInfoMap.get("localLessonTmNm");
		// コメント
		String commentTo = (String)lessonInfoMap.get("commentTo");

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

		if (lessonInfoMap.get("lessonPDF") != null) {
			// レッスンファイル有無
			model.setHasDuringLessons(true);
		} else {
			// レッスンファイル有無
			model.setHasDuringLessons(false);
		}

        // パラメータをセッションに渡す
		SessionLesson sessionData = null;
		if (SessionDataUtil.getSessionData(SessionLesson.class.toString()) != null) {
			if (model.getReservationNo().equals(((SessionLesson)SessionDataUtil.getSessionData(SessionLesson.class.toString())).getReservationNo())) {
				sessionData = ((SessionLesson)SessionDataUtil.getSessionData(SessionLesson.class.toString()));
			} else {
				// SessionLessonのクリア
				SessionDataUtil.clearSessionData(SessionLesson.class.toString());
				sessionData = new SessionLesson();
			}
		} else {
			sessionData = new SessionLesson();
		}
        // 予約番号
        sessionData.setReservationNo(model.getReservationNo());
        // TOKBOXのAPIキー
        sessionData.setTokboxApiKey(apiKey);
        // TOKBOXセッションID
        sessionData.setTokboxSessionId(tokboxSessionId);
        // TOKBOXトークンID
        sessionData.setTokboxTokenId(tokboxTokenId);
        // 担当講師ニックネーム
        sessionData.setTeacherNickNm(teacherNickNm);
        // 受講者ニックネーム
        sessionData.setStudentNickNm(studentNickNm);
        // コースネーム（日本語）
        sessionData.setCourseJnm(courseJnm);
        // コースネーム（英語）
        sessionData.setCourseEnm(courseEnm);
        // レッスン日程
        sessionData.setLessonSchedule(lessonSchedule);
        // 添付付き有無フラグ
        sessionData.setAttachmentFlg(attachmentFlg);
        // 講師ID
        sessionData.setTeacherId(teacherId);
        // 受講者ID
        sessionData.setStudentId(studentId);
        // レッスン日
        sessionData.setLessonDt(lessonDt);
        // レッスン時刻名
        sessionData.setLessonTmNm(lessonTmNm);
        // レッスン現地時刻名
        sessionData.setLocalLessonTmNm(localLessonTmNm);
        // コメント
        sessionData.setCommentTo(commentTo);
        // 内容をセッションに反映する
        SessionDataUtil.setSessionData(sessionData);

        // チャット履歴（マイページ）
        if (sessionData.getChatHistoryMyPage() != null && !NaikaraTalkConstants.BRANK.equals(sessionData.getChatHistoryMyPage())) {
        	model.setChatHistory(sessionData.getChatHistoryMyPage());
        }

		lessonInfoMap.put("nowTime", nowTime);
		return lessonInfoMap;
	}

	/**
	 * 受講者レッスン開始を設定する<br>
	 * <br>
	 *
	 * @param 受講者レッスン開始
	 * <br>
	 * @exception NaiException
	 */
	private String setStudentStartDttm(Map<String, Object> map) throws NaiException {
		// サービス生成
		LessonLauncherService service = new LessonLauncherService();

		// レッスン開始、再入場のときは設定しません。
		if (map.get("studentStartDttm") == null || NaikaraTalkConstants.BRANK.equals(map.get("studentStartDttm"))) {
			Map<String, Object> statusMap = new HashMap<String, Object>();
			statusMap.put("event", NaikaraLessonConstants.EVENT_START);
			statusMap.put("reservationNo", model.getReservationNo());
			statusMap.put("userId", model.getUserId());
			statusMap.put("lessonTmNm", map.get("lessonTmNm"));
			statusMap.put("lessonDt", map.get("lessonDt"));
			service.setStatusStudent(statusMap);
			// 画面を制御するため値を設定する
			return (String)map.get("nowTime");
		} else {
			// 画面を制御するため値を設定する
			return (String)map.get("studentStartDttm");
		}
	}

	/**
	 * 講師レッスン開始を設定する<br>
	 * <br>
	 *
	 * @param 講師レッスン開始
	 * <br>
	 * @return String SUCCESS<br>
	 * @exception NaiException
	 */
	private String setTeacherStartDttm(Map<String, Object> map) throws NaiException {
		// サービス生成
		LessonLauncherService service = new LessonLauncherService();

		// レッスン開始、再入場のときは設定しません。
		if (map.get("teacherStartDttm") == null || NaikaraTalkConstants.BRANK.equals(map.get("teacherStartDttm"))) {
			Map<String, Object> statusMap = new HashMap<String, Object>();
			statusMap.put("event", NaikaraLessonConstants.EVENT_START);
			statusMap.put("reservationNo", model.getReservationNo());
			statusMap.put("userId", model.getUserId());
			statusMap.put("lessonTmNm", map.get("lessonTmNm"));
			statusMap.put("lessonDt", map.get("lessonDt"));
			service.setStatusTeacher(statusMap);
			// 画面を制御するため値を設定する
			return (String)map.get("nowTime");
		} else {
			// 画面を制御するため値を設定する
			return (String)map.get("teacherStartDttm");
		}
	}

	/** メッセージ変数	*/
	private String message;

	/** アクションネーム */
	private String actionName = "";

	/** レッスン実施のモデル */
	private LessonLauncherModel model;

	/** セッションID */
	private String sessionId;

    /** 予約番号 */
	private String reservationNo;

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

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

    public void setActionName(String actionName){
    	this.actionName = actionName;
    }
    public String getActionName() {
    	return actionName;
    }

	public void setReservationNo(String reservationNo) {
		this.reservationNo = reservationNo;
	}
	public String getReservationNo() {
		return reservationNo;
	}
}