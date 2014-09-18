package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedHashMap;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.LessonResPerCommentDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.LessonResPerCommentLogic;
import com.naikara_talk.logic.TeacherLessonReportLogic;
import com.naikara_talk.model.TeacherLessonReportModel;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>講師。<br>
 * <b>クラス名称　　　:</b>講師側の視点から_1-2_レッスン実績初期処理Serviceクラス。<br>
 * <b>クラス概要　　　:</b>講師側の視点から_1-2_レッスン実績初期処理Service。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/12 TECS 新規作成。
 */
public class TeacherLessonReportLoadService implements ActionService {

    /** ダッシュ */
    private static final String DASH = "-";

    /** Date型文字列書式 */
    private static final String DATE_FORMAT_HH_mm = "HH:mm";

    /** コロン */
    private static final String COLON = "：";

    /**
     * 初期処理<br>
     * <br>
     * 画面項目の初期処理を行う<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @return model 新画面パラメータ<br>
     * @exception NaiException
     */
    public TeacherLessonReportModel select(TeacherLessonReportModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // ロジックの初期化
            LessonResPerCommentLogic lessonResPerCommentLogic = new LessonResPerCommentLogic(conn);

            // 検索を実行
            LessonResPerCommentDto lessonResPerCommentDto = lessonResPerCommentLogic.getLessonResPerComment(model
                    .getReservationNo_hid());

            // DTO値をModelにセット
            model = this.dtoToModel(lessonResPerCommentDto, model);

            return model;

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
     * データの存在チェック<br>
     * <br>
     * データの存在チェックを行う<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @return boolean チェック結果<br>
     * @exception NaiException
     */
    public int isExists(TeacherLessonReportModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // ロジックの初期化
            LessonResPerCommentLogic lessonResPerCommentLogic = new LessonResPerCommentLogic(conn);

            // 検索を実行
            LessonResPerCommentDto lessonResPerCommentDto = lessonResPerCommentLogic.getLessonResPerComment(model
                    .getReservationNo_hid());

            // データありの場合
            int existsFlg = lessonResPerCommentDto.getReturnCode();

            conn.commit();
            return existsFlg;

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
     * DTO値をセット<br>
     * <br>
     * DTO値をModelにセット<br>
     * <br>
     * @param prmDto 変換前DTO<br>
     * @param model 画面パラメータ<br>
     * @return model 新画面パラメータ<br>
     * @exception NaiException
     */
    private TeacherLessonReportModel dtoToModel(LessonResPerCommentDto prmDto, TeacherLessonReportModel model)
            throws NaiException {

        // 自己評価1
        model.setInstruction_rdl(prmDto.getTSelfEvaluation1Kbn());

        // 自己評価2
        model.setSatisfied_rdl(prmDto.getTSelfEvaluation2Kbn());

        // 推奨レベル
        model.setLevel_rdl(prmDto.getTRecommendedLevelKbn());

        // レッスン日
        model.setLessonDt_lbl(prmDto.getLessonDt());

        // レッスン時刻
        model.setLessonTmNm_lbl(prmDto.getLessonTmNm());

        StringBuffer sb = new StringBuffer();

        if (prmDto.getTeacherStartDttm() != null) {

            sb.append(DateUtil.toString(prmDto.getTeacherStartDttm(), DATE_FORMAT_HH_mm));
        }

        sb.append(DASH);

        if (prmDto.getTeacherEndDttm() != null) {

            sb.append(DateUtil.toString(prmDto.getTeacherEndDttm(), DATE_FORMAT_HH_mm));
        }

        // 実績時刻(講師)
        model.setTeacherStartDttm_lbl(sb.toString());

        sb.setLength(0);

        if (prmDto.getStudentStartDttm() != null) {

            sb.append(DateUtil.toString(prmDto.getStudentStartDttm(), DATE_FORMAT_HH_mm));
        }

        sb.append(DASH);

        if (prmDto.getStudentEndDttm() != null) {

            sb.append(DateUtil.toString(prmDto.getStudentEndDttm(), DATE_FORMAT_HH_mm));
        }

        // 実績時刻(受講者)
        model.setStudentStartDttm_lbl(sb.toString());

        // コース名(英語)
        model.setCourseEnm_lbl(NaikaraStringUtil.unionString4(prmDto.getBigClassificationEnm(),
                prmDto.getMiddleClassificationEnm(), prmDto.getSmallClassificationEnm(), prmDto.getCourseEnm()));

        // コース名
        model.setCourseJnm_lbl(NaikaraStringUtil.unionString4(prmDto.getBigClassificationJnm(),
                prmDto.getMiddleClassificationJnm(), prmDto.getSmallClassificationJnm(), prmDto.getCourseJnm()));

        // 受講者名
        model.setStudentNickNm_lbl(prmDto.getStudentNickNm());

        // 講師から受講者への前向きなコメント
        model.setPositiveComment_txa(prmDto.getTPositiveCmt());

        // 受講者から講師へのコメント
        model.setCOnTeacherCmt_lbl(prmDto.getCOnTeacherCmt().replaceAll(NaikaraTalkConstants.NEW_LINE_CODE_UNIX,
                NaikaraTalkConstants.MAIL_NEW_LINE_CODE));

        String role = NaikaraTalkConstants.BRANK;

        // ロールを取得
        if ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString()) != null) {

            role = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getRole();
        }

        // ロールを判断
        if (StringUtils.equals(role, SessionUser.ROLE_TEACHER)) {

            // スクールへのコメント
            model.setTeacherComment_txa(prmDto.getTOnSchoolCmt());

            // 受講者を引継ぎするコメント
            model.setInheriting_txa(prmDto.getTNextTeacherCmt());

            // スクールから講師へのコメント
            model.setSchoolComment_txa(prmDto.getSOnTeacherCmt().replaceAll(NaikaraTalkConstants.NEW_LINE_CODE_UNIX,
                    NaikaraTalkConstants.MAIL_NEW_LINE_CODE));

        } else {

            // スクールへのコメント
            model.setTeacherComment_txa(prmDto.getTOnSchoolCmt().replaceAll(NaikaraTalkConstants.NEW_LINE_CODE_UNIX,
                    NaikaraTalkConstants.MAIL_NEW_LINE_CODE));

            // 受講者を引継ぎするコメント
            model.setInheriting_txa(prmDto.getTNextTeacherCmt().replaceAll(NaikaraTalkConstants.NEW_LINE_CODE_UNIX,
                    NaikaraTalkConstants.MAIL_NEW_LINE_CODE));

            // スクールから講師へのコメント
            model.setSchoolComment_txa(prmDto.getSOnTeacherCmt());
        }

        // 予約No
        model.setReservationNo_hid(prmDto.getReservationNo());

        // レコードバージョン番号
        model.setRecordVerNo(prmDto.getRecordVerNo());

        // レコードバージョン番号(講師)
        model.setRecordVerNoForTeacher(prmDto.getRecordVerNoForTeacher());

        // レコードバージョン番号(受講者)
        model.setRecordVerNoForStudent(prmDto.getRecordVerNoForStudent());

        // レコードバージョン番号(スクール)
        model.setRecordVerNoForSchool(prmDto.getRecordVerNoForSchool());

        return model;
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
    public LinkedHashMap<String, String> getCourseSell(String eCategory, String jCategory) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // ロジックの初期化
            TeacherLessonReportLogic teacherLessonReportLogic = new TeacherLessonReportLogic(conn);

            // コード管理マスタを検索
            LinkedHashMap<String, String> eMap = teacherLessonReportLogic.selectCodeMst(eCategory);

            // コード管理マスタを検索
            LinkedHashMap<String, String> jMap = teacherLessonReportLogic.selectCodeMst(jCategory);

            LinkedHashMap<String, String> hashMap = new LinkedHashMap<String, String>();

            Iterator<String> eIter = eMap.keySet().iterator();

            Iterator<String> jIter = jMap.keySet().iterator();

            while (eIter.hasNext()) {

                jIter.hasNext();

                String key = eIter.next();

                String value = new StringBuffer().append(eMap.get(key)).append(COLON).append(jMap.get(key)).toString();

                hashMap.put(key, value);
            }

            conn.commit();
            return hashMap;

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