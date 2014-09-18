package com.naikara_talk.model;

import java.util.List;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様利用画面<br>
 * <b>クラス名称       :</b>ポイント購入ページ<br>
 * <b>クラス概要       :</b>ポイント購入ページModel<br>
 * <br>
 * <b>著作権           :</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/19 TECS 新規作成
 */
public class PurchasePointsModel implements Model {

    private static final long serialVersionUID = 1L;

    /** ユーザＩＤ */
    private String userId;

    /** 選択ポイントコード */
    private String pointCd;

    /** 月謝制（定額制）ポイント購入一覧一覧 */
    private List<PointsListModel> monthlyList;

    /** 通常ポイント購入一覧一覧 */
    private List<PointsListModel> normalList;


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
     * @return monthlyList
     */
    public List<PointsListModel> getMonthlyList() {
        return monthlyList;
    }

    /**
     * @param monthlyList セットする monthlyList
     */
    public void setMonthlyList(List<PointsListModel> monthlyList) {
        this.monthlyList = monthlyList;
    }

    /**
     * @return normalList
     */
    public List<PointsListModel> getNormalList() {
        return normalList;
    }

    /**
     * @param normalList セットする normalList
     */
    public void setNormalList(List<PointsListModel> normalList) {
        this.normalList = normalList;
    }

}