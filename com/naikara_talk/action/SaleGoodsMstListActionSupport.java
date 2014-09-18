package com.naikara_talk.action;

import java.util.LinkedHashMap;
import java.util.Map;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.SaleGoodsMstListModel;
import com.naikara_talk.service.SaleGoodsMstListLoadService;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>販売商品マスタメンテナンス(一覧)。<br>
 * <b>クラス名称　　　:</b>販売商品マスタメンテナンスActionスーパークラス。<br>
 * <b>クラス概要　　　:</b>販売商品マスタメンテナンスAction。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/06/19 TECS 新規作成。
 */
public abstract class SaleGoodsMstListActionSupport extends NaikaraActionSupport {

    private static final long serialVersionUID = 1L;

    // 画面表示のタイトル
    protected String title = "販売商品マスタメンテナンス";

    // Help画面名
    protected String helpPageId = "HelpSaleGoodsMstList.html";

    /** メッセージ用使用テーブル名 */
    protected static final String GOODS_MST_TBL_JNM = "販売商品マスタ";


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
     * サービスの呼び出しの実装。
     */
    abstract protected String requestService() throws NaiException;

    /**
     * 利用状態を取得する。<br>
     * <br>
     * @param なし
     * @return void
     * @throws Exception
     */
    public void initRadio() throws NaiException {

        SaleGoodsMstListLoadService service = new SaleGoodsMstListLoadService();
        // 利用状態を取得する
        try {
            this.useKbn_rdll = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_USE_STATE);
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
        this.model.setProcessKbn(this.processKbn_rdl);
        this.model.setGoodsCd(this.goodsCd_txt);
        this.model.setGoodsNm(this.goodsNm_txt);
        this.model.setUseKbn(this.useKbn_rdl);
    }

    /** メッセージ */
    protected String message;

    /** 処理区分(jsp) */
    protected String processKbn_rdl;

    /** 商品コード(jsp) */
    protected String goodsCd_txt;

    /** 商品名(jsp) */
    protected String goodsNm_txt;

    /** 利用状態(jsp) */
    protected String useKbn_rdl;
    protected Map<String, String> useKbn_rdll = new LinkedHashMap<String, String>();

    /** 検索結果 */
    protected SaleGoodsMstListModel model = new SaleGoodsMstListModel();

    /** 一覧から選択された明細データ(jsp) */
    protected String select_rdl;

    /** チェックエラー場合、検索判断フラグ */
    protected String hasSearchFlg;


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
     * @return goodsCd_txt
     */
    public String getGoodsCd_txt() {
        return goodsCd_txt;
    }

    /**
     * @param goodsCd_txt
     *            セットする goodsCd_txt
     */
    public void setGoodsCd_txt(String goodsCd_txt) {
        this.goodsCd_txt = goodsCd_txt;
    }

    /**
     * @return goodsNm_txt
     */
    public String getGoodsNm_txt() {
        return goodsNm_txt;
    }

    /**
     * @param goodsNm_txt
     *            セットする goodsNm_txt
     */
    public void setGoodsNm_txt(String goodsNm_txt) {
        this.goodsNm_txt = goodsNm_txt;
    }

    /**
     * @return useKbn_rdl
     */
    public String getUseKbn_rdl() {
        return useKbn_rdl;
    }

    /**
     * @param useKbn_rdl
     *            セットする useKbn_rdl
     */
    public void setUseKbn_rdl(String useKbn_rdl) {
        this.useKbn_rdl = useKbn_rdl;
    }

    /**
     * @return useKbn_rdll
     */
    public Map<String, String> getUseKbn_rdll() {
        return useKbn_rdll;
    }

    /**
     * @param useKbn_rdll
     *            セットする useKbn_rdll
     */
    public void setUseKbn_rdll(Map<String, String> useKbn_rdll) {
        this.useKbn_rdll = useKbn_rdll;
    }

    /**
     * @return model
     */
    public SaleGoodsMstListModel getModel() {
        return model;
    }

    /**
     * @param model
     *            セットする model
     */
    public void setModel(SaleGoodsMstListModel model) {
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
