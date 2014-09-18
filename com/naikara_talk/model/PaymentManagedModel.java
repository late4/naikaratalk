package com.naikara_talk.model;

import java.math.BigDecimal;
import java.util.List;

import com.naikara_talk.dto.PaymentManagedDto;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>売上入金・支払管理。<br>
 * <b>クラス名称　　　:</b>支払入力・修正クラス。<br>
 * <b>クラス概要　　　:</b>支払入力・修正Model。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴　　　　:</b>2013/07/30 TECS 新規作成。
 */

public class PaymentManagedModel implements Model {

	private static final long serialVersionUID = 1L;

	/** 検索結果一覧 */
    private List<PaymentManagedDto> resultList;

	/** 支払年月 */
    private String payment_txt;

    /** データ発生年月 */
    private String dataYyyyMm;

    /** 講師ID (利用者ID) */
    private String userId;

    /** レコードバージョン番号*/
    private String recordVerNo;

	/** 調整額 jsp*/
    private String[] adjustmentYen;

    /** レッスン日 jsp*/
    private String[] lessonDt;

    /** レッスン時刻コード jsp*/
    private String[] lessonTmCd;

    /** レコードバージョン番号 jsp 一覧*/
    private String[] recordVerNoM;

    /** 調整額合計 */
    private BigDecimal adjustmentYenSum;

    /** 新支払予定額合計 */
    private BigDecimal newPaymentYenSum;

    /**
	 * @return recordVerNo
	 */
	public String getRecordVerNo() {
		return recordVerNo;
	}

	/**
	 * @param recordVerNo セットする recordVerNo
	 */
	public void setRecordVerNo(String recordVerNo) {
		this.recordVerNo = recordVerNo;
	}
    /**
	 * @return adjustmentYenSum
	 */
	public BigDecimal getAdjustmentYenSum() {
		return adjustmentYenSum;
	}

	/**
	 * @param adjustmentYenSum セットする adjustmentYenSum
	 */
	public void setAdjustmentYenSum(BigDecimal adjustmentYenSum) {
		this.adjustmentYenSum = adjustmentYenSum;
	}

	/**
	 * @return newPaymentYenSum
	 */
	public BigDecimal getNewPaymentYenSum() {
		return newPaymentYenSum;
	}

	/**
	 * @param newPaymentYenSum セットする newPaymentYenSum
	 */
	public void setNewPaymentYenSum(BigDecimal newPaymentYenSum) {
		this.newPaymentYenSum = newPaymentYenSum;
	}

	/**
	 * @return adjustmentYen
	 */
	public String[] getAdjustmentYen() {
		return adjustmentYen;
	}

	/**
	 * @param adjustmentYen セットする adjustmentYen
	 */
	public void setAdjustmentYen(String[] adjustmentYen) {
		this.adjustmentYen = adjustmentYen;
	}

	/**
	 * @return lessonDt
	 */
	public String[] getLessonDt() {
		return lessonDt;
	}

	/**
	 * @param lessonDt セットする lessonDt
	 */
	public void setLessonDt(String[] lessonDt) {
		this.lessonDt = lessonDt;
	}

	/**
	 * @return lessonTmCd
	 */
	public String[] getLessonTmCd() {
		return lessonTmCd;
	}

	/**
	 * @param lessonTmCd セットする lessonTmCd
	 */
	public void setLessonTmCd(String[] lessonTmCd) {
		this.lessonTmCd = lessonTmCd;
	}

	/**
	 * @return recordVerNoM
	 */
	public String[] getRecordVerNoM() {
		return recordVerNoM;
	}

	/**
	 * @param recordVerNoM セットする recordVerNoM
	 */
	public void setRecordVerNoM(String[] recordVerNoM) {
		this.recordVerNoM = recordVerNoM;
	}

	/**
	 * @return resultList
	 */
	public List<PaymentManagedDto> getResultList() {
		return resultList;
	}

	/**
	 * @param resultList セットする resultList
	 */
	public void setResultList(List<PaymentManagedDto> resultList) {
		this.resultList = resultList;
	}
	/**
	 * @return payment_txt
	 */
	public String getPayment_txt() {
		return payment_txt;
	}

	/**
	 * @param payment_txt セットする payment_txt
	 */
	public void setPayment_txt(String payment_txt) {
		this.payment_txt = payment_txt;
	}

	/**
	 * @return dataYyyyMm
	 */
	public String getDataYyyyMm() {
		return dataYyyyMm;
	}

	/**
	 * @param dataYyyyMm セットする dataYyyyMm
	 */
	public void setDataYyyyMm(String dataYyyyMm) {
		this.dataYyyyMm = dataYyyyMm;
	}

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

}
