package com.naikara_talk.model;

import java.util.List;

import com.naikara_talk.dto.PointManagMstDto;

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
public class PointControlListModel implements Model {

    private static final long serialVersionUID = 1L;

    /** 処理区分：新規 */
    public static final String PROS_KBN_ADD = "0";

    /** 処理区分：修正 */
    public static final String PROS_KBN_UPD = "1";

    /** 処理区分：照会 */
    public static final String PROS_KBN_REF = "2";

    /** チェック：問題なし(正常) */
    public static final int CHECK_OK = 0;

    /** 処理区分 */
    private String processKbn;

    /** ポイントコード */
    private String pointCd;

    /** 通常月謝区分 */
    private String feeKbn;

    /** 検索結果一覧 */
    private List<PointManagMstDto> resultList;

    /** 検索判断フラグ */
    private String searchFlg;

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
     * @return resultList
     */
    public List<PointManagMstDto> getResultList() {
        return resultList;
    }

    /**
     * @param resultList セットする resultList
     */
    public void setResultList(List<PointManagMstDto> resultList) {
        this.resultList = resultList;
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