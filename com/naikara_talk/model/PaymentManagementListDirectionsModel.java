package com.naikara_talk.model;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>ポイント管理マスタメンテナンス(一覧)<br>
 * <b>クラス概要       :</b>ポイント管理マスタメンテナンス(一覧)Model<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/01 TECS 新規作成
 */
public class PaymentManagementListDirectionsModel implements Model {

    private static final long serialVersionUID = 1L;

    /** チェック：問題なし(正常) */
    public static final int CHECK_OK = 0;

    /** 支払予定年月 */
    private String payment_txt;

    /** 支払管理表ファイル名 */
    private String fileName;

    /** 作成判断フラグ */
    private String cerateFlg;

    /**
     * @return payment_txt
     */
    public String getPayment_txt() {
        return payment_txt;
    }

    /**
     * @param payment_txt セットする payment_txt
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
     * @param fileName セットする fileName
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
     * @param cerateFlg セットする cerateFlg
     */
    public void setCerateFlg(String cerateFlg) {
        this.cerateFlg = cerateFlg;
    }
}