package com.naikara_talk.model;

import java.math.BigDecimal;
import java.util.List;

import com.naikara_talk.dto.CourseUsePointListDto;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_初期登録。<br>
 * <b>クラス名称       :</b>コース別ポイント一覧初期処理Modelクラス。<br>
 * <b>クラス概要       :</b>コース別ポイント一覧初期処理Model。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/06 TECS 新規作成
 * <b>                 :</b>2014/04/22 TECS 検索条件：希望日のコントロールをテキストからプルダウンへ変更対応
 * <b>                 :</b>2014/04/22 TECS 検索条件：コース名の追加対応
 * <b>                 :</b>2014/04/22 TECS 検索条件：キーワード検索の削除・コース名の追加対応
 */
public class SComCoursePointModel implements Model {

    private static final long serialVersionUID = 1L;

    /** チェック：問題なし(正常) */
    public static final int CHECK_OK = 0;

    /** コースコード */
    private String courseCd;

    // 2014/04/22 Upd Start 希望日をテキストからプルダウンへ変更対応
    /** 希望日 */
    //private String sysDate;
    private String hopeDt;
    // 2014/04/22 Upd End   希望日をテキストからプルダウンへ変更対応

    // 2014/04/22 Del Start キーワード検索の削除・コース名の追加対応
    ///** キーワード１ */
    //private String keyword1;

    ///** キーワード２ */
    //private String keyword2;

    ///** キーワード３ */
    //private String keyword3;

    ///** キーワード４ */
    //private String keyword4;

    ///** キーワード５ */
    //private String keyword5;
    // 2014/04/22 Del End   キーワード検索の削除・コース名の追加対応

    // 2014/04/22 Add Start コース名の追加対応
    /** 検索条件部：コース名(大分類) */
    private String searchCourseLargeCd;

    /** 検索条件部：コース名(中分類) */
    private String searchCourseMediumCd;

    /** 検索条件部：コース名(小分類) */
    private String searchCourseSmallCd;

    /** 検索条件部：コース名(コース名⇒キーワード) */
    private String searchKeyword;
    // 2014/04/22 Add End   コース名の追加対応

    /** 大分類コード */
    private String bigClassificationCd;

    /** 中分類コード */
    private String middleClassificationCd;

    /** 小分類コード */
    private String smallClassificationCd;

    /** コース名 */
    private String courseJnm;

    /** 簡易説明 */
    private String simpleExplanation;

    /** 利用ポイント */
    private BigDecimal usePoint;

    /** お知らせ */
    private String information;

    /** 検索結果一覧 */
    private List<CourseUsePointListDto> resultList;

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

    // 2014/04/22 Upd Start 希望日をテキストからプルダウンへ変更対応
    /**
     * @return sysDate
     */
//    public String getSysDate() {
//        return sysDate;
//    }

    /**
     * @param sysDate セットする sysDate
     */
//    public void setSysDate(String sysDate) {
//        this.sysDate = sysDate;
//    }

    /**
     * @return hopeDt
     */
    public String getHopeDt() {
        return hopeDt;
    }

    /**
     * @param hopeDt セットする hopeDt
     */
    public void setHopeDt(String hopeDt) {
        this.hopeDt = hopeDt;
    }
    // 2014/04/22 Upd End   希望日をテキストからプルダウンへ変更対応

    // 2014/04/22 Del Start キーワード検索の削除・コース名の追加対応
    ///**
    // * @return keyword1
    // */
    //public String getKeyword1() {
    //    return keyword1;
    //}

    ///**
    // * @param keyword1 セットする keyword1
    // */
    //public void setKeyword1(String keyword1) {
    //    this.keyword1 = keyword1;
    //}

    ///**
    // * @return keyword2
    // */
    //public String getKeyword2() {
    //    return keyword2;
    //}

    ///**
    // * @param keyword2 セットする keyword2
    // */
    //public void setKeyword2(String keyword2) {
    //    this.keyword2 = keyword2;
    //}

    ///**
    // * @return keyword3
    // */
    //public String getKeyword3() {
    //    return keyword3;
    //}

    ///**
    // * @param keyword3 セットする keyword3
    // */
    //public void setKeyword3(String keyword3) {
    //    this.keyword3 = keyword3;
    //}

    ///**
    // * @return keyword4
    // */
    //public String getKeyword4() {
    //    return keyword4;
    //}

    ///**
    // * @param keyword4 セットする keyword4
    // */
    //public void setKeyword4(String keyword4) {
    //    this.keyword4 = keyword4;
    //}

    ///**
    // * @return keyword5
    // */
    //public String getKeyword5() {
    //    return keyword5;
    //}

    ///**
    // * @param keyword5 セットする keyword5
    // */
    //public void setKeyword5(String keyword5) {
    //    this.keyword5 = keyword5;
    //}
    // 2014/04/22 Del End  キーワード検索の削除・コース名の追加対応


    // 2014/04/22 Add Start コース名の追加対応
    /**
     * @return 検索条件部：コース名(大分類)
     */
    public String getSearchCourseLargeCd() {
        return searchCourseLargeCd;
    }

    /**
     * @param 検索条件部：コース名(大分類) セットする searchCourseLargeCd
     */
    public void setSearchCourseLargeCd(String searchCourseLargeCd) {
        this.searchCourseLargeCd = searchCourseLargeCd;
    }

    /**
     * @return 検索条件部：コース名(中分類)
     */
    public String getSearchCourseMediumCd() {
        return searchCourseMediumCd;
    }

    /**
     * @param 検索条件部：コース名(中分類) セットする searchCourseMediumCd
     */
    public void setSearchCourseMediumCd(String searchCourseMediumCd) {
        this.searchCourseMediumCd = searchCourseMediumCd;
    }

    /**
     * @return 検索条件部：コース名(小分類)
     */
    public String getSearchCourseSmallCd() {
        return searchCourseSmallCd;
    }

    /**
     * @param 検索条件部：コース名(小分類) セットする searchCourseSmallCd
     */
    public void setSearchCourseSmallCd(String searchCourseSmallCd) {
        this.searchCourseSmallCd = searchCourseSmallCd;
    }

    /**
     * @return 検索条件部：コース名(キーワード)
     */
    public String getSearchKeyword() {
        return searchKeyword;
    }

    /**
     * @param 検索条件部：コース名(キーワード) セットする searchKeyword
     */
    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }
    // 2014/04/22 Add End   コース名の追加対応


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
     * @return simpleExplanation
     */
    public String getSimpleExplanation() {
        return simpleExplanation;
    }

    /**
     * @param simpleExplanation セットする simpleExplanation
     */
    public void setSimpleExplanation(String simpleExplanation) {
        this.simpleExplanation = simpleExplanation;
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

    public List<CourseUsePointListDto> getResultList() {
        return resultList;
    }

    public void setResultList(List<CourseUsePointListDto> resultList) {
        this.resultList = resultList;
    }

}
