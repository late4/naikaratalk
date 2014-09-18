package com.naikara_talk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dto.CustomerManagedDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.CustomerManagedListModel;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様管理ページ詳細<br>
 * <b>クラス名称　　　:</b>お客様管理ページ詳細クラス。<br>
 * <b>クラス概要　　　:</b>お客様管理ページ詳細DAO。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/20 TECS 新規作成。
 */

public class CustomerManagedDao extends AbstractDao {

	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public CustomerManagedDao(Connection con) {
		this.conn = con;
	}

	/**
	 * データを取得する
	 * @param dto 入金管理ページDTO
	 * @return list
	 * @throws NaiException
	 */
	public  List<CustomerManagedDto> search(CustomerManagedDto dto) throws NaiException {

		ResultSet res = null;

		StringBuffer sb = new StringBuffer();

		sb.append(" SELECT ");
		sb.append(" RESULT.PURCHASE_USE_DT, ");
		sb.append(" RESULT.PURCHASE_ID, ");
		sb.append(" RESULT.PURCHASE_NM, ");
		sb.append(" RESULT.BIG_CLASSIFICATION_JNM, ");
		sb.append(" RESULT.MIDDLE_CLASSIFICATION_JNM, ");
		sb.append(" RESULT.SMALL_CLASSIFICATION_JNM, ");
		sb.append(" RESULT.COURSE_JNM, ");
		sb.append(" RESULT.FREE_USE_POINT, ");
		sb.append(" RESULT.COMPENSATION_USE_POINT, ");
		sb.append(" RESULT.GOODS_PURCHASE_YEN ");
		sb.append(" FROM ");
		sb.append(" ( ");

		// 画面の｢対象期間｣＝”0” (期間指定) の場合 売上明細テーブル 又は 商品購入テーブル、レッスン予実テーブルからデータを取得
		if(StringUtils.equals(CustomerManagedListModel.OBJECT_PERIOD_ZERO, dto.getObjectPeriod())) {

			sb.append(getLessonSql(dto));
			sb.append(" UNION ALL ");
			sb.append(getGoodsSql(dto));

		} else {
			// 画面の｢対象期間｣≠”0” (期間指定) の場合  商品購入テーブル、レッスン予実テーブルからデータを取得
			sb.append(getSalesDetailsTrnSql(dto));
			sb.append(" UNION ALL ");
			sb.append(getLessonSql(dto));
			sb.append(" UNION ALL ");
			sb.append(getGoodsSql(dto));
		}

		sb.append(" ) RESULT ");

		sb.append(" ORDER BY PURCHASE_USE_DT ASC,PURCHASE_ID ASC ");
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sb.toString());

            // 実行
            res = ps.executeQuery();

            ArrayList<CustomerManagedDto> list = new ArrayList<CustomerManagedDto>();

            while (res.next()) {
            	CustomerManagedDto retDto = new CustomerManagedDto();

            	// レッスン日/購入日
            	retDto.setPurchaseUseDt(NaikaraStringUtil.converToYYYY_MM_DD(res.getString("PURCHASE_USE_DT")));
            	retDto.setPurchaseId(res.getString("PURCHASE_ID"));                                    // 予約NO/購入ID
            	// コース名/購入商品
				if (StringUtils.isEmpty(res.getString("PURCHASE_NM"))) {
					// レッスン予実テーブルの共通部品：コース名の編集（大分類名、中分類名、小分類名、コース名)
					retDto.setPurchaseNm(NaikaraStringUtil.unionString4(
							res.getString("BIG_CLASSIFICATION_JNM"),
							res.getString("MIDDLE_CLASSIFICATION_JNM"),
							res.getString("SMALL_CLASSIFICATION_JNM"),
							res.getString("COURSE_JNM")));
				} else {
					retDto.setPurchaseNm(res.getString("PURCHASE_NM"));
				}
            	retDto.setFreeUsePoint(res.getBigDecimal("FREE_USE_POINT"));                           // 利用無償ポイント
            	retDto.setCompensationUsePoint(res.getBigDecimal("COMPENSATION_USE_POINT"));           // 利用有償ポイント
            	retDto.setGoodsPurchaseYen(res.getBigDecimal("GOODS_PURCHASE_YEN"));                   // ポイント外商品購入

            	retDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);

            	if (StringUtils.isNotEmpty(res.getString("PURCHASE_USE_DT"))) {
            		list.add(retDto);
            	}
            }

            if (list.size() < 1) {
            	CustomerManagedDto retDto = new CustomerManagedDto();
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
	 * 売上明細テーブルのデータ取得処理のsql
	 * @return sql
	 */
	private String getSalesDetailsTrnSql(CustomerManagedDto dto) {

		StringBuffer sb = new StringBuffer();

		sb.append(" SELECT ");
		sb.append(" SDT.PURCHASE_USE_DT PURCHASE_USE_DT, ");
		sb.append(" SDT.PURCHASE_ID PURCHASE_ID, ");
		sb.append(" SDT.PURCHASE_NM PURCHASE_NM, ");
		sb.append(" '' BIG_CLASSIFICATION_JNM, ");
		sb.append(" '' MIDDLE_CLASSIFICATION_JNM, ");
		sb.append(" '' SMALL_CLASSIFICATION_JNM, ");
		sb.append(" '' COURSE_JNM, ");
		sb.append(" CASE WHEN SDT.FREE_USE_POINT IS NULL THEN 0 ");
		sb.append("          ELSE SDT.FREE_USE_POINT ");
		sb.append("          END FREE_USE_POINT, ");
		sb.append(" CASE WHEN SDT.COMPENSATION_USE_POINT IS NULL THEN 0 ");
		sb.append("          ELSE SDT.COMPENSATION_USE_POINT ");
		sb.append("          END COMPENSATION_USE_POINT, ");
		sb.append(" CASE WHEN SDT.GOODS_PURCHASE_YEN IS NULL THEN 0 ");
		sb.append("          ELSE SDT.GOODS_PURCHASE_YEN ");
		sb.append("          END GOODS_PURCHASE_YEN ");
		sb.append(" FROM ");
		sb.append(" SALES_DETAILS_TRN SDT ");
		sb.append(" WHERE SDT.STUDENT_ID = '").append(dto.getStudentId()).append("' ");
		sb.append(" AND (SDT.YYYY_MM >= '").append(dto.getObjectPeriodFrom()).append("' ");
		sb.append(" AND SDT.YYYY_MM <= '").append(dto.getObjectPeriodTo()).append("') ");

		return sb.toString();
	}

	/**
	 * レッスン予実テーブルのデータ取得処理のsql
	 * @return sql
	 */
	private String getLessonSql(CustomerManagedDto dto) {

		StringBuffer sb = new StringBuffer();

		sb.append(" SELECT ");
		sb.append(" LRPT.LESSON_DT PURCHASE_USE_DT, ");
		sb.append(" LRPT.RESERVATION_NO PURCHASE_ID, ");
		sb.append(" '' PURCHASE_NM, ");
		sb.append(" LRPT.BIG_CLASSIFICATION_JNM BIG_CLASSIFICATION_JNM, ");
		sb.append(" LRPT.MIDDLE_CLASSIFICATION_JNM MIDDLE_CLASSIFICATION_JNM, ");
		sb.append(" LRPT.SMALL_CLASSIFICATION_JNM SMALL_CLASSIFICATION_JNM, ");
		sb.append(" LRPT.COURSE_JNM COURSE_JNM, ");
		sb.append(" CASE WHEN LRPT.FREE_USE_POINT IS NULL THEN 0 ");
		sb.append("          ELSE LRPT.FREE_USE_POINT ");
		sb.append("          END FREE_USE_POINT, ");
		sb.append(" CASE WHEN LRPT.PAYMENT_USE_POINT IS NULL THEN 0 ");
		sb.append("          ELSE LRPT.PAYMENT_USE_POINT ");
		sb.append("          END COMPENSATION_USE_POINT, ");
		sb.append(" 0 GOODS_PURCHASE_YEN ");
		sb.append(" FROM STUDENT_MST SM ");
		sb.append(" LEFT JOIN LESSON_RESERVATION_PERFORMANCE_TRN LRPT ");
		sb.append(" ON SM.STUDENT_ID = LRPT.STUDENT_ID ");
		sb.append(" AND (LRPT.LESSON_DT >= '").append(dto.getPeriodFrom()).append("' ");
		sb.append("      AND  LRPT.LESSON_DT <= '").append(dto.getPeriodTo()).append("') ");
		sb.append(" WHERE SM.STUDENT_ID = '").append(dto.getStudentId()).append("' ");

		return sb.toString();
	}

	/**
	 * 商品購入テーブルのデータ取得処理のsql
	 * @return sql
	 */
	private String getGoodsSql(CustomerManagedDto dto) {

		StringBuffer sb = new StringBuffer();

		sb.append(" SELECT ");
		sb.append(" GPT.PURCHASE_DT PURCHASE_USE_DT, ");
		sb.append(" GPT.PURCHASE_ID PURCHASE_ID, ");
		sb.append(" GPT.GOODS_NM PURCHASE_NM, ");
		sb.append(" '' BIG_CLASSIFICATION_JNM, ");
		sb.append(" '' MIDDLE_CLASSIFICATION_JNM, ");
		sb.append(" '' SMALL_CLASSIFICATION_JNM, ");
		sb.append(" '' COURSE_JNM, ");
		sb.append(" CASE WHEN GPT.FREE_USE_POINT IS NULL THEN 0 ");
		sb.append("          ELSE GPT.FREE_USE_POINT ");
		sb.append("          END FREE_USE_POINT, ");
		sb.append(" CASE WHEN GPT.PAYMENT_USE_POINT IS NULL THEN 0 ");
		sb.append("          ELSE GPT.PAYMENT_USE_POINT ");
		sb.append("          END COMPENSATION_USE_POINT, ");
		sb.append(" CASE WHEN GPT.PURCHASE_YEN IS NULL THEN 0 ");
		sb.append("          ELSE GPT.PURCHASE_YEN ");
		sb.append("          END GOODS_PURCHASE_YEN ");
		sb.append(" FROM STUDENT_MST SM ");
		sb.append(" LEFT JOIN GOODS_PURCHASE_TRN GPT ");
		sb.append(" ON SM.STUDENT_ID = GPT.STUDENT_ID ");
		sb.append(" AND (GPT.PURCHASE_DT >= '").append(dto.getPeriodFrom()).append("' ");
		sb.append("      AND  GPT.PURCHASE_DT <= '").append(dto.getPeriodTo()).append("') ");
		sb.append(" WHERE SM.STUDENT_ID = '").append(dto.getStudentId()).append("' ");

		return sb.toString();
	}

}
