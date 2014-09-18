package com.naikara_talk.batch;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.naikara_talk.service.SendMailService;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>バッチ<br>
 * <b>クラス名称       :</b>予約取消・購入のメール送信処理Batchクラス<br>
 * <b>クラス概要       :</b>各機能より起動されてＥメール送信処理を行う。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/04 TECS 新規作成
 */
public class SendMailBatch {

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public SendMailBatch(Connection con) {
        this.conn = con;
    }

    /**
     * メール送信処理<br>
     * <br>
     * メール送信処理を行う。<br>
     * <br>
     * @param mailPatternCd メールパターンコード<br>
     * @param sendFrom 送信者<br>
     * @param sendFromNm 送信者名<br>
     * @param sendIdList 宛先IDリスト<br>
     * @param sendToList 宛先リスト<br>
     * @param cc CC<br>
     * @param bcc BCC<br>
     * @param subjectTitleList 件名リスト<br>
     * @param mailTextList 本文リスト<br>
     * @param file 添付<br>
     * @param reservationNo 予約No<br>
     * @return なし <br>
     * @throws Exception
     */
    public void sendMail(String mailPatternCd, String sendFrom, String sendFromNm, List<String> sendIdList,
            List<String> sendToList, String cc, String bcc, List<String> subjectTitleList,
            List<List<String>> mailTextList, String file, String reservationNo) throws Exception {

        // ログのの初期化
        Logger log = Logger.getLogger(SendMailBatch.class);

        if (log.isDebugEnabled()) {
            log.debug("SendMailBatch sendMail Start");
        }

        SendMailService service = new SendMailService(this.conn);

        // 引数：メールパターンコード = ”DMC” (DM) の場合は、以下の処理を行う
        if (StringUtils.equals(mailPatternCd, NaikaraTalkConstants.MAIL_PATTERN_CODE_DMC)) {

            if (log.isDebugEnabled()) {
                log.debug("DMC Mail Start");
                log.debug("sendIdList= " + sendIdList.toString());
                log.debug("subjectTitleList.get(0)= " + subjectTitleList.get(0).toString());
                log.debug("mailTextList.get(0).get(0)= " + mailTextList.get(0).get(0).toString());
            }

            // 「DMC」メール送信
            service.directMailCustomer(sendIdList, subjectTitleList.get(0), mailTextList.get(0).get(0), sendFromNm);

            if (log.isDebugEnabled()) {
                log.debug("DMC Mail End");
            }

        } else {
            if (log.isDebugEnabled()) {
                log.debug("Other Mail Start");
                log.debug("mailPatternCd= " + mailPatternCd.toString());
                log.debug("sendFrom= " + sendFrom.toString());
                log.debug("sendToList= " + sendToList.toString());
                log.debug("cc= " + cc.toString());
                log.debug("bcc= " + bcc.toString());
                log.debug("subjectTitleList= " + subjectTitleList.toString());
                log.debug("mailTextList= " + mailTextList.toString());
                log.debug("file= " + file.toString());
                log.debug("reservationNo= " + reservationNo.toString());
            }

            // 「その他」メール送信
            service.othersPattern(mailPatternCd, sendFrom, sendToList, cc, bcc, subjectTitleList, mailTextList, file,
                    reservationNo);

            if (log.isDebugEnabled()) {
                log.debug("Other Mail End");
            }

        }

        if (log.isDebugEnabled()) {
            log.debug("SendMailBatch sendMail End");
        }

    }

}
