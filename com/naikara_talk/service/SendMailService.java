package com.naikara_talk.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
//import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.log4j.Logger;

//import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.DmHistoryDetailsTrnDto;
import com.naikara_talk.dto.DmHistoryTrnDto;
import com.naikara_talk.dto.LessonReservationPerformanceTrnDto;
import com.naikara_talk.dto.MailManagMstDto;
import com.naikara_talk.dto.StudentMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.OrderNumbersMstLogic;
import com.naikara_talk.logic.SendMailLogic;
import com.naikara_talk.logic.SmailAccountHistoryLogic;
import com.naikara_talk.model.SendMailModel;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.sessiondata.SessionMailWithAttachmentsSend;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>バッチ<br>
 * <b>クラス名称       :</b>予約取消・購入のメール送信処理Serviceクラス<br>
 * <b>クラス概要       :</b>各機能より起動されてＥメール送信処理を行う。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/04 TECS 新規作成
 *                      </b>2014/01/03 TECS 変更 スクールのメール送信・受信履歴照会に伴う対応
 *                      </b>2014/04/10 TECS 変更 othersPatternの障害修正(updateLessonReservationPerformanceTrnの更新判定)
 */
public class SendMailService {

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public SendMailService(Connection con) {
        this.conn = con;
    }

    // ログのの初期化
    Logger log = Logger.getLogger(SendMailService.class);

    /**
     * メールパターンコード「DMC」のメール送信処理<br>
     * <br>
     * メールパターンコード「DMC」のメール送信処理を行う。<br>
     * <br>
     * @param sendIdList 宛先IDリスト<br>
     * @param subjectTitle 件名<br>
     * @param mailText メール本文<br>
     * @return なし<br>
     * @throws Exception
     */
    public void directMailCustomer(List<String> sendIdList, String subjectTitle, String mailText, String sendFromNm)
            throws Exception {

        if (log.isDebugEnabled()) {
            log.debug("SendMailService directMailCustomer Start");
        }

        //Connection conn = null;
        try {
            //conn = DbUtil.getConnection();

	        // 送信者の初期化
	        String sendFrom = NaikaraTalkConstants.BRANK;
	        // ｢送信者｣を設定するための対象データを取得する
	        // コード種別：”J54” （ＤＭ送信者メールアドレス）、汎用コード：”0001”
	        List<CodeManagMstDto> codeManagMstList = this
	                .selectCodeManagMstList(NaikaraTalkConstants.CODE_CATEGORY_DM_SENDER_MAIL_ADDRESS,
	                        NaikaraTalkConstants.MAIL_ADDRESS_REFRAIN_DM);
	        // 「送信者」を取得する
	        if (codeManagMstList.get(0).getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_YES) {
	            sendFrom = codeManagMstList.get(0).getManagerNm();
	        }

	        String sendTo = NaikaraTalkConstants.BRANK;
	        // ｢控えメール｣を設定するための対象データを取得する
	        // コード種別：”J55” （ＤＭ控えメールアドレス）、汎用コード：”0001”
	        List<CodeManagMstDto> codeManagMstList2 = this.selectCodeManagMstList(
	                NaikaraTalkConstants.CODE_CATEGORY_MAIL_ADDRESS_REFRAIN_DM,
	                NaikaraTalkConstants.MAIL_ADDRESS_REFRAIN_DM);
	        // 「宛先」を取得する
	        if (codeManagMstList2.get(0).getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_YES) {
	            sendTo = codeManagMstList2.get(0).getManagerNm();
	        }

	        // 採番マスタ自動採番の初期化
	        OrderNumbersMstLogic logic = new OrderNumbersMstLogic();
	        // 採番取得
	        String orderNumber = logic.getOrderNumber(NaikaraTalkConstants.ORDER_NUMBERS_DM, DateUtil.getSysDate())
	                .getOrderNumber();

	        if (log.isDebugEnabled()) {
	            log.debug("SendMailService call logic.getOrderNumber orderNumber=" + orderNumber);
	        }

	        // 受講者メールの作成と送信を行う
	        for (String sendId : sendIdList) {
	            // モデルの初期化
	            SendMailModel model = new SendMailModel();
	            // 「送信者」を設定する
	            model.setSendFrom(sendFrom);
	            // 宛先IDを設定
	            model.setSendId(sendId);
	            // ｢宛先｣を設定するための対象データを取得して、取得項目を｢宛先｣へ設定する
	            List<StudentMstDto> studentMstDtoList = this.selectStudentMstList(sendId);
	            if (studentMstDtoList.get(0).getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_YES) {
	                StudentMstDto smDto = studentMstDtoList.get(0);
	                // メールアドレス1
	                model.setSendTo(smDto.getMailAddress1());
	                // 宛先名
	                model.setSendNm(NaikaraStringUtil.unionName(smDto.getFamilyJnm(), smDto.getFirstJnm()));
	                if (StringUtils.isEmpty(model.getSendTo())) {
	                    continue;
	                }
	            }
	            // ”” (設定なし) を｢CC｣へ設定する
	            model.setCc(NaikaraTalkConstants.BRANK);
	            // ”” (設定なし) を｢BCC｣へ設定する
	            model.setBcc(NaikaraTalkConstants.BRANK);
	            // 引数：件名 を｢件名｣へ設定する
	            model.setSubjectTitle(subjectTitle);
	            // 引数：本文 を｢メール本文｣へ設定する
	            model.setMailText(mailText);
	            // 備考を設定する
	            model.setRemark(null);

	            if (log.isDebugEnabled()) {
	                log.debug("SendMailService call this.sendDMCMail before sendId=" + sendId);
	            }

	            // 上記までに作成した内容で、メール送信処理を行う
	            this.sendDMCMail(model, true);

	            if (log.isDebugEnabled()) {
	                log.debug("SendMailService call this.insertDmHistoryDetailsTrn before ");
	            }

	            // 上記までに作成した内容で、DM履歴明細テーブルへ追加処理を行う
	            this.insertDmHistoryDetailsTrn(model, orderNumber, sendFromNm);


	            // 2014/01/03-スクールのメール送信・受信履歴照会に伴う対応-Start
	            // 共通：スクールのメール送信・受信履歴データ作成Logicクラスの生成
	            SmailAccountHistoryLogic sAMHiLogic = new SmailAccountHistoryLogic(this.conn);

	            // スクールメール・アカウント変更履歴テーブルの更新処理
	            sAMHiLogic.smailActHistoryInsertMain(NaikaraTalkConstants.MAIL_PATTERN_DM,
	            		sendFromNm, model.getSendId());
	            // 2014/01/03-スクールのメール送信・受信履歴照会に伴う対応-End

	        }

	        if (log.isDebugEnabled()) {
	            log.debug("SendMailService school-address before ");
	        }

	        // 控えメールの作成と送信を行う
	        SendMailModel model = new SendMailModel();
	        // 「送信者」を設定する
	        model.setSendFrom(sendFrom);
	        // 宛先IDを設定する
	        model.setSendId(NaikaraTalkConstants.DM_HISTORY_DETAILES_SCHOOL_ID);
	        // 宛先名を設定する
	        model.setSendNm(sendTo);
	        // ｢宛先｣を設定する
	        model.setSendTo(sendTo);
	        // ”” (設定なし) を｢CC｣へ設定する
	        model.setCc(NaikaraTalkConstants.BRANK);
	        // ”” (設定なし) を｢BCC｣へ設定する
	        model.setBcc(NaikaraTalkConstants.BRANK);
	        // 引数：件名 を｢件名｣へ設定する
	        model.setSubjectTitle(subjectTitle);
	        // 引数：本文 を｢メール本文｣へ設定する
	        model.setMailText(mailText);
	        // 備考を設定する
	        model.setRemark(sendTo);

	        if (log.isDebugEnabled()) {
	            log.debug("SendMailService school-address this.sendDMCMail before ");
	        }

	        // 上記までに作成した内容で、メール送信処理を行う
	        this.sendDMCMail(model, true);

	        // 宛先名が「スクール(控え)」を設定する
	        model.setSendNm(NaikaraTalkConstants.DM_HISTORY_DETAILES_SCHOOL_NOTE);

	        if (log.isDebugEnabled()) {
	            log.debug("SendMailService school-address this.insertDmHistoryDetailsTrn before ");
	        }

	        // 上記までに作成した内容で、DM履歴明細テーブルへ追加処理を行う
	        this.insertDmHistoryDetailsTrn(model, orderNumber, sendFromNm);

	        if (log.isDebugEnabled()) {
	            log.debug("SendMailService this.insertDmHistoryTrn before ");
	        }

	        // DM履歴テーブルへ追加処理を行う
	        this.insertDmHistoryTrn(model, orderNumber, sendFromNm);

            // コミット
            //conn.commit();

	        if (log.isDebugEnabled()) {
	            log.debug("SendMailService directMailCustomer End");
	        }


        } catch (Exception se) {
            log.info(se);
            se.printStackTrace();
            throw new NaiException(se);
        } finally {
            try {
                //conn.close();
            } catch (Exception e1) {
                log.info(e1);
                e1.printStackTrace();
            }
        }

    }

    /**
     * メールパターンコード「その他」のメール送信処理<br>
     * <br>
     * メールパターンコード「その他」のメール送信を処理する。<br>
     * <br>
     * @param mailPatternCd メールパターンコード<br>
     * @param sendFrom 送信者アドレス<br>
     * @param sendToList 宛先リスト<br>
     * @param cc CC<br>
     * @param bcc BCC<br>
     * @param subjectTitleList 件名リスト<br>
     * @param mailTextList 本文リスト<br>
     * @param file 添付<br>
     * @param reservationNo 予約No<br>
     * @return なし<br>
     * @throws Exception
     */
    public void othersPattern(String mailPatternCd, String sendFrom, List<String> sendToList, String cc, String bcc,
            List<String> subjectTitleList, List<List<String>> mailTextList, String file, String reservationNo)
            throws Exception {

        // 送信者アドレスの初期化
        String sSendFrom = NaikaraTalkConstants.BRANK;
        // 宛先の初期化
        String sSendTo = NaikaraTalkConstants.BRANK;
        // CCの初期化
        String sCc = NaikaraTalkConstants.BRANK;
        // BCCの初期化
        String sBcc = NaikaraTalkConstants.BRANK;
        // 件名の初期化
        String sSubjectTitle = NaikaraTalkConstants.BRANK;
        // 本文リストの初期化
        List<String> sMailTextList = new ArrayList<String>();

        // メール管理マスタから対象データの取得処理を行い、各変数にそれぞれ値を設定する
        List<MailManagMstDto> mailManagMstDtoList = this.selectMailManagMst(mailPatternCd);
        if (mailManagMstDtoList.get(0).getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_YES) {
            // 各変数にそれぞれ値を設定する
            MailManagMstDto mailManagMstDto = mailManagMstDtoList.get(0);
            // 送信者アドレス
            sSendFrom = mailManagMstDto.getSentMailAddress();
            // 宛先
            sSendTo = mailManagMstDto.getAddress();
            // CC
            sCc = mailManagMstDto.getCc();
            // BCC
            sBcc = mailManagMstDto.getBcc();
            // 件名
            sSubjectTitle = mailManagMstDto.getSubjectTitle();
            // 本文１～３５
            sMailTextList.add(mailManagMstDto.getText1());
            sMailTextList.add(mailManagMstDto.getText2());
            sMailTextList.add(mailManagMstDto.getText3());
            sMailTextList.add(mailManagMstDto.getText4());
            sMailTextList.add(mailManagMstDto.getText5());
            sMailTextList.add(mailManagMstDto.getText6());
            sMailTextList.add(mailManagMstDto.getText7());
            sMailTextList.add(mailManagMstDto.getText8());
            sMailTextList.add(mailManagMstDto.getText9());
            sMailTextList.add(mailManagMstDto.getText10());
            sMailTextList.add(mailManagMstDto.getText11());
            sMailTextList.add(mailManagMstDto.getText12());
            sMailTextList.add(mailManagMstDto.getText13());
            sMailTextList.add(mailManagMstDto.getText14());
            sMailTextList.add(mailManagMstDto.getText15());
            sMailTextList.add(mailManagMstDto.getText16());
            sMailTextList.add(mailManagMstDto.getText17());
            sMailTextList.add(mailManagMstDto.getText18());
            sMailTextList.add(mailManagMstDto.getText19());
            sMailTextList.add(mailManagMstDto.getText20());
            sMailTextList.add(mailManagMstDto.getText21());
            sMailTextList.add(mailManagMstDto.getText22());
            sMailTextList.add(mailManagMstDto.getText23());
            sMailTextList.add(mailManagMstDto.getText24());
            sMailTextList.add(mailManagMstDto.getText25());
            sMailTextList.add(mailManagMstDto.getText26());
            sMailTextList.add(mailManagMstDto.getText27());
            sMailTextList.add(mailManagMstDto.getText28());
            sMailTextList.add(mailManagMstDto.getText29());
            sMailTextList.add(mailManagMstDto.getText30());
            sMailTextList.add(mailManagMstDto.getText31());
            sMailTextList.add(mailManagMstDto.getText32());
            sMailTextList.add(mailManagMstDto.getText33());
            sMailTextList.add(mailManagMstDto.getText34());
            sMailTextList.add(mailManagMstDto.getText35());
        }

        SendMailModel model = new SendMailModel();

        // ｢送信者｣を設定する
        // 引数：送信者＝”” (設定なし) の場合
        if (StringUtils.isEmpty(sendFrom)) {
            // 変数：送信者アドレスを｢送信者｣へ設定する
            model.setSendFrom(sSendFrom);
        } else {
            // 引数：送信者を｢送信者｣へ設定する
            model.setSendFrom(sendFrom);
        }

        // ｢宛先｣を設定する
        // 引数：宛先＝”” (設定なし) の場合
        if (sendToList.isEmpty() || StringUtils.isEmpty(sendToList.get(0))) {
            // 変数：宛先アドレスを｢宛先｣へ設定する
            model.setSendTo(sSendTo);
        } else {
            // 引数：宛先アドレスを｢宛先｣へ設定する
            model.setSendTo(sendToList.get(0));
        }

        // ｢CC｣を設定する
        // 引数：CC＝”” (設定なし) の場合
        if (StringUtils.isEmpty(cc)) {
            // 変数：CC ≠ ”” (設定なし) の場合
            if (!StringUtils.isEmpty(sCc)) {
                // 変数：CCを｢CC｣へ設定する
                model.setCc(sCc);
            }
        } else {
            // 引数：CCを｢CC｣へ設定する
            model.setCc(cc);
        }

        // ｢BCC｣を設定する
        // 引数：BCC＝”” (設定なし) の場合
        if (StringUtils.isEmpty(bcc)) {
            // 変数：BCC ≠ ”” (設定なし) の場合
            if (!StringUtils.isEmpty(sBcc)) {
                // 変数：BCCを｢BCC｣へ設定する
                model.setBcc(sBcc);
            }
        } else {
            // 引数：BCCを｢BCC｣へ設定する
            model.setBcc(bcc);
        }

        // ｢件名｣を設定する
        StringBuffer subject = new StringBuffer();
        // 引数：件名リストのリスト件数＝0 の場合
        if (subjectTitleList.isEmpty()) {
            // 変数：件名を｢件名｣へ設定する
            subject.append(sSubjectTitle);
        } else {

            // 変数：件名 = ”” (設定なし) の場合
            if (StringUtils.isEmpty(sSubjectTitle)) {
                // 引数：件名を｢件名｣へ設定する
                subject.append(subjectTitleList.get(0));
            } else {
                // ”%”は変数：件名に出現の回数を取得
                int count = this.getFrequency(NaikaraTalkConstants.MESSAGE_PARAM_PREFIX, sSubjectTitle);

                for (int i = 0; i < count; i++) {

                    // 変数：件名の%ｎの箇所に、引数：件名リストのｎ個目を設定する (※%ｎは複数存在する可能性がある)
                    String regex = new StringBuffer().append(NaikaraTalkConstants.MESSAGE_PARAM_PREFIX)
                            .append(String.valueOf((i + 1))).toString();
                    String params = subjectTitleList.get(i);

                    sSubjectTitle = sSubjectTitle.replaceAll(regex, params);
                }

                subject.append(sSubjectTitle);
            }
        }
        // 上記の編集結果を｢件名｣へ設定する
        model.setSubjectTitle(subject.toString());

        // ｢本文｣を設定する
        StringBuffer mailText = new StringBuffer();
        // 引数：本文リストのリスト件数＝0 の場合
        if (mailTextList.isEmpty()) {
            // 変数：本文リストを｢本文｣へ設定する
            for (String text : sMailTextList) {
                mailText.append(text);
            }
        } else {

            // 変数：本文リストの件数分 以下の処理を繰り返す
            for (int i = 0; i < sMailTextList.size(); i++) {

                if (i < mailTextList.size()) {
                    // 引数：本文リストの 値が、","（カンマ）区切りの場合、複数行出力する
                    for (int j = 0; j < mailTextList.get(i).get(0).split(String.valueOf(NaikaraTalkConstants.COMMA)).length; j++) {

                        String lineText = String.valueOf(NaikaraStringUtil.nvlString(sMailTextList.get(i)));

                        // ”%”は変数：本文に出現の回数を取得
                        int count = this.getFrequency(NaikaraTalkConstants.MESSAGE_PARAM_PREFIX, lineText);

                        // 変数：本文リスト i 個目に、%ｎ が1つ以上 存在した場合、%ｎ 個分 以下の処理を繰り返す
                        for (int k = 0; k < count; k++) {

                            // %ｎ
                            String regex = new StringBuffer().append(NaikaraTalkConstants.MESSAGE_PARAM_PREFIX)
                                    .append(String.valueOf((k + 1))).toString();

                            // 値
                            String params = mailTextList.get(i).get(k)
                                    .split(String.valueOf(NaikaraTalkConstants.COMMA))[j];

                            // パラメータ（%ｎ）置換
                            lineText = lineText.replaceAll(regex, params);
                        }

                        // 本文の追加
                        mailText.append(lineText).append(NaikaraTalkConstants.NEW_LINE_CODE_WIN);
                    }
                }
            }
        }
        // 上記の編集結果を｢本文｣へ設定する
        model.setMailText(mailText.toString());

        // 引数：メールパターンコード ＝ ”SMC” (添付ファイル付きレッスンでのメール送信画面:講師⇒受講者) 又は
        // ”SMC” (レッスンに対するメール送信画面:受講者⇒講師) の場合は、以下の処理を行う
        if (StringUtils.equals(mailPatternCd, NaikaraTalkConstants.MAIL_PATTERN_CODE_SMT)
                || StringUtils.equals(mailPatternCd, NaikaraTalkConstants.MAIL_PATTERN_CODE_SMC)) {
            // ｢添付｣を設定する
            model.setFile(file);
        }

        // 上記までに作成した内容で、メール送信処理を行う
        this.sendMail(model, false);


        // 引数：メールパターンコード ＝ ”SMC” ((添付ファイル付きレッスンでのメール送信画面)) の場合は、以下の処理を行う
        if (StringUtils.equals(mailPatternCd, NaikaraTalkConstants.MAIL_PATTERN_CODE_SMC)) {
            // 上記までに作成した内容で、レッスン予実テーブルへ更新処理を行う
            this.updateLessonReservationPerformanceTrn(reservationNo);
        }


    }

    /**
     * レッスン予実テーブルデータの取得<br>
     * <br>
     * レッスン予実テーブルへ検索処理を行う。<br>
     * <br>
     * @param lessonDt レッスン日<br>
     * @param lessonTmCd レッスン時刻コード<br>
     * @param stateKbn 状態区分<br>
     * @return List<LessonReservationPerformanceTrnDto> レッスン予実テーブルDTOリスト<br>
     * @throws NaiException
     */
    public List<LessonReservationPerformanceTrnDto> selectLessonReservationPerformanceTrnList(String lessonDt,
            String lessonTmCd, String stateKbn) throws NaiException {

        // 初期化処理
        SendMailLogic logic = new SendMailLogic(this.conn);

        // DTOの初期化
        LessonReservationPerformanceTrnDto dto = new LessonReservationPerformanceTrnDto();
        // レッスン日
        dto.setLessonDt(lessonDt);
        // レッスン時刻コード
        dto.setLessonTmCd(lessonTmCd);
        // 状態区分
        dto.setStateKbn(stateKbn);

        // データの取得＆リターン
        return logic.selectLessonReservationPerformanceTrnList(dto);
    }

    /**
     * 受講者マスタデータ取得<br>
     * <br>
     * 対象予約レッスン者の情報を受講者マスタから取得する<br>
     * <br>
     * @param studentId 受講者ID<br>
     * @return List<StudentMstDto> 受講者マスタDTOリスト<br>
     * @throws NaiException
     */
    public List<StudentMstDto> selectStudentMstList(String studentId) throws NaiException {

        // 初期化処理
        SendMailLogic logic = new SendMailLogic(this.conn);

        StudentMstDto dto = new StudentMstDto();
        // 受講者ID
        dto.setStudentId(studentId);

        // データの取得＆リターン
        return logic.selectStudentMstList(dto);
    }

    /**
     * コード管理マスタデータ取得<br>
     * <br>
     * コード管理マスタからデータ取得処理。<br>
     * <br>
     * @param cdCategory コード種別<br>
     * @param managerCd 汎用コード<br>
     * @return List<CodeManagMstDto> コード管理マスタDTOリスト<br>
     * @throws NaiException
     */
    public List<CodeManagMstDto> selectCodeManagMstList(String cdCategory, String managerCd) throws NaiException {

        // 初期化処理
        SendMailLogic logic = new SendMailLogic(this.conn);

        CodeManagMstDto dto = new CodeManagMstDto();

        // コード種別
        dto.setCdCategory(cdCategory);

        // 汎用コード
        dto.setManagerCd(managerCd);

        // データの取得＆リターン
        return logic.selectCodeManagMstList(dto);
    }

    /**
     * メール送信処理<br>
     * <br>
     * メール送信処理を行う。<br>
     * <br>
     * @param model メール送信モデル<br>
     * @param htmlEmailFlg HTML送信フラグ<br>
     * @return なし<br>
     * @throws NaiException
     */
    public synchronized void sendMail(SendMailModel model, boolean htmlEmailFlg) throws NaiException {

        // 添付ファイル
        File file = null;

        if (log.isDebugEnabled()) {
            log.debug("SendMailService sendMail Start ");
        }

        try {
            // プロパティーファイルを取得
            InputStream inputStream = this.getClass().getClassLoader()
                    .getResourceAsStream(NaikaraTalkConstants.EMAIL_ROPERTIES);

            Properties properties = new Properties();
            properties.load(inputStream);

            // サーバーを取得
            String host = properties.getProperty(NaikaraTalkConstants.EMAIL_HOST);
            // ユーザ名を取得
            String username = properties.getProperty(NaikaraTalkConstants.EMAIL_USERNAME);
            // パスワードを取得
            String password = properties.getProperty(NaikaraTalkConstants.EMAIL_PASSWORD);

            MultiPartEmail email = new MultiPartEmail();

            // HTML送信
            if (htmlEmailFlg) {
                // HTMLメールの初期化
                email = new HtmlEmail();
            }
            // 文字セットの設定
            email.setCharset(NaikaraTalkConstants.UTF_8);
            // サーバーを設定
            email.setHostName(host);
            // メールサーバのユーザ名とパスワード
            email.setAuthentication(username, password);

            if (log.isDebugEnabled()) {
                log.debug("SendMailService sendMail transport.connect before");
                log.debug("SendMailService sendMail host=" + host);
                log.debug("SendMailService sendMail username=" + username);
                log.debug("SendMailService sendMail password=" + password);
            }

            // 接收人
            email.addTo(model.getSendTo(), model.getSendNm());
            // 送信者
            email.setFrom(model.getSendFrom());
            // CC
            if (!StringUtils.isEmpty(model.getCc())) {
                email.addCc(model.getCc());
            }
            // BCC
            if (!StringUtils.isEmpty(model.getBcc())) {
                email.addBcc(model.getBcc());
            }
            // タイトル
            email.setSubject(model.getSubjectTitle());

            // 本文
            String mailText = model.getMailText();
            // HTML送信
            if (htmlEmailFlg) {
                // 本文
                mailText = mailText.replaceAll(NaikaraTalkConstants.NEW_LINE_CODE_WIN,
                        NaikaraTalkConstants.MAIL_NEW_LINE_CODE);
                email.setContent(mailText, NaikaraTalkConstants.CHARSET_UTF_8);
            } else {
                email.setMsg(mailText);
            }

            if (!StringUtils.isEmpty(model.getFile())) {

                if ((SessionMailWithAttachmentsSend) SessionDataUtil
                        .getSessionData(SessionMailWithAttachmentsSend.class.toString()) != null) {
                    // 添付
                    byte[] bytes = ((SessionMailWithAttachmentsSend) SessionDataUtil
                            .getSessionData(SessionMailWithAttachmentsSend.class.toString())).getBytes();

                    // 汎用フィールド名の取得
                    CodeManagMstCache cache = CodeManagMstCache.getInstance();

                    // 添付付きメール送信の一時的な格納先の取得
                    LinkedHashMap<String, CodeManagMstDto> emailAttachTempathList = cache
                            .getList(NaikaraTalkConstants.CODE_CATEGORY_EMAIL_ATTACH_TEMPATH);
                    String path = emailAttachTempathList.get(NaikaraTalkConstants.EMAIL_ATTACH_TEMPATH_CD)
                            .getManagerNm();

                    file = new File(new StringBuffer().append(path).append(NaikaraTalkConstants.DATE_SEPARATOR)
                            .append(model.getFile()).toString());

                    // InputStream To File
                    this.byteToFile(bytes, file);

                    DataSource ds = new FileDataSource(file);

                    // 添付ファイルを追加
                    email.attach(ds, MimeUtility.encodeText(model.getFile(), NaikaraTalkConstants.UTF_8, null),
                            NaikaraTalkConstants.BRANK);
                }
            }

            if (log.isDebugEnabled()) {
                log.debug("SendMailService sendMail email.send() before ");
            }

            // 送信
            email.send();

            if (log.isDebugEnabled()) {
                log.debug("SendMailService sendMail email.send() after ");
            }

            if (log.isDebugEnabled()) {
                log.debug("SendMailService sendMail End ");
            }

        } catch (Exception e) {

            if (log.isDebugEnabled()) {
                log.debug("SendMailService sendMail Exception e.getMessage=" + e.getMessage());
            }

            throw new NaiException(e);
        } finally {
            // ファイルを削除
            if (file != null) {
                file.delete();
            }
        }
    }

    /**
     * メール送信処理<br>
     * <br>
     * メール送信処理を行う。<br>
     * <br>
     * @param model メール送信モデル<br>
     * @param htmlEmailFlg HTML送信フラグ<br>
     * @return なし<br>
     * @throws NaiException
     */
    public synchronized boolean sendDMCMail(SendMailModel model, boolean htmlEmailFlg) throws NaiException {

        if (log.isDebugEnabled()) {
            log.debug("SendMailService sendDMCMail Start");
        }

        // トランスポート
        Transport transport = null;
        try {
            // プロパティーファイルを取得
            InputStream inputStream = this.getClass().getClassLoader()
                    .getResourceAsStream(NaikaraTalkConstants.EMAIL_ROPERTIES);

            Properties props = new Properties();
            props.load(inputStream);
            Session session = Session.getDefaultInstance(props, null);
            // サーバーを取得
            String host = props.getProperty(NaikaraTalkConstants.EMAIL_HOST);
            // ユーザ名を取得
            String username = props.getProperty(NaikaraTalkConstants.EMAIL_USERNAME);
            // パスワードを取得
            String password = props.getProperty(NaikaraTalkConstants.EMAIL_PASSWORD);

            MimeMessage msg = new MimeMessage(session);

            // タイトルの設定
            msg.setSubject(model.getSubjectTitle(), NaikaraTalkConstants.EMAIL_CHARSET_SHIFT_JIS);

            // 送信アドレスの設定
            msg.setFrom(new InternetAddress(model.getSendFrom()));

            // TOアドレスの設定
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(model.getSendTo()));

            // CCアドレスの設定
            if (!StringUtils.isEmpty(model.getCc())) {
                msg.setRecipient(Message.RecipientType.CC, new InternetAddress(model.getCc()));
            }

            // BCCアドレスの設定
            if (!StringUtils.isEmpty(model.getBcc())) {
                msg.setRecipient(Message.RecipientType.BCC, new InternetAddress(model.getBcc()));
            }

            MimeMultipart mm = new MimeMultipart();

            BodyPart mdp = new MimeBodyPart();

            // メール内容の設定
            String mailText = StringUtils.replace(model.getMailText(),
                    NaikaraTalkConstants.EMAIL_IMG_PATH.concat(NaikaraTalkConstants.DATE_SEPARATOR),
                    NaikaraTalkConstants.EMAIL_IMG_CID);

            mdp.setContent(mailText, NaikaraTalkConstants.CHARSET_UTF_8);

            mm.setSubType(NaikaraTalkConstants.EMAIL_IMG_RELATED);
            mm.addBodyPart(mdp);

            // イメージ名を取得するため、メール本文を分割
            String[] strContectTemp = mailText.split(NaikaraTalkConstants.EMAIL_IMG_CID);

            TreeSet<String> trFileName = new TreeSet<String>();

            String strFileName = null;

            // イメール名の取得
            for (int i = 1; i < strContectTemp.length; i++) {
                strFileName = strContectTemp[i].substring(0,
                        strContectTemp[i].indexOf(NaikaraTalkConstants.EMAIL_IMG_QUOTES));
                trFileName.add(strFileName);
            }

            // イメール名の重複を削除
            String[] arrayFileName = new String[trFileName.size()];
            for (int i = 0; i < arrayFileName.length; i++) {
                arrayFileName[i] = trFileName.pollFirst();
            }

            for (int i = 0; i < arrayFileName.length; i++) {

                mdp = new MimeBodyPart();

                // イメージの取得
                StringBuffer sb = new StringBuffer();
                sb.setLength(0);
                // コード管理マスタのキャッシュ読み込み
                CodeManagMstCache cache = CodeManagMstCache.getInstance();
                LinkedHashMap<String, CodeManagMstDto> sbList = cache
                        .getList(NaikaraTalkConstants.CODE_CATEGORY_HTML_EMAIL_IMG_PATH);

                sb.append(sbList.get(NaikaraTalkConstants.CODE_CATEGORY_HTML_EMAIL_IMG_TEMPATH).getManagerNm())
                        .append(NaikaraTalkConstants.DATE_SEPARATOR).append(arrayFileName[i]);
                DataSource dataSource = new FileDataSource(sb.toString());

                DataHandler dataHandler = new DataHandler(dataSource);

                mdp.setDataHandler(dataHandler);

                // イメージ名の設定
                mdp.setFileName(arrayFileName[i]);
                mdp.setHeader(NaikaraTalkConstants.EMAIL_CONTENT_ID, arrayFileName[i]);
                mm.addBodyPart(mdp);
            }

            msg.setContent(mm);

            msg.saveChanges();
            transport = session.getTransport(NaikaraTalkConstants.EMAIL_SMTP);

            if (log.isDebugEnabled()) {
                log.debug("SendMailService sendDMCMail transport.connect before");
                log.debug("SendMailService sendDMCMail host=" + host);
                log.debug("SendMailService sendDMCMail username=" + username);
                log.debug("SendMailService sendDMCMail password=" + password);
            }

            transport.connect(host, username, password);

            if (log.isDebugEnabled()) {
                log.debug("SendMailService sendDMCMail transport.sendMessage before");
                log.debug("SendMailService sendDMCMail msg.getSize()=" + msg.getSize());
                log.debug("SendMailService sendDMCMail msg.getAllRecipients()" + msg.getAllRecipients());
            }

            // メール送信
            transport.sendMessage(msg, msg.getAllRecipients());

            if (log.isDebugEnabled()) {
                log.debug("SendMailService sendDMCMail transport.sendMessage End");
            }

            if (log.isDebugEnabled()) {
                log.debug("SendMailService sendDMCMail End");
            }

        } catch (Exception e) {

            if (log.isDebugEnabled()) {
                log.debug("SendMailService sendDMCMail A Exception e.getMessage()=" + e.getMessage());
            }

            throw new NaiException(e);
        } finally {
            try {
                transport.close();
            } catch (MessagingException e) {

                if (log.isDebugEnabled()) {
                    log.debug("SendMailService sendDMCMail B Exception e.getMessage()=" + e.getMessage());
                }

                throw new NaiException(e);
            }
        }
        return true;
    }

    /**
     * InputStream To File<br>
     * <br>
     * InputStream To File。<br>
     * <br>
     * @param is InputStream<br>
     * @param file ファイル<br>
     * @return なし<br>
     * @throws NaiException
     */
    public void byteToFile(byte[] bytes, File file) {

        try {
            OutputStream os = new FileOutputStream(file);

            if (bytes != null) {
                os.write(bytes);
            }

            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * DM履歴明細テーブルデータの挿入<br>
     * <br>
     * DM履歴明細テーブルへ追加処理を行う。<br>
     * <br>
     * @param model メール送信モデル<br>
     * @param orderNumber DMコード<br>
     * @param userId ユーザID<br>
     * @return なし<br>
     * @throws NaiException
     */
    public void insertDmHistoryDetailsTrn(SendMailModel model, String orderNumber, String userId) throws NaiException {

        // 初期化処理
        SendMailLogic logic = new SendMailLogic(this.conn);

        DmHistoryDetailsTrnDto dto = this.createDmHistoryDetailsTrnDto(model, orderNumber, userId);

        // データの挿入
        logic.insertDmHistoryDetailsTrn(dto);
    }

    /**
     * DM履歴明細テーブルDTOの作成。<br>
     * <br>
     * DM履歴明細テーブルDTOを作成する。<br>
     * <br>
     * @param model メール送信モデル<br>
     * @param orderNumber DMコード<br>
     * @param userId ユーザID<br>
     * @return DmHistoryDetailsTrnDto DM履歴明細テーブルDTO<br>
     * @exception NaiException
     */
    private DmHistoryDetailsTrnDto createDmHistoryDetailsTrnDto(SendMailModel model, String orderNumber, String userId)
            throws NaiException {

        DmHistoryDetailsTrnDto dto = new DmHistoryDetailsTrnDto();
        // DMコード：
        dto.setDmCd(orderNumber);
        // 受講者ID
        dto.setStudentId(model.getSendId());
        // 受講者名
        dto.setStudentJnm(model.getSendNm());
        // 備考
        dto.setRemark(model.getRemark());
        // レコードバージョン番号：'0（固定値）
        dto.setRecordVerNo(0);
        // 登録者コード
        dto.setInsertCd(userId);
        // 更新者コード
        dto.setUpdateCd(userId);

        return dto;
    }

    /**
     * DM履歴テーブルデータの挿入<br>
     * <br>
     * DM履歴テーブルへ追加処理を行う。<br>
     * <br>
     * @param model メール送信モデル<br>
     * @param orderNumber DMコード<br>
     * @param userId ユーザID<br>
     * @return なし<br>
     * @throws NaiException
     */
    public void insertDmHistoryTrn(SendMailModel model, String orderNumber, String userId) throws NaiException {

        // 初期化処理
        SendMailLogic logic = new SendMailLogic(this.conn);

        DmHistoryTrnDto dto = this.createDmHistoryTrnDto(model, orderNumber, userId);

        // データの挿入
        logic.insertDmHistoryTrn(dto);
    }

    /**
     * DM履歴テーブルDTOの作成。<br>
     * <br>
     * DM履歴テーブルDTOを作成する。<br>
     * <br>
     * @param model メール送信モデル<br>
     * @param orderNumber DMコード<br>
     * @param userId ユーザID<br>
     * @return DmHistoryDetailsTrnDto DM履歴テーブルDTO<br>
     * @exception NaiException
     */
    private DmHistoryTrnDto createDmHistoryTrnDto(SendMailModel model, String orderNumber, String userId)
            throws NaiException {

        // 本文 の先頭から255バイトまで
        String mailText = model.getMailText();
        if (mailText.getBytes().length > 255) {
            mailText = new String(Arrays.copyOf(mailText.getBytes(), 255));
        }

        DmHistoryTrnDto dto = new DmHistoryTrnDto();
        // DMコード
        dto.setDmCd(orderNumber);
        // 送信者ID
        dto.setSendId(userId);
        // 送信者
        dto.setSendNm(model.getSendFrom());
        // 件名
        dto.setSubjectTitle(model.getSubjectTitle());
        // メール本文255バイト分
        dto.setMailText(mailText);
        // レコードバージョン番号：'0（固定値）
        dto.setRecordVerNo(0);
        // 登録者コード
        dto.setInsertCd(userId);
        // 更新者コード
        dto.setUpdateCd(userId);

        return dto;
    }

    /**
     * メール管理マスタデータの取得<br>
     * <br>
     * メール管理マスタへ検索処理を行う。<br>
     * <br>
     * @param mailPatternCd メールパターンコード<br>
     * @return List<MailManagMstDto> メール管理マスタDTOリスト<br>
     * @throws NaiException
     */
    public List<MailManagMstDto> selectMailManagMst(String mailPatternCd) throws NaiException {

        // 初期化処理
        SendMailLogic logic = new SendMailLogic(this.conn);

        MailManagMstDto dto = new MailManagMstDto();
        // メールパターンコード
        dto.setMailPatternCd(mailPatternCd);

        // データの取得＆リターン
        return logic.selectMailManagMst(dto);
    }

    /**
     * レッスン予実テーブルデータの更新<br>
     * <br>
     * レッスン予実テーブルへ更新処理を行う。<br>
     * <br>
     * @param reservationNo 予約No<br>
     * @return なし<br>
     * @exception Exception
     */
    public void updateLessonReservationPerformanceTrn(String reservationNo) throws Exception {

        // 初期化処理
        SendMailLogic logic = new SendMailLogic(this.conn);

        // レッスン予実テーブルデータの取得
        LessonReservationPerformanceTrnDto dto = logic.selectLessonReservationPerformanceTrn(reservationNo);

        // ユーザIDを取得
        String userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();

        // 添付メール送付済区分「0：未送付、1：送付済、9：無し」
        dto.setMailKbn(NaikaraTalkConstants.MAIL_KBN_SENT);
        // 添付メール送付日：システム日付
        dto.setMailDt(DateUtil.getSysDate());
        // 更新者コード
        dto.setUpdateCd(userId);

        // 更新実行
        logic.updateLessonReservationPerformanceTrn(dto);
    }

    /**
     * 文字列（sub）は文字列（str）に出現の回数を取得<br>
     * <br>
     * 文字列（sub）は文字列（str）に出現の回数を取得する。<br>
     * <br>
     * @param sub 文字列<br>
     * @param str 文字列<br>
     * @return int 回数<br>
     * @exception なし
     */
    public int getFrequency(String sub, String str) {

        Pattern ptn = Pattern.compile(sub, Pattern.CASE_INSENSITIVE);
        Matcher matcher = ptn.matcher(str);
        int count = 0;
        while (matcher.find()) {
            count++;
        }

        return count;
    }
}