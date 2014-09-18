package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.naikara_talk.dao.CodeManagMstDao;
import com.naikara_talk.dao.DmHistoryDetailsTrnDao;
import com.naikara_talk.dao.DmHistoryTrnDao;
import com.naikara_talk.dao.LessonReservationPerformanceTrnDao;
import com.naikara_talk.dao.MailManagMstDao;
import com.naikara_talk.dao.StudentMstDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.DmHistoryDetailsTrnDto;
import com.naikara_talk.dto.DmHistoryTrnDto;
import com.naikara_talk.dto.LessonReservationPerformanceTrnDto;
import com.naikara_talk.dto.MailManagMstDto;
import com.naikara_talk.dto.StudentMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>バッチ<br>
 * <b>クラス名称       :</b>予約取消・購入のメール送信処理Logicクラス<br>
 * <b>クラス概要       :</b>各機能より起動されてＥメール送信処理を行う。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/04 TECS 新規作成
 */
public class SendMailLogic {

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public SendMailLogic(Connection con) {
        this.conn = con;
    }

    /**
     * レッスン予実テーブルデータの取得<br>
     * <br>
     * レッスン予実テーブルへ検索処理を行う。<br>
     * <br>
     * @param dto レッスン予実テーブルDTO<br>
     * @return List<LessonReservationPerformanceTrnDto> レッスン予実テーブルDTOリスト<br>
     * @exception NaiException
     */
    public List<LessonReservationPerformanceTrnDto> selectLessonReservationPerformanceTrnList(
            LessonReservationPerformanceTrnDto dto) throws NaiException {

        // 初期化処理
        List<LessonReservationPerformanceTrnDto> list = new ArrayList<LessonReservationPerformanceTrnDto>();

        LessonReservationPerformanceTrnDao dao = new LessonReservationPerformanceTrnDao(this.conn);

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // 状態区分
        conditions.addCondition(LessonReservationPerformanceTrnDao.COND_STATE_KBN, ConditionMapper.OPERATOR_EQUAL,
                dto.getStateKbn());
        // レッスン日
        conditions.addCondition(LessonReservationPerformanceTrnDao.COND_LESSON_DT, ConditionMapper.OPERATOR_EQUAL,
                dto.getLessonDt());
        // レッスン時刻コード
        conditions.addCondition(LessonReservationPerformanceTrnDao.COND_LESSON_TM_CD, ConditionMapper.OPERATOR_EQUAL,
                dto.getLessonTmCd());

        // 並び順
        OrderCondition orderBy = new OrderCondition();

        // 一覧データを取得する
        list = dao.search(conditions, orderBy);

        // 戻り値
        return list;
    }

    /**
     * 受講者マスタデータの取得<br>
     * <br>
     * 受講者マスタへ検索処理を行う。<br>
     * <br>
     * @param dto 受講者マスタDTO<br>
     * @return List<StudentMstDto> 受講者マスタDTOリスト<br>
     * @exception NaiException
     */
    public List<StudentMstDto> selectStudentMstList(StudentMstDto dto) throws NaiException {

        // 初期化処理
        List<StudentMstDto> list = new ArrayList<StudentMstDto>();

        StudentMstDao dao = new StudentMstDao(this.conn);

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();
        // 受講者ID
        conditions.addCondition(StudentMstDao.COND_STUDENT_ID, ConditionMapper.OPERATOR_EQUAL, dto.getStudentId());

        // 並び順
        OrderCondition orderBy = new OrderCondition();

        // 一覧データを取得する
        list = dao.search(conditions, orderBy);

        // 戻り値
        return list;
    }

    /**
     * コード管理マスタデータの取得<br>
     * <br>
     * コード管理マスタへ検索処理を行う。<br>
     * <br>
     * @param なし<br>
     * @return List<CodeManagMstDto> コード管理マスタDTOリスト<br>
     * @throws NaiException
     */
    public List<CodeManagMstDto> selectCodeManagMstList(CodeManagMstDto dto) throws NaiException {

        // 初期化処理
        List<CodeManagMstDto> list = new ArrayList<CodeManagMstDto>();

        CodeManagMstDao dao = new CodeManagMstDao(this.conn);

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();
        // コード種別
        conditions.addCondition(CodeManagMstDao.COND_CD_CATEGORY, ConditionMapper.OPERATOR_EQUAL, dto.getCdCategory());
        // 汎用コード
        conditions.addCondition(CodeManagMstDao.COND_MANAGER_CD, ConditionMapper.OPERATOR_EQUAL, dto.getManagerCd());

        // 一覧データを取得する
        list = dao.search(conditions);

        // 戻り値
        return list;
    }

    /**
     * DM履歴明細テーブルデータの挿入<br>
     * <br>
     * DM履歴明細テーブルへ追加処理を行う。<br>
     * <br>
     * @param dto DM履歴明細テーブルDTO<br>
     * @return なし<br>
     * @throws NaiException
     */
    public void insertDmHistoryDetailsTrn(DmHistoryDetailsTrnDto dto) throws NaiException {

        DmHistoryDetailsTrnDao dao = new DmHistoryDetailsTrnDao(this.conn);

        // データを挿入する
        dao.insert(dto);
    }

    /**
     * DM履歴テーブルデータの挿入<br>
     * <br>
     * DM履歴テーブルへ追加処理を行う。<br>
     * <br>
     * @param dto DM履歴テーブルDTO<br>
     * @return なし<br>
     * @throws NaiException
     */
    public void insertDmHistoryTrn(DmHistoryTrnDto dto) throws NaiException {

        DmHistoryTrnDao dao = new DmHistoryTrnDao(this.conn);

        // データを挿入する
        dao.insert(dto);
    }

    /**
     * メール管理マスタデータの取得<br>
     * <br>
     * メール管理マスタへ検索処理を行う<br>
     * <br>
     * @param dto メール管理マスタDTO<br>
     * @return List<MailManagMstDto> メール管理マスタDTOリスト<br>
     * @throws NaiException
     */
    public List<MailManagMstDto> selectMailManagMst(MailManagMstDto dto) throws NaiException {

        // 初期化処理
        List<MailManagMstDto> list = new ArrayList<MailManagMstDto>();

        MailManagMstDao dao = new MailManagMstDao(this.conn);

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();
        // メールパターンコード
        conditions.addCondition(MailManagMstDao.COND_MAIL_PATTERN_CD, ConditionMapper.OPERATOR_EQUAL,
                dto.getMailPatternCd());

        // 並び順
        OrderCondition orderBy = new OrderCondition();

        // 一覧データを取得する
        list = dao.search(conditions, orderBy);

        // 戻り値
        return list;
    }

    /**
     * レッスン予実テーブルデータの取得<br>
     * <br>
     * レッスン予実テーブルへ検索処理を行う。<br>
     * <br>
     * @param reservationNo 予約No<br>
     * @return LessonReservationPerformanceTrnDto レッスン予実テーブルDTO<br>
     * @exception NaiException
     */
    public LessonReservationPerformanceTrnDto selectLessonReservationPerformanceTrn(String reservationNo)
            throws NaiException {

        // 初期化処理
        List<LessonReservationPerformanceTrnDto> list = new ArrayList<LessonReservationPerformanceTrnDto>();

        LessonReservationPerformanceTrnDto dto = new LessonReservationPerformanceTrnDto();

        LessonReservationPerformanceTrnDao dao = new LessonReservationPerformanceTrnDao(this.conn);

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        conditions.addCondition(LessonReservationPerformanceTrnDao.COND_RESERVATION_NO, ConditionMapper.OPERATOR_EQUAL,
                reservationNo);

        // 並び順
        OrderCondition orderBy = new OrderCondition();

        // 一覧データを取得する
        list = dao.search(conditions, orderBy);

        // データありの場合
        if (list.get(0).getReturnCode() != NaikaraTalkConstants.RETURN_CD_DATA_NO) {

            dto = list.get(0);
        }

        // 戻り値
        return dto;
    }

    /**
     * レッスン予実テーブルデータの更新<br>
     * <br>
     * レッスン予実テーブルへ更新処理を行う。<br>
     * <br>
     * @param dto レッスン予実テーブルDTO<br>
     * @return なし<br>
     * @exception NaiException
     */
    public void updateLessonReservationPerformanceTrn(LessonReservationPerformanceTrnDto dto) throws NaiException {

        LessonReservationPerformanceTrnDao dao = new LessonReservationPerformanceTrnDao(this.conn);

        // データを更新する
        dao.update(dto);
    }

}