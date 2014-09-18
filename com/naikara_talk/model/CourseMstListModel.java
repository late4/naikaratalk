package com.naikara_talk.model;

import java.util.List;

import com.naikara_talk.dto.CourseMstDto;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>コースマスタメンテナンス(一覧)<br>
 * <b>クラス概要       :</b>コースマスタメンテナンス(一覧)Model<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/02 TECS 新規作成
 */
public class CourseMstListModel implements Model {

    private static final long serialVersionUID = 1L;

    /** 処理区分：新規 */
    public static final String PROS_KBN_ADD = "0";

    /** 処理区分：修正 */
    public static final String PROS_KBN_UPD = "1";

    /** 処理区分：照会 */
    public static final String PROS_KBN_REF = "2";

    /** チェック：問題なし(正常) */
    public static final int CHECK_OK = 0;

    /** 処理区分 */
    private String processKbn;

    /** 大分類 */
    private String bigClassificationCd;

    /** 中分類 */
    private String middleClassificationCd;

    /** 小分類 */
    private String smallClassificationCd;

    /** コース名  */
    private String courseJnm;

    /** キーワード1 */
    private String keyword1;

    /** キーワード2 */
    private String keyword2;

    /** キーワード3 */
    private String keyword3;

    /** キーワード4 */
    private String keyword4;

    /** キーワード5 */
    private String keyword5;

    /** 検索結果一覧 */
    private List<CourseMstDto> resultList;

    /** 検索判断フラグ */
    private String searchFlg;

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
     * @return keyword1
     */
    public String getKeyword1() {
        return keyword1;
    }

    /**
     * @param keyword1 セットする keyword1
     */
    public void setKeyword1(String keyword1) {
        this.keyword1 = keyword1;
    }

    /**
     * @return keyword2
     */
    public String getKeyword2() {
        return keyword2;
    }

    /**
     * @param keyword2 セットする keyword2
     */
    public void setKeyword2(String keyword2) {
        this.keyword2 = keyword2;
    }

    /**
     * @return keyword3
     */
    public String getKeyword3() {
        return keyword3;
    }

    /**
     * @param keyword3 セットする keyword3
     */
    public void setKeyword3(String keyword3) {
        this.keyword3 = keyword3;
    }

    /**
     * @return keyword4
     */
    public String getKeyword4() {
        return keyword4;
    }

    /**
     * @param keyword4 セットする keyword4
     */
    public void setKeyword4(String keyword4) {
        this.keyword4 = keyword4;
    }

    /**
     * @return keyword5
     */
    public String getKeyword5() {
        return keyword5;
    }

    /**
     * @param keyword5 セットする keyword5
     */
    public void setKeyword5(String keyword5) {
        this.keyword5 = keyword5;
    }

    /**
     * @return resultList
     */
    public List<CourseMstDto> getResultList() {
        return resultList;
    }

    /**
     * @param resultList セットする resultList
     */
    public void setResultList(List<CourseMstDto> resultList) {
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

}