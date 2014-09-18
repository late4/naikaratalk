package com.naikara_talk.action;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dto.TeacherRateMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.TeacherRateMstModel;
import com.naikara_talk.service.TeacherRateMstLoadService;
import com.naikara_talk.sessiondata.SessionUserMstTeacherMst;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>講師支払比率の設定<br>
 * <b>クラス概要       :</b>講師支払比率の設定共通Action<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/26 TECS 新規作成
 *                          2013/11/14 TECS 要望対応 No1022-6(講師支払比率99⇒99.999)
 */
public abstract class TeacherRateMstActionSupport extends NaikaraActionSupport {

    private static final long serialVersionUID = 1L;

    // 画面表示のタイトル
    protected String title = "講師支払比率の設定";

    // Help画面名
    protected String helpPageId = "HelpTeacherRateMst.html";

    /**
     * 支払サイクル、源泉税有無区分の再取得<br>
     * <br>
     * チェックエラーの場合、支払サイクル、源泉税の再取得。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception なし
     */
    @Override
    public void validate() {

        // チェックエラーの支払サイクル、源泉税有無区分の再取得。
        try {
            initRadio();

            this.model.setResultList(((SessionUserMstTeacherMst) SessionDataUtil
                    .getSessionData(SessionUserMstTeacherMst.class.toString())).getTempResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * サービスの呼び出しの実装。
     */
    abstract protected String requestService() throws NaiException;

    /**
     * 支払サイクル、源泉税有無区分を取得する。<br>
     * <br>
     * 支払サイクル、源泉税有無区分を取得する。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    public void initRadio() throws Exception {

        TeacherRateMstLoadService service = new TeacherRateMstLoadService();
        // 支払サイクルを取得する
        this.paymentCycle_sell = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_PAYMENT_CYCLE);
        // 源泉税有無区分を取得する
        this.source_rdll = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_SOURCE_WHETHER);
    }

    /**
     * 画面のパラメータをモデルにセット。<br>
     * <br>
     * 画面のパラメータをモデルにセット。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception なし
     */
    public void setupModel() {
        // 利用者ID
        this.model.setUserId(this.userId);
        // 名前(姓)
        this.model.setFamilyJnm(this.familyJnm);
        // 名前(名)
        this.model.setFirstJnm(this.firstJnm);
        // 契約開始日
        this.model.setContractStartDt(NaikaraStringUtil.converToYYYYMMDD(this.contractStartDt));
        // 契約終了日
        this.model.setContractEndDt(NaikaraStringUtil.converToYYYYMMDD(this.contractEndDt));
        // 処理判定
        this.model.setProcessDifference(this.processDifference);
        // 修正No
        this.model.setNumberNo(this.numberNo);
        // 適用期間：開始日
        this.model.setStartDt(this.buaiStart_txt);
        // 適用期間：終了日
        this.model.setEndDt(this.buaiEnd_txt);

        // 支払比率
        String buaiRitsuTxt = StringUtils.isEmpty(this.buaiRitsu_txt) ? "0" : this.buaiRitsu_txt;

        //No1022-6 講師支払比率99⇒99.999へ変更対応-Start
        //this.model.setPaymentRate(Integer.parseInt(buaiRitsuTxt));
        this.model.setPaymentRate(buaiRitsuTxt);
        //No1022-6 講師支払比率99⇒99.999へ変更対応-End


        // 支払サイクルコード
        this.model.setPaymentCycleCd(this.paymentCycle_sel);
        // 源泉税有無区分
        this.model.setWithholdingTaxKbn(this.source_rdl);

        // レコードバージョン番号
        String recVerNo = StringUtils.isEmpty(this.recordVerNo) ? "0" : this.recordVerNo;
        this.model.setRecordVerNo(Integer.parseInt(recVerNo));
    }

    /** 利用者ID */
    protected String userId;

    /** 名前(姓) */
    protected String familyJnm;

    /** 名前(名) */
    protected String firstJnm;

    /** 契約開始日 */
    protected String contractStartDt;

    /** 契約終了日 */
    protected String contractEndDt;

    /** 処理判定 */
    protected String processDifference;

    /** 検索結果一覧 */
    protected List<TeacherRateMstDto> resultList;

    /** 一覧部の「修正」 */
    protected String select_rdl;

    /** 一覧部の「削除」 */
    protected List<String> startDtList;

    /** 修正No */
    protected String numberNo;

    /** 適用期間：開始日 */
    protected String buaiStart_txt;

    /** 適用期間：終了日 */
    protected String buaiEnd_txt;

    /** 支払比率 */
    protected String buaiRitsu_txt;

    /** 修正チェック */
    protected String isUpdateChk;

    /** 支払サイクルコード */
    protected String paymentCycle_sel;
    protected Map<String, String> paymentCycle_sell = new LinkedHashMap<String, String>();

    /** 源泉税有無区分 */
    protected String source_rdl;
    protected Map<String, String> source_rdll = new LinkedHashMap<String, String>();

    /** レコードバージョン番号 */
    protected String recordVerNo;

    /** 検索結果 */
    protected TeacherRateMstModel model = new TeacherRateMstModel();

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
     * @return userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId セットする userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return familyJnm
     */
    public String getFamilyJnm() {
        return familyJnm;
    }

    /**
     * @param familyJnm セットする familyJnm
     */
    public void setFamilyJnm(String familyJnm) {
        this.familyJnm = familyJnm;
    }

    /**
     * @return firstJnm
     */
    public String getFirstJnm() {
        return firstJnm;
    }

    /**
     * @param firstJnm セットする firstJnm
     */
    public void setFirstJnm(String firstJnm) {
        this.firstJnm = firstJnm;
    }

    /**
     * @return contractStartDt
     */
    public String getContractStartDt() {
        return contractStartDt;
    }

    /**
     * @param contractStartDt セットする contractStartDt
     */
    public void setContractStartDt(String contractStartDt) {
        this.contractStartDt = contractStartDt;
    }

    /**
     * @return contractEndDt
     */
    public String getContractEndDt() {
        return contractEndDt;
    }

    /**
     * @param contractEndDt セットする contractEndDt
     */
    public void setContractEndDt(String contractEndDt) {
        this.contractEndDt = contractEndDt;
    }

    /**
     * @return processDifference
     */
    public String getProcessDifference() {
        return processDifference;
    }

    /**
     * @param processDifference セットする processDifference
     */
    public void setProcessDifference(String processDifference) {
        this.processDifference = processDifference;
    }

    /**
     * @return resultList
     */
    public List<TeacherRateMstDto> getResultList() {
        return resultList;
    }

    /**
     * @param resultList セットする resultList
     */
    public void setResultList(List<TeacherRateMstDto> resultList) {
        this.resultList = resultList;
    }

    /**
     * @return select_rdl
     */
    public String getSelect_rdl() {
        return select_rdl;
    }

    /**
     * @param select_rdl セットする select_rdl
     */
    public void setSelect_rdl(String select_rdl) {
        this.select_rdl = select_rdl;
    }

    /**
     * @return startDtList
     */
    public List<String> getStartDtList() {
        return startDtList;
    }

    /**
     * @param startDtList セットする startDtList
     */
    public void setStartDtList(List<String> startDtList) {
        this.startDtList = startDtList;
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
     * @return buaiStart_txt
     */
    public String getBuaiStart_txt() {
        return buaiStart_txt;
    }

    /**
     * @param buaiStart_txt セットする buaiStart_txt
     */
    public void setBuaiStart_txt(String buaiStart_txt) {
        this.buaiStart_txt = buaiStart_txt;
    }

    /**
     * @return buaiEnd_txt
     */
    public String getBuaiEnd_txt() {
        return buaiEnd_txt;
    }

    /**
     * @param buaiEnd_txt セットする buaiEnd_txt
     */
    public void setBuaiEnd_txt(String buaiEnd_txt) {
        this.buaiEnd_txt = buaiEnd_txt;
    }

    /**
     * @return buaiRitsu_txt
     */
    public String getBuaiRitsu_txt() {
        return buaiRitsu_txt;
    }

    /**
     * @param buaiRitsu_txt セットする buaiRitsu_txt
     */
    public void setBuaiRitsu_txt(String buaiRitsu_txt) {
        this.buaiRitsu_txt = buaiRitsu_txt;
    }

    /**
     * @return isUpdateChk
     */
    public String getIsUpdateChk() {
        return isUpdateChk;
    }

    /**
     * @param isUpdateChk セットする isUpdateChk
     */
    public void setIsUpdateChk(String isUpdateChk) {
        this.isUpdateChk = isUpdateChk;
    }

    /**
     * @return paymentCycle_sel
     */
    public String getPaymentCycle_sel() {
        return paymentCycle_sel;
    }

    /**
     * @param paymentCycle_sel セットする paymentCycle_sel
     */
    public void setPaymentCycle_sel(String paymentCycle_sel) {
        this.paymentCycle_sel = paymentCycle_sel;
    }

    /**
     * @return paymentCycle_sell
     */
    public Map<String, String> getPaymentCycle_sell() {
        return paymentCycle_sell;
    }

    /**
     * @param paymentCycle_sell セットする paymentCycle_sell
     */
    public void setPaymentCycle_sell(Map<String, String> paymentCycle_sell) {
        this.paymentCycle_sell = paymentCycle_sell;
    }

    /**
     * @return source_rdl
     */
    public String getSource_rdl() {
        return source_rdl;
    }

    /**
     * @param source_rdl セットする source_rdl
     */
    public void setSource_rdl(String source_rdl) {
        this.source_rdl = source_rdl;
    }

    /**
     * @return source_rdll
     */
    public Map<String, String> getSource_rdll() {
        return source_rdll;
    }

    /**
     * @param source_rdll セットする source_rdll
     */
    public void setSource_rdll(Map<String, String> source_rdll) {
        this.source_rdll = source_rdll;
    }

    /**
     * @return recordVerNo
     */
    public String getRecordVerNo() {
        return recordVerNo;
    }

    /**
     * @param recordVerNo セットする recordVerNo
     */
    public void setRecordVerNo(String recordVerNo) {
        this.recordVerNo = recordVerNo;
    }

    /**
     * @return model
     */
    public TeacherRateMstModel getModel() {
        return model;
    }

    /**
     * @param model セットする model
     */
    public void setModel(TeacherRateMstModel model) {
        this.model = model;
    }

}
