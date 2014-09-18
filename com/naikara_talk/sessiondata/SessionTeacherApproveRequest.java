package com.naikara_talk.sessiondata;

import java.util.List;

import com.naikara_talk.model.TeacherApproveRequestListModel;


/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>講師。<br>
 * <b>クラス名称　　　:</b>応相談回答画面のセッション情報クラス。<br>
 * <b>クラス概要　　　:</b>応相談回答画面の戻る用のセッション情報。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>
 * 2014/06/02 TECS 新規作成。
 */

public class SessionTeacherApproveRequest implements SessionData {

    /** ヘッダの戻るリンク用のSessionKey */
    private static final String HEADER_RETURN_KEY = "naikara_talk.sessiondata.SessionTeacherApproveRequest";

    /** 利用者ID(講師ID) */
    protected String userId;

    /** 講師ニックネーム */
    protected String userNm;

    /** 回答区分-選択値 */
    protected String replyKbn;

    /** 件名 */
    protected String subject;

    /** メール本文 */
    protected String emailText;

    /** 検索結果 */
    private List<TeacherApproveRequestListModel> targetList;

    /** 一覧から選択された明細-Key */
    protected String[] selectReservationNo;


    /**
     * このセッションデータのキーを返却する
     */
    public static String getKey() {
        return HEADER_RETURN_KEY;
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
     * @return targetList
     */
    public List<TeacherApproveRequestListModel> getTargetList() {
        return targetList;
    }

    /**
     * @param targetList セットする targetList
     */
    public void setTargetList(List<TeacherApproveRequestListModel> targetList) {
        this.targetList = targetList;
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


}
