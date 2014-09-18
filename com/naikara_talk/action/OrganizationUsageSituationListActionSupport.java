package com.naikara_talk.action;

import com.naikara_talk.model.OrganizationUsageSituationListModel;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>利用状況照会<br>
 * <b>クラス名称　　　:</b>利用状況照会Actionクラス。<br>
 * <b>クラス概要　　　:</b>利用状況照会共通Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴　　　　:</b>2013/08/02 TECS 新規作成。
 */

public abstract class OrganizationUsageSituationListActionSupport extends NaikaraActionSupport {

	private static final long serialVersionUID = 1L;

	// 画面表示のタイトル
    protected String title = "利用状況照会";

    // Help画面名
    protected String helpPageId = "HelpOrganizationUsageSituationList.html";

    /** 処理結果 */
    protected OrganizationUsageSituationListModel model = new OrganizationUsageSituationListModel();

    /** 組織ID */
    protected String organizationId;

    /** 受講者所属部署 */
    protected String studentPosition_txt;

    /** 所属組織内ID From */
    protected String positionOrganizationIdFrom_txt;

    /** 所属組織内ID To */
    protected String positionOrganizationIdTo_txt;

    /** 対象年月 */
    protected String objectYyyyMm_txt;

    /** 受講者名(フリガナ) */
    protected String familyFirstKnm_txt;

    /** メッセージ */
    protected String message;

    /** 一覧から選択された明細データ(jsp) */
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
	public OrganizationUsageSituationListModel getModel() {
		return model;
	}

	/**
	 * @param model セットする model
	 */
	public void setModel(OrganizationUsageSituationListModel model) {
		this.model = model;
	}

	/**
	 * @return organizationId
	 */
	public String getOrganizationId() {
		return organizationId;
	}

	/**
	 * @param organizationId セットする organizationId
	 */
	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
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
     * 画面のパラメータをモデルにセット。<br>
     * <br>
     * @param なし
     * @return なし
     */
	public void setupModel() {

		// 組織ID
		this.model.setOrganizationId(this.organizationId);
		// 受講者所属部署
		this.model.setStudentPosition(this.studentPosition_txt);
		// 所属組織内ID From
		this.model.setPositionOrganizationIdFrom(this.positionOrganizationIdFrom_txt);
		// 所属組織内ID To
		this.model.setPositionOrganizationIdTo(this.positionOrganizationIdTo_txt);
		// 対象年月
		this.model.setObjectYyyyMm(this.objectYyyyMm_txt);
		// 受講者名(フリガナ)
		this.model.setFamilyFirstKnm(this.familyFirstKnm_txt);
		// 一覧選択
		this.model.setSelect_rdl(this.select_rdl);
	}

}
