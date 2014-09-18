package com.naikara_talk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dbutil.QueryCondition;
import com.naikara_talk.dto.CdManagMstCdClassMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DAOクラス<br>
 * <b>クラス名称　　　:</b>コード管理マスタ、コード種別マスタデータ取得クラス<br>
 * <b>クラス概要　　　:</b>コード管理マスタ、コード種別マスタデータ取得DAO<br>
 * <br>
 * <b>著作権　　　　　:</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author MIRA/TECS
 * <b>変更履歴　　　　:</b>2013/05/30 TECS 新規作成
 */
public class CdManagMstCdClassMstDao extends AbstractDao {

	/** コード管理マスタ.コード種別　条件項目　*/
	public static final String COND_CD_CATEGORY_M = "CODE_MANAG_MST.CD_CATEGORY";

	/** コード管理マスタ.汎用コード　条件項目　*/
	public static final String COND_MANAGER_CD = "CODE_MANAG_MST.MANAGER_CD";

	/** コード管理マスタ.汎用フィールド　条件項目　*/
	public static final String COND_MANAGER_NM = "CODE_MANAG_MST.MANAGER_NM";

	/** コード管理マスタ.並び順　条件項目　*/
	public static final String COND_ORDER_BY_M = "CODE_MANAG_MST.ORDER_BY";

	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public CdManagMstCdClassMstDao(Connection con) {
		this.conn = con;
	}


    /**
     * コード管理マスタ、コード種別マスタデータ取得<br>
     * <br>
     * コード管理マスタ、コード種別マスタリストを戻り値で返却する<br>
     * <br>
     * @param conditions 検索条件<br>
     * @param orderConditions 並び順条件<br>
     * @return list 検索結果<br>
     * @exception NaiException
     */
    public List<CdManagMstCdClassMstDto> search(ConditionMapper conditions,
            OrderCondition orderConditions) throws NaiException {

        ResultSet res = null;

        // コード管理マスタ,コード種別マスタの全項目取得
        StringBuffer sb = new StringBuffer();
        sb.append(" select ");
        sb.append("   CODE_MANAG_MST.CD_CATEGORY ");            // コード管理マスタ.コード種別
        sb.append("  ,CODE_MANAG_MST.MANAGER_CD ");             // コード管理マスタ.汎用コード
        sb.append("  ,CODE_MANAG_MST.MANAGER_NM ");             // コード管理マスタ.汎用フィールド
        sb.append("  ,CODE_MANAG_MST.ORDER_BY ");               // コード管理マスタ.並び順
        sb.append("  ,CODE_MANAG_MST.REMARK ");                 // コード管理マスタ.備考
        sb.append("  ,CODE_MANAG_MST.STUDENT_MST_ENM ");        // コード管理マスタ.受講者マスタ英語項目名
        sb.append("  ,CODE_MANAG_MST.MARK ");                   // コード管理マスタ.表示用点数
        sb.append("  ,CODE_MANAG_MST.SYSTEM_DEL_NG_FLG ");      // コード管理マスタ.システム削除不可フラグ
        sb.append("  ,CODE_MANAG_MST.RECORD_VER_NO ");          // コード管理マスタ.レコードバージョン番号
        sb.append("  ,CODE_MANAG_MST.INSERT_DTTM ");            // コード管理マスタ.登録日時
        sb.append("  ,CODE_MANAG_MST.INSERT_CD ");              // コード管理マスタ.登録者コード
      	sb.append("  ,CODE_MANAG_MST.UPDATE_DTTM ");            // コード管理マスタ.更新日時
        sb.append("  ,CODE_MANAG_MST.UPDATE_CD ");              // コード管理マスタ.更新者コード
        sb.append("  ,CODE_CLASS_MST.CD_CATEGORY ");            // コード種別マスタ.コード種別
        sb.append("  ,CODE_CLASS_MST.CD_CATEGORY_JNM ");        // コード種別マスタ.コード種別名
        sb.append("  ,CODE_CLASS_MST.ORDER_BY ");               // コード種別マスタ.並び順
        sb.append("  ,CODE_CLASS_MST.REMARK ");                 // コード種別マスタ.備考
        sb.append("  ,CODE_CLASS_MST.MANAGER_INSERT_NG_FLG ");  // コード種別マスタ.汎用ｺｰﾄﾞ登録不可ﾌﾗｸﾞ
        sb.append("  ,CODE_CLASS_MST.RECORD_VER_NO ");          // コード種別マスタ.レコードバージョン番号
        sb.append("  ,CODE_CLASS_MST.INSERT_DTTM ");            // コード種別マスタ.登録日時
        sb.append("  ,CODE_CLASS_MST.INSERT_CD ");              // コード種別マスタ.登録者コード
        sb.append("  ,CODE_CLASS_MST.UPDATE_DTTM ");            // コード種別マスタ.更新日時
        sb.append("  ,CODE_CLASS_MST.UPDATE_CD ");              // コード種別マスタ.更新者コード
        sb.append(" from ");
        sb.append("   CODE_MANAG_MST ");                        // TBL:コード管理マスタ
        sb.append(" left join CODE_CLASS_MST ");                // TBL:コード種別マスタ
        sb.append(" on CODE_MANAG_MST.CD_CATEGORY = CODE_CLASS_MST.CD_CATEGORY ");


        // where句の作成
        if (!StringUtils.isEmpty(conditions.getConditionString())) {
            sb.append("where ");
            sb.append(conditions.getConditionString());
        }

        // Order By句の作成
        if (!StringUtils.isEmpty(orderConditions.getOrderString())) {
            sb.append(orderConditions.getOrderString());
        }

        try {

            // PreparedStatementの作成
            PreparedStatement ps = conn.prepareStatement(sb.toString());

            // パラメタの設定
            int i = 0;
            for (QueryCondition cond : conditions.getConditions()) {
                for (String val : cond.getValues()) {
                    ps.setString(i + 1, val);
                    i++;
                }
            }

            // SQL文の実行
            res = ps.executeQuery();

            ArrayList<CdManagMstCdClassMstDto> list = new ArrayList<CdManagMstCdClassMstDto>();

            while (res.next()) {
            	CdManagMstCdClassMstDto dto = new CdManagMstCdClassMstDto();

                dto.setCdCategory(res.getString("CD_CATEGORY"));                       // コード種別
                dto.setManagerCd(res.getString("MANAGER_CD"));                         // 汎用コード
                dto.setManagerNm(res.getString("MANAGER_NM"));                         // 汎用フィールド
                dto.setOrderBy(res.getInt("ORDER_BY"));                                // 並び順
                dto.setRemark(res.getString("REMARK"));                                // 備考
                dto.setStudentMstEnm(res.getString("STUDENT_MST_ENM"));                // 受講者マスタ英語項目名
                dto.setMark(res.getString("MARK"));                                    // 表示用点数
                dto.setSystemDelNgFlg(res.getString("SYSTEM_DEL_NG_FLG"));             // システム削除不可フラグ
                dto.setRecordVerNoM(res.getInt("RECORD_VER_NO"));                      // レコードバージョン番号
                dto.setInsertDttmM(res.getTimestamp("INSERT_DTTM"));                   // 登録日時
                dto.setInsertCdM(res.getString("INSERT_CD"));                          // 登録者コード
                dto.setUpdateDttmM(res.getTimestamp("UPDATE_DTTM"));                   // 更新日時
                dto.setUpdateCdM(res.getString("UPDATE_CD"));                          // 更新者コード
                dto.setCdCategoryC(res.getString("CD_CATEGORY"));                      // コード種別
                dto.setCdCategoryJnm(res.getString("CD_CATEGORY_JNM"));                // コード種別名
                dto.setOrderByC(res.getInt("ORDER_BY"));                               // 並び順
                dto.setRemarkC(res.getString("REMARK"));                               // 備考
                dto.setManagerInsertNgFlgC(res.getString("MANAGER_INSERT_NG_FLG"));    // 管理ｺｰﾄﾞ登録不可ﾌﾗｸﾞ
                dto.setRecordVerNoC(res.getInt("RECORD_VER_NO"));                      // レコードバージョン番号
                dto.setInsertDttmC(res.getTimestamp("INSERT_DTTM"));                   // 登録日時
                dto.setInsertCdC(res.getString("INSERT_CD"));                          // 登録者コード
                dto.setUpdateDttmC(res.getTimestamp("UPDATE_DTTM"));                   // 更新日時
                dto.setUpdateCdC(res.getString("UPDATE_CD"));                          // 更新者コード

                dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);            // リターンコード

                list.add(dto);
            }

            if (list.size() < 1) {
            	CdManagMstCdClassMstDto dto = new CdManagMstCdClassMstDto();
            	dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_NO);
                list.add(dto);
            }

            // 返却値
            return list;

        } catch (SQLException se) {
            throw new NaiException(se);
        } finally {
            try {
                if (res != null) {
                    res.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new NaiException(e);
            }
        }
    }



}
