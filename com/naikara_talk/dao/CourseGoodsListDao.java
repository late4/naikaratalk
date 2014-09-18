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
import com.naikara_talk.dto.CourseGoodsListDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraTalkConstants;


/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DAOクラス<br>
 * <b>クラス名称　　　:</b>コース単位の該当商品の取得クラス<br>
 * <b>クラス概要　　　:</b>コース単位の該当商品の取得DAO<br>
 * <br>
 * <b>著作権　　　　　:</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/06/06 TECS 新規作成
 */
public class CourseGoodsListDao extends AbstractDao {

	/** コースコード 条件項目 */
	public static final String COND_COURSE_CD = "COURSE_GOODS_MST.COURSE_CD";
	/** 提供開始日 条件項目 */
	public static final String COND_USE_STR_DT = "GOODS_MST.USE_STR_DT";
	/** 提供終了日 条件項目 */
	public static final String COND_USE_END_DT = "GOODS_MST.USE_END_DT";

	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public CourseGoodsListDao(Connection con) {
		this.conn = con;
	}

	/**
	 * コース単位の該当商品の取得<br>
	 * <br>
	 * コース単位の該当商品を戻り値で返却する<br>
	 * <br>
	 * @param conditions
	 * @param OrderBy
	 * @return ArrayList<CourseGoodsMstDto>
	 * @throws NaiException
	 */
	public ArrayList<CourseGoodsListDto> search(ConditionMapper conditions, OrderCondition OrderBy) throws NaiException {

		ResultSet res = null;

		StringBuffer sb = new StringBuffer();

		//コースマスタの全項目取得処理
		sb.append("select");
		sb.append(" COURSE_GOODS_MST.COURSE_CD ");
		sb.append(",COURSE_GOODS_MST.GOODS_CD ");
		sb.append(",COURSE_GOODS_MST.RECORD_VER_NO ");
		sb.append(",COURSE_GOODS_MST.INSERT_DTTM ");
		sb.append(",COURSE_GOODS_MST.INSERT_CD ");
		sb.append(",COURSE_GOODS_MST.UPDATE_DTTM ");
		sb.append(",COURSE_GOODS_MST.UPDATE_CD ");
		sb.append(",GOODS_MST.GOODS_NM ");
		sb.append(",GOODS_MST.EXPLANATION ");
		sb.append(",GOODS_MST.SALE_KBN ");
		sb.append(",GOODS_MST.IMG_PHOTO_NM ");
		sb.append(",GOODS_MST.IMG_PHOTO ");
		sb.append(",GOODS_MST.PRODUCT_KBN ");
		sb.append(",GOODS_MST.GOODS_FILE_NM ");
		sb.append(",GOODS_MST.GOODS_FILE ");
		sb.append(",GOODS_MST.SALES_PRICE ");
		sb.append(",GOODS_MST.USE_POINT ");
		sb.append(",GOODS_MST.PURCHASE_DESCRIPTION ");
		sb.append(",GOODS_MST.PURCHASE_URL ");
		sb.append(",GOODS_MST.USE_STR_DT ");
		sb.append(",GOODS_MST.USE_END_DT ");
		sb.append(",GOODS_MST.USE_COURSE_MEMO ");
		sb.append(",GOODS_MST.REMARK ");
		sb.append(",GOODS_MST.RECORD_VER_NO ");
		sb.append(",GOODS_MST.INSERT_DTTM ");
		sb.append(",GOODS_MST.INSERT_CD ");
		sb.append(",GOODS_MST.UPDATE_DTTM ");
		sb.append(",GOODS_MST.UPDATE_CD ");
		sb.append("from COURSE_GOODS_MST ");
		sb.append("inner join GOODS_MST ");
		sb.append("on COURSE_GOODS_MST.GOODS_CD = GOODS_MST.GOODS_CD ");
		// 抽出条件の設定
		if(!StringUtils.isEmpty(conditions.getConditionString())) {
			sb.append("where ");
			sb.append(conditions.getConditionString());
		}
		// 並び順の設定
		if(!StringUtils.isEmpty(OrderBy.getOrderString())) {
			sb.append(OrderBy.getOrderString());
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

			//戻り値とリターンコードの初期化
			ArrayList<CourseGoodsListDto> list = new ArrayList<CourseGoodsListDto>();

			while (res.next()){
				CourseGoodsListDto retDto = new CourseGoodsListDto();
				retDto.setCourseCd(res.getString("COURSE_GOODS_MST.COURSE_CD"));
				retDto.setGoodsCd(res.getString("COURSE_GOODS_MST.GOODS_CD"));
				retDto.setRecordVerNoC(res.getInt("COURSE_GOODS_MST.RECORD_VER_NO"));
				retDto.setInsertDttmC(res.getTimestamp("COURSE_GOODS_MST.INSERT_DTTM"));
				retDto.setInsertCdC(res.getString("COURSE_GOODS_MST.INSERT_CD"));
				retDto.setUpdateDttmC(res.getTimestamp("COURSE_GOODS_MST.UPDATE_DTTM"));
				retDto.setUpdateCdC(res.getString("COURSE_GOODS_MST.UPDATE_CD"));
				retDto.setGoodsNm(res.getString("GOODS_MST.GOODS_NM"));
				retDto.setExplanation(res.getString("GOODS_MST.EXPLANATION"));
				retDto.setSaleKbn(res.getString("GOODS_MST.SALE_KBN"));
				retDto.setImgPhotoNm(res.getString("GOODS_MST.IMG_PHOTO_NM"));
				retDto.setImgPhoto(res.getBlob("GOODS_MST.IMG_PHOTO"));
				retDto.setProductKbn(res.getString("GOODS_MST.PRODUCT_KBN"));
				retDto.setGoodsFileNm(res.getString("GOODS_MST.GOODS_FILE_NM"));
				retDto.setGoodsFile(res.getBlob("GOODS_MST.GOODS_FILE"));
				retDto.setSalesPrice(res.getBigDecimal("GOODS_MST.SALES_PRICE"));
				retDto.setUsePoint(res.getBigDecimal("GOODS_MST.USE_POINT"));
				retDto.setPurchaseDescription(res.getString("GOODS_MST.PURCHASE_DESCRIPTION"));
				retDto.setPurchaseUrl(res.getString("GOODS_MST.PURCHASE_URL"));
				retDto.setUseStrDt(res.getString("GOODS_MST.USE_STR_DT"));
				retDto.setUseEndDt(res.getString("GOODS_MST.USE_END_DT"));
				retDto.setUseCourseMemo(res.getString("GOODS_MST.USE_COURSE_MEMO"));
				retDto.setRemark(res.getString("GOODS_MST.REMARK"));
				retDto.setRecordVerNoG(res.getInt("GOODS_MST.RECORD_VER_NO"));
				retDto.setInsertDttmG(res.getTimestamp("GOODS_MST.INSERT_DTTM"));
				retDto.setInsertCdG(res.getString("GOODS_MST.INSERT_CD"));
				retDto.setUpdateDttmG(res.getTimestamp("GOODS_MST.UPDATE_DTTM"));
				retDto.setUpdateCdG(res.getString("GOODS_MST.UPDATE_CD"));
				retDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);
				list.add(retDto);
			}

			if ( list.size() < 1 ){
				CourseGoodsListDto retDto = new CourseGoodsListDto();
				retDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_NO);
				list.add(retDto);
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

}