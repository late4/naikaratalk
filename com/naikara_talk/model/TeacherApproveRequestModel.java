package com.naikara_talk.model;

import java.util.List;


/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>講師<br>
 * <b>クラス名称       :</b>応相談回答ページ初期処理Modelクラス。<br>
 * <b>クラス概要       :</b>応相談回答ページ初期処理Model。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2014/06/02 TECS 新規作成
 */
public class TeacherApproveRequestModel implements Model {

    private static final long serialVersionUID = 1L;

    /** 利用者ID */
    private String userId;

    /** 利用者名 ※ニックネーム※ */
    private String userNm;

    /** 画面：回答内容の選択値 */
    private String replyKbn;

    /** 画面：件名 */
    private String subject;

    /** 画面：メール本文 */
    private String emailText;

    /** 一覧から選択された明細 */
    private String[] selectReservationNo;

    /** 表示用のデータ一覧 */
    private List<TeacherApproveRequestListModel> targetList;

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
     * @return userNm
     */
    public String getUserNm() {
        return userNm;
    }

    /**
     * @param userNm セットする userNm
     */
    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    /**
     * @return replyKbn
     */
    public String getReplyKbn() {
        return replyKbn;
    }

    /**
     * @param replyKbn セットする replyKbn
     */
    public void setReplyKbn(String replyKbn) {
        this.replyKbn = replyKbn;
    }

    /**
     * @return subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject セットする subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return emailText
     */
    public String getEmailText() {
        return emailText;
    }

    /**
     * @param emailText セットする emailText
     */
    public void setEmailText(String emailText) {
        this.emailText = emailText;
    }

    /**
     * @return selectReservationNo
     */
    public String[] getSelectReservationNo() {
        return selectReservationNo;
    }

    /**
     * @param selectReservationNo セットする selectReservationNo
     */
    public void setSelectReservationNo(String[] selectReservationNo) {
        this.selectReservationNo = selectReservationNo;
    }

    /**
     * @return targetList
     */
    public List<TeacherApproveRequestListModel> getTargetList() {
        return targetList;
    }

    /**
     * @param targetList セットする targetList
     */
    public void setTargetList(
            List<TeacherApproveRequestListModel> targetList) {
        this.targetList = targetList;
    }

}