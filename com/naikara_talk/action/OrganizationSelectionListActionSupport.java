package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.OrganizationSelectionListModel;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>法人_初期処理<br>
 * <b>クラス名称       :</b>組織マイページ(組織 選択)Actionスーパークラス。<br>
 * <b>クラス概要       :</b>組織マイページ(組織 選択)。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/02 TECS 新規作成
 */
public abstract class OrganizationSelectionListActionSupport extends CertificationActionSupport {

    private static final long serialVersionUID = 1L;

    // 画面表示のタイトル
    protected String title = "組織選択";

    // Help画面名
    protected String helpPageId = "HelpOrganizationSelectionList.html";

    /**
     * サービスの呼び出しの実装。
     */
    abstract protected String requestService() throws NaiException;

    /**
     * 画面のパラメータをモデルにセット。<br>
     * <br>
     * 画面のパラメータをモデルにセット。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception なし
     */
    public void setupModel() {
        this.model.setOrganizationId(this.organizationId_txt);
        this.model.setOrganizationNm(this.organizationNm_txt);
        this.model.setManagKnm(this.managKnm_txt);
    }

    /** メッセージ */
    protected String message;

    /** 組織ID(jsp) */
    protected String organizationId_txt;

    /** 組織名(jsp) */
    protected String organizationNm_txt;

    /** 責任者名(フリガナ)(jsp) */
    protected String managKnm_txt;

    /** 連番(jsp) */
    protected String consSeq;

    /** 検索結果 */
    protected OrganizationSelectionListModel model = new OrganizationSelectionListModel();

    /** 一覧から選択された明細データ(jsp) */
    protected String select_rdl;

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            セットする title
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
     * @param helpPageId
     *            セットする helpPageId
     */
    public void setHelpPageId(String helpPageId) {
        this.helpPageId = helpPageId;
    }

    /**
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     *            セットする message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return organizationId_txt
     */
    public String getOrganizationId_txt() {
        return organizationId_txt;
    }

    /**
     * @param organizationId_txt
     *            セットする organizationId_txt
     */
    public void setOrganizationId_txt(String organizationId_txt) {
        this.organizationId_txt = organizationId_txt;
    }

    /**
     * @return organizationNm_txt
     */
    public String getOrganizationNm_txt() {
        return organizationNm_txt;
    }

    /**
     * @param organizationNm_txt
     *            セットする messageorganizationNm_txt
     */
    public void setOrganizationNm_txt(String organizationNm_txt) {
        this.organizationNm_txt = organizationNm_txt;
    }

    /**
     * @return managKnm
     */
    public String getManagKnm_txt() {
        return managKnm_txt;
    }

    /**
     * @param managKnm
     *            セットする managKnm
     */
    public void setManagKnm_txt(String managKnm_txt) {
        this.managKnm_txt = managKnm_txt;
    }

    /**
     * @return consSeq
     */
    public String getConsSeq() {
        return consSeq;
    }

    /**
     * @param consSeq
     *            セットする consSeq
     */
    public void setConsSeq(String consSeq) {
        this.consSeq = consSeq;
    }

    /**
     * @return model
     */
    public OrganizationSelectionListModel getModel() {
        return model;
    }

    /**
     * @param model
     *            セットする model
     */
    public void setModel(OrganizationSelectionListModel model) {
        this.model = model;
    }

    /**
     * @return select_rdl
     */
    public String getSelect_rdl() {
        return select_rdl;
    }

    /**
     * @param select_rdl
     *            セットする select_rdl
     */
    public void setSelect_rdl(String select_rdl) {
        this.select_rdl = select_rdl;
    }

}
