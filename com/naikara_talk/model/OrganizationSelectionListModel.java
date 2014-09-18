package com.naikara_talk.model;

import java.util.List;

import com.naikara_talk.dto.OrganizationMstDto;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>法人_初期処理<br>
 * <b>クラス名称       :</b>組織マイページ(組織 選択)Modelクラス。<br>
 * <b>クラス概要       :</b>組織マイページ(組織 選択)。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/01 TECS 新規作成
 */
public class OrganizationSelectionListModel implements Model {

    private static final long serialVersionUID = 1L;

    /** チェック：問題なし(正常) */
    public static final int CHECK_OK = 0;

    /** 組織ID */
    protected String organizationId;

    /** 組織名 */
    protected String organizationNm;

    /** 責任者所属 */
    protected String managPosition;

    /** 責任者名 */
    protected String managJnm;

    /** 責任者名(フリガナ) */
    protected String managKnm;

    /** 電話番号 */
    protected String tel;

    /** メールアドレス */
    protected String managAddress1;

    /** 連番 */
    protected String consSeq;

    /** 検索結果一覧 */
    private List<OrganizationMstDto> resultList;

    /** 画面状態フラグ */
    private String searchFlg;

    /**
     * @return organizationId
     */
    public String getOrganizationId() {
        return organizationId;
    }

    /**
     * @param organizationId
     *            セットする organizationId
     */
    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    /**
     * @return organizationNm
     */
    public String getOrganizationNm() {
        return organizationNm;
    }

    /**
     * @param organizationNm
     *            セットする messageorganizationNm
     */
    public void setOrganizationNm(String organizationNm) {
        this.organizationNm = organizationNm;
    }

    /**
     * @return managPosition
     */
    public String getManagPosition() {
        return managPosition;
    }

    /**
     * @param managPosition
     *            セットする managPosition
     */
    public void setManagPosition(String managPosition) {
        this.managPosition = managPosition;
    }

    /**
     * @return managJnm
     */
    public String getManagJnm() {
        return managJnm;
    }

    /**
     * @param managJnm
     *            セットする managJnm
     */
    public void setManagJnm(String managJnm) {
        this.managJnm = managJnm;
    }

    /**
     * @return managKnm
     */
    public String getManagKnm() {
        return managKnm;
    }

    /**
     * @param managKnm
     *            セットする managKnm
     */
    public void setManagKnm(String managKnm) {
        this.managKnm = managKnm;
    }

    /**
     * @return tel
     */
    public String getTel() {
        return tel;
    }

    /**
     * @param tel
     *            セットする tel
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * @return managAddress1
     */
    public String getManagAddress1() {
        return managAddress1;
    }

    /**
     * @param managAddress1
     *            セットする managAddress1
     */
    public void setManagAddress1(String managAddress1) {
        this.managAddress1 = managAddress1;
    }

    /**
     * @return consSeq
     */
    public String getConsSeq() {
        return consSeq;
    }

    /**
     * @param consSeq
     *            セットする consSeq
     */
    public void setConsSeq(String consSeq) {
        this.consSeq = consSeq;
    }

    /**
     * @return resultList
     */
    public List<OrganizationMstDto> getResultList() {
        return resultList;
    }

    /**
     * @param resultList
     *            セットする resultList
     */
    public void setResultList(List<OrganizationMstDto> resultList) {
        this.resultList = resultList;
    }

    /**
     * @return searchFlg
     */
    public String getSearchFlg() {
        return searchFlg;
    }

    /**
     * @param searchFlg
     *            セットする searchFlg
     */
    public void setSearchFlg(String searchFlg) {
        this.searchFlg = searchFlg;
    }

}