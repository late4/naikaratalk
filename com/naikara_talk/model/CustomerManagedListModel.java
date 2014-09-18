package com.naikara_talk.model;

import java.util.List;

import com.naikara_talk.dto.CustomerManagedListDto;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様管理ページ<br>
 * <b>クラス名称　　　:</b>お客様管理ページクラス。<br>
 * <b>クラス概要　　　:</b>お客様管理ページModel。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/20 TECS 新規作成。
 */

public class CustomerManagedListModel implements Model {

	private static final long serialVersionUID = 1L;

	/** (検索ボタン押下時)チェック：データ件数ZERO件 */
    public static final int ERR_ZERO_DATA = 1;

    /** (検索ボタン押下時)チェック：データ件数上限以上の件数 */
    public static final int ERR_MAXOVER_DATA = 2;

    /** (登録/選択ボタン押下時)チェック：一覧部の項目が選択なし */
    public static final int ERR_NO_SELECT = 3;

    /** (登録/選択ボタン押下時)チェック：一覧画面へ戻る */
    public static final int ERR_LIST_BACK = 4;

    /** (検索ボタン押下時)チェック：期間指定ではない場合は、開始日と終了日入力できません */
    public static final int ERR_OBJECT_PERIOD = 5;

    /** (検索ボタン押下時)チェック：期間指定である場合は、開始日必須 */
    public static final int ERR_PERIOD_FROM = 6;

    /** (検索ボタン押下時)チェック：期間指定である場合は、終了日必須 */
    public static final int ERR_PERIOD_TO = 7;

    /** (検索ボタン押下時)チェック：開始日≦終了日となるように  */
    public static final int ERR_FROM_TO = 8;

    /** チェック：問題なし(正常) */
    public static final int CHECK_OK = 0;

	/** 対象期間：過去３ヶ月 */
    public static final String OBJECT_PERIOD_ONE = "1";

    /** 対象期間：過去６ヶ月 */
    public static final String OBJECT_PERIOD_TWO = "2";

    /** 対象期間：過去１２ヶ月 */
    public static final String OBJECT_PERIOD_THREE = "3";

    /** 対象期間：期間指定 */
    public static final String OBJECT_PERIOD_ZERO = "0";

	/** 対象期間：過去３ヶ月 */
    public static final String OBJECT_PERIOD_ONE_NM = "過去３ヶ月";

    /** 対象期間：過去６ヶ月 */
    public static final String OBJECT_PERIOD_TWO_NM = "過去６ヶ月";

    /** 対象期間：過去１２ヶ月 */
    public static final String OBJECT_PERIOD_THREE_NM = "過去１２ヶ月";

    /** 対象期間：期間指定 */
    public static final String OBJECT_PERIOD_ZERO_NM = "期間指定";

    /** サーバーのシステム日付の年月-2ヶ月 */
    public static final int OBJECT_PERIOD_INT_ONE = -2;

    /** サーバーのシステム日付の年月-5ヶ月 */
    public static final int OBJECT_PERIOD_INT_TWO = -5;

    /** サーバーのシステム日付の年月-11ヶ月 */
    public static final int OBJECT_PERIOD_INT_THREE = -11;

    /** サーバーのシステム日付の年月-1ヶ月 */
    public static final int OBJECT_PERIOD_INT_MONE = -1;

	/** 顧客区分 */
    protected String costomerKbn;

    /** 受講者ID */
    protected String studentId;

    /** 受講者名(漢字) */
    protected String familyFirstJnm;

    /** 受講者名(フリガナ) */
    protected String familyFirstKnm;

    /** 受講者名(ローマ字) */
    protected String familyFirstRomaji;

    /** 受講者名(ニックネーム) */
    protected String nikeNm;

    /** 組織名 */
    protected String organizationJnm;

    /** 対象期間 */
    protected String objectPeriod;

    /** 期間指定 from */
    protected String periodFrom;

    /** 期間指定 to */
    protected String periodTo;

    /** 検索結果一覧 */
    private List<CustomerManagedListDto> resultList;

    /** 一覧選択 */
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
	 * @return familyFirstRomaji
	 */
	public String getFamilyFirstRomaji() {
		return familyFirstRomaji;
	}

	/**
	 * @param familyFirstRomaji セットする familyFirstRomaji
	 */
	public void setFamilyFirstRomaji(String familyFirstRomaji) {
		this.familyFirstRomaji = familyFirstRomaji;
	}

	/**
	 * @return nikeNm
	 */
	public String getNikeNm() {
		return nikeNm;
	}

	/**
	 * @param nikeNm セットする nikeNm
	 */
	public void setNikeNm(String nikeNm) {
		this.nikeNm = nikeNm;
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
	public List<CustomerManagedListDto> getResultList() {
		return resultList;
	}

	/**
	 * @param resultList セットする resultList
	 */
	public void setResultList(List<CustomerManagedListDto> resultList) {
		this.resultList = resultList;
	}

}
