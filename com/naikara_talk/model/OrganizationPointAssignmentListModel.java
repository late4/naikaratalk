package com.naikara_talk.model;

import java.math.BigDecimal;
import java.util.List;

import com.naikara_talk.dto.OrganizationPointAssignmentListDto;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>ポイント割振り<br>
 * <b>クラス名称　　　:</b>ポイント割振り初期処理クラス。<br>
 * <b>クラス概要　　　:</b>ポイント割振り初期処理Model。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/17 TECS 新規作成。
 */
public class OrganizationPointAssignmentListModel implements Model {

	private static final long serialVersionUID = 1L;

	/**
	 * @param contract セットする contract
	 */
	public void setContract(String contract) {
		this.contract = contract;
	}

    /** 受講者所属部署 */
    protected String studentPosition;

    /** 所属組織内ID From */
    protected String positionOrganizationIdFrom;

    /** 所属組織内ID To */
    protected String positionOrganizationIdTo;

    /** 契約期間 */
    protected String contract;

	/** 組織ID */
	protected String organizationId;

	/** 契約期間 From */
    protected String contractFrom;

    /** 契約期間 To */
    protected String contractTo;

    /** 連番 */
    protected int consSeq;

    /** レコードバージョン番号 */
    protected int recordVerNo;

    /** レコードバージョン番号 */
    protected String recordVerNoM;

    /**
	 * @return recordVerNoM
	 */
	public String getRecordVerNoM() {
		return recordVerNoM;
	}

	/**
	 * @param recordVerNoM セットする recordVerNoM
	 */
	public void setRecordVerNoM(String recordVerNoM) {
		this.recordVerNoM = recordVerNoM;
	}

	/** 未割振りポイント 一覧下 */
    protected BigDecimal balancePointNumDown;


	/**
	 * @return balancePointNumDown
	 */
	public BigDecimal getBalancePointNumDown() {
		return balancePointNumDown;
	}

	/**
	 * @param balancePointNumDown セットする balancePointNumDown
	 */
	public void setBalancePointNumDown(BigDecimal balancePointNumDown) {
		this.balancePointNumDown = balancePointNumDown;
	}

	/**
	 * @return consSeq
	 */
	public int getConsSeq() {
		return consSeq;
	}

	/**
	 * @param consSeq セットする consSeq
	 */
	public void setConsSeq(int consSeq) {
		this.consSeq = consSeq;
	}

	/**
	 * @return recordVerNo
	 */
	public int getRecordVerNo() {
		return recordVerNo;
	}

	/**
	 * @param recordVerNo セットする recordVerNo
	 */
	public void setRecordVerNo(int recordVerNo) {
		this.recordVerNo = recordVerNo;
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
	 * @return contractFrom
	 */
	public String getContractFrom() {
		return contractFrom;
	}

	/**
	 * @param contractFrom セットする contractFrom
	 */
	public void setContractFrom(String contractFrom) {
		this.contractFrom = contractFrom;
	}

	/**
	 * @return contractTo
	 */
	public String getContractTo() {
		return contractTo;
	}

	/**
	 * @param contractTo セットする contractTo
	 */
	public void setContractTo(String contractTo) {
		this.contractTo = contractTo;
	}

	/**
	 * @return resultList
	 */
	public List<OrganizationPointAssignmentListDto> getResultList() {
		return resultList;
	}

	/**
	 * @param resultList セットする resultList
	 */
	public void setResultList(List<OrganizationPointAssignmentListDto> resultList) {
		this.resultList = resultList;
	}

	/**
	 * @return contract
	 */
	public String getContract() {
		return contract;
	}

	/** 検索結果一覧 */
    private List<OrganizationPointAssignmentListDto> resultList;



}
