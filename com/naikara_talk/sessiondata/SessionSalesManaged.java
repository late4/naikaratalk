package com.naikara_talk.sessiondata;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>入金管理ページ<br>
 * <b>クラス名称　　　:</b>入金管理ページクラス。<br>
 * <b>クラス概要　　　:</b>入金管理ページ戻る用のセッション情報。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/09 TECS 新規作成。
 */

public class SessionSalesManaged implements SessionData {

	/** ヘッダの戻るリンク用のSessionKey */
    private static final String HEADER_RETURN_KEY = "SalesManagedList";

    /** 戻る判定Onフラグ */
    private boolean returnOnFlg;

    /**一覧から選択された明細データ(jsp) */
    private String select_rdl;

    /** 対象年月 */
    private String objectYyyyMm_txt;

    /** 顧客区分 */
    private String costomerKbn_rdl;

    /** 受講者ID／組織ID */
    private String studentOrganizationId_txt;

    /** 受講者名(フリガナ) */
    private String familyFirstKum_txt;

    /** 組織名 */
    private String organizationJnm_txt;

	/**
	 * @return returnOnFlg
	 */
	public boolean isReturnOnFlg() {
		return returnOnFlg;
	}

	/**
	 * @param returnOnFlg セットする returnOnFlg
	 */
	public void setReturnOnFlg(boolean returnOnFlg) {
		this.returnOnFlg = returnOnFlg;
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
	 * @return objectYyyyMm_txt
	 */
	public String getObjectYyyyMm_txt() {
		return objectYyyyMm_txt;
	}

	/**
	 * @param objectYyyyMm_txt セットする objectYyyyMm_txt
	 */
	public void setObjectYyyyMm_txt(String objectYyyyMm_txt) {
		this.objectYyyyMm_txt = objectYyyyMm_txt;
	}

	/**
	 * @return costomerKbn_rdl
	 */
	public String getCostomerKbn_rdl() {
		return costomerKbn_rdl;
	}

	/**
	 * @param costomerKbn_rdl セットする costomerKbn_rdl
	 */
	public void setCostomerKbn_rdl(String costomerKbn_rdl) {
		this.costomerKbn_rdl = costomerKbn_rdl;
	}

	/**
	 * @return studentOrganizationId_txt
	 */
	public String getStudentOrganizationId_txt() {
		return studentOrganizationId_txt;
	}

	/**
	 * @param studentOrganizationId_txt セットする studentOrganizationId_txt
	 */
	public void setStudentOrganizationId_txt(String studentOrganizationId_txt) {
		this.studentOrganizationId_txt = studentOrganizationId_txt;
	}

	/**
	 * @return familyFirstKum_txt
	 */
	public String getFamilyFirstKum_txt() {
		return familyFirstKum_txt;
	}

	/**
	 * @param familyFirstKum_txt セットする familyFirstKum_txt
	 */
	public void setFamilyFirstKum_txt(String familyFirstKum_txt) {
		this.familyFirstKum_txt = familyFirstKum_txt;
	}

	/**
	 * @return organizationJnm_txt
	 */
	public String getOrganizationJnm_txt() {
		return organizationJnm_txt;
	}

	/**
	 * @param organizationJnm_txt セットする organizationJnm_txt
	 */
	public void setOrganizationJnm_txt(String organizationJnm_txt) {
		this.organizationJnm_txt = organizationJnm_txt;
	}

	/**
	 * @return headerReturnKey
	 */
	public static String getHeaderReturnKey() {
		return HEADER_RETURN_KEY;
	}

}
