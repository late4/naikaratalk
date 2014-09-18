package com.naikara_talk.model;

import java.util.ArrayList;
import java.util.List;

import com.naikara_talk.dto.LessonReserveCancelResultListDto;
import com.naikara_talk.dto.PointProvisionDataListDto;
import com.naikara_talk.dto.PointReleaseDataListDto;
import com.naikara_talk.dto.SchResTLesResPerTDto;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_予約処理<br>
 * <b>クラス名称       :</b>予約／取消確認ページModelクラス<br>
 * <b>クラス概要       :</b>受講者が指定した講師の予約スケジュールを表示して、レッスン予約の登録／取消ができる。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/02 TECS 新規作成
 */
public class SComReservationCancellationConfirmationModel implements Model {

    private static final long serialVersionUID = 1L;

    /** ログインID */
    private String loginId;

    /** ログイン名 */
    private String loginNm;

    /** 遷移元画面ID */
    private String pageId;

    /** 講師ID */
    private String teacherId;

    /** 講師名（ニックネーム） */
    private String teacherNm;

    /** 一覧のDTOリスト */
    private List<SchResTLesResPerTDto> resultList = new ArrayList<SchResTLesResPerTDto>();

    /** 引当データリスト */
    private List<PointProvisionDataListDto> ppdlDtoList;

    /** 解除データリスト */
    private List<PointReleaseDataListDto> prdlDtoList;

    /** 予約フラグ */
    private boolean reserveFlg;

    /** 取消フラグ */
    private boolean cancelFlg;

    /** メールパターンコード */
    private String mailPatternCd;

    /** コース名(英語) */
    private String courseEnm;

    /** メッセージリスト */
    private List<String> messageList = new ArrayList<String>();

    /** ポイント引当・解除DTOリスト */
    List<LessonReserveCancelResultListDto> lrcrlDtoList = new ArrayList<LessonReserveCancelResultListDto>();

    /** 受講者メール送信フラグ */
    private List<Boolean> mailStudentFlgList = new ArrayList<Boolean>();

    /** 講師 メール送信フラグ*/
    private List<Boolean> mailTeacherFlgList = new ArrayList<Boolean>();

    /** コース名(英語)フラグ */
    private List<String> mailCourseEnmList = new ArrayList<String>();

    /**
     * @return loginId
     */
    public String getLoginId() {
        return loginId;
    }

    /**
     * @param loginId セットする loginId
     */
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    /**
     * @return loginNm
     */
    public String getLoginNm() {
        return loginNm;
    }

    /**
     * @param loginNm セットする loginNm
     */
    public void setLoginNm(String loginNm) {
        this.loginNm = loginNm;
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
     * @return resultList
     */
    public List<SchResTLesResPerTDto> getResultList() {
        return resultList;
    }

    /**
     * @param resultList セットする resultList
     */
    public void setResultList(List<SchResTLesResPerTDto> resultList) {
        this.resultList = resultList;
    }

    /**
     * @return ppdlDtoList
     */
    public List<PointProvisionDataListDto> getPpdlDtoList() {
        return ppdlDtoList;
    }

    /**
     * @param ppdlDtoList セットする ppdlDtoList
     */
    public void setPpdlDtoList(List<PointProvisionDataListDto> ppdlDtoList) {
        this.ppdlDtoList = ppdlDtoList;
    }

    /**
     * @return prdlDtoList
     */
    public List<PointReleaseDataListDto> getPrdlDtoList() {
        return prdlDtoList;
    }

    /**
     * @param prdlDtoList セットする prdlDtoList
     */
    public void setPrdlDtoList(List<PointReleaseDataListDto> prdlDtoList) {
        this.prdlDtoList = prdlDtoList;
    }

    /**
     * @return reserveFlg
     */
    public boolean getReserveFlg() {
        return reserveFlg;
    }

    /**
     * @param reserveFlg セットする reserveFlg
     */
    public void setReserveFlg(boolean reserveFlg) {
        this.reserveFlg = reserveFlg;
    }

    /**
     * @return cancelFlg
     */
    public boolean getCancelFlg() {
        return cancelFlg;
    }

    /**
     * @param cancelFlg セットする cancelFlg
     */
    public void setCancelFlg(boolean cancelFlg) {
        this.cancelFlg = cancelFlg;
    }

    /**
     * @return mailPatternCd
     */
    public String getMailPatternCd() {
        return mailPatternCd;
    }

    /**
     * @param mailPatternCd セットする mailPatternCd
     */
    public void setMailPatternCd(String mailPatternCd) {
        this.mailPatternCd = mailPatternCd;
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
     * @return messageList
     */
    public List<String> getMessageList() {
        return messageList;
    }

    /**
     * @param messageList セットする messageList
     */
    public void setMessageList(List<String> messageList) {
        this.messageList = messageList;
    }

    /**
     * @return lrcrlDtoList
     */
    public List<LessonReserveCancelResultListDto> getLrcrlDtoList() {
        return lrcrlDtoList;
    }

    /**
     * @param lrcrlDtoList セットする lrcrlDtoList
     */
    public void setLrcrlDtoList(List<LessonReserveCancelResultListDto> lrcrlDtoList) {
        this.lrcrlDtoList = lrcrlDtoList;
    }

    /**
     * @return mailStudentFlgList
     */
    public List<Boolean> getMailStudentFlgList() {
        return mailStudentFlgList;
    }

    /**
     * @param mailStudentFlgList セットする mailStudentFlgList
     */
    public void setMailStudentFlgList(List<Boolean> mailStudentFlgList) {
        this.mailStudentFlgList = mailStudentFlgList;
    }

    /**
     * @return mailTeacherFlgList
     */
    public List<Boolean> getMailTeacherFlgList() {
        return mailTeacherFlgList;
    }

    /**
     * @param mailTeacherFlgList セットする mailTeacherFlgList
     */
    public void setMailTeacherFlgList(List<Boolean> mailTeacherFlgList) {
        this.mailTeacherFlgList = mailTeacherFlgList;
    }

    /**
     * @return mailCourseEnmList
     */
    public List<String> getMailCourseEnmList() {
        return mailCourseEnmList;
    }

    /**
     * @param mailCourseEnmList セットする mailCourseEnmList
     */
    public void setMailCourseEnmList(List<String> mailCourseEnmList) {
        this.mailCourseEnmList = mailCourseEnmList;
    }

}