package com.naikara_talk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dbutil.QueryCondition;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.UserMstTeacherMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DAOクラス<br>
 * <b>クラス名称　　　:</b>利用者マスタ、講師マスタ、講師コースマスタ、コースマスタデータアクセスクラス<br>
 * <b>クラス概要　　　:</b>利用者マスタ、講師マスタ、講師コースマスタ、コースマスタの結合データを取得する<br>
 * <br>
 * <b>著作権　　　　　:</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴　　　　:</b>2014/04/22 TECS 新規作成
 *    　　　　　　　　:</b>2014/06/02 TECS DB不整合テスト対策の追加
 */
public class UMstTMstTCMstCMstDao extends AbstractDao {

    protected Logger log = Logger.getLogger(this.getClass());

    /** 利用者ID　条件項目　*/
    public static final String COND_USER_ID = "USER_MST.USER_ID";
    /** 種別区分　条件項目　*/
    public static final String COND_CLASSIFICATION_KBN = "USER_MST.CLASSIFICATION_KBN";
    /** ニックネーム　条件項目　 */
    public static final String COND_NICK_ANM = "TEACHER_MST.NICK_ANM";
    /** 性別区分　条件項目　 */
    public static final String COND_GENDER_KBN = "USER_MST.GENDER_KBN";
    /** 契約終了日　条件項目　 */
    public static final String COND_CONTRACT_END_DT = "TEACHER_MST.CONTRACT_END_DT";
    /** 名前(姓) 並び順　*/
    public static final String COND_FAMILY_JNM = "USER_MST.FAMILY_JNM";
    /** 名前(名) 並び順　*/
    public static final String COND_FIRST_JNM = "USER_MST.FIRST_JNM";


    /** コースマスタ：大分類　条件項目　*/
    public static final String COND_BIG_CLASSIFICATION_CD = "CM.BIG_CLASSIFICATION_CD";
    /** コースマスタ：中分類　条件項目　*/
    public static final String COND_MIDDLE_CLASSIFICATION_CD = "CM.MIDDLE_CLASSIFICATION_CD";
    /** コースマスタ：小分類　条件項目　*/
    public static final String COND_SMALL_CLASSIFICATION_CD = "CM.SMALL_CLASSIFICATION_CD";
    /** コースマスタ：コース名　条件項目　*/
    public static final String COND_COURSE_JNM = "CM.COURSE_JNM";


    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public UMstTMstTCMstCMstDao(Connection con) {
        this.conn = con;
    }

    /**
     * 利用者マスタ、受講者マスタデータ取得<br>
     * <br>
     * 利用者マスタ、受講者マスタリストを戻り値で返却する<br>
     * <br>
     * @param conditions
     * @param orderConditions
     * @return UserMstTeacherMstDto
     * @throws NaiException
     */
    public List<UserMstTeacherMstDto> search(ConditionMapper conditions, OrderCondition orderConditions)
            throws NaiException {

		ResultSet res = null;

        StringBuffer sb = new StringBuffer();
        StringBuffer column = new StringBuffer();

        // 「講師一覧」画面用のデータ取得処理
        sb.append("  select ");

        column.append(" USER_MST.USER_ID ");
        column.append(",USER_MST.PASSWORD ");
        column.append(",USER_MST.FAMILY_JNM ");
        column.append(",USER_MST.FIRST_JNM ");
        column.append(",USER_MST.FAMILY_KNM ");
        column.append(",USER_MST.FIRST_KNM ");
        column.append(",USER_MST.FAMILY_ROMAJI ");
        column.append(",USER_MST.FIRST_ROMAJI ");
        column.append(",USER_MST.TEL1 ");
        column.append(",USER_MST.TEL2 ");
        column.append(",USER_MST.BIRTH_YYYY ");
        column.append(",USER_MST.BIRTH_MM ");
        column.append(",USER_MST.BIRTH_DD ");
        column.append(",USER_MST.ZIP_CD ");
        column.append(",USER_MST.ADDRESS_AREA_CD ");
        column.append(",USER_MST.ADDRESS_PREFECTURE_CD ");
        column.append(",USER_MST.ADDRESS_CITY ");
        column.append(",USER_MST.ADDRESS_OTHERS ");
        column.append(",USER_MST.GENDER_KBN ");
        column.append(",USER_MST.MAIL_ADDRESS1 ");
        column.append(",USER_MST.MAIL_ADDRESS2 ");
        column.append(",USER_MST.MAIL_ADDRESS3 ");
        column.append(",USER_MST.USE_START_DT ");
        column.append(",USER_MST.USE_END_DT ");
        column.append(",USER_MST.CITY_TOWN ");
        column.append(",USER_MST.CLASSIFICATION_KBN ");
        column.append(",USER_MST.REMARK ");
        column.append(",USER_MST.RECORD_VER_NO ");
        column.append(",USER_MST.INSERT_DTTM ");
        column.append(",USER_MST.INSERT_CD ");
        column.append(",USER_MST.UPDATE_DTTM ");
        column.append(",USER_MST.UPDATE_CD ");
        column.append(",TEACHER_MST.USER_ID ");
        column.append(",TEACHER_MST.NICK_ANM ");
        column.append(",TEACHER_MST.NATIONALITY_CD ");
        column.append(",TEACHER_MST.NATIVE_LANG_CD ");
        column.append(",TEACHER_MST.COUNTRY_CD ");
        column.append(",TEACHER_MST.AREA_NO_CD ");
        column.append(",TEACHER_MST.CONTRACT_DT ");
        column.append(",TEACHER_MST.CONTRACT_START_DT ");
        column.append(",TEACHER_MST.CONTRACT_END_DT ");
        column.append(",TEACHER_MST.BANK_JPN_BANK_NM ");
        column.append(",TEACHER_MST.BANK_JPN_BRANCH_NM ");
        column.append(",TEACHER_MST.BANK_JPN_TYPE_OF_ACCOUNT ");
        column.append(",TEACHER_MST.BANK_JPN_ACCOUNT_HOLDERS_KNM ");
        column.append(",TEACHER_MST.BANK_JPN_ACCOUNT_HOLDERS_NM ");
        column.append(",TEACHER_MST.BANK_JPN_ACCOUNT_NUMBER ");
        column.append(",TEACHER_MST.BANK_JPN_ADDITIONAL_INFO ");
        column.append(",TEACHER_MST.JPN_PBANK_BRANCH_NM ");
        column.append(",TEACHER_MST.JPN_PBANK_TYPE_OF_ACCOUNT ");
        column.append(",TEACHER_MST.JPN_PBANK_ACCOUNT_HOLDERS_KNM ");
        column.append(",TEACHER_MST.JPN_PBANK_ACCOUNT_HOLDERS_NM ");
        column.append(",TEACHER_MST.JPN_PBANK_ACCOUNT_NUMBER ");
        column.append(",TEACHER_MST.JPN_PBANK_ADDITIONAL_INFO ");
        column.append(",TEACHER_MST.OVERSEA_ACCOUNT_H_NM ");
        column.append(",TEACHER_MST.OVERSEA_ACCOUNT_H_R_ADDRESS1 ");
        column.append(",TEACHER_MST.OVERSEA_ACCOUNT_H_R_ADDRESS2 ");
        column.append(",TEACHER_MST.OVERSEA_ACCOUNT_NUMBER_IBAN ");
        column.append(",TEACHER_MST.OVERSEA_ABANO_SWIFTCD_BICCD ");
        column.append(",TEACHER_MST.OVERSEA_ETC ");
        column.append(",TEACHER_MST.OVERSEA_BANK_NM ");
        column.append(",TEACHER_MST.OVERSEA_BRANCH_NM ");
        column.append(",TEACHER_MST.OVERSEA_BRANCH_ADDRESS1 ");
        column.append(",TEACHER_MST.OVERSEA_BRANCH_ADDRESS2 ");
        column.append(",TEACHER_MST.OVERSEA_COUNTRY_BRANCH_EXISTS ");
        column.append(",TEACHER_MST.OVERSEA_ADDITIONAL_INFO ");
        column.append(",TEACHER_MST.OTHER_REMITTANCE_FEE_KBN ");
        column.append(",TEACHER_MST.OVERSEA_PL_PAYPAL_ADDRESS ");
        column.append(",TEACHER_MST.OVERSEA_PL_ADDITIONAL_INFO ");
        column.append(",TEACHER_MST.SELLING_POINT ");
        column.append(",TEACHER_MST.SELF_RECOMMENDATION ");
        column.append(",TEACHER_MST.IMG_PHOTO_NM ");
        column.append(",TEACHER_MST.IMG_PHOTO ");
        column.append(",TEACHER_MST.EVALUATION_FROM_SCHOOL_CMT1 ");
        column.append(",TEACHER_MST.EVALUATION_FROM_SCHOOL_CMT2 ");
        column.append(",TEACHER_MST.LATEST_EVALUATION_FROM_STUDENT_CMT ");
        column.append(",TEACHER_MST.REMARK ");
        column.append(",TEACHER_MST.RECORD_VER_NO ");
        column.append(",TEACHER_MST.INSERT_DTTM ");
        column.append(",TEACHER_MST.INSERT_CD ");
        column.append(",TEACHER_MST.UPDATE_DTTM ");
        column.append(",TEACHER_MST.UPDATE_CD ");

        sb.append(column.toString());
        sb.append("  from TEACHER_MST ");                             // 講師マスタ
        sb.append("  left join USER_MST ");                           // 利用者マスタ
        sb.append("    on TEACHER_MST.USER_ID = USER_MST.USER_ID ");
        sb.append("  left join TEACHER_COURSE_MST TCM  ");            // 講師コースマスタ
        sb.append("    on TEACHER_MST.USER_ID = TCM.USER_ID ");
        sb.append("  left join COURSE_MST CM   ");                    // コースマスタ
        sb.append("    on TCM.COURSE_CD = CM.COURSE_CD ");

        // 抽出条件
        if (!StringUtils.isEmpty(conditions.getConditionString())) {
            sb.append("where ");
            sb.append(conditions.getConditionString());
        }

        // 集約条件
        sb.append("  group by ");
        sb.append(column.toString());

        // 並び順
        if (!StringUtils.isEmpty(orderConditions.getOrderString())) {
            sb.append(orderConditions.getOrderString());
        }

        try{

            PreparedStatement ps = conn.prepareStatement(sb.toString());

            // パラメタの設定
            int i = 0;
            for (QueryCondition cond : conditions.getConditions()) {
                for (String val : cond.getValues()) {
                    ps.setString(i + 1, val);
                    i++;
                }
            }

            // 実行
            res = ps.executeQuery();

            // コード管理マスタのキャッシュ読み込み
            CodeManagMstCache cache = CodeManagMstCache.getInstance();
            LinkedHashMap<String, CodeManagMstDto> genderKbnList = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_GENDER);

            ArrayList<UserMstTeacherMstDto> list = new ArrayList<UserMstTeacherMstDto>();

            while (res.next()) {

                // 2014/06/02 Add 基本的には発生しないがDB不整合テスト対策で追加 Start
                if (res.getString("USER_MST.USER_ID") == null) {
                    continue;
                }
                // 2014/06/02 Add 基本的には発生しないがDB不整合テスト対策で追加 End

                UserMstTeacherMstDto retDto = new UserMstTeacherMstDto();

                retDto.setUserId(res.getString("USER_MST.USER_ID"));
                retDto.setPassword(res.getString("USER_MST.PASSWORD"));
                retDto.setFamilyJnm(res.getString("USER_MST.FAMILY_JNM"));
                retDto.setFirstJnm(res.getString("USER_MST.FIRST_JNM"));
                retDto.setFamilyKnm(res.getString("USER_MST.FAMILY_KNM"));
                retDto.setFirstKnm(res.getString("USER_MST.FIRST_KNM"));
                retDto.setFamilyRomaji(res.getString("USER_MST.FAMILY_ROMAJI"));
                retDto.setFirstRomaji(res.getString("USER_MST.FIRST_ROMAJI"));
                retDto.setTel1(res.getString("USER_MST.TEL1"));
                retDto.setTel2(res.getString("USER_MST.TEL2"));
                retDto.setBirthYyyy(res.getString("USER_MST.BIRTH_YYYY"));
                retDto.setBirthMm(res.getString("USER_MST.BIRTH_MM"));
                retDto.setBirthDd(res.getString("USER_MST.BIRTH_DD"));
                retDto.setZipCd(res.getString("USER_MST.ZIP_CD"));
                retDto.setAddressAreaCd(res.getString("USER_MST.ADDRESS_AREA_CD"));
                retDto.setAddressPrefectureCd(res.getString("USER_MST.ADDRESS_PREFECTURE_CD"));
                retDto.setAddressCity(res.getString("USER_MST.ADDRESS_CITY"));
                retDto.setAddressOthers(res.getString("USER_MST.ADDRESS_OTHERS"));
                retDto.setGenderKbn(res.getString("USER_MST.GENDER_KBN"));

                retDto.setGenderKbnNm(genderKbnList.get(res.getString("USER_MST.GENDER_KBN")).getManagerNm());

                retDto.setMailAddress1(res.getString("USER_MST.MAIL_ADDRESS1"));
                retDto.setMailAddress2(res.getString("USER_MST.MAIL_ADDRESS2"));
                retDto.setMailAddress3(res.getString("USER_MST.MAIL_ADDRESS3"));
                retDto.setUseStartDt(res.getString("USER_MST.USE_START_DT"));
                retDto.setUseEndDt(res.getString("USER_MST.USE_END_DT"));
                retDto.setCityTown(res.getString("USER_MST.CITY_TOWN"));
                retDto.setClassificationKbn(res.getString("USER_MST.CLASSIFICATION_KBN"));
                retDto.setRemarkU(res.getString("USER_MST.REMARK"));
                retDto.setRecordVerNoU(res.getInt("USER_MST.RECORD_VER_NO"));
                retDto.setInsertDttmU(res.getTimestamp("USER_MST.INSERT_DTTM"));
                retDto.setInsertCdU(res.getString("USER_MST.INSERT_CD"));
                retDto.setUpdateDttmU(res.getTimestamp("USER_MST.UPDATE_DTTM"));
                retDto.setUpdateCdU(res.getString("USER_MST.UPDATE_CD"));

                retDto.setUserIdT(res.getString("TEACHER_MST.USER_ID"));
                retDto.setNickAnm(res.getString("TEACHER_MST.NICK_ANM"));
                retDto.setNationalityCd(res.getString("TEACHER_MST.NATIONALITY_CD"));
                retDto.setNativeLangCd(res.getString("TEACHER_MST.NATIVE_LANG_CD"));
                retDto.setCountryCd(res.getString("TEACHER_MST.COUNTRY_CD"));
                retDto.setAreaNoCd(res.getString("TEACHER_MST.AREA_NO_CD"));
                retDto.setContractDt(res.getString("TEACHER_MST.CONTRACT_DT"));
                retDto.setContractStartDt(res.getString("TEACHER_MST.CONTRACT_START_DT"));
                retDto.setContractEndDt(res.getString("TEACHER_MST.CONTRACT_END_DT"));
                retDto.setBankJpnBankNm(res.getString("TEACHER_MST.BANK_JPN_BANK_NM"));
                retDto.setBankJpnBranchNm(res.getString("TEACHER_MST.BANK_JPN_BRANCH_NM"));
                retDto.setBankJpnTypeOfAccount(res.getString("TEACHER_MST.BANK_JPN_TYPE_OF_ACCOUNT"));
                retDto.setBankJpnAccountHoldersKnm(res.getString("TEACHER_MST.BANK_JPN_ACCOUNT_HOLDERS_KNM"));
                retDto.setBankJpnAccountHoldersNm(res.getString("TEACHER_MST.BANK_JPN_ACCOUNT_HOLDERS_NM"));
                retDto.setBankJpnAccountNumber(res.getString("TEACHER_MST.BANK_JPN_ACCOUNT_NUMBER"));
                retDto.setBankJpnAdditionalInfo(res.getString("TEACHER_MST.BANK_JPN_ADDITIONAL_INFO"));
                retDto.setJpnPbankBranchNm(res.getString("TEACHER_MST.JPN_PBANK_BRANCH_NM"));
                retDto.setJpnPbankTypeOfAccount(res.getString("TEACHER_MST.JPN_PBANK_TYPE_OF_ACCOUNT"));
                retDto.setJpnPbankAccountHoldersKnm(res.getString("TEACHER_MST.JPN_PBANK_ACCOUNT_HOLDERS_KNM"));
                retDto.setJpnPbankAccountHoldersNm(res.getString("TEACHER_MST.JPN_PBANK_ACCOUNT_HOLDERS_NM"));
                retDto.setJpnPbankAccountNumber(res.getString("TEACHER_MST.JPN_PBANK_ACCOUNT_NUMBER"));
                retDto.setJpnPbankAdditionalInfo(res.getString("TEACHER_MST.JPN_PBANK_ADDITIONAL_INFO"));
                retDto.setOverseaAccountHNm(res.getString("TEACHER_MST.OVERSEA_ACCOUNT_H_NM"));
                retDto.setOverseaAccountHRAddress1(res.getString("TEACHER_MST.OVERSEA_ACCOUNT_H_R_ADDRESS1"));
                retDto.setOverseaAccountHRAddress2(res.getString("TEACHER_MST.OVERSEA_ACCOUNT_H_R_ADDRESS2"));
                retDto.setOverseaAccountNumberIban(res.getString("TEACHER_MST.OVERSEA_ACCOUNT_NUMBER_IBAN"));
                retDto.setOverseaAbanoSwiftcdBiccd(res.getString("TEACHER_MST.OVERSEA_ABANO_SWIFTCD_BICCD"));
                retDto.setOverseaEtc(res.getString("TEACHER_MST.OVERSEA_ETC"));
                retDto.setOverseaBankNm(res.getString("TEACHER_MST.OVERSEA_BANK_NM"));
                retDto.setOverseaBranchNm(res.getString("TEACHER_MST.OVERSEA_BRANCH_NM"));
                retDto.setOverseaBranchAddress1(res.getString("TEACHER_MST.OVERSEA_BRANCH_ADDRESS1"));
                retDto.setOverseaBranchAddress2(res.getString("TEACHER_MST.OVERSEA_BRANCH_ADDRESS2"));
                retDto.setOverseaCountryBranchExists(res.getString("TEACHER_MST.OVERSEA_COUNTRY_BRANCH_EXISTS"));
                retDto.setOverseaAdditionalInfo(res.getString("TEACHER_MST.OVERSEA_ADDITIONAL_INFO"));
                retDto.setOtherRemittanceFeeKbn(res.getString("TEACHER_MST.OTHER_REMITTANCE_FEE_KBN"));
                retDto.setOverseaPlPaypalAddress(res.getString("TEACHER_MST.OVERSEA_PL_PAYPAL_ADDRESS"));
                retDto.setOverseaPlAdditionalInfo(res.getString("TEACHER_MST.OVERSEA_PL_ADDITIONAL_INFO"));
                retDto.setSellingPoint(res.getString("TEACHER_MST.SELLING_POINT"));
                retDto.setSelfRecommendation(res.getString("TEACHER_MST.SELF_RECOMMENDATION"));
                retDto.setImgPhotoNm(res.getString("TEACHER_MST.IMG_PHOTO_NM"));
                retDto.setImgPhoto(res.getBlob("TEACHER_MST.IMG_PHOTO"));
                retDto.setEvaluationFromSchoolCmt1(res.getString("TEACHER_MST.EVALUATION_FROM_SCHOOL_CMT1"));
                retDto.setEvaluationFromSchoolCmt2(res.getString("TEACHER_MST.EVALUATION_FROM_SCHOOL_CMT2"));
                retDto.setLatestEvaluationFromStudentCmt(res
                        .getString("TEACHER_MST.LATEST_EVALUATION_FROM_STUDENT_CMT"));
                retDto.setRemarkT(res.getString("TEACHER_MST.REMARK"));
                retDto.setRecordVerNoT(res.getInt("TEACHER_MST.RECORD_VER_NO"));
                retDto.setInsertDttmT(res.getTimestamp("TEACHER_MST.INSERT_DTTM"));
                retDto.setInsertCdT(res.getString("TEACHER_MST.INSERT_CD"));
                retDto.setUpdateDttmT(res.getTimestamp("TEACHER_MST.UPDATE_DTTM"));
                retDto.setUpdateCdT(res.getString("TEACHER_MST.UPDATE_CD"));

                retDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);

                list.add(retDto);
            }

            if (list.size() < 1) {
                UserMstTeacherMstDto retDto = new UserMstTeacherMstDto();
                retDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_NO);
                list.add(retDto);
            }

            return list;

        } catch ( SQLException se ) {
            log.info(se);
            log.info(sb.toString());
            throw new NaiException(se);
        } finally {
            try {
                if ( res != null ) {
                    res.close();
                }
            } catch (SQLException e) {
                log.info(sb.toString());
                log.info(e);
                throw new NaiException(e);
            }
        }
    }


}
