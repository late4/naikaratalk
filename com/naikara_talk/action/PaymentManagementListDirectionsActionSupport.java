package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.PaymentManagementListDirectionsModel;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>会社側_管理帳票<br>
 * <b>クラス名称       :</b>講師毎の支払管理表Actionクラス<br>
 * <b>クラス概要       :</b>講師毎の支払管理表共通Action<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/08 TECS 新規作成
 */
public abstract class PaymentManagementListDirectionsActionSupport extends NaikaraActionSupport {

    private static final long serialVersionUID = 1L;

    // 画面表示のタイトル
    protected String title = "講師毎の支払管理表指示";

    // Help画面名
    protected String helpPageId = "HelpPaymentManagementListDirections.html";

    /**
     * サービスの呼び出しの実装。
     */
    abstract protected String requestService() throws NaiException;

    /** メッセージ */
    protected String message;

    /** 支払年月 */
    protected String payment_txt;

    /** 支払管理表ファイル名 */
    protected String fileName;

    /** 作成判断フラグ */
    protected String cerateFlg;

    /** 処理結果 */
    protected PaymentManagementListDirectionsModel model = new PaymentManagementListDirectionsModel();

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
     * @return payment_txt
     */
    public String getPayment_txt() {
        return payment_txt;
    }

    /**
     * @param payment_txt
     *            セットする payment_txt
     */
    public void setPayment_txt(String payment_txt) {
        this.payment_txt = payment_txt;
    }

    /**
     * @return fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName
     *            セットする fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return cerateFlg
     */
    public String getCerateFlg() {
        return cerateFlg;
    }

    /**
     * @param cerateFlg
     *            セットする cerateFlg
     */
    public void setCerateFlg(String cerateFlg) {
        this.cerateFlg = cerateFlg;
    }

    /**
     * @return model
     */
    public PaymentManagementListDirectionsModel getModel() {
        return model;
    }

    /**
     * @param model
     *            セットする model
     */
    public void setModel(PaymentManagementListDirectionsModel model) {
        this.model = model;
    }

}
