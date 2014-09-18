package com.naikara_talk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dbutil.QueryCondition;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.TeaCouMTeaRateMCouUsePoiMDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>支払単価表<br>
 * <b>クラス名称       :</b>支払単価表初期処理Daoクラス。<br>
 * <b>クラス概要       :</b>支払単価表初期処理Dao。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/17 TECS 新規作成
 *                          2013/11/14 TECS 要望対応 No1022-6(講師支払比率99⇒99.999)
 */
public class TeaCouMTeaRateMCouUsePoiMDao extends AbstractDao {

    /** 利用者ID */
    public static final String COND_USER_ID = "TCM.USER_ID";
    /** コースコード */
    public static final String COND_COURSE_CD = "TCM.COURSE_CD";
    /** 利用ポイント開始日 */
    public static final String COND_USE_POINT_STR_DT = "CUPM.USE_POINT_STR_DT";
    /** 利用ポイント終了日 */
    public static final String COND_USE_POINT_END_DT = "CUPM.USE_POINT_END_DT";
    /** 適用期間：開始日 */
    public static final String COND_START_DT = "TRM.START_DT";
    /** 適用期間：終了日 */
    public static final String COND_END_DT = "TRM.END_DT";

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public TeaCouMTeaRateMCouUsePoiMDao(Connection con) {
        this.conn = con;
    }

    /**
     * 初期検索処理。<br>
     * <br>
     * @param conditions 検索条件
     * @param OrderBy 並び順
     * @return UserMstTeacherMstTeacherCourseMstDto 検索結果
     * @throws NaiException
     */
    public ArrayList<TeaCouMTeaRateMCouUsePoiMDto> search(ConditionMapper conditions, OrderCondition OrderBy)
            throws NaiException {

        //戻り値とリターンコードの初期化
        ArrayList<TeaCouMTeaRateMCouUsePoiMDto> list = null; //DTOリスト

        ResultSet res = null;

        // SQL文作成
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT  ");
        sb.append(" TCM.COURSE_CD, ");
        sb.append(" CT.BIG_CLASSIFICATION_CD, ");
        sb.append(" CT.MIDDLE_CLASSIFICATION_CD, ");
        sb.append(" CT.SMALL_CLASSIFICATION_CD, ");
        sb.append(" CT.COURSE_JNM, ");
        sb.append(" CT.COURSE_ENM, ");

        ////sb.append(" TRUNCATE((CUPM.USE_POINT * TRM.PAYMENT_RATE/100) + 0.5, 0) AS MUL_UPPR "); // 小数点第一位を四捨五入
        sb.append(" TRUNCATE(CUPM.USE_POINT * TRM.PAYMENT_RATE/100, 0) AS MUL_UPPR ");           // 切り捨て

        sb.append(" FROM TEACHER_COURSE_MST TCM ");
        sb.append(" INNER JOIN COURSE_MST CT ");
        sb.append(" ON CT.COURSE_CD = TCM.COURSE_CD ");
        sb.append(" INNER JOIN COURSE_USE_POINT_MST CUPM ");
        sb.append(" ON CUPM.COURSE_CD = TCM.COURSE_CD ");
        sb.append(" INNER JOIN TEACHER_RATE_MST TRM ");
        sb.append(" ON TRM.USER_ID = TCM.USER_ID ");
        if (!StringUtils.isEmpty(conditions.getConditionString())) {
            sb.append("where ");
            sb.append(conditions.getConditionString());
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
            //汎用フィールド名の取得
            CodeManagMstCache cache = CodeManagMstCache.getInstance();
            //大分類の名称一覧取得
            LinkedHashMap<String, CodeManagMstDto> workBigList = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_BIG_CLASSIFICATION);

            //中分類の名称一覧取得
            LinkedHashMap<String, CodeManagMstDto> workMidList = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_MIDDLE_CLASSIFICATION);

            //小分類の名称一覧取得
            LinkedHashMap<String, CodeManagMstDto> workSmoList = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_SMALL_CLASSIFICATION);

            //大分類(英語)の名称一覧取得
            LinkedHashMap<String, CodeManagMstDto> workBigListE = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_BIG_CLASSIFICATION_T);

            //中分類(英語)の名称一覧取得
            LinkedHashMap<String, CodeManagMstDto> workMidListE = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_MIDDLE_CLASSIFICATION_T);

            //小分類(英語)の名称一覧取得
            LinkedHashMap<String, CodeManagMstDto> workSmoListE = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_SMALL_CLASSIFICATION_T);

            // 戻りdto作成
            list = new ArrayList<TeaCouMTeaRateMCouUsePoiMDto>();
            while (res.next()) {
                TeaCouMTeaRateMCouUsePoiMDto retDto = new TeaCouMTeaRateMCouUsePoiMDto();

                retDto.setCourseCd(res.getString("COURSE_CD"));
                //日本語コース名
                retDto.setCourseJnm(NaikaraStringUtil.unionString4(
                        getName(workBigList, res.getString("BIG_CLASSIFICATION_CD")),
                        getName(workMidList, res.getString("MIDDLE_CLASSIFICATION_CD")),
                        getName(workSmoList, res.getString("SMALL_CLASSIFICATION_CD")), res.getString("COURSE_JNM")));
                //英語コース名
                retDto.setCourseEnm(NaikaraStringUtil.unionString4(
                        getName(workBigListE, res.getString("BIG_CLASSIFICATION_CD")),
                        getName(workMidListE, res.getString("MIDDLE_CLASSIFICATION_CD")),
                        getName(workSmoListE, res.getString("SMALL_CLASSIFICATION_CD")), res.getString("COURSE_ENM")));

                //1レッスン当たりの単価(円)
                retDto.setMulUppr(res.getBigDecimal("MUL_UPPR"));


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
     * 汎用コードから汎用フィールドを抽出する<br>
     * <br>
     * @param list LinkedHashMap
     * @param managerCd String
     * @return ManagerNm String
     */
    private String getName(LinkedHashMap<String, CodeManagMstDto> list, String managerCd) {

        CodeManagMstDto dto;
        dto = list.get(managerCd);
        return dto.getManagerNm();
    }
}
