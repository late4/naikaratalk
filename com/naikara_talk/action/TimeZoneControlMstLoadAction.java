package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.TimeZoneControlMstListModel;
import com.naikara_talk.model.TimeZoneControlMstModel;
import com.naikara_talk.service.TimeZoneControlMstLoadService;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>>マスタ保守。<br>
 * <b>クラス名称　　　:</b>時差管理マスタメンテナンス【単票】初期処理Actionクラス。<br>
 * <b>クラス概要　　　:</b>時差管理マスタメンテナンス【単票】初期処理Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/09 TECS 新規作成。
 */
public class TimeZoneControlMstLoadAction extends TimeZoneControlMstActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 画面の初期表示。<br>
     * <br>
     * @param なし
     * @return String
     * @throws NaiException
     */
    @SkipValidation
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        setupModel();

        TimeZoneControlMstLoadService service = new TimeZoneControlMstLoadService();

        TimeZoneControlMstModel workModel = service.initLoad(model);

        // 前画面から処理区分を画面にセット
        this.processKbn_rdl = workModel.getProcessKbn_rdl();
        this.processKbn_txt = workModel.getProcessKbn_txt();

        // 国コード、時差地域NO、時間（符号）、サマータイム(時間)(符号)、サマータイムフラグの初期取得。
        try {
            initSelect();
            initRadio();
        } catch (Exception e) {
            throw new NaiException(e);
        }

        // ******************************
        // 新規・修正・削除・照会の処理
        // ******************************

        // 処理区分＝新規の場合('0':'新規','1':'修正','2':'削除','3':'照会')

        if (StringUtils.equals(TimeZoneControlMstListModel.PROS_KBN_ADD, this.processKbn_rdl)) {
            // 前画面からの国コード&時差管理Noをクリア
            this.countryCd_sel = NaikaraTalkConstants.BRANK;
            this.areaNoCd_sel = NaikaraTalkConstants.BRANK;
            return SUCCESS;
        }

        // 処理区分＝照会の場合('0':'新規','1':'修正','2':'削除','3':'照会')
        if (StringUtils.equals(TimeZoneControlMstListModel.PROS_KBN_REF, this.processKbn_rdl)) {
            this.setComment(NaikaraTalkConstants.PROCESSKBN_REF_COMMENT);
        }

        // 処理区分＝修正、削除、照会の場合('0':'新規','1':'修正','2':'削除','3':'照会')
        if (StringUtils.equals(TimeZoneControlMstListModel.PROS_KBN_UPD, this.processKbn_rdl)
                || StringUtils.equals(TimeZoneControlMstListModel.PROS_KBN_DEL, this.processKbn_rdl)
                || StringUtils.equals(TimeZoneControlMstListModel.PROS_KBN_REF, this.processKbn_rdl)) {

            try {
                // データが存在しない場合
                if (NaikaraTalkConstants.RETURN_CD_DATA_NO == service.getExist(model)) {
                    this.message = getMessage("EN0020", new String[] { "時差管理マスタ", "" });
                    removeLatestActionList();
                    return ERROR;
                } else {
                    // データが存在する場合

                    // 表示データの取得処理
                    this.load();
                    return SUCCESS;
                }
            } catch (Exception e) {
                throw new NaiException(e);
            }
        }

        // 画面を返す
        return SUCCESS;

    }

    /**
     * 初期処理、表示データの取得。<br>
     * <br>
     * @param なし
     * @return なし
     * @throws Exception
     */
    private void load() throws Exception {

        // 表示データを取得する
        TimeZoneControlMstLoadService service = new TimeZoneControlMstLoadService();

        // 前画面の情報

        this.model.setProcessKbn_rdl(this.processKbn_rdl);
        this.model.setProcessKbn_txt(this.processKbn_txt);

        model = service.select(this.model);

        /** 国コード */
        this.countryCd_sel = model.getCountryCd_sel();

        /** 時差地域NO */
        this.areaNoCd_sel = model.getAreaNoCd_sel();

        /** 時間（符号） */
        this.timeMarkKbn_rdl = model.getTimeMarkKbn_rdl();

        /** 時間(分) */
        this.timeMinutes_txt = String.valueOf(model.getTimeMinutes_txt());

        /** サマータイムフラグ */
        this.sumTimeFlg_rdl = model.getSumTimeFlg_rdl();

        /** サマータイム開始日 */
        this.sumTimeStrDt_txt = NaikaraStringUtil.converToYYYY_MM_DD(model.getSumTimeStrDt_txt());

        /** サマータイム終了日 */
        this.sumTimeEndDt_txt = NaikaraStringUtil.converToYYYY_MM_DD(model.getSumTimeEndDt_txt());

        /** サマータイム(時間)(符号) */
        this.sumTimeMarkKbn_rdl = model.getSumTimeMarkKbn_rdl();

        /** サマータイム(時間)(分) */
        if (StringUtils.equals(model.getSumTimeFlg_rdl(), NaikaraTalkConstants.SUM_TIME_FLG_NO)) {
            this.sumTimeMinutes_txt = NaikaraTalkConstants.BRANK;
        } else {
            this.sumTimeMinutes_txt = model.getSumTimeMinutes_txt();
        }

        /** 備考 */
        this.remark_txa = model.getRemark_txa();

        /** レコードバージョン番号 */
        this.recordVerNo = String.valueOf(model.getRecordVerNo());

    }
}
