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
import com.naikara_talk.dto.SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>共通部品 DAOクラス<br>
 * <b>クラス名称       :</b>予約申込ページDAOクラス<br>
 * <b>クラス概要       :</b>受講者がレッスンを予約登録、取消ができる。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/18 TECS 新規作成
 */
public class SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDao extends AbstractDao {

    /** レッスン日（講師予定予約テーブル）　条件項目　 */
    public static final String COND_SRT_LESSON_DT = "SRT.LESSON_DT";
    /** レッスン時刻コード（講師予定予約テーブル）　条件項目　 */
    public static final String COND_SRT_LESSON_TM_CD = "SRT.LESSON_TM_CD";
    /** ニックネーム（講師マスタ）　条件項目　 */
    public static final String COND_TM_NICK_ANM = "TM.NICK_ANM";
    /** 利用者ID（講師マスタ）　条件項目　 */
    public static final String COND_TM_USER_ID = "TM.USER_ID";
    /** コースコード（コースマスタ）　条件項目　 */
    public static final String COND_CM_COURSE_CD = "CM.COURSE_CD";
    /** コース名（コースマスタ）　条件項目　 */
    public static final String COND_CM_COURSE_JNM = "CM.COURSE_JNM";
    /** 大分類コード（コースマスタ）　条件項目　 */
    public static final String COND_CM_BIG_CLASSIFICATION_CD = "CM.BIG_CLASSIFICATION_CD";
    /** 中分類コード（コースマスタ）　条件項目　 */
    public static final String COND_CM_MIDDLE_CLASSIFICATION_CD = "CM.MIDDLE_CLASSIFICATION_CD";
    /** 小分類コード（コースマスタ）　条件項目　 */
    public static final String COND_CM_SMALL_CLASSIFICATION_CD = "CM.SMALL_CLASSIFICATION_CD";
    /** 予約区分（講師予定予約テーブル）　条件項目　 */
    public static final String COND_SRT_RESERVATION_KBN = "SRT.RESERVATION_KBN";
    /** 受講者ID（講師予定予約テーブル）　条件項目　 */
    public static final String COND_SRT_STUDENT_ID = "SRT.STUDENT_ID";
    /** 性別区分（利用者マスタ）　条件項目　 */
    public static final String COND_UM_GENDER_KBN = "UM.GENDER_KBN";
    /** 提供開始日（コースマスタ）　条件項目　 */
    public static final String COND_CM_USE_STR_DT = "CM.USE_STR_DT";
    /** 提供終了日（コースマスタ）　条件項目　 */
    public static final String COND_CM_USE_END_DT = "CM.USE_END_DT";
    /** 利用ポイント開始日（コース別利用ポイントマスタ）　条件項目　 */
    public static final String COND_CUPM_USE_POINT_STR_DT = "CUPM.USE_POINT_STR_DT";
    /** 利用ポイント終了日（コース別利用ポイントマスタ）　条件項目　 */
    public static final String COND_CUPM_USE_POINT_END_DT = "CUPM.USE_POINT_END_DT";

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDao(Connection con) {
        this.conn = con;
    }

    /**
     * 講師予定予約テーブル情報検索処理。<br>
     * <br>
     * 講師予定予約テーブル情報検索処理を行う。<br>
     * <br>
     * @param dto 予約申込ページDTO<br>
     * @param conditions 条件<br>
     * @param orderConditions 並び順<br>
     * @return List<SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDto> 予約申込ページDTOリスト<br>
     * @throws NaiException
     */
    public List<SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDto> search(
            SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDto dto, ConditionMapper conditions,
            OrderCondition orderConditions) throws NaiException {

        ResultSet res = null;

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT      SRT.TEACHER_ID ");                               // 講師予定予約テーブル．講師ID
        sb.append("           ,TM.USER_ID ");                                   // 講師マスタ．利用者ID
        sb.append("           ,SRT.LESSON_TM_CD ");                             // 講師予定予約テーブル．レッスン時刻コード
        sb.append("           ,TM.NICK_ANM ");                                  // 講師マスタ．ニックネーム
        sb.append("           ,TM.SELLING_POINT ");                             // 講師マスタ．セールスポイント(スクール記入)
        sb.append("           ,CM.BIG_CLASSIFICATION_CD");                      // コースマスタ．大分類コード
        sb.append("           ,CM.MIDDLE_CLASSIFICATION_CD");                   // コースマスタ．中分類コード
        sb.append("           ,CM.SMALL_CLASSIFICATION_CD");                    // コースマスタ．小分類コード
        sb.append("           ,CM.COURSE_JNM ");                                // コースマスタ．コース名
        sb.append("           ,CM.COURSE_ENM ");                                // コースマスタ．コース名(英語名)
        sb.append("           ,CUPM.USE_POINT ");                               // コース別利用ポイントマスタ．利用ポイント
        sb.append("           ,SRT.RESERVATION_KBN ");                          // 講師予定予約テーブル．予約区分
        sb.append("           ,SRT.RESERVATION_NO ");                           // 講師予定予約テーブル．予約No
        sb.append("           ,SRT.STUDENT_ID ");                               // 講師予定予約テーブル．受講者ID
        sb.append("           ,CM.COURSE_CD ");                                 // コースマスタ．コースコード
        sb.append("           ,SRT.RECORD_VER_NO AS SRT_RECORD_VER_NO ");       // 講師予定予約テーブル．レコードバージョン番号
        sb.append("           ,LRPT.RECORD_VER_NO AS LRPT_RECORD_VER_NO ");     // レッスン予実テーブル．レコードバージョン番号
        sb.append("FROM       SCHEDULE_RESERVATION_TRN SRT ");                  // 講師予定予約テーブル
        sb.append("INNER JOIN TEACHER_MST TM ");                                // 講師マスタ
        sb.append("        ON SRT.TEACHER_ID = TM.USER_ID ");                   // 講師予定予約テーブル．講師ID ＝ 講師マスタ．利用者ID
        sb.append("INNER JOIN USER_MST UM ");                                   // 利用者マスタ
        sb.append("        ON SRT.TEACHER_ID = UM.USER_ID ");                   // 講師予定予約テーブル．講師ID ＝ 利用者マスタ．利用者ID
        sb.append("INNER JOIN TEACHER_COURSE_MST TCM ");                        // 講師別コースマスタ
        sb.append("        ON SRT.TEACHER_ID = TCM.USER_ID ");                  // 講師予定予約テーブル．講師ID ＝ 講師別コースマスタ．講師ID (利用者ID)
        sb.append("LEFT  JOIN COURSE_MST CM ");                                 // コースマスタ
        sb.append("        ON TCM.COURSE_CD = CM.COURSE_CD ");                  // 講師別コースマスタ．コースコード 左結合 コースマスタ．コースコード
        sb.append("LEFT  JOIN COURSE_USE_POINT_MST CUPM ");                     // コース別利用ポイントマスタ
        sb.append("        ON CM.COURSE_CD = CUPM.COURSE_CD ");                 // コースマスタ．コースコード 左結合 コース別利用ポイントマスタ．コースコード
        sb.append("LEFT  JOIN LESSON_RESERVATION_PERFORMANCE_TRN LRPT ");       // レッスン予実テーブル
        sb.append("        ON SRT.RESERVATION_NO = LRPT.RESERVATION_NO ");      // 講師予定予約テーブル．予約No 左結合 レッスン予実テーブル．予約No

        // 抽出条件の設定
        if (!StringUtils.isEmpty(conditions.getConditionString())) {
            sb.append("WHERE ");
            sb.append(conditions.getConditionString());

            // 画面の｢予約状況｣ ＝ ”2”(予約済) の場合
            if (StringUtils.equals(dto.getReservationKbn(), NaikaraTalkConstants.RESERV_KBN_ALREADY)) {
                // 講師予定予約テーブル．コースコード ＝ コースマスタ．コースコード
                sb.append(" AND SRT.COURSE_CD = CM.COURSE_CD ");
                // 講師予定予約テーブル．コースコード ＝ 講師別コースマスタ．コースコード
                sb.append(" AND SRT.COURSE_CD = TCM.COURSE_CD ");
            }

            // 画面の｢予約状況｣ ＝ ”0000”(全て) の場合
            if (StringUtils.equals(dto.getReservationKbn(), NaikaraTalkConstants.CHOICE_ALL_ZERO)) {
                // 講師予定予約テーブル．予約区分 ＝ ”1” (予約受付可能)
                sb.append(" AND (SRT.RESERVATION_KBN = ? ");
                // 講師予定予約テーブル．予約区分 ＝ ”2” (予約済み)
                sb.append(" OR  (SRT.RESERVATION_KBN = ? ");
                // 講師予定予約テーブル．受講者ID ＝ ログイン情報：受講者ID
                sb.append(" AND SRT.STUDENT_ID = ? ");
                // 講師予定予約テーブル．コースコード ＝ コースマスタ．コースコード
                sb.append(" AND SRT.COURSE_CD = CM.COURSE_CD) ");
                sb.append(") ");
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

            // 画面の｢予約状況｣ ＝ ”0000”(全て) の場合
            if (StringUtils.equals(dto.getReservationKbn(), NaikaraTalkConstants.CHOICE_ALL_ZERO)) {

                // ユーザIDを取得
                String userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString()))
                        .getUserId();

                // ”1” (予約受付可能)
                ps.setString(i + 1, NaikaraTalkConstants.RESERV_KBN_YES);
                i++;
                // ”2” (予約済み)
                ps.setString(i + 1, NaikaraTalkConstants.RESERV_KBN_ALREADY);
                i++;
                // ログイン情報：受講者ID
                ps.setString(i + 1, userId);
            }

            // SQL文実行
            res = ps.executeQuery();

            // コード管理マスタのキャッシュ読み込み
            CodeManagMstCache cache = CodeManagMstCache.getInstance();
            LinkedHashMap<String, CodeManagMstDto> LessonTmList = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_LESSON_TM_S);

            ArrayList<SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDto> list = new ArrayList<SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDto>();

            SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDto retDto;

            while (res.next()) {

                retDto = new SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDto();

                retDto.setTeacherId(res.getString("TEACHER_ID"));
                retDto.setLessonTmCd(res.getString("LESSON_TM_CD"));
                if (LessonTmList.get(res.getString("LESSON_TM_CD")) == null) {
                    retDto.setLessonTm(NaikaraTalkConstants.BRANK);
                } else {
                    retDto.setLessonTm(LessonTmList.get(res.getString("LESSON_TM_CD")).getManagerNm());
                }
                retDto.setNickAnm(res.getString("NICK_ANM"));
                retDto.setSellingPoint(res.getString("SELLING_POINT"));
                retDto.setBigClassificationCd(res.getString("BIG_CLASSIFICATION_CD"));
                retDto.setMiddleClassificationCd(res.getString("MIDDLE_CLASSIFICATION_CD"));
                retDto.setSmallClassificationCd(res.getString("SMALL_CLASSIFICATION_CD"));
                retDto.setCourseJnm(res.getString("COURSE_JNM"));
                retDto.setCourseEnm(res.getString("COURSE_ENM"));
                retDto.setCoursePoint(res.getString("USE_POINT"));
                retDto.setReservationKbn(res.getString("RESERVATION_KBN"));
                retDto.setReservationNo(res.getString("RESERVATION_NO"));
                retDto.setStudentId(res.getString("STUDENT_ID"));
                retDto.setCourseCd(res.getString("COURSE_CD"));
                retDto.setRecordVerNo1(res.getInt("SRT_RECORD_VER_NO"));
                retDto.setRecordVerNo2(res.getInt("LRPT_RECORD_VER_NO"));

                retDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);

                list.add(retDto);
            }

            if (list.size() < 1) {
                list = new ArrayList<SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDto>();
                retDto = new SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDto();
                retDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_NO);
                list.add(retDto);
                return list;
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

}
