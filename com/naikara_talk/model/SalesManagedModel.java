package com.naikara_talk.model;

import java.util.List;

import com.naikara_talk.dto.SalesManagedDto;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>入金管理ページ詳細<br>
 * <b>クラス名称　　　:</b>入金管理ページ詳細クラス。<br>
 * <b>クラス概要　　　:</b>入金管理ページ詳細Model。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/09 TECS 新規作成。
 */

public class SalesManagedModel implements Model {

	private static final long serialVersionUID = 1L;

	/** 対象年月 */
    protected String objectYyyyMm;

    /** 顧客区分 */
    protected String costomerKbn;

    /** 受講者ID／組織ID */
    protected String studentOrganizationId;

    /** 検索結果一覧 */
    private List<SalesManagedDto> resultList;

	/**
	 * @return resultList
	 */
	public List<SalesManagedDto> getResultList() {
		return resultList;
	}

	/**
	 * @param resultList セットする resultList
	 */
	public void setResultList(List<SalesManagedDto> resultList) {
		this.resultList = resultList;
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
	 * @return serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
