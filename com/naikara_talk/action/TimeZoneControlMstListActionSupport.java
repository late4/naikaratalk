package com.naikara_talk.action;

import java.util.LinkedHashMap;
import java.util.Map;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.TimeZoneControlMstListModel;
import com.naikara_talk.service.TimeZoneControlMstListLoadService;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守<br>
 * <b>クラス名称　　　:</b>時差管理マスタメンテナンス【一覧】共通Actionクラス。<br>
 * <b>クラス概要　　　:</b>時差管理マスタメンテナンス【一覧】共通Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/09 TECS 新規作成。
 */
public abstract class TimeZoneControlMstListActionSupport extends NaikaraActionSupport {

    private static final long serialVersionUID = 1L;

    // 画面表示のタイトル
    protected String title = "時差管理マスタメンテナンス";

    // Help画面名
    protected String helpPageId = "HelpTimeZoneControlMstList.html";

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

        // チェックエラーの場合、国コード、時差地域Noの再取得。
        try {
            initSelect();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * サービスの呼び出しの実装。
     */
    abstract protected String requestService() throws NaiException;

    /**
     * 国コード、時差地域Noを取得する。<br>
     * <br>
     * @param なし
     * @return void
     * @throws Exception
     */
    public void initSelect() throws Exception {

        TimeZoneControlMstListLoadService service = new TimeZoneControlMstListLoadService();
        // 国コードを取得する
        this.countryCd_sell = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_COUNTRY);
        // 時差地域Noを取得する
        this.areaNoCd_sell = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_AREA_NO);
    }

    /**
     * 画面のパラメータをモデルにセット。<br>
     * <br>
     * @param なし
     * @return なし
     */
    public void setupModel() {
        this.model.setProcessKbn_rdl(this.processKbn_rdl);
        this.model.setCountryCd_sel(this.countryCd_sel);
        this.model.setAreaNoCd_sel(this.areaNoCd_sel);
    }

    /** メッセージ */
    protected String message;

    /** 処理区分(jsp) */
    protected String processKbn_rdl;

    /** 国コード (jsp) */
    protected String countryCd_sel;
    protected Map<String, String> countryCd_sell = new LinkedHashMap<String, String>();

    /** 時差地域No(jsp) */
    protected String areaNoCd_sel;
    protected Map<String, String> areaNoCd_sell = new LinkedHashMap<String, String>();

    /** 検索結果 */
    protected TimeZoneControlMstListModel model = new TimeZoneControlMstListModel();

    /** 一覧から選択された明細データ(jsp) */
    protected String select_rdl;

    /** チェックエラー場合、検索判断フラグ */
    protected String hasSearchFlg;

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param  title
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
     * @param  helpPageId
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
     * @param  message
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
     * @param  processKbn_rdl
     *            セットする processKbn_rdl
     */
    public void setProcessKbn_rdl(String processKbn_rdl) {
        this.processKbn_rdl = processKbn_rdl;
    }

    /**
     * @return countryCd_sel
     */
    public String getCountryCd_sel() {
        return countryCd_sel;
    }

    /**
     * @param  countryCd_sel
     *            セットする countryCd_sel
     */
    public void setCountryCd_sel(String countryCd_sel) {
        this.countryCd_sel = countryCd_sel;
    }

    /**
     * @return countryCd_sell
     */
    public Map<String, String> getCountryCd_sell() {
        return countryCd_sell;
    }

    /**
     * @param  countryCd_sell
     *            セットする countryCd_sell
     */
    public void setCountryCd_sell(Map<String, String> countryCd_sell) {
        this.countryCd_sell = countryCd_sell;
    }

    /**
     * @return areaNoCd_sel
     */
    public String getAreaNoCd_sel() {
        return areaNoCd_sel;
    }

    /**
     * @param  areaNoCd_sel
     *            セットする areaNoCd_sel
     */
    public void setAreaNoCd_sel(String areaNoCd_sel) {
        this.areaNoCd_sel = areaNoCd_sel;
    }

    /**
     * @return areaNoCd_sell
     */
    public Map<String, String> getAreaNoCd_sell() {
        return areaNoCd_sell;
    }

    /**
     * @param  areaNoCd_sell
     *            セットする areaNoCd_sell
     */
    public void setAreaNoCd_sell(Map<String, String> areaNoCd_sell) {
        this.areaNoCd_sell = areaNoCd_sell;
    }

    /**
     * @return model
     */
    public TimeZoneControlMstListModel getModel() {
        return model;
    }

    /**
     * @param  model
     *            セットする model
     */
    public void setModel(TimeZoneControlMstListModel model) {
        this.model = model;
    }

    /**
     * @return select_rdl
     */
    public String getSelect_rdl() {
        return select_rdl;
    }

    /**
     * @param  select_rdl
     *            セットする select_rdl
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
     * @param  hasSearchFlg
     *            セットする hasSearchFlg
     */
    public void setHasSearchFlg(String hasSearchFlg) {
        this.hasSearchFlg = hasSearchFlg;
    }

    /**
     * @return serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
