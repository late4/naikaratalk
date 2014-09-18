package com.naikara_talk.action;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.LinkedHashMap;

import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.StudentMyPageModel;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_初期処理<br>
 * <b>クラス名称       :</b>受講者用マイページActionスーパークラス。<br>
 * <b>クラス概要       :</b>受講者の情報を表示。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/16 TECS 新規作成
 */
public abstract class StudentMyPageActionSupport extends NaikaraActionSupport {

    private static final long serialVersionUID = 1L;

    // Help画面名
    protected String helpPageId = "HelpStudentMyPage.html";

    /**
     * 個人：スクールからのキャンペーン再取得<br>
     * 個人：スクールからのお知らせ再取得<br>
     * <
     * チェックエラーの場合、個人：スクールからのキャンペーン再取得。<br>
     * チェックエラーの場合、個人：スクールからのお知らせ再取得。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception なし
     */
    @Override
    public void validate() {

        // チェックエラーの場合、個人：スクールからのキャンペーンの再取得。
        // チェックエラーの場合、個人：スクールからのお知らせの再取得。
        try {
            initRadio();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * サービスの呼び出しの実装。
     */
    abstract protected String requestService() throws NaiException;

    /**
     * 個人：スクールからのキャンペーンを取得する。<br>
     * 個人：スクールからのお知らせを取得する。<br>
     * <br>
     * 個人：スクールからのキャンペーンを取得する。<br>
     * 個人：スクールからのお知らせを取得する。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    public void initRadio() throws Exception {

        CodeManagMstCache cache = CodeManagMstCache.getInstance();

        LinkedHashMap<String, CodeManagMstDto> actNoticeList = cache
                .getList(NaikaraTalkConstants.CODE_CATEGORY_CAMPAIGN_FROM_SCOOL_TO_PERS);

        this.actNotice = actNoticeList.get(NaikaraTalkConstants.CAMPAIGN_FROM_SCOOL_TO_PERS).getManagerNm();

        LinkedHashMap<String, CodeManagMstDto> noticeList = cache
                .getList(NaikaraTalkConstants.CODE_CATEGORY_NEWS_FROM_SCOOL_TO_PERS);

        this.notice = noticeList.get(NaikaraTalkConstants.NEWS_FROM_SCOOL_TO_PERS).getManagerNm();

    }

    /** メッセージ */
    protected String message;

    /** 受講者ID(jsp) */
    protected String studentId;

    /** ニックネーム(jsp) */
    protected String nickNm;

    /** キャンペーンのお知らせ (jsp) */
    protected String actNotice;

    /** お知らせ (jsp) */
    protected String notice;

    /** メールアドレス (jsp) */
    protected String mailAddress;

    /** ポイント残高合計 (jsp) */
    protected BigDecimal balancePoint;

    /** 検索結果 */
    protected StudentMyPageModel model = new StudentMyPageModel();

    /** 商品コード */
    protected String goodsCd;

    /** スクールからのコメント */
    protected String schoolCmt;

    /** 性別区分 */
    protected String genderKbn;

    /** 成人区別 */
    protected int adult;

    /** 生年月日 */
    protected String birthMd;

    /** ポイント購入済フラグ */
    protected String pointPurchaseFlg;

    /** 保護者の同意書の入手フラグ */

    protected String consentDocumentAcquisitionFlg;

    /** 顧客区分 */
    protected String customerKbn;

    /** 商品ファイル名 */
    protected String goodsFileNm;

    /** 商品ファイル */
    protected Blob goodsFile;

    /** 遷移元 */
    protected String frontPageId;

    /** 予約No */
    protected String reservationNo;

    /**
     * @return helpPageId
     */
    public String getHelpPageId() {
        return helpPageId;
    }

    /**
     * @param helpPageId セットする helpPageId
     */
    public void setHelpPageId(String helpPageId) {
        this.helpPageId = helpPageId;
    }

    /**
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message セットする message
     */
    public void setMessage(String message) {
        this.message = message;
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
     * @return actNotice
     */
    public String getActNotice() {
        return actNotice;
    }

    /**
     * @param actNotice セットする actNotice
     */
    public void setActNotice(String actNotice) {
        this.actNotice = actNotice;
    }

    /**
     * @return notice
     */
    public String getNotice() {
        return notice;
    }

    /**
     * @param notice セットする notice
     */
    public void setNotice(String notice) {
        this.notice = notice;
    }

    /**
     * @return model
     */
    public StudentMyPageModel getModel() {
        return model;
    }

    /**
     * @param model セットする model
     */
    public void setModel(StudentMyPageModel model) {
        this.model = model;
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

    /**
     * @return frontPageId
     */
    public String getFrontPageId() {
        return frontPageId;
    }

    /**
     * @param frontPageId セットする frontPageId
     */
    public void setFrontPageId(String frontPageId) {
        this.frontPageId = frontPageId;
    }

    /**
     * @return reservationNo
     */
    public String getReservationNo() {
        return reservationNo;
    }

    /**
     * @param reservationNo セットする reservationNo
     */
    public void setReservationNo(String reservationNo) {
        this.reservationNo = reservationNo;
    }

}
