package com.naikara_talk.sessiondata;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守。<br>
 * <b>クラス名称　　　:</b>受講者マスタメンテナンス【一覧】のセッション情報クラス。<br>
 * <b>クラス概要　　　:</b>受講者マスタメンテナンス【一覧】の戻る用のセッション情報。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b> 2013/07/30 TECS 新規作成。
 */

public class SessionStudentListMst implements SessionData {

    /** ヘッダの戻るリンク用のSessionKey */
    private static final String HEADER_RETURN_KEY = "StudentMstList";

    /** 戻る判定Onフラグ */
    private boolean returnOnFlg;

    /** 処理区分 */
    private String processKbn_rdl;

    /** 画面表示処理区分 */
    private String processKbn_txt;

    /** お客様区分 */
    private String customerKbn_rdl;

    /** 項目名1 */
    private String itemNm1_sel;

    /** 項目条件1 */
    private String itemCondn1_rdl;

    /** 項目1from */
    private String itemFrom1_txt;

    /** 項目1to */
    private String itemTo1_txt;

    /** 項目名2 */
    private String itemNm2_sel;

    /** 項目条件2 */
    private String itemCondn2_rdl;

    /** 項目2from */
    private String itemFrom2_txt;

    /** 項目2to */
    private String itemTo2_txt;

    /** 項目名3 */
    private String itemNm3_sel;

    /** 項目条件3 */
    private String itemCondn3_rdl;

    /** 項目3from */
    private String itemFrom3_txt;

    /** 項目3to */
    private String itemTo3_txt;

    /** 項目名4 */
    private String itemNm4_sel;

    /** 項目条件4 */
    private String itemCondn4_rdl;

    /** 項目4from */
    private String itemFrom4_txt;

    /** 項目4to */
    private String itemTo4_txt;

    /** 項目名5 */
    private String itemNm5_sel;

    /** 項目条件5 */
    private String itemCondn5_rdl;

    /** 項目5from */
    private String itemFrom5_txt;

    /** 項目5to */
    private String itemTo5_txt;

    /** 検索Key：処理区分 */
    private String searchProcessKbn_rdl;

    /** 検索Key：お客様区分 */
    private String searchCustomerKbn_rdl;

    /** 検索Key：項目名1 */
    private String searchItemNm1_sel;

    /** 検索Key：項目条件1 */
    private String searchItemCondn1_rdl;

    /** 検索Key：項目1from */
    private String searchItemFrom1_txt;

    /** 検索Key：項目1to */
    private String searchItemTo1_txt;

    /** 検索Key：項目名2 */
    private String searchItemNm2_sel;

    /** 検索Key：項目条件2 */
    private String searchItemCondn2_rdl;

    /** 検索Key：項目2from */
    private String searchItemFrom2_txt;

    /** 検索Key：項目2to */
    private String searchItemTo2_txt;

    /** 検索Key：項目名3 */
    private String searchItemNm3_sel;

    /** 検索Key：項目条件3 */
    private String searchItemCondn3_rdl;

    /** 検索Key：項目3from */
    private String searchItemFrom3_txt;

    /** 検索Key：項目3to */
    private String searchItemTo3_txt;

    /** 検索Key：項目名4 */
    private String searchItemNm4_sel;

    /** 検索Key：項目条件4 */
    private String searchItemCondn4_rdl;

    /** 検索Key：項目4from */
    private String searchItemFrom4_txt;

    /** 検索Key：項目4to */
    private String searchItemTo4_txt;

    /** 検索Key：項目名5 */
    private String searchItemNm5_sel;

    /** 検索Key：項目条件5 */
    private String searchItemCondn5_rdl;

    /** 検索Key：項目5from */
    private String searchItemFrom5_txt;

    /** 検索Key：項目5to */
    private String searchItemTo5_txt;

    /** 検索Key：選択された明細のKey */
    private String select_rdl;

    /** 画面検索ありフラグ*/
    private String hasSearchFlg;

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
     * @return processKbn_rdl
     */
    public String getProcessKbn_rdl() {
        return processKbn_rdl;
    }

    /**
     * @param processKbn_rdl セットする processKbn_rdl
     */
    public void setProcessKbn_rdl(String processKbn_rdl) {
        this.processKbn_rdl = processKbn_rdl;
    }

    /**
     * @return processKbn_txt
     */
    public String getProcessKbn_txt() {
        return processKbn_txt;
    }

    /**
     * @param processKbn_txt セットする processKbn_txt
     */
    public void setProcessKbn_txt(String processKbn_txt) {
        this.processKbn_txt = processKbn_txt;
    }

    /**
     * @return customerKbn_rdl
     */
    public String getCustomerKbn_rdl() {
        return customerKbn_rdl;
    }

    /**
     * @param customerKbn_rdl セットする customerKbn_rdl
     */
    public void setCustomerKbn_rdl(String customerKbn_rdl) {
        this.customerKbn_rdl = customerKbn_rdl;
    }

    /**
     * @return itemNm1_sel
     */
    public String getItemNm1_sel() {
        return itemNm1_sel;
    }

    /**
     * @param itemNm1_sel セットする itemNm1_sel
     */
    public void setItemNm1_sel(String itemNm1_sel) {
        this.itemNm1_sel = itemNm1_sel;
    }

    /**
     * @return itemCondn1_rdl
     */
    public String getItemCondn1_rdl() {
        return itemCondn1_rdl;
    }

    /**
     * @param itemCondn1_rdl セットする itemCondn1_rdl
     */
    public void setItemCondn1_rdl(String itemCondn1_rdl) {
        this.itemCondn1_rdl = itemCondn1_rdl;
    }

    /**
     * @return itemFrom1_txt
     */
    public String getItemFrom1_txt() {
        return itemFrom1_txt;
    }

    /**
     * @param itemFrom1_txt セットする itemFrom1_txt
     */
    public void setItemFrom1_txt(String itemFrom1_txt) {
        this.itemFrom1_txt = itemFrom1_txt;
    }

    /**
     * @return itemTo1_txt
     */
    public String getItemTo1_txt() {
        return itemTo1_txt;
    }

    /**
     * @param itemTo1_txt セットする itemTo1_txt
     */
    public void setItemTo1_txt(String itemTo1_txt) {
        this.itemTo1_txt = itemTo1_txt;
    }

    /**
     * @return itemNm2_sel
     */
    public String getItemNm2_sel() {
        return itemNm2_sel;
    }

    /**
     * @param itemNm2_sel セットする itemNm2_sel
     */
    public void setItemNm2_sel(String itemNm2_sel) {
        this.itemNm2_sel = itemNm2_sel;
    }

    /**
     * @return itemCondn2_rdl
     */
    public String getItemCondn2_rdl() {
        return itemCondn2_rdl;
    }

    /**
     * @param itemCondn2_rdl セットする itemCondn2_rdl
     */
    public void setItemCondn2_rdl(String itemCondn2_rdl) {
        this.itemCondn2_rdl = itemCondn2_rdl;
    }

    /**
     * @return itemFrom2_txt
     */
    public String getItemFrom2_txt() {
        return itemFrom2_txt;
    }

    /**
     * @param itemFrom2_txt セットする itemFrom2_txt
     */
    public void setItemFrom2_txt(String itemFrom2_txt) {
        this.itemFrom2_txt = itemFrom2_txt;
    }

    /**
     * @return itemTo2_txt
     */
    public String getItemTo2_txt() {
        return itemTo2_txt;
    }

    /**
     * @param itemTo2_txt セットする itemTo2_txt
     */
    public void setItemTo2_txt(String itemTo2_txt) {
        this.itemTo2_txt = itemTo2_txt;
    }

    /**
     * @return itemNm3_sel
     */
    public String getItemNm3_sel() {
        return itemNm3_sel;
    }

    /**
     * @param itemNm3_sel セットする itemNm3_sel
     */
    public void setItemNm3_sel(String itemNm3_sel) {
        this.itemNm3_sel = itemNm3_sel;
    }

    /**
     * @return itemCondn3_rdl
     */
    public String getItemCondn3_rdl() {
        return itemCondn3_rdl;
    }

    /**
     * @param itemCondn3_rdl セットする itemCondn3_rdl
     */
    public void setItemCondn3_rdl(String itemCondn3_rdl) {
        this.itemCondn3_rdl = itemCondn3_rdl;
    }

    /**
     * @return itemFrom3_txt
     */
    public String getItemFrom3_txt() {
        return itemFrom3_txt;
    }

    /**
     * @param itemFrom3_txt セットする itemFrom3_txt
     */
    public void setItemFrom3_txt(String itemFrom3_txt) {
        this.itemFrom3_txt = itemFrom3_txt;
    }

    /**
     * @return itemTo3_txt
     */
    public String getItemTo3_txt() {
        return itemTo3_txt;
    }

    /**
     * @param itemTo3_txt セットする itemTo3_txt
     */
    public void setItemTo3_txt(String itemTo3_txt) {
        this.itemTo3_txt = itemTo3_txt;
    }

    /**
     * @return itemNm4_sel
     */
    public String getItemNm4_sel() {
        return itemNm4_sel;
    }

    /**
     * @param itemNm4_sel セットする itemNm4_sel
     */
    public void setItemNm4_sel(String itemNm4_sel) {
        this.itemNm4_sel = itemNm4_sel;
    }

    /**
     * @return itemCondn4_rdl
     */
    public String getItemCondn4_rdl() {
        return itemCondn4_rdl;
    }

    /**
     * @param itemCondn4_rdl セットする itemCondn4_rdl
     */
    public void setItemCondn4_rdl(String itemCondn4_rdl) {
        this.itemCondn4_rdl = itemCondn4_rdl;
    }

    /**
     * @return itemFrom4_txt
     */
    public String getItemFrom4_txt() {
        return itemFrom4_txt;
    }

    /**
     * @param itemFrom4_txt セットする itemFrom4_txt
     */
    public void setItemFrom4_txt(String itemFrom4_txt) {
        this.itemFrom4_txt = itemFrom4_txt;
    }

    /**
     * @return itemTo4_txt
     */
    public String getItemTo4_txt() {
        return itemTo4_txt;
    }

    /**
     * @param itemTo4_txt セットする itemTo4_txt
     */
    public void setItemTo4_txt(String itemTo4_txt) {
        this.itemTo4_txt = itemTo4_txt;
    }

    /**
     * @return itemNm5_sel
     */
    public String getItemNm5_sel() {
        return itemNm5_sel;
    }

    /**
     * @param itemNm5_sel セットする itemNm5_sel
     */
    public void setItemNm5_sel(String itemNm5_sel) {
        this.itemNm5_sel = itemNm5_sel;
    }

    /**
     * @return itemCondn5_rdl
     */
    public String getItemCondn5_rdl() {
        return itemCondn5_rdl;
    }

    /**
     * @param itemCondn5_rdl セットする itemCondn5_rdl
     */
    public void setItemCondn5_rdl(String itemCondn5_rdl) {
        this.itemCondn5_rdl = itemCondn5_rdl;
    }

    /**
     * @return itemFrom5_txt
     */
    public String getItemFrom5_txt() {
        return itemFrom5_txt;
    }

    /**
     * @param itemFrom5_txt セットする itemFrom5_txt
     */
    public void setItemFrom5_txt(String itemFrom5_txt) {
        this.itemFrom5_txt = itemFrom5_txt;
    }

    /**
     * @return itemTo5_txt
     */
    public String getItemTo5_txt() {
        return itemTo5_txt;
    }

    /**
     * @param itemTo5_txt セットする itemTo5_txt
     */
    public void setItemTo5_txt(String itemTo5_txt) {
        this.itemTo5_txt = itemTo5_txt;
    }

    /**
     * @return searchProcessKbn_rdl
     */
    public String getSearchProcessKbn_rdl() {
        return searchProcessKbn_rdl;
    }

    /**
     * @param searchProcessKbn_rdl セットする searchProcessKbn_rdl
     */
    public void setSearchProcessKbn_rdl(String searchProcessKbn_rdl) {
        this.searchProcessKbn_rdl = searchProcessKbn_rdl;
    }

    /**
     * @return searchCustomerKbn_rdl
     */
    public String getSearchCustomerKbn_rdl() {
        return searchCustomerKbn_rdl;
    }

    /**
     * @param searchCustomerKbn_rdl セットする searchCustomerKbn_rdl
     */
    public void setSearchCustomerKbn_rdl(String searchCustomerKbn_rdl) {
        this.searchCustomerKbn_rdl = searchCustomerKbn_rdl;
    }

    /**
     * @return searchItemNm1_sel
     */
    public String getSearchItemNm1_sel() {
        return searchItemNm1_sel;
    }

    /**
     * @param searchItemNm1_sel セットする searchItemNm1_sel
     */
    public void setSearchItemNm1_sel(String searchItemNm1_sel) {
        this.searchItemNm1_sel = searchItemNm1_sel;
    }

    /**
     * @return searchItemCondn1_rdl
     */
    public String getSearchItemCondn1_rdl() {
        return searchItemCondn1_rdl;
    }

    /**
     * @param searchItemCondn1_rdl セットする searchItemCondn1_rdl
     */
    public void setSearchItemCondn1_rdl(String searchItemCondn1_rdl) {
        this.searchItemCondn1_rdl = searchItemCondn1_rdl;
    }

    /**
     * @return searchItemFrom1_txt
     */
    public String getSearchItemFrom1_txt() {
        return searchItemFrom1_txt;
    }

    /**
     * @param searchItemFrom1_txt セットする searchItemFrom1_txt
     */
    public void setSearchItemFrom1_txt(String searchItemFrom1_txt) {
        this.searchItemFrom1_txt = searchItemFrom1_txt;
    }

    /**
     * @return searchItemTo1_txt
     */
    public String getSearchItemTo1_txt() {
        return searchItemTo1_txt;
    }

    /**
     * @param searchItemTo1_txt セットする searchItemTo1_txt
     */
    public void setSearchItemTo1_txt(String searchItemTo1_txt) {
        this.searchItemTo1_txt = searchItemTo1_txt;
    }

    /**
     * @return searchItemNm2_sel
     */
    public String getSearchItemNm2_sel() {
        return searchItemNm2_sel;
    }

    /**
     * @param searchItemNm2_sel セットする searchItemNm2_sel
     */
    public void setSearchItemNm2_sel(String searchItemNm2_sel) {
        this.searchItemNm2_sel = searchItemNm2_sel;
    }

    /**
     * @return searchItemCondn2_rdl
     */
    public String getSearchItemCondn2_rdl() {
        return searchItemCondn2_rdl;
    }

    /**
     * @param searchItemCondn2_rdl セットする searchItemCondn2_rdl
     */
    public void setSearchItemCondn2_rdl(String searchItemCondn2_rdl) {
        this.searchItemCondn2_rdl = searchItemCondn2_rdl;
    }

    /**
     * @return searchItemFrom2_txt
     */
    public String getSearchItemFrom2_txt() {
        return searchItemFrom2_txt;
    }

    /**
     * @param searchItemFrom2_txt セットする searchItemFrom2_txt
     */
    public void setSearchItemFrom2_txt(String searchItemFrom2_txt) {
        this.searchItemFrom2_txt = searchItemFrom2_txt;
    }

    /**
     * @return searchItemTo2_txt
     */
    public String getSearchItemTo2_txt() {
        return searchItemTo2_txt;
    }

    /**
     * @param searchItemTo2_txt セットする searchItemTo2_txt
     */
    public void setSearchItemTo2_txt(String searchItemTo2_txt) {
        this.searchItemTo2_txt = searchItemTo2_txt;
    }

    /**
     * @return searchItemNm3_sel
     */
    public String getSearchItemNm3_sel() {
        return searchItemNm3_sel;
    }

    /**
     * @param searchItemNm3_sel セットする searchItemNm3_sel
     */
    public void setSearchItemNm3_sel(String searchItemNm3_sel) {
        this.searchItemNm3_sel = searchItemNm3_sel;
    }

    /**
     * @return searchItemCondn3_rdl
     */
    public String getSearchItemCondn3_rdl() {
        return searchItemCondn3_rdl;
    }

    /**
     * @param searchItemCondn3_rdl セットする searchItemCondn3_rdl
     */
    public void setSearchItemCondn3_rdl(String searchItemCondn3_rdl) {
        this.searchItemCondn3_rdl = searchItemCondn3_rdl;
    }

    /**
     * @return searchItemFrom3_txt
     */
    public String getSearchItemFrom3_txt() {
        return searchItemFrom3_txt;
    }

    /**
     * @param searchItemFrom3_txt セットする searchItemFrom3_txt
     */
    public void setSearchItemFrom3_txt(String searchItemFrom3_txt) {
        this.searchItemFrom3_txt = searchItemFrom3_txt;
    }

    /**
     * @return searchItemTo3_txt
     */
    public String getSearchItemTo3_txt() {
        return searchItemTo3_txt;
    }

    /**
     * @param searchItemTo3_txt セットする searchItemTo3_txt
     */
    public void setSearchItemTo3_txt(String searchItemTo3_txt) {
        this.searchItemTo3_txt = searchItemTo3_txt;
    }

    /**
     * @return searchItemNm4_sel
     */
    public String getSearchItemNm4_sel() {
        return searchItemNm4_sel;
    }

    /**
     * @param searchItemNm4_sel セットする searchItemNm4_sel
     */
    public void setSearchItemNm4_sel(String searchItemNm4_sel) {
        this.searchItemNm4_sel = searchItemNm4_sel;
    }

    /**
     * @return searchItemCondn4_rdl
     */
    public String getSearchItemCondn4_rdl() {
        return searchItemCondn4_rdl;
    }

    /**
     * @param searchItemCondn4_rdl セットする searchItemCondn4_rdl
     */
    public void setSearchItemCondn4_rdl(String searchItemCondn4_rdl) {
        this.searchItemCondn4_rdl = searchItemCondn4_rdl;
    }

    /**
     * @return searchItemFrom4_txt
     */
    public String getSearchItemFrom4_txt() {
        return searchItemFrom4_txt;
    }

    /**
     * @param searchItemFrom4_txt セットする searchItemFrom4_txt
     */
    public void setSearchItemFrom4_txt(String searchItemFrom4_txt) {
        this.searchItemFrom4_txt = searchItemFrom4_txt;
    }

    /**
     * @return searchItemTo4_txt
     */
    public String getSearchItemTo4_txt() {
        return searchItemTo4_txt;
    }

    /**
     * @param searchItemTo4_txt セットする searchItemTo4_txt
     */
    public void setSearchItemTo4_txt(String searchItemTo4_txt) {
        this.searchItemTo4_txt = searchItemTo4_txt;
    }

    /**
     * @return searchItemNm5_sel
     */
    public String getSearchItemNm5_sel() {
        return searchItemNm5_sel;
    }

    /**
     * @param searchItemNm5_sel セットする searchItemNm5_sel
     */
    public void setSearchItemNm5_sel(String searchItemNm5_sel) {
        this.searchItemNm5_sel = searchItemNm5_sel;
    }

    /**
     * @return searchItemCondn5_rdl
     */
    public String getSearchItemCondn5_rdl() {
        return searchItemCondn5_rdl;
    }

    /**
     * @param searchItemCondn5_rdl セットする searchItemCondn5_rdl
     */
    public void setSearchItemCondn5_rdl(String searchItemCondn5_rdl) {
        this.searchItemCondn5_rdl = searchItemCondn5_rdl;
    }

    /**
     * @return searchItemFrom5_txt
     */
    public String getSearchItemFrom5_txt() {
        return searchItemFrom5_txt;
    }

    /**
     * @param searchItemFrom5_txt セットする searchItemFrom5_txt
     */
    public void setSearchItemFrom5_txt(String searchItemFrom5_txt) {
        this.searchItemFrom5_txt = searchItemFrom5_txt;
    }

    /**
     * @return searchItemTo5_txt
     */
    public String getSearchItemTo5_txt() {
        return searchItemTo5_txt;
    }

    /**
     * @param searchItemTo5_txt セットする searchItemTo5_txt
     */
    public void setSearchItemTo5_txt(String searchItemTo5_txt) {
        this.searchItemTo5_txt = searchItemTo5_txt;
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
     * @return headerReturnKey
     */
    public static String getHeaderReturnKey() {
        return HEADER_RETURN_KEY;
    }

    /**
     * @return select_rdl
     */
    public String getSelect_rdl() {
        return select_rdl;
    }

    /**
     * @param select_rdl セットする select_rdl
     */
    public void setSelect_rdl(String select_rdl) {
        this.select_rdl = select_rdl;
    }

}
