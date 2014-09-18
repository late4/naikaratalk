package com.naikara_talk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dbutil.QueryCondition;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.OrganizationMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;
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
public class OrganizationMstDao extends AbstractDao {

    /** 組織ID 条件項目 */
    public static final String COND_ORGANIZATION_ID = "ORGANIZATION_ID";
    /** 連番 条件項目 */
    public static final String COND_CONS_SEQ = "CONS_SEQ";
    /** 組織名 条件項目 */
    public static final String COND_ORGANIZATION_JNM = "ORGANIZATION_JNM";
    /** 責任者名フリガナ(姓) 条件項目 */
    public static final String COND_MANAG_FAMILY_KNM = "MANAG_FAMILY_KNM";
    /** 責任者名フリガナ(名) 条件項目 */
    public static final String COND_MANAG_FIRST_KNM = "MANAG_FIRST_KNM";
    /** 契約開始日 条件項目 */
    public static final String COND_CONTRACT_STR_DT = "CONTRACT_STR_DT";
    /** 契約終了日 条件項目 */
    public static final String COND_CONTRACT_END_DT = "CONTRACT_END_DT";
    /** 組織名(フリガナ) 条件項目 */
    public static final String COND_ORGANIZATION_KNM = "ORGANIZATION_KNM";

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public OrganizationMstDao(Connection con) {
        this.conn = con;
    }

    /**
    * 組織マスタデータ取得<br>
    * <br>
    * 組織マスタリストを戻り値で返却する<br>
    * <br>
    * @param conditional
    * @param orderConditional
    * @return OrganizationMstDto
    * @throws NaiException
    */
    public List<OrganizationMstDto> search(ConditionMapper conditions, OrderCondition orderConditions)
            throws NaiException {

        ResultSet res = null;

        StringBuffer sb = new StringBuffer();

        // 組織マスタの全項目取得
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

        if (!StringUtils.isEmpty(conditions.getConditionString())) {
            sb.append("where ");
            sb.append(conditions.getConditionString());
        }
        if (!StringUtils.isEmpty(orderConditions.getOrderString())) {
            sb.append(orderConditions.getOrderString());
        }

        try {

            PreparedStatement ps = conn.prepareStatement(sb.toString());

            // パラメタの設定
            int i = 0;
            for (QueryCondition cond : conditions.getConditions()) {
                for (String val : cond.getValues()) {
                    ps.setString(i + 1, val);
                    i++;
                }
            }

            res = ps.executeQuery();

            ArrayList<OrganizationMstDto> list = new ArrayList<OrganizationMstDto>();

            while (res.next()) {

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

            if (list.size() < 1) {
                OrganizationMstDto retDto = new OrganizationMstDto();
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
            } catch (SQLException e) {
                e.printStackTrace();
                throw new NaiException(e);
            }
        }
    }

    /**
     * 組織マスタデータ取得<br>
     * <br>
     * 組織マスタリストを戻り値で返却する<br>
     * <br>
     * @param conditional
     * @param orderConditional
     * @return OrganizationMstDto
     * @throws NaiException
     */
    public List<OrganizationMstDto> searchWithOr(ConditionMapper conditions, OrderCondition orderConditions,
            OrganizationMstDto dto) throws NaiException {

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

        if (!StringUtils.isEmpty(conditions.getConditionString())) {
            sb.append("where ");
            sb.append(conditions.getConditionString());

            // 組織責任者名(フリガナ)が入力されている場合
            if (!StringUtils.isEmpty(dto.getManagKnm())) {

                sb.append(" and (MANAG_FAMILY_KNM like ? or MANAG_FIRST_KNM like ?) ");
            }
        }
        if (!StringUtils.isEmpty(orderConditions.getOrderString())) {
            sb.append(orderConditions.getOrderString());
        }

        try {

            PreparedStatement ps = conn.prepareStatement(sb.toString());

            // パラメタの設定
            int i = 0;
            for (QueryCondition cond : conditions.getConditions()) {
                for (String val : cond.getValues()) {
                    ps.setString(i + 1, val);
                    i++;
                }
            }
            // 組織責任者名(フリガナ)が入力されている場合
            if (!StringUtils.isEmpty(dto.getManagKnm())) {

                ps.setString(i + 1,
                        new StringBuffer().append(dto.getManagKnm()).append(NaikaraTalkConstants.OPERATOR_PERCENT)
                                .toString());
                i++;
                ps.setString(i + 1,
                        new StringBuffer().append(dto.getManagKnm()).append(NaikaraTalkConstants.OPERATOR_PERCENT)
                                .toString());
            }

            res = ps.executeQuery();

            ArrayList<OrganizationMstDto> list = new ArrayList<OrganizationMstDto>();

            while (res.next()) {

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

            if (list.size() < 1) {
                OrganizationMstDto retDto = new OrganizationMstDto();
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

            } catch (SQLException se) {
                throw new NaiException(se);
            }
        }
    }

    /**
     * 組織マスタデータ取得<br>
     * <br>
     * 組織マスタリストを戻り値で返却する<br>
     * <br>
     * @param conditional
     * @param orderConditional
     * @return OrganizationMstDto
     * @throws NaiException
     */
    public List<OrganizationMstDto> searchWithNm(ConditionMapper conditions, OrderCondition orderConditions)
            throws NaiException {

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

        if (!StringUtils.isEmpty(conditions.getConditionString())) {
            sb.append("where ");
            sb.append(conditions.getConditionString());
        }
        if (!StringUtils.isEmpty(orderConditions.getOrderString())) {
            sb.append(orderConditions.getOrderString());
        }

        try {

            PreparedStatement ps = conn.prepareStatement(sb.toString());

            // パラメタの設定
            int i = 0;
            for (QueryCondition cond : conditions.getConditions()) {
                for (String val : cond.getValues()) {
                    ps.setString(i + 1, val);
                    i++;
                }
            }

            res = ps.executeQuery();

            // コード管理マスタのキャッシュ読み込み
            CodeManagMstCache cache = CodeManagMstCache.getInstance();
            LinkedHashMap<String, CodeManagMstDto> managGenderKbnlist = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_GENDER);

            ArrayList<OrganizationMstDto> list = new ArrayList<OrganizationMstDto>();

            while (res.next()) {

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
                if (StringUtils.isEmpty(res.getString("MANAG_GENDER_KBN"))) {
                    retDto.setManagGenderKbnNm("");// 性別区分名
                } else {
                    retDto.setManagGenderKbnNm(managGenderKbnlist.get(res.getString("MANAG_GENDER_KBN")).getManagerNm());// 性別区分名
                }
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

            if (list.size() < 1) {
                OrganizationMstDto retDto = new OrganizationMstDto();
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
            } catch (SQLException e) {
                e.printStackTrace();
                throw new NaiException(e);
            }
        }
    }

    /**
     * 組織マスタ検索処理<br>
     * <br>
     * 組織マスタ検索処理<br>
     * <br>
     * @param ConditionMapper
     * @param OrderCondition
     * @return OrganizationMstDto
     * @throws NaiException
     */
    public ArrayList<OrganizationMstDto> searchOrganizationMst(ConditionMapper conditions, OrderCondition OrderBy)
            throws NaiException {

        // 戻り値とリターンコードの初期化
        ArrayList<OrganizationMstDto> list = null; // DTOリスト

        // データソース取得
        ResultSet res = null;

        // 組織マスタの契約開始日 契約終了日取得
        StringBuffer sb = new StringBuffer();
        sb.append(" select ");
        sb.append("  min(CONTRACT_STR_DT) as MIN_CONTRACT_STR_DT ");// 契約開始日
        sb.append(" ,max(CONTRACT_END_DT) as MAX_CONTRACT_END_DT ");// 契約終了日
        sb.append(" from ");
        sb.append(" ORGANIZATION_MST ");
        if (!StringUtils.isEmpty(conditions.getConditionString())) {
            sb.append("where ");
            sb.append(conditions.getConditionString());
        }
        if (!StringUtils.isEmpty(OrderBy.getOrderString())) {
            sb.append(OrderBy.getOrderString());
        }

        try {

            PreparedStatement ps = conn.prepareStatement(sb.toString());
            // パラメタの設定
            int i = 0;
            for (QueryCondition cond : conditions.getConditions()) {
                for (String val : cond.getValues()) {
                    ps.setString(i + 1, val);
                    i++;
                }
            }
            // SQL文を実行
            res = ps.executeQuery();

            // 戻りdto作成
            list = new ArrayList<OrganizationMstDto>();
            while (res.next()) {
                OrganizationMstDto retDto = new OrganizationMstDto();
                retDto.setContractStrDt(res.getString("MIN_CONTRACT_STR_DT")); // 契約開始日
                retDto.setContractEndDt(res.getString("MAX_CONTRACT_END_DT")); // 契約終了日
                list.add(retDto);
            }

            if (list.size() < 1) {
                OrganizationMstDto retDto = new OrganizationMstDto();
                retDto.setReturnCode(2);
                list.add(retDto);
            }

            ps.close();

            // 戻りdto
            return list;

        } catch (SQLException se) {
            throw new NaiException(se);
        } finally {
            try {
                if (res != null) {
                    res.close();
                }

            } catch (SQLException se) {
                throw new NaiException(se);
            }
        }
    }

    /**
     * データ件数取得<br>
     * <br>
     * データ件数を戻り値で返却する<br>
     * <br>
     * @param tableName テーブル名称<br>
     * @param conditions 検索条件<br>
     * @param dto 判定条件<br>
     * @return rowCount データ件数<br>
     * @exception NaiException
     */
    public int rowCount(String tableName, ConditionMapper conditions, OrganizationMstDto dto) throws NaiException {

        // 戻り値の初期化
        int rowCount = NaikaraTalkConstants.RETURN_CD_DATA_ERR; // データ件数

        // 引数のパラメータチェック
        if (tableName == null || "".equals(tableName)) {
            return rowCount;
        }

        ResultSet res = null;

        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT ");
        sb.append("   COUNT(*)  ");
        sb.append(" FROM ");
        sb.append(" ").append(tableName).append(" ");
        if (!StringUtils.isEmpty(conditions.getConditionString())) {
            sb.append("where ");
            sb.append(conditions.getConditionString());

            // 組織責任者名(フリガナ)が入力されている場合
            if (!StringUtils.isEmpty(dto.getManagKnm())) {

                sb.append(" and (MANAG_FAMILY_KNM like ? or MANAG_FIRST_KNM like ?) ");
            }
        }

        try {
            PreparedStatement ps = conn.prepareStatement(sb.toString());

            // パラメタの設定
            int i = 0;
            for (QueryCondition cond : conditions.getConditions()) {
                for (String val : cond.getValues()) {
                    ps.setString(i + 1, val);
                    i++;
                }
            }

            // 組織責任者名(フリガナ)が入力されている場合
            if (!StringUtils.isEmpty(dto.getManagKnm())) {

                ps.setString(i + 1,
                        new StringBuffer().append(dto.getManagKnm()).append(NaikaraTalkConstants.OPERATOR_PERCENT)
                                .toString());
                i++;
                ps.setString(i + 1,
                        new StringBuffer().append(dto.getManagKnm()).append(NaikaraTalkConstants.OPERATOR_PERCENT)
                                .toString());
            }

            // 実行
            res = ps.executeQuery();

            rowCount = NaikaraTalkConstants.RETURN_CD_DATA_ERR; // データ件数
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
            } catch (SQLException se) {
                throw new NaiException(se);
            }
        }
    }

    /**
     * 組織マスタ登録処理<br>
     * <br>
     * 組織マスタの登録を行う<br>
     * <br>
     * @param dto 登録データ
     * @return insertRowCount 登録件数
     * @exception NaiException
     */
    public int insert(OrganizationMstDto dto) throws NaiException {

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        int insertRowCount = NaikaraTalkConstants.RETURN_CD_ERR_INS; // 登録データ件数

        // 組織マスタのデータチェック
        if (!checkDto(dto)) {
            return insertRowCount;
        }

        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ");
        sb.append("ORGANIZATION_MST ");
        sb.append(" ( ");
        sb.append("ORGANIZATION_ID ");
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
        sb.append(" ) VALUE ( ");
        sb.append(" ? ");
        sb.append(", ? ");
        sb.append(", ? ");
        sb.append(", ? ");
        sb.append(", ? ");
        sb.append(", ? ");
        sb.append(", ? ");
        sb.append(", ? ");
        sb.append(", ? ");
        sb.append(", ? ");
        sb.append(", ? ");
        sb.append(", ? ");
        sb.append(", ? ");
        sb.append(", ? ");
        sb.append(", ? ");
        sb.append(", ? ");
        sb.append(", ? ");
        sb.append(", ? ");
        sb.append(", ? ");
        sb.append(", ? ");
        sb.append(", ? ");
        sb.append(", ? ");
        sb.append(", ? ");
        sb.append(", ? ");
        sb.append(", ? ");
        sb.append(", ? ");
        sb.append(", ? ");
        sb.append(", ? ");
        sb.append(", ? ");
        sb.append(", ? ");
        sb.append(", ? ");
        sb.append(", ? ");
        sb.append(", ? ");
        sb.append(", ? ");
        sb.append(", ? ");
        sb.append(", ? ");
        sb.append(", ? ");
        sb.append(", ? ");
        sb.append(", ? ");
        sb.append(", ? ");
        sb.append(", ? ");
        sb.append(", ? ");
        sb.append(", ? ");
        sb.append(", ? ");
        sb.append(", ? ");
        sb.append(", ? ");
        sb.append(", ? ");
        sb.append(", ? ");
        sb.append(", ? ");
        sb.append(", ? ");
        sb.append(", ? ");
        sb.append(" ) ");

        try {

            PreparedStatement ps = conn.prepareStatement(sb.toString());

            ps.setString(1, dto.getOrganizationId());
            ps.setInt(2, dto.getConsSeq());
            ps.setString(3, dto.getPassword());
            ps.setString(4, dto.getOrganizationJnm());
            ps.setString(5, dto.getOrganizationKnm());
            ps.setString(6, dto.getOrganizationRomaji());
            ps.setString(7, dto.getTel());
            ps.setString(8, dto.getZipCd());
            ps.setString(9, dto.getAddressAreaCd());
            ps.setString(10, dto.getAddressPrefectureCd());
            ps.setString(11, dto.getAddressCity());
            ps.setString(12, dto.getAddressOthers());
            ps.setString(13, dto.getManagFamilyJnm());
            ps.setString(14, dto.getManagFirstJnm());
            ps.setString(15, dto.getManagFamilyKnm());
            ps.setString(16, dto.getManagFirstKnm());
            ps.setString(17, dto.getManagFamilyRomaji());
            ps.setString(18, dto.getManagFirstRomaji());
            ps.setString(19, dto.getManagPosition());
            ps.setString(20, dto.getManagGenderKbn());
            ps.setString(21, dto.getManagMailAddress1());
            ps.setString(22, dto.getManagMailAddress2());
            ps.setString(23, dto.getManagMailAddress3());
            ps.setString(24, dto.getContractStrDt());
            ps.setString(25, dto.getContractEndDt());
            ps.setBigDecimal(26, dto.getTempPointNum());
            ps.setBigDecimal(27, dto.getBalancePointNum());
            ps.setString(28, dto.getRequestAddressKbn());
            ps.setString(29, dto.getRemarks());
            ps.setString(30, dto.getRequestOrganizationJnm());
            ps.setString(31, dto.getRequestOrganizationKnm());
            ps.setString(32, dto.getRequestOrganizationRnm());
            ps.setString(33, dto.getRequestTel());
            ps.setString(34, dto.getRequestZipCd());
            ps.setString(35, dto.getRequestAddressAreaCd());
            ps.setString(36, dto.getRequestAddressPrefectureCd());
            ps.setString(37, dto.getRequestAddressCity());
            ps.setString(38, dto.getRequestAddressOthers());
            ps.setString(39, dto.getRequestManagFamilyJnm());
            ps.setString(40, dto.getRequestManagFirstJnm());
            ps.setString(41, dto.getRequestManagFamilyKnm());
            ps.setString(42, dto.getRequestManagFirstKnm());
            ps.setString(43, dto.getRequestManagFamilyRomaji());
            ps.setString(44, dto.getRequestManagFirstRomaji());
            ps.setString(45, dto.getRequestManagPosition());
            ps.setString(46, dto.getRequestPaymentSiteKbn());
            ps.setString(47, String.valueOf(dto.getRecordVerNo()));
            ps.setString(48, String.valueOf(Timestamp.valueOf(sdf.format(cal.getTime()))));
            ps.setString(49, dto.getInsertCd());
            ps.setString(50, String.valueOf(Timestamp.valueOf(sdf.format(cal.getTime()))));
            ps.setString(51, dto.getUpdateCd());

            insertRowCount = ps.executeUpdate();

            return insertRowCount;
        } catch (SQLException se) {
            throw new NaiException(se);
        }
    }

    /**
     * 組織マスタ更新<br>
     * <br>
     * 組織マスタの更新を行う<br>
     * <br>
     * @param dto 更新データ<br>
     * @return updatedRowCount 更新件数<br>
     * @exception NaiException
     */
    public int update(OrganizationMstDto dto) throws NaiException {

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        int updatedRowCount = NaikaraTalkConstants.RETURN_CD_ERR_UPD; // 更新データ件数

        // 組織マスタのデータチェック
        if (!checkDto(dto)) {
            return updatedRowCount;
        }

        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ");
        sb.append("ORGANIZATION_MST ");
        sb.append("SET ");
        sb.append("PASSWORD = ? ");
        sb.append(",ORGANIZATION_JNM = ? ");
        sb.append(",ORGANIZATION_KNM = ? ");
        sb.append(",ORGANIZATION_ROMAJI = ? ");
        sb.append(",TEL = ? ");
        sb.append(",ZIP_CD = ? ");
        sb.append(",ADDRESS_AREA_CD = ? ");
        sb.append(",ADDRESS_PREFECTURE_CD = ? ");
        sb.append(",ADDRESS_CITY = ? ");
        sb.append(",ADDRESS_OTHERS = ? ");
        sb.append(",MANAG_FAMILY_JNM = ? ");
        sb.append(",MANAG_FIRST_JNM = ? ");
        sb.append(",MANAG_FAMILY_KNM = ? ");
        sb.append(",MANAG_FIRST_KNM = ? ");
        sb.append(",MANAG_FAMILY_ROMAJI = ? ");
        sb.append(",MANAG_FIRST_ROMAJI = ? ");
        sb.append(",MANAG_POSITION = ? ");
        sb.append(",MANAG_GENDER_KBN = ? ");
        sb.append(",MANAG_MAIL_ADDRESS1 = ? ");
        sb.append(",MANAG_MAIL_ADDRESS2 = ? ");
        sb.append(",MANAG_MAIL_ADDRESS3 = ? ");
        sb.append(",CONTRACT_STR_DT = ? ");
        sb.append(",CONTRACT_END_DT = ? ");
        sb.append(",TEMP_POINT_NUM = ? ");
        sb.append(",BALANCE_POINT_NUM = ? ");
        sb.append(",REQUEST_ADDRESS_KBN = ? ");
        sb.append(",REMARKS = ? ");
        sb.append(",REQUEST_ORGANIZATION_JNM = ? ");
        sb.append(",REQUEST_ORGANIZATION_KNM = ? ");
        sb.append(",REQUEST_ORGANIZATION_RNM = ? ");
        sb.append(",REQUEST_TEL = ? ");
        sb.append(",REQUEST_ZIP_CD = ? ");
        sb.append(",REQUEST_ADDRESS_AREA_CD = ? ");
        sb.append(",REQUEST_ADDRESS_PREFECTURE_CD = ? ");
        sb.append(",REQUEST_ADDRESS_CITY = ? ");
        sb.append(",REQUEST_ADDRESS_OTHERS = ? ");
        sb.append(",REQUEST_MANAG_FAMILY_JNM = ? ");
        sb.append(",REQUEST_MANAG_FIRST_JNM = ? ");
        sb.append(",REQUEST_MANAG_FAMILY_KNM = ? ");
        sb.append(",REQUEST_MANAG_FIRST_KNM = ? ");
        sb.append(",REQUEST_MANAG_FAMILY_ROMAJI = ? ");
        sb.append(",REQUEST_MANAG_FIRST_ROMAJI = ? ");
        sb.append(",REQUEST_MANAG_POSITION = ? ");
        sb.append(",REQUEST_PAYMENT_SITE_KBN = ? ");
        sb.append(",RECORD_VER_NO = ? ");
        sb.append(",UPDATE_DTTM = ? ");
        sb.append(",UPDATE_CD = ? ");
        sb.append("WHERE ");
        sb.append("ORGANIZATION_ID = ? ");
        sb.append("AND ");
        sb.append("CONS_SEQ = ? ");
        sb.append("AND ");
        sb.append("RECORD_VER_NO = ? ");

        try {
            PreparedStatement ps = conn.prepareStatement(sb.toString());

            ps.setString(1, dto.getPassword());
            ps.setString(2, dto.getOrganizationJnm());
            ps.setString(3, dto.getOrganizationKnm());
            ps.setString(4, dto.getOrganizationRomaji());
            ps.setString(5, dto.getTel());
            ps.setString(6, dto.getZipCd());
            ps.setString(7, dto.getAddressAreaCd());
            ps.setString(8, dto.getAddressPrefectureCd());
            ps.setString(9, dto.getAddressCity());
            ps.setString(10, dto.getAddressOthers());
            ps.setString(11, dto.getManagFamilyJnm());
            ps.setString(12, dto.getManagFirstJnm());
            ps.setString(13, dto.getManagFamilyKnm());
            ps.setString(14, dto.getManagFirstKnm());
            ps.setString(15, dto.getManagFamilyRomaji());
            ps.setString(16, dto.getManagFirstRomaji());
            ps.setString(17, dto.getManagPosition());
            ps.setString(18, dto.getManagGenderKbn());
            ps.setString(19, dto.getManagMailAddress1());
            ps.setString(20, dto.getManagMailAddress2());
            ps.setString(21, dto.getManagMailAddress3());
            ps.setString(22, dto.getContractStrDt());
            ps.setString(23, dto.getContractEndDt());
            ps.setBigDecimal(24, dto.getTempPointNum());
            ps.setBigDecimal(25, dto.getBalancePointNum());
            ps.setString(26, dto.getRequestAddressKbn());
            ps.setString(27, dto.getRemarks());
            ps.setString(28, dto.getRequestOrganizationJnm());
            ps.setString(29, dto.getRequestOrganizationKnm());
            ps.setString(30, dto.getRequestOrganizationRnm());
            ps.setString(31, dto.getRequestTel());
            ps.setString(32, dto.getRequestZipCd());
            ps.setString(33, dto.getRequestAddressAreaCd());
            ps.setString(34, dto.getRequestAddressPrefectureCd());
            ps.setString(35, dto.getRequestAddressCity());
            ps.setString(36, dto.getRequestAddressOthers());
            ps.setString(37, dto.getRequestManagFamilyJnm());
            ps.setString(38, dto.getRequestManagFirstJnm());
            ps.setString(39, dto.getRequestManagFamilyKnm());
            ps.setString(40, dto.getRequestManagFirstKnm());
            ps.setString(41, dto.getRequestManagFamilyRomaji());
            ps.setString(42, dto.getRequestManagFirstRomaji());
            ps.setString(43, dto.getRequestManagPosition());
            ps.setString(44, dto.getRequestPaymentSiteKbn());
            ps.setString(45, String.valueOf(dto.getRecordVerNo() + 1));
            ps.setString(46, String.valueOf(Timestamp.valueOf(sdf.format(cal.getTime()))));
            ps.setString(47, dto.getUpdateCd());
            ps.setString(48, dto.getOrganizationId());
            ps.setInt(49, dto.getConsSeq());
            ps.setInt(50, dto.getRecordVerNo());

            updatedRowCount = ps.executeUpdate();

            return updatedRowCount;
        } catch (SQLException se) {
            throw new NaiException(se);
        }
    }

    /**
     * 削除処理。<br>
     * <br>
     * @param OrganizationMstDto 画面のパラメータ
     * @return int 削除レコード数
     * @throws NaiException
     */
    public int delete(OrganizationMstDto dto) throws NaiException {

        // 削除データ件数
        int deleteRowCount = NaikaraTalkConstants.RETURN_CD_ERR_DEL; // 更新データ件数

        // SQL文作成
        StringBuffer sb = new StringBuffer();
        sb.append(" DELETE FROM ORGANIZATION_MST");
        sb.append(" WHERE ");
        sb.append(" ORGANIZATION_ID = ? ");
        sb.append(" AND CONS_SEQ = ? ");
        try {

            PreparedStatement ps = conn.prepareStatement(sb.toString());

            int index = 1;
            // 組織ID
            ps.setString(index++, dto.getOrganizationId());

            // 連番
            ps.setInt(index++, dto.getConsSeq());

            deleteRowCount = ps.executeUpdate();

            return deleteRowCount;
        } catch (SQLException se) {
            throw new NaiException(se);
        }
    }

    /**
     * 利用者マスタ更新データチェック<br>
     * <br>
     * 利用者マスタの更新データをチェックし<br>
     * エラー有の場合は、falseを返す<br>
     * エラー無の場合は、trueを返す<br>
     * <br>
     * @param dto チェックデータ
     * @return boolean チェック結果
     * @exception NaiException
     */
    private boolean checkDto(OrganizationMstDto dto) throws NaiException {

        // 組織IDのチェック
        if (dto.getOrganizationId() == null || "".equals(dto.getOrganizationId())) {
            return false;
        }
        // 連番のチェック
        if (dto.getConsSeq() == 0 || "".equals(dto.getConsSeq())) {
            return false;
        }
        // パスワードのチェック
        if (dto.getPassword() == null || "".equals(dto.getPassword())) {
            return false;
        }
        // 組織名のチェック
        if (dto.getOrganizationJnm() == null || "".equals(dto.getOrganizationJnm())) {
            return false;
        }
        // 組織名(フリガナ)のチェック
        if (dto.getOrganizationKnm() == null || "".equals(dto.getOrganizationKnm())) {
            return false;
        }
        // 組織名(ローマ字)のチェック
        if (dto.getOrganizationRomaji() == null || "".equals(dto.getOrganizationRomaji())) {
            return false;
        }
        // 電話番号のチェック
        if (dto.getTel() == null || "".equals(dto.getTel())) {
            return false;
        }
        // 郵便番号のチェック
        if (dto.getZipCd() == null || "".equals(dto.getZipCd())) {
            return false;
        }
        // 住所(地域)コードのチェック
        if (dto.getAddressAreaCd() == null || "".equals(dto.getAddressAreaCd())) {
            return false;
        }
        // 住所(都道府県)コードのチェック
        if (dto.getAddressPrefectureCd() == null || "".equals(dto.getAddressPrefectureCd())) {
            return false;
        }
        // 住所(市区町村)のチェック
        if (dto.getAddressCity() == null || "".equals(dto.getAddressCity())) {
            return false;
        }
        // 責任者名(姓)のチェック
        if (dto.getManagFamilyJnm() == null || "".equals(dto.getManagFamilyJnm())) {
            return false;
        }
        // 責任者名(名)のチェック
        if (dto.getManagFirstJnm() == null || "".equals(dto.getManagFirstJnm())) {
            return false;
        }
        // 責任者名フリガナ(姓)のチェック
        if (dto.getManagFamilyKnm() == null || "".equals(dto.getManagFamilyKnm())) {
            return false;
        }
        // 責任者名フリガナ(名)のチェック
        if (dto.getManagFirstKnm() == null || "".equals(dto.getManagFirstKnm())) {
            return false;
        }
        // 責任者名ローマ字(姓)のチェック
        if (dto.getManagFamilyRomaji() == null || "".equals(dto.getManagFamilyRomaji())) {
            return false;
        }
        // 責任者名ローマ字(名)のチェック
        if (dto.getManagFirstRomaji() == null || "".equals(dto.getManagFirstRomaji())) {
            return false;
        }
        // 責任者所属のチェック
        if (dto.getManagPosition() == null || "".equals(dto.getManagPosition())) {
            return false;
        }
        // 責任者性別区分のチェック
        if (dto.getManagGenderKbn() == null || "".equals(dto.getManagGenderKbn())) {
            return false;
        }
        // 責任者メールアドレス1のチェック
        if (dto.getManagMailAddress1() == null || "".equals(dto.getManagMailAddress1())) {
            return false;
        }
        // 契約開始日のチェック
        if (dto.getContractStrDt() == null || "".equals(dto.getContractStrDt())) {
            return false;
        }
        // 契約終了日のチェック
        if (dto.getContractEndDt() == null || "".equals(dto.getContractEndDt())) {
            return false;
        }
        // 仮ポイント数のチェック
        if (dto.getTempPointNum() == null || "".equals(dto.getTempPointNum())) {
            return false;
        }
        // 請求先住所区分のチェック
        if (dto.getRequestAddressKbn() == null || "".equals(dto.getRequestAddressKbn())) {
            return false;
        }
        // 更新者コードのチェック
        if (dto.getUpdateCd() == null || "".equals(dto.getUpdateCd())) {
            return false;
        }

        return true;

    }
}
