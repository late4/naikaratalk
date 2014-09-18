package com.naikara_talk.model;

import java.util.List;

import com.naikara_talk.dto.TimeManagMstDto;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守<br>
 * <b>クラス名称　　　:</b>時差管理マスタメンテナンス【一覧】Modelクラス。<br>
 * <b>クラス概要　　　:</b>時差管理マスタメンテナンス【一覧】Model。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b> 2013/07/10 TECS 新規作成。
 */
public class TimeZoneControlMstListModel implements Model {

    private static final long serialVersionUID = 1L;

    /** 処理区分：新規 */
    public static final String PROS_KBN_ADD = "0";

    /** 処理区分：修正 */
    public static final String PROS_KBN_UPD = "1";

    /** 処理区分：削除 */
    public static final String PROS_KBN_DEL = "2";

    /** 処理区分：照会 */
    public static final String PROS_KBN_REF = "3";

    /** チェック：問題なし(正常) */
    public static final int CHECK_OK = 0;

    /** 処理区分(jsp) */
    protected String processKbn_rdl;

    /** 国コード (jsp) */
    protected String countryCd_sel;

    /** 時差地域No(jsp) */
    protected String areaNoCd_sel;

    /** 検索結果一覧 */
    private List<TimeManagMstDto> resultList;

    /** 画面状態フラグ */
    private String searchFlg;

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
     * @return area_sel
     */
    public String getAreaNoCd_sel() {
        return areaNoCd_sel;
    }

    /**
     * @param  area_sel
     *            セットする area_sel
     */
    public void setAreaNoCd_sel(String areaNoCd_sel) {
        this.areaNoCd_sel = areaNoCd_sel;
    }

    /**
     * @return resultList
     */
    public List<TimeManagMstDto> getResultList() {
        return resultList;
    }

    /**
     * @param  resultList
     *            セットする resultList
     */
    public void setResultList(List<TimeManagMstDto> resultList) {
        this.resultList = resultList;
    }

    /**
     * @return searchFlg
     */
    public String getSearchFlg() {
        return searchFlg;
    }

    /**
     * @param  searchFlg
     *            セットする searchFlg
     */
    public void setSearchFlg(String searchFlg) {
        this.searchFlg = searchFlg;
    }

    /**
     * @return serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /**
     * @return PROS_KBN_ADD
     */
    public static String getProsKbnAdd() {
        return PROS_KBN_ADD;
    }

    /**
     * @return prosKbnUpd
     */
    public static String getProsKbnUpd() {
        return PROS_KBN_UPD;
    }

    /**
     * @return prosKbnDel
     */
    public static String getProsKbnDel() {
        return PROS_KBN_DEL;
    }

    /**
     * @return prosKbnRef
     */
    public static String getProsKbnRef() {
        return PROS_KBN_REF;
    }

    /**
     * @return checkOk
     */
    public static int getCheckOk() {
        return CHECK_OK;
    }

}