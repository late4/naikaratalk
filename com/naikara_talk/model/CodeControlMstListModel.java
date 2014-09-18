package com.naikara_talk.model;

import java.util.List;

import com.naikara_talk.dto.CodeClassMstDto;
import com.naikara_talk.dto.CdManagMstCdClassMstDto;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>コード管理マスタメンテナンス(一覧)。<br>
 * <b>クラス名称　　　:</b>コード管理マスタメンテナンスModelクラス。<br>
 * <b>クラス概要　　　:</b>コード管理マスタメンテナンスModel。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>
 * 2013/07/16 TECS 新規作成。
 */
public class CodeControlMstListModel implements Model {

    private static final long serialVersionUID = 1L;

    /* 処理区分：新規 */
    public static final String PROS_KBN_ADD = "0";

    /* 処理区分：修正 */
    public static final String PROS_KBN_UPD = "1";

    /* 処理区分：削除 */
    public static final String PROS_KBN_DEL = "2";

    /* 処理区分：照会 */
    public static final String PROS_KBN_REF = "3";

    /* チェック：問題なし(正常) */
    public static final int CHECK_OK = 0;

    /* (「検索ボタン」「登録/選択ボタン」共通)チェック：処理区分と押下ボタンの不整合 */
    public static final int ERR_PROS_BTN_MISMATCH = 10;

    /* (「検索ボタン」「登録/選択ボタン」共通)チェック：「管理者」の場合は[削除]権限なし */
    public static final int ERR_NO_DEL_ROLE = 11;

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

    /* (登録/選択ボタン押下時)チェック：新規且つ汎用コード登録不可フラグONは登録出来ない */
    public static final int ERR_MANAGER_NO_INS = 5;

    /* (登録/選択ボタン押下時)チェック：削除且つシステム削除不可フラグONは削除出来ない */
    public static final int ERR_MANAGER_NO_DEL = 6;

    /** 処理区分 */
    private String processKbn;

    /** コード種別名(List) */
    private List<CodeClassMstDto> cdCategoryList;

    /** コード種別名(選択されている行) */
    private String cdCategorySelected;

    /** 汎用コード */
    private String managerCd;

    /** 汎用フィールド */
    private String managerNm;

    /** 検索結果一覧 */
    private List<CdManagMstCdClassMstDto> resultList;

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
     * @return cdCategoryList
     */
    public List<CodeClassMstDto> getCdCategoryList() {
        return cdCategoryList;
    }

    /**
     * @param cdCategoryList セットする cdCategoryList
     */
    public void setCdCategoryList(List<CodeClassMstDto> cdCategoryList) {
        this.cdCategoryList = cdCategoryList;
    }

    /**
     * @return cdCategorySelected
     */
    public String getCdCategorySelected() {
        return cdCategorySelected;
    }

    /**
     * @param cdCategorySelected セットする cdCategorySelected
     */
    public void setCdCategorySelected(String cdCategorySelected) {
        this.cdCategorySelected = cdCategorySelected;
    }

    /**
     * @return managerCd
     */
    public String getManagerCd() {
        return managerCd;
    }

    /**
     * @param managerCd セットする managerCd
     */
    public void setManagerCd(String managerCd) {
        this.managerCd = managerCd;
    }

    /**
     * @return managerNm
     */
    public String getManagerNm() {
        return managerNm;
    }

    /**
     * @param managerNm セットする managerNm
     */
    public void setManagerNm(String managerNm) {
        this.managerNm = managerNm;
    }

    /**
     * @return resultList
     */
    public List<CdManagMstCdClassMstDto> getResultList() {
        return resultList;
    }

    /**
     * @param resultList セットする resultList
     */
    public void setResultList(List<CdManagMstCdClassMstDto> resultList) {
        this.resultList = resultList;
    }


}