package com.naikara_talk.action;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dto.SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.ReservationCancellationCourseListSearchService;
import com.naikara_talk.sessiondata.SessionReservationCancellationCourse;
//import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_予約処理<br>
 * <b>クラス名称       :</b>予約申込ページ検索Actionクラス<br>
 * <b>クラス概要       :</b>受講者がレッスンを予約登録、取消ができる。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/18 TECS 新規作成
 * <b>                 :</b>2013/11/14 TECS 要望対応 No1022-4(表示上限数の変更 100⇒500)
 * <b>                 :</b>2014/04/22 TECS 希望日をテキストからプルダウンへ変更対応
 */
public class ReservationCancellationCourseListSearchAction extends ReservationCancellationCourseListActionSupport {

    private static final long serialVersionUID = 1L;

    /** 一覧の表示上限 */
    // 2013-11-14 要望対応 No1022-4：表示上限数の変更 100⇒500 Start
    ////private static final int LIST_MAX_CNT = 100;
    private static final int LIST_MAX_CNT = 500;
    // 2013-11-14 要望対応 No1022-4：表示上限数の変更 100⇒500 End

    /**
     * 検索処理。<br>
     * <br>
     * 検索処理行う。<br>
     * <br>
     * @param なし<br>
     * @return String<br>
     * @throws NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // 画面のパラメータセット
        setupModel();

        // 2014/04/22 Upd Start 希望日をテキストからプルダウンへ変更対応
        //this.hopeDt_txt = NaikaraStringUtil.converToYYYY_MM_DD(this.hopeDt_txt);
        this.hopeDt_sel = NaikaraStringUtil.converToYYYY_MM_DD(this.hopeDt_sel);
        // 2014/04/22 Upd End   希望日をテキストからプルダウンへ変更対応

        ReservationCancellationCourseListSearchService service = new ReservationCancellationCourseListSearchService();

        // 戻る判定Onフラグ
        boolean returnOnFlg = false;

        if ((SessionReservationCancellationCourse) SessionDataUtil
                .getSessionData(SessionReservationCancellationCourse.class.toString()) != null) {

            // 戻る判定Onフラグ
            returnOnFlg = ((SessionReservationCancellationCourse) SessionDataUtil
                    .getSessionData(SessionReservationCancellationCourse.class.toString())).getReturnOnFlg();

            // 検索判断フラグ
            this.hasSearchFlg = ((SessionReservationCancellationCourse) SessionDataUtil
                    .getSessionData(SessionReservationCancellationCourse.class.toString())).getHasSearchFlg();
        }

        try {
            // 検索前チェック
            if (!returnOnFlg) {
                if (!service.hopeTimeCheck(this.model)) {
                    // メッセージ情報を設定する
                    this.addActionMessage(getMessage("EC0011", new String[] { "希望時刻（From）", "希望時刻（To）" }));
                    return SUCCESS;
                }

                // 2014/04/22 Del Start 希望日をテキストからプルダウンへ変更対応
/*
                // 予約期間は4週間先まで。希望日がそれ以上の場合はエラーとする
                boolean chkRtn = DateUtil.dateCompare3(NaikaraStringUtil.converToYYYYMMDD(this.hopeDt_txt), DateUtil.getSysDateAddDay(28));
                if (chkRtn) {
                    // メッセージ情報を設定する(%1は%2で入力してください。)
                    this.addActionMessage(getMessage("EC0009", new String[] { "希望日", "４週間以内" }));
                    return SUCCESS;
                }
*/
                // 2014/04/22 Del End   希望日をテキストからプルダウンへ変更対応


            }

            // ヘッダ部の戻るボタン押下の場合は値の入れ替え処理
            this.SessionReservationCancellationCourseToModelBefore();

            if (!returnOnFlg || StringUtils.equals(NaikaraTalkConstants.TRUE, this.hasSearchFlg)) {

                // 講師予定予約テーブル情報検索処理
                List<SchResTCouMUsrMTeaMCouUsePoiMTeaCouMLesResPerTDto> retDtoList = service
                        .selectScheduleReservationInfoList(this.model);

                // 件数のチェック
                // データ件数 ≦ 0件の場合
                if (retDtoList.get(0).getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_NO) {

                    // メッセージ情報を設定する
                    this.addActionMessage(getMessage("EC0020", new String[] {}));
                    return SUCCESS;
                }
                // 上限件数以上の場合
                if (retDtoList.size() > LIST_MAX_CNT) {

                    int cnt = LIST_MAX_CNT + 1;
                    // メッセージ情報を設定する
                    this.addActionMessage(getMessage("EC0023", new String[] { String.valueOf(cnt) }));
                    return SUCCESS;
                }

                // 画面表示データ設定
                this.model.setResultList(retDtoList);

                this.hasSearchFlg = Boolean.toString(Boolean.TRUE);

                // 選択したラジオボタンをクリアする
                this.select_rdl = NaikaraTalkConstants.BRANK;

                // ヘッダ部の戻るボタン押下の場合は値の入れ替え処理
                this.SessionReservationCancellationCourseToModelAfter();

                // 戻る用に必要な情報を格納
                this.modelToSessionReservationCancellationCourse();
            }
        } catch (Exception e) {
            throw new NaiException(e);
        }

        // 画面を返す
        return SUCCESS;
    }

    /**
     * Model値 To Session<br>
     * <br>
     * Model値・SessionReservationCancellationCourse値をSessionReservationCancellationCourseにセット。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    private void modelToSessionReservationCancellationCourse() throws Exception {

        // 戻る用に必要な情報を取得
        SessionReservationCancellationCourse sessionData = new SessionReservationCancellationCourse();
        // 戻る判定Onフラグ=Offとする
        sessionData.setReturnOnFlg(false);

        // 検索Key：希望日
        sessionData.setSearchHopeDt(this.model.getHopeDt());
        // 検索Key：希望時刻(From)
        sessionData.setSearchHopeTimeFr(this.model.getHopeTimeFr());
        // 検索Key：希望時刻(To)
        sessionData.setSearchHopeTimeTo(this.model.getHopeTimeTo());
        // 検索Key：予約状況
        sessionData.setSearchReservationKbn(this.model.getReservationKbn());
        // 検索Key：性別
        sessionData.setSearchSexKbn(this.model.getSexKbn());
        // 検索Key：コースコード
        sessionData.setSearchCourseCode(this.model.getCourseCode());
        // 検索Key：大分類
        sessionData.setSearchCourseLarge(this.model.getCourseLarge());
        // 検索Key：中分類
        sessionData.setSearchCourseMedium(this.model.getCourseMedium());
        // 検索Key：小分類
        sessionData.setSearchCourseSmall(this.model.getCourseSmall());
        // 検索Key：コース名：キーワード
        sessionData.setSearchCourseName(this.model.getCourseName());

        SessionDataUtil.setSessionData(sessionData);
    }

    /**
     * 検索する前 Session値 To Model<br>
     * <br>
     * SessionReservationCancellationCourse値をModelにセット。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    private void SessionReservationCancellationCourseToModelBefore() throws Exception {

        if ((SessionReservationCancellationCourse) SessionDataUtil
                .getSessionData(SessionReservationCancellationCourse.class.toString()) != null) {

            boolean returnOnFlg = ((SessionReservationCancellationCourse) SessionDataUtil
                    .getSessionData(SessionReservationCancellationCourse.class.toString())).getReturnOnFlg(); // 戻る判定Onフラグ

            if (returnOnFlg == true) {
                // 希望日
                String searchHopeDt = ((SessionReservationCancellationCourse) SessionDataUtil
                        .getSessionData(SessionReservationCancellationCourse.class.toString())).getSearchHopeDt();
                // 希望時刻(From)
                String searchHopeTimeFr = ((SessionReservationCancellationCourse) SessionDataUtil
                        .getSessionData(SessionReservationCancellationCourse.class.toString())).getSearchHopeTimeFr();
                // 希望時刻(To)
                String searchHopeTimeTo = ((SessionReservationCancellationCourse) SessionDataUtil
                        .getSessionData(SessionReservationCancellationCourse.class.toString())).getSearchHopeTimeTo();
                // 予約状況
                String searchReservationKbn = ((SessionReservationCancellationCourse) SessionDataUtil
                        .getSessionData(SessionReservationCancellationCourse.class.toString()))
                        .getSearchReservationKbn();
                // 性別
                String searchSexKbn = ((SessionReservationCancellationCourse) SessionDataUtil
                        .getSessionData(SessionReservationCancellationCourse.class.toString())).getSearchSexKbn();
                // コースコード
                String searchCourseCode = ((SessionReservationCancellationCourse) SessionDataUtil
                        .getSessionData(SessionReservationCancellationCourse.class.toString())).getSearchCourseCode();
                // 大分類
                String searchCourseLarge = ((SessionReservationCancellationCourse) SessionDataUtil
                        .getSessionData(SessionReservationCancellationCourse.class.toString())).getSearchCourseLarge();
                // 中分類
                String searchCourseMedium = ((SessionReservationCancellationCourse) SessionDataUtil
                        .getSessionData(SessionReservationCancellationCourse.class.toString())).getSearchCourseMedium();
                // 小分類
                String searchCourseSmall = ((SessionReservationCancellationCourse) SessionDataUtil
                        .getSessionData(SessionReservationCancellationCourse.class.toString())).getSearchCourseSmall();
                // コース名：キーワード
                String searchCourseName = ((SessionReservationCancellationCourse) SessionDataUtil
                        .getSessionData(SessionReservationCancellationCourse.class.toString())).getSearchCourseName();

                // 検索Key：希望日
                this.model.setHopeDt(searchHopeDt);
                // 検索Key：希望時刻(From)
                this.model.setHopeTimeFr(searchHopeTimeFr);
                // 検索Key：希望時刻(To)
                this.model.setHopeTimeTo(searchHopeTimeTo);
                // 検索Key：予約状況
                this.model.setReservationKbn(searchReservationKbn);
                // 検索Key：性別
                this.model.setSexKbn(searchSexKbn);
                // 検索Key：コースコード
                this.model.setCourseCode(searchCourseCode);
                // 検索Key：大分類
                this.model.setCourseLarge(searchCourseLarge);
                // 検索Key：中分類
                this.model.setCourseMedium(searchCourseMedium);
                // 検索Key：小分類
                this.model.setCourseSmall(searchCourseSmall);
                // 検索Key：コース名：キーワード
                this.model.setCourseName(searchCourseName);
            }
        }
    }

    /**
     * 検索の後 Session値 To Model<br>
     * <br>
     * SessionReservationCancellationCourse値をModelにセット。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    private void SessionReservationCancellationCourseToModelAfter() throws Exception {

        if ((SessionReservationCancellationCourse) SessionDataUtil
                .getSessionData(SessionReservationCancellationCourse.class.toString()) != null) {
            // 戻る判定Onフラグ
            boolean returnOnFlg = ((SessionReservationCancellationCourse) SessionDataUtil
                    .getSessionData(SessionReservationCancellationCourse.class.toString())).getReturnOnFlg();

            if (returnOnFlg == true) {
                // 希望日
                String hopeDt = ((SessionReservationCancellationCourse) SessionDataUtil
                        .getSessionData(SessionReservationCancellationCourse.class.toString())).getHopeDt();
                // 希望時刻(From)
                String hopeTimeFr = ((SessionReservationCancellationCourse) SessionDataUtil
                        .getSessionData(SessionReservationCancellationCourse.class.toString())).getHopeTimeFr();
                // 希望時刻(To)
                String hopeTimeTo = ((SessionReservationCancellationCourse) SessionDataUtil
                        .getSessionData(SessionReservationCancellationCourse.class.toString())).getHopeTimeTo();
                // 予約状況
                String reservationKbn = ((SessionReservationCancellationCourse) SessionDataUtil
                        .getSessionData(SessionReservationCancellationCourse.class.toString())).getReservationKbn();
                // 性別
                String sexKbn = ((SessionReservationCancellationCourse) SessionDataUtil
                        .getSessionData(SessionReservationCancellationCourse.class.toString())).getSexKbn();
                // コースコード
                String courseCode = ((SessionReservationCancellationCourse) SessionDataUtil
                        .getSessionData(SessionReservationCancellationCourse.class.toString())).getCourseCode();
                // 大分類
                String courseLarge = ((SessionReservationCancellationCourse) SessionDataUtil
                        .getSessionData(SessionReservationCancellationCourse.class.toString())).getCourseLarge();
                // 中分類
                String courseMedium = ((SessionReservationCancellationCourse) SessionDataUtil
                        .getSessionData(SessionReservationCancellationCourse.class.toString())).getCourseMedium();
                // 小分類
                String courseSmall = ((SessionReservationCancellationCourse) SessionDataUtil
                        .getSessionData(SessionReservationCancellationCourse.class.toString())).getCourseSmall();
                // コース名：キーワード
                String courseName = ((SessionReservationCancellationCourse) SessionDataUtil
                        .getSessionData(SessionReservationCancellationCourse.class.toString())).getCourseName();
                // 検索Key：選択された明細のKey-講師ID
                String select_rdl = ((SessionReservationCancellationCourse) SessionDataUtil
                        .getSessionData(SessionReservationCancellationCourse.class.toString())).getSearchTeacherIdKey();

                // 2014/04/22 Upd Start 希望日をテキストからプルダウンへ変更対応
                // 検索Key：希望日
                //this.hopeDt_txt = hopeDt;
                this.hopeDt_sel = hopeDt;
                // 2014/04/22 Upd End   希望日をテキストからプルダウンへ変更対応

                // 検索Key：希望時刻(From)
                this.hopeTimeFr_sel = hopeTimeFr;
                // 検索Key：希望時刻(To)
                this.hopeTimeTo_sel = hopeTimeTo;
                // 検索Key：予約状況
                this.reservationKbn_rdl = reservationKbn;
                // 検索Key：性別
                this.sexKbn_rdl = sexKbn;
                // 検索Key：コースコード
                this.courseCode_txt = courseCode;
                // 検索Key：大分類
                this.courseLarge_sel = courseLarge;
                // 検索Key：中分類
                this.courseMedium_sel = courseMedium;
                // 検索Key：小分類
                this.courseSmall_sel = courseSmall;
                // 検索Key：コース名：キーワード
                this.courseName_txt = courseName;
                this.select_rdl = select_rdl;
            }

            // SessionReservationCancellationCourseのクリア
            SessionDataUtil.clearSessionData(SessionReservationCancellationCourse.class.toString());
        }
    }
}
