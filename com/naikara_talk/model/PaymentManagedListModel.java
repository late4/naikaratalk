package com.naikara_talk.model;

import java.util.List;

import com.naikara_talk.dto.PaymentManagedListDto;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>売上入金・支払管理。<br>
 * <b>クラス名称　　　:</b>支払管理ページクラス。<br>
 * <b>クラス概要　　　:</b>支払管理ページModel。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴　　　　:</b>2013/07/23 TECS 新規作成。
 */

public class PaymentManagedListModel implements Model {

	private static final long serialVersionUID = 1L;

	/** 検索結果一覧 */
    private List<PaymentManagedListDto> resultList;

    /** 支払年月 */
    private String payment_txt;

    /** 一覧から選択された明細データ(jsp) */
    private String select_rdl;

    private String[] checkboxBaseValue;

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
	 * @return resultList
	 */
	public List<PaymentManagedListDto> getResultList() {
		return resultList;
	}

	/**
	 * @param resultList セットする resultList
	 */
	public void setResultList(List<PaymentManagedListDto> resultList) {
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



}
