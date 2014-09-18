package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.StudentMstListLogic;
import com.naikara_talk.model.StudentMstListModel;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守<br>
 * <b>クラス名称　　　:</b>受講者マスタメンテナンス【一覧】初期処理Serviceクラス。<br>
 * <b>クラス概要　　　:</b>受講者マスタメンテナンス【一覧】初期処理Service。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/30 TECS 新規作成。
 */
public class StudentMstListLoadService implements ActionService {

    /**
     * 初期処理<br>
     * <br>
     * 画面項目の初期処理を行う<br>
     * <br>
     * @return SaleGoodsMstListModel
     */
    public StudentMstListModel load() {
        StudentMstListModel model = new StudentMstListModel();
        // 処理区分は「照会」を選択する
        model.setProcessKbn_rdl(StudentMstListModel.PROS_KBN_REF);
        // お客様区分は「個人」を選択する
        model.setCustomerKbn_rdl(StudentMstListModel.CUSTOMER_KBN_SELF);

        return model;
    }

    /**
     * コード管理マスタからデータ取得処理。<br>
     * <br>
     * @param category
     *            汎用コード
     * @return LinkedHashMap<String, String>
     * @throws NaiException
     */
    public LinkedHashMap<String, String> selectCodeMst(String category) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            StudentMstListLogic studentMstListLogic = new StudentMstListLogic(conn);
            // コード管理マスタ検索
            return studentMstListLogic.selectCodeMst(category);
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
