package com.naikara_talk.service;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.model.TeacherMstListModel;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>講師マスタメンテナンス(一覧)<br>
 * <b>クラス概要       :</b>講師マスタメンテナンス(一覧)登録選択Service<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/26 TECS 新規作成
 */
public class TeacherMstListMoveService implements ActionService {

    /** チェック：一覧部の項目が選択なし */
    public static final int ERR_NO_SELECT = 1;

    /** チェック：一覧画面へ戻る */
    public static final int ERR_LIST_BACK = 2;

    /**
     * 単票へ画面遷移する制御処理<br>
     * <br>
     * 単票へ画面遷移する制御処理<br>
     * <br>
     * @param select_rdl hasSearchFlg<br>
     * @return int<br>
     * @exception なし
     */
    public int nextPageRequest(String select_rdl, String hasSearchFlg) {

        if (StringUtils.isEmpty(select_rdl)) {
            // エラー場合の再検索判断
            if (NaikaraTalkConstants.TRUE.equals(hasSearchFlg)) {
                return ERR_NO_SELECT;

            }
            // 一覧画面戻る
            return ERR_LIST_BACK;
        }

        return TeacherMstListModel.CHECK_OK;
    }

}
