package com.naikara_talk.model;


/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>組織_初期処理<br>
 * <b>クラス名称       :</b>システム受講者登録(単票)Modelクラス。<br>
 * <b>クラス概要       :</b>組織の社員情報(受講者)の新規アカウント(単票)。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/09 TECS 新規作成
 */
public class OrganizationStudentMstModel implements Model {

    private static final long serialVersionUID = 1L;

    /** 処理区分(前画面情報) */
    private String processKbn_rdl;

    /** 画面表示処理区分 */
    private String processKbn_txt;

    /** 組織名 */
    private String organizationNm_txt;

    /** 受講者ID */
    private String studentId;

    /** 仮パスワード */
    private String password_txt;

    /** 仮パスワード確認 */
    private String passworCon_txt;

    /** 受講者所属部署 */
    private String studentPosition;

    /** 所属組織内ID */
    private String positionOrganizationId;

    /** お名前(姓) */
    private String familyJnm;

    /** お名前(名) */
    private String firstJnm;

    /** 自己負担率 */
    private int burdenNum;

    /** 利用停止状態 */
    private String useStopFlg;

    /** 利用停止日 */
    private String useEndDt;

    /** 組織ID */
    private String organizationId;

    /** レコードバージョン番号 */
    protected int recordVerNo;

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
     * @return processKbn_txt
     */
    public String getProcessKbn_txt() {
        return processKbn_txt;
    }

    /**
     * @param processKbn_txt セットする processKbn_txt
     */
    public void setProcessKbn_txt(String processKbn_txt) {
        this.processKbn_txt = processKbn_txt;
    }

    /**
     * @return organizationNm_txt
     */
    public String getOrganizationNm_txt() {
        return organizationNm_txt;
    }

    /**
     * @param organizationNm_txt セットする organizationNm_txt
     */
    public void setOrganizationNm_txt(String organizationNm_txt) {
        this.organizationNm_txt = organizationNm_txt;
    }

    /**
     * @return studentId
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * @param studentId セットする studentId
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * @return password_txt
     */
    public String getPassword_txt() {
        return password_txt;
    }

    /**
     * @param password_txt セットする password_txt
     */
    public void setPassword_txt(String password_txt) {
        this.password_txt = password_txt;
    }

    /**
     * @return passworCon_txt
     */
    public String getPassworCon_txt() {
        return passworCon_txt;
    }

    /**
     * @param passworCon_txt セットする passworCon_txt
     */
    public void setPassworCon_txt(String passworCon_txt) {
        this.passworCon_txt = passworCon_txt;
    }

    /**
     * @return studentPosition
     */
    public String getStudentPosition() {
        return studentPosition;
    }

    /**
     * @param studentPosition セットする studentPosition
     */
    public void setStudentPosition(String studentPosition) {
        this.studentPosition = studentPosition;
    }

    /**
     * @return positionOrganizationId
     */
    public String getPositionOrganizationId() {
        return positionOrganizationId;
    }

    /**
     * @param positionOrganizationId セットする positionOrganizationId
     */
    public void setPositionOrganizationId(String positionOrganizationId) {
        this.positionOrganizationId = positionOrganizationId;
    }

    /**
     * @return familyJnm
     */
    public String getFamilyJnm() {
        return familyJnm;
    }

    /**
     * @param familyJnm セットする familyJnm
     */
    public void setFamilyJnm(String familyJnm) {
        this.familyJnm = familyJnm;
    }

    /**
     * @return firstJnm
     */
    public String getFirstJnm() {
        return firstJnm;
    }

    /**
     * @param firstJnm セットする firstJnm
     */
    public void setFirstJnm(String firstJnm) {
        this.firstJnm = firstJnm;
    }

    /**
     * @return burdenNum
     */
    public int getBurdenNum() {
        return burdenNum;
    }

    /**
     * @param burdenNum セットする burdenNum
     */
    public void setBurdenNum(int burdenNum) {
        this.burdenNum = burdenNum;
    }

    /**
     * @return useStopFlg
     */
    public String getUseStopFlg() {
        return useStopFlg;
    }

    /**
     * @param useStopFlg セットする useStopFlg
     */
    public void setUseStopFlg(String useStopFlg) {
        this.useStopFlg = useStopFlg;
    }

    /**
     * @return useEndDt
     */
    public String getUseEndDt() {
        return useEndDt;
    }

    /**
     * @param useEndDt セットする useEndDt
     */
    public void setUseEndDt(String useEndDt) {
        this.useEndDt = useEndDt;
    }

    /**
     * @return recordVerNo
     */
    public int getRecordVerNo() {
        return recordVerNo;
    }

    /**
     * @param recordVerNo セットする recordVerNo
     */
    public void setRecordVerNo(int recordVerNo) {
        this.recordVerNo = recordVerNo;
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

}
