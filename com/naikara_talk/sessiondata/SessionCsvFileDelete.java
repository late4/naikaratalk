package com.naikara_talk.sessiondata;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>共通部品 SessionData クラス<br>
 * <b>クラス名称       :</b>CSVファイルの情報クラス。<br>
 * <b>クラス概要       :</b>過去に作成したCSVファイルの削除用のセッション情報。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/11 TECS 新規作成
 */
public class SessionCsvFileDelete implements SessionData {

    private static final String KEY = "naikara_talk.sessiondata.SessionCsvFileDelete";

    /** ログイン者のログインID */
    private String userId;

    /** コード種別名 */
    private List<String> cdCategory;

    /** 汎用フィールド名 */
    private List<String> managerCd;

    /**
     * このセッションデータのキーを返却する
     */
    public static String getKey() {
        return KEY;
    }

    /**
     * @return userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId セットする userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return cdCategory
     */
    public List<String> getCdCategory() {
        if (this.cdCategory == null) {
            this.cdCategory = new ArrayList<String>();
        }
        return cdCategory;
    }

    /**
     * @param cdCategory セットする cdCategory
     */
    public void setCdCategory(List<String> cdCategory) {
        this.cdCategory = cdCategory;
    }

    /**
     * @return managerCd
     */
    public List<String> getManagerCd() {
        if (this.managerCd == null) {
            this.managerCd = new ArrayList<String>();
        }
        return managerCd;
    }

    /**
     * @param managerCd セットする managerCd
     */
    public void setManagerCd(List<String> managerCd) {
        this.managerCd = managerCd;
    }

}
