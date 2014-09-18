package com.naikara_talk.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.CourseMstDto;
import com.naikara_talk.dto.CourseUsePointMstDto;
import com.naikara_talk.dto.LessonReservationPerformanceTrnDto;
import com.naikara_talk.dto.LessonReserveCancelResultListDto;
import com.naikara_talk.dto.PointOwnershipTrnDto;
import com.naikara_talk.dto.PointProvisionDataListDto;
import com.naikara_talk.dto.PointReleaseDataListDto;
import com.naikara_talk.dto.SchResTLesResPerTDto;
import com.naikara_talk.dto.ScheduleReservationTrnDto;
import com.naikara_talk.dto.StudentMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.PointProvisionReleaseLogic;
import com.naikara_talk.logic.ReservationCancellationCourseSelectionListLogic;
import com.naikara_talk.model.ReservationCancellationCourseSelectionListModel;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_予約処理<br>
 * <b>クラス名称       :</b>コース名選択ページ画面遷移Serviceクラス<br>
 * <b>クラス概要       :</b>受講者が指定した講師の予約スケジュールを表示して、レッスン予約の登録／取消ができる。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/29 TECS 新規作成
 * <b>                 :</b>2014/06/02 TECS レッスン予約に関する「応相談」対応
 */
public class ReservationCancellationCourseSelectionListMoveService implements ActionService {

    /** チェック：レッスン時刻 */
    public static final int ERR_LESSON_TM = 1;

    /** チェック：レッスン時刻 */
    public static final int ERR_LESSON_TM2 = 2;

    /** チェック：レッスン日 */
    public static final int ERR_COURSE_CD = 3;

    /** チェック：レッスン日 */
    public static final int ERR_COURSE_CD2 = 4;

    /** チェック：予約受付ＮＧ */
    public static final int ERR_RESERV_KBN_NG = 5;

    /** チェック：予約済 */
    public static final int ERR_RESERV_KBN_ALREADY = 6;

    /** チェック：データが存在しない */
    public static final int ERR_RETURN_CD_DATA_NO = 7;

    /** チェック：同じ日時に自分が別講師で既予約済み */
    public static final int ERR_RESERV_SAME_DT_AND_TM = 8;

    // 2014/06/02 レッスン予約に関する「応相談」対応 Upd Start
    /** チェック：レッスン時刻(レッスン開始の3h前(応相談予約)かどうか) */
    public static final int ERR_LESSON_TM_CON = 9;

    /** チェック：応相談予約時のメールアドレスの設定なしエラー */
    public static final int ERR_MAIL_ADDRESS_CON_PROVISIONAL_RESERV = 10;
    // 2014/06/02 レッスン予約に関する「応相談」対応 Upd End


    /** チェック：処理ステータス＝”1” (ポイント所有テーブル取得エラー) */
    public static final int POINT_OWNERSHIP_TRN_NOTHING = 1;

    /** チェック：処理ステータス＝”2” (ポイント引当テーブル取得エラー) */
    public static final int POINT_PROVISION_TRN_NOTHING = 2;

    /** チェック：処理ステータス＝”1” (ポイント引当テーブルなし) */
    public static final int POINT_RELEASE_ERROR = 3;

    /** チェック：処理ステータス＝”1” (ポイント引当エラー（ポイント不足）) */

    public static final int POINT_PROVISION_ERROR = 4;

    /** チェック：処理ステータス＝”1” (ポイント引当エラー（ポイント不足）) */

    public static final int USE_POINT_NULL = 5;
    /** チェック：問題なし(正常) */
    public static final int CHECK_OK = 0;

    /**
     * コースが選択なしのチェック<br>
     * <br>
     * コースが選択なしのチェックを行う。<br>
     * <br>
     * @param model モデル <br>
     * @return int チェック結果<br>
     * @throws NaiException
     */
    public int checkCourseChoiceBrank(ReservationCancellationCourseSelectionListModel model) {

        // ｢コース名｣が選択されているかどうかチェック (全ての明細で、｢コース名｣が選択されていること)
        if (model.getResultList() != null) {
            for (int i = 0; i < model.getResultList().size(); i++) {
                // コース名のコード値 = ”0000” (空欄) の場合
                if (StringUtils
                        .equals(model.getResultList().get(i).getCourseCd(), NaikaraTalkConstants.CHOICE_ALL_ZERO)) {
                    // 一覧のエラーが発生した行数
                    return i + 1;
                }
            }
        }

        return CHECK_OK;
    }

    /**
     * 全てのコースが｢予約中止｣のチェック<br>
     * <br>
     * 全てのコースが｢予約中止｣のチェックを行う。<br>
     * <br>
     * @param model モデル <br>
     * @return boolean 判定フラグ<br>
     * @throws NaiException
     */
    public boolean checkCourseChoiceAllBreak(ReservationCancellationCourseSelectionListModel model) {

        // ｢コース名｣が選択されているかどうかチェック (1つ以上は｢コース名｣に”予約中止”以外が選択されていること)
        boolean chkFlg = true;
        if (model.getResultList() != null) {
            for (SchResTLesResPerTDto dto : model.getResultList()) {
                // コース名のコード値 ＝ ”9999” (予約中止) の場合
                if (!StringUtils.equals(dto.getCourseCd(), NaikaraTalkConstants.CHOICE_ALL_NINE)) {
                    chkFlg = false;
                    break;
                }
            }
        }

        return chkFlg;
    }

    /**
     * 業務チェック<br>
     * <br>
     * 業務チェックを行う。<br>
     * <br>
     * @param teacherId 講師ID<br>
     * @param dto 講師予定予約テーブル、レッスン予実テーブルDTO<br>
     * @return int チェック結果<br>
     * @throws NaiException
     */
    public int serviceCheck(String teacherId, SchResTLesResPerTDto dto) throws NaiException {

        // 2014/06/02 レッスン予約に関する「応相談」対応 Add Start

        // ユーザIDを取得
        String userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString()))
                .getUserId();

        // 応相談予約の場合(予約可能の有無(予約状況、予約区分):応相談関連の場合)
        if (StringUtils.equals(NaikaraTalkConstants.RESERV_KBN_CON_YES, dto.getReservationKbn())
                || StringUtils.equals(NaikaraTalkConstants.RESERV_KBN_CON_PROVISIONAL_RESERV, dto.getReservationKbn())
                || StringUtils.equals(NaikaraTalkConstants.RESERV_KBN_CON_ALREADY, dto.getReservationKbn())) {
            // 受講者マスタのデータ取得処理
            StudentMstDto stMDto = this.selectStudentMst(userId);

            if (stMDto.getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_YES) {
                // メールアドレス1が設定されているかどうかのチェック
                if (stMDto.getMailAddress1() == null || StringUtils.isEmpty(stMDto.getMailAddress1())) {
                    return ERR_MAIL_ADDRESS_CON_PROVISIONAL_RESERV;
                }
            } else {
                return ERR_MAIL_ADDRESS_CON_PROVISIONAL_RESERV;
            }
        }
        // 2014/06/02 レッスン予約に関する「応相談」対応 Add End


        // 予約可能／予約取消可能かどうかのチェック
        String lessonDateTm = this.getLessonDateTime(dto.getLessonDt(), dto.getLessonTm(), true);
        // （隠し項目）予約No ＝ ”” の場合
        if (StringUtils.isEmpty(dto.getReservationNo())
                || StringUtils.equals(NaikaraTalkConstants.RESERVATION_NO_NULL, dto.getReservationNo())) {
            // 一覧の｢コース名｣のコード≠”9999” (予約中止) の場合
            if (!StringUtils.equals(dto.getCourseCd(), NaikaraTalkConstants.CHOICE_ALL_NINE)) {

                // 2014/06/02 レッスン予約に関する「応相談」対応 Upd Start
                if (StringUtils.equals(NaikaraTalkConstants.RESERV_KBN_CON_YES, dto.getReservationKbn())) {
                    // 応相談-予約の場合 (RESERV_KBN_CON_YES:応相談可)
                    // サーバーのシステム日時 + 180 minute (3h)
                    String sysDate = DateUtil.getSysDateAddMinute(180);
                    // サーバーのシステム日時 + 180 minute ≧ 画面の｢日付｣ & 画面の｢レッスン時刻｣の開始時刻 の場合
                    if (!DateUtil.dateCompare4(lessonDateTm, sysDate)) {
                        return ERR_LESSON_TM_CON;
                    }
                } else {
                    // 予約の場合 (RESERV_KBN_YES:予約可)
                    // サーバーのシステム日時 + 20 minute
                    String sysDate = DateUtil.getSysDateAddMinute(20);
                    // サーバーのシステム日時 + 20 minute ≧ 画面の｢日付｣ & 画面の｢レッスン時刻｣の開始時刻 の場合
                    if (!DateUtil.dateCompare4(lessonDateTm, sysDate)) {
                        return ERR_LESSON_TM;
                    }
                }
                 // 2014/06/02 レッスン予約に関する「応相談」対応 Upd End


            }
        } else {
            // サーバーのシステム日時 + 24 hour（24 hour：60*24 minute）
            String sysDate = DateUtil.getSysDateAddMinute(1440);
            // サーバーのシステム日時 + 24 hour ≧ 画面の｢希望日｣ & 一覧の｢時刻｣の開始時刻 の場合
            if (!DateUtil.dateCompare4(lessonDateTm, sysDate)) {
                return ERR_LESSON_TM2;
            }
        }

        // 一覧の｢コース名｣のコード≠”9999” (予約中止) の場合
        if (!StringUtils.equals(dto.getCourseCd(), NaikaraTalkConstants.CHOICE_ALL_NINE)) {

            // 予約処理を行う対象のレッスン日と、選択されているコースの期間チェック
            // コースマスタのデータ取得処理
            CourseMstDto cmDto = this.selectCourseMst(dto.getCourseCd());

            if (cmDto.getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_YES) {
                // コースマスタ 提供開始日 ＞ 画面の｢日付｣ 又は コースマスタ 提供終了日 ＜ 画面の｢日付｣
                if (DateUtil.dateCompare2(cmDto.getUseStrDt(), NaikaraStringUtil.converToYYYYMMDD(dto.getLessonDt()))
                        || DateUtil.dateCompare2(NaikaraStringUtil.converToYYYYMMDD(dto.getLessonDt()), cmDto.getUseEndDt())) {

                    // 提供開始日
                    dto.setLessonDtFr(cmDto.getUseStrDt());
                    // 提供終了日
                    dto.setLessonDtTo(cmDto.getUseEndDt());

                    return ERR_COURSE_CD;
                }

            } else {
                return ERR_COURSE_CD2;
            }



            // 2013/09/25-自分が既に、同一日時の別の講師の予約をしていないかどうかチェック-Start

            // 講師予定予約テーブルのデータ取得処理  ※teacherIdはOPERATOR_NOT_EQUAL
            List<ScheduleReservationTrnDto> retDtoNoSelectTList = this.selectScheduleReservationTrnNoSelectTList(teacherId,
                    dto.getLessonDt(), dto.getLessonTmCd());

            // データが存在する場合
            if (retDtoNoSelectTList != null) {
                ScheduleReservationTrnDto retDtoNoSelectT = retDtoNoSelectTList.get(0);
                if (retDtoNoSelectT.getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_YES) {
                    return ERR_RESERV_SAME_DT_AND_TM;
                }
            }
            // 2013/09/25-自分が既に、同一日時の別の講師の予約をしていないかどうかチェック-End



            // 予約状況のチェック (講師の予約受付状態の変更が発生していないかのチェック、他の受講者からの予約の有無チェック)
            // 講師予定予約テーブルの データ取得処理
            ScheduleReservationTrnDto srtDto = this.selectScheduleReservationTrn(teacherId, dto.getLessonDt(),
                    dto.getLessonTmCd());

            // データが存在する場合
            if (srtDto.getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_YES) {
                // 講師予定予約テーブル．予約区分 ＝ ”0” (予約受付ＮＧ) の場合
                if (StringUtils.equals(srtDto.getReservationKbn(), NaikaraTalkConstants.RESERV_KBN_NG)) {
                    return ERR_RESERV_KBN_NG;
                }

                // 2014/06/02 レッスン予約に関する「応相談」対応 Upd Start
                // 講師予定予約テーブル．予約区分 ＝ ”2” (予約済み) の場合 又は ”5”(応相談-予約済(仮予約)) 又は  ”6”(応相談-予約済(予約確定))
                // if (StringUtils.equals(srtDto.getReservationKbn(), NaikaraTalkConstants.RESERV_KBN_ALREADY)) {
                if (StringUtils.equals(srtDto.getReservationKbn(), NaikaraTalkConstants.RESERV_KBN_ALREADY)
                		|| StringUtils.equals(srtDto.getReservationKbn(), NaikaraTalkConstants.RESERV_KBN_CON_PROVISIONAL_RESERV)
                		|| StringUtils.equals(srtDto.getReservationKbn(), NaikaraTalkConstants.RESERV_KBN_CON_ALREADY)) {
                // 2014/06/02 レッスン予約に関する「応相談」対応 Upd End

                    // ユーザIDを取得
                    //String userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString()))
                    //        .getUserId();
                    // 講師予定予約テーブル．受講者ID ≠ ログイン情報：受講者ID の場合
                    if (!StringUtils.equals(srtDto.getStudentId(), userId)) {
                        return ERR_RESERV_KBN_ALREADY;
                    }
                }
            } else {
                // データが存在しない場合
                return ERR_RETURN_CD_DATA_NO;
            }
        }

        return CHECK_OK;
    }

    /**
     * ｢レッスン日｣ & ｢レッスン時刻｣の取得<br>
     * <br>
     * ｢レッスン日｣ & ｢レッスン時刻｣の取得処理<br>
     * <br>
     * @param lessonDt レッスン日<br>
     * @param lessonTm レッスン時刻<br>
     * @param startTmFlg 開始時刻フラグ<br>
     * @return String ｢レッスン日｣ & ｢レッスン時刻｣<br>
     * @throws NaiException
     */
    public String getLessonDateTime(String lessonDt, String lessonTm, boolean startTmFlg) {

        // ｢時刻｣の開始時刻
        String lessonTmStart = lessonTm;

        if (startTmFlg) {
            lessonTmStart = lessonTmStart.substring(0, 5);
        }

        // 画面の｢希望日｣ & 一覧の｢時刻｣の開始時刻
        String lessonDateTime = NaikaraStringUtil.unionName(NaikaraStringUtil.converToYYYY_MM_DD(lessonDt),
                lessonTmStart);

        return lessonDateTime;
    }

    /**
     * コースマスタデータの取得<br>
     * <br>
     * コースマスタデータの取得を行う<br>
     * <br>
     * @param courseCd コースコード<br>
     * @return CourseMstDto コースマスタDTO<br>
     * @throws NaiException
     */
    public CourseMstDto selectCourseMst(String courseCd) throws NaiException {

        Connection conn = null;

        try {
            conn = DbUtil.getConnection();

            // 初期化
            ReservationCancellationCourseSelectionListLogic logic = new ReservationCancellationCourseSelectionListLogic(
                    conn);

            CourseMstDto dto = new CourseMstDto();
            // コースコード
            dto.setCourseCd(courseCd);

            return logic.selectCourseMst(dto);

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
     * 講師予定予約テーブルのデータ取得処理を行う。<br>
     * <br>
     * @param teacherId 講師ID<br>
     * @param lessonDt レッスン日<br>
     * @param lessonTm レッスン時刻<br>
     * @return ScheduleReservationTrnDto 講師予定予約テーブルDTO<br>
     * @throws NaiException
     */
    public ScheduleReservationTrnDto selectScheduleReservationTrn(String teacherId, String lessonDt, String lessonTm)
            throws NaiException {

        Connection conn = null;

        try {
            conn = DbUtil.getConnection();

            // 初期化
            ScheduleReservationTrnDto dto = new ScheduleReservationTrnDto();
            // 講師ID
            dto.setTeacherId(teacherId);
            // レッスン日：YYYYMMDD形式とする
            dto.setLessonDt(NaikaraStringUtil.converToYYYYMMDD(lessonDt));
            // レッスン時刻
            dto.setLessonTmCd(lessonTm);

            ReservationCancellationCourseSelectionListLogic logic = new ReservationCancellationCourseSelectionListLogic(
                    conn);

            return logic.selectScheduleReservationTrn(dto);

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
     * 引当データリストの取得処理。<br>
     * <br>
     * 引当データリストの取得処理を行う。<br>
     * <br>
     * @param studentId 受講者ID<br>
     * @param teacherId 講師ID<br>
     * @param dtoList 講師予定予約テーブル、レッスン予実テーブルDTOリスト<br>
     * @return List<PointProvisionDataListDto> ポイント引当DTOリスト<br>
     * @throws NaiException
     */
    public List<PointProvisionDataListDto> getPointProvisionDataList(String studentId,
        String teacherId, List<SchResTLesResPerTDto> dtoList) throws NaiException {

        Connection conn = null;

        try {
            conn = DbUtil.getConnection();

            // 引当データリスト
            List<PointProvisionDataListDto> ppdlDtoList = new ArrayList<PointProvisionDataListDto>();

            if (dtoList != null) {
                for (SchResTLesResPerTDto dto : dtoList) {
                    // 一覧の｢コース名｣のコード≠”9999” (予約中止) の かつ（隠し項目）予約No = ”” (予約) の場合
                    if (!StringUtils.equals(dto.getCourseCd(), NaikaraTalkConstants.CHOICE_ALL_NINE)
                            && (StringUtils.isEmpty(dto.getReservationNo()) || StringUtils.equals(
                                    NaikaraTalkConstants.RESERVATION_NO_NULL, dto.getReservationNo()))) {

                        // 初期化
                        CourseUsePointMstDto cpmDto = new CourseUsePointMstDto();
                        // コースコード
                        cpmDto.setCourseCd(dto.getCourseCd());
                        // 利用ポイント開始日 ⇒ 画面の(一覧) ｢レッスン日｣：YYYYMMDD形式とする
                        cpmDto.setUsePointStrDt(NaikaraStringUtil.converToYYYYMMDD(dto.getLessonDt()));
                        // 利用ポイント終了日 ⇒ 画面の(一覧) ｢レッスン日｣：YYYYMMDD形式とする
                        cpmDto.setUsePointEndDt(NaikaraStringUtil.converToYYYYMMDD(dto.getLessonDt()));

                        ReservationCancellationCourseSelectionListLogic logic = new ReservationCancellationCourseSelectionListLogic(
                                conn);

                        // コース別利用ポイントマスタのデータ取得処理
                        List<CourseUsePointMstDto> retDtoList = logic.selectCourseUsePointMstList(cpmDto);

                        CourseUsePointMstDto retDto = retDtoList.get(0);

                        // 上記で取得した｢利用ポイント｣ ⇒ 画面の(一覧) ｢ご利用ポイント｣
                        if (retDto.getUsePoint() != null) {
                            dto.setUsePoint(String.valueOf(retDto.getUsePoint()));
                        }

                        // ポイント引当DTOの作成
                        PointProvisionDataListDto ppdlDto = new PointProvisionDataListDto();
                        // レッスン日／商品の購入日 ⇒ 一覧の｢レッスン日｣ ※YYYYMMDD形式
                        ppdlDto.setLessonDt(NaikaraStringUtil.converToYYYYMMDD(dto.getLessonDt()));
                        // レッスン時刻 ⇒ 一覧の(隠し項目)｢レッスン時刻｣のコード ※汎用コード
                        ppdlDto.setLessonTmCd(dto.getLessonTmCd());
                        // 利用ポイント ⇒ コース別利用ポイントマスタ．利用ポイント
                        ppdlDto.setUsePoint(retDto.getUsePoint());
                        // 講師ID ⇒ 画面の｢講師ID｣
                        ppdlDto.setTeacherId(teacherId);
                        // コースコード ⇒ 選択されている画面の｢コースコード｣
                        ppdlDto.setCourseCd(dto.getCourseCd());

                        // 2014/06/02 レッスン予約に関する「応相談」対応 Add Start
                        // ◆現在の予約状況(予約区分)より更新すべき値の判定
                        String reservationKbnSet = NaikaraStringUtil.judgmentReservationKbn(
                        		NaikaraTalkConstants.RESERV_PROCESS_KBN_YES, dto.getReservationKbn());
                        ppdlDto.setReservationKbnSet(reservationKbnSet);
                        ppdlDto.setReservationKbnWhere(dto.getReservationKbn());

                        ppdlDto.setUpdateCd(studentId);    // 更新者＝受講者ID
                        // 2014/06/02 レッスン予約に関する「応相談」対応 Add End

                        ppdlDtoList.add(ppdlDto);
                    }
                }
            }

            return ppdlDtoList;

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
     * 解除データリストの取得処理。<br>
     * <br>
     * 解除データリストの取得処理を行う。<br>
     * <br>
     * @param studentId 受講者ID<br>
     * @param teacherId 講師ID<br>
     * @param dtoList 講師予定予約テーブル、レッスン予実テーブルDTOリスト<br>
     * @return List<PointReleaseDataListDto> ポイント解除DTOリスト<br>
     * @throws NaiException
     */
    public List<PointReleaseDataListDto> getPointReleaseDataList(
    		String studentId, String teacherId, List<SchResTLesResPerTDto> dtoList)
            throws NaiException {

        Connection conn = null;

        try {
            conn = DbUtil.getConnection();

            // 解除データリス
            List<PointReleaseDataListDto> prdlDtoList = new ArrayList<PointReleaseDataListDto>();

            if (dtoList != null) {
                for (SchResTLesResPerTDto dto : dtoList) {
                    // 一覧の｢コース名｣のコード≠”9999” (予約中止) の かつ（隠し項目）予約No ≠ ”” (予約) の場合
                    if (!StringUtils.equals(dto.getCourseCd(), NaikaraTalkConstants.CHOICE_ALL_NINE)
                            && !(StringUtils.isEmpty(dto.getReservationNo()) || StringUtils.equals(
                                    NaikaraTalkConstants.RESERVATION_NO_NULL, dto.getReservationNo()))) {

                        ReservationCancellationCourseSelectionListLogic logic = new ReservationCancellationCourseSelectionListLogic(
                                conn);

                        // レッスン予実テーブルのデータ取得処理
                        LessonReservationPerformanceTrnDto retDto = logic.selectLessonReservationPerformanceTrn(dto
                                .getReservationNo());

                        // 上記で取得した｢利用ポイント合計｣ ⇒ 画面の(一覧) ｢お取消ポイント｣
                        if (retDto.getUsePointSum() != null) {
                            dto.setCancelPoint(String.valueOf(retDto.getUsePointSum()));
                        }

                        // ポイント解除DTOの作成
                        PointReleaseDataListDto prdlDto = new PointReleaseDataListDto();
                        // 予約No／購入ID ⇒ 一覧の（隠し項目）予約No
                        prdlDto.setRsvNoPurchaseId(dto.getReservationNo());
                        // 講師ID ⇒ 画面の｢講師ID｣
                        prdlDto.setTeacherId(teacherId);
                        // レッスン日 ⇒ 一覧の｢レッスン日｣ ※YYYYMMDD形式
                        prdlDto.setLessonDt(NaikaraStringUtil.converToYYYYMMDD(dto.getLessonDt()));
                        // レッスン時刻 ⇒ 一覧の｢レッスン時刻｣のコード ※汎用コード
                        prdlDto.setLessonTmCd(dto.getLessonTmCd());

                        // 2013/10/01-コースコードに値を設定する-Start
                        // コースコード ⇒ ””
                        //prdlDto.setCourseCd(NaikaraTalkConstants.BRANK);
                        prdlDto.setCourseCd(retDto.getCourseCd());
                        // 2013/10/01-コースコードに値を設定する-End

                        // 2014/06/02 レッスン予約に関する「応相談」対応 Add Start
                        // ◆現在の予約状況(予約区分)より更新すべき値の判定
                        String reservationKbnSet = NaikaraStringUtil.judgmentReservationKbn(
                        		NaikaraTalkConstants.RESERV_PROCESS_KBN_NO, dto.getReservationKbn());
                        prdlDto.setReservationKbnSet(reservationKbnSet);
                        prdlDto.setReservationKbnWhere(dto.getReservationKbn());

                        prdlDto.setUpdateCd(studentId);        // 更新者＝受講者ID
                        // 2014/06/02 レッスン予約に関する「応相談」対応 Add End

                        prdlDtoList.add(prdlDto);
                    }
                }
            }

            return prdlDtoList;

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
     * ポイント引当・解除チェック<br>
     * <br>
     * ポイント引当・解除チェックを行う。<br>
     * <br>
     * @param ppdlDtoList 引当データリスト<br>
     * @param prdlDtoList 解除データリスト<br>
     * @return List<LessonReserveCancelResultListDto> レッスン予約・取消結果リスト<br>
     * @throws NaiException
     */
    public List<LessonReserveCancelResultListDto> pointCheck(List<PointProvisionDataListDto> ppdlDtoList,
            List<PointReleaseDataListDto> prdlDtoList) throws NaiException {

        // ユーザIDを取得
        String userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();

        Connection conn = null;

        try {
            conn = DbUtil.getConnection();

            // 初期化
            PointProvisionReleaseLogic logic = new PointProvisionReleaseLogic(conn);

            // ポイント引当・解除チェック
            List<LessonReserveCancelResultListDto> retDtoList = logic.pointCheck(userId, ppdlDtoList, prdlDtoList);

            return retDtoList;

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
     * 残高ポイントの集計の取得<br>
     * <br>
     * 残高ポイントの集計の取得を行う。<br>
     * <br>
     * @param なし<br>
     * @return String 残高ポイントの集計<br>
     * @throws NaiException
     */
    public String getBalancePointOldSum() throws NaiException {

        Connection conn = null;

        try {
            conn = DbUtil.getConnection();

            // 初期化
            ReservationCancellationCourseSelectionListLogic logic = new ReservationCancellationCourseSelectionListLogic(
                    conn);

            // ポイント所有テーブルのデータ取得処理
            List<PointOwnershipTrnDto> retDtoList = logic.selectPointOwnershipTrnList();

            BigDecimal pointSum = new BigDecimal(0);

            if (retDtoList.get(0).getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_YES) {

                for (PointOwnershipTrnDto retDto : retDtoList) {

                    if (retDto.getBalancePoint() != null) {
                        // 集計する
                        pointSum = pointSum.add(retDto.getBalancePoint());
                    }
                }
            }

            return String.valueOf(pointSum);

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
     * ご利用ポイント合計の取得<br>
     * <br>
     * ご利用ポイント合計の取得を行う。<br>
     * <br>
     * @param model モデル<br>
     * @return String 利用ポイント合計<br>
     * @throws なし
     */
    public String getUserPointSum(List<SchResTLesResPerTDto> dtoList) {

        BigDecimal usePointSum = new BigDecimal(0);

        for (SchResTLesResPerTDto dto : dtoList) {

            if (!StringUtils.isEmpty(dto.getUsePoint())) {
                // 合計
                usePointSum = usePointSum.add(new BigDecimal(dto.getUsePoint()));
            }
        }

        return String.valueOf(usePointSum);
    }

    /**
     * ご取消ポイント合計の取得<br>
     * <br>
     * ご取消ポイント合計の取得を行う。<br>
     * <br>
     * @param model モデル<br>
     * @return String 取消ポイント合計<br>
     * @throws なし
     */
    public String getCancelPointSum(List<SchResTLesResPerTDto> dtoList) {

        BigDecimal cancelPointSum = new BigDecimal(0);

        for (SchResTLesResPerTDto dto : dtoList) {

            if (!StringUtils.isEmpty(dto.getCancelPoint())) {
                // 合計
                cancelPointSum = cancelPointSum.add(new BigDecimal(dto.getCancelPoint()));
            }
        }

        return String.valueOf(cancelPointSum);
    }

    /**
     * 残高ポイントの集計の取得<br>
     * <br>
     * 残高ポイントの集計の取得を行う。<br>
     * <br>
     * @param reservationNo 予約No<br>
     * @return String 残高ポイントの集計<br>
     * @throws NaiException
     */
    public String getBalancePointNewSum(String balancePointOld, String usePointSum, String cancelPointSum) {

        BigDecimal pointSum = new BigDecimal(0);

        if (!StringUtils.isEmpty(balancePointOld)) {
            // 画面の(合計) ｢新ポイント残高｣ ⇒ 画面の(合計) ｢現ポイント残高｣ - 画面の(合計) ｢ご利用ポイント合計｣ +
            // 画面の(合計) ｢お取消ポイント合計｣
            pointSum = pointSum.add(new BigDecimal(balancePointOld));

            if (!StringUtils.isEmpty(usePointSum)) {
                pointSum = pointSum.subtract(new BigDecimal(usePointSum));

                if (!StringUtils.isEmpty(cancelPointSum)) {
                    pointSum = pointSum.add(new BigDecimal(cancelPointSum));
                }
            }
        }

        return String.valueOf(pointSum);
    }


    /**
     * 講師予定予約テーブルのデータ取得処理（選択されていない講師）。<br>
     * <br>
     * 講師予定予約テーブルのデータ取得処理を行う。<br>
     * <br>
     * @param String teacherId<br>
     * @param String lessonDt<br>
     * @param String lessonTmCd<br>
     * @param logic ReservationCancellationTeacherScheduleLogic<br>
     * @return List<ScheduleReservationTrnDto> 講師予定予約テーブルDTOリスト<br>
     * @throws NaiException
     */
    public List<ScheduleReservationTrnDto> selectScheduleReservationTrnNoSelectTList(
        String teacherId, String lessonDt, String lessonTmCd) throws NaiException {

        Connection conn = null;

        try {
            conn = DbUtil.getConnection();

            ReservationCancellationCourseSelectionListLogic logic = new ReservationCancellationCourseSelectionListLogic(
                    conn);

            ScheduleReservationTrnDto srdto = new ScheduleReservationTrnDto();

            // 講師ID
            srdto.setTeacherId(teacherId);
            // レッスン日：YYYYMMDD形式とする
            srdto.setLessonDt(NaikaraStringUtil.converToYYYYMMDD(lessonDt));
            // レッスン時刻
            srdto.setLessonTmCd(lessonTmCd);

            String studentId = "";
            SessionUser SessionUserData = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString()));
            if (SessionUserData != null) {
                // ユーザIDを取得
            	studentId = SessionUserData.getUserId();
            }
            // 受講者ID
            srdto.setStudentId(studentId);

            return logic.selectScheduleReservationTrnNoSelectTList(srdto);

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


    // 2014/06/02 レッスン予約に関する「応相談」対応 Add Start
    /**
     * 受講者マスタデータの取得<br>
     * <br>
     * 受講者マスタデータの取得を行う<br>
     * <br>
     * @param studentId 受講者ID<br>
     * @return StudentMstDto 受講者マスタDTO<br>
     * @throws NaiException
     */
    public StudentMstDto selectStudentMst(String studentId) throws NaiException {

        Connection conn = null;

        try {
            // DB接続
            conn = DbUtil.getConnection();

            ReservationCancellationCourseSelectionListLogic logic = new ReservationCancellationCourseSelectionListLogic(
                    conn);

            StudentMstDto dto = new StudentMstDto();

            // コースコード
            dto.setStudentId(studentId);

            return logic.selectStudentMst(dto);

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


}
