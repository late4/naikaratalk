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
import org.apache.log4j.Logger;

import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dbutil.QueryCondition;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.StudentMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DAOクラス<br>
 * <b>クラス名称　　　:</b>受講者マスタデータアクセスクラスクラス<br>
 * <b>クラス概要　　　:</b>受講者マスタの取得、登録、更新、削除を行う<br>
 * <br>
 * <b>著作権　　　　　:</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/05/30 TECS 新規作成
 */
public class StudentMstDao extends AbstractDao {

    protected Logger log = Logger.getLogger(this.getClass());

    /** 受講者ID 条件項目  */
    public static final String COND_STUDENT_ID = "STUDENT_ID";
    /** 名前(姓) 条件項目  */
    public static final String COND_FAMILY_JNM = "FAMILY_JNM";
    /** 名前(名) 条件項目  */
    public static final String COND_FIRST_JNM = "FIRST_JNM";
    /** フリガナ(姓) 条件項目  */
    public static final String COND_FAMILY_KNM = "FAMILY_KNM";
    /** フリガナ(名) 条件項目  */
    public static final String COND_FIRST_KNM = "FIRST_KNM";
    /** ローマ字(姓) 条件項目  */
    public static final String COND_FAMILY_ROMAJI = "FAMILY_ROMAJI";
    /** ローマ字(名) 条件項目  */
    public static final String COND_FIRST_ROMAJI = "FIRST_ROMAJI";
    /** ニックネーム 条件項目  */
    public static final String COND_NICK_NM = "NICK_NM";
    /** 顧客区分 条件項目  */
    public static final String COND_CUSTOMER_KBN = "CUSTOMER_KBN";
    /** 組織名 条件項目  */
    public static final String COND_ORGANIZATION_NM = "ORGANIZATION_NM";
    /** 利用開始日 条件項目  */
    public static final String COND_USE_STR_DT = "USE_STR_DT";
    /** 利用終了日 条件項目  */
    public static final String COND_USE_END_DT = "USE_END_DT";
    /** 利用停止フラグ 条件項目  */
    public static final String COND_USE_STOP_FLG = "USE_STOP_FLG";
    /** 組織ID 条件項目  */
    public static final String COND_ORGANIZATION_ID = "ORGANIZATION_ID";
    /** 所属組織内ID 条件項目  */
    public static final String COND_POSITION_ORGANIZATION_ID = "POSITION_ORGANIZATION_ID";
    /** 受講者所属部署 条件項目  */
    public static final String COND_STUDENT_POSITION = "STUDENT_POSITION";
    /** ポイント購入済フラグ 条件項目  */
    public static final String COND_POINT_PURCHASE_FLG = "POINT_PURCHASE_FLG";
    /** メールアドレス1　条件項目　*/
    public static final String COND_MAIL_ADDRESS1 = "MAIL_ADDRESS1";
    /** メールアドレス2　条件項目　*/
    public static final String COND_MAIL_ADDRESS2 = "MAIL_ADDRESS2";
    /** メールアドレス3　条件項目　*/
    public static final String COND_MAIL_ADDRESS3 = "MAIL_ADDRESS3";

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public StudentMstDao(Connection con) {
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
    public int rowCount(String tableName, ConditionMapper conditions, StudentMstDto dto) throws NaiException {

        // 戻り値の初期化
        int rowCount = NaikaraTalkConstants.RETURN_CD_DATA_ERR; // データ件数

        // 引数のパラメータチェック
        if (tableName == null || "".equals(tableName)) {
            return rowCount;
        }

        // データ件数の取得処理
        ResultSet res = null;

        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT ");
        sb.append("   COUNT(*)  ");
        sb.append(" FROM ");
        sb.append(" ").append(tableName).append(" ");
        if (!StringUtils.isEmpty(conditions.getConditionString())) {
            sb.append("where ");
            sb.append(conditions.getConditionString());

            // 受講者名(フリガナ)が入力されている場合
            if (!StringUtils.isEmpty(dto.getStudentNm())) {

                sb.append(" and (FAMILY_KNM like ? or FIRST_KNM like ?) ");
            }
        } else {

            // 受講者名(フリガナ)が入力されている場合
            if (!StringUtils.isEmpty(dto.getStudentNm())) {
                sb.append("where ");
                sb.append(" (FAMILY_KNM like ? or FIRST_KNM like ?) ");
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

            // 受講者名(フリガナ)が入力されている場合
            if (!StringUtils.isEmpty(dto.getStudentNm())) {

                ps.setString(i + 1,
                        new StringBuffer().append(dto.getStudentNm()).append(NaikaraTalkConstants.OPERATOR_PERCENT)
                                .toString());
                i++;
                ps.setString(i + 1,
                        new StringBuffer().append(dto.getStudentNm()).append(NaikaraTalkConstants.OPERATOR_PERCENT)
                                .toString());
            }

            // 実行
            res = ps.executeQuery();

            rowCount = 0; // データ件数
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
     * 受講者マスタデータ取得<br>
     * <br>
     * 受講者マスタリストを戻り値で返却する<br>
     * <br>
     * @param conditions
     * @param orderConditions
     * @return StudentMstDto
     * @throws NaiException
     */
    public List<StudentMstDto> search(ConditionMapper conditions, OrderCondition orderConditions) throws NaiException {

        ResultSet res = null;

        StringBuffer sb = new StringBuffer();

        // 受講者マスタの全項目取得
        sb.append("select ");
        sb.append(" STUDENT_ID ");
        sb.append(",FAMILY_JNM ");
        sb.append(",FIRST_JNM ");
        sb.append(",FAMILY_KNM ");
        sb.append(",FIRST_KNM ");
        sb.append(",FAMILY_ROMAJI ");
        sb.append(",FIRST_ROMAJI ");
        sb.append(",NICK_NM ");
        sb.append(",PASSWORD ");
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
        sb.append(",LOGIN_NUM ");
        sb.append(",END_LOGIN_DT ");
        sb.append(",OCCUPATION_CD ");
        sb.append(",CUSTOMER_KBN ");
        sb.append(",ORGANIZATION_NM ");
        sb.append(",USE_STR_DT ");
        sb.append(",USE_END_DT ");
        sb.append(",USE_STOP_FLG ");
        sb.append(",USE_MOTIVE_FLG1 ");
        sb.append(",USE_MOTIVE_FLG2 ");
        sb.append(",USE_MOTIVE_FLG3 ");
        sb.append(",USE_MOTIVE_FLG4 ");
        sb.append(",USE_MOTIVE_FLG5 ");
        sb.append(",USE_MOTIVE ");
        sb.append(",ACHIEVEMENT ");
        sb.append(",USE_AGREEMENT_FLG ");
        sb.append(",INDIVIDUAL_AGREEMENT_FLG ");
        sb.append(",SCHOOL_CMT ");
        sb.append(",REMARK ");
        sb.append(",ORGANIZATION_ID ");
        sb.append(",POSITION_ORGANIZATION_ID ");
        sb.append(",STUDENT_POSITION ");
        sb.append(",BURDEN_NUM ");
        sb.append(",CONSENT_DOCUMENT_ACQUISITION_FLG ");
        sb.append(",POINT_PURCHASE_FLG ");
        sb.append(",DM_NO_NEED_FLG ");
        sb.append(",OTHER1_FLG ");
        sb.append(",OTHER2_FLG ");
        sb.append(",OTHER3_FLG ");
        sb.append(",OTHER4_FLG ");
        sb.append(",GUARDIAN_FAMILY_JNM ");
        sb.append(",GUARDIAN_FIRST_JNM ");
        sb.append(",GUARDIAN_FAMILY_KNM ");
        sb.append(",GUARDIAN_FIRST_KNM ");
        sb.append(",GUARDIAN_FAMILY_RELATIONSHIP ");
        sb.append(",GUARDIAN_TEL1 ");
        sb.append(",GUARDIAN_TEL2 ");
        sb.append(",GUARDIAN_BIRTH_YYYY ");
        sb.append(",GUARDIAN_BIRTH_MM ");
        sb.append(",GUARDIAN_BIRTH_DD ");
        sb.append(",GUARDIAN_ZIP_CD ");
        sb.append(",GUARDIAN_ADDRESS_AREA_CD ");
        sb.append(",GUARDIAN_ADDRESS_PREFECTURE_CD ");
        sb.append(",GUARDIAN_ADDRESS_CITY ");
        sb.append(",GUARDIAN_ADDRESS_OTHERS ");
        sb.append(",GUARDIAN_GENDER_KBN ");
        sb.append(",GUARDIAN_MAIL_ADDRESS1 ");
        sb.append(",GUARDIAN_MAIL_ADDRESS2 ");
        sb.append(",GUARDIAN_MAIL_ADDRESS3 ");
        sb.append(",RECORD_VER_NO ");
        sb.append(",INSERT_DTTM ");
        sb.append(",INSERT_CD ");
        sb.append(",UPDATE_DTTM ");
        sb.append(",UPDATE_CD ");
        sb.append(",RECORD_VER_NO ");
        sb.append(",INSERT_DTTM ");
        sb.append(",INSERT_CD ");
        sb.append(",UPDATE_DTTM ");
        sb.append(",UPDATE_CD ");
        sb.append("from ");
        sb.append(" STUDENT_MST ");
        // 抽出条件の設定
        if (!StringUtils.isEmpty(conditions.getConditionString())) {
            sb.append("where ");
            sb.append(conditions.getConditionString());
        }
        // 並び順の設定
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

            ArrayList<StudentMstDto> list = new ArrayList<StudentMstDto>();

            while (res.next()) {

                StudentMstDto retDto = new StudentMstDto();

                retDto.setStudentId(res.getString("STUDENT_ID"));
                retDto.setFamilyJnm(res.getString("FAMILY_JNM"));
                retDto.setFirstJnm(res.getString("FIRST_JNM"));
                retDto.setFamilyKnm(res.getString("FAMILY_KNM"));
                retDto.setFirstKnm(res.getString("FIRST_KNM"));
                retDto.setFamilyRomaji(res.getString("FAMILY_ROMAJI"));
                retDto.setFirstRomaji(res.getString("FIRST_ROMAJI"));
                retDto.setNickNm(res.getString("NICK_NM"));
                retDto.setPassword(res.getString("PASSWORD"));
                retDto.setTel1(res.getString("TEL1"));
                retDto.setTel2(res.getString("TEL2"));
                retDto.setBirthYyyy(res.getString("BIRTH_YYYY"));
                retDto.setBirthMm(res.getString("BIRTH_MM"));
                retDto.setBirthDd(res.getString("BIRTH_DD"));
                retDto.setZipCd(res.getString("ZIP_CD"));
                retDto.setAddressAreaCd(res.getString("ADDRESS_AREA_CD"));
                retDto.setAddressPrefectureCd(res.getString("ADDRESS_PREFECTURE_CD"));
                retDto.setAddressCity(res.getString("ADDRESS_CITY"));
                retDto.setAddressOthers(res.getString("ADDRESS_OTHERS"));
                retDto.setGenderKbn(res.getString("GENDER_KBN"));
                retDto.setMailAddress1(res.getString("MAIL_ADDRESS1"));
                retDto.setMailAddress2(res.getString("MAIL_ADDRESS2"));
                retDto.setMailAddress3(res.getString("MAIL_ADDRESS3"));
                retDto.setLoginNum(res.getInt("LOGIN_NUM"));
                retDto.setEndLoginDt(res.getString("END_LOGIN_DT"));
                retDto.setOccupationCd(res.getString("OCCUPATION_CD"));
                retDto.setCustomerKbn(res.getString("CUSTOMER_KBN"));
                retDto.setOrganizationNm(res.getString("ORGANIZATION_NM"));
                retDto.setUseStrDt(res.getString("USE_STR_DT"));
                retDto.setUseEndDt(res.getString("USE_END_DT"));
                retDto.setUseStopFlg(res.getString("USE_STOP_FLG"));
                retDto.setUseMotiveFlg1(res.getString("USE_MOTIVE_FLG1"));
                retDto.setUseMotiveFlg2(res.getString("USE_MOTIVE_FLG2"));
                retDto.setUseMotiveFlg3(res.getString("USE_MOTIVE_FLG3"));
                retDto.setUseMotiveFlg4(res.getString("USE_MOTIVE_FLG4"));
                retDto.setUseMotiveFlg5(res.getString("USE_MOTIVE_FLG5"));
                retDto.setUseMotive(res.getString("USE_MOTIVE"));
                retDto.setAchievement(res.getString("ACHIEVEMENT"));
                retDto.setUseAgreementFlg(res.getString("USE_AGREEMENT_FLG"));
                retDto.setIndividualAgreementFlg(res.getString("INDIVIDUAL_AGREEMENT_FLG"));
                retDto.setSchoolCmt(res.getString("SCHOOL_CMT"));
                retDto.setRemark(res.getString("REMARK"));
                retDto.setOrganizationId(res.getString("ORGANIZATION_ID"));
                retDto.setPositionOrganizationId(res.getString("POSITION_ORGANIZATION_ID"));
                retDto.setStudentPosition(res.getString("STUDENT_POSITION"));
                retDto.setBurdenNum(res.getInt("BURDEN_NUM"));
                retDto.setConsentDocumentAcquisitionFlg(res.getString("CONSENT_DOCUMENT_ACQUISITION_FLG"));
                retDto.setPointPurchaseFlg(res.getString("POINT_PURCHASE_FLG"));
                retDto.setDmNoNeedFlg(res.getInt("DM_NO_NEED_FLG"));
                retDto.setOther1Flg(res.getInt("OTHER1_FLG"));
                retDto.setOther2Flg(res.getInt("OTHER2_FLG"));
                retDto.setOther3Flg(res.getInt("OTHER3_FLG"));
                retDto.setOther4Flg(res.getInt("OTHER4_FLG"));
                retDto.setGuardianFamilyJnm(res.getString("GUARDIAN_FAMILY_JNM"));
                retDto.setGuardianFirstJnm(res.getString("GUARDIAN_FIRST_JNM"));
                retDto.setGuardianFamilyKnm(res.getString("GUARDIAN_FAMILY_KNM"));
                retDto.setGuardianFirstKnm(res.getString("GUARDIAN_FIRST_KNM"));
                retDto.setGuardianFamilyRelationship(res.getString("GUARDIAN_FAMILY_RELATIONSHIP"));
                retDto.setGuardianTel1(res.getString("GUARDIAN_TEL1"));
                retDto.setGuardianTel2(res.getString("GUARDIAN_TEL2"));
                retDto.setGuardianBirthYyyy(res.getString("GUARDIAN_BIRTH_YYYY"));
                retDto.setGuardianBirthMm(res.getString("GUARDIAN_BIRTH_MM"));
                retDto.setGuardianBirthDd(res.getString("GUARDIAN_BIRTH_DD"));
                retDto.setGuardianZipCd(res.getString("GUARDIAN_ZIP_CD"));
                retDto.setGuardianAddressAreaCd(res.getString("GUARDIAN_ADDRESS_AREA_CD"));
                retDto.setGuardianAddressPrefectureCd(res.getString("GUARDIAN_ADDRESS_PREFECTURE_CD"));
                retDto.setGuardianAddressCity(res.getString("GUARDIAN_ADDRESS_CITY"));
                retDto.setGuardianAddressOthers(res.getString("GUARDIAN_ADDRESS_OTHERS"));
                retDto.setGuardianGenderKbn(res.getString("GUARDIAN_GENDER_KBN"));
                retDto.setGuardianMailAddress1(res.getString("GUARDIAN_MAIL_ADDRESS1"));
                retDto.setGuardianMailAddress2(res.getString("GUARDIAN_MAIL_ADDRESS2"));
                retDto.setGuardianMailAddress3(res.getString("GUARDIAN_MAIL_ADDRESS3"));
                retDto.setRecordVerNo(res.getInt("RECORD_VER_NO"));
                retDto.setInsertDttm(res.getTimestamp("INSERT_DTTM"));
                retDto.setInsertCd(res.getString("INSERT_CD"));
                retDto.setUpdateDttm(res.getTimestamp("UPDATE_DTTM"));
                retDto.setUpdateCd(res.getString("UPDATE_CD"));

                retDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);

                list.add(retDto);
            }

            if (list.size() < 1) {
                StudentMstDto retDto = new StudentMstDto();
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
     * @param StudentMstDto
     * @return insertRowCount
     * @throws NaiException
     */
    public int insert(StudentMstDto dto) throws NaiException {

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
        sb.append("STUDENT_MST ");
        sb.append(" ( ");
        sb.append(" STUDENT_ID ");
        sb.append(",FAMILY_JNM ");
        sb.append(",FIRST_JNM ");
        sb.append(",FAMILY_KNM ");
        sb.append(",FIRST_KNM ");
        sb.append(",FAMILY_ROMAJI ");
        sb.append(",FIRST_ROMAJI ");
        sb.append(",NICK_NM ");
        sb.append(",PASSWORD ");
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
        sb.append(",LOGIN_NUM ");
        sb.append(",END_LOGIN_DT ");
        sb.append(",OCCUPATION_CD ");
        sb.append(",CUSTOMER_KBN ");
        sb.append(",ORGANIZATION_NM ");
        sb.append(",USE_STR_DT ");
        sb.append(",USE_END_DT ");
        sb.append(",USE_STOP_FLG ");
        sb.append(",USE_MOTIVE_FLG1 ");
        sb.append(",USE_MOTIVE_FLG2 ");
        sb.append(",USE_MOTIVE_FLG3 ");
        sb.append(",USE_MOTIVE_FLG4 ");
        sb.append(",USE_MOTIVE_FLG5 ");
        sb.append(",USE_MOTIVE ");
        sb.append(",ACHIEVEMENT ");
        sb.append(",USE_AGREEMENT_FLG ");
        sb.append(",INDIVIDUAL_AGREEMENT_FLG ");
        sb.append(",SCHOOL_CMT ");
        sb.append(",REMARK ");
        sb.append(",ORGANIZATION_ID ");
        sb.append(",POSITION_ORGANIZATION_ID ");
        sb.append(",STUDENT_POSITION ");
        sb.append(",BURDEN_NUM ");
        sb.append(",CONSENT_DOCUMENT_ACQUISITION_FLG ");
        sb.append(",POINT_PURCHASE_FLG ");
        sb.append(",DM_NO_NEED_FLG ");
        sb.append(",OTHER1_FLG ");
        sb.append(",OTHER2_FLG ");
        sb.append(",OTHER3_FLG ");
        sb.append(",OTHER4_FLG ");
        sb.append(",GUARDIAN_FAMILY_JNM ");
        sb.append(",GUARDIAN_FIRST_JNM ");
        sb.append(",GUARDIAN_FAMILY_KNM ");
        sb.append(",GUARDIAN_FIRST_KNM ");
        sb.append(",GUARDIAN_FAMILY_RELATIONSHIP ");
        sb.append(",GUARDIAN_TEL1 ");
        sb.append(",GUARDIAN_TEL2 ");
        sb.append(",GUARDIAN_BIRTH_YYYY ");
        sb.append(",GUARDIAN_BIRTH_MM ");
        sb.append(",GUARDIAN_BIRTH_DD ");
        sb.append(",GUARDIAN_ZIP_CD ");
        sb.append(",GUARDIAN_ADDRESS_AREA_CD ");
        sb.append(",GUARDIAN_ADDRESS_PREFECTURE_CD ");
        sb.append(",GUARDIAN_ADDRESS_CITY ");
        sb.append(",GUARDIAN_ADDRESS_OTHERS ");
        sb.append(",GUARDIAN_GENDER_KBN ");
        sb.append(",GUARDIAN_MAIL_ADDRESS1 ");
        sb.append(",GUARDIAN_MAIL_ADDRESS2 ");
        sb.append(",GUARDIAN_MAIL_ADDRESS3 ");
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

            ps.setString(1, dto.getStudentId());
            ps.setString(2, dto.getFamilyJnm());
            ps.setString(3, dto.getFirstJnm());
            ps.setString(4, dto.getFamilyKnm());
            ps.setString(5, dto.getFirstKnm());
            ps.setString(6, dto.getFamilyRomaji());
            ps.setString(7, dto.getFirstRomaji());
            ps.setString(8, dto.getNickNm());
            ps.setString(9, dto.getPassword());
            ps.setString(10, dto.getTel1());
            ps.setString(11, dto.getTel2());
            ps.setString(12, dto.getBirthYyyy());
            ps.setString(13, dto.getBirthMm());
            ps.setString(14, dto.getBirthDd());
            ps.setString(15, dto.getZipCd());
            ps.setString(16, dto.getAddressAreaCd());
            ps.setString(17, dto.getAddressPrefectureCd());
            ps.setString(18, dto.getAddressCity());
            ps.setString(19, dto.getAddressOthers());
            ps.setString(20, dto.getGenderKbn());
            ps.setString(21, dto.getMailAddress1());
            ps.setString(22, dto.getMailAddress2());
            ps.setString(23, dto.getMailAddress3());
            ps.setString(24, String.valueOf(dto.getLoginNum()));
            ps.setString(25, dto.getEndLoginDt());
            ps.setString(26, dto.getOccupationCd());
            ps.setString(27, dto.getCustomerKbn());
            ps.setString(28, dto.getOrganizationNm());
            ps.setString(29, dto.getUseStrDt());
            ps.setString(30, dto.getUseEndDt());
            ps.setString(31, dto.getUseStopFlg());
            ps.setString(32, dto.getUseMotiveFlg1());
            ps.setString(33, dto.getUseMotiveFlg2());
            ps.setString(34, dto.getUseMotiveFlg3());
            ps.setString(35, dto.getUseMotiveFlg4());
            ps.setString(36, dto.getUseMotiveFlg5());
            ps.setString(37, dto.getUseMotive());
            ps.setString(38, dto.getAchievement());
            ps.setString(39, dto.getUseAgreementFlg());
            ps.setString(40, dto.getIndividualAgreementFlg());
            ps.setString(41, dto.getSchoolCmt());
            ps.setString(42, dto.getRemark());
            ps.setString(43, dto.getOrganizationId());
            ps.setString(44, dto.getPositionOrganizationId());
            ps.setString(45, dto.getStudentPosition());
            ps.setString(46, String.valueOf(dto.getBurdenNum()));
            ps.setString(47, dto.getConsentDocumentAcquisitionFlg());
            ps.setString(48, dto.getPointPurchaseFlg());
            ps.setString(49, String.valueOf(dto.getDmNoNeedFlg()));
            ps.setString(50, String.valueOf(dto.getOther1Flg()));
            ps.setString(51, String.valueOf(dto.getOther2Flg()));
            ps.setString(52, String.valueOf(dto.getOther3Flg()));
            ps.setString(53, String.valueOf(dto.getOther4Flg()));
            ps.setString(54, dto.getGuardianFamilyJnm());
            ps.setString(55, dto.getGuardianFirstJnm());
            ps.setString(56, dto.getGuardianFamilyKnm());
            ps.setString(57, dto.getGuardianFirstKnm());
            ps.setString(58, dto.getGuardianFamilyRelationship());
            ps.setString(59, dto.getGuardianTel1());
            ps.setString(60, dto.getGuardianTel2());
            ps.setString(61, dto.getGuardianBirthYyyy());
            ps.setString(62, dto.getGuardianBirthMm());
            ps.setString(63, dto.getGuardianBirthDd());
            ps.setString(64, dto.getGuardianZipCd());
            ps.setString(65, dto.getGuardianAddressAreaCd());
            ps.setString(66, dto.getGuardianAddressPrefectureCd());
            ps.setString(67, dto.getGuardianAddressCity());
            ps.setString(68, dto.getGuardianAddressOthers());
            ps.setString(69, dto.getGuardianGenderKbn());
            ps.setString(70, dto.getGuardianMailAddress1());
            ps.setString(71, dto.getGuardianMailAddress2());
            ps.setString(72, dto.getGuardianMailAddress3());
            ps.setString(73, String.valueOf(dto.getRecordVerNo()));
            ps.setString(74, String.valueOf(Timestamp.valueOf(sdf.format(cal.getTime()))));
            ps.setString(75, dto.getInsertCd());
            ps.setString(76, String.valueOf(Timestamp.valueOf(sdf.format(cal.getTime()))));
            ps.setString(77, dto.getUpdateCd());

            insertRowCount = ps.executeUpdate();

            return insertRowCount;

        } catch (SQLException se) {
            throw new NaiException(se);
        }
    }

    /**
     * 受講者マスタデータ取得<br>
     * <br>
     * 受講者マスタリストを戻り値で返却する<br>
     * <br>
     * @param conditions
     * @param orderConditions
     * @return StudentMstDto
     * @throws NaiException
     */
    public List<StudentMstDto> searchWithKnm(ConditionMapper conditions, OrderCondition orderConditions,
            StudentMstDto dto) throws NaiException {

        ResultSet res = null;

        // 受講者マスタの全項目取得
        StringBuffer sb = new StringBuffer();
        sb.append("select ");
        sb.append(" STUDENT_ID ");
        sb.append(",FAMILY_JNM ");
        sb.append(",FIRST_JNM ");
        sb.append(",FAMILY_KNM ");
        sb.append(",FIRST_KNM ");
        sb.append(",FAMILY_ROMAJI ");
        sb.append(",FIRST_ROMAJI ");
        sb.append(",NICK_NM ");
        sb.append(",PASSWORD ");
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
        sb.append(",LOGIN_NUM ");
        sb.append(",END_LOGIN_DT ");
        sb.append(",OCCUPATION_CD ");
        sb.append(",CUSTOMER_KBN ");
        sb.append(",ORGANIZATION_NM ");
        sb.append(",USE_STR_DT ");
        sb.append(",USE_END_DT ");
        sb.append(",USE_STOP_FLG ");
        sb.append(",USE_MOTIVE_FLG1 ");
        sb.append(",USE_MOTIVE_FLG2 ");
        sb.append(",USE_MOTIVE_FLG3 ");
        sb.append(",USE_MOTIVE_FLG4 ");
        sb.append(",USE_MOTIVE_FLG5 ");
        sb.append(",USE_MOTIVE ");
        sb.append(",ACHIEVEMENT ");
        sb.append(",USE_AGREEMENT_FLG ");
        sb.append(",INDIVIDUAL_AGREEMENT_FLG ");
        sb.append(",SCHOOL_CMT ");
        sb.append(",REMARK ");
        sb.append(",ORGANIZATION_ID ");
        sb.append(",POSITION_ORGANIZATION_ID ");
        sb.append(",STUDENT_POSITION ");
        sb.append(",BURDEN_NUM ");
        sb.append(",CONSENT_DOCUMENT_ACQUISITION_FLG ");
        sb.append(",POINT_PURCHASE_FLG ");
        sb.append(",DM_NO_NEED_FLG ");
        sb.append(",OTHER1_FLG ");
        sb.append(",OTHER2_FLG ");
        sb.append(",OTHER3_FLG ");
        sb.append(",OTHER4_FLG ");
        sb.append(",GUARDIAN_FAMILY_JNM ");
        sb.append(",GUARDIAN_FIRST_JNM ");
        sb.append(",GUARDIAN_FAMILY_KNM ");
        sb.append(",GUARDIAN_FIRST_KNM ");
        sb.append(",GUARDIAN_FAMILY_RELATIONSHIP ");
        sb.append(",GUARDIAN_TEL1 ");
        sb.append(",GUARDIAN_TEL2 ");
        sb.append(",GUARDIAN_BIRTH_YYYY ");
        sb.append(",GUARDIAN_BIRTH_MM ");
        sb.append(",GUARDIAN_BIRTH_DD ");
        sb.append(",GUARDIAN_ZIP_CD ");
        sb.append(",GUARDIAN_ADDRESS_AREA_CD ");
        sb.append(",GUARDIAN_ADDRESS_PREFECTURE_CD ");
        sb.append(",GUARDIAN_ADDRESS_CITY ");
        sb.append(",GUARDIAN_ADDRESS_OTHERS ");
        sb.append(",GUARDIAN_GENDER_KBN ");
        sb.append(",GUARDIAN_MAIL_ADDRESS1 ");
        sb.append(",GUARDIAN_MAIL_ADDRESS2 ");
        sb.append(",GUARDIAN_MAIL_ADDRESS3 ");
        sb.append(",RECORD_VER_NO ");
        sb.append(",INSERT_DTTM ");
        sb.append(",INSERT_CD ");
        sb.append(",UPDATE_DTTM ");
        sb.append(",UPDATE_CD ");
        sb.append(",RECORD_VER_NO ");
        sb.append(",INSERT_DTTM ");
        sb.append(",INSERT_CD ");
        sb.append(",UPDATE_DTTM ");
        sb.append(",UPDATE_CD ");
        sb.append("from ");
        sb.append(" STUDENT_MST ");
        // 抽出条件の設定
        if (!StringUtils.isEmpty(conditions.getConditionString())) {
            sb.append("where ");
            sb.append(conditions.getConditionString());

            // 受講者名(フリガナ)が入力されている場合
            if (!StringUtils.isEmpty(dto.getStudentNm())) {

                sb.append(" and (FAMILY_KNM like ? or FIRST_KNM like ?) ");
            }
        } else {

            // 受講者名(フリガナ)が入力されている場合
            if (!StringUtils.isEmpty(dto.getStudentNm())) {
                sb.append("where ");
                sb.append(" (FAMILY_KNM like ? or FIRST_KNM like ?) ");
            }
        }

        // 並び順の設定
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

            // 受講者名(フリガナ)が入力されている場合
            if (!StringUtils.isEmpty(dto.getStudentNm())) {

                ps.setString(i + 1,
                        new StringBuffer().append(dto.getStudentNm()).append(NaikaraTalkConstants.OPERATOR_PERCENT)
                                .toString());
                i++;
                ps.setString(i + 1,
                        new StringBuffer().append(dto.getStudentNm()).append(NaikaraTalkConstants.OPERATOR_PERCENT)
                                .toString());
            }

            res = ps.executeQuery();
            // コード管理マスタのキャッシュ読み込み
            CodeManagMstCache cache = CodeManagMstCache.getInstance();
            LinkedHashMap<String, CodeManagMstDto> genderKbnList = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_GENDER);
            LinkedHashMap<String, CodeManagMstDto> useStopFlgList = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_USE_STATE);
            LinkedHashMap<String, CodeManagMstDto> customerKbnList = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_CUSTOMER_KBN);

            ArrayList<StudentMstDto> list = new ArrayList<StudentMstDto>();

            while (res.next()) {

                StudentMstDto retDto = new StudentMstDto();

                retDto.setStudentId(res.getString("STUDENT_ID"));
                retDto.setFamilyJnm(res.getString("FAMILY_JNM"));
                retDto.setFirstJnm(res.getString("FIRST_JNM"));
                retDto.setFamilyKnm(res.getString("FAMILY_KNM"));
                retDto.setFirstKnm(res.getString("FIRST_KNM"));
                retDto.setFamilyRomaji(res.getString("FAMILY_ROMAJI"));
                retDto.setFirstRomaji(res.getString("FIRST_ROMAJI"));
                retDto.setNickNm(res.getString("NICK_NM"));
                retDto.setPassword(res.getString("PASSWORD"));
                retDto.setTel1(res.getString("TEL1"));
                retDto.setTel2(res.getString("TEL2"));
                retDto.setBirthYyyy(res.getString("BIRTH_YYYY"));
                retDto.setBirthMm(res.getString("BIRTH_MM"));
                retDto.setBirthDd(res.getString("BIRTH_DD"));
                retDto.setZipCd(res.getString("ZIP_CD"));
                retDto.setAddressAreaCd(res.getString("ADDRESS_AREA_CD"));
                retDto.setAddressPrefectureCd(res.getString("ADDRESS_PREFECTURE_CD"));
                retDto.setAddressCity(res.getString("ADDRESS_CITY"));
                retDto.setAddressOthers(res.getString("ADDRESS_OTHERS"));
                retDto.setGenderKbn(res.getString("GENDER_KBN"));

                if (StringUtils.isEmpty(res.getString("GENDER_KBN"))) {
                    retDto.setGenderKbnNm("");// 性別区分名
                } else {
                    retDto.setGenderKbnNm(genderKbnList.get(res.getString("GENDER_KBN")).getManagerNm());// 性別区分名
                }

                retDto.setMailAddress1(res.getString("MAIL_ADDRESS1"));
                retDto.setMailAddress2(res.getString("MAIL_ADDRESS2"));
                retDto.setMailAddress3(res.getString("MAIL_ADDRESS3"));
                retDto.setLoginNum(res.getInt("LOGIN_NUM"));
                retDto.setEndLoginDt(res.getString("END_LOGIN_DT"));
                retDto.setOccupationCd(res.getString("OCCUPATION_CD"));
                retDto.setCustomerKbn(res.getString("CUSTOMER_KBN"));

                if (StringUtils.isEmpty(res.getString("CUSTOMER_KBN"))) {
                    retDto.setCustomerKbnNm("");// 顧客区分名
                } else {
                    retDto.setCustomerKbnNm(customerKbnList.get(res.getString("CUSTOMER_KBN")).getManagerNm());// 顧客区分名
                }

                retDto.setOrganizationNm(res.getString("ORGANIZATION_NM"));
                retDto.setUseStrDt(res.getString("USE_STR_DT"));
                retDto.setUseEndDt(res.getString("USE_END_DT"));
                retDto.setUseStopFlg(res.getString("USE_STOP_FLG"));

                if (StringUtils.isEmpty(res.getString("USE_STOP_FLG"))) {
                    retDto.setUseStopFlgNm("");// 利用停止フラグ名
                } else {
                    retDto.setUseStopFlgNm(useStopFlgList.get(res.getString("USE_STOP_FLG")).getManagerNm());// 利用停止フラグ名
                }

                retDto.setUseMotiveFlg1(res.getString("USE_MOTIVE_FLG1"));
                retDto.setUseMotiveFlg2(res.getString("USE_MOTIVE_FLG2"));
                retDto.setUseMotiveFlg3(res.getString("USE_MOTIVE_FLG3"));
                retDto.setUseMotiveFlg4(res.getString("USE_MOTIVE_FLG4"));
                retDto.setUseMotiveFlg5(res.getString("USE_MOTIVE_FLG5"));
                retDto.setUseMotive(res.getString("USE_MOTIVE"));
                retDto.setAchievement(res.getString("ACHIEVEMENT"));
                retDto.setUseAgreementFlg(res.getString("USE_AGREEMENT_FLG"));
                retDto.setIndividualAgreementFlg(res.getString("INDIVIDUAL_AGREEMENT_FLG"));
                retDto.setSchoolCmt(res.getString("SCHOOL_CMT"));
                retDto.setRemark(res.getString("REMARK"));
                retDto.setOrganizationId(res.getString("ORGANIZATION_ID"));
                retDto.setPositionOrganizationId(res.getString("POSITION_ORGANIZATION_ID"));
                retDto.setStudentPosition(res.getString("STUDENT_POSITION"));
                retDto.setBurdenNum(res.getInt("BURDEN_NUM"));
                retDto.setConsentDocumentAcquisitionFlg(res.getString("CONSENT_DOCUMENT_ACQUISITION_FLG"));
                retDto.setPointPurchaseFlg(res.getString("POINT_PURCHASE_FLG"));
                retDto.setDmNoNeedFlg(res.getInt("DM_NO_NEED_FLG"));
                retDto.setOther1Flg(res.getInt("OTHER1_FLG"));
                retDto.setOther2Flg(res.getInt("OTHER2_FLG"));
                retDto.setOther3Flg(res.getInt("OTHER3_FLG"));
                retDto.setOther4Flg(res.getInt("OTHER4_FLG"));
                retDto.setGuardianFamilyJnm(res.getString("GUARDIAN_FAMILY_JNM"));
                retDto.setGuardianFirstJnm(res.getString("GUARDIAN_FIRST_JNM"));
                retDto.setGuardianFamilyKnm(res.getString("GUARDIAN_FAMILY_KNM"));
                retDto.setGuardianFirstKnm(res.getString("GUARDIAN_FIRST_KNM"));
                retDto.setGuardianFamilyRelationship(res.getString("GUARDIAN_FAMILY_RELATIONSHIP"));
                retDto.setGuardianTel1(res.getString("GUARDIAN_TEL1"));
                retDto.setGuardianTel2(res.getString("GUARDIAN_TEL2"));
                retDto.setGuardianBirthYyyy(res.getString("GUARDIAN_BIRTH_YYYY"));
                retDto.setGuardianBirthMm(res.getString("GUARDIAN_BIRTH_MM"));
                retDto.setGuardianBirthDd(res.getString("GUARDIAN_BIRTH_DD"));
                retDto.setGuardianZipCd(res.getString("GUARDIAN_ZIP_CD"));
                retDto.setGuardianAddressAreaCd(res.getString("GUARDIAN_ADDRESS_AREA_CD"));
                retDto.setGuardianAddressPrefectureCd(res.getString("GUARDIAN_ADDRESS_PREFECTURE_CD"));
                retDto.setGuardianAddressCity(res.getString("GUARDIAN_ADDRESS_CITY"));
                retDto.setGuardianAddressOthers(res.getString("GUARDIAN_ADDRESS_OTHERS"));
                retDto.setGuardianGenderKbn(res.getString("GUARDIAN_GENDER_KBN"));
                retDto.setGuardianMailAddress1(res.getString("GUARDIAN_MAIL_ADDRESS1"));
                retDto.setGuardianMailAddress2(res.getString("GUARDIAN_MAIL_ADDRESS2"));
                retDto.setGuardianMailAddress3(res.getString("GUARDIAN_MAIL_ADDRESS3"));
                retDto.setRecordVerNo(res.getInt("RECORD_VER_NO"));
                retDto.setInsertDttm(res.getTimestamp("INSERT_DTTM"));
                retDto.setInsertCd(res.getString("INSERT_CD"));
                retDto.setUpdateDttm(res.getTimestamp("UPDATE_DTTM"));
                retDto.setUpdateCd(res.getString("UPDATE_CD"));

                retDto.setStudentJnm(NaikaraStringUtil.unionName(retDto.getFamilyJnm(), retDto.getFirstJnm()));

                retDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);

                list.add(retDto);
            }

            if (list.size() < 1) {
                StudentMstDto retDto = new StudentMstDto();
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
     * 受講者マスタ更新処理<br>
     * <br>
     * 受講者マスタの更新を行う<br>
     * <br>
     * @param StudentMstDto
     * @return updateRowCount
     * @throws NaiException
     */
    public int update(StudentMstDto dto) throws NaiException {

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        int updateRowCount = NaikaraTalkConstants.RETURN_CD_ERR_UPD; // 更新データ件数

        // 受講者マスタのデータチェック
        if (!checkDto(dto)) {
            return updateRowCount;
        }

        StringBuffer sb = new StringBuffer();

        // データ更新処理
        sb.append("update ");
        sb.append("STUDENT_MST ");
        sb.append("set ");
        sb.append(" STUDENT_ID = ? ");
        sb.append(",FAMILY_JNM = ? ");
        sb.append(",FIRST_JNM = ? ");
        sb.append(",FAMILY_KNM = ? ");
        sb.append(",FIRST_KNM = ? ");
        sb.append(",FAMILY_ROMAJI = ? ");
        sb.append(",FIRST_ROMAJI = ? ");
        sb.append(",NICK_NM = ? ");
        sb.append(",PASSWORD = ? ");
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
        sb.append(",LOGIN_NUM = ? ");
        sb.append(",END_LOGIN_DT = ? ");
        sb.append(",OCCUPATION_CD = ? ");
        sb.append(",CUSTOMER_KBN = ? ");
        sb.append(",ORGANIZATION_NM = ? ");
        sb.append(",USE_STR_DT = ? ");
        sb.append(",USE_END_DT = ? ");
        sb.append(",USE_STOP_FLG = ? ");
        sb.append(",USE_MOTIVE_FLG1 = ? ");
        sb.append(",USE_MOTIVE_FLG2 = ? ");
        sb.append(",USE_MOTIVE_FLG3 = ? ");
        sb.append(",USE_MOTIVE_FLG4 = ? ");
        sb.append(",USE_MOTIVE_FLG5 = ? ");
        sb.append(",USE_MOTIVE = ? ");
        sb.append(",ACHIEVEMENT = ? ");
        sb.append(",USE_AGREEMENT_FLG = ? ");
        sb.append(",INDIVIDUAL_AGREEMENT_FLG = ? ");
        sb.append(",SCHOOL_CMT = ? ");
        sb.append(",REMARK = ? ");
        sb.append(",ORGANIZATION_ID = ? ");
        sb.append(",POSITION_ORGANIZATION_ID = ? ");
        sb.append(",STUDENT_POSITION = ? ");
        sb.append(",BURDEN_NUM = ? ");
        sb.append(",CONSENT_DOCUMENT_ACQUISITION_FLG = ? ");
        sb.append(",POINT_PURCHASE_FLG = ? ");
        sb.append(",DM_NO_NEED_FLG = ? ");
        sb.append(",OTHER1_FLG = ? ");
        sb.append(",OTHER2_FLG = ? ");
        sb.append(",OTHER3_FLG = ? ");
        sb.append(",OTHER4_FLG = ? ");
        sb.append(",GUARDIAN_FAMILY_JNM = ? ");
        sb.append(",GUARDIAN_FIRST_JNM = ? ");
        sb.append(",GUARDIAN_FAMILY_KNM = ? ");
        sb.append(",GUARDIAN_FIRST_KNM = ? ");
        sb.append(",GUARDIAN_FAMILY_RELATIONSHIP = ? ");
        sb.append(",GUARDIAN_TEL1 = ? ");
        sb.append(",GUARDIAN_TEL2 = ? ");
        sb.append(",GUARDIAN_BIRTH_YYYY = ? ");
        sb.append(",GUARDIAN_BIRTH_MM = ? ");
        sb.append(",GUARDIAN_BIRTH_DD = ? ");
        sb.append(",GUARDIAN_ZIP_CD = ? ");
        sb.append(",GUARDIAN_ADDRESS_AREA_CD = ? ");
        sb.append(",GUARDIAN_ADDRESS_PREFECTURE_CD = ? ");
        sb.append(",GUARDIAN_ADDRESS_CITY = ? ");
        sb.append(",GUARDIAN_ADDRESS_OTHERS = ? ");
        sb.append(",GUARDIAN_GENDER_KBN = ? ");
        sb.append(",GUARDIAN_MAIL_ADDRESS1 = ? ");
        sb.append(",GUARDIAN_MAIL_ADDRESS2 = ? ");
        sb.append(",GUARDIAN_MAIL_ADDRESS3 = ? ");
        sb.append(",RECORD_VER_NO = ? ");
        sb.append(",UPDATE_DTTM = ? ");
        sb.append(",UPDATE_CD = ? ");
        sb.append("where ");
        sb.append("STUDENT_ID = ? ");
        sb.append("and ");
        sb.append("RECORD_VER_NO = ? ");

        try {

            PreparedStatement ps = conn.prepareStatement(sb.toString());

            ps.setString(1, dto.getStudentId());
            ps.setString(2, dto.getFamilyJnm());
            ps.setString(3, dto.getFirstJnm());
            ps.setString(4, dto.getFamilyKnm());
            ps.setString(5, dto.getFirstKnm());
            ps.setString(6, dto.getFamilyRomaji());
            ps.setString(7, dto.getFirstRomaji());
            ps.setString(8, dto.getNickNm());
            ps.setString(9, dto.getPassword());
            ps.setString(10, dto.getTel1());
            ps.setString(11, dto.getTel2());
            ps.setString(12, dto.getBirthYyyy());
            ps.setString(13, dto.getBirthMm());
            ps.setString(14, dto.getBirthDd());
            ps.setString(15, dto.getZipCd());
            ps.setString(16, dto.getAddressAreaCd());
            ps.setString(17, dto.getAddressPrefectureCd());
            ps.setString(18, dto.getAddressCity());
            ps.setString(19, dto.getAddressOthers());
            ps.setString(20, dto.getGenderKbn());
            ps.setString(21, dto.getMailAddress1());
            ps.setString(22, dto.getMailAddress2());
            ps.setString(23, dto.getMailAddress3());
            ps.setString(24, String.valueOf(dto.getLoginNum()));
            ps.setString(25, dto.getEndLoginDt());
            ps.setString(26, dto.getOccupationCd());
            ps.setString(27, dto.getCustomerKbn());
            ps.setString(28, dto.getOrganizationNm());
            ps.setString(29, dto.getUseStrDt());
            ps.setString(30, dto.getUseEndDt());
            ps.setString(31, dto.getUseStopFlg());
            ps.setString(32, dto.getUseMotiveFlg1());
            ps.setString(33, dto.getUseMotiveFlg2());
            ps.setString(34, dto.getUseMotiveFlg3());
            ps.setString(35, dto.getUseMotiveFlg4());
            ps.setString(36, dto.getUseMotiveFlg5());
            ps.setString(37, dto.getUseMotive());
            ps.setString(38, dto.getAchievement());
            ps.setString(39, dto.getUseAgreementFlg());
            ps.setString(40, dto.getIndividualAgreementFlg());
            ps.setString(41, dto.getSchoolCmt());
            ps.setString(42, dto.getRemark());
            ps.setString(43, dto.getOrganizationId());
            ps.setString(44, dto.getPositionOrganizationId());
            ps.setString(45, dto.getStudentPosition());
            ps.setString(46, String.valueOf(dto.getBurdenNum()));
            ps.setString(47, dto.getConsentDocumentAcquisitionFlg());
            ps.setString(48, dto.getPointPurchaseFlg());
            ps.setString(49, String.valueOf(dto.getDmNoNeedFlg()));
            ps.setString(50, String.valueOf(dto.getOther1Flg()));
            ps.setString(51, String.valueOf(dto.getOther2Flg()));
            ps.setString(52, String.valueOf(dto.getOther3Flg()));
            ps.setString(53, String.valueOf(dto.getOther4Flg()));
            ps.setString(54, dto.getGuardianFamilyJnm());
            ps.setString(55, dto.getGuardianFirstJnm());
            ps.setString(56, dto.getGuardianFamilyKnm());
            ps.setString(57, dto.getGuardianFirstKnm());
            ps.setString(58, dto.getGuardianFamilyRelationship());
            ps.setString(59, dto.getGuardianTel1());
            ps.setString(60, dto.getGuardianTel2());
            ps.setString(61, dto.getGuardianBirthYyyy());
            ps.setString(62, dto.getGuardianBirthMm());
            ps.setString(63, dto.getGuardianBirthDd());
            ps.setString(64, dto.getGuardianZipCd());
            ps.setString(65, dto.getGuardianAddressAreaCd());
            ps.setString(66, dto.getGuardianAddressPrefectureCd());
            ps.setString(67, dto.getGuardianAddressCity());
            ps.setString(68, dto.getGuardianAddressOthers());
            ps.setString(69, dto.getGuardianGenderKbn());
            ps.setString(70, dto.getGuardianMailAddress1());
            ps.setString(71, dto.getGuardianMailAddress2());
            ps.setString(72, dto.getGuardianMailAddress3());
            ps.setString(73, String.valueOf(dto.getRecordVerNo() + 1));
            ps.setString(74, String.valueOf(Timestamp.valueOf(sdf.format(cal.getTime()))));
            ps.setString(75, dto.getUpdateCd());
            ps.setString(76, dto.getStudentId());
            ps.setString(77, String.valueOf(dto.getRecordVerNo()));

            updateRowCount = ps.executeUpdate();

            return updateRowCount;

        } catch (SQLException se) {
            throw new NaiException(se);
        }
    }

    /**
     * 削除処理。<br>
     * <br>
     * @param StudentMstDto
     *            画面のパラメータ
     * @return int 削除レコード数
     * @throws NaiException
     */
    public int delete(StudentMstDto dto) throws NaiException {

        // 削除データ件数
        int deleteRowCount = NaikaraTalkConstants.RETURN_CD_ERR_DEL; // 削除データ件数

        // SQL文作成
        StringBuffer sb = new StringBuffer();
        sb.append(" delete from STUDENT_MST");
        sb.append(" where ");
        sb.append(" STUDENT_ID = ? ");
        sb.append(" and RECORD_VER_NO = ?");
        try {

            PreparedStatement ps = conn.prepareStatement(sb.toString());

            // 受講者ID
            ps.setString(1, dto.getStudentId());

            // レコードバージョン番号
            ps.setInt(2, dto.getRecordVerNo());

            deleteRowCount = ps.executeUpdate();

            return deleteRowCount;

        } catch (SQLException se) {
            throw new NaiException(se);
        }
    }

    /**
     * 受講者マスタ登録、更新データチェック<br>
     * <br>
     * 受講者マスタの登録、更新データをチェックし<br>
     * エラー有の場合は、falseを返す<br>
     * エラー無の場合は、trueを返す<br>
     * <br>
     * @param UserMstDto
     * @return boolean
     */
    private boolean checkDto(StudentMstDto dto) throws NaiException {

        // 受講者IDのチェック：getStudentId
        if (dto.getStudentId() == null || "".equals(dto.getStudentId())) {
            return false;
        }

        // 名前(姓)のチェック：getFamilyJnm
        if (dto.getFamilyJnm() == null || "".equals(dto.getFamilyJnm())) {
            return false;
        }

        // 名前(名)のチェック：getFirstJnm
        if (dto.getFirstJnm() == null || "".equals(dto.getFirstJnm())) {
            return false;
        }

        // パスワードのチェック：getPassword
        if (dto.getPassword() == null || "".equals(dto.getPassword())) {
            return false;
        }

        // 顧客区分のチェック：getCustomerKbn
        if (dto.getCustomerKbn() == null || "".equals(dto.getCustomerKbn())) {
            return false;
        }

        // 利用停止フラグのチェック：getUseStopFlg
        if (dto.getUseStopFlg() == null || "".equals(dto.getUseStopFlg())) {
            return false;
        }

        // 利用動機フラグ１のチェック：getUseMotiveFlg1
        if (dto.getUseMotiveFlg1() == null || "".equals(dto.getUseMotiveFlg1())) {
            return false;
        }

        // 利用動機フラグ２のチェック：getUseMotiveFlg2
        if (dto.getUseMotiveFlg2() == null || "".equals(dto.getUseMotiveFlg2())) {
            return false;
        }

        // 利用動機フラグ３のチェック：getUseMotiveFlg3
        if (dto.getUseMotiveFlg3() == null || "".equals(dto.getUseMotiveFlg3())) {
            return false;
        }

        // 利用動機フラグ４のチェック：getUseMotiveFlg4
        if (dto.getStudentId() == null || "".equals(dto.getStudentId())) {
            return false;
        }

        // 利用動機フラグ５のチェック：getUseMotiveFlg5
        if (dto.getUseMotiveFlg5() == null || "".equals(dto.getUseMotiveFlg5())) {
            return false;
        }

        // 利用規約に同意する：チェックフラグのチェック：getUseAgreementFlg
        if (dto.getUseAgreementFlg() == null || "".equals(dto.getUseAgreementFlg())) {
            return false;
        }

        // 個人情報の同意：チェックフラグのチェック：getIndividualAgreementFlg
        if (dto.getIndividualAgreementFlg() == null || "".equals(dto.getIndividualAgreementFlg())) {
            return false;
        }

        // 保護者の同意書の入手フラグのチェック：getConsentDocumentAcquisitionFlg
        if (dto.getConsentDocumentAcquisitionFlg() == null || "".equals(dto.getConsentDocumentAcquisitionFlg())) {
            return false;
        }

        // ポイント購入済フラグのチェック：getPointPurchaseFlg
        if (dto.getPointPurchaseFlg() == null || "".equals(dto.getPointPurchaseFlg())) {
            return false;
        }

        // 登録者コードのチェック：getInsertCd
        if (dto.getInsertCd() == null || "".equals(dto.getInsertCd())) {
            return false;
        }

        // 更新者コードのチェック：getUpdateCd
        if (dto.getUpdateCd() == null || "".equals(dto.getUpdateCd())) {
            return false;
        }

        return true;

    }

    /**
     * 更新処理。<br>
     * <br>
     * 更新処理。<br>
     * <br>
     * @param StudentMstDto 画面のパラメータ<br>
     * @return int 更新レコード数<br>
     * @exception NaiException
     */
    public int loginUpdate(StudentMstDto dto) throws NaiException {

        // システム日付
        String sysDate = NaikaraStringUtil.converToDDMMYY(DateUtil.getSysDate());

        int updatedRowCount = NaikaraTalkConstants.RETURN_CD_ERR_UPD; // 更新データ件数

        // SQL文作成
        StringBuffer sb = new StringBuffer();
        sb.append(" update STUDENT_MST");
        sb.append(" set ");
        sb.append(" LOGIN_NUM = LOGIN_NUM + 1");
        sb.append(" ,END_LOGIN_DT = ?");
        sb.append(" ,RECORD_VER_NO = RECORD_VER_NO + 1");

        sb.append(" ,UPDATE_DTTM = sysdate()");
        sb.append(" ,UPDATE_CD = ?");
        sb.append(" where ");
        sb.append(" STUDENT_ID = ? ");
        sb.append(" and RECORD_VER_NO = ?");

        PreparedStatement ps = null;
        try {

            ps = conn.prepareStatement(sb.toString());
            // 更新者コード
            ps.setString(1, sysDate);
            // 更新者コード
            ps.setString(2, dto.getStudentId());
            // ポイントコード
            ps.setString(3, dto.getStudentId());
            // レコードバージョン番号
            ps.setInt(4, dto.getRecordVerNo());
            // SQL文を実行
            updatedRowCount = ps.executeUpdate();

            return updatedRowCount;

        } catch (SQLException se) {
            throw new NaiException(se);
        }
    }

}
