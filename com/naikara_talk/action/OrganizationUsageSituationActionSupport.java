package com.naikara_talk.action;

import com.naikara_talk.model.OrganizationUsageSituationModel;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>利用状況照会詳細。<br>
 * <b>クラス名称　　　:</b>利用状況照会詳細。<br>
 * <b>クラス概要　　　:</b>利用状況照会詳細共通AAction。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴　　　　:</b>2013/08/07 TECS 新規作成。
 */

public abstract class OrganizationUsageSituationActionSupport extends NaikaraActionSupport {

	private static final long serialVersionUID = 1L;

	// 画面表示のタイトル
    protected String title = "利用状況照会詳細";

    // Help画面名
    protected String helpPageId = "HelpOrganizationUsageSituation.html";

    /** 処理結果 */
    protected OrganizationUsageSituationModel model = new OrganizationUsageSituationModel();

    /** 受講者所属部署 */
    protected String studentPosition;

    /** 所属組織内ID  */
    protected String positionOrganizationId;

    /** 対象年月 */
    protected String objectYyyyMm_txt;

    /** 受講者ID */
    protected String studentId;

    /** 受講者名(フリガナ) */
    protected String familyFirstKnm;

    /** 受講者名 */
    protected String familyFirstJnm;

    /** メッセージ */
    protected String message;

    protected String select_rdl;

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
	 * @return model
	 */
	public OrganizationUsageSituationModel getModel() {
		return model;
	}

	/**
	 * @param model セットする model
	 */
	public void setModel(OrganizationUsageSituationModel model) {
		this.model = model;
	}

	/**
	 * @return studentPosition
	 */
	public String getStudentPosition() {
		return studentPosition;
	}

	/**
	 * @param studentPosition セットする studentPosition
	 */
	public void setStudentPosition(String studentPosition) {
		this.studentPosition = studentPosition;
	}

	/**
	 * @return positionOrganizationId
	 */
	public String getPositionOrganizationId() {
		return positionOrganizationId;
	}

	/**
	 * @param positionOrganizationId セットする positionOrganizationId
	 */
	public void setPositionOrganizationId(String positionOrganizationId) {
		this.positionOrganizationId = positionOrganizationId;
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
	 * @return studentId
	 */
	public String getStudentId() {
		return studentId;
	}

	/**
	 * @param studentId セットする studentId
	 */
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	/**
	 * @return familyFirstKnm
	 */
	public String getFamilyFirstKnm() {
		return familyFirstKnm;
	}

	/**
	 * @param familyFirstKnm セットする familyFirstKnm
	 */
	public void setFamilyFirstKnm(String familyFirstKnm) {
		this.familyFirstKnm = familyFirstKnm;
	}

	/**
	 * @return familyFirstJnm
	 */
	public String getFamilyFirstJnm() {
		return familyFirstJnm;
	}

	/**
	 * @param familyFirstJnm セットする familyFirstJnm
	 */
	public void setFamilyFirstJnm(String familyFirstJnm) {
		this.familyFirstJnm = familyFirstJnm;
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
     * 画面のパラメータをモデルにセット。<br>
     * <br>
     * @param なし
     * @return なし
     */
	public void setupModel() {

		// 受講者所属部署
		this.model.setStudentPosition(this.studentPosition);
		// 所属組織内ID
		this.model.setPositionOrganizationId(this.positionOrganizationId);
		// 受講者ID
		this.model.setStudentId(this.studentId);
		// 受講者名
		this.model.setFamilyFirstJnm(this.familyFirstJnm);
		// 受講者名(フリガナ)
		this.model.setFamilyFirstKnm(this.familyFirstKnm);
		// 対象年月
		this.model.setObjectYyyyMm(this.objectYyyyMm_txt);
	}

}
