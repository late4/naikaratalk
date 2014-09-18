package com.naikara_talk.model;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>ログイン・メニュー。<br>
 * <b>クラス名称　　　:</b>会社側のログイン処理Modelクラス。<br>
 * <b>クラス概要　　　:</b>会社側のログイン処理Model。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/02 TECS 新規作成。
 */
public class SchoolLoginModel implements Model {

    private static final long serialVersionUID = 1L;

    /* チェック：問題なし(正常) */
    public static final int CHECK_OK = 0;

    /** ログインID */
    private String loginId;

    /** パスワード */
    private String password;

    /** パスワードチェク */
    private String passwordChk;

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

    /**
     * ログインID取得<br>
     * <br>
     * ログインID取得を行う<br>
     * <br>
     * @param なし<br>
     * @return loginId ログインID<br>
     * @exception なし
     */
    public String getLoginId() {
        return loginId;
    }

    /**
     * ログインID設定<br>
     * <br>
     * ログインID設定を行う<br>
     * <br>
     * @param loginId ログインID<br>
     * @return なし<br>
     * @exception なし
     */
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    /**
     * パスワード取得<br>
     * <br>
     * パスワード取得を行う<br>
     * <br>
     * @param なし<br>
     * @return password パスワード<br>
     * @exception なし
     */
    public String getPassword() {
        return password;
    }

    /**
     * パスワード設定<br>
     * <br>
     * パスワード設定を行う<br>
     * <br>
     * @param password パスワード<br>
     * @return なし<br>
     * @exception なし
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 利用者ID取得<br>
     * <br>
     * 利用者ID取得を行う<br>
     * <br>
     * @param なし<br>
     * @return userId 利用者ID<br>
     * @exception なし
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 利用者ID設定<br>
     * <br>
     * 利用者ID設定を行う<br>
     * <br>
     * @param userId 利用者ID<br>
     * @return なし<br>
     * @exception なし
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 名前(姓)取得<br>
     * <br>
     * 名前(姓)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return familyJnm 名前(姓)<br>
     * @exception なし
     */
    public String getFamilyJnm() {
        return familyJnm;
    }

    /**
     * 名前(姓)設定<br>
     * <br>
     * 名前(姓)設定を行う<br>
     * <br>
     * @param familyJnm 名前(姓)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setFamilyJnm(String familyJnm) {
        this.familyJnm = familyJnm;
    }

    /**
     * 名前(名)取得<br>
     * <br>
     * 名前(名)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return firstJnm 名前(名)<br>
     * @exception なし
     */
    public String getFirstJnm() {
        return firstJnm;
    }

    /**
     * 名前(名)設定<br>
     * <br>
     * 名前(名)設定を行う<br>
     * <br>
     * @param firstJnm 名前(名)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setFirstJnm(String firstJnm) {
        this.firstJnm = firstJnm;
    }

    /**
     * 種別区分取得<br>
     * <br>
     * 種別区分取得を行う<br>
     * <br>
     * @param なし<br>
     * @return classificationKbn 種別区分<br>
     * @exception なし
     */
    public String getClassificationKbn() {
        return classificationKbn;
    }

    /**
     * 種別区分設定<br>
     * <br>
     * 種別区分設定を行う<br>
     * <br>
     * @param classificationKbn 種別区分<br>
     * @return なし<br>
     * @exception なし
     */
    public void setClassificationKbn(String classificationKbn) {
        this.classificationKbn = classificationKbn;
    }

    /**
     * 利用開始日取得<br>
     * <br>
     * 利用開始日取得を行う<br>
     * <br>
     * @param なし<br>
     * @return useStartDt 利用開始日<br>
     * @exception なし
     */
    public String getUseStartDt() {
        return useStartDt;
    }

    /**
     * 利用開始日設定<br>
     * <br>
     * 利用開始日設定を行う<br>
     * <br>
     * @param useStartDt 利用開始日<br>
     * @return なし<br>
     * @exception なし
     */
    public void setUseStartDt(String useStartDt) {
        this.useStartDt = useStartDt;
    }

    /**
     * 利用終了日取得<br>
     * <br>
     * 利用終了日取得を行う<br>
     * <br>
     * @param なし<br>
     * @return useEndDt 利用終了日<br>
     * @exception なし
     */
    public String getUseEndDt() {
        return useEndDt;
    }

    /**
     * 利用終了日設定<br>
     * <br>
     * 利用終了日設定を行う<br>
     * <br>
     * @param useEndDt 利用終了日<br>
     * @return なし<br>
     * @exception なし
     */
    public void setUseEndDt(String useEndDt) {
        this.useEndDt = useEndDt;
    }

    /**
     * パスワードチェク取得<br>
     * <br>
     * パスワードチェク取得を行う<br>
     * <br>
     * @param なし<br>
     * @return passwordChk パスワードチェク<br>
     * @exception なし
     */
    public String getPasswordChk() {
        return passwordChk;
    }

    /**
     * パスワードチェク設定<br>
     * <br>
     * パスワードチェク設定を行う<br>
     * <br>
     * @param passwordChk パスワードチェク<br>
     * @return なし<br>
     * @exception なし
     */
    public void setPasswordChk(String passwordChk) {
        this.passwordChk = passwordChk;
    }

}