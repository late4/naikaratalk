package com.naikara_talk.model;

import java.io.Serializable;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>バッチ<br>
 * <b>クラス名称       :</b>予約取消・購入のメール送信処理Modelクラス<br>
 * <b>クラス概要       :</b>各機能より起動されてＥメール送信処理を行う。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/04 TECS 新規作成
 */
public class SendMailModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 宛先ID */
    private String sendId;

    /** 宛先名 */
    private String sendNm;

    /** 送信者 */
    private String sendFrom;

    /** 宛先 */
    private String sendTo;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    /** CC */
    private String cc;

    /** BCC */
    private String bcc;

    /** 件名 */
    private String subjectTitle;

    /** 本文 */
    private String mailText;

    /** 添付ファイル */
    private String file;

    /** 備考 */
    private String remark;

    /**
     * @return sendId
     */
    public String getSendId() {
        return sendId;
    }

    /**
     * @param sendId セットする sendId
     */
    public void setSendId(String sendId) {
        this.sendId = sendId;
    }

    /**
     * @return sendNm
     */
    public String getSendNm() {
        return sendNm;
    }

    /**
     * @param sendNm セットする sendNm
     */
    public void setSendNm(String sendNm) {
        this.sendNm = sendNm;
    }

    /**
     * @return sendFrom
     */
    public String getSendFrom() {
        return sendFrom;
    }

    /**
     * @param sendFrom セットする sendFrom
     */
    public void setSendFrom(String sendFrom) {
        this.sendFrom = sendFrom;
    }

    /**
     * @return sendTo
     */
    public String getSendTo() {
        return sendTo;
    }

    /**
     * @param sendTo セットする sendTo
     */
    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }

    /**
     * @return cc
     */
    public String getCc() {
        return cc;
    }

    /**
     * @param cc セットする cc
     */
    public void setCc(String cc) {
        this.cc = cc;
    }

    /**
     * @return bcc
     */
    public String getBcc() {
        return bcc;
    }

    /**
     * @param bcc セットする bcc
     */
    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    /**
     * @return subjectTitle
     */
    public String getSubjectTitle() {
        return subjectTitle;
    }

    /**
     * @param subjecTitlet セットする subjectTitle
     */
    public void setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle;
    }

    /**
     * @return mailText
     */
    public String getMailText() {
        return mailText;
    }

    /**
     * @param mailText セットする mailText
     */
    public void setMailText(String mailText) {
        this.mailText = mailText;
    }

    /**
     * @return file
     */
    public String getFile() {
        return file;
    }

    /**
     * @param file セットする file
     */
    public void setFile(String file) {
        this.file = file;
    }

}