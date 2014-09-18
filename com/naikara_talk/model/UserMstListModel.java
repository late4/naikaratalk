package com.naikara_talk.model;

import java.util.List;

import com.naikara_talk.dto.UserMstDto;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守。<br>
 * <b>クラス名称　　　:</b>利用者マスタメンテナンスModelクラス。<br>
 * <b>クラス概要　　　:</b>利用者マスタメンテナンスModel。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/04 TECS 新規作成。
 */
public class UserMstListModel implements Model {

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

    /** 利用者ID */
    private String userId;

    /** 利用者名(フリガナ) */
    private String userKnm;

    /** 利用状態 */
    private String useKbn;

    /** 検索結果一覧 */
    private List<UserMstDto> resultList;

    /** 画面状態フラグ */
    private String searchFlg;

    /**
     * 画面状態フラグ取得<br>
     * <br>
     * 画面状態フラグ取得を行う<br>
     * <br>
     * @param なし<br>
     * @return searchFlg 画面状態フラグ<br>
     * @exception なし
     */
    public String getSearchFlg() {
        return searchFlg;
    }

    /**
     * 画面状態フラグ設定<br>
     * <br>
     * 画面状態フラグ設定を行う<br>
     * <br>
     * @param searchFlg 画面状態フラグ<br>
     * @return なし<br>
     * @exception なし
     */
    public void setSearchFlg(String searchFlg) {
        this.searchFlg = searchFlg;
    }

    /**
     * 処理区分取得<br>
     * <br>
     * 処理区分取得を行う<br>
     * <br>
     * @param なし<br>
     * @return processKbn 処理区分<br>
     * @exception なし
     */
    public String getProcessKbn() {
        return processKbn;
    }

    /**
     * 処理区分設定<br>
     * <br>
     * 処理区分設定を行う<br>
     * <br>
     * @param processKbn 処理区分<br>
     * @return なし<br>
     * @exception なし
     */
    public void setProcessKbn(String processKbn) {
        this.processKbn = processKbn;
    }

    /**
     * 利用者ID取得<br>
     * <br>
     * 利用者ID取得を行う<br>
     * <br>
     * @param なし<br>
     * @return userId 利用者ID<br>
     * @exception なし
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 利用者ID設定<br>
     * <br>
     * 利用者ID設定を行う<br>
     * <br>
     * @param userId 利用者ID<br>
     * @return なし<br>
     * @exception なし
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 利用者名(フリガナ)取得<br>
     * <br>
     * 利用者名(フリガナ)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return userKnm 利用者名(フリガナ)<br>
     * @exception なし
     */
    public String getUserKnm() {
        return userKnm;
    }

    /**
     * 利用者名(フリガナ)設定<br>
     * <br>
     * 利用者名(フリガナ)設定を行う<br>
     * <br>
     * @param userKnm 利用者名(フリガナ)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setUserKnm(String userKnm) {
        this.userKnm = userKnm;
    }

    /**
     * 利用状態取得<br>
     * <br>
     * 利用状態取得を行う<br>
     * <br>
     * @param なし<br>
     * @return useKbn 利用状態<br>
     * @exception なし
     */
    public String getUseKbn() {
        return useKbn;
    }

    /**
     * 利用状態設定<br>
     * <br>
     * 利用状態設定を行う<br>
     * <br>
     * @param useKbn 利用状態<br>
     * @return なし<br>
     * @exception なし
     */
    public void setUseKbn(String useKbn) {
        this.useKbn = useKbn;
    }

    /**
     * 検索結果一覧取得<br>
     * <br>
     * 検索結果一覧取得を行う<br>
     * <br>
     * @param なし<br>
     * @return resultList 検索結果一覧<br>
     * @exception なし
     */
    public List<UserMstDto> getResultList() {
        return resultList;
    }

    /**
     * 検索結果一覧設定<br>
     * <br>
     * 検索結果一覧設定を行う<br>
     * <br>
     * @param resultList 検索結果一覧<br>
     * @return なし<br>
     * @exception なし
     */
    public void setResultList(List<UserMstDto> resultList) {
        this.resultList = resultList;
    }
}