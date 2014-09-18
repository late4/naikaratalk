package com.naikara_talk.sessiondata;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称 :</b>レッスン画面起動<br>
 * <b>クラス名称　　　:</b>レッスンのセッション情報クラス。<br>
 * <b>クラス概要　　　:</b>レッスンのセッション情報。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b> 2014/01/15 TECS 新規作成。
 */
public class SessionLesson implements SessionData {

    /** 予約番号 */
    private String reservationNo;

    /** レッスン日 */
    private String lessonDt;

    /** レッスン時刻名 */
    private String lessonTmNm;

    /** レッスン現地時刻名 */
    private String localLessonTmNm;

    /** コメント */
    private String commentTo;

    /** 担当講師ニックネーム */
    private String teacherNickNm;

    /** 受講者ニックネーム */
    private String studentNickNm;

    /** コースネーム（日本語） */
    private String courseJnm;

    /** コースネーム（英語） */
    private String courseEnm;

    /** レッスン日程 */
    private String lessonSchedule;

    /** 簡易説明 */
    private String courseSimpleExplanation;

    /** 添付付き有無フラグ */
    private String attachmentFlg;

    /** TOKBOXのAPIキー */
    private int tokboxApiKey;

    /** TOKBOXセッションID */
    private String tokboxSessionId;

    /** TOKBOXトークンID */
    private String tokboxTokenId;

    /** 講師ID */
    private String teacherId;

    /** 受講者ID */
    private String studentId;

    /** チャット履歴（マイページ） */
    private String chatHistoryMyPage;

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

    /**
     * @return lessonDt
     */
    public String getLessonDt() {
        return lessonDt;
    }

    /**
     * @param lessonDt セットする lessonDt
     */
    public void setLessonDt(String lessonDt) {
        this.lessonDt = lessonDt;
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
     * @return courseSimpleExplanation
     */
    public String getCourseSimpleExplanation() {
        return courseSimpleExplanation;
    }

    /**
     * @param courseSimpleExplanation セットする courseSimpleExplanation
     */
    public void setCourseSimpleExplanation(String courseSimpleExplanation) {
        this.courseSimpleExplanation = courseSimpleExplanation;
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
     * @return chatHistoryMyPage
     */
    public String getChatHistoryMyPage() {
        return chatHistoryMyPage;
    }

    /**
     * @param chatHistoryMyPage セットする chatHistoryMyPage
     */
    public void setChatHistoryMyPage(String chatHistoryMyPage) {
        this.chatHistoryMyPage = chatHistoryMyPage;
    }
}