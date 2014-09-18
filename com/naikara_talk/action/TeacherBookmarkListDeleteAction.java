package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.TeacherBookmarkListDeleteService;
import com.naikara_talk.service.TeacherBookmarkListMoveService;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_予約処理<br>
 * <b>クラス名称       :</b>ブックマーク済の講師一覧（Pop）ブックマーク解除Actionクラス<br>
 * <b>クラス概要       :</b>ブックマーク済の講師一覧情報を処理する。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/13 TECS 新規作成
 */
public class TeacherBookmarkListDeleteAction extends TeacherBookmarkListActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * ブックマーク解除処理。<br>
     * <br>
     * ブックマーク解除処理を行う。<br>
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

            TeacherBookmarkListMoveService moveService = new TeacherBookmarkListMoveService();

            // 必須チェック
            if (!moveService.requiredCheck(this.select_rdl)) {
                // メッセージ情報を設定する
                this.addActionMessage(getMessage("EC0015", new String[] { "一覧部の左の選択", "" }));
            } else {

                TeacherBookmarkListDeleteService service = new TeacherBookmarkListDeleteService();

                // 講師ブックマーク削除処理
                int deleteRowCount = service.deleteTeacherBookmark(this.model);

                // メッセージの設定
                if (deleteRowCount == NaikaraTalkConstants.RETURN_CD_ERR_DEL) {
                    // 排他メッセージ
                    this.addActionMessage(getMessage("ES0014", new String[] {}));
                } else {
                    // 更新完了メッセージ
                    this.addActionMessage(getMessage("IC0011", new String[] { "講師ブックマーク", "" }));
                }

            }

            this.select();

        } catch (Exception e) {
            throw new NaiException(e);
        }

        // 画面を返す
        return SUCCESS;
    }
}
