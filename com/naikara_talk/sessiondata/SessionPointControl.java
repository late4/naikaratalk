package com.naikara_talk.sessiondata;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>ポイント管理マスタメンテナンスのセッション情報クラス。<br>
 * <b>クラス概要       :</b>ポイント管理マスタメンテナンスの戻る用のセッション情報。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/01 TECS 新規作成
 */
public class SessionPointControl implements SessionData {

    /** ヘッダの戻るリンク用のSessionKey */
    private static final String HEADER_RETURN_KEY = "PointControlList";

    /** 戻る判定Onフラグ */
    private boolean returnOnFlg;

    /** 検索判断フラグ */
    private String hasSearchFlg;

    /** 処理区分 */
    private String processKbn;

    /** ポイントコード */
    private String pointCd;

    /** 通常月謝区分 */
    private String feeKbn;

    /** 検索Key：ポイントコード */
    private String searchPointCd;

    /** 検索Key：通常月謝区分 */
    private String searchFeeKbn;

    /** 検索Key：選択された明細のKey-ポイントコード */
    private String searchPointCdKey;

    /**
     * このセッションデータのキーを返却する
     */
    public static String getKey() {
        return HEADER_RETURN_KEY;
    }

    /**
     * @return processKbn
     */
    public boolean getReturnOnFlg() {
        return returnOnFlg;
    }

    /**
     * @param returnOnFlg セットする returnOnFlg
     */
    public void setReturnOnFlg(boolean returnOnFlg) {
        this.returnOnFlg = returnOnFlg;
    }

    /**
     * @return hasSearchFlg
     */
    public String getHasSearchFlg() {
        return hasSearchFlg;
    }

    /**
     * @param hasSearchFlg セットする hasSearchFlg
     */
    public void setHasSearchFlg(String hasSearchFlg) {
        this.hasSearchFlg = hasSearchFlg;
    }

    /**
     * @return processKbn
     */
    public String getProcessKbn() {
        return processKbn;
    }

    /**
     * @param processKbn セットする processKbn
     */
    public void setProcessKbn(String processKbn) {
        this.processKbn = processKbn;
    }

    /**
     * @return pointCd
     */
    public String getPointCd() {
        return pointCd;
    }

    /**
     * @param pointCd セットする pointCd
     */
    public void setPointCd(String pointCd) {
        this.pointCd = pointCd;
    }

    /**
     * @return feeKbn
     */
    public String getFeeKbn() {
        return feeKbn;
    }

    /**
     * @param feeKbn セットする feeKbn
     */
    public void setFeeKbn(String feeKbn) {
        this.feeKbn = feeKbn;
    }

    /**
     * @return searchPointCd
     */
    public String getSearchPointCd() {
        return searchPointCd;
    }

    /**
     * @param searchPointCd セットする searchPointCd
     */
    public void setSearchPointCd(String searchPointCd) {
        this.searchPointCd = searchPointCd;
    }

    /**
     * @return searchFeeKbn
     */
    public String getSearchFeeKbn() {
        return searchFeeKbn;
    }

    /**
     * @param searchFeeKbn セットする searchFeeKbn
     */
    public void setSearchFeeKbn(String searchFeeKbn) {
        this.searchFeeKbn = searchFeeKbn;
    }

    /**
     * @return searchPointCdKey
     */
    public String getSearchPointCdKey() {
        return searchPointCdKey;
    }

    /**
     * @param searchPointCdKey セットする searchPointCdKey
     */
    public void setSearchPointCdKey(String searchPointCdKey) {
        this.searchPointCdKey = searchPointCdKey;
    }

}
