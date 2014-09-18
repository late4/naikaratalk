package com.naikara_talk.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.naikara_talk.batch.SendMailBatch;
import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.SmailAccountHistoryLogic;
import com.naikara_talk.model.StudentMstModel;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守<br>
 * <b>クラス名称　　　:</b>パスワード送信Serviceクラス。<br>
 * <b>クラス概要　　　:</b>パスワード送信をおこなう。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/30 TECS 新規作成。
 *                     </b>2014/01/03 TECS 変更 スクールのメール送信・受信履歴照会に伴う対応
 */
public class StudentMstPasswordSendService implements ActionService {

    /**
     * パスワード送信処理<br>
     * <br>
     * パスワード送信処理を行う。<br>
     * <br>
     * @param StudentMstModel<br>
     * @return なし <br>
     * @throws Exception
     */
    public void sendMail(StudentMstModel model) throws NaiException {

        // メールパターンコード
        String mailPatternCd = NaikaraTalkConstants.MAIL_PATTERN_CODE_PTC;
        // 送信者
        String sendFrom = NaikaraTalkConstants.BRANK;
        // 送信者名
        String sendFromNm = NaikaraTalkConstants.BRANK;
        // 宛先IDリスト
        List<String> sendIdList = new ArrayList<String>();
        sendIdList.add(0, model.getStudentId_lbl());
        // 宛先名アドレスリスト
        List<String> sendToList = new ArrayList<String>();
        sendToList.add(0, model.getMailAddress1_txt());
        // CC
        String cc = NaikaraTalkConstants.BRANK;
        // BCC
        String bcc = NaikaraTalkConstants.BRANK;
        // 件名リスト
        List<String> subjecCTitleList = new ArrayList<String>();
        // 本文リスト1
        List<List<String>> mailTextList = new ArrayList<List<String>>();

        List<String> mailTextList0 = new ArrayList<String>();
        mailTextList0.add(0, model.getFamilyJnm_txt() + NaikaraTalkConstants.BRANK + model.getFirstJnm_txt());
        mailTextList.add(0, mailTextList0);

        List<String> mailTextList1 = new ArrayList<String>();
        mailTextList1.add(0, NaikaraTalkConstants.BRANK);
        mailTextList.add(1, mailTextList1);

        List<String> mailTextList2 = new ArrayList<String>();
        mailTextList2.add(0, NaikaraTalkConstants.BRANK);
        mailTextList.add(2, mailTextList2);

        List<String> mailTextList3 = new ArrayList<String>();
        mailTextList3.add(0, NaikaraTalkConstants.BRANK);
        mailTextList.add(3, mailTextList3);

        List<String> mailTextList4 = new ArrayList<String>();
        mailTextList4.add(0, model.getFamilyJnm_txt() + NaikaraTalkConstants.BRANK + model.getFirstJnm_txt());
        mailTextList.add(4, mailTextList4);

        List<String> mailTextList5 = new ArrayList<String>();
        mailTextList5.add(0, model.getStudentId_lbl());
        mailTextList.add(5, mailTextList5);

        List<String> mailTextList6 = new ArrayList<String>();
        mailTextList6.add(0, model.getPassword_txt());
        mailTextList.add(6, mailTextList6);

        List<String> mailTextList7 = new ArrayList<String>();
        mailTextList7.add(0, NaikaraTalkConstants.BRANK);
        mailTextList.add(7, mailTextList7);

        List<String> mailTextList8 = new ArrayList<String>();
        mailTextList8.add(0, NaikaraTalkConstants.BRANK);
        mailTextList.add(8, mailTextList8);

        List<String> mailTextList9 = new ArrayList<String>();
        mailTextList9.add(0, NaikaraTalkConstants.BRANK);
        mailTextList.add(9, mailTextList9);

        List<String> mailTextList10 = new ArrayList<String>();
        mailTextList10.add(0, NaikaraTalkConstants.BRANK);
        mailTextList.add(10, mailTextList10);

        List<String> mailTextList11 = new ArrayList<String>();
        mailTextList11.add(0, NaikaraTalkConstants.BRANK);
        mailTextList.add(11, mailTextList11);

        List<String> mailTextList12 = new ArrayList<String>();
        mailTextList12.add(0, NaikaraTalkConstants.BRANK);
        mailTextList.add(12, mailTextList12);

        List<String> mailTextList13 = new ArrayList<String>();
        mailTextList13.add(0, NaikaraTalkConstants.BRANK);
        mailTextList.add(13, mailTextList13);

        List<String> mailTextList14 = new ArrayList<String>();
        mailTextList14.add(0, NaikaraTalkConstants.BRANK);
        mailTextList.add(14, mailTextList14);

        List<String> mailTextList15 = new ArrayList<String>();
        mailTextList15.add(0, NaikaraTalkConstants.BRANK);
        mailTextList.add(15, mailTextList15);

        String file = NaikaraTalkConstants.BRANK;

        String reservationNo = NaikaraTalkConstants.BRANK;

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            SendMailBatch sendMailBatch = new SendMailBatch(conn);
            sendMailBatch.sendMail(mailPatternCd, sendFrom, sendFromNm, sendIdList, sendToList, cc, bcc,
                    subjecCTitleList, mailTextList, file, reservationNo);

            // 2014/01/03-スクールのメール送信・受信履歴照会に伴う対応-Start
            // 共通：スクールのメール送信・受信履歴データ作成Logicクラスの生成
            SmailAccountHistoryLogic sAMHiLogic = new SmailAccountHistoryLogic(conn);

            // ユーザIDを取得
            String userId = NaikaraTalkConstants.BRANK;
            if ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString()) != null) {
                userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();
            }

            // スクールメール・アカウント変更履歴テーブルの更新処理
            sAMHiLogic.smailActHistoryInsertMain(NaikaraTalkConstants.MAIL_PATTERN_PWD,
            		userId, model.getStudentId_lbl());
            // 2014/01/03-スクールのメール送信・受信履歴照会に伴う対応-End


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
}
