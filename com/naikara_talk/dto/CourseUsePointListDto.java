package com.naikara_talk.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DTOクラス<br>
 * <b>クラス名称　　　:</b>コース別利用ポイント一覧クラス<br>
 * <b>クラス概要　　　:</b>コース別利用ポイント一覧DTO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/06/04 TECS 新規作成
 * <b>　　　　　　　　:</b>2014/04/22 TECS 検索条件：希望日のコントロールをテキストからプルダウンへ変更対応
 * <b>　　　　　　　　:</b>2014/04/22 TECS 検索条件：コース名の追加対応 ※コース名(コース名)⇒コース名(キーワード)
 */
public class CourseUsePointListDto extends AbstractDto {

    private String courseCd;                // コースコード
    private String bigClassificationCd;     // 大分類コード
    private String middleClassificationCd;  // 中分類コード
    private String smallClassificationCd;   // 小分類コード
    private String courseJnm;               // コース名
    private String simpleExplanation;       // 簡易説明
    private BigDecimal usePoint;            // 利用ポイント
    private String information;             // お知らせ

    private int returnCode;                 // リターンコード

    // 画面処理必要項目
    // 2014/04/22 Upd Start 検索条件部：希望日をテキストからプルダウンへ変更対応／検索条件部：コース名の追加対応
    //private String sysDate;                     // 検索条件部：システム日付
    private String hopeDt;                        // 検索条件部：キーワード１
    private String searchBigClassificationCd;     // 検索条件部：コース名(大分類コード)
    private String searchMiddleClassificationCd;  // 検索条件部：コース名(中分類コード)
    private String searchSmallClassificationCd;   // 検索条件部：コース名(小分類コード)
    //private String searchCourseJnm;             // 検索条件部：コース名(コース名)
    private String searchKeyword;                 // 検索条件部：コース名(キーワード)
    // 2014/04/22 Upd End   検索条件部：希望日をテキストからプルダウンへ変更対応／検索条件部：コース名の追加対応

    private String keyword1;                // 検索条件部：キーワード１
    private String keyword2;                // 検索条件部：キーワード２
    private String keyword3;                // 検索条件部：キーワード３
    private String keyword4;                // 検索条件部：キーワード４
    private String keyword5;                // 検索条件部：キーワード５

    private List<CourseGoodsListDto> courseGoodsListDto; // 販売商品マスタ．商品名一覧

    /**
     * コースコード取得<br>
     * <br>
     * コースコードを戻り値で返却する<br>
     * <br>
     * @return courseCd
     */
    public String getCourseCd() {
        return courseCd;
    }

    /**
     * コースコード設定<br>
     * <br>
     * コースコードを引数で設定する<br>
     * <br>
     * @param courseCd
     */
    public void setCourseCd(String courseCd) {
        this.courseCd = courseCd;
    }

    /**
     * 大分類コード取得<br>
     * <br>
     * 大分類コードを戻り値で返却する<br>
     * <br>
     * @return bigClassificationCd
     */
    public String getBigClassificationCd() {
        return bigClassificationCd;
    }

    /**
     * 大分類コード設定<br>
     * <br>
     * 大分類コードを引数で設定する<br>
     * <br>
     * @param bigClassificationCd
     */
    public void setBigClassificationCd(String bigClassificationCd) {
        this.bigClassificationCd = bigClassificationCd;
    }

    /**
     * 中分類コード取得<br>
     * <br>
     * 中分類コードを戻り値で返却する<br>
     * <br>
     * @return middleClassificationCd
     */
    public String getMiddleClassificationCd() {
        return middleClassificationCd;
    }

    /**
     * 中分類コード設定<br>
     * <br>
     * 中分類コードを引数で設定する<br>
     * <br>
     * @param middleClassificationCd
     */
    public void setMiddleClassificationCd(String middleClassificationCd) {
        this.middleClassificationCd = middleClassificationCd;
    }

    /**
     * 小分類コード取得<br>
     * <br>
     * 小分類コードを戻り値で返却する<br>
     * <br>
     * @return smallClassificationCd
     */
    public String getSmallClassificationCd() {
        return smallClassificationCd;
    }

    /**
     * 小分類コード設定<br>
     * <br>
     * 小分類コードを引数で設定する<br>
     * <br>
     * @param smallClassificationCd
     */
    public void setSmallClassificationCd(String smallClassificationCd) {
        this.smallClassificationCd = smallClassificationCd;
    }

    /**
     * コース名取得<br>
     * <br>
     * コース名を戻り値で返却する<br>
     * <br>
     * @return courseJnm
     */
    public String getCourseJnm() {
        return courseJnm;
    }

    /**
     * コース名設定<br>
     * <br>
     * コース名を引数で設定する<br>
     * <br>
     * @param courseJnm
     */
    public void setCourseJnm(String courseJnm) {
        this.courseJnm = courseJnm;
    }

    /**
     * 簡易説明取得<br>
     * <br>
     * 簡易説明を戻り値で返却する<br>
     * <br>
     * @return simpleExplanation
     */
    public String getSimpleExplanation() {
        return simpleExplanation;
    }

    /**
     * 簡易説明設定<br>
     * <br>
     * 簡易説明を引数で設定する<br>
     * <br>
     * @param simpleExplanation
     */
    public void setSimpleExplanation(String simpleExplanation) {
        this.simpleExplanation = simpleExplanation;
    }

    /**
     * 利用ポイント取得<br>
     * <br>
     * 利用ポイントを戻り値で返却する<br>
     * <br>
     * @return usePoint
     */
    public BigDecimal getUsePoint() {
        return usePoint;
    }

    /**
     * 利用ポイント設定<br>
     * <br>
     * 利用ポイントを引数で設定する<br>
     * <br>
     * @param usePoint
     */
    public void setUsePoint(BigDecimal usePoint) {
        this.usePoint = usePoint;
    }

    /**
     * お知らせ取得<br>
     * <br>
     * お知らせを戻り値で返却する<br>
     * <br>
     * @return information
     */
    public String getInformation() {
        return information;
    }

    /**
     * お知らせ設定<br>
     * <br>
     * お知らせを引数で設定する<br>
     * <br>
     * @param information
     */
    public void setInformation(String information) {
        this.information = information;
    }

    /**
     * リターンコード取得<br>
     * <br>
     * リターンコードを戻り値で返却する<br>
     * <br>
     * @return returnCode
     */
    public int getReturnCode() {
        return returnCode;
    }

    /**
     * リターンコード設定<br>
     * <br>
     * リターンコードを引数で設定する<br>
     * <br>
     * @param returnCode
     */
    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

    /**
     * キーワード１取得<br>
     * <br>
     * キーワード１を戻り値で返却する<br>
     * <br>
     * @return keyword1
     */
    public String getKeyword1() {
        return keyword1;
    }

    /**
     * キーワード１設定<br>
     * <br>
     * キーワード１を引数で設定する<br>
     * <br>
     * @param keyword1
     */
    public void setKeyword1(String keyword1) {
        this.keyword1 = keyword1;
    }

    /**
     * キーワード２取得<br>
     * <br>
     * キーワード２を戻り値で返却する<br>
     * <br>
     * @return keyword2
     */
    public String getKeyword2() {
        return keyword2;
    }

    /**
     * キーワード２設定<br>
     * <br>
     * キーワード２を引数で設定する<br>
     * <br>
     * @param keyword2
     */
    public void setKeyword2(String keyword2) {
        this.keyword2 = keyword2;
    }

    /**
     * キーワード３取得<br>
     * <br>
     * キーワード３を戻り値で返却する<br>
     * <br>
     * @return keyword3
     */
    public String getKeyword3() {
        return keyword3;
    }

    /**
     * キーワード３設定<br>
     * <br>
     * キーワード３を引数で設定する<br>
     * <br>
     * @param keyword3
     */
    public void setKeyword3(String keyword3) {
        this.keyword3 = keyword3;
    }

    /**
     * キーワード４取得<br>
     * <br>
     * キーワード４を戻り値で返却する<br>
     * <br>
     * @return keyword4
     */
    public String getKeyword4() {
        return keyword4;
    }

    /**
     * キーワード４設定<br>
     * <br>
     * キーワード４を引数で設定する<br>
     * <br>
     * @param keyword4
     */
    public void setKeyword4(String keyword4) {
        this.keyword4 = keyword4;
    }

    /**
     * キーワード５取得<br>
     * <br>
     * キーワード５を戻り値で返却する<br>
     * <br>
     * @return keyword5
     */
    public String getKeyword5() {
        return keyword5;
    }

    /**
     * キーワード５設定<br>
     * <br>
     * キーワード５を引数で設定する<br>
     * <br>
     * @param keyword5
     */
    public void setKeyword5(String keyword5) {
        this.keyword5 = keyword5;
    }


    // 2014/04/22 Upd Start 検索条件部：希望日をテキストからプルダウンへ変更対応／検索条件部：コース名の追加対応
//    public String getSysDate() {
//        return sysDate;
//    }

//    public void setSysDate(String sysDate) {
//        this.sysDate = sysDate;
//    }

    /**
     * 希望日取得<br>
     * <br>
     * 希望日を戻り値で返却する<br>
     * <br>
     * @return hopeDt
     */
    public String getHopeDt() {
        return hopeDt;
    }

    /**
     * 希望日設定<br>
     * <br>
     * 希望日を引数で設定する<br>
     * <br>
     * @param hopeDt
     */
    public void setHopeDt(String hopeDt) {
        this.hopeDt = hopeDt;
    }

    /**
     * コース名(大分類)取得<br>
     * <br>
     * コース名(大分類)を戻り値で返却する<br>
     * <br>
     * @return searchBigClassificationCd
     */
    public String getSearchBigClassificationCd() {
        return searchBigClassificationCd;
    }

    /**
     * コース名(大分類)設定<br>
     * <br>
     * コース名(大分類)を引数で設定する<br>
     * <br>
     * @param searchBigClassificationCd
     */
    public void setSearchBigClassificationCd(String searchBigClassificationCd) {
        this.searchBigClassificationCd = searchBigClassificationCd;
    }

    /**
     * コース名(中分類)取得<br>
     * <br>
     * コース名(中分類)を戻り値で返却する<br>
     * <br>
     * @return searchMiddleClassificationCd
     */
    public String getSearchMiddleClassificationCd() {
        return searchMiddleClassificationCd;
    }

    /**
     * コース名(中分類)設定<br>
     * <br>
     * コース名(中分類)を引数で設定する<br>
     * <br>
     * @param searchMiddleClassificationCd
     */
    public void setSearchMiddleClassificationCd(String searchMiddleClassificationCd) {
        this.searchMiddleClassificationCd = searchMiddleClassificationCd;
    }

    /**
     * コース名(小分類)取得<br>
     * <br>
     * コース名(小分類)を戻り値で返却する<br>
     * <br>
     * @return searchSmallClassificationCd
     */
    public String getSearchSmallClassificationCd() {
        return searchSmallClassificationCd;
    }

    /**
     * コース名(小分類)設定<br>
     * <br>
     * コース名(小分類)を引数で設定する<br>
     * <br>
     * @param searchSmallClassificationCd
     */
    public void setSearchSmallClassificationCd(String searchSmallClassificationCd) {
        this.searchSmallClassificationCd = searchSmallClassificationCd;
    }

    /**
     * コース名(コース名⇒キーワード)取得<br>
     * <br>
     * コース名(コース名⇒キーワード)を戻り値で返却する<br>
     * <br>
     * @return searchKeyword
     */
    public String getSearchKeyword() {
        return searchKeyword;
    }

    /**
     * コース名(コース名⇒キーワード)設定<br>
     * <br>
     * コース名(コース名⇒キーワード)を引数で設定する<br>
     * <br>
     * @param searchKeyword
     */
    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }
    // 2014/04/22 Upd End   検索条件部：希望日をテキストからプルダウンへ変更対応／検索条件部：コース名の追加対応


    public List<CourseGoodsListDto> getCourseGoodsListDto() {
        return courseGoodsListDto;
    }

    public void setCourseGoodsListDto(List<CourseGoodsListDto> courseGoodsListDto) {
        this.courseGoodsListDto = courseGoodsListDto;
    }

}
