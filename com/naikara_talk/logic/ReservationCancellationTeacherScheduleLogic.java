package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.naikara_talk.dao.CodeManagMstDao;
import com.naikara_talk.dao.SchResTLesResPerTDao;
import com.naikara_talk.dao.ScheduleReservationTrnDao;
import com.naikara_talk.dao.TeacherCourseDao;
import com.naikara_talk.dao.UserMstTeacherMstDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.SchResTLesResPerTDto;
import com.naikara_talk.dto.ScheduleReservationTrnDto;
import com.naikara_talk.dto.TeacherCourseDto;
import com.naikara_talk.dto.UserMstTeacherMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_予約処理<br>
 * <b>クラス名称       :</b>予約スケジュール初期化Logicクラス<br>
 * <b>クラス概要       :</b>受講者が指定した講師の予約スケジュールを表示して、レッスン予約の登録／取消ができる。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/25 TECS 新規作成
 */
public class ReservationCancellationTeacherScheduleLogic {

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public ReservationCancellationTeacherScheduleLogic(Connection con) {
        this.conn = con;
    }

    /**
     * 利用者マスタ、受講者マスタデータ取得。<br>
     * <br>
     * 利用者マスタ、受講者マスタリストを戻り値で返却する。<br>
     * <br>
     * @param dto 利用者マスタ、講師マスタデータ取得DTO<br>
     * @return UserMstTeacherMstDto 利用者マスタ、講師マスタデータ取得DTO<br>
     * @throws NaiException
     */
    public UserMstTeacherMstDto selectUserMstTeacherMst(UserMstTeacherMstDto dto) throws NaiException {

        // 初期化
        List<UserMstTeacherMstDto> retDtoList = new ArrayList<UserMstTeacherMstDto>();

        UserMstTeacherMstDao dao = new UserMstTeacherMstDao(this.conn);

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // 利用者ID
        conditions.addCondition(UserMstTeacherMstDao.COND_USER_ID, ConditionMapper.OPERATOR_EQUAL, dto.getUserId());

        // 並び順
        OrderCondition orderBy = new OrderCondition();

        // データを取得する
        retDtoList = dao.search(conditions, orderBy);

        UserMstTeacherMstDto retDto = new UserMstTeacherMstDto();

        // 返却値の判定
        if (retDtoList.get(0).getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_YES) {
            retDto = retDtoList.get(0);
        }

        return retDto;
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
     * コード管理マスタデータ取得<br>
     * <br>
     * コード管理マスタリストを戻り値で返却する<br>
     * @param なし<br>
     * @return List<CodeManagMstDto> コード管理マスタDTOリスト<br>
     * @throws NaiException
     */
    public List<CodeManagMstDto> selectCodeManagMstList() throws NaiException {

        CodeManagMstDao dao = new CodeManagMstDao(this.conn);

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();
        conditions.addCondition(CodeManagMstDao.COND_CD_CATEGORY, ConditionMapper.OPERATOR_EQUAL,
                NaikaraTalkConstants.CODE_CATEGORY_LESSON_TM_S);
        // 並び順
        OrderCondition orderBy = new OrderCondition();

        // データの取得
        return dao.search(conditions, orderBy);
    }

    /**
     * 講師予定予約テーブル、レッスン予実テーブルデータ取得<br>
     * <br>
     * 講師予定予約テーブル、レッスン予実テーブルリストを戻り値で返却する<br>
     * <br>
     * @param teacherId 講師ID<br>
     * @param lessonD レッスン日t<br>
     * @param lessonTmCd レッスン時刻コード <br>
     * @return SchResTLesResPerTDto 講師予定予約テーブル、レッスン予実テーブルDTO<br>
     * @throws NaiException
     */
    public SchResTLesResPerTDto selectSchResTLesResPerTDto(String teacherId, String lessonDt, String lessonTmCd)
            throws NaiException {

        SchResTLesResPerTDao dao = new SchResTLesResPerTDao(this.conn);

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();
        conditions.addCondition(SchResTLesResPerTDao.COND_SRT_TEACHER_ID, ConditionMapper.OPERATOR_EQUAL, teacherId);
        conditions.addCondition(SchResTLesResPerTDao.COND_SRT_LESSON_DT, ConditionMapper.OPERATOR_EQUAL,
                NaikaraStringUtil.delSlash(lessonDt));
        conditions.addCondition(SchResTLesResPerTDao.COND_SRT_LESSON_TM_CD, ConditionMapper.OPERATOR_EQUAL, lessonTmCd);

        // 並び順
        OrderCondition orderBy = new OrderCondition();

        // 利用者マスタ、講師マスタから対象データの取得
        List<SchResTLesResPerTDto> resultDtoList = dao.search(conditions, orderBy);

        SchResTLesResPerTDto retDto = new SchResTLesResPerTDto();

        // 返却値の判定
        if (NaikaraTalkConstants.RETURN_CD_DATA_YES == resultDtoList.get(0).getReturnCode()) {
            retDto = resultDtoList.get(0);
        }

        return retDto;
    }

    /**
     * 講師予定予約テーブルデータ取得<br>
     * <br>
     * 講師予定予約テーブルリストを戻り値で返却する<br>
     * <br>
     * @param teacherId 講師ID<br>
     * @param lessonD レッスン日t<br>
     * @param lessonTmCd レッスン時刻コード <br>
     * @return ScheduleReservationTrnDto 講師予定予約テーブルDTO<br>
     * @throws NaiException
     */
    public ScheduleReservationTrnDto selectScheduleReservationTrn(String teacherId, String lessonDt, String lessonTmCd)
            throws NaiException {

        ScheduleReservationTrnDao dao = new ScheduleReservationTrnDao(this.conn);

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();
        conditions.addCondition(ScheduleReservationTrnDao.COND_TEACHER_ID, ConditionMapper.OPERATOR_EQUAL, teacherId);
        conditions.addCondition(ScheduleReservationTrnDao.COND_LESSON_DT, ConditionMapper.OPERATOR_EQUAL,
                NaikaraStringUtil.delSlash(lessonDt));
        conditions
                .addCondition(ScheduleReservationTrnDao.COND_LESSON_TM_CD, ConditionMapper.OPERATOR_EQUAL, lessonTmCd);

        // 並び順
        OrderCondition orderBy = new OrderCondition();

        // 利用者マスタ、講師マスタから対象データの取得
        List<ScheduleReservationTrnDto> resultDtoList = dao.search(conditions, orderBy);

        return resultDtoList.get(0);

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


}
