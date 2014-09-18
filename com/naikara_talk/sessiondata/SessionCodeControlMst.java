package com.naikara_talk.sessiondata;


/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>コード管理マスタメンテナンス。<br>
 * <b>クラス名称　　　:</b>コード管理マスタメンテナンスのセッション情報クラス。<br>
 * <b>クラス概要　　　:</b>コード管理マスタメンテナンスの戻る用のセッション情報。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>
 * 2013/07/16 TECS 新規作成。
 */

public class SessionCodeControlMst implements SessionData {

    /** ヘッダの戻るリンク用のSessionKey */
    private static final String HEADER_RETURN_KEY = "naikara_talk.sessiondata.CodeControlMst";

    /** 戻る判定Onフラグ */
    private boolean returnOnFlg;

    /** 処理区分 */
    private String processKbn;

    /** コード種別名(コード) */
    private String cdCategorySelected;

    /** 汎用コード */
    private String managerCd;

    /** 汎用フィールド */
    private String managerNm;


    /** 検索Key：コード種別名(コード) */
    private String searchCdCategorySelected;

    /** 検索Key：汎用コード */
    private String searchManagerCd;

    /** 検索Key：汎用フィールド */
    private String searchManagerNm;

    /** 検索Key：選択された明細のKey-コード種別名(コード) */
    private String searchCdCategoryKey;

    /** 検索Key：選択された明細のKey-汎用コード */
    private String searchManagerCdKey;

    /** 画面状態判断フラグ */
    private String hasSearchFlg;


    /**
     * 画面状態判断フラグを取得する
     * @return hasSearchFlg
     */
    public String getHasSearchFlg() {
        return hasSearchFlg;
    }

    /**
     * 画面状態判断フラグをセットする
     * @param hasSearchFlg セットする hasSearchFlg
     */
    public void setHasSearchFlg(String hasSearchFlg) {
        this.hasSearchFlg = hasSearchFlg;
    }

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
     * @return cdCategorySelected
     */
    public String getCdCategorySelected() {
        return cdCategorySelected;
    }

    /**
     * @param cdCategorySelected セットする cdCategorySelected
     */
    public void setCdCategorySelected(String cdCategorySelected) {
        this.cdCategorySelected = cdCategorySelected;
    }

    /**
     * @return managerCd
     */
    public String getManagerCd() {
        return managerCd;
    }

    /**
     * @param managerCd セットする managerCd
     */
    public void setManagerCd(String managerCd) {
        this.managerCd = managerCd;
    }

    /**
     * @return managerNm
     */
    public String getManagerNm() {
        return managerNm;
    }

    /**
     * @param managerNm セットする managerNm
     */
    public void setManagerNm(String managerNm) {
        this.managerNm = managerNm;
    }

    /**
     * @return searchCdCategorySelected
     */
    public String getSearchCdCategorySelected() {
        return searchCdCategorySelected;
    }

    /**
     * @param searchCdCategorySelected セットする searchCdCategorySelected
     */
    public void setSearchCdCategorySelected(String searchCdCategorySelected) {
        this.searchCdCategorySelected = searchCdCategorySelected;
    }

    /**
     * @return searchManagerCd
     */
    public String getSearchManagerCd() {
        return searchManagerCd;
    }

    /**
     * @param searchManagerCd セットする searchManagerCd
     */
    public void setSearchManagerCd(String searchManagerCd) {
        this.searchManagerCd = searchManagerCd;
    }

    /**
     * @return searchManagerNm
     */
    public String getSearchManagerNm() {
        return searchManagerNm;
    }

    /**
     * @param searchManagerNm セットする searchManagerNm
     */
    public void setSearchManagerNm(String searchManagerNm) {
        this.searchManagerNm = searchManagerNm;
    }

    /**
     * @return searchCdCategoryKey
     */
    public String getSearchCdCategoryKey() {
        return searchCdCategoryKey;
    }

    /**
     * @param searchCdCategoryKey セットする searchCdCategoryKey
     */
    public void setSearchCdCategoryKey(String searchCdCategoryKey) {
        this.searchCdCategoryKey = searchCdCategoryKey;
    }

    /**
     * @return searchManagerCdKey
     */
    public String getSearchManagerCdKey() {
        return searchManagerCdKey;
    }

    /**
     * @param searchManagerCdKey セットする searchManagerCdKey
     */
    public void setSearchManagerCdKey(String searchManagerCdKey) {
        this.searchManagerCdKey = searchManagerCdKey;
    }

}
