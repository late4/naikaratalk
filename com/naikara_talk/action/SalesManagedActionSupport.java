package com.naikara_talk.action;

import java.math.BigDecimal;

import com.naikara_talk.model.SalesManagedModel;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>入金管理ページ詳細<br>
 * <b>クラス名称　　　:</b>入金管理ページ詳細クラス。<br>
 * <b>クラス概要　　　:</b>入金管理ページ詳細共通Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/09 TECS 新規作成。
 */

public abstract class SalesManagedActionSupport extends NaikaraActionSupport {

	private static final long serialVersionUID = 1L;

    // 画面表示のタイトル
    protected String title = "入金管理ページ詳細";

    // Help画面名
    protected String helpPageId = "HelpSalesManaged.html";

    /** 処理結果 */
    protected SalesManagedModel model = new SalesManagedModel();

    /** 対象年月 */
    protected String objectYyyyMm;

    /** 顧客区分 */
    protected String costomerKbn;

    /** 顧客区分名称 */
    protected String costomerKbnName;

    /** 受講者ID／組織ID */
    protected String studentOrganizationId;

    /** 受講者名/組織名 */
    protected String studentOrganizationJnm;

    /** 前受有償ポイント（合計） */
    protected BigDecimal compensationBeforePoint;

    /**  購入金額(合計)  購入有償ポイント（合計） */
    protected BigDecimal compensationPurchasePoint;

    /**  利用無償ポイント(合計) */
    protected BigDecimal freeUsePoint;

    /**  利用有償ポイント（合計） */
    protected BigDecimal compensationUsePoint;


    /** 一覧から選択された明細データ(jsp) */
    protected String select_rdl;


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
	public SalesManagedModel getModel() {
		return model;
	}


	/**
	 * @param model セットする model
	 */
	public void setModel(SalesManagedModel model) {
		this.model = model;
	}


	/**
	 * @return objectYyyyMm
	 */
	public String getObjectYyyyMm() {
		return objectYyyyMm;
	}


	/**
	 * @param objectYyyyMm セットする objectYyyyMm
	 */
	public void setObjectYyyyMm(String objectYyyyMm) {
		this.objectYyyyMm = objectYyyyMm;
	}


	/**
	 * @return costomerKbn
	 */
	public String getCostomerKbn() {
		return costomerKbn;
	}


	/**
	 * @param costomerKbn セットする costomerKbn
	 */
	public void setCostomerKbn(String costomerKbn) {
		this.costomerKbn = costomerKbn;
	}


	/**
	 * @return costomerKbnName
	 */
	public String getCostomerKbnName() {
		return costomerKbnName;
	}


	/**
	 * @param costomerKbnName セットする costomerKbnName
	 */
	public void setCostomerKbnName(String costomerKbnName) {
		this.costomerKbnName = costomerKbnName;
	}


	/**
	 * @return studentOrganizationId
	 */
	public String getStudentOrganizationId() {
		return studentOrganizationId;
	}


	/**
	 * @param studentOrganizationId セットする studentOrganizationId
	 */
	public void setStudentOrganizationId(String studentOrganizationId) {
		this.studentOrganizationId = studentOrganizationId;
	}


	/**
	 * @return studentOrganizationJnm
	 */
	public String getStudentOrganizationJnm() {
		return studentOrganizationJnm;
	}


	/**
	 * @param studentOrganizationJnm セットする studentOrganizationJnm
	 */
	public void setStudentOrganizationJnm(String studentOrganizationJnm) {
		this.studentOrganizationJnm = studentOrganizationJnm;
	}


	/**
	 * @return compensationBeforePoint
	 */
	public BigDecimal getCompensationBeforePoint() {
		return compensationBeforePoint;
	}


	/**
	 * @param compensationBeforePoint セットする compensationBeforePoint
	 */
	public void setCompensationBeforePoint(BigDecimal compensationBeforePoint) {
		this.compensationBeforePoint = compensationBeforePoint;
	}


	/**
	 * @return compensationPurchasePoint
	 */
	public BigDecimal getCompensationPurchasePoint() {
		return compensationPurchasePoint;
	}


	/**
	 * @param compensationPurchasePoint セットする compensationPurchasePoint
	 */
	public void setCompensationPurchasePoint(BigDecimal compensationPurchasePoint) {
		this.compensationPurchasePoint = compensationPurchasePoint;
	}


	/**
	 * @return freeUsePoint
	 */
	public BigDecimal getFreeUsePoint() {
		return freeUsePoint;
	}


	/**
	 * @param freeUsePoint セットする freeUsePoint
	 */
	public void setFreeUsePoint(BigDecimal freeUsePoint) {
		this.freeUsePoint = freeUsePoint;
	}


	/**
	 * @return compensationUsePoint
	 */
	public BigDecimal getCompensationUsePoint() {
		return compensationUsePoint;
	}


	/**
	 * @param compensationUsePoint セットする compensationUsePoint
	 */
	public void setCompensationUsePoint(BigDecimal compensationUsePoint) {
		this.compensationUsePoint = compensationUsePoint;
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

		// 顧客区分
		this.model.setCostomerKbn(this.costomerKbn);
		// 対象年月
		this.model.setObjectYyyyMm(this.objectYyyyMm);
		// 受講者ID／組織ID
		this.model.setStudentOrganizationId(this.studentOrganizationId);
	}

}
