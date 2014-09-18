package com.naikara_talk.model;

import java.util.List;

import com.naikara_talk.dto.PointOwnershipTrnDto;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守<br>
 * <b>クラス名称　　　:</b>受講者別月謝購入情報Modelクラス。<br>
 * <b>クラス概要　　　:</b>受講者別月謝購入情報Model。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b> 2013/07/30 TECS 新規作成。
 */
public class StudentMonthlyFeePurchaseInformationModel implements Model {

    private static final long serialVersionUID = 1L;

    /** 処理区分：新規 */
    public static final String PROS_KBN_ADD = "0";

    /** 処理区分：修正 */
    public static final String PROS_KBN_UPD = "1";

    /** 処理区分：照会 */
    public static final String PROS_KBN_REF = "2";

    /** チェック：問題なし(正常) */
    public static final int CHECK_OK = 0;

    /** 受講者ID */
    private String studentId;

    /** ニックネーム */
    private String nickNm;

    /** 名前(姓) */
    private String studentFamilyNm;

    /** 名前(名) */
    private String studentFirstNm;

    /** 利用期間：開始日 */
    private String useStartDt;

    /** 利用期間：終了日 */
    private String useEndDt;

    /** 検索結果一覧 */
    private List<PointOwnershipTrnDto> resultList;

    /** 月謝停止年月 */
    private String[] endDt;

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
     * @return nickNm
     */
    public String getNickNm() {
        return nickNm;
    }

    /**
     * @param nickNm セットする nickNm
     */
    public void setNickNm(String nickNm) {
        this.nickNm = nickNm;
    }

    /**
     * @return studentFamilyNm
     */
    public String getStudentFamilyNm() {
        return studentFamilyNm;
    }

    /**
     * @param studentFamilyNm セットする studentFamilyNm
     */
    public void setStudentFamilyNm(String studentFamilyNm) {
        this.studentFamilyNm = studentFamilyNm;
    }

    /**
     * @return studentFirstNm
     */
    public String getStudentFirstNm() {
        return studentFirstNm;
    }

    /**
     * @param studentFirstNm セットする studentFirstNm
     */
    public void setStudentFirstNm(String studentFirstNm) {
        this.studentFirstNm = studentFirstNm;
    }

    /**
     * @return useStartDt
     */
    public String getUseStartDt() {
        return useStartDt;
    }

    /**
     * @param useStartDt セットする useStartDt
     */
    public void setUseStartDt(String useStartDt) {
        this.useStartDt = useStartDt;
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
    * @return resultList
    */
    public List<PointOwnershipTrnDto> getResultList() {
        return resultList;
    }

    /**
    * @param resultList セットする resultList
    */
    public void setResultList(List<PointOwnershipTrnDto> resultList) {
        this.resultList = resultList;
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
     * @return prosKbnRef
     */
    public static String getProsKbnRef() {
        return PROS_KBN_REF;
    }

    /**
     * @return checkOk
     */
    public static int getCheckOk() {
        return CHECK_OK;
    }

    /**
     * @return endDt
     */
    public String[] getEndDt() {
        return endDt;
    }

    /**
     * @param endDt セットする endDt
     */
    public void setEndDt(String[] endDt) {
        this.endDt = endDt;
    }

}