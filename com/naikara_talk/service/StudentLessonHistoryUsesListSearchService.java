package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.LessonCommentTrnDto;
import com.naikara_talk.dto.LessonReservationPerformanceTrnDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.ReserveLessonCommentLogic;
import com.naikara_talk.logic.StudentLessonHistoryUsesListLogic;
import com.naikara_talk.model.StudentLessonHistoryUsesListModel;
import com.naikara_talk.util.NaikaraStringUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様(個人)_実績照会<br>
 * <b>クラス名称　　　:</b>受講者側の視点から_1-2_レッスン実績検索処理Serviceクラス。<br>
 * <b>クラス概要　　　:</b>受講者側の視点から_1-2_レッスン実績検索処理Service。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/05 TECS 新規作成。
 */
public class StudentLessonHistoryUsesListSearchService implements ActionService {

    /** 一覧 ZERO件 */
    private static final int LIST_ZERO_CNT = 0;

    /** 一覧の表示上限 */
    private static final int LIST_MAX_CNT = 100;

    /** 検索前チェック：データ件数ZERO件 */
    public static final int ERR_ZERO_DATA = 2;

    /** 検索前チェック：データ件数上限以上の件数 */
    public static final int ERR_MAXOVER_DATA = 3;

    /**
     * 検索前チェック<br>
     * <br>
     * 検索前チェック処理<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @return int エラー有無フラグ<br>
     * @exception NaiException
     */
    public int checkPreSelectT(StudentLessonHistoryUsesListModel model) throws NaiException {

        // 入力チェック - DBアクセスありチェック
        // 共通部品：レッスン予実テーブルのデータ件数取得処理
        int count = getRowCountT(model);

        // ZERO件の場合
        if (count == LIST_ZERO_CNT) {

            return ERR_ZERO_DATA;

        } else if (count > LIST_MAX_CNT) {

            return ERR_MAXOVER_DATA;

        }

        return StudentLessonHistoryUsesListModel.CHECK_OK;
    }

    /**
     * 検索前チェック<br>
     * <br>
     * 検索前チェック処理<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @return int エラー有無フラグ<br>
     * @exception NaiException
     */
    public int checkPreSelectS(StudentLessonHistoryUsesListModel model) throws NaiException {

        // 入力チェック - DBアクセスありチェック
        // 共通部品：レッスン予実テーブルのデータ件数取得処理
        int count = getRowCountS(model);

        // ZERO件の場合
        if (count == LIST_ZERO_CNT) {

            return ERR_ZERO_DATA;

        } else if (count > LIST_MAX_CNT) {

            return ERR_MAXOVER_DATA;

        }

        return StudentLessonHistoryUsesListModel.CHECK_OK;
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
    public int getRowCountT(StudentLessonHistoryUsesListModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            // ロジックの初期化
            StudentLessonHistoryUsesListLogic studentLessonHistoryUsesListLogic = new StudentLessonHistoryUsesListLogic(
                    conn);

            // DTOの初期化
            LessonReservationPerformanceTrnDto dto = new LessonReservationPerformanceTrnDto();

            // Model値をDTOにセット
            dto = this.modelToDtoT(model);

            // データの取得件数＆リターン
            return studentLessonHistoryUsesListLogic.getRowCountT(dto);
        } catch (SQLException e) {
            throw new NaiException(e);
        } catch (Exception e) {
            throw new NaiException(e);
        } finally {
            try {
                conn.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

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
    public int getRowCountS(StudentLessonHistoryUsesListModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            // ロジックの初期化
            StudentLessonHistoryUsesListLogic studentLessonHistoryUsesListLogic = new StudentLessonHistoryUsesListLogic(
                    conn);

            // DTOの初期化
            LessonReservationPerformanceTrnDto dto = new LessonReservationPerformanceTrnDto();

            // Model値をDTOにセット
            dto = this.modelToDtoS(model);

            // データの取得件数＆リターン
            return studentLessonHistoryUsesListLogic.getRowCountS(dto);
        } catch (SQLException e) {
            throw new NaiException(e);
        } catch (Exception e) {
            throw new NaiException(e);
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
    public List<LessonReservationPerformanceTrnDto> selectListT(StudentLessonHistoryUsesListModel model)
            throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            // ロジックの初期化
            StudentLessonHistoryUsesListLogic studentLessonHistoryUsesListLogic = new StudentLessonHistoryUsesListLogic(
                    conn);

            // DTOの初期化
            LessonReservationPerformanceTrnDto dto = new LessonReservationPerformanceTrnDto();

            // Model値をDTOにセット
            dto = this.modelToDtoT(model);

            // ロジックの初期化
            ReserveLessonCommentLogic reserveLessonCommentLogic = new ReserveLessonCommentLogic(conn);

            // DTOの初期化
            LessonCommentTrnDto lctDto = new LessonCommentTrnDto();

            // レッスンリストを取得
            List<LessonReservationPerformanceTrnDto> list = studentLessonHistoryUsesListLogic.selectList(dto);

            for (LessonReservationPerformanceTrnDto lrptDto : list) {

                lctDto = reserveLessonCommentLogic.getCommentData(lrptDto.getReservationNo());

                lrptDto.setTNextTeacherCmt(lctDto.getTNextTeacherCmt());
                lrptDto.setTRecommendedLevelEnm(lctDto.getTRecommendedLevelEnm());
                lrptDto.setCourseEnmAll(NaikaraStringUtil.unionString4(lrptDto.getBigClassificationEnm(),
                        lrptDto.getMiddleClassificationEnm(), lrptDto.getSmallClassificationEnm(),
                        lrptDto.getCourseEnm()));
                lrptDto.setCourseJnmAll(NaikaraStringUtil.unionString4(lrptDto.getBigClassificationJnm(),
                        lrptDto.getMiddleClassificationJnm(), lrptDto.getSmallClassificationJnm(),
                        lrptDto.getCourseJnm()));
            }

            return list;
        } catch (SQLException e) {
            throw new NaiException(e);
        } catch (Exception e) {
            throw new NaiException(e);
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
    public List<LessonReservationPerformanceTrnDto> selectListS(StudentLessonHistoryUsesListModel model)
            throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            // ロジックの初期化
            StudentLessonHistoryUsesListLogic studentLessonHistoryUsesListLogic = new StudentLessonHistoryUsesListLogic(
                    conn);

            // DTOの初期化
            LessonReservationPerformanceTrnDto dto = new LessonReservationPerformanceTrnDto();

            // Model値をDTOにセット
            dto = this.modelToDtoS(model);

            // ロジックの初期化
            ReserveLessonCommentLogic reserveLessonCommentLogic = new ReserveLessonCommentLogic(conn);

            // DTOの初期化
            LessonCommentTrnDto lctDto = new LessonCommentTrnDto();

            // レッスンリストを取得
            List<LessonReservationPerformanceTrnDto> list = studentLessonHistoryUsesListLogic.selectList(dto);

            for (LessonReservationPerformanceTrnDto lrptDto : list) {

                lctDto = reserveLessonCommentLogic.getCommentData(lrptDto.getReservationNo());

                lrptDto.setTPositiveCmt(lctDto.getTPositiveCmt());
                lrptDto.setCourseJnmAll(NaikaraStringUtil.unionString4(lrptDto.getBigClassificationJnm(),
                        lrptDto.getMiddleClassificationJnm(), lrptDto.getSmallClassificationJnm(),
                        lrptDto.getCourseJnm()));
            }

            return list;
        } catch (SQLException e) {
            throw new NaiException(e);
        } catch (Exception e) {
            throw new NaiException(e);
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
    private LessonReservationPerformanceTrnDto modelToDtoT(StudentLessonHistoryUsesListModel model) throws NaiException {

        // DTOの初期化
        LessonReservationPerformanceTrnDto prmDto = new LessonReservationPerformanceTrnDto();

        // レッスン開始日
        prmDto.setLessonDtFr(model.getLessonDtFr());
        // レッスン終了日
        prmDto.setLessonDtTo(model.getLessonDtTo());
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
        prmDto.setCourseEnm(model.getCourseName());

        return prmDto;
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
    private LessonReservationPerformanceTrnDto modelToDtoS(StudentLessonHistoryUsesListModel model) throws NaiException {

        // DTOの初期化
        LessonReservationPerformanceTrnDto prmDto = new LessonReservationPerformanceTrnDto();

        // レッスン開始日
        prmDto.setLessonDtFr(model.getLessonDtFr());
        // レッスン終了日
        prmDto.setLessonDtTo(model.getLessonDtTo());
        // 受講者ID
        prmDto.setStudentId(model.getStudentId());
        // 受講者ニックネーム
        prmDto.setStudentNickNm(model.getStudentNickname());
        // 大分類
        prmDto.setBigClassificationJcd(model.getCourseLarge());
        // 中分類
        prmDto.setMiddleClassificationJcd(model.getCourseMedium());
        // 小分類
        prmDto.setSmallClassificationJcd(model.getCourseSmall());
        // コース名
        prmDto.setCourseJnm(model.getCourseName());

        return prmDto;
    }
}