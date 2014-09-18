package com.naikara_talk.action;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.LessonLauncherModel;
import com.naikara_talk.service.LessonPdfDownloadService;
import com.naikara_talk.service.LessonPdfUploadService;
import com.naikara_talk.sessiondata.SessionLesson;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称 :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>レッスン画面起動<br>
 * <b>クラス名称 :</b>レッスン受講者からPDFアップロード<br>
 * <b>クラス概要 :</b>レッスン受講者からPDFアップロードAction<br>
 * <br>
 * <b>著作権 :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴 :</b>2014/01/15 TECS 新規作成
 *                           </b>2014/04/22 TECS 最大ファイルサイズの変更に伴うメッセージ修正
 */

public class LessonPdfUploadAction extends LessonActionSupport {

	private static final long serialVersionUID = 1L;

	/** 更新処理(正常) */
	private static final int UPDATE_OK = 1;

	/** なにもしない */
	private static final int NOTHING = 2;

	/** なにもしない */
	private static final int SIZEOVER = 3;

	/** なにもしない */
	private static final int WRONGTYPE = 4;

	private String jst = "(JST)";
	private String local = "(Local)";

	/**
	 * レッスンファイルをアップロードする<br>
	 * <br>
	 *
	 * @param なし
	 * <br>
	 * @return String TEACHER OR STUDENT<br>
	 * @exception NaiException
	 */
	protected String requestService() throws NaiException {

		// ユーザ情報を取得する
		String role = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getRole();
		String userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();

		if (StringUtils.equals(role,  SessionUser.ROLE_TEACHER)) {
			// 開始ログ
			log.info(NaikaraStringUtil.unionProcesslog("レッスン講師からPDFアップロード"));
		} else {
			// 開始ログ
			log.info(NaikaraStringUtil.unionProcesslog("レッスン受講者からPDFアップロード"));
		}

		// サービス生成
		LessonPdfUploadService uploadService = new LessonPdfUploadService();

		// モデルのインスタンスを生成する
		LessonLauncherModel model = new LessonLauncherModel();

		// ロール
		model.setRole(role);
		// ユーザID
		model.setUserId(userId);
		// 各種のパラメータを作成する
		this.setParameter(model);

		// 画面遷移先
		String transitionScreen = null;

		// HIDDEN用パラメータを作成する
		this.reservationNo = model.getReservationNo();

		// 更新結果の初期設定
		int cnt = NOTHING;

		try {
			// メッセージ
			String msg = null;

			// ◆◆◆ファイルサイズのチェック◆◆◆
			// ファイルサイズのチェック(レッスンファイル)
			if (this.fileDuringLessons != null) {
				if (uploadService.isSizeOver(this.fileDuringLessons)) { // available:推定バイト数
					cnt = SIZEOVER;
				}

				// ◆◆◆ファイルタイプのチェック◆◆◆
				// ファイルタイプのチェック(レッスンファイル)
				if (uploadService.isWrongType(this.fileDuringLessons, this.fileDuringLessonsContentType)) {
					cnt = WRONGTYPE;
				}

				if (cnt != SIZEOVER && cnt != WRONGTYPE) {
					// モデル設定
					// レッスンファイル
					model.setFileDuringLessons(this.fileDuringLessons);
					// 予約番号
					model.setReservationNo(model.getReservationNo());

					// 2014/04/22 Upd Start ファイルタイプの追加対応(pdf⇒pdf、txt)
			        String lessonFileContentType = this.fileDuringLessonsContentType;
					model.setLessonFileContentType(lessonFileContentType);
					// 2014/04/22 Upd End   ファイルタイプの追加対応(pdf⇒pdf、txt)

					// 更新処理のサービス呼び出し
					cnt = uploadService.uploadLessonPdf(model);
				}
			}

			// アップロード済みファイルがあるかどうかチェックする
			LessonPdfDownloadService downloadService = new LessonPdfDownloadService();
			LessonLauncherModel downloadModel = new LessonLauncherModel();
			downloadModel.setReservationNo(model.getReservationNo());
			// ダウンロード対象を取得する
			downloadModel = downloadService.downloadLessonPdf(downloadModel);

			if (downloadModel == null || downloadModel.getBlobDuringLessons() == null) {
				// レッスンファイル有無：なし
				model.setHasDuringLessons(false);
			} else {
				// レッスンファイル有無：あり
				model.setHasDuringLessons(true);
			}

			// 更新結果を判断する
			switch (cnt) {
			case NaikaraTalkConstants.RETURN_CD_ERR_NO_UPD:
				if (StringUtils.equals(role, SessionUser.ROLE_TEACHER)) {
					// 講師の場合
					// 排他エラーメッセージの設定
					msg = getMessage("EE0014", new String[] { "", "" });
					this.addActionMessage(msg);
					// 排他エラー
					transitionScreen = NaikaraActionSupport.TEACHER;
				} else {
					// 生徒の場合
					// 排他エラーメッセージの設定
					msg = getMessage("ES0014", new String[] { "", "" });
					this.addActionMessage(msg);
					// 排他エラー
					transitionScreen = NaikaraActionSupport.STUDENT;
				}

				break;
			case SIZEOVER:
				// ファイルサイズエラー
				if (StringUtils.equals(role, SessionUser.ROLE_TEACHER)) {
					// 講師の場合
					// 2014/04/22 Upd Start 最大ファイルサイズの変更に伴うメッセージ修正(サイズを明示的に表現しない)
					// msg = getMessage("ET0065", new String[] { "1MB", "1MB" });
					msg = getMessage("ET0078", new String[] {});
					// 2014/04/22 Upd End   最大ファイルサイズの変更に伴うメッセージ修正(サイズを明示的に表現しない)
					this.addActionMessage(msg);
					transitionScreen = NaikaraActionSupport.TEACHER;
				} else {
					// 生徒の場合
					// 2014/04/22 Upd Start 最大ファイルサイズの変更に伴うメッセージ修正(サイズを明示的に表現しない)
					// msg = getMessage("EC0065", new String[] { "1MB", "" });
					 msg = getMessage("EC0078", new String[] {});
					// 2014/04/22 Upd End   最大ファイルサイズの変更に伴うメッセージ修正(サイズを明示的に表現しない)
					this.addActionMessage(msg);
					transitionScreen = NaikaraActionSupport.STUDENT;
				}

				break;
			case WRONGTYPE:
				// ファイルタイプエラー
				if (StringUtils.equals(role, SessionUser.ROLE_TEACHER)) {
					// 講師の場合
					msg = getMessage("ET0035", new String[] {"File extension", "ファイルの拡張子" });
					this.addActionMessage(msg);
					transitionScreen = NaikaraActionSupport.TEACHER;
				} else {
					// 生徒の場合
					msg = getMessage("EC0035", new String[] {"ファイルの拡張子", "" });
					this.addActionMessage(msg);
					transitionScreen = NaikaraActionSupport.STUDENT;
				}

				break;
			case UPDATE_OK:
				if (StringUtils.equals(role, SessionUser.ROLE_TEACHER)) {
					// 講師の場合
					// 更新完了メッセージの設定
					msg = getMessage("IT0014", new String[] { "Upload of the file", "ファイルアップロード" });
					this.addActionMessage(msg);
					// 更新処理(正常)
					transitionScreen = NaikaraActionSupport.TEACHER;
				} else {
					// 生徒の場合
					// 更新完了メッセージの設定
					msg = getMessage("IC0014", new String[] { "ファイルアップロード", "" });
					this.addActionMessage(msg);
					// 更新処理(正常)
					transitionScreen = NaikaraActionSupport.STUDENT;
				}

				break;
			case NOTHING:
				if (StringUtils.equals(role, SessionUser.ROLE_TEACHER)) {
					// 講師の場合
					transitionScreen = NaikaraActionSupport.TEACHER;
				} else {
					// 生徒の場合
					transitionScreen = NaikaraActionSupport.STUDENT;
				}

				break;
			}
		} catch (Exception e) {
			throw new NaiException(e);
		}

		// 一覧画面を戻る
		return transitionScreen;
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
		if (this.chatHistoryUpload != null) {
			Pattern p1 = Pattern.compile("\t|\r|\n");
			Matcher m1 = p1.matcher(this.chatHistoryUpload);
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
