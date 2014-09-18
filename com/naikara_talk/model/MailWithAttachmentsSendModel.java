package com.naikara_talk.model;

import java.io.File;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様(個人)_初期登録。<br>
 * <b>クラス名称　　　:</b>マイページModelクラス。<br>
 * <b>クラス概要　　　:</b>マイページModel。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/02 TECS 新規作成。
 */
public class MailWithAttachmentsSendModel implements Model {

    private static final long serialVersionUID = 1L;

    /** チェック：問題なし(正常) */
    public static final int CHECK_OK = 0;

    /** 受講者ID */
    private String studentId;

    /** 受講者ニックネーム */
    private String studentNickNm;

    /** 講師ID */
    private String teacherId;

    /** 講師ニックネーム */
    private String teacherNickNm;

    /** レッスン日 */
    private String lessonDt;

    /** レッスン時刻 */
    private String lessonTmNm;

    /** コース名の行(英語) */
    private String courseEnmAll;

    /** コース名の行 */
    private String courseJnmAll;

    /** 母国語 */
    private String nativeLanguage;

    /** 送信者 */
    private String teacherIdNm;

    /** 受信者 */
    private String studentIdNm;

    /** 添付 */
    private File tempFile;

    /** 添付名 */
    private String tempFileFileName;

    /** 添付タイプ */
    private String tempFileContentType;

    /** 内容 */
    private String commentTxt;

    /** 遷移元画面ID */
    private String pageId;

    /** 予約No */
    private String reservationNo;

    /**
     * 添付タイプ取得<br>
     * <br>
     * 添付タイプ取得を行う<br>
     * <br>
     * @param なし<br>
     * @return tempFileContentType 添付タイプ<br>
     * @exception なし
     */
    public String getTempFileContentType() {
        return tempFileContentType;
    }

    /**
     * 添付タイプ設定<br>
     * <br>
     * 添付タイプ設定を行う<br>
     * <br>
     * @param tempFileContentType 添付タイプ<br>
     * @return なし<br>
     * @exception なし
     */
    public void setTempFileContentType(String tempFileContentType) {
        this.tempFileContentType = tempFileContentType;
    }

    /**
     * 添付名取得<br>
     * <br>
     * 添付名取得を行う<br>
     * <br>
     * @param なし<br>
     * @return tempFileFileName 添付名<br>
     * @exception なし
     */
    public String getTempFileFileName() {
        return tempFileFileName;
    }

    /**
     * 添付名設定<br>
     * <br>
     * 添付名設定を行う<br>
     * <br>
     * @param tempFileFileName 添付名<br>
     * @return なし<br>
     * @exception なし
     */
    public void setTempFileFileName(String tempFileFileName) {
        this.tempFileFileName = tempFileFileName;
    }

    /**
     * 受講者ID取得<br>
     * <br>
     * 受講者ID取得を行う<br>
     * <br>
     * @param なし<br>
     * @return studentId 受講者ID<br>
     * @exception なし
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * 受講者ID設定<br>
     * <br>
     * 受講者ID設定を行う<br>
     * <br>
     * @param studentId 受講者ID<br>
     * @return なし<br>
     * @exception なし
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * 受講者ニックネーム取得<br>
     * <br>
     * 受講者ニックネーム取得を行う<br>
     * <br>
     * @param なし<br>
     * @return studentNickNm 受講者ニックネーム<br>
     * @exception なし
     */
    public String getStudentNickNm() {
        return studentNickNm;
    }

    /**
     * 受講者ニックネーム設定<br>
     * <br>
     * 受講者ニックネーム設定を行う<br>
     * <br>
     * @param studentNickNm 受講者ニックネーム<br>
     * @return なし<br>
     * @exception なし
     */
    public void setStudentNickNm(String studentNickNm) {
        this.studentNickNm = studentNickNm;
    }

    /**
     * 講師ID取得<br>
     * <br>
     * 講師ID取得を行う<br>
     * <br>
     * @param なし<br>
     * @return teacherId 講師ID<br>
     * @exception なし
     */
    public String getTeacherId() {
        return teacherId;
    }

    /**
     * 講師ID設定<br>
     * <br>
     * 講師ID設定を行う<br>
     * <br>
     * @param teacherId 講師ID<br>
     * @return なし<br>
     * @exception なし
     */
    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * 講師ニックネーム取得<br>
     * <br>
     * 講師ニックネーム取得を行う<br>
     * <br>
     * @param なし<br>
     * @return teacherNickNm 講師ニックネーム<br>
     * @exception なし
     */
    public String getTeacherNickNm() {
        return teacherNickNm;
    }

    /**
     * 講師ニックネーム設定<br>
     * <br>
     * 講師ニックネーム設定を行う<br>
     * <br>
     * @param teacherNickNm 講師ニックネーム<br>
     * @return なし<br>
     * @exception なし
     */
    public void setTeacherNickNm(String teacherNickNm) {
        this.teacherNickNm = teacherNickNm;
    }

    /**
     * レッスン日取得<br>
     * <br>
     * レッスン日取得を行う<br>
     * <br>
     * @param なし<br>
     * @return lessonDt レッスン日<br>
     * @exception なし
     */
    public String getLessonDt() {
        return lessonDt;
    }

    /**
     * レッスン日設定<br>
     * <br>
     * レッスン日設定を行う<br>
     * <br>
     * @param lessonDt レッスン日<br>
     * @return なし<br>
     * @exception なし
     */
    public void setLessonDt(String lessonDt) {
        this.lessonDt = lessonDt;
    }

    /**
     * レッスン時刻取得<br>
     * <br>
     * レッスン時刻取得を行う<br>
     * <br>
     * @param なし<br>
     * @return lessonTmNm レッスン時刻<br>
     * @exception なし
     */
    public String getLessonTmNm() {
        return lessonTmNm;
    }

    /**
     * レッスン時刻設定<br>
     * <br>
     * レッスン時刻設定を行う<br>
     * <br>
     * @param lessonTmNm レッスン時刻<br>
     * @return なし<br>
     * @exception なし
     */
    public void setLessonTmNm(String lessonTmNm) {
        this.lessonTmNm = lessonTmNm;
    }

    /**
     * コース名の行(英語)取得<br>
     * <br>
     * コース名の行(英語)を行う<br>
     * <br>
     * @param なし<br>
     * @return courseEnmAll コース名の行(英語)<br>
     * @exception なし
     */
    public String getCourseEnmAll() {
        return courseEnmAll;
    }

    /**
     * コース名の行(英語)設定<br>
     * <br>
     * コース名の行(英語)設定を行う<br>
     * <br>
     * @param courseEnmAll コース名の行(英語)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setCourseEnmAll(String courseEnmAll) {
        this.courseEnmAll = courseEnmAll;
    }

    /**
     * コース名の行取得<br>
     * <br>
     * コース名の行取得を行う<br>
     * <br>
     * @param なし<br>
     * @return courseJnmAll コース名の行<br>
     * @exception なし
     */
    public String getCourseJnmAll() {
        return courseJnmAll;
    }

    /**
     * コース名の行設定<br>
     * <br>
     * コース名の行設定を行う<br>
     * <br>
     * @param courseJnmAll コース名の行<br>
     * @return なし<br>
     * @exception なし
     */
    public void setCourseJnmAll(String courseJnmAll) {
        this.courseJnmAll = courseJnmAll;
    }

    /**
     * 母国語取得<br>
     * <br>
     * 母国語取得を行う<br>
     * <br>
     * @param なし<br>
     * @return nativeLanguage 母国語<br>
     * @exception なし
     */
    public String getNativeLanguage() {
        return nativeLanguage;
    }

    /**
     * 母国語設定<br>
     * <br>
     * 母国語設定を行う<br>
     * <br>
     * @param nativeLanguage 母国語<br>
     * @return なし<br>
     * @exception なし
     */
    public void setNativeLanguage(String nativeLanguage) {
        this.nativeLanguage = nativeLanguage;
    }

    /**
     * 送信者取得<br>
     * <br>
     * 送信者取得を行う<br>
     * <br>
     * @param なし<br>
     * @return teacherIdNm 送信者<br>
     * @exception なし
     */
    public String getTeacherIdNm() {
        return teacherIdNm;
    }

    /**
     * 送信者設定<br>
     * <br>
     * 送信者設定を行う<br>
     * <br>
     * @param teacherIdNm 送信者<br>
     * @return なし<br>
     * @exception なし
     */
    public void setTeacherIdNm(String teacherIdNm) {
        this.teacherIdNm = teacherIdNm;
    }

    /**
     * 受信者取得<br>
     * <br>
     * 受信者取得を行う<br>
     * <br>
     * @param なし<br>
     * @return studentIdNm 受信者<br>
     * @exception なし
     */
    public String getStudentIdNm() {
        return studentIdNm;
    }

    /**
     * 受信者設定<br>
     * <br>
     * 受信者設定を行う<br>
     * <br>
     * @param studentIdNm 受信者<br>
     * @return なし<br>
     * @exception なし
     */
    public void setStudentIdNm(String studentIdNm) {
        this.studentIdNm = studentIdNm;
    }

    /**
     * 添付取得<br>
     * <br>
     * 添付取得を行う<br>
     * <br>
     * @param なし<br>
     * @return tempFile 添付<br>
     * @exception なし
     */
    public File getTempFile() {
        return tempFile;
    }

    /**
     * 添付設定<br>
     * <br>
     * 添付設定を行う<br>
     * <br>
     * @param tempFile 添付<br>
     * @return なし<br>
     * @exception なし
     */
    public void setTempFile(File tempFile) {
        this.tempFile = tempFile;
    }

    /**
     * 内容取得<br>
     * <br>
     * 内容取得を行う<br>
     * <br>
     * @param なし<br>
     * @return commentTxt 内容<br>
     * @exception なし
     */
    public String getCommentTxt() {
        return commentTxt;
    }

    /**
     * 内容設定<br>
     * <br>
     * 内容設定を行う<br>
     * <br>
     * @param commentTxt 内容<br>
     * @return なし<br>
     * @exception なし
     */
    public void setCommentTxt(String commentTxt) {
        this.commentTxt = commentTxt;
    }

    /**
     * 遷移元画面ID取得<br>
     * <br>
     * 遷移元画面ID取得を行う<br>
     * <br>
     * @param なし<br>
     * @return pageId 遷移元画面ID<br>
     * @exception なし
     */
    public String getPageId() {
        return pageId;
    }

    /**
     * 遷移元画面ID設定<br>
     * <br>
     * 遷移元画面ID設定を行う<br>
     * <br>
     * @param pageId 遷移元画面ID<br>
     * @return なし<br>
     * @exception なし
     */
    public void setPageId(String pageId) {
        this.pageId = pageId;
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