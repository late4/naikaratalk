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
import com.naikara_talk.dto.SalesTrnDto;
import com.naikara_talk.dto.SalesOrganizationTrnDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DAOクラス<br>
 * <b>クラス名称　　　:</b>売上集計テーブルデータアクセスクラス<br>
 * <b>クラス概要　　　:</b>売上集計テーブルのデータを取得する<br>
 * <br>
 * <b>著作権　　　　　:</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/03 TECS 新規作成
 */
public class SalesTrnDao extends AbstractDao {

	/** 年月 条件項目 */
	public static final String COND_YYYY_MM = "YYYY_MM";
	/** 受講者ID 条件項目 */
	public static final String COND_STUDENT_ID = "STUDENT_ID";
	/** 顧客区分 条件項目 */
	public static final String COND_CUSTOMER_KBN = "CUSTOMER_KBN";
	/** 受講者名フリガナ(姓) 条件項目 */
	public static final String COND_FAMILY_KNM = "FAMILY_KNM";
	/** 受講者名フリガナ(名) 条件項目 */
	public static final String COND_FIRST_KNM = "FIRST_KNM";

	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public SalesTrnDao(Connection con) {
		this.conn = con;
	}

	/**
	 * 売上集計テーブルデータ取得<br>
	 * <br>
	 * 売上集計テーブルリストを戻り値で返却する<br>
	 * <br>
	 * @param conditional
	 * @param orderConditional
	 * @return SalesTrnDto
	 * @throws NaiException
	 */
	public List<SalesTrnDto> search(ConditionMapper conditions, OrderCondition orderConditions) throws NaiException {

		ResultSet res = null;

		// 売上集計テーブルの全項目取得
		StringBuffer sb = new StringBuffer();
		sb.append("select");
		sb.append(" YYYY_MM ");
		sb.append(",STUDENT_ID ");
		sb.append(",CUSTOMER_KBN ");
		sb.append(",ORGANIZATION_ID ");
		sb.append(",BURDEN_NUM ");
		sb.append(",STUDENT_POSITION ");
		sb.append(",POSITION_ORGANIZATION_ID ");
		sb.append(",FAMILY_KNM ");
		sb.append(",FIRST_KNM ");
		sb.append(",FAMILY_ROMAJI ");
		sb.append(",FIRST_ROMAJI ");
		sb.append(",NICK_NM ");
		sb.append(",FAMILY_FIRST_JNM ");
		sb.append(",MAIL_ADDRESS1 ");
		sb.append(",COMPENSATION_BEFORE_POINT ");
		sb.append(",COMPENSATION_PURCHASE_POINT ");
		sb.append(",COMPENSATION_USE_POINT ");
		sb.append(",GOODS_PURCHASE_YEN ");
		sb.append(",FREE_BEFORE_POINT ");
		sb.append(",FREE_PURCHASE_POINT ");
		sb.append(",FREE_USE_POINT ");
		sb.append(",BILL_NO ");
		sb.append(",BILL_STUDENT_ID ");
		sb.append(",LESSON_NUM ");
		sb.append(",LAST_USE_DT ");
		sb.append(",RECORD_VER_NO ");
		sb.append(",INSERT_DTTM ");
		sb.append(",INSERT_CD ");
		sb.append(",UPDATE_DTTM ");
		sb.append(",UPDATE_CD ");
		sb.append("from ");
		sb.append("SALES_TRN ");

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

			ArrayList<SalesTrnDto> list = new ArrayList<SalesTrnDto>();

			while (res.next()){

				SalesTrnDto retDto = new SalesTrnDto();

				retDto.setYyyyMm(res.getString("YYYY_MM"));
				retDto.setStudentId(res.getString("STUDENT_ID"));
				retDto.setCustomerKbn(res.getString("CUSTOMER_KBN"));
				retDto.setOrganizationId(res.getString("ORGANIZATION_ID"));
				retDto.setBurdenNum(res.getInt("BURDEN_NUM"));
				retDto.setStudentPosition(res.getString("STUDENT_POSITION"));
				retDto.setPositionOrganizationId(res.getString("POSITION_ORGANIZATION_ID"));
				retDto.setFamilyKnm(res.getString("FAMILY_KNM"));
				retDto.setFirstKnm(res.getString("FIRST_KNM"));
				retDto.setFamilyRomaji(res.getString("FAMILY_ROMAJI"));
				retDto.setFirstRomaji(res.getString("FIRST_ROMAJI"));
				retDto.setNickNm(res.getString("NICK_NM"));
				retDto.setFamilyFirstJnm(res.getString("FAMILY_FIRST_JNM"));
				retDto.setMailAddress1(res.getString("MAIL_ADDRESS1"));
				retDto.setCompensationBeforePoint(res.getBigDecimal("COMPENSATION_BEFORE_POINT"));
				retDto.setCompensationPurchasePoint(res.getBigDecimal("COMPENSATION_PURCHASE_POINT"));
				retDto.setCompensationUsePoint(res.getBigDecimal("COMPENSATION_USE_POINT"));
				retDto.setGoodsPurchaseYen(res.getBigDecimal("GOODS_PURCHASE_YEN"));
				retDto.setFreeBeforePoint(res.getBigDecimal("FREE_BEFORE_POINT"));
				retDto.setFreePurchasePoint(res.getBigDecimal("FREE_PURCHASE_POINT"));
				retDto.setFreeUsePoint(res.getBigDecimal("FREE_USE_POINT"));
				retDto.setBillNo(res.getString("BILL_NO"));
				retDto.setBillStudentId(res.getString("BILL_STUDENT_ID"));
				retDto.setLessonNum(res.getInt("LESSON_NUM"));
				retDto.setLastUseDt(res.getString("LAST_USE_DT"));
				retDto.setRecordVerNo(res.getInt("RECORD_VER_NO"));
				retDto.setInsertDttm(res.getTimestamp("INSERT_DTTM"));
				retDto.setInsertCd(res.getString("INSERT_CD"));
				retDto.setUpdateDttm(res.getTimestamp("UPDATE_DTTM"));
				retDto.setUpdateCd(res.getString("UPDATE_CD"));

				retDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);

				list.add(retDto);
			}

			if(list.size()<1){
				SalesTrnDto retDto = new SalesTrnDto();
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
				throw new NaiException(e);
			}
		}
	}

	/**
	 * 売上集計テーブル法人データ取得<br>
	 * <br>
	 * 売上集計テーブル法人リストを戻り値で返却する<br>
	 * <br>
	 * @param strProcDate:処理日
	 * @param strProcMonth:処理月
	 * @param strCustomerKbn:顧客区分
	 * @return SalesOrganizationTrnDto
	 * @throws NaiException
	 */
	public List<SalesOrganizationTrnDto> searchSummary(String strProcMonth, String strProcDateEnd, String strCustomerKbn)
			throws NaiException {

		ResultSet res = null;

		// 売上集計テーブルの全項目取得
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT ST.YYYY_MM YYYY_MM ");
		sb.append(",ST.ORGANIZATION_ID ORGANIZATION_ID ");
		sb.append(",OM.ORGANIZATION_JNM ORGANIZATION_JNM ");
		sb.append(",SUM(ST.COMPENSATION_BEFORE_POINT) COMPENSATION_BEFORE_POINT ");
		sb.append(",SUM(ST.COMPENSATION_PURCHASE_POINT) COMPENSATION_PURCHASE_POINT ");
		sb.append(",SUM(ST.COMPENSATION_USE_POINT) COMPENSATION_USE_POINT ");
		sb.append(",SUM(ST.GOODS_PURCHASE_YEN) GOODS_PURCHASE_YEN ");
		sb.append(",SUM(ST.FREE_BEFORE_POINT) FREE_BEFORE_POINT ");
		sb.append(",SUM(ST.FREE_PURCHASE_POINT) FREE_PURCHASE_POINT ");
		sb.append(",SUM(ST.FREE_USE_POINT) FREE_USE_POINT ");
		sb.append("FROM SALES_TRN ST ");
		sb.append("INNER JOIN ORGANIZATION_MST OM ");
		sb.append("ON ST.ORGANIZATION_ID = OM.ORGANIZATION_ID ");
		sb.append("AND OM.CONTRACT_STR_DT <= ? ");
		sb.append("AND OM.CONTRACT_END_DT >= ? ");
		sb.append("WHERE ST.YYYY_MM = ? ");
		sb.append("AND ST.CUSTOMER_KBN = ? ");
		sb.append("GROUP BY ST.YYYY_MM, ST.ORGANIZATION_ID ");

		try{

			PreparedStatement ps = conn.prepareStatement(sb.toString());

       		ps.setString(1, strProcDateEnd);
       		ps.setString(2, strProcDateEnd);
       		ps.setString(3, strProcMonth);
       		ps.setString(4, strCustomerKbn);

			res = ps.executeQuery();

			ArrayList<SalesOrganizationTrnDto> list = new ArrayList<SalesOrganizationTrnDto>();

			while (res.next()){

				SalesOrganizationTrnDto dto = new SalesOrganizationTrnDto();

				dto.setYyyyMm(res.getString("YYYY_MM"));
				dto.setOrganizationId(res.getString("ORGANIZATION_ID"));
				dto.setOrganizationJnm(res.getString("ORGANIZATION_JNM"));
				dto.setCompensationBeforePoint(res.getBigDecimal("COMPENSATION_BEFORE_POINT"));
				dto.setCompensationPurchasePoint(res.getBigDecimal("COMPENSATION_PURCHASE_POINT"));
				dto.setCompensationUsePoint(res.getBigDecimal("COMPENSATION_USE_POINT"));
				dto.setGoodsPurchaseYen(res.getBigDecimal("GOODS_PURCHASE_YEN"));
				dto.setFreeBeforePoint(res.getBigDecimal("FREE_BEFORE_POINT"));
				dto.setFreePurchasePoint(res.getBigDecimal("FREE_PURCHASE_POINT"));
				dto.setFreeUsePoint(res.getBigDecimal("FREE_USE_POINT"));

				dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);

				list.add(dto);
			}

			if(list.size()<1){
				SalesOrganizationTrnDto dto = new SalesOrganizationTrnDto();
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
	 * 売上集計テーブル削除処理<br>
	 * <br>
	 * 売上集計テーブルの削除を行う<br>
	 * <br>
	 * @param conditional
	 * @return delRowCount:削除件数<br>
	 * @exception NaiException
	 */
	public int delete(ConditionMapper conditions) throws NaiException {

		int delRowCount = 0;

		StringBuffer sb = new StringBuffer();

		// データ削除処理
		sb.append("DELETE ");
		sb.append("FROM SALES_TRN ");

		if(!StringUtils.isEmpty(conditions.getConditionString())) {
			sb.append("where ");
			sb.append(conditions.getConditionString());
		}

		try {

			PreparedStatement ps = conn.prepareStatement(sb.toString());

			// パラメタの設定
			int i = 0;
			for( QueryCondition cond : conditions.getConditions()){
				for(String val : cond.getValues()){
					ps.setString(i + 1, val);
					i++;
				}
			}

			delRowCount = ps.executeUpdate();
			if (delRowCount < 1) {
				delRowCount = NaikaraTalkConstants.RETURN_CD_ERR_DEL;
			}

			return delRowCount;

		} catch (SQLException se) {
			se.printStackTrace();
			throw new NaiException(se);
		}
	}

	/**
     * 売上集計テーブル登録処理<br>
     * <br>
     * 売上集計テーブルの登録を行う<br>
     * <br>
     * @param SalesTrnDto
     * @return insertRowCount
     * @throws NaiException
     */
    public int insert(SalesTrnDto dto) throws NaiException {

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        int insertRowCount = NaikaraTalkConstants.RETURN_CD_ERR_INS; // 登録データ件数

        StringBuffer sb = new StringBuffer();

        // データ登録処理
        sb.append("INSERT INTO SALES_TRN ");
        sb.append("VALUE ( ");
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

            ps.setString( 1, dto.getYyyyMm());
            ps.setString( 2, dto.getStudentId());
            ps.setString( 3, dto.getCustomerKbn());
            ps.setString( 4, dto.getOrganizationId());
            ps.setString( 5, String.valueOf(dto.getBurdenNum()));
            ps.setString( 6, dto.getStudentPosition());
            ps.setString( 7, dto.getPositionOrganizationId());
            ps.setString( 8, dto.getFamilyKnm());
            ps.setString( 9, dto.getFirstKnm());
            ps.setString(10, dto.getFamilyRomaji());
            ps.setString(11, dto.getFirstRomaji());
            ps.setString(12, dto.getNickNm());
            ps.setString(13, dto.getFamilyFirstJnm());
            ps.setString(14, dto.getMailAddress1());
            ps.setString(15, String.valueOf(dto.getCompensationBeforePoint()));
            ps.setString(16, String.valueOf(dto.getCompensationPurchasePoint()));
            ps.setString(17, String.valueOf(dto.getCompensationUsePoint()));
            ps.setString(18, String.valueOf(dto.getGoodsPurchaseYen()));
            ps.setString(19, String.valueOf(dto.getFreeBeforePoint()));
            ps.setString(20, String.valueOf(dto.getFreePurchasePoint()));
            ps.setString(21, String.valueOf(dto.getFreeUsePoint()));
            ps.setString(22, dto.getBillNo());
            ps.setString(23, dto.getBillStudentId());
            ps.setString(24, String.valueOf(dto.getLessonNum()));
            ps.setString(25, dto.getLastUseDt());
            ps.setString(26, String.valueOf(dto.getRecordVerNo()));
            ps.setString(27, String.valueOf(Timestamp.valueOf(sdf.format(cal.getTime()))));
            ps.setString(28, dto.getInsertCd());
            ps.setString(29, String.valueOf(Timestamp.valueOf(sdf.format(cal.getTime()))));
            ps.setString(30, dto.getUpdateCd());

            insertRowCount = ps.executeUpdate();

            return insertRowCount;

        } catch (SQLException se) {
            throw new NaiException(se);
        }
    }

}