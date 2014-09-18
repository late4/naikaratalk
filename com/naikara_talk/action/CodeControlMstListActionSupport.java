package com.naikara_talk.action;

import java.util.ArrayList;
import java.util.List;

import com.naikara_talk.dto.CodeClassMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.CodeControlMstListModel;
import com.naikara_talk.service.CodeControlMstListLoadService;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>コード管理マスタメンテナンス(一覧)。<br>
 * <b>クラス名称　　　:</b>コード管理マスタメンテナンスActionスーパークラス。<br>
 * <b>クラス概要　　　:</b>コード管理マスタメンテナンスAction。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/16 TECS 新規作成。
 */
public abstract class CodeControlMstListActionSupport extends NaikaraActionSupport {

    private static final long serialVersionUID = 1L;

    // 画面表示のタイトル
    protected String title = "コード管理マスタメンテナンス";

    // Help画面名
    protected String helpPageId = "HelpCodeControlMstList.html";

    /** メッセージ用使用テーブル名 */
    protected static final String CODE_MANAG_MST_TBL_JNM = "コード管理マスタ";


    /**
     * チェック。<br>
     * <br>
     * コード種別名を戻り値で返却する<br>
     * <br>
     * @param なし
     * @return なし
     */
    @Override
    public void validate() {

        // チェックエラーの場合、コード種別名(ドロップダウンリスト)の再取得。
        try {
            this.initSelectList();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * サービスの呼び出しの実装。
     */
    abstract protected String requestService() throws NaiException;

    /**
     * コード種別名(ドロップダウンリスト)を取得する。<br>
     * <br>
     * @param なし
     * @return void
     * @throws Exception
     */
    public void initSelectList() throws Exception {

        // CodeControlMstListLoadServiceクラスの生成
        CodeControlMstListLoadService service = new CodeControlMstListLoadService();

        // CodeClassMstDtoクラスの生成
        CodeClassMstDto dto = new CodeClassMstDto();

        // コード種別名(ドロップダウンリスト)のデータの取得
        List<CodeClassMstDto> list = service.selectCodeClassMst(dto);

        // コード種別名(ドロップダウンリスト)を取得する
        this.setCdCategoryList(list);

    }

    /**
     * 画面のパラメータをモデルにセット。<br>
     * <br>
     * @param なし
     * @return なし
     */
    public void setupModel() {
        this.model.setProcessKbn(this.processKbn_rdl);            // 処理区分
        this.model.setCdCategorySelected(this.cdCategory_sel);    // コード種別(選択された値)
        this.model.setCdCategoryList(this.cdCategoryList);        // コード種別(List)
        this.model.setManagerCd(this.managerCd_txt);              // 汎用コード
        this.model.setManagerNm(this.managerNm_txt);              // 汎用フィールド
    }


    /** メッセージ */
    protected String message;

    /** 処理区分(jsp) */
    protected String processKbn_rdl;

    /** コード種別名(jsp) name */
    protected String cdCategory_sel;

    /** コード種別名(jsp) name,list */
    protected List<CodeClassMstDto> cdCategoryList = new ArrayList<CodeClassMstDto>();

    /** コード種別名(jsp)-listKey */
    protected String cdCategory;

    /** コード種別名(jsp)-value 初期値 */
    protected String defaultCdCategory;

    /** コード種別名(jsp)-検索ボタン押下時の選択値 */
    protected String searchPressCdCategory;

    /** コード種別名(jsp)-選択値 */
    protected String cdCategorySelected;

    /** 汎用コード(jsp) */
    protected String managerCd_txt;

    /** 汎用フィールド(jsp) */
    protected String managerNm_txt;

    /** 検索結果 */
    protected CodeControlMstListModel model = new CodeControlMstListModel();

    /** 一覧から選択された明細データ(jsp) */
    protected String select_rdl;

    /** チェックエラー場合、検索判断フラグ */
    protected String hasSearchFlg;

    /** 画面状態フラグ */
    protected String initFlg;

    /**
     * 画面状態フラグを取得する
     * @return initFlg
     */
    public String getInitFlg() {
        return initFlg;
    }

    /**
     * 画面状態フラグをセットする
     * @param initFlg セットする initFlg
     */
    public void setInitFlg(String initFlg) {
        this.initFlg = initFlg;
    }

    /**
     * @return hasSearchFlg
     */
    public String getHasSearchFlg() {
        return hasSearchFlg;
    }

    /**
     * @param hasSearchFlg
     *            セットする hasSearchFlg
     */
    public void setHasSearchFlg(String hasSearchFlg) {
        this.hasSearchFlg = hasSearchFlg;
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
     * @return processKbn_rdl
     */
    public String getProcessKbn_rdl() {
        return processKbn_rdl;
    }

    /**
     * @param processKbn_rdl
     *            セットする processKbn
     */
    public void setProcessKbn_rdl(String processKbn_rdl) {
        this.processKbn_rdl = processKbn_rdl;
    }


    /**
     * @return cdCategory_sel
     */
    public String getCdCategory_sel() {
        return processKbn_rdl;
    }

    /**
     * @param cdCategory_sel
     *            セットする cdCategory_sel
     */
    public void setCdCategory_sel(String cdCategory_sel) {
        this.cdCategory_sel = cdCategory_sel;
    }

    /**
     * @return cdCategoryList
     */
    public List<CodeClassMstDto> getCdCategoryList() {
        return cdCategoryList;
    }

    /**
     * @param cdCategoryList
     *            セットする cdCategoryList
     */
    public void setCdCategoryList(List<CodeClassMstDto> cdCategoryList) {
        this.cdCategoryList = cdCategoryList;
    }

    /**
     * @return defaultCdCategory
     */
    public String getDefaultCdCategory() {
        return defaultCdCategory;
    }

    /**
     * @param defaultCdCategory
     *            セットする defaultCdCategory
     */
    public void setDefaultCdCategory(String defaultCdCategory) {
        this.defaultCdCategory = defaultCdCategory;
    }

    /**
     * @return cdCategorySelected
     */
    public String getCdCategorySelected() {
        return cdCategorySelected;
    }

    /**
     * @param cdCategorySelected
     *            セットする cdCategorySelected
     */
    public void setCdCategorySelected(String cdCategorySelected) {
        this.cdCategorySelected = cdCategorySelected;
    }

    /**
     * @return searchPressCdCategory
     */
    public String getSearchPressCdCategory() {
        return searchPressCdCategory;
    }

    /**
     * @param searchPressCdCategory
     *            セットする searchPressCdCategory
     */
    public void setSearchPressCdCategory(String searchPressCdCategory) {
        this.searchPressCdCategory = searchPressCdCategory;
    }

    /**
     * @return managerCd_txt
     */
    public String getManagerCd_txt() {
        return managerCd_txt;
    }

    /**
     * @param managerCd_txt
     *            セットする managerCd_txt
     */
    public void setManagerCd_txt(String managerCd_txt) {
        this.managerCd_txt = managerCd_txt;
    }

    /**
     * @return managerNm_txt
     */
    public String getManagerNm_txt() {
        return managerNm_txt;
    }

    /**
     * @param managerNm_txt
     *            セットする managerNm_txt
     */
    public void setManagerNm_txt(String managerNm_txt) {
        this.managerNm_txt = managerNm_txt;
    }

    /**
     * @return model
     */
    public CodeControlMstListModel getModel() {
        return model;
    }

    /**
     * @param model
     *            セットする model
     */
    public void setModel(CodeControlMstListModel model) {
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
