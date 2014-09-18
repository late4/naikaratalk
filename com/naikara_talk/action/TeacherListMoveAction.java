package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.TeacherListMoveService;
import com.naikara_talk.service.TeacherListSearchService;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_予約処理<br>
 * <b>クラス名称       :</b>講師一覧（Pop）画面遷移Actionクラス<br>
 * <b>クラス概要       :</b>講師一覧（Pop）画面より講師の予約スケジュールへの遷移を行う。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/19 TECS 新規作成
 */
public class TeacherListMoveAction extends TeacherListActionSupport {

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

        // 一覧から選択された明細データの取得
        this.load();

        // 画面のパラメータセット
        setupModel();

        TeacherListMoveService service = new TeacherListMoveService();

        // チェック
        try {
            boolean errFlg = false;

            // 必須入力チェック
            int chkResult = service.nextPageRequest(this.model, this.getSelect_rdl(), this.getHasSearchFlg());

            // エラーの場合、メッセージ設定
            switch (chkResult) {
            case TeacherListMoveService.ERR_NO_SELECT:

                errFlg = true;
                // メッセージ情報を設定する
                this.addActionMessage(getMessage("EC0015", new String[] { "一覧部の左の選択" }));
                break;

            case TeacherListMoveService.ERR_LIST_BACK:
                // メッセージ情報を設定する
                this.addActionMessage(getMessage("EC0015", new String[] { "一覧部の左の選択" }));
                return SUCCESS;
            }

            if (!errFlg) {
                // 契約期間外の講師は、予約が出来ない
                // 利用者マスタのデータ件数取得処理
                int count = service.selectUserMstCount(this.model);
                // レコード件数が、０（Ｚｅｒｏ）の場合
                if (count == 0) {
                    errFlg = true;
                    // エラーメッセージを設定する
                    this.addActionMessage(getMessage("EC0061", new String[] { model.getTeacherNmSel(), "一覧部の左の選択" }));
                }
            }

            if (errFlg) {
                TeacherListSearchService searchService = new TeacherListSearchService();

                // 表示データの取得
                this.model.setResultList(searchService.selectTeacherList(this.model));

                this.hasSearchFlg = NaikaraTalkConstants.TRUE;
                return SUCCESS;
            }

            // 戻る用に必要な情報を取得/格納
            this.modelToSessionTeacher();

            // ヘッダの戻るリンク用
            setCurrentActionName(NaikaraTalkConstants.RESERVATION_CANCELLATION_COURSE_LIST_LOAD);

        } catch (Exception e) {
            throw new NaiException(e);
        }

        this.closeFlg = NaikaraTalkConstants.TRUE;
        return SUCCESS;
    }

}
