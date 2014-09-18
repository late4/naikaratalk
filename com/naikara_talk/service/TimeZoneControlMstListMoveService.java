package com.naikara_talk.service;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.model.TimeZoneControlMstListModel;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守<br>
 * <b>クラス名称　　　:</b>時差管理マスタメンテナンス【一覧】遷移選択Serviceクラス。<br>
 * <b>クラス概要　　　:</b>時差管理マスタメンテナンス【一覧】遷移選択Service。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/10 TECS 新規作成。
 */
public class TimeZoneControlMstListMoveService implements ActionService {

    /** 検索前チェック：一覧部の項目が選択なし */
    public static final int ERR_NO_SELECT = 1;

    /** 検索前チェック：一覧画面へ戻る */
    public static final int ERR_LIST_BACK = 2;

    /** 検索前チェック：権限無 */
    public static final int ERR_NO_AUTH = 3;

    /**
     * 単票へ画面遷移する制御処理<br>
     * <br>
     * @param TimeZoneControlMstListModel
     * @param select_rdl
     * @param hasSearchFlg
     * @return TimeZoneControlMstListModel
     */
    public int nextPageRequest(TimeZoneControlMstListModel model, String select_rdl, String hasSearchFlg) {

        // 処理区分の取得
        String processKbn = model.getProcessKbn_rdl();

        // 処理区分が[新規]を除く場合、一覧部の項目が選択されていない場合は、メッセージ情報を設定する
        if (!StringUtils.equals(TimeZoneControlMstListModel.PROS_KBN_ADD, processKbn)
                && StringUtils.isEmpty(select_rdl)) {
            // エラー場合の再検索判断
            if (NaikaraTalkConstants.TRUE.equals(hasSearchFlg)) {
                return ERR_NO_SELECT;

            }
            // 一覧画面戻る
            return ERR_LIST_BACK;
        }

        // 権限の取得
        String userRole = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getRole();

        // (1) 処理区分が [照会] 以外の場合で 且つ 操作者（ログイン者）の権限が 「スタッフ」
        // の場合は、メッセージ情報を設定する
        if (!StringUtils.equals(TimeZoneControlMstListModel.PROS_KBN_REF, processKbn)
                && StringUtils.equals(NaikaraTalkConstants.AUTHORITY_S, userRole)) {

            return ERR_NO_AUTH;
        }
        // (2) 処理区分が[削除]の場合 且つ 操作者（ログイン者）が「システム管理者」以外の場合は、メッセージ情報を設定する
        if (StringUtils.equals(TimeZoneControlMstListModel.PROS_KBN_DEL, processKbn)
                && !StringUtils.equals(NaikaraTalkConstants.AUTHORITY_A, userRole)) {
            return ERR_NO_AUTH;
        }

        return TimeZoneControlMstListModel.CHECK_OK;
    }

}
