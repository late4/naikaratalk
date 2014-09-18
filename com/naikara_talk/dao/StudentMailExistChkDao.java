package com.naikara_talk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraTalkConstants;


/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DAOクラス<br>
 * <b>クラス名称　　　:</b>メール重複チェッククラス<br>
 * <b>クラス概要　　　:</b>メール重複チェックDAO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/05/24 TECS 新規作成
 */
public class StudentMailExistChkDao extends AbstractDao {

	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public StudentMailExistChkDao(Connection con) {
		this.conn = con;
	}

	/**
	 * 共通部品：受講者マスタのメール存在チェック<br>
	 * <br>
	 * 受講者マスタにメールが存在しているかチェックする<br>
	 * <br>
	 * @param mailAddress
	 * @param yyyyMMdd
	 * @param studentId
	 * @return int:result
	 * @throws NaiException
	 */
	public int count(String mailAddress, String yyyyMMdd, String studentId) throws NaiException {

		//戻り値とリターンコードの初期化
		int result = -1; //判定結果

		//データ取得処理
		ResultSet res = null;

		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT ");
		sb.append("   COUNT(*)  ");
		sb.append(" FROM ");
		sb.append("   STUDENT_MST ");
		sb.append(" WHERE ");
		sb.append("  (   MAIL_ADDRESS1 = ? ");
		sb.append("   OR MAIL_ADDRESS2 = ? ");
		sb.append("   OR MAIL_ADDRESS3 = ? ");
		sb.append("  ) ");
		sb.append("  AND ");
		sb.append("   USE_STR_DT <= ? ");
		sb.append("  AND ");
		sb.append("   USE_END_DT >= ? ");
		sb.append("  AND ");
		sb.append("   STUDENT_ID <> ? ");

		try{

			PreparedStatement ps = conn.prepareStatement(sb.toString());
			ps.setString(1, mailAddress);
			ps.setString(2, mailAddress);
			ps.setString(3, mailAddress);
			ps.setString(4, yyyyMMdd);
			ps.setString(5, yyyyMMdd);
			ps.setString(6, studentId);

			res = ps.executeQuery();
			if(res.next())
			if(res.getInt(1)==0){
				result = NaikaraTalkConstants.RETURN_CD_DATA_YES; //重複なしでOK
			}else{
				result = NaikaraTalkConstants.RETURN_CD_DATA_ERR; //重複ありでNG
			}

			return result;

        } catch (SQLException se) {
            throw new NaiException(se);
        } finally {
        	try{
        		if (res != null) {
        			res.close();
        		}

        	}catch(SQLException se ){
        		throw new NaiException(se);
        	}
        }
	}

}
