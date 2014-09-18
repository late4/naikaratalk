package com.naikara_talk.sessiondata;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>組織_初期処理<br>
 * <b>クラス名称       :</b>システム受講者登録のセッション情報クラス。<br>
 * <b>クラス概要       :</b>システム受講者登録の戻る用のセッション情報。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/01 TECS 新規作成
 */
public class SessionOrganizationStudentMst implements SessionData {

    /** ヘッダの戻るリンク用のSessionKey */
    private static final String HEADER_RETURN_KEY = "OrganizationStudentMstList";

    /** 戻る判定Onフラグ */
    private boolean returnOnFlg;

    /** 検索判断フラグ */
    private String hasSearchFlg;

    /** 処理区分 */
    private String processKbn;

    /** 受講者所属部署 */
    private String studentPosition;

    /** 所属組織内ID */
    private String positionOrganizationId;

    /** 受講者ID */
    private String studentId;

    /** 組織ID */
    private String organizationId;

    /** 組織名 */
    private String organizationNm;

    /** 受講者名(フリガナ) */
    private String studentNm;

    /** 利用状態 */
    private String useKbn;

    /** 検索Key：処理区分 */
    private String searchProcessKbn;

    /** 検索Key：受講者所属部署 */
    private String searchStudentPosition;

    /** 検索Key：所属組織内ID */
    private String searchPositionOrganizationId;

    /** 検索Key：受講者ID */
    private String searchStudentId;

    /** 検索Key：受講者名(フリガナ) */
    private String searchStudentNm;

    /** 検索Key：利用状態 */
    private String searchUseKbn;

    /** 検索Key：組織ID */
    private String searchOrganizationId;

    /** 検索Key：組織名 */
    private String searchOrganizationNm;

    /** 検索Key：選択された明細のKey-受講者ID */
    private String searchStudentIdKey;

    /**
     * このセッションデータのキーを返却する
     */
    public static String getKey() {
        return HEADER_RETURN_KEY;
    }

    /**
     * @return processKbn
     */
    public boolean getReturnOnFlg() {
        return returnOnFlg;
    }

    /**
     * @param returnOnFlg セットする returnOnFlg
     */
    public void setReturnOnFlg(boolean returnOnFlg) {
        this.returnOnFlg = returnOnFlg;
    }

    /**
     * @return hasSearchFlg
     */
    public String getHasSearchFlg() {
        return hasSearchFlg;
    }

    /**
     * @param hasSearchFlg セットする hasSearchFlg
     */
    public void setHasSearchFlg(String hasSearchFlg) {
        this.hasSearchFlg = hasSearchFlg;
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
     * @return studentNm
     */
    public String getStudentNm() {
        return studentNm;
    }

    /**
     * @param studentNm セットする studentNm
     */
    public void setStudentNm(String studentNm) {
        this.studentNm = studentNm;
    }

    /**
     * @return useKbn
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
     * @return searchProcessKbn
     */
    public String getSearchProcessKbn() {
        return searchProcessKbn;
    }

    /**
     * @param searchProcessKbn セットする searchProcessKbn
     */
    public void setSearchProcessKbn(String searchProcessKbn) {
        this.searchProcessKbn = searchProcessKbn;
    }

    /**
     * @return searchStudentPosition
     */
    public String getSearchStudentPosition() {
        return searchStudentPosition;
    }

    /**
     * @param searchStudentPosition セットする searchStudentPosition
     */
    public void setSearchStudentPosition(String searchStudentPosition) {
        this.searchStudentPosition = searchStudentPosition;
    }

    /**
     * @return searchPositionOrganizationId
     */
    public String getSearchPositionOrganizationId() {
        return searchPositionOrganizationId;
    }

    /**
     * @param searchPositionOrganizationId セットする searchPositionOrganizationId
     */
    public void setSearchPositionOrganizationId(String searchPositionOrganizationId) {
        this.searchPositionOrganizationId = searchPositionOrganizationId;
    }

    /**
     * @return searchStudentId
     */
    public String getSearchStudentId() {
        return searchStudentId;
    }

    /**
     * @param searchStudentId セットする searchStudentId
     */
    public void setSearchStudentId(String searchStudentId) {
        this.searchStudentId = searchStudentId;
    }

    /**
     * @return searchStudentNm
     */
    public String getSearchStudentNm() {
        return searchStudentNm;
    }

    /**
     * @param searchStudentNm セットする searchStudentNm
     */
    public void setSearchStudentNm(String searchStudentNm) {
        this.searchStudentNm = searchStudentNm;
    }

    /**
     * @return searchUseKbn
     */
    public String getSearchUseKbn() {
        return searchUseKbn;
    }

    /**
     * @param searchUseKbn セットする searchUseKbn
     */
    public void setSearchUseKbn(String searchUseKbn) {
        this.searchUseKbn = searchUseKbn;
    }

    /**
     * @return searchStudentIdKey
     */
    public String getSearchStudentIdKey() {
        return searchStudentIdKey;
    }

    /**
     * @param searchStudentIdKey セットする searchStudentIdKey
     */
    public void setSearchStudentIdKey(String searchStudentIdKey) {
        this.searchStudentIdKey = searchStudentIdKey;
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
     * @return organizationNm
     */
    public String getOrganizationNm() {
        return organizationNm;
    }

    /**
     * @param organizationNm セットする organizationNm
     */
    public void setOrganizationNm(String organizationNm) {
        this.organizationNm = organizationNm;
    }

    /**
     * @return searchOrganizationId
     */
    public String getSearchOrganizationId() {
        return searchOrganizationId;
    }

    /**
     * @param searchOrganizationId セットする searchOrganizationId
     */
    public void setSearchOrganizationId(String searchOrganizationId) {
        this.searchOrganizationId = searchOrganizationId;
    }

    /**
     * @return searchOrganizationNm
     */
    public String getSearchOrganizationNm() {
        return searchOrganizationNm;
    }

    /**
     * @param searchOrganizationNm セットする searchOrganizationNm
     */
    public void setSearchOrganizationNm(String searchOrganizationNm) {
        this.searchOrganizationNm = searchOrganizationNm;
    }

}
