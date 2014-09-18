package com.naikara_talk.sessiondata;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>売上入金・支払管理。<br>
 * <b>クラス名称　　　:</b>売上入金・支払管理のセッション情報クラス。<br>
 * <b>クラス概要　　　:</b>売上入金・支払管理の戻る用のセッション情報。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>
 * 2013/07/25 TECS 新規作成。
 */

public class SessionPaymentManaged implements SessionData {

	/** ヘッダの戻るリンク用のSessionKey */
    private static final String HEADER_RETURN_KEY = "PaymentManagedList";

    /** 戻る判定Onフラグ */
    private boolean returnOnFlg;

    /** 検索Key：支払年月 */
    private String payment_txt;

    private String select_rdl;

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
	 * @return headerReturnKey
	 */
	public static String getHeaderReturnKey() {
		return HEADER_RETURN_KEY;
	}

}
