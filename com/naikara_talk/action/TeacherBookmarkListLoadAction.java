package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.sessiondata.SessionTeacherBookmark;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_予約処理<br>
 * <b>クラス名称       :</b>ブックマーク済の講師一覧（Pop）初期化Actionクラス<br>
 * <b>クラス概要       :</b>ブックマーク済の講師一覧情報を処理する。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/13 TECS 新規作成
 */
public class TeacherBookmarkListLoadAction extends TeacherBookmarkListActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 画面の初期表示。<br>
     * <br>
     * 画面の初期表示を設定する。<br>
     * <br>
     * @param なし<br>
     * @return String<br>
     * @throws NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        try {
            // ヘッダ部の戻るボタン押下の場合は値の入れ替え処理
            this.SessionTeacherBookmarkToModelBefore();

            this.setupModel();

            this.select();

        } catch (Exception e) {
            throw new NaiException(e);
        }

        // 画面を返す
        return SUCCESS;
    }

    /**
     * Session値 To Model<br>
     * <br>
     * SessionTeacherBookmark値をModelにセット。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    private void SessionTeacherBookmarkToModelBefore() throws Exception {

        if (StringUtils.isEmpty(this.teacherId)) {
            if ((SessionTeacherBookmark) SessionDataUtil.getSessionData(SessionTeacherBookmark.class.toString()) != null) {
                // 受講者ID
                String studentId = ((SessionTeacherBookmark) SessionDataUtil
                        .getSessionData(SessionTeacherBookmark.class.toString())).getStudentId();

                // 一覧から選択された明細データ
                String selected = ((SessionTeacherBookmark) SessionDataUtil.getSessionData(SessionTeacherBookmark.class
                        .toString())).getSelected();

                // 受講者ID
                this.studentId = studentId;
                // 一覧から選択された明細データ
                this.select_rdl = selected;

                // SessionTeacherBookmarkのクリア
                SessionDataUtil.clearSessionData(SessionTeacherBookmark.class.toString());
            }
        }
    }
}
