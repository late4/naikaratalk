package com.naikara_talk.model;

import java.io.Serializable;

import java.util.List;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 Modelクラス<br>
 * <b>クラス名称　　　:</b>MailManagMstMiddleModelクラス<br>
 * <b>クラス概要　　　:</b>sendMailBatch.sendMail呼出のための中間変数クラス<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2014/06/02 TECS 新規作成
 */
public class MailManagMstMiddleModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String mailPatternCd;               // メールパターンコード
    private String sendFrom;                    // 送信者アドレス
    private String sendFromNm;                  // 送信者名
    private List<String> sendIdList;            // 宛先IDリスト
    private List<String> sendToList;            // 宛先リスト
    private String cc;                          // CC
    private String bcc;                         // BCC
    private List<String> subjectTitleList;      // 件名リスト
    private List<List<String>> mailTextList;    // 本文リスト
    private String file;                        // 添付ファイル
    private String reservationNo;               // 予約No


    /**
     * メールパターンコード取得<br>
     * <br>
     * メールパターンコードを戻り値で返却する<br>
     * <br>
     * @return mailPatternCd
     */
    public String getMailPatternCd() {
        return mailPatternCd;
    }

    /**
     * メールパターンコード設定<br>
     * <br>
     * メールパターンコードを引数で設定する<br>
     * <br>
     * @param mailPatternCd
     */
    public void setMailPatternCd(String mailPatternCd) {
        this.mailPatternCd = mailPatternCd;
    }

    /**
     * 送信者アドレス取得<br>
     * <br>
     * 送信者アドレスを戻り値で返却する<br>
     * <br>
     * @return sendFrom
     */
    public String getSendFrom() {
        return sendFrom;
    }

    /**
     * 送信者アドレス設定<br>
     * <br>
     * 送信者アドレスを引数で設定する<br>
     * <br>
     * @param sendFrom
     */
    public void setSendFrom(String sendFrom) {
        this.sendFrom = sendFrom;
    }


    /**
     * 送信者名 取得<br>
     * <br>
     * 送信者名 を戻り値で返却する<br>
     * <br>
     * @return sendFromNm
     */
    public String getSendFromNm() {
        return sendFromNm;
    }

    /**
     * 送信者名 設定<br>
     * <br>
     * 送信者名 を引数で設定する<br>
     * <br>
     * @param sendFromNm
     */
    public void setSendFromNm(String sendFromNm) {
        this.sendFromNm = sendFromNm;
    }

    /**
     * 宛先IDリスト 取得<br>
     * <br>
     * 宛先IDリスト を戻り値で返却する<br>
     * <br>
     * @return sendIdList
     */
    public List<String> getSendIdList() {
        return sendIdList;
    }

    /**
     * 宛先IDリスト 設定<br>
     * <br>
     * 宛先IDリスト を引数で設定する<br>
     * <br>
     * @param sendIdList
     */
    public void setSendIdList(List<String> sendIdList) {
        this.sendIdList = sendIdList;
    }

    /**
     * 宛先リスト 取得<br>
     * <br>
     * 宛先リスト を戻り値で返却する<br>
     * <br>
     * @return sendToList
     */
    public List<String> getSendToList() {
        return sendToList;
    }

    /**
     * 宛先リスト 設定<br>
     * <br>
     * 宛先リスト を引数で設定する<br>
     * <br>
     * @param sendToList
     */
    public void setSendToList(List<String> sendToList) {
        this.sendToList = sendToList;
    }

    /**
     * CC取得<br>
     * <br>
     * CCを戻り値で返却する<br>
     * <br>
     * @return cc
     */
    public String getCc() {
        return cc;
    }

    /**
     * CC設定<br>
     * <br>
     * CCを引数で設定する<br>
     * <br>
     * @param cc
     */
    public void setCc(String cc) {
        this.cc = cc;
    }

    /**
     * BCC取得<br>
     * <br>
     * BCCを戻り値で返却する<br>
     * <br>
     * @return bcc
     */
    public String getBcc() {
        return bcc;
    }

    /**
     * BCC設定<br>
     * <br>
     * BCCを引数で設定する<br>
     * <br>
     * @param bcc
     */
    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    /**
     * 件名リスト 取得<br>
     * <br>
     * 件名リスト を戻り値で返却する<br>
     * <br>
     * @return subjectTitleList
     */
    public List<String> getSubjectTitleList() {
        return subjectTitleList;
    }

    /**
     * 件名リスト 設定<br>
     * <br>
     * 件名リスト を引数で設定する<br>
     * <br>
     * @param subjectTitleList
     */
    public void setSubjectTitleList(List<String> subjectTitleList) {
        this.subjectTitleList = subjectTitleList;
    }

    /**
     * 本文１取得<br>
     * <br>
     * 本文１を戻り値で返却する<br>
     * <br>
     * @return mailTextList
     */
    public List<List<String>> getMailTextList() {
        return mailTextList;
    }

    /**
     * 本文１設定<br>
     * <br>
     * 本文１を引数で設定する<br>
     * <br>
     * @param mailTextList
     */
    public void setMailTextList(List<List<String>> mailTextList) {
        this.mailTextList = mailTextList;
    }

    /**
     * 添付ファイル 取得<br>
     * <br>
     * 添付ファイル を戻り値で返却する<br>
     * <br>
     * @return file
     */
    public String getFile() {
        return file;
    }

    /**
     * 添付ファイル 設定<br>
     * <br>
     * 添付ファイル を引数で設定する<br>
     * <br>
     * @param file
     */
    public void setFile(String file) {
        this.file = file;
    }

    /**
     * 予約No 取得<br>
     * <br>
     * 予約No を戻り値で返却する<br>
     * <br>
     * @return reservationNo
     */
    public String getReservationNo() {
        return reservationNo;
    }

    /**
     * 予約No 設定<br>
     * <br>
     * 予約No を引数で設定する<br>
     * <br>
     * @param reservationNo
     */
    public void setReservationNo(String reservationNo) {
        this.reservationNo = reservationNo;
    }


}
