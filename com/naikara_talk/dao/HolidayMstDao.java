package com.naikara_talk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.naikara_talk.dto.HolidayMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraTalkConstants;


/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DAOクラス<br>
 * <b>クラス名称　　　:</b>祝日判定処理クラス<br>
 * <b>クラス概要　　　:</b>祝日判定処理DAO<br>
 * <br>
 * <b>著作権　　　　　:</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/05/27 TECS 新規作成
 */
public class HolidayMstDao extends AbstractDao {

	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public HolidayMstDao(Connection con) {
		this.conn = con;
	}

	/**
	 * 祝日マスタデータ取得<br>
	 * <br>
	 * 祝日マスタから、指定日でデータを取得する<br>
	 * <br>
	 * @param baseDay
	 * @param addDays
	 * @param countNum
	 * @return ArrayList<HolidayMstDto>
	 * @throws NaiException
	 */
	public ArrayList<HolidayMstDto> search(Date start,Date End) throws NaiException {

		//戻り値とリターンコードの初期化
		ArrayList<HolidayMstDto> list = null;    //祝日マスタリスト
		HolidayMstDto dto = null;                //祝日マスタDTO


		//Date型をString型(YYYYMMDD)に変換する
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		String strStart=sdf.format(start);
		String strEnd=sdf.format(End);

		list =new ArrayList<HolidayMstDto>();


		ResultSet res = null;

		StringBuffer sb = new StringBuffer();

		//データ取得
		sb.append(" SELECT ");
		sb.append("   HOLIDAY_DT  ");
		sb.append(" FROM ");
		sb.append("   HOLIDAY_MST ");
		sb.append(" WHERE ");
		sb.append("  HOLIDAY_DT ");
		sb.append("    BETWEEN ");
		sb.append("      ? ");
		sb.append("     AND ");
		sb.append("      ? ");
		sb.append(" ORDER BY HOLIDAY_DT ");

		try{

			PreparedStatement ps = conn.prepareStatement(sb.toString());
			ps.setString(1, strStart);
			ps.setString(2, strEnd);

			res = ps.executeQuery();
			while (res.next()){

				dto=new HolidayMstDto();
				dto.setHolidayDt(res.getString("HOLIDAY_DT"));

				dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);

				list.add(dto);
			}

			if(list.size()<1){
				dto = new HolidayMstDto();
				dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_NO);
				list.add(dto);
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
}
