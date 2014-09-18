package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.MailWithAttachmentsSendLoadService;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様(個人)_初期登録。<br>
 * <b>クラス名称　　　:</b>マイページ初期処理Actionクラス。<br>
 * <b>クラス概要　　　:</b>マイページ初期処理Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/02 TECS 新規作成。
 */
public class MailWithAttachmentsSendTeacherLoadAction extends MailWithAttachmentsSendActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 画面初期表示<br>
     * <br>
     * 画面の初期表示<br>
     * <br>
     * @param なし<br>
     * @return String SUCCESS <br>
     * @exception NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // 戻るフラグ(jsp)
        this.pageFlg = NaikaraTalkConstants.FALSE;

        // ヘッダ部の戻るボタン押下の場合は値の入れ替え処理
        this.SessionMailWithAttachmentsSendToModelBefore();

        // 画面のパラメータセット
        setupModel();

        if (!StringUtils.isEmpty(this.message)) {

            this.addActionMessage(this.message);
        }

        // 画面表示のタイトル
        this.title = TITLE_TEACHER;

        // Help画面名
        this.helpPageId = HELP_PAGE_ID_TEACHER;

        try {

            // データを取得
            this.load();

        } catch (Exception e) {

            throw new NaiException(e);
        }

        // セッションをクリーンアップ
        this.clearSessionMailWithAttachmentsSend();

        return SUCCESS;
    }

    /**
     * データ取得<br>
     * <br>
     * データを取得<br>
     * <br>
     * @param なし<br>
     * @return なし <br>
     * @exception Exception
     */
    private void load() throws Exception {

        // サービスの初期化
        MailWithAttachmentsSendLoadService service = new MailWithAttachmentsSendLoadService();

        // データを取得
        model = service.select(this.model);

        // 母国語(jsp)
        this.nativeLanguage = model.getNativeLanguage();

        // 送信者(jsp)
        this.teacherIdNm = model.getTeacherIdNm();

        // 受信者(jsp)
        this.studentIdNm = model.getStudentIdNm();
    }
}