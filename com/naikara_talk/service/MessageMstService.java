package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.MessageMstLogic;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>共通部品：メッセージの取得処理。<br>
 * <b>クラス名称　　　:</b>メッセージマスタ取得Serviceクラス。<br>
 * <b>クラス概要　　　:</b>メッセージマスタ取得Service。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/06/12 TECS 新規作成。
 */
public class MessageMstService implements ActionService {


    /**
     * メッセージの取得。
     *
     * @param String   メッセージID
     * @param String[] メッセージパラメータ
     * @return String  メッセージ内容
     * @throws NaiException
     */
    public String getMessage(String messageId, String[] params)
            throws NaiException {

        Connection conn = null;
        try{
            conn = DbUtil.getConnection();

            // Logic初期化
            MessageMstLogic logic = new MessageMstLogic(conn);

            // データの取得＆リターン
            return logic.getMessage(messageId, params);

        } catch ( SQLException se ) {
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
