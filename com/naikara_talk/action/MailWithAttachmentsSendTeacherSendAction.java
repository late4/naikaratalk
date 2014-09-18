package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.MailWithAttachmentsSendSendService;
import com.naikara_talk.util.NaikaraStringUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様(個人)_初期登録。<br>
 * <b>クラス名称　　　:</b>マイページ送信処理Actionクラス。<br>
 * <b>クラス概要　　　:</b>マイページ送信処理Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/02 TECS 新規作成。
 */
public class MailWithAttachmentsSendTeacherSendAction extends MailWithAttachmentsSendActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 画面送信処理<br>
     * <br>
     * 画面送信処理を行う<br>
     * <br>
     * @param なし<br>
     * @return String<br>
     * @throws NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // 画面のパラメータセット
        setupModel();

        // サービスの初期化
        MailWithAttachmentsSendSendService service = new MailWithAttachmentsSendSendService();

        try {
            // メールを送信
            service.sendMail(this.model);

            StringBuffer sb = new StringBuffer();
            sb.append(model.getStudentId()).append(MailWithAttachmentsSendSendService.COLON)
                    .append(model.getStudentNickNm());

            // メッセージ
            this.message = getMessage("IT0015", new String[] { sb.toString(), sb.toString() });

        } catch (Exception e) {

            throw new NaiException(e);
        }

        return NEXTPAGE;
    }
}