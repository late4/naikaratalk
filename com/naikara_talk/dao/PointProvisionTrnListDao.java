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
import com.naikara_talk.dto.PointProvisionTrnListDto;
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

public class PointProvisionTrnListDao extends AbstractDao {

	/** 予約No／購入ID　条件項目　*/
	public static final String COND_RSV_NO_PURCHASE_ID = "RSV_NO_PURCHASE_ID";

	/** 連番　条件項目　*/
	public static final String COND_CONS_SEQ = "CONS_SEQ";

	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public PointProvisionTrnListDao(Connection con) {
		this.conn = con;
	}

	/**
	 * ポイント引当テーブルデータ取得<br>
	 * <br>
	 * ポイント引当テーブルリストを戻り値で返却する<br>
	 * <br>
	 * @param conditions
	 * @param orderConditions
	 * @return PointProvisionTrnListDto
	 * @throws NaiException
	 */
	public List<PointProvisionTrnListDto> search(ConditionMapper conditions, OrderCondition orderConditions) throws NaiException {

		//戻り値とリターンコードの初期化
		ArrayList<PointProvisionTrnListDto> list = null;  //DTOリスト
		PointProvisionTrnListDto retDto = null;           //DTO

		ResultSet res = null;

		StringBuffer sb = new StringBuffer();

		// ポイント引当テーブルの項目取得
		sb.append("select ");
		sb.append(" RSV_NO_PURCHASE_ID ");
		sb.append(",CONS_SEQ ");
		sb.append(",OWN_ID ");
		sb.append(",COMPENSATION_FREE_KBN ");
		sb.append(",USE_POINT ");
		sb.append("from POINT_PROVISION_TRN ");
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

			list = new ArrayList<PointProvisionTrnListDto>();
			while (res.next()){
				retDto = new PointProvisionTrnListDto();
				retDto.setRsvNoPurchaseId(res.getString("RSV_NO_PURCHASE_ID"));
				retDto.setConsSeq(res.getInt("CONS_SEQ"));
				retDto.setOwnId(res.getString("OWN_ID"));
				retDto.setCompensationFreeKbn(res.getString("COMPENSATION_FREE_KBN"));
				retDto.setUsePoint(res.getBigDecimal("USE_POINT"));

				retDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);

				list.add(retDto);
			}

			if(list.size()<1){
				list=new ArrayList<PointProvisionTrnListDto>();
				retDto=new PointProvisionTrnListDto();
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
				throw new NaiException(e);
			}
		}
	}

}