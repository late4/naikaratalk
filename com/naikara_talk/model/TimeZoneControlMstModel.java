package com.naikara_talk.model;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守<br>
 * <b>クラス名称　　　:</b>時差管理マスタメンテナンス【単票】Modelクラス。<br>
 * <b>クラス概要　　　:</b>時差管理マスタメンテナンス【単票】Model。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/10 TECS 新規作成。
 */
public class TimeZoneControlMstModel implements Model {

    private static final long serialVersionUID = 1L;

    /** 正常 */
    public static final int PROCESS_NORMAL = 0;

    /** 国コード */
    private String countryCd_sel;

    /** 時差地域NO */
    private String areaNoCd_sel;

    /** 時間（符号） */
    private String timeMarkKbn_rdl;

    /** 時間(分) */
    private int timeMinutes_txt;

    /** サマータイムフラグ */
    private String sumTimeFlg_rdl;

    /** サマータイム開始日 */
    private String sumTimeStrDt_txt;

    /** サマータイム終了日 */
    private String sumTimeEndDt_txt;

    /** サマータイム(時間)(符号) */
    private String sumTimeMarkKbn_rdl;

    /** サマータイム(時間)(分) */
    private String sumTimeMinutes_txt;

    /** 備考 */
    private String remark_txa;

    /** レコードバージョン番号 */
    private int recordVerNo;

    /** 処理区分(前画面情報) */
    private String processKbn_rdl;

    /** 画面表示処理区分 */
    private String processKbn_txt;

    /** 検索key */
    private String primaryKey;

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
     * @return timeMarkKbn_rdl
     */
    public String getTimeMarkKbn_rdl() {
        return timeMarkKbn_rdl;
    }

    /**
     * @param  timeMarkKbn_rdl
     *            セットする timeMarkKbn_rdl
     */
    public void setTimeMarkKbn_rdl(String timeMarkKbn_rdl) {
        this.timeMarkKbn_rdl = timeMarkKbn_rdl;
    }

    /**
     * @return timeMinutes_txt
     */
    public int getTimeMinutes_txt() {
        return timeMinutes_txt;
    }

    /**
     * @param  timeMinutes_txt
     *            セットする timeMinutes_txt
     */
    public void setTimeMinutes_txt(int timeMinutes_txt) {
        this.timeMinutes_txt = timeMinutes_txt;
    }

    /**
     * @return sumTimeFlg_rdl
     */
    public String getSumTimeFlg_rdl() {
        return sumTimeFlg_rdl;
    }

    /**
     * @param  sumTimeFlg_rdl
     *            セットする sumTimeFlg_rdl
     */
    public void setSumTimeFlg_rdl(String sumTimeFlg_rdl) {
        this.sumTimeFlg_rdl = sumTimeFlg_rdl;
    }

    /**
     * @return sumTimeStrDt_txt
     */
    public String getSumTimeStrDt_txt() {
        return sumTimeStrDt_txt;
    }

    /**
     * @param  sumTimeStrDt_txt
     *            セットする sumTimeStrDt_txt
     */
    public void setSumTimeStrDt_txt(String sumTimeStrDt_txt) {
        this.sumTimeStrDt_txt = sumTimeStrDt_txt;
    }

    /**
     * @return sumTimeEndDt_txt
     */
    public String getSumTimeEndDt_txt() {
        return sumTimeEndDt_txt;
    }

    /**
     * @param  sumTimeEndDt_txt
     *            セットする sumTimeEndDt_txt
     */
    public void setSumTimeEndDt_txt(String sumTimeEndDt_txt) {
        this.sumTimeEndDt_txt = sumTimeEndDt_txt;
    }

    /**
     * @return sumTimeMarkKbn_rdl
     */
    public String getSumTimeMarkKbn_rdl() {
        return sumTimeMarkKbn_rdl;
    }

    /**
     * @param  sumTimeMarkKbn_rdl
     *            セットする sumTimeMarkKbn_rdl
     */
    public void setSumTimeMarkKbn_rdl(String sumTimeMarkKbn_rdl) {
        this.sumTimeMarkKbn_rdl = sumTimeMarkKbn_rdl;
    }

    /**
     * @return sumTimeMinutes_txt
     */
    public String getSumTimeMinutes_txt() {
        return sumTimeMinutes_txt;
    }

    /**
     * @param  sumTimeMinutes_txt
     *            セットする sumTimeMinutes_txt
     */
    public void setSumTimeMinutes_txt(String sumTimeMinutes_txt) {
        this.sumTimeMinutes_txt = sumTimeMinutes_txt;
    }

    /**
     * @return remark_txa
     */
    public String getRemark_txa() {
        return remark_txa;
    }

    /**
     * @param  remark_txa
     *            セットする remark_txa
     */
    public void setRemark_txa(String remark_txa) {
        this.remark_txa = remark_txa;
    }

    /**
     * @return recordVerNo
     */
    public int getRecordVerNo() {
        return recordVerNo;
    }

    /**
     * @param  recordVerNo
     *            セットする recordVerNo
     */
    public void setRecordVerNo(int recordVerNo) {
        this.recordVerNo = recordVerNo;
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
     * @return processKbn_txt
     */
    public String getProcessKbn_txt() {
        return processKbn_txt;
    }

    /**
     * @param  processKbn_txt
     *            セットする processKbn_txt
     */
    public void setProcessKbn_txt(String processKbn_txt) {
        this.processKbn_txt = processKbn_txt;
    }

    /**
     * @return primaryKey
     */
    public String getPrimaryKey() {
        return primaryKey;
    }

    /**
     * @param  primaryKey
     *            セットする primaryKey
     */
    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    /**
     * @return serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /**
     * @return processNormal
     */
    public static int getProcessNormal() {
        return PROCESS_NORMAL;
    }

}