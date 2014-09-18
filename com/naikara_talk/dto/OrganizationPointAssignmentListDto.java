package com.naikara_talk.dto;

import java.math.BigDecimal;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>ポイント割振り<br>
 * <b>クラス名称　　　:</b>ポイント割振りクラス。<br>
 * <b>クラス概要　　　:</b>ポイント割振りDTO。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/17 TECS 新規作成。
 */
public class OrganizationPointAssignmentListDto extends AbstractDto{

	private String organizationId;                     // 組織ID
    private String contractFrom;                       // 契約期間 From
    private String contractTo;                         // 契約期間 To
    private BigDecimal balancePoint;                   // 現残高ポイント(合計)
    private int returnCode;                            // returnCode
    private String studentPosition;                    // 受講者所属部署
    private String positionOrganizationId;             // 所属組織内ID
    private String studentName;                        // 名前 名前(姓) + 名前(名)
    private BigDecimal usedPoint;                      // ご利用済ポイント
    private BigDecimal oldbalancePoint;                // ご利用可能ポイント
    private String allocatePoint;                      // 今回割振ポイント
    private BigDecimal newbalancePoint;                // 新残高ポイント
    private BigDecimal purchaseFreePoint;              // 購入／無償ポイント
    private Boolean useStopFlag;                       // 利用停止フラグ
    private String ownershipId;                        // 所有ID
    private String positionOrganizationIdFrom;         // 所属組織内ID From
    private String positionOrganizationIdTo;           // 所属組織内ID To
    private BigDecimal balancePointNumDown;            // 未割振りポイント 一覧下
    private int consSeq;                               // 連番
    private String studentId;                          // 受講者ID
    private int recordVerNo;                           // レコードバージョン番号
    private String recordVerNoM;                       // レコードバージョン番号

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
	 * @return ownershipId
	 */
	public String getOwnershipId() {
		return ownershipId;
	}

	/**
	 * @param ownershipId セットする ownershipId
	 */
	public void setOwnershipId(String ownershipId) {
		this.ownershipId = ownershipId;
	}

	/**
	 * @return useStopFlag
	 */
	public Boolean getUseStopFlag() {
		return useStopFlag;
	}

	/**
	 * @param useStopFlag セットする useStopFlag
	 */
	public void setUseStopFlag(Boolean useStopFlag) {
		this.useStopFlag = useStopFlag;
	}

	/**
	 * @return purchaseFreePoint
	 */
	public BigDecimal getPurchaseFreePoint() {
		return purchaseFreePoint;
	}

	/**
	 * @param purchaseFreePoint セットする purchaseFreePoint
	 */
	public void setPurchaseFreePoint(BigDecimal purchaseFreePoint) {
		this.purchaseFreePoint = purchaseFreePoint;
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
	 * @return studentName
	 */
	public String getStudentName() {
		return studentName;
	}

	/**
	 * @param studentName セットする studentName
	 */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	/**
	 * @return usedPoint
	 */
	public BigDecimal getUsedPoint() {
		return usedPoint;
	}

	/**
	 * @param usedPoint セットする usedPoint
	 */
	public void setUsedPoint(BigDecimal usedPoint) {
		this.usedPoint = usedPoint;
	}

	/**
	 * @return oldbalancePoint
	 */
	public BigDecimal getOldbalancePoint() {
		return oldbalancePoint;
	}

	/**
	 * @param oldbalancePoint セットする oldbalancePoint
	 */
	public void setOldbalancePoint(BigDecimal oldbalancePoint) {
		this.oldbalancePoint = oldbalancePoint;
	}

	/**
	 * @return allocatePoint
	 */
	public String getAllocatePoint() {
		return allocatePoint;
	}

	/**
	 * @param allocatePoint セットする allocatePoint
	 */
	public void setAllocatePoint(String allocatePoint) {
		this.allocatePoint = allocatePoint;
	}

	/**
	 * @return newbalancePoint
	 */
	public BigDecimal getNewbalancePoint() {
		return newbalancePoint;
	}

	/**
	 * @param newbalancePoint セットする newbalancePoint
	 */
	public void setNewbalancePoint(BigDecimal newbalancePoint) {
		this.newbalancePoint = newbalancePoint;
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
	 * @return returnCode
	 */
	public int getReturnCode() {
		return returnCode;
	}

	/**
	 * @param returnCode セットする returnCode
	 */
	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
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
	 * @return balancePoint
	 */
	public BigDecimal getBalancePoint() {
		return balancePoint;
	}

	/**
	 * @param balancePoint セットする balancePoint
	 */
	public void setBalancePoint(BigDecimal balancePoint) {
		this.balancePoint = balancePoint;
	}

}
