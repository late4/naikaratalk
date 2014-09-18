package com.naikara_talk.sessiondata;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>講師マスタメンテナンスのセッション情報クラス。<br>
 * <b>クラス概要       :</b>講師マスタメンテナンスの戻る用のセッション情報。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/18 TECS 新規作成
 */
public class SessionTeacherMst implements SessionData {

    /** ヘッダの戻るリンク用のSessionKey */
    private static final String HEADER_RETURN_KEY = "TeacherMstList";

    /** 戻る判定Onフラグ */
    private boolean returnOnFlg;

    /** 検索判断フラグ */
    private String hasSearchFlg;

    /** 処理区分 */
    private String processKbn;

    /** 講師ID */
    private String userId;

    /** 講師名(フリガナ) */
    private String userKnm;

    /** 検索Key：講師ID */
    private String searchUserId;

    /** 検索Key：講師名(フリガナ) */
    private String searchUserKnm;

    /** 検索Key：選択された明細のKey-ポイントコード */
    private String searchUserIdKey;

    /**
     * このセッションデータのキーを返却する
     */
    public static String getKey() {
        return HEADER_RETURN_KEY;
    }

    /**
     * @return returnOnFlg
     */
    public boolean getReturnOnFlg() {
        return returnOnFlg;
    }

    /**
     * @param returnOnFlg セットする returnOnFlg
     */
    public void setReturnOnFlg(boolean returnOnFlg) {
        this.returnOnFlg = returnOnFlg;
    }

    /**
     * @return hasSearchFlg
     */
    public String getHasSearchFlg() {
        return hasSearchFlg;
    }

    /**
     * @param hasSearchFlg セットする hasSearchFlg
     */
    public void setHasSearchFlg(String hasSearchFlg) {
        this.hasSearchFlg = hasSearchFlg;
    }

    /**
     * @return processKbn
     */
    public String getProcessKbn() {
        return processKbn;
    }

    /**
     * @param processKbn セットする processKbn
     */
    public void setProcessKbn(String processKbn) {
        this.processKbn = processKbn;
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
     * @return userKnm
     */
    public String getUserKnm() {
        return userKnm;
    }

    /**
     * @param userKnm セットする userKnm
     */
    public void setUserKnm(String userKnm) {
        this.userKnm = userKnm;
    }

    /**
     * @return searchUserId
     */
    public String getSearchUserId() {
        return searchUserId;
    }

    /**
     * @param searchUserId セットする searchUserId
     */
    public void setSearchUserId(String searchUserId) {
        this.searchUserId = searchUserId;
    }

    /**
     * @return searchUserKnm
     */
    public String getSearchUserKnm() {
        return searchUserKnm;
    }

    /**
     * @param searchUserKnm セットする searchUserKnm
     */
    public void setSearchUserKnm(String searchUserKnm) {
        this.searchUserKnm = searchUserKnm;
    }

    /**
     * @return searchUserIdKey
     */
    public String getSearchUserIdKey() {
        return searchUserIdKey;
    }

    /**
     * @param searchUserIdKey セットする searchUserIdKey
     */
    public void setSearchUserIdKey(String searchUserIdKey) {
        this.searchUserIdKey = searchUserIdKey;
    }
}