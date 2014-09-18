package com.naikara_talk.service;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.model.CourseMstListModel;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>コースマスタメンテナンス(一覧)<br>
 * <b>クラス概要       :</b>コースマスタメンテナンス(一覧)登録選択Service<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/05 TECS 新規作成
 */
public class CourseMstListMoveService implements ActionService {

    /** チェック：一覧部の項目が選択なし */
    public static final int ERR_NO_SELECT = 1;

    /** チェック：一覧画面へ戻る */
    public static final int ERR_LIST_BACK = 2;

    /** チェック： 権限無*/
    public static final int ERR_NO_AUTH = 3;

    /** チェック： 権限無 一覧画面へ戻る*/
    public static final int ERR_NO_AUTH_BACK = 4;

    /**
     * 単票へ画面遷移する制御処理<br>
     * <br>
     * 単票へ画面遷移する制御処理<br>
     * <br>
     * @param CourseMstListModel select_rdl hasSearchFlg<br>
     * @return CourseMstListModel<br>
     * @exception なし
     */
    public int nextPageRequest(CourseMstListModel model, String select_rdl, String hasSearchFlg) {

        // 処理区分が[新規]を除く場合、一覧部の項目が選択されていない場合は、メッセージ情報を設定する
        if (!StringUtils.equals(CourseMstListModel.PROS_KBN_ADD, model.getProcessKbn())
                && StringUtils.isEmpty(select_rdl)) {
            // エラー場合の再検索判断
            if (NaikaraTalkConstants.TRUE.equals(hasSearchFlg)) {
                return ERR_NO_SELECT;

            }
            // 一覧画面戻る
            return ERR_LIST_BACK;
        }

        // 権限
        String userRole = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getRole();

        // 処理区分
        String processKbn = model.getProcessKbn();

        // 処理区分が [照会] 以外の場合で
        // 且つ 操作者（ログイン者）の権限が 「スタッフ」 の場合は、メッセージ情報を設定する
        if (!StringUtils.equals(CourseMstListModel.PROS_KBN_REF, processKbn)
                && StringUtils.equals(SessionUser.ROLE_STAFF, userRole)) {
            return ERR_NO_AUTH;
        }

        return CourseMstListModel.CHECK_OK;
    }

}
