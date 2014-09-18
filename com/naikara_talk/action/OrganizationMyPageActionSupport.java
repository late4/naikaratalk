package com.naikara_talk.action;

import java.math.BigDecimal;
import java.util.LinkedHashMap;

import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.OrganizationMyPageModel;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>法人_初期処理<br>
 * <b>クラス名称       :</b>組織マイページActionスーパークラス。<br>
 * <b>クラス概要       :</b>組織責任者の情報を表示。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/01 TECS 新規作成
 */
public abstract class OrganizationMyPageActionSupport extends NaikaraActionSupport {

    private static final long serialVersionUID = 1L;

    // 画面表示のタイトル
    protected String title = "マイページ";

    // Help画面名
    protected String helpPageId = "HelpOrganizationMyPage.html";

    /**
     * 法人：スクールからのキャンペーン再取得<br>
     * 法人：スクールからのお知らせ再取得<br>
     * <
     * チェックエラーの場合、法人：スクールからのキャンペーン再取得。<br>
     * チェックエラーの場合、法人：スクールからのお知らせ再取得。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception なし
     */
    @Override
    public void validate() {

        // チェックエラーの場合、法人：スクールからのキャンペーンの再取得。
        // チェックエラーの場合、法人：スクールからのお知らせの再取得。
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
     * 法人：スクールからのキャンペーンを取得する。<br>
     * 法人：スクールからのお知らせを取得する。<br>
     * <br>
     * 法人：スクールからのキャンペーンを取得する。<br>
     * 法人：スクールからのお知らせを取得する。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    public void initRadio() throws Exception {

        CodeManagMstCache cache = CodeManagMstCache.getInstance();

        LinkedHashMap<String, CodeManagMstDto> actNoticeList = cache
                .getList(NaikaraTalkConstants.CODE_CATEGORY_CAMPAIGN_FROM_SCOOL_TO_CORP);

        this.actNotice = actNoticeList.get(NaikaraTalkConstants.CAMPAIGN_FROM_SCOOL_TO_CORP).getManagerNm();

        LinkedHashMap<String, CodeManagMstDto> noticeList = cache
                .getList(NaikaraTalkConstants.CODE_CATEGORY_NEWS_FROM_SCOOL_TO_CORP);

        this.notice = noticeList.get(NaikaraTalkConstants.NEWS_FROM_SCOOL_TO_CORP).getManagerNm();

    }

    /** メッセージ */
    protected String message;

    /** 組織名(jsp) */
    protected String organizationJnm;

    /** 組織ID(jsp) */
    protected String organizationId;

    /** 責任者名(姓名)(jsp) */
    protected String managJnm;

    /** メールアドレス(jsp) */
    protected String managMailAddress1;

    /** 契約期間：開始日(jsp) */
    protected String contractStrDt;

    /** 契約期間：終了日(jsp) */
    protected String contractEndDt;

    /** 検索結果 */
    protected OrganizationMyPageModel model = new OrganizationMyPageModel();

    /** チェックエラー場合、検索判断フラグ */
    protected String hasSearchFlg;

    /** キャンペーンのお知らせ (jsp) */
    protected String actNotice;

    /** お知らせ (jsp) */
    protected String notice;

    /** 連番 (jsp) */
    protected int consSeq;

    /** ポイント残高 (jsp) */
    protected String balancePoint;

    /** 今月の利用ポイント (jsp) */
    protected String usePoint;

    /** 責任者性別区分 (jsp) */
    protected String managGenderKbn;

    /** 契約ポイント (jsp) */
    protected BigDecimal tempPointNum;

    /** 商品購入 有償利用ポイント */
    protected BigDecimal paymentUsePoint;

    /** レッスン予実 有償利用ポイント */
    protected BigDecimal LesPaymentUsePoint;

    /** 権限 */
    protected String role;

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            セットする title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return helpPageId
     */
    public String getHelpPageId() {
        return helpPageId;
    }

    /**
     * @param helpPageId
     *            セットする helpPageId
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
     * @param message
     *            セットする message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return model
     */
    public OrganizationMyPageModel getModel() {
        return model;
    }

    /**
     * @param model
     *            セットする model
     */
    public void setModel(OrganizationMyPageModel model) {
        this.model = model;
    }

    /**
     * @return organizationJnm
     */
    public String getOrganizationJnm() {
        return organizationJnm;
    }

    /**
     * @param organizationJnm
     *            セットする organizationJnm
     */
    public void setOrganizationJnm(String organizationJnm) {
        this.organizationJnm = organizationJnm;
    }

    /**
     * @return organizationId
     */
    public String getOrganizationId() {
        return organizationId;
    }

    /**
     * @param organizationId
     *            セットする organizationId
     */
    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    /**
     * @return managJnm
     */
    public String getManagJnm() {
        return managJnm;
    }

    /**
     * @param managJnm
     *            セットする managJnm
     */
    public void setManagJnm(String managJnm) {
        this.managJnm = managJnm;
    }

    /**
     * @return managMailAddress1
     */
    public String getManagMailAddress1() {
        return managMailAddress1;
    }

    /**
     * @param managMailAddress1
     *            セットする managMailAddress1
     */
    public void setManagMailAddress1(String managMailAddress1) {
        this.managMailAddress1 = managMailAddress1;
    }

    /**
     * @return contractStrDt
     */
    public String getContractStrDt() {
        return contractStrDt;
    }

    /**
     * @param contractStrDt
     *            セットする contractStrDt
     */
    public void setContractStrDt(String contractStrDt) {
        this.contractStrDt = contractStrDt;
    }

    /**
     * @return contractEndDt
     */
    public String getContractEndDt() {
        return contractEndDt;
    }

    /**
     * @param contractEndDt
     *            セットする contractEndDt
     */
    public void setContractEndDt(String contractEndDt) {
        this.contractEndDt = contractEndDt;
    }

    /**
     * @return hasSearchFlg
     */
    public String getHasSearchFlg() {
        return hasSearchFlg;
    }

    /**
     * @param hasSearchFlg
     *            セットする hasSearchFlg
     */
    public void setHasSearchFlg(String hasSearchFlg) {
        this.hasSearchFlg = hasSearchFlg;
    }

    /**
     * @return actNotice
     */
    public String getActNotice() {
        return actNotice;
    }

    /**
     * @param actNotice
     *            セットする actNotice
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
     * @param notice
     *            セットする notice
     */
    public void setNotice(String notice) {
        this.notice = notice;
    }

    /**
     * @return consSeq
     */
    public int getConsSeq() {
        return consSeq;
    }

    /**
     * @param consSeq
     *            セットする consSeq
     */
    public void setConsSeq(int consSeq) {
        this.consSeq = consSeq;
    }

    /**
     * @return balancePoint
     */
    public String getBalancePoint() {
        return balancePoint;
    }

    /**
     * @param balancePoint
     *            セットする balancePoint
     */
    public void setBalancePoint(String balancePoint) {
        this.balancePoint = balancePoint;
    }

    /**
     * @return usePoint
     */
    public String getUsePoint() {
        return usePoint;
    }

    /**
     * @param usePoint
     *            セットする usePoint
     */
    public void setUsePoint(String usePoint) {
        this.usePoint = usePoint;
    }

    /**
     * @return managGenderKbn
     */
    public String getManagGenderKbn() {
        return managGenderKbn;
    }

    /**
     * @param managGenderKbnNm
     *            セットする managGenderKbn
     */
    public void setManagGenderKbn(String managGenderKbn) {
        this.managGenderKbn = managGenderKbn;
    }

    /**
     * @return tempPointNum
     */
    public BigDecimal getTempPointNum() {
        return tempPointNum;
    }

    /**
     * @param tempPointNum
     *            セットする tempPointNum
     */
    public void setTempPointNum(BigDecimal tempPointNum) {
        this.tempPointNum = tempPointNum;
    }

    /**
     * @return paymentUsePoint
     */
    public BigDecimal getPaymentUsePoint() {
        return paymentUsePoint;
    }

    /**
     * @param paymentUsePoint
     *            セットする paymentUsePoint
     */
    public void setPaymentUsePoint(BigDecimal paymentUsePoint) {
        this.paymentUsePoint = paymentUsePoint;
    }

    /**
     * @return LesPaymentUsePoint
     */
    public BigDecimal getLesPaymentUsePoint() {
        return LesPaymentUsePoint;
    }

    /**
     * @param LesPaymentUsePoint
     *            セットする LesPaymentUsePoint
     */
    public void setLesPaymentUsePoint(BigDecimal lesPaymentUsePoint) {
        LesPaymentUsePoint = lesPaymentUsePoint;
    }

    /**
     * @return role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role
     *            セットする role
     */
    public void setRole(String role) {
        this.role = role;
    }

}
