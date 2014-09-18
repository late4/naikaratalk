package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.SchoolMailListSearchService;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>スクールメール送信・受信履歴照会<br>
 * <b>クラス名称　　　:</b>スクールメール送信・受信履歴照会クラス。<br>
 * <b>クラス概要　　　:</b>スクールメール送信・受信履歴照会検索Action。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/12/16 TECS 新規作成
 */
public class SchoolMailListSearchAction extends SchoolMailListActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 検索処理。<br>
     * <br>
     * 検索処理。<br>
     * <br>
     * @param なし<br>
     * @return String SUCCESS<br>
     * @exception NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // 画面のパラメータセット
        this.mailSendDtFr_txt = NaikaraStringUtil.converToYYYY_MM_DD(this.mailSendDtFr_txt);
        this.mailSendDtTo_txt = NaikaraStringUtil.converToYYYY_MM_DD(this.mailSendDtTo_txt);
        // Modelクラス設定
        setupModel();
        // Serviceクラス生成
        SchoolMailListSearchService service = new SchoolMailListSearchService();

        // 検索前チェック
        int chkResult;
        try {
            chkResult = service.checkPreSelect(model);
            // エラーの場合、メッセージ設定
            switch (chkResult) {
            case SchoolMailListSearchService.ERR_MAIL_SEND_DT:
                this.addActionMessage(getMessage("EN0011", new String[] { "送信日時（from)", "送信日時（to）" }));
                return SUCCESS;
            case SchoolMailListSearchService.ERR_ZERO_DATA:
                this.addActionMessage(getMessage("EN0020", new String[] { "スクールメール・アカウント変更履歴テーブル", "" }));
                return SUCCESS;
            case SchoolMailListSearchService.ERR_MAXOVER_DATA:
                this.addActionMessage(getMessage("EN0023", new String[] { "101" }));
                return SUCCESS;
            }
        } catch (Exception e) {
            throw new NaiException(e);
        }
        try {
            // 表示データの取得
            this.model.setResultList(service.selectList(this.model));
            if (NaikaraTalkConstants.RETURN_CD_DATA_NO == this.model.getResultList().get(0).getReturnCode()) {
                this.model.getResultList().clear();
            }

        } catch (Exception e) {
            throw new NaiException(e);
        }

        // メッセージの設定
        if (!StringUtils.isEmpty(this.message)) {
            this.addActionMessage(this.message);
        }

        return SUCCESS;
    }

}
