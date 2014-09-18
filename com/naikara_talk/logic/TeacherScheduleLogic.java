package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.List;

import com.naikara_talk.dao.CodeManagMstDao;
import com.naikara_talk.dao.ScheduleReservationTrnDao;
import com.naikara_talk.dao.TableCountDao;
import com.naikara_talk.dao.TeacherMstDao;
import com.naikara_talk.dao.TimeManagMstDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.ScheduleReservationTrnDto;
import com.naikara_talk.dto.TeacherMstDto;
import com.naikara_talk.dto.TimeManagMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>講師スケジュールページ。<br>
 * <b>クラス名称       :</b>講師スケジュールページLogicクラス。<br>
 * <b>クラス概要       :</b>講師スケジュール<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/09 TECS 新規作成
 */
public class TeacherScheduleLogic {

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public TeacherScheduleLogic(Connection con) {
        this.conn = con;
    }

    /**
     * 講師マスタのデータ取得処理。<br>
     * <br>
     * @param dto TeacherMstDto
     * @return dtoResult TeacherMstDto
     * @throws NaiException
     */
    public TeacherMstDto selectTeacherMst(TeacherMstDto dto) throws NaiException {

        TeacherMstDao dao = new TeacherMstDao(this.conn);

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();
        conditions.addCondition(TeacherMstDao.COND_USER_ID, ConditionMapper.OPERATOR_EQUAL, dto.getUserId());
        // 並び順
        OrderCondition orderBy = new OrderCondition();

        // 講師マスタから対象データの取得
        List<TeacherMstDto> resultDtoList = dao.search(conditions, orderBy);

        return resultDtoList.get(0);

    }

    /**
     * 講師マスタの検索データ件数取得。<br>
     * <br>
     * @param TeacherMstDto
     * @return int:DataCount
     * @throws NaiException
     */
    public int selectTeacherMstCnt(TeacherMstDto dto) throws NaiException {

        // 初期化処理
        TableCountDao dao = new TableCountDao(this.conn);

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();
        conditions.addCondition(TeacherMstDao.COND_USER_ID, ConditionMapper.OPERATOR_EQUAL, dto.getUserId());
        conditions.addCondition(TeacherMstDao.COND_CONTRACT_START_DT, ConditionMapper.OPERATOR_LESS_EQUAL, dto.getContractStartDt());
        conditions.addCondition(TeacherMstDao.COND_CONTRACT_END_DT, ConditionMapper.OPERATOR_LARGE_EQUAL, dto.getContractEndDt());

        // 一覧のデータ件数を取得する
        int DataCount = dao.rowCount(NaikaraTalkConstants.TEACHER_MST, conditions);

        // 戻り値
        return DataCount;

    }

    /**
     * 時差管理マスタのデータ取得処理。<br>
     * <br>
     * @param dto TeacherMstDto
     * @return dtoResult TeacherMstDto
     * @throws NaiException
     */
    public TimeManagMstDto selectTimeManag(TeacherMstDto dto) throws NaiException {

        TimeManagMstDao dao = new TimeManagMstDao(this.conn);

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();
        conditions.addCondition(TimeManagMstDao.COND_COUNTRY_CD, ConditionMapper.OPERATOR_EQUAL, dto.getCountryCd());
        conditions.addCondition(TimeManagMstDao.COND_AREA_NO_CD, ConditionMapper.OPERATOR_EQUAL, dto.getAreaNoCd());
        // 並び順
        OrderCondition orderBy = new OrderCondition();

        // 時差管理マスタから対象データの取得
        List<TimeManagMstDto> resultDtoList = dao.search(conditions, orderBy);

        return resultDtoList.get(0);

    }

    /**
     * コード管理マスタのデータ取得処理。<br>
     * <br>
     * @param dto selectCodeManagMstDto
     * @return resultDtoList selectCodeManagMstDtoリスト
     * @throws NaiException
     */
    public List<CodeManagMstDto> selectCodeManagMstDto() throws NaiException {

        CodeManagMstDao dao = new CodeManagMstDao(this.conn);

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();
        conditions.addCondition(CodeManagMstDao.COND_CD_CATEGORY, ConditionMapper.OPERATOR_EQUAL,
                NaikaraTalkConstants.CODE_CATEGORY_LESSON_TM_S);
        // 並び順
        OrderCondition orderBy = new OrderCondition();

        // コード管理マスタから対象データの取得
        List<CodeManagMstDto> resultDtoList = dao.search(conditions, orderBy);

        return resultDtoList;

    }

    /**
     * 講師予定予約テーブルのデータ取得処理。<br>
     * <br>
     * @param userId 講師ID
     * @param date レッスン日
     * @param time レッスン時刻
     * @return scheduleReservationTrnDto ScheduleReservationTrnDto
     * @throws NaiException
     */
    public ScheduleReservationTrnDto selectScheduleReservationTrnDto(ScheduleReservationTrnDto prmDto)
            throws NaiException {

        ScheduleReservationTrnDao dao = new ScheduleReservationTrnDao(this.conn);

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();
        conditions.addCondition(ScheduleReservationTrnDao.COND_TEACHER_ID, ConditionMapper.OPERATOR_EQUAL,
                prmDto.getTeacherId());
        conditions.addCondition(ScheduleReservationTrnDao.COND_LESSON_DT, ConditionMapper.OPERATOR_EQUAL,
                NaikaraStringUtil.converToYYYYMMDD(prmDto.getLessonDt()));
        conditions.addCondition(ScheduleReservationTrnDao.COND_LESSON_TM_CD, ConditionMapper.OPERATOR_EQUAL,
                prmDto.getLessonTmCd());
        // 並び順
        OrderCondition orderBy = new OrderCondition();

        // 講師予定予約テーブルから対象データの取得
        List<ScheduleReservationTrnDto> resultDtoList = dao.search(conditions, orderBy);

        return resultDtoList.get(0);

    }

    /**
     * 講師予定予約テーブル更新処理。<br>
     * <br>
     * @param dto ScheduleReservationTrnDto
     * @return updatedRowCount int
     * @throws NaiException
     */
    public int updateDto(ScheduleReservationTrnDto dto) throws NaiException {
        ScheduleReservationTrnDao dao = new ScheduleReservationTrnDao(this.conn);
        return dao.update(dto);

    }

    /**
     * 講師予定予約テーブル登録処理。<br>
     * <br>
     * @param dto ScheduleReservationTrnDto
     * @return updatedRowCount int
     * @throws NaiException
     */
    public int insert(ScheduleReservationTrnDto dto) throws NaiException {
        ScheduleReservationTrnDao dao = new ScheduleReservationTrnDao(this.conn);
        return dao.insert(dto);

    }
}
