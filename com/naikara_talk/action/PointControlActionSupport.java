package com.naikara_talk.action;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.PointControlModel;
import com.naikara_talk.service.PointControlLoadService;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>ポイント管理マスタメンテナンス(単票)<br>
 * <b>クラス概要       :</b>ポイント管理マスタメンテナンス(単票)共通Action<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/01 TECS 新規作成
 */
public abstract class PointControlActionSupport extends NaikaraActionSupport {

    private static final long serialVersionUID = 1L;

    // 画面表示のタイトル
    protected String title = "ポイント管理マスタメンテナンス";

    // Help画面名
    protected String helpPageId = "HelpPointControl.html";

    /**
     * 通常月謝区分の再取得<br>
     * <br>
     * チェックエラーの場合、通常月謝区分の再取得。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception なし
     */
    @Override
    public void validate() {

        // チェックエラーの場合、通常月謝区分の再取得。
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
     * 通常月謝区分を取得する。<br>
     * <br>
     * 通常月謝区分を取得する。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    public void initRadio() throws Exception {

        PointControlLoadService service = new PointControlLoadService();
        // 通常月謝区分を取得する
        this.monthlyKbn_rdll = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_FEE_KBN);
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

        // ポイントコード
        this.model.setPointCd(this.pointCode_txt);
        // 通常月謝区分
        this.model.setFeeKbn(this.monthlyKbn_rdl);
        // 金額(税込)
        this.model.setMoneyYen(new BigDecimal(NaikaraStringUtil.delComma(StringUtils.isEmpty(this.payPoint_txt) ? "0"
                : this.payPoint_txt)));
        // 有償ポイント
        this.model.setPaymentPoint(new BigDecimal(
                NaikaraStringUtil.delComma(StringUtils.isEmpty(this.payPoint_txt) ? "0" : this.payPoint_txt)));
        // 有償ポイント期限
        this.model
                .setPaymentPointTim(Integer.valueOf(NaikaraStringUtil.delComma(StringUtils.isEmpty(this.payTime_txt) ? "0"
                        : this.payTime_txt)));
        // 無償ポイント
        this.model.setFreePoint(new BigDecimal(
                NaikaraStringUtil.delComma(StringUtils.isEmpty(this.notpayPoint_txt) ? "0" : this.notpayPoint_txt)));
        // 無償ポイント期限
        this.model
                .setFreePointTim(Integer.valueOf(NaikaraStringUtil.delComma(StringUtils.isEmpty(this.notpayTime_txt) ? "0"
                        : this.notpayTime_txt)));
        // 提供期間開始日
        this.model.setUseStrDt(this.utilizationStart_txt);
        // 提供期間終了日
        this.model.setUseEndDt(this.utilizationEnd_txt);
        // 備考
        this.model.setRemarks(this.remarks_txa);

        // 処理区分(前画面の引き継ぎ情報)
        this.model.setProcessKbn_Rdl(this.processKbn_rdl);
        // 画面表示処理区分
        this.model.setProcessKbn_Txt(this.processKbn_txt);

        // レコードバージョン番号
        String recVerNo = StringUtils.isEmpty(this.recordVerNo) ? "0" : this.recordVerNo;
        this.model.setRecordVerNo(Integer.parseInt(recVerNo));

    }

    /** メッセージ */
    protected String message;

    /** 処理区分(前画面の引き継ぎ情報) */
    protected String processKbn_rdl;

    /** 画面表示処理区分 */
    protected String processKbn_txt;

    /** 説明コメント */
    protected String comment;

    /** ポイントコード */
    protected String pointCode_txt;

    /** 通常月謝区分 */
    protected String monthlyKbn_rdl;
    protected Map<String, String> monthlyKbn_rdll = new LinkedHashMap<String, String>();

    /** 金額(税込)有償ポイント  */
    protected String payPoint_txt;

    /** 有償ポイント期限 */
    protected String payTime_txt;

    /** 無償ポイント */
    protected String notpayPoint_txt;

    /** 無償ポイント期限 */
    protected String notpayTime_txt;

    /** 提供期間開始日 */
    protected String utilizationStart_txt;

    /** 提供期間終了日 */
    protected String utilizationEnd_txt;

    /** 備考 */
    protected String remarks_txa;

    /** 排他用レコードバージョン */
    protected String recordVerNo;

    /** 処理結果 */
    protected PointControlModel model = new PointControlModel();

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
     * @return processKbn_rdl
     */
    public String getProcessKbn_rdl() {
        return processKbn_rdl;
    }

    /**
     * @param processKbn_rdl
     *            セットする processKbn_rdl
     */
    public void setProcessKbn_rdl(String processKbn_rdl) {
        this.processKbn_rdl = processKbn_rdl;
    }

    /**
     * @return processKbn_txt
     */
    public String getProcessKbn_txt() {
        return processKbn_txt;
    }

    /**
     * @param processKbn_txt
     *            セットする processKbn_txt
     */
    public void setProcessKbn_txt(String processKbn_txt) {
        this.processKbn_txt = processKbn_txt;
    }

    /**
     * @return comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment
     *            セットする comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return pointCode_txt
     */
    public String getPointCode_txt() {
        return pointCode_txt;
    }

    /**
     * @param pointCode_txt
     *            セットする pointCode_txt
     */
    public void setPointCode_txt(String pointCode_txt) {
        this.pointCode_txt = pointCode_txt;
    }

    /**
     * @return monthlyKbn_rdl
     */
    public String getMonthlyKbn_rdl() {
        return monthlyKbn_rdl;
    }

    /**
     * @param monthlyKbn_rdl
     *            セットする monthlyKbn_rdl
     */
    public void setMonthlyKbn_rdl(String monthlyKbn_rdl) {
        this.monthlyKbn_rdl = monthlyKbn_rdl;
    }

    /**
     * @return monthlyKbn_rdll
     */
    public Map<String, String> getMonthlyKbn_rdll() {
        return monthlyKbn_rdll;
    }

    /**
     * @param monthlyKbn_rdll
     *            セットする monthlyKbn_rdll
     */
    public void setMonthlyKbn_rdll(Map<String, String> monthlyKbn_rdll) {
        this.monthlyKbn_rdll = monthlyKbn_rdll;
    }

    /**
     * @return payPoint_txt
     */
    public String getPayPoint_txt() {
        return payPoint_txt;
    }

    /**
     * @param payPoint_txt
     *            セットする payPoint_txt
     */
    public void setPayPoint_txt(String payPoint_txt) {
        this.payPoint_txt = payPoint_txt;
    }

    /**
     * @return payTime_txt
     */
    public String getPayTime_txt() {
        return payTime_txt;
    }

    /**
     * @param payTime_txt
     *            セットする payTime_txt
     */
    public void setPayTime_txt(String payTime_txt) {
        this.payTime_txt = payTime_txt;
    }

    /**
     * @return notpayPoint_txt
     */
    public String getNotpayPoint_txt() {
        return notpayPoint_txt;
    }

    /**
     * @param notpayPoint_txt
     *            セットする notpayPoint_txt
     */
    public void setNotpayPoint_txt(String notpayPoint_txt) {
        this.notpayPoint_txt = notpayPoint_txt;
    }

    /**
     * @return notpayTime_txt
     */
    public String getNotpayTime_txt() {
        return notpayTime_txt;
    }

    /**
     * @param notpayTime_txt
     *            セットする notpayTime_txt
     */
    public void setNotpayTime_txt(String notpayTime_txt) {
        this.notpayTime_txt = notpayTime_txt;
    }

    /**
     * @return utilizationStart_txt
     */
    public String getUtilizationStart_txt() {
        return utilizationStart_txt;
    }

    /**
     * @param utilizationStart_txt
     *            セットする utilizationStart_txt
     */
    public void setUtilizationStart_txt(String utilizationStart_txt) {
        this.utilizationStart_txt = utilizationStart_txt;
    }

    /**
     * @return utilizationEnd_txt
     */
    public String getUtilizationEnd_txt() {
        return utilizationEnd_txt;
    }

    /**
     * @param utilizationEnd_txt
     *            セットする utilizationEnd_txt
     */
    public void setUtilizationEnd_txt(String utilizationEnd_txt) {
        this.utilizationEnd_txt = utilizationEnd_txt;
    }

    /**
     * @return remarks_txa
     */
    public String getRemarks_txa() {
        return remarks_txa;
    }

    /**
     * @param remarks_txa
     *            セットする remarks_txa
     */
    public void setRemarks_txa(String remarks_txa) {
        this.remarks_txa = remarks_txa;
    }

    /**
     * @return recordVerNo
     */
    public String getRecordVerNo() {
        return recordVerNo;
    }

    /**
     * @param recordVerNo
     *            セットする recordVerNo
     */
    public void setRecordVerNo(String recordVerNo) {
        this.recordVerNo = recordVerNo;
    }

    /**
     * @return model
     */
    public PointControlModel getModel() {
        return model;
    }

    /**
     * @param model
     *            セットする model
     */
    public void setModel(PointControlModel model) {
        this.model = model;
    }

}
