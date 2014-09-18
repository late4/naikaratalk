package com.naikara_talk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dto.OrganizationUsageSituationListDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>利用状況照会<br>
 * <b>クラス名称　　　:</b>利用状況照会クラス。<br>
 * <b>クラス概要　　　:</b>利用状況照会DAO。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴　　　　:</b>2013/08/06 TECS 新規作成。
 */

public class OrganizationUsageSituationListDao extends AbstractDao {

	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public OrganizationUsageSituationListDao(Connection con) {
		this.conn = con;
	}

	/**
	 * ｢対象年月｣がシステム日付-1ヶ月以前の場合 データ件数を取得する
	 * @param dto 利用状況照会DTO
	 * @return list
	 * @throws NaiException
	 */
	public int rowCountBefore(OrganizationUsageSituationListDto dto) throws NaiException {

        int rowCount = NaikaraTalkConstants.RETURN_CD_DATA_ERR; // データ件数
        int index = 1;

		ResultSet res = null;

		StringBuffer sb = new StringBuffer();

		// データ件数の取得処理
		sb.append(" SELECT ");
		sb.append(" COUNT(*) ");
		sb.append(" FROM ");
		sb.append(" STUDENT_MST SM ");
		sb.append(" LEFT JOIN SALES_TRN ST ");
		sb.append(" ON (SM.ORGANIZATION_ID = ST.ORGANIZATION_ID) ");
		sb.append(" AND (SM.STUDENT_ID = ST.STUDENT_ID) ");
		sb.append(" WHERE SM.ORGANIZATION_ID = ? ");
		sb.append(" AND (ST.YYYY_MM = ? OR ST.YYYY_MM Is Null) ");
		sb.append(" AND (SUBSTRING(SM.USE_STR_DT,1,6) <= ? AND SUBSTRING(SM.USE_END_DT,1,6) >= ?) ");

		// 受講者所属部署が入力されている場合
		if (!StringUtils.isEmpty(dto.getStudentPosition())) {
			sb.append(" AND SM.STUDENT_POSITION LIKE '%").append(dto.getStudentPosition()).append("%'");
		}
		// 所属組織内ID Fromが入力されている場合
		if (!StringUtils.isEmpty(dto.getPositionOrganizationIdFrom())) {
			sb.append(" AND SM.POSITION_ORGANIZATION_ID >= '").append(dto.getPositionOrganizationIdFrom()).append("'");
		}
		// 所属組織内ID Toが入力されている場合
		if (!StringUtils.isEmpty(dto.getPositionOrganizationIdTo())) {
			sb.append(" AND SM.POSITION_ORGANIZATION_ID <= '").append(dto.getPositionOrganizationIdTo()).append("'");
		}
		// 受講者名(フリガナ)が入力されている場合
		if (!StringUtils.isEmpty(dto.getFamilyFirstKnm())) {
			sb.append(" AND (SM.FAMILY_KNM LIKE '").append(dto.getFamilyFirstKnm())
			.append("%' OR SM.FIRST_KNM LIKE '").append(dto.getFamilyFirstKnm()).append("%')");
		}

		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sb.toString());

			ps.setString(index++, dto.getOrganizationId());
			ps.setString(index++, dto.getObjectYyyyMm());
			ps.setString(index++, dto.getObjectYyyyMm());
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
	 * ｢対象年月｣がシステム日付の年月が当月以降の場合 データ件数を取得する
	 * @param dto 利用状況照会DTO
	 * @return list
	 * @throws NaiException
	 */
	public int rowCountNow(OrganizationUsageSituationListDto dto) throws NaiException {

		// 戻り値の初期化
		int rowCount = NaikaraTalkConstants.RETURN_CD_DATA_ERR; // データ件数
		int index = 1;

		ResultSet res = null;

		StringBuffer sb = new StringBuffer();

		// データ件数の取得処理
		sb.append(" SELECT ");
		sb.append(" COUNT(*) ");
		sb.append(" FROM ");
		sb.append(" ( ");
		sb.append(" SELECT ");
		sb.append(" SM.STUDENT_POSITION ");
		sb.append("   FROM STUDENT_MST SM ");

		// 共通のsql文を付ける
		sb.append(getSql());

		// 受講者所属部署が入力されている場合
		if (!StringUtils.isEmpty(dto.getStudentPosition())) {
			sb.append("AND SM.STUDENT_POSITION LIKE '%").append(dto.getStudentPosition()).append("%'");
		}
		// 所属組織内ID Fromが入力されている場合
		if (!StringUtils.isEmpty(dto.getPositionOrganizationIdFrom())) {
			sb.append(" AND SM.POSITION_ORGANIZATION_ID >= '").append(dto.getPositionOrganizationIdFrom()).append("'");
		}
		// 所属組織内ID Toが入力されている場合
		if (!StringUtils.isEmpty(dto.getPositionOrganizationIdTo())) {
			sb.append(" AND SM.POSITION_ORGANIZATION_ID <= '").append(dto.getPositionOrganizationIdTo()).append("'");
		}
		// 受講者名(フリガナ)が入力されている場合
		if (!StringUtils.isEmpty(dto.getFamilyFirstKnm())) {
			sb.append(" AND (SM.FAMILY_KNM LIKE '").append(dto.getFamilyFirstKnm())
			.append("%' OR SM.FIRST_KNM LIKE '").append(dto.getFamilyFirstKnm()).append("%')");
		}
		sb.append(" )  COUNT ");

		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sb.toString());

			ps.setString(index++, dto.getSysdateYyyymm01());
			ps.setString(index++, dto.getSysdateYyyymmdd());
			ps.setString(index++, dto.getSysdateYyyymm01());
			ps.setString(index++, dto.getSysdateYyyymmdd());
			ps.setString(index++, dto.getSysdateYyyymm01());
			ps.setString(index++, dto.getSysdateYyyymmdd());
			ps.setString(index++, dto.getOrganizationId());
			ps.setString(index++, dto.getObjectYyyyMm());
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
	 * ｢対象年月｣がシステム日付-1ヶ月以前の場合 データを取得する
	 * @param dto 利用状況照会DTO
	 * @return list
	 * @throws NaiException
	 */
	public  List<OrganizationUsageSituationListDto> searchBefore(OrganizationUsageSituationListDto dto) throws NaiException {

        int index = 1;

		ResultSet res = null;

		StringBuffer sb = new StringBuffer();

		// データの取得処理
		sb.append(" SELECT ");
		sb.append(" CASE WHEN ST.STUDENT_POSITION IS NULL THEN SM.STUDENT_POSITION ");
		sb.append(" ELSE ST.STUDENT_POSITION ");
		sb.append(" END STUDENT_POSITION, ");
		sb.append(" CASE WHEN ST.POSITION_ORGANIZATION_ID IS NULL THEN SM.POSITION_ORGANIZATION_ID ");
		sb.append(" ELSE ST.POSITION_ORGANIZATION_ID ");
		sb.append(" END POSITION_ORGANIZATION_ID, ");
		sb.append(" ST.FAMILY_FIRST_JNM, ");
		sb.append(" CASE WHEN ST.GOODS_PURCHASE_YEN IS NULL THEN 0 ");
		sb.append(" ELSE ST.GOODS_PURCHASE_YEN ");
		sb.append(" END PURCHASE_YEN, ");
		sb.append(" CASE WHEN ST.COMPENSATION_USE_POINT IS NULL THEN 0 ");
		sb.append(" ELSE ST.COMPENSATION_USE_POINT ");
		sb.append(" END USE_POINT, ");
		sb.append(" ST.BURDEN_NUM, ");
		sb.append(" CASE WHEN ST.LESSON_NUM IS NULL THEN 0 ");
		sb.append(" ELSE ST.LESSON_NUM ");
		sb.append(" END LESSON_NUM, ");
		sb.append(" ST.LAST_USE_DT, ");
		sb.append(" SM.STUDENT_ID, ");
		sb.append(" SM.FAMILY_KNM, ");
		sb.append(" SM.FIRST_KNM, ");
		sb.append(" SM.FAMILY_JNM, ");
		sb.append(" SM.FIRST_JNM ");
		sb.append(" FROM ");
		sb.append(" STUDENT_MST SM ");
		sb.append(" LEFT JOIN SALES_TRN ST ");
		sb.append(" ON (SM.ORGANIZATION_ID = ST.ORGANIZATION_ID) ");
		sb.append(" AND (SM.STUDENT_ID = ST.STUDENT_ID) ");
		sb.append(" WHERE SM.ORGANIZATION_ID = ? ");
		sb.append(" AND (ST.YYYY_MM = ? OR ST.YYYY_MM IS Null) ");
		sb.append(" AND (SUBSTRING(SM.USE_STR_DT,1,6) <= ? AND SUBSTRING(SM.USE_END_DT,1,6) >= ?) ");

		// 受講者所属部署が入力されている場合
		if (!StringUtils.isEmpty(dto.getStudentPosition())) {
			sb.append(" AND SM.STUDENT_POSITION LIKE '%").append(dto.getStudentPosition()).append("%'");
		}
		// 所属組織内ID Fromが入力されている場合
		if (!StringUtils.isEmpty(dto.getPositionOrganizationIdFrom())) {
			sb.append(" AND SM.POSITION_ORGANIZATION_ID >= '").append(dto.getPositionOrganizationIdFrom()).append("'");
		}
		// 所属組織内ID Toが入力されている場合
		if (!StringUtils.isEmpty(dto.getPositionOrganizationIdTo())) {
			sb.append(" AND SM.POSITION_ORGANIZATION_ID <= '").append(dto.getPositionOrganizationIdTo()).append("'");
		}
		// 受講者名(フリガナ)が入力されている場合
		if (!StringUtils.isEmpty(dto.getFamilyFirstKnm())) {
			sb.append(" AND (SM.FAMILY_KNM LIKE '").append(dto.getFamilyFirstKnm())
			.append("%' OR SM.FIRST_KNM LIKE '").append(dto.getFamilyFirstKnm()).append("%')");
		}

		sb.append(" ORDER BY POSITION_ORGANIZATION_ID,FAMILY_JNM,FIRST_JNM ");


		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sb.toString());

			ps.setString(index++, dto.getOrganizationId());
			ps.setString(index++, dto.getObjectYyyyMm());
			ps.setString(index++, dto.getObjectYyyyMm());
			ps.setString(index++, dto.getObjectYyyyMm());

            // 実行
            res = ps.executeQuery();

            ArrayList<OrganizationUsageSituationListDto> list = new ArrayList<OrganizationUsageSituationListDto>();

            while (res.next()) {
            	OrganizationUsageSituationListDto retDto = new OrganizationUsageSituationListDto();
            	retDto.setStudentPosition(res.getString("STUDENT_POSITION"));                          // 受講者所属部署
            	retDto.setPositionOrganizationId(res.getString("POSITION_ORGANIZATION_ID"));           // 所属組織内ID
            	retDto.setFamilyFirstKnm(res.getString("FAMILY_FIRST_JNM"));                           // 受講者名
            	retDto.setPurchaseYen(res.getBigDecimal("PURCHASE_YEN"));                              // ポイント外商品購入
            	retDto.setUserPoint(res.getBigDecimal("USE_POINT"));                                   // ご利用済ポイント
            	retDto.setBurdenNum(res.getInt("BURDEN_NUM"));                                         // 自己負担率
            	retDto.setLessonNum(res.getInt("LESSON_NUM"));                                         // 受講済ﾚｯｽﾝ回数
            	retDto.setLastUseDt(res.getString("LAST_USE_DT"));                                     // 最終利用日
            	retDto.setStudentId(res.getString("STUDENT_ID"));                                      // 受講者ID
            	retDto.setFamilyKnm(res.getString("FAMILY_KNM"));                                      // フリガナ(姓)
            	retDto.setFirstKnm(res.getString("FIRST_KNM"));                                        // フリガナ(名)
            	retDto.setFamilyJnm(res.getString("FAMILY_JNM"));                                      // 名前(姓)
            	retDto.setFirstJnm(res.getString("FIRST_JNM"));                                        // 名前(名)

            	retDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);

                list.add(retDto);

            }

            if (list.size() < 1) {
            	OrganizationUsageSituationListDto retDto = new OrganizationUsageSituationListDto();
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
	 * ｢対象年月｣がシステム日付の年月が当月以降の場合 データを取得する
	 * @param dto 利用状況照会DTO
	 * @return list
	 * @throws NaiException
	 */
	public List<OrganizationUsageSituationListDto> searchNow(OrganizationUsageSituationListDto dto) throws NaiException {

		int index = 1;

		ResultSet res = null;

		StringBuffer sb = new StringBuffer();

		// データの取得処理
		sb.append(" SELECT ");
		sb.append(" SM.STUDENT_POSITION, ");
		sb.append(" SM.POSITION_ORGANIZATION_ID, ");
		sb.append(" SM.FAMILY_JNM, ");
		sb.append(" SM.FIRST_JNM, ");
		sb.append(" CASE WHEN GPTP.PURCHASE_YEN IS NULL THEN 0 ");
		sb.append(" ELSE GPTP.PURCHASE_YEN ");
		sb.append(" END PURCHASE_YEN, ");
		sb.append(" CASE WHEN ");
		sb.append("   (CASE WHEN GPTO.PAYMENT_USE_POINT IS NULL THEN 0 ");
		sb.append("    ELSE GPTO.PAYMENT_USE_POINT END) + ");
		sb.append("   (CASE WHEN LES.USE_POINT_SUM IS NULL THEN 0 ");
		sb.append("    ELSE LES.USE_POINT_SUM END) ");
		sb.append(" IS NULL THEN 0 ");
		sb.append(" ELSE ");
		sb.append("   (CASE WHEN GPTO.PAYMENT_USE_POINT IS NULL THEN 0 ");
		sb.append("    ELSE GPTO.PAYMENT_USE_POINT END) + ");
		sb.append("   (CASE WHEN LES.USE_POINT_SUM IS NULL THEN 0 ");
		sb.append("    ELSE LES.USE_POINT_SUM END) ");
		sb.append(" END USE_POINT, ");
		sb.append(" CASE WHEN SM.BURDEN_NUM IS NULL THEN 0 ");
		sb.append(" ELSE SM.BURDEN_NUM ");
		sb.append(" END BURDEN_NUM, ");
		sb.append(" SM.STUDENT_ID, ");
		sb.append(" SM.FAMILY_KNM, ");
		sb.append(" SM.FIRST_KNM, ");
		sb.append(" LES.LESSON_DT, ");
		sb.append(" GPTP.PURCHASE_DT1, ");
		sb.append(" GPTO.PURCHASE_DT2, ");
		sb.append(" LES.LESSON_NUM ");
		sb.append(" FROM STUDENT_MST SM ");

		// 共通のsql文を付ける
		sb.append(getSql());

		// 受講者所属部署が入力されている場合
		if (!StringUtils.isEmpty(dto.getStudentPosition())) {
			sb.append("AND SM.STUDENT_POSITION LIKE '%").append(dto.getStudentPosition()).append("%'");
		}
		// 所属組織内ID Fromが入力されている場合
		if (!StringUtils.isEmpty(dto.getPositionOrganizationIdFrom())) {
			sb.append(" AND SM.POSITION_ORGANIZATION_ID >= '").append(dto.getPositionOrganizationIdFrom()).append("'");
		}
		// 所属組織内ID Toが入力されている場合
		if (!StringUtils.isEmpty(dto.getPositionOrganizationIdTo())) {
			sb.append(" AND SM.POSITION_ORGANIZATION_ID <= '").append(dto.getPositionOrganizationIdTo()).append("'");
		}
		// 受講者名(フリガナ)が入力されている場合
		if (!StringUtils.isEmpty(dto.getFamilyFirstKnm())) {
			sb.append(" AND (SM.FAMILY_KNM LIKE '").append(dto.getFamilyFirstKnm())
			.append("%' OR SM.FIRST_KNM LIKE '").append(dto.getFamilyFirstKnm()).append("%')");
		}

		sb.append(" ORDER BY POSITION_ORGANIZATION_ID,FAMILY_JNM,FIRST_JNM ");

		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sb.toString());

			ps.setString(index++, dto.getSysdateYyyymm01());
			ps.setString(index++, dto.getSysdateYyyymmdd());
			ps.setString(index++, dto.getSysdateYyyymm01());
			ps.setString(index++, dto.getSysdateYyyymmdd());
			ps.setString(index++, dto.getSysdateYyyymm01());
			ps.setString(index++, dto.getSysdateYyyymmdd());
			ps.setString(index++, dto.getOrganizationId());
			ps.setString(index++, dto.getObjectYyyyMm());
			ps.setString(index++, dto.getObjectYyyyMm());

			// 実行
			res = ps.executeQuery();

			ArrayList<OrganizationUsageSituationListDto> list = new ArrayList<OrganizationUsageSituationListDto>();

            while (res.next()) {
            	OrganizationUsageSituationListDto retDto = new OrganizationUsageSituationListDto();
            	retDto.setStudentPosition(res.getString("STUDENT_POSITION"));                          // 受講者所属部署
            	retDto.setPositionOrganizationId(res.getString("POSITION_ORGANIZATION_ID"));           // 所属組織内ID
            	retDto.setPurchaseYen(res.getBigDecimal("PURCHASE_YEN"));                              // ポイント外商品購入
            	retDto.setUserPoint(res.getBigDecimal("USE_POINT"));                                   // ご利用済ポイント
            	retDto.setBurdenNum(res.getInt("BURDEN_NUM"));                                         // 自己負担率
            	retDto.setLessonNum(res.getInt("LESSON_NUM"));                                         // 受講済ﾚｯｽﾝ回数
            	retDto.setStudentId(res.getString("STUDENT_ID"));                                      // 受講者ID
            	retDto.setFamilyKnm(res.getString("FAMILY_KNM"));                                      // フリガナ(姓)
            	retDto.setFirstKnm(res.getString("FIRST_KNM"));                                        // フリガナ(名)
            	retDto.setFamilyJnm(res.getString("FAMILY_JNM"));                                      // 名前(姓)
            	retDto.setFirstJnm(res.getString("FIRST_JNM"));                                        // 名前(名)
            	retDto.setLessonDt(res.getString("LESSON_DT"));                                        // レッスン
            	retDto.setPurchaseDt1(res.getString("PURCHASE_DT1"));                                  // 購入日1
            	retDto.setPurchaseDt2(res.getString("PURCHASE_DT2"));                                  // 購入日2

            	retDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);

                list.add(retDto);

            }

            if (list.size() < 1) {
            	OrganizationUsageSituationListDto retDto = new OrganizationUsageSituationListDto();
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
	 * ｢対象年月｣がシステム日付の年月が当月以降の場合
	 * 件数検索とデータ検索共通のsql文
	 * @return str
	 */
	private String getSql() {

		StringBuffer sb = new StringBuffer();

		sb.append("     LEFT JOIN ");
		sb.append("       (SELECT LRPT.STUDENT_ID,SUM(LRPT.USE_POINT_SUM) USE_POINT_SUM ");
		sb.append("             , COUNT(LRPT.STUDENT_ID) LESSON_NUM,MAX(LRPT.LESSON_DT) LESSON_DT ");
		sb.append("          FROM LESSON_RESERVATION_PERFORMANCE_TRN LRPT ");
		sb.append("          WHERE (LRPT.LESSON_DT >= ? ");
		sb.append("            AND  LRPT.LESSON_DT <= ?) ");
		sb.append("          GROUP BY LRPT.STUDENT_ID ");
		sb.append("       ) LES ");
		sb.append("     ON SM.STUDENT_ID = LES.STUDENT_ID ");

		sb.append("     LEFT JOIN ");
		sb.append("       (SELECT GPT1.STUDENT_ID,SUM(GPT1.PURCHASE_YEN) PURCHASE_YEN,MAX(GPT1.PURCHASE_DT) PURCHASE_DT1 ");
		sb.append("          FROM GOODS_PURCHASE_TRN GPT1 ");
		sb.append("          WHERE GPT1.PAYMENT_METHOD_KBN = '1' ");
		sb.append("            AND (GPT1.PURCHASE_DT >= ? ");
		sb.append("            AND  GPT1.PURCHASE_DT <= ?) ");
		sb.append("          GROUP BY GPT1.STUDENT_ID ");
		sb.append("       ) GPTP ");
		sb.append("     ON SM.STUDENT_ID = GPTP.STUDENT_ID ");

		sb.append("     LEFT JOIN ");
		sb.append("       (SELECT GPT2.STUDENT_ID,SUM(GPT2.PAYMENT_USE_POINT) PAYMENT_USE_POINT,MAX(GPT2.PURCHASE_DT) PURCHASE_DT2 ");
		sb.append("          FROM GOODS_PURCHASE_TRN GPT2 ");
		sb.append("          WHERE GPT2.PAYMENT_METHOD_KBN = '2' ");
		sb.append("            AND (GPT2.PURCHASE_DT >= ? ");
		sb.append("            AND  GPT2.PURCHASE_DT <= ?) ");
		sb.append("          GROUP BY GPT2.STUDENT_ID ");
		sb.append("       ) GPTO ");
		sb.append("     ON SM.STUDENT_ID = GPTO.STUDENT_ID ");

		sb.append(" WHERE SM.ORGANIZATION_ID = ? ");
		sb.append(" AND (SUBSTRING(SM.USE_STR_DT,1,6) <= ? AND SUBSTRING(SM.USE_END_DT,1,6) >= ?) ");

		return sb.toString();
	}

}
