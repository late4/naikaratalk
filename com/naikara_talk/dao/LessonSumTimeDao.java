package com.naikara_talk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.naikara_talk.dto.TimeManagMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraTalkConstants;




/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DAOクラス<br>
 * <b>クラス名称　　　:</b>レッスン時刻単位の時差計算処理クラス<br>
 * <b>クラス概要　　　:</b>レッスン時刻単位の時差計算処理DAO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/05/27 TECS 新規作成
 */
public class LessonSumTimeDao extends AbstractDao {

	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public LessonSumTimeDao(Connection con) {
		this.conn = con;
	}

	/**
	 * レッスン時刻単位の時差計算処理<br>
	 * <br>
	 * 講師IDとレッスン時刻で指定のローカル・サマータイムを時差管理マスタから算出する<br>
	 * <br>
	 * @param userID
	 * @param lessonTime
	 * @return ArrayList<LocSumTimeDto>
	 * @throws NaiException
	 */
	public TimeManagMstDto getLocSumTime(String userID) throws NaiException {

		//戻り値とリターンコードの初期化
		TimeManagMstDto tmmDto = null;                //DTO

		ResultSet res = null;

		StringBuffer sb = new StringBuffer();

		//ローカル・サマータイムのデータ取得処理
		sb.append(" SELECT ");
		sb.append("   TIM.TIME_MARK_KBN ");
		sb.append("  ,TIM.TIME_MINUTES ");
		sb.append("  ,TIM.SUM_TIME_FLG ");
		sb.append("  ,TIM.SUM_TIME_STR_DT ");
		sb.append("  ,TIM.SUM_TIME_END_DT ");
		sb.append("  ,TIM.SUM_TIME_MARK_KBN ");
		sb.append("  ,TIM.SUM_TIME_MINUTES ");
		sb.append(" FROM ");
		sb.append("   TIME_MANAG_MST AS TIM ");
		sb.append("  ,TEACHER_MST    AS TEM ");
		sb.append(" WHERE ");
		sb.append("   TEM.USER_ID    = ? ");
		sb.append("  AND ");
		sb.append("   TEM.COUNTRY_CD = TIM.COUNTRY_CD ");
		sb.append("  AND ");
		sb.append("   TEM.AREA_NO_CD = TIM.AREA_NO_CD ");

		//時差管理マスタ
		tmmDto = new TimeManagMstDto();

		try{

			PreparedStatement ps = conn.prepareStatement(sb.toString());
			ps.setString(1, userID);
			res = ps.executeQuery();

			// データなし
			tmmDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_NO);

			while(res.next()) {
				tmmDto.setTimeMarkKbn(res.getString("TIME_MARK_KBN"));
				tmmDto.setTimeMinutes(res.getInt("TIME_MINUTES"));
				tmmDto.setSumTimeFlg(res.getString("SUM_TIME_FLG"));
				tmmDto.setSumTimeStrDt(res.getString("SUM_TIME_STR_DT"));
				tmmDto.setSumTimeEndDt(res.getString("SUM_TIME_END_DT"));
				tmmDto.setSumTimeMarkKbn(res.getString("SUM_TIME_MARK_KBN"));
				tmmDto.setSumTimeMinutes(res.getInt("SUM_TIME_MINUTES"));

				tmmDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);

			}
			return tmmDto;

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


