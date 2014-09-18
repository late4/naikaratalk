package com.naikara_talk.sessiondata;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様(個人)<br>
 * <b>クラス名称　　　:</b>講師一覧のセッション情報クラス。<br>
 * <b>クラス概要　　　:</b>講師一覧のボタン判定用のセッション情報。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b> 2014/02/18 TECS 新規作成。
 */
public class SessionTeacherListBtnControl implements SessionData {

    /** ヘッダの戻るリンク用のSessionKey */
    private static final String HEADER_RETURN_KEY = "TeacherListBtnControl";

    /** 検索判断フラグ */
    private String hasSearchTcontactFlg;

    /**
     * このセッションデータのキーを返却する
     */
    public static String getKey() {
        return HEADER_RETURN_KEY;
    }

    /**
     * @return hasSearchTcontactFlg
     */
    public String getHasSearchTcontactFlg() {
        return hasSearchTcontactFlg;
    }

    /**
     * @param hasSearchTcontactFlg セットする hasSearchTcontactFlg
     */
    public void setHasSearchTcontactFlg(String hasSearchTcontactFlg) {
        this.hasSearchTcontactFlg = hasSearchTcontactFlg;
    }


}
