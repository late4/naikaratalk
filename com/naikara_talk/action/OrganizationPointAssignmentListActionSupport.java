package com.naikara_talk.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dto.OrganizationContractListDto;
import com.naikara_talk.dto.OrganizationPointAssignmentListDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.OrganizationPointAssignmentListModel;
import com.naikara_talk.service.OrganizationPointAssignmentListLoadService;
import com.naikara_talk.service.OrganizationPointAssignmentListSearchService;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>ポイント割振り<br>
 * <b>クラス名称　　　:</b>ポイント割振りクラス。<br>
 * <b>クラス概要　　　:</b>ポイント割振り共通Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/17 TECS 新規作成。
 */
public abstract class OrganizationPointAssignmentListActionSupport extends NaikaraActionSupport {

	private static final long serialVersionUID = 1L;

    // 画面表示のタイトル
    protected String title = "ポイント割振り";

    // Help画面名
    protected String helpPageId = "HelpOrganizationPointAssignmentList.html";

    /** 処理結果 */
    protected OrganizationPointAssignmentListModel model = new OrganizationPointAssignmentListModel();

    /** 組織ID */
    protected String organizationId;

    /** 契約期間 map */
    protected Map<String, String> contractMap = new LinkedHashMap<String, String>();

	/** 契約期間 */
    protected String contract;

	/** 受講者所属部署 */
    protected String studentPosition;

    /** 所属組織内ID From */
    protected String positionOrganizationIdFrom;

    /** 所属組織内ID To */
    protected String positionOrganizationIdTo;

    /** 未割振りポイント */
    protected BigDecimal balancePointNum;

    /** 現残高ポイント(合計) */
    protected BigDecimal balancePoint;

    /** ご利用済ポイント */
    protected BigDecimal sumUsedPoint;

    /** ご利用可能ポイント */
    protected BigDecimal sumOldbalancePoint;

    /** 今回割振ポイント */
    protected BigDecimal sumAllocatePoint;

    /** 新残高ポイント */
    protected BigDecimal sumNewbalancePoint;

    /** 未割振りポイント 一覧下 */
    protected BigDecimal balancePointNumDown;

    /** 連番 */
    protected int consSeq;

    /** レコードバージョン番号 */
    protected int recordVerNo;

    /** メッセージ */
    protected String message;

    /** 今回割振ポイント */
    protected String[] allocatePoint;

    protected String allocatePointCon;

	/**
	 * @return allocatePointCon
	 */
	public String getAllocatePointCon() {
		return allocatePointCon;
	}


	/**
	 * @param allocatePointCon セットする allocatePointCon
	 */
	public void setAllocatePointCon(String allocatePointCon) {
		this.allocatePointCon = allocatePointCon;
	}


	/**
	 * @return allocatePoint
	 */
	public String[] getAllocatePoint() {
		return allocatePoint;
	}


	/**
	 * @param allocatePoint セットする allocatePoint
	 */
	public void setAllocatePoint(String[] allocatePoint) {
		this.allocatePoint = allocatePoint;
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
	 * @return sumUsedPoint
	 */
	public BigDecimal getSumUsedPoint() {
		return sumUsedPoint;
	}


	/**
	 * @param sumUsedPoint セットする sumUsedPoint
	 */
	public void setSumUsedPoint(BigDecimal sumUsedPoint) {
		this.sumUsedPoint = sumUsedPoint;
	}


	/**
	 * @return sumOldbalancePoint
	 */
	public BigDecimal getSumOldbalancePoint() {
		return sumOldbalancePoint;
	}


	/**
	 * @param sumOldbalancePoint セットする sumOldbalancePoint
	 */
	public void setSumOldbalancePoint(BigDecimal sumOldbalancePoint) {
		this.sumOldbalancePoint = sumOldbalancePoint;
	}


	/**
	 * @return sumAllocatePoint
	 */
	public BigDecimal getSumAllocatePoint() {
		return sumAllocatePoint;
	}


	/**
	 * @param sumAllocatePoint セットする sumAllocatePoint
	 */
	public void setSumAllocatePoint(BigDecimal sumAllocatePoint) {
		this.sumAllocatePoint = sumAllocatePoint;
	}


	/**
	 * @return sumNewbalancePoint
	 */
	public BigDecimal getSumNewbalancePoint() {
		return sumNewbalancePoint;
	}


	/**
	 * @param sumNewbalancePoint セットする sumNewbalancePoint
	 */
	public void setSumNewbalancePoint(BigDecimal sumNewbalancePoint) {
		this.sumNewbalancePoint = sumNewbalancePoint;
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
	public OrganizationPointAssignmentListModel getModel() {
		return model;
	}


	/**
	 * @param model セットする model
	 */
	public void setModel(OrganizationPointAssignmentListModel model) {
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
	 * @return contractMap
	 */
	public Map<String, String> getContractMap() {
		return contractMap;
	}


	/**
	 * @param contractMap セットする contractMap
	 */
	public void setContractMap(Map<String, String> contractMap) {
		this.contractMap = contractMap;
	}


	/**
	 * @return contract
	 */
	public String getContract() {
		return contract;
	}


	/**
	 * @param contract セットする contract
	 */
	public void setContract(String contract) {
		this.contract = contract;
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
	 * @return balancePointNum
	 */
	public BigDecimal getBalancePointNum() {
		return balancePointNum;
	}


	/**
	 * @param balancePointNum セットする balancePointNum
	 */
	public void setBalancePointNum(BigDecimal balancePointNum) {
		this.balancePointNum = balancePointNum;
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
		// 契約期間
		this.model.setContract(this.contract);
		// 受講者所属部署
		this.model.setStudentPosition(this.studentPosition);
		// 所属組織内ID From
		this.model.setPositionOrganizationIdFrom(this.positionOrganizationIdFrom);
		// 所属組織内ID To
		this.model.setPositionOrganizationIdTo(this.positionOrganizationIdTo);
		// 連番
		this.model.setConsSeq(this.consSeq);
		//レコードバージョン番号
		this.model.setRecordVerNo(this.recordVerNo);
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
     * 合計計算。
     * @param list 画面一覧のlist
     * @return なし
	 * @throws NaiException
     */
    public void sum(List<OrganizationPointAssignmentListDto> list) throws NaiException {
    	BigDecimal sumUsedPoint = new BigDecimal(0);               // ご利用済ポイント
    	BigDecimal sumOldbalancePoint = new BigDecimal(0);         // ご利用可能ポイント
    	BigDecimal sumAllocatePoint = new BigDecimal(0);           // 今回割振ポイント
    	BigDecimal sumNewbalancePoint = new BigDecimal(0);         // 新残高ポイント
    	OrganizationPointAssignmentListDto dto ;
    	OrganizationPointAssignmentListSearchService service = new OrganizationPointAssignmentListSearchService();

    	for(int i=0;list.size()>i;i++){
    		dto=list.get(i);
    		sumUsedPoint = sumUsedPoint.add(dto.getUsedPoint());
    		sumOldbalancePoint = sumOldbalancePoint.add(dto.getOldbalancePoint());
    		if (StringUtils.equals("", dto.getAllocatePoint()) || !service.isHalfNum(dto.getAllocatePoint())) {
    			// 今回割振ポイントが未入力の場合
    			sumAllocatePoint = sumAllocatePoint.add(new BigDecimal(0));
    		} else {
    			// 今回割振ポイントが入力の場合
    			sumAllocatePoint = sumAllocatePoint.add(new BigDecimal(NaikaraStringUtil.delComma(dto.getAllocatePoint())));
    		}
    		sumNewbalancePoint = sumNewbalancePoint.add(dto.getNewbalancePoint());
    	}

    	this.sumUsedPoint = sumUsedPoint;
    	this.sumOldbalancePoint = sumOldbalancePoint;
    	this.sumAllocatePoint = sumAllocatePoint;
    	this.sumNewbalancePoint = sumNewbalancePoint;

    }

    /**
     * 初期処理、表示データの取得。<br>
     * <br>
     * @param なし
     * @return なし
     * @throws Exception
     */
	public void load() throws Exception {

		// 表示データを取得する
		OrganizationPointAssignmentListLoadService service = new OrganizationPointAssignmentListLoadService();

		Map<String, String> map = new LinkedHashMap<String, String>();
		// ドロップダウン・ラジオボタン :選択なし・すべて
    	map.put(NaikaraTalkConstants.CHOICE_ALL_ZERO, "選択してください");

		// 組織契約期間の一覧取得
		ArrayList<OrganizationContractListDto> oclDto = service.getContractList(this.model);
		OrganizationContractListDto dto;
		for (int i = 0; oclDto.size() > i; i++) {
			dto = oclDto.get(i);
			map.put(dto.getContract(), dto.getContract());
		}
		// 画面の契約期間を設定
		this.contractMap = map;
	}

	/**
     * チェック。<br>
     * <br>
     * コースコードを戻り値で返却する<br>
     * <br>
     * @param なし
     * @return なし
     */
    @Override
    public void validate() {

        // チェックエラーの場合、利用状態の再取得。
        try {
        	load();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
