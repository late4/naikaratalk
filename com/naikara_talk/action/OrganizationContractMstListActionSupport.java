package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.OrganizationContractMstListModel;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>組織契約情報登録一覧。<br>
 * <b>クラス名称       :</b>組織契約情報登録一覧Actionクラス。<br>
 * <b>クラス概要       :</b>組織契約情報登録一覧の情報ができる<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/01 TECS 新規作成
 */
public abstract class OrganizationContractMstListActionSupport extends NaikaraActionSupport {
    private static final long serialVersionUID = 1L;

    // 画面表示のタイトル
    protected String title = "組織契約情報登録一覧";

    // Help画面名
    protected String helpPageId = "HelpOrganizationContractMstList.html";

    /**
     * サービスの呼び出しの実装。
     */
    abstract protected String requestService() throws NaiException;

    /**
     * 画面のパラメータをモデルにセット。<br>
     * <br>
     * @param なし
     * @return なし
     */
    public void setupModel() {

        // 組織ID
        this.model.setOrganizationId(this.organizationId_txt);
        // 組織名
        this.model.setOrganizationJnm(this.organizationJnm_txt);
        // 処理区分
        this.model.setProcessKbn_rdl(this.processKbn_rdl);
    }

    /** 検索結果 */
    protected OrganizationContractMstListModel model = new OrganizationContractMstListModel();
    /** 処理区分 */
    protected String processKbn_rdl;
    /** 組織ID */
    protected String organizationId_txt;
    /** 組織名 */
    protected String organizationJnm_txt;
    /** 一覧部の「選択」 */
    protected String select_rdl;
    /** チェックエラー場合、検索判断フラグ */
    protected String hasSearchFlg;
    /** メッセージ */
    protected String message;

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
    public OrganizationContractMstListModel getModel() {
        return model;
    }

    /**
     * @param model セットする model
     */
    public void setModel(OrganizationContractMstListModel model) {
        this.model = model;
    }

    /**
     * @return processKbn_rdl
     */
    public String getProcessKbn_rdl() {
        return processKbn_rdl;
    }

    /**
     * @param processKbn_rdl セットする processKbn_rdl
     */
    public void setProcessKbn_rdl(String processKbn_rdl) {
        this.processKbn_rdl = processKbn_rdl;
    }

    /**
     * @return organizationId_txt
     */
    public String getOrganizationId_txt() {
        return organizationId_txt;
    }

    /**
     * @param organizationId_txt セットする organizationId_txt
     */
    public void setOrganizationId_txt(String organizationId_txt) {
        this.organizationId_txt = organizationId_txt;
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

}
