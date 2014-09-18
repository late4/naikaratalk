package com.naikara_talk.sessiondata;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守。<br>
 * <b>クラス名称　　　:</b>利用者マスタメンテナンスのセッション情報クラス。<br>
 * <b>クラス概要　　　:</b>利用者マスタメンテナンスの戻る用のセッション情報。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/04 TECS 新規作成。
 */

public class SessionUserMst implements SessionData {

    /** ヘッダの戻るリンク用のSessionKey */
    private static final String HEADER_RETURN_KEY = "UserMstList";

    /** 戻る判定Onフラグ */
    private boolean returnOnFlg;

    /** 処理区分 */
    private String processKbn;

    /** 利用者ID */
    private String userId;

    /** 利用者名(フリガナ) */
    private String userKnm;

    /** 利用状態 */
    private String useKbn;

    /** 検索Key：利用者ID */
    private String searchUserId;

    /** 検索Key：利用者名(フリガナ) */
    private String searchUserKnm;

    /** 検索Key：利用状態 */
    private String searchUseKbn;

    /** 検索Key：選択された明細のKey-利用者ID */
    private String searchUserIdKey;

    /** 検索判断フラグ */
    private String hasSearchFlg;

    /**
     * このセッションデータのキーを返却する
     */
    public static String getKey() {
        return HEADER_RETURN_KEY;
    }

    /**
     * 検索判断フラグ取得<br>
     * <br>
     * 検索判断フラグ取得を行う<br>
     * <br>
     * @param なし<br>
     * @return hasSearchFlg 検索判断フラグ<br>
     * @exception なし
     */
    public String getHasSearchFlg() {
        return hasSearchFlg;
    }

    /**
     * 検索判断フラグ設定<br>
     * <br>
     * 検索判断フラグ設定を行う<br>
     * <br>
     * @param hasSearchFlg 検索判断フラグ<br>
     * @return なし<br>
     * @exception なし
     */
    public void setHasSearchFlg(String hasSearchFlg) {
        this.hasSearchFlg = hasSearchFlg;
    }

    /**
     * 戻る判定Onフラグ取得<br>
     * <br>
     * 戻る判定Onフラグ取得を行う<br>
     * <br>
     * @param なし<br>
     * @return returnOnFlg 戻る判定Onフラグ<br>
     * @exception なし
     */
    public boolean getReturnOnFlg() {
        return returnOnFlg;
    }

    /**
     * 戻る判定Onフラグ設定<br>
     * <br>
     * 戻る判定Onフラグ設定を行う<br>
     * <br>
     * @param returnOnFlg 戻る判定Onフラグ<br>
     * @return なし<br>
     * @exception なし
     */
    public void setReturnOnFlg(boolean returnOnFlg) {
        this.returnOnFlg = returnOnFlg;
    }

    /**
     * 処理区分取得<br>
     * <br>
     * 処理区分取得を行う<br>
     * <br>
     * @param なし<br>
     * @return processKbn 処理区分<br>
     * @exception なし
     */
    public String getProcessKbn() {
        return processKbn;
    }

    /**
     * 処理区分設定<br>
     * <br>
     * 処理区分設定を行う<br>
     * <br>
     * @param processKbn 処理区分<br>
     * @return なし<br>
     * @exception なし
     */
    public void setProcessKbn(String processKbn) {
        this.processKbn = processKbn;
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
     * 利用者名(フリガナ)取得<br>
     * <br>
     * 利用者名(フリガナ)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return userKnm 利用者名(フリガナ)<br>
     * @exception なし
     */
    public String getUserKnm() {
        return userKnm;
    }

    /**
     * 利用者名(フリガナ)設定<br>
     * <br>
     * 利用者名(フリガナ)設定を行う<br>
     * <br>
     * @param userKnm 利用者名(フリガナ)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setUserKnm(String userKnm) {
        this.userKnm = userKnm;
    }

    /**
     * 利用状態取得<br>
     * <br>
     * 利用状態取得を行う<br>
     * <br>
     * @param なし<br>
     * @return useKbn 利用状態<br>
     * @exception なし
     */
    public String getUseKbn() {
        return useKbn;
    }

    /**
     * 利用状態設定<br>
     * <br>
     * 利用状態設定を行う<br>
     * <br>
     * @param useKbn 利用状態<br>
     * @return なし<br>
     * @exception なし
     */
    public void setUseKbn(String useKbn) {
        this.useKbn = useKbn;
    }

    /**
     * 検索Key：利用者ID取得<br>
     * <br>
     * 検索Key：利用者ID取得を行う<br>
     * <br>
     * @param なし<br>
     * @return searchUserId 検索Key：利用者ID<br>
     * @exception なし
     */
    public String getSearchUserId() {
        return searchUserId;
    }

    /**
     * 検索Key：利用者ID設定<br>
     * <br>
     * 検索Key：利用者ID設定を行う<br>
     * <br>
     * @param searchUserId 検索Key：利用者ID<br>
     * @return なし<br>
     * @exception なし
     */
    public void setSearchUserId(String searchUserId) {
        this.searchUserId = searchUserId;
    }

    /**
     * 検索Key：利用者名(フリガナ)取得<br>
     * <br>
     * 検索Key：利用者名(フリガナ)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return searchUserKnm 検索Key：利用者名(フリガナ)<br>
     * @exception なし
     */
    public String getSearchUserKnm() {
        return searchUserKnm;
    }

    /**
     * 検索Key：利用者名(フリガナ)設定<br>
     * <br>
     * 検索Key：利用者名(フリガナ)設定を行う<br>
     * <br>
     * @param searchUserKnm 検索Key：利用者名(フリガナ)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setSearchUserKnm(String searchUserKnm) {
        this.searchUserKnm = searchUserKnm;
    }

    /**
     * 検索Key：利用状態取得<br>
     * <br>
     * 検索Key：利用状態取得を行う<br>
     * <br>
     * @param なし<br>
     * @return searchUseKbn 検索Key：利用状態<br>
     * @exception なし
     */
    public String getSearchUseKbn() {
        return searchUseKbn;
    }

    /**
     * 検索Key：利用状態設定<br>
     * <br>
     * 検索Key：利用状態設定を行う<br>
     * <br>
     * @param setSearchUseKbn 検索Key：利用状態<br>
     * @return なし<br>
     * @exception なし
     */
    public void setSearchUseKbn(String searchUseKbn) {
        this.searchUseKbn = searchUseKbn;
    }

    /**
     * 検索Key：選択された明細のKey-利用者ID取得<br>
     * <br>
     * 検索Key：選択された明細のKey-利用者ID取得を行う<br>
     * <br>
     * @param なし<br>
     * @return searchUserIdKey 検索Key：選択された明細のKey-利用者ID<br>
     * @exception なし
     */
    public String getSearchUserIdKey() {
        return searchUserIdKey;
    }

    /**
     * 検索Key：選択された明細のKey-利用者ID設定<br>
     * <br>
     * 検索Key：選択された明細のKey-利用者ID設定を行う<br>
     * <br>
     * @param searchUserIdKey 検索Key：選択された明細のKey-利用者ID<br>
     * @return なし<br>
     * @exception なし
     */
    public void setSearchUserIdKey(String searchUserIdKey) {
        this.searchUserIdKey = searchUserIdKey;
    }
}