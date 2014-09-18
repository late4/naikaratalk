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
import com.naikara_talk.dto.SalesManagedDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>入金管理ページ明細<br>
 * <b>クラス名称　　　:</b>入金管理ページ明細クラス。<br>
 * <b>クラス概要　　　:</b>入金管理ページ明細DAO。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/09 TECS 新規作成。
 */

public class SalesManagedDao {

	/** 年月 条件項目 */
    public static final String COND_YYYY_MM = "ST.YYYY_MM";

    /** 受講者ID 条件項目 */
    public static final String COND_OSTUDENT_ID = "ST.STUDENT_ID";

    /** 組織ID 条件項目 */
    public static final String COND_ORGANIZATION_ID = "ST.ORGANIZATION_ID";

    /** 受講者ID 条件項目 */
    public static final String COND_STUDENT_ID = "SDT.STUDENT_ID";

    /** 購入日／利用日 条件項目 */
    public static final String COND_PURCHASE_USE_DT = "SDT.PURCHASE_USE_DT";

    /** レッスン時刻名 条件項目 */
    public static final String COND_LESSON_TM_NM = "SDT.LESSON_TM_NM";

    /** 購入ID(所有ID／予約No) 条件項目 */
    public static final String COND_PURCHASE_ID = "SDT.PURCHASE_ID";

	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public SalesManagedDao(Connection con) {
		this.conn = con;
	}

	/**
	 * データ(明細)を取得する
	 * @param dto
	 * @return
	 * @throws NaiException
	 */
	public List<SalesManagedDto> searchDetail(
			ConditionMapper conditions, OrderCondition orderConditions, String costomerKbn)
			throws NaiException {

		ResultSet res = null;

		StringBuffer sb = new StringBuffer();

		sb.append(" SELECT ");
		sb.append(" SDT.STUDENT_ID, ");
		sb.append(" ST.FAMILY_FIRST_JNM, ");
		sb.append(" SDT.PURCHASE_USE_DT, ");
		sb.append(" SDT.PURCHASE_ID, ");
		sb.append(" SDT.PURCHASE_NM, ");
		sb.append(" CASE WHEN SDT.COMPENSATION_PURCHASE_POINT IS NULL THEN 0 ");
		sb.append("      ELSE SDT.COMPENSATION_PURCHASE_POINT ");
		sb.append("      END COMPENSATION_PURCHASE_POINT, ");
		sb.append(" CASE WHEN SDT.FREE_USE_POINT IS NULL THEN 0 ");
		sb.append("      ELSE SDT.FREE_USE_POINT ");
		sb.append("      END FREE_USE_POINT, ");
		sb.append(" CASE WHEN SDT.COMPENSATION_USE_POINT IS NULL THEN 0 ");
		sb.append("      ELSE SDT.COMPENSATION_USE_POINT ");
		sb.append("      END COMPENSATION_USE_POINT, ");
		sb.append(" CASE WHEN SDT.GOODS_PURCHASE_YEN IS NULL THEN 0 ");
		sb.append("      ELSE SDT.GOODS_PURCHASE_YEN ");
		sb.append("      END GOODS_PURCHASE_YEN ");
		sb.append(" FROM ");
		sb.append(" SALES_TRN ST ");
		sb.append(" INNER JOIN SALES_DETAILS_TRN SDT ");
		sb.append(" ON ST.YYYY_MM = SDT.YYYY_MM ");
		sb.append(" AND ST.STUDENT_ID = SDT.STUDENT_ID ");

		// 抽出条件の設定
		if(!StringUtils.isEmpty(conditions.getConditionString())) {
			sb.append("WHERE ");
			sb.append(conditions.getConditionString());
		}
		// 並び順の設定
		if(!StringUtils.isEmpty(orderConditions.getOrderString())) {
			sb.append(orderConditions.getOrderString());
		}

		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sb.toString());

			// パラメタの設定
            int i = 0;
            for( QueryCondition cond : conditions.getConditions()){
            	for(String val : cond.getValues()){
            		ps.setString(i + 1, val);
            		i++;
            	}
            }

            // 実行
            res = ps.executeQuery();

            ArrayList<SalesManagedDto> list = new ArrayList<SalesManagedDto>();

            while (res.next()) {
            	SalesManagedDto retDto = new SalesManagedDto();

            	if (StringUtils.equals(NaikaraTalkConstants.CUSTOMER_KBN_ORGANIZATION, costomerKbn)) {

            		// 法人の場合
            		retDto.setStudentId(res.getString("STUDENT_ID"));                                   // 受講者ID
                	retDto.setFamilyFirstJnm(res.getString("FAMILY_FIRST_JNM"));                        // 受講者名
            	} else if (StringUtils.equals(NaikaraTalkConstants.CUSTOMER_KBN_PERSON, costomerKbn)) {

            		// 個人の場合 受講者ID 受講者名を表示しない
            		retDto.setStudentId("");                                                            // 受講者ID
                	retDto.setFamilyFirstJnm("");                                                       // 受講者名
            	}

            	 // ポイント購入日/利用日
            	retDto.setPurchaseUseDt(NaikaraStringUtil.converToYYYY_MM_DD(res.getString("PURCHASE_USE_DT")));
            	retDto.setPurchaseId(res.getString("PURCHASE_ID"));                                      // 購入ID
            	retDto.setPurchaseNm(res.getString("PURCHASE_NM"));                                      // 購入(レッスン／商品)
            	retDto.setCompensationPurchasePoint(res.getBigDecimal("COMPENSATION_PURCHASE_POINT"));   // 購入有償ポイント
            	retDto.setFreeUsePoint(res.getBigDecimal("FREE_USE_POINT"));                             // 利用無償ポイント
            	retDto.setCompensationUsePoint(res.getBigDecimal("COMPENSATION_USE_POINT"));             // 利用有償ポイント(売上)
            	retDto.setGoodsPurchaseYen(res.getBigDecimal("GOODS_PURCHASE_YEN"));                     // ポイント外商品購入

            	retDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);

                list.add(retDto);

            }

            if (list.size() < 1) {
            	SalesManagedDto retDto = new SalesManagedDto();
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
