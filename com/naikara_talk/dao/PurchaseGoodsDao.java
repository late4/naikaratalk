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
import com.naikara_talk.dto.PurchaseGoodsDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様利用画面<br>
 * <b>クラス名称       :</b>商品購入ページＤＡＯクラス<br>
 * <b>クラス概要       :</b>商品購入ページのデータを取得する<br>
 * <br>
 * <b>著作権           :</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/12 TECS 新規作成
 *                     :</b>2014/02/18 TECS 商品購入ページからの呼出追加に伴う修正
*/
public class PurchaseGoodsDao extends AbstractDao {

	/** 大分類コード 条件項目 */
	public static final String COND_BIG_CLASSIFICATION_CD = "COURSE_MST.BIG_CLASSIFICATION_CD";
	/** 中分類コード 条件項目 */
	public static final String COND_MIDDLE_CLASSIFICATION_CD = "COURSE_MST.MIDDLE_CLASSIFICATION_CD";
	/** 小分類コード 条件項目 */
	public static final String COND_SMALL_CLASSIFICATION_CD = "COURSE_MST.SMALL_CLASSIFICATION_CD";
	/** コース名 条件項目 */
	public static final String COND_COURSE_JNM = "COURSE_MST.COURSE_JNM";
	/** 提供開始日 条件項目 */
	public static final String COND_USE_STR_DT_CM = "COURSE_MST.USE_STR_DT";
	/** 提供終了日 条件項目 */
	public static final String COND_USE_END_DT_CM = "COURSE_MST.USE_END_DT";

	/** 商品コード 条件項目 */
	public static final String COND_GOODS_CD = "GOODS_MST.GOODS_CD";
	/** 商品名 条件項目 */
	public static final String COND_GOODS_NM = "GOODS_MST.GOODS_NM";
	/** 受取方法区分 条件項目 */
	public static final String COND_SALE_KBN = "GOODS_MST.SALE_KBN";
	/** 商品形態区分 条件項目 */
	public static final String COND_PRODUCT_KBN = "GOODS_MST.PRODUCT_KBN";
	/** 提供開始日 条件項目 */
	public static final String COND_USE_STR_DT_GM = "GOODS_MST.USE_STR_DT";
	/** 提供終了日 条件項目 */
	public static final String COND_USE_END_DT_GM = "GOODS_MST.USE_END_DT";

	// 2014/02/18 Add Start 商品購入ページからの呼出追加に伴う修正
	/** 講師への商品購入確認通知メール 条件項目 */
	public static final String COND_TEACHER_CONTACT_KBN = "GOODS_MST.TEACHER_CONTACT_KBN";
	// 2014/02/18 Add End   商品購入ページからの呼出追加に伴う修正


	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public PurchaseGoodsDao(Connection con) {
		this.conn = con;
	}

	/**
	 * 商品購入ページデータ取得<br>
	 * <br>
	 * 商品購入ページリストを戻り値で返却する<br>
	 * <br>
	 * @param conditional
	 * @param orderConditional
	 * @return PurchaseGoodsDto
	 * @throws NaiException
	 */
	public List<PurchaseGoodsDto> search(ConditionMapper conditions, OrderCondition orderConditions) throws NaiException {

		ResultSet res = null;

		StringBuffer sb = new StringBuffer();

		// 商品購入テーブルの全項目取得
		sb.append("select");
		sb.append(" GOODS_MST.GOODS_CD ");
		sb.append(",GOODS_MST.GOODS_NM ");
		sb.append(",GOODS_MST.EXPLANATION ");
		sb.append(",GOODS_MST.SALE_KBN ");
		sb.append(",GOODS_MST.PRODUCT_KBN ");
		sb.append(",GOODS_MST.USE_POINT ");
		sb.append(",GOODS_MST.PURCHASE_DESCRIPTION ");
		sb.append(",GOODS_MST.PURCHASE_URL ");
		sb.append(",TEACHER_CONTACT_KBN ");    // 講師への購入メール連絡付き区分  2014.02.18 TECS 商品購入ページからの呼出追加に伴う修正
		sb.append(",COURSE_MST.COURSE_CD ");
		sb.append(",COURSE_MST.BIG_CLASSIFICATION_CD ");
		sb.append(",COURSE_MST.MIDDLE_CLASSIFICATION_CD ");
		sb.append(",COURSE_MST.SMALL_CLASSIFICATION_CD ");
		sb.append(",COURSE_MST.COURSE_JNM ");
		sb.append(" from GOODS_MST ");
		sb.append(" inner join COURSE_GOODS_MST ");
		sb.append(" on GOODS_MST.GOODS_CD = COURSE_GOODS_MST.GOODS_CD ");
		sb.append(" inner join COURSE_MST ");
		sb.append(" on COURSE_MST.COURSE_CD = COURSE_GOODS_MST.COURSE_CD ");

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

			ArrayList<PurchaseGoodsDto> list = new ArrayList<PurchaseGoodsDto>();

			while (res.next()){

				PurchaseGoodsDto retDto = new PurchaseGoodsDto();

				retDto.setGoodsCd(res.getString("GOODS_MST.GOODS_CD"));
				retDto.setGoodsNm(res.getString("GOODS_MST.GOODS_NM"));
				retDto.setExplanation(res.getString("GOODS_MST.EXPLANATION"));
				retDto.setSaleKbn(res.getString("GOODS_MST.SALE_KBN"));
				retDto.setProductKbn(res.getString("GOODS_MST.PRODUCT_KBN"));
				retDto.setUsePoint(res.getBigDecimal("GOODS_MST.USE_POINT"));
				retDto.setPurchaseDescription(res.getString("GOODS_MST.PURCHASE_DESCRIPTION"));
				retDto.setPurchaseUrl(res.getString("GOODS_MST.PURCHASE_URL"));

				retDto.setTContactKbn(res.getString("GOODS_MST.TEACHER_CONTACT_KBN"));    // 講師への購入メール連絡付き区分  2014.02.18 TECS 商品購入ページからの呼出追加に伴う修正

				retDto.setCourseCd(res.getString("COURSE_MST.COURSE_CD"));
				retDto.setBigClassificationCd(res.getString("COURSE_MST.BIG_CLASSIFICATION_CD"));
				retDto.setMiddleClassificationCd(res.getString("COURSE_MST.MIDDLE_CLASSIFICATION_CD"));
				retDto.setSmallClassificationCd(res.getString("COURSE_MST.SMALL_CLASSIFICATION_CD"));
				retDto.setCourseJnm(res.getString("COURSE_MST.COURSE_JNM"));

				retDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);

				list.add(retDto);
			}

			if(list.size()<1){
				PurchaseGoodsDto retDto = new PurchaseGoodsDto();
				retDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_NO);
				list.add(retDto);
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
				e.printStackTrace();
				throw new NaiException(e);
			}
		}
	}

	/**
	 * 商品購入ページデータ件数取得<br>
	 * <br>
	 * 商品購入ページデータ件数を戻り値で返却する<br>
	 * <br>
	 * @param conditional
	 * @param orderConditional
	 * @return int
	 * @throws NaiException
	 */
	public int getRowCount(ConditionMapper conditions) throws NaiException {

		int rowCount = NaikaraTalkConstants.RETURN_CD_DATA_ERR; // データ件数

		ResultSet res = null;

		StringBuffer sb = new StringBuffer();

		// 商品購入ページデータ件数取得
		sb.append("select count(*)");
		sb.append(" from GOODS_MST ");
		sb.append(" inner join COURSE_GOODS_MST ");
		sb.append(" on GOODS_MST.GOODS_CD = COURSE_GOODS_MST.GOODS_CD ");
		sb.append(" inner join COURSE_MST ");
		sb.append(" on COURSE_MST.COURSE_CD = COURSE_GOODS_MST.COURSE_CD ");

		if(!StringUtils.isEmpty(conditions.getConditionString())) {
			sb.append("where ");
			sb.append(conditions.getConditionString());
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

			//実行
			res = ps.executeQuery();

			if (res.next()) {
				rowCount = res.getInt(1);
			}

			return rowCount;

		} catch ( SQLException se ) {
			throw new NaiException(se);
		} finally {
			try {
				if ( res != null ) {
					res.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new NaiException(e);
			}
		}
	}

}
