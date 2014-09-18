package com.naikara_talk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.naikara_talk.dto.OrginationBalancePointDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraTalkConstants;


/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DAOクラス<br>
 * <b>クラス名称　　　:</b>組織別未使用ポイント算出処理クラス<br>
 * <b>クラス概要　　　:</b>組織別未使用ポイント算出処理DAO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/05/28 TECS 新規作成
 */
public class OrganizationBalancePointDao extends AbstractDao {

	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public OrganizationBalancePointDao(Connection con) {
		this.conn = con;
	}

	/**
	 * 組織別未使用ポイント算出処理<br>
	 * <br>
	 * 組織別の残高ポイントを集計して、返却する<br>
	 * <br>
	 * @param organizationId
	 * @param endDate
	 * @return BigDecimal:SumPoint
	 * @throws NaiException
	 */
	public OrginationBalancePointDto search(String organizationId,String endDate) throws NaiException {

		//戻り値とリターンコードの初期化
		OrginationBalancePointDto dto=null;

		ResultSet res = null;

		StringBuffer sb = new StringBuffer();

		//残高ポイントの合計取得
		sb.append(" select ");
		sb.append("   SUM(POT.BALANCE_POINT) AS SUM_POINT ");
		sb.append(" from ");

		sb.append(" STUDENT_MST  AS STM ");
		sb.append(" left join POINT_OWNERSHIP_TRN AS POT ");
		sb.append(" on STM.STUDENT_ID = POT.STUDENT_ID ");
		sb.append(" where ");
		sb.append("   STM.ORGANIZATION_ID = ? ");
		sb.append("  and ");
		sb.append("   POT.EFFECTIVE_END_DT = ? ");

		try{

			PreparedStatement ps = conn.prepareStatement(sb.toString());

			ps.setString(1, organizationId);
			ps.setString(2, endDate);
			res = ps.executeQuery();

			if (res.next())

			dto=new OrginationBalancePointDto();
			if(res.getBigDecimal("SUM_POINT")!=null){
				dto.setSumBalancePoint(res.getBigDecimal("SUM_POINT"));
				dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);
			}else{
				dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_NO);
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
}