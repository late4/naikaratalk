package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.TeacherListRegisterService;
import com.naikara_talk.service.TeacherListSearchService;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_予約処理<br>
 * <b>クラス名称       :</b>講師一覧（Pop）登録Actionクラス<br>
 * <b>クラス概要       :</b>講師一覧（Pop）画面より講師のブックマークの登録処理を行う。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/19 TECS 新規作成
 */
public class TeacherListRegisterAction extends TeacherListActionSupport {

    private static final long serialVersionUID = 1L;

    /** 一覧の表示上限 */
    private static final int LIST_MAX_CNT = 50;

    /**
     * 更新処理。<br>
     * <br>
     * 更新処理を行う。<br>
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

        TeacherListRegisterService service = new TeacherListRegisterService();

        // チェック
        try {
            boolean errFlg = false;

            // 必須入力チェック
            int chkResult = service.nextPageRequest(this.model, this.getSelect_rdl(), this.getHasSearchFlg());

            // エラーの場合、メッセージ設定
            switch (chkResult) {
            case TeacherListRegisterService.ERR_NO_SELECT:

                errFlg = true;
                // メッセージ情報を設定する
                this.addActionMessage(getMessage("EC0015", new String[] { "一覧部の左の選択" }));
                break;

            case TeacherListRegisterService.ERR_LIST_BACK:
                // メッセージ情報を設定する
                this.addActionMessage(getMessage("EC0015", new String[] { "一覧部の左の選択" }));
                return SUCCESS;
            }

            if (!errFlg) {
                // ブックマーク済の講師の件数チェック
                // 受講者別講師ブックマークテーブルのデータ件数取得処理
                int count = service.selectTeacherBookmarkMstCount(this.model);
                // 件数のチェック：50件を超える場合
                if (count >= LIST_MAX_CNT) {
                    errFlg = true;
                    // エラーメッセージを設定する
                    this.addActionMessage(getMessage("EC0060", new String[] { "50" }));
                }
            }

            if (errFlg) {
                TeacherListSearchService searchService = new TeacherListSearchService();

                // 表示データの取得
                this.model.setResultList(searchService.selectTeacherList(this.model));

                // 選択したラジオボタンをクリアする
                this.select_rdl = NaikaraTalkConstants.BRANK;
                this.hasSearchFlg = NaikaraTalkConstants.TRUE;
                return SUCCESS;
            }

            // 講師ブックマーク登録処理
            service.insertTeacherBookmarkMst(this.model);

            // 更新完了メッセージを設定する
            this.message = getMessage("IC0011", new String[] { "講師ブックマーク", "" });

            // 戻る用に必要な情報を取得/格納
            this.modelToSessionTeacher();

        } catch (Exception e) {
            throw new NaiException(e);
        }

        return NEXTPAGE;
    }
}
