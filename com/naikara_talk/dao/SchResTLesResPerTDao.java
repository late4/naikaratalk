package com.naikara_talk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dbutil.QueryCondition;
import com.naikara_talk.dto.SchResTLesResPerTDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DAOクラス<br>
 * <b>クラス名称　　　:</b>講師予定予約テーブル、レッスン予実テーブルデータアクセスクラス<br>
 * <b>クラス概要　　　:</b>講師予定予約テーブル、レッスン予実テーブルの結合データを取得する<br>
 * <br>
 * <b>著作権　　　　　:</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/30 TECS 新規作成
 */
public class SchResTLesResPerTDao extends AbstractDao {

    /** 講師ID（講師予定予約テーブル） 条件項目 */
    public static final String COND_SRT_TEACHER_ID = "SRT.TEACHER_ID";
    /** レッスン日（講師予定予約テーブル） 条件項目 */
    public static final String COND_SRT_LESSON_DT = "SRT.LESSON_DT";
    /** レッスン時刻コード（講師予定予約テーブル） 条件項目 */
    public static final String COND_SRT_LESSON_TM_CD = "SRT.LESSON_TM_CD";

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public SchResTLesResPerTDao(Connection con) {
        this.conn = con;
    }

    /**
     * 講師予定予約テーブル、レッスン予実テーブルデータ取得<br>
     * <br>
     * 講師予定予約テーブル、レッスン予実テーブルリストを戻り値で返却する<br>
     * <br>
     * @param conditional
     * @param orderConditional
     * @return SchResTLesResPerTDto
     * @throws NaiException
     */
    public List<SchResTLesResPerTDto> search(ConditionMapper conditions, OrderCondition orderConditions)
            throws NaiException {

        ResultSet res = null;

        // 講師予定予約テーブルの全項目取得
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT");
        sb.append(" SRT.TEACHER_ID ");
        sb.append(",SRT.LESSON_DT ");
        sb.append(",SRT.LESSON_TM_CD ");
        sb.append(",SRT.RESERVATION_KBN ");
        sb.append(",SRT.RESERVATION_NO ");
        sb.append(",SRT.STUDENT_ID ");
        sb.append(",SRT.COURSE_CD ");
        sb.append(",SRT.RECORD_VER_NO AS RECORD_VER_NO1 ");
        sb.append(",LRPT.RECORD_VER_NO AS RECORD_VER_NO2 ");
        sb.append("FROM ");
        sb.append("SCHEDULE_RESERVATION_TRN SRT ");                  // 講師予定予約テーブル
        sb.append("LEFT JOIN ");
        sb.append("LESSON_RESERVATION_PERFORMANCE_TRN LRPT ");       // レッスン予実テーブル
        sb.append("ON ");
        sb.append("SRT.RESERVATION_NO = LRPT.RESERVATION_NO ");     // 講師予定予約テーブル．予約No 左結合 レッスン予実テーブル．予約No

        if (!StringUtils.isEmpty(conditions.getConditionString())) {
            sb.append("WHERE ");
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

            ArrayList<SchResTLesResPerTDto> list = new ArrayList<SchResTLesResPerTDto>();

            while (res.next()) {

                SchResTLesResPerTDto retDto = new SchResTLesResPerTDto();

                retDto.setTeacherId(res.getString("TEACHER_ID"));
                retDto.setLessonDt(res.getString("LESSON_DT"));
                retDto.setLessonTmCd(res.getString("LESSON_TM_CD"));
                retDto.setReservationKbn(res.getString("RESERVATION_KBN"));
                retDto.setReservationNo(res.getString("RESERVATION_NO"));
                retDto.setStudentId(res.getString("STUDENT_ID"));
                retDto.setCourseCd(res.getString("COURSE_CD"));
                retDto.setRecordVerNo1(res.getInt("RECORD_VER_NO1"));
                retDto.setRecordVerNo2(res.getInt("RECORD_VER_NO2"));

                retDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);

                list.add(retDto);
            }

            if (list.size() < 1) {
                SchResTLesResPerTDto retDto = new SchResTLesResPerTDto();
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

}
