package com.naikara_talk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dbutil.QueryCondition;
import com.naikara_talk.dto.CodeClassMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DAOクラス<br>
 * <b>クラス名称　　　:</b>コード種別マスタデータ取得クラス<br>
 * <b>クラス概要　　　:</b>コード種別マスタデータ取得DAO<br>
 * <br>
 * <b>著作権　　　　　:</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/05/30 MIRA/TECS 新規作成
 */
public class CodeClassMstDao extends AbstractDao {

    /** コード種別　条件項目　*/
    public static final String COND_CD_CATEGORY = "CD_CATEGORY";
    /** コード種別名　条件項目　*/
    public static final String COND_CD_CATEGORY_JNM = "CD_CATEGORY_JNM";
    /** 並び順　条件項目　*/
    public static final String COND_ORDER_BY = "ORDER_BY";
    /** 汎用コード登録不可フラグ　条件項目　*/
    public static final String COND_MANAGER_INSERT_NG_FLG = "MANAGER_INSERT_NG_FLG";

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public CodeClassMstDao(Connection con) {
        this.conn = con;
    }

    public List<CodeClassMstDto> search(CodeClassMstDto dto)
            throws NaiException {

        ResultSet res = null;

        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT ");
        sb.append("   CD_CATEGORY ");
        sb.append("  ,CD_CATEGORY_JNM ");
        sb.append("  ,ORDER_BY ");
        sb.append("  ,REMARK ");
        sb.append("  ,MANAGER_INSERT_NG_FLG ");
        sb.append("  ,RECORD_VER_NO ");
        sb.append("  ,INSERT_DTTM ");
        sb.append("  ,INSERT_CD ");
        sb.append("  ,UPDATE_DTTM ");
        sb.append("  ,UPDATE_CD ");
        sb.append(" FROM ");
        sb.append("   CODE_CLASS_MST  ");
        sb.append(" WHERE ");
        sb.append("   CD_CATEGORY = ?  ");
        sb.append(" ORDER BY ");
        sb.append("   CD_CATEGORY ");

        try {

            PreparedStatement ps = conn.prepareStatement(sb.toString());

            ps.setString(1, dto.getCdCategory());

            res = ps.executeQuery();

            ArrayList<CodeClassMstDto> list = new ArrayList<CodeClassMstDto>();

            while (res.next()) {

                CodeClassMstDto retDto = new CodeClassMstDto();
                retDto.setCdCategory(res.getString("CD_CATEGORY"));
                retDto.setCdCategoryJnm(res.getString("CD_CATEGORY_JNM"));
                retDto.setOrderBy(res.getInt("ORDER_BY"));
                retDto.setRemark(res.getString("REMARK"));
                retDto.setManagerInsertNgFlg(res.getString("MANAGER_INSERT_NG_FLG"));

                list.add(retDto);
            }

            return list;

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

    /**
     * コード種別マスタデータ取得(TECS)<br>
     * <br>
     * コード種別マスタリストを戻り値で返却する<br>
     * <br>
     * @param conditions
     * @param orderConditions
     * @return CodeClassMstDto
     * @throws NaiException
     */

    public List<CodeClassMstDto> search(ConditionMapper conditions, OrderCondition orderConditions) throws NaiException {

        ResultSet res = null;

        //ArrayListクラスのオブジェクトを生成
        ArrayList<CodeClassMstDto> list = new ArrayList<CodeClassMstDto>();

        //CodeClassMstDtoクラスのオブジェクトを生成
        CodeClassMstDto retDto = new CodeClassMstDto();

        // SQL文の生成
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT ");
        sb.append("   CD_CATEGORY ");
        sb.append("  ,CD_CATEGORY_JNM ");
        sb.append("  ,ORDER_BY ");
        sb.append("  ,REMARK ");
        sb.append("  ,MANAGER_INSERT_NG_FLG ");
        sb.append("  ,RECORD_VER_NO ");
        sb.append("  ,INSERT_DTTM ");
        sb.append("  ,INSERT_CD ");
        sb.append("  ,UPDATE_DTTM ");
        sb.append("  ,UPDATE_CD ");
        sb.append(" FROM ");
        sb.append("   CODE_CLASS_MST  ");

        // where句
        if(!StringUtils.isEmpty(conditions.getConditionString())) {
            sb.append("where ");
            sb.append(conditions.getConditionString());
        }

        // Order By句
        if(!StringUtils.isEmpty(orderConditions.getOrderString())) {
            sb.append(orderConditions.getOrderString());
        }

        try {

            // PreparedStatementの作成
            PreparedStatement ps = conn.prepareStatement(sb.toString());

            // Where句のパラメタの設定
            int i = 0;
            for( QueryCondition cond : conditions.getConditions()){
                for(String val : cond.getValues()){
                    ps.setString(i + 1, val);
                    i++;
                }
            }

            // SQL文の実行
            res = ps.executeQuery();

            // SQL文の実行結果
            while (res.next()) {
                retDto = new CodeClassMstDto();
                retDto.setCdCategory(res.getString("CD_CATEGORY"));                      // コード種別
                retDto.setCdCategoryJnm(res.getString("CD_CATEGORY_JNM"));               // コード種別名
                retDto.setOrderBy(res.getInt("ORDER_BY"));                               // 並び順
                retDto.setRemark(res.getString("REMARK"));                               // 備考
                retDto.setManagerInsertNgFlg(res.getString("MANAGER_INSERT_NG_FLG"));    // 管理ｺｰﾄﾞ登録不可ﾌﾗｸﾞ
                retDto.setRecordVerNo(res.getInt("RECORD_VER_NO"));                      // レコードバージョン番号
                retDto.setInsertDttm(res.getTimestamp("INSERT_DTTM"));                   // 登録日時
                retDto.setInsertCd(res.getString("INSERT_CD"));                          // 登録者コード
                retDto.setUpdateDttm(res.getTimestamp("UPDATE_DTTM"));                   // 更新日時
                retDto.setUpdateCd(res.getString("UPDATE_CD"));                          // 更新者コード

                retDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);           // リターンコード

                // リストへ追加
                list.add(retDto);
            }

            if (list.size() < 1) {
            	retDto = new CodeClassMstDto();
                retDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_NO);
                list.add(retDto);
            }

            if ( ps != null ) {
                ps.close();
            }

            // 返却
            return list;

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
