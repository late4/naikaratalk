package com.naikara_talk.sessiondata;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>ポイント管理マスタメンテナンスのセッション情報クラス。<br>
 * <b>クラス概要       :</b>ポイント管理マスタメンテナンスの戻る用のセッション情報。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/01 TECS 新規作成
 */
public class SessionOrganizationSelectionList implements SessionData {

    /** ヘッダの戻るリンク用のSessionKey */
    private static final String HEADER_RETURN_KEY = "OrganizationSelectionList";

    /** 戻る判定Onフラグ */
    private boolean returnOnFlg;

    /** 検索判断フラグ */
    private String hasSearchFlg;

    /** 組織ID */
    private String organizationId;

    /** 組織名 */
    private String organizationnm;

    /** 組織責任者名(フリガナ) */
    private String managKnm;

    /** 検索Key：組織ID */
    private String searchOrganizationId;

    /** 検索Key：組織名 */
    private String searchOrganizationNm;

    /** 検索Key：組織責任者名(フリガナ) */
    private String searchManagKnm;

    /** 検索Key：選択された明細のKey-ポイントコード */
    private String searchOrganizationIdKey;

    /**
     * このセッションデータのキーを返却する
     */
    public static String getKey() {
        return HEADER_RETURN_KEY;
    }

    /**
     * @return processKbn
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
     * @return organizationnm
     */
    public String getOrganizationnm() {
        return organizationnm;
    }

    /**
     * @param organizationnm セットする organizationnm
     */
    public void setOrganizationnm(String organizationnm) {
        this.organizationnm = organizationnm;
    }

    /**
     * @return managKnm
     */
    public String getManagKnm() {
        return managKnm;
    }

    /**
     * @param managKnm セットする managKnm
     */
    public void setManagKnm(String managKnm) {
        this.managKnm = managKnm;
    }

    /**
     * @return searchOrganizationId
     */
    public String getSearchOrganizationId() {
        return searchOrganizationId;
    }

    /**
     * @param searchOrganizationId セットする searchOrganizationId
     */
    public void setSearchOrganizationId(String searchOrganizationId) {
        this.searchOrganizationId = searchOrganizationId;
    }

    /**
     * @return searchOrganizationNm
     */
    public String getSearchOrganizationNm() {
        return searchOrganizationNm;
    }

    /**
     * @param searchOrganizationNm セットする searchOrganizationNm
     */
    public void setSearchOrganizationNm(String searchOrganizationNm) {
        this.searchOrganizationNm = searchOrganizationNm;
    }

    /**
     * @return searchManagKnm
     */
    public String getSearchManagKnm() {
        return searchManagKnm;
    }

    /**
     * @param searchManagKnm セットする searchManagKnm
     */
    public void setSearchManagKnm(String searchManagKnm) {
        this.searchManagKnm = searchManagKnm;
    }

    /**
     * @return searchOrganizationIdKey
     */
    public String getSearchOrganizationIdKey() {
        return searchOrganizationIdKey;
    }

    /**
     * @param searchOrganizationIdKey セットする searchOrganizationIdKey
     */
    public void setSearchOrganizationIdKey(String searchOrganizationIdKey) {
        this.searchOrganizationIdKey = searchOrganizationIdKey;
    }

}
