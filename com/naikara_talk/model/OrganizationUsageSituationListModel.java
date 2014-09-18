package com.naikara_talk.model;

import java.util.List;

import com.naikara_talk.dto.OrganizationUsageSituationListDto;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>利用状況照会<br>
 * <b>クラス名称　　　:</b>利用状況照会クラス。<br>
 * <b>クラス概要　　　:</b>利用状況照会Model。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴　　　　:</b>2013/08/02 TECS 新規作成。
 */

public class OrganizationUsageSituationListModel implements Model {

	private static final long serialVersionUID = 1L;

	/** 受講者所属部署 */
    protected String studentPosition;

    /** 所属組織内ID From */
    protected String positionOrganizationIdFrom;

    /** 所属組織内ID To */
    protected String positionOrganizationIdTo;

    /** 組織ID */
    protected String organizationId;

    /** 対象年月 */
    protected String objectYyyyMm;

    /** 受講者名(フリガナ) */
    protected String familyFirstKnm;

    /** 検索結果一覧 */
    private List<OrganizationUsageSituationListDto> resultList;

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
	 * @return resultList
	 */
	public List<OrganizationUsageSituationListDto> getResultList() {
		return resultList;
	}

	/**
	 * @param resultList セットする resultList
	 */
	public void setResultList(List<OrganizationUsageSituationListDto> resultList) {
		this.resultList = resultList;
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
	 * @return positionOrganizationIdFrom
	 */
	public String getPositionOrganizationIdFrom() {
		return positionOrganizationIdFrom;
	}

	/**
	 * @param positionOrganizationIdFrom セットする positionOrganizationIdFrom
	 */
	public void setPositionOrganizationIdFrom(String positionOrganizationIdFrom) {
		this.positionOrganizationIdFrom = positionOrganizationIdFrom;
	}

	/**
	 * @return positionOrganizationIdTo
	 */
	public String getPositionOrganizationIdTo() {
		return positionOrganizationIdTo;
	}

	/**
	 * @param positionOrganizationIdTo セットする positionOrganizationIdTo
	 */
	public void setPositionOrganizationIdTo(String positionOrganizationIdTo) {
		this.positionOrganizationIdTo = positionOrganizationIdTo;
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

}
