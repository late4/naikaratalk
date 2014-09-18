package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Iterator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.CourseMstDto;
import com.naikara_talk.dto.LessonReservationPerformanceTrnDto;
import com.naikara_talk.dto.LocSumTimeDto;
import com.naikara_talk.dto.ScheduleReservationTrnDto;
import com.naikara_talk.dto.StudentMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.LessonSumTimeLogic;
import com.naikara_talk.logic.TeacherApproveRequestLogic;
import com.naikara_talk.model.TeacherApproveRequestListModel;
import com.naikara_talk.model.TeacherApproveRequestModel;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>講師<br>
 * <b>クラス名称       :</b>応相談回答ページ初期処理Serviceクラス。<br>
 * <b>クラス概要       :</b>応相談回答ページ初期処理<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2014/06/02 TECS 新規作成
 */
public class TeacherApproveRequestLoadService implements ActionService {

    /** コロン */
    //private static final String COLON = "：";
    /** スラッシュ */
    private static final String SLASH = "／";

    /**
     * コード取得<br>
     * <br>
     * コード管理マスタからデータ取得処理<br>
     * <br>
     * @param category 汎用コード<br>
     * @return LinkedHashMap 取得結果<br>
     * @exception NaiException
     */
    public LinkedHashMap<String, String> getReplyKbn(String eCategory, String jCategory) throws NaiException {

        try {

            // ◆◆◆ 汎用フィールドのインスタンス取得(ラジオボタン：回答区分)
            CodeManagMstCache cache = CodeManagMstCache.getInstance();

            LinkedHashMap<String, CodeManagMstDto> eMap = cache.getList(eCategory);
            LinkedHashMap<String, CodeManagMstDto> jMap = cache.getList(jCategory);

            Iterator<String> eIter = eMap.keySet().iterator();
            Iterator<String> jIter = jMap.keySet().iterator();

            LinkedHashMap<String, String> hashMap = new LinkedHashMap<String, String>();

            // ◆◆◆ 英語、日本語の編集処理
            StringBuffer value = new StringBuffer();
            while (eIter.hasNext()) {
                jIter.hasNext();
                String key = eIter.next();
                value = new StringBuffer();
                value.append(eMap.get(key).getManagerNm()).append(SLASH);
                value.append(jMap.get(key).getManagerNm());
                hashMap.put(key, value.toString());
            }

            // ◆◆◆ 返却
            return hashMap;

        } catch (Exception e) {
            e.printStackTrace();
            throw new NaiException(e);
        }
    }

    /**
     * 講師予定予約テーブルのデータ取得処理。<br>
     * <br>
     * @param model TeacherApproveRequestModel
     * @return model TeacherApproveRequestModel
     * @throws NaiException
     */
    public TeacherApproveRequestModel select(TeacherApproveRequestModel model) throws NaiException {
        Connection conn = null;

        try {
            // ◆◆◆ DB接続
            conn = DbUtil.getConnection();

            // Logicの生成
            TeacherApproveRequestLogic logic = new TeacherApproveRequestLogic(conn);

            // DTOの生成
            ScheduleReservationTrnDto prmScheduleReservationTrnDto = new ScheduleReservationTrnDto();

            // ◆◆◆ Model値をDTOにセット
            prmScheduleReservationTrnDto = this.modelToDto(model);

            // ◆◆◆ 講師予定予約テーブルへの検索処理
            List<ScheduleReservationTrnDto> dtoList = logic.selectScheduleReservationTrn(prmScheduleReservationTrnDto);

            // ◆◆◆ DTO値をModelにセット
            model = this.dtoToModel(conn, logic, dtoList, model);

            // ◆◆◆ 返却
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
     * @param  model TeacherApproveRequestModel
     * @return prmDto ScheduleReservationTrnDto
     * @throws NaiException
     */
    private ScheduleReservationTrnDto modelToDto(TeacherApproveRequestModel model) throws NaiException {

        // dtoの生成
        ScheduleReservationTrnDto prmDto = new ScheduleReservationTrnDto();

        // ◆◆◆dtoへ設定
        prmDto.setTeacherId(model.getUserId());    // 利用者ID(講師ID)

        // ◆◆◆返却
        return prmDto;

    }

    /**
     * DTO値をModelにセット。<br>
     * <br>
     * @param dtoList List<ScheduleReservationTrnDto>
     * @param model TeacherApproveRequestModel
     * @return TeacherApproveRequestModel
     * @throws NaiException
     */
    private TeacherApproveRequestModel dtoToModel(
            Connection conn, TeacherApproveRequestLogic logic,
            List<ScheduleReservationTrnDto> dtoList, TeacherApproveRequestModel model) throws NaiException {

        String localTm = "";          // ローカルタイム
        String summerTm = "";         // サマータイム
        String courseCd = "";         // コースコード
        String courseNmE = "";        // コース名(英語)
        String courseNmJ = "";        // コース名(日本語)
        String stuNickNm = "";        // 受講者ニックネーム
        String requestedDttm = "";    // 応相談予約登録日時
        String lessonDt = "";         // レッスン日

        // 編集結果格納List<TeacherApproveRequestListModel>の生成
        List<TeacherApproveRequestListModel> returnList = new ArrayList<TeacherApproveRequestListModel>();

        // ◆◆◆ 汎用フィールドのインスタンス取得(表示：レッスン時刻名)
        CodeManagMstCache cache = CodeManagMstCache.getInstance();

        // ◆◆◆ 応相談予約登録日時用の編集用
        DateFormat df = new SimpleDateFormat(DateUtil.DATE_FORMAT_yy_MM_dd_HH_mm);

        // ◆◆◆ 値の設定処理
        if (NaikaraTalkConstants.RETURN_CD_DATA_YES == dtoList.get(0).getReturnCode()) {
            // 検索処理結果が正常の場合
            for (int i = 0; i < dtoList.size(); i++) {
                ScheduleReservationTrnDto dto = dtoList.get(i);

                // 編集結果格納TeacherApproveRequestListModelの生成
                TeacherApproveRequestListModel resultM = new TeacherApproveRequestListModel();

                // ◆◆◆レッスン時刻単位の時差計算処理 (ローカルタイムとサマータイム)
                String LessonTmNm = cache.decode(NaikaraTalkConstants.CODE_CATEGORY_LESSON_TM_S, dto.getLessonTmCd());
                LessonSumTimeLogic lesTmLogic = new LessonSumTimeLogic(conn);
                ArrayList<LocSumTimeDto> locSumTimeDtoList = lesTmLogic.getLocSumTime(dto.getTeacherId(), LessonTmNm);
                localTm = "";
                summerTm = "";
                if (locSumTimeDtoList != null) {
                    localTm = locSumTimeDtoList.get(0).getLocalTimeFromTo();    // ローカルタイム
                    summerTm = locSumTimeDtoList.get(0).getSumTimeFromTo();     // サマータイム
                }

                // ◆◆◆ レッスン予実テーブルへの検索処理 (主キー検索) ※コース名の取得
                LessonReservationPerformanceTrnDto lesDto = logic.selectLessonReservationPerformanceTrn(dto.getReservationNo());
                courseCd = "";
                courseNmE = "";
                courseNmJ = "";
                if (lesDto != null) {
                    courseCd = lesDto.getCourseCd();                                       // コースコード
                    courseNmE = NaikaraStringUtil.unionString4(
                            lesDto.getBigClassificationEnm(), lesDto.getMiddleClassificationEnm(),
                            lesDto.getSmallClassificationEnm(), lesDto.getCourseEnm());    // コース名(英語)

                    // 今後のメンテナンスで随時 短縮名にするとのこと。よって、この機能では「講師一覧表示用の短縮コース名」
                    //courseNmJ = NaikaraStringUtil.unionString4(
                    //        lesDto.getBigClassificationJnm(), lesDto.getMiddleClassificationJnm(),
                    //        lesDto.getSmallClassificationJnm(), lesDto.getCourseJnm());    // コース名(日本語)
                }

                // ◆◆◆ コースマスタへの検索処理 (主キー検索) ※「講師一覧表示用の短縮コース名」の取得
                if (courseCd != null && !StringUtils.isEmpty(courseCd)) {
                    CourseMstDto courseMDto = logic.selectCourseMst(courseCd);
                    if (courseMDto != null) {
                        // 今後のメンテナンスで随時 短縮名にするとのこと。よって、この機能では「講師一覧表示用の短縮コース名」
                        courseNmJ = courseMDto.getCourseJnmShort();
                    }
                }

                // ◆◆◆ 受講者マスタから対象データの取得処理
                StudentMstDto studentMstDto = logic.selectStudentMst(dto.getStudentId());
                stuNickNm = "";
                if (studentMstDto != null) {
                    stuNickNm = studentMstDto.getNickNm();
                    if (stuNickNm == null || StringUtils.isEmpty(stuNickNm)) {
                        // 法人対策
                        stuNickNm = NaikaraStringUtil.unionName(studentMstDto.getFamilyJnm(), studentMstDto.getFirstJnm());
                    }
                }

                // ◆◆◆ 値の設定
                requestedDttm = "";
                if (dto.getRequestedDttm() != null ) {
                    requestedDttm = df.format(dto.getRequestedDttm());
                }
                resultM.setRequestedDttm(requestedDttm);                        // 応相談予約登録日時
                lessonDt = "";
                if (dto.getLessonDt() != null && !StringUtils.isEmpty(dto.getLessonDt())) {
                    lessonDt = NaikaraStringUtil.converToYY_MM_DD(dto.getLessonDt());
                }
                resultM.setLessonDt(dto.getLessonDt());                         // 日本日付
                resultM.setLessonDtDsp(lessonDt);                               // 日本日付 ※画面表示用編集
                resultM.setLessonTmCd(dto.getLessonTmCd());                     // 日本時刻コード
                resultM.setLessonTmNm(LessonTmNm);                              // 日本時刻名
                resultM.setLocalTimeFromTo(localTm);                            // ローカルタイム
                resultM.setSumTimeFromTo(summerTm);                             // サマータイム
                resultM.setCourseCd(courseCd);                                  // コースコード
                resultM.setCourseEnm(courseNmE);                                // コース名(英語)
                resultM.setCourseJnm(courseNmJ);                                // コース名
                resultM.setStudentId(dto.getStudentId());                       // 受講者ID
                resultM.setStudentNickNm(stuNickNm);                            // 受講者（ニックネーム）
                resultM.setReservationNo(dto.getReservationNo());               // 予約No
                resultM.setReservationKbn(dto.getReservationKbn());             // 予約区分(予約状況)

                // ◆◆◆ リストへの追加
                returnList.add(resultM);
            }
        }

        // ◆◆◆modelへ設定
        model.setTargetList(returnList);

        // ◆◆◆返却
        return model;

    }
}
