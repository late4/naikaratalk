package com.naikara_talk.action;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.LessonLauncherModel;
import com.naikara_talk.service.LessonPdfDownloadService;
import com.naikara_talk.sessiondata.SessionLesson;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称 :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>レッスン画面起動<br>
 * <b>クラス名称 :</b>レッスン受講者からPDFダウンロード<br>
 * <b>クラス概要 :</b>レッスン受講者からPDFダウンロードAction<br>
 * <br>
 * <b>著作権 :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴 :</b>2014/01/15 TECS 新規作成
 */
public class LessonPdfDownloadAction extends LessonActionSupport {

	private static final long serialVersionUID = 1L;

	private String jst = "(JST)";
	private String local = "(Local)";

	/**
	 * レッスンファイルをダウンロードする<br>
	 * <br>
	 *
	 * @param なし
	 * <br>
	 * @return String TEACHER OR STUDENT<br>
	 * @exception NaiException
	 */
	public String requestService() throws NaiException {

		// ユーザ情報を取得する
		String role = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getRole();
		String userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();

		if (StringUtils.equals(role,  SessionUser.ROLE_TEACHER)) {
			// 開始ログ
			log.info(NaikaraStringUtil.unionProcesslog("レッスン講師からPDFダウンロード"));
		} else {
			// 開始ログ
			log.info(NaikaraStringUtil.unionProcesslog("レッスン受講者からPDFダウンロード"));
		}

		// モデルのインスタンスを作成する
		LessonLauncherModel model = new LessonLauncherModel();

		// ロール
		model.setRole(role);
		// ユーザID
		model.setUserId(userId);
		// 各種のパラメータを作成する
		this.setParameter(model);

		// サービス生成
		LessonPdfDownloadService service = new LessonPdfDownloadService();
		LessonLauncherModel downloadModel = new LessonLauncherModel();

		// ダウンロード
		downloadModel = service.downloadLessonPdf(model);

		// ダウンロード対象が存在しない場合、なにもしない
		if (downloadModel == null || downloadModel.getBlobDuringLessons() == null) {
			// レッスンファイル有無：なし
			model.setHasDuringLessons(false);
		} else {
			// レッスンファイル有無：あり
			model.setHasDuringLessons(true);
		}

		// HIDDEN用パラメータを作成する
		this.reservationNo = model.getReservationNo();

		try {
			// メッセージ
			String msg = null;

			// レッスンファイルがなしの場合
			if (!model.getHasDuringLessons()) {
				if (StringUtils.equals(role, SessionUser.ROLE_TEACHER)) {
					// 講師の場合
					// ダウンロードファイルが存在しない
					msg = getMessage("ET0077", new String[] { "", "" });
					this.addActionMessage(msg);

				} else {
					// 生徒の場合
					// ダウンロードファイルが存在しない
					msg = getMessage("EC0077", new String[] { "", "" });
					this.addActionMessage(msg);
				}
			} else {
				if (StringUtils.equals(role, SessionUser.ROLE_TEACHER)) {
					// 講師の場合
					// ファイルの表示が完了しました。
					msg = getMessage("IT0014", new String[] { "Display of the file", "ファイルの表示" });
					this.addActionMessage(msg);
				} else {
					// 生徒の場合
					// ファイルの表示が完了しました。
					msg = getMessage("IC0014", new String[] { "ファイルの表示", "" });
					this.addActionMessage(msg);
				}
			}
		} catch (Exception e) {
			throw new NaiException(e);
		}

		// 戻り値を設定する
		if (StringUtils.equals(role, SessionUser.ROLE_TEACHER)) {
			// 講師の場合
			return NaikaraActionSupport.TEACHER;
		} else {
			// 生徒の場合
			return NaikaraActionSupport.STUDENT;
		}
	}

	/**
	 * 各種のパラメータを作成する<br>
	 * <br>
	 *
	 * @param なし
	 * <br>
	 * @return String SUCCESS<br>
	 * @exception NaiException
	 */
	private void setParameter(LessonLauncherModel model) throws NaiException {
		// セッションIDを取得
    	setSessionId(SessionDataUtil.getSessionId());

		// セッションから情報を取得する
		// 予約番号
		String reservationNo = ((SessionLesson) SessionDataUtil.getSessionData(SessionLesson.class.toString())).getReservationNo();
		// TOKBOXのAPIキー
		int apiKey = ((SessionLesson) SessionDataUtil.getSessionData(SessionLesson.class.toString())).getTokboxApiKey();
		// TOKBOXセッションID
		String tokboxSessionId = ((SessionLesson) SessionDataUtil.getSessionData(SessionLesson.class.toString())).getTokboxSessionId();
		// TOKBOXトークンID
		String tokboxTokenId = ((SessionLesson) SessionDataUtil.getSessionData(SessionLesson.class.toString())).getTokboxTokenId();
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
		// レッスン時刻名
		String lessonTmNm = ((SessionLesson) SessionDataUtil.getSessionData(SessionLesson.class.toString())).getLessonTmNm();
		// レッスン現地時刻名
		String localLessonTmNm = ((SessionLesson) SessionDataUtil.getSessionData(SessionLesson.class.toString())).getLocalLessonTmNm();
		// コメント
		String commentTo = ((SessionLesson) SessionDataUtil.getSessionData(SessionLesson.class.toString())).getCommentTo();
		// 簡易説明
		String courseSimpleExplanation = ((SessionLesson) SessionDataUtil.getSessionData(SessionLesson.class.toString())).getCourseSimpleExplanation();
		// 添付付き有無フラグ
		String attachmentFlg = ((SessionLesson) SessionDataUtil.getSessionData(SessionLesson.class.toString())).getAttachmentFlg();
		// 講師ID
		String teacherId = ((SessionLesson) SessionDataUtil.getSessionData(SessionLesson.class.toString())).getTeacherId();
		// 受講者ID
		String studentId = ((SessionLesson) SessionDataUtil.getSessionData(SessionLesson.class.toString())).getStudentId();
		// 現在時刻
		String nowTime = DateUtil.getSysDateTimeNoSplit();

		// パラメータを画面に渡す
		// 予約番号
		model.setReservationNo(reservationNo);
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
		// コメント
		model.setCommentTo(commentTo);
		// 簡易説明
		model.setCourseMemo(courseSimpleExplanation);
		// 添付付き有無フラグ
		model.setAttachmentFlg(attachmentFlg);
		// 講師ID
		model.setTeacherId(teacherId);
		// 受講者ID
		model.setStudentId(studentId);
		// 現在時刻
		model.setNowTime(nowTime);
		// レッスンファイル有無：有
		model.setHasDuringLessons(true);
		if (StringUtils.equals(model.getRole(), SessionUser.ROLE_TEACHER)) {
			// 講師の場合
			// 講師START押下時刻
			model.setTeacherStartDttm(nowTime);
			// レッスン時刻名
			model.setLessonTmNm(new StringBuffer(lessonTmNm).append(this.jst).toString());
	        // レッスン現地時刻名
			model.setLocalLessonTmNm(new StringBuffer(localLessonTmNm).append(this.local).toString());
		} else {
			// 生徒の場合
			// 受講者START押下時刻
			model.setStudentStartDttm(nowTime);
		}

		// チャット履歴
		if (this.chatHistoryDownload != null) {
			Pattern p1 = Pattern.compile("\t|\r|\n");
			Matcher m1 = p1.matcher(this.chatHistoryDownload);
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

			model.setChatHistory(str4);
			//String escaped = StringEscapeUtils.escapeHtml3(this.chatHistory);
			//String escaped2 = StringEscapeUtils.escapeHtml4(this.chatHistory);
		}

		// モデルを設定する
		setModel(model);
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