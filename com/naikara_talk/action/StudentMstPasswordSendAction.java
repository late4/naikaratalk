package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.StudentMstPasswordSendService;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守<br>
 * <b>クラス名称　　　:</b>受講者マスタメンテナンス【単票】パスワード送信Actionクラス。<br>
 * <b>クラス概要　　　:</b>受講者マスタメンテナンス【単票】パスワード送信Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b> 2013/07/30 TECS 新規作成。
 */
public class StudentMstPasswordSendAction extends StudentMstActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * パスワード送信ボタンの処理。<br>
     * <br>
     * @param なし
     * @return String
     * @throws NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // Modelクラス設定
        setupModel();

        // チェック
        if (StringUtils.equals(NaikaraTalkConstants.BRANK, model.getPassword_txt())) {
            try {
                this.addActionMessage(getMessage("EN0020", new String[] { "受講者マスタ", "（パスワードの取得処理）" }));
                return SUCCESS;
            } catch (Exception ex) {
                throw new NaiException(ex);
            }
        }

        // Serviceクラス生成：メール送信バッチ
        StudentMstPasswordSendService studentMstPasswordSendService = new StudentMstPasswordSendService();
        studentMstPasswordSendService.sendMail(model);

        // 詳細画面遷移
        return SUCCESS;
    }

}
