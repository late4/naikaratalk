package com.naikara_talk.sessiondata;

import java.util.List;

import com.naikara_talk.dto.PointOwnershipTrnDto;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守。<br>
 * <b>クラス名称　　　:</b>特別無償ポイント設定のセッション情報クラス。<br>
 * <b>クラス概要　　　:</b>特別無償ポイント設定の戻る用のセッション情報。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b> 2013/07/30 TECS 新規作成。
 */

public class SessionStudentSpecialFreePointMst implements SessionData {

    /** ヘッダの戻るリンク用のSessionKey */
    private static final String HEADER_RETURN_KEY = "StudentSpecialFreePointMst";

    /** 検索Key：処理区分 */
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
     * @return headerReturnKey
     */
    public static String getHeaderReturnKey() {
        return HEADER_RETURN_KEY;
    }

}
