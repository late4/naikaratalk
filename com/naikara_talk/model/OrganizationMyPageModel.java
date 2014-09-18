package com.naikara_talk.model;

import java.math.BigDecimal;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>法人_初期処理<br>
 * <b>クラス名称       :</b>組織マイページModelクラス。<br>
 * <b>クラス概要       :</b>組織責任者の情報を表示。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/01 TECS 新規作成
 */
public class OrganizationMyPageModel implements Model {

    private static final long serialVersionUID = 1L;

    /** 組織名 */
    protected String organizationJnm;

    /** 組織ID */
    protected String organizationId;

    /** 責任者名(姓名) */
    protected String managJnm;

    /** メールアドレス */
    protected String managMailAddress1;

    /** 契約期間：開始日 */
    protected String contractStrDt;

    /** 契約期間：終了日 */
    protected String contractEndDt;

    /** キャンペーンのお知らせ */
    protected String actNotice;

    /** お知らせ */
    protected String notice;

    /** 連番 */
    protected int consSeq;

    /** ポイント残高 */
    protected BigDecimal balancePoint;

    /** 今月の利用ポイント */
    protected BigDecimal usePoint;

    /** 責任者性別区分 */
    protected String managGenderKbn;

    /** 契約ポイント */
    protected BigDecimal tempPointNum;

    /** 未割振ポイント(残高) */
    protected BigDecimal balancePointNum;

    /** 商品購入 有償利用ポイント */
    protected BigDecimal paymentUsePoint;

    /** レッスン予実 有償利用ポイント */
    protected BigDecimal LesPaymentUsePoint;

    /**
     * @return organizationJnm
     */
    public String getOrganizationJnm() {
        return organizationJnm;
    }

    /**
     * @param organizationJnm
     *            セットする organizationJnm
     */
    public void setOrganizationJnm(String organizationJnm) {
        this.organizationJnm = organizationJnm;
    }

    /**
     * @return organizationId
     */
    public String getOrganizationId() {
        return organizationId;
    }

    /**
     * @param organizationId
     *            セットする organizationId
     */
    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    /**
     * @return managJnm
     */
    public String getManagJnm() {
        return managJnm;
    }

    /**
     * @param managJnm
     *            セットする managJnm
     */
    public void setManagJnm(String managJnm) {
        this.managJnm = managJnm;
    }

    /**
     * @return managMailAddress1
     */
    public String getManagMailAddress1() {
        return managMailAddress1;
    }

    /**
     * @param managMailAddress1
     *            セットする managMailAddress1
     */
    public void setManagMailAddress1(String managMailAddress1) {
        this.managMailAddress1 = managMailAddress1;
    }

    /**
     * @return contractStrDt
     */
    public String getContractStrDt() {
        return contractStrDt;
    }

    /**
     * @param contractStrDt
     *            セットする contractStrDt
     */
    public void setContractStrDt(String contractStrDt) {
        this.contractStrDt = contractStrDt;
    }

    /**
     * @return contractEndDt
     */
    public String getContractEndDt() {
        return contractEndDt;
    }

    /**
     * @param contractEndDt
     *            セットする contractEndDt
     */
    public void setContractEndDt(String contractEndDt) {
        this.contractEndDt = contractEndDt;
    }

    /**
     * @return actNotice
     */
    public String getActNotice() {
        return actNotice;
    }

    /**
     * @param actNotice
     *            セットする actNotice
     */
    public void setActNotice(String actNotice) {
        this.actNotice = actNotice;
    }

    /**
     * @return notice
     */
    public String getNotice() {
        return notice;
    }

    /**
     * @param notice
     *            セットする notice
     */
    public void setNotice(String notice) {
        this.notice = notice;
    }

    /**
     * @return consSeq
     */
    public int getConsSeq() {
        return consSeq;
    }

    /**
     * @param consSeq
     *            セットする consSeq
     */
    public void setConsSeq(int consSeq) {
        this.consSeq = consSeq;
    }

    /**
     * @return balancePoint
     */
    public BigDecimal getBalancePoint() {
        return balancePoint;
    }

    /**
     * @param balancePoint
     *            セットする balancePoint
     */
    public void setBalancePoint(BigDecimal balancePoint) {
        this.balancePoint = balancePoint;
    }

    /**
     * @return usePoint
     */
    public BigDecimal getUsePoint() {
        return usePoint;
    }

    /**
     * @param usePoint
     *            セットする usePoint
     */
    public void setUsePoint(BigDecimal usePoint) {
        this.usePoint = usePoint;
    }

    /**
     * @return managGenderKbn
     */
    public String getManagGenderKbn() {
        return managGenderKbn;
    }

    /**
     * @param managGenderKbn
     *            セットする managGenderKbn
     */
    public void setManagGenderKbn(String managGenderKbn) {
        this.managGenderKbn = managGenderKbn;
    }

    /**
     * @return tempPointNum
     */
    public BigDecimal getTempPointNum() {
        return tempPointNum;
    }

    /**
     * @param tempPointNum
     *            セットする tempPointNum
     */
    public void setTempPointNum(BigDecimal tempPointNum) {
        this.tempPointNum = tempPointNum;
    }

    /**
     * @return balancePointNum
     */
    public BigDecimal getBalancePointNum() {
        return balancePointNum;
    }

    /**
     * @param balancePointNum
     *            セットする balancePointNum
     */
    public void setBalancePointNum(BigDecimal balancePointNum) {
        this.balancePointNum = balancePointNum;
    }

    /**
     * @return PaymentUsePoint
     */
    public BigDecimal getPaymentUsePoint() {
        return paymentUsePoint;
    }

    /**
     * @param PaymentUsePoint
     *            セットする PaymentUsePoint
     */
    public void setPaymentUsePoint(BigDecimal paymentUsePoint) {
        this.paymentUsePoint = paymentUsePoint;
    }

    /**
     * @return LesPaymentUsePoint
     */
    public BigDecimal getLesPaymentUsePoint() {
        return LesPaymentUsePoint;
    }

    /**
     * @param LesPaymentUsePoint
     *            セットする LesPaymentUsePoint
     */
    public void setLesPaymentUsePoint(BigDecimal lesPaymentUsePoint) {
        LesPaymentUsePoint = lesPaymentUsePoint;
    }

}
