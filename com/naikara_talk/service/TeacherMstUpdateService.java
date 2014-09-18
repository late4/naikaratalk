package com.naikara_talk.service;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.CourseMstDto;
import com.naikara_talk.dto.ScheduleReservationTrnDto;
import com.naikara_talk.dto.TeacherCourseDto;
import com.naikara_talk.dto.TeacherCourseMstDto;
import com.naikara_talk.dto.TeacherMstDto;
import com.naikara_talk.dto.TeacherRateMstDto;
import com.naikara_talk.dto.TimeManagMstDto;
import com.naikara_talk.dto.UserMstDto;
import com.naikara_talk.dto.UserMstTeacherMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.TeacherMstLogic;
import com.naikara_talk.logic.TeacherRateMstLogic;
import com.naikara_talk.logic.TimeZoneControlMstLogic;
import com.naikara_talk.model.TeacherMstModel;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>講師マスタメンテナンス(単票)<br>
 * <b>クラス概要       :</b>講師マスタメンテナンス(単票)登録更新Service<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/07 TECS 新規作成
 */
public class TeacherMstUpdateService implements ActionService {

    /** ドロップダウンリストの必須チェック　(国籍) */
    public static final int ERR_NOT_SELECT_NATIONALIY_SEL = 1;

    /** ドロップダウンリストの必須チェック　(母国語) */
    public static final int ERR_NOT_SELECT_TONGUE_SEL = 2;

    /** ドロップダウンリストの必須チェック　(滞在国) */
    public static final int ERR_NOT_SELECT_RESIDENCE_SEL = 3;

    /** ドロップダウンリストの必須チェック　(時差地域NO) */
    public static final int ERR_NOT_SELECT_REGIONNO_SEL = 4;

    /** 生年月日：月チェックエラー */
    public static final int ERR_NG_MONTH = 5;

    /** 生年月日：日チェックエラー */
    public static final int ERR_NG_DATE = 6;

    /** 未来日付チェック */
    public static final int ERR_FUTURE_DATE = 7;

    /** チェック：｢契約日｣　＞　｢契約期間：開始日｣　の場合 */
    public static final int ERR_INTEGRITY_DT = 8;

    /** チェック：｢契約期間：開始日｣　＞　｢契約期間：終了日｣ */
    public static final int ERR_INTEGRITY_DATE = 9;

    /** チェック：｢提供可能コース｣が複数設定されている場合は、重複チェック */
    public static final int ERR_COURSE_REPEAT = 10;

    /** チェック： 講師画像：削除　のチェック */
    public static final int ERR_IMGPHOTO = 11;

    /** チェック： 画像ファイルサイズ　のチェック */
    public static final int ERR_IMGPHOTO_FIL = 12;

    /** チェック： 時差管理マスタへのデータの存在チェック */
    public static final int ERR_NOT_TIME_ZONE = 13;

    /** チェック： (隠し項目：利用者マスタの) 利用期間と契約期間の範囲チェック */
    public static final int ERR_USE_DT = 14;

    /** チェック： 講師支払比率の設定画面にて、1件でもデータを保存してあるかどうかのチェック */
    public static final int ERR_ZERO_COUNT = 15;

    /** チェック： 契約期間と講師の歩合(支払比率)の範囲チェック */
    public static final int ERR_CONTRACT_DT = 16;

    /** チェック：コースマスタ データなし */
    public static final int ERR_COURSE_NOT_EXIST = 17;

    /** チェック：データの存在チェック   データが存在しない場合 */
    public static final int ERR_USER_NOT_EXIST = 18;

    /** チェック：データの存在チェック   処理判定：”新規” 且つ 講師マスタ．利用者ID ≠ NULL の場合 */
    public static final int ERR_TEACHER_EXIST = 19;

    /** チェック：データの存在チェック   処理判定：”修正” 且つ 講師マスタ．利用者IDが NULL の場合 */
    public static final int ERR_TEACHER_NOT_EXIST = 20;

    /** チェック：データの存在チェック   処理判定：”修正” 且つ 講師の契約開始日～終了日外に講師スケジュールにデータが存在する 場合 */
    public static final int ERR_SCHEDULE_RESERVATION_TRN_YES = 21;

    /**
     * 登録、更新のチェック。<br>
     * <br>
     * 登録、更新のチェック。<br>
     * <br>
     * @param TeacherMstModel<br>
     * @return int チェック結果<br>
     * @exception Exception
     */
    public int errorCheck(TeacherMstModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // 関連チェック
            // 国籍のコード値＝”0000”の場合
            if (StringUtils.equals(NaikaraTalkConstants.CHOICE_ALL_ZERO, model.getNationalityCd())) {
                return ERR_NOT_SELECT_NATIONALIY_SEL;
            }

            // 母国語のコード値＝”0000”の場合
            if (StringUtils.equals(NaikaraTalkConstants.CHOICE_ALL_ZERO, model.getNativeLangCd())) {
                return ERR_NOT_SELECT_TONGUE_SEL;
            }

            // 滞在国のコード値＝”0000”の場合
            if (StringUtils.equals(NaikaraTalkConstants.CHOICE_ALL_ZERO, model.getCountryCd())) {
                return ERR_NOT_SELECT_RESIDENCE_SEL;
            }

            // 時差地域NOのコード値＝”0000”の場合
            if (StringUtils.equals(NaikaraTalkConstants.CHOICE_ALL_ZERO, model.getAreaNoCd())) {
                return ERR_NOT_SELECT_REGIONNO_SEL;
            }

            // 生年月日String(yyyyMMdd)
            String birthStr = NaikaraStringUtil.converToYYYYMMDD(NaikaraStringUtil.unionString2(
                    NaikaraStringUtil.unionString2(model.getBirthYyyy(), model.getBirthMm()), model.getBirthDd()));

            // 生年月日Date(yyyyMMdd)
            Date birthDate = new SimpleDateFormat(DateUtil.DATE_FORMAT_yyyyMMdd).parse(birthStr);

            // 生年月日Calendar(yyyyMMdd)
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(birthDate);

            int year = Integer.parseInt(birthStr.substring(0, 4));
            int month = Integer.parseInt(birthStr.substring(4, 6));
            int date = Integer.parseInt(birthStr.substring(6, 8));

            // 生年月日チェック
            if (year != calendar.get(Calendar.YEAR) || month != calendar.get(Calendar.MONTH) + 1
                    || date != calendar.get(Calendar.DAY_OF_MONTH)) {

                if (month > 12 || month < 1) {
                    return ERR_NG_MONTH;
                }

                int temp = Integer.MIN_VALUE;

                boolean flg = (0 == year % 4 && (year % 100 != 0 || year % 400 == 0));

                switch (month) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    temp = 31;
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    temp = 30;
                    break;
                case 2:
                    if (flg) {
                        temp = 29;
                    } else {
                        temp = 28;
                    }
                    break;
                }
                if (date > temp || date < 1) {
                    return ERR_NG_DATE;
                }
            }

            // 生年月日　＞　サーバーのシステム日付
            if (DateUtil.dateCompare2(NaikaraStringUtil.converToYYYYMMDD(birthStr), DateUtil.getSysDate())) {
                return ERR_FUTURE_DATE;
            }

            // ｢契約日｣　＞　｢契約期間：開始日｣　の場合
            if (DateUtil.dateCompare2(NaikaraStringUtil.converToYYYYMMDD(model.getContractDt()),
                    NaikaraStringUtil.converToYYYYMMDD(model.getContractStartDt()))) {
                return ERR_INTEGRITY_DT;
            }

            // ｢契約期間：開始日｣　＞　｢契約期間：終了日｣　の場合
            if (DateUtil.dateCompare2(NaikaraStringUtil.converToYYYYMMDD(model.getContractStartDt()),
                    NaikaraStringUtil.converToYYYYMMDD(model.getContractEndDt()))) {
                return ERR_INTEGRITY_DATE;
            }

            // ｢提供可能コース｣のリスト内に重複するコースコードが存在する場合
            for (int i = 0; i < model.getTeacherCourseDtoList().size(); i++) {
                if (StringUtils.isEmpty(model.getTeacherCourseDtoList().get(i).getCourseCd())
                        || StringUtils.equals(NaikaraTalkConstants.TRUE, model.getTeacherCourseDtoList().get(i)
                                .getCourse_chkn())) {
                    continue;
                }
                for (int j = 0; j < model.getTeacherCourseDtoList().size(); j++) {
                    if (StringUtils.isEmpty(model.getTeacherCourseDtoList().get(j).getCourseCd())
                            || StringUtils.equals(NaikaraTalkConstants.TRUE, model.getTeacherCourseDtoList().get(j)
                                    .getCourse_chkn())) {
                        continue;
                    }
                    if (i == j) {
                        continue;
                    }
                    if (StringUtils.equals(model.getTeacherCourseDtoList().get(i).getCourseCd(), model
                            .getTeacherCourseDtoList().get(j).getCourseCd())) {
                        model.setCheckMessage("提供可能コース(" + (i + 1) + "個目と" + (j + 1) + "個目)");
                        return ERR_COURSE_REPEAT;
                    }
                }
            }

            // 講師画像ファイル名==なし && 講師画像：削除==true
            if (StringUtils.equals(NaikaraTalkConstants.TRUE, model.getImgPhotoChkn())
                    && StringUtils.isEmpty(model.getTeacherFileName())) {
                return ERR_IMGPHOTO;
            }

            // ファイルサイズが５００．０ＫＢ以上の場合
            if (model.getImgPhoto() != null) {
                FileInputStream fis = new FileInputStream(model.getImgPhoto());
                if (fis.available() > 500 * 1024) {
                    return ERR_IMGPHOTO_FIL;
                }
            }

            // 時差管理マスタへのデータの存在チェック
            TimeZoneControlMstLogic timeZoneControlMstLogic = new TimeZoneControlMstLogic(conn);
            TimeManagMstDto dto = new TimeManagMstDto();
            dto.setCountryCd(model.getCountryCd());
            dto.setAreaNoCd(model.getAreaNoCd());
            // データが存在しない場合
            if (NaikaraTalkConstants.RETURN_CD_DATA_NO == timeZoneControlMstLogic.getExist(dto)) {
                return ERR_NOT_TIME_ZONE;
            }

            // 利用開始日 > 契約期間：開始日 利用終了日 < 契約期間：終了日
            if (DateUtil.dateCompare2(NaikaraStringUtil.converToYYYYMMDD(model.getUseStartDt()),
                    NaikaraStringUtil.converToYYYYMMDD(model.getContractStartDt()))
                    || !DateUtil.dateCompare3(NaikaraStringUtil.converToYYYYMMDD(model.getUseEndDt()),
                            NaikaraStringUtil.converToYYYYMMDD(model.getContractEndDt()))) {
                return ERR_USE_DT;
            }

            List<TeacherRateMstDto> tempTeacherRateMstDtoList = new ArrayList<TeacherRateMstDto>();
            tempTeacherRateMstDtoList.addAll(model.getTeacherRateMstDtoList());
            // リストソート
            Collections.sort(tempTeacherRateMstDtoList, new ComparatorTeacherRateMstDtos());
            if (0 == tempTeacherRateMstDtoList.size()) {
                return ERR_ZERO_COUNT;
            } else {
                if (!(StringUtils.equals(NaikaraStringUtil.converToYYYY_MM_DD(model.getContractStartDt()),
                        NaikaraStringUtil.converToYYYY_MM_DD(tempTeacherRateMstDtoList.get(0).getStartDt())) && StringUtils
                        .equals(NaikaraStringUtil.converToYYYY_MM_DD(model.getContractEndDt()),
                                NaikaraStringUtil.converToYYYY_MM_DD(tempTeacherRateMstDtoList.get(
                                        tempTeacherRateMstDtoList.size() - 1).getEndDt())))) {
                    return ERR_CONTRACT_DT;
                }
            }

            // 契約期間：開始日 > 講師支払比率の設定画面で設定した｢適用開始日｣
            // 契約期間：終了日 < 講師支払比率の設定画面で設定した｢適用開始日｣
            // 契約期間：開始日 > 講師支払比率の設定画面で設定した｢適用終了日｣
            // 契約期間：終了日 < 講師支払比率の設定画面で設定した｢適用終了日｣
            for (TeacherRateMstDto teacherRateMstDto : model.getTeacherRateMstDtoList()) {
                if (DateUtil.dateCompare2(NaikaraStringUtil.converToYYYYMMDD(model.getContractStartDt()),
                        NaikaraStringUtil.converToYYYYMMDD(teacherRateMstDto.getStartDt()))
                        || DateUtil.dateCompare2(NaikaraStringUtil.converToYYYYMMDD(teacherRateMstDto.getStartDt()),
                                NaikaraStringUtil.converToYYYYMMDD(model.getContractEndDt()))
                        || DateUtil.dateCompare2(NaikaraStringUtil.converToYYYYMMDD(model.getContractStartDt()),
                                NaikaraStringUtil.converToYYYYMMDD(teacherRateMstDto.getEndDt()))
                        || DateUtil.dateCompare2(NaikaraStringUtil.converToYYYYMMDD(teacherRateMstDto.getEndDt()),
                                NaikaraStringUtil.converToYYYYMMDD(model.getContractEndDt()))) {
                    return ERR_CONTRACT_DT;
                }
            }

            // コードが存在しない場合
            for (TeacherCourseDto teacherCourseDto : model.getTeacherCourseDtoList()) {
                if (!StringUtils.isEmpty(teacherCourseDto.getCourseCd())
                        && !StringUtils.equals(NaikaraTalkConstants.TRUE, teacherCourseDto.getCourse_chkn())) {
                    TeacherMstLogic teacherMstLogic = new TeacherMstLogic(conn);
                    CourseMstDto courseMstDto = new CourseMstDto();
                    courseMstDto.setCourseCd(teacherCourseDto.getCourseCd());
                    courseMstDto.setUseStrDt(model.getContractStartDt());
                    courseMstDto.setUseEndDt(model.getContractEndDt());
                    if (NaikaraTalkConstants.RETURN_CD_DATA_NO == teacherMstLogic.getExist(courseMstDto)) {
                        return ERR_COURSE_NOT_EXIST;
                    }
                }
            }

            // データの存在チェック
            TeacherMstLogic teacherMstLogic = new TeacherMstLogic(conn);
            UserMstTeacherMstDto userMstTeacherMstDto = new UserMstTeacherMstDto();
            userMstTeacherMstDto.setUserId(model.getUserId());
            // データが存在しない場合
            if (NaikaraTalkConstants.RETURN_CD_DATA_NO == teacherMstLogic.getExist(userMstTeacherMstDto)) {
                return ERR_USER_NOT_EXIST;
            } else {
                userMstTeacherMstDto = teacherMstLogic.select(userMstTeacherMstDto);
                // 処理判定：”新規” 且つ 講師マスタ．利用者ID ≠ NULL の場合
                if (StringUtils.equals(NaikaraTalkConstants.PROCESSKBN_INS, model.getProcessDifference())
                        && (!StringUtils.isEmpty(userMstTeacherMstDto.getUserIdT()))) {
                    return ERR_TEACHER_EXIST;
                }
                // 処理判定：”修正” 且つ 講師マスタ．利用者IDが NULL の場合
                if (StringUtils.equals(NaikaraTalkConstants.PROCESSKBN_UPD, model.getProcessDifference())
                        && StringUtils.isEmpty(userMstTeacherMstDto.getUserIdT())) {
                    return ERR_TEACHER_NOT_EXIST;
                }

                // 2013/12/21
                // 処理判定：”修正” 且つ 講師の契約開始日～終了日外に講師スケジュールにデータが存在する 場合
                if (StringUtils.equals(NaikaraTalkConstants.PROCESSKBN_UPD, model.getProcessDifference())) {
                    ScheduleReservationTrnDto prmDto = new ScheduleReservationTrnDto();
                    prmDto.setTeacherId(model.getUserId());
                    prmDto.setLessonDt(NaikaraStringUtil.converToYYYYMMDD(model.getContractStartDt()));
                    prmDto.setLessonDtTo(NaikaraStringUtil.converToYYYYMMDD(model.getContractEndDt()));
                    int cnt = this.teacherSearch(prmDto);
                    if (cnt > 0) {
                        return ERR_SCHEDULE_RESERVATION_TRN_YES;
                    }
                }

            }

            return 0;

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
     * 講師予定予約テーブル検索処理。<br>
     * <br>
     * @param prmDto ScheduleReservationTrnDto
     * @return int
     * @throws NaiException
     */
    public int teacherSearch(ScheduleReservationTrnDto prmDto) throws NaiException {
        Connection conn = null;

        try {

            conn = DbUtil.getConnection();

            TeacherMstLogic logic = new TeacherMstLogic(conn);

            // 講師予定予約テーブルから対象データのカウント取得
            return logic.selectScheduleReservationTrnMstCnt(prmDto);

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
     * 登録処理。<br>
     * <br>
     * 登録処理。<br>
     * <br>
     * @param TeacherMstModel 画面のパラメータ<br>
     * @return int<br>
     * @exception NaiException
     */
    public int insert(TeacherMstModel model) throws NaiException {
        int cnt = NaikaraTalkConstants.RETURN_CD_ERR_INS;

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // 利用者マスタへ更新処理を行う　
            cnt = this.updateUser(model, conn);
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

            // 講師マスタへ追加処理を行う　
            cnt = this.insertTeacherMst(model, conn);
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

            // 講師別支払比率マスタへ追加処理を行う　
            for (TeacherRateMstDto teacherRateMstDto : model.getTeacherRateMstDtoList()) {
                cnt = this.insertTeacherRateMst(teacherRateMstDto, conn);
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

            // 講師別コースマスタへ追加処理を行う　
            for (TeacherCourseDto teacherCourseDto : model.getTeacherCourseDtoList()) {

                if (!StringUtils.isEmpty(teacherCourseDto.getCourseCd())
                		&& !StringUtils.equals(NaikaraTalkConstants.TRUE, teacherCourseDto.getCourse_chkn())) {

                	TeacherCourseMstDto teacherCourseMstDto = new TeacherCourseMstDto();
                    teacherCourseMstDto.setUserId(model.getUserId());
                    teacherCourseMstDto.setCourseCd(teacherCourseDto.getCourseCd());
                    cnt = this.insertTeacherCourseMst(teacherCourseMstDto, conn);
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
            }
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
     * 更新処理。<br>
     * <br>
     * 更新処理。<br>
     * <br>
     * @param TeacherMstModel 画面のパラメータ<br>
     * @return int<br>
     * @exception NaiException
     */
    public int update(TeacherMstModel model) throws NaiException {
        int cnt = NaikaraTalkConstants.RETURN_CD_ERR_UPD;
        int delcnt = NaikaraTalkConstants.RETURN_CD_ERR_DEL;

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // 利用者マスタへ更新処理を行う　
            cnt = this.updateUser(model, conn);
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

            // 講師マスタへ更新処理を行う
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

            // 検索用DTO作成
            TeacherRateMstDto delTeacherRateMstDto = new TeacherRateMstDto();
            delTeacherRateMstDto.setUserId(model.getUserIdT());
            // 削除用リスト取得処理
            TeacherRateMstLogic teacherRateMstLogic = new TeacherRateMstLogic(conn);
            List<TeacherRateMstDto> teacherRateMstDtoList = teacherRateMstLogic.select(delTeacherRateMstDto);
            // データがない場合リストをクリアする
            if (NaikaraTalkConstants.RETURN_CD_DATA_NO == teacherRateMstDtoList.get(0).getReturnCode()) {
                teacherRateMstDtoList.clear();
            }
            // ループ削除用リスト
            for (int i = 0; i < teacherRateMstDtoList.size(); i++) {
                // 画面リストをループする
                for (int j = 0; j < model.getTeacherRateMstDtoList().size(); j++) {
                    // 商品コードが同じの場合
                    if (StringUtils.equals(model.getTeacherRateMstDtoList().get(j).getStartDt(), teacherRateMstDtoList
                            .get(i).getStartDt())) {
                        // 該当データを削除する
                        teacherRateMstDtoList.remove(i);
                        i = -1;
                        break;
                    }
                }
            }
            // ループ削除用リスト
            for (TeacherRateMstDto teacherRateMstDto : teacherRateMstDtoList) {
                // 講師別支払比率マスタへ削除処理を行う　
                delcnt = this.deleteTeacherRateMst(teacherRateMstDto, conn);
                if (NaikaraTalkConstants.RETURN_CD_ERR_DEL == delcnt) {
                    cnt = delcnt;
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

            // 講師別支払比率マスタへ追加・更新処理を行う　
            for (TeacherRateMstDto teacherRateMstDto : model.getTeacherRateMstDtoList()) {
                TeacherMstLogic teacherMstLogic = new TeacherMstLogic(conn);
                // 講師別支払比率マスタへ追加処理を行う　
                if (NaikaraTalkConstants.RETURN_CD_DATA_NO == teacherMstLogic.getExistTeacherRateMst(teacherRateMstDto)) {
                    cnt = this.insertTeacherRateMst(teacherRateMstDto, conn);
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
                    // 講師別支払比率マスタへ更新処理を行う　
                } else {
                    cnt = this.updateTeacherRateMst(teacherRateMstDto, conn);
                    if (NaikaraTalkConstants.RETURN_CD_ERR_UPD == cnt
                            || NaikaraTalkConstants.RETURN_CD_ERR_NO_UPD == cnt) {
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
            }

            // 検索用DTO作成
            TeacherCourseDto dbTeacherCourseDtoList = new TeacherCourseDto();
            dbTeacherCourseDtoList.setUserId(model.getUserIdT());
            // 削除用リスト取得処理
            TeacherMstLogic teacherMstLogic = new TeacherMstLogic(conn);
            List<TeacherCourseDto> teacherCourseDtoList = teacherMstLogic.getTeacherCourseDtos(dbTeacherCourseDtoList);
            // データがない場合リストをクリアする
            if (NaikaraTalkConstants.RETURN_CD_DATA_NO == teacherCourseDtoList.get(0).getReturnCode()) {
                teacherCourseDtoList.clear();
            }
            // ループ削除用リスト
            for (int i = 0; i < teacherCourseDtoList.size(); i++) {
                // 画面リストをループする
                for (int j = 0; j < model.getTeacherCourseDtoList().size(); j++) {
                    // 画面の該当データがブランクまたは削除チェックボックスがチェックされた場合
                    if (StringUtils.isEmpty(model.getTeacherCourseDtoList().get(j).getCourseCd())
                            || StringUtils.equals(NaikaraTalkConstants.TRUE, model.getTeacherCourseDtoList().get(j)
                                    .getCourse_chkn())) {
                        continue;
                    }
                    // コースコードが同じの場合
                    if (StringUtils.equals(model.getTeacherCourseDtoList().get(j).getCourseCd(), teacherCourseDtoList
                            .get(i).getCourseCd())) {
                        // 該当データを削除する
                        teacherCourseDtoList.remove(i);
                        i = -1;
                        break;
                    }
                }
            }
            // ループ削除用リスト
            for (TeacherCourseDto teacherCourseDto : teacherCourseDtoList) {
                // 講師別コースマスタへ削除処理を行う　
                TeacherCourseMstDto delTeacherCourseDto = new TeacherCourseMstDto();
                delTeacherCourseDto.setUserId(teacherCourseDto.getUserId());
                delTeacherCourseDto.setCourseCd(teacherCourseDto.getCourseCd());
                delcnt = this.deleteTeacherCourseMst(delTeacherCourseDto, conn);
                if (NaikaraTalkConstants.RETURN_CD_ERR_DEL == delcnt) {
                    cnt = delcnt;
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

            // 講師別コースマスタへ追加・更新処理を行う　
            for (TeacherCourseDto teacherCourseDto : model.getTeacherCourseDtoList()) {
                if (!StringUtils.isEmpty(teacherCourseDto.getCourseCd())
                        && !StringUtils.equals(NaikaraTalkConstants.TRUE, teacherCourseDto.getCourse_chkn())) {
                    TeacherCourseMstDto teacherCourseMstDto = new TeacherCourseMstDto();
                    teacherCourseMstDto.setUserId(model.getUserId());
                    teacherCourseMstDto.setCourseCd(teacherCourseDto.getCourseCd());
                    // 講師別コースマスタへ追加処理を行う　
                    if (NaikaraTalkConstants.RETURN_CD_DATA_NO == teacherMstLogic
                            .getExistTeacherCourseDto(teacherCourseMstDto)) {
                        cnt = this.insertTeacherCourseMst(teacherCourseMstDto, conn);
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
                        // 講師別コースマスタへ更新処理を行う　
                    } else {
                        cnt = this.updateTeacherCourseMst(teacherCourseMstDto, conn);
                        if (NaikaraTalkConstants.RETURN_CD_ERR_UPD == cnt
                                || NaikaraTalkConstants.RETURN_CD_ERR_NO_UPD == cnt) {
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
     * コード管理マスタからデータ取得処理。<br>
     * <br>
     * コード管理マスタからデータ取得処理。<br>
     * <br>
     * @param category 汎用コード<br>
     * @return LinkedHashMap<String, String><br>
     * @exception NaiException
     */
    public LinkedHashMap<String, String> selectCodeMst(String category) throws NaiException {

        Connection conn = null;

        try {
            conn = DbUtil.getConnection();
            TeacherMstLogic teacherMstLogic = new TeacherMstLogic(conn);
            // コード管理マスタ検索
            return teacherMstLogic.selectCodeMst(category);
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
     * 利用者マスタ更新処理<br>
     * <br>
     * 利用者マスタ更新処理<br>
     * <br>
     * @param TeacherMstModel Connection <br>
     * @return int <br>
     * @exception NaiException
     */
    private int updateUser(TeacherMstModel model, Connection conn) throws NaiException {

        TeacherMstLogic teacherMstLogic = new TeacherMstLogic(conn);
        // DTOの初期化
        UserMstDto prmDto = new UserMstDto();

        // Model値をDTOにセット

        prmDto = this.modelToUserMstDto(model, prmDto);

        // 利用者マスタ取得
        UserMstDto resDto = teacherMstLogic.searchUserMst(prmDto);
        if (resDto != null) {
            resDto = this.modelToUserMstDto(model, resDto);
            // 利用者マスタ更新
            return teacherMstLogic.updateUserMst(resDto);
        }

        return NaikaraTalkConstants.RETURN_CD_ERR_UPD;
    }

    /**
     * 講師マスタ登録処理<br>
     * <br>
     * 講師マスタ登録処理<br>
     * <br>
     * @param TeacherMstModel Connection  <br>
     * @return int <br>
     * @exception NaiException
     */
    private int insertTeacherMst(TeacherMstModel model, Connection conn) throws NaiException {

        TeacherMstLogic teacherMstLogic = new TeacherMstLogic(conn);
        // DTOの初期化
        TeacherMstDto prmDto = new TeacherMstDto();
        prmDto = this.modelToTeacherMstDto(model, prmDto);
        // 講師マスタ登録
        return teacherMstLogic.insertTeacherMst(prmDto);
    }

    /**
     * 講師マスタ更新処理<br>
     * <br>
     * 講師マスタ更新処理<br>
     * <br>
     * @param TeacherMstModel Connection  <br>
     * @return int <br>
     * @exception NaiException
     */
    private int updateTeacherMst(TeacherMstModel model, Connection conn) throws NaiException {

        TeacherMstLogic teacherMstLogic = new TeacherMstLogic(conn);
        // DTOの初期化
        TeacherMstDto prmDto = new TeacherMstDto();
        prmDto = this.modelToTeacherMstDto(model, prmDto);
        // 講師マスタ更新
        return teacherMstLogic.updateTeacherMst(prmDto);
    }

    /**
     * 講師支払比率の登録処理<br>
     * <br>
     * 講師支払比率の登録処理<br>
     * <br>
     * @param TeacherRateMstDto Connection  <br>
     * @return int <br>
     * @exception NaiException
     */
    private int insertTeacherRateMst(TeacherRateMstDto dto, Connection conn) throws NaiException {

        TeacherMstLogic teacherMstLogic = new TeacherMstLogic(conn);
        // 講師支払比率登録
        return teacherMstLogic.insertTeacherRateMst(dto);
    }

    /**
     * 講師支払比率の更新処理<br>
     * <br>
     * 講師支払比率の更新処理<br>
     * <br>
     * @param TeacherRateMstDto Connection <br>
     * @return int <br>
     * @exception NaiException
     */
    private int updateTeacherRateMst(TeacherRateMstDto dto, Connection conn) throws NaiException {

        TeacherMstLogic teacherMstLogic = new TeacherMstLogic(conn);
        // 講師支払比率更新
        return teacherMstLogic.updateTeacherRateMst(dto);
    }

    /**
     * 講師支払比率の削除処理<br>
     * <br>
     * 講師支払比率の削除処理<br>
     * <br>
     * @param TeacherRateMstDto Connection <br>
     * @return int <br>
     * @exception NaiException
     */
    private int deleteTeacherRateMst(TeacherRateMstDto dto, Connection conn) throws NaiException {

        TeacherMstLogic teacherMstLogic = new TeacherMstLogic(conn);
        // 講師支払比率削除
        return teacherMstLogic.deleteTeacherRateMst(dto);
    }

    /**
     * 講師別コースマスタの登録処理<br>
     * <br>
     * 講師別コースマスタの登録処理<br>
     * <br>
     * @param TeacherCourseMstDto Connection <br>
     * @return int <br>
     * @exception NaiException
     */
    private int insertTeacherCourseMst(TeacherCourseMstDto dto, Connection conn) throws NaiException {

        TeacherMstLogic teacherMstLogic = new TeacherMstLogic(conn);
        return teacherMstLogic.insertTeacherCourseMst(dto);
    }

    /**
     * 講師別コースマスタの更新処理<br>
     * <br>
     * 講師別コースマスタの更新処理<br>
     * <br>
     * @param TeacherCourseMstDto Connection <br>
     * @return int <br>
     * @exception NaiException
     */
    private int updateTeacherCourseMst(TeacherCourseMstDto dto, Connection conn) throws NaiException {

        TeacherMstLogic teacherMstLogic = new TeacherMstLogic(conn);
        return teacherMstLogic.updateTeacherCourseMst(dto);
    }

    /**
     * 講師別コースマスタの削除処理<br>
     * <br>
     * 講師別コースマスタの削除処理<br>
     * <br>
     * @param TeacherCourseMstDto Connection <br>
     * @return int <br>
     * @exception NaiException
     */
    public int deleteTeacherCourseMst(TeacherCourseMstDto dto, Connection conn) throws NaiException {

        TeacherMstLogic teacherMstLogic = new TeacherMstLogic(conn);
        // 講師別コースマスタ削除
        return teacherMstLogic.deleteTeacherCourseMst(dto);
    }

    /**
     * Model値をDTOにセット。<br>
     * <br>
     * Model値をDTOにセット。<br>
     * <br>
     * @param TeacherMstModel TeacherMstDto<br>
     * @return TeacherMstDto<br>
     * @exception なし
     */
    private TeacherMstDto modelToTeacherMstDto(TeacherMstModel model, TeacherMstDto prmDto) {

        // 利用者ID
        prmDto.setUserId(model.getUserId());
        // ニックネーム
        prmDto.setNickAnm(model.getNickAnm());
        // 国籍コード
        prmDto.setNationalityCd(model.getNationalityCd());
        // 母国語コード
        prmDto.setNativeLangCd(model.getNativeLangCd());
        // 滞在国コード
        prmDto.setCountryCd(model.getCountryCd());
        // 時差地域NOコード
        prmDto.setAreaNoCd(model.getAreaNoCd());
        // 契約日
        prmDto.setContractDt(NaikaraStringUtil.converToYYYYMMDD(model.getContractDt()));
        // 契約開始日
        prmDto.setContractStartDt(NaikaraStringUtil.converToYYYYMMDD(model.getContractStartDt()));
        // 契約終了日
        prmDto.setContractEndDt(NaikaraStringUtil.converToYYYYMMDD(model.getContractEndDt()));
        // 国内銀行向け送金の場合：銀行名
        prmDto.setBankJpnBankNm(model.getBankJpnBankNm());
        // 国内銀行向け送金の場合：支店名
        prmDto.setBankJpnBranchNm(model.getBankJpnBranchNm());
        // 国内銀行向け送金の場合：預金種別
        prmDto.setBankJpnTypeOfAccount(model.getBankJpnTypeOfAccount());
        // 国内銀行向け送金の場合：口座名義人（フリガナ）
        prmDto.setBankJpnAccountHoldersKnm(model.getBankJpnAccountHoldersKnm());
        // 国内銀行向け送金の場合：口座名義人
        prmDto.setBankJpnAccountHoldersNm(model.getBankJpnAccountHoldersNm());
        // 国内銀行向け送金の場合：口座番号
        prmDto.setBankJpnAccountNumber(model.getBankJpnAccountNumber());
        // 国内銀行向け送金の場合：追加情報
        prmDto.setBankJpnAdditionalInfo(model.getBankJpnAdditionalInfo());
        // 国内ゆうちょ銀行向け送金の場合:店番
        prmDto.setJpnPbankBranchNm(model.getJpnPbankBranchNm());
        // 国内ゆうちょ銀行向け送金の場合:預金種別
        prmDto.setJpnPbankTypeOfAccount(model.getJpnPbankTypeOfAccount());
        // 国内ゆうちょ銀行向け送金の場合:口座名義人（フリガナ）
        prmDto.setJpnPbankAccountHoldersKnm(model.getJpnPbankAccountHoldersKnm());
        // 国内ゆうちょ銀行向け送金の場合:口座名義人
        prmDto.setJpnPbankAccountHoldersNm(model.getJpnPbankAccountHoldersNm());
        // 国内ゆうちょ銀行向け送金の場合:口座番号
        prmDto.setJpnPbankAccountNumber(model.getJpnPbankAccountNumber());
        // 国内ゆうちょ銀行向け送金の場合:追加情報
        prmDto.setJpnPbankAdditionalInfo(model.getJpnPbankAdditionalInfo());
        // 海外送金の場合:口座名義人
        prmDto.setOverseaAccountHNm(model.getOverseaAccountHNm());
        // 海外送金の場合:口座名義人登録住所
        prmDto.setOverseaAccountHRAddress1(model.getOverseaAccountHRAddress1());
        // 海外送金の場合:口座名義人登録住所（上記住所欄が一杯のとき）
        prmDto.setOverseaAccountHRAddress2(model.getOverseaAccountHRAddress2());
        // 海外送金の場合:口座番号/IBAN（ヨーロッパ）
        prmDto.setOverseaAccountNumberIban(model.getOverseaAccountNumberIban());
        // 海外送金の場合:ABAナンバー/SWIFTコード/BIC Code　等
        prmDto.setOverseaAbanoSwiftcdBiccd(model.getOverseaAbanoSwiftcdBiccd());
        // 海外送金の場合:等
        prmDto.setOverseaEtc(model.getOverseaEtc());
        // 海外送金の場合:銀行名
        prmDto.setOverseaBankNm(model.getOverseaBankNm());
        // 海外送金の場合:支店名
        prmDto.setOverseaBranchNm(model.getOverseaBranchNm());
        // 海外送金の場合:支店住所
        prmDto.setOverseaBranchAddress1(model.getOverseaBranchAddress1());
        // 海外送金の場合:支店住所（上記住所欄が一杯のとき）
        prmDto.setOverseaBranchAddress2(model.getOverseaBranchAddress2());
        // 海外送金の場合:店が所在する国名
        prmDto.setOverseaCountryBranchExists(model.getOverseaCountryBranchExists());
        // 海外送金の場合:追加情報
        prmDto.setOverseaAdditionalInfo(model.getOverseaAdditionalInfo());
        // 外国送金関係銀行手数料区分
        prmDto.setOtherRemittanceFeeKbn(model.getOtherRemittanceFeeKbn());
        // 海外ペイパル送金の場合:PayPalアドレス
        prmDto.setOverseaPlPaypalAddress(model.getOverseaPlPaypalAddress());
        // 海外ペイパル送金の場合:追加情報
        prmDto.setOverseaPlAdditionalInfo(model.getOverseaPlAdditionalInfo());
        // セールスポイント(スクール記入)
        prmDto.setSellingPoint(model.getSellingPoint().replaceAll(NaikaraTalkConstants.NEW_LINE_CODE_WIN,
                NaikaraTalkConstants.NEW_LINE_CODE_UNIX));
        // 受講生へのアピールポイント
        prmDto.setSelfRecommendation(model.getSelfRecommendation().replaceAll(NaikaraTalkConstants.NEW_LINE_CODE_WIN,
                NaikaraTalkConstants.NEW_LINE_CODE_UNIX));

        // 講師画像名
        prmDto.setImgPhotoNm(model.getImgPhotoNm());
        // 講師画像
        prmDto.setImgPhotoPage(model.getImgPhoto());
        // 講師画像削除
        prmDto.setImgPhotoChkn(model.getImgPhotoChkn());

        // スクール側からのコメント(講師公開)
        prmDto.setEvaluationFromSchoolCmt1(model.getEvaluationFromSchoolCmt1().replaceAll(
                NaikaraTalkConstants.NEW_LINE_CODE_WIN, NaikaraTalkConstants.NEW_LINE_CODE_UNIX));
        // スクール側からのコメント(講師非公開)
        prmDto.setEvaluationFromSchoolCmt2(model.getEvaluationFromSchoolCmt2().replaceAll(
                NaikaraTalkConstants.NEW_LINE_CODE_WIN, NaikaraTalkConstants.NEW_LINE_CODE_UNIX));
        // 最新の受講生から講師へのコメント
        prmDto.setLatestEvaluationFromStudentCmt(model.getLatestEvaluationFromStudentCmt().replaceAll(
                NaikaraTalkConstants.NEW_LINE_CODE_WIN, NaikaraTalkConstants.NEW_LINE_CODE_UNIX));
        // 備考(講師は見えません)
        prmDto.setRemark(model.getRemarkT().replaceAll(NaikaraTalkConstants.NEW_LINE_CODE_WIN,
                NaikaraTalkConstants.NEW_LINE_CODE_UNIX));
        // レコードバージョン番号
        prmDto.setRecordVerNo(model.getRecordVerNoT());
        // 更新者コード
        prmDto.setUpdateCd(((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId());

        return prmDto;
    }

    /**
     * Model値をDTOにセット。<br>
     * <br>
     * Model値をDTOにセット。<br>
     * <br>
     * @param TeacherMstModel UserMstDto<br>
     * @return UserMstDto<br>
     * @exception なし
     */
    private UserMstDto modelToUserMstDto(TeacherMstModel model, UserMstDto prmDto) {

        prmDto.setUserId(model.getUserId());                // 利用者ID
        prmDto.setFamilyJnm(model.getFamilyJnm());          // 名前(姓)
        prmDto.setFirstJnm(model.getFirstJnm());            // 名前(名)
        prmDto.setFamilyKnm(model.getFamilyKnm());          // フリガナ(姓)
        prmDto.setFirstKnm(model.getFirstKnm());            // フリガナ(名）
        prmDto.setFamilyRomaji(model.getFamilyRomaji());    // ローマ字(姓)
        prmDto.setFirstRomaji(model.getFirstRomaji());      // ローマ字(名)
        prmDto.setTel1(model.getTel1());                    // 電話番号1
        prmDto.setTel2(model.getTel2());                    // 電話番号2
        prmDto.setBirthYyyy(model.getBirthYyyy());          // 生年月日：年
        prmDto.setBirthMm(model.getBirthMm());              // 生年月日：月
        prmDto.setBirthDd(model.getBirthDd());              // 生年月日：日
        prmDto.setZipCd(model.getZipCd());                  // 郵便番号
        prmDto.setAddressCity(model.getAddressCity());      // 住所(市区町村)
        prmDto.setAddressOthers(model.getAddressOthers());  // 住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)
        prmDto.setGenderKbn(model.getGenderKbn());          // 性別区分
        prmDto.setMailAddress1(model.getMailAddress1());    // メールアドレス1
        prmDto.setMailAddress2(model.getMailAddress2());    // メールアドレス2
        prmDto.setMailAddress3(model.getMailAddress3());    // メールアドレス3
        prmDto.setCityTown(model.getCityTown());            // 勤務拠点
        prmDto.setRecordVerNo(model.getRecordVerNoU());     // レコードバージョン番号

        return prmDto;
    }

    class ComparatorTeacherRateMstDtos implements Comparator<TeacherRateMstDto> {

        @Override
        public int compare(TeacherRateMstDto o1, TeacherRateMstDto o2) {
            // 比較開始日
            int flag = o1.getStartDt().compareTo(o2.getStartDt());
            // 開始日が同じの場合
            if (flag == 0) {
                // 比較終了日．
                return o1.getEndDt().compareTo(o2.getEndDt());
            } else {
                return flag;
            }
        }
    }
}
