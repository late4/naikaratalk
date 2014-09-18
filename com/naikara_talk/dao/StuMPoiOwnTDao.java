package com.naikara_talk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dbutil.QueryCondition;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.StuMPoiOwnTDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>DAOクラス<br>
 * <b>クラス名称　　　:</b>受講者マスタとポイント所有テーブルデータアクセスクラスクラス<br>
 * <b>クラス概要　　　:</b>受講者マスタとポイント所有テーブルの取得を行う<br>
 * <br>
 * <b>著作権　　　　　:</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/05/30 TECS 新規作成
 */
public class StuMPoiOwnTDao extends AbstractDao {

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
    /** 有効終了日  */
    public static final String COND_EFFECTIVE_END_DT = "EFFECTIVE_END_DT";

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public StuMPoiOwnTDao(Connection con) {
        this.conn = con;
    }

    /**
     * 受講者マスタとポイント所有テーブルデータ取得<br>
     * <br>
     * 受講者マスタとポイント所有テーブルデータリストを戻り値で返却する<br>
     * <br>
     * @param conditions
     * @param orderConditions
     * @return StudentMstDto
     * @throws NaiException
     */
    public ArrayList<StuMPoiOwnTDto> search(ConditionMapper conditions, OrderCondition orderConditions)
            throws NaiException {

        ArrayList<StuMPoiOwnTDto> list = null; // 受講者マスタとポイント所有テーブルマスタDTOリスト
        StuMPoiOwnTDto retDto = null; // 受講者マスタとポイント所有テーブルマスタDTO

        ResultSet res = null;

        // 受講者マスタの全項目取得
        StringBuffer sb = new StringBuffer();
        sb.append("select ");
        sb.append(" SM.STUDENT_ID STUDENT_ID ");
        sb.append(",SM.FAMILY_JNM FAMILY_JNM ");
        sb.append(",SM.FIRST_JNM FIRST_JNM ");
        sb.append(",SM.FAMILY_KNM FAMILY_KNM ");
        sb.append(",SM.FIRST_KNM FIRST_KNM ");
        sb.append(",SM.CUSTOMER_KBN CUSTOMER_KBN ");
        sb.append(",SM.MAIL_ADDRESS1 MAIL_ADDRESS1 ");
        sb.append(",SM.ADDRESS_PREFECTURE_CD ADDRESS_PREFECTURE_CD ");
        sb.append(",SM.GENDER_KBN GENDER_KBN ");
        sb.append(",SM.OCCUPATION_CD OCCUPATION_CD ");
        sb.append(",SM.ORGANIZATION_NM ORGANIZATION_NM ");
        sb.append(",ifNull(max(POT.EFFECTIVE_END_DT),'') MAX_EFFECTIVE_END_DT");
        sb.append(",ifNull(sum(POT.BALANCE_POINT),0) SUM_BALANCE_POINT");
        sb.append(" from ");
        sb.append(" STUDENT_MST SM");
        sb.append(" left join ");
        sb.append(" POINT_OWNERSHIP_TRN POT");
        sb.append(" on SM.STUDENT_ID = POT.STUDENT_ID");
        sb.append(" and POT.EFFECTIVE_END_DT >= ? ");

        // 抽出条件の設定
        if (!StringUtils.isEmpty(conditions.getConditionString())) {
            sb.append(" where ");
            sb.append(conditions.getConditionString());
        }

        sb.append(" group by STUDENT_ID ");

        // 並び順の設定
        if (!StringUtils.isEmpty(orderConditions.getOrderString())) {
            sb.append(orderConditions.getOrderString());
        }

        try {

            PreparedStatement ps = conn.prepareStatement(sb.toString());

            // パラメタの設定
            ps.setString(1, DateUtil.getSysDate());
            int i = 1;
            for (QueryCondition cond : conditions.getConditions()) {
                for (String val : cond.getValues()) {
                    ps.setString(i + 1, val);
                    i++;
                }
            }

            res = ps.executeQuery();

            // コード管理マスタのキャッシュ読み込み
            CodeManagMstCache cache = CodeManagMstCache.getInstance();
            LinkedHashMap<String, CodeManagMstDto> prefectureList = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_STATE);
            LinkedHashMap<String, CodeManagMstDto> genderList = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_GENDER);
            LinkedHashMap<String, CodeManagMstDto> occupationList = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_OCCUPATION);

            list = new ArrayList<StuMPoiOwnTDto>();

            while (res.next()) {

                retDto = new StuMPoiOwnTDto();

                retDto.setStudentId(res.getString("STUDENT_ID"));
                retDto.setFamilyJnm(res.getString("FAMILY_JNM"));
                retDto.setFirstJnm(res.getString("FIRST_JNM"));
                retDto.setFamilyKnm(res.getString("FAMILY_KNM"));
                retDto.setFirstKnm(res.getString("FIRST_KNM"));
                retDto.setCustomerKbn(res.getString("CUSTOMER_KBN"));
                retDto.setMailAddress1(res.getString("MAIL_ADDRESS1"));
                retDto.setAddressPrefectureCd(res.getString("ADDRESS_PREFECTURE_CD"));

                if (StringUtils.isEmpty(res.getString("ADDRESS_PREFECTURE_CD"))) {
                    retDto.setAddressPrefectureNm("");
                } else {
                    retDto.setAddressPrefectureNm(prefectureList.get(res.getString("ADDRESS_PREFECTURE_CD"))
                            .getManagerNm());
                }

                retDto.setGenderKbn(res.getString("GENDER_KBN"));
                if (StringUtils.isEmpty(res.getString("GENDER_KBN"))) {
                    retDto.setGenderKbnNm("");
                } else {
                    retDto.setGenderKbnNm(genderList.get(res.getString("GENDER_KBN")).getManagerNm());
                }

                retDto.setOrganizationNm(res.getString("ORGANIZATION_NM"));
                retDto.setOccupationCd(res.getString("OCCUPATION_CD"));

                if (StringUtils.isEmpty(res.getString("OCCUPATION_CD"))
                        || StringUtils.equals(res.getString("OCCUPATION_CD"), NaikaraTalkConstants.CHOICE_ALL_ZERO)) {
                    retDto.setOccupationNm("");
                } else {
                    retDto.setOccupationNm(occupationList.get(res.getString("OCCUPATION_CD")).getManagerNm());
                }

                retDto.setEffectiveEndDt(res.getString("MAX_EFFECTIVE_END_DT"));
                retDto.setBalancePoint(res.getString("SUM_BALANCE_POINT"));

                retDto.setReturnCode(0);

                list.add(retDto);
            }

            if (list.size() < 1) {
                list = new ArrayList<StuMPoiOwnTDto>();
                retDto = new StuMPoiOwnTDto();
                retDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_NO);
                list.add(retDto);
                return list;
            }

            return list;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new NaiException(e);
        } finally {
            try {
                res.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new NaiException(e);
            }
        }
    }
}
