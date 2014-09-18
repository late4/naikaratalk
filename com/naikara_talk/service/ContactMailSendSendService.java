package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.naikara_talk.batch.SendMailBatch;
import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.ContactMailSendLogic;
import com.naikara_talk.logic.SmailAccountHistoryLogic;
import com.naikara_talk.model.ContactMailSendModel;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様(個人)_初期登録<br>
 * <b>クラス名称　　　:</b>問合せ画面送信Serviceクラス。<br>
 * <b>クラス概要　　　:</b>メール送信をおこなう。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/30 TECS 新規作成。
 *                         2013/01/03 TECS スクールのメール送信・受信履歴照会に伴う対応
 */
public class ContactMailSendSendService implements ActionService {

    /**
     * メール送信処理<br>
     * <br>
     * メール送信処理を行う。<br>
     * <br>
     * @param ContactMailSendModel<br>
     * @return なし <br>
     * @throws NaiException
     */
    public void sendMail(ContactMailSendModel model) throws NaiException {

        // メールパターンコード
        String mailPatternCd = NaikaraTalkConstants.MAIL_PATTERN_CODE_SIS;

        // 送信者
        String sendFrom = model.getManagMailAddress1();

        // 送信者名
        String sendFromNm = NaikaraStringUtil.unionName(model.getManagFamilyJnm(), model.getManagFirstJnm());

        // 宛先IDリスト
        List<String> sendIdList = new ArrayList<String>();
        // (0個) ”” (設定なし)
        sendIdList.add(NaikaraTalkConstants.BRANK);

        // 宛先リスト
        List<String> sendToList = new ArrayList<String>();
        // (0個) ”” (設定なし)
        sendToList.add(NaikaraTalkConstants.BRANK);

        // CC ”” (設定なし)
        String cc = NaikaraTalkConstants.BRANK;

        // BCC ”” (設定なし)
        String bcc = NaikaraTalkConstants.BRANK;

        // 件名リスト
        List<String> subjectTitleList = new ArrayList<String>();
        // 画面の｢お問合せの目的｣の選択されている名称
        subjectTitleList.add(0,
                this.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_CONTACT).get(model.getCodeCategoryContact()));
        // 共通部品：名前の編集(画面の｢お名前(姓)｣、画面の｢お名前(名)｣)
        subjectTitleList.add(1, NaikaraStringUtil.unionName(model.getManagFamilyJnm(), model.getManagFirstJnm()));

        // 本文リスト
        List<List<String>> mailTextList = new ArrayList<List<String>>();

        // 本文リスト(0個目) 画面の｢お問合せの目的｣の選択されている名称
        List<String> textList0 = new ArrayList<String>();
        textList0.add(this.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_CONTACT)
                .get(model.getCodeCategoryContact()));
        mailTextList.add(textList0);

        // 本文リスト(1個目) 画面の｢件名｣
        List<String> textList1 = new ArrayList<String>();
        textList1.add(model.getSubject());
        mailTextList.add(textList1);

        // 本文リスト(2個目) ”” (設定なし)
        List<String> textList2 = new ArrayList<String>();
        textList2.add(NaikaraTalkConstants.BRANK);
        mailTextList.add(textList2);

        // 本文リスト(3個目) 画面の｢組織ID/受講者ID｣
        List<String> textList3 = new ArrayList<String>();
        textList3.add(model.getOrganizationId());
        mailTextList.add(textList3);

        // 本文リスト(4個目) 共通部品：名前の編集(画面の｢お名前(姓)｣、画面の｢お名前(名)｣)
        List<String> textList4 = new ArrayList<String>();
        textList4.add(NaikaraStringUtil.unionName(model.getManagFamilyJnm(), model.getManagFirstJnm()));
        mailTextList.add(textList4);

        // 本文リスト(5個目) 画面の｢電話番号｣
        List<String> textList5 = new ArrayList<String>();
        textList5.add(model.getTel());
        mailTextList.add(textList5);

        // 本文リスト(6個目) 画面の｢メールアドレス｣
        List<String> textList6 = new ArrayList<String>();
        textList6.add(model.getManagMailAddress1());
        mailTextList.add(textList6);

        // 本文リスト(7個目) ”” (設定なし)
        List<String> textList7 = new ArrayList<String>();
        textList7.add(NaikaraTalkConstants.BRANK);
        mailTextList.add(textList7);

        // 本文リスト(8個目) ”” (設定なし)
        List<String> textList8 = new ArrayList<String>();
        textList8.add(NaikaraTalkConstants.BRANK);
        mailTextList.add(textList8);

        // 本文リスト(9個目) 画面の｢ご意見・ご要望・お問合せ内容｣
        List<String> textList9 = new ArrayList<String>();
        textList9.add(model.getContactText());
        mailTextList.add(textList9);

        // 添付 ”” (設定なし)
        String file = NaikaraTalkConstants.BRANK;

        // 予約No ”” (設定なし)
        String reservationNo = NaikaraTalkConstants.BRANK;

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
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
            sAMHiLogic.smailActHistoryInsertMain(NaikaraTalkConstants.MAIL_PATTERN_CONTACT_MAIL_SEND,
            		userId, model.getOrganizationId());
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
     * コード管理マスタからデータ取得処理。<br>
     * <br>
     * コード管理マスタからデータ取得処理。<br>
     * <br>
     * @param category 汎用コード <br>
     * @return LinkedHashMap<String, String><br>
     * @exception NaiException
     */
    public LinkedHashMap<String, String> selectCodeMst(String category) throws NaiException {
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            ContactMailSendLogic contactMailSendLogic = new ContactMailSendLogic(conn);
            // コード管理マスタ検索
            return contactMailSendLogic.selectCodeMst(category);
        } catch (SQLException e) {
            throw new NaiException(e);
        } catch (Exception e) {
            throw new NaiException(e);
        } finally {
            try {
                conn.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

    }
}
