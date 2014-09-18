package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.CourseUsePointMstDto;
import com.naikara_talk.dto.LessonReserveCancelResultListDto;
import com.naikara_talk.dto.PointProvisionDataListDto;
import com.naikara_talk.dto.PointReleaseDataListDto;
import com.naikara_talk.dto.ScheduleReservationTrnDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.PointProvisionReleaseLogic;
import com.naikara_talk.logic.ReservationCancellationCourseListLogic;
import com.naikara_talk.model.ReservationCancellationCourseListModel;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_予約処理<br>
 * <b>クラス名称       :</b>予約申込ページ画面遷移Serviceクラス<br>
 * <b>クラス概要       :</b>受講者がレッスンを予約登録、取消ができる。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/18 TECS 新規作成
 * <b>                 :</b>2014/06/02 TECS レッスン予約に関する「応相談」対応
 */
public class ReservationCancellationCourseListMoveService implements ActionService {

    /** チェック：一覧部の項目が選択なし */
    public static final int ERR_NO_SELECT = 1;

    /** チェック：一覧画面へ戻る */
    public static final int ERR_LIST_BACK = 2;

    /** チェック：レッスン時刻 */
    public static final int ERR_LESSON_TM = 3;

    /** チェック：レッスン時刻 */
    public static final int ERR_LESSON_TM2 = 4;

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
    // 2014/06/02 レッスン予約に関する「応相談」対応 Upd End


    /** チェック：処理ステータス＝”1” (ポイント所有テーブル取得エラー) */
    public static final int POINT_OWNERSHIP_TRN_NOTHING = 1;

    /** チェック：処理ステータス＝”2” (ポイント引当テーブル取得エラー) */
    public static final int POINT_PROVISION_TRN_NOTHING = 2;

    /** チェック：処理ステータス＝”1” (ポイント引当テーブルなし) */
    public static final int POINT_RELEASE_ERROR = 3;

    /** チェック：処理ステータス＝”1” (ポイント引当エラー（ポイント不足）) */
    public static final int POINT_PROVISION_ERROR = 4;

    /** チェック：問題なし(正常) */
    public static final int CHECK_OK = 0;

    /**
     * 画面遷移の制御処理<br>
     * <br>
     * 画面遷移の制御処理<br>
     * <br>
     * @param model モデル<br>
     * @param select_rdl 一覧から選択された明細データ<br>
     * @param hasSearchFlg 検索判断フラグ<br>
     * @return int チェック結果<br>
     * @throws NaiException
     */
    public int nextPageRequest(ReservationCancellationCourseListModel model, String select_rdl, String hasSearchFlg)
            throws NaiException {

        // 一覧部の項目が選択されていない場合は、メッセージ情報を設定する
        if (StringUtils.isEmpty(select_rdl)) {
            // エラー場合の再検索判断
            if (NaikaraTalkConstants.TRUE.equals(hasSearchFlg)) {
                return ERR_NO_SELECT;
            }
            // 一覧画面戻る
            return ERR_LIST_BACK;
        }

        // 予約可能／予約取消可能かどうかのチェック
        String lessonDateTm = this.getLessonDateTime(model, true);
        // （隠し項目）予約No ＝ ”” の場合
        if (StringUtils.isEmpty(model.getSchResTLesResPerTDto().getReservationNo())
                || StringUtils.equals(NaikaraTalkConstants.RESERVATION_NO_NULL, model.getSchResTLesResPerTDto()
                        .getReservationNo())) {
            // サーバーのシステム日時 + 20 minute
            String sysDate = DateUtil.getSysDateAddMinute(20);
            // サーバーのシステム日時 + 20 minute ≧ 画面の｢希望日｣ & 一覧の｢時刻｣の開始時刻 の場合
            if (!DateUtil.dateCompare4(lessonDateTm, sysDate)) {
                return ERR_LESSON_TM;
            }
        } else {
            // サーバーのシステム日時 + 24 hour（24 hour：60*24 minute）
            String sysDate = DateUtil.getSysDateAddMinute(1440);
            // サーバーのシステム日時 + 24 hour ≧ 画面の｢希望日｣ & 一覧の｢時刻｣の開始時刻 の場合
            if (!DateUtil.dateCompare4(lessonDateTm, sysDate)) {
                return ERR_LESSON_TM2;
            }
        }


        // 2013/09/22-自分が既に、同一日時の別の講師の予約をしていないかどうかチェック-Start
        // 講師予定予約テーブルのデータ取得処理
        List<ScheduleReservationTrnDto> retDtoNoSelectTList = this.selectScheduleReservationTrnNoSelectTList(model);

        // データが存在する場合
        ScheduleReservationTrnDto retDtoNoSelectT = retDtoNoSelectTList.get(0);
        if (retDtoNoSelectT.getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_YES) {
            return ERR_RESERV_SAME_DT_AND_TM;
        }
        // 2013/09/22-自分が既に、同一日時の別の講師の予約をしていないかどうかチェック-End



        // 予約状態のチェック (講師の予約受付状態の変更が発生していないかのチェック、他の受講者からの予約の有無チェック)
        // 講師予定予約テーブルのデータ取得処理
        List<ScheduleReservationTrnDto> retDtoList = this.selectScheduleReservationTrnList(model);

        // データが存在する場合
        ScheduleReservationTrnDto retDto = retDtoList.get(0);
        if (retDto.getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_YES) {
            // 講師予定予約テーブル．予約区分 ＝ ”0” (予約受付ＮＧ) の場合
            if (StringUtils.equals(retDto.getReservationKbn(), NaikaraTalkConstants.RESERV_KBN_NG)) {
                return ERR_RESERV_KBN_NG;
            }

            // 講師予定予約テーブル．予約区分 ＝ ”2” (予約済み)
            // 又は  "5"(応相談-予約済(仮予約)) 又は  "6"(応相談-予約確定)の場合
            if (StringUtils.equals(retDto.getReservationKbn(), NaikaraTalkConstants.RESERV_KBN_ALREADY)
                    || StringUtils.equals(retDto.getReservationKbn(), NaikaraTalkConstants.RESERV_KBN_CON_PROVISIONAL_RESERV)
                    || StringUtils.equals(retDto.getReservationKbn(), NaikaraTalkConstants.RESERV_KBN_CON_ALREADY)) {
                // ユーザIDを取得
                String userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString()))
                        .getUserId();
                // 講師予定予約テーブル．受講者ID ≠ ログイン情報：受講者ID の場合
                if (!StringUtils.equals(retDto.getStudentId(), userId)) {
                    return ERR_RESERV_KBN_ALREADY;
                }
            }
        } else {
            // データが存在しない場合
            return ERR_RETURN_CD_DATA_NO;
        }

        return CHECK_OK;
    }

    /**
     * 画面の｢希望日｣ & 一覧の｢時刻｣の取得<br>
     * <br>
     * 画面の｢希望日｣ & 一覧の｢時刻｣の取得処理<br>
     * <br>
     * @param model モデル<br>
     * @param startTmFlg 開始時刻フラグ<br>
     * @return String ｢希望日｣ & ｢時刻｣<br>
     * @throws NaiException
     */
    public String getLessonDateTime(ReservationCancellationCourseListModel model, boolean startTmFlg)
            throws NaiException {

        // 時刻
        String lessonTmStart = model.getSchResTLesResPerTDto().getLessonTm();

        // ｢時刻｣の開始時刻
        if (startTmFlg) {
            lessonTmStart = lessonTmStart.substring(0, 5);
        }

        // 画面の｢希望日｣ & 一覧の｢時刻｣の開始時刻
        String lessonDateTime = NaikaraStringUtil.unionName(NaikaraStringUtil.converToYYYY_MM_DD(model.getHopeDt()),
                lessonTmStart);

        return lessonDateTime;
    }

    /**
     * 講師予定予約テーブルのデータ取得処理。<br>
     * <br>
     * 講師予定予約テーブルのデータ取得処理を行う。<br>
     * <br>
     * @param model モデル<br>
     * @return List<ScheduleReservationTrnDto> 講師予定予約テーブルDTOリスト<br>
     * @throws NaiException
     */
    public List<ScheduleReservationTrnDto> selectScheduleReservationTrnList(ReservationCancellationCourseListModel model)
            throws NaiException {

        Connection conn = null;

        try {
            conn = DbUtil.getConnection();

            // 初期化
            ReservationCancellationCourseListLogic logic = new ReservationCancellationCourseListLogic(conn);

            ScheduleReservationTrnDto dto = new ScheduleReservationTrnDto();
            // 講師ID
            dto.setTeacherId(model.getTeacherId());
            // レッスン日：YYYYMMDD形式とする
            dto.setLessonDt(NaikaraStringUtil.converToYYYYMMDD(model.getHopeDt()));
            // レッスン時刻
            dto.setLessonTmCd(model.getSchResTLesResPerTDto().getLessonTmCd());

            return logic.selectScheduleReservationTrnList(dto);

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
     * 講師予定予約テーブルのデータ取得処理（選択されていない講師）。<br>
     * <br>
     * 講師予定予約テーブルのデータ取得処理を行う。<br>
     * <br>
     * @param model モデル<br>
     * @return List<ScheduleReservationTrnDto> 講師予定予約テーブルDTOリスト<br>
     * @throws NaiException
     */
    public List<ScheduleReservationTrnDto> selectScheduleReservationTrnNoSelectTList(ReservationCancellationCourseListModel model)
            throws NaiException {

        Connection conn = null;

        try {
            conn = DbUtil.getConnection();

            // 初期化
            ReservationCancellationCourseListLogic logic = new ReservationCancellationCourseListLogic(conn);

            ScheduleReservationTrnDto dto = new ScheduleReservationTrnDto();
            // 講師ID
            dto.setTeacherId(model.getTeacherId());
            // レッスン日：YYYYMMDD形式とする
            dto.setLessonDt(NaikaraStringUtil.converToYYYYMMDD(model.getHopeDt()));
            // レッスン時刻
            dto.setLessonTmCd(model.getSchResTLesResPerTDto().getLessonTmCd());

            String studentId = "";
            SessionUser SessionUserData = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString()));
            if (SessionUserData != null) {
                // ユーザIDを取得
            	studentId = SessionUserData.getUserId();
            }
            // 受講者ID
            dto.setStudentId(studentId);

            return logic.selectScheduleReservationTrnNoSelectTList(dto);

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
     * @param model モデル<br>
     * @return List<PointProvisionDataListDto> ポイント引当DTOリスト<br>
     * @throws NaiException
     */
    public List<PointProvisionDataListDto> getPointProvisionDataList(ReservationCancellationCourseListModel model)
            throws NaiException {

        Connection conn = null;

        try {
            conn = DbUtil.getConnection();

            // 引当データリスト
            List<PointProvisionDataListDto> ppdlDtoList = new ArrayList<PointProvisionDataListDto>();

            // （隠し項目）予約No = ”” (予約) の場合
            if (StringUtils.isEmpty(model.getSchResTLesResPerTDto().getReservationNo())
                    || StringUtils.equals(NaikaraTalkConstants.RESERVATION_NO_NULL, model.getSchResTLesResPerTDto()
                            .getReservationNo())) {

                // 初期化
                ReservationCancellationCourseListLogic logic = new ReservationCancellationCourseListLogic(conn);

                CourseUsePointMstDto dto = new CourseUsePointMstDto();
                // コースコード
                dto.setCourseCd(model.getSchResTLesResPerTDto().getCourseCd());
                // 利用ポイント開始日：YYYYMMDD形式とする
                dto.setUsePointStrDt(NaikaraStringUtil.converToYYYYMMDD(model.getHopeDt()));
                // 利用ポイント終了日：YYYYMMDD形式とする
                dto.setUsePointEndDt(NaikaraStringUtil.converToYYYYMMDD(model.getHopeDt()));

                // コース別利用ポイントマスタのデータ取得処理
                List<CourseUsePointMstDto> retDtoList = logic.selectCourseUsePointMstList(dto);

                CourseUsePointMstDto retDto = retDtoList.get(0);

                PointProvisionDataListDto ppdlDto = new PointProvisionDataListDto();
                // レッスン日／商品の購入日 ⇒ 希望日 ※YYYYMMDD形式
                ppdlDto.setLessonDt(NaikaraStringUtil.converToYYYYMMDD(model.getHopeDt()));
                // レッスン時刻 ⇒ 一覧の(隠し項目)｢レッスン時刻｣のコード ※汎用コード
                ppdlDto.setLessonTmCd(model.getSchResTLesResPerTDto().getLessonTmCd());
                // 利用ポイント ⇒ コース別利用ポイントマスタ．利用ポイント
                ppdlDto.setUsePoint(retDto.getUsePoint());
                // 講師ID ⇒ 一覧の(隠し項目)｢講師ID｣
                ppdlDto.setTeacherId(model.getTeacherId());
                // コースコード ⇒ 一覧の(隠し項目)｢コースコード｣
                ppdlDto.setCourseCd(model.getSchResTLesResPerTDto().getCourseCd());

                ppdlDtoList.add(ppdlDto);
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
     * @param model モデル<br>
     * @return List<PointReleaseDataListDto> ポイント解除DTOリスト<br>
     * @throws NaiException
     */
    public List<PointReleaseDataListDto> getPointReleaseDataList(ReservationCancellationCourseListModel model)
            throws NaiException {

        // 解除データリスト
        List<PointReleaseDataListDto> prdlDtoList = new ArrayList<PointReleaseDataListDto>();

        // （隠し項目）予約No ≠ ”” (予約) の場合
        if (!(StringUtils.isEmpty(model.getSchResTLesResPerTDto().getReservationNo()) || StringUtils.equals(
                NaikaraTalkConstants.RESERVATION_NO_NULL, model.getSchResTLesResPerTDto().getReservationNo()))) {

            PointReleaseDataListDto prdlDto = new PointReleaseDataListDto();
            // 予約No／購入ID ⇒ 一覧の（隠し項目）予約No
            prdlDto.setRsvNoPurchaseId(model.getSchResTLesResPerTDto().getReservationNo());
            // 講師ID ⇒ 一覧の(隠し項目)｢講師ID｣
            prdlDto.setTeacherId(model.getTeacherId());
            // レッスン日 ⇒ 希望日 ※YYYYMMDD形式
            prdlDto.setLessonDt(NaikaraStringUtil.converToYYYYMMDD(model.getHopeDt()));
            // レッスン時刻 ⇒ 一覧の(隠し項目)｢レッスン時刻｣のコード ※汎用コード
            prdlDto.setLessonTmCd(model.getSchResTLesResPerTDto().getLessonTmCd());
            // コースコード ⇒ ””
            prdlDto.setCourseCd(NaikaraTalkConstants.BRANK);

            prdlDtoList.add(prdlDto);
        }

        return prdlDtoList;
    }

    /**
     * ポイント引当・解除チェック<br>
     * <br>
     * ポイント引当・解除チェックを行う。<br>
     * <br>
     * @param ppdlDtoList 引当データリスト<br>
     * @param prdlDtoList 解除データリスト<br>
     * @return int レッスン予約・取消結果<br>
     * @throws NaiException
     */
    public int pointCheck(List<PointProvisionDataListDto> ppdlDtoList, List<PointReleaseDataListDto> prdlDtoList)
            throws NaiException {

        // ユーザIDを取得
        String userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();

        Connection conn = null;

        try {
            conn = DbUtil.getConnection();

            // 初期化
            PointProvisionReleaseLogic logic = new PointProvisionReleaseLogic(conn);

            // ポイント引当・解除チェック
            List<LessonReserveCancelResultListDto> retDtoList = logic.pointCheck(userId, ppdlDtoList, prdlDtoList);

            return retDtoList.get(0).getReturnCode();

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
