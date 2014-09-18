package com.naikara_talk.sessiondata;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>コースマスタメンテナンスのセッション情報クラス。<br>
 * <b>クラス概要       :</b>コースマスタメンテナンスの戻る用のセッション情報。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/02 TECS 新規作成
 */
public class SessionCourseMst implements SessionData {

    /** ヘッダの戻るリンク用のSessionKey */
    private static final String HEADER_RETURN_KEY = "CourseMstList";

    /** 戻る判定Onフラグ */
    private boolean returnOnFlg;

    /** 検索判断フラグ */
    private String hasSearchFlg;

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

    /** 検索Key：大分類 */
    private String searchBigClassificationCd;

    /** 検索Key：中分類 */
    private String searchMiddleClassificationCd;

    /** 検索Key：小分類 */
    private String searchSmallClassificationCd;

    /** 検索Key：コース名  */
    private String searchCourseJnm;

    /** 検索Key：キーワード1 */
    private String searchKeyword1;

    /** 検索Key：キーワード2 */
    private String searchKeyword2;

    /** 検索Key：キーワード3 */
    private String searchKeyword3;

    /** 検索Key：キーワード4 */
    private String searchKeyword4;

    /** 検索Key：キーワード5 */
    private String searchKeyword5;

    /** 検索Key：選択された明細のKey-コースコード */
    private String searchCourseCdKey;

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
     * @return searchBigClassificationCd
     */
    public String getSearchBigClassificationCd() {
        return searchBigClassificationCd;
    }

    /**
     * @param searchBigClassificationCd セットする searchBigClassificationCd
     */
    public void setSearchBigClassificationCd(String searchBigClassificationCd) {
        this.searchBigClassificationCd = searchBigClassificationCd;
    }

    /**
     * @return searchMiddleClassificationCd
     */
    public String getSearchMiddleClassificationCd() {
        return searchMiddleClassificationCd;
    }

    /**
     * @param searchMiddleClassificationCd セットする searchMiddleClassificationCd
     */
    public void setSearchMiddleClassificationCd(String searchMiddleClassificationCd) {
        this.searchMiddleClassificationCd = searchMiddleClassificationCd;
    }

    /**
     * @return searchSmallClassificationCd
     */
    public String getSearchSmallClassificationCd() {
        return searchSmallClassificationCd;
    }

    /**
     * @param searchSmallClassificationCd セットする searchSmallClassificationCd
     */
    public void setSearchSmallClassificationCd(String searchSmallClassificationCd) {
        this.searchSmallClassificationCd = searchSmallClassificationCd;
    }

    /**
     * @return searchCourseJnm
     */
    public String getSearchCourseJnm() {
        return searchCourseJnm;
    }

    /**
     * @param searchCourseJnm セットする searchCourseJnm
     */
    public void setSearchCourseJnm(String searchCourseJnm) {
        this.searchCourseJnm = searchCourseJnm;
    }

    /**
     * @return searchKeyword1
     */
    public String getSearchKeyword1() {
        return searchKeyword1;
    }

    /**
     * @param searchKeyword1 セットする searchKeyword1
     */
    public void setSearchKeyword1(String searchKeyword1) {
        this.searchKeyword1 = searchKeyword1;
    }

    /**
     * @return searchKeyword2
     */
    public String getSearchKeyword2() {
        return searchKeyword2;
    }

    /**
     * @param searchKeyword2 セットする searchKeyword2
     */
    public void setSearchKeyword2(String searchKeyword2) {
        this.searchKeyword2 = searchKeyword2;
    }

    /**
     * @return searchKeyword3
     */
    public String getSearchKeyword3() {
        return searchKeyword3;
    }

    /**
     * @param searchKeyword3 セットする searchKeyword3
     */
    public void setSearchKeyword3(String searchKeyword3) {
        this.searchKeyword3 = searchKeyword3;
    }

    /**
     * @return searchKeyword4
     */
    public String getSearchKeyword4() {
        return searchKeyword4;
    }

    /**
     * @param searchKeyword4 セットする searchKeyword4
     */
    public void setSearchKeyword4(String searchKeyword4) {
        this.searchKeyword4 = searchKeyword4;
    }

    /**
     * @return searchKeyword5
     */
    public String getSearchKeyword5() {
        return searchKeyword5;
    }

    /**
     * @param searchKeyword5 セットする searchKeyword5
     */
    public void setSearchKeyword5(String searchKeyword5) {
        this.searchKeyword5 = searchKeyword5;
    }

    /**
     * @return searchCourseCdKey
     */
    public String getSearchCourseCdKey() {
        return searchCourseCdKey;
    }

    /**
     * @param searchCourseCdKey セットする searchCourseCdKey
     */
    public void setSearchCourseCdKey(String searchCourseCdKey) {
        this.searchCourseCdKey = searchCourseCdKey;
    }

}
