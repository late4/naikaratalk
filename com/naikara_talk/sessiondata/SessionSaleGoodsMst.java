package com.naikara_talk.sessiondata;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>販売商品マスタメンテナンス。<br>
 * <b>クラス名称　　　:</b>販売商品マスタメンテナンスのセッション情報クラス。<br>
 * <b>クラス概要　　　:</b>販売商品マスタメンテナンスの戻る用のセッション情報。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>
 * 2013/06/18 TECS 新規作成。
 */

public class SessionSaleGoodsMst implements SessionData {

    /** ヘッダの戻るリンク用のSessionKey */
    private static final String HEADER_RETURN_KEY = "naikara_talk.sessiondata.SaleGoodsMstList";

    /** 戻る判定Onフラグ */
    private boolean returnOnFlg;

    /** 処理区分 */
    private String processKbn;

    /** 商品コード */
    private String goodsCd;

    /** 商品名 */
    private String goodsNm;

    /** 利用状態 */
    private String useKbn;

    /** 検索Key：商品コード */
    private String searchGoodsCd;

    /** 検索Key：商品名 */
    private String searchGoodsNm;

    /** 検索Key：利用状態 */
    private String searchUseKbn;

    /** 検索Key：選択された明細のKey-商品コード */
    private String searchGoodsCdKey;

    /** 画面状態判断フラグ */
    private String hasSearchFlg;


    /**
     * 画面状態フラグを取得する
     * @return hasSearchFlg
     */
    public String getHasSearchFlg() {
        return hasSearchFlg;
    }

    /**
     * 画面状態フラグをセットする
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
     * @return goodsCd
     */
    public String getGoodsCd() {
        return goodsCd;
    }

    /**
     * @param goodsCd セットする goodsCd
     */
    public void setGoodsCd(String goodsCd) {
        this.goodsCd = goodsCd;
    }

    /**
     * @return goodsNm
     */
    public String getGoodsNm() {
        return goodsNm;
    }

    /**
     * @param goodsNm セットする goodsNm
     */
    public void setGoodsNm(String goodsNm) {
        this.goodsNm = goodsNm;
    }

    /**
     * @return useKbn
     */
    public String getUseKbn() {
        return useKbn;
    }

    /**
     * @param useKbn セットする useKbn
     */
    public void setUseKbn(String useKbn) {
        this.useKbn = useKbn;
    }

    /**
     * @return searchGoodsCd
     */
    public String getSearchGoodsCd() {
        return searchGoodsCd;
    }

    /**
     * @param searchGoodsCd セットする searchGoodsCd
     */
    public void setSearchGoodsCd(String searchGoodsCd) {
        this.searchGoodsCd = searchGoodsCd;
    }

    /**
     * @return searchGoodsNm
     */
    public String getSearchGoodsNm() {
        return searchGoodsNm;
    }

    /**
     * @param searchGoodsNm セットする searchGoodsNm
     */
    public void setSearchGoodsNm(String searchGoodsNm) {
        this.searchGoodsNm = searchGoodsNm;
    }

    /**
     * @return searchUseKbn
     */
    public String getSearchUseKbn() {
        return searchUseKbn;
    }

    /**
     * @param searchUseKbn セットする searchUseKbn
     */
    public void setSearchUseKbn(String searchUseKbn) {
        this.searchUseKbn = searchUseKbn;
    }

    /**
     * @return searchGoodsCdKey
     */
    public String getSearchGoodsCdKey() {
        return searchGoodsCdKey;
    }

    /**
     * @param searchGoodsCdKey セットする searchGoodsCdKey
     */
    public void setSearchGoodsCdKey(String searchGoodsCdKey) {
        this.searchGoodsCdKey = searchGoodsCdKey;
    }

}
