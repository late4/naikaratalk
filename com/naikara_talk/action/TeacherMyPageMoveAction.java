package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>講師用個別マイページ。<br>
 * <b>クラス名称　　　:</b>講師用個別マイページ遷移Actionクラス。<br>
 * <b>クラス概要　　　:</b>講師用個別マイページ遷移の処理。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b> 2013/07/09 TECS 新規作成。
 *                    :</b> 2014/04/10 TECS 変更 予約Noの追加(添付付メール送信画面用)
 */
public class TeacherMyPageMoveAction extends TeacherMyPageActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 各ボタン押下時の処理。<br>
     * <br>
     * @param なし
     * @return String NEXTPAGE
     * @throws NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // ヘッダの戻るリンク用
        setCurrentActionName(NaikaraTalkConstants.TEACHER_MY_PAGE_LOAD);

        // 詳細画面遷移
        return NEXTPAGE;
    }

    /** 遷移Actionネーム */
    private String pageId;
    /** ｢予約状況｣の選択された｢日本日付｣ */
    private String lessonDt;
    /** ｢予約状況｣の選択された｢日本時刻｣ */
    private String lessonTm;
    /** ｢予約状況｣の選択された｢コース名｣ */
    private String courseEnm;
    /** ｢予約状況｣の選択された(隠しﾌｨｰﾙﾄﾞ)｢受講者ID｣ */
    private String studentId;
    /** ｢予約状況｣の選択された｢受講者名｣ */
    private String nickNm;
    /** (隠しﾌｨｰﾙﾄﾞ) 講師ID */
    private String userId;
    /** ニックネーム */
    private String nickAnm;
    /** ｢予約状況｣の選択された｢コース名｣の日本語の行 */
    private String courseJnm;
    /** 予約No */
    private String reservationNo;

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
     * @return lessonTm
     */
    public String getLessonTm() {
        return lessonTm;
    }

    /**
     * @param lessonTm セットする lessonTm
     */
    public void setLessonTm(String lessonTm) {
        this.lessonTm = lessonTm;
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
