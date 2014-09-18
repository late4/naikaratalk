package com.naikara_talk.action;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.TimeZoneControlMstModel;
import com.naikara_talk.service.TimeZoneControlMstLoadService;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守<br>
 * <b>クラス名称　　　:</b>時差管理マスタメンテナンス【単票】共通Actionクラス。<br>
 * <b>クラス概要　　　:</b>時差管理マスタメンテナンス【単票】共通Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/09 TECS 新規作成。
 */
public abstract class TimeZoneControlMstActionSupport extends NaikaraActionSupport {

    private static final long serialVersionUID = 1L;

    // 画面表示のタイトル
    protected String title = "時差管理マスタメンテナンス";

    // Help画面名
    protected String helpPageId = "HelpTimeZoneControlMst.html";

    /**
     * チェック。<br>
     * <br>
     * コースコードを戻り値で返却する<br>
     * <br>
     * @param なし
     * @return なし
     */
    @Override
    public void validate() {

        // チェックエラーの場合、国コード、時差地域NO、時間（符号）、サマータイム(時間)(符号)、サマータイムフラグの初期取得。
        try {
            initSelect();
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
     * 国コード、時差地域名Noを取得する。<br>
     * <br>
     * @param なし
     * @return void
     * @throws Exception
     */
    public void initSelect() throws Exception {

        TimeZoneControlMstLoadService service = new TimeZoneControlMstLoadService();
        // 国コードを取得する
        this.countryCd_sell = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_COUNTRY);
        // 時差地域名Noを取得する
        this.areaNoCd_sell = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_AREA_NO);
    }

    /**
     * 国コード、時差地域名Noを取得する。<br>
     * <br>
     * @param なし
     * @return void
     * @throws Exception
     */
    public void initRadio() throws Exception {

        TimeZoneControlMstLoadService service = new TimeZoneControlMstLoadService();
        // 時間（符号）
        this.timeMarkKbn_rdll = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_SIGN);
        // サマータイム(時間)(符号)
        this.sumTimeMarkKbn_rdll = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_SIGN);
        // サマータイムフラグ
        this.sumTimeFlg_rdll = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_SUM_TIME_KBN);

    }

    /**
     * 画面のパラメータをモデルにセット。<br>
     * <br>
     * @param なし
     * @return なし
     */
    public void setupModel() {

        // search PrimaryKey
        this.model.setPrimaryKey(this.primaryKey);
        if (!StringUtils.isEmpty(this.primaryKey)) {
            String searchKeys = this.primaryKey.substring(1, this.primaryKey.length() - 1);
            // 国コード
            this.model.setCountryCd_sel(searchKeys.split(",")[0].toString());
            // 時差地域NO
            this.model.setAreaNoCd_sel(searchKeys.split(",")[1].toString().trim());
        } else {
            // 国コード
            this.model.setCountryCd_sel(this.getCountryCd_sel());
            // 時差地域NO
            this.model.setAreaNoCd_sel(this.getAreaNoCd_sel());
        }

        // 時間（符号）
        this.model.setTimeMarkKbn_rdl(this.timeMarkKbn_rdl);
        // 時間(分)
        this.model.setTimeMinutes_txt(Integer.valueOf(StringUtils.isEmpty(this.timeMinutes_txt) ? "0"
                : this.timeMinutes_txt));
        // サマータイムフラグ
        this.model.setSumTimeFlg_rdl(this.sumTimeFlg_rdl);
        // サマータイム開始日
        this.model.setSumTimeStrDt_txt(this.sumTimeStrDt_txt);
        // サマータイム終了日
        this.model.setSumTimeEndDt_txt(this.sumTimeEndDt_txt);
        // サマータイム(時間)(符号)
        this.model.setSumTimeMarkKbn_rdl(this.sumTimeMarkKbn_rdl);
        // サマータイム(時間)(分)
        this.model.setSumTimeMinutes_txt(this.sumTimeMinutes_txt);
        // 備考
        this.model.setRemark_txa(this.remark_txa);
        // レコードバージョン番号
        String recVerNo = StringUtils.isEmpty(this.recordVerNo) ? "0" : this.recordVerNo;
        this.model.setRecordVerNo(Integer.parseInt(recVerNo));

        // 処理区分(前画面の引き継ぎ情報)
        this.model.setProcessKbn_rdl(this.processKbn_rdl);
        // 画面表示処理区分
        this.model.setProcessKbn_txt(this.processKbn_txt);

    }

    /** メッセージ */
    protected String message;

    /** 処理区分(前画面の引き継ぎ情報) */
    protected String processKbn_rdl;

    /** 画面表示処理区分 */
    protected String processKbn_txt;

    /** コメン */
    protected String comment;

    /** 国コード */
    protected String countryCd_sel;
    protected Map<String, String> countryCd_sell = new LinkedHashMap<String, String>();

    /** 時差地域NO */
    protected String areaNoCd_sel;
    protected Map<String, String> areaNoCd_sell = new LinkedHashMap<String, String>();

    /** 時間（符号） */
    protected String timeMarkKbn_rdl;
    protected Map<String, String> timeMarkKbn_rdll = new LinkedHashMap<String, String>();

    /** 時間(分) */
    protected String timeMinutes_txt;

    /** サマータイムフラグ */
    protected String sumTimeFlg_rdl;
    protected Map<String, String> sumTimeFlg_rdll = new LinkedHashMap<String, String>();

    /** サマータイム開始日 */
    protected String sumTimeStrDt_txt;

    /** サマータイム終了日 */
    protected String sumTimeEndDt_txt;

    /** サマータイム(時間)(符号) */
    protected String sumTimeMarkKbn_rdl;
    protected Map<String, String> sumTimeMarkKbn_rdll = new LinkedHashMap<String, String>();

    /** サマータイム(時間)(分) */
    protected String sumTimeMinutes_txt;

    /** 備考 */
    protected String remark_txa;

    /** 排他用レコードバージョン */
    protected String recordVerNo;

    /** 処理結果 */
    protected TimeZoneControlMstModel model = new TimeZoneControlMstModel();

    /** search Primary Key */
    protected String primaryKey;

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param  title
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
     * @param  helpPageId
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
     * @param  message
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
     * @param  processKbn_rdl
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
     * @param  processKbn_txt
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
     * @param  comment
     *            セットする comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return countryCd_sel
     */
    public String getCountryCd_sel() {
        return countryCd_sel;
    }

    /**
     * @param  countryCd_sel
     *            セットする countryCd_sel
     */
    public void setCountryCd_sel(String countryCd_sel) {
        this.countryCd_sel = countryCd_sel;
    }

    /**
     * @return countryCd_sell
     */
    public Map<String, String> getCountryCd_sell() {
        return countryCd_sell;
    }

    /**
     * @param  countryCd_sell
     *            セットする countryCd_sell
     */
    public void setCountryCd_sell(Map<String, String> countryCd_sell) {
        this.countryCd_sell = countryCd_sell;
    }

    /**
     * @return areaNoCd_sel
     */
    public String getAreaNoCd_sel() {
        return areaNoCd_sel;
    }

    /**
     * @param  areaNoCd_sel
     *            セットする areaNoCd_sel
     */
    public void setAreaNoCd_sel(String areaNoCd_sel) {
        this.areaNoCd_sel = areaNoCd_sel;
    }

    /**
     * @return areaNoCd_sell
     */
    public Map<String, String> getAreaNoCd_sell() {
        return areaNoCd_sell;
    }

    /**
     * @param  areaNoCd_sell
     *            セットする areaNoCd_sell
     */
    public void setAreaNoCd_sell(Map<String, String> areaNoCd_sell) {
        this.areaNoCd_sell = areaNoCd_sell;
    }

    /**
     * @return timeMarkKbn_rdl
     */
    public String getTimeMarkKbn_rdl() {
        return timeMarkKbn_rdl;
    }

    /**
     * @param  timeMarkKbn_rdl
     *            セットする timeMarkKbn_rdl
     */
    public void setTimeMarkKbn_rdl(String timeMarkKbn_rdl) {
        this.timeMarkKbn_rdl = timeMarkKbn_rdl;
    }

    /**
     * @return timeMarkKbn_rdll
     */
    public Map<String, String> getTimeMarkKbn_rdll() {
        return timeMarkKbn_rdll;
    }

    /**
     * @param  timeMarkKbn_rdll
     *            セットする timeMarkKbn_rdll
     */
    public void setTimeMarkKbn_rdll(Map<String, String> timeMarkKbn_rdll) {
        this.timeMarkKbn_rdll = timeMarkKbn_rdll;
    }

    /**
     * @return timeMinutes_txt
     */
    public String getTimeMinutes_txt() {
        return timeMinutes_txt;
    }

    /**
     * @param  timeMinutes_txt
     *            セットする timeMinutes_txt
     */
    public void setTimeMinutes_txt(String timeMinutes_txt) {
        this.timeMinutes_txt = timeMinutes_txt;
    }

    /**
     * @return sumTimeFlg_rdl
     */
    public String getSumTimeFlg_rdl() {
        return sumTimeFlg_rdl;
    }

    /**
     * @param  sumTimeFlg_rdl
     *            セットする sumTimeFlg_rdl
     */
    public void setSumTimeFlg_rdl(String sumTimeFlg_rdl) {
        this.sumTimeFlg_rdl = sumTimeFlg_rdl;
    }

    /**
     * @return sumTimeStrDt_txt
     */
    public String getSumTimeStrDt_txt() {
        return sumTimeStrDt_txt;
    }

    /**
     * @param  sumTimeStrDt_txt
     *            セットする sumTimeStrDt_txt
     */
    public void setSumTimeStrDt_txt(String sumTimeStrDt_txt) {
        this.sumTimeStrDt_txt = sumTimeStrDt_txt;
    }

    /**
     * @return sumTimeEndDt_txt
     */
    public String getSumTimeEndDt_txt() {
        return sumTimeEndDt_txt;
    }

    /**
     * @param  sumTimeEndDt_txt
     *            セットする sumTimeEndDt_txt
     */
    public void setSumTimeEndDt_txt(String sumTimeEndDt_txt) {
        this.sumTimeEndDt_txt = sumTimeEndDt_txt;
    }

    /**
     * @return sumTimeMarkKbn_rdl
     */
    public String getSumTimeMarkKbn_rdl() {
        return sumTimeMarkKbn_rdl;
    }

    /**
     * @param  sumTimeMarkKbn_rdl
     *            セットする sumTimeMarkKbn_rdl
     */
    public void setSumTimeMarkKbn_rdl(String sumTimeMarkKbn_rdl) {
        this.sumTimeMarkKbn_rdl = sumTimeMarkKbn_rdl;
    }

    /**
     * @return sumTimeFlg_rdll
     */
    public Map<String, String> getSumTimeFlg_rdll() {
        return sumTimeFlg_rdll;
    }

    /**
     * @param  sumTimeFlg_rdll
     *            セットする sumTimeFlg_rdll
     */
    public void setSumTimeFlg_rdll(Map<String, String> sumTimeFlg_rdll) {
        this.sumTimeFlg_rdll = sumTimeFlg_rdll;
    }

    /**
     * @return sumTimeMarkKbn_rdll
     */
    public Map<String, String> getSumTimeMarkKbn_rdll() {
        return sumTimeMarkKbn_rdll;
    }

    /**
     * @param  sumTimeMarkKbn_rdll
     *            セットする sumTimeMarkKbn_rdll
     */
    public void setSumTimeMarkKbn_rdll(Map<String, String> sumTimeMarkKbn_rdll) {
        this.sumTimeMarkKbn_rdll = sumTimeMarkKbn_rdll;
    }

    /**
     * @return sumTimeMinutes_txt
     */
    public String getSumTimeMinutes_txt() {
        return sumTimeMinutes_txt;
    }

    /**
     * @param  sumTimeMinutes_txt
     *            セットする sumTimeMinutes_txt
     */
    public void setSumTimeMinutes_txt(String sumTimeMinutes_txt) {
        this.sumTimeMinutes_txt = sumTimeMinutes_txt;
    }

    /**
     * @return remark_txa
     */
    public String getRemark_txa() {
        return remark_txa;
    }

    /**
     * @param  remark_txa
     *            セットする remark_txa
     */
    public void setRemark_txa(String remark_txa) {
        this.remark_txa = remark_txa;
    }

    /**
     * @return recordVerNo
     */
    public String getRecordVerNo() {
        return recordVerNo;
    }

    /**
     * @param  recordVerNo
     *            セットする recordVerNo
     */
    public void setRecordVerNo(String recordVerNo) {
        this.recordVerNo = recordVerNo;
    }

    /**
     * @return model
     */
    public TimeZoneControlMstModel getModel() {
        return model;
    }

    /**
     * @param  model
     *            セットする model
     */
    public void setModel(TimeZoneControlMstModel model) {
        this.model = model;
    }

    /**
     * @return primaryKey
     */
    public String getPrimaryKey() {
        return primaryKey;
    }

    /**
     * @param  primaryKey
     *            セットする primaryKey
     */
    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    /**
     * @return serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
