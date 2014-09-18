package com.naikara_talk.model;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.List;

import com.naikara_talk.dto.DmHistoryDetailsTrnDto;
import com.naikara_talk.dto.GoodsPurchaseListDto;
import com.naikara_talk.dto.LessonReservationPerformanceTrnDto;
import com.naikara_talk.dto.PointOwnershipTrnDto;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様(個人)_初期処理<br>
 * <b>クラス名称　　　:</b>受講者用マイページModelクラス。<br>
 * <b>クラス概要　　　:</b>受講者の情報を表示。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/16 TECS 新規作成。
 */
public class StudentMyPageModel implements Model {

    private static final long serialVersionUID = 1L;

    /** 受講者ID(jsp) */
    private String studentId;

    /** レッスン検索結果一覧 */
    private List<LessonReservationPerformanceTrnDto> lessonResultList;

    /** 商品検索結果一覧 */
    private List<GoodsPurchaseListDto> goodResultList;

    /** レッスン予実検索結果一覧 */
    private List<LessonReservationPerformanceTrnDto> lesResultList;

    /** レッスン予実検索結果一覧 */
    private List<DmHistoryDetailsTrnDto> dmResultList;

    /** スクールからのコメント */
    private String schoolCmt;

    /** ポイント所有検索結果一覧 */
    private List<PointOwnershipTrnDto> pointResultList;

    /** メールアドレス (jsp) */
    private String mailAddress;

    /** ポイント残高合計 (jsp) */
    private BigDecimal balancePoint;

    /** 性別区分 */
    private String genderKbn;

    /** 成人区分 */
    private int adult;

    /** 生年月日 */
    private String birthMd;

    /** ポイント購入済フラグ */
    private String pointPurchaseFlg;

    /** 保護者の同意書の入手フラグ */
    private String consentDocumentAcquisitionFlg;

    /** 顧客区分 */
    private String customerKbn;

    /** 商品コード */
    private String goodsCd;

    /** 商品ファイル名 */
    private String goodsFileNm;

    /** 商品ファイル */
    private Blob goodsFile;

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
     * @return lessonResultList
     */
    public List<LessonReservationPerformanceTrnDto> getLessonResultList() {
        return lessonResultList;
    }

    /**
     * @param lessonResultList セットする lessonResultList
     */
    public void setLessonResultList(List<LessonReservationPerformanceTrnDto> lessonResultList) {
        this.lessonResultList = lessonResultList;
    }

    /**
     * @return goodResultList
     */
    public List<GoodsPurchaseListDto> getGoodResultList() {
        return goodResultList;
    }

    /**
     * @param goodResultList セットする goodResultList
     */
    public void setGoodResultList(List<GoodsPurchaseListDto> goodResultList) {
        this.goodResultList = goodResultList;
    }

    /**
     * @return lesResultList
     */
    public List<LessonReservationPerformanceTrnDto> getLesResultList() {
        return lesResultList;
    }

    /**
     * @param lesResultList セットする lesResultList
     */
    public void setLesResultList(List<LessonReservationPerformanceTrnDto> lesResultList) {
        this.lesResultList = lesResultList;
    }

    /**
     * @return schoolCmt
     */
    public String getSchoolCmt() {
        return schoolCmt;
    }

    /**
     * @param schoolCmt セットする schoolCmt
     */
    public void setSchoolCmt(String schoolCmt) {
        this.schoolCmt = schoolCmt;
    }

    /**
     * @return dmResultList
     */
    public List<DmHistoryDetailsTrnDto> getDmResultList() {
        return dmResultList;
    }

    /**
     * @param dmResultList セットする dmResultList
     */
    public void setDmResultList(List<DmHistoryDetailsTrnDto> dmResultList) {
        this.dmResultList = dmResultList;
    }

    /**
     * @return pointResultList
     */
    public List<PointOwnershipTrnDto> getPointResultList() {
        return pointResultList;
    }

    /**
     * @param pointResultList セットする pointResultList
     */
    public void setPointResultList(List<PointOwnershipTrnDto> pointResultList) {
        this.pointResultList = pointResultList;
    }

    /**
     * @return mailAddress
     */
    public String getMailAddress() {
        return mailAddress;
    }

    /**
     * @param mailAddress セットする mailAddress
     */
    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    /**
     * @return balancePoint
     */
    public BigDecimal getBalancePoint() {
        return balancePoint;
    }

    /**
     * @param balancePoint セットする balancePoint
     */
    public void setBalancePoint(BigDecimal balancePoint) {
        this.balancePoint = balancePoint;
    }

    /**
     * @return genderKbn
     */
    public String getGenderKbn() {
        return genderKbn;
    }

    /**
     * @param genderKbn セットする genderKbn
     */
    public void setGenderKbn(String genderKbn) {
        this.genderKbn = genderKbn;
    }

    /**
     * @return adult
     */
    public int getAdult() {
        return adult;
    }

    /**
     * @param adult セットする adult
     */
    public void setAdult(int adult) {
        this.adult = adult;
    }

    /**
     * @return birthMd
     */
    public String getBirthMd() {
        return birthMd;
    }

    /**
     * @param birthMd セットする birthMd
     */
    public void setBirthMd(String birthMd) {
        this.birthMd = birthMd;
    }

    /**
     * @return pointPurchaseFlg
     */
    public String getPointPurchaseFlg() {
        return pointPurchaseFlg;
    }

    /**
     * @param pointPurchaseFlg セットする pointPurchaseFlg
     */
    public void setPointPurchaseFlg(String pointPurchaseFlg) {
        this.pointPurchaseFlg = pointPurchaseFlg;
    }

    /**
     * @return consentDocumentAcquisitionFlg
     */
    public String getConsentDocumentAcquisitionFlg() {
        return consentDocumentAcquisitionFlg;
    }

    /**
     * @param consentDocumentAcquisitionFlg セットする consentDocumentAcquisitionFlg
     */
    public void setConsentDocumentAcquisitionFlg(String consentDocumentAcquisitionFlg) {
        this.consentDocumentAcquisitionFlg = consentDocumentAcquisitionFlg;
    }

    /**
     * @return customerKbn
     */
    public String getCustomerKbn() {
        return customerKbn;
    }

    /**
     * @param customerKbn セットする customerKbn
     */
    public void setCustomerKbn(String customerKbn) {
        this.customerKbn = customerKbn;
    }

    /**
     * @return goodsCd
     */
    public String getGoodsCd() {
        return goodsCd;
    }

    /**
     * @param goodsCd セットする goodsCd
     */
    public void setGoodsCd(String goodsCd) {
        this.goodsCd = goodsCd;
    }

    /**
     * @return goodsFileNm
     */
    public String getGoodsFileNm() {
        return goodsFileNm;
    }

    /**
     * @param goodsFileNm セットする goodsFileNm
     */
    public void setGoodsFileNm(String goodsFileNm) {
        this.goodsFileNm = goodsFileNm;
    }

    /**
     * @return goodsFile
     */
    public Blob getGoodsFile() {
        return goodsFile;
    }

    /**
     * @param goodsFile セットする goodsFile
     */
    public void setGoodsFile(Blob goodsFile) {
        this.goodsFile = goodsFile;
    }

}
