package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_初期処理<br>
 * <b>クラス名称       :</b>受講者用マイページ画面遷移Actionクラス<br>
 * <b>クラス概要       :</b>各画面へ遷移する。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/16 TECS 新規作成
 */
public class StudentMyPageMoveAction extends StudentMyPageActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 画面遷移前処理。<br>
     * <br>
     * 画面遷移前処理を行う。<br>
     * <br>
     * @param なし<br>
     * @return String<br>
     * @throws NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // ヘッダの戻るリンク用
        setCurrentActionName(NaikaraTalkConstants.STUDENT_MY_PAGE_LOAD);

        if (StringUtils.equals(NaikaraTalkConstants.TEACHER_BOOKMARK_LIST_LOAD, pageId)) {
            SessionUser sessionUserData = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString()));
            this.studentId = sessionUserData.getUserId();

        }
        if (StringUtils.equals(NaikaraTalkConstants.MAIL_WITH_ATTACHMENTS_SEND_STUDENT_LOAD, pageId)) {
            SessionUser sessionUserData = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString()));
            this.studentId = sessionUserData.getUserId();

        }

        // 画面を返す
        return NEXTPAGE;
    }

    /** 画面ID */
    private String pageId;
    /** 受講者ID */
    private String studentId;
    /** ｢現在予約中のレッスン｣の選択された｢日付｣ */
    private String lessonDt;
    /** ｢現在予約中のレッスン｣の選択された｢予約時間｣ */
    private String lessonTmNm;
    /** ｢現在予約中のレッスン｣の選択された｢コース名｣ */
    private String courseJnmAll;
    /** ｢現在予約中のレッスン｣の選択された｢(隠し) 講師ID｣ */
    private String teacherId;
    /** ｢現在予約中のレッスン｣の選択された｢講師名｣ */
    private String teacherNickNm;
    /** ｢現在予約中のレッスン｣の選択された｢(隠し) コース名(英語)｣ */
    private String courseEnmAll;
    /** ニックネーム */
    private String nickNm;
    /** 遷移元 */
    private String frontPageId;

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
     * @return nickNm
     */
    public String getNickNm() {
        return nickNm;
    }

    /**
     * @param nickNm セットする nickNm
     */
    public void setNickNm(String nickNm) {
        this.nickNm = nickNm;
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

}
