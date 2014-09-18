package com.naikara_talk.action;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.CustomerManagedModel;
import com.naikara_talk.service.CustomerManagedLoadService;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様管理ページ詳細<br>
 * <b>クラス名称　　　:</b>お客様管理ページ詳細クラス。<br>
 * <b>クラス概要　　　:</b>お客様管理ページ詳細共通Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/20 TECS 新規作成。
 */

public abstract class CustomerManagedActionSupport extends NaikaraActionSupport {

	private static final long serialVersionUID = 1L;

	/** 画面表示のタイトル */
    protected String title = "お客様管理ページ詳細";

    /** Help画面名 */
    protected String helpPageId = "HelpCustomerManaged.html";

    /** 処理結果 */
    protected CustomerManagedModel model = new CustomerManagedModel();

    /** 対象期間 */
    protected String objectPeriod_rdl;
    protected Map<String, String> objectPeriod_rdll = new LinkedHashMap<String, String>();

    /** 対象期間名 */
    protected String objectPeriodNm;

    /** 期間指定 from */
    protected String periodFrom_txt;

    /** 期間指定 to */
    protected String periodTo_txt;

    /** 一覧選択 */
    protected String select_rdl;

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

    /** 顧客区分 */
    protected String costomerKbn;
    protected Map<String, String> costomerKbn_rdll = new LinkedHashMap<String, String>();

    /** 顧客区分名 */
    protected String costomerKbnNm;

    /** 組織名 */
    protected String organizationJnm;

    /** 受講者(メールアドレス) */
    protected String mailAddress1;

    /** 利用無償ポイント(合計) */
    protected BigDecimal freeUsePoint;

    /** 利用有償ポイント(合計) */
    protected BigDecimal compensationUsePoint;

    /** ポイント外商品購入 */
    protected BigDecimal goodsPurchaseYen;

    /**
     * 対象期間の初期取得。<br>
     * <br>
     * @param なし
     * @return void
     * @throws Exception
     */
    public void initRadio() throws NaiException {

    	CustomerManagedLoadService service = new CustomerManagedLoadService();

        try {
        	// 顧客区分を取得する
            this.costomerKbn_rdll = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_CUSTOMER_KBN);

            // 対象期間を取得する
            this.objectPeriod_rdll = service.selectObjectPeriod();
        } catch (Exception e) {
            throw new NaiException(e);
        }
    }

    /**
     * 画面のパラメータをモデルにセット。<br>
     * <br>
     * @param なし
     * @return なし
     */
	public void setupModel() {
		// 受講者ID
		this.model.setStudentId(this.studentId);
		// 対象期間
		this.model.setObjectPeriod(this.objectPeriod_rdl);
		// 期間指定 from
		this.model.setPeriodFrom(NaikaraStringUtil.delSlash(this.periodFrom_txt));
		// 期間指定 to
		this.model.setPeriodTo(NaikaraStringUtil.delSlash(this.periodTo_txt));
	}

	/**
	 * @return objectPeriod_rdll
	 */
	public Map<String, String> getObjectPeriod_rdll() {
		return objectPeriod_rdll;
	}

	/**
	 * @param objectPeriod_rdll セットする objectPeriod_rdll
	 */
	public void setObjectPeriod_rdll(Map<String, String> objectPeriod_rdll) {
		this.objectPeriod_rdll = objectPeriod_rdll;
	}

	/**
	 * @return costomerKbn_rdll
	 */
	public Map<String, String> getCostomerKbn_rdll() {
		return costomerKbn_rdll;
	}

	/**
	 * @param costomerKbn_rdll セットする costomerKbn_rdll
	 */
	public void setCostomerKbn_rdll(Map<String, String> costomerKbn_rdll) {
		this.costomerKbn_rdll = costomerKbn_rdll;
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
	public CustomerManagedModel getModel() {
		return model;
	}

	/**
	 * @param model セットする model
	 */
	public void setModel(CustomerManagedModel model) {
		this.model = model;
	}

	/**
	 * @return objectPeriod_rdl
	 */
	public String getObjectPeriod_rdl() {
		return objectPeriod_rdl;
	}

	/**
	 * @param objectPeriod_rdl セットする objectPeriod_rdl
	 */
	public void setObjectPeriod_rdl(String objectPeriod_rdl) {
		this.objectPeriod_rdl = objectPeriod_rdl;
	}

	/**
	 * @return objectPeriodNm
	 */
	public String getObjectPeriodNm() {
		return objectPeriodNm;
	}

	/**
	 * @param objectPeriodNm セットする objectPeriodNm
	 */
	public void setObjectPeriodNm(String objectPeriodNm) {
		this.objectPeriodNm = objectPeriodNm;
	}

	/**
	 * @return periodFrom_txt
	 */
	public String getPeriodFrom_txt() {
		return periodFrom_txt;
	}

	/**
	 * @param periodFrom_txt セットする periodFrom_txt
	 */
	public void setPeriodFrom_txt(String periodFrom_txt) {
		this.periodFrom_txt = periodFrom_txt;
	}

	/**
	 * @return periodTo_txt
	 */
	public String getPeriodTo_txt() {
		return periodTo_txt;
	}

	/**
	 * @param periodTo_txt セットする periodTo_txt
	 */
	public void setPeriodTo_txt(String periodTo_txt) {
		this.periodTo_txt = periodTo_txt;
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
	 * @return costomerKbnNm
	 */
	public String getCostomerKbnNm() {
		return costomerKbnNm;
	}

	/**
	 * @param costomerKbnNm セットする costomerKbnNm
	 */
	public void setCostomerKbnNm(String costomerKbnNm) {
		this.costomerKbnNm = costomerKbnNm;
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
	 * @return mailAddress1
	 */
	public String getMailAddress1() {
		return mailAddress1;
	}

	/**
	 * @param mailAddress1 セットする mailAddress1
	 */
	public void setMailAddress1(String mailAddress1) {
		this.mailAddress1 = mailAddress1;
	}

	/**
	 * @return freeUsePoint
	 */
	public BigDecimal getFreeUsePoint() {
		return freeUsePoint;
	}

	/**
	 * @param freeUsePoint セットする freeUsePoint
	 */
	public void setFreeUsePoint(BigDecimal freeUsePoint) {
		this.freeUsePoint = freeUsePoint;
	}

	/**
	 * @return compensationUsePoint
	 */
	public BigDecimal getCompensationUsePoint() {
		return compensationUsePoint;
	}

	/**
	 * @param compensationUsePoint セットする compensationUsePoint
	 */
	public void setCompensationUsePoint(BigDecimal compensationUsePoint) {
		this.compensationUsePoint = compensationUsePoint;
	}

	/**
	 * @return goodsPurchaseYen
	 */
	public BigDecimal getGoodsPurchaseYen() {
		return goodsPurchaseYen;
	}

	/**
	 * @param goodsPurchaseYen セットする goodsPurchaseYen
	 */
	public void setGoodsPurchaseYen(BigDecimal goodsPurchaseYen) {
		this.goodsPurchaseYen = goodsPurchaseYen;
	}

}
