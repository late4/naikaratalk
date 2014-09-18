package com.naikara_talk.model;

import java.util.List;

import com.naikara_talk.dto.CustomerManagedDto;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様管理ページ詳細<br>
 * <b>クラス名称　　　:</b>お客様管理ページ詳細クラス。<br>
 * <b>クラス概要　　　:</b>お客様管理ページ詳細Model。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/20 TECS 新規作成。
 */

public class CustomerManagedModel implements Model {

	private static final long serialVersionUID = 1L;

	/** 対象期間：過去３ヶ月 */
    public static final String OBJECT_PERIOD_ONE = "1";

    /** 対象期間：過去６ヶ月 */
    public static final String OBJECT_PERIOD_TWO = "2";

    /** 対象期間：過去１２ヶ月 */
    public static final String OBJECT_PERIOD_THREE = "3";

    /** 対象期間：期間指定 */
    public static final String OBJECT_PERIOD_ZERO = "0";

    /** サーバーのシステム日付の年月-2ヶ月 */
    public static final int OBJECT_PERIOD_INT_ONE = -2;

    /** サーバーのシステム日付の年月-5ヶ月 */
    public static final int OBJECT_PERIOD_INT_TWO = -5;

    /** サーバーのシステム日付の年月-11ヶ月 */
    public static final int OBJECT_PERIOD_INT_THREE = -11;

    /** サーバーのシステム日付の年月-1ヶ月 */
    public static final int OBJECT_PERIOD_INT_MONE = -1;

    /** 受講者ID */
    protected String studentId;

    /** 対象期間 */
    protected String objectPeriod;

    /** 期間指定 from */
    protected String periodFrom;

    /** 期間指定 to */
    protected String periodTo;

    /** 検索結果一覧 */
    private List<CustomerManagedDto> resultList;

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
	 * @return objectPeriod
	 */
	public String getObjectPeriod() {
		return objectPeriod;
	}

	/**
	 * @param objectPeriod セットする objectPeriod
	 */
	public void setObjectPeriod(String objectPeriod) {
		this.objectPeriod = objectPeriod;
	}

	/**
	 * @return periodFrom
	 */
	public String getPeriodFrom() {
		return periodFrom;
	}

	/**
	 * @param periodFrom セットする periodFrom
	 */
	public void setPeriodFrom(String periodFrom) {
		this.periodFrom = periodFrom;
	}

	/**
	 * @return periodTo
	 */
	public String getPeriodTo() {
		return periodTo;
	}

	/**
	 * @param periodTo セットする periodTo
	 */
	public void setPeriodTo(String periodTo) {
		this.periodTo = periodTo;
	}

	/**
	 * @return resultList
	 */
	public List<CustomerManagedDto> getResultList() {
		return resultList;
	}

	/**
	 * @param resultList セットする resultList
	 */
	public void setResultList(List<CustomerManagedDto> resultList) {
		this.resultList = resultList;
	}

}
