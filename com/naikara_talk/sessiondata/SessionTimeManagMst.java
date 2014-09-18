package com.naikara_talk.sessiondata;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守<br>
 * <b>クラス名称　　　:</b>時差管理マスタメンテナンスのセッション情報クラス。<br>
 * <b>クラス概要　　　:</b>時差管理マスタメンテナンスの戻る用のセッション情報。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b> 2013/07/10 TECS 新規作成。
 */

public class SessionTimeManagMst implements SessionData {

    /** ヘッダの戻るリンク用のSessionKey */
    private static final String HEADER_RETURN_KEY = "StudentMstList";

    /** 戻る判定Onフラグ */
    private boolean returnOnFlg;

    /** 処理区分 */
    private String processKbn_rdl;

    /** 国コード */
    private String countryCd_sel;

    /** 時差管理No */
    private String areaNoCd_sel;

    /** 検索Key：国コード */
    private String searchCountryCd_sel;

    /** 検索Key：時差管理No */
    private String searchAreaNoCd_sel;

    /** 検索Key：選択された明細のKey-国コード&時差管理No */
    private String searchPrimaryKey;

    /** 画面検索ありフラグ */
    private String hasSearchFlg;

    /**
     * @return returnOnFlg
     */
    public boolean getReturnOnFlg() {
        return returnOnFlg;
    }

    /**
     * @param  returnOnFlg
     *            セットする returnOnFlg
     */
    public void setReturnOnFlg(boolean returnOnFlg) {
        this.returnOnFlg = returnOnFlg;
    }

    /**
     * @return processKbn_rdl
     */
    public String getProcessKbn_rdl() {
        return processKbn_rdl;
    }

    /**
     * @param  processKbn_rdl
     *            セットする processKbn_rdl
     */
    public void setProcessKbn_rdl(String processKbn_rdl) {
        this.processKbn_rdl = processKbn_rdl;
    }

    /**
     * @return countryCd_sel
     */
    public String getCountryCd_sel() {
        return countryCd_sel;
    }

    /**
     * @param  countryCd_sel
     *            セットする countryCd_sel
     */
    public void setCountryCd_sel(String countryCd_sel) {
        this.countryCd_sel = countryCd_sel;
    }

    /**
     * @return areaNoCd_sel
     */
    public String getAreaNoCd_sel() {
        return areaNoCd_sel;
    }

    /**
     * @param  areaNoCd_sel
     *            セットする areaNoCd_sel
     */
    public void setAreaNoCd_sel(String areaNoCd_sel) {
        this.areaNoCd_sel = areaNoCd_sel;
    }

    /**
     * @return searchCountryCd_sel
     */
    public String getSearchCountryCd_sel() {
        return searchCountryCd_sel;
    }

    /**
     * @param  searchCountryCd_sel
     *            セットする searchCountryCd_sel
     */
    public void setSearchCountryCd_sel(String searchCountryCd_sel) {
        this.searchCountryCd_sel = searchCountryCd_sel;
    }

    /**
     * @return searchAreaNoCd_sel
     */
    public String getSearchAreaNoCd_sel() {
        return searchAreaNoCd_sel;
    }

    /**
     * @param  searchAreaNoCd_sel
     *            セットする searchAreaNoCd_sel
     */
    public void setSearchAreaNoCd_sel(String searchAreaNoCd_sel) {
        this.searchAreaNoCd_sel = searchAreaNoCd_sel;
    }

    /**
     * @return headerReturnKey
     */
    public static String getHeaderReturnKey() {
        return HEADER_RETURN_KEY;
    }

    /**
     * @return searchGoodsCdKey
     */
    public String getSearchPrimaryKey() {
        return searchPrimaryKey;
    }

    /**
     * @param  searchPrimaryKey
     *            セットする searchPrimaryKey
     */
    public void setSearchPrimaryKey(String searchPrimaryKey) {
        this.searchPrimaryKey = searchPrimaryKey;
    }

    /**
     * @return hasSearchFlg
     */
    public String getHasSearchFlg() {
        return hasSearchFlg;
    }

    /**
     * @param  hasSearchFlg
     *            セットする hasSearchFlg
     */
    public void setHasSearchFlg(String hasSearchFlg) {
        this.hasSearchFlg = hasSearchFlg;
    }

}
