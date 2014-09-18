package com.naikara_talk.sessiondata;


/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称 :</b>お客様(個人)_予約処理<br>
 * <b>クラス名称　　　:</b>添付資料付メール送信のセッション情報クラス。<br>
 * <b>クラス概要　　　:</b>添付資料付メール送信戻る用のセッション情報。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b> 2013/08/22 TECS 新規作成。
 */
public class SessionMailWithAttachmentsSend implements SessionData {

    /** ヘッダの戻るリンク用のSessionKey */
    private static final String HEADER_RETURN_KEY = "MailWithAttachmentsSendTeacher";

    /** 遷移元画面ID */
    private String pageId;

    /** 講師ID */
    private String teacherId;

    /** 講師ニックネーム */
    private String teacherNickNm;

    /** 受講者ID */
    private String studentId;

    /** 受講者ニックネーム */
    private String studentNickNm;

    /** レッスン日 */
    private String lessonDt;

    /** レッスン時間 */
    private String lessonTmNm;

    /** コース名 */
    private String courseJnmAll;

    /** コース名(英語名) */
    private String courseEnmAll;

    /** 内容 */
    private String comment;

    /** 添付 */
    private byte[] bytes;

    /** 予約No */
    private String reservationNo;

    /**
     * このセッションデータのキーを返却する
     */
    public static String getKey() {
        return HEADER_RETURN_KEY;
    }

    /**
     * @return pageId
     */
    public String getPageId() {
        return pageId;
    }

    /**
     * @param pageId セットする pageId
     */
    public void setPageId(String pageId) {
        this.pageId = pageId;
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
     * @return courseJnmAll
     */
    public String getCourseJnmAll() {
        return courseJnmAll;
    }

    /**
     * @param courseJnmAll セットする courseJnmAll
     */
    public void setCourseJnmAll(String courseJnmAll) {
        this.courseJnmAll = courseJnmAll;
    }

    /**
     * @return courseEnmAll
     */
    public String getCourseEnmAll() {
        return courseEnmAll;
    }

    /**
     * @param courseEnmAll セットする courseEnmAll
     */
    public void setCourseEnmAll(String courseEnmAll) {
        this.courseEnmAll = courseEnmAll;
    }

    /**
     * @return comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment セットする comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return bytes
     */
    public byte[] getBytes() {
        return bytes;
    }

    /**
     * @param bytes セットする bytes
     */
    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
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


}