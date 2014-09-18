package com.naikara_talk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.OrganizationMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DAOクラス<br>
 * <b>クラス名称　　　:</b>組織マスタデータアクセスクラス<br>
 * <b>クラス概要　　　:</b>組織マスタのデータを取得する<br>
 * <br>
 * <b>著作権　　　　　:</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/03 TECS 新規作成
 */
public class OrganizationContractListDao extends AbstractDao {

	/** 組織ID 条件項目 */
	public static final String COND_ORGANIZATION_ID = "ORGANIZATION_ID";
	/** 連番 条件項目 */
	public static final String COND_CONS_SEQ = "CONS_SEQ";
	/** 契約開始日 条件項目 */
	public static final String COND_CONTRACT_STR_DT = "CONTRACT_STR_DT";
	/** 契約終了日 条件項目 */
	public static final String COND_CONTRACT_END_DT = "CONTRACT_END_DT";

	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public OrganizationContractListDao(Connection con) {
		this.conn = con;
	}

	/**
	 * 組織マスタデータ取得<br>
	 * <br>
	 * 組織マスタリストを戻り値で返却する<br>
	 * <br>
	 * @param sysDate
	 * @param organizationId
	 * @param orderConditional
	 * @return OrganizationMstDto
	 * @throws NaiException
	 */
	public List<OrganizationMstDto> search(String sysDate, String organizationId, OrderCondition orderConditions) throws NaiException {

		ResultSet res = null;

		// 組織マスタの全項目取得
		StringBuffer sb = new StringBuffer();
		sb.append("select");
		sb.append(" ORGANIZATION_ID ");
		sb.append(",CONS_SEQ ");
		sb.append(",PASSWORD ");
		sb.append(",ORGANIZATION_JNM ");
		sb.append(",ORGANIZATION_KNM ");
		sb.append(",ORGANIZATION_ROMAJI ");
		sb.append(",TEL ");
		sb.append(",ZIP_CD ");
		sb.append(",ADDRESS_AREA_CD ");
		sb.append(",ADDRESS_PREFECTURE_CD ");
		sb.append(",ADDRESS_CITY ");
		sb.append(",ADDRESS_OTHERS ");
		sb.append(",MANAG_FAMILY_JNM ");
		sb.append(",MANAG_FIRST_JNM ");
		sb.append(",MANAG_FAMILY_KNM ");
		sb.append(",MANAG_FIRST_KNM ");
		sb.append(",MANAG_FAMILY_ROMAJI ");
		sb.append(",MANAG_FIRST_ROMAJI ");
		sb.append(",MANAG_POSITION ");
		sb.append(",MANAG_GENDER_KBN ");
		sb.append(",MANAG_MAIL_ADDRESS1 ");
		sb.append(",MANAG_MAIL_ADDRESS2 ");
		sb.append(",MANAG_MAIL_ADDRESS3 ");
		sb.append(",CONTRACT_STR_DT ");
		sb.append(",CONTRACT_END_DT ");
		sb.append(",TEMP_POINT_NUM ");
		sb.append(",BALANCE_POINT_NUM ");
		sb.append(",REQUEST_ADDRESS_KBN ");
		sb.append(",REMARKS ");
		sb.append(",REQUEST_ORGANIZATION_JNM ");
		sb.append(",REQUEST_ORGANIZATION_KNM ");
		sb.append(",REQUEST_ORGANIZATION_RNM ");
		sb.append(",REQUEST_TEL ");
		sb.append(",REQUEST_ZIP_CD ");
		sb.append(",REQUEST_ADDRESS_AREA_CD ");
		sb.append(",REQUEST_ADDRESS_PREFECTURE_CD ");
		sb.append(",REQUEST_ADDRESS_CITY ");
		sb.append(",REQUEST_ADDRESS_OTHERS ");
		sb.append(",REQUEST_MANAG_FAMILY_JNM ");
		sb.append(",REQUEST_MANAG_FIRST_JNM ");
		sb.append(",REQUEST_MANAG_FAMILY_KNM ");
		sb.append(",REQUEST_MANAG_FIRST_KNM ");
		sb.append(",REQUEST_MANAG_FAMILY_ROMAJI ");
		sb.append(",REQUEST_MANAG_FIRST_ROMAJI ");
		sb.append(",REQUEST_MANAG_POSITION ");
		sb.append(",REQUEST_PAYMENT_SITE_KBN ");
		sb.append(",RECORD_VER_NO ");
		sb.append(",INSERT_DTTM ");
		sb.append(",INSERT_CD ");
		sb.append(",UPDATE_DTTM ");
		sb.append(",UPDATE_CD ");
		sb.append("from ");
		sb.append("ORGANIZATION_MST ");
		sb.append(" where ORGANIZATION_ID = ? ");
		sb.append(" and ( (CONTRACT_STR_DT <= ? ");
		sb.append(" and  CONTRACT_END_DT >= ?) ");
		sb.append(" or  (CONTRACT_STR_DT >= ? ");
		sb.append(" and  CONTRACT_END_DT >= ?) ) ");


		if(!StringUtils.isEmpty(orderConditions.getOrderString())) {
			sb.append(orderConditions.getOrderString());
		}

		try{

			PreparedStatement ps = conn.prepareStatement(sb.toString());
			ps.setString(1, organizationId);
			ps.setString(2, sysDate);
			ps.setString(3, sysDate);
			ps.setString(4, sysDate);
			ps.setString(5, sysDate);
			res = ps.executeQuery();


			ArrayList<OrganizationMstDto> list = new ArrayList<OrganizationMstDto>();

			while (res.next()){

				OrganizationMstDto retDto = new OrganizationMstDto();

				retDto.setOrganizationId(res.getString("ORGANIZATION_ID"));
				retDto.setConsSeq(res.getInt("CONS_SEQ"));
				retDto.setPassword(res.getString("PASSWORD"));
				retDto.setOrganizationJnm(res.getString("ORGANIZATION_JNM"));
				retDto.setOrganizationKnm(res.getString("ORGANIZATION_KNM"));
				retDto.setOrganizationRomaji(res.getString("ORGANIZATION_ROMAJI"));
				retDto.setTel(res.getString("TEL"));
				retDto.setZipCd(res.getString("ZIP_CD"));
				retDto.setAddressAreaCd(res.getString("ADDRESS_AREA_CD"));
				retDto.setAddressPrefectureCd(res.getString("ADDRESS_PREFECTURE_CD"));
				retDto.setAddressCity(res.getString("ADDRESS_CITY"));
				retDto.setAddressOthers(res.getString("ADDRESS_OTHERS"));
				retDto.setManagFamilyJnm(res.getString("MANAG_FAMILY_JNM"));
				retDto.setManagFirstJnm(res.getString("MANAG_FIRST_JNM"));
				retDto.setManagFamilyKnm(res.getString("MANAG_FAMILY_KNM"));
				retDto.setManagFirstKnm(res.getString("MANAG_FIRST_KNM"));
				retDto.setManagFamilyRomaji(res.getString("MANAG_FAMILY_ROMAJI"));
				retDto.setManagFirstRomaji(res.getString("MANAG_FIRST_ROMAJI"));
				retDto.setManagPosition(res.getString("MANAG_POSITION"));
				retDto.setManagGenderKbn(res.getString("MANAG_GENDER_KBN"));
				retDto.setManagMailAddress1(res.getString("MANAG_MAIL_ADDRESS1"));
				retDto.setManagMailAddress2(res.getString("MANAG_MAIL_ADDRESS2"));
				retDto.setManagMailAddress3(res.getString("MANAG_MAIL_ADDRESS3"));
				retDto.setContractStrDt(res.getString("CONTRACT_STR_DT"));
				retDto.setContractEndDt(res.getString("CONTRACT_END_DT"));
				retDto.setTempPointNum(res.getBigDecimal("TEMP_POINT_NUM"));
				retDto.setBalancePointNum(res.getBigDecimal("BALANCE_POINT_NUM"));
				retDto.setRequestAddressKbn(res.getString("REQUEST_ADDRESS_KBN"));
				retDto.setRemarks(res.getString("REMARKS"));
				retDto.setRequestOrganizationJnm(res.getString("REQUEST_ORGANIZATION_JNM"));
				retDto.setRequestOrganizationKnm(res.getString("REQUEST_ORGANIZATION_KNM"));
				retDto.setRequestOrganizationRnm(res.getString("REQUEST_ORGANIZATION_RNM"));
				retDto.setRequestTel(res.getString("REQUEST_TEL"));
				retDto.setRequestZipCd(res.getString("REQUEST_ZIP_CD"));
				retDto.setRequestAddressAreaCd(res.getString("REQUEST_ADDRESS_AREA_CD"));
				retDto.setRequestAddressPrefectureCd(res.getString("REQUEST_ADDRESS_PREFECTURE_CD"));
				retDto.setRequestAddressCity(res.getString("REQUEST_ADDRESS_CITY"));
				retDto.setRequestAddressOthers(res.getString("REQUEST_ADDRESS_OTHERS"));
				retDto.setRequestManagFamilyJnm(res.getString("REQUEST_MANAG_FAMILY_JNM"));
				retDto.setRequestManagFirstJnm(res.getString("REQUEST_MANAG_FIRST_JNM"));
				retDto.setRequestManagFamilyKnm(res.getString("REQUEST_MANAG_FAMILY_KNM"));
				retDto.setRequestManagFirstKnm(res.getString("REQUEST_MANAG_FIRST_KNM"));
				retDto.setRequestManagFamilyRomaji(res.getString("REQUEST_MANAG_FAMILY_ROMAJI"));
				retDto.setRequestManagFirstRomaji(res.getString("REQUEST_MANAG_FIRST_ROMAJI"));
				retDto.setRequestManagPosition(res.getString("REQUEST_MANAG_POSITION"));
				retDto.setRequestPaymentSiteKbn(res.getString("REQUEST_PAYMENT_SITE_KBN"));
				retDto.setRecordVerNo(res.getInt("RECORD_VER_NO"));
				retDto.setInsertDttm(res.getTimestamp("INSERT_DTTM"));
				retDto.setInsertCd(res.getString("INSERT_CD"));
				retDto.setUpdateDttm(res.getTimestamp("UPDATE_DTTM"));
				retDto.setUpdateCd(res.getString("UPDATE_CD"));

				retDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);

				list.add(retDto);
			}

			if(list.size()<1){
				OrganizationMstDto retDto = new OrganizationMstDto();
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

}
