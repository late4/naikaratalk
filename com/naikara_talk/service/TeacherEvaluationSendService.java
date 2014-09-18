package com.naikara_talk.service;

import java.sql.Connection;
import java.util.LinkedHashMap;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.LessonCommentTrnDto;
import com.naikara_talk.dto.TeacherMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.TeacherEvaluationLogic;
import com.naikara_talk.model.TeacherEvaluationModel;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_受講処理<br>
 * <b>クラス名称       :</b>講師評価画面<br>
 * <b>クラス概要       :</b>講師評価画面送信するService<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/10 TECS 新規作成
 */
public class TeacherEvaluationSendService implements ActionService {

    /** コロン （項目「受講者：講師への評価」で使用）*/
    private static final String COLON = "：";

    /**
     * 送信する処理。<br>
     * <br>
     * 送信する処理。<br>
     * <br>
     * @param TeacherEvaluationModel<br>
     * @return なし<br>
     * @exception NaiException
     */
    public int send(TeacherEvaluationModel model) throws NaiException {
        int cnt = NaikaraTalkConstants.RETURN_CD_ERR_UPD;

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            if (NaikaraTalkConstants.RETURN_CD_DATA_YES == this.getExist(model, conn)) {
                cnt = this.updateLessonCommentTrn(model, conn);
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
            } else {
                cnt = this.insertLessonCommentTrn(model, conn);
                if (NaikaraTalkConstants.RETURN_CD_ERR_INS == cnt) {
                    // ロールバック
                    try {
                        conn.rollback();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                        throw new NaiException(e1);
                    }
                    return cnt;
                }
            }
            cnt = this.updateTeacherMst(model, conn);
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
            if (StringUtils.equals(NaikaraTalkConstants.TRUE, model.getBookmark())) {
                int tempCnt = this.insertTeacherBookmark(model.getStudentId(), model.getTeacherId(), conn);
                if (NaikaraTalkConstants.RETURN_CD_ERR_INS == tempCnt) {
                    cnt = tempCnt;
                    // ロールバック
                    try {
                        conn.rollback();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                        throw new NaiException(e1);
                    }
                    return cnt;
                }
            }

            // コミット
            conn.commit();

        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
                throw new NaiException(e1);
            }
            throw new NaiException(e);
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
     * 遷移チェック<br>
     * <br>
     * 遷移チェックを行う<br>
     * <br>
     * @param なし<br>
     * @return boolean 結果フラグ<br>
     * @exception なし
     */
    public boolean redirectCheck() {

        // ユーザroleを取得
        String role = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getRole();

        // 遷移チェック
        if (StringUtils.equals(role, SessionUser.ROLE_STUDENT)) {
            return true;
        }

        return false;
    }

    /**
     * データの存在チェック。<br>
     * <br>
     * 画面初期表示。<br>
     * <br>
     * @param PointControlModel  画面のパラメータ<br>
     * @return int<br>
     * @exception NaiException
     */
    private int getExist(TeacherEvaluationModel model, Connection conn) throws NaiException {

        TeacherEvaluationLogic teacherEvaluationLogic = new TeacherEvaluationLogic(conn);
        // DTOの初期化
        LessonCommentTrnDto prmDto = new LessonCommentTrnDto();

        // Model値をDTOにセット
        prmDto = this.modelToLessonCommentTrnDto(model, prmDto, conn);

        // 検索実行
        return teacherEvaluationLogic.getExist(prmDto);

    }

    /**
     * レッスンコメントテーブル更新処理<br>
     * <br>
     * レッスンコメントテーブル更新処理<br>
     * <br>
     * @param LessonCommentTrnDto <br>
     * @return int <br>
     * @exception NaiException
     */
    private int updateLessonCommentTrn(TeacherEvaluationModel model, Connection conn) throws NaiException {

        TeacherEvaluationLogic teacherEvaluationLogic = new TeacherEvaluationLogic(conn);
        // DTOの初期化
        LessonCommentTrnDto prmDto = new LessonCommentTrnDto();

        // Model値をDTOにセット
        try {

            prmDto = this.modelToLessonCommentTrnDto(model, prmDto, conn);

            // レッスンコメントテーブル取得
            LessonCommentTrnDto resDto = teacherEvaluationLogic.searchLessonCommentTrn(prmDto);
            if (resDto != null) {
                resDto = this.modelToLessonCommentTrnDto(model, resDto, conn);
                // レッスンコメントテーブル更新
                return teacherEvaluationLogic.updateLessonCommentTrn(resDto);
            }
        } catch (Exception e) {
            throw new NaiException(e);
        }
        return NaikaraTalkConstants.RETURN_CD_ERR_UPD;

    }

    /**
     * レッスンコメントテーブル登録処理<br>
     * <br>
     * レッスンコメントテーブル登録処理<br>
     * <br>
     * @param LessonCommentTrnDto <br>
     * @return int <br>
     * @exception NaiException
     */
    private int insertLessonCommentTrn(TeacherEvaluationModel model, Connection conn) throws NaiException {

        TeacherEvaluationLogic teacherEvaluationLogic = new TeacherEvaluationLogic(conn);
        // DTOの初期化
        LessonCommentTrnDto prmDto = new LessonCommentTrnDto();

        // Model値をDTOにセット
        prmDto = this.modelToLessonCommentTrnDto(model, prmDto, conn);

        // レッスンコメントテーブル登録
        return teacherEvaluationLogic.insertLessonCommentTrn(prmDto);

    }

    /**
     * 講師マスタ更新処理<br>
     * <br>
     * 講師マスタ更新処理<br>
     * <br>
     * @param TeacherMstDto <br>
     * @return int <br>
     * @exception NaiException
     */
    private int updateTeacherMst(TeacherEvaluationModel model, Connection conn) throws NaiException {

        TeacherEvaluationLogic teacherEvaluationLogic = new TeacherEvaluationLogic(conn);
        // DTOの初期化
        TeacherMstDto prmDto = new TeacherMstDto();

        // Model値をDTOにセット
        try {

            prmDto = this.modelToTeacherMstDto(model, prmDto);

            // レッスンコメントテーブル取得
            TeacherMstDto resDto = teacherEvaluationLogic.searchTeacherMst(prmDto);
            if (resDto != null) {
                resDto = this.modelToTeacherMstDto(model, resDto);
                // レッスンコメントテーブル更新
                return teacherEvaluationLogic.updateTeacherMst(resDto);
            }
        } catch (Exception e) {
            throw new NaiException(e);
        }
        return NaikaraTalkConstants.RETURN_CD_ERR_UPD;

    }

    /**
     * 受講者別講師ブックマークテーブル登録処理<br>
     * <br>
     * 受講者別講師ブックマークテーブル登録処理<br>
     * <br>
     * @param TeacherBookmarkMstDto <br>
     * @return int <br>
     * @exception NaiException
     */
    private int insertTeacherBookmark(String studentId, String userId, Connection conn) throws NaiException {

        TeacherEvaluationLogic teacherEvaluationLogic = new TeacherEvaluationLogic(conn);
        return teacherEvaluationLogic.insertTeacherBookmark(studentId, userId);
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
    public LinkedHashMap<String, String> selectCodeMst(String category, Connection conn) throws NaiException {

        TeacherEvaluationLogic teacherEvaluationLogic = new TeacherEvaluationLogic(conn);

        // コード管理マスタ検索
        return teacherEvaluationLogic.selectCodeMst(category);

    }

    /**
     * Model値をDTOにセット。<br>
     * <br>
     * Model値をDTOにセット。<br>
     * <br>
     * @param TeacherEvaluationModel LessonCommentTrnDto<br>
     * @return LessonCommentTrnDto<br>
     * @throws NaiException
     * @exception なし
     */
    private LessonCommentTrnDto modelToLessonCommentTrnDto(TeacherEvaluationModel model, LessonCommentTrnDto prmDto,
            Connection conn) throws NaiException {

        // 予約No
        prmDto.setReservationNo(model.getReservationNo());
        // コメント入力者区分
        prmDto.setCmtInputsKbn(model.getCmtInputsKbn());
        // 受講者：講師への評価区分
        prmDto.setCEvaluationKbn(model.getcEvaluationKbn());


        // 受講者：講師への評価
        StringBuffer cEvaluationJnm = new StringBuffer();
        // 英語版
        cEvaluationJnm.append(this.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_EVALUATION_FROM_PERS_TO_TEAC_T,
                conn).get(model.getcEvaluationKbn()));
        // 文字の区切り
        cEvaluationJnm.append(COLON);
        // 日本語版
        cEvaluationJnm.append(this.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_EVALUATION_FROM_PERS_TO_TEAC,
                conn).get(model.getcEvaluationKbn()));
        prmDto.setCEvaluationJnm(cEvaluationJnm.toString());


        // 受講者：レッスンに対する講師への意見コメント
        prmDto.setCOnTeacherCmt(model.getcOnTeacherCmt().replaceAll(NaikaraTalkConstants.NEW_LINE_CODE_WIN,
                NaikaraTalkConstants.NEW_LINE_CODE_UNIX));
        // レコードバージョン番号
        prmDto.setRecordVerNo(model.getRecordVerNoLCT());

        return prmDto;

    }

    /**
     * Model値をDTOにセット。<br>
     * <br>
     * Model値をDTOにセット。<br>
     * <br>
     * @param TeacherEvaluationModel TeacherMstDto<br>
     * @return TeacherMstDto<br>
     * @exception なし
     */
    private TeacherMstDto modelToTeacherMstDto(TeacherEvaluationModel model, TeacherMstDto prmDto) {

        // 利用者ID
        prmDto.setUserId(model.getTeacherId());
        // 最新の受講生から講師へのコメント
        prmDto.setLatestEvaluationFromStudentCmt(model.getcOnTeacherCmt().replaceAll(
                NaikaraTalkConstants.NEW_LINE_CODE_WIN, NaikaraTalkConstants.NEW_LINE_CODE_UNIX));
        // レコードバージョン番号
        prmDto.setRecordVerNo(model.getRecordVerNoT());

        return prmDto;
    }
}
