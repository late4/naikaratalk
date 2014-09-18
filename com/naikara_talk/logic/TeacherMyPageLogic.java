package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.List;

import com.naikara_talk.dao.CodeManagMstDao;
import com.naikara_talk.dao.LessonReservationPerformanceTrnDao;
import com.naikara_talk.dao.ScheduleReservationTrnDao;
import com.naikara_talk.dao.StudentMstDao;
import com.naikara_talk.dao.TeacherMstDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.LessonReservationPerformanceTrnDto;
import com.naikara_talk.dto.ScheduleReservationTrnDto;
import com.naikara_talk.dto.StudentMstDto;
import com.naikara_talk.dto.TeacherMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>講師初期登録ページ。<br>
 * <b>クラス名称       :</b>講師初期登録ページ初期処理Logicクラス。<br>
 * <b>クラス概要       :</b>講師が下記の情報照会ができる<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/09 TECS 新規作成
 * <b>                 :</b>2014/06/02 TECS レッスン予約に関する「応相談」対応
 */
public class TeacherMyPageLogic {

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public TeacherMyPageLogic(Connection con) {
        this.conn = con;
    }

    /**
     * 講師マスタのデータ取得処理。<br>
     * <br>
     * @param dto UserMstTeacherMstDto
     * @return dtoResult UserMstTeacherMstDto
     * @throws NaiException
     */
    public TeacherMstDto selectTeacherMst(TeacherMstDto dto) throws NaiException {

        TeacherMstDao dao = new TeacherMstDao(this.conn);

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();
        conditions.addCondition(TeacherMstDao.COND_USER_ID, ConditionMapper.OPERATOR_EQUAL, dto.getUserId());
        // 並び順
        OrderCondition orderBy = new OrderCondition();

        // 利用者マスタ、講師マスタから対象データの取得
        List<TeacherMstDto> resultDtoList = dao.search(conditions, orderBy);

        TeacherMstDto dtoResult = new TeacherMstDto();
        // 設定
        if (NaikaraTalkConstants.RETURN_CD_DATA_YES == resultDtoList.get(0).getReturnCode()) {
            dtoResult = resultDtoList.get(0);
        }
        return dtoResult;

    }

    /**
     * 講師予定予約テーブルのデータ取得処理。<br>
     * <br>
     * @param dto UserMstTeacherMstDto
     * @return dtoResult UserMstTeacherMstDto
     * @throws NaiException
     */
    public List<ScheduleReservationTrnDto> selectScheduleReservationTrnDto(ScheduleReservationTrnDto dto)
            throws NaiException {

        ScheduleReservationTrnDao dao = new ScheduleReservationTrnDao(this.conn);

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // 講師ID＝ログイン者
        conditions.addCondition(ScheduleReservationTrnDao.COND_TEACHER_ID, ConditionMapper.OPERATOR_EQUAL,
                dto.getTeacherId());
        // レッスン日＝システム日付
        conditions.addCondition(ScheduleReservationTrnDao.COND_LESSON_DT, ConditionMapper.OPERATOR_LARGE_EQUAL,
                DateUtil.getSysDate());

        // 2014/06/02 レッスン予約に関する「応相談」対応 Upd Start
        // 予約区分(予約状況)＝予約済
        //conditions.addCondition(ScheduleReservationTrnDao.COND_RESERVATION_KBN, ConditionMapper.OPERATOR_EQUAL,
        //        NaikaraTalkConstants.RESERV_KBN_ALREADY);
        // 予約区分(予約状況)＝予約済 又は 応相談予約済(仮予約) 又は 応相談予約済(予約確定)
        String[] reservKbns = {NaikaraTalkConstants.RESERV_KBN_ALREADY,
        		NaikaraTalkConstants.RESERV_KBN_CON_PROVISIONAL_RESERV,
        		NaikaraTalkConstants.RESERV_KBN_CON_ALREADY};
        conditions.addCondition(ScheduleReservationTrnDao.COND_RESERVATION_KBN, reservKbns);
        // 2014/06/02 レッスン予約に関する「応相談」対応 Upd End


        // 並び順
        OrderCondition orderBy = new OrderCondition();

        // 利用者マスタ、講師マスタから対象データの取得
        List<ScheduleReservationTrnDto> resultDtoList = dao.search(conditions, orderBy);

        return resultDtoList;

    }

    /**
     * レッスン予実テーブルのデータ取得処理。<br>
     * <br>
     * @param dto UserMstTeacherMstDto
     * @return dtoResult UserMstTeacherMstDto
     * @throws NaiException
     */
    public LessonReservationPerformanceTrnDto selectLessonReservationPerformanceTrnDto(String reservationNo)
            throws NaiException {

        LessonReservationPerformanceTrnDao dao = new LessonReservationPerformanceTrnDao(this.conn);

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();
        conditions.addCondition(LessonReservationPerformanceTrnDao.COND_RESERVATION_NO, ConditionMapper.OPERATOR_EQUAL,
                reservationNo);
        // 並び順
        OrderCondition orderBy = new OrderCondition();

        // 利用者マスタ、講師マスタから対象データの取得
        List<LessonReservationPerformanceTrnDto> resultDtoList = dao.search(conditions, orderBy);
        LessonReservationPerformanceTrnDto dtoResult = new LessonReservationPerformanceTrnDto();
        // 設定
        if (NaikaraTalkConstants.RETURN_CD_DATA_YES == resultDtoList.get(0).getReturnCode()) {
            dtoResult = resultDtoList.get(0);
        }
        return dtoResult;

    }

    /**
     * 受講者マスタのデータ取得処理。<br>
     * <br>
     * @param dto UserMstTeacherMstDto
     * @return dtoResult UserMstTeacherMstDto
     * @throws NaiException
     */
    public StudentMstDto selectStudentMstDto(String studentId) throws NaiException {

        StudentMstDao dao = new StudentMstDao(this.conn);

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();
        conditions.addCondition(StudentMstDao.COND_STUDENT_ID, ConditionMapper.OPERATOR_EQUAL, studentId);
        // 並び順
        OrderCondition orderBy = new OrderCondition();

        // 利用者マスタ、講師マスタから対象データの取得
        List<StudentMstDto> resultDtoList = dao.search(conditions, orderBy);
        StudentMstDto dtoResult = new StudentMstDto();
        // 設定
        if (NaikaraTalkConstants.RETURN_CD_DATA_YES == resultDtoList.get(0).getReturnCode()) {
            dtoResult = resultDtoList.get(0);
        }
        return dtoResult;

    }

    /**
     * 問合せメールアドレス(講師用個別マイページ用)取得処理。<br>
     * <br>
     * @param なし
     * @return dtoResult CodeManagMstDto
     * @throws NaiException
     */
    public CodeManagMstDto selectCodeManagMstDto(String cdCategory) throws NaiException {

        CodeManagMstDao dao = new CodeManagMstDao(this.conn);
        // 日本時刻の取得
        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();
        conditions.addCondition(CodeManagMstDao.COND_CD_CATEGORY, ConditionMapper.OPERATOR_EQUAL, cdCategory);
        // 並び順
        OrderCondition orderBy = new OrderCondition();

        // コード管理マスタから対象データの取得
        List<CodeManagMstDto> resultDtoList = dao.search(conditions, orderBy);

        CodeManagMstDto dtoResult = new CodeManagMstDto();
        // 設定
        if (NaikaraTalkConstants.RETURN_CD_DATA_YES == resultDtoList.get(0).getReturnCode()) {
            dtoResult = resultDtoList.get(0);
        }
        return dtoResult;

    }
}
