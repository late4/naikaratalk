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
import com.naikara_talk.dto.PointOwnershipTrnListDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DAOクラス<br>
 * <b>クラス名称　　　:</b>ポイント引当テーブルデータアクセスクラス<br>
 * <b>クラス概要　　　:</b>ポイント引当テーブルの取得を行う<br>
 * <br>
 * <b>著作権　　　　　:</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/06/27 TECS 新規作成
 */

public class PointOwnershipTrnListDao extends AbstractDao {

	/** 受講者ID　条件項目　*/
	public static final String COND_STUDENT_ID = "STUDENT_ID";
	/** 有効無効別区分　条件項目　*/
	public static final String COND_COMPENSATION_FREE_KBN = "COMPENSATION_FREE_KBN";
	/** 通常月謝区分　条件項目　*/
	public static final String COND_FEE_KBN = "FEE_KBN";
	/** 有効開始日　条件項目　*/
	public static final String COND_EFFECTIVE_STR_DT = "EFFECTIVE_STR_DT";
	/** 有効終了日　条件項目　*/
	public static final String COND_EFFECTIVE_END_DT = "EFFECTIVE_END_DT";

	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public PointOwnershipTrnListDao(Connection con) {
		this.conn = con;
	}

	/**
	 * ポイント引当テーブルデータ取得<br>
	 * <br>
	 * ポイント引当テーブルリストを戻り値で返却する<br>
	 * <br>
	 * @param conditions
	 * @param orderConditions
	 * @return PointProvisionListDto
	 * @throws NaiException
	 */
	public List<PointOwnershipTrnListDto> search(ConditionMapper conditions, OrderCondition orderConditions) throws NaiException {


		//戻り値とリターンコードの初期化
		ArrayList<PointOwnershipTrnListDto> list = null; //DTOリスト
		PointOwnershipTrnListDto retDto = null;          //DTO

		ResultSet res = null;

		// 利用者マスタ、受講者マスタの全項目取得
		StringBuffer sb = new StringBuffer();
		sb.append("select ");
		sb.append(" OWNERSHIP_ID ");
		sb.append(",COMPENSATION_FREE_KBN ");
		sb.append(",EFFECTIVE_STR_DT ");
		sb.append(",EFFECTIVE_END_DT ");
		sb.append(",BALANCE_POINT ");
		sb.append("from POINT_OWNERSHIP_TRN ");

		if(!StringUtils.isEmpty(conditions.getConditionString())) {
			sb.append("where ");
			sb.append(conditions.getConditionString());
		}
		if(!StringUtils.isEmpty(orderConditions.getOrderString())) {
			sb.append(orderConditions.getOrderString());
		}

		try{

			PreparedStatement ps = conn.prepareStatement(sb.toString());

            // パラメタの設定
            int i = 0;
            for( QueryCondition cond : conditions.getConditions()){
            	for(String val : cond.getValues()){
            		ps.setString(i + 1, val);
            		i++;
            	}
            }

			res = ps.executeQuery();

			list = new ArrayList<PointOwnershipTrnListDto>();

			while (res.next()){

				retDto = new PointOwnershipTrnListDto();

				retDto.setOwnershipID(res.getString("OWNERSHIP_ID"));
				retDto.setCompensationFreeKbn(res.getString("COMPENSATION_FREE_KBN"));
				retDto.setEffectiveStrDt(res.getString("EFFECTIVE_STR_DT"));
				retDto.setEffectiveEndDt(res.getString("EFFECTIVE_END_DT"));
				retDto.setBalancePoint(res.getBigDecimal("BALANCE_POINT"));

				retDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);

				list.add(retDto);
			}

			if(list.size()<1){
				list=new ArrayList<PointOwnershipTrnListDto>();
				retDto=new PointOwnershipTrnListDto();
				retDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_NO);
				list.add(retDto);
				return list;
			}

			return list;

		} catch ( SQLException se ) {
			throw new NaiException(se);
		} finally {
			try {
				if(res!=null){
					res.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new NaiException(e);
			}
		}
	}

}