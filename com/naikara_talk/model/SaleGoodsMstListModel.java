package com.naikara_talk.model;

import java.util.List;

import com.naikara_talk.dto.GoodsMstDto;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>販売商品マスタメンテナンス(一覧)。<br>
 * <b>クラス名称　　　:</b>販売商品マスタメンテナンスModelクラス。<br>
 * <b>クラス概要　　　:</b>販売商品マスタメンテナンスModel。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>
 * 2013/06/18 TECS 新規作成。
 */
public class SaleGoodsMstListModel implements Model {

    private static final long serialVersionUID = 1L;

    /* 処理区分：新規 */
    public static final String PROS_KBN_ADD = "0";

    /* 処理区分：修正 */
    public static final String PROS_KBN_UPD = "1";

    /* 処理区分：照会 */
    public static final String PROS_KBN_REF = "2";

    /* チェック：問題なし(正常) */
    public static final int CHECK_OK = 0;

    /* (「検索ボタン」「登録/選択ボタン」共通)チェック：処理区分と押下ボタンの不整合 */
    public static final int ERR_PROS_BTN_MISMATCH = 10;

    /* (「検索ボタン」「登録/選択ボタン」共通)チェック：「スタッフ」の場合は[照会]のみ */
    public static final int ERR_NO_UPD_ROLE = 12;

    /* (検索ボタン押下時)チェック：データ件数ZERO件 */
    public static final int ERR_ZERO_DATA = 1;

    /* (検索ボタン押下時)チェック：データ件数上限以上の件数 */
    public static final int ERR_MAXOVER_DATA = 2;

    /* (登録/選択ボタン押下時)チェック：一覧部の項目が選択なし */
    public static final int ERR_NO_SELECT = 3;

    /* (登録/選択ボタン押下時)チェック：一覧画面へ戻る */
    public static final int ERR_LIST_BACK = 4;


    /** 処理区分 */
    private String processKbn;

    /** 商品コード */
    private String goodsCd;

    /** 商品名 */
    private String goodsNm;

    /** 利用状態 */
    private String useKbn;

    /** 検索結果一覧 */
    private List<GoodsMstDto> resultList;

    /** 画面状態フラグ */
    private String initFlg;

    /**
     * 画面状態フラグを取得する
     * @return initFlg
     */
    public String getInitFlg() {
        return initFlg;
    }

    /**
     * 画面状態フラグをセットする
     * @param initFlg セットする initFlg
     */
    public void setInitFlg(String initFlg) {
        this.initFlg = initFlg;
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
     * @return goodsCd
     */
    public String getGoodsCd() {
        return goodsCd;
    }

    /**
     * @param goodsCd セットする goodsCd
     */
    public void setGoodsCd(String goodsCd) {
        this.goodsCd = goodsCd;
    }

    /**
     * @return goodsNm
     */
    public String getGoodsNm() {
        return goodsNm;
    }

    /**
     * @param goodsNm セットする goodsNm
     */
    public void setGoodsNm(String goodsNm) {
        this.goodsNm = goodsNm;
    }

    /**
     * @return usekubun
     */
    public String getUseKbn() {
        return useKbn;
    }

    /**
     * @param useKbn セットする useKbn
     */
    public void setUseKbn(String useKbn) {
        this.useKbn = useKbn;
    }

    /**
     * @return resultList
     */
    public List<GoodsMstDto> getResultList() {
        return resultList;
    }

    /**
     * @param resultList セットする resultList
     */
    public void setResultList(List<GoodsMstDto> resultList) {
        this.resultList = resultList;
    }

}