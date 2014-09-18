package com.naikara_talk.dto;

import java.math.BigDecimal;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>支払単価表 DTOクラス<br>
 * <b>クラス名称　　　:</b>支払単価表クラス<br>
 * <b>クラス概要　　　:</b>支払単価表DTO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/17 TECS 新規作成
 */
public class TeaCouMTeaRateMCouUsePoiMDto extends AbstractDto {

    // 講師別コースマスタ
    private String userId; //講師ID (利用者ID)
    private String courseCd; //コースコード

    // コースマスタ
    private String bigClassificationCd; //大分類コード
    private String middleClassificationCd; //中分類コード
    private String smallClassificationCd; //小分類コード
    private String courseJnm; //コース名
    private String courseEnm; //コース名(英語名)

    // 講師別支払比率マスタ
    private BigDecimal mulUppr; //支払比率

    /**
     * 講師ID (利用者ID)取得<br>
     * <br>
     * 講師ID (利用者ID)を戻り値で返却する<br>
     * <br>
     * @return userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 講師ID (利用者ID)設定<br>
     * <br>
     * 講師ID (利用者ID)を引数で設定する<br>
     * <br>
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

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
     * コース名(英語名)取得<br>
     * <br>
     * コース名(英語名)を戻り値で返却する<br>
     * <br>
     * @return courseEnm
     */
    public String getCourseEnm() {
        return courseEnm;
    }

    /**
     * コース名(英語名)設定<br>
     * <br>
     * コース名(英語名)を引数で設定する<br>
     * <br>
     * @param courseEnm
     */
    public void setCourseEnm(String courseEnm) {
        this.courseEnm = courseEnm;
    }

    /**
     * @return mulUppr
     */
    public BigDecimal getMulUppr() {
        return mulUppr;
    }

    /**
     * @param mulUppr セットする mulUppr
     */
    public void setMulUppr(BigDecimal mulUppr) {
        this.mulUppr = mulUppr;
    }

}