package com.naikara_talk.action;

import java.util.List;
import java.util.Map;

import com.naikara_talk.dto.LessonReserveCancelResultListDto;
import com.naikara_talk.dto.SchResTLesResPerTDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.ReservationCancellationCourseSelectionListMoveService;
//import com.naikara_talk.service.ReservationCancellationTeacherScheduleMoveService;
import com.naikara_talk.service.SComReservationCancellationConfirmationSendService;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_予約処理<br>
 * <b>クラス名称       :</b>予約／取消確認ページメール送信Actionクラス<br>
 * <b>クラス概要       :</b>受講者が指定した講師の予約スケジュールを表示して、レッスン予約の登録／取消ができる。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/02 TECS 新規作成
 * <b>                 :</b>2014/06/02 TECS レッスン予約に関する「応相談」対応
 */
public class SComReservationCancellationConfirmationSendAction extends
        SComReservationCancellationConfirmationActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 画面の初期表示。<br>
     * <br>
     * 画面の初期表示を設定する。<br>
     * <br>
     * @param なし<br>
     * @return String<br>
     * @throws NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        ReservationCancellationCourseSelectionListMoveService courseService = new ReservationCancellationCourseSelectionListMoveService();

        ////////////////////////////////////////////
        SessionUser SessionUserData = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString()));
        String studentId = "system";
        if (SessionUserData != null) {
            // ユーザIDを取得
            studentId = SessionUserData.getUserId();
        }
        ////////////////////////////////////////////

        // 引当データリストの取得
        this.ppdlDtoList = courseService.getPointProvisionDataList(studentId, this.teacherId, this.schResTLesResPerTDtoList);

        // 解除データリストの取得
        this.prdlDtoList = courseService.getPointReleaseDataList(studentId, this.teacherId, this.schResTLesResPerTDtoList);

        this.setupModel();

        try {
            ReservationCancellationCourseSelectionListMoveService service = new ReservationCancellationCourseSelectionListMoveService();

            // エラーフラグ
            boolean errFlg = false;

            // 画面上の一覧の件数分を繰り返す
            for (SchResTLesResPerTDto dto : this.schResTLesResPerTDtoList) {

                // 業務チェック
                int chkResult = service.serviceCheck(this.teacherId, dto);

                switch (chkResult) {
                case ReservationCancellationCourseSelectionListMoveService.ERR_LESSON_TM:

                    errFlg = true;
                    // メッセージ情報を設定する
                    this.addActionMessage(getMessage("EC0067", new String[] { service.getLessonDateTime(
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

                case ReservationCancellationCourseSelectionListMoveService.ERR_MAIL_ADDRESS_CON_PROVISIONAL_RESERV:
                    errFlg = true;
                    // ◆◆◆10-XX.受講者のメールアドレスが設定されているかどうかのチェック(法人対策) 応相談予約の場合 ◆◆◆
                    // ※※※ 通常の予約の場合は、下記のメッセージは警告レベルだが、応相談予約はエラーとする ※※※
                    // メッセージ情報を設定する
                    // メールアドレスが設定されていないため応相談予約ができません。マイページのプロフィールよりメールアドレスをご登録ください。
                    this.addActionMessage(getMessage("EC0080", new String[] {}));

                    break;
                // 2014/06/02 レッスン予約に関する「応相談」対応 Add Start

                case ReservationCancellationCourseSelectionListMoveService.ERR_LESSON_TM2:

                    errFlg = true;
                    // メッセージ情報を設定する
                    this.addActionMessage(getMessage("EC0047", new String[] { service.getLessonDateTime(
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
                    this.addActionMessage(getMessage("EC0048", new String[] { service.getLessonDateTime(
                            NaikaraStringUtil.converToM_D(dto.getLessonDt()), dto.getLessonTm(), false) }));
                    break;

                case ReservationCancellationCourseSelectionListMoveService.ERR_RESERV_KBN_ALREADY:

                    errFlg = true;
                    // メッセージ情報を設定する
                    this.addActionMessage(getMessage("EC0068", new String[] { service.getLessonDateTime(
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

            if (!errFlg) {

                // ポイント引当・解除の確認チェック
                List<LessonReserveCancelResultListDto> retDtoList = service.pointCheck(this.ppdlDtoList,
                        this.prdlDtoList);

                for (LessonReserveCancelResultListDto retDto : retDtoList) {

                    switch (retDto.getReturnCode()) {
                    case ReservationCancellationCourseSelectionListMoveService.POINT_OWNERSHIP_TRN_NOTHING:

                        errFlg = true;
                        // メッセージ情報を設定する
                        this.addActionMessage(getMessage("EC0042", new String[] { "ポイント残高の取得処理" }));
                        break;

                    case ReservationCancellationCourseSelectionListMoveService.POINT_PROVISION_TRN_NOTHING:

                        errFlg = true;
                        // メッセージ情報を設定する
                        this.addActionMessage(getMessage("EC0042", new String[] { "ポイントの取得処理" }));
                        break;

                    case ReservationCancellationCourseSelectionListMoveService.POINT_RELEASE_ERROR:

                        errFlg = true;
                        // メッセージ情報を設定する
                        this.addActionMessage(getMessage("EC0069", new String[] {}));
                        break;

                    case ReservationCancellationCourseSelectionListMoveService.POINT_PROVISION_ERROR:

                        errFlg = true;
                        // メッセージ情報を設定する
                        this.addActionMessage(getMessage("EC0070", new String[] {}));
                        break;
                    }
                }
            }

            if (!errFlg) {
                // ◆◆◆ 実テーブルへの予約・取消、ポイント引当・返却 等の処理とメール送信処理
                this.updateAndSendMail();

            } else {
                // ◆◆◆ ポイント合計の設定
                this.setPointSum();

                // 画面を返す
                return SUCCESS;

            }

            if (log.isDebugEnabled()) {
                log.debug("SComReservationCancellationConfirmationSendAction requestService End");
            }

        } catch (Exception e) {

            if (log.isDebugEnabled()) {
                log.debug("SComReservationCancellationConfirmationSendAction requestService Exception e.getMessage()=" + e.getMessage());
                log.debug("SComReservationCancellationConfirmationSendAction requestService Exception e.getStackTrace()=" + e.getStackTrace());
            }

            throw new NaiException(e);
        }

        if (log.isDebugEnabled()) {
            log.debug("SComReservationCancellationConfirmationSendAction requestService message.size=" + this.model.getMessageList().size() );
            log.debug("SComReservationCancellationConfirmationSendAction requestService message=" + this.model.getMessageList() );
            log.debug("SComReservationCancellationConfirmationSendAction requestService NEXTPAGE=" + NEXTPAGE);
        }

        // ◆◆◆ 画面を返す
        return NEXTPAGE;
    }

    /**
     * 実テーブルへの予約・取消、ポイント引当・返却 等の処理とメール送信処理<br>
     * <br>
     * 実テーブルへの予約・取消、ポイント引当・返却 等の処理とメール送信処理を行う。<br>
     * <br>
     * @param なし<br>
     * @return String<br>
     * @throws NaiException
     */
    public void updateAndSendMail() throws NaiException {

        SComReservationCancellationConfirmationSendService sendService = new SComReservationCancellationConfirmationSendService();

        try {
            // ◆◆◆レッスンの予約・取消処理◆◆◆
            int result = sendService.lessonReserveCancel(this.model);

            // 処理結果判定：全て異常ないの場合
            if (result != SComReservationCancellationConfirmationSendService.LESSON_WRONG_ALL) {

                if (log.isDebugEnabled()) {
                    log.debug("call sendService.sendMail before");
                }

                // ◆◆◆メール送信処理◆◆◆
                Map<Integer, Boolean> retMap = sendService.sendMail(this.model);

                if (log.isDebugEnabled()) {
                    log.debug("call sendService.sendMail after");
                }

                // メッセージのパラメタ
                String[] msgParams = new String[3];
                msgParams[0] = "レッスン予約又は取消";

                // ”予約”と”取消”の明細が存在する場合
                if (this.model.getReserveFlg() && this.model.getCancelFlg()) {
                    msgParams[0] = "レッスン予約・取消";

                    // ”予約”の明細のみが存在する場合
                } else if (this.model.getReserveFlg()) {
                    msgParams[0] = "レッスン予約";

                    // ”取消”の明細のみが存在する場合
                } else if (this.model.getCancelFlg()) {
                    msgParams[0] = "レッスン取消";
                }

                // ◆◆◆全て正常
                if (result == SComReservationCancellationConfirmationSendService.LESSON_RIGHT_ALL) {
                    msgParams[1] = "正常に";
                    msgParams[2] = "予約・取消されたレッスンはマイページの｢現在予約中のレッスン｣にてご確認ください。";

                    // ◆◆◆一部正常
                } else if (result == SComReservationCancellationConfirmationSendService.LESSON_RIGHT_SOME) {
                    msgParams[1] = "一部は正常に";
                    msgParams[2] = "講師の受付変更および他の受講者の予約により処理できなかったレッスンがございます。マイページの｢現在予約中のレッスン｣にてご確認ください。";
                }

                // メッセージのパタを追加
                this.model.getMessageList().add(getMessage("IC0016", msgParams));

                // チェック：データが存在しない
                if (retMap.get(SComReservationCancellationConfirmationSendService.ERR_RETURN_CD_DATA_NO)) {
                    // メッセージのパタを追加
                    this.model.getMessageList().add(getMessage("EC0020", new String[] {}));
                }

                // チェック：メールアドレスエラー
                if (retMap.get(SComReservationCancellationConfirmationSendService.ERR_MAIL_ADDRESS)) {
                    // メッセージのパタを追加
                    this.model.getMessageList().add(getMessage("EC0073", new String[] {}));
                }

                // メール送信エラー：レッスン予約：受講者
                if (retMap.get(SComReservationCancellationConfirmationSendService.ERR_SEND_MAIL_RESERVE_STUDENT)) {
                    // メッセージのパタを追加
                    this.model.getMessageList().add(getMessage("EC0042", new String[] { "メール送信(レッスン予約)" }));
                }

                // メール送信エラー：レッスン予約：講師
                if (retMap.get(SComReservationCancellationConfirmationSendService.ERR_SEND_MAIL_RESERVE_TEACHER)) {
                    // メッセージのパタを追加
                    this.model.getMessageList().add(getMessage("EC0042", new String[] { "メール送信(レッスン予約：講師宛)" }));
                }

                // メール送信エラー：レッスン取消：受講者
                if (retMap.get(SComReservationCancellationConfirmationSendService.ERR_SEND_MAIL_CANCEL_STUDENT)) {
                    // メッセージのパタを追加
                    this.model.getMessageList().add(getMessage("EC0042", new String[] { "メール送信(レッスン取消)" }));
                }

                // メール送信エラー：レッスン取消：講師
                if (retMap.get(SComReservationCancellationConfirmationSendService.ERR_SEND_MAIL_CANCEL_TEACHER)) {
                    // メッセージのパタを追加
                    this.model.getMessageList().add(getMessage("EC0042", new String[] { "メール送信(レッスン取消：講師宛)" }));
                }

                if (log.isDebugEnabled()) {
                    log.debug("Mail XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                }

                // ◆◆◆全て異常の場合
            } else {

                if (log.isDebugEnabled()) {
                    log.debug("EC0066");
                }

                // メッセージのパタを追加
                // 予約・取消が出来ませんでした。お手数ですが再度、予約・取消を行ってください。
                this.model.getMessageList().add(getMessage("EC0066", new String[] {}));
            }

            if (log.isDebugEnabled()) {
                log.debug("SComReservationCancellationConfirmationSendAction updateAndSendMail Infomation-Massage");
            }

            // ◆◆◆ 更新完了メッセージを取得して、完了メッセージを設定する
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < this.model.getMessageList().size(); i++) {

                sb.append(this.model.getMessageList().get(i));

                if (i + 1 != this.model.getMessageList().size()) {
                    sb.append(NaikaraTalkConstants.NEW_LINE_CODE_WIN);
                }
            }
            this.message = sb.toString();

            if (log.isDebugEnabled()) {
                log.debug("SComReservationCancellationConfirmationSendAction updateAndSendMail End");
            }

        } catch (Exception e) {

            if (log.isDebugEnabled()) {
                log.debug("SComReservationCancellationConfirmationSendAction updateAndSendMail Exception e.getMessage()=" + e.getMessage());
                log.debug("SComReservationCancellationConfirmationSendAction updateAndSendMail Exception e.getStackTrace()=" + e.getStackTrace());
            }

            throw new NaiException(e);
        }
    }
}
