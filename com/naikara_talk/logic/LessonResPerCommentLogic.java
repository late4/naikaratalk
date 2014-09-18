package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dao.LessonReservationPerformanceTrnDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.LessonCommentTrnDto;
import com.naikara_talk.dto.LessonResPerCommentDto;
import com.naikara_talk.dto.LessonReservationPerformanceTrnDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DAOクラス<br>
 * <b>クラス名称　　　:</b>レッスン予実テーブル、レッスンコメントテーブルのデータ取得クラス<br>
 * <b>クラス概要　　　:</b>レッスン予実テーブル、レッスンコメントテーブルのデータ取得DAO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/06/05 TECS 新規作成
 */
public class LessonResPerCommentLogic {

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public LessonResPerCommentLogic(Connection con) {
        this.conn = con;
    }

    /**
     * レッスン予実テーブル、レッスンコメントテーブルのデータ取得<br>
     * <br>
     * レッスン予実テーブル、レッスンコメントテーブルのデータ取得を行う<br>
     * <br>
     * @param reservationNo
     * @return LessonResPerCommentDto
     * @throws NaiException
     */
    public LessonResPerCommentDto getLessonResPerComment(String reservationNo) throws NaiException {

        // 戻り値とリターンコードの初期化
        LessonResPerCommentDto dto = null;

        // 引数のパラメータチェック
        // 受講者ID
        // 必須チェック
        if (reservationNo == null || "".equals(reservationNo)) {
            dto = new LessonResPerCommentDto();
            dto.setReturnCode(1);
            return dto;
        }

        // ◆◆◆レッスン予実テーブルのデータ取得◆◆◆
        LessonReservationPerformanceTrnDao daoYJ = new LessonReservationPerformanceTrnDao(this.conn);
        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // 予約Noを入力されている場合
        if (!StringUtils.isEmpty(reservationNo)) {
            conditions.addCondition(LessonReservationPerformanceTrnDao.RESERVATION_NO, ConditionMapper.OPERATOR_EQUAL,
                    reservationNo);
        }
        // 並び順:なし
        OrderCondition orderBy = new OrderCondition();

        ArrayList<LessonReservationPerformanceTrnDto> workDtoYJ = daoYJ.search(conditions, orderBy);

        if (workDtoYJ.get(0).getReturnCode() > NaikaraTalkConstants.RETURN_CD_DATA_YES) {
            dto = new LessonResPerCommentDto();
            dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_NO);
            return dto;
        }

        // ◆◆◆レッスンコメントデータ取得◆◆◆
        ReserveLessonCommentLogic dao = new ReserveLessonCommentLogic(this.conn);
        LessonCommentTrnDto workDto2 = dao.getCommentData(reservationNo);

        // レッスン予実、レッスンコメントの編集
        dto = new LessonResPerCommentDto();

        LessonReservationPerformanceTrnDto workDto1 = workDtoYJ.get(0);

        dto.setReservationNo(workDto1.getReservationNo());
        dto.setStateKbn(workDto1.getStateKbn());
        dto.setStudentId(workDto1.getStudentId());
        dto.setStudentNickNm(workDto1.getStudentNickNm());
        dto.setTeacherId(workDto1.getTeacherId());
        dto.setTeacherNickNm(workDto1.getTeacherNickNm());
        dto.setLessonDt(workDto1.getLessonDt());
        dto.setLessonTmCd(workDto1.getLessonTmCd());
        dto.setLessonTmNm(workDto1.getLessonTmNm());
        dto.setBigClassificationJcd(workDto1.getBigClassificationJcd());
        dto.setBigClassificationJnm(workDto1.getBigClassificationJnm());
        dto.setMiddleClassificationJcd(workDto1.getMiddleClassificationJcd());
        dto.setMiddleClassificationJnm(workDto1.getMiddleClassificationJnm());
        dto.setSmallClassificationJcd(workDto1.getSmallClassificationJcd());
        dto.setSmallClassificationJnm(workDto1.getSmallClassificationJnm());
        dto.setBigClassificationEcd(workDto1.getBigClassificationEcd());
        dto.setBigClassificationEnm(workDto1.getBigClassificationEnm());
        dto.setMiddleClassificationEcd(workDto1.getMiddleClassificationEcd());
        dto.setMiddleClassificationEnm(workDto1.getMiddleClassificationEnm());
        dto.setSmallClassificationEcd(workDto1.getSmallClassificationEcd());
        dto.setSmallClassificationEnm(workDto1.getSmallClassificationEnm());
        dto.setCourseCd(workDto1.getCourseCd());
        dto.setCourseJnm(workDto1.getCourseJnm());
        dto.setCourseEnm(workDto1.getCourseEnm());
        dto.setPaymentUsePoint(workDto1.getPaymentUsePoint());
        dto.setFreeUsePoint(workDto1.getFreeUsePoint());
        dto.setUsePointSum(workDto1.getUsePointSum());
        dto.setMailKbn(workDto1.getMailKbn());
        dto.setMailDt(workDto1.getMailDt());

        // 2014/01/15 FlashからTokboxに変更対応 start
        //dto.setRoomNo(workDto1.getRoomNo());
        dto.setTokboxSessionId(workDto1.getTokboxSessionId());
        dto.setTokboxTokenId(workDto1.getTokboxTokenId());
        dto.setFileDuringLessons(workDto1.getFileDuringLessons());
        // 2014/01/15 FlashからTokboxに変更対応 end

        dto.setTeacherSessionId(workDto1.getTeacherSessionId());
        dto.setStudentSessionId(workDto1.getStudentSessionId());
        dto.setStudentInDttm(workDto1.getStudentInDttm());
        dto.setStudentStartDttm(workDto1.getStudentStartDttm());
        dto.setStudentEndDttm(workDto1.getStudentEndDttm());
        dto.setStudentLessonKbn(workDto1.getStudentLessonKbn());
        dto.setTeacherInDttm(workDto1.getTeacherInDttm());
        dto.setTeacherStartDttm(workDto1.getTeacherStartDttm());
        dto.setTeacherEndDttm(workDto1.getTeacherEndDttm());
        dto.setTeacherNextDttm(workDto1.getTeacherNextDttm());
        dto.setTeacherLessonKbn(workDto1.getTeacherLessonKbn());
        dto.setLessonStartDttm(workDto1.getLessonStartDttm());
        dto.setLessonEndDttm(workDto1.getLessonEndDttm());
        dto.setRecordVerNo(workDto1.getRecordVerNo());
        dto.setInsertDttm(workDto1.getInsertDttm());
        dto.setInsertCd(workDto1.getInsertCd());
        dto.setUpdateDttm(workDto1.getUpdateDttm());
        dto.setUpdateCd(workDto1.getUpdateCd());
        dto.setTSelfEvaluation1Kbn(workDto2.getTSelfEvaluation1Kbn());
        dto.setTSelfEvaluation1Enm(workDto2.getTSelfEvaluation1Enm());
        dto.setTSelfEvaluation2Kbn(workDto2.getTSelfEvaluation2Kbn());
        dto.setTSelfEvaluation2Enm(workDto2.getTSelfEvaluation2Enm());
        dto.setTPositiveCmt(workDto2.getTPositiveCmt());
        dto.settPositiveCmtCut(StringUtils.substring(workDto2.getTPositiveCmt(), 0, 37));
        dto.setTOnSchoolCmt(workDto2.getTOnSchoolCmt());
        dto.setTRecommendedLevelKbn(workDto2.getTRecommendedLevelKbn());
        dto.setTRecommendedLevelEnm(workDto2.getTRecommendedLevelEnm());
        dto.setTNextTeacherCmt(workDto2.getTNextTeacherCmt());
        dto.setCEvaluationKbn(workDto2.getCEvaluationKbn());
        dto.setCEvaluationJnm(workDto2.getCEvaluationJnm());
        dto.setCOnTeacherCmt(workDto2.getCOnTeacherCmt());
        dto.setSOnTeacherCmt(workDto2.getSOnTeacherCmt());
        dto.setRecordVerNoLCT(workDto2.getRecordVerNo());
        dto.setRecordVerNoForTeacher(workDto2.getRecordVerNoForTeacher());
        dto.setRecordVerNoForStudent(workDto2.getRecordVerNoForStudent());
        dto.setRecordVerNoForSchool(workDto2.getRecordVerNoForSchool());

        dto.setReturnCode(0);

        return dto;

    }

}
