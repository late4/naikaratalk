package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.TimeZoneControlMstListLogic;
import com.naikara_talk.model.TimeZoneControlMstListModel;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守<br>
 * <b>クラス名称　　　:</b>時差管理マスタメンテナンス【一覧】初期処理Serviceクラス。<br>
 * <b>クラス概要　　　:</b>時差管理マスタメンテナンス【一覧】初期処理Service。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/10 TECS 新規作成。
 */
public class TimeZoneControlMstListLoadService implements ActionService {

    /**
     * 初期処理<br>
     * <br>
     * 画面項目の初期処理を行う<br>
     * <br>
     * @return TimeZoneControlMstListModel
     */
    public TimeZoneControlMstListModel load() {
        TimeZoneControlMstListModel model = new TimeZoneControlMstListModel();
        // 処理区分は「照会」を選択する
        model.setProcessKbn_rdl(TimeZoneControlMstListModel.PROS_KBN_REF);

        // 国コードは「全て」を選択する
        model.setAreaNoCd_sel(NaikaraTalkConstants.CHOICE_ALL_ZERO);

        // 時差地域Noは「全て」を選択する
        model.setCountryCd_sel(NaikaraTalkConstants.CHOICE_ALL_ZERO);

        return model;
    }

    /**
     * コード管理マスタからデータ取得処理。<br>
     * <br>
     * @param category
     *            汎用コード
     * @return LinkedHashMap<String, String>
     * @throws Exception
     */
    public LinkedHashMap<String, String> selectCodeMst(String category) throws Exception {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            TimeZoneControlMstListLogic timeZoneControlMstListLogic = new TimeZoneControlMstListLogic(conn);
            // コード管理マスタ検索
            return timeZoneControlMstListLogic.selectCodeMst(category);
        } catch (SQLException se) {
            se.printStackTrace();
            throw new NaiException(se);
        } finally {
            try {
                conn.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}
