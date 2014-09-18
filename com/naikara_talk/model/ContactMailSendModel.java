package com.naikara_talk.model;

import java.util.List;

import com.naikara_talk.dto.OrganizationMstDto;
import com.naikara_talk.dto.StudentMstDto;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_初期登録<br>
 * <b>クラス名称       :</b>問合せ画面Modelクラス。<br>
 * <b>クラス概要       :</b>メール送信をおこなう。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/30 TECS 新規作成
 */
public class ContactMailSendModel implements Model {

    private static final long serialVersionUID = 1L;

    /** チェック：問題なし(正常) */
    public static final int CHECK_OK = 0;

    /** 組織ID／受講者ID((前画面情報)) */
    private String organizationId;

    /** お問合せの目的(jsp) */
    private String codeCategoryContact;

    /** 件名(jsp) */
    private String subject;

    /** お名前(姓)(jsp) */
    private String managFamilyJnm;

    /** お名前(名)(jsp) */
    private String managFirstJnm;

    /** 電話番号(jsp) */
    private String tel;

    /** メールアドレス(jsp) */
    private String managMailAddress1;

    /** ご意見・ご要望・お問合せ内容(jsp) */
    private String contactText;

    /** 検索結果一覧 */
    private List<StudentMstDto> smresultList;
    private List<OrganizationMstDto> omresultList;

    /** 遷移元画面ID */
    private String frontPageId;

    /** 連番 */
    private int consSeq;

    /**
     * @return organizationId
     */
    public String getOrganizationId() {
        return organizationId;
    }

    /**
     * @param organizationId
     *            セットする organizationId
     */
    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    /**
     * @return codeCategoryContact
     */
    public String getCodeCategoryContact() {
        return codeCategoryContact;
    }

    /**
     * @param codeCategoryContact
     *            セットする codeCategoryContact
     */
    public void setCodeCategoryContact(String codeCategoryContact) {
        this.codeCategoryContact = codeCategoryContact;
    }

    /**
     * @return subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject
     *            セットする subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return managFamilyJnm
     */
    public String getManagFamilyJnm() {
        return managFamilyJnm;
    }

    /**
     * @param managFamilyJnm
     *            セットする managFamilyJnm
     */
    public void setManagFamilyJnm(String managFamilyJnm) {
        this.managFamilyJnm = managFamilyJnm;
    }

    /**
     * @return managFirstJnm
     */
    public String getManagFirstJnm() {
        return managFirstJnm;
    }

    /**
     * @param managFirstJnm
     *            セットする managFirstJnm
     */
    public void setManagFirstJnm(String managFirstJnm) {
        this.managFirstJnm = managFirstJnm;
    }

    /**
     * @return tel
     */
    public String getTel() {
        return tel;
    }

    /**
     * @param tel
     *            セットする tel
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * @return managMailAddress1
     */
    public String getManagMailAddress1() {
        return managMailAddress1;
    }

    /**
     * @param managMailAddress1
     *            セットする managMailAddress1
     */
    public void setManagMailAddress1(String managMailAddress1) {
        this.managMailAddress1 = managMailAddress1;
    }

    /**
     * @return contactText
     */
    public String getContactText() {
        return contactText;
    }

    /**
     * @param contactText
     *            セットする contactText
     */
    public void setContactText(String contactText) {
        this.contactText = contactText;
    }

    /**
     * @return smresultList
     */
    public List<StudentMstDto> getSmresultList() {
        return smresultList;
    }

    /**
     * @param smresultList
     *            セットする smresultList
     */
    public void setSmresultList(List<StudentMstDto> smresultList) {
        this.smresultList = smresultList;
    }

    /**
     * @return omresultList
     */
    public List<OrganizationMstDto> getOmresultList() {
        return omresultList;
    }

    /**
     * @param omresultList
     *            セットする omresultList
     */
    public void setOmresultList(List<OrganizationMstDto> omresultList) {
        this.omresultList = omresultList;
    }

    /**
     * @return frontPageId
     */
    public String getFrontPageId() {
        return frontPageId;
    }

    /**
     * @param frontPageId
     *            セットする frontPageId
     */
    public void setFrontPageId(String frontPageId) {
        this.frontPageId = frontPageId;
    }

    /**
     * @return consSeq
     */
    public int getConsSeq() {
        return consSeq;
    }

    /**
     * @param consSeq セットする consSeq
     */
    public void setConsSeq(int consSeq) {
        this.consSeq = consSeq;
    }

}
