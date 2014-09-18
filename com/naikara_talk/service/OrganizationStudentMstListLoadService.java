package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.OrganizationStudentMstListLogic;
import com.naikara_talk.model.OrganizationStudentMstListModel;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>組織_初期処理<br>
 * <b>クラス名称　　　:</b>システム受講者登録(一覧)Serviceクラス。<br>
 * <b>クラス概要　　　:</b>組織の社員情報(受講者)の新規アカウント(一覧)。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/15 TECS 新規作成。
 */
public class OrganizationStudentMstListLoadService implements ActionService {

    /**
     * 初期処理<br>
     * <br>
     * 画面項目の初期処理を行う<br>
     * <br>
     * @param なし<br>
     * @return OrganizationStudentMstListModel<br>
     * @exception なし
     */
    public OrganizationStudentMstListModel load() {
        OrganizationStudentMstListModel model = new OrganizationStudentMstListModel();
        // 処理区分は「新規」を選択する
        model.setProcessKbn(OrganizationStudentMstListModel.PROS_KBN_ADD);
        // 利用状態は「利用可」を選択する
        model.setUseKbn(NaikaraTalkConstants.USE_KBN_OK);

        return model;
    }

    /**
     * コード管理マスタからデータ取得処理。<br>
     * <br>
     * コード管理マスタからデータ取得処理。<br>
     * <br>
     * @param category 汎用コード <br>
     * @return LinkedHashMap<String, String><br>
     * @exception Exception
     */
    public LinkedHashMap<String, String> selectCodeMst(String category) throws NaiException {
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            OrganizationStudentMstListLogic organizationStudentMstListLogic = new OrganizationStudentMstListLogic(conn);
            // コード管理マスタ検索
            return organizationStudentMstListLogic.selectCodeMst(category);
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
