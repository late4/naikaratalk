package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.List;

import com.naikara_talk.dao.CodeManagMstDao;
import com.naikara_talk.dao.CourceMstDao;
import com.naikara_talk.dao.ScheduleReservationTrnDao;
import com.naikara_talk.dao.StudentMstDao;
import com.naikara_talk.dao.UserMstDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.CourseMstDto;
import com.naikara_talk.dto.ScheduleReservationTrnDto;
import com.naikara_talk.dto.StudentMstDto;
import com.naikara_talk.dto.UserMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_予約処理<br>
 * <b>クラス名称       :</b>予約／取消確認ページLogicクラス<br>
 * <b>クラス概要       :</b>受講者が指定した講師の予約スケジュールを表示して、レッスン予約の登録／取消ができる。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/02 TECS 新規作成
 * <b>                 :</b>2014/06/02 TECS レッスン予約に関する「応相談」対応
 */
public class SComReservationCancellationConfirmationLogic {

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public SComReservationCancellationConfirmationLogic(Connection con) {
        this.conn = con;
    }

    /**
     * 受講者マスタデータ取得<br>
     * <br>
     * 受講者マスタリストを戻り値で返却する<br>
     * <br>
     * @param studentId 受講者ID<br>
     * @return StudentMstDto 受講者マスタDTO<br>
     * @throws NaiException
     */
    public StudentMstDto selectStudentMst(String studentId) throws NaiException {

        // 初期化
        StudentMstDao dao = new StudentMstDao(this.conn);

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // 受講者ID
        conditions.addCondition(StudentMstDao.COND_STUDENT_ID, ConditionMapper.OPERATOR_EQUAL, studentId);

        // 並び順
        OrderCondition orderBy = new OrderCondition();

        // データを取得する
        return dao.search(conditions, orderBy).get(0);
    }

    /**
     * 受講者マスタデータ取得<br>
     * <br>
     * 受講者マスタリストを戻り値で返却する<br>
     * <br>
     * @param teacherId 講師ID<br>
     * @return UserMstDto 利用者マスタDTO<br>
     * @throws NaiException
     */
    public UserMstDto selectUserMst(String teacherId) throws NaiException {

        // 初期化
        UserMstDao dao = new UserMstDao(this.conn);

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // 利用者ID
        conditions.addCondition(UserMstDao.COND_USER_ID, ConditionMapper.OPERATOR_EQUAL, teacherId);

        // 並び順
        OrderCondition orderBy = new OrderCondition();

        // データを取得する
        return dao.search(conditions, orderBy).get(0);
    }

    /**
     * コースマスタデータ取得<br>
     * <br>
     * コースマスタリストを戻り値で返却する<br>
     * <br>
     * @param courseCd コースコード<br>
     * @return CourseMstDto コースマスタDTO<br>
     * @throws NaiException
     */
    public CourseMstDto selectCourseMst(String courseCd) throws NaiException {

        // 初期化
        CourceMstDao dao = new CourceMstDao(this.conn);

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // コースコード
        conditions.addCondition(CourceMstDao.COND_COURSE_CD, ConditionMapper.OPERATOR_EQUAL, courseCd);

        // 並び順
        OrderCondition orderBy = new OrderCondition();

        // データを取得する
        return dao.search(conditions, orderBy).get(0);
    }

    /**
     * コード管理マスタデータ取得<br>
     * <br>
     * コード管理マスタリストを戻り値で返却する<br>
     * <br>
     * @param categoryCd 種別コード<br>
     * @param managerCd 汎用コード<br>
     * @return CodeManagMstDto コード管理マスタDTO<br>
     * @throws NaiException
     */
    public CodeManagMstDto selectCodeManagMst(String categoryCd, String managerCd) throws NaiException {

        // 初期化
        CodeManagMstDao dao = new CodeManagMstDao(this.conn);

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // コード種別
        conditions.addCondition(CodeManagMstDao.COND_CD_CATEGORY, ConditionMapper.OPERATOR_EQUAL, categoryCd);

        // 汎用コード
        conditions.addCondition(CodeManagMstDao.COND_MANAGER_CD, ConditionMapper.OPERATOR_EQUAL, managerCd);

        // データを取得する
        return dao.search(conditions).get(0);
    }


    // 2014/06/02 レッスン予約に関する「応相談」対応 Add Start
    /**
     * 講師予定予約テーブルのデータ取得処理(主KEY検索)。<br>
     * <br>
     * @param teacherId  講師ID
     * @param lessonDt   レッスン日
     * @param lessonTmCd レッスン時刻コード
     * @return dtoResult ScheduleReservationTrnDto
     * @throws NaiException
     */
    public ScheduleReservationTrnDto selectScheduleReservationTrn(String teacherId, String lessonDt, String lessonTmCd)
            throws NaiException {

        // ◆◆◆ daoの生成
        ScheduleReservationTrnDao dao = new ScheduleReservationTrnDao(this.conn);

        // ◆◆◆ 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        conditions.addCondition(ScheduleReservationTrnDao.COND_TEACHER_ID,
                ConditionMapper.OPERATOR_EQUAL, teacherId);           // 講師ID＝ログイン者

        conditions.addCondition(ScheduleReservationTrnDao.COND_LESSON_DT,
                ConditionMapper.OPERATOR_LARGE_EQUAL, lessonDt);      // レッスン日＝レッスン日

        conditions.addCondition(ScheduleReservationTrnDao.COND_LESSON_TM_CD,
                ConditionMapper.OPERATOR_LARGE_EQUAL, lessonTmCd);    // レッスン時刻コード＝レッスン時刻コード


        // ◆◆◆ 並び順
        OrderCondition orderBy = new OrderCondition();

        // ◆◆◆ 講師予定予約テーブルのデータの取得
        List<ScheduleReservationTrnDto> resultDtoList = dao.search(conditions, orderBy);

        // ◆◆◆ 取得結果の判定と返却値の設定
        ScheduleReservationTrnDto dtoResult = new ScheduleReservationTrnDto();
        if (NaikaraTalkConstants.RETURN_CD_DATA_YES == resultDtoList.get(0).getReturnCode()) {
            dtoResult = resultDtoList.get(0);
        }

        // ◆◆◆ 返却
        return dtoResult;

    }
    // 2014/06/02 レッスン予約に関する「応相談」対応 Add End


}
