package com.naikara_talk.model;

import java.util.ArrayList;
import java.util.List;

//import java.math.BigDecimal;

import com.naikara_talk.dto.TeacherRateMstDto;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>講師支払比率の設定<br>
 * <b>クラス概要       :</b>講師支払比率の設定Model<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/25 TECS 新規作成
 *                          2013/11/14 TECS 要望対応 No1022-6(講師支払比率99⇒99.999)
 */
public class TeacherRateMstModel implements Model {

    private static final long serialVersionUID = 1L;

    /** チェック：問題なし(正常) */
    public static final String CHECK_OK = "OK";

    /** 利用者ID */
    private String userId;

    /** 名前(姓) */
    private String familyJnm;

    /** 名前(名) */
    private String firstJnm;

    /** 契約開始日 */
    private String contractStartDt;

    /** 契約終了日 */
    private String contractEndDt;

    /** 処理判定 */
    private String processDifference;

    /** 検索結果一覧 */
    private List<TeacherRateMstDto> resultList;

    /** 修正No */
    private String numberNo;

    /** 適用期間：開始日 */
    private String startDt;

    /** 適用期間：終了日 */
    private String endDt;

    //No1022-6 講師支払比率99⇒99.999へ変更対応-Start
    /** 支払比率 */
    //private int paymentRate;
    private String paymentRate;
    //No1022-6 講師支払比率99⇒99.999へ変更対応-End


    /** 支払サイクルコード */
    private String paymentCycleCd;

    /** 源泉税有無区分 */
    private String withholdingTaxKbn;

    /** レコードバージョン番号 */
    private int recordVerNo;

    /**
     * @return userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId セットする userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return familyJnm
     */
    public String getFamilyJnm() {
        return familyJnm;
    }

    /**
     * @param familyJnm セットする familyJnm
     */
    public void setFamilyJnm(String familyJnm) {
        this.familyJnm = familyJnm;
    }

    /**
     * @return firstJnm
     */
    public String getFirstJnm() {
        return firstJnm;
    }

    /**
     * @param firstJnm セットする firstJnm
     */
    public void setFirstJnm(String firstJnm) {
        this.firstJnm = firstJnm;
    }

    /**
     * @return contractStartDt
     */
    public String getContractStartDt() {
        return contractStartDt;
    }

    /**
     * @param contractStartDt セットする contractStartDt
     */
    public void setContractStartDt(String contractStartDt) {
        this.contractStartDt = contractStartDt;
    }

    /**
     * @return contractEndDt
     */
    public String getContractEndDt() {
        return contractEndDt;
    }

    /**
     * @param contractEndDt セットする contractEndDt
     */
    public void setContractEndDt(String contractEndDt) {
        this.contractEndDt = contractEndDt;
    }

    /**
     * @return processDifference
     */
    public String getProcessDifference() {
        return processDifference;
    }

    /**
     * @param processDifference セットする processDifference
     */
    public void setProcessDifference(String processDifference) {
        this.processDifference = processDifference;
    }

    /**
     * @return resultList
     */
    public List<TeacherRateMstDto> getResultList() {
        if (null == this.resultList) {
            this.resultList = new ArrayList<TeacherRateMstDto>();
        }
        return resultList;
    }

    /**
     * @param resultList セットする resultList
     */
    public void setResultList(List<TeacherRateMstDto> resultList) {
        this.resultList = resultList;
    }

    /**
     * @return numberNo
     */
    public String getNumberNo() {
        return numberNo;
    }

    /**
     * @param numberNo セットする numberNo
     */
    public void setNumberNo(String numberNo) {
        this.numberNo = numberNo;
    }

    /**
     * @return startDt
     */
    public String getStartDt() {
        return startDt;
    }

    /**
     * @param startDt セットする startDt
     */
    public void setStartDt(String startDt) {
        this.startDt = startDt;
    }

    /**
     * @return endDt
     */
    public String getEndDt() {
        return endDt;
    }

    /**
     * @param endDt セットする endDt
     */
    public void setEndDt(String endDt) {
        this.endDt = endDt;
    }

    /**
     * @return paymentRate
     */
    public String getPaymentRate() {
        return paymentRate;
    }

    /**
     * @param paymentRate セットする paymentRate
     */
    public void setPaymentRate(String paymentRate) {
        this.paymentRate = paymentRate;
    }

    /**
     * @return paymentCycleCd
     */
    public String getPaymentCycleCd() {
        return paymentCycleCd;
    }

    /**
     * @param paymentCycleCd セットする paymentCycleCd
     */
    public void setPaymentCycleCd(String paymentCycleCd) {
        this.paymentCycleCd = paymentCycleCd;
    }

    /**
     * @return withholdingTaxKbn
     */
    public String getWithholdingTaxKbn() {
        return withholdingTaxKbn;
    }

    /**
     * @param withholdingTaxKbn セットする withholdingTaxKbn
     */
    public void setWithholdingTaxKbn(String withholdingTaxKbn) {
        this.withholdingTaxKbn = withholdingTaxKbn;
    }

    /**
     * @return recordVerNo
     */
    public int getRecordVerNo() {
        return recordVerNo;
    }

    /**
     * @param recordVerNo セットする recordVerNo
     */
    public void setRecordVerNo(int recordVerNo) {
        this.recordVerNo = recordVerNo;
    }

}