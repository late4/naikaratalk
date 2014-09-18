package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Timestamp;
//import java.text.DateFormat;
//import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.naikara_talk.batch.SendMailBatch;
import com.naikara_talk.dbutil.DbUtil;
//import com.naikara_talk.dto.LessonReservationPerformanceTrnDto;
import com.naikara_talk.dto.LessonReserveCancelResultListDto;
//import com.naikara_talk.dto.PointProvisionDataListDto;
//import com.naikara_talk.dto.GoodsPurchaseListDto;
import com.naikara_talk.dto.CourseMstDto;
import com.naikara_talk.dto.SchResTLesResPerTDto;
import com.naikara_talk.dto.ScheduleReservationTrnDto;
import com.naikara_talk.dto.StudentMstDto;
import com.naikara_talk.dto.UserMstDto;
import com.naikara_talk.exception.NaiException;
//import com.naikara_talk.logic.ReservationCancellationCourseSelectionListLogic;
import com.naikara_talk.logic.SComReservationCancellationConfirmationLogic;
import com.naikara_talk.model.MailManagMstMiddleModel;
import com.naikara_talk.model.SComReservationCancellationConfirmationModel;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_予約処理<br>
 * <b>クラス名称       :</b>予約／取消確認ページメール送信ThreadServiceクラス<br>
 * <b>クラス概要       :</b>受講者が指定した講師の予約スケジュールを表示して、レッスン予約の登録／取消ができる。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/09/23 TECS 新規作成
 * <b>                 :</b>2014/06/02 TECS レッスン予約に関する「応相談」対応
 */
public class SComReservationCancellationConfirmationSendThreadService implements Runnable {

    // ログ
    Logger log = Logger.getLogger(SComReservationCancellationConfirmationSendThreadService.class);

    /** 予約／取消確認ページModel */
    private SComReservationCancellationConfirmationModel model;

    /** 受講者マスタDTO */
    private StudentMstDto smDto;

    /** 利用者マスタDTO */
    private UserMstDto umDto;

    /** コンストラクタ */
    public SComReservationCancellationConfirmationSendThreadService(SComReservationCancellationConfirmationModel model,
            StudentMstDto smDto, UserMstDto umDto) {
        this.model = model;
        this.smDto = smDto;
        this.umDto = umDto;
    }

    /**
     * メール送信処理<br>
     * <br>
     * メール送信処理を行う。<br>
     * <br>
     * @return なし <br>
     */
    @Override
    public void run() {

        Connection conn = null;

        try {
            conn = DbUtil.getConnection();

            SchResTLesResPerTDto retDto = new SchResTLesResPerTDto();
            String courseNm = "";
            String lessonTm = "";
            String reservationKbn = "";                        // 2014/06/02 レッスン予約に関する「応相談」対応 Add
            MailManagMstMiddleModel mailModel = null;          // sendMailBatch.sendMail用WK領域 2014/06/02 レッスン予約に関する「応相談」対応 Add
            String courseNmShort = "";                         // 2014/06/02 レッスン予約に関する「応相談」対応 Add

            for (int i = 0; i < model.getLrcrlDtoList().size(); i++) {

                // コース名(英語)
                model.setCourseEnm(model.getMailCourseEnmList().get(i));
                // レッスン予約・取消結果リストの明細のリターンコード＝”0”(正常)の場合
                if (model.getLrcrlDtoList().get(i).getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_YES) {

                    // 2013/10/01-システムエラー対応-レッスン予約・解除結果を使用-Start
                    //SchResTLesResPerTDto dto = model.getResultList().get(i);
                	LessonReserveCancelResultListDto dto = model.getLrcrlDtoList().get(i);
                    // 2013/10/01-システムエラー対応-レッスン予約・解除結果を使用-End


                    // 一覧の｢コース名｣のコード≠”9999” (予約中止) の場合
                    if (!StringUtils.equals(dto.getCourseCd(), NaikaraTalkConstants.CHOICE_ALL_NINE)) {

                        //String reservationNo = "";
                        if (0 < model.getResultList().size()) {
                            for (int j = 0; j < model.getResultList().size(); j++) {
                                retDto = model.getResultList().get(j);
                                if (StringUtils.equals(NaikaraStringUtil.converToYYYYMMDD(retDto.getLessonDt()),
                                		NaikaraStringUtil.converToYYYYMMDD(dto.getLessonDt()))
                                		&& StringUtils.equals(NaikaraStringUtil.converToYYYYMMDD(retDto.getLessonTmCd()),
                                				NaikaraStringUtil.converToYYYYMMDD(dto.getLessonTmCd()))) {
                                    //reservationNo = retDto.getReservationNo();
                                    courseNm = retDto.getCourseNm();
                                    lessonTm = retDto.getLessonTm();

                                    // 2014/06/02 レッスン予約に関する「応相談」対応 Add Start
                                    reservationKbn = retDto.getReservationKbn();
                                    // 2014/06/02 レッスン予約に関する「応相談」対応 Add End

                                    break;
                                }
                            }
                        }

                        // 2014/06/02 レッスン予約に関する「応相談」対応 Add Start
                        Timestamp requestedDttm = null;
                        courseNmShort = "";
                        try {
                            SComReservationCancellationConfirmationLogic logic = new SComReservationCancellationConfirmationLogic(conn);
                            // ◆ 講師予定予約テーブルのデータ取得(主Key検索)
                            ScheduleReservationTrnDto schDto = logic.selectScheduleReservationTrn(
                                    dto.getTeacherId(), dto.getLessonDt(), dto.getLessonTmCd());
                            if (schDto != null) {
                                requestedDttm = schDto.getRequestedDttm();
                            }

                            // ◆ コースマスタのデータ取得(主Key検索)
                            CourseMstDto courseMDto = logic.selectCourseMst(dto.getCourseCd());
                            if (courseMDto != null) {
                                courseNmShort = courseMDto.getCourseJnmShort();
                            }

                        } catch (Exception e) {
                            log.error(e.getStackTrace());
                            log.error(e.getMessage());
                        }
                        // 2014/06/02 レッスン予約に関する「応相談」対応 Add End


                        // ◆◆◆ 一覧の｢予約/取消｣＝”予約” の場合
                        if (StringUtils.equals(dto.getReserveCancelFlg(), NaikaraTalkConstants.LESSON_RESERVE)) {

                            if (model.getMailStudentFlgList().get(i)) {
                                // レッスン予約：受講者用


                                // 2014/06/02 レッスン予約に関する「応相談」対応 Add Start
                                if (StringUtils.equals(reservationKbn, NaikaraTalkConstants.RESERV_KBN_CON_YES)) {
                                    // 応相談可で表示されているデータが選択されている場合：受講者用
                                    try {
                                        // ◆ メール送信用の値設定
                                        mailModel = new MailManagMstMiddleModel();
                                        mailModel = this.setSendMailValueLessonTentativeRequestCustomer(model, smDto, dto.getLessonDt(), lessonTm,
                                                courseNm, courseNmShort, mailModel, requestedDttm);

                                        // ◆ メール送信処理<応相談予約：受講者用>
                                        SendMailBatch batch = new SendMailBatch(conn);
                                        batch.sendMail(mailModel.getMailPatternCd(), mailModel.getSendFrom(), mailModel.getSendFromNm(),
                                                mailModel.getSendIdList(), mailModel.getSendToList(),
                                                mailModel.getCc(), mailModel.getBcc(), mailModel.getSubjectTitleList(),
                                                mailModel.getMailTextList(), mailModel.getFile(), mailModel.getReservationNo());

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        log.error(e.getStackTrace());
                                        log.error(e.getMessage());
                                    }

                                } else {
                                    // 2014/06/02 レッスン予約に関する「応相談」対応 Add End

                                    // メールパターンコード ”LBC” (レッスン予約)
                                    model.setMailPatternCd(NaikaraTalkConstants.MAIL_PATTERN_CODE_LBC);

                                    try {
                                        // ◆ メール送信処理<レッスン予約：受講者用>
                                        this.sendMailToStudent(model, dto, smDto, conn, courseNm, lessonTm);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        log.error(e.getStackTrace());
                                        log.error(e.getMessage());
                                    }

                                // 2014/06/02 レッスン予約に関する「応相談」対応 Add Start
                                }
                                // 2014/06/02 レッスン予約に関する「応相談」対応 Add End

                            }


                            if (model.getMailTeacherFlgList().get(i)) {

                                // 2014/06/02 レッスン予約に関する「応相談」対応 Add Start
                                if (StringUtils.equals(reservationKbn, NaikaraTalkConstants.RESERV_KBN_CON_YES)) {
                                    // 応相談可で表示されているデータが選択されている場合：講師用

                                    try {
                                        // ◆ メール送信用の値設定
                                        mailModel = new MailManagMstMiddleModel();
                                        mailModel = this.setSendMailValueLessonTentativeRequestTeacher(model, smDto, umDto, dto.getLessonDt(), lessonTm,
                                                model.getCourseEnm(), courseNm, mailModel, requestedDttm);

                                        // ◆ メール送信処理<応相談予約：講師用>
                                        SendMailBatch batch = new SendMailBatch(conn);
                                        batch.sendMail(mailModel.getMailPatternCd(), mailModel.getSendFrom(), mailModel.getSendFromNm(),
                                                mailModel.getSendIdList(), mailModel.getSendToList(),
                                                mailModel.getCc(), mailModel.getBcc(), mailModel.getSubjectTitleList(),
                                                mailModel.getMailTextList(), mailModel.getFile(), mailModel.getReservationNo());

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        log.error(e.getStackTrace());
                                        log.error(e.getMessage());
                                    }

                                } else {
                                    // 2014/06/02 レッスン予約に関する「応相談」対応 Add End

                                    // レッスン予約：講師用
                                    // メールパターンコード ”LBT” (レッスン予約)
                                    model.setMailPatternCd(NaikaraTalkConstants.MAIL_PATTERN_CODE_LBT);

                                    try {
                                        // ◆ メール送信処理<レッスン予約：講師用>
                                        this.sendMailToTeacher(model, dto, umDto, conn, courseNm, lessonTm);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        log.error(e.getStackTrace());
                                        log.error(e.getMessage());
                                    }

                                // 2014/06/02 レッスン予約に関する「応相談」対応 Add Start
                                }
                                // 2014/06/02 レッスン予約に関する「応相談」対応 Add End

                            }

                        }

                        // ◆◆◆ 一覧の｢予約/取消｣＝”取消” の場合
                        if (StringUtils.equals(dto.getReserveCancelFlg(), NaikaraTalkConstants.LESSON_CANCEL)) {
                            if (model.getMailStudentFlgList().get(i)) {

                                // レッスン取消：受講者用
                                // メールパターンコード ”LCC” (レッスン取消)
                                model.setMailPatternCd(NaikaraTalkConstants.MAIL_PATTERN_CODE_LCC);

                                // 2014/06/02 レッスン予約に関する「応相談」対応 Add Start
                                if (StringUtils.equals(reservationKbn, NaikaraTalkConstants.RESERV_KBN_CON_PROVISIONAL_RESERV)
                                        || StringUtils.equals(reservationKbn, NaikaraTalkConstants.RESERV_KBN_CON_ALREADY)) {
                                    // メールパターンコード ”ACT” (応相談-レッスン取消)<受講者用>
                                    model.setMailPatternCd(NaikaraTalkConstants.MAIL_PATTERN_CODE_ACC);
                                }
                                // 2014/06/02 レッスン予約に関する「応相談」対応 Add End

                                try {
                                    // ◆ メール送信処理<レッスン取消：受講者用>
                                    this.sendMailToStudent(model, dto, smDto, conn, courseNm, lessonTm);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    log.error(e.getStackTrace());
                                    log.error(e.getMessage());
                                }
                            }
                            if (model.getMailTeacherFlgList().get(i)) {
                                // レッスン取消：講師用

                                // メールパターンコード ”LCT” (レッスン取消)
                                model.setMailPatternCd(NaikaraTalkConstants.MAIL_PATTERN_CODE_LCT);

                                // 2014/06/02 レッスン予約に関する「応相談」対応 Add Start
                                if (StringUtils.equals(reservationKbn, NaikaraTalkConstants.RESERV_KBN_CON_PROVISIONAL_RESERV)
                                        || StringUtils.equals(reservationKbn, NaikaraTalkConstants.RESERV_KBN_CON_ALREADY)) {
                                    // メールパターンコード ”ACT” (応相談-レッスン取消)<講師用>
                                    model.setMailPatternCd(NaikaraTalkConstants.MAIL_PATTERN_CODE_ACT);
                                }
                                // 2014/06/02 レッスン予約に関する「応相談」対応 Add End


                                try {
                                    // ◆ メール送信処理<レッスン取消：講師用>
                                    this.sendMailToTeacher(model, dto, umDto, conn, courseNm, lessonTm);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    log.error(e.getStackTrace());
                                    log.error(e.getMessage());
                                }
                            }

                        }
                    }
                }
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
            log.error(e1.getStackTrace());
            log.error(e1.getMessage());
        } finally {
            try {
                conn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
                log.error(e2.getStackTrace());
                log.error(e2.getMessage());
            }
        }
    }

    /**
     * 受講者のメール送信処理<br>
     * <br>
     * 受講者のメール送信処理を起動する。<br>
     * <br>
     * @param model モデル<br>
     * @param dto 講師予定予約テーブル、レッスン予実テーブルDTO<br>
     * @param smDto 受講者マスタDTO<br>
     * @param conn コネクション<br>
     * @param String courseNm<br>
     * @param String lessonTm<br>
     * @return なし<br>
     * @throws NaiException
     */
    private void sendMailToStudent(SComReservationCancellationConfirmationModel model, LessonReserveCancelResultListDto dto,
            StudentMstDto smDto, Connection conn, String courseNm, String lessonTm) throws NaiException {

        // メールパターンコード
        String mailPatternCd = model.getMailPatternCd();

        // 送信者 ”” (設定なし)
        String sendFrom = NaikaraTalkConstants.BRANK;

        // 送信者名 ”” (設定なし)
        String sendFromNm = NaikaraTalkConstants.BRANK;

        // 宛先IDリスト
        List<String> sendIdList = new ArrayList<String>();
        // (0個) ※ ログイン情報：受講者ID
        sendIdList.add(model.getLoginId());

        // 宛先アドレスリスト
        List<String> sendToList = new ArrayList<String>();
        // (0個) 受講者マスタ．メールアドレス1
        sendToList.add(smDto.getMailAddress1());

        // CC ”” (設定なし)
        String cc = NaikaraTalkConstants.BRANK;

        // BCC ”” (設定なし)
        String bcc = NaikaraTalkConstants.BRANK;

        // 件名リスト (なし) ”” (設定なし)
        List<String> subjectTitleList = new ArrayList<String>();

        // 本文リスト
        List<List<String>> mailTextList = new ArrayList<List<String>>();

        // 本文リスト(0個目) 共通部品：名前の編集(受講者マスタ．名前(姓)、受講者マスタ．名前(名))
        List<String> textList0 = new ArrayList<String>();
        textList0.add(NaikaraStringUtil.unionName(smDto.getFamilyJnm(), smDto.getFirstJnm()));
        mailTextList.add(textList0);

        // 本文リスト (1個目) ～ (4個目) ”” (設定なし)
        List<String> textList1_4 = new ArrayList<String>();
        textList1_4.add(NaikaraTalkConstants.BRANK);
        mailTextList.add(textList1_4);
        mailTextList.add(textList1_4);
        mailTextList.add(textList1_4);
        mailTextList.add(textList1_4);

        // 本文リスト (5個目) 一覧の｢レッスン日｣ & ” ” & 一覧の｢レッスン時刻名｣
        List<String> textList5 = new ArrayList<String>();
        textList5.add(NaikaraStringUtil.converToYYMMDDHHMM(NaikaraStringUtil.converToYYYY_MM_DD(dto.getLessonDt()),
        		lessonTm));
        mailTextList.add(textList5);

        // 本文リスト (6個目) 一覧の｢コース名｣
        List<String> textList6 = new ArrayList<String>();
        textList6.add(courseNm);
        mailTextList.add(textList6);

        // 本文リスト (7個目) ｢講師名(ニックネーム)｣
        List<String> textList7 = new ArrayList<String>();
        textList7.add(model.getTeacherNm());
        mailTextList.add(textList7);

        // 本文リスト (8個目) ～ (20個目) ”” (設定なし)
        List<String> textList8_20 = new ArrayList<String>();
        textList8_20.add(NaikaraTalkConstants.BRANK);
        mailTextList.add(textList8_20);
        mailTextList.add(textList8_20);
        mailTextList.add(textList8_20);
        mailTextList.add(textList8_20);
        mailTextList.add(textList8_20);
        mailTextList.add(textList8_20);
        mailTextList.add(textList8_20);
        mailTextList.add(textList8_20);
        mailTextList.add(textList8_20);
        mailTextList.add(textList8_20);
        mailTextList.add(textList8_20);
        mailTextList.add(textList8_20);
        mailTextList.add(textList8_20);

        // 添付 ”” (設定なし)
        String file = NaikaraTalkConstants.BRANK;

        // 予約No ”” (設定なし)
        String reservationNo = NaikaraTalkConstants.BRANK;

        try {

            SendMailBatch batch = new SendMailBatch(conn);

            // メール送信処理
            batch.sendMail(mailPatternCd, sendFrom, sendFromNm, sendIdList, sendToList, cc, bcc, subjectTitleList,
                    mailTextList, file, reservationNo);
        } catch (Exception e) {
            log.error(e.getStackTrace());
            log.error(e.getMessage());
            throw new NaiException(e);
        }
    }

    /**
     * 講師のメール送信処理<br>
     * <br>
     * 講師のメール送信処理を起動する。<br>
     * <br>
     * @param model モデル<br>
     * @param dto 講師予定予約テーブル、レッスン予実テーブルDTO<br>
     * @param umDto 利用者マスタDTO<br>
     * @param conn コネクション<br>
     * @param String courseNm<br>
     * @param String lessonTm<br>
     * @return なし<br>
     * @throws NaiException
     */
    private void sendMailToTeacher(SComReservationCancellationConfirmationModel model, LessonReserveCancelResultListDto dto,
            UserMstDto umDto, Connection conn, String courseNm, String lessonTm) throws NaiException {

        // 共通部品：日付編集（講師用）(画面の｢レッスン日｣、画面の｢レッスン時刻名｣)
        String lessonDtTm = NaikaraStringUtil.converToDDMMYYHHMM(NaikaraStringUtil.converToYY_MM_DD(dto.getLessonDt()),
        		lessonTm);

        // メールパターンコード
        String mailPatternCd = model.getMailPatternCd();

        // 送信者 ”” (設定なし)
        String sendFrom = NaikaraTalkConstants.BRANK;

        // 送信者名 ”” (設定なし)
        String sendFromNm = NaikaraTalkConstants.BRANK;

        // 宛先IDリスト
        List<String> sendIdList = new ArrayList<String>();
        // (0個) ｢講師ID｣
        sendIdList.add(model.getTeacherId());

        // 宛先アドレスリスト
        List<String> sendToList = new ArrayList<String>();
        // (0個) 利用者マスタ．メールアドレス1
        sendToList.add(umDto.getMailAddress1());

        // CC ”” (設定なし)
        String cc = NaikaraTalkConstants.BRANK;

        // BCC ”” (設定なし)
        String bcc = NaikaraTalkConstants.BRANK;

        // 件名リスト (なし) ”” (設定なし)
        List<String> subjectTitleList = new ArrayList<String>();
        // (0個目) 共通部品：日付編集（講師用）(画面の｢レッスン日｣、画面の｢レッスン時刻名｣)
        subjectTitleList.add(lessonDtTm);

        // 本文リスト
        List<List<String>> mailTextList = new ArrayList<List<String>>();

        // 本文リスト (0個目)｢講師名(ニックネーム)｣
        List<String> textList0 = new ArrayList<String>();
        textList0.add(model.getTeacherNm());
        mailTextList.add(textList0);

        // 本文リスト (1個目)
        List<String> textList1 = new ArrayList<String>();
        // ログイン情報：受講者ニックネーム
        textList1.add(model.getLoginNm());
        // 共通部品：日付編集（講師用）(一覧の｢レッスン日｣、一覧の｢レッスン時刻｣)
        textList1.add(lessonDtTm);
        mailTextList.add(textList1);

        // 本文リスト (2個目)
        List<String> textList2 = new ArrayList<String>();
        // ログイン情報：受講者ニックネーム
        textList2.add(model.getLoginNm());
        // 共通部品：日付編集（講師用）(一覧の｢レッスン日｣、一覧の｢レッスン時刻｣)
        textList2.add(lessonDtTm);
        mailTextList.add(textList2);

        // ”LBT” (レッスン予約)の場合
        if (StringUtils.equals(mailPatternCd, NaikaraTalkConstants.MAIL_PATTERN_CODE_LBT)) {

            // 本文リスト (3個目) ”” (設定なし)
            List<String> textList3 = new ArrayList<String>();
            textList3.add(NaikaraTalkConstants.BRANK);
            mailTextList.add(textList3);

            // 本文リスト (4個目) 変数｢コース名(英語)｣
            List<String> textList4 = new ArrayList<String>();
            textList4.add(model.getCourseEnm());
            mailTextList.add(textList4);

            // 本文リスト (5個目) 一覧の｢コース名｣
            List<String> textList5 = new ArrayList<String>();
            textList5.add(courseNm);
            mailTextList.add(textList5);

            // 本文リスト (6個目)～(30個目) ”” (設定なし)
            List<String> textList6_30 = new ArrayList<String>();
            textList6_30.add(NaikaraTalkConstants.BRANK);
            mailTextList.add(textList6_30);
            mailTextList.add(textList6_30);
            mailTextList.add(textList6_30);
            mailTextList.add(textList6_30);
            mailTextList.add(textList6_30);
            mailTextList.add(textList6_30);
            mailTextList.add(textList6_30);
            mailTextList.add(textList6_30);
            mailTextList.add(textList6_30);
            mailTextList.add(textList6_30);
            mailTextList.add(textList6_30);
            mailTextList.add(textList6_30);
            mailTextList.add(textList6_30);
            mailTextList.add(textList6_30);
            mailTextList.add(textList6_30);
            mailTextList.add(textList6_30);
            mailTextList.add(textList6_30);
            mailTextList.add(textList6_30);
            mailTextList.add(textList6_30);
            mailTextList.add(textList6_30);
            mailTextList.add(textList6_30);
            mailTextList.add(textList6_30);
            mailTextList.add(textList6_30);
            mailTextList.add(textList6_30);
            mailTextList.add(textList6_30);

            // ”LCT” (レッスン取消)の場合
        } else if (StringUtils.equals(mailPatternCd, NaikaraTalkConstants.MAIL_PATTERN_CODE_LCT)
        		|| StringUtils.equals(mailPatternCd, NaikaraTalkConstants.MAIL_PATTERN_CODE_ACT)) {

            // 本文リスト (3個目) 変数｢コース名(英語)｣
            List<String> textList3 = new ArrayList<String>();
            textList3.add(model.getCourseEnm());
            mailTextList.add(textList3);

            // 本文リスト (4個目) 一覧の｢コース名｣
            List<String> textList4 = new ArrayList<String>();
            textList4.add(courseNm);
            mailTextList.add(textList4);

            List<String> textList5_16 = new ArrayList<String>();
            // 本文リスト (5個目) ～ (16個目) ”” (設定なし)
            textList5_16.add(NaikaraTalkConstants.BRANK);
            mailTextList.add(textList5_16);
            mailTextList.add(textList5_16);
            mailTextList.add(textList5_16);
            mailTextList.add(textList5_16);
            mailTextList.add(textList5_16);
            mailTextList.add(textList5_16);
            mailTextList.add(textList5_16);
            mailTextList.add(textList5_16);
            mailTextList.add(textList5_16);
            mailTextList.add(textList5_16);
            mailTextList.add(textList5_16);
            mailTextList.add(textList5_16);
        }

        // 添付 ”” (設定なし)
        String file = NaikaraTalkConstants.BRANK;

        // 予約No ”” (設定なし)
        String reservationNo = NaikaraTalkConstants.BRANK;

        try {

            SendMailBatch batch = new SendMailBatch(conn);

            // メール送信処理
            batch.sendMail(mailPatternCd, sendFrom, sendFromNm, sendIdList, sendToList, cc, bcc, subjectTitleList,
                    mailTextList, file, reservationNo);
        } catch (Exception e) {
            log.error(e.getStackTrace());
            log.error(e.getMessage());
            throw new NaiException(e);
        }
    }


    // 2014/06/02 レッスン予約に関する「応相談」対応 Add Start
    /**
     * メール送信内容の設定（受講者：応相談予約）<br>
     * <br>
     * メール送信内容の設定処理を行う。<br>
     * <br>
     * @param model SComReservationCancellationConfirmationModel<br>
     * @param studentMstDto StudentMstDto<br>
     * @param lessonDt レッスン日<br>
     * @param lessonTmNm レッスン時刻名<br>
     * @param courseNmJ コース名(日本語)<br>
     * @param courseNmJShort コース名(短縮)<br>
     * @param mailModel MailManagMstMiddleModel<br>
     * @param requestedDttm 講師予定予約テーブル.応相談予約登録日時<br>
     * @return MailManagMstMiddleModel<br>
     * @throws NaiException
     */
    private MailManagMstMiddleModel setSendMailValueLessonTentativeRequestCustomer(SComReservationCancellationConfirmationModel model,
            StudentMstDto studentMstDto, String lessonDt, String lessonTmNm,
            String courseNmJ, String courseNmJShort, MailManagMstMiddleModel mailModel, Timestamp prmRequestedDttm) throws NaiException {

        String mailPatternCd = "";          // メールパターンコード
        String studentJNm = "";             // 受講者マスタ：名前(姓) & 名前(名)
        String studentMailAddress = "";     // 受講者マスタ：メールアドレス1


        // ◆◆◆ 応相談予約(受講者)
        mailPatternCd = NaikaraTalkConstants.MAIL_PATTERN_CODE_LRC;

        try {

            if (studentMstDto != null) {
                studentJNm = NaikaraStringUtil.unionName(studentMstDto.getFamilyJnm(), studentMstDto.getFirstJnm());
                studentMailAddress = studentMstDto.getMailAddress1();
            }

            // 宛先IDリスト ※ 受講者ID
            List<String> sendIdList = new ArrayList<String>();
            sendIdList.add(model.getLoginId());

            // 宛先リスト
            List<String> sendToList = new ArrayList<String>();
            sendToList.add(studentMailAddress);

            // 件名リスト (なし) ”” (設定なし)
            List<String> subjectTitleList = new ArrayList<String>();
            subjectTitleList.add(NaikaraTalkConstants.BRANK);

            // 本文リスト
            List<List<String>> mailTextList = new ArrayList<List<String>>();
            List<String> textList = new ArrayList<String>();

            // 本文リスト(0個目) 受講者名
            textList.add(studentJNm);
            mailTextList.add(0, textList);

            // 本文リスト (1個目)  ”” (設定なし)
            textList = new ArrayList<String>();
            textList.add(NaikaraTalkConstants.BRANK);
            mailTextList.add(1, textList);

            // 本文リスト (2個目) ”” (設定なし)
            textList = new ArrayList<String>();
            textList.add(NaikaraTalkConstants.BRANK);
            mailTextList.add(2, textList);

            // 本文リスト (3個目) ”” (設定なし)
            textList = new ArrayList<String>();
            textList.add(NaikaraTalkConstants.BRANK);
            mailTextList.add(3, textList);

            // 本文リスト (4個目) ”” (設定なし)
            textList = new ArrayList<String>();
            textList.add(NaikaraTalkConstants.BRANK);
            mailTextList.add(4, textList);

            // 本文リスト (5個目) ”” (設定なし)
            textList = new ArrayList<String>();
            textList.add(NaikaraTalkConstants.BRANK);
            mailTextList.add(5, textList);

            // 本文リスト (6個目) 一覧の｢レッスン日｣ & ” ” & 一覧の｢レッスン時刻名｣
            textList = new ArrayList<String>();
            textList.add(NaikaraStringUtil.converToYYMMDDHHMM(
                    NaikaraStringUtil.converToYYYY_MM_DD(lessonDt), lessonTmNm));
            mailTextList.add(6, textList);

            // 本文リスト (7個目) 一覧の｢コース名｣(日本語)
            textList = new ArrayList<String>();
            textList.add(courseNmJ);
            mailTextList.add(7, textList);

            // 本文リスト (8個目) コースマスタの「講師一覧表示用の短縮コース名」
            textList = new ArrayList<String>();
            textList.add(courseNmJShort);
            mailTextList.add(8, textList);

            // 本文リスト (9個目) 講師ニックネーム
            textList = new ArrayList<String>();
            textList.add(model.getTeacherNm());
            mailTextList.add(9, textList);

            // 本文リスト (10個目) ～ (27個目) ”” (設定なし)
            textList = new ArrayList<String>();
            textList.add(NaikaraTalkConstants.BRANK);
            for(int i = 10, n = 27; i < n; i++) {
                // 本文リスト(X個目) ”” (設定なし)
                mailTextList.add(i, textList);
            }

            mailModel.setMailPatternCd(mailPatternCd);                 // メールパターンコード
            mailModel.setSendFrom(NaikaraTalkConstants.BRANK);         // 送信者アドレス”” (設定なし)※TBL値を利用：システム
            mailModel.setSendFromNm(NaikaraTalkConstants.BRANK);       // 送信者名      ”” (設定なし)※TBL値を利用：システム
            mailModel.setSendIdList(sendIdList);                       // 宛先IDリスト
            mailModel.setSendToList(sendToList);                       // 宛先リスト
            mailModel.setCc(NaikaraTalkConstants.BRANK);               // CC            ”” (設定なし)  ※TBL値を利用：システム
            mailModel.setBcc(NaikaraTalkConstants.BRANK);              // BCC           ”” (設定なし)
            mailModel.setFile(NaikaraTalkConstants.BRANK);             // 添付          ”” (設定なし)
            mailModel.setReservationNo(NaikaraTalkConstants.BRANK);    // 予約No        ”” (設定なし)

            mailModel.setSubjectTitleList(subjectTitleList);           // 件名リスト
            mailModel.setMailTextList(mailTextList);                   // 本文リスト

            // ◆◆◆ 返却
            return mailModel;

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getStackTrace());
            log.error(e.getMessage());
            throw new NaiException(e);
        }

    }

    // 2014/06/02 レッスン予約に関する「応相談」対応 Add End

    // 2014/06/02 レッスン予約に関する「応相談」対応 Add Start
    /**
     * メール送信内容の設定（講師：応相談予約）<br>
     * <br>
     * メール送信内容の設定処理を行う。<br>
     * <br>
     * @param model SComReservationCancellationConfirmationModel<br>
     * @param studentMstDto StudentMstDto<br>
     * @param userMstDto UserMstDto<br>
     * @param lessonDt レッスン日<br>
     * @param lessonTmNm レッスン時刻名<br>
     * @param mailModel MailManagMstMiddleModel<br>
     * @param requestedDttm 講師予定予約テーブル.応相談予約登録日時<br>
     * @return MailManagMstMiddleModel<br>
     * @throws NaiException
     */
    private MailManagMstMiddleModel setSendMailValueLessonTentativeRequestTeacher(SComReservationCancellationConfirmationModel model,
            StudentMstDto studentMstDto, UserMstDto userMstDto, String lessonDt, String lessonTmNm,
            String courseNmE, String courseNmJ, MailManagMstMiddleModel mailModel, Timestamp prmRequestedDttm) throws NaiException {

        String mailPatternCd = "";          // メールパターンコード
        String studentJNm = "";             // 受講者マスタ：名前(姓) & 名前(名)
        String studentNickNm = "";          // 受講者マスタ：ニックネーム
        String teacherAddress = "";         // 利用者マスタ：メールアドレス1
        String requestedDttm = null;        // 講師予定予約テーブル：応相談予約登録日時
        String requestedDttmDdMmYy = "";    // 講師予定予約テーブル：応相談予約登録日時(DD/MM/YY)
        String requestedHhmm = "";          // 講師予定予約テーブル：応相談予約登録日時(HH:MM)
        String reqYyyymd = "";
        String reqHhmm = "";
        StringBuilder requestedDttmDdMmYyhhmm = new StringBuilder();
        StringBuilder reqYyyymdHhmm = new StringBuilder();


        // ◆◆◆ 応相談予約(講師)
        mailPatternCd = NaikaraTalkConstants.MAIL_PATTERN_CODE_LRT;

        try {

            if (studentMstDto != null) {
                studentJNm = NaikaraStringUtil.unionName(studentMstDto.getFamilyJnm(), studentMstDto.getFirstJnm());
                studentNickNm = (String) NaikaraStringUtil.nvlString(studentMstDto.getNickNm());
                if (studentNickNm == null || StringUtils.isEmpty(studentNickNm)) {
                    studentNickNm = studentJNm;  // 法人対策
                }
            }

            if (userMstDto != null) {
                teacherAddress = (String) NaikaraStringUtil.nvlString(userMstDto.getMailAddress1());
            }

            try {
                // 講師予定予約テーブル.応相談予約登録日時 "yyyy/MM/dd HH:mm"
                requestedDttm = new SimpleDateFormat(DateUtil.DATE_FORMAT_yyyy_MM_dd_HH_mm).format(prmRequestedDttm);
                if (requestedDttm != null) {
                    // DD/MM/YY 編集
                    requestedDttmDdMmYy = NaikaraStringUtil.converToDDMMYY(requestedDttm.substring(2, 10));
                    // HH:MM 編集
                    requestedHhmm = requestedDttm.substring(11, 16);
                    // DD/MM/YY HH:MM 編集
                    requestedDttmDdMmYyhhmm = new StringBuilder();
                    requestedDttmDdMmYyhhmm.append(requestedDttmDdMmYy).append(" ").append(requestedHhmm);

                    // YYYY年M月D日 編集
                    // 出力用の日付 時刻編集 <応相談予約登録日時> "yyyy/MM/dd HH:mm"
                    reqYyyymd = NaikaraStringUtil.converToYYYYMD(NaikaraStringUtil.converToYYYYMMDD(requestedDttm.substring(0, 10)));
                    reqHhmm = requestedDttm.substring(11);
                    reqYyyymdHhmm = new StringBuilder();
                    reqYyyymdHhmm.append(reqYyyymd).append(" ").append(reqHhmm);
                }

            } catch (Exception e) {
                log.error(e.getStackTrace());
                log.error(e.getMessage());
                e.printStackTrace();
            }


            // 共通部品：日付編集（講師用）(画面の｢レッスン日｣、画面の｢レッスン時刻名｣)
            String lessonDtYyMmDd = NaikaraStringUtil.converToYY_MM_DD(lessonDt);
            String lessonDtTm = NaikaraStringUtil.converToDDMMYYHHMM(lessonDtYyMmDd, lessonTmNm);

            // 共通部品：日付編集（講師用）(画面の｢レッスン時刻名｣)
            String lessonTm5 = NaikaraStringUtil.converToHHMM(lessonTmNm);

            // 宛先IDリスト ※ 講師ID
            List<String> sendIdList = new ArrayList<String>();
            sendIdList.add(model.getTeacherId());

            // 宛先リスト
            List<String> sendToList = new ArrayList<String>();
            sendToList.add(teacherAddress);

            // 件名リスト (なし) ”” (設定なし)
            List<String> subjectTitleList = new ArrayList<String>();
            subjectTitleList.add(NaikaraTalkConstants.BRANK);

            // 本文リスト
            List<List<String>> mailTextList = new ArrayList<List<String>>();
            List<String> textList = new ArrayList<String>();

            // 本文リスト(0個目) 講師名(ニックネーム)
            textList.add(model.getTeacherNm());
            mailTextList.add(0, textList);

            // 本文リスト (1個目) 講師名(ニックネーム)
            textList = new ArrayList<String>();
            textList.add(model.getTeacherNm());
            mailTextList.add(1, textList);

            // 本文リスト (2個目) ”” (設定なし)
            textList = new ArrayList<String>();
            textList.add(NaikaraTalkConstants.BRANK);
            mailTextList.add(2, textList);

            // 本文リスト (3個目) 応相談予約登録日時、受講者ニックネーム、レッスン日＆レッスン時刻
            textList = new ArrayList<String>();
            textList.add(requestedDttmDdMmYyhhmm.toString());
            textList.add(studentNickNm);
            textList.add(lessonDtTm);
            mailTextList.add(3, textList);

            // 本文リスト (4個目) 応相談予約登録日時、受講者ニックネーム、レッスン日
            textList = new ArrayList<String>();
            textList.add(requestedDttmDdMmYyhhmm.toString());
            textList.add(studentNickNm);
            textList.add(lessonDtYyMmDd);
            mailTextList.add(4, textList);

            // 本文リスト (5個目) レッスン時刻 HH:MM
            textList = new ArrayList<String>();
            textList.add(lessonTm5);
            mailTextList.add(5, textList);

            // 本文リスト (6個目) ”” (設定なし)
            textList = new ArrayList<String>();
            textList.add(NaikaraTalkConstants.BRANK);
            mailTextList.add(6, textList);

            // 本文リスト (7個目) 変数｢コース名(英語)｣
            textList = new ArrayList<String>();
            textList.add(courseNmE);
            mailTextList.add(7, textList);

            // 本文リスト (8個目) 一覧の｢コース名｣(日本語)
            textList = new ArrayList<String>();
            textList.add(courseNmJ);
            mailTextList.add(8, textList);

            // 本文リスト (9個目) ”” (設定なし)
            textList = new ArrayList<String>();
            textList.add(NaikaraTalkConstants.BRANK);
            mailTextList.add(9, textList);

            // 本文リスト (10個目) 応相談予約登録日時の日付 DD/MM/YY
            textList = new ArrayList<String>();
            textList.add(requestedDttmDdMmYy);
            mailTextList.add(10, textList);

            // 本文リスト (11個目) 応相談予約登録日時の時刻 HH:MM
            textList = new ArrayList<String>();
            textList.add(reqHhmm);
            mailTextList.add(11, textList);

            // 本文リスト (12個目) 受講者ニックネーム
            textList = new ArrayList<String>();
            textList.add(studentNickNm);
            mailTextList.add(12, textList);

            // 本文リスト (13個目)  ”” (設定なし)
            textList = new ArrayList<String>();
            textList.add(NaikaraTalkConstants.BRANK);
            mailTextList.add(13, textList);

            // 本文リスト (14個目)  応相談予約登録日時の日付 YYYY年M月D日
            textList = new ArrayList<String>();
            textList.add(reqYyyymd);
            mailTextList.add(14, textList);

            // 本文リスト (15個目) 応相談予約登録日時の時刻 HH:MM
            textList = new ArrayList<String>();
            textList.add(reqHhmm);
            mailTextList.add(15, textList);

            // 本文リスト (16個目) 受講者ニックネーム
            textList = new ArrayList<String>();
            textList.add(studentNickNm);
            mailTextList.add(16, textList);

            // 本文リスト (17個目)  ”” (設定なし)
            textList = new ArrayList<String>();
            textList.add(NaikaraTalkConstants.BRANK);
            mailTextList.add(17, textList);

            // 本文リスト (18個目)  応相談予約登録日時の日付 DD/MM/YY
            textList = new ArrayList<String>();
            textList.add(requestedDttmDdMmYy);
            mailTextList.add(18, textList);

            // 本文リスト (19個目) 応相談予約登録日時の時刻 HH:MM
            textList = new ArrayList<String>();
            textList.add(requestedHhmm);
            mailTextList.add(19, textList);

            // 本文リスト (20個目)  応相談予約登録日時の日付 DD/MM/YY
            textList = new ArrayList<String>();
            textList.add(requestedDttmDdMmYy);
            mailTextList.add(20, textList);

            // 本文リスト (21個目) 応相談予約登録日時の時刻 HH:MM
            textList = new ArrayList<String>();
            textList.add(requestedHhmm);
            mailTextList.add(21, textList);


            // 本文リスト (22個目) ～ (34個目) ”” (設定なし)
            textList = new ArrayList<String>();
            textList.add(NaikaraTalkConstants.BRANK);
            for(int i = 22, n = 34; i < n; i++) {
                // 本文リスト(X個目) ”” (設定なし)
                mailTextList.add(i, textList);
            }

            mailModel.setMailPatternCd(mailPatternCd);                 // メールパターンコード
            mailModel.setSendFrom(NaikaraTalkConstants.BRANK);         // 送信者アドレス”” (設定なし)※TBL値を利用：システム
            mailModel.setSendFromNm(NaikaraTalkConstants.BRANK);       // 送信者名      ”” (設定なし)※TBL値を利用：システム
            mailModel.setSendIdList(sendIdList);                       // 宛先IDリスト
            mailModel.setSendToList(sendToList);                       // 宛先リスト
            mailModel.setCc(NaikaraTalkConstants.BRANK);               // CC            ”” (設定なし)  ※TBL値を利用：システム
            mailModel.setBcc(NaikaraTalkConstants.BRANK);              // BCC           ”” (設定なし)
            mailModel.setFile(NaikaraTalkConstants.BRANK);             // 添付          ”” (設定なし)
            mailModel.setReservationNo(NaikaraTalkConstants.BRANK);    // 予約No        ”” (設定なし)

            mailModel.setSubjectTitleList(subjectTitleList);           // 件名リスト
            mailModel.setMailTextList(mailTextList);                   // 本文リスト

            // ◆◆◆ 返却
            return mailModel;

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getStackTrace());
            log.error(e.getMessage());
            throw new NaiException(e);
        }

    }

    // 2014/06/02 レッスン予約に関する「応相談」対応 Add End

}