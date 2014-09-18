package com.naikara_talk.action;

import com.naikara_talk.model.TeacherMyPageModel;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>講師用個別マイページ。<br>
 * <b>クラス名称       :</b>講師用個別マイページ初期処理Actionクラス。<br>
 * <b>クラス概要       :</b>講師が下記の情報照会ができる<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/09 TECS 新規作成
 */
public abstract class TeacherMyPageActionSupport extends NaikaraActionSupport {
    private static final long serialVersionUID = 1L;

    // 画面表示のタイトル
    protected String title = "Teacher My Page";

    // Help画面名
    protected String helpPageId = "HelpTeacherMyPage.html";

    /** 検索結果 */
    protected TeacherMyPageModel model = new TeacherMyPageModel();

    /** スクール側からお知らせ */
    protected String osiraseJ;
    /** スクール側からお知らせ */
    protected String osiraseE;
    /** 利用者ID */
    protected String userId;
    /** ニックネーム  */
    protected String nickAnm;
    /** 問合せメールアドレス(講師用個別マイページ用) */
    protected String mailAdd;
    /** メッセージ */
    protected String message;
    /** 遷移元画面 */
    protected String frontPageId;
    /** ｢予約状況｣の選択された｢日本日付｣ */
    protected String lessonDt;
    /** ｢予約状況｣の選択された｢日本時刻｣ */
    protected String lessonTmNm;
    /** ｢予約状況｣の選択された｢コース名｣ */
    protected String courseEnmAll;
    /** ｢予約状況｣の選択された(隠しﾌｨｰﾙﾄﾞ)｢受講者ID｣ */
    protected String studentId;
    /** ｢予約状況｣の選択された｢受講者名｣ */
    protected String studentNickNm;
    /** ニックネーム */
    protected String teacherNickNm;
    /** (隠しﾌｨｰﾙﾄﾞ) 講師ID */
    protected String teacherId;
    /** ｢予約状況｣の選択された｢コース名｣の日本語の行 */
    protected String courseJnmAll;

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title セットする title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return helpPageId
     */
    public String getHelpPageId() {
        return helpPageId;
    }

    /**
     * @param helpPageId セットする helpPageId
     */
    public void setHelpPageId(String helpPageId) {
        this.helpPageId = helpPageId;
    }

    /**
     * @return model
     */
    public TeacherMyPageModel getModel() {
        return model;
    }

    /**
     * @param model セットする model
     */
    public void setModel(TeacherMyPageModel model) {
        this.model = model;
    }

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
     * @return nickAnm
     */
    public String getNickAnm() {
        return nickAnm;
    }

    /**
     * @param nickAnm セットする nickAnm
     */
    public void setNickAnm(String nickAnm) {
        this.nickAnm = nickAnm;
    }

    /**
     * @return mailAdd
     */
    public String getMailAdd() {
        return mailAdd;
    }

    /**
     * @param mailAdd セットする mailAdd
     */
    public void setMailAdd(String mailAdd) {
        this.mailAdd = mailAdd;
    }

    /**
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message セットする message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return osiraseJ
     */
    public String getOsiraseJ() {
        return osiraseJ;
    }

    /**
     * @param osiraseJ セットする osiraseJ
     */
    public void setOsiraseJ(String osiraseJ) {
        this.osiraseJ = osiraseJ;
    }

    /**
     * @return osiraseE
     */
    public String getOsiraseE() {
        return osiraseE;
    }

    /**
     * @param osiraseE セットする osiraseE
     */
    public void setOsiraseE(String osiraseE) {
        this.osiraseE = osiraseE;
    }

    /**
     * @return frontPageId
     */
    public String getFrontPageId() {
        return frontPageId;
    }

    /**
     * @param frontPageId セットする frontPageId
     */
    public void setFrontPageId(String frontPageId) {
        this.frontPageId = frontPageId;
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

}
