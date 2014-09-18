package com.naikara_talk.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.naikara_talk.dto.CourseUsePointMstDto;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>コースマスタメンテナンス(コース利用ポイント)<br>
 * <b>クラス概要       :</b>コースマスタメンテナンス(コース利用ポイント)Model<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/13 TECS 新規作成
 */
public class GoodsCourseUsePointMstModel implements Model {

    private static final long serialVersionUID = 1L;

    /** チェック：問題なし(正常) */
    public static final String CHECK_OK = "OK";

    /** コースコード */
    private String courseCd;

    /** 大分類 */
    private String bigClassificationCd;

    /** 中分類 */
    private String middleClassificationCd;

    /** 小分類 */
    private String smallClassificationCd;

    /** コース名  */
    private String courseJnm;

    /** 提供開始日 */
    private String useStrDt;

    /** 提供終了日 */
    private String useEndDt;

    /** 検索結果一覧 */
    private List<CourseUsePointMstDto> resultList;

    /** 修正No */
    private String numberNo;

    /** 利用ポイント開始日 */
    private String usePointStrDt;

    /** 利用ポイント終了日 */
    private String usePointEndDt;

    /** 利用ポイント */
    private BigDecimal usePoint;

    /** お知らせ*/
    private String information;

    /** レコードバージョン番号 */
    private int recordVerNo;

    /** 処理区分(前画面の引き継ぎ情報) */
    private String processKbn_rdl;

    /**
     * @return courseCd
     */
    public String getCourseCd() {
        return courseCd;
    }

    /**
     * @param courseCd セットする courseCd
     */
    public void setCourseCd(String courseCd) {
        this.courseCd = courseCd;
    }

    /**
     * @return bigClassificationCd
     */
    public String getBigClassificationCd() {
        return bigClassificationCd;
    }

    /**
     * @param bigClassificationCd セットする bigClassificationCd
     */
    public void setBigClassificationCd(String bigClassificationCd) {
        this.bigClassificationCd = bigClassificationCd;
    }

    /**
     * @return middleClassificationCd
     */
    public String getMiddleClassificationCd() {
        return middleClassificationCd;
    }

    /**
     * @param middleClassificationCd セットする middleClassificationCd
     */
    public void setMiddleClassificationCd(String middleClassificationCd) {
        this.middleClassificationCd = middleClassificationCd;
    }

    /**
     * @return smallClassificationCd
     */
    public String getSmallClassificationCd() {
        return smallClassificationCd;
    }

    /**
     * @param smallClassificationCd セットする smallClassificationCd
     */
    public void setSmallClassificationCd(String smallClassificationCd) {
        this.smallClassificationCd = smallClassificationCd;
    }

    /**
     * @return courseJnm
     */
    public String getCourseJnm() {
        return courseJnm;
    }

    /**
     * @param courseJnm セットする courseJnm
     */
    public void setCourseJnm(String courseJnm) {
        this.courseJnm = courseJnm;
    }

    /**
     * @return useStrDt
     */
    public String getUseStrDt() {
        return useStrDt;
    }

    /**
     * @param useStrDt セットする useStrDt
     */
    public void setUseStrDt(String useStrDt) {
        this.useStrDt = useStrDt;
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
    public List<CourseUsePointMstDto> getResultList() {
        if (this.resultList == null) {
            this.resultList = new ArrayList<CourseUsePointMstDto>();
        }
        return resultList;
    }

    /**
     * @param resultList セットする resultList
     */
    public void setResultList(List<CourseUsePointMstDto> resultList) {
        this.resultList = resultList;
    }

    /**
     * @return numberNo
     */
    public String getNumberNo() {
        return numberNo;
    }

    /**
     * @param numberNo セットする numberNo
     */
    public void setNumberNo(String numberNo) {
        this.numberNo = numberNo;
    }

    /**
     * @return usePointStrDt
     */
    public String getUsePointStrDt() {
        return usePointStrDt;
    }

    /**
     * @param usePointStrDt セットする usePointStrDt
     */
    public void setUsePointStrDt(String usePointStrDt) {
        this.usePointStrDt = usePointStrDt;
    }

    /**
     * @return usePointEndDt
     */
    public String getUsePointEndDt() {
        return usePointEndDt;
    }

    /**
     * @param usePointEndDt セットする usePointEndDt
     */
    public void setUsePointEndDt(String usePointEndDt) {
        this.usePointEndDt = usePointEndDt;
    }

    /**
     * @return usePoint
     */
    public BigDecimal getUsePoint() {
        return usePoint;
    }

    /**
     * @param usePoint セットする usePoint
     */
    public void setUsePoint(BigDecimal usePoint) {
        this.usePoint = usePoint;
    }

    /**
     * @return information
     */
    public String getInformation() {
        return information;
    }

    /**
     * @param information セットする information
     */
    public void setInformation(String information) {
        this.information = information;
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

}