package com.naikara_talk.action;

//import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraStringUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様(個人)、講師<br>
 * <b>クラス名称　　　:</b>note(レッスン開始にあたるご注意事項)のボタン表示<br>
 * <b>クラス概要　　　:</b>マイページ初期処理Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2014/04/22 TECS 新規作成。
 */
public class LessonLaunchNoteLoadAction extends NaikaraActionSupport {

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

        try {
            // 特に処理は必要ないが、顧客作成(note.html, note_english.html)が
            // メンテナンスしやすいようにするため、作成している

        } catch (Exception e) {
            throw new NaiException(e);
        }

        return SUCCESS;
    }

}