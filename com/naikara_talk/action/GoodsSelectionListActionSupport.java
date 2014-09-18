package com.naikara_talk.action;

import java.util.ArrayList;
import java.util.List;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.GoodsSelectionListModel;
import com.naikara_talk.util.NaikaraStringUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>コースマスタメンテナンス(商品選択)<br>
 * <b>クラス概要       :</b>コースマスタメンテナンス(商品選択)共通Action<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/05 TECS 新規作成
 */
public abstract class GoodsSelectionListActionSupport extends NaikaraActionSupport {

    private static final long serialVersionUID = 1L;

    // 画面表示のタイトル
    protected String title = "商品　選択";

    // Help画面名
    protected String helpPageId = "HelpGoodsSelectionList.html";

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

        // 商品コード
        this.model.setGoodsCd(this.merchandiseCd_txt);
        // 商品名
        this.model.setGoodsNm(this.merchandiseNm_txt);
        // 提供開始日
        this.model.setUseStrDt(NaikaraStringUtil.converToYYYYMMDD(this.useStrDt));
        // 提供終了日
        this.model.setUseEndDt(NaikaraStringUtil.converToYYYYMMDD(this.useEndDt));

    }

    /** 商品コード */
    private String merchandiseCd_txt;

    /** 商品名 */
    private String merchandiseNm_txt;

    /** 処理結果 */
    protected GoodsSelectionListModel model = new GoodsSelectionListModel();

    /** 一覧部の「選択」 */
    protected List<String> select_chk_list = new ArrayList<String>();

    /** ボタン区分 */
    protected String buttonIndex;

    /** 提供開始日 */
    private String useStrDt;

    /** 提供終了日 */
    private String useEndDt;

    /** 一覧部の「選択」が選択されていない場合 */
    protected String error_noSelect;

    /** 前画面の　「複数選択」　ボタンで遷移した場合は、最大５個。 */
    protected String error_gtFive;

    /** 前画面の　「検索」　ボタンで遷移した場合は、最大１個 */
    protected String error_gtOne;

    /** 検索判断フラグ */
    protected String searchFlg;

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
     * @return merchandiseCd_txt
     */
    public String getMerchandiseCd_txt() {
        return merchandiseCd_txt;
    }

    /**
     * @param merchandiseCd_txt セットする merchandiseCd_txt
     */
    public void setMerchandiseCd_txt(String merchandiseCd_txt) {
        this.merchandiseCd_txt = merchandiseCd_txt;
    }

    /**
     * @return merchandiseNm_txt
     */
    public String getMerchandiseNm_txt() {
        return merchandiseNm_txt;
    }

    /**
     * @param merchandiseNm_txt セットする merchandiseNm_txt
     */
    public void setMerchandiseNm_txt(String merchandiseNm_txt) {
        this.merchandiseNm_txt = merchandiseNm_txt;
    }

    /**
     * @return model
     */
    public GoodsSelectionListModel getModel() {
        return model;
    }

    /**
     * @param model セットする model
     */
    public void setModel(GoodsSelectionListModel model) {
        this.model = model;
    }

    /**
     * @return select_chk_list
     */
    public List<String> getSelect_chk_list() {
        return select_chk_list;
    }

    /**
     * @param select_chk_list セットする select_chk_list
     */
    public void setSelect_chk_list(List<String> select_chk_list) {
        this.select_chk_list = select_chk_list;
    }

    /**
     * @return buttonIndex
     */
    public String getButtonIndex() {
        return buttonIndex;
    }

    /**
     * @param buttonIndex セットする buttonIndex
     */
    public void setButtonIndex(String buttonIndex) {
        this.buttonIndex = buttonIndex;
    }

    /**
     * @return useStrDt
     */
    public String getUseStrDt() {
        return useStrDt;
    }

    /**
     * @param useStrDt セットする useStrDt
     */
    public void setUseStrDt(String useStrDt) {
        this.useStrDt = useStrDt;
    }

    /**
     * @return useEndDt
     */
    public String getUseEndDt() {
        return useEndDt;
    }

    /**
     * @param useEndDt セットする useEndDt
     */
    public void setUseEndDt(String useEndDt) {
        this.useEndDt = useEndDt;
    }

    /**
     * @return error_noSelect
     */
    public String getError_noSelect() {
        return error_noSelect;
    }

    /**
     * @param error_noSelect セットする error_noSelect
     */
    public void setError_noSelect(String error_noSelect) {
        this.error_noSelect = error_noSelect;
    }

    /**
     * @return error_gtFive
     */
    public String getError_gtFive() {
        return error_gtFive;
    }

    /**
     * @param error_gtFive セットする error_gtFive
     */
    public void setError_gtFive(String error_gtFive) {
        this.error_gtFive = error_gtFive;
    }

    /**
     * @return error_gtOne
     */
    public String getError_gtOne() {
        return error_gtOne;
    }

    /**
     * @param error_gtOne セットする error_gtOne
     */
    public void setError_gtOne(String error_gtOne) {
        this.error_gtOne = error_gtOne;
    }

    /**
     * @return searchFlg
     */
    public String getSearchFlg() {
        return searchFlg;
    }

    /**
     * @param searchFlg セットする searchFlg
     */
    public void setSearchFlg(String searchFlg) {
        this.searchFlg = searchFlg;
    }

}
