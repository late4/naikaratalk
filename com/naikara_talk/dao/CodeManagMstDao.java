package com.naikara_talk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dbutil.QueryCondition;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DAOクラス<br>
 * <b>クラス名称　　　:</b>コード管理マスタデータアクセスクラスクラス<br>
 * <b>クラス概要　　　:</b>コード管理マスタの取得、登録、更新、削除を行う<br>
 * <br>
 * <b>著作権　　　　　:</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author MIRA/TECS
 * <b>変更履歴　　　　:</b>2013/05/30 MIRA/TECS 新規作成
 */
public class CodeManagMstDao extends AbstractDao {

    protected Logger log = Logger.getLogger(this.getClass());

    /** コード種別　条件項目　*/
    public static final String COND_CD_CATEGORY = "CD_CATEGORY";

    /** 汎用コード　条件項目　*/
    public static final String COND_MANAGER_CD = "MANAGER_CD";

    /** 汎用フィールド　条件項目　*/
    public static final String COND_MANAGER_NM = "MANAGER_NM";

    /** 並び順　条件項目　*/
    public static final String COND_ORDER_BY = "ORDER_BY";

    /** 並び順　最小値　*/
    public static final int MIN_ORDER_BY = 1;

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public CodeManagMstDao(Connection con) {
        this.conn = con;
    }


    /**
     * コード管理マスタからデータ取得処理。(MIRA)
     * @param conditions
     * @param orderConditions
     * @return CodeManagMstDto
     * @throws NaiException
     */
    public List<CodeManagMstDto> search(ConditionMapper conditions)
            throws NaiException {

        ResultSet res = null;

        // SQL文作成
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT ");
        sb.append("   CD_CATEGORY ");
        sb.append("  ,MANAGER_CD ");
        sb.append("  ,MANAGER_NM ");
        sb.append("  ,ORDER_BY ");
        sb.append("  ,REMARK ");
        sb.append("  ,STUDENT_MST_ENM ");
        sb.append("  ,MARK ");
        sb.append("  ,SYSTEM_DEL_NG_FLG ");
        sb.append("  ,RECORD_VER_NO ");
        sb.append("  ,INSERT_DTTM ");
        sb.append("  ,INSERT_CD ");
      	sb.append("  ,UPDATE_DTTM ");
        sb.append("  ,UPDATE_CD ");
        sb.append(" FROM ");
        sb.append("   CODE_MANAG_MST ");// コード管理マスタ

        if(!StringUtils.isEmpty(conditions.getConditionString())) {
    		sb.append(" WHERE ");
    		sb.append(conditions.getConditionString());
        }
        sb.append(" ORDER BY ");
        sb.append("   ORDER_BY ");


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

            // SQL文を実行
            res = ps.executeQuery();
            // 戻りLinkedHashMap作成
            List<CodeManagMstDto> list = new ArrayList<CodeManagMstDto>();
            while (res.next()) {
            	CodeManagMstDto dto = new CodeManagMstDto();
            	dto.setCdCategory(res.getString("CD_CATEGORY"));
            	dto.setManagerCd(res.getString("MANAGER_CD"));
            	dto.setManagerNm(res.getString("MANAGER_NM"));
            	dto.setOrderBy(res.getInt("ORDER_BY"));
            	dto.setRemark(res.getString("REMARK"));
            	dto.setStudentMstEnm(res.getString("STUDENT_MST_ENM"));
            	dto.setMark(res.getString("MARK"));
            	dto.setSystemDelNgFlg(res.getString("SYSTEM_DEL_NG_FLG"));
            	dto.setRecordVerNo(res.getInt("RECORD_VER_NO"));
            	dto.setInsertDttm(res.getTimestamp("INSERT_DTTM"));
            	dto.setInsertCd(res.getString("INSERT_CD"));
            	dto.setUpdateDttm(res.getTimestamp("UPDATE_DTTM"));
            	dto.setUpdateCd(res.getString("UPDATE_CD"));
                list.add(dto);
            }
            // 戻り
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
     * コード管理マスタデータ取得(TECS)<br>
     * <br>
     * コード管理マスタリストを戻り値で返却する<br>
     * <br>
     * @param conditions 検索条件<br>
     * @param orderConditions 並び順条件<br>
     * @return list 検索結果<br>
     * @exception NaiException
     */
    public List<CodeManagMstDto> search(ConditionMapper conditions,
            OrderCondition orderConditions) throws NaiException {

        ResultSet res = null;

        // コード管理マスタの全項目取得
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT ");
        sb.append("   CD_CATEGORY ");          // コード種別
        sb.append("  ,MANAGER_CD ");           // 汎用コード
        sb.append("  ,MANAGER_NM ");           // 汎用フィールド
        sb.append("  ,ORDER_BY ");             // 並び順
        sb.append("  ,REMARK ");               // 備考
        sb.append("  ,STUDENT_MST_ENM ");      // 受講者マスタ英語項目名
        sb.append("  ,MARK ");                 // 表示用点数
        sb.append("  ,SYSTEM_DEL_NG_FLG ");    // システム削除不可フラグ
        sb.append("  ,RECORD_VER_NO ");        // レコードバージョン番号
        sb.append("  ,INSERT_DTTM ");          // 登録日時
        sb.append("  ,INSERT_CD ");            // 登録者コード
      	sb.append("  ,UPDATE_DTTM ");          // 更新日時
        sb.append("  ,UPDATE_CD ");            // 更新者コード
        sb.append(" FROM ");
        sb.append("   CODE_MANAG_MST ");    // コード管理マスタ


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

            ArrayList<CodeManagMstDto> list = new ArrayList<CodeManagMstDto>();

            while (res.next()) {
                CodeManagMstDto dto = new CodeManagMstDto();

                dto.setCdCategory(res.getString("CD_CATEGORY"));            // コード種別
                dto.setManagerCd(res.getString("MANAGER_CD"));              // 汎用コード
                dto.setManagerNm(res.getString("MANAGER_NM"));              // 汎用フィールド
                dto.setOrderBy(res.getInt("ORDER_BY"));                     // 並び順
                dto.setRemark(res.getString("REMARK"));                     // 備考
                dto.setStudentMstEnm(res.getString("STUDENT_MST_ENM"));     // 受講者マスタ英語項目名
                dto.setMark(res.getString("MARK"));                         // 表示用点数
                dto.setSystemDelNgFlg(res.getString("SYSTEM_DEL_NG_FLG"));  // システム削除不可フラグ
                dto.setRecordVerNo(res.getInt("RECORD_VER_NO"));            // レコードバージョン番号
                dto.setInsertDttm(res.getTimestamp("INSERT_DTTM"));         // 登録日時
                dto.setInsertCd(res.getString("INSERT_CD"));                // 登録者コード
                dto.setUpdateDttm(res.getTimestamp("UPDATE_DTTM"));         // 更新日時
                dto.setUpdateCd(res.getString("UPDATE_CD"));                // 更新者コード

                dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES); // リターンコード

                list.add(dto);
            }

            if (list.size() < 1) {
            	CodeManagMstDto retDto = new CodeManagMstDto();
                retDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_NO);
                list.add(retDto);
            }

            // 返却値
            return list;

        } catch (SQLException se) {
            log.info(se);
            throw new NaiException(se);
        } finally {
            try {
                if (res != null) {
                    res.close();
                }
            } catch (SQLException e) {
                log.info(e);
                e.printStackTrace();
                throw new NaiException(e);
            }
        }
    }

    /**
     * コード管理マスタ登録処理(TECS)<br>
     * <br>
     * コード管理マスタの登録を行う<br>
     * <br>
     * @param dto 登録データ
     * @return insertRowCount 登録件数
     * @exception NaiException
     */
    public int insert(CodeManagMstDto dto) throws NaiException {

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        int insertRowCount = NaikaraTalkConstants.RETURN_CD_ERR_INS; // 登録データ件数

        // コード管理マスタのデータチェック
        if (!checkDto(dto)) {
            return insertRowCount;
        }

        StringBuffer sb = new StringBuffer();

        // データ登録処理
        sb.append("insert into ");
        sb.append(" CODE_MANAG_MST ");
        sb.append(" ( ");
        sb.append(" CD_CATEGORY ");
        sb.append(",MANAGER_CD ");
        sb.append(",MANAGER_NM ");
        sb.append(",ORDER_BY ");
        sb.append(",REMARK ");
        sb.append(",STUDENT_MST_ENM ");
        sb.append(",MARK ");
        sb.append(",SYSTEM_DEL_NG_FLG ");
        sb.append(",RECORD_VER_NO ");
        sb.append(",INSERT_DTTM ");
        sb.append(",INSERT_CD ");
        sb.append(",UPDATE_DTTM ");
        sb.append(",UPDATE_CD ");
        sb.append(" ) value ( ");
        sb.append(" ? ");                    // 01.CD_CATEGORY
        sb.append(",? ");                    // 02.MANAGER_CD
        sb.append(",? ");                    // 03.MANAGER_NM
        sb.append(",? ");                    // 04.ORDER_BY
        sb.append(",? ");                    // 05.REMARK
        sb.append(",? ");                    // 06.STUDENT_MST_ENM
        sb.append(",? ");                    // 07.MARK
        sb.append(",? ");                    // 08.SYSTEM_DEL_NG_FLG
        sb.append(",? ");                    // 09.RECORD_VER_NO
        sb.append(",? ");                    // 10.INSERT_DTTM
        sb.append(",? ");                    // 11.INSERT_CD
        sb.append(",? ");                    // 12.UPDATE_DTTM
        sb.append(",? ");                    // 13.UPDATE_CD
        sb.append(" ) ");

        try {

            PreparedStatement ps = conn.prepareStatement(sb.toString());

            ps.setString(1, dto.getCdCategory());                     // 01.コード種別
            ps.setString(2, dto.getManagerCd());                      // 02.汎用コード
            ps.setString(3, dto.getManagerNm());                      // 03.汎用フィールド
            ps.setString(4, String.valueOf(dto.getOrderBy()));        // 04.並び順
            ps.setString(5, dto.getRemark());                         // 05.備考
            ps.setString(6, dto.getStudentMstEnm());                  // 06.受講者マスタ英語項目名
            ps.setString(7, dto.getMark());                           // 07.表示用点数
            ps.setString(8, dto.getSystemDelNgFlg());                 // 08.システム削除不可フラグ

            ps.setString(9, String.valueOf(dto.getRecordVerNo()));    // 09.レコードバージョン番号
            ps.setString(10, String.valueOf(Timestamp.valueOf(
            		sdf.format(cal.getTime()))));                     // 10.登録日時
            ps.setString(11, dto.getInsertCd());                      // 11.登録者コード
            ps.setString(12, String.valueOf(Timestamp.valueOf(
            		sdf.format(cal.getTime()))));                     // 12.更新日時
            ps.setString(13, dto.getUpdateCd());                      // 13.更新者コード

            insertRowCount = ps.executeUpdate();

            return insertRowCount;

        } catch (SQLException se) {
            se.printStackTrace();
            throw new NaiException(se);
        }
    }


    /**
     * コード管理マスタ更新処理(TECS)<br>
     * <br>
     * コード管理マスタの更新を行う<br>
     * <br>
     * @param dto 更新データ<br>
     * @return updatedRowCount 更新件数<br>
     * @exception NaiException
     */
    public int update(CodeManagMstDto dto) throws NaiException {

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        int updatedRowCount = NaikaraTalkConstants.RETURN_CD_ERR_UPD; // 更新データ件数

        // コード管理マスタのデータチェック
        if (!checkDto(dto)) {
            return updatedRowCount;
        }

        StringBuffer sb = new StringBuffer();

        // データ更新処理
        sb.append("update ");
        sb.append(" CODE_MANAG_MST ");           // TBL:コード管理マスタ
        sb.append("set ");
        sb.append(" MANAGER_NM = ? ");           // 01.汎用フィールド
        sb.append(",ORDER_BY = ? ");             // 02.並び順
        sb.append(",REMARK = ? ");               // 03.備考
        sb.append(",STUDENT_MST_ENM = ? ");      // 04.受講者マスタ英語項目名
        sb.append(",MARK = ? ");                 // 05.表示用点数
        sb.append(",SYSTEM_DEL_NG_FLG = ? ");    // 06.システム削除不可フラグ
        sb.append(",RECORD_VER_NO = ? ");        // 07.レコードバージョン番号
        sb.append(",UPDATE_DTTM = ? ");          // 08.更新日時
        sb.append(",UPDATE_CD = ? ");            // 09.更新者コード
        sb.append(" where ");
        sb.append("   CD_CATEGORY = ? ");        // 10.KEY:コード種別
        sb.append(" and ");
        sb.append("   MANAGER_CD = ? ");         // 11.KEY:汎用コード
        sb.append("and ");
        sb.append("   RECORD_VER_NO = ? ");      // 12.レコードバージョン番号


        try {
            PreparedStatement ps = conn.prepareStatement(sb.toString());

            ps.setString(1, dto.getManagerNm());                          // 汎用フィールド
            ps.setString(2, String.valueOf(dto.getOrderBy()));            // 並び順
            ps.setString(3, dto.getRemark());                             // 備考
            ps.setString(4, dto.getStudentMstEnm());                      // 受講者マスタ英語項目名
            ps.setString(5, dto.getMark());                               // 表示用点数
            ps.setString(6, dto.getSystemDelNgFlg());                     // システム削除不可フラグ
            ps.setString(7, String.valueOf(dto.getRecordVerNo() + 1));    // レコードバージョン番号
            ps.setString(8, String.valueOf(Timestamp.valueOf(
            		sdf.format(cal.getTime()))));                         // 更新日時
            ps.setString(9, dto.getUpdateCd());                           // 更新者コード

            ps.setString(10, dto.getCdCategory());                        // KEY:コード種別
            ps.setString(11, dto.getManagerCd());                         // KEY:汎用コード
            ps.setString(12, String.valueOf(dto.getRecordVerNo()));       // レコードバージョン番号

            updatedRowCount = ps.executeUpdate();

            return updatedRowCount;

        } catch (SQLException se) {
            se.printStackTrace();
            throw new NaiException(se);
        }
    }


    /**
     * コード管理マスタ削除処理(TECS)<br>
     * <br>
     * コード管理マスタの削除を行う<br>
     * <br>
     * @param dto 更新データ<br>
     * @return deleteRowCount 削除件数<br>
     * @exception NaiException
     */
    public int delete(CodeManagMstDto dto) throws NaiException {

        int deleteRowCount = NaikaraTalkConstants.RETURN_CD_ERR_DEL; // 削除データ件数

        StringBuffer sb = new StringBuffer();

        // データ削除処理
        sb.append("delete ");
        sb.append("from CODE_MANAG_MST ");
        sb.append(" where ");
        sb.append("   CD_CATEGORY = ? ");        // 1.KEY:コード種別
        sb.append(" and ");
        sb.append("   MANAGER_CD = ? ");         // 2.KEY:汎用コード
        sb.append("and ");
        sb.append("   RECORD_VER_NO = ? ");      // 3.レコードバージョン番号


        try{

            PreparedStatement ps = conn.prepareStatement(sb.toString());

            ps.setString( 1, dto.getCdCategory());
            ps.setString( 2, dto.getManagerCd());
            ps.setString( 3, String.valueOf(dto.getRecordVerNo()));

            deleteRowCount= ps.executeUpdate();

            return deleteRowCount;

        } catch (SQLException se) {
            se.printStackTrace();
            throw new NaiException(se);
        }

    }


    /**
     * コード管理マスタ更新データチェック(TECS)<br>
     * <br>
     * コード管理マスタの更新データをチェックし<br>
     * エラー有の場合は、falseを返す<br>
     * エラー無の場合は、trueを返す<br>
     * <br>
     * @param dto チェックデータ
     * @return boolean チェック結果
     * @exception NaiException
     */
    private boolean checkDto(CodeManagMstDto dto) throws NaiException {

        // コード種別のチェック
        if (dto.getCdCategory() == null || "".equals(dto.getCdCategory())) {
            return false;
        }

        // 汎用コードのチェック
        if (dto.getManagerCd() == null || "".equals(dto.getManagerCd())) {
            return false;
        }

        // 汎用フィールドのチェック
        if (dto.getManagerNm() == null || "".equals(dto.getManagerNm())) {
            return false;
        }

        //  並び順のチェック
        if (dto.getOrderBy() < MIN_ORDER_BY) {
            return false;
        }

        // システム削除不可フラグのチェック
        if (dto.getSystemDelNgFlg() == null || "".equals(dto.getSystemDelNgFlg())) {
            return false;
        }

        // 更新者コードのチェック
        if (dto.getUpdateCd() == null || "".equals(dto.getUpdateCd())) {
            return false;
        }

        return true;

    }


}
