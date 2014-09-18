package com.naikara_talk.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.batch.SendMailBatch;
import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.StudentMstDto;
import com.naikara_talk.dto.UserMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.MailWithAttachmentsSendLogic;
import com.naikara_talk.logic.SmailAccountHistoryLogic;
import com.naikara_talk.model.MailWithAttachmentsSendModel;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様(個人)_初期登録。<br>
 * <b>クラス名称　　　:</b>マイページ送信処理Serviceクラス。<br>
 * <b>クラス概要　　　:</b>マイページ送信処理Service。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/02 TECS 新規作成。
 *                     </b>2014/01/03 TECS 変更 スクールのメール送信・受信履歴照会に伴う対応
 *                     </b>2014/04/10 TECS 変更 コメント追加
 */
public class MailWithAttachmentsSendSendService implements ActionService {

    /** コロン */
    public static final String COLON = "：";

    /**
     * メール送信<br>
     * <br>
     * メール送信を行う<br>
     * <br>
     * @param MailWithAttachmentsSendModel 画面パラメータ<br>
     * @return なし<br>
     * @throws Exception
     */
    public void sendMail(MailWithAttachmentsSendModel model) throws NaiException {

        // 前画面(引数)の｢遷移元画面ID｣="TeacherMyPage"又は"TeacherLessonManagement"の場合
        if (StringUtils.equals(NaikaraTalkConstants.TEACHER_MY_PAGE_LOAD, model.getPageId())
                || StringUtils.equals(NaikaraTalkConstants.TEACHER_LESSON_MANAGEMENT_LOAD, model.getPageId())) {

            // メール送信(講師⇒受講者)
            sendMailTeacher(model);
        }

        // 前画面(引数)の｢遷移元画面ID｣="StudentMyPage"の場合
        if (StringUtils.equals(NaikaraTalkConstants.STUDENT_MY_PAGE_LOAD, model.getPageId())) {

            // メール送信(受講者⇒講師)
            sendMailStudent(model);
        }
    }

    /**
     * メール送信(講師⇒受講者)<br>
     * <br>
     * メール送信(講師⇒受講者)を行う<br>
     * <br>
     * @param MailWithAttachmentsSendModel 画面パラメータ<br>
     * @return なし<br>
     * @throws Exception
     */
    private void sendMailTeacher(MailWithAttachmentsSendModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            // ロジックの初期化
            MailWithAttachmentsSendLogic mailWithAttachmentsSendLogic = new MailWithAttachmentsSendLogic(conn);

            // Model値をDTOにセット
            StudentMstDto prmDto = this.modelToStudentDto(model);

            // 検索を実行
            StudentMstDto resultDto = mailWithAttachmentsSendLogic.selectStudent(prmDto);

            // メールパターンコード
            String mailPatternCd = NaikaraTalkConstants.MAIL_PATTERN_CODE_SMC;
            // 送信者
            String sendFrom = NaikaraTalkConstants.BRANK;
            // 送信者名
            String sendFromNm = NaikaraTalkConstants.BRANK;
            // 宛先IDリスト
            List<String> sendIdList = new ArrayList<String>();
            sendIdList.add(model.getStudentId());
            // 宛先アドレスリスト
            List<String> sendToList = new ArrayList<String>();
            sendToList.add(resultDto.getMailAddress1());
            // CC
            String cc = NaikaraTalkConstants.BRANK;
            // BCC
            String bcc = NaikaraTalkConstants.BRANK;
            // 件名リスト
            List<String> subjectTitleList = new ArrayList<String>();
            // 本文リスト
            List<List<String>> mailTextList = new ArrayList<List<String>>();
            List<String> textList0 = new ArrayList<String>();
            textList0.add(NaikaraStringUtil.unionName(resultDto.getFamilyJnm(), resultDto.getFirstJnm()));
            mailTextList.add(textList0);
            List<String> textList1_4 = new ArrayList<String>();
            textList1_4.add(NaikaraTalkConstants.BRANK);
            mailTextList.add(textList1_4);
            mailTextList.add(textList1_4);
            mailTextList.add(textList1_4);
            mailTextList.add(textList1_4);
            List<String> textList5 = new ArrayList<String>();
            String lessonTmNm = model.getLessonTmNm();
            if (lessonTmNm.length() > 5) {
                lessonTmNm = lessonTmNm.substring(0, 5);
            }
            textList5.add(new StringBuffer().append(NaikaraStringUtil.converToYYYY_MM_DD(model.getLessonDt()))
                    .append(NaikaraTalkConstants.HALF_SPACE).append(lessonTmNm).toString());
            mailTextList.add(textList5);
            List<String> textList6 = new ArrayList<String>();
            textList6.add(model.getCourseJnmAll());
            mailTextList.add(textList6);
            List<String> textList7 = new ArrayList<String>();
            textList7.add(model.getTeacherNickNm());
            mailTextList.add(textList7);
            List<String> textList8_9 = new ArrayList<String>();
            textList8_9.add(NaikaraTalkConstants.BRANK);
            mailTextList.add(textList8_9);
            mailTextList.add(textList8_9);
            List<String> textList10 = new ArrayList<String>();
            textList10.add(model.getCommentTxt());
            mailTextList.add(textList10);
            List<String> textList11_17 = new ArrayList<String>();
            textList11_17.add(NaikaraTalkConstants.BRANK);
            mailTextList.add(textList11_17);
            mailTextList.add(textList11_17);
            mailTextList.add(textList11_17);
            mailTextList.add(textList11_17);
            mailTextList.add(textList11_17);
            mailTextList.add(textList11_17);
            mailTextList.add(textList11_17);
            // 添付
            String file = model.getTempFileFileName();
            // 予約No
            String reservationNo = model.getReservationNo();

            SendMailBatch sendMailBatch = new SendMailBatch(conn);

            sendMailBatch.sendMail(mailPatternCd, sendFrom, sendFromNm, sendIdList, sendToList, cc, bcc,
                    subjectTitleList, mailTextList, file, reservationNo);

            // 2014/01/03-スクールのメール送信・受信履歴照会に伴う対応-Start
            // 共通：スクールのメール送信・受信履歴データ作成Logicクラスの生成
            SmailAccountHistoryLogic sAMHiLogic = new SmailAccountHistoryLogic(conn);

            // ユーザIDを取得
            String userId = NaikaraTalkConstants.BRANK;
            if ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString()) != null) {
                userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();
            }

            // スクールメール・アカウント変更履歴テーブルの更新処理
            // 送信者：講師
            sAMHiLogic.smailActHistoryInsertMain(NaikaraTalkConstants.MAIL_PATTERN_ATTACHMENTS_S_FROM_T,
            		userId, model.getTeacherId());

            // 受信者：受講者
            sAMHiLogic.smailActHistoryInsertMain(NaikaraTalkConstants.MAIL_PATTERN_ATTACHMENTS_S_FROM_T,
            		userId, model.getStudentId());
            // 2014/01/03-スクールのメール送信・受信履歴照会に伴う対応-End

            // コミット
            conn.commit();

        } catch (Exception se) {
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
     * メール送信(受講者⇒講師)<br>
     * <br>
     * メール送信(受講者⇒講師)を行う<br>
     * <br>
     * @param MailWithAttachmentsSendModel 画面パラメータ<br>
     * @return なし<br>
     * @throws Exception
     */
    private void sendMailStudent(MailWithAttachmentsSendModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // ロジックの初期化
            MailWithAttachmentsSendLogic mailWithAttachmentsSendLogic = new MailWithAttachmentsSendLogic(conn);

            // Model値をDTOにセット
            UserMstDto prmDto = this.modelToUserDto(model);

            // 検索を実行
            UserMstDto resultDto = mailWithAttachmentsSendLogic.selectUser(prmDto);

            // 件名リスト
            String subjectTitle = "";

            if (!StringUtils.isEmpty(model.getLessonDt()) && !StringUtils.isEmpty(model.getLessonTmNm())
                    && model.getLessonTmNm().length() >= 5) {

                subjectTitle = NaikaraStringUtil.converToDDMMYYHHMM(
                        NaikaraStringUtil.converToYY_MM_DD(model.getLessonDt()), model.getLessonTmNm());
            }

            // メールパターンコード
            String mailPatternCd = NaikaraTalkConstants.MAIL_PATTERN_CODE_SMT;
            // 送信者
            String sendFrom = NaikaraTalkConstants.BRANK;
            // 送信者名
            String sendFromNm = NaikaraTalkConstants.BRANK;
            // 宛先IDリスト
            List<String> sendIdList = new ArrayList<String>();
            sendIdList.add(model.getTeacherId());
            // 宛先アドレスリスト
            List<String> sendToList = new ArrayList<String>();
            sendToList.add(resultDto.getMailAddress1());
            // CC
            String cc = NaikaraTalkConstants.BRANK;
            // BCC
            String bcc = NaikaraTalkConstants.BRANK;
            // 件名リスト
            List<String> subjectTitleList = new ArrayList<String>();
            subjectTitleList.add(subjectTitle);
            // 本文リスト
            List<List<String>> mailTextList = new ArrayList<List<String>>();
            List<String> textList0 = new ArrayList<String>();
            textList0.add(model.getTeacherNickNm());
            mailTextList.add(textList0);
            List<String> textList1_7 = new ArrayList<String>();
            textList1_7.add(NaikaraTalkConstants.BRANK);
            mailTextList.add(textList1_7);
            mailTextList.add(textList1_7);
            mailTextList.add(textList1_7);
            mailTextList.add(textList1_7);
            mailTextList.add(textList1_7);
            mailTextList.add(textList1_7);
            mailTextList.add(textList1_7);
            List<String> textList8 = new ArrayList<String>();
            textList8.add(subjectTitle);
            mailTextList.add(textList8);
            List<String> textList9 = new ArrayList<String>();
            textList9.add(model.getStudentNickNm());
            mailTextList.add(textList9);
            List<String> textList10 = new ArrayList<String>();
            textList10.add(model.getCourseEnmAll());
            mailTextList.add(textList10);
            List<String> textList11 = new ArrayList<String>();
            textList11.add(model.getCourseJnmAll());
            mailTextList.add(textList11);
            List<String> textList12_14 = new ArrayList<String>();
            textList12_14.add(NaikaraTalkConstants.BRANK);
            mailTextList.add(textList12_14);
            mailTextList.add(textList12_14);
            mailTextList.add(textList12_14);
            List<String> textList15 = new ArrayList<String>();
            textList15.add(model.getCommentTxt());
            mailTextList.add(textList15);
            List<String> textList16_29 = new ArrayList<String>();
            textList16_29.add(NaikaraTalkConstants.BRANK);
            mailTextList.add(textList16_29);
            mailTextList.add(textList16_29);
            mailTextList.add(textList16_29);
            mailTextList.add(textList16_29);
            mailTextList.add(textList16_29);
            mailTextList.add(textList16_29);
            mailTextList.add(textList16_29);
            mailTextList.add(textList16_29);
            mailTextList.add(textList16_29);
            mailTextList.add(textList16_29);
            mailTextList.add(textList16_29);
            mailTextList.add(textList16_29);
            mailTextList.add(textList16_29);
            mailTextList.add(textList16_29);
            // 添付
            String file = model.getTempFileFileName();
            // 予約No
            String reservationNo = model.getReservationNo();

            SendMailBatch sendMailBatch = new SendMailBatch(conn);

            sendMailBatch.sendMail(mailPatternCd, sendFrom, sendFromNm, sendIdList, sendToList, cc, bcc,
                    subjectTitleList, mailTextList, file, reservationNo);

            // 2014/01/03-スクールのメール送信・受信履歴照会に伴う対応-Start
            // 共通：スクールのメール送信・受信履歴データ作成Logicクラスの生成
            SmailAccountHistoryLogic sAMHiLogic = new SmailAccountHistoryLogic(conn);

            // ユーザIDを取得
            String userId = NaikaraTalkConstants.BRANK;
            if ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString()) != null) {
                userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();
            }

            // スクールメール・アカウント変更履歴テーブルの更新処理
            // 送信者：受講者
            sAMHiLogic.smailActHistoryInsertMain(NaikaraTalkConstants.MAIL_PATTERN_ATTACHMENTS_T_FROM_S,
            		userId, model.getStudentId());

            // 受信者：講師
            sAMHiLogic.smailActHistoryInsertMain(NaikaraTalkConstants.MAIL_PATTERN_ATTACHMENTS_T_FROM_S,
            		userId, model.getTeacherId());
            // 2014/01/03-スクールのメール送信・受信履歴照会に伴う対応-End

            // コミット
            conn.commit();

        } catch (Exception se) {
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
     * Model値をセット<br>
     * <br>
     * Model値をDTOにセット<br>
     * <br>
     * @param MailWithAttachmentsSendModel 画面パラメータ<br>
     * @return StudentMstDto 変換後結果<br>
     * @exception Exception
     */
    private StudentMstDto modelToStudentDto(MailWithAttachmentsSendModel model) throws NaiException {

        // DTOの初期化
        StudentMstDto prmDto = new StudentMstDto();

        // 受講者ID
        prmDto.setStudentId(model.getStudentId());

        return prmDto;
    }

    /**
     * Model値をセット<br>
     * <br>
     * Model値をDTOにセット<br>
     * <br>
     * @param MailWithAttachmentsSendModel 画面パラメータ<br>
     * @return UserMstDto 変換後結果<br>
     * @exception Exception
     */
    private UserMstDto modelToUserDto(MailWithAttachmentsSendModel model) throws NaiException {

        // DTOの初期化
        UserMstDto prmDto = new UserMstDto();

        // 利用者ID
        prmDto.setUserId(model.getTeacherId());

        return prmDto;
    }
}