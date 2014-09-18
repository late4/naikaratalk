package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.LessonCommentTrnDto;
import com.naikara_talk.dto.LessonReservationPerformanceTrnDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.ReserveLessonCommentLogic;
import com.naikara_talk.logic.TeacherLessonManagementLogic;
import com.naikara_talk.model.TeacherLessonManagementModel;
import com.naikara_talk.util.NaikaraStringUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>講師。<br>
 * <b>クラス名称　　　:</b>講師側の視点から_1-1_レッスン実績検索処理Serviceクラス。<br>
 * <b>クラス概要　　　:</b>講師側の視点から_1-1_レッスン実績検索処理Service。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/12 TECS 新規作成。
 */
public class TeacherLessonManagementSearchService implements ActionService {

    /** 一覧 ZERO件 */
    private static final int LIST_ZERO_CNT = 0;

    /** 一覧の表示上限 */
    private static final int LIST_MAX_CNT = 100;

    /** 検索前チェック：データ件数ZERO件 */
    public static final int ERR_ZERO_DATA = 2;

    /** 検索前チェック：データ件数上限以上の件数 */
    public static final int ERR_MAXOVER_DATA = 3;

    /** 検索前チェック：抽出開始日チェック */
    public static final int PERIOD_DATE_FROM_CHECK = 4;

    /** 検索前チェック：抽出終了日チェック */
    public static final int PERIOD_DATE_TO_CHECK = 5;

    /**
     * 検索前チェック<br>
     * <br>
     * 検索前チェック処理<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @return int エラー有無フラグ<br>
     * @exception NaiException
     */
    public int checkPreSelect(TeacherLessonManagementModel model) throws NaiException {

        // 抽出開始日チェック - 検索前チェック
        if (StringUtils.equals("", model.getPeriodDtFr())) {

            return PERIOD_DATE_FROM_CHECK;
        }

        // 抽出終了日チェック - 検索前チェック
        if (StringUtils.equals("", model.getPeriodDtTo())) {

            return PERIOD_DATE_TO_CHECK;
        }

        // 入力チェック - DBアクセスありチェック
        // 共通部品：レッスン予実テーブルのデータ件数取得処理
        int count = getRowCount(model);

        // ZERO件の場合
        if (count == LIST_ZERO_CNT) {

            return ERR_ZERO_DATA;

        } else if (count > LIST_MAX_CNT) {

            return ERR_MAXOVER_DATA;

        }

        return TeacherLessonManagementModel.CHECK_OK;
    }

    /**
     * 検索データ件数取得<br>
     * <br>
     * 検索データ件数取得を行う<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @return int 件数<br>
     * @exception NaiException
     */
    public int getRowCount(TeacherLessonManagementModel model) throws NaiException {

        // コネクションを取得
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // ロジックの初期化
            TeacherLessonManagementLogic teacherLessonManagementLogic = new TeacherLessonManagementLogic(conn);

            // Model値をDTOにセット
            LessonReservationPerformanceTrnDto dto = this.modelToDto(model);

            // データ件数を取得
            int count = teacherLessonManagementLogic.getRowCount(dto);

            conn.commit();
            return count;

        } catch (SQLException se) {
            se.printStackTrace();
            throw new NaiException(se);
        } finally {
            try {
                conn.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * レッスンリスト取得<br>
     * <br>
     * レッスンリスト取得を行う<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @return List 検索結果<br>
     * @exception NaiException
     */
    public List<LessonReservationPerformanceTrnDto> selectList(TeacherLessonManagementModel model) throws NaiException {

        // コネクションを取得
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // ロジックの初期化
            TeacherLessonManagementLogic teacherLessonManagementLogic = new TeacherLessonManagementLogic(conn);

            // Model値をDTOにセット
            LessonReservationPerformanceTrnDto dto = this.modelToDto(model);

            // ロジックの初期化
            ReserveLessonCommentLogic reserveLessonCommentLogic = new ReserveLessonCommentLogic(conn);

            // レッスンリストを取得
            List<LessonReservationPerformanceTrnDto> list = teacherLessonManagementLogic.selectList(dto);

            for (LessonReservationPerformanceTrnDto lrptDto : list) {

                LessonCommentTrnDto lctDto = reserveLessonCommentLogic.getCommentData(lrptDto.getReservationNo());

                lrptDto.setCEvaluationJnm(lctDto.getCEvaluationJnm());
                lrptDto.setCOnTeacherCmt(lctDto.getCOnTeacherCmt());
                lrptDto.setSOnTeacherCmt(lctDto.getSOnTeacherCmt());

                lrptDto.setCourseEnmAll(NaikaraStringUtil.unionString4(lrptDto.getBigClassificationEnm(),
                        lrptDto.getMiddleClassificationEnm(), lrptDto.getSmallClassificationEnm(),
                        lrptDto.getCourseEnm()));
                lrptDto.setCourseJnmAll(NaikaraStringUtil.unionString4(lrptDto.getBigClassificationJnm(),
                        lrptDto.getMiddleClassificationJnm(), lrptDto.getSmallClassificationJnm(),
                        lrptDto.getCourseJnm()));
            }

            conn.commit();
            return list;

        } catch (SQLException se) {
            se.printStackTrace();
            throw new NaiException(se);
        } finally {
            try {
                conn.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Model値をセット<br>
     * <br>
     * Model値をDTOにセット<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @return prmDto 変換後結果<br>
     * @exception NaiException
     */
    private LessonReservationPerformanceTrnDto modelToDto(TeacherLessonManagementModel model) throws NaiException {

        // DTOの初期化
        LessonReservationPerformanceTrnDto prmDto = new LessonReservationPerformanceTrnDto();

        // 抽出開始日
        prmDto.setPeriodDtFr(NaikaraStringUtil.converToYYYYMMDD(model.getPeriodDtFr()));
        // 抽出終了日
        prmDto.setPeriodDtTo(NaikaraStringUtil.converToYYYYMMDD(model.getPeriodDtTo()));
        // 講師ID
        prmDto.setTeacherId(model.getTeacherId());
        // 講師ニックネーム
        prmDto.setTeacherNickNm(model.getTeacherNickname());
        // 受講者ID
        prmDto.setStudentId(model.getStudentId());
        // 受講者ニックネーム
        prmDto.setStudentNickNm(model.getStudentNickname());
        // 大分類
        prmDto.setBigClassificationEcd(model.getCourseLarge());
        // 中分類
        prmDto.setMiddleClassificationEcd(model.getCourseMedium());
        // 小分類
        prmDto.setSmallClassificationEcd(model.getCourseSmall());
        // コース名
        prmDto.setCourseJnm(model.getCourseName());

        return prmDto;
    }
}