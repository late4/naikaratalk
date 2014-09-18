package com.naikara_talk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.naikara_talk.dto.ApplicationsDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraTalkConstants;


/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>バッチ<br>
 * <b>クラス名称　　　:</b>請求書作成クラス<br>
 * <b>クラス概要　　　:</b>請求書作成DAO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/09/03 MIRA 新規作成
 */
public class BillDownloadDao extends AbstractDao {

	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public BillDownloadDao(Connection con) {
		this.conn = con;
	}

	/**
	 * 請求書格納テーブル取得<br>
	 * <br>
	 * 請求書格納テーブルからデータを取得する<br>
	 * <br>
	 * @param ApplicationsDto
	 * @return int
	 * @throws NaiException
	 */
	public int selectApplications(ApplicationsDto dto) throws NaiException {


        StringBuffer sb = new StringBuffer();
		ResultSet res = null;
        int daoReturnCd_ = NaikaraTalkConstants.RETURN_CD_DATA_ERR;

		//データ取得処理
		sb.append(" SELECT ");
		sb.append("  BILL_ISSUE_YYYYMM ");	// 請求年月
		sb.append(" ,APPLICATIONS ");	    // 請求書データ
		sb.append("  ");
		sb.append(" FROM ");
		sb.append("  APPLICATIONS ");	// 請求書格納テーブル
		sb.append(" WHERE  ");
		sb.append(" BILL_ISSUE_YYYYMM = ? ");


		try{

			PreparedStatement ps = conn.prepareStatement(sb.toString());
			ps.setString(1, dto.getBillIssueYm());
			res = ps.executeQuery();
			while (res.next()) {
				dto.setApplications(res.getBinaryStream("APPLICATIONS"));
				daoReturnCd_ = NaikaraTalkConstants.RETURN_CD_DATA_YES;
			}


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
		return daoReturnCd_;
	}

	/**
	 * 請求書格納テーブル取得<br>
	 * <br>
	 * 請求書格納テーブルからデータを取得する<br>
	 * <br>
	 * @param ApplicationsDto
	 * @return List<String>
	 * @throws NaiException
	 */
	public List<String> getBillIssueYyyymm(ApplicationsDto dto) throws NaiException {


        StringBuffer sb = new StringBuffer();
		ResultSet res = null;
		List<String> retList = new ArrayList<String>();

		//データ取得処理
		sb.append(" SELECT ");
		sb.append("  BILL_ISSUE_YYYYMM ");	// 請求年月
		sb.append("  ");
		sb.append(" FROM ");
		sb.append("  APPLICATIONS ");	// 請求書格納テーブル
		sb.append(" ORDER BY BILL_ISSUE_YYYYMM DESC");

		try{

			PreparedStatement ps = conn.prepareStatement(sb.toString());
			res = ps.executeQuery();
			while (res.next()) {
				retList.add(res.getString("BILL_ISSUE_YYYYMM"));
			}


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
		return retList;
	}
}
