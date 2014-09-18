package com.naikara_talk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dto.CustomerManagedListDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.CustomerManagedListModel;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様管理ページ<br>
 * <b>クラス名称　　　:</b>お客様管理ページクラス。<br>
 * <b>クラス概要　　　:</b>お客様管理ページDAO。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/20 TECS 新規作成。
 */

public class CustomerManagedListDao extends AbstractDao {

	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public CustomerManagedListDao(Connection con) {
		this.conn = con;
	}

	/**
	 * データ件数を取得する
	 * @param dto
	 * @return int
	 * @throws NaiException
	 */
	public int rowCount(CustomerManagedListDto dto) throws NaiException {

		int rowCount = NaikaraTalkConstants.RETURN_CD_DATA_ERR; // データ件数

		ResultSet res = null;

		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT COUNT(*) FROM ( ");
		sb.append(" SELECT ");
		sb.append(" STUDENT_ID ");
		sb.append(" FROM ");
		sb.append(" ( ");

		// 画面の｢対象期間｣＝”0” (期間指定) の場合 当月分のデータだけ
		if(StringUtils.equals(CustomerManagedListModel.OBJECT_PERIOD_ZERO, dto.getObjectPeriod())) {

			sb.append(getNowSql(dto));

		} else {
			// 画面の｢対象期間｣≠”0” (期間指定) の場合 当月分のデータ と 先月以前のデータ
			sb.append(getNowSql(dto));
			sb.append(" UNION ALL ");
			sb.append(getBeforeSql(dto));

		}

		sb.append(" ) RESULT ");
		sb.append(" WHERE 1 = 1 ");

		// ※画面の｢受講者ID｣が入力されている場合 ※曖昧検索
		if (!StringUtils.isEmpty(dto.getStudentId())) {
			sb.append(" AND RESULT.STUDENT_ID LIKE '%").append(dto.getStudentId()).append("%'");
		}
		// ※画面の｢受講者名(漢字)｣が入力されている場合 ※曖昧検索
		if (!StringUtils.isEmpty(dto.getFamilyFirstJnm())) {
			sb.append(" AND (RESULT.FAMILY_FIRST_JNM LIKE '%").append(dto.getFamilyFirstJnm())
			.append("%' OR RESULT.FAMILY_JNM LIKE '%").append(dto.getFamilyFirstJnm())
			.append("%' OR RESULT.FIRST_JNM LIKE '%").append(dto.getFamilyFirstJnm()).append("%') ");

		}
		// ※画面の｢受講者名(フリガナ)｣が入力されている場合 ※曖昧検索
		if (!StringUtils.isEmpty(dto.getFamilyFirstKnm())) {
			sb.append(" AND (RESULT.FAMILY_KNM LIKE '").append(dto.getFamilyFirstKnm())
			.append("%' OR RESULT.FIRST_KNM LIKE '").append(dto.getFamilyFirstKnm()).append("%')");
		}
		// ※画面の｢受講者名(ローマ字)｣が入力されている場合 ※曖昧検索
		if (!StringUtils.isEmpty(dto.getFamilyFirstRomaji())) {
			sb.append(" AND (RESULT.FAMILY_ROMAJI LIKE '%").append(dto.getFamilyFirstRomaji())
			.append("%' OR RESULT.FIRST_ROMAJI LIKE '%").append(dto.getFamilyFirstRomaji()).append("%')");
		}
		// ※画面の｢受講者名(ニックネーム)｣が入力されている場合 ※曖昧検索
		if (!StringUtils.isEmpty(dto.getNickNm())) {
			sb.append(" AND RESULT.NICK_NM LIKE '%").append(dto.getNickNm()).append("%'");
		}
		// ※画面の｢組織名｣が入力されている場合 ※曖昧検索
		if (!StringUtils.isEmpty(dto.getOrganizationJnm())) {
			sb.append(" AND RESULT.ORGANIZATION_JNM LIKE '%").append(dto.getOrganizationJnm()).append("%'");
		}

		sb.append(" GROUP BY RESULT.STUDENT_ID ");
		sb.append(" ) COUNTRESULT ");
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sb.toString());

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
	 * データを取得する
	 * @param dto 入金管理ページDTO
	 * @return list
	 * @throws NaiException
	 */
	public  List<CustomerManagedListDto> search(CustomerManagedListDto dto) throws NaiException {

		ResultSet res = null;

		StringBuffer sb = new StringBuffer();

		sb.append(" SELECT ");
		sb.append(" RESULT.STUDENT_ID, ");
		sb.append(" RESULT.FAMILY_FIRST_JNM, ");
		sb.append(" RESULT.FAMILY_JNM, ");
		sb.append(" RESULT.FIRST_JNM, ");
		sb.append(" RESULT.ORGANIZATION_JNM, ");
		sb.append(" SUM(RESULT.FREE_USE_POINT) FREE_USE_POINT, ");
		sb.append(" SUM(RESULT.COMPENSATION_USE_POINT) COMPENSATION_USE_POINT, ");
		sb.append(" RESULT.BALANCE_POINT, ");
		sb.append(" SUM(RESULT.GOODS_PURCHASE_YEN) GOODS_PURCHASE_YEN, ");
		sb.append(" RESULT.CUSTOMER_KBN CUSTOMER_KBN, ");
		sb.append(" RESULT.FAMILY_KNM, ");
		sb.append(" RESULT.FIRST_KNM, ");
		sb.append(" RESULT.FAMILY_ROMAJI, ");
		sb.append(" RESULT.FIRST_ROMAJI, ");
		sb.append(" RESULT.NICK_NM, ");
		sb.append(" RESULT.MAIL_ADDRESS1 ");
		sb.append(" FROM ");
		sb.append(" ( ");

		// 画面の｢対象期間｣＝”0” (期間指定) の場合 当月分のデータだけ
		if(StringUtils.equals(CustomerManagedListModel.OBJECT_PERIOD_ZERO, dto.getObjectPeriod())) {

			sb.append(getNowSql(dto));

		} else {
			// 画面の｢対象期間｣≠”0” (期間指定) の場合 当月分のデータ と 先月以前のデータ
			sb.append(getNowSql(dto));
			sb.append(" UNION ALL ");
			sb.append(getBeforeSql(dto));

		}

		sb.append(" ) RESULT ");
		sb.append(" WHERE 1 = 1 ");

		// ※画面の｢受講者ID｣が入力されている場合 ※曖昧検索
		if (!StringUtils.isEmpty(dto.getStudentId())) {
			sb.append(" AND RESULT.STUDENT_ID LIKE '%").append(dto.getStudentId()).append("%'");
		}
		// ※画面の｢受講者名(漢字)｣が入力されている場合 ※曖昧検索
		if (!StringUtils.isEmpty(dto.getFamilyFirstJnm())) {
			sb.append(" AND (RESULT.FAMILY_FIRST_JNM LIKE '%").append(dto.getFamilyFirstJnm())
			.append("%' OR RESULT.FAMILY_JNM LIKE '%").append(dto.getFamilyFirstJnm())
			.append("%' OR RESULT.FIRST_JNM LIKE '%").append(dto.getFamilyFirstJnm()).append("%') ");

		}
		// ※画面の｢受講者名(フリガナ)｣が入力されている場合 ※曖昧検索
		if (!StringUtils.isEmpty(dto.getFamilyFirstKnm())) {
			sb.append(" AND (RESULT.FAMILY_KNM LIKE '").append(dto.getFamilyFirstKnm())
			.append("%' OR RESULT.FIRST_KNM LIKE '").append(dto.getFamilyFirstKnm()).append("%')");
		}
		// ※画面の｢受講者名(ローマ字)｣が入力されている場合 ※曖昧検索
		if (!StringUtils.isEmpty(dto.getFamilyFirstRomaji())) {
			sb.append(" AND (RESULT.FAMILY_ROMAJI LIKE '%").append(dto.getFamilyFirstRomaji())
			.append("%' OR RESULT.FIRST_ROMAJI LIKE '%").append(dto.getFamilyFirstRomaji()).append("%')");
		}
		// ※画面の｢受講者名(ニックネーム)｣が入力されている場合 ※曖昧検索
		if (!StringUtils.isEmpty(dto.getNickNm())) {
			sb.append(" AND RESULT.NICK_NM LIKE '%").append(dto.getNickNm()).append("%'");
		}
		// ※画面の｢組織名｣が入力されている場合 ※曖昧検索
		if (!StringUtils.isEmpty(dto.getOrganizationJnm())) {
			sb.append(" AND RESULT.ORGANIZATION_JNM LIKE '%").append(dto.getOrganizationJnm()).append("%'");
		}

		sb.append(" GROUP BY RESULT.STUDENT_ID ");
		sb.append(" ORDER BY RESULT.FAMILY_KNM,RESULT.FIRST_KNM,RESULT.STUDENT_ID ");
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sb.toString());

            // 実行
            res = ps.executeQuery();

            ArrayList<CustomerManagedListDto> list = new ArrayList<CustomerManagedListDto>();

            while (res.next()) {
            	CustomerManagedListDto retDto = new CustomerManagedListDto();
            	retDto.setStudentId(res.getString("STUDENT_ID"));                                     // 受講者ID
            	retDto.setFamilyFirstJnm(res.getString("FAMILY_FIRST_JNM"));                          // 受講者名
            	retDto.setFamilyJnm(res.getString("FAMILY_JNM"));                                     // 名前(姓)
            	retDto.setFirstJnm(res.getString("FIRST_JNM"));                                       // 名前(名)
            	retDto.setOrganizationJnm(res.getString("ORGANIZATION_JNM"));                         // 組織名
            	retDto.setFreeUsePoint(res.getBigDecimal("FREE_USE_POINT"));                          // 利用無償ポイント(合計)
            	retDto.setCompensationUsePoint(res.getBigDecimal("COMPENSATION_USE_POINT"));          // 利用有償ポイント(合計)
            	retDto.setBalancePoint(res.getBigDecimal("BALANCE_POINT"));                           // ポイント残高(合計)
            	retDto.setGoodsPurchaseYen(res.getBigDecimal("GOODS_PURCHASE_YEN"));                  // ポイント外商品購入
            	retDto.setCostomerKbn(res.getString("CUSTOMER_KBN"));                                 // 顧客区分
            	// 受講者名(フリガナ)
            	retDto.setFamilyFirstKnm(NaikaraStringUtil.unionName(res.getString("FAMILY_KNM"), res.getString("FIRST_KNM")));
            	// 受講者名(ローマ字)
            	retDto.setFamilyFirstRomaji(NaikaraStringUtil.unionName(res.getString("FAMILY_ROMAJI"), res.getString("FIRST_ROMAJI")));
            	retDto.setNickNm(res.getString("NICK_NM"));                                           // 受講者名(ニックネーム)
            	retDto.setMailAddress1(res.getString("MAIL_ADDRESS1"));                               // 受講者(メールアドレス)

            	retDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);

                list.add(retDto);

            }

            if (list.size() < 1) {
            	CustomerManagedListDto retDto = new CustomerManagedListDto();
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
	 * 先月以前のデータ取得処理のsql
	 * @return sql
	 */
	private String getBeforeSql(CustomerManagedListDto dto) {

		StringBuffer sb = new StringBuffer();

		sb.append(" SELECT ");
		sb.append(" SUB.STUDENT_ID, ");
		sb.append(" '' FAMILY_JNM, ");
		sb.append(" '' FIRST_JNM, ");
		sb.append(" SUB.FAMILY_FIRST_JNM, ");
		sb.append(" SUB.ORGANIZATION_JNM, ");
		sb.append(" SUB.FREE_USE_POINT, ");
		sb.append(" SUB.COMPENSATION_USE_POINT, ");
		sb.append(" SUB.BALANCE_POINT, ");
		sb.append(" SUB.GOODS_PURCHASE_YEN, ");
		sb.append(" SUB.CUSTOMER_KBN CUSTOMER_KBN, ");
		sb.append(" SUB.FAMILY_KNM, ");
		sb.append(" SUB.FIRST_KNM, ");
		sb.append(" SUB.FAMILY_ROMAJI, ");
		sb.append(" SUB.FIRST_ROMAJI, ");
		sb.append(" SUB.NICK_NM, ");
		sb.append(" SUB.MAIL_ADDRESS1 ");
		sb.append(" FROM ");
		sb.append(" ( ");
		sb.append("     SELECT ");
		sb.append("     ST.YYYY_MM, ");
		sb.append("     ST.STUDENT_ID STUDENT_ID, ");
		sb.append("     ST.FAMILY_FIRST_JNM FAMILY_FIRST_JNM, ");
		sb.append("     SOT.ORGANIZATION_JNM ORGANIZATION_JNM, ");
		sb.append("     CASE WHEN ST.FREE_USE_POINT IS NULL THEN 0 ");
		sb.append("          ELSE ST.FREE_USE_POINT ");
		sb.append("          END FREE_USE_POINT, ");
		sb.append("     CASE WHEN ST.COMPENSATION_USE_POINT IS NULL THEN 0 ");
		sb.append("          ELSE ST.COMPENSATION_USE_POINT ");
		sb.append("          END COMPENSATION_USE_POINT, ");
		sb.append("     CASE WHEN LEP.BALANCE_POINT IS NULL THEN 0 ");
		sb.append("          ELSE LEP.BALANCE_POINT ");
		sb.append("          END BALANCE_POINT, ");
		sb.append("     CASE WHEN ST.GOODS_PURCHASE_YEN IS NULL THEN 0 ");
		sb.append("          ELSE ST.GOODS_PURCHASE_YEN ");
		sb.append("          END GOODS_PURCHASE_YEN, ");
		sb.append("     ST.CUSTOMER_KBN CUSTOMER_KBN, ");
		sb.append("     ST.FAMILY_KNM, ");
		sb.append("     ST.FIRST_KNM, ");
		sb.append("     ST.FAMILY_ROMAJI, ");
		sb.append("     ST.FIRST_ROMAJI, ");
		sb.append("     ST.NICK_NM, ");
		sb.append("     ST.MAIL_ADDRESS1 ");
		sb.append("     FROM ");
		sb.append("     SALES_TRN ST ");
		sb.append("     LEFT JOIN SALES_ORGANIZATION_TRN SOT ");
		sb.append("     ON ST.ORGANIZATION_ID = SOT.ORGANIZATION_ID ");
		sb.append("     LEFT JOIN ");
		sb.append("          (SELECT POT.STUDENT_ID,SUM(POT.BALANCE_POINT) BALANCE_POINT ");
		sb.append("          FROM POINT_OWNERSHIP_TRN POT ");
		sb.append("          GROUP BY STUDENT_ID ");
		sb.append("          ) LEP ");
		sb.append("     ON ST.STUDENT_ID = LEP.STUDENT_ID ");
		sb.append("     WHERE 1=1 ");

		// 顧客区分 は 全ての場合
		if (StringUtils.equals(dto.getCostomerKbn(), NaikaraTalkConstants.CHOICE_ALL_ZERO)) {
			sb.append("     AND ST.CUSTOMER_KBN = '").append(NaikaraTalkConstants.CUSTOMER_KBN_PERSON)
			.append("'  OR ST.CUSTOMER_KBN = '").append(NaikaraTalkConstants.CUSTOMER_KBN_ORGANIZATION).append("' ");
		}
		// 顧客区分 は 法人の場合
		if (StringUtils.equals(dto.getCostomerKbn(), NaikaraTalkConstants.CUSTOMER_KBN_ORGANIZATION)) {
			sb.append("     AND ST.CUSTOMER_KBN = '").append(NaikaraTalkConstants.CUSTOMER_KBN_ORGANIZATION).append("' ");
		}

		// 顧客区分 は 個人の場合
		if (StringUtils.equals(dto.getCostomerKbn(), NaikaraTalkConstants.CUSTOMER_KBN_PERSON)) {
			sb.append("     AND ST.CUSTOMER_KBN = '").append(NaikaraTalkConstants.CUSTOMER_KBN_PERSON).append("' ");
		}

		sb.append("     ) SUB ");
		sb.append(" WHERE 1=1 ");
		// 対象期間
		sb.append(" AND (SUB.YYYY_MM >= '").append(dto.getObjectPeriodFrom()).append("' ");
		sb.append(" AND SUB.YYYY_MM <= '").append(dto.getObjectPeriodTo()).append("') ");

		return sb.toString();
	}

	/**
	 * 当月分のデータ取得処理のsql
	 * @return sql
	 */
	private String getNowSql(CustomerManagedListDto dto) {

		StringBuffer sb = new StringBuffer();

		sb.append(" SELECT ");
		sb.append(" SM.STUDENT_ID STUDENT_ID, ");
		sb.append(" SM.FAMILY_JNM FAMILY_JNM, ");
		sb.append(" SM.FIRST_JNM FIRST_JNM, ");
		sb.append(" '' FAMILY_FIRST_JNM, ");
		sb.append(" OM.ORGANIZATION_JNM ORGANIZATION_JNM, ");
		sb.append(" CASE WHEN ");
		sb.append("   (CASE WHEN GPTP.FREE_USE_POINT IS NULL THEN 0 ");
		sb.append("    ELSE GPTP.FREE_USE_POINT END) + ");
		sb.append("   (CASE WHEN LES.FREE_USE_POINT IS NULL THEN 0 ");
		sb.append("    ELSE LES.FREE_USE_POINT END) ");
		sb.append(" IS NULL THEN 0 ");
		sb.append(" ELSE ");
		sb.append("   (CASE WHEN GPTP.FREE_USE_POINT IS NULL THEN 0 ");
		sb.append("    ELSE GPTP.FREE_USE_POINT END) + ");
		sb.append("   (CASE WHEN LES.FREE_USE_POINT IS NULL THEN 0 ");
		sb.append("    ELSE LES.FREE_USE_POINT END) ");
		sb.append(" END FREE_USE_POINT, ");
		sb.append(" CASE WHEN ");
		sb.append("   (CASE WHEN GPTP.PAYMENT_USE_POINT IS NULL THEN 0 ");
		sb.append("    ELSE GPTP.PAYMENT_USE_POINT END) + ");
		sb.append("   (CASE WHEN LES.PAYMENT_USE_POINT IS NULL THEN 0 ");
		sb.append("    ELSE LES.PAYMENT_USE_POINT END) ");
		sb.append(" IS NULL THEN 0 ");
		sb.append(" ELSE ");
		sb.append("   (CASE WHEN GPTP.PAYMENT_USE_POINT IS NULL THEN 0 ");
		sb.append("    ELSE GPTP.PAYMENT_USE_POINT END) + ");
		sb.append("   (CASE WHEN LES.PAYMENT_USE_POINT IS NULL THEN 0 ");
		sb.append("    ELSE LES.PAYMENT_USE_POINT END) ");
		sb.append(" END COMPENSATION_USE_POINT, ");
		sb.append(" CASE WHEN LEP.BALANCE_POINT IS NULL THEN 0 ");
		sb.append("      ELSE LEP.BALANCE_POINT ");
		sb.append("      END BALANCE_POINT, ");
		sb.append(" CASE WHEN GPTP.PURCHASE_YEN IS NULL THEN 0 ");
		sb.append("      ELSE GPTP.PURCHASE_YEN ");
		sb.append("      END GOODS_PURCHASE_YEN, ");
		sb.append(" SM.CUSTOMER_KBN CUSTOMER_KBN, ");
		sb.append(" SM.FAMILY_KNM, ");
		sb.append(" SM.FIRST_KNM, ");
		sb.append(" SM.FAMILY_ROMAJI, ");
		sb.append(" SM.FIRST_ROMAJI, ");
		sb.append(" SM.NICK_NM, ");
		sb.append(" SM.MAIL_ADDRESS1 ");
		sb.append(" FROM ");
		sb.append(" STUDENT_MST SM ");
		sb.append(" LEFT JOIN ORGANIZATION_MST OM ");
		sb.append(" ON SM.ORGANIZATION_ID = OM.ORGANIZATION_ID ");
		sb.append(" AND OM.CONTRACT_STR_DT <= '").append(dto.getSystemDate()).append("' ");
		sb.append(" AND  OM.CONTRACT_END_DT >= '").append(dto.getSystemDate()).append("' ");
		sb.append(" LEFT JOIN ");
		sb.append("      (SELECT POT.STUDENT_ID,SUM(POT.BALANCE_POINT) BALANCE_POINT ");
		sb.append("          FROM POINT_OWNERSHIP_TRN POT ");
		sb.append("          GROUP BY STUDENT_ID ");
		sb.append("      ) LEP ");
		sb.append(" ON SM.STUDENT_ID = LEP.STUDENT_ID ");
		sb.append(" LEFT JOIN ");
		sb.append("      (SELECT LRPT.STUDENT_ID,SUM(LRPT.FREE_USE_POINT) FREE_USE_POINT,SUM(LRPT.PAYMENT_USE_POINT) PAYMENT_USE_POINT ");
		sb.append("          FROM LESSON_RESERVATION_PERFORMANCE_TRN LRPT ");
		sb.append("          WHERE (LRPT.LESSON_DT >= '").append(dto.getPeriodFrom()).append("' ");
		sb.append("            AND  LRPT.LESSON_DT <= '").append(dto.getPeriodTo()).append("') ");
		sb.append("          GROUP BY LRPT.STUDENT_ID ");
		sb.append("      ) LES ");
		sb.append(" ON SM.STUDENT_ID = LES.STUDENT_ID ");
		sb.append(" LEFT JOIN ");
		sb.append("      (SELECT GPT1.STUDENT_ID,SUM(GPT1.FREE_USE_POINT) FREE_USE_POINT,SUM(GPT1.PAYMENT_USE_POINT) PAYMENT_USE_POINT, ");
		sb.append("               SUM(GPT1.PURCHASE_YEN) PURCHASE_YEN ");
		sb.append("          FROM GOODS_PURCHASE_TRN GPT1 ");
		sb.append("          WHERE (GPT1.PURCHASE_DT >= '").append(dto.getPeriodFrom()).append("' ");
		sb.append("            AND  GPT1.PURCHASE_DT <= '").append(dto.getPeriodTo()).append("') ");
		sb.append("          GROUP BY GPT1.STUDENT_ID ");
		sb.append("       ) GPTP ");
		sb.append(" ON SM.STUDENT_ID = GPTP.STUDENT_ID ");
		sb.append("     WHERE 1=1 ");

		// 顧客区分 は 全ての場合
		if (StringUtils.equals(dto.getCostomerKbn(), NaikaraTalkConstants.CHOICE_ALL_ZERO)) {
			sb.append("     AND SM.CUSTOMER_KBN = '").append(NaikaraTalkConstants.CUSTOMER_KBN_PERSON)
			.append("'  OR SM.CUSTOMER_KBN = '").append(NaikaraTalkConstants.CUSTOMER_KBN_ORGANIZATION).append("' ");
		}
		// 顧客区分 は 法人の場合
		if (StringUtils.equals(dto.getCostomerKbn(), NaikaraTalkConstants.CUSTOMER_KBN_ORGANIZATION)) {
			sb.append("     AND SM.CUSTOMER_KBN = '").append(NaikaraTalkConstants.CUSTOMER_KBN_ORGANIZATION).append("' ");
		}

		// 顧客区分 は 個人の場合
		if (StringUtils.equals(dto.getCostomerKbn(), NaikaraTalkConstants.CUSTOMER_KBN_PERSON)) {
			sb.append("     AND SM.CUSTOMER_KBN = '").append(NaikaraTalkConstants.CUSTOMER_KBN_PERSON).append("' ");
		}

		return sb.toString();
	}

}
