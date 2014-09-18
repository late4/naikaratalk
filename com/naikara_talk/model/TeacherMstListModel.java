package com.naikara_talk.model;

import java.util.List;

import com.naikara_talk.dto.UserMstTeacherMstDto;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>講師マスタメンテナンス(一覧)<br>
 * <b>クラス概要       :</b>講師マスタメンテナンス(一覧)Model<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/18 TECS 新規作成
 */
public class TeacherMstListModel implements Model {

    private static final long serialVersionUID = 1L;

    /** 処理区分：修正 */
    public static final String PROS_KBN_UPD = "1";

    /** 処理区分：照会 */
    public static final String PROS_KBN_REF = "2";

    /** チェック：問題なし(正常) */
    public static final int CHECK_OK = 0;

    /** 処理区分 */
    private String processKbn;

    /** 講師ID */
    private String userId;

    /** 講師名(フリガナ) */
    private String userKnm;

    /** 検索結果一覧 */
    private List<UserMstTeacherMstDto> resultList;

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
     * @return userKnm
     */
    public String getUserKnm() {
        return userKnm;
    }

    /**
     * @param userKnm セットする userKnm
     */
    public void setUserKnm(String userKnm) {
        this.userKnm = userKnm;
    }

    /**
     * @return resultList
     */
    public List<UserMstTeacherMstDto> getResultList() {
        return resultList;
    }

    /**
     * @param resultList セットする resultList
     */
    public void setResultList(List<UserMstTeacherMstDto> resultList) {
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