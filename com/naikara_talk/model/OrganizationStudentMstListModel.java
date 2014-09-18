package com.naikara_talk.model;

import java.util.List;

import com.naikara_talk.dto.StudentMstDto;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>組織_初期処理<br>
 * <b>クラス名称       :</b>システム受講者登録(一覧)Modelクラス。<br>
 * <b>クラス概要       :</b>組織の社員情報(受講者)の新規アカウント(一覧)。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/09 TECS 新規作成
 */
public class OrganizationStudentMstListModel implements Model {

    private static final long serialVersionUID = 1L;

    /** 処理区分：新規 */
    public static final String PROS_KBN_ADD = "0";

    /** 処理区分：修正 */
    public static final String PROS_KBN_UPD = "1";

    /** 処理区分：削除 */
    public static final String PROS_KBN_DEL = "2";

    /** チェック：問題なし(正常) */
    public static final int CHECK_OK = 0;

    /** 処理区分 */
    private String processKbn;

    /** 受講者所属部署 */
    private String studentPosition;

    /** 所属組織内ID */
    private String positionOrganizationId;

    /** 受講者ID */
    private String studentId;

    /** 受講者名(フリガナ) */
    private String studentNm;

    /** 利用状態 */
    private String useKbn;

    /** 検索結果一覧 */
    private List<StudentMstDto> resultList;

    /** 画面状態フラグ */
    private String searchFlg;

    /** 組織ID */
    private String organizationId;

    /** 組織名 */
    private String organizationNm;

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
     * @return resultList
     */
    public List<StudentMstDto> getResultList() {
        return resultList;
    }

    /**
     * @param resultList セットする resultList
     */
    public void setResultList(List<StudentMstDto> resultList) {
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

}