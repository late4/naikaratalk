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
import com.naikara_talk.dto.UserMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DAOクラス<br>
 * <b>クラス名称　　　:</b>利用者マスタデータアクセスクラス<br>
 * <b>クラス概要　　　:</b>利用者マスタの取得、登録、更新を行う<br>
 * <br>
 * <b>著作権　　　　　:</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/06/27 TECS 新規作成
 */
public class UserMstDao extends AbstractDao {

    /** 利用者ID 条件項目 */
    public static final String COND_USER_ID = "USER_ID";
    /** 種別区分 条件項目 */
    public static final String COND_CLASSIFICATION_KBN = "CLASSIFICATION_KBN";
    /** 利用開始日 条件項目 */
    public static final String COND_USE_START_DT = "USE_START_DT";
    /** 利用終了日 条件項目 */
    public static final String COND_USE_END_DT = "USE_END_DT";
    /** フリガナ(姓) 条件項目 */
    public static final String COND_FAMILY_KNM = "FAMILY_KNM";
    /** フリガナ(名) 条件項目 */
    public static final String COND_FIRST_KNM = "FIRST_KNM";

	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public UserMstDao(Connection con) {
		this.conn = con;
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
    public int rowCount(String tableName, ConditionMapper conditions,
            UserMstDto dto) throws NaiException {

        // 戻り値の初期化
        int rowCount = NaikaraTalkConstants.RETURN_CD_DATA_ERR; // データ件数

        // 引数のパラメータチェック
        if (tableName == null || "".equals(tableName)) {
            return rowCount;
        }

        ResultSet res = null;

        StringBuffer sb = new StringBuffer();

        // データ件数の取得処理
        sb.append(" SELECT ");
        sb.append("   COUNT(*)  ");
        sb.append(" FROM ");
        sb.append(" ").append(tableName).append(" ");
        if (!StringUtils.isEmpty(conditions.getConditionString())) {
            sb.append("where ");
            sb.append(conditions.getConditionString());

            // 利用者名(フリガナ)が入力されている場合
            if (!StringUtils.isEmpty(dto.getUserKnm())) {

                sb.append(" and (FAMILY_KNM like ? or FIRST_KNM like ?) ");
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

            // 利用者名(フリガナ)が入力されている場合
            if (!StringUtils.isEmpty(dto.getUserKnm())) {

                ps.setString(i + 1, new StringBuffer().append(dto.getUserKnm())
                        .append(NaikaraTalkConstants.OPERATOR_PERCENT)
                        .toString());
                i++;
                ps.setString(i + 1, new StringBuffer().append(dto.getUserKnm())
                        .append(NaikaraTalkConstants.OPERATOR_PERCENT)
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
     * 利用者マスタデータ取得<br>
     * <br>
     * 利用者マスタリストを戻り値で返却する<br>
     * <br>
     * @param conditions 検索条件<br>
     * @param orderConditions 並び順条件<br>
     * @param dto 判定条件<br>
     * @return list 検索結果<br>
     * @exception NaiException
     */
    public List<UserMstDto> searchWithKnm(ConditionMapper conditions,
            OrderCondition orderConditions, UserMstDto dto) throws NaiException {

        ResultSet res = null;

        StringBuffer sb = new StringBuffer();

        // 利用者マスタ、受講者マスタの全項目取得
        sb.append("select ");
        sb.append(" USER_ID ");
        sb.append(",PASSWORD ");
        sb.append(",FAMILY_JNM ");
        sb.append(",FIRST_JNM ");
        sb.append(",FAMILY_KNM ");
        sb.append(",FIRST_KNM ");
        sb.append(",FAMILY_ROMAJI ");
        sb.append(",FIRST_ROMAJI ");
        sb.append(",TEL1 ");
        sb.append(",TEL2 ");
        sb.append(",BIRTH_YYYY ");
        sb.append(",BIRTH_MM ");
        sb.append(",BIRTH_DD ");
        sb.append(",ZIP_CD ");
        sb.append(",ADDRESS_AREA_CD ");
        sb.append(",ADDRESS_PREFECTURE_CD ");
        sb.append(",ADDRESS_CITY ");
        sb.append(",ADDRESS_OTHERS ");
        sb.append(",GENDER_KBN ");
        sb.append(",MAIL_ADDRESS1 ");
        sb.append(",MAIL_ADDRESS2 ");
        sb.append(",MAIL_ADDRESS3 ");
        sb.append(",USE_START_DT ");
        sb.append(",USE_END_DT ");
        sb.append(",CITY_TOWN ");
        sb.append(",CLASSIFICATION_KBN ");
        sb.append(",REMARK ");
        sb.append(",RECORD_VER_NO ");
        sb.append(",INSERT_DTTM ");
        sb.append(",INSERT_CD ");
        sb.append(",UPDATE_DTTM ");
        sb.append(",UPDATE_CD ");
        sb.append("from USER_MST ");

        if (!StringUtils.isEmpty(conditions.getConditionString())) {
            sb.append("where ");
            sb.append(conditions.getConditionString());

            // 利用者名(フリガナ)が入力されている場合
            if (!StringUtils.isEmpty(dto.getUserKnm())) {

                sb.append(" and (FAMILY_KNM like ? or FIRST_KNM like ?) ");
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

            // 利用者名(フリガナ)が入力されている場合
            if (!StringUtils.isEmpty(dto.getUserKnm())) {

                ps.setString(i + 1, new StringBuffer().append(dto.getUserKnm())
                        .append(NaikaraTalkConstants.OPERATOR_PERCENT)
                        .toString());
                i++;
                ps.setString(i + 1, new StringBuffer().append(dto.getUserKnm())
                        .append(NaikaraTalkConstants.OPERATOR_PERCENT)
                        .toString());
            }

            res = ps.executeQuery();

            // コード管理マスタのキャッシュ読み込み
            CodeManagMstCache cache = CodeManagMstCache.getInstance();
            LinkedHashMap<String, CodeManagMstDto> authorityList = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_AUTHORITY);

            ArrayList<UserMstDto> list = new ArrayList<UserMstDto>();

            while (res.next()) {

                UserMstDto retDto = new UserMstDto();

                retDto.setUserId(res.getString("USER_ID"));
                retDto.setPassword(res.getString("PASSWORD"));
                retDto.setFamilyJnm(res.getString("FAMILY_JNM"));
                retDto.setFirstJnm(res.getString("FIRST_JNM"));
                retDto.setFamilyKnm(res.getString("FAMILY_KNM"));
                retDto.setFirstKnm(res.getString("FIRST_KNM"));
                retDto.setFamilyRomaji(res.getString("FAMILY_ROMAJI"));
                retDto.setFirstRomaji(res.getString("FIRST_ROMAJI"));
                retDto.setTel1(res.getString("TEL1"));
                retDto.setTel2(res.getString("TEL2"));
                retDto.setBirthYyyy(res.getString("BIRTH_YYYY"));
                retDto.setBirthMm(res.getString("BIRTH_MM"));
                retDto.setBirthDd(res.getString("BIRTH_DD"));
                retDto.setZipCd(res.getString("ZIP_CD"));
                retDto.setAddressAreaCd(res.getString("ADDRESS_AREA_CD"));
                retDto.setAddressPrefectureCd(res
                        .getString("ADDRESS_PREFECTURE_CD"));
                retDto.setAddressCity(res.getString("ADDRESS_CITY"));
                retDto.setAddressOthers(res.getString("ADDRESS_OTHERS"));
                retDto.setGenderKbn(res.getString("GENDER_KBN"));
                retDto.setMailAddress1(res.getString("MAIL_ADDRESS1"));
                retDto.setMailAddress2(res.getString("MAIL_ADDRESS2"));
                retDto.setMailAddress3(res.getString("MAIL_ADDRESS3"));
                retDto.setUseStartDt(res.getString("USE_START_DT"));
                retDto.setUseEndDt(res.getString("USE_END_DT"));
                retDto.setCityTown(res.getString("CITY_TOWN"));
                retDto.setClassificationKbn(res.getString("CLASSIFICATION_KBN"));
                retDto.setRemark(res.getString("REMARK"));
                retDto.setRecordVerNo(res.getInt("RECORD_VER_NO"));
                retDto.setInsertDttm(res.getTimestamp("INSERT_DTTM"));
                retDto.setInsertCd(res.getString("INSERT_CD"));
                retDto.setUpdateDttm(res.getTimestamp("UPDATE_DTTM"));
                retDto.setUpdateCd(res.getString("UPDATE_CD"));

                retDto.setUserNm(NaikaraStringUtil.unionName(
                        retDto.getFamilyJnm(), retDto.getFirstJnm()));
                retDto.setUserKnm(NaikaraStringUtil.unionName(
                        retDto.getFamilyKnm(), retDto.getFirstKnm()));
                retDto.setAuthority(authorityList.get(
                        res.getString("CLASSIFICATION_KBN")).getManagerNm());

                retDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);

                list.add(retDto);
            }

            if (list.size() < 1) {
                UserMstDto retDto = new UserMstDto();
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
                throw new NaiException(e);
            }
        }
    }

    /**
     * 受講者マスタ登録処理<br>
     * <br>
     * 受講者マスタの登録を行う<br>
     * <br>
     * @param dto 登録データ
     * @return insertRowCount 登録件数
     * @exception NaiException
     */
    public int insert(UserMstDto dto) throws NaiException {

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        int insertRowCount = NaikaraTalkConstants.RETURN_CD_ERR_INS; // 登録データ件数

        // 受講者マスタのデータチェック
        if (!checkDto(dto)) {
            return insertRowCount;
        }

        StringBuffer sb = new StringBuffer();

        // データ登録処理
        sb.append("insert into ");
        sb.append("USER_MST ");
        sb.append(" ( ");
        sb.append(" USER_ID ");
        sb.append(",PASSWORD ");
        sb.append(",FAMILY_JNM ");
        sb.append(",FIRST_JNM ");
        sb.append(",FAMILY_KNM ");
        sb.append(",FIRST_KNM ");
        sb.append(",FAMILY_ROMAJI ");
        sb.append(",FIRST_ROMAJI ");
        sb.append(",TEL1 ");
        sb.append(",TEL2 ");
        sb.append(",BIRTH_YYYY ");
        sb.append(",BIRTH_MM ");
        sb.append(",BIRTH_DD ");
        sb.append(",ZIP_CD ");
        sb.append(",ADDRESS_AREA_CD ");
        sb.append(",ADDRESS_PREFECTURE_CD ");
        sb.append(",ADDRESS_CITY ");
        sb.append(",ADDRESS_OTHERS ");
        sb.append(",GENDER_KBN ");
        sb.append(",MAIL_ADDRESS1 ");
        sb.append(",MAIL_ADDRESS2 ");
        sb.append(",MAIL_ADDRESS3 ");
        sb.append(",USE_START_DT ");
        sb.append(",USE_END_DT ");
        sb.append(",CITY_TOWN ");
        sb.append(",CLASSIFICATION_KBN ");
        sb.append(",REMARK ");
        sb.append(",RECORD_VER_NO ");
        sb.append(",INSERT_DTTM ");
        sb.append(",INSERT_CD ");
        sb.append(",UPDATE_DTTM ");
        sb.append(",UPDATE_CD ");
        sb.append(" ) value ( ");
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
        sb.append(",? ");
        sb.append(",? ");
        sb.append(" ) ");

        try {

            PreparedStatement ps = conn.prepareStatement(sb.toString());

            ps.setString(1, dto.getUserId());
            ps.setString(2, dto.getPassword());
            ps.setString(3, dto.getFamilyJnm());
            ps.setString(4, dto.getFirstJnm());
            ps.setString(5, dto.getFamilyKnm());
            ps.setString(6, dto.getFirstKnm());
            ps.setString(7, dto.getFamilyRomaji());
            ps.setString(8, dto.getFirstRomaji());
            ps.setString(9, dto.getTel1());
            ps.setString(10, dto.getTel2());
            ps.setString(11, dto.getBirthYyyy());
            ps.setString(12, dto.getBirthMm());
            ps.setString(13, dto.getBirthDd());
            ps.setString(14, dto.getZipCd());
            ps.setString(15, dto.getAddressAreaCd());
            ps.setString(16, dto.getAddressPrefectureCd());
            ps.setString(17, dto.getAddressCity());
            ps.setString(18, dto.getAddressOthers());
            ps.setString(19, dto.getGenderKbn());
            ps.setString(20, dto.getMailAddress1());
            ps.setString(21, dto.getMailAddress2());
            ps.setString(22, dto.getMailAddress3());
            ps.setString(23, dto.getUseStartDt());
            ps.setString(24, dto.getUseEndDt());
            ps.setString(25, dto.getCityTown());
            ps.setString(26, dto.getClassificationKbn());
            ps.setString(27, dto.getRemark());
            ps.setString(28, String.valueOf(dto.getRecordVerNo()));
            ps.setString(29, String.valueOf(Timestamp.valueOf(sdf.format(cal
                    .getTime()))));
            ps.setString(30, dto.getInsertCd());
            ps.setString(31, String.valueOf(Timestamp.valueOf(sdf.format(cal
                    .getTime()))));
            ps.setString(32, dto.getUpdateCd());


            insertRowCount = ps.executeUpdate();

            return insertRowCount;

        } catch (SQLException se) {
            throw new NaiException(se);
        }
    }

    /**
     * 利用者マスタデータ取得<br>
     * <br>
     * 利用者マスタリストを戻り値で返却する<br>
     * <br>
     * @param conditions 検索条件<br>
     * @param orderConditions 並び順条件<br>
     * @return list 検索結果<br>
     * @exception NaiException
     */
    public List<UserMstDto> search(ConditionMapper conditions,
            OrderCondition orderConditions) throws NaiException {

        ResultSet res = null;

        StringBuffer sb = new StringBuffer();

        // 利用者マスタ、受講者マスタの全項目取得
        sb.append("select ");
        sb.append(" USER_ID ");
        sb.append(",PASSWORD ");
        sb.append(",FAMILY_JNM ");
        sb.append(",FIRST_JNM ");
        sb.append(",FAMILY_KNM ");
        sb.append(",FIRST_KNM ");
        sb.append(",FAMILY_ROMAJI ");
        sb.append(",FIRST_ROMAJI ");
        sb.append(",TEL1 ");
        sb.append(",TEL2 ");
        sb.append(",BIRTH_YYYY ");
        sb.append(",BIRTH_MM ");
        sb.append(",BIRTH_DD ");
        sb.append(",ZIP_CD ");
        sb.append(",ADDRESS_AREA_CD ");
        sb.append(",ADDRESS_PREFECTURE_CD ");
        sb.append(",ADDRESS_CITY ");
        sb.append(",ADDRESS_OTHERS ");
        sb.append(",GENDER_KBN ");
        sb.append(",MAIL_ADDRESS1 ");
        sb.append(",MAIL_ADDRESS2 ");
        sb.append(",MAIL_ADDRESS3 ");
        sb.append(",USE_START_DT ");
        sb.append(",USE_END_DT ");
        sb.append(",CITY_TOWN ");
        sb.append(",CLASSIFICATION_KBN ");
        sb.append(",REMARK ");
        sb.append(",RECORD_VER_NO ");
        sb.append(",INSERT_DTTM ");
        sb.append(",INSERT_CD ");
        sb.append(",UPDATE_DTTM ");
        sb.append(",UPDATE_CD ");
        sb.append("from USER_MST ");

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

            ArrayList<UserMstDto> list = new ArrayList<UserMstDto>();

            while (res.next()) {

                UserMstDto retDto = new UserMstDto();

                retDto.setUserId(res.getString("USER_ID"));
                retDto.setPassword(res.getString("PASSWORD"));
                retDto.setFamilyJnm(res.getString("FAMILY_JNM"));
                retDto.setFirstJnm(res.getString("FIRST_JNM"));
                retDto.setFamilyKnm(res.getString("FAMILY_KNM"));
                retDto.setFirstKnm(res.getString("FIRST_KNM"));
                retDto.setFamilyRomaji(res.getString("FAMILY_ROMAJI"));
                retDto.setFirstRomaji(res.getString("FIRST_ROMAJI"));
                retDto.setTel1(res.getString("TEL1"));
                retDto.setTel2(res.getString("TEL2"));
                retDto.setBirthYyyy(res.getString("BIRTH_YYYY"));
                retDto.setBirthMm(res.getString("BIRTH_MM"));
                retDto.setBirthDd(res.getString("BIRTH_DD"));
                retDto.setZipCd(res.getString("ZIP_CD"));
                retDto.setAddressAreaCd(res.getString("ADDRESS_AREA_CD"));
                retDto.setAddressPrefectureCd(res
                        .getString("ADDRESS_PREFECTURE_CD"));
                retDto.setAddressCity(res.getString("ADDRESS_CITY"));
                retDto.setAddressOthers(res.getString("ADDRESS_OTHERS"));
                retDto.setGenderKbn(res.getString("GENDER_KBN"));
                retDto.setMailAddress1(res.getString("MAIL_ADDRESS1"));
                retDto.setMailAddress2(res.getString("MAIL_ADDRESS2"));
                retDto.setMailAddress3(res.getString("MAIL_ADDRESS3"));
                retDto.setUseStartDt(res.getString("USE_START_DT"));
                retDto.setUseEndDt(res.getString("USE_END_DT"));
                retDto.setCityTown(res.getString("CITY_TOWN"));
                retDto.setClassificationKbn(res.getString("CLASSIFICATION_KBN"));
                retDto.setRemark(res.getString("REMARK"));
                retDto.setRecordVerNo(res.getInt("RECORD_VER_NO"));
                retDto.setInsertDttm(res.getTimestamp("INSERT_DTTM"));
                retDto.setInsertCd(res.getString("INSERT_CD"));
                retDto.setUpdateDttm(res.getTimestamp("UPDATE_DTTM"));
                retDto.setUpdateCd(res.getString("UPDATE_CD"));

                retDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);

                list.add(retDto);
            }

            if (list.size() < 1) {
                UserMstDto retDto = new UserMstDto();
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
                throw new NaiException(e);
            }
        }
    }

    /**
     * 利用者マスタ更新<br>
     * <br>
     * 利用者マスタの更新を行う<br>
     * <br>
     * @param dto 更新データ<br>
     * @return updatedRowCount 更新件数<br>
     * @exception NaiException
     */
    public int update(UserMstDto dto) throws NaiException {

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        int updatedRowCount = NaikaraTalkConstants.RETURN_CD_ERR_UPD; // 更新データ件数

        // 利用者マスタのデータチェック
        if (!checkDto(dto)) {
            return updatedRowCount;
        }


        StringBuffer sb = new StringBuffer();

        // データ更新処理
        sb.append("update ");
        sb.append("USER_MST ");
        sb.append("set ");
        sb.append(" PASSWORD = ? ");
        sb.append(",FAMILY_JNM = ? ");
        sb.append(",FIRST_JNM = ? ");
        sb.append(",FAMILY_KNM = ? ");
        sb.append(",FIRST_KNM = ? ");
        sb.append(",FAMILY_ROMAJI = ? ");
        sb.append(",FIRST_ROMAJI = ? ");
        sb.append(",TEL1 = ? ");
        sb.append(",TEL2 = ? ");
        sb.append(",BIRTH_YYYY = ? ");
        sb.append(",BIRTH_MM = ? ");
        sb.append(",BIRTH_DD = ? ");
        sb.append(",ZIP_CD = ? ");
        sb.append(",ADDRESS_AREA_CD = ? ");
        sb.append(",ADDRESS_PREFECTURE_CD = ? ");
        sb.append(",ADDRESS_CITY = ? ");
        sb.append(",ADDRESS_OTHERS = ? ");
        sb.append(",GENDER_KBN = ? ");
        sb.append(",MAIL_ADDRESS1 = ? ");
        sb.append(",MAIL_ADDRESS2 = ? ");
        sb.append(",MAIL_ADDRESS3 = ? ");
        sb.append(",USE_START_DT = ? ");
        sb.append(",USE_END_DT = ? ");
        sb.append(",CITY_TOWN = ? ");
        sb.append(",CLASSIFICATION_KBN = ? ");
        sb.append(",REMARK = ? ");
        sb.append(",RECORD_VER_NO = ? ");
        sb.append(",UPDATE_DTTM = ? ");
        sb.append(",UPDATE_CD = ? ");
        sb.append("where ");
        sb.append("USER_ID = ? ");
        sb.append("and ");
        sb.append("RECORD_VER_NO = ? ");

        try {

            PreparedStatement ps = conn.prepareStatement(sb.toString());

            ps.setString(1, dto.getPassword());
            ps.setString(2, dto.getFamilyJnm());
            ps.setString(3, dto.getFirstJnm());
            ps.setString(4, dto.getFamilyKnm());
            ps.setString(5, dto.getFirstKnm());
            ps.setString(6, dto.getFamilyRomaji());
            ps.setString(7, dto.getFirstRomaji());
            ps.setString(8, dto.getTel1());
            ps.setString(9, dto.getTel2());
            ps.setString(10, dto.getBirthYyyy());
            ps.setString(11, dto.getBirthMm());
            ps.setString(12, dto.getBirthDd());
            ps.setString(13, dto.getZipCd());
            ps.setString(14, dto.getAddressAreaCd());
            ps.setString(15, dto.getAddressPrefectureCd());
            ps.setString(16, dto.getAddressCity());
            ps.setString(17, dto.getAddressOthers());
            ps.setString(18, dto.getGenderKbn());
            ps.setString(19, dto.getMailAddress1());
            ps.setString(20, dto.getMailAddress2());
            ps.setString(21, dto.getMailAddress3());
            ps.setString(22, dto.getUseStartDt());
            ps.setString(23, dto.getUseEndDt());
            ps.setString(24, dto.getCityTown());
            ps.setString(25, dto.getClassificationKbn());
            ps.setString(26, dto.getRemark());
            ps.setString(27, String.valueOf(dto.getRecordVerNo() + 1));
            ps.setString(28, String.valueOf(Timestamp.valueOf(sdf.format(cal
                    .getTime()))));
            ps.setString(29, dto.getUpdateCd());
            ps.setString(30, dto.getUserId());
            ps.setString(31, String.valueOf(dto.getRecordVerNo()));


            updatedRowCount = ps.executeUpdate();

            return updatedRowCount;

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
    private boolean checkDto(UserMstDto dto) throws NaiException {

        // ユーザIDのチェック
        if (dto.getUserId() == null || "".equals(dto.getUserId())) {
            return false;
        }

        // パスワードのチェック
        if (dto.getPassword() == null || "".equals(dto.getPassword())) {
            return false;
        }

        // 名前(姓)のチェック
        if (dto.getFamilyJnm() == null || "".equals(dto.getFamilyJnm())) {
            return false;
        }

        // 名前(名)のチェック
        if (dto.getFirstJnm() == null || "".equals(dto.getFirstJnm())) {
            return false;
        }

        // フリガナ(姓)のチェック
        if (dto.getFamilyKnm() == null || "".equals(dto.getFamilyKnm())) {
            return false;
        }

        // フリガナ(名)のチェック
        if (dto.getFirstKnm() == null || "".equals(dto.getFirstKnm())) {
            return false;
        }

        // ローマ字(姓)のチェック
        if (dto.getFamilyRomaji() == null || "".equals(dto.getFamilyRomaji())) {
            return false;
        }

        // ローマ字(名)のチェック
        if (dto.getFirstRomaji() == null || "".equals(dto.getFirstRomaji())) {
            return false;
        }

        // 電話番号1のチェック
        if (dto.getTel1() == null || "".equals(dto.getTel1())) {
            return false;
        }

        // 生年月日：年のチェック
        if (dto.getBirthYyyy() == null || "".equals(dto.getBirthYyyy())) {
            return false;
        }

        // 生年月日：月のチェック
        if (dto.getBirthMm() == null || "".equals(dto.getBirthMm())) {
            return false;
        }

        // 生年月日：日のチェック
        if (dto.getBirthDd() == null || "".equals(dto.getBirthDd())) {
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
        if (dto.getAddressPrefectureCd() == null
                || "".equals(dto.getAddressPrefectureCd())) {
            return false;
        }

        // 住所(市区町村)のチェック
        if (dto.getAddressCity() == null || "".equals(dto.getAddressCity())) {
            return false;
        }

        // 性別区分のチェック
        if (dto.getGenderKbn() == null || "".equals(dto.getGenderKbn())) {
            return false;
        }

        // メールアドレス1のチェック
        if (dto.getMailAddress1() == null || "".equals(dto.getMailAddress1())) {
            return false;
        }

        // 利用開始日のチェック
        if (dto.getUseStartDt() == null || "".equals(dto.getUseStartDt())) {
            return false;
        }

        // 利用終了日のチェック
        if (dto.getUseEndDt() == null || "".equals(dto.getUseEndDt())) {
            return false;
        }

        // 種別区分のチェック
        if (dto.getClassificationKbn() == null
                || "".equals(dto.getClassificationKbn())) {
            return false;
        }

        // 更新者コードのチェック
        if (dto.getUpdateCd() == null || "".equals(dto.getUpdateCd())) {
            return false;
        }

        return true;

    }
}