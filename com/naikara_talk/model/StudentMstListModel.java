package com.naikara_talk.model;

import java.util.List;

import com.naikara_talk.dto.StuMPoiOwnTDto;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守<br>
 * <b>クラス名称　　　:</b>受講者マスタメンテナンス【一覧】Modelクラス。<br>
 * <b>クラス概要　　　:</b>受講者マスタメンテナンス【一覧】Model。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b> 2013/07/30 TECS 新規作成。
 */
public class StudentMstListModel implements Model {

    private static final long serialVersionUID = 1L;

    /** 処理区分：新規 */
    public static final String PROS_KBN_ADD = "0";

    /** 処理区分：修正 */
    public static final String PROS_KBN_UPD = "1";

    /** 処理区分：照会 */
    public static final String PROS_KBN_REF = "2";

    /** お客様区分：全て */
    public static final String CUSTOMER_KBN_ALL = "0000";

    /** お客様区分：法人 */
    public static final String CUSTOMER_KBN_LEGAL = "0001";

    /** お客様区分：個人 */
    public static final String CUSTOMER_KBN_SELF = "0002";

    /** 条件区分：なし */
    public static final String CONDITION_KBN_NONE = "0";

    /** 条件区分：範囲 */
    public static final String CONDITION_KBN_RANGE = "1";

    /** 条件区分：前方一致 */
    public static final String CONDITION_KBN_FOREEQU = "2";

    /** 条件区分：曖昧 */
    public static final String CONDITION_KBN_LIKE = "3";

    /** 条件区分：後方一致 */
    public static final String CONDITION_KBN_BACKEQU = "4";

    /** 条件区分：完全一致 */
    public static final String CONDITION_KBN_ALLEQU = "5";

    /** 条件5初期化設定: DM不要フラグ */
    public static final String CONDITION_INIT_VALUE_5 = "0";

    /** FROM5初期化設定: 0 */
    public static final String FROM_INIT_VALUE_5 = "0";

    /** 項目名5初期化設定: DM不要フラグ */
    public static final String ITEMNM_SEL_INIT_VALUE = "DM_NO_NEED_FLG";

    /** チェック：問題なし(正常) */
    public static final int CHECK_OK = 0;

    /** 処理区分(jsp) */
    private String processKbn_rdl;

    /** 処理区分名(jsp) */
    private String processKbn_txt;

    /** 受講者ID */
    private String customerKbn_rdl;

    /** 有効開始日 */
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

    /** 検索結果一覧 */
    private List<StuMPoiOwnTDto> resultList;

    /** メール チェックボックスリスト */
    private String[] selectMail_chk;

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
     * @return resultList
     */
    public List<StuMPoiOwnTDto> getResultList() {
        return resultList;
    }

    /**
     * @param resultList セットする resultList
     */
    public void setResultList(List<StuMPoiOwnTDto> resultList) {
        this.resultList = resultList;
    }

    /**
     * @return selectMail_chk
     */
    public String[] getSelectMail_chk() {
        return selectMail_chk;
    }

    /**
     * @param selectMail_chk セットする selectMail_chk
     */
    public void setSelectMail_chk(String[] selectMail_chk) {
        this.selectMail_chk = selectMail_chk;
    }

}