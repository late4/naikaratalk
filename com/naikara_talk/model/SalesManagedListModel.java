package com.naikara_talk.model;

import java.util.List;

import com.naikara_talk.dto.SalesManagedListDto;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>入金管理ページ<br>
 * <b>クラス名称　　　:</b>入金管理ページクラス。<br>
 * <b>クラス概要　　　:</b>入金管理ページModel。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/09 TECS 新規作成。
 */

public class SalesManagedListModel implements Model {

	private static final long serialVersionUID = 1L;

	/** 対象年月 */
    protected String objectYyyyMm;

    /** 顧客区分 */
    protected String costomerKbn;

    /** 受講者ID／組織ID */
    protected String studentOrganizationId;

    /** 受講者名(フリガナ) */
    protected String familyFirstKnm;

    /** 組織名 */
    protected String organizationJnm;

    /** 入金管理ページファイル名 */
    private String fileName;

    /** 一覧から選択された明細データ(jsp) */
    protected String select_rdl;

    /** 検索結果一覧 */
    private List<SalesManagedListDto> resultList;

	/**
	 * @return fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName セットする fileName
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
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
	 * @return organizationJnm
	 */
	public String getOrganizationJnm() {
		return organizationJnm;
	}

	/**
	 * @param organizationJnm セットする organizationJnm
	 */
	public void setOrganizationJnm(String organizationJnm) {
		this.organizationJnm = organizationJnm;
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
	public List<SalesManagedListDto> getResultList() {
		return resultList;
	}

	/**
	 * @param resultList セットする resultList
	 */
	public void setResultList(List<SalesManagedListDto> resultList) {
		this.resultList = resultList;
	}

}
