package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.PointControlListModel;
import com.naikara_talk.model.PointControlModel;
import com.naikara_talk.service.PointControlLoadService;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>ポイント管理マスタメンテナンス(単票)<br>
 * <b>クラス概要       :</b>ポイント管理マスタメンテナンス(単票)初期処理Action<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/01 TECS 新規作成
 */
public class PointControlLoadAction extends PointControlActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 画面の初期表示。<br>
     * <br>
     * 画面の初期表示。<br>
     * <br>
     * @param なし<br>
     * @return String SUCCESS <br>
     * @exception NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        setupModel();
        PointControlLoadService service = new PointControlLoadService();

        PointControlModel workModel = service.initLoad(model);

        // 前画面から処理区分を画面にセット
        this.processKbn_rdl = workModel.getProcessKbn_Rdl();
        this.processKbn_txt = workModel.getProcessKbn_Txt();

        // 通常月謝区分の初期取得。
        try {
            initRadio();
        } catch (Exception e) {
            throw new NaiException(e);
        }

        // ******************************
        // 新規・修正・照会の処理
        // ******************************

        // 処理区分＝新規の場合('0':'新規','1':'修正','2':'照会')
        if (StringUtils.equals(PointControlListModel.PROS_KBN_ADD, this.processKbn_rdl)) {
            // 前画面からの商品コードをクリア
            this.pointCode_txt = NaikaraTalkConstants.BRANK;

            // 無償ポイント
            this.notpayPoint_txt = String.valueOf(workModel.getFreePoint());

            // 無償ポイント期限
            this.notpayTime_txt = String.valueOf(workModel.getFreePointTim());

            // 提供期間開始日
            this.utilizationStart_txt = workModel.getUseStrDt();

            // 提供期間終了日
            this.utilizationEnd_txt = workModel.getUseEndDt();
            return SUCCESS;
        }

        // 処理区分＝照会の場合('0':'新規','1':'修正','2':'照会')
        if (StringUtils.equals(PointControlListModel.PROS_KBN_REF, this.processKbn_rdl)) {
            this.setComment(NaikaraTalkConstants.PROCESSKBN_REF_COMMENT);
        }

        // 処理区分＝修正、照会の場合('0':'新規','1':'修正','2':'照会')
        if (StringUtils.equals(PointControlListModel.PROS_KBN_UPD, this.processKbn_rdl)
                || StringUtils.equals(PointControlListModel.PROS_KBN_REF, this.processKbn_rdl)) {

            try {
                // データが存在しない場合
                if (NaikaraTalkConstants.RETURN_CD_DATA_NO == service.getExist(model)) {
                    this.message = getMessage("EN0020", new String[] { "ポイント管理マスタ", "" });
                    removeLatestActionList();
                    // 前画面(一覧)へ戻り、エラーメッセージを表示
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
     * 初期処理、表示データの取得。<br>
     * <br>
     * @param なし<br>
     * @return なし <br>
     * @exception Exception
     */
    private void load() throws Exception {

        // 表示データを取得する
        PointControlLoadService service = new PointControlLoadService();

        // 前画面の情報
        this.model.setPointCd(this.pointCode_txt);
        this.model.setProcessKbn_Rdl(this.processKbn_rdl);
        this.model.setProcessKbn_Txt(this.processKbn_txt);

        model = service.select(this.model);

        /** ポイントコード */
        this.pointCode_txt = model.getPointCd();

        /** 通常月謝区分 */
        this.monthlyKbn_rdl = model.getFeeKbn();

        /** 金額(税込)有償ポイント */
        this.payPoint_txt = NaikaraStringUtil.addComma(String.valueOf(model.getMoneyYen()));

        /** 有償ポイント期限 */
        this.payTime_txt = NaikaraStringUtil.addComma(String.valueOf(model.getPaymentPointTim()));

        /** 無償ポイント */
        this.notpayPoint_txt = NaikaraStringUtil.addComma(String.valueOf(model.getFreePoint()));

        /** 無償ポイント期限 */
        this.notpayTime_txt = NaikaraStringUtil.addComma(String.valueOf(model.getFreePointTim()));

        /** 提供期間開始日 */
        this.utilizationStart_txt = NaikaraStringUtil.converToYYYY_MM_DD(model.getUseStrDt());

        /** 提供期間終了日 */
        this.utilizationEnd_txt = NaikaraStringUtil.converToYYYY_MM_DD(model.getUseEndDt());

        /** 備考 */
        this.remarks_txa = model.getRemarks();

        /** レコードバージョン番号 */
        this.recordVerNo = String.valueOf(model.getRecordVerNo());

    }

}
