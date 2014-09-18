package com.naikara_talk.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.HolidayListDto;
import com.naikara_talk.dto.LocSumTimeDto;
import com.naikara_talk.dto.SchResTCodeManagMDto;
import com.naikara_talk.dto.ScheduleReservationTrnDto;
import com.naikara_talk.dto.TeacherMstDto;
import com.naikara_talk.dto.TimeManagMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.HolidayListGetLogic;
import com.naikara_talk.logic.LessonSumTimeLogic;
import com.naikara_talk.logic.TeacherListLogic;
import com.naikara_talk.logic.TeacherScheduleLogic;
import com.naikara_talk.model.TeacherScheduleModel;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>講師スケジュールページ。<br>
 * <b>クラス名称       :</b>講師スケジュールページ初期処理Serviceクラス。<br>
 * <b>クラス概要       :</b>講師スケジュールの情報照会ができる<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/09 TECS 新規作成
 * <b>                 :</b>2014/06/02 TECS レッスン予約に関する「応相談」対応
 */
public class TeacherScheduleLoadService implements ActionService {


    // 2014/06/02 レッスン予約に関する「応相談」対応 Add Start
    /**
     * コード管理マスタからデータ取得処理。<br>
     * <br>
     * コード管理マスタからデータを取得処理する。<br>
     * <br>
     * @param category 汎用コード<br>
     * @return LinkedHashMap<String, String> ハッシュマップ<br>
     * @throws NaiException
     */
    public LinkedHashMap<String, String> selectCodeMst(String category) throws NaiException {

        Connection conn = null;

        try {
            // DBへの接続
            conn = DbUtil.getConnection();

            TeacherListLogic logic = new TeacherListLogic(conn);
            // コード管理マスタ検索
            return logic.selectCodeMst(category);

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
    // 2014/06/02 レッスン予約に関する「応相談」対応 Add End


    /**
     * 講師マスタのデータ取得処理。<br>
     * <br>
     * @param model TeacherScheduleModel
     * @return model TeacherScheduleModel
     * @throws NaiException
     */
    public TeacherMstDto selectTeacherMst(TeacherScheduleModel model) throws NaiException {
        Connection conn = null;
        try {
            // DBへの接続
            conn = DbUtil.getConnection();
            TeacherScheduleLogic teacherScheduleLogic = new TeacherScheduleLogic(conn);
            // DTOの初期化
            TeacherMstDto prmDto = new TeacherMstDto();

            // Model値をDTOにセット
            prmDto.setUserId(model.getUserId());

            // 検索実行
            TeacherMstDto resultDto = teacherScheduleLogic.selectTeacherMst(prmDto);

            return resultDto;
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
     * 講師マスタのデータ取得処理。<br>
     * <br>
     * @param model TeacherScheduleModel
     * @return model TeacherScheduleModel
     * @throws NaiException
     */
    public TimeManagMstDto selectTimeManag(TeacherMstDto prmDto) throws NaiException {
        Connection conn = null;
        try {
            // DBへの接続
            conn = DbUtil.getConnection();
            TeacherScheduleLogic teacherScheduleLogic = new TeacherScheduleLogic(conn);

            // 検索実行
            TimeManagMstDto resultDto = teacherScheduleLogic.selectTimeManag(prmDto);

            return resultDto;
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
     * @param model TeacherScheduleModel
     * @return model TeacherScheduleModel
     * @throws NaiException
     */
    public TeacherScheduleModel select(TeacherScheduleModel model) throws NaiException {

        // ◆◆◆講師マスタのデータ取得
        TeacherMstDto teacherMstDto = selectTeacherMst(model);

        if (teacherMstDto.getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_NO) {
            // 対象データなし
            model.setTeacherId(null);
            return model;
        }

        // ◆◆◆時差管理マスタのデータ取得
        TimeManagMstDto timeManagMstDto = selectTimeManag(teacherMstDto);

        // ◆◆◆汎用フィールド名の取得
        CodeManagMstCache cache = CodeManagMstCache.getInstance();
        // 符号取得
        LinkedHashMap<String, CodeManagMstDto> categorySignList = cache
                .getList(NaikaraTalkConstants.CODE_CATEGORY_SIGN_T);

        if (timeManagMstDto.getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_YES) {
            // 時間（符号）
            model.setTimeMark(categorySignList.get(timeManagMstDto.getTimeMarkKbn()).getManagerNm());
            // 時間(分)
            model.setTimeMinutes(new BigDecimal(timeManagMstDto.getTimeMinutes()).divide(new BigDecimal(60), 1,
                    BigDecimal.ROUND_DOWN));
            if (!StringUtils.isEmpty(timeManagMstDto.getSumTimeMarkKbn())) {
                // サマータイム(時間)(符号)
                model.setSumTimeMark(categorySignList.get(timeManagMstDto.getSumTimeMarkKbn()).getManagerNm());
            } else {
                model.setSumTimeMark("");
            }
            // サマータイム(分)
            model.setSumTimeMinutes(new BigDecimal(timeManagMstDto.getSumTimeMinutes()).divide(new BigDecimal(60), 1,
                    BigDecimal.ROUND_DOWN));
            // 備考
            model.setRemark(timeManagMstDto.getRemark());
        }

        Connection conn = null;

        try {

            // DBへの接続
            conn = DbUtil.getConnection();

            TeacherScheduleLogic teacherScheduleLogic = new TeacherScheduleLogic(conn);
            // 祝日判定
            HolidayListGetLogic holidayListGetLogic = new HolidayListGetLogic(conn);
            ArrayList<HolidayListDto> holidayListDto = holidayListGetLogic.chkHoliday(
                    DateUtil.getSysDateAddDay(model.getDateFlg()), 1, 14);

            // 日本時刻の取得
            List<CodeManagMstDto> codeManagMstDto = teacherScheduleLogic.selectCodeManagMstDto();

            // 共通部品：時差計算処理
            LessonSumTimeLogic lessonSumTimeLogic = new LessonSumTimeLogic(conn);
            ArrayList<LocSumTimeDto> locSumTimeDto = lessonSumTimeLogic.getLocSumTime(model.getUserId());

            // ｢00：00 - 06：00｣部分の明細
            ArrayList<SchResTCodeManagMDto> returnList0 = this.getDetailsList(model, holidayListDto, codeManagMstDto,
                    locSumTimeDto, 0);
            // ｢06：00 - 12：00｣部分の明細
            ArrayList<SchResTCodeManagMDto> returnList1 = this.getDetailsList(model, holidayListDto, codeManagMstDto,
                    locSumTimeDto, 9);
            // ｢12：00 - 18：00｣部分の明細
            ArrayList<SchResTCodeManagMDto> returnList2 = this.getDetailsList(model, holidayListDto, codeManagMstDto,
                    locSumTimeDto, 18);
            // ｢18：00 - 24：00｣部分の明細
            ArrayList<SchResTCodeManagMDto> returnList3 = this.getDetailsList(model, holidayListDto, codeManagMstDto,
                    locSumTimeDto, 27);

            // 講師ID
            model.setTeacherId(model.getUserId());
            // 日付曜日祝日クラス
            model.setHolidayListDto(holidayListDto);


            // 講師予定予約テーブルの データ0  ｢00：00 - 06：00｣
            model.setSchResTCodeManagMDto0(returnList0);
            // 講師予定予約テーブルの データ1  ｢06：00 - 12：00｣
            model.setSchResTCodeManagMDto1(returnList1);
            // 講師予定予約テーブルの データ2  ｢12：00 - 18：00｣
            model.setSchResTCodeManagMDto2(returnList2);
            // 講師予定予約テーブルの データ3  ｢18：00 - 24：00｣
            model.setSchResTCodeManagMDto3(returnList3);

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
     * 一覧明細部分の取得。<br>
     * <br>
     * 一覧明細部分を取得する。<br>
     * <br>
     * @param model モデル<br>
     * @param holidayList 祝日と曜日リスト<br>
     * @param codeManagMstDtoList 日本時刻リスト<br>
     * @param idx インデックス<br>
     * @return List<SchResTCodeManagMDto> 講師予定予約テーブルの明細データ<br>
     * @throws NaiException
     */
    public ArrayList<SchResTCodeManagMDto> getDetailsList(TeacherScheduleModel model, List<HolidayListDto> holidayList,
            List<CodeManagMstDto> codeManagMstDtoList, ArrayList<LocSumTimeDto> locSumTimeDto, int idx)
            throws NaiException {
        Connection conn = null;
        try {
            // DBへの接続
            conn = DbUtil.getConnection();
            // 初期化
            ArrayList<SchResTCodeManagMDto> detailsList = new ArrayList<SchResTCodeManagMDto>();

            // TeacherScheduleLogicの生成
            TeacherScheduleLogic teacherScheduleLogic = new TeacherScheduleLogic(conn);

            // 36コマを4分割のため、1時間帯あたり9コマ
            for (int i = idx; i < idx + 9; i++) {

                // SchResTCodeManagMDtoの生成
                SchResTCodeManagMDto schResTCodeManagMDto = new SchResTCodeManagMDto();

                // ScheduleReservationTrnDtoの生成
                ArrayList<ScheduleReservationTrnDto> list = new ArrayList<ScheduleReservationTrnDto>();

                for (int j = 0; j < holidayList.size(); j++) {
                    // ScheduleReservationTrnDtoの生成
                    ScheduleReservationTrnDto prmDto = new ScheduleReservationTrnDto();

                    prmDto.setTeacherId(model.getUserId());                             // 講師ID
                    prmDto.setLessonDt(holidayList.get(j).getDay());                    // レッスン日
                    prmDto.setLessonTmCd(codeManagMstDtoList.get(i).getManagerCd());    // レッスン時刻

                    // 講師予定予約テーブルから対象データの取得
                    ScheduleReservationTrnDto scheduleReservationTrnDto = teacherScheduleLogic
                            .selectScheduleReservationTrnDto(prmDto);

                    // 2014/06/02 レッスン予約に関する「応相談」対応 Add Start
                    if (scheduleReservationTrnDto.getLessonDt() == null) {
                        scheduleReservationTrnDto.setLessonDt(prmDto.getLessonDt());
                        scheduleReservationTrnDto.setLessonTmCd(prmDto.getLessonTmCd());
                    }

                    if (StringUtils.equals(NaikaraTalkConstants.RESERV_KBN_YES, scheduleReservationTrnDto.getReservationKbn())) {
                        // ◆ 予約可 の場合
                        scheduleReservationTrnDto.setDefaultSelValue(NaikaraTalkConstants.RESERV_STATE_YES);
                        scheduleReservationTrnDto.setDisabledFlg(NaikaraTalkConstants.FALSE);
                        scheduleReservationTrnDto.setReservationState(NaikaraTalkConstants.RESERV_STATE_YES);
                    } else if (StringUtils.equals(NaikaraTalkConstants.RESERV_KBN_ALREADY, scheduleReservationTrnDto.getReservationKbn())) {
                        // ◆ 予約済 の場合
                        scheduleReservationTrnDto.setDefaultSelValue(NaikaraTalkConstants.RESERV_STATE_ALREADY);
                        scheduleReservationTrnDto.setDisabledFlg(NaikaraTalkConstants.TRUE);
                        scheduleReservationTrnDto.setReservationState(NaikaraTalkConstants.RESERV_STATE_ALREADY);
                    } else if (StringUtils.equals(NaikaraTalkConstants.RESERV_KBN_CON_YES, scheduleReservationTrnDto.getReservationKbn())) {
                        // ◆ 応相談可 の場合
                        scheduleReservationTrnDto.setDefaultSelValue(NaikaraTalkConstants.RESERV_STATE_CON_YES);
                        scheduleReservationTrnDto.setDisabledFlg(NaikaraTalkConstants.FALSE);
                        scheduleReservationTrnDto.setReservationState(NaikaraTalkConstants.RESERV_STATE_CON_YES);
                    } else if (StringUtils.equals(NaikaraTalkConstants.RESERV_KBN_CON_PROVISIONAL_RESERV, scheduleReservationTrnDto.getReservationKbn())) {
                        // ◆ 応相談-予約済(仮予約) の場合
                        scheduleReservationTrnDto.setDefaultSelValue(NaikaraTalkConstants.RESERV_STATE_CON_PROVISIONAL_RESERV);
                        scheduleReservationTrnDto.setDisabledFlg(NaikaraTalkConstants.TRUE);
                        scheduleReservationTrnDto.setReservationState(NaikaraTalkConstants.RESERV_STATE_CON_PROVISIONAL_RESERV);
                    } else if (StringUtils.equals(NaikaraTalkConstants.RESERV_KBN_CON_ALREADY, scheduleReservationTrnDto.getReservationKbn())) {
                        // ◆ 応相談-予約済(確定予約) の場合 ⇒ 予約済
                        scheduleReservationTrnDto.setDefaultSelValue(NaikaraTalkConstants.RESERV_STATE_ALREADY);
                        scheduleReservationTrnDto.setDisabledFlg(NaikaraTalkConstants.TRUE);
                        scheduleReservationTrnDto.setReservationState(NaikaraTalkConstants.RESERV_STATE_ALREADY);
                    } else {
                        // ◆ 予約受付不可 の場合
                        scheduleReservationTrnDto.setDefaultSelValue(NaikaraTalkConstants.RESERV_STATE_NO);
                        scheduleReservationTrnDto.setDisabledFlg(NaikaraTalkConstants.FALSE);
                        scheduleReservationTrnDto.setReservationState(NaikaraTalkConstants.RESERV_STATE_NO);
                    }
                    // 2014/06/02 レッスン予約に関する「応相談」対応 Add End

                    // (ScheduleReservationTrnDto)リストへ追加
                    list.add(scheduleReservationTrnDto);
                }

                // * 日本時刻
                schResTCodeManagMDto.setManagerCd(codeManagMstDtoList.get(i).getManagerCd());          // 汎用コード
                schResTCodeManagMDto.setManagerNm(codeManagMstDtoList.get(i).getManagerNm());          // 汎用フィールド

                // * ローカルタイム／サマータイム
                schResTCodeManagMDto.setLocalTimeFromTo(locSumTimeDto.get(i).getLocalTimeFromTo());    // ローカルタイム
                schResTCodeManagMDto.setSumTimeFromTo(locSumTimeDto.get(i).getSumTimeFromTo());        // サマータイム

                // 講師予定予約テーブルDTOリスト
                schResTCodeManagMDto.setScheduleReservationTrnDto(list);

                // (SchResTCodeManagMDto)リストへ追加
                detailsList.add(schResTCodeManagMDto);
            }
            return detailsList;
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
