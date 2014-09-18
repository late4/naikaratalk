package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.List;

import org.apache.log4j.Logger;

import com.naikara_talk.dao.CourceMstDao;
import com.naikara_talk.dao.ScheduleReservationTrnDao;
import com.naikara_talk.dao.LessonReservationPerformanceTrnDao;
import com.naikara_talk.dao.StudentMstDao;
import com.naikara_talk.dao.UserMstDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.CourseMstDto;
import com.naikara_talk.dto.ScheduleReservationTrnDto;
import com.naikara_talk.dto.LessonReservationPerformanceTrnDto;
import com.naikara_talk.dto.StudentMstDto;
import com.naikara_talk.dto.UserMstDto;

import com.naikara_talk.exception.NaiException;

import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>講師<br>
 * <b>クラス名称       :</b>応相談回答ページLogicクラス。<br>
 * <b>クラス概要       :</b>応相談回答ページで使用するLogicクラス<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2014/06/02 TECS 新規作成
 */
public class TeacherApproveRequestLogic {

    protected Logger log = Logger.getLogger(this.getClass());

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public TeacherApproveRequestLogic(Connection con) {
        this.conn = con;
    }

    /**
     * 講師予定予約テーブルのデータ取得処理。<br>
     * <br>
     * @param dto ScheduleReservationTrnDto
     * @return dtoResult List<ScheduleReservationTrnDto>
     * @throws NaiException
     */
    public List<ScheduleReservationTrnDto> selectScheduleReservationTrn(ScheduleReservationTrnDto dto)
            throws NaiException {

        // ◆◆◆ daoの生成
        ScheduleReservationTrnDao dao = new ScheduleReservationTrnDao(this.conn);

        // ◆◆◆ 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        conditions.addCondition(ScheduleReservationTrnDao.COND_TEACHER_ID,
                ConditionMapper.OPERATOR_EQUAL, dto.getTeacherId());             // 講師ID＝ログイン者

        conditions.addCondition(ScheduleReservationTrnDao.COND_LESSON_DT,
                ConditionMapper.OPERATOR_LARGE_EQUAL, DateUtil.getSysDate());    // レッスン日>=システム日付

        String[] reservKbns = {NaikaraTalkConstants.RESERV_KBN_CON_PROVISIONAL_RESERV};
        conditions.addCondition(ScheduleReservationTrnDao.COND_RESERVATION_KBN, reservKbns);
                                                                                 // 予約区分(予約状況)＝応相談予約済(仮予約)

        // ◆◆◆ 並び順
        OrderCondition orderBy = new OrderCondition();
        orderBy.addCondition(ScheduleReservationTrnDao.COND_REQUESTED_DTTM, OrderCondition.ASC);
        orderBy.addCondition(ScheduleReservationTrnDao.COND_LESSON_DT, OrderCondition.ASC);
        orderBy.addCondition(ScheduleReservationTrnDao.COND_LESSON_TM_CD, OrderCondition.ASC);

        // ◆◆◆ 講師予定予約テーブルのデータの取得
        List<ScheduleReservationTrnDto> resultDtoList = dao.search(conditions, orderBy);

        // ◆◆◆ 返却
        return resultDtoList;

    }

    /**
     * レッスン予実テーブルのデータ取得処理(主KEY検索)。<br>
     * <br>
     * @param reservationNo String
     * @return dtoResult UserMstTeacherMstDto
     * @throws NaiException
     */
    public LessonReservationPerformanceTrnDto selectLessonReservationPerformanceTrn(String reservationNo)
            throws NaiException {

        // ◆◆◆ daoの生成
        LessonReservationPerformanceTrnDao dao = new LessonReservationPerformanceTrnDao(this.conn);

        // ◆◆◆ 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        conditions.addCondition(LessonReservationPerformanceTrnDao.COND_RESERVATION_NO,
                ConditionMapper.OPERATOR_EQUAL, reservationNo);    // 予約No = 引数：予約No

        // ◆◆◆ 並び順 ※なし
        OrderCondition orderBy = new OrderCondition();

        // ◆◆◆ レッスン予実テーブルのデータの取得
        List<LessonReservationPerformanceTrnDto> resultDtoList = dao.search(conditions, orderBy);

        // ◆◆◆ 取得結果の判定と返却値の設定
        LessonReservationPerformanceTrnDto dtoResult = new LessonReservationPerformanceTrnDto();
        if (NaikaraTalkConstants.RETURN_CD_DATA_YES == resultDtoList.get(0).getReturnCode()) {
            // 正常の場合
            dtoResult = resultDtoList.get(0);
        }

        // ◆◆◆ 返却
        return dtoResult;

    }

    /**
     * コースマスタのデータ取得処理(主KEY検索)。<br>
     * <br>
     * @param courseCd String
     * @return dtoResult CourseMstDto
     * @throws NaiException
     */
    public CourseMstDto selectCourseMst(String courseCd)
            throws NaiException {

        // ◆◆◆ daoの生成
        CourceMstDao dao = new CourceMstDao(this.conn);

        // ◆◆◆ 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        conditions.addCondition(CourceMstDao.COND_COURSE_CD,
                ConditionMapper.OPERATOR_EQUAL, courseCd);    // コースコード = 引数：コースコード

        // ◆◆◆ 並び順 ※なし
        OrderCondition orderBy = new OrderCondition();

        // ◆◆◆ コースマスタのデータの取得
        List<CourseMstDto> resultDtoList = dao.search(conditions, orderBy);

        // ◆◆◆ 取得結果の判定と返却値の設定
        CourseMstDto dtoResult = new CourseMstDto();
        if (NaikaraTalkConstants.RETURN_CD_DATA_YES == resultDtoList.get(0).getReturnCode()) {
            // 正常の場合
            dtoResult = resultDtoList.get(0);
        }

        // ◆◆◆ 返却
        return dtoResult;

    }


    /**
     * 利用者マスタのデータ取得処理(主KEY検索)。<br>
     * <br>
     * @param teacherId String
     * @return dtoResult UserMstDto
     * @throws NaiException
     */
    public UserMstDto selectUserMst(String teacherId) throws NaiException {

        // ◆◆◆ daoの生成
        UserMstDao dao = new UserMstDao(this.conn);

        // ◆◆◆ 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        conditions.addCondition(UserMstDao.COND_USER_ID,
                ConditionMapper.OPERATOR_EQUAL, teacherId);    // 利用者ID = 引数：利用者ID

        // ◆◆◆ 並び順 ※なし
        OrderCondition orderBy = new OrderCondition();

        // ◆◆◆ 利用者マスタのデータの取得
        List<UserMstDto> resultDtoList = dao.search(conditions, orderBy);

        // ◆◆◆ 取得結果の判定と返却値の設定
        UserMstDto dtoResult = new UserMstDto();
        if (NaikaraTalkConstants.RETURN_CD_DATA_YES == resultDtoList.get(0).getReturnCode()) {
            dtoResult = resultDtoList.get(0);
        }

        // ◆◆◆ 返却
        return dtoResult;

    }


    /**
     * 受講者マスタのデータ取得処理(主KEY検索)。<br>
     * <br>
     * @param studentId String
     * @return dtoResult StudentMstDto
     * @throws NaiException
     */
    public StudentMstDto selectStudentMst(String studentId) throws NaiException {

        // ◆◆◆ daoの生成
        StudentMstDao dao = new StudentMstDao(this.conn);

        // ◆◆◆ 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        conditions.addCondition(StudentMstDao.COND_STUDENT_ID,
                ConditionMapper.OPERATOR_EQUAL, studentId);    // 受講者ID = 引数：受講者ID

        // ◆◆◆ 並び順 ※なし
        OrderCondition orderBy = new OrderCondition();

        // ◆◆◆ 受講者マスタのデータの取得
        List<StudentMstDto> resultDtoList = dao.search(conditions, orderBy);

        // ◆◆◆ 取得結果の判定と返却値の設定
        StudentMstDto dtoResult = new StudentMstDto();
        if (NaikaraTalkConstants.RETURN_CD_DATA_YES == resultDtoList.get(0).getReturnCode()) {
            dtoResult = resultDtoList.get(0);
        }

        // ◆◆◆ 返却
        return dtoResult;

    }

    /**
     * 講師予定予約テーブルのデータ取得処理(主KEY検索)。<br>
     * <br>
     * @param dto ScheduleReservationTrnDto
     * @return dtoResult ScheduleReservationTrnDto
     * @throws NaiException
     */
    public ScheduleReservationTrnDto searchKeyScheduleReservationTrn(ScheduleReservationTrnDto dto)
            throws NaiException {

        // ◆◆◆ 返却用のdtoの生成
        ScheduleReservationTrnDto dtoResult = new ScheduleReservationTrnDto();

        // ◆◆◆ daoの生成
        ScheduleReservationTrnDao dao = new ScheduleReservationTrnDao(this.conn);

        // ◆◆◆ 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        conditions.addCondition(ScheduleReservationTrnDao.COND_TEACHER_ID,
                ConditionMapper.OPERATOR_EQUAL, dto.getTeacherId());     // 講師ID＝ログイン者

        conditions.addCondition(ScheduleReservationTrnDao.COND_LESSON_DT,
                ConditionMapper.OPERATOR_EQUAL, dto.getLessonDt());      // レッスン日＝パラメタのレッスン日

        conditions.addCondition(ScheduleReservationTrnDao.COND_LESSON_TM_CD,
                ConditionMapper.OPERATOR_EQUAL, dto.getLessonTmCd());    // レッスン時刻コード＝パラメタのレッスン時刻コード

        // ◆◆◆ 並び順 ※なし
        OrderCondition orderBy = new OrderCondition();

        // ◆◆◆ 講師予定予約テーブルのデータの取得
        List<ScheduleReservationTrnDto> resultDtoList = dao.search(conditions, orderBy);

        // 戻り値へ値の設定
        if (resultDtoList.size() > 0) {
            // 1件分
            dtoResult = resultDtoList.get(0);
        }

        // ◆◆◆ 返却
        return dtoResult;

    }

    /**
     * 講師予定予約テーブルの更新処理。<br>
     * <br>
     * @param ScheduleReservationTrnDto
     *            画面のパラメータ
     * @param reservationKbnSet
     *            予約区分(予約状況)
     * @return int
     * @throws NaiException
     */
    public int updateScheduleReservationTrn(ScheduleReservationTrnDto dto, String reservationKbnSet) throws NaiException {
        int result = NaikaraTalkConstants.RETURN_CD_ERR_UPD;
        // DAOクラスの生成
        ScheduleReservationTrnDao dao = new ScheduleReservationTrnDao(this.conn);

        try {
            // 講師予定予約テーブルの更新処理
            result = dao.scheduleReservationTrnUpdate(dto.getStudentId(), dto.getTeacherId(), dto.getLessonDt(),
                    dto.getLessonTmCd(), reservationKbnSet, dto.getReservationKbn(),
                    dto.getReservationNo(), dto.getCourseCd(), dto.getUpdateCd());

        } catch (Exception e) {
            log.info(e);
        }
        return result;

    }

}
