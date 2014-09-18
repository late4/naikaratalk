package com.naikara_talk.sessiondata;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様管理ページ<br>
 * <b>クラス名称　　　:</b>お客様管理ページクラス。<br>
 * <b>クラス概要　　　:</b>お客様管理ページ戻る用のセッション情報。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/20 TECS 新規作成。
 */

public class SessionCustomerManaged implements SessionData {

	 /** ヘッダの戻るリンク用のSessionKey */
    private static final String HEADER_RETURN_KEY = "naikara_talk.sessiondata.CustomerManagedList";

    /** 戻る判定Onフラグ */
    private boolean returnOnFlg;

    /** 検索Key：選択された明細のKey */
    private String searchKey;

    /** 画面状態判断フラグ */
    private String hasSearchFlg;

    /** 顧客区分 */
    private String costomerKbn;

    /** 受講者ID */
    private String studentId;

    /** 受講者名(漢字) */
    private String familyFirstJnm;

    /** 受講者名(フリガナ) */
    private String familyFirstKnm;

    /** 受講者名(ローマ字) */
    private String familyFirstRomaji;

    /** 組織名 */
    private String organizationJnm;

    /** 受講者名(ニックネーム) */
    private String nikeNm;

    /** 対象期間 */
    private String objectPeriod;

    /** 期間指定 from */
    private String periodFrom;

    /** 期間指定 to */
    private String periodTo;

    /** 検索Key：顧客区分 */
    private String searchCostomerKbn;

    /** 検索Key：受講者ID */
    private String searchStudentId;

    /** 検索Key：受講者名(漢字) */
    private String searchFamilyFirstJnm;

    /** 検索Key：受講者名(フリガナ) */
    private String searchFamilyFirstKnm;

    /** 検索Key：受講者名(ローマ字) */
    private String searchFamilyFirstRomaji;

    /** 検索Key：組織名 */
    private String searchOrganizationJnm;

    /** 検索Key：受講者名(ニックネーム) */
    private String searchNikeNm;

    /** 検索Key：対象期間 */
    private String searchObjectPeriod;

    /** 検索Key：期間指定 from */
    private String searchPeriodFrom;

    /** 検索Key：期間指定 to */
    private String searchPeriodTo;

    /**
     * このセッションデータのキーを返却する
     */
    public static String getKey() {
        return HEADER_RETURN_KEY;
    }

	/**
	 * @return returnOnFlg
	 */
	public boolean getReturnOnFlg() {
		return returnOnFlg;
	}

	/**
	 * @param returnOnFlg セットする returnOnFlg
	 */
	public void setReturnOnFlg(boolean returnOnFlg) {
		this.returnOnFlg = returnOnFlg;
	}

	/**
	 * @return searchKey
	 */
	public String getSearchKey() {
		return searchKey;
	}

	/**
	 * @param searchKey セットする searchKey
	 */
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	/**
	 * @return hasSearchFlg
	 */
	public String getHasSearchFlg() {
		return hasSearchFlg;
	}

	/**
	 * @param hasSearchFlg セットする hasSearchFlg
	 */
	public void setHasSearchFlg(String hasSearchFlg) {
		this.hasSearchFlg = hasSearchFlg;
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
	 * @return searchCostomerKbn
	 */
	public String getSearchCostomerKbn() {
		return searchCostomerKbn;
	}

	/**
	 * @param searchCostomerKbn セットする searchCostomerKbn
	 */
	public void setSearchCostomerKbn(String searchCostomerKbn) {
		this.searchCostomerKbn = searchCostomerKbn;
	}

	/**
	 * @return searchStudentId
	 */
	public String getSearchStudentId() {
		return searchStudentId;
	}

	/**
	 * @param searchStudentId セットする searchStudentId
	 */
	public void setSearchStudentId(String searchStudentId) {
		this.searchStudentId = searchStudentId;
	}

	/**
	 * @return searchFamilyFirstJnm
	 */
	public String getSearchFamilyFirstJnm() {
		return searchFamilyFirstJnm;
	}

	/**
	 * @param searchFamilyFirstJnm セットする searchFamilyFirstJnm
	 */
	public void setSearchFamilyFirstJnm(String searchFamilyFirstJnm) {
		this.searchFamilyFirstJnm = searchFamilyFirstJnm;
	}

	/**
	 * @return searchFamilyFirstKnm
	 */
	public String getSearchFamilyFirstKnm() {
		return searchFamilyFirstKnm;
	}

	/**
	 * @param searchFamilyFirstKnm セットする searchFamilyFirstKnm
	 */
	public void setSearchFamilyFirstKnm(String searchFamilyFirstKnm) {
		this.searchFamilyFirstKnm = searchFamilyFirstKnm;
	}

	/**
	 * @return searchFamilyFirstRomaji
	 */
	public String getSearchFamilyFirstRomaji() {
		return searchFamilyFirstRomaji;
	}

	/**
	 * @param searchFamilyFirstRomaji セットする searchFamilyFirstRomaji
	 */
	public void setSearchFamilyFirstRomaji(String searchFamilyFirstRomaji) {
		this.searchFamilyFirstRomaji = searchFamilyFirstRomaji;
	}

	/**
	 * @return searchOrganizationJnm
	 */
	public String getSearchOrganizationJnm() {
		return searchOrganizationJnm;
	}

	/**
	 * @param searchOrganizationJnm セットする searchOrganizationJnm
	 */
	public void setSearchOrganizationJnm(String searchOrganizationJnm) {
		this.searchOrganizationJnm = searchOrganizationJnm;
	}

	/**
	 * @return searchNikeNm
	 */
	public String getSearchNikeNm() {
		return searchNikeNm;
	}

	/**
	 * @param searchNikeNm セットする searchNikeNm
	 */
	public void setSearchNikeNm(String searchNikeNm) {
		this.searchNikeNm = searchNikeNm;
	}

	/**
	 * @return searchObjectPeriod
	 */
	public String getSearchObjectPeriod() {
		return searchObjectPeriod;
	}

	/**
	 * @param searchObjectPeriod セットする searchObjectPeriod
	 */
	public void setSearchObjectPeriod(String searchObjectPeriod) {
		this.searchObjectPeriod = searchObjectPeriod;
	}

	/**
	 * @return searchPeriodFrom
	 */
	public String getSearchPeriodFrom() {
		return searchPeriodFrom;
	}

	/**
	 * @param searchPeriodFrom セットする searchPeriodFrom
	 */
	public void setSearchPeriodFrom(String searchPeriodFrom) {
		this.searchPeriodFrom = searchPeriodFrom;
	}

	/**
	 * @return searchPeriodTo
	 */
	public String getSearchPeriodTo() {
		return searchPeriodTo;
	}

	/**
	 * @param searchPeriodTo セットする searchPeriodTo
	 */
	public void setSearchPeriodTo(String searchPeriodTo) {
		this.searchPeriodTo = searchPeriodTo;
	}

}
