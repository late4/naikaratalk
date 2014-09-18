package com.naikara_talk.model;

import java.util.List;

import com.naikara_talk.dto.CodeClassMstDto;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>コード管理マスタメンテナンス(単票)。<br>
 * <b>クラス名称　　　:</b>コード管理マスタメンテナンスModelクラス。<br>
 * <b>クラス概要　　　:</b>コード管理マスタメンテナンスModel。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>
 * 2013/07/16 TECS 新規作成。
 */
public class CodeControlMstModel implements Model {

    private static final long serialVersionUID = 1L;

    /** 正常 */
    public static final int PROCESS_NORMAL = 0;

    /** 処理区分(前画面情報) */
    private String processKbn_rdl;

    /** 画面表示処理区分 */
    private String processKbn_txt;

    /** コード種別名(List) */
    private List<CodeClassMstDto> cdCategoryList;

    /** コード種別名(選択されている行) */
    private String cdCategorySelected;

    /** コード種別名(初期値) */
    private String defaultCdCategory;

    /** 汎用コード */
    private String managerCd;

    /** 汎用フィールド */
    private String managerNm;

    /** 汎用フィールド(可変：コード種別別のバイト数) */
    private int managerNmMaxLength;

    /** 並び順 */
    private String orderBy;

    /** 備考 */
    private String remark;

    /** 受講者マスタ英語項目名 */
    protected String studentMstEnm;

    /** 表示用点数 */
    protected String mark;

    /** システム削除不可フラグ */
    private String systemDelNgFlg;

    /** レコードバージョン番号 */
    protected int recordVerNo;

    /**
     * @return processKbn_rdl
     */
    public String getProcessKbn_Rdl() {
        return processKbn_rdl;
    }

    /**
     * @param processKbn_rdl
     *            セットする processKbn_rdl
     */
    public void setProcessKbn_Rdl(String processKbn_rdl) {
        this.processKbn_rdl = processKbn_rdl;
    }

    /**
     * @return processKbn_txt
     */
    public String getProcessKbn_Txt() {
        return processKbn_txt;
    }

    /**
     * @param processKbn_txt
     *            セットする processKbn_txt
     */
    public void setProcessKbn_Txt(String processKbn_txt) {
        this.processKbn_txt = processKbn_txt;
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
     * @return defaultCdCategory
     */
    public String getDefaultCdCategory() {
        return defaultCdCategory;
    }

    /**
     * @param defaultCdCategory セットする defaultCdCategory
     */
    public void setDefaultCdCategory(String defaultCdCategory) {
        this.defaultCdCategory = defaultCdCategory;
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
     * @return managerNmMaxLength
     */
    public int getManagerNmMaxLength() {
        return managerNmMaxLength;
    }

    /**
     * @param managerNmMaxLength セットする managerNmMaxLength
     */
    public void setManagerNmMaxLength(int managerNmMaxLength) {
        this.managerNmMaxLength = managerNmMaxLength;
    }

    /**
     * @return orderBy
     */
    public String getOrderBy() {
        return orderBy;
    }

    /**
     * @param orderBy セットする orderBy
     */
    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     *            セットする remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return studentMstEnm
     */
    public String getStudentMstEnm() {
        return studentMstEnm;
    }

    /**
     * @param studentMstEnm
     *            セットする studentMstEnm
     */
    public void setStudentMstEnm(String studentMstEnm) {
        this.studentMstEnm = studentMstEnm;
    }

    /**
     * @return mark
     */
    public String getMark() {
        return mark;
    }

    /**
     * @param mark
     *            セットする mark
     */
    public void setMark(String mark) {
        this.mark = mark;
    }

    /**
     * @return systemDelNgFlg
     */
    public String getSystemDelNgFlg() {
        return systemDelNgFlg;
    }

    /**
     * @param systemDelNgFlg
     *            セットする systemDelNgFlg
     */
    public void setSystemDelNgFlg(String systemDelNgFlg) {
        this.systemDelNgFlg = systemDelNgFlg;
    }

    /**
     * @return recordVerNo
     */
    public int getRecordVerNo() {
        return recordVerNo;
    }

    /**
     * @param recordVerNo
     *            セットする recordVerNo
     */
    public void setRecordVerNo(int recordVerNo) {
        this.recordVerNo = recordVerNo;
    }


}