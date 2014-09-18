package com.naikara_talk.service;

import java.sql.Connection;
import java.util.LinkedHashMap;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.logic.UserMstListLogic;
import com.naikara_talk.model.UserMstListModel;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守。<br>
 * <b>クラス名称　　　:</b>利用者マスタメンテナンス初期処理Serviceクラス。<br>
 * <b>クラス概要　　　:</b>利用者マスタメンテナンス初期処理Service。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/04 TECS 新規作成。
 */
public class UserMstListLoadService implements ActionService {

    /**
     * 初期処理<br>
     * <br>
     * 画面項目の初期処理を行う<br>
     * <br>
     * @param なし<br>
     * @return model 画面パラメータ<br>
     * @exception なし
     */
    public UserMstListModel load() {

        // モデルの初期化
        UserMstListModel model = new UserMstListModel();

        // 処理区分は「照会」を選択
        model.setProcessKbn(UserMstListModel.PROS_KBN_REF);

        // 利用状態は「利用可」を選択
        model.setUseKbn(NaikaraTalkConstants.USE_KBN_OK);

        return model;
    }

    /**
     * コード取得<br>
     * <br>
     * コード管理マスタからデータ取得処理<br>
     * <br>
     * @param category 汎用コード<br>
     * @return LinkedHashMap 取得結果<br>
     * @exception Exception
     */
    public LinkedHashMap<String, String> selectCodeMst(String category)
            throws Exception {

        // コネクションを取得
        Connection conn = DbUtil.getConnection();

        // ロジックの初期化
        UserMstListLogic userMstListLogic = new UserMstListLogic(conn);

        // コード管理マスタを検索
        LinkedHashMap<String, String> codeMap = userMstListLogic
                .selectCodeMst(category);

        // コネクションを閉じる
        conn.close();

        return codeMap;
    }
}