package com.naikara_talk.model;

import java.math.BigDecimal;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>ポイント管理マスタメンテナンス(単票)<br>
 * <b>クラス概要       :</b>ポイント管理マスタメンテナンス(単票)Model<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/01 TECS 新規作成
 */
public class PointControlModel implements Model {

    private static final long serialVersionUID = 1L;

    /** 正常 */
    public static final int PROCESS_NORMAL = 0;

    /** ポイントコード */
    private String pointCd;

    /** 通常月謝区分 */
    private String feeKbn;

    /** 通常月謝区分名 */
    private String feeKbnNm;

    /** 金額(税込) */
    private BigDecimal moneyYen;

    /** 有償ポイント */
    private BigDecimal paymentPoint;

    /** 有償ポイント期限 */
    private int paymentPointTim;

    /** 無償ポイント */
    private BigDecimal freePoint;

    /** 無償ポイント期限 */
    private int freePointTim;

    /** 提供期間開始日 */
    private String useStrDt;

    /** 提供期間終了日 */
    private String useEndDt;

    /** 備考 */
    private String remarks;

    /** レコードバージョン番号 */
    private int recordVerNo;

    /** 処理区分(前画面情報) */
    private String processKbn_rdl;

    /** 画面表示処理区分 */
    private String processKbn_txt;

    /**
     * @return pointCd
     */
    public String getPointCd() {
        return pointCd;
    }

    /**
     * @param pointCd
     *            セットする pointCd
     */
    public void setPointCd(String pointCd) {
        this.pointCd = pointCd;
    }

    /**
     * @return feeKbn
     */
    public String getFeeKbn() {
        return feeKbn;
    }

    /**
     * @param feeKbn
     *            セットする feeKbn
     */
    public void setFeeKbn(String feeKbn) {
        this.feeKbn = feeKbn;
    }

    /**
     * @return feeKbnNm
     */
    public String getFeeKbnNm() {
        return feeKbnNm;
    }

    /**
     * @param feeKbnNm
     *            セットする feeKbnNm
     */
    public void setFeeKbnNm(String feeKbnNm) {
        this.feeKbnNm = feeKbnNm;
    }

    /**
     * @return moneyYen
     */
    public BigDecimal getMoneyYen() {
        return moneyYen;
    }

    /**
     * @param moneyYen
     *            セットする moneyYen
     */
    public void setMoneyYen(BigDecimal moneyYen) {
        this.moneyYen = moneyYen;
    }

    /**
     * @return paymentPoint
     */
    public BigDecimal getPaymentPoint() {
        return paymentPoint;
    }

    /**
     * @param paymentPoint
     *            セットする paymentPoint
     */
    public void setPaymentPoint(BigDecimal paymentPoint) {
        this.paymentPoint = paymentPoint;
    }

    /**
     * @return paymentPointTim
     */
    public int getPaymentPointTim() {
        return paymentPointTim;
    }

    /**
     * @param paymentPointTim
     *            セットする paymentPointTim
     */
    public void setPaymentPointTim(int paymentPointTim) {
        this.paymentPointTim = paymentPointTim;
    }

    /**
     * @return freePoint
     */
    public BigDecimal getFreePoint() {
        return freePoint;
    }

    /**
     * @param freePoint
     *            セットする freePoint
     */
    public void setFreePoint(BigDecimal freePoint) {
        this.freePoint = freePoint;
    }

    /**
     * @return freePointTim
     */
    public int getFreePointTim() {
        return freePointTim;
    }

    /**
     * @param freePointTim
     *            セットする freePointTim
     */
    public void setFreePointTim(int freePointTim) {
        this.freePointTim = freePointTim;
    }

    /**
     * @return useStrDt
     */
    public String getUseStrDt() {
        return useStrDt;
    }

    /**
     * @param useStrDt
     *            セットする useStrDt
     */
    public void setUseStrDt(String useStrDt) {
        this.useStrDt = useStrDt;
    }

    /**
     * @return useEndDt
     */
    public String getUseEndDt() {
        return useEndDt;
    }

    /**
     * @param useEndDt
     *            セットする useEndDt
     */
    public void setUseEndDt(String useEndDt) {
        this.useEndDt = useEndDt;
    }

    /**
     * @return remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * @param remarks
     *            セットする remarks
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * @return recordVerNo
     */
    public int getRecordVerNo() {
        return recordVerNo;
    }

    /**
     * @param recordVerNo
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
     * @param processKbn_rdl
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
     * @param processKbn_txt
     *            セットする processKbn_txt
     */
    public void setProcessKbn_txt(String processKbn_txt) {
        this.processKbn_txt = processKbn_txt;
    }

    /**
     * @return processKbn_rdl
     */
    public String getProcessKbn_Rdl() {
        return processKbn_rdl;
    }

    /**
     * @param processKbn_rdl
     *            セットする processKbn_rdl
     */
    public void setProcessKbn_Rdl(String processKbn_rdl) {
        this.processKbn_rdl = processKbn_rdl;
    }

    /**
     * @return processKbn_txt
     */
    public String getProcessKbn_Txt() {
        return processKbn_txt;
    }

    /**
     * @param processKbn_txt
     *            セットする processKbn_txt
     */
    public void setProcessKbn_Txt(String processKbn_txt) {
        this.processKbn_txt = processKbn_txt;
    }

}