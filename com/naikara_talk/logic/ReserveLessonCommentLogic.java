package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dao.LessonCommentTrnDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.LessonCommentTrnDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraStringUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DAOクラス<br>
 * <b>クラス名称　　　:</b>予約NO単位のレッスンコメントデータ取得クラス<br>
 * <b>クラス概要　　　:</b>予約NO単位のレッスンコメントデータ取得DAO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/06/03 TECS 新規作成
 */
public class ReserveLessonCommentLogic {

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public ReserveLessonCommentLogic(Connection con) {
        this.conn = con;
    }

    /**
     * 予約NO単位のレッスンコメントデータ１件取得<br>
     * <br>
     * 予約NO単位のレッスンコメントを戻り値で返却する<br>
     * <br>
     * @param reservationNo
     * @return LessonCommentTrnDto
     * @throws NaiException
     */
    public LessonCommentTrnDto getCommentData(String reservationNo) throws NaiException {

        // 戻り値とリターンコードの初期化
        LessonCommentTrnDto dto = null;                     // レッスンコメントDTO
        ArrayList<LessonCommentTrnDto> workdtoT = null;     // 講師コメントDTO(ワーク用)
        ArrayList<LessonCommentTrnDto> workdtoC = null;     // 受講者コメントDTO(ワーク用)
        ArrayList<LessonCommentTrnDto> workdtoS = null;     // スクールコメントDTO(ワーク用)
        LessonCommentTrnDto workdtoTSet = null;             // 講師コメントDTO(ワーク用)
        LessonCommentTrnDto workdtoCSet = null;             // 受講者コメントDTO(ワーク用)
        LessonCommentTrnDto workdtoSSet = null;             // スクールコメントDTO(ワーク用)

        // 引数のパラメータチェック
        if (reservationNo == null || "".equals(reservationNo)) {
            dto = new LessonCommentTrnDto();
            dto.setReturnCode(1);
            return dto;
        }

        LessonCommentTrnDao dao = new LessonCommentTrnDao(this.conn);

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // 予約Noを入力されている場合
        if (!StringUtils.isEmpty(reservationNo)) {
            conditions.addCondition(LessonCommentTrnDao.RESERVATION_NO, ConditionMapper.OPERATOR_EQUAL, reservationNo);
        }

        // コメント入力者区分を入力されている場合(講師)
        if (!StringUtils.isEmpty(reservationNo)) {
            conditions.addCondition(LessonCommentTrnDao.CMT_INPUTS_KBN, ConditionMapper.OPERATOR_EQUAL, "T");
        }
        // 並び順:なし
        OrderCondition orderBy = new OrderCondition();

        // 講師コメントの取得
        workdtoT = dao.search(conditions, orderBy);

        // 検索条件の作成
        conditions = new ConditionMapper();

        // 予約Noを入力されている場合
        if (!StringUtils.isEmpty(reservationNo)) {
            conditions.addCondition(LessonCommentTrnDao.RESERVATION_NO, ConditionMapper.OPERATOR_EQUAL, reservationNo);
        }

        // コメント入力者区分を入力されている場合(受講者)
        if (!StringUtils.isEmpty(reservationNo)) {
            conditions.addCondition(LessonCommentTrnDao.CMT_INPUTS_KBN, ConditionMapper.OPERATOR_EQUAL, "C");
        }
        // 受講者コメントの取得
        workdtoC = dao.search(conditions, orderBy);

        // 検索条件の作成
        conditions = new ConditionMapper();

        // 予約Noを入力されている場合
        if (!StringUtils.isEmpty(reservationNo)) {
            conditions.addCondition(LessonCommentTrnDao.RESERVATION_NO, ConditionMapper.OPERATOR_EQUAL, reservationNo);
        }

        // コメント入力者区分を入力されている場合(受講者)
        if (!StringUtils.isEmpty(reservationNo)) {
            conditions.addCondition(LessonCommentTrnDao.CMT_INPUTS_KBN, ConditionMapper.OPERATOR_EQUAL, "S");
        }
        // スクールコメントの取得
        workdtoS = dao.search(conditions, orderBy);

        // 講師、受講者、スクールの全てにデータが存在しない場合はエラー
        if (!((workdtoT != null && workdtoT.size() > 0) && (workdtoC != null && workdtoC.size() > 0) && (workdtoS != null && workdtoS
                .size() > 0))) {
            dto = new LessonCommentTrnDto();
            dto.setReturnCode(2);
            return dto;
        }

        // コメントの編集処理
        dto = new LessonCommentTrnDto();
        // 講師
        if (workdtoT != null) {

            workdtoTSet = workdtoT.get(0);

            dto.setReservationNo(String.valueOf(NaikaraStringUtil.nvlString(workdtoTSet.getReservationNo())));
            dto.setTSelfEvaluation1Kbn(String.valueOf(NaikaraStringUtil.nvlString(workdtoTSet.getTSelfEvaluation1Kbn())));
            dto.setTSelfEvaluation1Enm(String.valueOf(NaikaraStringUtil.nvlString(workdtoTSet.getTSelfEvaluation1Enm())));
            dto.setTSelfEvaluation2Kbn(String.valueOf(NaikaraStringUtil.nvlString(workdtoTSet.getTSelfEvaluation2Kbn())));
            dto.setTSelfEvaluation2Enm(String.valueOf(NaikaraStringUtil.nvlString(workdtoTSet.getTSelfEvaluation2Enm())));
            dto.setTPositiveCmt(String.valueOf(NaikaraStringUtil.nvlString(workdtoTSet.getTPositiveCmt())));
            dto.setTOnSchoolCmt(String.valueOf(NaikaraStringUtil.nvlString(workdtoTSet.getTOnSchoolCmt())));
            dto.setTRecommendedLevelKbn(String.valueOf(NaikaraStringUtil.nvlString(workdtoTSet
                    .getTRecommendedLevelKbn())));
            dto.setTRecommendedLevelEnm(String.valueOf(NaikaraStringUtil.nvlString(workdtoTSet
                    .getTRecommendedLevelEnm())));
            dto.setTNextTeacherCmt(String.valueOf(NaikaraStringUtil.nvlString(workdtoTSet.getTNextTeacherCmt())));
            dto.setRecordVerNoForTeacher(workdtoTSet.getRecordVerNo());
        }

        // 受講者
        if (workdtoC != null) {

            workdtoCSet = workdtoC.get(0);

            dto.setCEvaluationKbn(String.valueOf(NaikaraStringUtil.nvlString(workdtoCSet.getCEvaluationKbn())));
            dto.setCEvaluationJnm(String.valueOf(NaikaraStringUtil.nvlString(workdtoCSet.getCEvaluationJnm())));
            dto.setCOnTeacherCmt(String.valueOf(NaikaraStringUtil.nvlString(workdtoCSet.getCOnTeacherCmt())));
            dto.setRecordVerNo(workdtoCSet.getRecordVerNo());
            dto.setRecordVerNoForStudent(workdtoCSet.getRecordVerNo());
        }

        // スクール
        if (workdtoS != null) {

            workdtoSSet = workdtoS.get(0);

            dto.setSOnTeacherCmt(String.valueOf(NaikaraStringUtil.nvlString(workdtoSSet.getSOnTeacherCmt())));
            dto.setRecordVerNoForSchool(workdtoSSet.getRecordVerNo());
        }

        dto.setReturnCode(0);
        return dto;
    }

}
