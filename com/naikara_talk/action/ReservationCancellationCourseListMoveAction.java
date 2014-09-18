package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dto.SchResTLesResPerTDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.ReservationCancellationCourseListMoveService;
import com.naikara_talk.service.ReservationCancellationCourseListSearchService;
//import com.naikara_talk.service.ReservationCancellationTeacherScheduleMoveService;
import com.naikara_talk.sessiondata.SessionReservationCancellationCourse;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_予約処理<br>
 * <b>クラス名称       :</b>予約申込ページ画面遷移Actionクラス<br>
 * <b>クラス概要       :</b>受講者がレッスンを予約登録、取消ができる。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/18 TECS 新規作成
 * <b>                 :</b>2014/04/22 TECS 希望日をテキストからプルダウンへ変更対応
 * <b>                 :</b>2014/06/02 TECS レッスン予約に関する「応相談」対応
 */
public class ReservationCancellationCourseListMoveAction extends ReservationCancellationCourseListActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 画面遷移前処理。<br>
     * <br>
     * 画面遷移前処理を行う。<br>
     * <br>
     * @param なし<br>
     * @return String<br>
     * @throws NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // 一覧から選択された明細データの取得
        this.load();

        // 画面のパラメータセット
        setupModel();

        ReservationCancellationCourseListMoveService service = new ReservationCancellationCourseListMoveService();

        // チェック
        try {
            boolean errFlg = false;

            // 必須入力チェック　と　予約可能／予約取消可能かどうかのチェック　と　予約状態のチェック
            int chkResult = service.nextPageRequest(this.model, this.getSelect_rdl(), this.getHasSearchFlg());

            // エラーの場合、メッセージ設定
            switch (chkResult) {
            case ReservationCancellationCourseListMoveService.ERR_NO_SELECT:

                errFlg = true;
                // メッセージ情報を設定する
                this.addActionMessage(getMessage("EC0015", new String[] { "一覧部の左の選択" }));
                break;

            case ReservationCancellationCourseListMoveService.ERR_LIST_BACK:
                // メッセージ情報を設定する
                this.addActionMessage(getMessage("EC0015", new String[] { "一覧部の左の選択" }));
                return SUCCESS;

            case ReservationCancellationCourseListMoveService.ERR_LESSON_TM:

                errFlg = true;
                // メッセージ情報を設定する
                this.addActionMessage(getMessage("EC0067",
                        new String[] { service.getLessonDateTime(this.model, false) }));
                break;

                // 2014/06/02 レッスン予約に関する「応相談」対応 Add Start
            case ReservationCancellationCourseListMoveService.ERR_LESSON_TM_CON:

                errFlg = true;
                // ◆◆◆10-XX.予約可能/予約取消可能かどうかのチェック 応相談予約の場合 ◆◆◆
                // メッセージ情報を設定する
                // %1分の予約受付は終了しております。応相談のご予約はレッスン開始３時間前にお願いいたします。
                this.addActionMessage(getMessage("EC0079",
                        new String[] { service.getLessonDateTime(this.model, false) }));
                break;
                // 2014/06/02 レッスン予約に関する「応相談」対応 Add Start

            case ReservationCancellationCourseListMoveService.ERR_LESSON_TM2:

                errFlg = true;
                // メッセージ情報を設定する
                this.addActionMessage(getMessage("EC0047",
                        new String[] { service.getLessonDateTime(this.model, false) }));
                break;

            case ReservationCancellationCourseListMoveService.ERR_RESERV_KBN_NG:

                errFlg = true;
                // メッセージ情報を設定する
                this.addActionMessage(getMessage("EC0048",
                        new String[] { service.getLessonDateTime(this.model, false) }));
                break;

            case ReservationCancellationCourseListMoveService.ERR_RESERV_KBN_ALREADY:

                errFlg = true;
                // メッセージ情報を設定する
                this.addActionMessage(getMessage("EC0068",
                        new String[] { service.getLessonDateTime(this.model, false) }));
                break;

            case ReservationCancellationCourseListMoveService.ERR_RETURN_CD_DATA_NO:

                errFlg = true;
                // メッセージ情報を設定する
                this.addActionMessage(getMessage("EC0020", new String[] {}));
                break;

            case ReservationCancellationCourseListMoveService.ERR_RESERV_SAME_DT_AND_TM:

                errFlg = true;
                StringBuffer sbMsg = new StringBuffer();
                sbMsg.append("予約日時");
                // メッセージ情報を設定する(既に登録されている%1です。%2を変更してください。)
                this.addActionMessage(getMessage("EC0059", new String[] {sbMsg.toString(), sbMsg.toString()}));
                break;

            }

            if (!errFlg) {

                // 引当データリストの取得
                this.ppdlDtoList = service.getPointProvisionDataList(this.model);

                // 解除データリストの取得
                this.prdlDtoList = service.getPointReleaseDataList(this.model);

                // ポイントの引当・解除処理チェック
                chkResult = service.pointCheck(this.ppdlDtoList, this.prdlDtoList);

                // エラーの場合、メッセージ設定
                switch (chkResult) {
                case ReservationCancellationCourseListMoveService.POINT_OWNERSHIP_TRN_NOTHING:

                    errFlg = true;
                    // メッセージ情報を設定する
                    this.addActionMessage(getMessage("EC0042", new String[] { "ポイント残高の取得処理" }));
                    break;

                case ReservationCancellationCourseListMoveService.POINT_PROVISION_TRN_NOTHING:

                    errFlg = true;
                    // メッセージ情報を設定する
                    this.addActionMessage(getMessage("EC0042", new String[] { "ポイントの取得処理" }));
                    break;

                case ReservationCancellationCourseListMoveService.POINT_RELEASE_ERROR:

                    errFlg = true;
                    // メッセージ情報を設定する
                    this.addActionMessage(getMessage("EC0069", new String[] {}));
                    break;

                case ReservationCancellationCourseListMoveService.POINT_PROVISION_ERROR:

                    errFlg = true;
                    // メッセージ情報を設定する
                    this.addActionMessage(getMessage("EC0070", new String[] {}));
                    break;
                }
            }

            if (errFlg) {
                ReservationCancellationCourseListSearchService searchService = new ReservationCancellationCourseListSearchService();

                // 表示データの取得
                this.model.setResultList(searchService.selectScheduleReservationInfoList(this.model));

                this.hasSearchFlg = NaikaraTalkConstants.TRUE;
                return SUCCESS;
            }

            // 戻る用に必要な情報を取得/格納
            this.modelToSessionReservationCancellationCourse();

        } catch (Exception e) {
            throw new NaiException(e);
        }

        // ヘッダの戻るリンク用
        setCurrentActionName(NaikaraTalkConstants.RESERVATION_CANCELLATIONCOURSE_LIST_SEARCH);

        return NEXTPAGE;
    }

    /**
     * 一覧から選択された明細データの取得。<br>
     * <br>
     * 一覧から選択された明細データの取得。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @throws Exception
     */
    private void load() {
        if (!StringUtils.isEmpty(this.select_rdl)) {
            String[] selected = this.getSelect_rdl().substring(1, this.getSelect_rdl().length() - 1)
                    .replaceAll(NaikaraTalkConstants.HALF_SPACE, NaikaraTalkConstants.BRANK)
                    .split(String.valueOf(NaikaraTalkConstants.COMMA));
            // 一覧選択の講師ID
            this.teacherId = selected[0];
            // 一覧選択の講師名
            this.teacherNm = selected[1];

            SchResTLesResPerTDto dto = new SchResTLesResPerTDto();

            // 2014/04/22 Upd Start 希望日をテキストからプルダウンへ変更対応
            // (検索条件部) 希望日
            //dto.setLessonDt(this.hopeDt_txt);
            dto.setLessonDt(this.hopeDt_sel);
            // 2014/04/22 Upd End   希望日をテキストからプルダウンへ変更対応

            // 一覧選択の予約No
            dto.setReservationNo(selected[2]);
            // 一覧選択の予約状況
            dto.setReservationKbn(selected[3]);
            // 一覧選択の時刻コード
            dto.setLessonTmCd(selected[4]);
            // 一覧選択の時刻
            dto.setLessonTm(selected[5]);
            // 一覧選択のコースコード
            dto.setCourseCd(selected[6]);
            // 一覧選択のコース名
            dto.setCourseNm(selected[7]);
            // 一覧選択のコースポイント
            dto.setUsePoint(selected[8]);
            // 一覧選択のコースポイント
            dto.setCancelPoint(selected[8]);
            // 一覧選択のレコードバージョン番号1
            dto.setRecordVerNo1(Integer.parseInt(selected[9]));
            // 一覧選択のレコードバージョン番号2
            dto.setRecordVerNo2(Integer.parseInt(selected[10]));

            this.schResTLesResPerTDtoList.add(dto);
        }
    }

    /**
     * Model値 To Session<br>
     * <br>
     * Model値をSessionReservationCancellationCourseにセット。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    private void modelToSessionReservationCancellationCourse() throws Exception {

        // 戻る用に必要な情報を格納
        SessionReservationCancellationCourse sessionData = new SessionReservationCancellationCourse();

        if ((SessionReservationCancellationCourse) SessionDataUtil
                .getSessionData(SessionReservationCancellationCourse.class.toString()) != null) {
            // 戻る用に必要な情報を取得
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
                    .getSessionData(SessionReservationCancellationCourse.class.toString())).getSearchReservationKbn();
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
            sessionData.setSearchHopeDt(searchHopeDt);
            // 検索Key：希望時刻(From)
            sessionData.setSearchHopeTimeFr(searchHopeTimeFr);
            // 検索Key：希望時刻(To)
            sessionData.setSearchHopeTimeTo(searchHopeTimeTo);
            // 検索Key：予約状況
            sessionData.setSearchReservationKbn(searchReservationKbn);
            // 検索Key：性別
            sessionData.setSearchSexKbn(searchSexKbn);
            // 検索Key：コースコード
            sessionData.setSearchCourseCode(searchCourseCode);
            // 検索Key：大分類
            sessionData.setSearchCourseLarge(searchCourseLarge);
            // 検索Key：中分類
            sessionData.setSearchCourseMedium(searchCourseMedium);
            // 検索Key：小分類
            sessionData.setSearchCourseSmall(searchCourseSmall);
            // 検索Key：コース名：キーワード
            sessionData.setSearchCourseName(searchCourseName);
        }

        // 戻る判定Onフラグ
        sessionData.setReturnOnFlg(true);
        // 希望日
        sessionData.setHopeDt(this.model.getHopeDt());
        // 希望時刻(From)
        sessionData.setHopeTimeFr(this.model.getHopeTimeFr());
        // 希望時刻(To)
        sessionData.setHopeTimeTo(this.model.getHopeTimeTo());
        // 予約状況
        sessionData.setReservationKbn(this.model.getReservationKbn());
        // 性別
        sessionData.setSexKbn(this.model.getSexKbn());
        // コースコード
        sessionData.setCourseCode(this.model.getCourseCode());
        // 大分類
        sessionData.setCourseLarge(this.model.getCourseLarge());
        // 中分類
        sessionData.setCourseMedium(this.model.getCourseMedium());
        // 小分類
        sessionData.setCourseSmall(this.model.getCourseSmall());
        // コース名：キーワード
        sessionData.setCourseName(this.model.getCourseName());
        // 検索Key：選択された明細のKey-商品コード
        sessionData.setSearchTeacherIdKey(this.getSelect_rdl());
        // 検索判断フラグ
        sessionData.setHasSearchFlg(this.getHasSearchFlg());

        SessionDataUtil.setSessionData(sessionData);

    }
}
