package com.naikara_talk.dao;

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
import com.naikara_talk.dto.ScheduleReservationTrnDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DAOクラス<br>
 * <b>クラス名称　　　:</b>講師予定予約テーブルデータアクセスクラス<br>
 * <b>クラス概要　　　:</b>講師予定予約テーブルのデータを取得する<br>
 * <br>
 * <b>著作権　　　　　:</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/06/27 TECS 新規作成
 *                     </b>2014/06/02 TECS レッスン予約に関する「応相談」対応
 */
public class ScheduleReservationTrnDao extends AbstractDao {

    /** 講師ID 条件項目 */
    public static final String COND_TEACHER_ID = "TEACHER_ID";
    /** レッスン日 条件項目 */
    public static final String COND_LESSON_DT = "LESSON_DT";
    /** レッスン時刻コード 条件項目 */
    public static final String COND_LESSON_TM_CD = "LESSON_TM_CD";
    /** 予約区分 条件項目 */
    public static final String COND_RESERVATION_KBN = "RESERVATION_KBN";
    /** 受講者ID 条件項目 */
    public static final String COND_STUDENT_ID = "STUDENT_ID";

    // 2014/06/02 レッスン予約に関する「応相談」対応 Add Start
    /** 応相談予約登録日時 条件項目 */
    public static final String COND_REQUESTED_DTTM = "REQUESTED_DTTM";
    // 2014/06/02 レッスン予約に関する「応相談」対応 Add End

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public ScheduleReservationTrnDao(Connection con) {
        this.conn = con;
    }

    /**
     * 講師予定予約テーブルデータ取得<br>
     * <br>
     * 講師予定予約テーブルリストを戻り値で返却する<br>
     * <br>
     * @param conditional
     * @param orderConditional
     * @return ScheduleReservationTrnDto
     * @throws NaiException
     */
    public List<ScheduleReservationTrnDto> search(ConditionMapper conditions, OrderCondition orderConditions)
            throws NaiException {

        ResultSet res = null;

        // 講師予定予約テーブルの全項目取得
        StringBuffer sb = new StringBuffer();
        sb.append("select");
        sb.append(" TEACHER_ID ");
        sb.append(",LESSON_DT ");
        sb.append(",LESSON_TM_CD ");
        sb.append(",RESERVATION_KBN ");
        sb.append(",RESERVATION_NO ");
        sb.append(",STUDENT_ID ");
        sb.append(",COURSE_CD ");

        // 2014/06/02 レッスン予約に関する「応相談」対応 Add Start
        sb.append(", REQUESTED_DTTM ");
        sb.append(", APP_REQUEST_DTTM ");
        sb.append(", APP_REQUEST_AUTO_CXL_DTTM ");
        // 2014/06/02 レッスン予約に関する「応相談」対応 Add End

        sb.append(",RECORD_VER_NO ");
        sb.append(",INSERT_DTTM ");
        sb.append(",INSERT_CD ");
        sb.append(",UPDATE_DTTM ");
        sb.append(",UPDATE_CD ");
        sb.append("from ");
        sb.append("SCHEDULE_RESERVATION_TRN ");

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

            ArrayList<ScheduleReservationTrnDto> list = new ArrayList<ScheduleReservationTrnDto>();

            while (res.next()) {

                ScheduleReservationTrnDto retDto = new ScheduleReservationTrnDto();

                retDto.setTeacherId(res.getString("TEACHER_ID"));
                retDto.setLessonDt(res.getString("LESSON_DT"));
                retDto.setLessonTmCd(res.getString("LESSON_TM_CD"));
                retDto.setReservationKbn(res.getString("RESERVATION_KBN"));
                retDto.setReservationNo(res.getString("RESERVATION_NO"));
                retDto.setStudentId(res.getString("STUDENT_ID"));
                retDto.setCourseCd(res.getString("COURSE_CD"));

                // 2014/06/02 レッスン予約に関する「応相談」対応 Add Start
                retDto.setRequestedDttm(res.getTimestamp("REQUESTED_DTTM"));
                retDto.setAppRequestDttm(res.getTimestamp("APP_REQUEST_DTTM"));
                retDto.setAppRequestAutoCxlDttm(res.getTimestamp("APP_REQUEST_AUTO_CXL_DTTM"));
                // 2014/06/02 レッスン予約に関する「応相談」対応 Add End

                retDto.setRecordVerNo(res.getInt("RECORD_VER_NO"));
                retDto.setInsertDttm(res.getTimestamp("INSERT_DTTM"));
                retDto.setInsertCd(res.getString("INSERT_CD"));
                retDto.setUpdateDttm(res.getTimestamp("UPDATE_DTTM"));
                retDto.setUpdateCd(res.getString("UPDATE_CD"));

                retDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);

                list.add(retDto);
            }

            if (list.size() < 1) {
                ScheduleReservationTrnDto retDto = new ScheduleReservationTrnDto();
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
     * 講師予定予約テーブル更新<br>
     * <br>
     * 講師予定予約テーブルの更新を行う<br>
     * <br>
     * @param dto 更新データ<br>
     * @return updatedRowCount 更新件数<br>
     * @exception NaiException
     */
    public int update(ScheduleReservationTrnDto dto) throws NaiException {

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        int updatedRowCount = NaikaraTalkConstants.RETURN_CD_ERR_UPD; // 更新データ件数

        // 講師予定予約テーブルのデータチェック
        if (!checkDto(dto)) {
            return updatedRowCount;
        }

        StringBuffer sb = new StringBuffer();
        sb.append("update ");
        sb.append("SCHEDULE_RESERVATION_TRN ");
        sb.append("set ");
        sb.append(" RESERVATION_KBN = ? ");
        sb.append(",RESERVATION_NO = ? ");
        sb.append(",STUDENT_ID = ? ");
        sb.append(",COURSE_CD = ? ");
        sb.append(",RECORD_VER_NO = ? ");
        sb.append(",UPDATE_DTTM = ? ");
        sb.append(",UPDATE_CD = ? ");
        sb.append("where ");
        sb.append("TEACHER_ID = ? ");
        sb.append("and ");
        sb.append("LESSON_DT = ? ");
        sb.append("and ");
        sb.append("LESSON_TM_CD = ? ");
        sb.append("and ");
        sb.append("RECORD_VER_NO = ? ");

        try {

            PreparedStatement ps = conn.prepareStatement(sb.toString());

            ps.setString(1, dto.getReservationKbn());
            ps.setString(2, dto.getReservationNo());
            ps.setString(3, dto.getStudentId());
            ps.setString(4, dto.getCourseCd());
            ps.setString(5, String.valueOf(dto.getRecordVerNo() + 1));
            ps.setString(6, String.valueOf(Timestamp.valueOf(sdf.format(cal.getTime()))));
            ps.setString(7, dto.getUpdateCd());
            ps.setString(8, dto.getTeacherId());
            ps.setString(9, dto.getLessonDt());
            ps.setString(10, dto.getLessonTmCd());
            ps.setString(11, String.valueOf(dto.getRecordVerNo()));

            updatedRowCount = ps.executeUpdate();

            return updatedRowCount;
        } catch (SQLException se) {
            throw new NaiException(se);
        }
    }

    /**
     * 講師予定予約テーブル登録処理<br>
     * <br>
     * 講師予定予約テーブルの登録を行う<br>
     * <br>
     * @param dto 登録データ
     * @return insertRowCount 登録件数
     * @exception NaiException
     */
    public int insert(ScheduleReservationTrnDto dto) throws NaiException {

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        int insertRowCount = NaikaraTalkConstants.RETURN_CD_ERR_INS; // 登録データ件数

        // 組織マスタのデータチェック
        if (!checkDto(dto)) {
            return insertRowCount;
        }

        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO ");
        sb.append("SCHEDULE_RESERVATION_TRN ");
        sb.append(" ( ");
        sb.append("TEACHER_ID ");
        sb.append(",LESSON_DT ");
        sb.append(",LESSON_TM_CD ");
        sb.append(",RESERVATION_KBN ");
        sb.append(",RESERVATION_NO ");
        sb.append(",STUDENT_ID ");
        sb.append(",COURSE_CD ");
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
        sb.append(" ) ");

        try {

            PreparedStatement ps = conn.prepareStatement(sb.toString());

            ps.setString(1, dto.getTeacherId());
            ps.setString(2, dto.getLessonDt());
            ps.setString(3, dto.getLessonTmCd());
            ps.setString(4, dto.getReservationKbn());
            ps.setString(5, dto.getReservationNo());
            ps.setString(6, dto.getStudentId());
            ps.setString(7, dto.getCourseCd());
            ps.setString(8, String.valueOf(dto.getRecordVerNo()));
            ps.setString(9, String.valueOf(Timestamp.valueOf(sdf.format(cal.getTime()))));
            ps.setString(10, dto.getInsertCd());
            ps.setString(11, String.valueOf(Timestamp.valueOf(sdf.format(cal.getTime()))));
            ps.setString(12, dto.getUpdateCd());

            insertRowCount = ps.executeUpdate();

            return insertRowCount;
        } catch (SQLException se) {
            throw new NaiException(se);
        }
    }

    /**
     * 講師予定予約テーブル更新データチェック<br>
     * <br>
     * 講師予定予約テーブルの更新データをチェックし<br>
     * エラー有の場合は、falseを返す<br>
     * エラー無の場合は、trueを返す<br>
     * <br>
     * @param dto チェックデータ
     * @return boolean チェック結果
     * @exception NaiException
     */
    private boolean checkDto(ScheduleReservationTrnDto dto) throws NaiException {

        // 講師IDのチェック
        if (dto.getTeacherId() == null || "".equals(dto.getTeacherId())) {
            return false;
        }

        // レッスン日のチェック
        if (dto.getLessonDt() == null || "".equals(dto.getLessonDt())) {
            return false;
        }

        // レッスン時刻コードのチェック
        if (dto.getLessonTmCd() == null || "".equals(dto.getLessonTmCd())) {
            return false;
        }

        // 予約区分(予約状況)のチェック
        if (dto.getReservationKbn() == null || "".equals(dto.getReservationKbn())) {
            return false;
        }

        // 更新者コードのチェック
        if (dto.getUpdateCd() == null || "".equals(dto.getUpdateCd())) {
            return false;
        }

        return true;

    }

	/**
	 * 講師予定予約テーブル更新（レッスン予約・取消専用）<br>
	 * <br>
	 * 解除ポイントの講師予定予約テーブルを更新する<br>
	 * <br>
	 * @param studentId：受講者ID
	 * @param teacherId：講師ID
	 * @param lessonDt：レッスン日
	 * @param lessonTmCd：レッスン時刻コード
	 * @param reservatioKbnSet：
	 * @param reservatioKbnWhere：
	 * @param ReservationNo：予約No
	 * @param courseCd：コースコード
	 * @param updateId：更新者ID
	 * @return int：0(更新成功),1(更新失敗)
	 * @throws NaiException
	 */
    public int scheduleReservationTrnUpdate(String studentId, String teacherId, String lessonDt,
    		String lessonTmCd, String reservatioKbnSet, String reservatioKbnWhere,
    		String reservationNo, String courseCd, String updateId) throws NaiException {

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// 戻り値初期化
		int result = NaikaraTalkConstants.RETURN_CD_DATA_YES;

		// データ更新処理
		StringBuffer sb = new StringBuffer();
		sb.append("update SCHEDULE_RESERVATION_TRN ");
		sb.append("set RESERVATION_KBN = ? ");          // 予約区分
		sb.append(",RESERVATION_NO = ? ");              // レッスン予約No
		sb.append(",STUDENT_ID = ? ");                  // レッスン予約した受講者
		sb.append(",COURSE_CD = ? ");                   // レッスン予約されているコース名

/*
		sb.append(",RESERVATION_NO = '' ");
		sb.append(",STUDENT_ID = '' ");
		sb.append(",COURSE_CD = '' ");
*/

		sb.append(",RECORD_VER_NO = RECORD_VER_NO + 1 ");
		sb.append(",UPDATE_DTTM = ? ");
		sb.append(",UPDATE_CD = ? ");

		// 2014/06/02 レッスン予約に関する「応相談」対応 Add Start
		if (StringUtils.equals(reservatioKbnSet, NaikaraTalkConstants.RESERV_KBN_CON_PROVISIONAL_RESERV)) {
			// 応相談予約 の場合
			sb.append(" ,REQUESTED_DTTM = now() ");             // 応相談予約登録日時
			sb.append(" ,APP_REQUEST_DTTM = null ");            // 応相談予約回答日時
			sb.append(" ,APP_REQUEST_AUTO_CXL_DTTM = null ");   // 応相談自動ｷｬﾝｾﾙ処理日時
		} else if (StringUtils.equals(reservatioKbnSet, NaikaraTalkConstants.RESERV_KBN_CON_ALREADY)) {
			// 応相談-予約確定 の場合
			sb.append(" ,APP_REQUEST_DTTM = now() ");            // 応相談予約回答日時
		} else {
			// 通常の予約、取消(通常、応相談(仮)、応相談(確定))の場合
			sb.append(" ,REQUESTED_DTTM = null ");              // 応相談予約登録日時
			sb.append(" ,APP_REQUEST_DTTM = null ");            // 応相談予約回答日時
			sb.append(" ,APP_REQUEST_AUTO_CXL_DTTM = null ");   // 応相談自動ｷｬﾝｾﾙ処理日時
		}
		// 2014/06/02 レッスン予約に関する「応相談」対応 Add End

		sb.append("where TEACHER_ID = ? ");
		sb.append("and LESSON_DT = ? ");
		sb.append("and LESSON_TM_CD = ? ");
		sb.append("and RESERVATION_KBN = ? ");

		try{

			PreparedStatement ps = conn.prepareStatement(sb.toString());

			ps.setString(1, reservatioKbnSet);

			ps.setString(2, reservationNo);
			ps.setString(3, studentId);
			ps.setString(4, courseCd);

			ps.setString(5, String.valueOf(Timestamp.valueOf(sdf.format(cal.getTime()))));
			ps.setString(6, updateId);

			ps.setString(7, teacherId);
			ps.setString(8, lessonDt);
			ps.setString(9, lessonTmCd);
			ps.setString(10, reservatioKbnWhere);

			if(ps.executeUpdate()==0){
				result = NaikaraTalkConstants.RETURN_CD_DATA_NO;
			}

		} catch ( SQLException se ) {
			throw new NaiException(se);
		}
		return result;
	}



    /**
     * 条件固定の講師予定予約テーブルデータ件数の取得<br>
     * <br>
     * 講師予定予約テーブル件数を戻り値で返却する<br>
     * <br>
     * @param teacherId
     * @param lessonDtFrom
     * @param lessonDtTo
     * @return int
     * @throws NaiException
     */
    public int rowCount(String teacherId, String lessonDtFrom, String lessonDtTo)
            throws NaiException {

        // 戻り値の初期化
        int rowCount = NaikaraTalkConstants.RETURN_CD_DATA_ERR; // データ件数

        // 引数のパラメータチェック
        if (teacherId == null || "".equals(teacherId)) {
            return rowCount;
        }
        if (lessonDtFrom == null || "".equals(lessonDtFrom)) {
            return rowCount;
        }
        if (lessonDtTo == null || "".equals(lessonDtTo)) {
            return rowCount;
        }

        ResultSet res = null;

        // 講師予定予約テーブルの全項目取得
        StringBuffer sb = new StringBuffer();
        sb.append("select");
        sb.append(" count(*) ");
        sb.append("from ");
        sb.append("SCHEDULE_RESERVATION_TRN ");
        sb.append("where TEACHER_ID = '");
        sb.append(teacherId);
        sb.append("' and NOT ( ");
        sb.append("     LESSON_DT >= '");
        sb.append(lessonDtFrom);
        sb.append("' and LESSON_DT <= '");
        sb.append(lessonDtTo);
        sb.append("' ) ");
        sb.append(" and RESERVATION_KBN <> '0' "); // 予約受付不可を除く

        try {

            PreparedStatement ps = conn.prepareStatement(sb.toString());

            //実行
            res = ps.executeQuery();

            rowCount = NaikaraTalkConstants.RETURN_CD_DATA_ERR;      // データ件数
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
            } catch (SQLException e) {
                throw new NaiException(e);
            }
        }
    }


}
