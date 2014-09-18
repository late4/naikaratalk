package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.SaleGoodsMstListLogic;
import com.naikara_talk.model.SaleGoodsMstListModel;
import com.naikara_talk.util.NaikaraTalkConstants;


/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>販売商品マスタメンテナンス(一覧)。<br>
 * <b>クラス名称　　　:</b>販売商品マスタメンテナンス初期処理Serviceクラス。<br>
 * <b>クラス概要　　　:</b>販売商品マスタメンテナンス初期処理Service。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/06/18 TECS 新規作成。
 */
public class SaleGoodsMstListLoadService implements ActionService {


    /**
     * 初期処理<br>
     * <br>
     * 画面項目の初期処理を行う<br>
     * <br>
     * @return SaleGoodsMstListModel
     */
    public SaleGoodsMstListModel load(){
        SaleGoodsMstListModel model=new SaleGoodsMstListModel();
        // 処理区分は「照会」を選択する
        model.setProcessKbn(SaleGoodsMstListModel.PROS_KBN_REF);
        // 利用状態は「利用可」を選択する
        model.setUseKbn(NaikaraTalkConstants.USE_KBN_OK);

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
    public LinkedHashMap<String, String> selectCodeMst(String category)
            throws Exception {

        Connection conn = null;
        try{
            conn = DbUtil.getConnection();

            SaleGoodsMstListLogic saleGoodsMstListLogic = new SaleGoodsMstListLogic(conn);
            // コード管理マスタ検索
            return saleGoodsMstListLogic.selectCodeMst(category);

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

}
