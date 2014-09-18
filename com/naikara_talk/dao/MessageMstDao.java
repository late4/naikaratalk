package com.naikara_talk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.naikara_talk.dto.MessageMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DAOクラス<br>
 * <b>クラス名称　　　:</b>メッセージマスタのデータ取得処理クラス<br>
 * <b>クラス概要　　　:</b>メッセージマスタのデータ取得処理DAO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/06/05 TECS 新規作成
 */
public class MessageMstDao {

	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public MessageMstDao(Connection con) {
		this.conn = con;
	}

    /**
     * メッセージマスタからデータを検索する。
     *
     * @param messageId
     *            メッセージID
     * @return MessageMstDto
     * @throws NaiException
     *             データベース異常
     */
    public MessageMstDto getMessage(String messageId) throws NaiException {

        ResultSet res = null;

        // SQL文を作成
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT ");
        sb.append("  MESSAGE_ID ");
        sb.append(" ,MESSAGE ");
        sb.append(" ,RECORD_VER_NO ");
        sb.append(" ,INSERT_DTTM ");
        sb.append(" ,INSERT_CD ");
        sb.append(" ,UPDATE_DTTM ");
        sb.append(" ,UPDATE_CD ");
        sb.append(" FROM ");
        sb.append(" MESSAGE_MST ");// メッセージマスタ
        sb.append(" WHERE ");
        sb.append(" MESSAGE_ID = ? ");// 引数渡しされたメッセージID

        try {

            PreparedStatement ps = conn.prepareStatement(sb.toString());

            // 検索条件をセット
            ps.setString(1, messageId);

            // SQL文を実行
            res = ps.executeQuery();

            // MessageMstDtoを作成
            MessageMstDto retDto = new MessageMstDto();

            // 初期値の設定(データなしの場合)
            retDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_NO);

            // 検索結果をdtoにセットする
            while (res.next()) {
                retDto.setMessageId(res.getString("MESSAGE_ID"));                 // メッセージＩＤ
                retDto.setMessage(res.getString("MESSAGE"));                      // メッセージ内容
                retDto.setRecordVerNo(res.getInt("RECORD_VER_NO"));               // レコードバージョン番号
                retDto.setInsertDttm(res.getTimestamp("INSERT_DTTM"));            // 登録日時
                retDto.setInsertCd(res.getString("INSERT_CD"));                   // 登録者コード
                retDto.setUpdateDttm(res.getTimestamp("UPDATE_DTTM"));            // 更新日時
                retDto.setUpdateCd(res.getString("UPDATE_CD"));                   // 更新者コード

                retDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);    // リターンコード

            }


            // 検索結果dtoを返す
            return retDto;

        } catch (SQLException se) {
            throw new NaiException(se);
        } finally {
        	try{
        		if ( res != null ) {
            		res.close();
        		}
        	}catch(SQLException se ){
        		throw new NaiException(se);
        	}
        }
    }
}
