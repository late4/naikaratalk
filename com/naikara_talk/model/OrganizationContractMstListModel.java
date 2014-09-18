package com.naikara_talk.model;

import java.util.List;

import com.naikara_talk.dto.OrganizationMstDto;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守<br>
 * <b>クラス名称　　　:</b>組織契約情報登録一覧Modelクラス。<br>
 * <b>クラス概要　　　:</b>組織契約情報登録一覧Model。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b> 2013/08/01 TECS 新規作成。
 */
public class OrganizationContractMstListModel implements Model {

    private static final long serialVersionUID = 1L;

    /** 処理区分：新規 */
    public static final String PROS_KBN_ADD = "0";
    /** 処理区分：流用作成 */
    public static final String PROS_KBN_MAKE = "1";
    /** 処理区分：修正 */
    public static final String PROS_KBN_UPD = "2";
    /** 処理区分：削除 */
    public static final String PROS_KBN_DEL = "3";
    /** 処理区分 */
    private String processKbn_rdl;
    /** 組織ID */
    private String organizationId;
    /** 組織名 */
    private String organizationJnm;
    /** 契約終了日 */
    private String contractEndDt;
    /** 検索結果一覧 */
    private List<OrganizationMstDto> organizationMstDto;

    /**
     * @return processKbn_rdl
     */
    public String getProcessKbn_rdl() {
        return processKbn_rdl;
    }

    /**
     * @param processKbn_rdl セットする processKbn_rdl
     */
    public void setProcessKbn_rdl(String processKbn_rdl) {
        this.processKbn_rdl = processKbn_rdl;
    }

    /**
     * @return organizationId
     */
    public String getOrganizationId() {
        return organizationId;
    }

    /**
     * @param organizationId セットする organizationId
     */
    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    /**
     * @return organizationJnm
     */
    public String getOrganizationJnm() {
        return organizationJnm;
    }

    /**
     * @param organizationJnm セットする organizationJnm
     */
    public void setOrganizationJnm(String organizationJnm) {
        this.organizationJnm = organizationJnm;
    }

    /**
     * @return organizationMstDto
     */
    public List<OrganizationMstDto> getOrganizationMstDto() {
        return organizationMstDto;
    }

    /**
     * @param organizationMstDto セットする organizationMstDto
     */
    public void setOrganizationMstDto(List<OrganizationMstDto> organizationMstDto) {
        this.organizationMstDto = organizationMstDto;
    }

    /**
     * @return prosKbnAdd
     */
    public static String getProsKbnAdd() {
        return PROS_KBN_ADD;
    }

    /**
     * @return prosKbnUpd
     */
    public static String getProsKbnUpd() {
        return PROS_KBN_UPD;
    }

    /**
     * @return prosKbnDel
     */
    public static String getProsKbnDel() {
        return PROS_KBN_DEL;
    }

    /**
     * @return prosKbnMake
     */
    public static String getProsKbnMake() {
        return PROS_KBN_MAKE;
    }

    /**
     * @return contractEndDt
     */
    public String getContractEndDt() {
        return contractEndDt;
    }

    /**
     * @param contractEndDt セットする contractEndDt
     */
    public void setContractEndDt(String contractEndDt) {
        this.contractEndDt = contractEndDt;
    }

}