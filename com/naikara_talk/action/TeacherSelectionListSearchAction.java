package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.TeacherSelectionListSearchService;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>一覧_講師選択ページ。<br>
 * <b>クラス名称       :</b>一覧_講師選択ページ検索処理Actionクラス。<br>
 * <b>クラス概要       :</b>一覧_講師選択の情報照会ができる<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/09 TECS 新規作成
 */
public class TeacherSelectionListSearchAction extends TeacherSelectionListActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 画面の初期表示。<br>
     * <br>
     *
     * @param なし<br>
     * @return String SUCCESS <br>
     * @exception NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // 表示データを取得する
        TeacherSelectionListSearchService service = new TeacherSelectionListSearchService();

        // 画面のデータをmodelにセットする
        setupModel();

        // ヘッダ部の戻るボタン押下の場合は値の入れ替え処理
        try {
            this.hasSearchFlg = Boolean.toString(Boolean.FALSE);

            // 関連チェック
            if (errorCheck()) {
                return SUCCESS;
            }

            // 表示データの取得
            this.model = service.select(this.model);
            this.hasSearchFlg = Boolean.toString(Boolean.TRUE);
            this.select_rdl = NaikaraTalkConstants.BRANK;

        } catch (Exception e1) {
            throw new NaiException(e1);
        }

        return SUCCESS;
    }

    /**
     * 検索前チェック。<br>
     * <br
     * @param なし
     * @return boolean
     * @throws Exception
     */
    private boolean errorCheck() throws Exception {

        // 更新処理のサービス呼び出し
        TeacherSelectionListSearchService service = new TeacherSelectionListSearchService();

        // 関連チェック
        int chkResult = service.errorCheck(model);

        if (chkResult == TeacherSelectionListSearchService.ERR_GET_DATA_ZERO) {
            this.addActionMessage(getMessage("EN0020", new String[] { "利用者マスタ(講師)", "" }));
            return true;
        }
        if (chkResult > TeacherSelectionListSearchService.ERR_GET_DATE_HUNDRED) {
            this.addActionMessage(getMessage("EN0023", new String[] { "101" }));
            return true;
        }

        return false;
    }
}
