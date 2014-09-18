package com.naikara_talk.model;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>法人<br>
 * <b>クラス名称　　　:</b>ログイン(組織)Modelクラス。<br>
 * <b>クラス概要　　　:</b>組織責任者の認証処理Model。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/05 TECS 新規作成。
 */
public class OrganizationLoginModel implements Model {

    private static final long serialVersionUID = 1L;

    /* チェック：問題なし(正常) */
    public static final int CHECK_OK = 0;

    /** ログインＩＤ */
    protected String loginId;

    /** パスワード */
    protected String password;

    /** パスワードチェク */
    private String passwordChk;

    /** 組織ID */
    private String organizationId;

    /** 責任者名(姓) */
    private String managFamilyJnm;

    /** 責任者名(名) */
    private String managFirstJnm;

    /** 連番 */
    private int consSeq;

    /** 組織名 */
    private String organizationJnm;

    /**
     * @return loginId
     */
    public String getLoginId() {
        return loginId;
    }

    /**
     * @param loginId
     *            セットする loginId
     */
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     *            セットする password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return organizationId
     */
    public String getOrganizationId() {
        return organizationId;
    }

    /**
     * @param organizationId セットする organizationId
     */
    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    /**
     * @return managFamilyJnm
     */
    public String getManagFamilyJnm() {
        return managFamilyJnm;
    }

    /**
     * @param managFamilyJnm セットする managFamilyJnm
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
     * @param managFirstJnm セットする managFirstJnm
     */
    public void setManagFirstJnm(String managFirstJnm) {
        this.managFirstJnm = managFirstJnm;
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

    /**
     * @return organizationJnm
     */
    public String getOrganizationJnm() {
        return organizationJnm;
    }

    /**
     * @param organizationJnm セットする organizationJnm
     */
    public void setOrganizationJnm(String organizationJnm) {
        this.organizationJnm = organizationJnm;
    }

    /**
     * @return passwordChk
     */
    public String getPasswordChk() {
        return passwordChk;
    }

    /**
     * @param passwordChk セットする passwordChk
     */
    public void setPasswordChk(String passwordChk) {
        this.passwordChk = passwordChk;
    }

}