package com.naikara_talk.action;

import com.naikara_talk.model.PaymentManagedListModel;


/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>売上入金・支払管理。<br>
 * <b>クラス名称　　　:</b>支払管理ページクラス。<br>
 * <b>クラス概要　　　:</b>支払管理ページ共通Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴　　　　:</b>2013/07/23 TECS 新規作成。
 */

public abstract class PaymentManagedListActionSupport extends NaikaraActionSupport {

	private static final long serialVersionUID = 1L;

	// 画面表示のタイトル
    protected String title = "支払管理ページ";

    // Help画面名
    protected String helpPageId = "HelpPaymentManagedList.html";

    /** 処理結果 */
    protected PaymentManagedListModel model = new PaymentManagedListModel();

    /** 支払対象使用不可flag */
    protected boolean checkBoxFlag;

	/** 支払年月 */
    protected String payment_txt;

	/** 一覧から選択された明細データ(jsp) */
    protected String select_rdl;

    /** 一覧から選択された明細データ(jsp) */
    protected String[] checkboxBaseValue;

    /** メッセージ */
    protected String message;

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
	 * @return checkboxBaseValue
	 */
	public String[] getCheckboxBaseValue() {
		return checkboxBaseValue;
	}

	/**
	 * @param checkboxBaseValue セットする checkboxBaseValue
	 */
	public void setCheckboxBaseValue(String[] checkboxBaseValue) {
		this.checkboxBaseValue = checkboxBaseValue;
	}

	/**
	 * @return checkBoxFlag
	 */
	public boolean isCheckBoxFlag() {
		return checkBoxFlag;
	}

	/**
	 * @param checkBoxFlag セットする checkBoxFlag
	 */
	public void setCheckBoxFlag(boolean checkBoxFlag) {
		this.checkBoxFlag = checkBoxFlag;
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
	 * @return model
	 */
	public PaymentManagedListModel getModel() {
		return model;
	}

	/**
	 * @param model セットする model
	 */
	public void setModel(PaymentManagedListModel model) {
		this.model = model;
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
     * 画面のパラメータをモデルにセット。<br>
     * <br>
     * @param なし
     * @return なし
     */
	public void setupModel() {
		this.model.setPayment_txt(this.payment_txt);
		this.model.setSelect_rdl(this.select_rdl);
		this.model.setCheckboxBaseValue(this.checkboxBaseValue);
	}
}
