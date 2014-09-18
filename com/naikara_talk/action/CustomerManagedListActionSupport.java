package com.naikara_talk.action;

import java.util.LinkedHashMap;
import java.util.Map;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.CustomerManagedListModel;
import com.naikara_talk.service.CustomerManagedListLoadService;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様管理ページ<br>
 * <b>クラス名称　　　:</b>お客様管理ページクラス。<br>
 * <b>クラス概要　　　:</b>お客様管理ページ共通Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/20 TECS 新規作成。
 */

public abstract class CustomerManagedListActionSupport extends NaikaraActionSupport {

	private static final long serialVersionUID = 1L;

	/** 画面表示のタイトル */
    protected String title = "お客様管理ページ";

    /** Help画面名 */
    protected String helpPageId = "HelpCustomerManagedList.html";

    /** 処理結果 */
    protected CustomerManagedListModel model = new CustomerManagedListModel();

    /** 顧客区分 */
    protected String costomerKbn_rdl;
    protected Map<String, String> costomerKbn_rdll = new LinkedHashMap<String, String>();

    /** 対象期間 */
    protected String objectPeriod_rdl;
    protected Map<String, String> objectPeriod_rdll = new LinkedHashMap<String, String>();

    /** 受講者ID */
    protected String studentId_txt;

    /** 受講者名(漢字) */
    protected String familyFirstJnm_txt;

    /** 受講者名(フリガナ) */
    protected String familyFirstKnm_txt;

    /** 受講者名(ローマ字) */
    protected String familyFirstRomaji_txt;

    /** 受講者名(ニックネーム) */
    protected String nikeNm_txt;

    /** 組織名 */
    protected String organizationJnm_txt;

    /** 期間指定 from */
    protected String periodFrom_txt;

    /** 期間指定 to */
    protected String periodTo_txt;

    /** 一覧選択 */
    protected String select_rdl;

    /** チェックエラー場合、検索判断フラグ */
    protected String hasSearchFlg;

    /** メッセージ */
    protected String message;

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
            initRadio();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 顧客区分と対象期間の初期取得。<br>
     * <br>
     * @param なし
     * @return void
     * @throws Exception
     */
    public void initRadio() throws NaiException {

    	CustomerManagedListLoadService service = new CustomerManagedListLoadService();

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

		// 顧客区分
		this.model.setCostomerKbn(this.costomerKbn_rdl);
		// 受講者ID
		this.model.setStudentId(this.studentId_txt);
		// 受講者名(漢字)
		this.model.setFamilyFirstJnm(this.familyFirstJnm_txt);
		// 受講者名(フリガナ)
		this.model.setFamilyFirstKnm(this.familyFirstKnm_txt);
		// 受講者名(ローマ字)
		this.model.setFamilyFirstRomaji(this.familyFirstRomaji_txt);
		// 受講者名(ニックネーム)
		this.model.setNikeNm(this.nikeNm_txt);
		// 組織名
		this.model.setOrganizationJnm(this.organizationJnm_txt);
		// 対象期間
		this.model.setObjectPeriod(this.objectPeriod_rdl);
		// 期間指定 from
		this.model.setPeriodFrom(this.periodFrom_txt);
		// 期間指定 to
		this.model.setPeriodTo(this.periodTo_txt);
		// 一覧選択
		this.model.setSelect_rdl(this.select_rdl);
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
	public CustomerManagedListModel getModel() {
		return model;
	}

	/**
	 * @param model セットする model
	 */
	public void setModel(CustomerManagedListModel model) {
		this.model = model;
	}

	/**
	 * @return costomerKbn_rdl
	 */
	public String getCostomerKbn_rdl() {
		return costomerKbn_rdl;
	}

	/**
	 * @param costomerKbn_rdl セットする costomerKbn_rdl
	 */
	public void setCostomerKbn_rdl(String costomerKbn_rdl) {
		this.costomerKbn_rdl = costomerKbn_rdl;
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
	 * @return studentId_txt
	 */
	public String getStudentId_txt() {
		return studentId_txt;
	}

	/**
	 * @param studentId_txt セットする studentId_txt
	 */
	public void setStudentId_txt(String studentId_txt) {
		this.studentId_txt = studentId_txt;
	}

	/**
	 * @return familyFirstJnm_txt
	 */
	public String getFamilyFirstJnm_txt() {
		return familyFirstJnm_txt;
	}

	/**
	 * @param familyFirstJnm_txt セットする familyFirstJnm_txt
	 */
	public void setFamilyFirstJnm_txt(String familyFirstJnm_txt) {
		this.familyFirstJnm_txt = familyFirstJnm_txt;
	}

	/**
	 * @return familyFirstKnm_txt
	 */
	public String getFamilyFirstKnm_txt() {
		return familyFirstKnm_txt;
	}

	/**
	 * @param familyFirstKnm_txt セットする familyFirstKnm_txt
	 */
	public void setFamilyFirstKnm_txt(String familyFirstKnm_txt) {
		this.familyFirstKnm_txt = familyFirstKnm_txt;
	}

	/**
	 * @return familyFirstRomaji_txt
	 */
	public String getFamilyFirstRomaji_txt() {
		return familyFirstRomaji_txt;
	}

	/**
	 * @param familyFirstRomaji_txt セットする familyFirstRomaji_txt
	 */
	public void setFamilyFirstRomaji_txt(String familyFirstRomaji_txt) {
		this.familyFirstRomaji_txt = familyFirstRomaji_txt;
	}

	/**
	 * @return nikeNm_txt
	 */
	public String getNikeNm_txt() {
		return nikeNm_txt;
	}

	/**
	 * @param nikeNm_txt セットする nikeNm_txt
	 */
	public void setNikeNm_txt(String nikeNm_txt) {
		this.nikeNm_txt = nikeNm_txt;
	}

	/**
	 * @return organizationJnm_txt
	 */
	public String getOrganizationJnm_txt() {
		return organizationJnm_txt;
	}

	/**
	 * @param organizationJnm_txt セットする organizationJnm_txt
	 */
	public void setOrganizationJnm_txt(String organizationJnm_txt) {
		this.organizationJnm_txt = organizationJnm_txt;
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

}
