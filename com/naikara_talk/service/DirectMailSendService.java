package com.naikara_talk.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.naikara_talk.batch.SendMailBatch;
import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.DirectMailSendModel;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守<br>
 * <b>クラス名称　　　:</b>受講者マスタメンテナンス ダイレクトメール送信Serviceクラス。<br>
 * <b>クラス概要　　　:</b>受講者マスタメンテナンス ダイレクトメール送信をおこなう。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/30 TECS 新規作成。
 */
public class DirectMailSendService implements ActionService {

    /**
     * メール送信処理<br>
     * <br>
     * メール送信処理を行う。<br>
     * <br>
     * @param DirectMailSendSendModel<br>
     * @return なし <br>
     * @throws Exception
     */
    public void sendMail(DirectMailSendModel model) throws NaiException {

        // メールパターンコード
        String mailPatternCd = NaikaraTalkConstants.MAIL_PATTERN_CODE_DMC;
        // 送信者
        String sendFrom = NaikaraTalkConstants.BRANK;
        // 送信者名
        String sendFromNm = model.getUserId();
        // 宛先IDリスト
        List<String> sendIdList = model.getStudentIdLst();
        // 宛先リスト
        List<String> sendToList = model.getMailAddrLst();
        // CC
        String cc = NaikaraTalkConstants.BRANK;
        // BCC
        String bcc = NaikaraTalkConstants.BRANK;
        // 件名リスト
        List<String> subjecCTitleList = new ArrayList<String>();
        subjecCTitleList.add(0, model.getSubject());
        // 本文リスト
        List<List<String>> mailTextList = new ArrayList<List<String>>();
        List<String> mailTextList0 = new ArrayList<String>();
        mailTextList0.add(0, model.getMailContent());
        mailTextList.add(mailTextList0);
        // 添付
        String file = NaikaraTalkConstants.BRANK;

        String reservationNo = NaikaraTalkConstants.BRANK;

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            SendMailBatch sendMailBatch = new SendMailBatch(conn);
            sendMailBatch.sendMail(mailPatternCd, sendFrom, sendFromNm, sendIdList, sendToList, cc, bcc,
                    subjecCTitleList, mailTextList, file, reservationNo);
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
