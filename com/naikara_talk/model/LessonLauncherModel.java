package com.naikara_talk.model;

import java.io.File;
import java.sql.Blob;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>受講者、講師<br>
 * <b>クラス名称　　　:</b>レッスン開始処理モデル<br>
 * <b>クラス概要　　　:</b>レッスン開始処理モデル<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author nos <b>変更履歴　　　　:</b> 2013/XX/XX MIRA 新規作成
 *                                :</b> 2014/01/15 TECS FlashからTokboxへの変更対応
 *                                :</b> 2014/04/22 TECS ファイルタイプの追加対応(pdf⇒pdf、txt)
 */

public class LessonLauncherModel implements Model {

    private static final long serialVersionUID = 1L;

    /** 講師・生徒	*/
	private String role;
	/** 受講者ID */
	private String studentId;
	/** 講師ID */
	private String teacherId;
	/** 予約番号 */
	private String reservationNo;

	// 2014/01/15 FlashからTokboxに変更対応 start
	///** 教室番号	*/
	//private String roomNo;
	/** Tokbox用セッションID */
	private String tokboxSessionId;
	/** Tokbox用トークンID */
	private String tokboxTokenId;
	/** レッスンファイル(File) */
	private File fileDuringLessons;
	/** レッスンファイル(Blob) */
	private Blob blobDuringLessons;
	/** レッスンファイル(boolean) */
	private boolean hasDuringLessons;
	/** Tokbox用APIキー */
	private int tokboxApiKey;
	/** 受講者名前 */
	private String studentNm;
	/** 講師名前 */
	private String teacherNm;
	/** 講師ニックネーム */
	private String teacherNickNm;
	/** 受講者ニックネーム */
	private String studentNickNm;
	/** コース名（日本語） */
	private String courseJnm;
	/** コース名（英語） */
	private String courseEnm;
	/** レッソン日程 */
	private String lessonSchedule;
	/** レッスン時刻名 */
	private String lessonTmNm;
	/** レッスン現地時刻名 */
	private String localLessonTmNm;
	/** コメント */
	private String commentTo;
	/** コース説明 */
	private String courseMemo;
	/** 現在時刻 */
	private String nowTime;
	/** 添付付き有無フラグ */
	private String attachmentFlg;
	/** 受講者START押下時刻 */
	private String studentStartDttm;
	/** 講師START押下時刻 */
	private String teacherStartDttm;
	/** 講師END押下時刻 */
	private String teacherEndDttm;
	/** チャット履歴 */
	private String chatHistory;
	// 2014/01/15 FlashからTokboxに変更対応 end

	// 2014/04/22 Add Start ファイルタイプの追加対応(pdf⇒pdf、txt)
	/** レッスン中ファイルのファイルタイプ */
	private String lessonFileContentType;
	// 2014/04/22 Add end   ファイルタイプの追加対応(pdf⇒pdf、txt)

	/** 受講者セッションID */
	private String studentSessionId;
	/** 講師セッションID */
	private String teacherSessionId;
	/** 利用者ID */
	private String userId;

	/**
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId セットする userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return role
	 */
	public String getRole() {
		return role;
	}
	/**
	 * @param role セットする role
	 */
	public void setRole(String role) {
		this.role = role;
	}
	/**
	 * @return studentId
	 */
	public String getStudentId() {
		return studentId;
	}
	/**
	 * @param studentId セットする studentId
	 */
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	/**
	 * @return teacherId
	 */
	public String getTeacherId() {
		return teacherId;
	}
	/**
	 * @param teacherId セットする teacherId
	 */
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	/**
	 * @return reservationNo
	 */
	public String getReservationNo() {
		return reservationNo;
	}
	/**
	 * @param reservationNo セットする reservationNo
	 */
	public void setReservationNo(String reservationNo) {
		this.reservationNo = reservationNo;
	}

	// 2014/01/15 FlashからTokboxに変更対応 start
//	/**
//	 * @return roomNo
//	 */
//	public String getRoomNo() {
//		return roomNo;
//	}
//	/**
//	 * @param roomNo セットする roomNo
//	 */
//	public void setRoomNo(String roomNo) {
//		this.roomNo = roomNo;
//	}
	/**
	 * @return tokboxSessionId
	 */
	public String getTokboxSessionId() {
		return tokboxSessionId;
	}
	/**
	 * @param tokboxSessionId セットする tokboxSessionId
	 */
	public void setTokboxSessionId(String tokboxSessionId) {
		this.tokboxSessionId = tokboxSessionId;
	}
	/**
	 * @return tokboxTokenId
	 */
	public String getTokboxTokenId() {
		return tokboxTokenId;
	}
	/**
	 * @param tokboxTokenId セットする tokboxTokenId
	 */
	public void setTokboxTokenId(String tokboxTokenId) {
		this.tokboxTokenId = tokboxTokenId;
	}
	/**
	 * @return fileDuringLessons
	 */
	public File getFileDuringLessons() {
		return fileDuringLessons;
	}
	/**
	 * @param fileDuringLessons セットする fileDuringLessons
	 */
	public void setFileDuringLessons(File fileDuringLessons) {
		this.fileDuringLessons = fileDuringLessons;
	}
	/**
	 * @return blobDuringLessons
	 */
	public Blob getBlobDuringLessons() {
		return blobDuringLessons;
	}
	/**
	 * @param blobDuringLessons セットする blobDuringLessons
	 */
	public void setBlobDuringLessons(Blob blobDuringLessons) {
		this.blobDuringLessons = blobDuringLessons;
	}
	/**
	 * @return hasDuringLessons
	 */
	public boolean getHasDuringLessons() {
		return hasDuringLessons;
	}
	/**
	 * @param hasDuringLessons セットする hasDuringLessons
	 */
	public void setHasDuringLessons(boolean hasDuringLessons) {
		this.hasDuringLessons = hasDuringLessons;
	}
	/**
	 * @return tokboxApiKey
	 */
	public int getTokboxApiKey() {
		return tokboxApiKey;
	}
	/**
	 * @param tokboxApiKey セットする tokboxApiKey
	 */
	public void setTokboxApiKey(int tokboxApiKey) {
		this.tokboxApiKey = tokboxApiKey;
	}

	/**
	 * @return studentNm
	 */
	public String getStudentNm() {
		return studentNm;
	}
	/**
	 * @param studentNm セットする studentNm
	 */
	public void setStudentNm(String studentNm) {
		this.studentNm = studentNm;
	}

	/**
	 * @return teacherNm
	 */
	public String getTeacherNm() {
		return teacherNm;
	}
	/**
	 * @param teacherNm セットする teacherNm
	 */
	public void setTeacherNm(String teacherNm) {
		this.teacherNm = teacherNm;
	}

	/**
	 * @return teacherNickNm
	 */
	public String getTeacherNickNm() {
		return teacherNickNm;
	}
	/**
	 * @param teacherNickNm セットする teacherNickNm
	 */
	public void setTeacherNickNm(String teacherNickNm) {
		this.teacherNickNm = teacherNickNm;
	}

	/**
	 * @return studentNickNm
	 */
	public String getStudentNickNm() {
		return studentNickNm;
	}
	/**
	 * @param studentNickNm セットする studentNickNm
	 */
	public void setStudentNickNm(String studentNickNm) {
		this.studentNickNm = studentNickNm;
	}

	/**
	 * @return courseJnm
	 */
	public String getCourseJnm() {
		return courseJnm;
	}
	/**
	 * @param courseJnm セットする courseJnm
	 */
	public void setCourseJnm(String courseJnm) {
		this.courseJnm = courseJnm;
	}

	/**
	 * @return courseEnm
	 */
	public String getCourseEnm() {
		return courseEnm;
	}
	/**
	 * @param courseEnm セットする courseEnm
	 */
	public void setCourseEnm(String courseEnm) {
		this.courseEnm = courseEnm;
	}

	/**
	 * @return lessonSchedule
	 */
	public String getLessonSchedule() {
		return lessonSchedule;
	}
	/**
	 * @param lessonSchedule セットする lessonSchedule
	 */
	public void setLessonSchedule(String lessonSchedule) {
		this.lessonSchedule = lessonSchedule;
	}

	/**
	 * @return lessonTmNm
	 */
	public String getLessonTmNm() {
		return lessonTmNm;
	}
	/**
	 * @param lessonTmNm セットする lessonTmNm
	 */
	public void setLessonTmNm(String lessonTmNm) {
		this.lessonTmNm = lessonTmNm;
	}

	/**
	 * @return localLessonTmNm
	 */
	public String getLocalLessonTmNm() {
		return localLessonTmNm;
	}
	/**
	 * @param localLessonTmNm セットする localLessonTmNm
	 */
	public void setLocalLessonTmNm(String localLessonTmNm) {
		this.localLessonTmNm = localLessonTmNm;
	}

	/**
	 * @return commentTo
	 */
	public String getCommentTo() {
		return commentTo;
	}
	/**
	 * @param commentTo セットする commentTo
	 */
	public void setCommentTo(String commentTo) {
		this.commentTo = commentTo;
	}

	/**
	 * @return courseMemo
	 */
	public String getCourseMemo() {
		return courseMemo;
	}
	/**
	 * @param courseMemo セットする courseMemo
	 */
	public void setCourseMemo(String courseMemo) {
		this.courseMemo = courseMemo;
	}

	/**
	 * @return nowTime
	 */
	public String getNowTime() {
		return nowTime;
	}
	/**
	 * @param nowTime セットする nowTime
	 */
	public void setNowTime(String nowTime) {
		this.nowTime = nowTime;
	}

	/**
	 * @return attachmentFlg
	 */
	public String getAttachmentFlg() {
		return attachmentFlg;
	}
	/**
	 * @param attachmentFlg セットする attachmentFlg
	 */
	public void setAttachmentFlg(String attachmentFlg) {
		this.attachmentFlg = attachmentFlg;
	}

	/**
	 * @return studentStartDttm
	 */
	public String getStudentStartDttm() {
		return studentStartDttm;
	}
	/**
	 * @param studentStartDttm セットする studentStartDttm
	 */
	public void setStudentStartDttm(String studentStartDttm) {
		this.studentStartDttm = studentStartDttm;
	}

	/**
	 * @return teacherStartDttm
	 */
	public String getTeacherStartDttm() {
		return teacherStartDttm;
	}
	/**
	 * @param teacherStartDttm セットする teacherStartDttm
	 */
	public void setTeacherStartDttm(String teacherStartDttm) {
		this.teacherStartDttm = teacherStartDttm;
	}

	/**
	 * @return teacherEndDttm
	 */
	public String getTeacherEndDttm() {
		return teacherEndDttm;
	}
	/**
	 * @param teacherEndDttm セットする teacherEndDttm
	 */
	public void setTeacherEndDttm(String teacherEndDttm) {
		this.teacherEndDttm = teacherEndDttm;
	}

	/**
	 * @return chatHistory
	 */
	public String getChatHistory() {
		return chatHistory;
	}
	/**
	 * @param chatHistory セットする chatHistory
	 */
	public void setChatHistory(String chatHistory) {
		this.chatHistory = chatHistory;
	}
	// 2014/01/15 FlashからTokboxに変更対応 end

	/**
	 * @return studentSessionId
	 */
	public String getStudentSessionId() {
		return studentSessionId;
	}
	/**
	 * @param studentSessionId セットする studentSessionId
	 */
	public void setStudentSessionId(String studentSessionId) {
		this.studentSessionId = studentSessionId;
	}
	/**
	 * @return teacherSessionId
	 */
	public String getTeacherSessionId() {
		return teacherSessionId;
	}
	/**
	 * @param teacherSessionId セットする teacherSessionId
	 */
	public void setTeacherSessionId(String teacherSessionId) {
		this.teacherSessionId = teacherSessionId;
	}

	// 2014/04/22 Add Start ファイルタイプの追加対応(pdf⇒pdf、txt)
	/**
	 * @return lessonFileContentType
	 */
	public String getLessonFileContentType() {
		return lessonFileContentType;
	}

	/**
	 * @param lessonFileContentType セットする lessonFileContentType
	 */
	public void setLessonFileContentType(String lessonFileContentType) {
		this.lessonFileContentType = lessonFileContentType;
	}
	// 2014/04/22 Add End   ファイルタイプの追加対応(pdf⇒pdf、txt)


}
