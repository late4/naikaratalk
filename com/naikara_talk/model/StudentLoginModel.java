package com.naikara_talk.model;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様（個人）<br>
 * <b>クラス名称　　　:</b>ログイン(お客様（個人）)Modelクラス。<br>
 * <b>クラス概要　　　:</b>お客様（個人）責任者の認証処理Model。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/25 TECS 新規作成。
 */
public class StudentLoginModel implements Model {

    private static final long serialVersionUID = 1L;

    /** チェック：問題なし(正常) */
    public static final int CHECK_OK = 0;

    /** ログインＩＤ */
    private String loginId;

    /** パスワード */
    private String password;

    /** パスワードチェク */
    private String passwordChk;

    /** レコードバージョン番号 */
    private int recordVerNo;

    /** 受講者ID */
    private String studentId;

    /** ユーザ表示名 */
    private String nickNm;

    /** 保護者の同意書の入手フラグ */
    private String consentDocumentAcquisitionFlg;

    /** ポイント購入済フラグ */
    private String pointPurchaseFlg;

    /** 利用開始日 */
    private String useStrDt;

    /** 利用終了日 */
    private String useEndDt;

    /** 名前(姓) */
    private String familyJnm;

    /** 名前(名) */
    private String firstJnm;

    /** 顧客区分 */
    private String customerKbn;



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
     * @return recordVerNo
     */
    public int getRecordVerNo() {
        return recordVerNo;
    }

    /**
     * @param recordVerNo
     *            セットする recordVerNo
     */
    public void setRecordVerNo(int recordVerNo) {
        this.recordVerNo = recordVerNo;
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
     * @return consentDocumentAcquisitionFlg
     */
    public String getConsentDocumentAcquisitionFlg() {
        return consentDocumentAcquisitionFlg;
    }

    /**
     * @param consentDocumentAcquisitionFlg セットする consentDocumentAcquisitionFlg
     */
    public void setConsentDocumentAcquisitionFlg(String consentDocumentAcquisitionFlg) {
        this.consentDocumentAcquisitionFlg = consentDocumentAcquisitionFlg;
    }

    /**
     * @return pointPurchaseFlg
     */
    public String getPointPurchaseFlg() {
        return pointPurchaseFlg;
    }

    /**
     * @param pointPurchaseFlg セットする pointPurchaseFlg
     */
    public void setPointPurchaseFlg(String pointPurchaseFlg) {
        this.pointPurchaseFlg = pointPurchaseFlg;
    }

    /**
     * @return useStrDt
     */
    public String getUseStrDt() {
        return useStrDt;
    }

    /**
     * @param useStrDt セットする useStrDt
     */
    public void setUseStrDt(String useStrDt) {
        this.useStrDt = useStrDt;
    }

    /**
     * @return useEndDt
     */
    public String getUseEndDt() {
        return useEndDt;
    }

    /**
     * @param useEndDt セットする useEndDt
     */
    public void setUseEndDt(String useEndDt) {
        this.useEndDt = useEndDt;
    }

    /**
     * @return familyJnm
     */
    public String getFamilyJnm() {
        return familyJnm;
    }

    /**
     * @param familyJnm セットする familyJnm
     */
    public void setFamilyJnm(String familyJnm) {
        this.familyJnm = familyJnm;
    }

    /**
     * @return firstJnm
     */
    public String getFirstJnm() {
        return firstJnm;
    }

    /**
     * @param firstJnm セットする firstJnm
     */
    public void setFirstJnm(String firstJnm) {
        this.firstJnm = firstJnm;
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