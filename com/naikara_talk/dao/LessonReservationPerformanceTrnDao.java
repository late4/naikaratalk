package com.naikara_talk.dao;

import java.io.FileInputStream;
import java.io.IOException;
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
import com.naikara_talk.dto.LessonReservationPerformanceTrnDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.lessonservice.util.NaikaraLessonConstants;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DAOクラス<br>
 * <b>クラス名称　　　:</b>レッスン予実テーブルデータ取得クラス<br>
 * <b>クラス概要　　　:</b>レッスン予実テーブルデータ取得DAO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author MIRA/TECS
 * <b>変更履歴　　　　:</b>2013/05/31 MIRA/TECS 新規作成
 *                    :</b>2014/01/15 TECS      FlashからTokboxに変更対応
 *                    :</b>2014/04/22 TECS      レッスンファイルのコンテンツタイプの追加対応(pdf⇒pdf、txt)
 */
public class LessonReservationPerformanceTrnDao extends AbstractDao {

	/** TECS */
    /** 予約番号　条件項目　*/
    public static final String COND_RESERVATION_NO = "RESERVATION_NO";
    /** 状態区分　条件項目　*/
    public static final String COND_STATE_KBN = "STATE_KBN";
    /** 講師ID　条件項目　*/
    public static final String COND_TEACHER_ID = "TEACHER_ID";
    /** 受講者ID　条件項目　*/
    public static final String COND_STUDENT_ID = "STUDENT_ID";
    /** レッスン日　条件項目　*/
    public static final String COND_LESSON_DT = "LESSON_DT";
    /** レッスン時刻コード　条件項目　*/
    public static final String COND_LESSON_TM_CD = "LESSON_TM_CD";
    /** 添付メール送付済区分　条件項目　*/
    public static final String COND_MAIL_KBN = "MAIL_KBN";
    /** 添付メール送付日　条件項目　*/
    public static final String COND_MAIL_DT = "MAIL_DT";
    /** 売上計上済フラグ　条件項目　*/
    public static final String COND_SALES_ACCRUED_FLG = "SALES_ACCRUED_FLG";
    /** 受講者レッスン区分　条件項目　*/
    public static final String COND_STUDENT_LESSON_KBN = "STUDENT_LESSON_KBN";
    /** 講師レッスン区分　条件項目　*/
    public static final String COND_TEACHER_LESSON_KBN = "TEACHER_LESSON_KBN";
    /** 担当講師ニックネーム　条件項目　*/
    public static final String COND_TEACHER_NICK_NM = "TEACHER_NICK_NM";
    /** 受講者ニックネーム　条件項目　*/
    public static final String COND_STUDENT_NICK_NM = "STUDENT_NICK_NM";
    /** 大分類コード(英語)　条件項目　*/
    public static final String COND_BIG_CLASSIFICATION_ECD = "BIG_CLASSIFICATION_ECD";
    /** 中分類コード(英語)　条件項目　*/
    public static final String COND_MIDDLE_CLASSIFICATION_ECD = "MIDDLE_CLASSIFICATION_ECD";
    /** 小分類コード(英語)　条件項目　*/
    public static final String COND_SMALL_CLASSIFICATION_ECD = "SMALL_CLASSIFICATION_ECD";
    /** コース名　条件項目　*/
    public static final String COND_COURSE_JNM = "COURSE_JNM";
    /** コース名(英語)　条件項目　*/
    public static final String COND_COURSE_ENM = "COURSE_ENM";

    /** レッスン日　条件項目（月次バッチ用：6桁）*/
    public static final String COND_LESSON_DT6 = "SUBSTR(LESSON_DT, 1, 6)";

	/** 日次作成フラグ 条件項目 */
	public static final String COND_DAILYBATCH_FLG = "DAILYBATCH_FLG";



	/** MIRA */
	/** 予約番号　条件項目　*/
	public static final String RESERVATION_NO = "RESERVATION_NO";
	/** 講師ID　条件項目　*/
	public static final String TEACHER_ID = "TEACHER_ID";
	/** 生徒ID　条件項目　*/
	public static final String STUDENT_ID = "STUDENT_ID";

    /** 講師セッションID　条件項目　*/
    public static final String TEACHER_SESSION_ID = "TEACHER_SESSION_ID";
    /** 生徒セッションID　条件項目　*/
    public static final String STUDENT_SESSION_ID = "STUDENT_SESSION_ID";
    /** レコードバージョン番号	*/
    public static final String RECORD_VER_NO = "RECORD_VER_NO";
    /** レッスン日(YYYYMMDD)　条件項目　*/
    public static final String LESSON_DT = "LESSON_DT";
    /** レッスン完了時刻 */
    public static final String LESSON_END_DTTM = "LESSON_END_DTTM";

    /** 大分類コード　条件項目　*/
    public static final String COND_BIG_CLASSIFICATION_JCD = "BIG_CLASSIFICATION_JCD";
    /** 中分類コード　条件項目　*/
    public static final String COND_MIDDLE_CLASSIFICATION_JCD = "MIDDLE_CLASSIFICATION_JCD";
    /** 小分類コード　条件項目　*/
    public static final String COND_SMALL_CLASSIFICATION_JCD = "SMALL_CLASSIFICATION_JCD";

    /** コースコード　条件項目　*/
    public static final String COND_COURSE_CD = "COURSE_CD";

    /** レッスン時刻名　　条件項目　*/
    public static final String LESSON_TM_NM = "LESSON_TM_NM";

	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public LessonReservationPerformanceTrnDao(Connection con) {
		this.conn = con;
	}

	/**
     * レッスン予実テーブルデータ取得<br>
     * <br>
     * レッスン予実テーブルリストを戻り値で返却する<br>
     * <br>
     * @param conditions
     * @param OrderBy
     * @return ArrayList<LessonReservationPerformanceTrnDto>
     * @throws NaiException
     */
    public ArrayList<LessonReservationPerformanceTrnDto> search(ConditionMapper conditions, OrderCondition OrderBy)
            throws NaiException {

		//戻り値とリターンコードの初期化
		ArrayList<LessonReservationPerformanceTrnDto> list = null;  //レッスン予実テーブルDTOリスト
		LessonReservationPerformanceTrnDto dto = null;              //レッスン予実テーブルDTO

		ResultSet res = null;

		StringBuffer sb = new StringBuffer();

		//レッスン予実テーブルの全項目取得処理
		sb.append(" SELECT ");
		sb.append("  RESERVATION_NO");
		sb.append(" ,STATE_KBN");
		sb.append(" ,STUDENT_ID");
		sb.append(" ,STUDENT_NICK_NM");
		sb.append(" ,TEACHER_ID");
		sb.append(" ,TEACHER_NICK_NM");
		sb.append(" ,LESSON_DT");
		sb.append(" ,LESSON_TM_CD");
		sb.append(" ,LESSON_TM_NM");
		sb.append(" ,BIG_CLASSIFICATION_JCD");
		sb.append(" ,BIG_CLASSIFICATION_JNM");
		sb.append(" ,MIDDLE_CLASSIFICATION_JCD");
		sb.append(" ,MIDDLE_CLASSIFICATION_JNM");
		sb.append(" ,SMALL_CLASSIFICATION_JCD");
		sb.append(" ,SMALL_CLASSIFICATION_JNM");
		sb.append(" ,BIG_CLASSIFICATION_ECD");
		sb.append(" ,BIG_CLASSIFICATION_ENM");
		sb.append(" ,MIDDLE_CLASSIFICATION_ECD");
		sb.append(" ,MIDDLE_CLASSIFICATION_ENM");
		sb.append(" ,SMALL_CLASSIFICATION_ECD");
		sb.append(" ,SMALL_CLASSIFICATION_ENM");
		sb.append(" ,COURSE_CD");
		sb.append(" ,COURSE_JNM");
		sb.append(" ,COURSE_ENM");
		sb.append(" ,PAYMENT_USE_POINT");
		sb.append(" ,FREE_USE_POINT");
		sb.append(" ,USE_POINT_SUM");
		sb.append(" ,MAIL_KBN");
		sb.append(" ,MAIL_DT");
		sb.append(" ,SALES_ACCRUED_FLG");

		sb.append(" ,DAILYBATCH_FLG");

		sb.append(" ,ROOM_NO");
		sb.append(" ,TEACHER_SESSION_ID");
		sb.append(" ,STUDENT_SESSION_ID");
		sb.append(" ,STUDENT_IN_DTTM");
		sb.append(" ,STUDENT_START_DTTM");
		sb.append(" ,STUDENT_END_DTTM");
		sb.append(" ,STUDENT_LESSON_KBN");
		sb.append(" ,TEACHER_IN_DTTM");
		sb.append(" ,TEACHER_START_DTTM");
		sb.append(" ,TEACHER_END_DTTM");
		sb.append(" ,TEACHER_NEXT_DTTM");
		sb.append(" ,TEACHER_LESSON_KBN");
		sb.append(" ,LESSON_START_DTTM");
		sb.append(" ,LESSON_END_DTTM");
		// 2014/01/15 FlashからTokboxに変更対応 start
		sb.append(" ,TOKBOX_SESSION_ID");
		sb.append(" ,TOKBOX_TOKEN_ID");
		sb.append(" ,FILE_DURING_LESSONS");
		// 2014/01/15 FlashからTokboxに変更対応 end

		// 2014/04/22 Add Start ファイルタイプの追加対応(pdf⇒pdf、txt)
		sb.append(" ,FILE_CONTENT_TYPE");
		// 2014/04/22 Add End ファイルタイプの追加対応(pdf⇒pdf、txt)

		sb.append(" ,RECORD_VER_NO");
		sb.append(" ,INSERT_DTTM");
		sb.append(" ,INSERT_CD");
		sb.append(" ,UPDATE_DTTM");
		sb.append(" ,UPDATE_CD");
		sb.append(" FROM ");
		sb.append("   LESSON_RESERVATION_PERFORMANCE_TRN ");
		//抽出条件の設定
		if(!StringUtils.isEmpty(conditions.getConditionString())) {
			sb.append("where ");
			sb.append(conditions.getConditionString());
		}
		//並び順の設定
		if(!StringUtils.isEmpty(OrderBy.getOrderString())) {
			sb.append(OrderBy.getOrderString());
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

            // SQL文を実行
            res = ps.executeQuery();

            list = new ArrayList<LessonReservationPerformanceTrnDto>();
            while (res.next()) {

                dto = new LessonReservationPerformanceTrnDto();

                dto.setReservationNo(res.getString("RESERVATION_NO"));
                dto.setStateKbn(res.getString("STATE_KBN"));
                dto.setStudentId(res.getString("STUDENT_ID"));
                dto.setStudentNickNm(res.getString("STUDENT_NICK_NM"));
                dto.setTeacherId(res.getString("TEACHER_ID"));
                dto.setTeacherNickNm(res.getString("TEACHER_NICK_NM"));
                dto.setLessonDt(res.getString("LESSON_DT"));
                dto.setLessonTmCd(res.getString("LESSON_TM_CD"));
                dto.setLessonTmNm(res.getString("LESSON_TM_NM"));
                dto.setBigClassificationJcd(res.getString("BIG_CLASSIFICATION_JCD"));
                dto.setBigClassificationJnm(res.getString("BIG_CLASSIFICATION_JNM"));
                dto.setMiddleClassificationJcd(res.getString("MIDDLE_CLASSIFICATION_JCD"));
                dto.setMiddleClassificationJnm(res.getString("MIDDLE_CLASSIFICATION_JNM"));
                dto.setSmallClassificationJcd(res.getString("SMALL_CLASSIFICATION_JCD"));
                dto.setSmallClassificationJnm(res.getString("SMALL_CLASSIFICATION_JNM"));
                dto.setBigClassificationEcd(res.getString("BIG_CLASSIFICATION_ECD"));
                dto.setBigClassificationEnm(res.getString("BIG_CLASSIFICATION_ENM"));
                dto.setMiddleClassificationEcd(res.getString("MIDDLE_CLASSIFICATION_ECD"));
                dto.setMiddleClassificationEnm(res.getString("MIDDLE_CLASSIFICATION_ENM"));
                dto.setSmallClassificationEcd(res.getString("SMALL_CLASSIFICATION_ECD"));
                dto.setSmallClassificationEnm(res.getString("SMALL_CLASSIFICATION_ENM"));
                dto.setCourseCd(res.getString("COURSE_CD"));
                dto.setCourseJnm(res.getString("COURSE_JNM"));
                dto.setCourseEnm(res.getString("COURSE_ENM"));
                dto.setPaymentUsePoint(res.getBigDecimal("PAYMENT_USE_POINT"));
                dto.setFreeUsePoint(res.getBigDecimal("FREE_USE_POINT"));
                dto.setUsePointSum(res.getBigDecimal("USE_POINT_SUM"));
                dto.setMailKbn(res.getString("MAIL_KBN"));
                dto.setMailDt(res.getString("MAIL_DT"));
                dto.setSalesAccruedFlg(res.getString("SALES_ACCRUED_FLG"));

                dto.setDailyBatchFlg(res.getString("DAILYBATCH_FLG"));

                // 2014/01/15 FlashからTokboxに変更対応 start
                //dto.setRoomNo(res.getString("ROOM_NO"));
                // 2014/01/15 FlashからTokboxに変更対応 end

                dto.setTeacherSessionId(res.getString("TEACHER_SESSION_ID"));
                dto.setStudentSessionId(res.getString("STUDENT_SESSION_ID"));
                dto.setStudentInDttm(res.getTimestamp("STUDENT_IN_DTTM"));
                dto.setStudentStartDttm(res.getTimestamp("STUDENT_START_DTTM"));
                dto.setStudentEndDttm(res.getTimestamp("STUDENT_END_DTTM"));
                dto.setStudentLessonKbn(res.getString("STUDENT_LESSON_KBN"));
                dto.setTeacherInDttm(res.getTimestamp("TEACHER_IN_DTTM"));
                dto.setTeacherStartDttm(res.getTimestamp("TEACHER_START_DTTM"));
                dto.setTeacherEndDttm(res.getTimestamp("TEACHER_END_DTTM"));
                dto.setTeacherNextDttm(res.getTimestamp("TEACHER_NEXT_DTTM"));
                dto.setTeacherLessonKbn(res.getString("TEACHER_LESSON_KBN"));
                dto.setLessonStartDttm(res.getTimestamp("LESSON_START_DTTM"));
                dto.setLessonEndDttm(res.getTimestamp("LESSON_END_DTTM"));

                // 2014/01/15 FlashからTokboxに変更対応 start
                dto.setTokboxSessionId(res.getString("TOKBOX_SESSION_ID"));
                dto.setTokboxTokenId(res.getString("TOKBOX_TOKEN_ID"));
                dto.setFileDuringLessons(res.getBlob("FILE_DURING_LESSONS"));
                // 2014/01/15 FlashからTokboxに変更対応 end

                // 2014/04/22 Add Start ファイルタイプの追加対応(pdf⇒pdf、txt)
                dto.setLessonFileContentType(res.getString("FILE_CONTENT_TYPE"));
                // 2014/04/22 Add End ファイルタイプの追加対応(pdf⇒pdf、txt)


                dto.setRecordVerNo(res.getInt("RECORD_VER_NO"));
                dto.setInsertDttm(res.getTimestamp("INSERT_DTTM"));
                dto.setInsertCd(res.getString("INSERT_CD"));
                dto.setUpdateDttm(res.getTimestamp("UPDATE_DTTM"));
                dto.setUpdateCd(res.getString("UPDATE_CD"));

                dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);

                list.add(dto);
            }

            if (list.size() < 1) {
                list = new ArrayList<LessonReservationPerformanceTrnDto>();
                dto = new LessonReservationPerformanceTrnDto();
                dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_NO);
                list.add(dto);
                return list;
            }

            return list;


        } catch (SQLException se) {
            throw new NaiException(se);
        } finally {
        	try{
        		if ( res != null ) {
            		res.close();
        		}
        	}catch(SQLException se ){
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
    public int rowCount(String tableName, ConditionMapper conditions, LessonReservationPerformanceTrnDto dto)
            throws NaiException {

        // 戻り値の初期化
        int rowCount = -1; // データ件数

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

            // コース名が入力されている場合
            if (!StringUtils.isEmpty(dto.getCourseJnm())) {

                sb.append(" and (COURSE_JNM like ? or COURSE_ENM like ?) ");
            }

            sb.append(" and (STATE_KBN = ? or STATE_KBN = ?) ");
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

            // コース名が入力されている場合
            if (!StringUtils.isEmpty(dto.getCourseJnm())) {

                ps.setString(i + 1,
                        new StringBuffer().append(NaikaraTalkConstants.OPERATOR_PERCENT).append(dto.getCourseJnm())
                                .append(NaikaraTalkConstants.OPERATOR_PERCENT).toString());
                i++;
                ps.setString(i + 1,
                        new StringBuffer().append(NaikaraTalkConstants.OPERATOR_PERCENT).append(dto.getCourseJnm())
                                .append(NaikaraTalkConstants.OPERATOR_PERCENT).toString());
                i++;
            }

            ps.setString(i + 1, new StringBuffer().append("2").toString());
            i++;
            ps.setString(i + 1, new StringBuffer().append("3").toString());

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
    public int rowCountKbn(String tableName, ConditionMapper conditions, LessonReservationPerformanceTrnDto dto)
            throws NaiException {

        // 戻り値の初期化
        int rowCount = -1; // データ件数

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

            // コース名が入力されている場合
            sb.append(" and (STATE_KBN = ? or STATE_KBN = ?) ");
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

            // コース名が入力されている場合
            ps.setString(i + 1, new StringBuffer().append(NaikaraTalkConstants.LESSON_STATE_KBN_START).toString());
            i++;
            ps.setString(i + 1, new StringBuffer().append(NaikaraTalkConstants.LESSON_STATE_KBN_END).toString());

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
     * レッスン予実テーブルデータ取得<br>
     * <br>
     * レッスン予実テーブルリストを戻り値で返却する<br>
     * <br>
     * @param conditions 検索条件<br>
     * @param orderConditions 並び順条件<br>
     * @param lrptDto 判定条件<br>
     * @return list 検索結果<br>
     * @exception NaiException
     */
    public List<LessonReservationPerformanceTrnDto> searchWithOr(ConditionMapper conditions,
            OrderCondition orderConditions, LessonReservationPerformanceTrnDto lrptDto) throws NaiException {

        // 戻り値とリターンコードの初期化
        ArrayList<LessonReservationPerformanceTrnDto> list = null; // レッスン予実テーブルDTOリスト
        LessonReservationPerformanceTrnDto dto = null; // レッスン予実テーブルDTO

        // レッスン予実テーブルの全項目取得処理
        ResultSet res = null;

        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT ");
        sb.append("  RESERVATION_NO");
        sb.append(" ,STATE_KBN");
        sb.append(" ,STUDENT_ID");
        sb.append(" ,STUDENT_NICK_NM");
        sb.append(" ,TEACHER_ID");
        sb.append(" ,TEACHER_NICK_NM");
        sb.append(" ,LESSON_DT");
        sb.append(" ,LESSON_TM_CD");
        sb.append(" ,LESSON_TM_NM");
        sb.append(" ,BIG_CLASSIFICATION_JCD");
        sb.append(" ,BIG_CLASSIFICATION_JNM");
        sb.append(" ,MIDDLE_CLASSIFICATION_JCD");
        sb.append(" ,MIDDLE_CLASSIFICATION_JNM");
        sb.append(" ,SMALL_CLASSIFICATION_JCD");
        sb.append(" ,SMALL_CLASSIFICATION_JNM");
        sb.append(" ,BIG_CLASSIFICATION_ECD");
        sb.append(" ,BIG_CLASSIFICATION_ENM");
        sb.append(" ,MIDDLE_CLASSIFICATION_ECD");
        sb.append(" ,MIDDLE_CLASSIFICATION_ENM");
        sb.append(" ,SMALL_CLASSIFICATION_ECD");
        sb.append(" ,SMALL_CLASSIFICATION_ENM");
        sb.append(" ,COURSE_CD");
        sb.append(" ,COURSE_JNM");
        sb.append(" ,COURSE_ENM");
        sb.append(" ,PAYMENT_USE_POINT");
        sb.append(" ,FREE_USE_POINT");
        sb.append(" ,USE_POINT_SUM");
        sb.append(" ,MAIL_KBN");
        sb.append(" ,MAIL_DT");
        sb.append(" ,SALES_ACCRUED_FLG");

        sb.append(" ,DAILYBATCH_FLG");

        sb.append(" ,ROOM_NO");
        sb.append(" ,TEACHER_SESSION_ID");
        sb.append(" ,STUDENT_SESSION_ID");
        sb.append(" ,STUDENT_IN_DTTM");
        sb.append(" ,STUDENT_START_DTTM");
        sb.append(" ,STUDENT_END_DTTM");
        sb.append(" ,STUDENT_LESSON_KBN");
        sb.append(" ,TEACHER_IN_DTTM");
        sb.append(" ,TEACHER_START_DTTM");
        sb.append(" ,TEACHER_END_DTTM");
        sb.append(" ,TEACHER_NEXT_DTTM");
        sb.append(" ,TEACHER_LESSON_KBN");
        sb.append(" ,LESSON_START_DTTM");
        sb.append(" ,LESSON_END_DTTM");
		// 2014/01/15 FlashからTokboxに変更対応 start
		sb.append(" ,TOKBOX_SESSION_ID");
		sb.append(" ,TOKBOX_TOKEN_ID");
		sb.append(" ,FILE_DURING_LESSONS");
		// 2014/01/15 FlashからTokboxに変更対応 end
        sb.append(" ,RECORD_VER_NO");
        sb.append(" ,INSERT_DTTM");
        sb.append(" ,INSERT_CD");
        sb.append(" ,UPDATE_DTTM");
        sb.append(" ,UPDATE_CD");
        sb.append(" FROM ");
        sb.append("   LESSON_RESERVATION_PERFORMANCE_TRN ");
        // 抽出条件の設定
        if (!StringUtils.isEmpty(conditions.getConditionString())) {
            sb.append("where ");
            sb.append(conditions.getConditionString());

            // コース名が入力されている場合
            if (!StringUtils.isEmpty(lrptDto.getCourseJnm())) {

                sb.append(" and (COURSE_JNM like ? or COURSE_ENM like ?) ");
            }

            sb.append(" and (STATE_KBN = ? or STATE_KBN = ?) ");
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

            // コース名が入力されている場合
            if (!StringUtils.isEmpty(lrptDto.getCourseJnm())) {

                ps.setString(i + 1,
                        new StringBuffer().append(NaikaraTalkConstants.OPERATOR_PERCENT).append(lrptDto.getCourseJnm())
                                .append(NaikaraTalkConstants.OPERATOR_PERCENT).toString());
                i++;
                ps.setString(i + 1,
                        new StringBuffer().append(NaikaraTalkConstants.OPERATOR_PERCENT).append(lrptDto.getCourseJnm())
                                .append(NaikaraTalkConstants.OPERATOR_PERCENT).toString());
                i++;
            }

            ps.setString(i + 1, new StringBuffer().append(NaikaraTalkConstants.LESSON_STATE_KBN_START).toString());
            i++;
            ps.setString(i + 1, new StringBuffer().append(NaikaraTalkConstants.LESSON_STATE_KBN_END).toString());

            // SQL文を実行
            res = ps.executeQuery();

            list = new ArrayList<LessonReservationPerformanceTrnDto>();
            while (res.next()) {

                dto = new LessonReservationPerformanceTrnDto();

                dto.setReservationNo(res.getString("RESERVATION_NO"));
                dto.setStateKbn(res.getString("STATE_KBN"));
                dto.setStudentId(res.getString("STUDENT_ID"));
                dto.setStudentNickNm(res.getString("STUDENT_NICK_NM"));
                dto.setTeacherId(res.getString("TEACHER_ID"));
                dto.setTeacherNickNm(res.getString("TEACHER_NICK_NM"));
                dto.setLessonDt(res.getString("LESSON_DT"));
                dto.setLessonTmCd(res.getString("LESSON_TM_CD"));
                dto.setLessonTmNm(res.getString("LESSON_TM_NM"));
                dto.setBigClassificationJcd(res.getString("BIG_CLASSIFICATION_JCD"));
                dto.setBigClassificationJnm(res.getString("BIG_CLASSIFICATION_JNM"));
                dto.setMiddleClassificationJcd(res.getString("MIDDLE_CLASSIFICATION_JCD"));
                dto.setMiddleClassificationJnm(res.getString("MIDDLE_CLASSIFICATION_JNM"));
                dto.setSmallClassificationJcd(res.getString("SMALL_CLASSIFICATION_JCD"));
                dto.setSmallClassificationJnm(res.getString("SMALL_CLASSIFICATION_JNM"));
                dto.setBigClassificationEcd(res.getString("BIG_CLASSIFICATION_ECD"));
                dto.setBigClassificationEnm(res.getString("BIG_CLASSIFICATION_ENM"));
                dto.setMiddleClassificationEcd(res.getString("MIDDLE_CLASSIFICATION_ECD"));
                dto.setMiddleClassificationEnm(res.getString("MIDDLE_CLASSIFICATION_ENM"));
                dto.setSmallClassificationEcd(res.getString("SMALL_CLASSIFICATION_ECD"));
                dto.setSmallClassificationEnm(res.getString("SMALL_CLASSIFICATION_ENM"));
                dto.setCourseCd(res.getString("COURSE_CD"));
                dto.setCourseJnm(res.getString("COURSE_JNM"));
                dto.setCourseEnm(res.getString("COURSE_ENM"));
                dto.setPaymentUsePoint(res.getBigDecimal("PAYMENT_USE_POINT"));
                dto.setFreeUsePoint(res.getBigDecimal("FREE_USE_POINT"));
                dto.setUsePointSum(res.getBigDecimal("USE_POINT_SUM"));
                dto.setMailKbn(res.getString("MAIL_KBN"));
                dto.setMailDt(res.getString("MAIL_DT"));

                dto.setSalesAccruedFlg(res.getString("SALES_ACCRUED_FLG"));

                dto.setDailyBatchFlg(res.getString("DAILYBATCH_FLG"));

                // 2014/01/15 FlashからTokboxに変更対応 start
                //dto.setRoomNo(res.getString("ROOM_NO"));
                dto.setTokboxSessionId(res.getString("TOKBOX_SESSION_ID"));
                dto.setTokboxTokenId(res.getString("TOKBOX_TOKEN_ID"));
                dto.setFileDuringLessons(res.getBlob("FILE_DURING_LESSONS"));
                // 2014/01/15 FlashからTokboxに変更対応 end

                dto.setTeacherSessionId(res.getString("TEACHER_SESSION_ID"));
                dto.setStudentSessionId(res.getString("STUDENT_SESSION_ID"));
                dto.setStudentInDttm(res.getTimestamp("STUDENT_IN_DTTM"));
                dto.setStudentStartDttm(res.getTimestamp("STUDENT_START_DTTM"));
                dto.setStudentEndDttm(res.getTimestamp("STUDENT_END_DTTM"));
                dto.setStudentLessonKbn(res.getString("STUDENT_LESSON_KBN"));
                dto.setTeacherInDttm(res.getTimestamp("TEACHER_IN_DTTM"));
                dto.setTeacherStartDttm(res.getTimestamp("TEACHER_START_DTTM"));
                dto.setTeacherEndDttm(res.getTimestamp("TEACHER_END_DTTM"));
                dto.setTeacherNextDttm(res.getTimestamp("TEACHER_NEXT_DTTM"));
                dto.setTeacherLessonKbn(res.getString("TEACHER_LESSON_KBN"));
                dto.setLessonStartDttm(res.getTimestamp("LESSON_START_DTTM"));
                dto.setLessonEndDttm(res.getTimestamp("LESSON_END_DTTM"));
                dto.setRecordVerNo(res.getInt("RECORD_VER_NO"));
                dto.setInsertDttm(res.getTimestamp("INSERT_DTTM"));
                dto.setInsertCd(res.getString("INSERT_CD"));
                dto.setUpdateDttm(res.getTimestamp("UPDATE_DTTM"));
                dto.setUpdateCd(res.getString("UPDATE_CD"));

                dto.setReturnCode(0);

                list.add(dto);
            }

            if (list.size() < 1) {
                list = new ArrayList<LessonReservationPerformanceTrnDto>();
                dto = new LessonReservationPerformanceTrnDto();
                dto.setReturnCode(1);
                list.add(dto);
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
            } catch (SQLException se) {
                throw new NaiException(se);
            }
        }
    }

    /**
     * レッスン予実テーブルデータ取得<br>
     * <br>
     * レッスン予実テーブルリストを戻り値で返却する<br>
     * <br>
     * @param conditions
     * @param OrderBy
     * @return ArrayList<LessonReservationPerformanceTrnDto>
     * @throws NaiException
     */
    public ArrayList<LessonReservationPerformanceTrnDto> searchKbn(ConditionMapper conditions, OrderCondition OrderBy,
            LessonReservationPerformanceTrnDto lrptDto) throws NaiException {

        // 戻り値とリターンコードの初期化
        ArrayList<LessonReservationPerformanceTrnDto> list = null;  // レッスン予実テーブルDTOリスト
        LessonReservationPerformanceTrnDto dto = null;              // レッスン予実テーブルDTO

        // レッスン予実テーブルの全項目取得処理
        ResultSet res = null;

        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT ");
        sb.append("  RESERVATION_NO");
        sb.append(" ,STATE_KBN");
        sb.append(" ,STUDENT_ID");
        sb.append(" ,STUDENT_NICK_NM");
        sb.append(" ,TEACHER_ID");
        sb.append(" ,TEACHER_NICK_NM");
        sb.append(" ,LESSON_DT");
        sb.append(" ,LESSON_TM_CD");
        sb.append(" ,LESSON_TM_NM");
        sb.append(" ,BIG_CLASSIFICATION_JCD");
        sb.append(" ,BIG_CLASSIFICATION_JNM");
        sb.append(" ,MIDDLE_CLASSIFICATION_JCD");
        sb.append(" ,MIDDLE_CLASSIFICATION_JNM");
        sb.append(" ,SMALL_CLASSIFICATION_JCD");
        sb.append(" ,SMALL_CLASSIFICATION_JNM");
        sb.append(" ,BIG_CLASSIFICATION_ECD");
        sb.append(" ,BIG_CLASSIFICATION_ENM");
        sb.append(" ,MIDDLE_CLASSIFICATION_ECD");
        sb.append(" ,MIDDLE_CLASSIFICATION_ENM");
        sb.append(" ,SMALL_CLASSIFICATION_ECD");
        sb.append(" ,SMALL_CLASSIFICATION_ENM");
        sb.append(" ,COURSE_CD");
        sb.append(" ,COURSE_JNM");
        sb.append(" ,COURSE_ENM");
        sb.append(" ,PAYMENT_USE_POINT");
        sb.append(" ,FREE_USE_POINT");
        sb.append(" ,USE_POINT_SUM");
        sb.append(" ,MAIL_KBN");
        sb.append(" ,MAIL_DT");
        sb.append(" ,SALES_ACCRUED_FLG");

        sb.append(" ,DAILYBATCH_FLG");

        sb.append(" ,ROOM_NO");
        sb.append(" ,TEACHER_SESSION_ID");
        sb.append(" ,STUDENT_SESSION_ID");
        sb.append(" ,STUDENT_IN_DTTM");
        sb.append(" ,STUDENT_START_DTTM");
        sb.append(" ,STUDENT_END_DTTM");
        sb.append(" ,STUDENT_LESSON_KBN");
        sb.append(" ,TEACHER_IN_DTTM");
        sb.append(" ,TEACHER_START_DTTM");
        sb.append(" ,TEACHER_END_DTTM");
        sb.append(" ,TEACHER_NEXT_DTTM");
        sb.append(" ,TEACHER_LESSON_KBN");
        sb.append(" ,LESSON_START_DTTM");
        sb.append(" ,LESSON_END_DTTM");
		// 2014/01/15 FlashからTokboxに変更対応 start
		sb.append(" ,TOKBOX_SESSION_ID");
		sb.append(" ,TOKBOX_TOKEN_ID");
		sb.append(" ,FILE_DURING_LESSONS");
		// 2014/01/15 FlashからTokboxに変更対応 end
        sb.append(" ,RECORD_VER_NO");
        sb.append(" ,INSERT_DTTM");
        sb.append(" ,INSERT_CD");
        sb.append(" ,UPDATE_DTTM");
        sb.append(" ,UPDATE_CD");
        sb.append(" FROM ");
        sb.append("   LESSON_RESERVATION_PERFORMANCE_TRN ");
        // 抽出条件の設定
        if (!StringUtils.isEmpty(conditions.getConditionString())) {
            sb.append("where ");
            sb.append(conditions.getConditionString());

            // コース名が入力されている場合
            sb.append(" and (STATE_KBN = ? or STATE_KBN = ?) ");
        }
        // 並び順の設定
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

            // コース名が入力されている場合
            ps.setString(i + 1, new StringBuffer().append(NaikaraTalkConstants.LESSON_STATE_KBN_START).toString());
            i++;
            ps.setString(i + 1, new StringBuffer().append(NaikaraTalkConstants.LESSON_STATE_KBN_END).toString());

            // SQL文を実行
            res = ps.executeQuery();

            list = new ArrayList<LessonReservationPerformanceTrnDto>();
            while (res.next()) {

                dto = new LessonReservationPerformanceTrnDto();

                dto.setReservationNo(res.getString("RESERVATION_NO"));
                dto.setStateKbn(res.getString("STATE_KBN"));
                dto.setStudentId(res.getString("STUDENT_ID"));
                dto.setStudentNickNm(res.getString("STUDENT_NICK_NM"));
                dto.setTeacherId(res.getString("TEACHER_ID"));
                dto.setTeacherNickNm(res.getString("TEACHER_NICK_NM"));
                dto.setLessonDt(res.getString("LESSON_DT"));
                dto.setLessonTmCd(res.getString("LESSON_TM_CD"));
                dto.setLessonTmNm(res.getString("LESSON_TM_NM"));
                dto.setBigClassificationJcd(res.getString("BIG_CLASSIFICATION_JCD"));
                dto.setBigClassificationJnm(res.getString("BIG_CLASSIFICATION_JNM"));
                dto.setMiddleClassificationJcd(res.getString("MIDDLE_CLASSIFICATION_JCD"));
                dto.setMiddleClassificationJnm(res.getString("MIDDLE_CLASSIFICATION_JNM"));
                dto.setSmallClassificationJcd(res.getString("SMALL_CLASSIFICATION_JCD"));
                dto.setSmallClassificationJnm(res.getString("SMALL_CLASSIFICATION_JNM"));
                dto.setBigClassificationEcd(res.getString("BIG_CLASSIFICATION_ECD"));
                dto.setBigClassificationEnm(res.getString("BIG_CLASSIFICATION_ENM"));
                dto.setMiddleClassificationEcd(res.getString("MIDDLE_CLASSIFICATION_ECD"));
                dto.setMiddleClassificationEnm(res.getString("MIDDLE_CLASSIFICATION_ENM"));
                dto.setSmallClassificationEcd(res.getString("SMALL_CLASSIFICATION_ECD"));
                dto.setSmallClassificationEnm(res.getString("SMALL_CLASSIFICATION_ENM"));
                dto.setCourseCd(res.getString("COURSE_CD"));
                dto.setCourseJnm(res.getString("COURSE_JNM"));
                dto.setCourseEnm(res.getString("COURSE_ENM"));
                dto.setPaymentUsePoint(res.getBigDecimal("PAYMENT_USE_POINT"));
                dto.setFreeUsePoint(res.getBigDecimal("FREE_USE_POINT"));
                dto.setUsePointSum(res.getBigDecimal("USE_POINT_SUM"));
                dto.setMailKbn(res.getString("MAIL_KBN"));
                dto.setMailDt(res.getString("MAIL_DT"));
                dto.setSalesAccruedFlg(res.getString("SALES_ACCRUED_FLG"));

                dto.setDailyBatchFlg(res.getString("DAILYBATCH_FLG"));

                // 2014/01/15 FlashからTokboxに変更対応 start
                //dto.setRoomNo(res.getString("ROOM_NO"));
                dto.setTokboxSessionId(res.getString("TOKBOX_SESSION_ID"));
                dto.setTokboxTokenId(res.getString("TOKBOX_TOKEN_ID"));
                dto.setFileDuringLessons(res.getBlob("FILE_DURING_LESSONS"));
                // 2014/01/15 FlashからTokboxに変更対応 end

                dto.setTeacherSessionId(res.getString("TEACHER_SESSION_ID"));
                dto.setStudentSessionId(res.getString("STUDENT_SESSION_ID"));
                dto.setStudentInDttm(res.getTimestamp("STUDENT_IN_DTTM"));
                dto.setStudentStartDttm(res.getTimestamp("STUDENT_START_DTTM"));
                dto.setStudentEndDttm(res.getTimestamp("STUDENT_END_DTTM"));
                dto.setStudentLessonKbn(res.getString("STUDENT_LESSON_KBN"));
                dto.setTeacherInDttm(res.getTimestamp("TEACHER_IN_DTTM"));
                dto.setTeacherStartDttm(res.getTimestamp("TEACHER_START_DTTM"));
                dto.setTeacherEndDttm(res.getTimestamp("TEACHER_END_DTTM"));
                dto.setTeacherNextDttm(res.getTimestamp("TEACHER_NEXT_DTTM"));
                dto.setTeacherLessonKbn(res.getString("TEACHER_LESSON_KBN"));
                dto.setLessonStartDttm(res.getTimestamp("LESSON_START_DTTM"));
                dto.setLessonEndDttm(res.getTimestamp("LESSON_END_DTTM"));
                dto.setRecordVerNo(res.getInt("RECORD_VER_NO"));
                dto.setInsertDttm(res.getTimestamp("INSERT_DTTM"));
                dto.setInsertCd(res.getString("INSERT_CD"));
                dto.setUpdateDttm(res.getTimestamp("UPDATE_DTTM"));
                dto.setUpdateCd(res.getString("UPDATE_CD"));

                dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);

                list.add(dto);
            }

            if (list.size() < 1) {
                list = new ArrayList<LessonReservationPerformanceTrnDto>();
                dto = new LessonReservationPerformanceTrnDto();
                dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_NO);
                list.add(dto);
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
            } catch (SQLException se) {
                throw new NaiException(se);
            }
        }
    }


    /**
     * レッスン予実テーブル更新(TECS)<br>
     * <br>
     * レッスン予実テーブルの更新を行う<br>
     * <br>
     * @param dto 更新データ<br>
     * @return updatedRowCount 更新件数<br>
     * @exception NaiException
     */
    public int update(LessonReservationPerformanceTrnDto dto) throws NaiException {

        // 更新日時用
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        int updatedRowCount = NaikaraTalkConstants.RETURN_CD_ERR_UPD; // 更新データ件数

        // レッスン予実テーブルのデータチェック
        if (!checkDto(dto)) {
            return updatedRowCount;
        }

        StringBuffer sb = new StringBuffer();
        int index = 1;

        // データ更新処理
        // 更新SQL文の作成
        sb.append("update ");
        sb.append(" LESSON_RESERVATION_PERFORMANCE_TRN ");
        sb.append(" set ");
        sb.append(" RESERVATION_NO = ? ");
        sb.append(",STATE_KBN = ? ");
        sb.append(",STUDENT_ID = ? ");
        sb.append(",STUDENT_NICK_NM = ? ");
        sb.append(",TEACHER_ID = ? ");
        sb.append(",TEACHER_NICK_NM = ? ");
        sb.append(",LESSON_DT = ? ");
        sb.append(",LESSON_TM_CD = ? ");
        sb.append(",LESSON_TM_NM = ? ");
        sb.append(",BIG_CLASSIFICATION_JCD = ? ");
        sb.append(",BIG_CLASSIFICATION_JNM = ? ");
        sb.append(",MIDDLE_CLASSIFICATION_JCD = ? ");
        sb.append(",MIDDLE_CLASSIFICATION_JNM = ? ");
        sb.append(",SMALL_CLASSIFICATION_JCD = ? ");
        sb.append(",SMALL_CLASSIFICATION_JNM = ? ");
        sb.append(",BIG_CLASSIFICATION_ECD = ? ");
        sb.append(",BIG_CLASSIFICATION_ENM = ? ");
        sb.append(",MIDDLE_CLASSIFICATION_ECD = ? ");
        sb.append(",MIDDLE_CLASSIFICATION_ENM = ? ");
        sb.append(",SMALL_CLASSIFICATION_ECD = ? ");
        sb.append(",SMALL_CLASSIFICATION_ENM = ? ");
        sb.append(",COURSE_CD = ? ");
        sb.append(",COURSE_JNM = ? ");
        sb.append(",COURSE_ENM = ? ");
        sb.append(",PAYMENT_USE_POINT = ? ");
        sb.append(",FREE_USE_POINT = ? ");
        sb.append(",USE_POINT_SUM = ? ");
        sb.append(",MAIL_KBN = ? ");
        sb.append(",MAIL_DT = ? ");
        sb.append(",SALES_ACCRUED_FLG = ? ");
        sb.append(",DAILYBATCH_FLG = ? ");  // 日次売上計上済フラグ
        // 2014/01/15 FlashからTokboxに変更対応 start
        //sb.append(",ROOM_NO = ? ");
        // 2014/01/15 FlashからTokboxに変更対応 end
        sb.append(",TEACHER_SESSION_ID = ? ");
        sb.append(",STUDENT_SESSION_ID = ? ");
        sb.append(",STUDENT_IN_DTTM = ? ");
        sb.append(",STUDENT_START_DTTM = ? ");
        sb.append(",STUDENT_END_DTTM = ? ");
        sb.append(",STUDENT_LESSON_KBN = ? ");
        sb.append(",TEACHER_IN_DTTM = ? ");
        sb.append(",TEACHER_START_DTTM = ? ");
        sb.append(",TEACHER_END_DTTM = ? ");
/*
        // 2014/01/15 FlashからTokboxに変更対応 start
		sb.append(" ,TOKBOX_SESSION_ID = ? ");
		sb.append(" ,TOKBOX_TOKEN_ID = ? ");
		sb.append(" ,FILE_DURING_LESSONS = ? ");
		// 2014/01/15 FlashからTokboxに変更対応 end
*/
        sb.append(",TEACHER_NEXT_DTTM = ? ");
        sb.append(",TEACHER_LESSON_KBN = ? ");
        sb.append(",LESSON_START_DTTM = ? ");
        sb.append(",LESSON_END_DTTM = ? ");
        sb.append(",RECORD_VER_NO = ? ");
        sb.append(",UPDATE_DTTM = ? ");
        sb.append(",UPDATE_CD = ? ");

        // Where句
        sb.append(" where ");
        sb.append("   RESERVATION_NO = ? ");    // 予約No
        sb.append(" and ");
        sb.append("   RECORD_VER_NO = ? ");     // レコードバージョン番号

        // BLOG用
        FileInputStream fis1 = null;
        try {

            // PreparedStatementの作成
            PreparedStatement ps = conn.prepareStatement(sb.toString());

            ps.setString(index++, dto.getReservationNo());                            //予約No
            ps.setString(index++, dto.getStateKbn());                                 //状態区分
            ps.setString(index++, dto.getStudentId());                                //受講者ID
            ps.setString(index++, dto.getStudentNickNm());                            //受講者ニックネーム
            ps.setString(index++, dto.getTeacherId());                                //担当講師ID
            ps.setString(index++, dto.getTeacherNickNm());                            //担当講師ニックネーム
            ps.setString(index++, dto.getLessonDt());                                 //レッスン日
            ps.setString(index++, dto.getLessonTmCd());                               //レッスン時刻コード
            ps.setString(index++, dto.getLessonTmNm());                               //レッスン時刻名
            ps.setString(index++, dto.getBigClassificationJcd());                     //大分類コード
            ps.setString(index++, dto.getBigClassificationJnm());                     //大分類名
            ps.setString(index++, dto.getMiddleClassificationJcd());                  //中分類コード
            ps.setString(index++, dto.getMiddleClassificationJnm());                  //中分類名
            ps.setString(index++, dto.getSmallClassificationJcd());                   //小分類コード
            ps.setString(index++, dto.getSmallClassificationJnm());                   //小分類名
            ps.setString(index++, dto.getBigClassificationEcd());                     //大分類コード(英語)
            ps.setString(index++, dto.getBigClassificationEnm());                     //大分類名(英語)
            ps.setString(index++, dto.getMiddleClassificationEcd());                  //中分類コード(英語)
            ps.setString(index++, dto.getMiddleClassificationEnm());                  //中分類名(英語)
            ps.setString(index++, dto.getSmallClassificationEcd());                   //小分類コード(英語)
            ps.setString(index++, dto.getSmallClassificationEnm());                   //小分類名(英語)
            ps.setString(index++, dto.getCourseCd());                                 //コースコード
            ps.setString(index++, dto.getCourseJnm());                                //コース名
            ps.setString(index++, dto.getCourseEnm());                                //コース名(英語)
            ps.setBigDecimal(index++, dto.getPaymentUsePoint());                      //有償利用ポイント
            ps.setBigDecimal(index++, dto.getFreeUsePoint());                         //無償利用ポイント
            ps.setBigDecimal(index++, dto.getUsePointSum());                          //利用ポイント合計
            ps.setString(index++, dto.getMailKbn());                                  //添付メール送付済区分
            ps.setString(index++, dto.getMailDt());                                   //添付メール送付日
            ps.setString(index++, dto.getSalesAccruedFlg());                          //売上計上済フラグ
            ps.setString(index++, dto.getDailyBatchFlg());                            //日次売上計上済フラグ

            // 2014/01/15 FlashからTokboxに変更対応 start
            //ps.setString(index++, dto.getRoomNo());                                   //教室No
            // 2014/01/15 FlashからTokboxに変更対応 end

            ps.setString(index++, dto.getTeacherSessionId());                         //講師セッションID
            ps.setString(index++, dto.getStudentSessionId());                         //受講者セッションID
            ps.setTimestamp(index++, dto.getStudentInDttm());                         //受講者入室時刻
            ps.setTimestamp(index++, dto.getStudentStartDttm());                      //受講者START押下時刻
            ps.setTimestamp(index++, dto.getStudentEndDttm());                        //受講者END押下時刻
            ps.setString(index++, dto.getStudentLessonKbn());                         //受講者レッスン区分
            ps.setTimestamp(index++, dto.getTeacherInDttm());                         //講師入室時刻
            ps.setTimestamp(index++, dto.getTeacherStartDttm());                      //講師START押下時刻
            ps.setTimestamp(index++, dto.getTeacherEndDttm());                        //講師END押下時刻
            ps.setTimestamp(index++, dto.getTeacherNextDttm());                       //講師NEXT押下時刻
            ps.setString(index++, dto.getTeacherLessonKbn());                         //講師レッスン区分
            ps.setTimestamp(index++, dto.getLessonStartDttm());                       //レッスンSTART時刻
            ps.setTimestamp(index++, dto.getLessonEndDttm());                         //レッスンEND時刻

/*
            // 2014/01/15 FlashからTokboxに変更対応 start
            ps.setString(index++, dto.getTokboxSessionId());                          //TokboxのトークンID
            ps.setString(index++, dto.getTokboxTokenId());                            //TokboxのセッションID

            if (dto.getLessonPDF() != null) {
                try {
                    fis1 = new FileInputStream(dto.getLessonPDF());
                    ps.setBinaryStream(index++, fis1, fis1.available());              //レッスンファイル
                } catch (IOException se) {
                    throw new NaiException(se);
                }
            } else {
                ps.setNull(index++, java.sql.Types.BLOB);
            }
            // 2014/01/15 FlashからTokboxに変更対応 end
*/

            ps.setString(index++, String.valueOf(dto.getRecordVerNo() + 1));          // レコードバージョン番号
            ps.setString(index++, String.valueOf(Timestamp.valueOf(
                             sdf.format(cal.getTime()))));                            // 更新日時
            ps.setString(index++, dto.getUpdateCd());                                 // 更新者コード

            // Where句
            ps.setString(index++, dto.getReservationNo());                            // 予約No
            ps.setString(index++, String.valueOf(dto.getRecordVerNo()));              // レコードバージョン番号

            // SQL文の実行
            updatedRowCount = ps.executeUpdate();

            return updatedRowCount;

        } catch (SQLException se) {
            throw new NaiException(se);
        }
    }

    /**
     * レッスン予実テーブル更新データチェック(TECS)<br>
     * <br>
     * レッスン予実テーブルの更新データをチェックし<br>
     * エラー有の場合は、falseを返す<br>
     * エラー無の場合は、trueを返す<br>
     * <br>
     * @param dto チェックデータ
     * @return boolean チェック結果
     * @exception NaiException
     */
    private boolean checkDto(LessonReservationPerformanceTrnDto dto) throws NaiException {

        // 予約Noのチェック
        if (dto.getReservationNo() == null || "".equals(dto.getReservationNo())) {
            return false;
        }
        // 状態区分
        if (dto.getStateKbn() == null || "".equals(dto.getStateKbn())) {
            return false;
        }
        // 受講者ID
        if (dto.getStudentId() == null || "".equals(dto.getStudentId())) {
            return false;
        }
        // 受講者ニックネーム
        if (dto.getStudentNickNm() == null || "".equals(dto.getStudentNickNm())) {
            return false;
        }
        // 担当講師ID
        if (dto.getTeacherId() == null || "".equals(dto.getTeacherId())) {
            return false;
        }
        // 担当講師ニックネーム
        if (dto.getTeacherNickNm() == null || "".equals(dto.getTeacherNickNm())) {
            return false;
        }
        // レッスン日
        if (dto.getLessonDt() == null || "".equals(dto.getLessonDt())) {
            return false;
        }
        // レッスン時刻コード
        if (dto.getLessonTmCd() == null || "".equals(dto.getLessonTmCd())) {
            return false;
        }
        // レッスン時刻名
        if (dto.getLessonTmNm() == null || "".equals(dto.getLessonTmNm())) {
            return false;
        }
        // 大分類コード
        if (dto.getBigClassificationJcd() == null || "".equals(dto.getBigClassificationJcd())) {
            return false;
        }
        // 大分類名
        if (dto.getBigClassificationJnm() == null || "".equals(dto.getBigClassificationJnm())) {
            return false;
        }
        // 中分類コード
        if (dto.getMiddleClassificationJcd() == null || "".equals(dto.getMiddleClassificationJcd())) {
            return false;
        }
        // 中分類名
        if (dto.getMiddleClassificationJnm() == null || "".equals(dto.getMiddleClassificationJnm())) {
            return false;
        }
        // 小分類コード
        if (dto.getSmallClassificationJcd() == null || "".equals(dto.getSmallClassificationJcd())) {
            return false;
        }
        // 小分類名
        if (dto.getSmallClassificationJnm() == null || "".equals(dto.getSmallClassificationJnm())) {
            return false;
        }
        // 大分類コード(英語)
        if (dto.getBigClassificationEcd() == null || "".equals(dto.getBigClassificationEcd())) {
            return false;
        }
        // 大分類名(英語)
        if (dto.getBigClassificationEnm() == null || "".equals(dto.getBigClassificationEnm())) {
            return false;
        }
        // 中分類コード(英語)
        if (dto.getMiddleClassificationEcd() == null || "".equals(dto.getMiddleClassificationEcd())) {
            return false;
        }
        // 中分類名(英語)
        if (dto.getMiddleClassificationEnm() == null || "".equals(dto.getMiddleClassificationEnm())) {
            return false;
        }
        // 小分類コード(英語)
        if (dto.getSmallClassificationEcd() == null || "".equals(dto.getSmallClassificationEcd())) {
            return false;
        }
        // 小分類名(英語)
        if (dto.getSmallClassificationEnm() == null || "".equals(dto.getSmallClassificationEnm())) {
            return false;
        }
        // コースコード
        if (dto.getCourseCd() == null || "".equals(dto.getCourseCd())) {
            return false;
        }
        // コース名
        if (dto.getCourseJnm() == null || "".equals(dto.getCourseJnm())) {
            return false;
        }
        // コース名(英語)
        if (dto.getCourseEnm() == null || "".equals(dto.getCourseEnm())) {
            return false;
        }
        // 利用ポイント合計(BigDecimal)
        if (dto.getUsePointSum() == null || "".equals(dto.getUsePointSum())) {
            return false;
        }
        // 添付メール送付済区分
        if (dto.getMailKbn() == null || "".equals(dto.getMailKbn())) {
            return false;
        }
        // 売上計上済フラグ
        if (dto.getSalesAccruedFlg() == null || "".equals(dto.getSalesAccruedFlg())) {
            return false;
        }
        // 受講者レッスン区分
        if (dto.getStudentLessonKbn() == null || "".equals(dto.getStudentLessonKbn())) {
            return false;
        }
        // 講師レッスン区分
        if (dto.getTeacherLessonKbn() == null || "".equals(dto.getTeacherLessonKbn())) {
            return false;
        }
        // レコードバージョン番号のチェック(int)
        if ("".equals(dto.getRecordVerNo())) {
            return false;
        }
        // 更新者コードのチェック
        if (dto.getUpdateCd() == null || "".equals(dto.getUpdateCd())) {
            return false;
        }

        return true;

    }
	/**
	 * 更新処理(MIRA)
	 * @param dto
	 * @param conditions
	 * @return 更新結果件数
	 * @throws NaiException
	 */
	public int update(LessonReservationPerformanceTrnDto dto, ConditionMapper conditions) throws NaiException {


        StringBuilder sb = new StringBuilder();
        ArrayList<String> values = new ArrayList<String>();

        // update文構築
        sb.append("update LESSON_RESERVATION_PERFORMANCE_TRN set ");

        // 2014/01/15 FlashからTokboxに変更対応 start
		//レッスンファイル
        boolean lessonPDFFlg = false;
		if (dto.getLessonPDF() != null) {
			sb.append("FILE_DURING_LESSONS = ?, ");
			values.add(NaikaraTalkConstants.BRANK);
			lessonPDFFlg = true;
		}
        // 2014/01/15 FlashからTokboxに変更対応 end

		// 2014/04/22 Add Start レッスンファイルのコンテンツタイプの追加対応(pdf⇒pdf、txt)
		if (!StringUtils.isEmpty(dto.getLessonFileContentType())){
			sb.append("FILE_CONTENT_TYPE = ?, ");
			values.add(dto.getLessonFileContentType());
		}
		// 2014/04/22 Add End  レッスンファイルのコンテンツタイプの追加対応(pdf⇒pdf、txt)

		// 生徒セッションID
		if (!StringUtils.isEmpty(dto.getStudentSessionId())){
			sb.append("STUDENT_SESSION_ID = ?, ");
			values.add(dto.getStudentSessionId());
		}
		// 講師セッションID
		if (!StringUtils.isEmpty(dto.getTeacherSessionId())){
			sb.append("TEACHER_SESSION_ID = ?, ");
			values.add(dto.getTeacherSessionId());
		}

		/* 2014/01/15 FlashからTokboxに変更対応 start
		//教室番号
		if (!StringUtils.isEmpty(dto.getRoomNo())){
			sb.append("ROOM_NO = ?, ");
			values.add(dto.getRoomNo());
		}*/

		//TokboxのセッションID
		if (!StringUtils.isEmpty(dto.getTokboxSessionId())){
			sb.append("TOKBOX_SESSION_ID = ?, ");
			values.add(dto.getTokboxSessionId());
		}

		//TokboxのトークンID
		if (!StringUtils.isEmpty(dto.getTokboxTokenId())){
			sb.append("TOKBOX_TOKEN_ID = ?, ");
			values.add(dto.getTokboxTokenId());
		}
		// 2014/01/15 FlashからTokboxに変更対応 end

		// レコードバージョン番号 TODO;数値のパターン
		if (!StringUtils.isEmpty(String.valueOf(dto.getRecordVerNo()))){
			sb.append("RECORD_VER_NO = ?, ");
			values.add(String.valueOf(dto.getRecordVerNo() + 1));
		}

        // 更新者
        if (!StringUtils.isEmpty(dto.getUpdateCd())) {
            sb.append("UPDATE_CD = ?, ");
            values.add(dto.getUpdateCd());
        }

        sb.append("UPDATE_DTTM = sysdate() ");

        // 更新条件句
        if (!StringUtils.isEmpty(conditions.getConditionString())) {
            sb.append("where ");
            sb.append(conditions.getConditionString());
        }

        // BLOG用
        FileInputStream fis1 = null;
		try{
			PreparedStatement ps = conn.prepareStatement(sb.toString());

            // パラメタの設定
            int i = 1;
            // updateの値
            for (String val : values) {
            	// 2014/01/15 FlashからTokboxに変更対応 start
            	// レッスンファイルを設定する場合
            	if (lessonPDFFlg && i == 1) {
            		if (dto.getLessonPDF() != null) {
                        try {
                            fis1 = new FileInputStream(dto.getLessonPDF());
                            ps.setBinaryStream(i, fis1, fis1.available());
                        } catch (IOException se) {
                            throw new NaiException(se);
                        }
                    }
            		lessonPDFFlg = false;
            	} else {
                    ps.setString(i, val);
            	}
            	// 2014/01/15 FlashからTokboxに変更対応 end
                i++;
            }
            // 更新条件
            for (QueryCondition cond : conditions.getConditions()) {
                ps.setString(i, cond.getValue());
                i++;
            }

            // 実行結果件数を返却
            return ps.executeUpdate();


		} catch ( SQLException se ) {
			throw new NaiException(se);
		}
	}

	/**
	 * レッスン予実テーブル削除（レッスン予約・取消専用）<br>
	 * <br>
	 * 解除ポイントのレッスン予実テーブルを削除する<br>
	 * <br>
	 * @param rsvNoPurchaseId：予約No
	 * @return int：0(削除成功),1(削除失敗)
	 * @throws NaiException
	 */
	public int lessonReservasionPerformanceTrnDelete(String rsvNoPurchaseId) throws NaiException {

		// 戻り値初期化
		int result = NaikaraTalkConstants.RETURN_CD_DATA_YES;

		// データ削除処理
		StringBuffer sb = new StringBuffer();
		sb.append("delete ");
		sb.append("from LESSON_RESERVATION_PERFORMANCE_TRN ");
		sb.append("where RESERVATION_NO = ? ");
		sb.append("and STATE_KBN = '1' ");

		try{

			PreparedStatement ps = conn.prepareStatement(sb.toString());

			ps.setString(1, rsvNoPurchaseId);

			if(ps.executeUpdate()==0){
				result = NaikaraTalkConstants.RETURN_CD_DATA_NO;
			}

		} catch ( SQLException se ) {
			throw new NaiException(se);
		}
		return result;
	}

	/**
	 * レッスン予実テーブル登録（レッスン予約・取消専用）<br>
	 * <br>
	 * 引当データのレッスン予実テーブルの登録を行う<br>
	 * <br>
	 * @param PointProvisionTrnDto：ポイント引当テーブルDTO
	 * @return int：0(登録成功),1(登録失敗)
	 * @throws NaiException
	 */
	public int lessonReservasionPerformanceTrnInsert(LessonReservationPerformanceTrnDto lrptDto)
			throws NaiException {

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// 戻り値初期化
		int result = NaikaraTalkConstants.RETURN_CD_DATA_YES;

		// データ削除処理
		StringBuffer sb = new StringBuffer();
		sb.append("insert into ");
		sb.append("LESSON_RESERVATION_PERFORMANCE_TRN ");
		sb.append("(RESERVATION_NO ");
		sb.append(",STATE_KBN ");
		sb.append(",STUDENT_ID ");
		sb.append(",STUDENT_NICK_NM ");
		sb.append(",TEACHER_ID ");
		sb.append(",TEACHER_NICK_NM ");
		sb.append(",LESSON_DT ");
		sb.append(",LESSON_TM_CD ");
		sb.append(",LESSON_TM_NM ");
		sb.append(",BIG_CLASSIFICATION_JCD ");
		sb.append(",BIG_CLASSIFICATION_JNM ");
		sb.append(",MIDDLE_CLASSIFICATION_JCD ");
		sb.append(",MIDDLE_CLASSIFICATION_JNM ");
		sb.append(",SMALL_CLASSIFICATION_JCD ");
		sb.append(",SMALL_CLASSIFICATION_JNM ");
		sb.append(",BIG_CLASSIFICATION_ECD ");
		sb.append(",BIG_CLASSIFICATION_ENM ");
		sb.append(",MIDDLE_CLASSIFICATION_ECD ");
		sb.append(",MIDDLE_CLASSIFICATION_ENM ");
		sb.append(",SMALL_CLASSIFICATION_ECD ");
		sb.append(",SMALL_CLASSIFICATION_ENM ");
		sb.append(",COURSE_CD ");
		sb.append(",COURSE_JNM ");
		sb.append(",COURSE_ENM ");
		sb.append(",PAYMENT_USE_POINT ");
		sb.append(",FREE_USE_POINT ");
		sb.append(",USE_POINT_SUM ");
		sb.append(",MAIL_KBN ");
		sb.append(",SALES_ACCRUED_FLG ");

		sb.append(",DAILYBATCH_FLG ");  // 30.日次売上計上済フラグ

		sb.append(",STUDENT_LESSON_KBN ");

		sb.append(",TEACHER_LESSON_KBN ");
		sb.append(",RECORD_VER_NO ");
		sb.append(",INSERT_DTTM ");
		sb.append(",INSERT_CD ");
		sb.append(",UPDATE_DTTM ");
		sb.append(",UPDATE_CD ");
		sb.append(") value (");
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

		sb.append(",? ");  //30.日次売上計上済フラグ

		sb.append(",? ");
		sb.append(",? ");
		sb.append(",? ");
		sb.append(",? ");
		sb.append(",? ");
		sb.append(",? ");
		sb.append(",? ");
		sb.append(") ");

		try{

			PreparedStatement ps = conn.prepareStatement(sb.toString());

			ps.setString(1, lrptDto.getReservationNo());
			ps.setString(2, lrptDto.getStateKbn());
			ps.setString(3, lrptDto.getStudentId());
			ps.setString(4, lrptDto.getStudentNickNm());
			ps.setString(5, lrptDto.getTeacherId());
			ps.setString(6, lrptDto.getTeacherNickNm());
			ps.setString(7, lrptDto.getLessonDt());
			ps.setString(8, lrptDto.getLessonTmCd());
			ps.setString(9, lrptDto.getLessonTmNm());
			ps.setString(10, lrptDto.getBigClassificationJcd());
			ps.setString(11, lrptDto.getBigClassificationJnm());
			ps.setString(12, lrptDto.getMiddleClassificationJcd());
			ps.setString(13, lrptDto.getMiddleClassificationJnm());
			ps.setString(14, lrptDto.getSmallClassificationJcd());
			ps.setString(15, lrptDto.getSmallClassificationJnm());
			ps.setString(16, lrptDto.getBigClassificationEcd());
			ps.setString(17, lrptDto.getBigClassificationEnm());
			ps.setString(18, lrptDto.getMiddleClassificationEcd());
			ps.setString(19, lrptDto.getMiddleClassificationEnm());
			ps.setString(20, lrptDto.getSmallClassificationEcd());
			ps.setString(21, lrptDto.getSmallClassificationEnm());
			ps.setString(22, lrptDto.getCourseCd());
			ps.setString(23, lrptDto.getCourseJnm());
			ps.setString(24, lrptDto.getCourseEnm());
			ps.setBigDecimal(25, lrptDto.getPaymentUsePoint());
			ps.setBigDecimal(26, lrptDto.getFreeUsePoint());
			ps.setBigDecimal(27, lrptDto.getUsePointSum());
			ps.setString(28, lrptDto.getMailKbn());
			ps.setString(29, lrptDto.getSalesAccruedFlg());

			ps.setString(30, NaikaraTalkConstants.DAILYBATCH_FLG_OFF);  // 30.日次売上計上済フラグ 初期値：0

			ps.setString(31, lrptDto.getStudentLessonKbn());
			ps.setString(32, lrptDto.getTeacherLessonKbn());
			ps.setInt(33, lrptDto.getRecordVerNo());
			ps.setString(34, String.valueOf(Timestamp.valueOf(sdf.format(cal.getTime()))));
			ps.setString(35, lrptDto.getInsertCd());
			ps.setString(36, String.valueOf(Timestamp.valueOf(sdf.format(cal.getTime()))));
			ps.setString(37, lrptDto.getUpdateCd());

			if(ps.executeUpdate()==0){
				result = NaikaraTalkConstants.RETURN_CD_DATA_NO;
			}

		} catch ( SQLException se ) {
			throw new NaiException(se);
		}
		return result;
	}

	/**
	 * レッスンステータス更新処理
	 * この処理はステータス更新だけなので、排他制御を対象としません。
	 * waitで処理されます。
	 * レコードバージョン番号は、カウントアップされます。
	 * @param dto
	 * @param conditions
	 * @return 更新結果件数
	 * @throws NaiException
	 */
	public int updateStatus(LessonReservationPerformanceTrnDto dto, ConditionMapper conditions) throws NaiException {

//		DataSource ds = DbUtil.getDataSource();
//		Connection conn = null;

		StringBuilder sb = new StringBuilder();
//		ArrayList<Object> values = new ArrayList<Object>();

		// update文構築
		sb.append("update LESSON_RESERVATION_PERFORMANCE_TRN set ");

		// パラメタの設定
		int i = 1;

		// 状態区分
		if (dto.getStateKbn() != null){
			sb.append("STATE_KBN = ?, ");
		}
		// 受講者入室時刻
		if (dto.getStudentInDttm() != null){
			sb.append("STUDENT_IN_DTTM = ?, ");
		}
		// 受講者START押下時刻
		if (dto.getStudentStartDttm() != null){
			sb.append("STUDENT_START_DTTM = ?, ");
		}
		// 受講者END押下時刻
		if (dto.getStudentEndDttm() != null){
			sb.append("STUDENT_END_DTTM = ?, ");
		}
		// 受講者レッスン区分
		if (!StringUtils.isEmpty(dto.getStudentLessonKbn())){
			sb.append("STUDENT_LESSON_KBN = ?, ");
		}
		// 講師入室時刻
		if (dto.getTeacherInDttm() != null){
			sb.append("TEACHER_IN_DTTM = ?, ");
		}
		// 講師START押下時刻
		if (dto.getTeacherStartDttm() != null){
			sb.append("TEACHER_START_DTTM = ?, ");
		}
		// 	講師END押下時刻
		if (dto.getTeacherEndDttm() != null){
			sb.append("TEACHER_END_DTTM = ?, ");
		}
		// 	講師NEXT押下時刻
		if (dto.getTeacherNextDttm() != null){
			sb.append("TEACHER_NEXT_DTTM = ?, ");
		}
		// 講師レッスン区分
		if (!StringUtils.isEmpty(dto.getTeacherLessonKbn())){
			sb.append("TEACHER_LESSON_KBN = ?, ");
		}
		// レッスンSTART時刻
		if (dto.getLessonStartDttm() != null){
			sb.append("LESSON_START_DTTM = ?, ");
		}
		// レッスンEND時刻
		if (dto.getLessonEndDttm() != null){
			sb.append("LESSON_END_DTTM = ?, ");
		}

		// 2014/01/15 FlashからTokboxに変更対応 start
		// レッスンが完了した場合
		if (NaikaraLessonConstants.STGATE_DONE.equals(dto.getStateKbn())) {
			// Tokbox用トークンIDをクリアする
			sb.append("TOKBOX_SESSION_ID = ?, ");
			// Tokbox用セッションIDをクリアする
			sb.append("TOKBOX_TOKEN_ID = ?, ");
			// レッスンファイルをクリアする
			sb.append("FILE_DURING_LESSONS = ?, ");

			// 2014/04/22 Add Start レッスンファイルのコンテンツタイプの追加対応(pdf⇒pdf、txt)
			// レッスンファイルのコンテンツタイプをクリアする
			sb.append("FILE_CONTENT_TYPE = ?, ");
			// 2014/04/22 Add End   レッスンファイルのコンテンツタイプの追加対応(pdf⇒pdf、txt)

		}
		// 2014/01/15 FlashからTokboxに変更対応 end

		// レコードバージョン番号 TODO;数値のパターン
			sb.append("RECORD_VER_NO = RECORD_VER_NO + 1, ");

		// 更新者
		if (!StringUtils.isEmpty(dto.getUpdateCd())){
			sb.append("UPDATE_CD = ?, ");
		}

		sb.append("UPDATE_DTTM = sysdate() ");

		// 更新条件句
		if(!StringUtils.isEmpty(conditions.getConditionString())) {
			sb.append("where ");
			sb.append(conditions.getConditionString());
		}

		try{
//			conn = DBConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sb.toString());

			// パラメタの設定
//			// updateの値

			// 状態区分
			if (dto.getStateKbn() != null){
				ps.setString(i, dto.getStateKbn());
				i++;
			}

			// 受講者入室時刻
			if (dto.getStudentInDttm() != null){
				ps.setTimestamp(i, dto.getStudentInDttm());
				i++;
			}
			// 受講者START押下時刻
			if (dto.getStudentStartDttm() != null){
				ps.setTimestamp(i, dto.getStudentStartDttm());
				i++;
			}
			// 受講者END押下時刻
			if (dto.getStudentEndDttm() != null){
				ps.setTimestamp(i, dto.getStudentEndDttm());
				i++;
			}
			// 受講者レッスン区分
			if (!StringUtils.isEmpty(dto.getStudentLessonKbn())){
				ps.setString(i, dto.getStudentLessonKbn());
				i++;
			}
			// 講師入室時刻
			if (dto.getTeacherInDttm() != null){
				ps.setTimestamp(i, dto.getTeacherInDttm());
				i++;
			}
			// 講師START押下時刻
			if (dto.getTeacherStartDttm() != null){
				ps.setTimestamp(i, dto.getTeacherStartDttm());
				i++;
			}
			// 	講師END押下時刻
			if (dto.getTeacherEndDttm() != null){
				ps.setTimestamp(i, dto.getTeacherEndDttm());
				i++;
			}
			// 	講師NEXT押下時刻
			if (dto.getTeacherNextDttm() != null){
				ps.setTimestamp(i, dto.getTeacherNextDttm());
				i++;
			}
			// 講師レッスン区分
			if (!StringUtils.isEmpty(dto.getTeacherLessonKbn())){
				ps.setString(i, dto.getTeacherLessonKbn());
				i++;
			}
			// レッスンSTART時刻
			if (dto.getLessonStartDttm() != null){
				ps.setTimestamp(i, dto.getLessonStartDttm());
				i++;
			}
			// レッスンEND時刻
			if (dto.getLessonEndDttm() != null){
				ps.setTimestamp(i, dto.getLessonEndDttm());
				i++;
			}

			// 2014/01/15 FlashからTokboxに変更対応 start
			// レッスンが完了した場合
			if (NaikaraLessonConstants.STGATE_DONE.equals(dto.getStateKbn())) {
				// Tokbox用セッションIDをクリアする
				ps.setNull(i, java.sql.Types.CHAR);    // TOKBOX_SESSION_ID
				i++;
				// Tokbox用トークンIDをクリアする
				ps.setNull(i, java.sql.Types.CHAR);    // TOKBOX_TOKEN_ID
				i++;
				// レッスンファイルをクリアする
				ps.setNull(i, java.sql.Types.BLOB);    // FILE_DURING_LESSONS
				i++;

				// 2014/04/22 Add Start レッスンファイルのコンテンツタイプの追加対応(pdf⇒pdf、txt)
				// レッスンファイルのコンテンツタイプをクリアする
				ps.setNull(i, java.sql.Types.CHAR);    // FILE_CONTENT_TYPE
				i++;
				// 2014/04/22 Add End   レッスンファイルのコンテンツタイプの追加対応(pdf⇒pdf、txt)

			}
			// 2014/01/15 FlashからTokboxに変更対応 end

			// 更新者
			if (!StringUtils.isEmpty(dto.getUpdateCd())){
				ps.setString(i, dto.getUpdateCd());
				i++;
			}

			// 更新条件
			for( QueryCondition cond : conditions.getConditions()){
				ps.setString(i , cond.getValue());
				i++;
			}

			// 更新実行
			int ret = ps.executeUpdate();

			conn.commit();
			// 実行結果件数を返却
			return ret;

		} catch ( SQLException se ) {
			se.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new NaiException(e);
			}
			throw new NaiException(se);
		} finally {
//			try {
//        		if ( conn != null ) {
//        			conn.close();
//        		}
//			} catch ( SQLException se ) {
//				throw new NaiException(se);
//			}
		}
	}

}
