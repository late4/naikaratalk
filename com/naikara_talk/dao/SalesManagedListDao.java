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
import com.naikara_talk.dto.SalesManagedListDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>入金管理ページ<br>
 * <b>クラス名称　　　:</b>入金管理ページクラス。<br>
 * <b>クラス概要　　　:</b>入金管理ページDAO。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/09 TECS 新規作成。
 */

public class SalesManagedListDao extends AbstractDao {

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
	public SalesManagedListDao(Connection con) {
		this.conn = con;
	}

	/**
	 * データ件数を取得する
	 * @param dto
	 * @return int
	 * @throws NaiException
	 */
	public int rowCount(SalesManagedListDto dto) throws NaiException {

		int rowCount = NaikaraTalkConstants.RETURN_CD_DATA_ERR; // データ件数
        int index = 1;

		ResultSet res = null;

		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT ");
		sb.append(" COUNT(*) ");
		sb.append(" FROM ");
		sb.append(" ( ");

		if (StringUtils.equals(NaikaraTalkConstants.CHOICE_ALL_ZERO, dto.getCostomerKbn())) {
			// 画面の｢顧客区分｣ = ”0000” (全て) の場合
			sb.append(getSalesOrganizatuinTrnSql());
			sb.append(" UNION ALL ");
			sb.append(getSalesTrnSql());
		} else if (StringUtils.equals(NaikaraTalkConstants.CUSTOMER_KBN_ORGANIZATION, dto.getCostomerKbn())) {
			// 画面の｢顧客区分｣ = ”0001” (法人) の場合
			sb.append(getSalesOrganizatuinTrnSql());
		} else if (StringUtils.equals(NaikaraTalkConstants.CUSTOMER_KBN_PERSON, dto.getCostomerKbn())) {
			// 画面の｢顧客区分｣ = ”0002” (個人) の場合
			sb.append(getSalesTrnSql());
		}

		sb.append(" ) RESULT ");
		sb.append(" WHERE RESULT.YYYY_MM = ? ");

		// ※画面の｢受講者ID/組織ID｣が入力されている場合 ※曖昧検索
		if (!StringUtils.isEmpty(dto.getStudentOrganizationId())) {
			sb.append(" AND RESULT.STUDENT_ORGANIZATION_ID LIKE '%").append(dto.getStudentOrganizationId()).append("%'");
		}

		// ※画面の｢組織名｣が入力されている場合 ※曖昧検索
		if (!StringUtils.isEmpty(dto.getOrganizationJnm())) {
			sb.append(" AND RESULT.STUDENT_ORGANIZATION_JNM LIKE '%").append(dto.getOrganizationJnm()).append("%'");
		}

		// ※画面の｢受講者名(フリガナ)｣が入力されている場合 ※曖昧検索
		if (!StringUtils.isEmpty(dto.getFamilyFirstKnm())) {
			sb.append(" AND (RESULT.FAMILY_KNM LIKE '").append(dto.getFamilyFirstKnm())
			.append("%' OR RESULT.FIRST_KNM LIKE '").append(dto.getFamilyFirstKnm()).append("%')");
		}

		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sb.toString());

			ps.setString(index++, dto.getObjectYyyyMm());

            // 実行
            res = ps.executeQuery();

            if (res.next()) {
                rowCount = res.getInt(1);
            }

            return rowCount;

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
	 * データ(集計)を取得する
	 * @param dto 入金管理ページDTO
	 * @return list
	 * @throws NaiException
	 */
	public  List<SalesManagedListDto> search(SalesManagedListDto dto) throws NaiException {

		int index = 1;

		ResultSet res = null;

		StringBuffer sb = new StringBuffer();

		sb.append(" SELECT ");
		sb.append(" RESULT.STUDENT_ORGANIZATION_ID, ");
		sb.append(" RESULT.STUDENT_ORGANIZATION_JNM, ");
		sb.append(" RESULT.COMPENSATION_BEFORE_POINT, ");
		sb.append(" RESULT.COMPENSATION_PURCHASE_POINT, ");
		sb.append(" RESULT.COMPENSATION_USE_POINT, ");
		sb.append(" RESULT.GOODS_PURCHASE_YEN, ");
		sb.append(" RESULT.FREE_BEFORE_POINT, ");
		sb.append(" RESULT.FREE_PURCHASE_POINT, ");
		sb.append(" RESULT.FREE_USE_POINT, ");
		sb.append(" RESULT.CUSTOMER_KBN, ");
		sb.append(" BILL_NO ");
		sb.append(" FROM ");
		sb.append(" ( ");

		if (StringUtils.equals(NaikaraTalkConstants.CHOICE_ALL_ZERO, dto.getCostomerKbn())) {
			// 画面の｢顧客区分｣ = ”0000” (全て) の場合
			sb.append(getSalesOrganizatuinTrnSql());
			sb.append(" UNION ALL ");
			sb.append(getSalesTrnSql());
		} else if (StringUtils.equals(NaikaraTalkConstants.CUSTOMER_KBN_ORGANIZATION, dto.getCostomerKbn())) {
			// 画面の｢顧客区分｣ = ”0001” (法人) の場合
			sb.append(getSalesOrganizatuinTrnSql());
		} else if (StringUtils.equals(NaikaraTalkConstants.CUSTOMER_KBN_PERSON, dto.getCostomerKbn())) {
			// 画面の｢顧客区分｣ = ”0002” (個人) の場合
			sb.append(getSalesTrnSql());
		}

		sb.append(" ) RESULT ");
		sb.append(" WHERE RESULT.YYYY_MM = ? ");

		// ※画面の｢受講者ID/組織ID｣が入力されている場合 ※曖昧検索
		if (!StringUtils.isEmpty(dto.getStudentOrganizationId())) {
			sb.append(" AND RESULT.STUDENT_ORGANIZATION_ID LIKE '%").append(dto.getStudentOrganizationId()).append("%'");
		}

		// ※画面の｢組織名｣が入力されている場合 ※曖昧検索
		if (!StringUtils.isEmpty(dto.getOrganizationJnm())) {
			sb.append(" AND RESULT.STUDENT_ORGANIZATION_JNM LIKE '%").append(dto.getOrganizationJnm()).append("%'");
		}

		// ※画面の｢受講者名(フリガナ)｣が入力されている場合 ※曖昧検索
		if (!StringUtils.isEmpty(dto.getFamilyFirstKnm())) {
			sb.append(" AND (RESULT.FAMILY_KNM LIKE '").append(dto.getFamilyFirstKnm())
			.append("%' OR RESULT.FIRST_KNM LIKE '").append(dto.getFamilyFirstKnm()).append("%')");
		}

		sb.append(" ORDER BY RESULT.STUDENT_ORGANIZATION_ID ");

		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sb.toString());

			ps.setString(index++, dto.getObjectYyyyMm());

            // 実行
            res = ps.executeQuery();

            ArrayList<SalesManagedListDto> list = new ArrayList<SalesManagedListDto>();

            while (res.next()) {
            	SalesManagedListDto retDto = new SalesManagedListDto();
            	retDto.setStudentOrganizationId(res.getString("STUDENT_ORGANIZATION_ID"));             // 受講者ID／組織ID
            	retDto.setStudentOrganizationJnm(res.getString("STUDENT_ORGANIZATION_JNM"));           // 受講者名／組織名
            	retDto.setCompensationBeforePoint(res.getBigDecimal("COMPENSATION_BEFORE_POINT"));     // 前受有償ポイント(前月残)
            	retDto.setCompensationPurchasePoint(res.getBigDecimal("COMPENSATION_PURCHASE_POINT")); // 購入有償ポイント(合計)
            	retDto.setCompensationUsePoint(res.getBigDecimal("COMPENSATION_USE_POINT"));           // 利用ポイント(売上)※有償
            	retDto.setGoodsPurchaseYen(res.getBigDecimal("GOODS_PURCHASE_YEN"));                   // ポイント外商品購入
            	retDto.setFreeBeforePoint(res.getBigDecimal("FREE_BEFORE_POINT"));                     // 前受無償ポイント(前月残)
            	retDto.setFreePurchasePoint(res.getBigDecimal("FREE_PURCHASE_POINT"));                 // 購入無償ポイント(合計)
            	retDto.setFreeUsePoint(res.getBigDecimal("FREE_USE_POINT"));                           // 利用ポイント(無償)
            	retDto.setCostomerKbn(res.getString("CUSTOMER_KBN"));                                  // 顧客区分
            	retDto.setBillNo(res.getString("BILL_NO"));                                            // 請求書番号

            	retDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);

                list.add(retDto);

            }

            if (list.size() < 1) {
            	SalesManagedListDto retDto = new SalesManagedListDto();
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
	 * データ(明細)を取得する
	 * @param dto
	 * @return
	 * @throws NaiException
	 */
	public List<SalesManagedListDto> searchDetail(
			ConditionMapper conditions, OrderCondition orderConditions)
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

            ArrayList<SalesManagedListDto> list = new ArrayList<SalesManagedListDto>();

            while (res.next()) {
            	SalesManagedListDto retDto = new SalesManagedListDto();
            	retDto.setStudentId(res.getString("STUDENT_ID"));                                        // 受講者ID
            	retDto.setFamilyFirstJnm(res.getString("FAMILY_FIRST_JNM"));                             // 受講者名
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
            	SalesManagedListDto retDto = new SalesManagedListDto();
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
	 * 売上集計法人テーブルデータを取得のsql
	 * @return sql
	 */
	private String getSalesOrganizatuinTrnSql() {

		StringBuffer sb = new StringBuffer();

		sb.append(" SELECT ");
		sb.append(" YYYY_MM, ");
		sb.append(" ORGANIZATION_ID STUDENT_ORGANIZATION_ID, ");
		sb.append(" ORGANIZATION_JNM STUDENT_ORGANIZATION_JNM, ");
		sb.append(" '' FAMILY_KNM, ");
		sb.append(" '' FIRST_KNM, ");
		sb.append(" CASE WHEN COMPENSATION_BEFORE_POINT IS NULL THEN 0 ");
		sb.append("      ELSE COMPENSATION_BEFORE_POINT ");
		sb.append("      END COMPENSATION_BEFORE_POINT, ");
		sb.append(" CASE WHEN COMPENSATION_PURCHASE_POINT IS NULL THEN 0 ");
		sb.append("      ELSE COMPENSATION_PURCHASE_POINT ");
		sb.append("      END COMPENSATION_PURCHASE_POINT, ");
		sb.append(" CASE WHEN COMPENSATION_USE_POINT IS NULL THEN 0 ");
		sb.append("      ELSE COMPENSATION_USE_POINT ");
		sb.append("      END COMPENSATION_USE_POINT, ");
		sb.append(" CASE WHEN GOODS_PURCHASE_YEN IS NULL THEN 0 ");
		sb.append("      ELSE GOODS_PURCHASE_YEN ");
		sb.append("      END GOODS_PURCHASE_YEN, ");
		sb.append(" CASE WHEN FREE_BEFORE_POINT IS NULL THEN 0 ");
		sb.append("      ELSE FREE_BEFORE_POINT ");
		sb.append("      END FREE_BEFORE_POINT, ");
		sb.append(" CASE WHEN FREE_PURCHASE_POINT IS NULL THEN 0 ");
		sb.append("      ELSE FREE_PURCHASE_POINT ");
		sb.append("      END FREE_PURCHASE_POINT, ");
		sb.append(" CASE WHEN FREE_USE_POINT IS NULL THEN 0 ");
		sb.append("      ELSE FREE_USE_POINT ");
		sb.append("      END FREE_USE_POINT, ");
		sb.append(" '").append(NaikaraTalkConstants.CUSTOMER_KBN_ORGANIZATION).append("' CUSTOMER_KBN, ");
		sb.append(" BILL_NO ");
		sb.append(" FROM ");
		sb.append(" SALES_ORGANIZATION_TRN ");

		return sb.toString();
	}

	/**
	 * 売上集計テーブルデータを取得のsql
	 * @return sql
	 */
	private String getSalesTrnSql() {

		StringBuffer sb = new StringBuffer();

		sb.append(" SELECT ");
		sb.append(" YYYY_MM, ");
		sb.append(" STUDENT_ID STUDENT_ORGANIZATION_ID, ");
		sb.append(" FAMILY_FIRST_JNM STUDENT_ORGANIZATION_JNM, ");
		sb.append(" FAMILY_KNM, ");
		sb.append(" FIRST_KNM, ");
		sb.append(" CASE WHEN COMPENSATION_BEFORE_POINT IS NULL THEN 0 ");
		sb.append("      ELSE COMPENSATION_BEFORE_POINT ");
		sb.append("      END COMPENSATION_BEFORE_POINT, ");
		sb.append(" CASE WHEN COMPENSATION_PURCHASE_POINT IS NULL THEN 0 ");
		sb.append("      ELSE COMPENSATION_PURCHASE_POINT ");
		sb.append("      END COMPENSATION_PURCHASE_POINT, ");
		sb.append(" CASE WHEN COMPENSATION_USE_POINT IS NULL THEN 0 ");
		sb.append("      ELSE COMPENSATION_USE_POINT ");
		sb.append("      END COMPENSATION_USE_POINT, ");
		sb.append(" CASE WHEN GOODS_PURCHASE_YEN IS NULL THEN 0 ");
		sb.append("      ELSE GOODS_PURCHASE_YEN ");
		sb.append("      END GOODS_PURCHASE_YEN, ");
		sb.append(" CASE WHEN FREE_BEFORE_POINT IS NULL THEN 0 ");
		sb.append("      ELSE FREE_BEFORE_POINT ");
		sb.append("      END FREE_BEFORE_POINT, ");
		sb.append(" CASE WHEN FREE_PURCHASE_POINT IS NULL THEN 0 ");
		sb.append("      ELSE FREE_PURCHASE_POINT ");
		sb.append("      END FREE_PURCHASE_POINT, ");
		sb.append(" CASE WHEN FREE_USE_POINT IS NULL THEN 0 ");
		sb.append("      ELSE FREE_USE_POINT ");
		sb.append("      END FREE_USE_POINT, ");
		sb.append(" '").append(NaikaraTalkConstants.CUSTOMER_KBN_PERSON).append("' CUSTOMER_KBN, ");
		sb.append(" BILL_NO ");
		sb.append(" FROM ");
		sb.append(" SALES_TRN ");
		sb.append(" WHERE CUSTOMER_KBN = '").append(NaikaraTalkConstants.CUSTOMER_KBN_PERSON).append("'  ");

		return sb.toString();
	}

}
