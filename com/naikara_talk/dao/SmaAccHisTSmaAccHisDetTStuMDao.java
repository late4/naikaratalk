package com.naikara_talk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dbutil.QueryCondition;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.SmaAccHisTSmaAccHisDetTStuMDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>顧客管理 DAOクラス<br>
 * <b>クラス名称　　　:</b>スクールメール・アカウント変更履歴明細テーブル、スクールメール・アカウント変更履歴テーブル、受講者マスタデータアクセスクラス<br>
 * <b>クラス概要　　　:</b>スクールメール・アカウント変更履歴明細テーブル、スクールメール・アカウント変更履歴テーブル、受講者マスタの取得を行う<br>
 * <br>
 * <b>著作権　　　　　:</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/12/16 TECS 新規作成
 */
public class SmaAccHisTSmaAccHisDetTStuMDao extends AbstractDao {

    /** 送信日時 条件項目 並び順  */
    public static final String COND_SEND_DTTM = "SAHT.SEND_DTTM";
    /** メールパターンコード 条件項目 並び順  */
    public static final String COND_MAIL_PATTERN_CD = "SAHT.MAIL_PATTERN_CD";
    /** 受講者ID 条件項目 並び順 */
    public static final String COND_STUDENT_ID = "SAHT.STUDENT_ID";
    /** 顧客区分 条件項目 */
    public static final String COND_CUSTOMER_KBN = "SM.CUSTOMER_KBN";
    /** ニックネーム 条件項目 */
    public static final String COND_NICK_NM = "SM.NICK_NM";
    /** 組織名 条件項目 */
    public static final String COND_ORGANIZATION_NM = "SM.ORGANIZATION_NM";
    /** 組織ID  並び順　*/
    public static final String COND_ORGANIZATION_ID = "SM.ORGANIZATION_ID";
    /** 連番　並び順　*/
    public static final String COND_SEND_DTTM_SEQ = "SAHT.SEND_DTTM_SEQ";

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public SmaAccHisTSmaAccHisDetTStuMDao(Connection con) {
        this.conn = con;
    }

    /**
     * スクールメール・アカウント変更履歴明細テーブルデータ取得<br>
     * <br>
     * スクールメール・アカウント変更履歴明細テーブルリストを戻り値で返却する<br>
     * <br>
     * @param conditions
     * @param orderConditions
     * @return List<SmaAccHisTSmaAccHisDetTStuMDto>
     * @throws NaiException
     */
    public List<SmaAccHisTSmaAccHisDetTStuMDto> search(ConditionMapper conditions, OrderCondition orderConditions,
            SmaAccHisTSmaAccHisDetTStuMDto dto) throws NaiException {

        ResultSet res = null;

        StringBuffer sb = new StringBuffer();

        // 項目取得
        sb.append("select");
        sb.append(" SAHT.SEND_DTTM ");
        sb.append(",SAHT.MAIL_PATTERN_CD ");
        sb.append(",IFNULL(SM.CUSTOMER_KBN, '') AS CUSTOMER_KBN ");
        sb.append(",SAHT.STUDENT_ID ");
        sb.append(",IFNULL(SM.FAMILY_JNM, '') AS SM_FAMILY_JNM ");
        sb.append(",IFNULL(SM.FIRST_JNM, '') AS SM_FIRST_JNM ");
        sb.append(",IFNULL(SM.ORGANIZATION_NM, '') AS SM_ORGANIZATION_NM ");
        sb.append(",IFNULL(SM.NICK_NM, '') AS SM_NICK_NM ");

        // 組織マスタ
        sb.append(",IFNULL(OM.ORGANIZATION_JNM, '') AS OM_ORGANIZATION_JNM ");
        sb.append(",IFNULL(OM.MANAG_FAMILY_JNM, '') AS OM_MANAG_FAMILY_JNM ");
        sb.append(",IFNULL(OM.MANAG_FIRST_JNM, '') AS OM_MANAG_FIRST_JNM ");

        // 講師マスタ
        sb.append(",IFNULL(TM.FAMILY_JNM, '') AS TM_FAMILY_JNM ");
        sb.append(",IFNULL(TM.FIRST_JNM, '') AS TM_FIRST_JNM ");

        sb.append(",IFNULL(SAHDT.B_GUARDIAN_FAMILY_RELATIONSHIP, '') AS B_GUARDIAN_FAMILY_RELATIONSHIP ");
        sb.append(",IFNULL(SAHDT.B_NICK_NM, '') AS B_NICK_NM ");
        sb.append(",IFNULL(SAHDT.B_PASSWORD,'') AS B_PASSWORD ");
        sb.append(",IFNULL(SAHDT.B_FAMILY_KNM, '') AS B_FAMILY_KNM ");
        sb.append(",IFNULL(SAHDT.B_FIRST_KNM, '') AS B_FIRST_KNM ");
        sb.append(",IFNULL(SAHDT.B_MAIL_ADDRESS1, '') AS B_MAIL_ADDRESS1 ");
        sb.append(",IFNULL(SAHDT.B_MAIL_ADDRESS2, '') AS B_MAIL_ADDRESS2 ");
        sb.append(",IFNULL(SAHDT.B_MAIL_ADDRESS3, '') AS B_MAIL_ADDRESS3 ");
        sb.append(",IFNULL(SAHDT.B_FAMILY_ROMAJI, '') AS B_FAMILY_ROMAJI ");
        sb.append(",IFNULL(SAHDT.B_FIRST_ROMAJI, '') AS B_FIRST_ROMAJI ");
        sb.append(",IFNULL(SAHDT.B_ADDRESS_OTHERS, '') AS B_ADDRESS_OTHERS ");
        sb.append(",IFNULL(SAHDT.B_ADDRESS_CITY, '') AS B_ADDRESS_CITY ");
        sb.append(",IFNULL(SAHDT.B_ADDRESS_AREA_JNM, '') AS B_ADDRESS_AREA_JNM ");
        sb.append(",IFNULL(SAHDT.B_ADDRESS_PREFECTURE_JNM, '') AS B_ADDRESS_PREFECTURE_JNM ");
        sb.append(",IFNULL(SAHDT.B_GENDER_JNM, '') AS B_GENDER_JNM ");
        sb.append(",IFNULL(SAHDT.B_BIRTH_DT, '') AS B_BIRTH_DT ");
        sb.append(",IFNULL(SAHDT.B_TEL1, '') AS B_TEL1 ");
        sb.append(",IFNULL(SAHDT.B_TEL2, '') AS B_TEL2 ");
        sb.append(",IFNULL(SAHDT.B_GUARDIAN_FAMILY_KNM, '') AS B_GUARDIAN_FAMILY_KNM ");
        sb.append(",IFNULL(SAHDT.B_GUARDIAN_FIRST_KNM, '') AS B_GUARDIAN_FIRST_KNM ");
        sb.append(",IFNULL(SAHDT.B_GUARDIAN_MAIL_ADDRESS1, '') AS B_GUARDIAN_MAIL_ADDRESS1 ");
        sb.append(",IFNULL(SAHDT.B_GUARDIAN_MAIL_ADDRESS2, '') AS B_GUARDIAN_MAIL_ADDRESS2 ");
        sb.append(",IFNULL(SAHDT.B_GUARDIAN_MAIL_ADDRESS3, '') AS B_GUARDIAN_MAIL_ADDRESS3 ");
        sb.append(",IFNULL(SAHDT.B_GUARDIAN_ADDRESS_OTHERS, '') AS B_GUARDIAN_ADDRESS_OTHERS ");
        sb.append(",IFNULL(SAHDT.B_GUARDIAN_ADDRESS_CITY, '') AS B_GUARDIAN_ADDRESS_CITY ");
        sb.append(",IFNULL(SAHDT.B_GUARDIAN_ADDRESS_AREA_JNM, '') AS B_GUARDIAN_ADDRESS_AREA_JNM ");
        sb.append(",IFNULL(SAHDT.B_GUARDIAN_ADDRESS_PREFECTURE_JNM, '') AS B_GUARDIAN_ADDRESS_PREFECTURE_JNM ");
        sb.append(",IFNULL(SAHDT.B_GUARDIAN_GENDER_JNM, '') AS B_GUARDIAN_GENDER_JNM ");
        sb.append(",IFNULL(SAHDT.B_GUARDIAN_BIRTH_DT, '') AS B_GUARDIAN_BIRTH_DT ");
        sb.append(",IFNULL(SAHDT.B_GUARDIAN_TEL1, '') AS B_GUARDIAN_TEL1 ");
        sb.append(",IFNULL(SAHDT.B_GUARDIAN_TEL2, '') AS B_GUARDIAN_TEL2 ");
        sb.append(",IFNULL(SAHDT.B_GUARDIAN_FAMILY_JNM, '') AS B_GUARDIAN_FAMILY_JNM ");
        sb.append(",IFNULL(SAHDT.B_GUARDIAN_FIRST_JNM, '') AS B_GUARDIAN_FIRST_JNM ");
        sb.append(",IFNULL(SAHDT.B_GUARDIAN_ZIP_CD, '') AS B_GUARDIAN_ZIP_CD ");
        sb.append(",IFNULL(SAHDT.B_FAMILY_JNM, '') AS B_FAMILY_JNM ");
        sb.append(",IFNULL(SAHDT.B_FIRST_JNM, '') AS B_FIRST_JNM ");
        sb.append(",IFNULL(SAHDT.B_ZIP_CD, '') AS B_ZIP_CD ");
        sb.append(",IFNULL(SAHDT.A_GUARDIAN_FAMILY_RELATIONSHIP, '') AS A_GUARDIAN_FAMILY_RELATIONSHIP ");
        sb.append(",IFNULL(SAHDT.A_NICK_NM, '') AS A_NICK_NM ");
        sb.append(",IFNULL(SAHDT.A_PASSWORD, '') AS A_PASSWORD ");
        sb.append(",IFNULL(SAHDT.A_FAMILY_KNM, '') AS A_FAMILY_KNM ");
        sb.append(",IFNULL(SAHDT.A_FIRST_KNM, '') AS A_FIRST_KNM ");
        sb.append(",IFNULL(SAHDT.A_MAIL_ADDRESS1, '') AS A_MAIL_ADDRESS1 ");
        sb.append(",IFNULL(SAHDT.A_MAIL_ADDRESS2, '') AS A_MAIL_ADDRESS2 ");
        sb.append(",IFNULL(SAHDT.A_MAIL_ADDRESS3, '') AS A_MAIL_ADDRESS3 ");
        sb.append(",IFNULL(SAHDT.A_FAMILY_ROMAJI, '') AS A_FAMILY_ROMAJI ");
        sb.append(",IFNULL(SAHDT.A_FIRST_ROMAJI, '') AS A_FIRST_ROMAJI ");
        sb.append(",IFNULL(SAHDT.A_ADDRESS_OTHERS, '') AS A_ADDRESS_OTHERS ");
        sb.append(",IFNULL(SAHDT.A_ADDRESS_CITY, '') AS A_ADDRESS_CITY ");
        sb.append(",IFNULL(SAHDT.A_ADDRESS_AREA_JNM, '') AS A_ADDRESS_AREA_JNM ");
        sb.append(",IFNULL(SAHDT.A_ADDRESS_PREFECTURE_JNM, '') AS A_ADDRESS_PREFECTURE_JNM ");
        sb.append(",IFNULL(SAHDT.A_GENDER_JNM, '') AS A_GENDER_JNM ");
        sb.append(",IFNULL(SAHDT.A_BIRTH_DT, '') AS A_BIRTH_DT ");
        sb.append(",IFNULL(SAHDT.A_TEL1, '') AS A_TEL1 ");
        sb.append(",IFNULL(SAHDT.A_TEL2, '') AS A_TEL2 ");
        sb.append(",IFNULL(SAHDT.A_GUARDIAN_FAMILY_KNM, '') AS A_GUARDIAN_FAMILY_KNM ");
        sb.append(",IFNULL(SAHDT.A_GUARDIAN_FIRST_KNM, '') AS A_GUARDIAN_FIRST_KNM ");
        sb.append(",IFNULL(SAHDT.A_GUARDIAN_MAIL_ADDRESS1, '') AS A_GUARDIAN_MAIL_ADDRESS1 ");
        sb.append(",IFNULL(SAHDT.A_GUARDIAN_MAIL_ADDRESS2, '') AS A_GUARDIAN_MAIL_ADDRESS2 ");
        sb.append(",IFNULL(SAHDT.A_GUARDIAN_MAIL_ADDRESS3, '') AS A_GUARDIAN_MAIL_ADDRESS3 ");
        sb.append(",IFNULL(SAHDT.A_GUARDIAN_ADDRESS_OTHERS, '') AS A_GUARDIAN_ADDRESS_OTHERS ");
        sb.append(",IFNULL(SAHDT.A_GUARDIAN_ADDRESS_CITY, '') AS A_GUARDIAN_ADDRESS_CITY ");
        sb.append(",IFNULL(SAHDT.A_GUARDIAN_ADDRESS_AREA_JNM, '') AS A_GUARDIAN_ADDRESS_AREA_JNM ");
        sb.append(",IFNULL(SAHDT.A_GUARDIAN_ADDRESS_PREFECTURE_JNM, '') AS A_GUARDIAN_ADDRESS_PREFECTURE_JNM ");
        sb.append(",IFNULL(SAHDT.A_GUARDIAN_GENDER_JNM, '') AS A_GUARDIAN_GENDER_JNM ");
        sb.append(",IFNULL(SAHDT.A_GUARDIAN_BIRTH_DT, '') AS A_GUARDIAN_BIRTH_DT ");
        sb.append(",IFNULL(SAHDT.A_GUARDIAN_TEL1, '') AS A_GUARDIAN_TEL1 ");
        sb.append(",IFNULL(SAHDT.A_GUARDIAN_TEL2, '') AS A_GUARDIAN_TEL2 ");
        sb.append(",IFNULL(SAHDT.A_GUARDIAN_FAMILY_JNM, '') AS A_GUARDIAN_FAMILY_JNM ");
        sb.append(",IFNULL(SAHDT.A_GUARDIAN_FIRST_JNM, '') AS A_GUARDIAN_FIRST_JNM ");
        sb.append(",IFNULL(SAHDT.A_GUARDIAN_ZIP_CD, '') AS A_GUARDIAN_ZIP_CD ");
        sb.append(",IFNULL(SAHDT.A_FAMILY_JNM, '') AS A_FAMILY_JNM ");
        sb.append(",IFNULL(SAHDT.A_FIRST_JNM, '') AS A_FIRST_JNM ");
        sb.append(",IFNULL(SAHDT.A_ZIP_CD, '') AS A_ZIP_CD ");

        sb.append(" from ");
        sb.append(" SMAIL_ACCOUNT_HISTORY_TRN SAHT ");
        sb.append(" left join ");
        sb.append(" SMAIL_ACCOUNT_HISTORY_DETAILS_TRN SAHDT ");
        sb.append(" on SAHT.SEND_DTTM = SAHDT.SEND_DTTM ");
        sb.append(" and SAHT.SEND_DTTM_SEQ = SAHDT.SEND_DTTM_SEQ ");
        sb.append(" left join ");
        sb.append(" STUDENT_MST SM ");
        sb.append(" on SAHT.STUDENT_ID = SM.STUDENT_ID ");

        // 組織マスタ
        if (StringUtils.equals(NaikaraTalkConstants.CUSTOMER_KBN_ORGANIZATION_MANAG, dto.getCustomerKbn())) {
            // 法人責任者の場合
            sb.append(" inner join ");
        } else {
            // 法人責任者を除く場合
            sb.append(" left join ");
        }
        sb.append(" (select ");
        sb.append("      S1.SEND_DTTM ");
        sb.append("     ,S1.SEND_DTTM_SEQ ");
        sb.append("     ,O1.ORGANIZATION_ID ");
        sb.append("     ,O1.ORGANIZATION_JNM ");
        sb.append("     ,O1.MANAG_FAMILY_JNM ");
        sb.append("     ,O1.MANAG_FIRST_JNM ");
        sb.append("     ,O1.CONTRACT_STR_DT ");
        sb.append("     ,O1.CONTRACT_END_DT ");
        sb.append("  from SMAIL_ACCOUNT_HISTORY_TRN S1 ");
        sb.append("      left join ORGANIZATION_MST O1 on S1.STUDENT_ID = O1.ORGANIZATION_ID ");
        // 組織マスタ(限定の条件)
        sb.append("      where O1.CONTRACT_STR_DT <= DATE_FORMAT(S1.SEND_DTTM, '%Y%m%d') ");
        sb.append("        and O1.CONTRACT_END_DT >= DATE_FORMAT(S1.SEND_DTTM, '%Y%m%d') ");
        sb.append(" ) OM ");
        sb.append("  on SAHT.STUDENT_ID = OM.ORGANIZATION_ID ");
        sb.append("  and SAHT.SEND_DTTM = OM.SEND_DTTM ");
        sb.append("  and SAHT.SEND_DTTM_SEQ = OM.SEND_DTTM_SEQ ");

        // 利用者マスタ（講師）
        if (StringUtils.equals(NaikaraTalkConstants.CUSTOMER_KBN_TEACHER, dto.getCustomerKbn())) {
            // 講師の場合
            sb.append(" inner join ");
        } else {
            // 講師を除く場合
            sb.append(" left join ");
        }

        sb.append(" USER_MST TM ");
        sb.append(" on SAHT.STUDENT_ID = TM.USER_ID ");

        sb.append("where ");
        sb.append(" TO_DAYS(SAHT.SEND_DTTM) >= TO_DAYS(?) ");
        sb.append(" and TO_DAYS(SAHT.SEND_DTTM) <= TO_DAYS(?) ");


        if (!StringUtils.isEmpty(conditions.getConditionString())) {
            sb.append(" and ");
            sb.append(conditions.getConditionString());
        }

        // フリガナが入力されている場合
        if (!StringUtils.isEmpty(dto.getStudentKnm())) {

            sb.append(" and (SM.FAMILY_KNM like ? or SM.FIRST_KNM like ?) ");
        }

        if (!StringUtils.isEmpty(orderConditions.getOrderString())) {
            sb.append(orderConditions.getOrderString());
        }

        try {

            PreparedStatement ps = conn.prepareStatement(sb.toString());

            // パラメタの設定
            int i = 0;

            ps.setString(i + 1, dto.getMailSendDtFr());
            i++;
            ps.setString(i + 1, dto.getMailSendDtTo());
            i++;

            for (QueryCondition cond : conditions.getConditions()) {
                for (String val : cond.getValues()) {
                    ps.setString(i + 1, val);
                    i++;
                }
            }

            // フリガナが入力されている場合
            if (!StringUtils.isEmpty(dto.getStudentKnm())) {

                ps.setString(i + 1,
                        new StringBuffer().append(NaikaraTalkConstants.OPERATOR_PERCENT).append(dto.getStudentKnm())
                                .append(NaikaraTalkConstants.OPERATOR_PERCENT).toString());
                i++;
                ps.setString(i + 1,
                        new StringBuffer().append(NaikaraTalkConstants.OPERATOR_PERCENT).append(dto.getStudentKnm())
                                .append(NaikaraTalkConstants.OPERATOR_PERCENT).toString());
            }

            res = ps.executeQuery();

            // コード管理マスタのキャッシュ読み込み
            CodeManagMstCache cache = CodeManagMstCache.getInstance();
            LinkedHashMap<String, CodeManagMstDto> mailPatternNmList = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_TMAIL_PATTERN_NM);
            LinkedHashMap<String, CodeManagMstDto> categoryProductsGoodsKbnList = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_CUSTOMER_OTHER_KBN);

            List<SmaAccHisTSmaAccHisDetTStuMDto> list = new ArrayList<SmaAccHisTSmaAccHisDetTStuMDto>();

            while (res.next()) {

                SmaAccHisTSmaAccHisDetTStuMDto retDto = new SmaAccHisTSmaAccHisDetTStuMDto();

                // スクールメール・アカウント変更履歴テーブル
                retDto.setSendDttm(res.getTimestamp("SAHT.SEND_DTTM"));                                               // 送信日時
                retDto.setStudentId(res.getString("SAHT.STUDENT_ID"));                                                // 受講者ID
                retDto.setMailPatternCd(res.getString("SAHT.MAIL_PATTERN_CD"));                                       // メールパターンコード
                retDto.setMailPatternNm(mailPatternNmList.get(res.getString("SAHT.MAIL_PATTERN_CD")).getManagerNm()); // メールパターンコード名

                // 受講者マスタ
                String familyJnm = res.getString("SM_FAMILY_JNM");               // 名前(姓)
                String firstJnm = res.getString("SM_FIRST_JNM");                 // 名前(名)
                String organizationNm = res.getString("SM_ORGANIZATION_NM");     // 組織名
                String customerKbn = res.getString("CUSTOMER_KBN");              // 利用者区分(顧客区分+法人責任者+講師)

                if (StringUtils.isEmpty(familyJnm) && StringUtils.isEmpty(firstJnm)) {
                    // 組織マスタ（責任者名(姓)、責任者名(名)）
                    familyJnm = res.getString("OM_MANAG_FAMILY_JNM");
                    firstJnm = res.getString("OM_MANAG_FIRST_JNM");
                    organizationNm = res.getString("OM_ORGANIZATION_JNM");
                    customerKbn = NaikaraTalkConstants.CUSTOMER_KBN_ORGANIZATION_MANAG;
                    if (StringUtils.isEmpty(familyJnm) && StringUtils.isEmpty(firstJnm)) {
                        // 利用者マスタ（講師）（名前(姓)、名前(名)）
                        familyJnm = res.getString("TM_FAMILY_JNM");
                        firstJnm = res.getString("TM_FIRST_JNM");
                        organizationNm = NaikaraTalkConstants.BRANK;
                        customerKbn = NaikaraTalkConstants.CUSTOMER_KBN_TEACHER;
                    }
                }

                retDto.setFamilyJnm(familyJnm);                                                                       // 名前(姓)
                retDto.setFirstJnm(firstJnm);                                                                         // 名前(名)
                retDto.setOrganizationNm(organizationNm);                                                             // 組織名

                retDto.setCustomerKbn(customerKbn);                                                                   // 顧客区分
                if (!StringUtils.isEmpty(customerKbn)) {
                    retDto.setCustomerKbnNm(categoryProductsGoodsKbnList.get(customerKbn)
                            .getManagerNm());                                                                         // 顧客区分名
                }
                retDto.setNickNm(res.getString("SM_NICK_NM"));                                                        // ニックネーム



                // スクールメール・アカウント変更履歴明細テーブル
                retDto.setBGuardianFamilyRelationship(res.getString("B_GUARDIAN_FAMILY_RELATIONSHIP"));               // 変更前：あなたとの続柄
                retDto.setBNickNm(res.getString("B_NICK_NM"));                                                        // 変更前：ニックネーム
                retDto.setBPassword(res.getString("B_PASSWORD"));                                                     // 変更前：パスワード
                retDto.setBFamilyKnm(res.getString("B_FAMILY_KNM"));                                                  // 変更前：フリガナ(姓)
                retDto.setBFirstKnm(res.getString("B_FIRST_KNM"));                                                    // 変更前：フリガナ(名)
                retDto.setBMailAddress1(res.getString("B_MAIL_ADDRESS1"));                                            // 変更前：メールアドレス1
                retDto.setBMailAddress2(res.getString("B_MAIL_ADDRESS2"));                                            // 変更前：メールアドレス2
                retDto.setBMailAddress3(res.getString("B_MAIL_ADDRESS3"));                                            // 変更前：メールアドレス3
                retDto.setBFamilyRomaji(res.getString("B_FAMILY_ROMAJI"));                                            // 変更前：ローマ字(姓)
                retDto.setBFirstRomaji(res.getString("B_FIRST_ROMAJI"));                                              // 変更前：ローマ字(名)
                retDto.setBAddressOthers(res.getString("B_ADDRESS_OTHERS"));                                          // 変更前：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名　等)
                retDto.setBAddressCity(res.getString("B_ADDRESS_CITY"));                                              // 変更前：住所(市区町村　等)
                retDto.setBAddressAreaJnm(res.getString("B_ADDRESS_AREA_JNM"));                                       // 変更前：住所(地域)名
                retDto.setBAddressPrefectureJnm(res.getString("B_ADDRESS_PREFECTURE_JNM"));                           // 変更前：住所(都道府県)名
                retDto.setBGenderJnm(res.getString("B_GENDER_JNM"));                                                  // 変更前：性別名
                retDto.setBBirthDt(res.getString("B_BIRTH_DT"));                                                      // 変更前：生年月日
                retDto.setBTel1(res.getString("B_TEL1"));                                                             // 変更前：電話番号1
                retDto.setBTel2(res.getString("B_TEL2"));                                                             // 変更前：電話番号2
                retDto.setBGuardianFamilyKnm(res.getString("B_GUARDIAN_FAMILY_KNM"));                                 // 変更前：保護者：フリガナ(姓)
                retDto.setBGuardianFirstKnm(res.getString("B_GUARDIAN_FIRST_KNM"));                                   // 変更前：保護者：フリガナ(名）
                retDto.setBGuardianMailAddress1(res.getString("B_GUARDIAN_MAIL_ADDRESS1"));                           // 変更前：保護者：メールアドレス1
                retDto.setBGuardianMailAddress2(res.getString("B_GUARDIAN_MAIL_ADDRESS2"));                           // 変更前：保護者：メールアドレス2
                retDto.setBGuardianMailAddress3(res.getString("B_GUARDIAN_MAIL_ADDRESS3"));                           // 変更前：保護者：メールアドレス3
                retDto.setBGuardianAddressOthers(res.getString("B_GUARDIAN_ADDRESS_OTHERS"));                         // 変更前：保護者：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名　等)
                retDto.setBGuardianAddressCity(res.getString("B_GUARDIAN_ADDRESS_CITY"));                             // 変更前：保護者：住所(市区町村　等)
                retDto.setBGuardianAddressAreaJnm(res.getString("B_GUARDIAN_ADDRESS_AREA_JNM"));                      // 変更前：保護者：住所(地域)名
                retDto.setBGuardianAddressPrefectureJnm(res.getString("B_GUARDIAN_ADDRESS_PREFECTURE_JNM"));          // 変更前：保護者：住所(都道府県)名
                retDto.setBGuardianGenderJnm(res.getString("B_GUARDIAN_GENDER_JNM"));                                 // 変更前：保護者：性別名
                retDto.setBGuardianBirthDt(res.getString("B_GUARDIAN_BIRTH_DT"));                                     // 変更前：保護者：生年月日
                retDto.setBGuardianTel1(res.getString("B_GUARDIAN_TEL1"));                                            // 変更前：保護者：電話番号1
                retDto.setBGuardianTel2(res.getString("B_GUARDIAN_TEL2"));                                            // 変更前：保護者：電話番号2
                retDto.setBGuardianFamilyJnm(res.getString("B_GUARDIAN_FAMILY_JNM"));                                 // 変更前：保護者：名前(姓)
                retDto.setBGuardianFirstJnm(res.getString("B_GUARDIAN_FIRST_JNM"));                                   // 変更前：保護者：名前(名）
                retDto.setBGuardianZipCd(res.getString("B_GUARDIAN_ZIP_CD"));                                         // 変更前：保護者：郵便番号
                retDto.setBFamilyJnm(res.getString("B_FAMILY_JNM"));                                                  // 変更前：名前(姓)
                retDto.setBFirstJnm(res.getString("B_FIRST_JNM"));                                                    // 変更前：名前(名)
                retDto.setBZipCd(res.getString("B_ZIP_CD"));                                                          // 変更前：郵便番号
                retDto.setAGuardianFamilyRelationship(res.getString("A_GUARDIAN_FAMILY_RELATIONSHIP"));               // 変更後：あなたとの続柄
                retDto.setANickNm(res.getString("A_NICK_NM"));                                                        // 変更後：ニックネーム
                retDto.setAPassword(res.getString("A_PASSWORD"));                                                     // 変更後：パスワード
                retDto.setAFamilyKnm(res.getString("A_FAMILY_KNM"));                                                  // 変更後：フリガナ(姓)
                retDto.setAFirstKnm(res.getString("A_FIRST_KNM"));                                                    // 変更後：フリガナ(名)
                retDto.setAMailAddress1(res.getString("A_MAIL_ADDRESS1"));                                            // 変更後：メールアドレス1
                retDto.setAMailAddress2(res.getString("A_MAIL_ADDRESS2"));                                            // 変更後：メールアドレス2
                retDto.setAMailAddress3(res.getString("A_MAIL_ADDRESS3"));                                            // 変更後：メールアドレス3
                retDto.setAFamilyRomaji(res.getString("A_FAMILY_ROMAJI"));                                            // 変更後：ローマ字(姓)
                retDto.setAFirstRomaji(res.getString("A_FIRST_ROMAJI"));                                              // 変更後：ローマ字(名)
                retDto.setAAddressOthers(res.getString("A_ADDRESS_OTHERS"));                                          // 変更後：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名　等)
                retDto.setAAddressCity(res.getString("A_ADDRESS_CITY"));                                              // 変更後：住所(市区町村　等)
                retDto.setAAddressAreaJnm(res.getString("A_ADDRESS_AREA_JNM"));                                       // 変更後：住所(地域)名
                retDto.setAAddressPrefectureJnm(res.getString("A_ADDRESS_PREFECTURE_JNM"));                           // 変更後：住所(都道府県)名
                retDto.setAGenderJnm(res.getString("A_GENDER_JNM"));                                                  // 変更後：性別名
                retDto.setABirthDt(res.getString("A_BIRTH_DT"));                                                      // 変更後：生年月日
                retDto.setATel1(res.getString("A_TEL1"));                                                             // 変更後：電話番号1
                retDto.setATel2(res.getString("A_TEL2"));                                                             // 変更後：電話番号2
                retDto.setAGuardianFamilyKnm(res.getString("A_GUARDIAN_FAMILY_KNM"));                                 // 変更後：保護者：フリガナ(姓)
                retDto.setAGuardianFirstKnm(res.getString("A_GUARDIAN_FIRST_KNM"));                                   // 変更後：保護者：フリガナ(名）
                retDto.setAGuardianMailAddress1(res.getString("A_GUARDIAN_MAIL_ADDRESS1"));                           // 変更後：保護者：メールアドレス1
                retDto.setAGuardianMailAddress2(res.getString("A_GUARDIAN_MAIL_ADDRESS2"));                           // 変更後：保護者：メールアドレス2
                retDto.setAGuardianMailAddress3(res.getString("A_GUARDIAN_MAIL_ADDRESS3"));                           // 変更後：保護者：メールアドレス3
                retDto.setAGuardianAddressOthers(res.getString("A_GUARDIAN_ADDRESS_OTHERS"));                         // 変更後：保護者：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名　等)
                retDto.setAGuardianAddressCity(res.getString("A_GUARDIAN_ADDRESS_CITY"));                             // 変更後：保護者：住所(市区町村　等)
                retDto.setAGuardianAddressAreaJnm(res.getString("A_GUARDIAN_ADDRESS_AREA_JNM"));                      // 変更後：保護者：住所(地域)名
                retDto.setAGuardianAddressPrefectureJnm(res.getString("A_GUARDIAN_ADDRESS_PREFECTURE_JNM"));          // 変更後：保護者：住所(都道府県)名
                retDto.setAGuardianGenderJnm(res.getString("A_GUARDIAN_GENDER_JNM"));                                 // 変更後：保護者：性別名
                retDto.setAGuardianBirthDt(res.getString("A_GUARDIAN_BIRTH_DT"));                                     // 変更後：保護者：生年月日
                retDto.setAGuardianTel1(res.getString("A_GUARDIAN_TEL1"));                                            // 変更後：保護者：電話番号1
                retDto.setAGuardianTel2(res.getString("A_GUARDIAN_TEL2"));                                            // 変更後：保護者：電話番号2
                retDto.setAGuardianFamilyJnm(res.getString("A_GUARDIAN_FAMILY_JNM"));                                 // 変更後：保護者：名前(姓)
                retDto.setAGuardianFirstJnm(res.getString("A_GUARDIAN_FIRST_JNM"));                                   // 変更後：保護者：名前(名）
                retDto.setAGuardianZipCd(res.getString("A_GUARDIAN_ZIP_CD"));                                         // 変更後：保護者：郵便番号
                retDto.setAFamilyJnm(res.getString("A_FAMILY_JNM"));                                                  // 変更後：名前(姓)
                retDto.setAFirstJnm(res.getString("A_FIRST_JNM"));                                                    // 変更後：名前(名)
                retDto.setAZipCd(res.getString("A_ZIP_CD"));                                                          // 変更後：郵便番号

                retDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);

                list.add(retDto);
            }

            if (list.size() < 1) {
                SmaAccHisTSmaAccHisDetTStuMDto retDto = new SmaAccHisTSmaAccHisDetTStuMDto();
                retDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_NO);
                list.add(retDto);
            }

            return list;

        } catch (SQLException se) {
        	System.out.println(se.getMessage());
        	System.out.println(se.getErrorCode());
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
     * データ件数取得<br>
     * <br>
     * データ件数を戻り値で返却する<br>
     * <br>
     * @param conditions 検索条件<br>
     * @param dto 判定条件<br>
     * @return rowCount データ件数<br>
     * @exception NaiException
     */
    public int rowCount(ConditionMapper conditions, SmaAccHisTSmaAccHisDetTStuMDto dto) throws NaiException {

        // 戻り値の初期化
        int rowCount = NaikaraTalkConstants.RETURN_CD_DATA_ERR; // データ件数

        // データ件数の取得処理
        ResultSet res = null;

        StringBuffer sb = new StringBuffer();
        sb.append(" select ");
        sb.append(" COUNT(1) ");
        sb.append(" from ");
        sb.append(" SMAIL_ACCOUNT_HISTORY_TRN SAHT ");
        sb.append(" left join ");
        sb.append(" SMAIL_ACCOUNT_HISTORY_DETAILS_TRN SAHDT ");
        sb.append(" on SAHT.SEND_DTTM = SAHDT.SEND_DTTM ");
        sb.append(" and SAHT.SEND_DTTM_SEQ = SAHDT.SEND_DTTM_SEQ ");
        sb.append(" left join ");
        sb.append(" STUDENT_MST SM ");
        sb.append(" on SAHT.STUDENT_ID = SM.STUDENT_ID ");

        // 組織マスタ
        if (StringUtils.equals(NaikaraTalkConstants.CUSTOMER_KBN_ORGANIZATION_MANAG, dto.getCustomerKbn())) {
            // 法人責任者の場合
            sb.append(" inner join ");
        } else {
            // 法人責任者を除く場合
            sb.append(" left join ");
        }
        sb.append(" (select ");
        sb.append("      S1.SEND_DTTM ");
        sb.append("     ,S1.SEND_DTTM_SEQ ");
        sb.append("     ,O1.ORGANIZATION_ID ");
        sb.append("     ,O1.ORGANIZATION_JNM ");
        sb.append("     ,O1.MANAG_FAMILY_JNM ");
        sb.append("     ,O1.MANAG_FIRST_JNM ");
        sb.append("     ,O1.CONTRACT_STR_DT ");
        sb.append("     ,O1.CONTRACT_END_DT ");
        sb.append("  from SMAIL_ACCOUNT_HISTORY_TRN S1 ");
        sb.append("      left join ORGANIZATION_MST O1 on S1.STUDENT_ID = O1.ORGANIZATION_ID ");
        // 組織マスタ(限定の条件)
        sb.append("      where O1.CONTRACT_STR_DT <= DATE_FORMAT(S1.SEND_DTTM, '%Y%m%d') ");
        sb.append("        and O1.CONTRACT_END_DT >= DATE_FORMAT(S1.SEND_DTTM, '%Y%m%d') ");
        sb.append(" ) OM ");
        sb.append("   on SAHT.STUDENT_ID = OM.ORGANIZATION_ID ");
        sb.append("  and SAHT.SEND_DTTM = OM.SEND_DTTM ");
        sb.append("  and SAHT.SEND_DTTM_SEQ = OM.SEND_DTTM_SEQ ");

        // 利用者マスタ（講師）
        if (StringUtils.equals(NaikaraTalkConstants.CUSTOMER_KBN_TEACHER, dto.getCustomerKbn())) {
            // 講師の場合
            sb.append(" inner join ");
        } else {
            // 講師を除く場合
            sb.append(" left join ");
        }
        sb.append(" USER_MST TM ");
        sb.append(" on SAHT.STUDENT_ID = TM.USER_ID ");

        sb.append("where ");
        sb.append(" TO_DAYS(SAHT.SEND_DTTM) >= TO_DAYS(?) ");
        sb.append(" and TO_DAYS(SAHT.SEND_DTTM) <= TO_DAYS(?) ");

        if (!StringUtils.isEmpty(conditions.getConditionString())) {
            sb.append(" and ");
            sb.append(conditions.getConditionString());
        }
        // フリガナが入力されている場合
        if (!StringUtils.isEmpty(dto.getStudentKnm())) {

            sb.append(" and (SM.FAMILY_KNM like ? or SM.FIRST_KNM like ?) ");
        }

        try {
            PreparedStatement ps = conn.prepareStatement(sb.toString());

            // パラメタの設定
            int i = 0;

            ps.setString(i + 1, dto.getMailSendDtFr());
            i++;
            ps.setString(i + 1, dto.getMailSendDtTo());
            i++;

            for (QueryCondition cond : conditions.getConditions()) {
                for (String val : cond.getValues()) {
                    ps.setString(i + 1, val);
                    i++;
                }
            }

            // フリガナが入力されている場合
            if (!StringUtils.isEmpty(dto.getStudentKnm())) {

                ps.setString(i + 1,
                        new StringBuffer().append(NaikaraTalkConstants.OPERATOR_PERCENT).append(dto.getStudentKnm())
                                .append(NaikaraTalkConstants.OPERATOR_PERCENT).toString());
                i++;
                ps.setString(i + 1,
                        new StringBuffer().append(NaikaraTalkConstants.OPERATOR_PERCENT).append(dto.getStudentKnm())
                                .append(NaikaraTalkConstants.OPERATOR_PERCENT).toString());
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
}
