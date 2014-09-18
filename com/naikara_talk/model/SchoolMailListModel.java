package com.naikara_talk.model;

import java.util.List;

import com.naikara_talk.dto.SmaAccHisTSmaAccHisDetTStuMDto;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>顧客管理<br>
 * <b>クラス名称       :</b>スクールのメール送信・受信履歴照会<br>
 * <b>クラス概要       :</b>スクールのメール送信・受信履歴照会Model<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/12/16 TECS 新規作成
 */
public class SchoolMailListModel implements Model {

    private static final long serialVersionUID = 1L;

    /** チェック：問題なし(正常) */
    public static final int CHECK_OK = 0;

    /** メール送信日(From) */
    private String mailSendDtFr;

    /** メール送信日(To) */
    private String mailSendDtTo;

    /** メールパターン */
    private String mailPatternCd;

    /** 顧客区分 */
    private String customerKbn;

    /** 受講者ID */
    private String studentId;

    /** 受講者名(ニックネーム) */
    private String nickNm;

    /** 受講者名(フリガナ) */
    private String studentKnm;

    /** 組織名 */
    private String organizationNm;

    /** 検索結果一覧 */
    private List<SmaAccHisTSmaAccHisDetTStuMDto> resultList;

    /**
     * @return mailSendDtFr
     */
    public String getMailSendDtFr() {
        return mailSendDtFr;
    }

    /**
     * @param mailSendDtFr セットする mailSendDtFr
     */
    public void setMailSendDtFr(String mailSendDtFr) {
        this.mailSendDtFr = mailSendDtFr;
    }

    /**
     * @return mailSendDtTo
     */
    public String getMailSendDtTo() {
        return mailSendDtTo;
    }

    /**
     * @param mailSendDtTo セットする mailSendDtTo
     */
    public void setMailSendDtTo(String mailSendDtTo) {
        this.mailSendDtTo = mailSendDtTo;
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
     * @return customerKbn
     */
    public String getCustomerKbn() {
        return customerKbn;
    }

    /**
     * @param customerKbn セットする customerKbn
     */
    public void setCustomerKbn(String customerKbn) {
        this.customerKbn = customerKbn;
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
     * @return studentKnm
     */
    public String getStudentKnm() {
        return studentKnm;
    }

    /**
     * @param studentKnm セットする studentKnm
     */
    public void setStudentKnm(String studentKnm) {
        this.studentKnm = studentKnm;
    }

    /**
     * @return organizationNm
     */
    public String getOrganizationNm() {
        return organizationNm;
    }

    /**
     * @param organizationNm セットする organizationNm
     */
    public void setOrganizationNm(String organizationNm) {
        this.organizationNm = organizationNm;
    }

    /**
     * @return resultList
     */
    public List<SmaAccHisTSmaAccHisDetTStuMDto> getResultList() {
        return resultList;
    }

    /**
     * @param resultList セットする resultList
     */
    public void setResultList(List<SmaAccHisTSmaAccHisDetTStuMDto> resultList) {
        this.resultList = resultList;
    }

}