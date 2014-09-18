package com.naikara_talk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;

import com.naikara_talk.dto.SmailAccountHistoryTrnDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DAOクラス<br>
 * <b>クラス名称　　　:</b>スクールメール・アカウント変更履歴テーブルデータアクセスクラスクラス<br>
 * <b>クラス概要　　　:</b>スクールメール・アカウント変更履歴テーブルの登録を行う<br>
 * <br>
 * <b>著作権　　　　　:</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/12/29 TECS 新規作成
 */
public class SmailAccountHistoryTrnDao extends AbstractDao {

    protected Logger log = Logger.getLogger(this.getClass());


    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public SmailAccountHistoryTrnDao(Connection con) {
        this.conn = con;
    }


    /**
     * スクールメール・アカウント変更履歴テーブルのデータ取得<br>
     * <br>
     * スクールメール・アカウント変更履歴テーブルの条件に一致する連番の最大値を戻り値で返却する<br>
     * <br>
     * @param conditions 検索条件<br>
     * @param orderConditions 並び順条件<br>
     * @return int 最大値<br>
     * @exception NaiException
     */
    public int searchMaxSeq(Timestamp sendDttm) throws NaiException {

        int rtn = 0;
        ResultSet res = null;

        // スクールメール・アカウント変更履歴テーブルの連番の最大値の取得
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT ");
        sb.append("   MAX(SEND_DTTM_SEQ) MAX_SEQ");    // 連番
        sb.append(" FROM ");
        sb.append("   SMAIL_ACCOUNT_HISTORY_TRN ");    // スクールメール・アカウント変更履歴テーブル
        sb.append(" WHERE ");
        sb.append("   SEND_DTTM = ?");

        try {

            // PreparedStatementの作成
            PreparedStatement ps = conn.prepareStatement(sb.toString());
            ps.setTimestamp(1, sendDttm);

            // SQL文の実行
            res = ps.executeQuery();

            while (res.next()) {
                rtn = res.getInt("MAX_SEQ");    // 連番の最大値
            }

            // 返却値
            return rtn;

        } catch (SQLException se) {
            log.info(se);
            throw new NaiException(se);
        } finally {
            try {
                if (res != null) {
                    res.close();
                }
            } catch (SQLException e) {
                log.info(e);
                e.printStackTrace();
                throw new NaiException(e);
            }
        }
    }


    /**
     * スクールメール・アカウント変更履歴テーブル登録処理<br>
     * <br>
     * スクールメール・アカウント変更履歴テーブルの登録を行う<br>
     * <br>
     * @param dto 登録データ
     * @return insertRowCount 登録件数
     * @exception NaiException
     */
    public int insert(SmailAccountHistoryTrnDto dto) throws NaiException {

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        int insertRowCount = NaikaraTalkConstants.RETURN_CD_ERR_INS; // 登録データ件数

        StringBuffer sb = new StringBuffer();

        // データ登録処理
        sb.append("insert into ");
        sb.append(" SMAIL_ACCOUNT_HISTORY_TRN ");
        sb.append(" ( ");
        sb.append(" SEND_DTTM ");        // 01.送信日時
        sb.append(",SEND_DTTM_SEQ ");    // 02.連番
        sb.append(",MAIL_PATTERN_CD ");  // 03.メールパターンコード
        sb.append(",STUDENT_ID ");       // 04.受講者ID
        sb.append(",RECORD_VER_NO ");    // 05.レコードバージョン番号
        sb.append(",INSERT_DTTM ");      // 06.登録日時
        sb.append(",INSERT_CD ");        // 07.登録者コード
        sb.append(",UPDATE_DTTM ");      // 08.更新日時
        sb.append(",UPDATE_CD ");        // 09.更新者コード
        sb.append(" ) value ( ");
        sb.append(" ? ");                // 01.SEND_DTTM
        sb.append(",? ");                // 02.SEND_DTTM_SEQ
        sb.append(",? ");                // 03.MAIL_PATTERN_CD
        sb.append(",? ");                // 04.STUDENT_ID
        sb.append(",? ");                // 05.RECORD_VER_NO
        sb.append(",? ");                // 06.INSERT_DTTM
        sb.append(",? ");                // 07.INSERT_CD
        sb.append(",? ");                // 08.UPDATE_DTTM
        sb.append(",? ");                // 09.UPDATE_CD
        sb.append(" ) ");

        try {

            PreparedStatement ps = conn.prepareStatement(sb.toString());

            ps.setTimestamp(1, dto.getSendDttm());                    // 01.送信日時
            ps.setInt(2, dto.getSendDttmSeq());                       // 02.連番
            ps.setString(3, dto.getMailPatternCd());                  // 03.メールパターンコード
            ps.setString(4, dto.getStudentId());                      // 04.受講者ID
            ps.setString(5, String.valueOf(dto.getRecordVerNo()));    // 05.レコードバージョン番号
            ps.setString(6, String.valueOf(Timestamp.valueOf(
            		sdf.format(cal.getTime()))));                     // 06.登録日時
            ps.setString(7, dto.getInsertCd());                       // 07.登録者コード
            ps.setString(8, String.valueOf(Timestamp.valueOf(
            		sdf.format(cal.getTime()))));                     // 08.更新日時
            ps.setString(9, dto.getUpdateCd());                       // 09.更新者コード

            // 実行
            insertRowCount = ps.executeUpdate();

            // 返却
            return insertRowCount;

        } catch (SQLException se) {
            se.printStackTrace();
            throw new NaiException(se);
        }
    }



}
