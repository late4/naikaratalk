package com.naikara_talk.action;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dto.LessonReserveCancelResultListDto;
import com.naikara_talk.dto.SchResTLesResPerTDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.ReservationCancellationCourseSelectionListMoveService;
import com.naikara_talk.sessiondata.SessionReservationCancellationCourseSelection;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_予約処理<br>
 * <b>クラス名称       :</b>コース名選択ページ画面遷移Actionクラス<br>
 * <b>クラス概要       :</b>受講者が指定した講師の予約スケジュールを表示して、レッスン予約の登録／取消ができる。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/29 TECS 新規作成
 * <b>                 :</b>2014/06/02 TECS レッスン予約に関する「応相談」対応
 */
public class ReservationCancellationCourseSelectionListMoveAction extends
        ReservationCancellationCourseSelectionListActionSupport {

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

        try {
            this.setupModel();

            // セレクトボックス取得
            this.initSelect();

            // コースの設定
            this.setCourse();

            ReservationCancellationCourseSelectionListMoveService service = new ReservationCancellationCourseSelectionListMoveService();

            ////////////////////////////////////////////
            SessionUser SessionUserData = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString()));
            String studentId = "system";
            if (SessionUserData != null) {
                // ユーザIDを取得
                studentId = SessionUserData.getUserId();
            }
            ////////////////////////////////////////////

            // 引当データリストの取得
            this.ppdlDtoList = service.getPointProvisionDataList(studentId, this.teacherId, this.schResTLesResPerTDtoList);

            // 解除データリストの取得
            this.prdlDtoList = service.getPointReleaseDataList(studentId, this.teacherId, this.schResTLesResPerTDtoList);

            // エラーフラグ
            boolean errFlg = false;

            // コースが選択なしのチェック
            int chkResult = service.checkCourseChoiceBrank(this.model);

            if (chkResult != ReservationCancellationCourseSelectionListMoveService.CHECK_OK) {

                errFlg = true;

                // メッセージ情報を設定する
                this.addActionMessage(getMessage("EC0015",
                        new String[] { new StringBuffer().append("コース名 (").append(chkResult).append("行目 )").toString(),
                                "予約をしない明細の場合は、｢予約中止｣も選択できます。" }));
            }

            if (!errFlg) {
                // 全てのコースが｢予約中止｣のチェック
                if (service.checkCourseChoiceAllBreak(model)) {

                    errFlg = true;
                    // メッセージ情報を設定する
                    this.addActionMessage(getMessage("EC0015", new String[] { "コース名",
                            "全てのコースが｢予約中止｣でポイントチェック/確認へに、遷移することはできません。" }));
                }
            }

            if (!errFlg) {

                for (SchResTLesResPerTDto dto : this.schResTLesResPerTDtoList) {

                    // 業務チェック
                    chkResult = service.serviceCheck(this.teacherId, dto);

                    switch (chkResult) {
                    case ReservationCancellationCourseSelectionListMoveService.ERR_LESSON_TM:

                        errFlg = true;
                        // メッセージ情報を設定する
                        this.addActionMessage(getMessage(
                                "EC0067",
                                new String[] { service.getLessonDateTime(
                                        NaikaraStringUtil.converToM_D(dto.getLessonDt()), dto.getLessonTm(), false) }));
                        break;

                        // 2014/06/02 レッスン予約に関する「応相談」対応 Add Start
                    case ReservationCancellationCourseSelectionListMoveService.ERR_LESSON_TM_CON:

                        errFlg = true;
                        // ◆◆◆10-XX.予約可能/予約取消可能かどうかのチェック 応相談予約の場合 ◆◆◆
                        // メッセージ情報を設定する
                        // %1分の予約受付は終了しております。応相談のご予約はレッスン開始３時間前にお願いいたします。
                        this.addActionMessage(getMessage(
                                "EC0079",
                                new String[] { service.getLessonDateTime(
                                        NaikaraStringUtil.converToM_D(dto.getLessonDt()), dto.getLessonTmCd(), false) }));
                        break;
                        // 2014/06/02 レッスン予約に関する「応相談」対応 Add Start

                    case ReservationCancellationCourseSelectionListMoveService.ERR_LESSON_TM2:

                        errFlg = true;
                        // メッセージ情報を設定する
                        this.addActionMessage(getMessage(
                                "EC0047",
                                new String[] { service.getLessonDateTime(
                                        NaikaraStringUtil.converToM_D(dto.getLessonDt()), dto.getLessonTm(), false) }));
                        break;

                    case ReservationCancellationCourseSelectionListMoveService.ERR_COURSE_CD:

                        errFlg = true;
                        // メッセージ情報を設定する
                        this.addActionMessage(getMessage(
                                "EC0061",
                                new String[] {
                                        new StringBuffer().append(dto.getCourseNm()).append("（提供期間：")
                                                .append(dto.getLessonDtFr()).append("-").append(dto.getLessonDtTo())
                                                .toString(), "他のコース名" }));
                        break;

                    case ReservationCancellationCourseSelectionListMoveService.ERR_COURSE_CD2:

                        errFlg = true;
                        // メッセージ情報を設定する
                        this.addActionMessage(getMessage("EC0061", new String[] { dto.getCourseNm(), "他のコース名" }));
                        break;

                    case ReservationCancellationCourseSelectionListMoveService.ERR_RESERV_KBN_NG:

                        errFlg = true;
                        // メッセージ情報を設定する
                        this.addActionMessage(getMessage(
                                "EC0048",
                                new String[] { service.getLessonDateTime(
                                        NaikaraStringUtil.converToM_D(dto.getLessonDt()), dto.getLessonTm(), false) }));
                        break;

                    case ReservationCancellationCourseSelectionListMoveService.ERR_RESERV_KBN_ALREADY:

                        errFlg = true;
                        // メッセージ情報を設定する
                        this.addActionMessage(getMessage(
                                "EC0068",
                                new String[] { service.getLessonDateTime(
                                        NaikaraStringUtil.converToM_D(dto.getLessonDt()), dto.getLessonTm(), false) }));
                        break;

                    case ReservationCancellationCourseSelectionListMoveService.ERR_RETURN_CD_DATA_NO:

                        errFlg = true;
                        // メッセージ情報を設定する
                        this.addActionMessage(getMessage("EC0020", new String[] {}));
                        break;


                    case ReservationCancellationCourseSelectionListMoveService.ERR_RESERV_SAME_DT_AND_TM:

                        errFlg = true;
                        StringBuffer sbMsg = new StringBuffer();
                        sbMsg.append("予約日時");
                        // メッセージ情報を設定する(既に登録されている%1です。%2を変更してください。)
                        this.addActionMessage(getMessage("EC0059", new String[] {sbMsg.toString(), sbMsg.toString()}));
                        break;

                    }

                    if (errFlg) {
                        break;
                    }
                }
            }

            if (!errFlg) {

                // ポイント引当・解除の確認チェック
                List<LessonReserveCancelResultListDto> retDtoList = service.pointCheck(this.ppdlDtoList,
                        this.prdlDtoList);

                // ポイント残高の取得エラーフラグ
                boolean ownTrnFlg = true;
                // ポイントの取得エラーフラグ
                boolean proTrnFlg = true;
                // ポイント引当テーブルなしフラグ
                boolean relFlg = true;
                // ポイント不足エラーフラグ
                boolean proFlg = true;
                // ポイント引当エラーフラグ
                boolean useFlg = true;

                for (LessonReserveCancelResultListDto retDto : retDtoList) {

                    switch (retDto.getReturnCode()) {
                    case ReservationCancellationCourseSelectionListMoveService.POINT_OWNERSHIP_TRN_NOTHING:
                        if (ownTrnFlg) {
                            errFlg = true;
                            // メッセージ情報を設定する
                            this.addActionMessage(getMessage("EC0042", new String[] { "ポイント残高の取得処理" }));
                        }
                        ownTrnFlg = false;
                        break;

                    case ReservationCancellationCourseSelectionListMoveService.POINT_PROVISION_TRN_NOTHING:

                        if (proTrnFlg) {
                            errFlg = true;
                            // メッセージ情報を設定する
                            this.addActionMessage(getMessage("EC0042", new String[] { "ポイントの取得処理" }));
                        }
                        proTrnFlg = false;
                        break;

                    case ReservationCancellationCourseSelectionListMoveService.POINT_RELEASE_ERROR:

                        if (relFlg) {
                            errFlg = true;
                            // メッセージ情報を設定する
                            this.addActionMessage(getMessage("EC0069", new String[] {}));
                        }
                        relFlg = false;
                        break;

                    case ReservationCancellationCourseSelectionListMoveService.POINT_PROVISION_ERROR:

                        if (proFlg) {
                            errFlg = true;
                            // メッセージ情報を設定する
                            this.addActionMessage(getMessage("EC0070", new String[] {}));
                        }
                        proFlg = false;
                        break;

                    case ReservationCancellationCourseSelectionListMoveService.USE_POINT_NULL:

                        if (useFlg) {
                            errFlg = true;
                            // メッセージ情報を設定する
                            this.addActionMessage(getMessage("EC0061", new String[] {
                                    this.schResTLesResPerTDtoList.get(0).getCourseNm(), "他のコース名" }));
                        }
                        useFlg = false;
                        break;
                    }
                }
            }

            // メッセージ情報に設定されている場合は、この画面にて、メッセージを表示する
            if (errFlg) {

                // 現ポイント残高の取得
                this.balancePointOld = service.getBalancePointOldSum();

                // ご利用ポイント合計の取得
                this.usePointSum = service.getUserPointSum(this.model.getResultList());

                // お取消ポイント合計の取得
                this.cancelPointSum = service.getCancelPointSum(this.model.getResultList());

                // 新ポイント残高の取得
                this.balancePointNew = service.getBalancePointNewSum(this.balancePointOld, this.usePointSum,
                        this.cancelPointSum);

                return SUCCESS;
            }

            // 戻る用に必要な情報を取得/格納
            this.modelToSessionReservationCancellationCourseSelection();

        } catch (Exception e) {
            throw new NaiException(e);
        }

        // ヘッダの戻るリンク用
        setCurrentActionName(NaikaraTalkConstants.RESERVATION_CANCELLATION_COURSE_SELECTION_LIST_LOAD);

        return NEXTPAGE;
    }

    /**
     * コースの設定。<br>
     * <br>
     * コースの設定を行う。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @throws なし
     */
    private void setCourse() {

        if (this.schResTLesResPerTDtoList != null) {

            int i = 0;

            for (SchResTLesResPerTDto dto : this.schResTLesResPerTDtoList) {

                // 隠し項目：予約No＝””の場合
                if (StringUtils.isEmpty(dto.getReservationNo())
                        || StringUtils.equals(NaikaraTalkConstants.RESERVATION_NO_NULL, dto.getReservationNo())) {

                    if (this.course_sel != null && i < this.course_sel.length) {

                        String[] selected = this.course_sel[i].substring(1, this.course_sel[i].length() - 1).split(
                                String.valueOf(NaikaraTalkConstants.COMMA));

                        // コースコードの取得
                        dto.setCourseCd(selected[0]);
                        // コース名の取得
                        dto.setCourseNm(selected[1]);

                        i++;
                    }
                } else {
                    // コース名を取得
                    dto.setCourseNm(this.course_sell.get(dto.getCourseCd()));
                }
            }
        }
    }

    /**
     * Model値 To Session<br>
     * <br>
     * Model値をSessionReservationCancellationCourseSelectionにセット。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    private void modelToSessionReservationCancellationCourseSelection() throws Exception {

        // 戻る用に必要な情報を格納
        SessionReservationCancellationCourseSelection sessionData = new SessionReservationCancellationCourseSelection();

        // 講師ID
        sessionData.setTeacherId(this.teacherId);
        // 講師名
        sessionData.setTeacherNm(this.teacherNm);
        // 選択のコース
        sessionData.setCourse(this.course_sel);
        // 一覧のDTOリスト
        sessionData.setSchResTLesResPerTDtoList(this.model.getResultList());

        SessionDataUtil.setSessionData(sessionData);
    }
}
