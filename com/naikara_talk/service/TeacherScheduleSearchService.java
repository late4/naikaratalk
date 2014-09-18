package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
//import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.HolidayListDto;
import com.naikara_talk.dto.LocSumTimeDto;
import com.naikara_talk.dto.SchResTCodeManagMDto;
import com.naikara_talk.dto.ScheduleReservationTrnDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.TeacherScheduleLogic;
import com.naikara_talk.model.TeacherScheduleModel;
import com.naikara_talk.mstcache.CodeManagMstCache;
//import com.naikara_talk.sessiondata.SessionUser;
//import com.naikara_talk.sessiondata.SessionCodeControlMst;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
//import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>講師スケジュールページ。<br>
 * <b>クラス名称       :</b>講師スケジュールページ検索Serviceクラス。<br>
 * <b>クラス概要       :</b>講師スケジュールの情報検索ができる<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/09 TECS 新規作成
 * <b>                 :</b>2014/06/02 TECS レッスン予約に関する「応相談」対応
 */
public class TeacherScheduleSearchService implements ActionService {

    /** 更新前チェック：チェックBOX)がONのものが0つも存在しない場合 */
    public static final int ERR_CHECK_ON = 0;


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
            conn = DbUtil.getConnection();
            // 初期化
            ArrayList<SchResTCodeManagMDto> detailsList = new ArrayList<SchResTCodeManagMDto>();
            TeacherScheduleLogic teacherScheduleLogic = new TeacherScheduleLogic(conn);
            for (int i = idx; i < idx + 9; i++) {
                SchResTCodeManagMDto schResTCodeManagMDto = new SchResTCodeManagMDto();
                ArrayList<ScheduleReservationTrnDto> list = new ArrayList<ScheduleReservationTrnDto>();
                for (int j = 0; j < holidayList.size(); j++) {
                    ScheduleReservationTrnDto prmDto = new ScheduleReservationTrnDto();
                    prmDto.setTeacherId(model.getUserId());
                    prmDto.setLessonDt(holidayList.get(j).getDay());
                    prmDto.setLessonTmCd(codeManagMstDtoList.get(i).getManagerCd());
                    // 講師予定予約テーブルから対象データの取得
                    ScheduleReservationTrnDto scheduleReservationTrnDto = teacherScheduleLogic
                            .selectScheduleReservationTrnDto(prmDto);
                    list.add(scheduleReservationTrnDto);
                }
                // 汎用コード
                schResTCodeManagMDto.setManagerCd(codeManagMstDtoList.get(i).getManagerCd());
                // 汎用フィールド
                schResTCodeManagMDto.setManagerNm(codeManagMstDtoList.get(i).getManagerNm());
                // ローカルタイム
                schResTCodeManagMDto.setLocalTimeFromTo(locSumTimeDto.get(i).getLocalTimeFromTo());
                // サマータイム
                schResTCodeManagMDto.setSumTimeFromTo(locSumTimeDto.get(i).getSumTimeFromTo());
                // 講師予定予約テーブルDTOリスト
                schResTCodeManagMDto.setScheduleReservationTrnDto(list);
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
     * 検索のチェック。<br>
     * <br>
     * @param model TeacherScheduleModel
     * @return StringBuffer
     * @throws NaiException
     */
    //public StringBuffer errorCheck(TeacherScheduleModel model) throws NaiException {
    public StringBuffer errorCheck(TeacherScheduleModel nowModel, TeacherScheduleModel model) throws NaiException {

        StringBuffer msg = new StringBuffer();

        // 2014/06/02 レッスン予約に関する「応相談」対応 Del Start
/*
        // 汎用フィールド名の取得
        CodeManagMstCache cache = CodeManagMstCache.getInstance();
        // 符号取得
        LinkedHashMap<String, CodeManagMstDto> lessonTmList = cache
                .getList(NaikaraTalkConstants.CODE_CATEGORY_LESSON_TM_S);

        if (model.getMidnight_chkn() != null) {
            String[] selected = model.getMidnight_chkn()[0].substring(1, model.getMidnight_chkn()[0].length() - 1)
                    .replaceAll(NaikaraTalkConstants.HALF_SPACE, NaikaraTalkConstants.BRANK)
                    .split(String.valueOf(NaikaraTalkConstants.COMMA));

            // 発生月日変換(yyyyMMdd ⇒ M/d)
            msg.append(NaikaraStringUtil.converToM_D(selected[0]));
            msg.append(" ");
            //
            msg.append(lessonTmList.get(selected[1]).getManagerNm());
            return msg;
        }

        // ｢06：00 - 12：00｣部分のチェックボックス
        if (model.getMorning_chkn() != null) {
            String[] selected = model.getMorning_chkn()[0].substring(1, model.getMorning_chkn()[0].length() - 1)
                    .replaceAll(NaikaraTalkConstants.HALF_SPACE, NaikaraTalkConstants.BRANK)
                    .split(String.valueOf(NaikaraTalkConstants.COMMA));
            msg.append(NaikaraStringUtil.converToM_D(selected[0]));
            msg.append(" ");
            msg.append(lessonTmList.get(selected[1]).getManagerNm());
            return msg;
        }
        // ｢12：00 - 18：00｣部分のチェックボックス
        if (model.getDaytime_chkn() != null) {
            String[] selected = model.getDaytime_chkn()[0].substring(1, model.getDaytime_chkn()[0].length() - 1)
                    .replaceAll(NaikaraTalkConstants.HALF_SPACE, NaikaraTalkConstants.BRANK)
                    .split(String.valueOf(NaikaraTalkConstants.COMMA));
            msg.append(NaikaraStringUtil.converToM_D(selected[0]));
            msg.append(" ");
            msg.append(lessonTmList.get(selected[1]).getManagerNm());
            return msg;
        }
        // ｢18：00 - 24：00｣部分のチェックボックス
        if (model.getNight_chkn() != null) {
            String[] selected = model.getNight_chkn()[0].substring(1, model.getNight_chkn()[0].length() - 1)
                    .replaceAll(NaikaraTalkConstants.HALF_SPACE, NaikaraTalkConstants.BRANK)
                    .split(String.valueOf(NaikaraTalkConstants.COMMA));
            msg.append(NaikaraStringUtil.converToM_D(selected[0]));
            msg.append(" ");
            msg.append(lessonTmList.get(selected[1]).getManagerNm());
            return msg;
        }
*/
        // 2014/06/02 レッスン予約に関する「応相談」対応 Del End


        // 2014/06/02 レッスン予約に関する「応相談」対応 Add Start
        try {
            msg = this.valueChangedCheck(nowModel, model);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 2014/06/02 レッスン予約に関する「応相談」対応 Add End

        return msg;
    }

    // 2014/06/02 レッスン予約に関する「応相談」対応 Add Start

    /**
     * 一覧選択のデータが初期表示時と変更されていないかチェック。<br>
     * <br>
     * 一覧選択のデータが初期表示時より変更されている場合は更新していない可能性があるため<br>
     * エラーメッセージを返却する。<br>
     * <br>
     * @param model TeacherScheduleModel<br>
     * @return エラーメッセージ StringBuffer<br>
     * @throws Exception
     */
    private StringBuffer valueChangedCheck(TeacherScheduleModel nowModel, TeacherScheduleModel model) throws Exception {

        StringBuffer msg = new StringBuffer();    // 返却値

        String nowSelList = null;            // 予約区分(予約状況)4桁(0001:○ /0002:● /0003:－   /0004:△   /0005:▲)：画面の選択値
        ArrayList<SchResTCodeManagMDto> schResTListDto = null;

        searchfirstErr: for (int i = 0; i < 4; i++) {
            switch (i) {
                case 0:    // ｢00：00 - 06：00｣部分
                    nowSelList = nowModel.getMidnight();                             // 予約区分(予約状況)4桁：画面の選択値
                    schResTListDto = model.getSchResTCodeManagMDto0();
                    break;
                case 1:    // ｢06：00 - 12：00｣部分
                    nowSelList = nowModel.getMorning();                              // 予約区分(予約状況)4桁：画面の選択値
                    schResTListDto = model.getSchResTCodeManagMDto1();
                    break;
                case 2:    // ｢12：00 - 18：00｣部分
                    nowSelList = nowModel.getDaytime();                              // 予約区分(予約状況)4桁：画面の選択値
                    schResTListDto = model.getSchResTCodeManagMDto2();
                    break;
                case 3:    // ｢18：00 - 24：00｣部分
                    nowSelList = nowModel.getNight();                                // 予約区分(予約状況)4桁：画面の選択値
                    schResTListDto = model.getSchResTCodeManagMDto3();
                    break;
            }


            String[] nowSel = nowSelList.substring(0, nowSelList.length())
                .replaceAll(NaikaraTalkConstants.HALF_SPACE, NaikaraTalkConstants.BRANK)
                .split(String.valueOf(NaikaraTalkConstants.COMMA));        // 予約区分(予約状況)：画面の選択値

            // 9コマ×14日(2週間)単位に処理
            int skipCnt = 0;
            int nowSelCnt = 0;
            int col = 0;        // 縦：日付(2週間)
            int row = 0;        // 横：コマ数
            int amari = -1;

            // 9コマ*14日＝0～125回
            for (int j = 0; j < 126; j++) {

                if (j != 0) {
                    col = col + 1;     // 日付(2週間)
                    amari = j%14;      // 14＝2週間
                }

                if (amari == 0) {
                    row = row + 1;     // コマ数
                    col = 0;           // 日付(2週間)
                }

                SchResTCodeManagMDto schResTDto = schResTListDto.get(row);
                ArrayList<ScheduleReservationTrnDto> schTDto = schResTDto.getScheduleReservationTrnDto();
                ScheduleReservationTrnDto schrDto = schTDto.get(col);
                if (schrDto == null) {
                    continue;
                }

                if (StringUtils.equals(schrDto.getDisabledFlg(), NaikaraTalkConstants.TRUE)) {
                    // 画面で使用不可の場合
                    // 初期表示時で予約済、応相談予約済(仮予約)のデータは処理対象外 ※応相談予約済(予約確定)の場合は0002：予約済へ変換している
                    skipCnt = skipCnt + 1;
                    continue;
                }

                nowSelCnt = j-skipCnt;    // Lockしている項目の選択値は画面から引き渡されないので減算

                // 表示時と現在の値の入れ替え処理(エラー時に現在の値を保持させて表示させるため)。
                // err後再度初期表示時と同じ値を設定する可能性ありのため、この位置にて設定
                schrDto.setDefaultSelValue(nowSel[nowSelCnt]);
                // 格納先の判定
                switch (i) {
                case 0:    // ｢00：00 - 06：00｣部分
                    model.setSchResTCodeManagMDto0(schResTListDto);
                    break;
                case 1:    // ｢06：00 - 12：00｣部分
                    model.setSchResTCodeManagMDto1(schResTListDto);
                    break;
                case 2:    // ｢12：00 - 18：00｣部分
                    model.setSchResTCodeManagMDto2(schResTListDto);
                    break;
                case 3:    // ｢18：00 - 24：00｣部分
                    model.setSchResTCodeManagMDto3(schResTListDto);
                    break;
                }

                // 過去と現在で値変更がないかチェック
                if (!StringUtils.equals(schrDto.getReservationState(), nowSel[nowSelCnt])) {
                    // 表示時と現在の選択値が違う場合はエラーとする
                    CodeManagMstCache cache = CodeManagMstCache.getInstance();
                    String LessonTmNm = cache.decode(NaikaraTalkConstants.CODE_CATEGORY_LESSON_TM_S, schrDto.getLessonTmCd());
                    msg.append(NaikaraStringUtil.converToM_D(schrDto.getLessonDt()));
                    msg.append(" ");
                    msg.append(LessonTmNm);
                    break searchfirstErr;
                }

            }    // for (int j = 0; j < nowSel.length-1; j++) {

        }    // for (int i = 0; i < 4; i++) {

        // 正常(値変更なし。更新対象なし)
        return msg;
    }

    // 2014/06/02 レッスン予約に関する「応相談」対応 Add End


}
