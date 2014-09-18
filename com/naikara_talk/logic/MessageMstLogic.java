package com.naikara_talk.logic;

import java.sql.Connection;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dao.MessageMstDao;
import com.naikara_talk.dto.MessageMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 LOGICクラス<br>
 * <b>クラス名称　　　:</b>メッセージ取得処理処理クラス<br>
 * <b>クラス概要　　　:</b>メッセージ取得処理LOGIC<br>
 * <br>
 * <b>著作権　　　　　:</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/06/12 TECS 新規作成
 */
public class MessageMstLogic {

	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public MessageMstLogic(Connection con) {
		this.conn = con;
	}

    /**
     * メッセージ内容取得処理。
     *
     * @param String メッセージID
     * @param String[] 表示項目名等
     * @return String メッセージ内容
     * @throws NaiException
     */
    public String getMessage(String messageId, String[] params)
            throws NaiException {

        // 初期化処理
        MessageMstDto retDto = new MessageMstDto();
        MessageMstDao dao = new MessageMstDao(this.conn);

        // データを取得する
        retDto = dao.getMessage(messageId);

        String message = retDto.getMessage();
        if (StringUtils.isEmpty(message)) {
            return "";
        }

        // メッセージのパラメータ置換
        for (int i = 0; i < params.length; i++) {
        	StringBuffer sb = new StringBuffer();
        	sb = sb.append(NaikaraTalkConstants.MESSAGE_PARAM_PREFIX).append(String.valueOf((i + 1)));
            String regex = sb.toString();
            message = message.replaceAll(regex, params[i]);
        }

        // 戻り値
        return message;

    }
}
