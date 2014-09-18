package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.LessonResPerCommentDto;
import com.naikara_talk.dto.TeacherMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.LessonResPerCommentLogic;
import com.naikara_talk.logic.TeacherEvaluationLogic;
import com.naikara_talk.model.TeacherEvaluationModel;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_受講処理<br>
 * <b>クラス名称       :</b>講師評価画面<br>
 * <b>クラス概要       :</b>講師評価画面初期処理Service<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/10 TECS 新規作成
 */
public class TeacherEvaluationLoadService implements ActionService {

    /**
     * データの存在チェック。<br>
     * <br>
     * 画面初期表示。<br>
     * <br>
     * @param PointControlModel  画面のパラメータ<br>
     * @return int<br>
     * @exception NaiException
     */
    public int getExist(TeacherEvaluationModel model) throws NaiException {

        Connection conn = null;

        try {
            conn = DbUtil.getConnection();
            LessonResPerCommentLogic lessonResPerCommentLogic = new LessonResPerCommentLogic(conn);
            // DTOの初期化
            LessonResPerCommentDto prmDto = new LessonResPerCommentDto();

            // Model値をDTOにセット
            prmDto = this.modelToDto(model);

            // 検索実行
            LessonResPerCommentDto resultDto = lessonResPerCommentLogic.getLessonResPerComment(prmDto
                    .getReservationNo());
            return resultDto.getReturnCode();
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
     * 画面初期表示。<br>
     * <br>
     * 画面初期表示。<br>
     * <br>
     * @param TeacherEvaluationModel  画面のパラメータ<br>
     * @return TeacherEvaluationModel<br>
     * @exception NaiException
     */
    public TeacherEvaluationModel select(TeacherEvaluationModel model) throws NaiException {

        Connection conn = null;

        try {
            conn = DbUtil.getConnection();
            LessonResPerCommentLogic lessonResPerCommentLogic = new LessonResPerCommentLogic(conn);
            // DTOの初期化
            LessonResPerCommentDto prmDto = new LessonResPerCommentDto();

            // Model値をDTOにセット
            prmDto = this.modelToDto(model);

            // 検索実行
            LessonResPerCommentDto resultDto = lessonResPerCommentLogic.getLessonResPerComment(prmDto
                    .getReservationNo());

            // DTO値をModelにセット
            model = this.dtoToModel(resultDto, model);

            TeacherEvaluationLogic teacherEvaluationLogic = new TeacherEvaluationLogic(conn);

            TeacherMstDto teacherMstDto = new TeacherMstDto();
            teacherMstDto.setUserId(model.getTeacherId());
            teacherMstDto = teacherEvaluationLogic.searchTeacherMst(teacherMstDto);
            model.setNativeLangNm(this.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_NATIVE_LANG).get(
                    teacherMstDto.getNativeLangCd()));
            model.setRecordVerNoT(teacherMstDto.getRecordVerNo());

            return model;
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
     * コード管理マスタからデータ取得処理。<br>
     * <br>
     * コード管理マスタからデータ取得処理。<br>
     * <br>
     * @param category  汎用コード<br>
     * @return LinkedHashMap<String, String><br>
     * @exception NaiException
     */
    public LinkedHashMap<String, String> selectCodeMst(String category) throws NaiException {

        Connection conn = null;

        try {
            conn = DbUtil.getConnection();
            TeacherEvaluationLogic teacherEvaluationLogic = new TeacherEvaluationLogic(conn);

            // コード管理マスタ検索
            return teacherEvaluationLogic.selectCodeMst(category);
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
     * Model値をDTOにセット。<br>
     * <br>
     * Model値をDTOにセット。<br>
     * <br>
     * @param TeacherEvaluationModel<br>
     * @return LessonResPerCommentDto<br>
     * @exception なし
     */
    private LessonResPerCommentDto modelToDto(TeacherEvaluationModel model) {

        // DTOの初期化
        LessonResPerCommentDto prmDto = new LessonResPerCommentDto();

        prmDto.setReservationNo(model.getReservationNo());

        return prmDto;

    }

    /**
     * DTO値をModelにセット。<br>
     * <br>
     * DTO値をModelにセット。<br>
     * <br>
     * @param LessonResPerCommentDto TeacherEvaluationModel<br>
     * @return TeacherEvaluationModel<br>
     * @exception なし
     */
    private TeacherEvaluationModel dtoToModel(LessonResPerCommentDto prmDto, TeacherEvaluationModel model) {

        model.setStudentId(prmDto.getStudentId());                                                                  // 受講者ID
        model.setStudentNickNm(prmDto.getStudentNickNm());                                                          // 受講者ニックネーム
        model.setLessonDt(prmDto.getLessonDt());                                                                    // レッスン日
        model.setLessonTmNm(prmDto.getLessonTmNm());                                                                // レッスン時刻名
        model.setCourseJnm(NaikaraStringUtil.unionString4(prmDto.getBigClassificationJnm(),
                prmDto.getMiddleClassificationJnm(), prmDto.getSmallClassificationJnm(), prmDto.getCourseJnm()));   // コース名
        model.setTeacherId(prmDto.getTeacherId());                                                                  // 講師ID
        model.setTeacherNickNm(prmDto.getTeacherNickNm());                                                          // 講師名(ニックネーム)
        model.setcEvaluationKbn(prmDto.getCEvaluationKbn());                                                        // 評価
        model.setcOnTeacherCmt(prmDto.getCOnTeacherCmt());                                                          // レッスンに対する講師への意見コメント
        model.setRecordVerNo(prmDto.getRecordVerNo());                                                              // レコードバージョン番号
        model.setReservationNo(prmDto.getReservationNo());                                                          // 予約No
        model.setRecordVerNoLCT(prmDto.getRecordVerNoLCT());                                                        // レッスンコメントテーブル　レコードバージョン番号

        return model;

    }
}
