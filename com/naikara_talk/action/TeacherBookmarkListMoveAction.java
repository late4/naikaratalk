package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.TeacherBookmarkListMoveService;
import com.naikara_talk.sessiondata.SessionTeacherBookmark;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_予約処理<br>
 * <b>クラス名称       :</b>ブックマーク済の講師一覧（Pop）画面遷移Actionクラス<br>
 * <b>クラス概要       :</b>ブックマーク済の講師一覧情報を処理する。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/13 TECS 新規作成
 */
public class TeacherBookmarkListMoveAction extends TeacherBookmarkListActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 画面遷移前処理。<br>
     * <br>
     * 画面遷移前処理を行う。<br>
     * <br>
     * @param なし<br>
     * @return String<br>
     * @throws NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        this.load();

        this.setupModel();

        try {
            TeacherBookmarkListMoveService service = new TeacherBookmarkListMoveService();

            // 必須チェック
            if (!service.requiredCheck(this.select_rdl)) {
                // メッセージ情報を設定する
                this.addActionMessage(getMessage("EC0015", new String[] { "一覧部の左の選択", "" }));
            } else {

                // 利用者マスタデータ件数取得
                int count = service.selectUserMstCount(this.model);

                // レコード件数が、０（Ｚｅｒｏ）の場合
                if (count == 0) {
                    // メッセージ情報を設定する
                    this.addActionMessage(getMessage("EC0061", new String[] { this.teacherNm, "一覧部の左の選択" }));
                }
            }

            if (this.hasActionMessages()) {

                this.select();
                return SUCCESS;
            }

            // 戻る用に必要な情報を取得/格納
            this.modelToSessionTeacherBookmark();

            // ヘッダの戻るリンク用
            setCurrentActionName(NaikaraTalkConstants.STUDENT_MY_PAGE_LOAD);

        } catch (Exception e) {
            throw new NaiException(e);
        }

        this.closeFlg = NaikaraTalkConstants.TRUE;
        return SUCCESS;

    }

    /**
     * Model値 To Session<br>
     * <br>
     * Model値をSessionTeacherBookmarkにセット。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    private void modelToSessionTeacherBookmark() throws Exception {

        // 戻る用に必要な情報を格納
        SessionTeacherBookmark sessionData = new SessionTeacherBookmark();

        // 受講者ID
        sessionData.setStudentId(this.model.getStudentId());
        // 一覧から選択された明細データ
        sessionData.setSelected(this.select_rdl);

        SessionDataUtil.setSessionData(sessionData);
    }
}
