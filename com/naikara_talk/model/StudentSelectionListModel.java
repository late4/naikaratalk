package com.naikara_talk.model;

import java.util.List;

import com.naikara_talk.dto.StudentMstDto;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様(個人)_初期登録。<br>
 * <b>クラス名称　　　:</b>マイページ(お客様_個人)Modelクラス。<br>
 * <b>クラス概要　　　:</b>マイページ(お客様_個人)Model。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/31 TECS 新規作成。
 */
public class StudentSelectionListModel implements Model {

    private static final long serialVersionUID = 1L;

    /** チェック：問題なし(正常) */
    public static final int CHECK_OK = 0;

    /** 顧客区分 */
    private String customerKbn;

    /** 受講者ID */
    private String studentId;

    /** 受講者名(ニックネーム) */
    private String studentNm;

    /** 受講者名(フリガナ) */
    private String studentFuri;

    /** 組織名 */
    private String organization;

    /** 検索結果一覧 */
    private List<StudentMstDto> resultList;

    /** 画面状態フラグ */
    private String searchFlg;

    /** 保護者の同意書の入手フラグ */
    private String consentDocumentAcquisitionFlg;

    /** ポイント購入済フラグ */
    private String pointPurchaseFlg;

    /**
     * 保護者の同意書の入手フラグ取得<br>
     * <br>
     * 保護者の同意書の入手フラグ取得を行う<br>
     * <br>
     * @param なし<br>
     * @return consentDocumentAcquisitionFlg 保護者の同意書の入手フラグ<br>
     * @exception なし
     */
    public String getConsentDocumentAcquisitionFlg() {
        return consentDocumentAcquisitionFlg;
    }

    /**
     * 保護者の同意書の入手フラグ設定<br>
     * <br>
     * 保護者の同意書の入手フラグ設定を行う<br>
     * <br>
     * @param consentDocumentAcquisitionFlg 保護者の同意書の入手フラグ<br>
     * @return なし<br>
     * @exception なし
     */
    public void setConsentDocumentAcquisitionFlg(String consentDocumentAcquisitionFlg) {
        this.consentDocumentAcquisitionFlg = consentDocumentAcquisitionFlg;
    }

    /**
     * ポイント購入済フラグ取得<br>
     * <br>
     * ポイント購入済フラグ取得を行う<br>
     * <br>
     * @param なし<br>
     * @return pointPurchaseFlg ポイント購入済フラグ<br>
     * @exception なし
     */
    public String getPointPurchaseFlg() {
        return pointPurchaseFlg;
    }

    /**
     * ポイント購入済フラグ設定<br>
     * <br>
     * ポイント購入済フラグ設定を行う<br>
     * <br>
     * @param pointPurchaseFlg ポイント購入済フラグ<br>
     * @return なし<br>
     * @exception なし
     */
    public void setPointPurchaseFlg(String pointPurchaseFlg) {
        this.pointPurchaseFlg = pointPurchaseFlg;
    }

    /**
     * 受講者名(フリガナ)取得<br>
     * <br>
     * 受講者名(フリガナ)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return studentFuri 受講者名(フリガナ)<br>
     * @exception なし
     */
    public String getStudentFuri() {
        return studentFuri;
    }

    /**
     * 受講者名(フリガナ)設定<br>
     * <br>
     * 受講者名(フリガナ)設定を行う<br>
     * <br>
     * @param studentFuri 受講者名(フリガナ)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setStudentFuri(String studentFuri) {
        this.studentFuri = studentFuri;
    }

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
     * 顧客区分取得<br>
     * <br>
     * 顧客区分取得を行う<br>
     * <br>
     * @param なし<br>
     * @return customerKbn 顧客区分<br>
     * @exception なし
     */
    public String getCustomerKbn() {
        return customerKbn;
    }

    /**
     * 顧客区分設定<br>
     * <br>
     * 顧客区分設定を行う<br>
     * <br>
     * @param customerKbn 顧客区分<br>
     * @return なし<br>
     * @exception なし
     */
    public void setCustomerKbn(String customerKbn) {
        this.customerKbn = customerKbn;
    }

    /**
     * 受講者ID取得<br>
     * <br>
     * 受講者ID取得を行う<br>
     * <br>
     * @param なし<br>
     * @return studentId 受講者ID<br>
     * @exception なし
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * 受講者ID設定<br>
     * <br>
     * 受講者ID設定を行う<br>
     * <br>
     * @param studentId 受講者ID<br>
     * @return なし<br>
     * @exception なし
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * 受講者名(ニックネーム)取得<br>
     * <br>
     * 受講者名(ニックネーム)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return studentNm 受講者名(ニックネーム)<br>
     * @exception なし
     */
    public String getStudentNm() {
        return studentNm;
    }

    /**
     * 受講者名(ニックネーム)設定<br>
     * <br>
     * 受講者名(ニックネーム)設定を行う<br>
     * <br>
     * @param studentNm 受講者名(ニックネーム)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setStudentNm(String studentNm) {
        this.studentNm = studentNm;
    }

    /**
     * 組織名取得<br>
     * <br>
     * 組織名取得を行う<br>
     * <br>
     * @param なし<br>
     * @return organization 組織名<br>
     * @exception なし
     */
    public String getOrganization() {
        return organization;
    }

    /**
     * 組織名設定<br>
     * <br>
     * 組織名設定を行う<br>
     * <br>
     * @param organization 組織名<br>
     * @return なし<br>
     * @exception なし
     */
    public void setOrganization(String organization) {
        this.organization = organization;
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
    public List<StudentMstDto> getResultList() {
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
    public void setResultList(List<StudentMstDto> resultList) {
        this.resultList = resultList;
    }
}