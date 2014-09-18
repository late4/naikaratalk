package com.naikara_talk.sessiondata;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>共通部品 SessionData クラス<br>
 * <b>クラス名称       :</b>画面共通情報クラス<br>
 * <b>クラス概要       :</b>グローバルナビの各ボタン制御、画面制御を行う<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/02 TECS 新規作成
 */

public class ScreenComInfo implements SessionData {

    private static final String KEY = "naikara_talk.SessionData.ScreenComInfo";

    /** ログイン中のユーザID */
    private String userId;
    /** 保護者の同意書の入手フラグ */
    private String consentDocumentAcquisitionFlg;
    /** ポイント購入済フラグ */
    private String pointPurchaseFlg;

    /**
     * このセッションデータのキーを返却する
     */
    public static String getKey() {
        return KEY;
    }

    /**
     * ユーザID取得<br>
     * <br>
     * ユーザIDを戻り値で返却する<br>
     * <br>
     * @return userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザID設定<br>
     * <br>
     * ユーザIDを引数で設定する<br>
     * <br>
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 保護者の同意書の入手フラグ取得<br>
     * <br>
     * 保護者の同意書の入手フラグを戻り値で返却する<br>
     * <br>
     * @return consentDocumentAcquisitionFlg
     */
    public String getConsentDocumentAcquisitionFlg() {
        return consentDocumentAcquisitionFlg;
    }

    /**
     * 保護者の同意書の入手フラグ設定<br>
     * <br>
     * 保護者の同意書の入手フラグを引数で設定する<br>
     * <br>
     * @param consentDocumentAcquisitionFlg
     */
    public void setConsentDocumentAcquisitionFlg(String consentDocumentAcquisitionFlg) {
        this.consentDocumentAcquisitionFlg = consentDocumentAcquisitionFlg;
    }

    /**
     * ポイント購入済フラグ取得<br>
     * <br>
     * ポイント購入済フラグを戻り値で返却する<br>
     * <br>
     * @return pointPurchaseFlg
     */
    public String getPointPurchaseFlg() {
        return pointPurchaseFlg;
    }

    /**
     * ポイント購入済フラグ設定<br>
     * <br>
     * ポイント購入済フラグを引数で設定する<br>
     * <br>
     * @param pointPurchaseFlg
     */
    public void setPointPurchaseFlg(String pointPurchaseFlg) {
        this.pointPurchaseFlg = pointPurchaseFlg;
    }

}
