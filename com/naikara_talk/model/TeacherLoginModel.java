package com.naikara_talk.model;

import com.naikara_talk.dto.UserMstDto;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>初期処理<br>
 * <b>クラス名称　　　:</b>サービス提供ページModelクラス。<br>
 * <b>クラス概要　　　:</b>講師の認証処理Model。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/02 TECS 新規作成。
 */
public class TeacherLoginModel implements Model {

    private static final long serialVersionUID = 1L;

    /* チェック：問題なし(正常) */
    public static final int CHECK_OK = 0;

    /** ログインＩＤ */
    protected String loginId;

    /** パスワード */
    protected String password;

    /** パスワードチェク */
    private String passwordChk;

    /** 検索結果一覧 */
    private UserMstDto dto;

    /** 利用者ID */
    private String userId;

    /** 名前(姓) */
    private String familyJnm;

    /** 名前(名) */
    private String firstJnm;

    /** 種別区分 */
    private String classificationKbn;

    /** 利用開始日 */
    private String useStartDt;

    /** 利用終了日 */
    private String useEndDt;

    /** ニックネーム */
    private String nickAnm;

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
     * @return dto
     */
    public UserMstDto getDto() {
        return dto;
    }

    /**
     * @param dto
     *            セットする dto
     */
    public void setDto(UserMstDto dto) {
        this.dto = dto;
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
     * @return classificationKbn
     */
    public String getClassificationKbn() {
        return classificationKbn;
    }

    /**
     * @param classificationKbn セットする classificationKbn
     */
    public void setClassificationKbn(String classificationKbn) {
        this.classificationKbn = classificationKbn;
    }

    /**
     * @return useStartDt
     */
    public String getUseStartDt() {
        return useStartDt;
    }

    /**
     * @param useStartDt セットする useStartDt
     */
    public void setUseStartDt(String useStartDt) {
        this.useStartDt = useStartDt;
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

    /**
     * @return nickAnm
     */
    public String getNickAnm() {
        return nickAnm;
    }

    /**
     * @param nickAnm セットする nickAnm
     */
    public void setNickAnm(String nickAnm) {
        this.nickAnm = nickAnm;
    }

}