package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.SchoolMailListModel;
import com.naikara_talk.service.SchoolMailListLoadService;
import com.naikara_talk.util.NaikaraStringUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>スクールメール送信・受信履歴照会<br>
 * <b>クラス名称　　　:</b>スクールメール送信・受信履歴照会クラス。<br>
 * <b>クラス概要　　　:</b>スクールメール送信・受信履歴照会初期処理Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/12/07 TECS 新規作成。
 */

public class SchoolMailListLoadAction extends SchoolMailListActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 画面の初期表示。<br>
     * <br>
     *
     * @param なし
     * @return String
     * @throws NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // メールパターン客区分、顧客区分の初期取得。
        try {
            initRadio();
        } catch (Exception e) {
            throw new NaiException(e);
        }

        SchoolMailListLoadService service = new SchoolMailListLoadService();

        SchoolMailListModel model = service.load();

        // メール送信日(From)はシステム日付を設定
        this.setMailSendDtFr_txt(model.getMailSendDtFr());

        // メール送信日(To)はシステム日付を設定
        this.setMailSendDtTo_txt(model.getMailSendDtTo());

        // 顧客区分は「全て」を選択する
        this.setCustomer_rdl(model.getCustomerKbn());

        if (!StringUtils.isEmpty(this.message)) {
            this.addActionMessage(this.message);
        }

        // 画面を返す
        return SUCCESS;
    }

}
