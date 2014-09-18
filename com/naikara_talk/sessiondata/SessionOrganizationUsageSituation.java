package com.naikara_talk.sessiondata;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>利用状況照会。<br>
 * <b>クラス名称　　　:</b>利用状況照会のセッション情報クラス。<br>
 * <b>クラス概要　　　:</b>売利用状況照会の戻る用のセッション情報。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>
 * 2013/07/25 TECS 新規作成。
 */

public class SessionOrganizationUsageSituation implements SessionData {

	/** ヘッダの戻るリンク用のSessionKey */
    private static final String HEADER_RETURN_KEY = "OrganizationUsageSituationList";

    /** 戻る判定Onフラグ */
    private boolean returnOnFlg;

    /**一覧から選択された明細データ(jsp) */
    private String select_rdl;

    /** 対象年月 */
    private String objectYyyyMm_txt;

    /** 受講者名(フリガナ) */
    private String familyFirstKnm_txt;

    /** 受講者所属部署 */
    private String studentPosition_txt;

    /** 所属組織内ID From */
    private String positionOrganizationIdFrom_txt;

    /** 所属組織内ID To */
    private String positionOrganizationIdTo_txt;

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
	 * @return familyFirstKnm_txt
	 */
	public String getFamilyFirstKnm_txt() {
		return familyFirstKnm_txt;
	}

	/**
	 * @param familyFirstKnm_txt セットする familyFirstKnm_txt
	 */
	public void setFamilyFirstKnm_txt(String familyFirstKnm_txt) {
		this.familyFirstKnm_txt = familyFirstKnm_txt;
	}

	/**
	 * @return studentPosition_txt
	 */
	public String getStudentPosition_txt() {
		return studentPosition_txt;
	}

	/**
	 * @param studentPosition_txt セットする studentPosition_txt
	 */
	public void setStudentPosition_txt(String studentPosition_txt) {
		this.studentPosition_txt = studentPosition_txt;
	}

	/**
	 * @return positionOrganizationIdFrom_txt
	 */
	public String getPositionOrganizationIdFrom_txt() {
		return positionOrganizationIdFrom_txt;
	}

	/**
	 * @param positionOrganizationIdFrom_txt セットする positionOrganizationIdFrom_txt
	 */
	public void setPositionOrganizationIdFrom_txt(
			String positionOrganizationIdFrom_txt) {
		this.positionOrganizationIdFrom_txt = positionOrganizationIdFrom_txt;
	}

	/**
	 * @return positionOrganizationIdTo_txt
	 */
	public String getPositionOrganizationIdTo_txt() {
		return positionOrganizationIdTo_txt;
	}

	/**
	 * @param positionOrganizationIdTo_txt セットする positionOrganizationIdTo_txt
	 */
	public void setPositionOrganizationIdTo_txt(String positionOrganizationIdTo_txt) {
		this.positionOrganizationIdTo_txt = positionOrganizationIdTo_txt;
	}

	/**
	 * @return headerReturnKey
	 */
	public static String getHeaderReturnKey() {
		return HEADER_RETURN_KEY;
	}

}
