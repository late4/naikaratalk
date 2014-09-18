package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.CustomerManagedListLogic;
import com.naikara_talk.model.CustomerManagedListModel;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様管理ページ<br>
 * <b>クラス名称　　　:</b>お客様管理ページクラス。<br>
 * <b>クラス概要　　　:</b>お客様管理ページ初期処理Service。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/20 TECS 新規作成。
 */

public class CustomerManagedListLoadService implements ActionService {

	/**
     * 初期処理<br>
     * <br>
     * 画面項目の初期処理を行う<br>
     * <br>
     * @return CustomerManagedListModel
     */
    public CustomerManagedListModel load() throws NaiException {

    	CustomerManagedListModel model = new CustomerManagedListModel();

        // 顧客区分は「全て」を選択する
        model.setCostomerKbn(NaikaraTalkConstants.CHOICE_ALL_ZERO);
        // 対象期間は「過去３ヶ月」を選択する
        model.setObjectPeriod(CustomerManagedListModel.OBJECT_PERIOD_ONE);

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
    public LinkedHashMap<String, String> selectCodeMst(String category) throws NaiException {

        Connection conn = null;
        try{
            conn = DbUtil.getConnection();

            CustomerManagedListLogic logic = new CustomerManagedListLogic(conn);
            // コード管理マスタ検索
            return logic.selectCodeMst(category);

        } catch ( SQLException se ) {
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

    /**
     * 対象期間を取得する
     * @return LinkedHashMap<String, String>
     * @throws NaiException
     */
    public LinkedHashMap<String, String> selectObjectPeriod() throws NaiException {

    	LinkedHashMap<String, String> hashMap = new LinkedHashMap<String, String>();

    	// 対象期間：過去３ヶ月を設定
       	hashMap.put(CustomerManagedListModel.OBJECT_PERIOD_ONE, CustomerManagedListModel.OBJECT_PERIOD_ONE_NM);

    	// 対象期間：過去６ヶ月を設定
       	hashMap.put(CustomerManagedListModel.OBJECT_PERIOD_TWO, CustomerManagedListModel.OBJECT_PERIOD_TWO_NM);

    	// 対象期間：過去１２ヶ月を設定
       	hashMap.put(CustomerManagedListModel.OBJECT_PERIOD_THREE, CustomerManagedListModel.OBJECT_PERIOD_THREE_NM);

    	// 対象期間：期間指定を設定
       	hashMap.put(CustomerManagedListModel.OBJECT_PERIOD_ZERO, CustomerManagedListModel.OBJECT_PERIOD_ZERO_NM);

        return hashMap;

    }

}
