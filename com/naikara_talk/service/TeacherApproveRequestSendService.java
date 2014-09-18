package com.naikara_talk.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.batch.SendMailBatch;
import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.LessonReserveCancelResultListDto;
import com.naikara_talk.dto.PointProvisionDataListDto;
import com.naikara_talk.dto.PointReleaseDataListDto;
import com.naikara_talk.dto.ScheduleReservationTrnDto;
import com.naikara_talk.dto.StudentMstDto;
import com.naikara_talk.dto.UserMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.LessonReserveCancelLogic;
import com.naikara_talk.logic.TeacherApproveRequestLogic;
import com.naikara_talk.logic.SmailAccountHistoryLogic;
import com.naikara_talk.model.MailManagMstMiddleModel;
import com.naikara_talk.model.TeacherApproveRequestListModel;
import com.naikara_talk.model.TeacherApproveRequestModel;
import com.naikara_talk.sessiondata.SessionTeacherApproveRequest;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;


/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>講師<br>
 * <b>クラス名称　　　:</b>応相談回答ページメール送信およびＤＢ更新Serviceクラス。<br>
 * <b>クラス概要　　　:</b>メール送信およびＤＢ更新をおこなう。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2014/06/02 TECS 新規作成。
 */
public class TeacherApproveRequestSendService implements ActionService {

    /** 処理 正常 */
    public static final int PROCCES1_YES = 0;
    /** 講師マスタのメールアドレス1に値の設定なしErr */
    public static final int NO_SETTING_TEACHER_MAIL_ADDRESS = 1;
    /** 受講者マスタのメールアドレス1に値の設定なしErr */
    public static final int NO_SETTING_STUDENT_MAIL_ADDRESS = 2;
    /** 講師予定予約テーブルに更新対象データなしErr */
    public static final int NO_DATA_SCHEDULE_RESERVATION_TRN = 3;
    /** 講師予定予約テーブルに更新Err */
    public static final int NO_UPD_SCHEDULE_RESERVATION_TRN = 4;
    /** 共通処理：レッスンの予約・取消処理 Err */
    public static final int COM_LESSON_RESERVE_CANCEL = 5;



    /**
     * メール送信およびＤＢ更新処理<br>
     * <br>
     * メール送信およびＤＢ更新処理を行う。<br>
     * <br>
     * @param TeacherApproveRequestModel<br>
     * @return なし <br>
     * @throws NaiException
     */
    public int sendMailAndDbUpdate(TeacherApproveRequestModel model) throws NaiException {
        int retValue = PROCCES1_YES;

        ScheduleReservationTrnDto prmDto = null;           // 講師予定予約テーブル
        String reservationKbnSet= null;                    // 講師予定予約テーブルの予約区分(予約状況)
        MailManagMstMiddleModel mailModel = null;          // sendMailBatch.sendMail用WK領域＜受講者＞
        MailManagMstMiddleModel mailModelT = null;         // sendMailBatch.sendMail用WK領域＜講師＞
        StudentMstDto studentMstDto = null;                // 受講者マスタ
        UserMstDto userMstDto = null;                      // 利用者マスタ
        String hisMailPatternCd = null;                    // スクールメール・アカウント変更履歴テーブルの更新処理用のパターンコード
        String teacherMailAddress = null;                  // 利用者マスタ:メールアドレス
        TeacherApproveRequestListModel loadData = null;    //

        Connection conn = null;
        try {
            // DB接続
            conn = DbUtil.getConnection();

            // Logicの生成
            TeacherApproveRequestLogic logic = new TeacherApproveRequestLogic(conn);

            // SendMailBatchの生成
            SendMailBatch sendMailBatch = new SendMailBatch(conn);

            // 共通：スクールのメール送信・受信履歴データ作成Logicクラスの生成
            SmailAccountHistoryLogic sAMHiLogic = new SmailAccountHistoryLogic(conn);

            // 共通：レッスン予約/キャンセルLogicクラスの生成
            LessonReserveCancelLogic lessonCxlLogic = new LessonReserveCancelLogic(conn);

            // ◆◆◆ (レッスン予約に使用する送信先である) 利用者マスタのデータ取得処理
            userMstDto = logic.selectUserMst(model.getUserId());
            if (userMstDto == null) {
                return NO_SETTING_TEACHER_MAIL_ADDRESS;
            } else {
                teacherMailAddress = userMstDto.getMailAddress1();
                if (teacherMailAddress == null || StringUtils.isEmpty(teacherMailAddress)) {
                    return NO_SETTING_TEACHER_MAIL_ADDRESS;
                }
            }

            // 画面表示時の情報の取得
            SessionTeacherApproveRequest sessionApp = (SessionTeacherApproveRequest) SessionDataUtil.getSessionData(SessionTeacherApproveRequest.class.toString());

            String[] reservationNo = model.getSelectReservationNo();
            for(int i = 0, n = model.getSelectReservationNo().length; i < n; i++) {
                if (reservationNo[i] == null || StringUtils.isEmpty(reservationNo[i])) {
                    continue;
                }

                loadData = null;
                for(int j = 0, o = sessionApp.getTargetList().size(); j < o; j++) {
                    loadData = sessionApp.getTargetList().get(j);
                    if (StringUtils.equals(reservationNo[i], loadData.getReservationNo())) {
                        break;
                    }
                }
                if (loadData == null) {
                    // 処理できない
                    continue;
                }

                // ◆◆◆ (送信先である) 受講者マスタから対象データの取得処理
                studentMstDto = logic.selectStudentMst(loadData.getStudentId());
                if (studentMstDto == null) {
                    return NO_SETTING_STUDENT_MAIL_ADDRESS;
                } else {
                    if (studentMstDto.getMailAddress1() == null || StringUtils.isEmpty(studentMstDto.getMailAddress1())) {
                        return NO_SETTING_STUDENT_MAIL_ADDRESS;
                    }
                }

                // ◆◆◆ 送信メール用の値の設定
                mailModel = new MailManagMstMiddleModel();
                // 回答区分を判定し、メール送信用の値の設定＜受講者向け＞
                mailModel = this.setSendMailValueStudent(model, studentMstDto, loadData.getLessonDt(), loadData.getLessonTmNm(), mailModel);

                // 回答区分を判定し、メール送信用の値の設定＜講師向け＞
                mailModelT = new MailManagMstMiddleModel();
                mailModelT = this.setSendMailValueTeacher(model, studentMstDto, userMstDto, loadData.getLessonDt(), loadData.getLessonTmNm(), mailModelT);

                // ◆◆◆ 講師予定予約テーブルの更新処理 <ＯＫ：応相談確定／ＮＧ：応相談>
                // ◆◆◆ メール送信処理 <レッスン予約、レッスンキャンセル> と キャンセル時のポイント返却
                hisMailPatternCd = "";    // スクールメール・アカウント変更履歴テーブルの更新処理用のパターンコードの設定
                if (StringUtils.equals(NaikaraTalkConstants.CODE_CATEGORY_REPLY_KBN_YES, model.getReplyKbn())) {
                    // 応相談回答画面 ＯＫの場合 ※応相談時にポイント引当済、レッスンデータ作成済なので、講師予定予約テーブルのみ更新処理する

                    // ◆◆◆ 予約区分(予約状況)の判定
                    reservationKbnSet = NaikaraStringUtil.judgmentReservationKbn(NaikaraTalkConstants.RESERV_PROCESS_KBN_YES, loadData.getReservationKbn());

                    // ◆◆◆ 講師予定予約テーブルの更新処理 <ＯＫ：応相談確定>
                    prmDto = new ScheduleReservationTrnDto();
                    prmDto.setTeacherId(model.getUserId());                    // 講師ID
                    prmDto.setLessonDt(loadData.getLessonDt());                // レッスン日
                    prmDto.setLessonTmCd(loadData.getLessonTmCd());            // レッスン時刻コード
                    prmDto.setStudentId(loadData.getStudentId());              // 受講者ID
                    prmDto.setReservationKbn(loadData.getReservationKbn());    // 予約区分(予約状況)
                    prmDto.setReservationNo(loadData.getReservationNo());      // 予約No
                    prmDto.setCourseCd(loadData.getCourseCd());                // コースコード
                    prmDto.setUpdateCd(model.getUserId());                     // 更新者 = 講師ID
                    int resurtUpdSRT = logic.updateScheduleReservationTrn(prmDto, reservationKbnSet);
                    if (resurtUpdSRT == NaikaraTalkConstants.RETURN_CD_ERR_UPD) {
                        // 更新失敗
                        return NO_UPD_SCHEDULE_RESERVATION_TRN;
                    }

                    // ◆◆◆ 講師予定予約テーブルの更新完了の場合 ◆◆◆
                    // ◆◆◆ メール送信処理 <回答 OK：受講者>
                    sendMailBatch.sendMail(mailModel.getMailPatternCd(), mailModel.getSendFrom(), mailModel.getSendFromNm(),
                            mailModel.getSendIdList(), mailModel.getSendToList(),
                            mailModel.getCc(), mailModel.getBcc(), mailModel.getSubjectTitleList(),
                            mailModel.getMailTextList(), mailModel.getFile(), mailModel.getReservationNo());

                    // ◆◆◆ メール送信処理 <回答 OK：講師>
                    sendMailBatch.sendMail(mailModelT.getMailPatternCd(), mailModelT.getSendFrom(), mailModelT.getSendFromNm(),
                            mailModelT.getSendIdList(), mailModelT.getSendToList(),
                            mailModelT.getCc(), mailModelT.getBcc(), mailModelT.getSubjectTitleList(),
                            mailModelT.getMailTextList(), mailModelT.getFile(), mailModelT.getReservationNo());

                    // <レッスン予約:受講者> 用の値の設定
                    mailModel = new MailManagMstMiddleModel();
                    mailModel = this.setSendMailValueLessonResrvStudent(NaikaraTalkConstants.MAIL_PATTERN_CODE_LBC,
                            model, studentMstDto, loadData.getLessonDt(), loadData.getLessonTmNm(),
                            loadData.getCourseJnm(), mailModel);

                    // ◆◆◆ メール送信処理 <レッスン予約:受講者>
                    sendMailBatch.sendMail(mailModel.getMailPatternCd(), mailModel.getSendFrom(), mailModel.getSendFromNm(),
                            mailModel.getSendIdList(), mailModel.getSendToList(),
                            mailModel.getCc(), mailModel.getBcc(), mailModel.getSubjectTitleList(),
                            mailModel.getMailTextList(), mailModel.getFile(), mailModel.getReservationNo());

                    // <レッスン予約:講師> 用の値の設定
                    mailModel = new MailManagMstMiddleModel();
                    mailModel = this.setSendMailValueLessonResrvTeacher(model, studentMstDto, userMstDto, loadData.getLessonDt(), loadData.getLessonTmNm(),
                    		loadData.getCourseEnm(), loadData.getCourseJnm(), mailModel);

                    // ◆◆◆ メール送信処理 <レッスン予約:講師>
                    sendMailBatch.sendMail(mailModel.getMailPatternCd(), mailModel.getSendFrom(), mailModel.getSendFromNm(),
                            mailModel.getSendIdList(), mailModel.getSendToList(),
                            mailModel.getCc(), mailModel.getBcc(), mailModel.getSubjectTitleList(),
                            mailModel.getMailTextList(), mailModel.getFile(), mailModel.getReservationNo());

                    // スクールメール・アカウント変更履歴テーブルの更新処理用のパターンコードの設定
                    hisMailPatternCd = NaikaraTalkConstants.MAIL_PATTERN_APPROVE_REQUEST_YES;

                } else {
                    // 応相談回答画面 ＮＧの場合
                    List<PointProvisionDataListDto> ppdlDto = new ArrayList<PointProvisionDataListDto>();  // ポイント引当データリスト ※応相談時に引当済なので値設定必要なし
                    List<PointReleaseDataListDto> prdlDto = null;                                          // ポイント解除データリスト

                    // ◆◆◆ 予約区分(予約状況)の判定
                    reservationKbnSet = NaikaraStringUtil.judgmentReservationKbn(NaikaraTalkConstants.RESERV_PROCESS_KBN_NO, loadData.getReservationKbn());

                    // ◆◆◆ ポイントの返却、講師予定予約テーブルのデータ更新、レッスン予実テーブルのデータ削除
                    prdlDto = this.setPointReleaseDataList(model, loadData, reservationKbnSet);
                    List<LessonReserveCancelResultListDto> lessonCxlResultList = lessonCxlLogic.lessonReserveCancel(loadData.getStudentId(), ppdlDto, prdlDto);
                    if (lessonCxlResultList != null) {
                        if( lessonCxlResultList.get(0).getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_YES){
                            // ポイント返却 正常の場合

                            // ◆◆◆ メール送信処理 <回答 NG：受講者>
                            sendMailBatch.sendMail(mailModel.getMailPatternCd(), mailModel.getSendFrom(), mailModel.getSendFromNm(),
                                    mailModel.getSendIdList(), mailModel.getSendToList(),
                                    mailModel.getCc(), mailModel.getBcc(), mailModel.getSubjectTitleList(),
                                    mailModel.getMailTextList(), mailModel.getFile(), mailModel.getReservationNo());

                            // ◆◆◆ メール送信処理 <回答 OK：講師>
                            sendMailBatch.sendMail(mailModelT.getMailPatternCd(), mailModelT.getSendFrom(), mailModelT.getSendFromNm(),
                                    mailModelT.getSendIdList(), mailModelT.getSendToList(),
                                    mailModelT.getCc(), mailModelT.getBcc(), mailModelT.getSubjectTitleList(),
                                    mailModelT.getMailTextList(), mailModelT.getFile(), mailModelT.getReservationNo());

                            // ◆◆◆ メール送信処理 <レッスン取消> ◆◆◆

                            // <レッスン取消:受講者> 用の値の設定 ※受講者の設定パラメタ値は予約と取消は同じ
                            mailModel = new MailManagMstMiddleModel();
                            mailModel = this.setSendMailValueLessonResrvStudent(NaikaraTalkConstants.MAIL_PATTERN_CODE_ACC,
                                    model, studentMstDto, loadData.getLessonDt(), loadData.getLessonTmNm(),
                                    loadData.getCourseJnm(), mailModel);

                            // ◆◆◆ メール送信処理 <レッスン取消:受講者>
                            sendMailBatch.sendMail(mailModel.getMailPatternCd(), mailModel.getSendFrom(), mailModel.getSendFromNm(),
                                    mailModel.getSendIdList(), mailModel.getSendToList(),
                                    mailModel.getCc(), mailModel.getBcc(), mailModel.getSubjectTitleList(),
                                    mailModel.getMailTextList(), mailModel.getFile(), mailModel.getReservationNo());

                            // <レッスン取消:講師> 用の値の設定
                            mailModel = new MailManagMstMiddleModel();
                            mailModel = this.setSendMailValueLessonCxlTeacher(model, studentMstDto, userMstDto, loadData.getLessonDt(), loadData.getLessonTmNm(),
                                    loadData.getCourseEnm(), loadData.getCourseJnm(), mailModel);

                            // ◆◆◆ メール送信処理 <レッスン取消:講師>
                            sendMailBatch.sendMail(mailModel.getMailPatternCd(), mailModel.getSendFrom(), mailModel.getSendFromNm(),
                                    mailModel.getSendIdList(), mailModel.getSendToList(),
                                    mailModel.getCc(), mailModel.getBcc(), mailModel.getSubjectTitleList(),
                                    mailModel.getMailTextList(), mailModel.getFile(), mailModel.getReservationNo());
                        } else {
                            // 共通部品：レッスンの予約・取消処理 失敗
                            return COM_LESSON_RESERVE_CANCEL;
                        }
                    } else {
                        // 共通部品：レッスンの予約・取消処理 失敗
                        return COM_LESSON_RESERVE_CANCEL;
                    }

                    // スクールメール・アカウント変更履歴テーブルの更新処理用のパターンコードの設定
                    hisMailPatternCd = NaikaraTalkConstants.MAIL_PATTERN_APPROVE_REQUEST_NO;
                }

                // ◆◆◆ スクールメール・アカウント変更履歴テーブルの更新処理
                sAMHiLogic.smailActHistoryInsertMain(hisMailPatternCd, model.getUserId(), model.getUserId());

            }

            // ◆◆◆ コミット
            conn.commit();

            // 返却
            return retValue;

        } catch (Exception e) {
            try {
                // ◆◆◆ ロールバック
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

    }


    /**
     * メール送信内容の設定＜受講者向け＞<br>
     * <br>
     * メール送信内容の設定処理を行う。<br>
     * <br>
     * @param model TeacherApproveRequestModel<br>
     * @param studentMstDto StudentMstDto<br>
     * @param lessonDt String<br>
     * @param lessonTmNm String<br>
     * @param mailModel MailManagMstMiddleModel<br>
     * @return MailManagMstMiddleModel<br>
     * @throws NaiException
     */
    private MailManagMstMiddleModel setSendMailValueStudent(TeacherApproveRequestModel model,
            StudentMstDto studentMstDto, String lessonDt, String lessonTmNm,
            MailManagMstMiddleModel mailModel) throws NaiException {

        String mailPatternCd = "";    // メールパターンコード
        String jNm = "";              // 受講者マスタ：名前(姓) & 名前(名)
        String nickNm = "";           // 受講者マスタ：ニックネーム
        String address = "";          // 受講者マスタ：メールアドレス1
        String studentId = "";        // 受講者マスタ：受講者ID

        try {

            if (studentMstDto != null) {
                address = (String) NaikaraStringUtil.nvlString(studentMstDto.getMailAddress1());
                jNm = NaikaraStringUtil.unionName(studentMstDto.getFamilyJnm(), studentMstDto.getFirstJnm());
                nickNm = (String) NaikaraStringUtil.nvlString(studentMstDto.getNickNm());
                studentId = studentMstDto.getStudentId();
                if (nickNm == null || StringUtils.isEmpty(nickNm)) {
                    nickNm = jNm;  // 法人対策
                }
            }

            // 宛先IDリスト
            List<String> sendIdList = new ArrayList<String>();
            sendIdList.add(studentId);

            // 宛先リスト
            List<String> sendToList = new ArrayList<String>();
            sendToList.add(address);

            // ◆◆◆ 回答区分の判定
            if (StringUtils.equals(NaikaraTalkConstants.CODE_CATEGORY_REPLY_KBN_YES, model.getReplyKbn())) {
                // メールパターンコード：応相談回答画面 ＯＫ＜受講者＞
                mailPatternCd = NaikaraTalkConstants.MAIL_PATTERN_CODE_AOC;
                // SubjectTitleList(件名リスト)、MailTextList(本文リスト)の設定
                mailModel = this.setSendMailValueSubjectTextYes(model, nickNm, lessonDt, lessonTmNm, mailModel);
            } else {
                // メールパターンコード：応相談回答画面 ＮＧ＜受講者＞
                mailPatternCd = NaikaraTalkConstants.MAIL_PATTERN_CODE_ANC;
                // SubjectTitleList(件名リスト)、MailTextList(本文リスト)の設定
                mailModel = this.setSendMailValueSubjectTextNo(model, nickNm, lessonDt, lessonTmNm, mailModel);
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

            // ◆◆◆ 返却
            return mailModel;

        } catch (Exception e) {
            e.printStackTrace();
            throw new NaiException(e);
        }

    }

    /**
     * メール送信内容の設定処理（回答：ＯＫ）＜受講者向け＞<br>
     * <br>
     * メール送信内容の設定処理（回答：ＯＫ）＜受講者向け＞を行う。<br>
     * <br>
     * @param model TeacherApproveRequestModel<br>
     * @param studentNickNm String<br>
     * @param lessonDt String<br>
     * @param lessonTmCd String<br>
     * @param mailModel MailManagMstMiddleModel<br>
     * @return MailManagMstMiddleModel<br>
     * @throws NaiException
     */
    private MailManagMstMiddleModel setSendMailValueSubjectTextYes(TeacherApproveRequestModel model,
            String studentNickNm, String lessonDt, String lessonTmNm,
            MailManagMstMiddleModel mailModel) throws NaiException {

        // 件名リスト 画面の｢件名｣
        List<String> subjectTitleList = new ArrayList<String>();
        subjectTitleList.add(model.getSubject());

        // 本文リスト
        List<List<String>> mailTextList = new ArrayList<List<String>>();
        List<String> textList = new ArrayList<String>();

        // 本文リスト(0個目) 受講者ニックネーム、レッスン日 YYYY/MM/DD
        textList = new ArrayList<String>();
        textList.add(studentNickNm);
        textList.add(NaikaraStringUtil.converToYYYY_MM_DD(lessonDt));
        mailTextList.add(0, textList);

        // 本文リスト(1個目) レッスン時刻 HH:MM
        textList = new ArrayList<String>();
        textList.add(lessonTmNm.substring(0, 5));
        mailTextList.add(1, textList);

        // 本文リスト(2個目) ”” (設定なし)
        textList = new ArrayList<String>();
        textList.add(NaikaraTalkConstants.BRANK);
        mailTextList.add(2, textList);

        // 本文リスト(3個目) ”” (設定なし)
        textList = new ArrayList<String>();
        textList.add(NaikaraTalkConstants.BRANK);
        mailTextList.add(3, textList);

        // 本文リスト(4個目) ”” (設定なし)
        textList = new ArrayList<String>();
        textList.add(NaikaraTalkConstants.BRANK);
        mailTextList.add(4, textList);

        // 本文リスト(5個目) 画面．メール本文
        textList = new ArrayList<String>();
        textList.add(model.getEmailText());
        mailTextList.add(5, textList);

        // 本文リスト(6個目) ”” (設定なし) ～ 本文リスト(13個目) ”” (設定なし)
        for(int i = 6, n = 13; i < n; i++) {
            // 本文リスト(X個目) ”” (設定なし)
            textList = new ArrayList<String>();
            textList.add(NaikaraTalkConstants.BRANK);
            mailTextList.add(i, textList);
        }

        mailModel.setSubjectTitleList(subjectTitleList);    // 件名リスト
        mailModel.setMailTextList(mailTextList);            // 本文リスト

        // ◆◆◆ 返却
        return mailModel;

    }

    /**
     * メール送信内容の設定処理（回答：ＮＧ）＜受講者向け＞<br>
     * <br>
     * メール送信内容の設定処理（回答：ＮＧ）＜受講者向け＞を行う。<br>
     * <br>
     * @param model TeacherApproveRequestModel<br>
     * @param studentNickNm String<br>
     * @param lessonDt String<br>
     * @param lessonTmCd String<br>
     * @param mailModel MailManagMstMiddleModel<br>
     * @return MailManagMstMiddleModel<br>
     * @throws NaiException
     */
    private MailManagMstMiddleModel setSendMailValueSubjectTextNo(TeacherApproveRequestModel model,
            String studentNickNm, String lessonDt, String lessonTmNm,
            MailManagMstMiddleModel mailModel) throws NaiException {

        // 件名リスト 「件名」
        List<String> subjectTitleList = new ArrayList<String>();
        subjectTitleList.add(model.getSubject());

        // 本文リスト
        List<List<String>> mailTextList = new ArrayList<List<String>>();
        List<String> textList = new ArrayList<String>();

        // 本文リスト(0個目) 受講者ニックネーム
        textList = new ArrayList<String>();
        textList.add(studentNickNm);
        mailTextList.add(0, textList);

        // 本文リスト(1個目) ”” (設定なし)
        textList = new ArrayList<String>();
        textList.add(NaikaraTalkConstants.BRANK);
        mailTextList.add(1, textList);

        // 本文リスト(2個目) ”” (設定なし)
        textList = new ArrayList<String>();
        textList.add(NaikaraTalkConstants.BRANK);
        mailTextList.add(2, textList);

        // 本文リスト(3個目) 画面．メール本文
        textList = new ArrayList<String>();
        textList.add(model.getEmailText());
        mailTextList.add(3, textList);

        // 本文リスト(4個目) ”” (設定なし) ～ 本文リスト(10個目) ”” (設定なし)
        for(int i = 4, n = 11; i < n; i++) {
            // 本文リスト(X個目) ”” (設定なし)
            textList = new ArrayList<String>();
            textList.add(NaikaraTalkConstants.BRANK);
            mailTextList.add(i, textList);
        }

        mailModel.setSubjectTitleList(subjectTitleList);    // 件名リスト
        mailModel.setMailTextList(mailTextList);            // 本文リスト

        // ◆◆◆ 返却
        return mailModel;

    }


    /**
     * メール送信内容の設定＜講師向け＞<br>
     * <br>
     * メール送信内容の設定処理を行う。＜講師向け＞<br>
     * <br>
     * @param model TeacherApproveRequestModel<br>
     * @param studentMstDto StudentMstDto<br>
     * @param userMstDto userMstDto<br>
     * @param lessonDt String<br>
     * @param lessonTmNm String<br>
     * @param mailModel MailManagMstMiddleModel<br>
     * @return MailManagMstMiddleModel<br>
     * @throws NaiException
     */
    private MailManagMstMiddleModel setSendMailValueTeacher(TeacherApproveRequestModel model,
            StudentMstDto studentMstDto, UserMstDto userMstDto, String lessonDt, String lessonTmNm,
            MailManagMstMiddleModel mailModel) throws NaiException {

        String mailPatternCd = "";    // メールパターンコード
        String jNm = "";              // 受講者マスタ：名前(姓) & 名前(名)
        String nickNm = "";           // 受講者マスタ：ニックネーム

        String teacherAddress = "";   // 講師マスタ：メールアドレス1

        try {

            if (studentMstDto != null) {
                jNm = NaikaraStringUtil.unionName(studentMstDto.getFamilyJnm(), studentMstDto.getFirstJnm());
                nickNm = (String) NaikaraStringUtil.nvlString(studentMstDto.getNickNm());
                if (nickNm == null || StringUtils.isEmpty(nickNm)) {
                    nickNm = jNm;  // 法人対策
                }
            }

            if (userMstDto != null) {
                teacherAddress = (String) NaikaraStringUtil.nvlString(userMstDto.getMailAddress1());
            }

            // 宛先IDリスト
            List<String> sendIdList = new ArrayList<String>();
            sendIdList.add(model.getUserId());

            // 宛先リスト
            List<String> sendToList = new ArrayList<String>();
            sendToList.add(teacherAddress);

            // ◆◆◆ 回答区分の判定
            if (StringUtils.equals(NaikaraTalkConstants.CODE_CATEGORY_REPLY_KBN_YES, model.getReplyKbn())) {
                // メールパターンコード：応相談回答画面 ＯＫ＜講師＞
                mailPatternCd = NaikaraTalkConstants.MAIL_PATTERN_CODE_AOT;
                // SubjectTitleList(件名リスト)、MailTextList(本文リスト)の設定
                mailModel = this.setSendMailValueSubjectTextYesTeacher(model, nickNm, lessonDt, lessonTmNm, mailModel);
            } else {
                // メールパターンコード：応相談回答画面 ＮＧ＜講師＞
                mailPatternCd = NaikaraTalkConstants.MAIL_PATTERN_CODE_ANT;
                // SubjectTitleList(件名リスト)、MailTextList(本文リスト)の設定
                mailModel = this.setSendMailValueSubjectTextNoTeacher(model, nickNm, lessonDt, lessonTmNm, mailModel);
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

            // ◆◆◆ 返却
            return mailModel;

        } catch (Exception e) {
            e.printStackTrace();
            throw new NaiException(e);
        }

    }


    /**
     * メール送信内容の設定処理（回答：ＯＫ）＜講師向け＞<br>
     * <br>
     * メール送信内容の設定処理（回答：ＯＫ）＜講師向け＞を行う。<br>
     * <br>
     * @param model TeacherApproveRequestModel<br>
     * @param studentNickNm String<br>
     * @param lessonDt String<br>
     * @param lessonTmCd String<br>
     * @param mailModel MailManagMstMiddleModel<br>
     * @return MailManagMstMiddleModel<br>
     * @throws NaiException
     */
    private MailManagMstMiddleModel setSendMailValueSubjectTextYesTeacher(TeacherApproveRequestModel model,
            String studentNickNm, String lessonDt, String lessonTmNm,
            MailManagMstMiddleModel mailModel) throws NaiException {

        // 件名リスト 画面の｢件名｣
        List<String> subjectTitleList = new ArrayList<String>();
        subjectTitleList.add(model.getSubject());

        // 本文リスト
        List<List<String>> mailTextList = new ArrayList<List<String>>();
        List<String> textList = new ArrayList<String>();

        // 本文リスト(0個目) 受講者ニックネーム、レッスン日&レッスン時刻 YY/MM/DD HH:MM
        textList.add(studentNickNm);
        String yymmdd = NaikaraStringUtil.converToYY_MM_DD(lessonDt);
        textList.add(NaikaraStringUtil.converToYYMMDDHHMM(yymmdd, lessonTmNm));
        mailTextList.add(0, textList);

        // 本文リスト(1個目) ”” (設定なし)
        textList = new ArrayList<String>();
        textList.add(NaikaraTalkConstants.BRANK);
        mailTextList.add(1, textList);

        // 本文リスト(2個目) 受講者ニックネーム、レッスン日 YYYY/MM/DD
        textList = new ArrayList<String>();
        textList.add(studentNickNm);
        textList.add(NaikaraStringUtil.converToYYYY_MM_DD(lessonDt));
        mailTextList.add(2, textList);

        // 本文リスト(3個目) レッスン時刻 HH:MM
        textList = new ArrayList<String>();
        textList.add(lessonTmNm.substring(0, 5));
        mailTextList.add(3, textList);

        // 本文リスト(4個目) ”” (設定なし)
        textList = new ArrayList<String>();
        textList.add(NaikaraTalkConstants.BRANK);
        mailTextList.add(4, textList);

        // 本文リスト(5個目) ”” (設定なし)
        textList = new ArrayList<String>();
        textList.add(NaikaraTalkConstants.BRANK);
        mailTextList.add(5, textList);

        // 本文リスト(6個目) ”” (設定なし)
        textList = new ArrayList<String>();
        textList.add(NaikaraTalkConstants.BRANK);
        mailTextList.add(6, textList);

        // 本文リスト(7個目) 画面．メール本文
        textList = new ArrayList<String>();
        textList.add(model.getEmailText());
        mailTextList.add(7, textList);

        // 本文リスト(8個目) ”” (設定なし) ～ 本文リスト(16個目) ”” (設定なし)
        for(int i = 8, n = 17; i < n; i++) {
            // 本文リスト(X個目) ”” (設定なし)
            textList = new ArrayList<String>();
            textList.add(NaikaraTalkConstants.BRANK);
            mailTextList.add(i, textList);
        }

        mailModel.setSubjectTitleList(subjectTitleList);    // 件名リスト
        mailModel.setMailTextList(mailTextList);            // 本文リスト

        // ◆◆◆ 返却
        return mailModel;

    }

    /**
     * メール送信内容の設定処理（回答：ＮＧ）＜講師向け＞<br>
     * <br>
     * メール送信内容の設定処理（回答：ＮＧ）＜講師向け＞を行う。<br>
     * <br>
     * @param model TeacherApproveRequestModel<br>
     * @param studentNickNm String<br>
     * @param lessonDt String<br>
     * @param lessonTmCd String<br>
     * @param mailModel MailManagMstMiddleModel<br>
     * @return MailManagMstMiddleModel<br>
     * @throws NaiException
     */
    private MailManagMstMiddleModel setSendMailValueSubjectTextNoTeacher(TeacherApproveRequestModel model,
            String studentNickNm, String lessonDt, String lessonTmNm,
            MailManagMstMiddleModel mailModel) throws NaiException {

        // 件名リスト 「件名」
        List<String> subjectTitleList = new ArrayList<String>();
        subjectTitleList.add(model.getSubject());

        // 本文リスト
        List<List<String>> mailTextList = new ArrayList<List<String>>();
        List<String> textList = new ArrayList<String>();

        // 本文リスト(0個目) 受講者ニックネーム
        textList.add(studentNickNm);
        mailTextList.add(0, textList);

        // 本文リスト(1個目) 、レッスン日&レッスン時刻 YY/MM/DD HH:MM
        textList = new ArrayList<String>();
        String yymmdd = NaikaraStringUtil.converToYY_MM_DD(lessonDt);
        textList.add(NaikaraStringUtil.converToYYMMDDHHMM(yymmdd, lessonTmNm));
        mailTextList.add(1, textList);

        // 本文リスト(2個目) ”” (設定なし)
        textList = new ArrayList<String>();
        textList.add(NaikaraTalkConstants.BRANK);
        mailTextList.add(2, textList);

        // 本文リスト(3個目) 受講者ニックネーム
        textList = new ArrayList<String>();
        textList.add(studentNickNm);
        mailTextList.add(3, textList);

        // 本文リスト(4個目) ”” (設定なし)
        textList = new ArrayList<String>();
        textList.add(NaikaraTalkConstants.BRANK);
        mailTextList.add(4, textList);

        // 本文リスト(5個目) ”” (設定なし)
        textList = new ArrayList<String>();
        textList.add(NaikaraTalkConstants.BRANK);
        mailTextList.add(5, textList);

        // 本文リスト(6個目) 画面．メール本文
        textList = new ArrayList<String>();
        textList.add(model.getEmailText());
        mailTextList.add(6, textList);

        // 本文リスト(7個目) ”” (設定なし) ～ 本文リスト(15個目) ”” (設定なし)
        for(int i = 7, n = 16; i < n; i++) {
            // 本文リスト(X個目) ”” (設定なし)
            textList = new ArrayList<String>();
            textList.add(NaikaraTalkConstants.BRANK);
            mailTextList.add(i, textList);
        }

        mailModel.setSubjectTitleList(subjectTitleList);    // 件名リスト
        mailModel.setMailTextList(mailTextList);            // 本文リスト

        // ◆◆◆ 返却
        return mailModel;

    }


    /**
     * メール送信内容の設定（受講者：レッスン予約／取消）<br>
     * <br>
     * メール送信内容の設定処理を行う。<br>
     * <br>
     * @param pMailPatternCd String<br>
     * @param model TeacherApproveRequestModel<br>
     * @param studentMstDto StudentMstDto<br>
     * @param lessonDt String<br>
     * @param lessonTmNm String<br>
     * @param mailModel MailManagMstMiddleModel<br>
     * @return MailManagMstMiddleModel<br>
     * @throws NaiException
     */
    private MailManagMstMiddleModel setSendMailValueLessonResrvStudent(String pMailPatternCd,
            TeacherApproveRequestModel model,
            StudentMstDto studentMstDto, String lessonDt, String lessonTmNm,
            String courseNm,
            MailManagMstMiddleModel mailModel) throws NaiException {

        String mailPatternCd = "";    // メールパターンコード
        String jNm = "";              // 受講者マスタ：名前(姓) & 名前(名)
        String nickNm = "";           // 受講者マスタ：ニックネーム
        String address = "";          // 受講者マスタ：メールアドレス1
        String studentId = "";        // 受講者マスタ：受講者ID

        // ◆◆◆ レッスン予約(受講者)
        mailPatternCd = pMailPatternCd;

        try {

            if (studentMstDto != null) {
                address = (String) NaikaraStringUtil.nvlString(studentMstDto.getMailAddress1());
                jNm = NaikaraStringUtil.unionName(studentMstDto.getFamilyJnm(), studentMstDto.getFirstJnm());
                nickNm = (String) NaikaraStringUtil.nvlString(studentMstDto.getNickNm());
                studentId = studentMstDto.getStudentId();
                if (nickNm == null || StringUtils.isEmpty(nickNm)) {
                    nickNm = jNm;  // 法人対策
                }
            }

            // 宛先IDリスト ※ ログイン情報：受講者ID
            List<String> sendIdList = new ArrayList<String>();
            sendIdList.add(studentId);

            // 宛先リスト
            List<String> sendToList = new ArrayList<String>();
            sendToList.add(address);

            // 件名リスト (なし) ”” (設定なし)
            List<String> subjectTitleList = new ArrayList<String>();

            // 本文リスト
            List<List<String>> mailTextList = new ArrayList<List<String>>();
            List<String> textList = new ArrayList<String>();

            // 本文リスト(0個目) 名前の編集(受講者マスタ．名前(姓)、受講者マスタ．名前(名))
            textList.add(jNm);
            mailTextList.add(0, textList);

            // 本文リスト (1個目) ～ (4個目) ”” (設定なし)
            textList = new ArrayList<String>();
            textList.add(NaikaraTalkConstants.BRANK);
            mailTextList.add(1, textList);
            mailTextList.add(2, textList);
            mailTextList.add(3, textList);
            mailTextList.add(4, textList);

            // 本文リスト (5個目) 一覧の｢レッスン日｣ & ” ” & 一覧の｢レッスン時刻名｣
            textList = new ArrayList<String>();
            textList.add(NaikaraStringUtil.converToYYMMDDHHMM(NaikaraStringUtil.converToYYYY_MM_DD(lessonDt), lessonTmNm));
            mailTextList.add(5, textList);

            // 本文リスト (6個目) 一覧の｢コース名｣
            textList = new ArrayList<String>();
            textList.add(courseNm);
            mailTextList.add(6, textList);

            // 本文リスト (7個目) ｢講師名(ニックネーム)｣
            textList = new ArrayList<String>();
            textList.add(model.getUserNm());
            mailTextList.add(7, textList);

            // 本文リスト (8個目) ～ (20個目) ”” (設定なし)
            textList = new ArrayList<String>();
            textList.add(NaikaraTalkConstants.BRANK);
            for(int i = 8, n = 21; i < n; i++) {
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
            throw new NaiException(e);
        }

    }

    /**
     * メール送信内容の設定（講師：レッスン予約）<br>
     * <br>
     * メール送信内容の設定処理を行う。<br>
     * <br>
     * @param model TeacherApproveRequestModel<br>
     * @param studentMstDto StudentMstDto<br>
     * @param userMstDto UserMstDto<br>
     * @param lessonDt String<br>
     * @param lessonTmNm String<br>
     * @param mailModel MailManagMstMiddleModel<br>
     * @return MailManagMstMiddleModel<br>
     * @throws NaiException
     */
    private MailManagMstMiddleModel setSendMailValueLessonResrvTeacher(TeacherApproveRequestModel model,
            StudentMstDto studentMstDto, UserMstDto userMstDto, String lessonDt, String lessonTmNm,
            String courseNmE, String courseNmJ, MailManagMstMiddleModel mailModel) throws NaiException {

        String mailPatternCd = "";    // メールパターンコード
        String studentJNm = "";       // 受講者マスタ：名前(姓) & 名前(名)
        String studentNickNm = "";    // 受講者マスタ：ニックネーム
        String teacherAddress = "";   // 利用者マスタ：メールアドレス1

        // ◆◆◆ レッスン予約(講師)
        mailPatternCd = NaikaraTalkConstants.MAIL_PATTERN_CODE_LBT;

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

            // 共通部品：日付編集（講師用）(画面の｢レッスン日｣、画面の｢レッスン時刻名｣)
            String lessonDtTm = NaikaraStringUtil.converToDDMMYYHHMM(NaikaraStringUtil.converToYY_MM_DD(lessonDt), lessonTmNm);

            // 宛先IDリスト ※ 講師ID
            List<String> sendIdList = new ArrayList<String>();
            sendIdList.add(model.getUserId());

            // 宛先リスト
            List<String> sendToList = new ArrayList<String>();
            sendToList.add(teacherAddress);

            // 件名リスト (なし) ”” (設定なし)
            List<String> subjectTitleList = new ArrayList<String>();
            // (0個目) 共通部品：日付編集（講師用）(画面の｢レッスン日｣、画面の｢レッスン時刻名｣)
            subjectTitleList.add(lessonDtTm);

            // 本文リスト
            List<List<String>> mailTextList = new ArrayList<List<String>>();
            List<String> textList = new ArrayList<String>();

            // 本文リスト(0個目) 講師名(ニックネーム)
            textList.add(model.getUserNm());
            mailTextList.add(0, textList);

            // 本文リスト (1個目) 受講者ニックネーム、共通部品：日付編集（講師用）(一覧の｢レッスン日｣、一覧の｢レッスン時刻｣)
            textList = new ArrayList<String>();
            textList.add(studentNickNm);
            textList.add(lessonDtTm);
            mailTextList.add(1, textList);

            // 本文リスト (2個目) 受講者ニックネーム、共通部品：日付編集（講師用）(一覧の｢レッスン日｣、一覧の｢レッスン時刻｣)
            textList = new ArrayList<String>();
            textList.add(studentNickNm);
            textList.add(lessonDtTm);
            mailTextList.add(2, textList);

            // 本文リスト (3個目) ”” (設定なし)
            textList = new ArrayList<String>();
            textList.add(NaikaraTalkConstants.BRANK);
            mailTextList.add(3, textList);

            // 本文リスト (4個目) 変数｢コース名(英語)｣
            textList = new ArrayList<String>();
            textList.add(courseNmE);
            mailTextList.add(4, textList);

            // 本文リスト (5個目) 一覧の｢コース名｣(日本語)
            textList = new ArrayList<String>();
            textList.add(courseNmJ);
            mailTextList.add(5, textList);

            // 本文リスト (6個目) ～ (30個目) ”” (設定なし)
            textList = new ArrayList<String>();
            textList.add(NaikaraTalkConstants.BRANK);
            for(int i = 6, n = 31; i < n; i++) {
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
            throw new NaiException(e);
        }

    }


    /**
     * メール送信内容の設定（講師：レッスン取消）<br>
     * <br>
     * メール送信内容の設定処理を行う。<br>
     * <br>
     * @param model TeacherApproveRequestModel<br>
     * @param studentMstDto StudentMstDto<br>
     * @param userMstDto UserMstDto<br>
     * @param lessonDt String<br>
     * @param lessonTmNm String<br>
     * @param mailModel MailManagMstMiddleModel<br>
     * @return MailManagMstMiddleModel<br>
     * @throws NaiException
     */
    private MailManagMstMiddleModel setSendMailValueLessonCxlTeacher(TeacherApproveRequestModel model,
            StudentMstDto studentMstDto, UserMstDto userMstDto, String lessonDt, String lessonTmNm,
            String courseNmE, String courseNmJ, MailManagMstMiddleModel mailModel) throws NaiException {

        String mailPatternCd = "";    // メールパターンコード
        String studentJNm = "";       // 受講者マスタ：名前(姓) & 名前(名)
        String studentNickNm = "";    // 受講者マスタ：ニックネーム
        String teacherAddress = "";   // 利用者マスタ：メールアドレス1

        // ◆◆◆ レッスン取消(講師)
        mailPatternCd = NaikaraTalkConstants.MAIL_PATTERN_CODE_ACT;

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

            // 共通部品：日付編集（講師用）(画面の｢レッスン日｣、画面の｢レッスン時刻名｣)
            String lessonDtTm = NaikaraStringUtil.converToDDMMYYHHMM(NaikaraStringUtil.converToYY_MM_DD(lessonDt), lessonTmNm);

            // 宛先IDリスト ※ 講師ID
            List<String> sendIdList = new ArrayList<String>();
            sendIdList.add(model.getUserId());

            // 宛先リスト
            List<String> sendToList = new ArrayList<String>();
            sendToList.add(teacherAddress);

            // 件名リスト (なし) ”” (設定なし)
            List<String> subjectTitleList = new ArrayList<String>();
            // (0個目) 共通部品：日付編集（講師用）(画面の｢レッスン日｣、画面の｢レッスン時刻名｣)
            subjectTitleList.add(lessonDtTm);

            // 本文リスト
            List<List<String>> mailTextList = new ArrayList<List<String>>();
            List<String> textList = new ArrayList<String>();

            // 本文リスト(0個目) 講師名(ニックネーム)
            textList.add(model.getUserNm());
            mailTextList.add(0, textList);

            // 本文リスト (1個目) 受講者ニックネーム、共通部品：日付編集（講師用）(一覧の｢レッスン日｣、一覧の｢レッスン時刻｣)
            textList = new ArrayList<String>();
            textList.add(studentNickNm);
            textList.add(lessonDtTm);
            mailTextList.add(1, textList);

            // 本文リスト (2個目) 受講者ニックネーム、共通部品：日付編集（講師用）(一覧の｢レッスン日｣、一覧の｢レッスン時刻｣)
            textList = new ArrayList<String>();
            textList.add(studentNickNm);
            textList.add(lessonDtTm);
            mailTextList.add(2, textList);

            // 本文リスト (3個目) コース名(英語)
            textList = new ArrayList<String>();
            textList.add(courseNmE);
            mailTextList.add(3, textList);

            // 本文リスト (4個目) コース名(日本語)
            textList = new ArrayList<String>();
            textList.add(courseNmJ);
            mailTextList.add(4, textList);

            // 本文リスト (5個目) ～ (16個目) ”” (設定なし)
            textList = new ArrayList<String>();
            textList.add(NaikaraTalkConstants.BRANK);
            for(int i = 5, n = 17; i < n; i++) {
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
            throw new NaiException(e);
        }

    }


    /**
     * 解除データリストの作成処理。<br>
     * <br>
     * 解除データリストの作成処理を行う。<br>
     * <br>
     * @param model 画面ヘッダ部の情報<br>
     * @param loadData 画面一覧部の情報<br>
     * @param reservationKbnSet 予約区分(予約状況)[更新値用]<br>
     * @return List<PointReleaseDataListDto> ポイント解除DTOリスト<br>
     * @throws NaiException
     */
    private List<PointReleaseDataListDto> setPointReleaseDataList(TeacherApproveRequestModel model,
    		TeacherApproveRequestListModel loadData, String reservationKbnSet) throws NaiException {

        List<PointReleaseDataListDto> prdlDtoList = new ArrayList<PointReleaseDataListDto>();
        PointReleaseDataListDto prdlDto = new PointReleaseDataListDto();

        // ◆◆◆ ポイント解除DTOの設定
        prdlDto.setRsvNoPurchaseId(loadData.getReservationNo());         // 予約No
        prdlDto.setTeacherId(model.getUserId());                         // 講師ID
        prdlDto.setLessonDt(loadData.getLessonDt());                     // レッスン日※YYYYMMDD形式
        prdlDto.setLessonTmCd(loadData.getLessonTmCd());                 // ｢レッスン時刻｣のコード
        prdlDto.setCourseCd(loadData.getCourseCd());                     // コースコード
        prdlDto.setReservationKbnSet(reservationKbnSet);                 // 予約区分(予約状況)[更新値用]
        prdlDto.setReservationKbnWhere(loadData.getReservationKbn());    // 予約区分(予約状況)[条件句用]

        prdlDto.setUpdateCd(model.getUserId());                          // 更新者ID

        // ◆◆◆ リストへの追加
        prdlDtoList.add(prdlDto);

        // ◆◆◆ 返却
        return prdlDtoList;

    }

}
