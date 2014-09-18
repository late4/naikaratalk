package com.naikara_talk.service;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.model.UserMstListModel;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守。<br>
 * <b>クラス名称　　　:</b>利用者マスタメンテナンス登録選択Serviceクラス。<br>
 * <b>クラス概要　　　:</b>利用者マスタメンテナンス登録選択Service。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/04 TECS 新規作成。
 */
public class UserMstListMoveService implements ActionService {

    /** チェック：一覧部の項目が選択なし */
    public static final int ERR_NO_SELECT = 1;

    /** チェック：一覧画面へ戻る */
    public static final int ERR_LIST_BACK = 2;

    /**
     * 遷移処理<br>
     * <br>
     * 単票へ画面遷移する制御処理<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @param select_rdl 選択行利用者ID<br>
     * @param hasSearchFlg 検索有無フラグ<br>
     * @return int エラー有無フラグ<br>
     * @exception なし
     */
    public int nextPageRequest(UserMstListModel model, String select_rdl,
            String hasSearchFlg) {

        // 処理区分が[新規]を除く場合、一覧部の項目が選択されていない場合は、メッセージ情報を設定する
        if (!StringUtils.equals(UserMstListModel.PROS_KBN_ADD,
                model.getProcessKbn())
                && StringUtils.isEmpty(select_rdl)) {

            // エラー場合の再検索判断
            if (NaikaraTalkConstants.TRUE.equals(hasSearchFlg)) {

                return ERR_NO_SELECT;
            }

            return ERR_LIST_BACK;
        }

        return UserMstListModel.CHECK_OK;
    }
}