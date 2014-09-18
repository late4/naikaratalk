package com.naikara_talk.action;

import java.util.LinkedHashMap;
import java.util.Map;

import com.naikara_talk.model.SalesManagedListModel;
import com.naikara_talk.service.SalesManagedListLoadService;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>入金管理ページ<br>
 * <b>クラス名称　　　:</b>入金管理ページクラス。<br>
 * <b>クラス概要　　　:</b>入金管理ページ共通Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/09 TECS 新規作成。
 */

public abstract class SalesManagedListActionSupport extends NaikaraActionSupport {

	private static final long serialVersionUID = 1L;

    // 画面表示のタイトル
    protected String title = "入金管理ページ";

    // Help画面名
    protected String helpPageId = "HelpSalesManagedList.html";

    /** 処理結果 */
    protected SalesManagedListModel model = new SalesManagedListModel();

    /** 対象年月 */
    protected String objectYyyyMm_txt;

    /** 顧客区分 */
    protected String costomerKbn_rdl;
    protected Map<String, String> costomerKbn_rdll = new LinkedHashMap<String, String>();

    /** 受講者ID／組織ID */
    protected String studentOrganizationId_txt;

    /** 受講者名(フリガナ) */
    protected String familyFirstKum_txt;

    /** 組織名 */
    protected String organizationJnm_txt;

    /** 一覧から選択された明細データ(jsp) */
    protected String select_rdl;

    /** 入金管理ページ(集計)ファイル名 */
    protected String fileNameCount;

    /** 入金管理ページ(明細)ファイル名 */
    protected String fileNameDetail;

	/**
	 * @return fileNameCount
	 */
	public String getFileNameCount() {
		return fileNameCount;
	}

	/**
	 * @param fileNameCount セットする fileNameCount
	 */
	public void setFileNameCount(String fileNameCount) {
		this.fileNameCount = fileNameCount;
	}

	/**
	 * @return fileNameDetail
	 */
	public String getFileNameDetail() {
		return fileNameDetail;
	}

	/**
	 * @param fileNameDetail セットする fileNameDetail
	 */
	public void setFileNameDetail(String fileNameDetail) {
		this.fileNameDetail = fileNameDetail;
	}

	/**
	 * @return objectYyyyMm_txt
	 */
	public String getObjectYyyyMm_txt() {
		return objectYyyyMm_txt;
	}

	/**
	 * @param objectYyyyMm_txt セットする objectYyyyMm_txt
	 */
	public void setObjectYyyyMm_txt(String objectYyyyMm_txt) {
		this.objectYyyyMm_txt = objectYyyyMm_txt;
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
	public SalesManagedListModel getModel() {
		return model;
	}

	/**
	 * @param model セットする model
	 */
	public void setModel(SalesManagedListModel model) {
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
	 * @return studentOrganizationId_txt
	 */
	public String getStudentOrganizationId_txt() {
		return studentOrganizationId_txt;
	}

	/**
	 * @param studentOrganizationId_txt セットする studentOrganizationId_txt
	 */
	public void setStudentOrganizationId_txt(String studentOrganizationId_txt) {
		this.studentOrganizationId_txt = studentOrganizationId_txt;
	}

	/**
	 * @return familyFirstKum_txt
	 */
	public String getFamilyFirstKum_txt() {
		return familyFirstKum_txt;
	}

	/**
	 * @param familyFirstKum_txt セットする familyFirstKum_txt
	 */
	public void setFamilyFirstKum_txt(String familyFirstKum_txt) {
		this.familyFirstKum_txt = familyFirstKum_txt;
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
     * 顧客区分を取得する。<br>
     * <br>
     * @param なし
     * @return void
     * @throws Exception
     */
    public void initRadio() throws Exception {

    	SalesManagedListLoadService service = new SalesManagedListLoadService();
        // 利顧客区分 を取得する
        this.costomerKbn_rdll = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_CUSTOMER_KBN);
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
		// 対象年月
		this.model.setObjectYyyyMm(this.objectYyyyMm_txt);
		// 受講者ID／組織ID
		this.model.setStudentOrganizationId(this.studentOrganizationId_txt);
		// 受講者名(フリガナ)
		this.model.setFamilyFirstKnm(this.familyFirstKum_txt);
		// 組織名
		this.model.setOrganizationJnm(this.organizationJnm_txt);
		// 一覧選択
		this.model.setSelect_rdl(this.select_rdl);
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
            initRadio();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
