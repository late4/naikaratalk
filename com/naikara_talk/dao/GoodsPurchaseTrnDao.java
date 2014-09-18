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

import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dbutil.QueryCondition;
import com.naikara_talk.dto.GoodsPurchaseTrnDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraTalkConstants;


/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DAOクラス<br>
 * <b>クラス名称　　　:</b>商品購入テーブルデータアクセスクラス<br>
 * <b>クラス概要　　　:</b>商品購入テーブル<br>
 * <br>
 * <b>著作権　　　　　:</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/15 TECS 新規作成
 */
public class GoodsPurchaseTrnDao extends AbstractDao {

	/** 購入日（上6桁） 条件項目 */
	public static final String COND_PURCHASE_DT6 = "SUBSTR(PURCHASE_DT, 1, 6)";

	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public GoodsPurchaseTrnDao(Connection con) {
		this.conn = con;
	}

	/**
	 * 商品購入テーブルデータ取得<br>
	 * <br>
	 * 商品購入テーブルリストを戻り値で返却する<br>
	 * <br>
	 * @param conditional
	 * @param orderConditional
	 * @return GoodsPurchaseTrnDto
	 * @throws NaiException
	 */
	public List<GoodsPurchaseTrnDto> search(ConditionMapper conditions, OrderCondition orderConditions) throws NaiException {

		ResultSet res = null;

		// 売上集計テーブルの全項目取得
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT");
		sb.append(" PURCHASE_ID ");
		sb.append(",PAYMENT_METHOD_KBN ");
		sb.append(",STUDENT_ID ");
		sb.append(",PURCHASE_DT ");
		sb.append(",PURCHASE_TM ");
		sb.append(",GOODS_CD ");
		sb.append(",GOODS_NM ");
		sb.append(",SALE_KBN ");
		sb.append(",SALE_NM ");
		sb.append(",PURCHASE_YEN ");
		sb.append(",PAYMENT_USE_POINT ");
		sb.append(",FREE_USE_POINT ");
		sb.append(",RECORD_VER_NO ");
		sb.append(",INSERT_DTTM ");
		sb.append(",INSERT_CD ");
		sb.append(",UPDATE_DTTM ");
		sb.append(",UPDATE_CD ");
		sb.append("FROM GOODS_PURCHASE_TRN ");

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

			ArrayList<GoodsPurchaseTrnDto> list = new ArrayList<GoodsPurchaseTrnDto>();

			while (res.next()){

				GoodsPurchaseTrnDto dto = new GoodsPurchaseTrnDto();

				dto.setPurchaseId(res.getString("PURCHASE_ID"));
				dto.setPaymentMethodKbn(res.getString("PAYMENT_METHOD_KBN"));
				dto.setStudentId(res.getString("STUDENT_ID"));
				dto.setPurchaseDt(res.getString("PURCHASE_DT"));
				dto.setPurchaseTm(res.getString("PURCHASE_TM"));
				dto.setGoodsCd(res.getString("GOODS_CD"));
				dto.setGoodsNm(res.getString("GOODS_NM"));
				dto.setSaleKbn(res.getString("SALE_KBN"));
				dto.setSaleNm(res.getString("SALE_NM"));
				dto.setPurchaseYen(res.getBigDecimal("PURCHASE_YEN"));
				dto.setPaymentUsePoint(res.getBigDecimal("PAYMENT_USE_POINT"));
				dto.setFreeUsePoint(res.getBigDecimal("FREE_USE_POINT"));
				dto.setRecordVerNo(res.getInt("RECORD_VER_NO"));
				dto.setInsertDttm(res.getTimestamp("INSERT_DTTM"));
				dto.setInsertCd(res.getString("INSERT_CD"));
				dto.setUpdateDttm(res.getTimestamp("UPDATE_DTTM"));
				dto.setUpdateCd(res.getString("UPDATE_CD"));

				dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);

				list.add(dto);
			}

			if(list.size()<1){
				GoodsPurchaseTrnDto dto = new GoodsPurchaseTrnDto();
				dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_NO);
				list.add(dto);
			}

			return list;

		} catch ( SQLException se ) {
			throw new NaiException(se);
		} finally {
			try {
        		if ( res != null ) {
            		res.close();
        		}
			} catch (SQLException e) {
				throw new NaiException(e);
			}
		}
	}

	/**
	 * 商品購入テーブル登録処理<br>
	 * <br>
	 * 登録した件数を返す<br>
	 * <br>
	 * @param PointOwnershipTrnDto
	 * @return insertRowCount
	 * @throws NaiException
	 */
	public int insert(GoodsPurchaseTrnDto dto) throws NaiException {

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		int rslt = 0;

		StringBuffer sb = new StringBuffer();

		// データ登録処理
		sb.append("insert into ");
		sb.append("GOODS_PURCHASE_TRN ");
		sb.append(" ( ");
		sb.append(" PURCHASE_ID ");
		sb.append(",PAYMENT_METHOD_KBN ");
		sb.append(",STUDENT_ID ");
		sb.append(",PURCHASE_DT ");
		sb.append(",PURCHASE_TM ");
		sb.append(",GOODS_CD ");
		sb.append(",GOODS_NM ");
		sb.append(",SALE_KBN ");
		sb.append(",SALE_NM ");
		sb.append(",PURCHASE_YEN ");
		sb.append(",PAYMENT_USE_POINT ");
		sb.append(",FREE_USE_POINT ");
		sb.append(",RECORD_VER_NO ");
		sb.append(",INSERT_DTTM ");
		sb.append(",INSERT_CD ");
		sb.append(",UPDATE_DTTM ");
		sb.append(",UPDATE_CD ");
		sb.append(" ) value ( ");
		sb.append(" ? ");
		sb.append(",? ");
		sb.append(",? ");
		sb.append(",? ");
		sb.append(",? ");
		sb.append(",? ");
		sb.append(",? ");
		sb.append(",? ");
		sb.append(",? ");
		sb.append(",? ");
		sb.append(",? ");
		sb.append(",? ");
		sb.append(",? ");
		sb.append(",? ");
		sb.append(",? ");
		sb.append(",? ");
		sb.append(",? ");
		sb.append(" ) ");

		try {

			PreparedStatement ps = conn.prepareStatement(sb.toString());

			ps.setString(1, dto.getPurchaseId());
			ps.setString(2, dto.getPaymentMethodKbn());
			ps.setString(3, dto.getStudentId());
			ps.setString(4, dto.getPurchaseDt());
			ps.setString(5, dto.getPurchaseTm());
			ps.setString(6, dto.getGoodsCd());
			ps.setString(7, dto.getGoodsNm());
			ps.setString(8, dto.getSaleKbn());
			ps.setString(9, dto.getSaleNm());
			ps.setString(10, String.valueOf(dto.getPurchaseYen()));
			ps.setString(11, String.valueOf(dto.getPaymentUsePoint()));
			ps.setString(12, String.valueOf(dto.getFreeUsePoint()));
			ps.setString(13, String.valueOf(dto.getRecordVerNo()));
			ps.setString(14, String.valueOf(Timestamp.valueOf(sdf.format(cal.getTime()))));
			ps.setString(15, dto.getInsertCd());
			ps.setString(16, String.valueOf(Timestamp.valueOf(sdf.format(cal.getTime()))));
			ps.setString(17, dto.getUpdateCd());

			rslt = ps.executeUpdate();

			return rslt;

		} catch (SQLException se) {
			throw new NaiException(se);
		}
	}

}
