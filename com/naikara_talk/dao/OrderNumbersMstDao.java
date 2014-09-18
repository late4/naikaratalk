package com.naikara_talk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dbutil.QueryCondition;
import com.naikara_talk.dto.OrderNumbersMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraTalkConstants;


/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DAOクラス<br>
 * <b>クラス名称　　　:</b>採番マスタ自動採番クラス<br>
 * <b>クラス概要　　　:</b>採番マスタ自動採番DAO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/05/23 TECS 新規作成
 */
public class OrderNumbersMstDao extends AbstractDao {

    /** 採番ID　条件項目　*/
    public static final String COND_ORDER_NUMBERS_ID = "ORDER_NUMBERS_ID";
    /** 年月　条件項目　*/
    public static final String COND_YYMM = "YYMM";
    /** シーケンス　条件項目　*/
    public static final String COND_SEQ = "SEQ";
    /** 有効桁数　条件項目　*/
    public static final String COND_EFFECTIVE_DIGIT_NUM = "EFFECTIVE_DIGIT_NUM";


	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public OrderNumbersMstDao(Connection con) {
		this.conn = con;
	}


	/**
	 * 採番データ取得<br>
	 * <br>
	 * 採番マスタからデータを取得する<br>
	 * <br>
	 * @param conditions
	 * @return OrderBy
	 * @throws NaiException
	 */
	public ArrayList<OrderNumbersMstDto> searchWhere(ConditionMapper conditions, OrderCondition OrderBy) throws NaiException {

		// 戻り値とリターンコードの初期化
        ArrayList<OrderNumbersMstDto> list = null;  // 採番マスタリスト
		OrderNumbersMstDto dto;    //採番DTO

		ResultSet res = null;

		StringBuffer sb = new StringBuffer();

		//データ取得処理
		sb.append(" select ");
		sb.append("   ORDER_NUMBERS_ID ");
		sb.append("  ,YYMM ");
		sb.append("  ,SEQ ");
		sb.append("  ,EFFECTIVE_DIGIT_NUM ");
		sb.append("  ,RECORD_VER_NO ");
		sb.append("  ,INSERT_DTTM ");
		sb.append("  ,INSERT_CD ");
		sb.append("  ,UPDATE_DTTM ");
		sb.append("  ,UPDATE_CD ");
		sb.append(" from ");
		sb.append("   ORDER_NUMBERS_MST ");

        // 抽出条件の設定
        if (!StringUtils.isEmpty(conditions.getConditionString())) {
            sb.append("where ");
            sb.append(conditions.getConditionString());
        }
        // 並び順の設定
        if (!StringUtils.isEmpty(OrderBy.getOrderString())) {
            sb.append(OrderBy.getOrderString());
        }


		try{

            PreparedStatement ps = conn.prepareStatement(sb.toString());

            // パラメタの設定
            int i = 0;
            for (QueryCondition cond : conditions.getConditions()) {
                for (String val : cond.getValues()) {
                    ps.setString(i + 1, val);
                    i++;
                }
            }

            // SQL文を実行
            res = ps.executeQuery();

            list = new ArrayList<OrderNumbersMstDto>();
            while (res.next()) {

                dto = new OrderNumbersMstDto();

                dto.setOrderNumbersId(res.getString("ORDER_NUMBERS_ID"));
                dto.setYymm(res.getString("YYMM"));
                dto.setSeq(res.getInt("SEQ"));
                dto.setEffectiveDigitNum(res.getInt("EFFECTIVE_DIGIT_NUM"));
                dto.setRecordVerNo(res.getInt("RECORD_VER_NO"));
                dto.setInsertDttm(res.getTimestamp("INSERT_DTTM"));
                dto.setInsertCd(res.getString("INSERT_CD"));
                dto.setUpdateDttm(res.getTimestamp("UPDATE_DTTM"));
                dto.setUpdateCd(res.getString("UPDATE_CD"));

                dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);
                list.add(dto);
            }

            if (list.size() < 1) {
                list = new ArrayList<OrderNumbersMstDto>();
                dto = new OrderNumbersMstDto();
                dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_NO);
                list.add(dto);
                return list;
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
	 * 採番データ取得<br>
	 * <br>
	 * 採番マスタからデータを取得する<br>
	 * <br>
	 * @param orderNumId
	 * @return seqNum
	 * @throws NaiException
	 */
	public OrderNumbersMstDto search(String orderNumId) throws NaiException {

		OrderNumbersMstDto dto;    //採番DTO

		ResultSet res = null;

		StringBuffer sb = new StringBuffer();

		//データ取得処理
		sb.append(" SELECT ");
		sb.append("   ORDER_NUMBERS_ID ");
		sb.append("  ,YYMM ");
		sb.append("  ,SEQ ");
		sb.append("  ,EFFECTIVE_DIGIT_NUM ");
		sb.append("  ,RECORD_VER_NO ");
		sb.append(" FROM ");
		sb.append("   ORDER_NUMBERS_MST ");
		sb.append(" WHERE ");
		sb.append("   ORDER_NUMBERS_ID = ? ");


		try{
			dto = new OrderNumbersMstDto();
			dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_NO);

			PreparedStatement ps = conn.prepareStatement(sb.toString());
			ps.setString(1, orderNumId);
			res = ps.executeQuery();
			if(res.next()) {
				dto.setOrderNumbersId(res.getString("ORDER_NUMBERS_ID"));
				dto.setYymm(res.getString("YYMM"));
				dto.setSeq(res.getInt("SEQ"));
				dto.setEffectiveDigitNum(res.getInt("EFFECTIVE_DIGIT_NUM"));
				dto.setRecordVersionNum(res.getInt("RECORD_VER_NO"));

				dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);

			}

			return dto;

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
	 * 採番データ更新<br>
	 * <br>
	 * 採番マスタのデータ更新を行う<br>
	 * <br>
	 * @param OrderNumbersMstDto
	 * @return updatedRowCount
	 * @throws NaiException
	 */
	public int updateData(OrderNumbersMstDto dto) throws NaiException {

		int updatedRowCount=NaikaraTalkConstants.RETURN_CD_ERR_UPD; //更新データ件数

		StringBuffer sb = new StringBuffer();

		//データ更新処理
		sb.append(" UPDATE ");
		sb.append("   ORDER_NUMBERS_MST ");
		sb.append(" SET ");
		sb.append("  SEQ = LAST_INSERT_ID(SEQ+1)  ");
		sb.append(" WHERE ");
		sb.append("   ORDER_NUMBERS_ID = ? ");

		try{

			PreparedStatement ps = conn.prepareStatement(sb.toString());
			ps.setString(1, dto.getOrderNumbersId());

			updatedRowCount= ps.executeUpdate();

			return updatedRowCount;

		} catch ( SQLException se ) {
            throw new NaiException(se);
		}
	}

		/**
		 * 採番データ更新<br>
		 * <br>
		 * 採番マスタのデータ更新を行う<br>
		 * <br>
		 * @param OrderNumbersMstDto
		 * @return updatedRowCount
		 * @throws NaiException
		 */
		public int update(OrderNumbersMstDto dto) throws NaiException {

			int updatedRowCount=NaikaraTalkConstants.RETURN_CD_ERR_UPD; //更新データ件数

			StringBuffer sb = new StringBuffer();

			//データ更新処理
			sb.append(" UPDATE ");
			sb.append("   ORDER_NUMBERS_MST ");
			sb.append(" SET ");
			sb.append(" YYMM = ? ");                              // 01.年月
			sb.append(",SEQ = ? ");                               // 02.シーケンス
			sb.append(",EFFECTIVE_DIGIT_NUM = ? ");               // 03.有効桁数
			sb.append(",RECORD_VER_NO = ? ");                     // 04.レコードバージョン番号
			sb.append(",UPDATE_DTTM = sysdate() ");
			sb.append(",UPDATE_CD = ? ");                         // 05.更新者コード
			sb.append(" WHERE ");
			sb.append("   ORDER_NUMBERS_ID = ? ");                // 06.採番ID
			sb.append("   AND ");
			sb.append("   ((YYMM <> '') AND (YYMM IS NOT NULL)) ");    // --.年月

			try{

				PreparedStatement ps = conn.prepareStatement(sb.toString());


				ps.setString(1, dto.getYymm());
				ps.setInt(2, dto.getSeq());
				ps.setInt(3, dto.getEffectiveDigitNum());
				ps.setString(4, String.valueOf(dto.getRecordVerNo() + 1));
				ps.setString(5, dto.getUpdateCd());
				ps.setString(6, dto.getOrderNumbersId());

				// 更新
				updatedRowCount= ps.executeUpdate();

				return updatedRowCount;

			} catch ( SQLException se ) {
	            throw new NaiException(se);
			}

	}

}
