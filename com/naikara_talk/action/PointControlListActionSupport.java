package com.naikara_talk.action;

import java.util.LinkedHashMap;
import java.util.Map;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.PointControlListModel;
import com.naikara_talk.service.PointControlListLoadService;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>ポイント管理マスタメンテナンス(一覧)<br>
 * <b>クラス概要       :</b>ポイント管理マスタメンテナンス(一覧)共通Action<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/01 TECS 新規作成
 */
public abstract class PointControlListActionSupport extends NaikaraActionSupport {

    private static final long serialVersionUID = 1L;

    // 画面表示のタイトル
    protected String title = "ポイント管理マスタメンテナンス";

    // Help画面名
    protected String helpPageId = "HelpPointControlList.html";

    /**
     * 通常月謝区分の再取得<br>
     * <br>
     * チェックエラーの場合、通常月謝区分の再取得。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception なし
     */
    @Override
    public void validate() {

        // チェックエラーの場合、通常月謝区分の再取得。
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
     * 通常月謝区分を取得する。<br>
     * <br>
     * 通常月謝区分を取得する。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    public void initRadio() throws Exception {

        PointControlListLoadService service = new PointControlListLoadService();
        // 通常月謝区分を取得する
        this.feeKbn_rdll = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_FEE_KBN);
    }

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
        this.model.setProcessKbn(this.processKbn_rdl);
        this.model.setPointCd(this.pointCode_txt);
        this.model.setFeeKbn(this.feeKbn_rdl);
    }

    /** メッセージ */
    protected String message;

    /** 処理区分(jsp) */
    protected String processKbn_rdl;

    /** ポイントコード(jsp) */
    protected String pointCode_txt;

    /** 通常月謝区分(jsp) */
    protected String feeKbn_rdl;
    protected Map<String, String> feeKbn_rdll = new LinkedHashMap<String, String>();

    /** 検索結果 */
    protected PointControlListModel model = new PointControlListModel();

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
     * @return model
     */
    public String getPointCode_txt() {
        return pointCode_txt;
    }

    /**
     * @param processKbn_rdl
     *            セットする processKbn
     */
    public void setPointCode_txt(String pointCode_txt) {
        this.pointCode_txt = pointCode_txt;
    }

    /**
     * @return model
     */
    public String getFeeKbn_rdl() {
        return feeKbn_rdl;
    }

    /**
     * @param processKbn_rdl
     *            セットする processKbn
     */
    public void setFeeKbn_rdl(String feeKbn_rdl) {
        this.feeKbn_rdl = feeKbn_rdl;
    }

    /**
     * @return model
     */
    public Map<String, String> getFeeKbn_rdll() {
        return feeKbn_rdll;
    }

    /**
     * @param processKbn_rdl
     *            セットする processKbn
     */
    public void setFeeKbn_rdll(Map<String, String> feeKbn_rdll) {
        this.feeKbn_rdll = feeKbn_rdll;
    }

    /**
     * @return model
     */
    public PointControlListModel getModel() {
        return model;
    }

    /**
     * @param model
     *            セットする model
     */
    public void setModel(PointControlListModel model) {
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
}
