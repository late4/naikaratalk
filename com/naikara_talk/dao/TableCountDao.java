package com.naikara_talk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.QueryCondition;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DAOクラス<br>
 * <b>クラス名称　　　:</b>データ件数取得クラス<br>
 * <b>クラス概要　　　:</b>データ件数取得DAO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴　　　　:</b>2013/05/21 TECS 新規作成
 */
public class TableCountDao extends AbstractDao {

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public TableCountDao(Connection con) {
        this.conn = con;
    }

    /**
     * データ件数取得<br>
     * <br>
     * データ件数を戻り値で返却する<br>
     * <br>
     *
     * @param tableName
     * @param conditions
     * @return int:DataRowCount
     * @throws NaiException
     */
    public int rowCount(String tableName, ConditionMapper conditions) throws NaiException {

        // 戻り値の初期化
        int rowCount = NaikaraTalkConstants.RETURN_CD_DATA_ERR; // データ件数

        // 引数のパラメータチェック
        if (tableName == null || "".equals(tableName)) {
            return rowCount;
        }

        ResultSet res = null;

        StringBuffer sb = new StringBuffer();

        // データ件数の取得処理
        sb.append(" SELECT ");
        sb.append("   COUNT(*)  ");
        sb.append(" FROM ");
        sb.append(" ").append(tableName).append(" ");
        if(!StringUtils.isEmpty(conditions.getConditionString())) {
            sb.append("where ");
            sb.append(conditions.getConditionString());
	    }

        try {

            PreparedStatement ps = conn.prepareStatement(sb.toString());

            // パラメタの設定
            int i = 0;
            for( QueryCondition cond : conditions.getConditions()){
                for(String val : cond.getValues()){
                    ps.setString(i + 1, val);
                    i++;
                }
            }

            //実行
            res = ps.executeQuery();

            rowCount = NaikaraTalkConstants.RETURN_CD_DATA_ERR;      // データ件数
            if (res.next()) {
                rowCount = res.getInt(1);
            }

            return rowCount;

        } catch (SQLException se) {
            throw new NaiException(se);
        } finally {
            try{
                if ( res != null ) {
                    res.close();
                }
            }catch(SQLException se ){
                throw new NaiException(se);
            }
        }
    }

}
