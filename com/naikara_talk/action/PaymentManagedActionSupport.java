package com.naikara_talk.action;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dto.PaymentManagedDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.PaymentManagedModel;
import com.naikara_talk.service.PaymentManagedReflectService;
import com.naikara_talk.util.NaikaraStringUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>売上入金・支払管理。<br>
 * <b>クラス名称　　　:</b>支払入力・修正クラス。<br>
 * <b>クラス概要　　　:</b>支払入力・修正共通Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴　　　　:</b>2013/07/24 TECS 新規作成。
 */

public abstract class PaymentManagedActionSupport extends NaikaraActionSupport {

	private static final long serialVersionUID = 1L;

	// 画面表示のタイトル
    protected String title = "支払入力・修正";

    // Help画面名
    protected String helpPageId = "HelpPaymentManaged.html";

    /** 処理結果 */
    protected PaymentManagedModel model = new PaymentManagedModel();

	/** 支払年月 */
    protected String payment_txt;

    /** 一覧から選択された明細データ(jsp) */
    protected String select_rdl;

    /** データ発生年月 */
    protected String dataYyyyMm;

    /** 講師ID (利用者ID) */
    protected String userId;

    /** 名前(姓) + 名前(名) */
    protected String userName;

    /** 支払予定額 */
    protected BigDecimal endPaymentYen;

    /** レコードバージョン番号 */
    protected String recordVerNo;

    /** メッセージ */
    protected String message;

    /** 調整額合計 */
    protected BigDecimal adjustmentYenSum;

    /** 新支払予定額合計 */
    protected BigDecimal newPaymentYenSum;

    /** 調整額 jsp*/
    protected String[] adjustmentYen;

    /** レッスン日 jsp*/
    protected String[] lessonDt;

    /** レッスン時刻コード jsp*/
    protected String[] lessonTmCd;

    /** レコードバージョン番号 jsp 一覧*/
    protected String[] recordVerNoM;


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
	 * @return model
	 */
	public PaymentManagedModel getModel() {
		return model;
	}

	/**
	 * @param model セットする model
	 */
	public void setModel(PaymentManagedModel model) {
		this.model = model;
	}

    /**
	 * @return message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message セットする message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

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

	/**
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName セットする userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return endPaymentYen
	 */
	public BigDecimal getEndPaymentYen() {
		return endPaymentYen;
	}

	/**
	 * @param endPaymentYen セットする endPaymentYen
	 */
	public void setEndPaymentYen(BigDecimal endPaymentYen) {
		this.endPaymentYen = endPaymentYen;
	}

	/**
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title セットする title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return helpPageId
	 */
	public String getHelpPageId() {
		return helpPageId;
	}

	/**
	 * @param helpPageId セットする helpPageId
	 */
	public void setHelpPageId(String helpPageId) {
		this.helpPageId = helpPageId;
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

	/**
     * 画面のパラメータをモデルにセット。<br>
     * <br>
     * @param なし
     * @return なし
     */
	public void setupModel() {
		// 支払年月
		this.model.setPayment_txt(this.payment_txt);
		// データ発生年月
		this.model.setDataYyyyMm(this.dataYyyyMm);
		// 講師ID (利用者ID)
		this.model.setUserId(this.userId);
		// 調整額
		this.model.setAdjustmentYen(this.adjustmentYen);
		// レッスン日
		this.model.setLessonDt(this.lessonDt);
		// レッスン時刻コード
		this.model.setLessonTmCd(this.lessonTmCd);
		// レコードバージョン番号 jsp 一覧
		this.model.setRecordVerNoM(this.recordVerNoM);
		// 調整額(合計)
		this.model.setAdjustmentYenSum(this.adjustmentYenSum);
		// 新支払予定額(合計)
		this.model.setNewPaymentYenSum(this.newPaymentYenSum);
		// レコードバージョン番号
		this.model.setRecordVerNo(this.recordVerNo);
	}

	/**
	 * 一覧下の各合計欄(調整額 合計、新支払予定額 合計)を計算して表示する
	 * @param list
	 * @return なし
	 * @throws NaiException
	 */
	public void compute(List<PaymentManagedDto> list) throws NaiException {

		// 調整額合計
	    BigDecimal adjustmentYenSumR = new BigDecimal(0);
	    // 新支払予定額合計
	    BigDecimal newPaymentYenSumR = new BigDecimal(0);
	    PaymentManagedReflectService service = new PaymentManagedReflectService();

	    PaymentManagedDto dto;
	    for (int i = 0; i < list.size(); i++) {
	    	dto=list.get(i);
	    	// 調整額合計を計算 一覧の各明細の｢調整額｣の集計値
			if (dto.getAdjustmentYen() == null || StringUtils.equals("", dto.getAdjustmentYen())
					|| !service.isHalfNum(dto.getAdjustmentYen())) {

	    		// 調整額が未入力の場合、半角数ではない場合
	    		adjustmentYenSumR = adjustmentYenSumR.add(new BigDecimal(0));
	    	} else {

	    		// 調整額が正常に入力の場合
	    		adjustmentYenSumR = adjustmentYenSumR.add(new BigDecimal(NaikaraStringUtil.delComma(dto.getAdjustmentYen())));
	    	}

	    	// 新支払予定額合計を計算 一覧の各明細の｢新支払予定額｣の集計値
	    	newPaymentYenSumR = newPaymentYenSumR.add(dto.getNewPaymentYen());
	    }

	    // 表示する
	    this.adjustmentYenSum = adjustmentYenSumR;
	    this.newPaymentYenSum = newPaymentYenSumR;

	}

}
