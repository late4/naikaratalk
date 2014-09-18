package com.naikara_talk.dao;

import java.math.BigDecimal;
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
import com.naikara_talk.dto.OrganizationUsageSituationDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>利用状況照会詳細<br>
 * <b>クラス名称　　　:</b>利用状況照会詳細クラス。<br>
 * <b>クラス概要　　　:</b>利用状況照会詳細DAO。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴　　　　:</b>2013/08/06 TECS 新規作成。
 */

public class OrganizationUsageSituationDao extends AbstractDao {

	/** 年月 条件項目 */
    public static final String COND_YYYY_MM = "YYYY_MM";
    /** 受講者ID 条件項目 */
    public static final String COND_STUDENT_ID = "STUDENT_ID";
    /** 購入日／利用日 条件項目 */
    public static final String COND_PURCHASE_USE_DT = "PURCHASE_USE_DT";
    /** レッスン時刻名 条件項目 */
    public static final String COND_LESSON_TM_NM = "LESSON_TM_NM";
    /** レッスン日 条件項目 */
    public static final String COND_LESSON_DT = "LESSON_DT";
    /** 購入日 条件項目 */
    public static final String COND_PURCHASE_DT = "PURCHASE_DT";
    /** 支払方法区分 PAYPALで */
    public static final String PAYMENT_METHOD_1 = "1";
    /** 支払方法区分 ポイントで */
    public static final String PAYMENT_METHOD_2 = "2";

	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public OrganizationUsageSituationDao(Connection con) {
		this.conn = con;
	}

	/**
	 * 売上明細テーブル データを取得
	 * @param conditions
	 * @param orderConditions
	 * @return list
	 * @throws NaiException
	 */
	public List<OrganizationUsageSituationDto> searchBefore(
			ConditionMapper conditions, OrderCondition orderConditions)	throws NaiException {

		ResultSet res = null;

		StringBuffer sb = new StringBuffer();

		// データの取得処理
		sb.append(" SELECT ");
		sb.append(" PURCHASE_USE_DT, ");
		sb.append(" LESSON_TM_NM, ");
		sb.append(" PURCHASE_NM, ");
		sb.append(" GOODS_PURCHASE_YEN, ");
		sb.append(" COMPENSATION_USE_POINT ");
		sb.append(" FROM ");
		sb.append(" SALES_DETAILS_TRN ");

		if (!StringUtils.isEmpty(conditions.getConditionString())) {
            sb.append("where ");
            sb.append(conditions.getConditionString());
        }
        if (!StringUtils.isEmpty(orderConditions.getOrderString())) {
            sb.append(orderConditions.getOrderString());
        }

        PreparedStatement ps = null;
        try {
        	ps = conn.prepareStatement(sb.toString());

        	// パラメタの設定
            int j = 0;
            for (QueryCondition cond : conditions.getConditions()) {
                for (String val : cond.getValues()) {
                    ps.setString(j + 1, val);
                    j++;
                }
            }

            res = ps.executeQuery();

            ArrayList<OrganizationUsageSituationDto> list = new ArrayList<OrganizationUsageSituationDto>();
            OrganizationUsageSituationDto retDto;

            while (res.next()) {
            	retDto = new OrganizationUsageSituationDto();
            	// レッスン日
            	retDto.setLessonDt(NaikaraStringUtil.converToYYYY_MM_DD(res.getString("PURCHASE_USE_DT")));
            	retDto.setLessonTm(res.getString("LESSON_TM_NM"));                              // レッスン時刻名
            	retDto.setPurchaseNm(res.getString("PURCHASE_NM"));                             // コース名／商品名
            	retDto.setGoodsPurchaseYen(res.getBigDecimal("GOODS_PURCHASE_YEN"));            // 有償：ポイント外商品購入
            	retDto.setCompensationUsePoint(res.getBigDecimal("COMPENSATION_USE_POINT"));    // 有償：利用有償ポイント
            	retDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);                  // リターンコード

            	list.add(retDto);
            }

            if (list.size() < 1) {
                retDto = new OrganizationUsageSituationDto();
                retDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_NO);
                list.add(retDto);
            }

            return list;
        } catch (SQLException se) {
            throw new NaiException(se);
        } finally {
            try {
                if (res != null) {
                    res.close();
                }
                if (ps != null) {
                	ps.close();
                }
            } catch (SQLException se) {
                throw new NaiException(se);
            }
        }

	}

	/**
	 * レッスン予実テーブル データを取得
	 * @param conditions
	 * @param orderConditions
	 * @return list
	 * @throws NaiException
	 */
	public List<OrganizationUsageSituationDto> searchLesson(
			ConditionMapper conditions, OrderCondition orderConditions)	throws NaiException {

		ResultSet res = null;

		StringBuffer sb = new StringBuffer();

		// データの取得処理
		sb.append(" SELECT ");
		sb.append(" LESSON_DT PURCHASE_USE_DT, ");
		sb.append(" LESSON_TM_NM LESSON_TM_NM, ");
		sb.append(" USE_POINT_SUM COMPENSATION_USE_POINT, ");
		sb.append(" BIG_CLASSIFICATION_JNM, ");
		sb.append(" MIDDLE_CLASSIFICATION_JNM, ");
		sb.append(" SMALL_CLASSIFICATION_JNM, ");
		sb.append(" COURSE_JNM ");
		sb.append(" FROM ");
		sb.append(" LESSON_RESERVATION_PERFORMANCE_TRN ");

		if (!StringUtils.isEmpty(conditions.getConditionString())) {
            sb.append("where ");
            sb.append(conditions.getConditionString());
        }
        if (!StringUtils.isEmpty(orderConditions.getOrderString())) {
            sb.append(orderConditions.getOrderString());
        }

        PreparedStatement ps = null;
        try {
        	ps = conn.prepareStatement(sb.toString());

        	// パラメタの設定
            int j = 0;
            for (QueryCondition cond : conditions.getConditions()) {
                for (String val : cond.getValues()) {
                    ps.setString(j + 1, val);
                    j++;
                }
            }

            res = ps.executeQuery();

            ArrayList<OrganizationUsageSituationDto> list = new ArrayList<OrganizationUsageSituationDto>();
            OrganizationUsageSituationDto retDto;

            while (res.next()) {
            	retDto = new OrganizationUsageSituationDto();
            	// レッスン日
            	retDto.setLessonDt(NaikaraStringUtil.converToYYYY_MM_DD(res.getString("PURCHASE_USE_DT")));
            	retDto.setLessonTm(res.getString("LESSON_TM_NM"));                              // レッスン時刻名
            	retDto.setCompensationUsePoint(res.getBigDecimal("COMPENSATION_USE_POINT"));    // 有償：利用有償ポイント
            	retDto.setGoodsPurchaseYen(new BigDecimal(0));                                  // 有償：ポイント外商品購入

            	// レッスン予実テーブルの共通部品：コース名の編集（大分類名、中分類名、小分類名、コース名)
				retDto.setPurchaseNm(NaikaraStringUtil.unionString4(
						res.getString("BIG_CLASSIFICATION_JNM"),
						res.getString("MIDDLE_CLASSIFICATION_JNM"),
						res.getString("SMALL_CLASSIFICATION_JNM"),
						res.getString("COURSE_JNM")));

            	retDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);                  // リターンコード

            	list.add(retDto);
            }

            if (list.size() < 1) {
                retDto = new OrganizationUsageSituationDto();
                retDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_NO);
                list.add(retDto);
            }

            return list;
        } catch (SQLException se) {
            throw new NaiException(se);
        } finally {
            try {
                if (res != null) {
                    res.close();
                }
                if (ps != null) {
                	ps.close();
                }
            } catch (SQLException se) {
                throw new NaiException(se);
            }
        }

	}

	/**
	 * 商品購入テーブル データを取得
	 * @param conditions
	 * @param orderConditions
	 * @return list
	 * @throws NaiException
	 */
	public List<OrganizationUsageSituationDto> searchGoods(
			ConditionMapper conditions, OrderCondition orderConditions)	throws NaiException {

		ResultSet res = null;

		StringBuffer sb = new StringBuffer();

		// データの取得処理
		sb.append(" SELECT ");
		sb.append(" PURCHASE_DT PURCHASE_USE_DT, ");
		sb.append(" GOODS_NM PURCHASE_NM, ");
		sb.append(" PAYMENT_METHOD_KBN, ");
		sb.append(" CASE WHEN PURCHASE_YEN IS NULL THEN 0 ");
		sb.append(" ELSE PURCHASE_YEN ");
		sb.append(" END PURCHASE_YEN, ");
		sb.append(" CASE WHEN PAYMENT_USE_POINT IS NULL THEN 0 ");
		sb.append(" ELSE PAYMENT_USE_POINT ");
		sb.append(" END PAYMENT_USE_POINT ");
		sb.append(" FROM ");
		sb.append(" GOODS_PURCHASE_TRN ");

		if (!StringUtils.isEmpty(conditions.getConditionString())) {
            sb.append("where ");
            sb.append(conditions.getConditionString());
        }
        if (!StringUtils.isEmpty(orderConditions.getOrderString())) {
            sb.append(orderConditions.getOrderString());
        }

        PreparedStatement ps = null;
        try {
        	ps = conn.prepareStatement(sb.toString());

        	// パラメタの設定
            int j = 0;
            for (QueryCondition cond : conditions.getConditions()) {
                for (String val : cond.getValues()) {
                    ps.setString(j + 1, val);
                    j++;
                }
            }

            res = ps.executeQuery();

            ArrayList<OrganizationUsageSituationDto> list = new ArrayList<OrganizationUsageSituationDto>();
            OrganizationUsageSituationDto retDto;

            while (res.next()) {
            	retDto = new OrganizationUsageSituationDto();
            	// レッスン日
            	retDto.setLessonDt(NaikaraStringUtil.converToYYYY_MM_DD(res.getString("PURCHASE_USE_DT")));
            	retDto.setPurchaseNm(res.getString("PURCHASE_NM"));                                    // コース名／商品名

            	if (StringUtils.equals(PAYMENT_METHOD_1, res.getString("PAYMENT_METHOD_KBN"))) {
            		// 商品購入テーブル．支払方法区分='1'（PayPal購入）の購入金額(税込)
            		retDto.setGoodsPurchaseYen(res.getBigDecimal("PURCHASE_YEN"));                     // 有償：ポイント外商品購入
            		retDto.setCompensationUsePoint(new BigDecimal(0));                                 // 有償：利用有償ポイント
            	} else if (StringUtils.equals(PAYMENT_METHOD_2, res.getString("PAYMENT_METHOD_KBN"))){
            		// 商品購入テーブル．支払方法区分='2'（ポイント購入）の有償利用ポイント
            		retDto.setGoodsPurchaseYen(new BigDecimal(0));                                     // 有償：ポイント外商品購入
            		retDto.setCompensationUsePoint(res.getBigDecimal("PAYMENT_USE_POINT"));            // 有償：利用有償ポイント
            	} else {
            		retDto.setGoodsPurchaseYen(new BigDecimal(0));                                     // 有償：ポイント外商品購入
            		retDto.setCompensationUsePoint(new BigDecimal(0));                                 // 有償：利用有償ポイント
            	}
            	retDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);                         // リターンコード

            	list.add(retDto);
            }

            if (list.size() < 1) {
                retDto = new OrganizationUsageSituationDto();
                retDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_NO);
                list.add(retDto);
            }

            return list;
        } catch (SQLException se) {
            throw new NaiException(se);
        } finally {
            try {
                if (res != null) {
                    res.close();
                }
                if (ps != null) {
                	ps.close();
                }
            } catch (SQLException se) {
                throw new NaiException(se);
            }
        }

	}

}
