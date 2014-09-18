package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.HolidayListDto;
import com.naikara_talk.dto.SchResTCodeManagMDto;
import com.naikara_talk.dto.SchResTLesResPerTDto;
import com.naikara_talk.dto.TeacherCourseDto;
import com.naikara_talk.dto.UserMstTeacherMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.HolidayListGetLogic;
import com.naikara_talk.logic.ReservationCancellationTeacherScheduleLogic;
import com.naikara_talk.model.ReservationCancellationTeacherScheduleModel;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_予約処理<br>
 * <b>クラス名称       :</b>予約スケジュール初期化Serviceクラス<br>
 * <b>クラス概要       :</b>受講者が指定した講師の予約スケジュールを表示して、レッスン予約の登録／取消ができる。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/25 TECS 新規作成
 */
public class ReservationCancellationTeacherScheduleLoadService implements ActionService {

    /**
     * 利用者マスタ、受講者マスタデータ取得<br>
     * <br>
     * 利用者マスタ、受講者マスタリストを戻り値で返却する<br>
     * @param model モデル<br>
     * @return ReservationCancellationTeacherScheduleModel モデル<br>
     * @throws NaiException
     */
    public ReservationCancellationTeacherScheduleModel selectUserMstTeacherMst(
            ReservationCancellationTeacherScheduleModel model) throws NaiException {

        Connection conn = null;

        try {
            conn = DbUtil.getConnection();

            ReservationCancellationTeacherScheduleLogic logic = new ReservationCancellationTeacherScheduleLogic(conn);

            // DTOの初期化
            UserMstTeacherMstDto prmDto = new UserMstTeacherMstDto();

            // Model値をDTOにセット
            prmDto.setUserId(model.getTeacherId());

            // 検索実行
            UserMstTeacherMstDto resultDto = logic.selectUserMstTeacherMst(prmDto);

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
     * 講師別コースマスタ(+コースマスタ)リスト取得<br>
     * <br>
     * 講師別コースマスタ(+コースマスタ)リスト取得を行う<br>
     * @param teacherId 講師<br>
     * @param useStrD 利用開始日<br>
     * @param useEndDt 利用終了日<br>
     * @return List<TeacherCourseDto> 講師別コースマスタ(+コースマスタ)DTOリスト<br>
     * @throws NaiException
     */
    public List<TeacherCourseDto> selectTeacherCourseList(String teacherId, String useStrD, String useEndDt)
            throws NaiException {

        Connection conn = null;

        try {
            conn = DbUtil.getConnection();

            ReservationCancellationTeacherScheduleLogic logic = new ReservationCancellationTeacherScheduleLogic(conn);

            // DTOの初期化
            TeacherCourseDto prmDto = new TeacherCourseDto();

            // 講師ID
            prmDto.setUserId(teacherId);
            // 利用開始日
            prmDto.setUseStrDt(useStrD);
            // 利用終了日
            prmDto.setUseEndDt(useEndDt);

            // 検索実行
            return logic.selectTeacherCourseList(prmDto);

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
     * DTO値をModelにセット。<br>
     * <br>
     * DTO値をModelにセット。<br>
     * <br>
     * @param prmDto 利用者マスタ、講師マスタデータ取得DTO<br>
     * @param model モデル<br>
     * @return ReservationCancellationTeacherScheduleModel モデル<br>
     * @throws NaiException
     */
    private ReservationCancellationTeacherScheduleModel dtoToModel(UserMstTeacherMstDto prmDto,
            ReservationCancellationTeacherScheduleModel model) throws NaiException {

        // コード管理マスタのキャッシュ読み込み
        CodeManagMstCache cache = CodeManagMstCache.getInstance();

        // 母国語リスト
        LinkedHashMap<String, CodeManagMstDto> nativeLangList = cache
                .getList(NaikaraTalkConstants.CODE_CATEGORY_NATIVE_LANG);
        // 国籍リスト
        LinkedHashMap<String, CodeManagMstDto> nationalityList = cache
                .getList(NaikaraTalkConstants.CODE_CATEGORY_COUNTRY);

        // 講師名
        model.setTeacherNm(prmDto.getNickAnm());
        // 性別
        model.setGenderKbnNm(prmDto.getGenderKbnNm());
        // 母国語
        model.setNativeLang(nativeLangList.get(prmDto.getNativeLangCd()).getManagerNm());
        // 国籍
        model.setNationality(nationalityList.get(prmDto.getNationalityCd()).getManagerNm());

        return model;
    }

    /**
     * 一覧のヘッダ部と明細部の取得。<br>
     * <br>
     * 一覧のヘッダ部と明細部を取得する。<br>
     * <br>
     * @param model モデル
     * @return TeacherRegistrationModel モデル
     * @throws NaiException
     */
    public ReservationCancellationTeacherScheduleModel select(ReservationCancellationTeacherScheduleModel model)
            throws NaiException {

        Connection conn = null;

        try {
            conn = DbUtil.getConnection();

            ReservationCancellationTeacherScheduleLogic logic = new ReservationCancellationTeacherScheduleLogic(conn);

            // 利用者マスタ、講師マスタから対象データの取得処理を行う
            model = this.selectUserMstTeacherMst(model);

            // 祝日判定処理
            HolidayListGetLogic holidayListGetLogic = new HolidayListGetLogic(conn);

            // 祝日、曜日の取得
            ArrayList<HolidayListDto> holidayList = holidayListGetLogic.chkHoliday(
                    DateUtil.getSysDateAddDay(model.getDayCnt()), 1, 14);

            // 日本時刻の取得
            List<CodeManagMstDto> codeManagMstDtoList = logic.selectCodeManagMstList();

            // 深夜・早朝のレッスンの取得
            List<SchResTCodeManagMDto> detailsList0 = this.getDetailsList(model, holidayList, codeManagMstDtoList, 0);
            // 朝・午前のレッスンの取得
            List<SchResTCodeManagMDto> detailsList1 = this.getDetailsList(model, holidayList, codeManagMstDtoList, 9);
            // 午後・夕方のレッスンの取得
            List<SchResTCodeManagMDto> detailsList2 = this.getDetailsList(model, holidayList, codeManagMstDtoList, 18);
            // 夕方・夜のレッスンの取得
            List<SchResTCodeManagMDto> detailsList3 = this.getDetailsList(model, holidayList, codeManagMstDtoList, 27);

            // ユーザIDを取得
            String userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();

            // Modelにセット
            model.setLoginId(userId);
            model.setHolidayList(holidayList);
            model.setSchResTCodeManagMDtoList0(detailsList0);
            model.setSchResTCodeManagMDtoList1(detailsList1);
            model.setSchResTCodeManagMDtoList2(detailsList2);
            model.setSchResTCodeManagMDtoList3(detailsList3);

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
    public List<SchResTCodeManagMDto> getDetailsList(ReservationCancellationTeacherScheduleModel model,
            List<HolidayListDto> holidayList, List<CodeManagMstDto> codeManagMstDtoList, int idx) throws NaiException {

        Connection conn = null;

        try {
            conn = DbUtil.getConnection();

            // 初期化
            List<SchResTCodeManagMDto> detailsList = new ArrayList<SchResTCodeManagMDto>();

            ReservationCancellationTeacherScheduleLogic logic = new ReservationCancellationTeacherScheduleLogic(conn);

            for (int i = idx; i < idx + 9; i++) {
                // 初期化
                SchResTCodeManagMDto schResTCodeManagMDto = new SchResTCodeManagMDto();

                ArrayList<SchResTLesResPerTDto> schResTLesResPerTDtoList = new ArrayList<SchResTLesResPerTDto>();

                for (int j = 0; j < holidayList.size(); j++) {
                    // 講師予定予約テーブルから対象データの取得
                    SchResTLesResPerTDto retDto = logic.selectSchResTLesResPerTDto(model.getTeacherId(), holidayList
                            .get(j).getDay(), codeManagMstDtoList.get(i).getManagerCd());

                    schResTLesResPerTDtoList.add(retDto);
                }
                schResTCodeManagMDto.setManagerCd(codeManagMstDtoList.get(i).getManagerCd());
                schResTCodeManagMDto.setManagerNm(codeManagMstDtoList.get(i).getManagerNm());
                schResTCodeManagMDto.setSchResTLesResPerTDtoList(schResTLesResPerTDtoList);

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

    /**
     * レッスン日の最大値の取得<br>
     * <br>
     * レッスン日の最大値の取得を行う。<br>
     * <br>
     * @param dtoList 講師予定予約テーブル、レッスン予実テーブルDTOリスト<br>
     * @return String レッスン日の最大値<br>
     * @throws NaiException
     */
    public String getMaxLessonDt(List<SchResTLesResPerTDto> dtoList) {

        // 初期化
        String maxLessonDt = NaikaraTalkConstants.BRANK;

        // レッスン日比較
        if (dtoList != null && dtoList.size() > 0) {
            maxLessonDt = dtoList.get(0).getLessonDt();
            for (SchResTLesResPerTDto dto : dtoList) {
                if (DateUtil.dateCompare2(dto.getLessonDt(), maxLessonDt)) {
                    maxLessonDt = dto.getLessonDt();
                }
            }
        }

        // 値を返す
        return maxLessonDt;
    }
}
