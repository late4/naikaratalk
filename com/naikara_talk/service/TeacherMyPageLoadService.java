package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.LessonReservationPerformanceTrnDto;
import com.naikara_talk.dto.LocSumTimeDto;
import com.naikara_talk.dto.SchResTLesResPerTStuMLocSumTMDto;
import com.naikara_talk.dto.ScheduleReservationTrnDto;
import com.naikara_talk.dto.StudentMstDto;
import com.naikara_talk.dto.TeacherMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.LessonSumTimeLogic;
import com.naikara_talk.logic.TeacherMyPageLogic;
import com.naikara_talk.model.TeacherMyPageModel;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>講師用個別マイページ。<br>
 * <b>クラス名称       :</b>講師用個別マイページ初期処理Serviceクラス。<br>
 * <b>クラス概要       :</b>講師が下記の情報照会ができる<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/09 TECS 新規作成
 *                     :</b>2014/04/10 TECS 変更 予約Noの追加(添付付メール送信画面用)
 *                     :</b>2014/06/02 TECS レッスン予約に関する「応相談」対応
 */
public class TeacherMyPageLoadService implements ActionService {

    /**
     * 講師マスタのデータ取得処理。<br>
     * <br>
     * @param model TeacherMyPageModel
     * @return model TeacherMyPageModel
     * @throws NaiException
     */
    public TeacherMyPageModel selectTeacherMst(TeacherMyPageModel model) throws NaiException {
        Connection conn = null;

        try {

            conn = DbUtil.getConnection();
            TeacherMyPageLogic teacherMyPageLogic = new TeacherMyPageLogic(conn);
            // DTOの初期化
            TeacherMstDto prmDto = new TeacherMstDto();

            // Model値をDTOにセット
            prmDto.setUserId(model.getUserId());

            // 検索実行
            TeacherMstDto resultDto = teacherMyPageLogic.selectTeacherMst(prmDto);

            // DTO値をModelにセット
            model = this.dtoToModel(resultDto, model);
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
     * 講師予定予約テーブルのデータ取得処理。<br>
     * <br>
     * @param model TeacherMyPageModel
     * @return model TeacherMyPageModel
     * @throws NaiException
     */
    public TeacherMyPageModel select(TeacherMyPageModel model) throws NaiException {
        Connection conn = null;

        try {

            conn = DbUtil.getConnection();

            TeacherMyPageLogic teacherMyPageLogic = new TeacherMyPageLogic(conn);
            // DTOの生成
            ScheduleReservationTrnDto prmScheduleReservationTrnDto = new ScheduleReservationTrnDto();

            // Model値をDTOにセット
            prmScheduleReservationTrnDto = this.modelToDto(model);

            // ◆◆◆検索実行
            List<ScheduleReservationTrnDto> scheduleReservationTrnDtoList = teacherMyPageLogic
                    .selectScheduleReservationTrnDto(prmScheduleReservationTrnDto);

            // 汎用フィールド名の取得
            CodeManagMstCache cache = CodeManagMstCache.getInstance();
            // レッスン時刻の名称一覧取得
            LinkedHashMap<String, CodeManagMstDto> lessonTmList = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_LESSON_TM_S);

            // (講師英語用)の名称一覧取得
            LinkedHashMap<String, CodeManagMstDto> mailKbnList = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_MAIL_KBN);
            // 添付メール送付済区分(講師英語用)の名称一覧取得
            LinkedHashMap<String, CodeManagMstDto> mailkbntList = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_MAIL_KBN_T);

            ArrayList<SchResTLesResPerTStuMLocSumTMDto> returnList = new ArrayList<SchResTLesResPerTStuMLocSumTMDto>();

            // ◆◆◆設定
            if (NaikaraTalkConstants.RETURN_CD_DATA_YES == scheduleReservationTrnDtoList.get(0).getReturnCode()) {
                for (int i = 0; i < scheduleReservationTrnDtoList.size(); i++) {

                    SchResTLesResPerTStuMLocSumTMDto dto = new SchResTLesResPerTStuMLocSumTMDto();

                    // レッスン予実テーブルから当日以降の予約済みのデータの取得処理
                    LessonReservationPerformanceTrnDto lessonCommentTrnDto = teacherMyPageLogic
                            .selectLessonReservationPerformanceTrnDto(scheduleReservationTrnDtoList.get(i)
                                    .getReservationNo());

                    // ローカルタイムとサマータイムの計算
                    LessonSumTimeLogic lessonSumTimeLogic = new LessonSumTimeLogic(conn);
                    ArrayList<LocSumTimeDto> locSumTimeDtoList = lessonSumTimeLogic.getLocSumTime(
                            scheduleReservationTrnDtoList.get(i).getTeacherId(),
                            lessonTmList.get(scheduleReservationTrnDtoList.get(i).getLessonTmCd()).getManagerNm());

                    // 受講者マスタから対象データの取得処理
                    StudentMstDto studentMstDto = teacherMyPageLogic.selectStudentMstDto(scheduleReservationTrnDtoList
                            .get(i).getStudentId());

                    // 日本日付
                    dto.setLessonDt(scheduleReservationTrnDtoList.get(i).getLessonDt());
                    // 日本時刻
                    dto.setLessonTm(lessonTmList.get(scheduleReservationTrnDtoList.get(i).getLessonTmCd())
                            .getManagerNm());
                    // 受講者ID
                    dto.setStudentId(scheduleReservationTrnDtoList.get(i).getStudentId());
                    // 予約No
                    dto.setReservationNo(scheduleReservationTrnDtoList.get(i).getReservationNo());

                    // 2014/06/02 レッスン予約に関する「応相談」対応 Add Start
                    // 予約区分(予約状況)
                    String reservationKbn = scheduleReservationTrnDtoList.get(i).getReservationKbn();
                    dto.setReservationKbn(reservationKbn);

                    // 予約区分(予約状況)英語表示
                    String reservationKbnEnm = cache.decode(NaikaraTalkConstants.CODE_CATEGORY_RESERV_KBN_T, reservationKbn);
                    dto.setReservationKbnEnm(reservationKbnEnm);

                    // 予約区分(予約状況)日本語表示
                    String reservationKbnJnm = cache.decode(NaikaraTalkConstants.CODE_CATEGORY_RESERV_KBN_STUDENT_MYPAGE, reservationKbn);
                    dto.setReservationKbnJnm(reservationKbnJnm);
                    // 2014/06/02 レッスン予約に関する「応相談」対応 Add End

                    // ローカルタイム
                    dto.setLocalTimeFromTo(locSumTimeDtoList.get(0).getLocalTimeFromTo());
                    // サマータイム
                    dto.setSumTimeFromTo(locSumTimeDtoList.get(0).getSumTimeFromTo());

                    // コース名
                    dto.setCourseJnm(NaikaraStringUtil.unionString4(lessonCommentTrnDto.getBigClassificationJnm(),
                            lessonCommentTrnDto.getMiddleClassificationJnm(),
                            lessonCommentTrnDto.getSmallClassificationJnm(), lessonCommentTrnDto.getCourseJnm()));
                    // コース名(英語)
                    dto.setCourseEnm(NaikaraStringUtil.unionString4(lessonCommentTrnDto.getBigClassificationEnm(),
                            lessonCommentTrnDto.getMiddleClassificationEnm(),
                            lessonCommentTrnDto.getSmallClassificationEnm(), lessonCommentTrnDto.getCourseEnm()));
                    // 添付メール送付済
                    dto.setMailKbnJnm(mailKbnList.get(lessonCommentTrnDto.getMailKbn()).getManagerNm());
                    // 添付メール送付済(英語)
                    dto.setMailKbnEnm(mailkbntList.get(lessonCommentTrnDto.getMailKbn()).getManagerNm());
                    // 添付メール送付日
                    dto.setMailDt(NaikaraStringUtil.converToMM_DD(lessonCommentTrnDto.getMailDt()));

                    // 受講者（ニックネーム）
                    dto.setNickNm(studentMstDto.getNickNm());

                    // 2014.04.10. 予約Noの追加(添付付メール送信画面用) Start
                    dto.setReservationNo(lessonCommentTrnDto.getReservationNo());
                    // 2014.04.10. 予約Noの追加(添付付メール送信画面用) End

                    returnList.add(dto);
                }
            }

            model.setMailAdd(teacherMyPageLogic.selectCodeManagMstDto(
                    NaikaraTalkConstants.CODE_CATEGORY_CONTACT_MAIL_ADDRESS_T).getManagerNm());
            model.setOsiraseJ(teacherMyPageLogic.selectCodeManagMstDto(
                    NaikaraTalkConstants.CODE_CATEGORY_NEWS_FROM_SCOOL_TO_TEAC_J).getManagerNm());
            model.setOsiraseE(teacherMyPageLogic.selectCodeManagMstDto(
                    NaikaraTalkConstants.CODE_CATEGORY_NEWS_FROM_SCOOL_TO_TEAC_E).getManagerNm());
            model.setSchResTLesResPerTStuMLocSumTMDto(returnList);
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
     * Model値をDTOにセット。<br>
     * <br>
     * @param model TeacherMyPageModel
     * @return ScheduleReservationTrnDto prmDto
     * @throws NaiException
     */
    private ScheduleReservationTrnDto modelToDto(TeacherMyPageModel model) throws NaiException {

        // DTOの初期化
        ScheduleReservationTrnDto prmDto = new ScheduleReservationTrnDto();

        prmDto.setTeacherId(model.getUserId()); // 利用者ID

        return prmDto;

    }

    /**
     * DTO値をModelにセット。<br>
     * <br>
     * @param prmDto TeacherMyPageModel
     * @param model TeacherMstDto
     * @return TeacherMyPageModel
     * @throws NaiException
     */
    private TeacherMyPageModel dtoToModel(TeacherMstDto prmDto, TeacherMyPageModel model) throws NaiException {

        model.setUserId(prmDto.getUserId());
        model.setNickAnm(prmDto.getNickAnm());
        model.setImgPhoto(prmDto.getImgPhoto());

        return model;

    }
}
