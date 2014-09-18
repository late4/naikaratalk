package com.naikara_talk.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.MailWithAttachmentsSendModel;
import com.naikara_talk.sessiondata.SessionMailWithAttachmentsSend;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様(個人)_初期登録。<br>
 * <b>クラス名称　　　:</b>マイページActionスーパークラス。<br>
 * <b>クラス概要　　　:</b>マイページAction。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/02 TECS 新規作成。
 *  　　　　　　　　　:</b>2014/04/10 TECS 変更 予約Noの追加(添付付メール送信画面用)
 */
public abstract class MailWithAttachmentsSendActionSupport extends NaikaraActionSupport {

    private static final long serialVersionUID = 1L;

    /** 講師の場合のHelp画面名 */
    protected static final String HELP_PAGE_ID_TEACHER = "HelpMailWithAttachmentsSendTeacher.html";

    /** 受講者の場合のHelp画面名 */
    protected static final String HELP_PAGE_ID_STUDENT = "HelpMailWithAttachmentsSendStudent.html";

    /** 講師の場合の画面表示のタイトル */
    protected static final String TITLE_TEACHER = "Mail Transmission";

    /** 画面表示のタイトル */
    protected String title = "";

    /** Help画面名 */
    protected String helpPageId = "";

    /**
     * サービスの呼び出しの実装。
     */
    abstract protected String requestService() throws NaiException;

    /**
     * 画面のパラメータセット<br>
     * <br>
     * 画面のパラメータをモデルにセット<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception なし
     */
    public void setupModel() {

        // 遷移元画面ID(jsp)
        this.model.setPageId(this.pageId);
        // 受講者ID(jsp)
        this.model.setStudentId(this.studentId);
        // 受講者ニックネーム(jsp)
        this.model.setStudentNickNm(this.studentNickNm);
        // 講師ID(jsp)
        this.model.setTeacherId(this.teacherId);
        // 講師ニックネーム(jsp)
        this.model.setTeacherNickNm(this.teacherNickNm);
        // レッスン日(jsp)
        this.model.setLessonDt(this.lessonDt);
        // レッスン時刻(jsp)
        this.model.setLessonTmNm(this.lessonTmNm);
        // コース名の行(英語)(jsp)
        this.model.setCourseEnmAll(this.courseEnmAll);
        // コース名の行(jsp)
        this.model.setCourseJnmAll(this.courseJnmAll);
        // 母国語(jsp)
        this.model.setNativeLanguage(this.nativeLanguage);
        // 送信者(jsp)
        this.model.setTeacherIdNm(this.teacherIdNm);
        // 受信者(jsp)
        this.model.setStudentIdNm(this.studentIdNm);
        // 添付(jsp)
        this.model.setTempFile(this.tempFile);
        // 添付名(jsp)
        this.model.setTempFileFileName(this.tempFileFileName);
        // 添付タイプ(jsp)
        this.model.setTempFileContentType(this.tempFileContentType);
        // 内容(jsp)
        this.model.setCommentTxt(this.commentTxt);
        // 予約No
        this.model.setReservationNo(this.reservationNo);
    }

    /**
     * セッションをクリーンアップ<br>
     * <br>
     * セッションデータをクリアする。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    public void clearSessionMailWithAttachmentsSend() {

        // セッションをクリーンアップ
        if ((SessionMailWithAttachmentsSend) SessionDataUtil.getSessionData(SessionMailWithAttachmentsSend.class
                .toString()) != null) {
            SessionDataUtil.clearSessionData(SessionMailWithAttachmentsSend.class.toString());
        }
    }

    /**
     * 検索する前 Session値 To Model<br>
     * <br>
     * SessionMailWithAttachmentsSend値をModelにセット。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    public void SessionMailWithAttachmentsSendToModelBefore() {

        if (StringUtils.isEmpty(this.pageId)) {

            if ((SessionMailWithAttachmentsSend) SessionDataUtil.getSessionData(SessionMailWithAttachmentsSend.class
                    .toString()) != null) {

                // 遷移元画面ID
                String pageId = ((SessionMailWithAttachmentsSend) SessionDataUtil
                        .getSessionData(SessionMailWithAttachmentsSend.class.toString())).getPageId();
                // 講師ID
                String teacherId = ((SessionMailWithAttachmentsSend) SessionDataUtil
                        .getSessionData(SessionMailWithAttachmentsSend.class.toString())).getTeacherId();
                // 講師ニックネーム
                String teacherNickNm = ((SessionMailWithAttachmentsSend) SessionDataUtil
                        .getSessionData(SessionMailWithAttachmentsSend.class.toString())).getTeacherNickNm();
                // 受講者ID
                String studentId = ((SessionMailWithAttachmentsSend) SessionDataUtil
                        .getSessionData(SessionMailWithAttachmentsSend.class.toString())).getStudentId();
                // 受講者ニックネーム
                String studentNickNm = ((SessionMailWithAttachmentsSend) SessionDataUtil
                        .getSessionData(SessionMailWithAttachmentsSend.class.toString())).getStudentNickNm();
                // レッスン日
                String lessonDt = ((SessionMailWithAttachmentsSend) SessionDataUtil
                        .getSessionData(SessionMailWithAttachmentsSend.class.toString())).getLessonDt();
                // レッスン時間
                String lessonTmNm = ((SessionMailWithAttachmentsSend) SessionDataUtil
                        .getSessionData(SessionMailWithAttachmentsSend.class.toString())).getLessonTmNm();
                // コース名
                String courseJnmAll = ((SessionMailWithAttachmentsSend) SessionDataUtil
                        .getSessionData(SessionMailWithAttachmentsSend.class.toString())).getCourseJnmAll();
                // コース名(英語名)
                String courseEnmAll = ((SessionMailWithAttachmentsSend) SessionDataUtil
                        .getSessionData(SessionMailWithAttachmentsSend.class.toString())).getCourseEnmAll();
                // 内容
                String comment = ((SessionMailWithAttachmentsSend) SessionDataUtil
                        .getSessionData(SessionMailWithAttachmentsSend.class.toString())).getComment();

                // 予約No
                String reservationNo = ((SessionMailWithAttachmentsSend) SessionDataUtil
                        .getSessionData(SessionMailWithAttachmentsSend.class.toString())).getReservationNo();


                // 遷移元画面ID
                this.pageId = pageId;
                // 講師ID
                this.teacherId = teacherId;
                // 講師ニックネーム
                this.teacherNickNm = teacherNickNm;
                // 受講者ID
                this.studentId = studentId;
                // 受講者ニックネーム
                this.studentNickNm = studentNickNm;
                // レッスン日
                this.lessonDt = lessonDt;
                // レッスン時間
                this.lessonTmNm = lessonTmNm;
                // コース名
                this.courseJnmAll = courseJnmAll;
                // コース名(英語名)
                this.courseEnmAll = courseEnmAll;
                // 内容
                this.commentTxt = comment;
                // 予約No
                this.reservationNo = reservationNo;
            }
        }
    }

    /**
     * Model値 To Session<br>
     * <br>
     * Model値をSessionMailWithAttachmentsSendにセット。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    public void modelToSessionMailWithAttachmentsSend() throws Exception {

        SessionMailWithAttachmentsSend sessionData = new SessionMailWithAttachmentsSend();

        // 遷移元画面ID
        sessionData.setPageId(this.model.getPageId());
        // 講師ID
        sessionData.setTeacherId(this.model.getTeacherId());
        // 講師ニックネーム
        sessionData.setTeacherNickNm(this.model.getTeacherNickNm());
        // 受講者ID
        sessionData.setStudentId(this.model.getStudentId());
        // 受講者ニックネーム
        sessionData.setStudentNickNm(this.model.getStudentNickNm());
        // レッスン日
        sessionData.setLessonDt(this.model.getLessonDt());
        // レッスン時間
        sessionData.setLessonTmNm(this.model.getLessonTmNm());
        // コース名
        sessionData.setCourseJnmAll(this.model.getCourseJnmAll());
        // コース名(英語名)
        sessionData.setCourseEnmAll(this.model.getCourseEnmAll());
        // 内容
        sessionData.setComment(this.model.getCommentTxt());

        // 添付
        if (this.tempFile != null) {
            InputStream is = new FileInputStream(this.tempFile);

            byte[] bytes = new byte[is.available()];
            is.read(bytes, 0, is.available());

            sessionData.setBytes(bytes);

            is.close();
        }

        // 予約No
        sessionData.setReservationNo(this.model.getReservationNo());

        SessionDataUtil.setSessionData(sessionData);

    }

    /** メッセージ */
    protected String message;

    /** 受講者ID(jsp) */
    protected String studentId;

    /** 受講者ニックネーム(jsp) */
    protected String studentNickNm;

    /** 講師ID(jsp) */
    protected String teacherId;

    /** 講師ニックネーム(jsp) */
    protected String teacherNickNm;

    /** レッスン日(jsp) */
    protected String lessonDt;

    /** レッスン時刻(jsp) */
    protected String lessonTmNm;

    /** コース名の行(英語)(jsp) */
    protected String courseEnmAll;

    /** コース名の行(jsp) */
    protected String courseJnmAll;

    /** 母国語(jsp) */
    protected String nativeLanguage;

    /** 送信者(jsp) */
    protected String teacherIdNm;

    /** 受信者(jsp) */
    protected String studentIdNm;

    /** 添付(jsp) */
    protected File tempFile;

    /** 添付名(jsp) */
    protected String tempFileFileName;

    /** 添付タイプ(jsp) */
    protected String tempFileContentType;

    /** 内容(jsp) */
    protected String commentTxt;

    /** 遷移元画面ID(jsp) */
    protected String pageId;

    /** 戻るフラグ(jsp) */
    protected String pageFlg;

    /** 検索結果 */
    protected MailWithAttachmentsSendModel model = new MailWithAttachmentsSendModel();

    /** 予約No */
    protected String reservationNo;

    /**
     * 受講者ID(jsp)取得<br>
     * <br>
     * 受講者ID(jsp)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return studentId 受講者ID(jsp)<br>
     * @exception なし
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * 受講者ID(jsp)設定<br>
     * <br>
     * 受講者ID(jsp)設定を行う<br>
     * <br>
     * @param studentId 受講者ID(jsp)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * 受講者ニックネーム(jsp)取得<br>
     * <br>
     * 受講者ニックネーム(jsp)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return studentNickNm 受講者ニックネーム(jsp)<br>
     * @exception なし
     */
    public String getStudentNickNm() {
        return studentNickNm;
    }

    /**
     * 受講者ニックネーム(jsp)設定<br>
     * <br>
     * 受講者ニックネーム(jsp)設定を行う<br>
     * <br>
     * @param studentNickNm 受講者ニックネーム(jsp)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setStudentNickNm(String studentNickNm) {
        this.studentNickNm = studentNickNm;
    }

    /**
     * 講師ID(jsp)取得<br>
     * <br>
     * 講師ID(jsp)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return teacherId 講師ID(jsp)<br>
     * @exception なし
     */
    public String getTeacherId() {
        return teacherId;
    }

    /**
     * 講師ID(jsp)設定<br>
     * <br>
     * 講師ID(jsp)設定を行う<br>
     * <br>
     * @param teacherId 講師ID(jsp)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * 講師ニックネーム(jsp)取得<br>
     * <br>
     * 講師ニックネーム(jsp)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return teacherNickNm 講師ニックネーム(jsp)<br>
     * @exception なし
     */
    public String getTeacherNickNm() {
        return teacherNickNm;
    }

    /**
     * 講師ニックネーム(jsp)設定<br>
     * <br>
     * 講師ニックネーム(jsp)設定を行う<br>
     * <br>
     * @param teacherNickNm 講師ニックネーム(jsp)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setTeacherNickNm(String teacherNickNm) {
        this.teacherNickNm = teacherNickNm;
    }

    /**
     * 添付タイプ(jsp)取得<br>
     * <br>
     * 添付タイプ(jsp)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return tempFileContentType 添付タイプ(jsp)<br>
     * @exception なし
     */
    public String getTempFileContentType() {
        return tempFileContentType;
    }

    /**
     * 添付タイプ(jsp)設定<br>
     * <br>
     * 添付タイプ(jsp)設定を行う<br>
     * <br>
     * @param tempFileContentType 添付タイプ(jsp)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setTempFileContentType(String tempFileContentType) {
        this.tempFileContentType = tempFileContentType;
    }

    /**
     * 添付名(jsp)取得<br>
     * <br>
     * 添付名(jsp)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return tempFileFileName 添付名(jsp)<br>
     * @exception なし
     */
    public String getTempFileFileName() {
        return tempFileFileName;
    }

    /**
     * 添付名(jsp)設定<br>
     * <br>
     * 添付名(jsp)設定を行う<br>
     * <br>
     * @param tempFileFileName 添付名(jsp)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setTempFileFileName(String tempFileFileName) {
        this.tempFileFileName = tempFileFileName;
    }

    /**
     * 添付(jsp)取得<br>
     * <br>
     * 添付(jsp)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return tempFile 添付(jsp)<br>
     * @exception なし
     */
    public File getTempFile() {
        return tempFile;
    }

    /**
     * 添付(jsp)設定<br>
     * <br>
     * 添付(jsp)設定を行う<br>
     * <br>
     * @param tempFile 添付(jsp)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setTempFile(File tempFile) {
        this.tempFile = tempFile;
    }

    /**
     * 内容(jsp)取得<br>
     * <br>
     * 内容(jsp)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return commentTxt 内容(jsp)<br>
     * @exception なし
     */
    public String getCommentTxt() {
        return commentTxt;
    }

    /**
     * 内容(jsp)設定<br>
     * <br>
     * 内容(jsp)設定を行う<br>
     * <br>
     * @param commentTxt 内容(jsp)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setCommentTxt(String commentTxt) {
        this.commentTxt = commentTxt;
    }

    /**
     * 画面表示のタイトル取得<br>
     * <br>
     * 画面表示のタイトル取得を行う<br>
     * <br>
     * @param なし<br>
     * @return title 画面表示のタイトル<br>
     * @exception なし
     */
    public String getTitle() {
        return title;
    }

    /**
     * 画面表示のタイトル設定<br>
     * <br>
     * 画面表示のタイトル設定を行う<br>
     * <br>
     * @param title 画面表示のタイトル<br>
     * @return なし<br>
     * @exception なし
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Help画面名取得<br>
     * <br>
     * Help画面名取得を行う<br>
     * <br>
     * @param なし<br>
     * @return helpPageId Help画面名<br>
     * @exception なし
     */
    public String getHelpPageId() {
        return helpPageId;
    }

    /**
     * Help画面名設定<br>
     * <br>
     * Help画面名設定を行う<br>
     * <br>
     * @param helpPageId Help画面名<br>
     * @return なし<br>
     * @exception なし
     */
    public void setHelpPageId(String helpPageId) {
        this.helpPageId = helpPageId;
    }

    /**
     * メッセージ取得<br>
     * <br>
     * メッセージ取得を行う<br>
     * <br>
     * @param なし<br>
     * @return message メッセージ<br>
     * @exception なし
     */
    public String getMessage() {
        return message;
    }

    /**
     * メッセージ設定<br>
     * <br>
     * メッセージ設定を行う<br>
     * <br>
     * @param message メッセージ<br>
     * @return なし<br>
     * @exception なし
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * レッスン日(jsp)取得<br>
     * <br>
     * レッスン日(jsp)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return lessonDt レッスン日(jsp)<br>
     * @exception なし
     */
    public String getLessonDt() {
        return lessonDt;
    }

    /**
     * レッスン日(jsp)設定<br>
     * <br>
     * レッスン日(jsp)設定を行う<br>
     * <br>
     * @param lessonDt レッスン日(jsp)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setLessonDt(String lessonDt) {
        this.lessonDt = lessonDt;
    }

    /**
     * レッスン時刻(jsp)取得<br>
     * <br>
     * レッスン時刻(jsp)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return lessonTmNm レッスン時刻(jsp)<br>
     * @exception なし
     */
    public String getLessonTmNm() {
        return lessonTmNm;
    }

    /**
     * レッスン時刻(jsp)設定<br>
     * <br>
     * レッスン時刻(jsp)設定を行う<br>
     * <br>
     * @param lessonTmNm レッスン時刻(jsp)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setLessonTmNm(String lessonTmNm) {
        this.lessonTmNm = lessonTmNm;
    }

    /**
     * コース名の行(英語)(jsp)取得<br>
     * <br>
     * コース名の行(英語)(jsp)を行う<br>
     * <br>
     * @param なし<br>
     * @return courseEnmAll コース名の行(英語)(jsp)<br>
     * @exception なし
     */
    public String getCourseEnmAll() {
        return courseEnmAll;
    }

    /**
     * コース名の行(英語)(jsp)設定<br>
     * <br>
     * コース名の行(英語)(jsp)設定を行う<br>
     * <br>
     * @param courseEnmAll コース名の行(英語)(jsp)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setCourseEnmAll(String courseEnmAll) {
        this.courseEnmAll = courseEnmAll;
    }

    /**
     * コース名の行(jsp)取得<br>
     * <br>
     * コース名の行(jsp)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return courseJnmAll コース名の行(jsp)<br>
     * @exception なし
     */
    public String getCourseJnmAll() {
        return courseJnmAll;
    }

    /**
     * コース名の行(jsp)設定<br>
     * <br>
     * コース名の行(jsp)設定を行う<br>
     * <br>
     * @param courseJnmAll コース名の行(jsp)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setCourseJnmAll(String courseJnmAll) {
        this.courseJnmAll = courseJnmAll;
    }

    /**
     * 母国語(jsp)取得<br>
     * <br>
     * 母国語(jsp)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return nativeLanguage 母国語(jsp)<br>
     * @exception なし
     */
    public String getNativeLanguage() {
        return nativeLanguage;
    }

    /**
     * 母国語(jsp)設定<br>
     * <br>
     * 母国語(jsp)設定を行う<br>
     * <br>
     * @param nativeLanguage 母国語(jsp)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setNativeLanguage(String nativeLanguage) {
        this.nativeLanguage = nativeLanguage;
    }

    /**
     * 送信者(jsp)取得<br>
     * <br>
     * 送信者(jsp)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return teacherIdNm 送信者(jsp)<br>
     * @exception なし
     */
    public String getTeacherIdNm() {
        return teacherIdNm;
    }

    /**
     * 送信者(jsp)設定<br>
     * <br>
     * 送信者(jsp)設定を行う<br>
     * <br>
     * @param teacherIdNm 送信者(jsp)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setTeacherIdNm(String teacherIdNm) {
        this.teacherIdNm = teacherIdNm;
    }

    /**
     * 受信者(jsp)取得<br>
     * <br>
     * 受信者(jsp)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return studentIdNm 受信者(jsp)<br>
     * @exception なし
     */
    public String getStudentIdNm() {
        return studentIdNm;
    }

    /**
     * 受信者(jsp)設定<br>
     * <br>
     * 受信者(jsp)設定を行う<br>
     * <br>
     * @param studentIdNm 受信者(jsp)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setStudentIdNm(String studentIdNm) {
        this.studentIdNm = studentIdNm;
    }

    /**
     * 検索結果取得<br>
     * <br>
     * 検索結果取得を行う<br>
     * <br>
     * @param なし<br>
     * @return model 検索結果<br>
     * @exception なし
     */
    public MailWithAttachmentsSendModel getModel() {
        return model;
    }

    /**
     * 検索結果設定<br>
     * <br>
     * 検索結果設定を行う<br>
     * <br>
     * @param model 検索結果<br>
     * @return なし<br>
     * @exception なし
     */
    public void setModel(MailWithAttachmentsSendModel model) {
        this.model = model;
    }

    /**
     * 遷移元画面ID(jsp)取得<br>
     * <br>
     * 遷移元画面ID(jsp)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return pageId 遷移元画面ID(jsp)<br>
     * @exception なし
     */
    public String getPageId() {
        return pageId;
    }

    /**
     * 遷移元画面ID(jsp)設定<br>
     * <br>
     * 遷移元画面ID(jsp)設定を行う<br>
     * <br>
     * @param pageId 遷移元画面ID(jsp)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    /**
     * 戻るフラグ(jsp)取得<br>
     * <br>
     * 戻るフラグ(jsp)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return pageFlg 戻るフラグ(jsp)<br>
     * @exception なし
     */
    public String getPageFlg() {
        return pageFlg;
    }

    /**
     * 戻るフラグ(jsp)設定<br>
     * <br>
     * 戻るフラグ(jsp)設定を行う<br>
     * <br>
     * @param pageFlg 戻るフラグ(jsp)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setPageFlg(String pageFlg) {
        this.pageFlg = pageFlg;
    }

    /**
     * 予約No取得<br>
     * <br>
     * 予約No取得を行う<br>
     * <br>
     * @param なし<br>
     * @return reservationNo 予約No<br>
     * @exception なし
     */
    public String getReservationNo() {
        return reservationNo;
    }

    /**
     * 予約No設定<br>
     * <br>
     * 予約No設定を行う<br>
     * <br>
     * @param reservationNo 予約No<br>
     * @return なし<br>
     * @exception なし
     */
    public void setReservationNo(String reservationNo) {
        this.reservationNo = reservationNo;
    }
}