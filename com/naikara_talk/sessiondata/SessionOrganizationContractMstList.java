package com.naikara_talk.sessiondata;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>組織契約情報登録一覧検索処理<br>
 * <b>クラス名称　　　:</b>組織契約情報登録一覧のセッション情報クラス。<br>
 * <b>クラス概要　　　:</b>組織契約情報登録の戻る用のセッション情報。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b> 2013/08/01 TECS 新規作成。
 */
public class SessionOrganizationContractMstList implements SessionData {

    /** ヘッダの戻るリンク用のSessionKey */
    private static final String HEADER_RETURN_KEY = "OrganizationContractMstList";

    /** 戻る判定Onフラグ */
    private boolean returnOnFlg;

    /** 検索判断フラグ */
    private String hasSearchFlg;

    /** 処理区分 */
    private String processKbn;

    /** 組織ID */
    private String organizationId;

    /** 組織名 */
    private String organizationJnm;

    /** 検索Key：処理区分 */
    private String searchProcessKbn;

    /** 検索Key：組織ID */
    private String searchOrganizationId;

    /** 検索Key：組織名 */
    private String searchOrganizationJnm;

    /** 検索Key：選択された明細のKey-講師ID */
    private String searchOrganizationIdKey;

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
     * @return searchProcessKbn
     */
    public String getSearchProcessKbn() {
        return searchProcessKbn;
    }

    /**
     * @param searchProcessKbn セットする searchProcessKbn
     */
    public void setSearchProcessKbn(String searchProcessKbn) {
        this.searchProcessKbn = searchProcessKbn;
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
     * @return searchOrganizationJnm
     */
    public String getSearchOrganizationJnm() {
        return searchOrganizationJnm;
    }

    /**
     * @param searchOrganizationJnm セットする searchOrganizationJnm
     */
    public void setSearchOrganizationJnm(String searchOrganizationJnm) {
        this.searchOrganizationJnm = searchOrganizationJnm;
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

    /**
     * @return headerReturnKey
     */
    public static String getHeaderReturnKey() {
        return HEADER_RETURN_KEY;
    }

}
