package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様(個人)_初期登録。<br>
 * <b>クラス名称　　　:</b>マイページ確認処理Actionクラス。<br>
 * <b>クラス概要　　　:</b>マイページ確認処理Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/02 TECS 新規作成。
 */
public class MailWithAttachmentsSendStudentConfirmAction extends MailWithAttachmentsSendActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 送信確認処理<br>
     * <br>
     * 送信確認ボタンの処理<br>
     * <br>
     * @param なし<br>
     * @return String NEXTPAGE<br>
     * @exception NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // 画面のパラメータセット
        setupModel();

        // 戻るフラグ(jsp)
        this.pageFlg = NaikaraTalkConstants.TRUE;

        try {
            // 戻る用に必要な情報を取得/格納
            this.modelToSessionMailWithAttachmentsSend();

        } catch (Exception e) {
            throw new NaiException(e);
        }

        // ヘッダの戻るリンク用
        setCurrentActionName(NaikaraTalkConstants.MAIL_WITH_ATTACHMENTS_SEND_STUDENT_LOAD);

        return NEXTPAGE;
    }
}