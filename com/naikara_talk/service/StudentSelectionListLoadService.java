package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.StudentSelectionListLogic;
import com.naikara_talk.model.StudentSelectionListModel;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様(個人)_初期登録。<br>
 * <b>クラス名称　　　:</b>マイページ(お客様_個人)初期処理Serviceクラス。<br>
 * <b>クラス概要　　　:</b>マイページ(お客様_個人)初期処理Service。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/31 TECS 新規作成。
 */
public class StudentSelectionListLoadService implements ActionService {

    /**
     * 初期処理<br>
     * <br>
     * 画面項目の初期処理を行う<br>
     * <br>
     * @param なし<br>
     * @return model 画面パラメータ<br>
     * @exception なし
     */
    public StudentSelectionListModel load() {

        // モデルの初期化
        StudentSelectionListModel model = new StudentSelectionListModel();

        // 顧客区分は「全て」を選択
        model.setCustomerKbn(NaikaraTalkConstants.CHOICE_ALL_ZERO);

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
    public LinkedHashMap<String, String> selectCodeMst(String category) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            // ロジックの初期化
            StudentSelectionListLogic studentSelectionListLogic = new StudentSelectionListLogic(conn);
            // コード管理マスタを検索
            LinkedHashMap<String, String> codeMap = studentSelectionListLogic.selectCodeMst(category);
            return codeMap;
        } catch (SQLException e) {
            throw new NaiException(e);
        } catch (NaiException e) {
            throw new NaiException(e);
        } finally {
            try {
                conn.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}