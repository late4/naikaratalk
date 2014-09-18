package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.List;

import com.naikara_talk.dao.CourceMstDao;
import com.naikara_talk.dao.CourseUsePointMstDao;
import com.naikara_talk.dao.LessonReservationPerformanceTrnDao;
import com.naikara_talk.dao.PointOwnershipTrnDao;
import com.naikara_talk.dao.ScheduleReservationTrnDao;
import com.naikara_talk.dao.StudentMstDao;
import com.naikara_talk.dao.TeacherCourseDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.CourseMstDto;
import com.naikara_talk.dto.CourseUsePointMstDto;
import com.naikara_talk.dto.LessonReservationPerformanceTrnDto;
import com.naikara_talk.dto.PointOwnershipTrnDto;
import com.naikara_talk.dto.ScheduleReservationTrnDto;
import com.naikara_talk.dto.StudentMstDto;
import com.naikara_talk.dto.TeacherCourseDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_予約処理<br>
 * <b>クラス名称       :</b>コース名選択ページLogicクラス<br>
 * <b>クラス概要       :</b>受講者が指定した講師の予約スケジュールを表示して、レッスン予約の登録／取消ができる。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/29 TECS 新規作成
 */
public class ReservationCancellationCourseSelectionListLogic {

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public ReservationCancellationCourseSelectionListLogic(Connection con) {
        this.conn = con;
    }

    /**
     * 講師別コースマスタ(+コースマスタ)リスト取得<br>
     * <br>
     * 講師別コースマスタ(+コースマスタ)リスト取得を行う<br>
     * <br>
     * @param dto 講師別コースマスタ(+コースマスタ)DTO<br>
     * @return List<TeacherCourseDto> 講師別コースマスタ(+コースマスタ)DTOリスト<br>
     * @throws NaiException
     */
    public List<TeacherCourseDto> selectTeacherCourseList(TeacherCourseDto dto) throws NaiException {

        // 初期化
        TeacherCourseDao dao = new TeacherCourseDao(this.conn);

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // 利用者ID
        conditions.addCondition(TeacherCourseDao.COND_USER_ID, ConditionMapper.OPERATOR_EQUAL, dto.getUserId());
        // 提供開始日
        conditions.addCondition(TeacherCourseDao.COND_USE_STR_DT, ConditionMapper.OPERATOR_LESS_EQUAL,
                dto.getUseStrDt());
        // 提供終了日
        conditions.addCondition(TeacherCourseDao.COND_USE_END_DT, ConditionMapper.OPERATOR_LARGE_EQUAL,
                dto.getUseEndDt());

        // 並び順
        OrderCondition orderBy = new OrderCondition();

        // データを取得する
        return dao.search(conditions, orderBy);
    }

    /**
     * コースマスタデータの取得<br>
     * <br>
     * コースマスタデータの取得を行う<br>
     * <br>
     * @param dto コースマスタDTO<br>
     * @return CourseMstDto コースマスタDTO<br>
     * @throws NaiException
     */
    public CourseMstDto selectCourseMst(CourseMstDto dto) throws NaiException {

        // 初期化
        CourceMstDao dao = new CourceMstDao(this.conn);

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // コースコード
        conditions.addCondition(CourceMstDao.COND_COURSE_CD, ConditionMapper.OPERATOR_EQUAL, dto.getCourseCd());

        // 並び順
        OrderCondition orderBy = new OrderCondition();

        // データを取得する
        List<CourseMstDto> retDtoList = dao.search(conditions, orderBy);

        return retDtoList.get(0);
    }

    /**
     * 講師予定予約テーブルのデータ取得処理。<br>
     * <br>
     * 講師予定予約テーブルのデータ取得処理を行う。<br>
     * <br>
     * @param dto 講師予定予約テーブルDTO<br>
     * @return ScheduleReservationTrnDto 講師予定予約テーブルDTO<br>
     * @throws NaiException
     */
    public ScheduleReservationTrnDto selectScheduleReservationTrn(ScheduleReservationTrnDto dto) throws NaiException {

        // 初期化
        ScheduleReservationTrnDao dao = new ScheduleReservationTrnDao(this.conn);

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // 講師予定予約テーブル．講師ID
        conditions.addCondition(ScheduleReservationTrnDao.COND_TEACHER_ID, ConditionMapper.OPERATOR_EQUAL,
                dto.getTeacherId());
        // 講師予定予約テーブル．レッスン日
        conditions.addCondition(ScheduleReservationTrnDao.COND_LESSON_DT, ConditionMapper.OPERATOR_EQUAL,
                dto.getLessonDt());
        // 講師予定予約テーブル．レッスン時刻
        conditions.addCondition(ScheduleReservationTrnDao.COND_LESSON_TM_CD, ConditionMapper.OPERATOR_EQUAL,
                dto.getLessonTmCd());

        // 並び順
        OrderCondition orderBy = new OrderCondition();

        // データを取得する
        return dao.search(conditions, orderBy).get(0);
    }

    /**
     * コース別利用ポイントマスタのデータ取得処理。<br>
     * <br>
     * コース別利用ポイントマスタのデータ取得処理を行う。<br>
     * <br>
     * @param dto コース別利用ポイントマスタDTO<br>
     * @return List<CourseUsePointMstDto> コース別利用ポイントマスタDTOリスト<br>
     * @throws NaiException
     */
    public List<CourseUsePointMstDto> selectCourseUsePointMstList(CourseUsePointMstDto dto) throws NaiException {

        // 初期化
        CourseUsePointMstDao dao = new CourseUsePointMstDao(this.conn);

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // コース別利用ポイントマスタ．コースコード
        conditions.addCondition(CourseUsePointMstDao.COND_COURSE_CD, ConditionMapper.OPERATOR_EQUAL, dto.getCourseCd());
        // コース別利用ポイントマスタ．利用ポイント開始日
        conditions.addCondition(CourseUsePointMstDao.COND_USE_POINT_STR_DT, ConditionMapper.OPERATOR_LESS_EQUAL,
                dto.getUsePointStrDt());
        // コース別利用ポイントマスタ．利用ポイント終了日
        conditions.addCondition(CourseUsePointMstDao.COND_USE_POINT_END_DT, ConditionMapper.OPERATOR_LARGE_EQUAL,
                dto.getUsePointEndDt());

        // 並び順
        OrderCondition orderBy = new OrderCondition();

        // データを取得する
        return dao.search(conditions, orderBy);
    }

    /**
     * レッスン予実テーブルデータ取得<br>
     * <br>
     * レッスン予実テーブルリストを戻り値で返却する<br>
     * <br>
     * @param reservationNo 予約番号<br>
     * @return LessonReservationPerformanceTrnDto レッスン予実テーブルDTOリスト<br>
     * @throws NaiException
     */
    public LessonReservationPerformanceTrnDto selectLessonReservationPerformanceTrn(String reservationNo)
            throws NaiException {

        // 初期化
        LessonReservationPerformanceTrnDao dao = new LessonReservationPerformanceTrnDao(this.conn);

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // 予約番号
        conditions.addCondition(LessonReservationPerformanceTrnDao.COND_RESERVATION_NO, ConditionMapper.OPERATOR_EQUAL,
                reservationNo);

        // 並び順
        OrderCondition orderBy = new OrderCondition();

        // データを取得する
        return dao.search(conditions, orderBy).get(0);
    }

    /**
     * ポイント所有テーブルデータ取得<br>
     * <br>
     * ポイント所有テーブルリストを戻り値で返却する<br>
     * <br>
     * @param なし<br>
     * @return List<PointOwnershipTrnDto> ポイン所有テーブルDTOリスト<br>
     * @throws NaiException
     */
    public List<PointOwnershipTrnDto> selectPointOwnershipTrnList() throws NaiException {

        // ユーザIDを取得
        String userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();

        // 初期化
        PointOwnershipTrnDao dao = new PointOwnershipTrnDao(this.conn);

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // ポイント所有テーブル 受講者ID ＝ ログイン情報：ユーザID
        conditions.addCondition(PointOwnershipTrnDao.COND_STUDENT_ID, ConditionMapper.OPERATOR_EQUAL, userId);
        // ポイント所有テーブル 有効開始日 ≦ 現時点のサーバーのシステム日付
        conditions.addCondition(PointOwnershipTrnDao.COND_EFFECTIVE_STR_DT, ConditionMapper.OPERATOR_LESS_EQUAL,
                DateUtil.getSysDate());
        // ポイント所有テーブル 有効終了日 ≧ 現時点のサーバーのシステム日付
        conditions.addCondition(PointOwnershipTrnDao.COND_EFFECTIVE_END_DT, ConditionMapper.OPERATOR_LARGE_EQUAL,
                DateUtil.getSysDate());

        // 並び順
        OrderCondition orderBy = new OrderCondition();

        // データを取得する
        return dao.searchWithOr(conditions, orderBy);
    }


    /**
     * 講師予定予約テーブルのデータ取得処理（選択されていない講師）。<br>
     * <br>
     * 講師予定予約テーブルのデータ取得処理を行う。<br>
     * <br>
     * @param dto 講師予定予約テーブルDTO<br>
     * @return List<ScheduleReservationTrnDto> 講師予定予約テーブルDTOリスト<br>
     * @throws NaiException
     */
    public List<ScheduleReservationTrnDto> selectScheduleReservationTrnNoSelectTList(ScheduleReservationTrnDto dto)
            throws NaiException {

        // 初期化
        ScheduleReservationTrnDao dao = new ScheduleReservationTrnDao(this.conn);

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // 講師予定予約テーブル．講師ID
        conditions.addCondition(ScheduleReservationTrnDao.COND_TEACHER_ID, ConditionMapper.OPERATOR_NOT_EQUAL,
                dto.getTeacherId());
        // 講師予定予約テーブル．レッスン日
        conditions.addCondition(ScheduleReservationTrnDao.COND_LESSON_DT, ConditionMapper.OPERATOR_EQUAL,
                dto.getLessonDt());
        // 講師予定予約テーブル．レッスン時刻
        conditions.addCondition(ScheduleReservationTrnDao.COND_LESSON_TM_CD, ConditionMapper.OPERATOR_EQUAL,
                dto.getLessonTmCd());
        // 講師予定予約テーブル．受講者ID
        conditions.addCondition(ScheduleReservationTrnDao.COND_STUDENT_ID, ConditionMapper.OPERATOR_EQUAL,
                dto.getStudentId());

        // 並び順
        OrderCondition orderBy = new OrderCondition();

        // データを取得する
        return dao.search(conditions, orderBy);
    }

    // 2014/06/02 レッスン予約に関する「応相談」対応 Add Start
    /**
     * 受講者マスタデータの取得<br>
     * <br>
     * 受講者マスタデータの取得を行う<br>
     * <br>
     * @param dto 受講者マスタDTO<br>
     * @return StudentMstDto 受講者マスタDTO<br>
     * @throws NaiException
     */
    public StudentMstDto selectStudentMst(StudentMstDto dto) throws NaiException {

        // 返却用のDtoの生成
        StudentMstDto dtoResult = new StudentMstDto();

        // Daoの生成
        StudentMstDao dao = new StudentMstDao(this.conn);

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // 検索条件の設定:受講者ID
        conditions.addCondition(StudentMstDao.COND_STUDENT_ID, ConditionMapper.OPERATOR_EQUAL, dto.getStudentId());

        // 並び順
        OrderCondition orderBy = new OrderCondition();

        // データの取得
        List<StudentMstDto> retDtoList = dao.search(conditions, orderBy);

        // 戻り値へ値の設定
        if (retDtoList.size() > 0) {
            // 1件分
            dtoResult = retDtoList.get(0);
        }

        return dtoResult;
    }
    // 2014/06/02 レッスン予約に関する「応相談」対応 Add End

}
