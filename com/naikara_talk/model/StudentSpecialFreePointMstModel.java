package com.naikara_talk.model;

import java.util.List;

import com.naikara_talk.dto.PointOwnershipTrnDto;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守<br>
 * <b>クラス名称　　　:</b>特別無償ポイント設定Modelクラス。<br>
 * <b>クラス概要　　　:</b>特別無償ポイント設定Model。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b> 2013/07/30 TECS 新規作成。
 */
public class StudentSpecialFreePointMstModel implements Model {

    private static final long serialVersionUID = 1L;

    /** 処理区分：新規 */
    public static final String PROS_KBN_ADD = "0";

    /** 処理区分：修正 */
    public static final String PROS_KBN_UPD = "1";

    /** 処理区分：削除 */
    public static final String PROS_KBN_DEL = "2";

    /** 処理区分：照会 */
    public static final String PROS_KBN_REF = "3";

    /** 更新区分（新規："1"）*/
    public static final String UPDATE_KBN_ADD = "1";

    /** 更新区分（削除："2"）*/
    public static final String UPDATE_KBN_DEL = "2";

    /** チェック：問題なし(正常) */
    public static final int CHECK_OK = 0;

    /** 処理区分(前画面の引き継ぎ情報) */
    private String processKbn_rdl;

    /** 受講者ID */
    private String studentId;

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

    /** 入力：有効開始日 */
    private String effectStartDt;

    /** 入力：有効終了日 */
    private String effectEndDt;

    /** 入力：無償ポイント */
    private String balancePoint;

    /** 入力：ポイント付加理由 */
    private String pointAdditionReasonCd;

    /** 入力：ポイント付加理由 */
    private String pointAdditionReason;

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
     * @return prosKbnDel
     */
    public static String getProsKbnDel() {
        return PROS_KBN_DEL;
    }

    /**
     * @return prosKbnRef
     */
    public static String getProsKbnRef() {
        return PROS_KBN_REF;
    }

    /**
     * @return updateKbnAdd
     */
    public static String getUpdateKbnAdd() {
        return UPDATE_KBN_ADD;
    }

    /**
     * @return updateKbnDel
     */
    public static String getUpdateKbnDel() {
        return UPDATE_KBN_DEL;
    }

    /**
     * @return checkOk
     */
    public static int getCheckOk() {
        return CHECK_OK;
    }

    /**
     * @return effectStartDt
     */
    public String getEffectStartDt() {
        return effectStartDt;
    }

    /**
     * @param effectStartDt セットする effectStartDt
     */
    public void setEffectStartDt(String effectStartDt) {
        this.effectStartDt = effectStartDt;
    }

    /**
     * @return effectEndDt
     */
    public String getEffectEndDt() {
        return effectEndDt;
    }

    /**
     * @param effectEndDt セットする effectEndDt
     */
    public void setEffectEndDt(String effectEndDt) {
        this.effectEndDt = effectEndDt;
    }

    /**
     * @return balancePoint
     */
    public String getBalancePoint() {
        return balancePoint;
    }

    /**
     * @param balancePoint セットする balancePoint
     */
    public void setBalancePoint(String balancePoint) {
        this.balancePoint = balancePoint;
    }

    /**
     * @return pointAdditionReasonCd
     */
    public String getPointAdditionReasonCd() {
        return pointAdditionReasonCd;
    }

    /**
     * @param pointAdditionReasonCd セットする pointAdditionReasonCd
     */
    public void setPointAdditionReasonCd(String pointAdditionReasonCd) {
        this.pointAdditionReasonCd = pointAdditionReasonCd;
    }

    /**
     * @return pointAdditionReason
     */
    public String getPointAdditionReason() {
        return pointAdditionReason;
    }

    /**
     * @param pointAdditionReason セットする pointAdditionReason
     */
    public void setPointAdditionReason(String pointAdditionReason) {
        this.pointAdditionReason = pointAdditionReason;
    }

}