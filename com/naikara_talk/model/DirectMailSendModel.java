package com.naikara_talk.model;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>ダイレクトメール送信Modelクラス。<br>
 * <b>クラス概要       :</b>ダイレクトメール送信をおこなう。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/30 TECS 新規作成
 */
public class DirectMailSendModel implements Model {

    private static final long serialVersionUID = 1L;

    /** 件名 */
    private String subject;

    /** 画面の｢メール本文｣ */
    private String mailContent;

    /** 受講者IDリスト */
    private List<String> studentIdLst = new ArrayList<String>();

    /** メールアドレス1リスト */
    private List<String> mailAddrLst = new ArrayList<String>();

    /** ユーザID */
    private String userId;

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
     * @return mailContent
     */
    public String getMailContent() {
        return mailContent;
    }

    /**
     * @param mailContent セットする mailContent
     */
    public void setMailContent(String mailContent) {
        this.mailContent = mailContent;
    }

    /**
     * @return studentIdLst
     */
    public List<String> getStudentIdLst() {
        return studentIdLst;
    }

    /**
     * @param studentIdLst セットする studentIdLst
     */
    public void setStudentIdLst(List<String> studentIdLst) {
        this.studentIdLst = studentIdLst;
    }

    /**
     * @return mailAddrLst
     */
    public List<String> getMailAddrLst() {
        return mailAddrLst;
    }

    /**
     * @param mailAddrLst セットする mailAddrLst
     */
    public void setMailAddrLst(List<String> mailAddrLst) {
        this.mailAddrLst = mailAddrLst;
    }

    /**
     * @return serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
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

}
