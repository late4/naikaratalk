package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.PaymentManagementListDirectionsModel;
import com.naikara_talk.service.PaymentManagementListDirectionsCsvCreateService;
import com.naikara_talk.util.NaikaraStringUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>会社側_管理帳票<br>
 * <b>クラス名称       :</b>講師毎の支払管理表Actionクラス<br>
 * <b>クラス概要       :</b>講師毎の支払管理表CSV作成Action<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/08 TECS 新規作成
 */
public class PaymentManagementListDirectionsCsvCreateAction extends PaymentManagementListDirectionsActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * CSV作成処理。<br>
     * <br>
     * CSV作成処理。<br>
     * <br>
     * @param なし<br>
     * @return String SUCCESS<br>
     * @exception NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // Serviceクラス生成
        PaymentManagementListDirectionsCsvCreateService service = new PaymentManagementListDirectionsCsvCreateService();

        this.model.setPayment_txt(this.payment_txt);
        try {
            // 検索前チェック
            if (PaymentManagementListDirectionsCsvCreateService.ERR_ZERO_DATA == service.checkPreCreate(this.model)) {
                this.addActionMessage(getMessage("EN0020", new String[] { "支払集計テーブル", "" }));
                return SUCCESS;
            }
        } catch (Exception e) {
            throw new NaiException(e);
        }

        // CSV作成処理
        PaymentManagementListDirectionsModel resModel;
        try {
            resModel = service.csvCreate(this.model);
        } catch (Exception e1) {
            throw new NaiException(e1);
        }
        try {
            StringBuffer message = new StringBuffer();
            message.append(NaikaraStringUtil.converToYYYY_MM(this.model.getPayment_txt())).append("のCSV作成");
            this.addActionMessage(getMessage("IN0014", new String[] { message.toString() }));
        } catch (Exception e) {
            throw new NaiException(e);
        }
        this.fileName = resModel.getFileName();
        this.cerateFlg = resModel.getCerateFlg();

        return SUCCESS;
    }
}
