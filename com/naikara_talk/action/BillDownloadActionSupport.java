package com.naikara_talk.action;

import java.util.LinkedHashMap;
import java.util.Map;

import com.naikara_talk.exception.NaiException;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>会社側_管理帳票<br>
 * <b>クラス名称       :</b>請求書のダウンロードActionクラス<br>
 * <b>クラス概要       :</b>請求書のダウンロード共通Action<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author MIRA
 * <b>変更履歴         :</b>2013/09/08 MIRA 新規作成
 */
public abstract class BillDownloadActionSupport extends NaikaraActionSupport {

    private static final long serialVersionUID = 1L;

    // 画面表示のタイトル
    protected String title = "請求書発行";

    // Help画面名
    protected String helpPageId = "HelpBillDownload.html";

    /**
     * サービスの呼び出しの実装。
     */
    abstract protected String requestService() throws NaiException;

    /** メッセージ */
    protected String message;

    /** ボタン押下フラグ */
    protected String buttonFlg;

    /** 請求年月 */
    protected Map<String, String>  yyyymmList = new LinkedHashMap<String, String>();

    /** 請求年月 */
    protected String  yyyymm;

    /**
     * @return yyyymm
     */
    public String getYyyymm() {
        return yyyymm;
    }

    /**
     * @param yyyymm
     *            セットする yyyymm
     */
    public void setYyyymm(String yyyymm) {
        this.yyyymm = yyyymm;
    }
    /**
     * @return yyyymmList
     */
    public Map<String, String> getYyyymmList() {
        return yyyymmList;
    }

    /**
     * @param yyyymmList
     *            セットする yyyymmList
     */
    public void setYyyymmList(Map<String, String> yyyymmList) {
        this.yyyymmList = yyyymmList;
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
     * @return buttonFlg
     */
    public String getButtonFlg() {
        return buttonFlg;
    }

    /**
     * @param buttonFlg
     *            セットする buttonFlg
     */
    public void setButtonFlg(String buttonFlg) {
        this.buttonFlg = buttonFlg;
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

}
