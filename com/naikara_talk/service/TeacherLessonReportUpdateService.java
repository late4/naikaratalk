package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.LessonCommentTrnDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.TeacherLessonReportLogic;
import com.naikara_talk.model.TeacherLessonReportModel;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>講師。<br>
 * <b>クラス名称　　　:</b>講師側の視点から_1-2_レッスン実績登録更新Serviceクラス。<br>
 * <b>クラス概要　　　:</b>講師側の視点から_1-2_レッスン実績登録更新Service。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/12 TECS 新規作成。
 */
public class TeacherLessonReportUpdateService implements ActionService {

    /** 自己評価1未選択チェックエラー */
    public static final int NG_RADIO_1 = 1;

    /** 自己評価2未選択チェックエラー */
    public static final int NG_RADIO_2 = 2;

    /** 推奨レベル未選択チェックエラー */
    public static final int NG_RADIO_3 = 3;

    /** エラーなし */
    public static final int OK_CHECK = 4;

    /** 登録モード */
    public static final String INSERT_KBN = "insert";

    /** 更新モード */
    public static final String UPDATE_KBN = "update";

    /** コロン */
    private static final String COLON = "：";

    /**
     * 更新のチェック<br>
     * <br>
     * 更新のチェックを行う<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @return int 結果フラグ<br>
     * @exception NaiException
     */
    public int errorCheck(TeacherLessonReportModel model) throws NaiException {

        String role = NaikaraTalkConstants.BRANK;

        // ロールを取得
        if ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString()) != null) {

            role = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getRole();
        }

        // ロールを判断
        if (StringUtils.equals(role, SessionUser.ROLE_TEACHER)) {

            // 自己評価1未選択
            if (StringUtils.isEmpty(model.getInstruction_rdl())) {

                return NG_RADIO_1;
            }

            // 自己評価2未選択
            if (StringUtils.isEmpty(model.getSatisfied_rdl())) {

                return NG_RADIO_2;
            }

            // 推奨レベル未選択
            if (StringUtils.isEmpty(model.getLevel_rdl())) {

                return NG_RADIO_3;
            }
        }

        return OK_CHECK;
    }

    /**
     * 取得処理<br>
     * <br>
     * 画面項目の取得処理を行う<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @return lessonCommentTrnDto 画面項目<br>
     * @exception NaiException
     */
    public LessonCommentTrnDto select(TeacherLessonReportModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // ロジックの初期化
            TeacherLessonReportLogic teacherLessonReportLogic = new TeacherLessonReportLogic(conn);

            // Model値をDTOにセット
            LessonCommentTrnDto prmDto = this.modelToDto(model);

            // 検索を実行
            LessonCommentTrnDto lessonCommentTrnDto = teacherLessonReportLogic.select(prmDto);

            conn.commit();
            return lessonCommentTrnDto;

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
     * 登録処理<br>
     * <br>
     * 登録処理を行う<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @return なし<br>
     * @exception NaiException
     */
    public void insert(TeacherLessonReportModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // ロジックの初期化
            TeacherLessonReportLogic teacherLessonReportLogic = new TeacherLessonReportLogic(conn);

            // DTOの初期化
            LessonCommentTrnDto prmDto = new LessonCommentTrnDto();

            String role = NaikaraTalkConstants.BRANK;

            // ロールを取得
            if ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString()) != null) {

                role = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getRole();
            }

            // スクールおよび講師
            if (StringUtils.equals(model.getCmtInputsKbn(), SessionUser.ROLE_TEACHER)) {

                if (!StringUtils.equals(role, SessionUser.ROLE_TEACHER)) {

                    // Model値をDTOにセット
                    prmDto = this.modelToDtoForSchoolOne(model);

                } else {

                    // Model値をDTOにセット
                    prmDto = this.modelToDtoForTeacher(model);
                }
            }

            // スクールのみ
            if (StringUtils.equals(model.getCmtInputsKbn(), SessionUser.ROLE_STAFF)) {

                if (!StringUtils.equals(role, SessionUser.ROLE_TEACHER)) {

                    // Model値をDTOにセット
                    prmDto = this.modelToDtoForSchoolTwo(model);
                }
            }

            // 登録を実行
            teacherLessonReportLogic.insert(prmDto);

            // コネクションをコミット
            conn.commit();

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
     * 更新処理<br>
     * <br>
     * 更新処理を行う<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @return cnt 更新結果<br>
     * @exception NaiException
     */
    public int update(TeacherLessonReportModel model) throws NaiException {

        int cnt = 0;
        Connection conn = null;

        try {
            conn = DbUtil.getConnection();

            // ロジックの初期化
            TeacherLessonReportLogic teacherLessonReportLogic = new TeacherLessonReportLogic(conn);

            // DTOの初期化
            LessonCommentTrnDto prmDto = new LessonCommentTrnDto();

            String role = NaikaraTalkConstants.BRANK;

            // ロールを取得
            if ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString()) != null) {

                role = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getRole();
            }

            // スクールおよび講師
            if (StringUtils.equals(model.getCmtInputsKbn(), SessionUser.ROLE_TEACHER)) {

                if (!StringUtils.equals(role, SessionUser.ROLE_TEACHER)) {

                    // Model値をDTOにセット
                    prmDto = this.modelToDtoForSchoolOne(model);

                } else {

                    // Model値をDTOにセット
                    prmDto = this.modelToDtoForTeacher(model);
                }
            }

            // スクールのみ
            if (StringUtils.equals(model.getCmtInputsKbn(), SessionUser.ROLE_STAFF)) {

                if (!StringUtils.equals(role, SessionUser.ROLE_TEACHER)) {

                    // Model値をDTOにセット
                    prmDto = this.modelToDtoForSchoolTwo(model);
                }
            }

            // 更新を実行
            cnt = teacherLessonReportLogic.update(prmDto);

            // 更新処理が失敗の場合,ロールバック
            if (NaikaraTalkConstants.RETURN_CD_ERR_UPD == cnt || NaikaraTalkConstants.RETURN_CD_ERR_NO_UPD == cnt) {
                // ロールバック
                try {
                    conn.rollback();
                } catch (Exception e1) {
                    e1.printStackTrace();
                    throw new NaiException(e1);
                }
                return cnt;
            }

            // コネクションをコミット
            conn.commit();

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
        return cnt;
    }

    /**
     * コード取得<br>
     * <br>
     * コード管理マスタからデータ取得処理<br>
     * <br>
     * @param category 汎用コード<br>
     * @return LinkedHashMap 取得結果<br>
     * @exception NaiException
     */
    public LinkedHashMap<String, String> selectCodeMst(String category) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // ロジックの初期化
            TeacherLessonReportLogic teacherLessonReportLogic = new TeacherLessonReportLogic(conn);

            // コード管理マスタを検索
            LinkedHashMap<String, String> codeMap = teacherLessonReportLogic.selectCodeMst(category);

            conn.commit();
            return codeMap;

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
    private LessonCommentTrnDto modelToDto(TeacherLessonReportModel model) throws NaiException {

        // DTOの初期化
        LessonCommentTrnDto prmDto = new LessonCommentTrnDto();

        // 予約No
        prmDto.setReservationNo(model.getReservationNo_hid());

        // コメント入力者区分
        prmDto.setCmtInputsKbn(model.getCmtInputsKbn());

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
    private LessonCommentTrnDto modelToDtoForSchoolOne(TeacherLessonReportModel model) throws NaiException {

        // ユーザIDを取得
        String userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();

        // DTOの初期化
        LessonCommentTrnDto prmDto = new LessonCommentTrnDto();

        if (StringUtils.equals(model.getMode(), UPDATE_KBN)) {

            LessonCommentTrnDto lessonCommentTrnDto = this.select(model);

            // 講師：講師のレッスン自己分析１区分
            prmDto.setTSelfEvaluation1Kbn(lessonCommentTrnDto.getTSelfEvaluation1Kbn());

            // 講師：講師のレッスン自己分析１
            prmDto.setTSelfEvaluation1Enm(lessonCommentTrnDto.getTSelfEvaluation1Enm());

            // 講師：講師のレッスン自己分析２区分
            prmDto.setTSelfEvaluation2Kbn(lessonCommentTrnDto.getTSelfEvaluation2Kbn());

            // 講師：講師のレッスン自己分析２
            prmDto.setTSelfEvaluation2Enm(lessonCommentTrnDto.getTSelfEvaluation2Enm());

            // 講師：講師からスクールへのコメント
            prmDto.setTOnSchoolCmt(lessonCommentTrnDto.getTOnSchoolCmt());

            // 講師：推奨レベル区分
            prmDto.setTRecommendedLevelKbn(lessonCommentTrnDto.getTRecommendedLevelKbn());

            // 講師：推奨レベル
            prmDto.setTRecommendedLevelEnm(lessonCommentTrnDto.getTRecommendedLevelEnm());

            // 講師：受講者を引継ぎするコメント
            prmDto.setTNextTeacherCmt(lessonCommentTrnDto.getTNextTeacherCmt());

            // 受講者：講師への評価区分
            prmDto.setCEvaluationKbn(lessonCommentTrnDto.getCEvaluationKbn());

            // 受講者：講師への評価
            prmDto.setCEvaluationJnm(lessonCommentTrnDto.getCEvaluationJnm());

            // 受講者：レッスンに対する講師への意見コメント
            prmDto.setCOnTeacherCmt(lessonCommentTrnDto.getCOnTeacherCmt());

            // スクール：スクール側から講師へのコメント
            prmDto.setSOnTeacherCmt(lessonCommentTrnDto.getSOnTeacherCmt());

        } else {

            // 登録者コード
            prmDto.setInsertCd(userId);
        }

        // 予約No
        prmDto.setReservationNo(model.getReservationNo_hid());

        // コメント入力者区分
        prmDto.setCmtInputsKbn(model.getCmtInputsKbn());

        // 講師：受講者への前向きコメント
        prmDto.setTPositiveCmt(model.getPositiveComment_txa().replaceAll(NaikaraTalkConstants.NEW_LINE_CODE_WIN,
                NaikaraTalkConstants.NEW_LINE_CODE_UNIX));

        // レコードバージョン番号
        prmDto.setRecordVerNo(model.getRecordVerNoForTeacher());

        // 更新者コード
        prmDto.setUpdateCd(userId);

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
    private LessonCommentTrnDto modelToDtoForSchoolTwo(TeacherLessonReportModel model) throws NaiException {

        // ユーザIDを取得
        String userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();

        // DTOの初期化
        LessonCommentTrnDto prmDto = new LessonCommentTrnDto();

        if (StringUtils.equals(model.getMode(), UPDATE_KBN)) {

            LessonCommentTrnDto lessonCommentTrnDto = this.select(model);

            // 講師：講師のレッスン自己分析１区分
            prmDto.setTSelfEvaluation1Kbn(lessonCommentTrnDto.getTSelfEvaluation1Kbn());

            // 講師：講師のレッスン自己分析１
            prmDto.setTSelfEvaluation1Enm(lessonCommentTrnDto.getTSelfEvaluation1Enm());

            // 講師：講師のレッスン自己分析２区分
            prmDto.setTSelfEvaluation2Kbn(lessonCommentTrnDto.getTSelfEvaluation2Kbn());

            // 講師：講師のレッスン自己分析２
            prmDto.setTSelfEvaluation2Enm(lessonCommentTrnDto.getTSelfEvaluation2Enm());

            // 講師：受講者への前向きコメント
            prmDto.setTPositiveCmt(lessonCommentTrnDto.getTPositiveCmt());

            // 講師：講師からスクールへのコメント
            prmDto.setTOnSchoolCmt(lessonCommentTrnDto.getTOnSchoolCmt());

            // 講師：推奨レベル区分
            prmDto.setTRecommendedLevelKbn(lessonCommentTrnDto.getTRecommendedLevelKbn());

            // 講師：推奨レベル
            prmDto.setTRecommendedLevelEnm(lessonCommentTrnDto.getTRecommendedLevelEnm());

            // 講師：受講者を引継ぎするコメント
            prmDto.setTNextTeacherCmt(lessonCommentTrnDto.getTNextTeacherCmt());

            // 受講者：講師への評価区分
            prmDto.setCEvaluationKbn(lessonCommentTrnDto.getCEvaluationKbn());

            // 受講者：講師への評価
            prmDto.setCEvaluationJnm(lessonCommentTrnDto.getCEvaluationJnm());

            // 受講者：レッスンに対する講師への意見コメント
            prmDto.setCOnTeacherCmt(lessonCommentTrnDto.getCOnTeacherCmt());

        } else {

            // 登録者コード
            prmDto.setInsertCd(userId);
        }

        // 予約No
        prmDto.setReservationNo(model.getReservationNo_hid());

        // コメント入力者区分
        prmDto.setCmtInputsKbn(model.getCmtInputsKbn());

        // スクール：スクール側から講師へのコメント
        prmDto.setSOnTeacherCmt(model.getSchoolComment_txa().replaceAll(NaikaraTalkConstants.NEW_LINE_CODE_WIN,
                NaikaraTalkConstants.NEW_LINE_CODE_UNIX));

        // レコードバージョン番号
        prmDto.setRecordVerNo(model.getRecordVerNoForSchool());

        // 更新者コード
        prmDto.setUpdateCd(userId);

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
    private LessonCommentTrnDto modelToDtoForTeacher(TeacherLessonReportModel model) throws NaiException {

        // ユーザIDを取得
        String userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();

        // DTOの初期化
        LessonCommentTrnDto prmDto = new LessonCommentTrnDto();

        if (StringUtils.equals(model.getMode(), UPDATE_KBN)) {

            LessonCommentTrnDto lessonCommentTrnDto = this.select(model);

            // 受講者：講師への評価区分
            prmDto.setCEvaluationKbn(lessonCommentTrnDto.getCEvaluationKbn());

            // 受講者：講師への評価
            prmDto.setCEvaluationJnm(lessonCommentTrnDto.getCEvaluationJnm());

            // 受講者：レッスンに対する講師への意見コメント
            prmDto.setCOnTeacherCmt(lessonCommentTrnDto.getCOnTeacherCmt());

            // スクール：スクール側から講師へのコメント
            prmDto.setSOnTeacherCmt(lessonCommentTrnDto.getSOnTeacherCmt());

        } else {

            // 登録者コード
            prmDto.setInsertCd(userId);
        }

        // 予約No
        prmDto.setReservationNo(model.getReservationNo_hid());

        // コメント入力者区分
        prmDto.setCmtInputsKbn(model.getCmtInputsKbn());

        // 講師：講師のレッスン自己分析１区分
        prmDto.setTSelfEvaluation1Kbn(model.getInstruction_rdl());

        // 講師：講師のレッスン自己分析１
        StringBuffer tSelfEvaluation1Enm = new StringBuffer();
        tSelfEvaluation1Enm
                .append(this.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_SELF_EVALUATION_T).get(
                        model.getInstruction_rdl()))
                .append(COLON)
                .append(this.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_SELF_EVALUATION).get(
                        model.getInstruction_rdl()));
        prmDto.setTSelfEvaluation1Enm(tSelfEvaluation1Enm.toString());

        // 講師：講師のレッスン自己分析２区分
        prmDto.setTSelfEvaluation2Kbn(model.getSatisfied_rdl());

        // 講師：講師のレッスン自己分析２
        StringBuffer tSelfEvaluation2Enm = new StringBuffer();
        tSelfEvaluation2Enm
                .append(this.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_SELF_EVALUATION_T).get(
                        model.getSatisfied_rdl()))
                .append(COLON)
                .append(this.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_SELF_EVALUATION).get(
                        model.getSatisfied_rdl()));
        prmDto.setTSelfEvaluation2Enm(tSelfEvaluation2Enm.toString());

        // 講師：受講者への前向きコメント
        prmDto.setTPositiveCmt(model.getPositiveComment_txa().replaceAll(NaikaraTalkConstants.NEW_LINE_CODE_WIN,
                NaikaraTalkConstants.NEW_LINE_CODE_UNIX));

        // 講師：講師からスクールへのコメント
        prmDto.setTOnSchoolCmt(model.getTeacherComment_txa().replaceAll(NaikaraTalkConstants.NEW_LINE_CODE_WIN,
                NaikaraTalkConstants.NEW_LINE_CODE_UNIX));

        // 講師：推奨レベル区分
        prmDto.setTRecommendedLevelKbn(model.getLevel_rdl());

        // 講師：推奨レベル
        StringBuffer tRecommendedLevelEnm = new StringBuffer();
        tRecommendedLevelEnm
                .append(this.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_EVALUATION_FROM_TEAC_TO_PERS_T).get(
                        model.getLevel_rdl()))
                .append(COLON)
                .append(this.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_EVALUATION_FROM_TEAC_TO_PERS).get(
                        model.getLevel_rdl()));
        prmDto.setTRecommendedLevelEnm(tRecommendedLevelEnm.toString());

        // 講師：受講者を引継ぎするコメント
        prmDto.setTNextTeacherCmt(model.getInheriting_txa().replaceAll(NaikaraTalkConstants.NEW_LINE_CODE_WIN,
                NaikaraTalkConstants.NEW_LINE_CODE_UNIX));

        // レコードバージョン番号
        prmDto.setRecordVerNo(model.getRecordVerNoForTeacher());

        // 更新者コード
        prmDto.setUpdateCd(userId);

        return prmDto;
    }

    /**
     * データの存在チェック<br>
     * <br>
     * データの存在チェックを行う<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @return int チェック結果<br>
     * @exception NaiException
     */
    public int getExists(TeacherLessonReportModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            // ロジックの初期化
            TeacherLessonReportLogic teacherLessonReportLogic = new TeacherLessonReportLogic(conn);

            // Model値をDTOにセット
            LessonCommentTrnDto prmDto = this.modelToDto(model);

            // 検索を実行
            return teacherLessonReportLogic.getExists(prmDto);

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
}