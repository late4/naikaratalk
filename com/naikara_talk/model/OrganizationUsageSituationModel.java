package com.naikara_talk.model;

import java.util.List;

import com.naikara_talk.dto.OrganizationUsageSituationDto;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>利用状況照会詳細<br>
 * <b>クラス名称　　　:</b>利用状況照会詳細クラス。<br>
 * <b>クラス概要　　　:</b>利用状況照会詳細Model。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴　　　　:</b>2013/08/02 TECS 新規作成。
 */

public class OrganizationUsageSituationModel implements Model {

	private static final long serialVersionUID = 1L;

	 /** 受講者所属部署 */
    protected String studentPosition;

    /** 所属組織内ID  */
    protected String positionOrganizationId;

    /** 対象年月 */
    protected String objectYyyyMm;

    /** 受講者ID */
    private String studentId;

    /** 受講者名(フリガナ) */
    private String familyFirstKnm;

    /** 受講者名 */
    private String familyFirstJnm;

    /** 検索結果一覧 */
    private List<OrganizationUsageSituationDto> resultList;

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
	 * @return resultList
	 */
	public List<OrganizationUsageSituationDto> getResultList() {
		return resultList;
	}

	/**
	 * @param resultList セットする resultList
	 */
	public void setResultList(List<OrganizationUsageSituationDto> resultList) {
		this.resultList = resultList;
	}



}
