package com.naikara_talk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dbutil.QueryCondition;
import com.naikara_talk.dto.DmHistoryTrnDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DAOクラス<br>
 * <b>クラス名称　　　:</b>DM履歴テーブルデータアクセスクラス<br>
 * <b>クラス概要　　　:</b>DM履歴テーブルデータ取得、登録DAO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/06/03 TECS 新規作成
 */
public class DmHistoryTrnDao extends AbstractDao {

    /** DMコード　条件項目　*/
    public static final String COND_DM_CD = "DM_CD";
    /** 登録日時　条件項目　*/
    public static final String COND_INSERT_DTTM = "INSERT_DTTM";

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public DmHistoryTrnDao(Connection con) {
        this.conn = con;
    }

    /**
     * DM履歴テーブルデータ取得<br>
     * <br>
     * DM履歴テーブルリストを戻り値で返却する<br>
     * <br>
     * @param conditions
     * @param OrderBy
     * @return ArrayList<DmHistoryTrnDto>
     * @throws NaiException
     */
    public ArrayList<DmHistoryTrnDto> search(ConditionMapper conditions, OrderCondition OrderBy) throws NaiException {

        // 戻り値とリターンコードの初期化
        ArrayList<DmHistoryTrnDto> list = null;  // DM履歴テーブルリスト
        DmHistoryTrnDto dto = null;              // DM履歴テーブルDTO

        ResultSet res = null;

        StringBuffer sb = new StringBuffer();

        // DM履歴テーブルの全項目取得処理
        sb.append(" SELECT ");
        sb.append("  DM_CD");
        sb.append(" ,SEND_ID");
        sb.append(" ,SEND_NM");
        sb.append(" ,SUBJECT_TITLE");
        sb.append(" ,MAIL_TEXT");
        sb.append(" ,RECORD_VER_NO");
        sb.append(" ,INSERT_DTTM");
        sb.append(" ,INSERT_CD");
        sb.append(" ,UPDATE_DTTM");
        sb.append(" ,UPDATE_CD");
        sb.append(" FROM ");
        sb.append("   DM_HISTORY_TRN ");
        // 抽出条件の設定
        if (!StringUtils.isEmpty(conditions.getConditionString())) {
            sb.append("where ");
            sb.append(conditions.getConditionString());
        }
        // 並び順の設定
        if (!StringUtils.isEmpty(OrderBy.getOrderString())) {
            sb.append(OrderBy.getOrderString());
        }

        try {

            PreparedStatement ps = conn.prepareStatement(sb.toString());

            // パラメタの設定
            int i = 0;
            for (QueryCondition cond : conditions.getConditions()) {
                for (String val : cond.getValues()) {
                    ps.setString(i + 1, val);
                    i++;
                }
            }

            // SQL文を実行
            res = ps.executeQuery();

            list = new ArrayList<DmHistoryTrnDto>();
            while (res.next()) {

                dto = new DmHistoryTrnDto();

                dto.setDmCd(res.getString("DM_CD"));
                dto.setSendId(res.getString("SEND_ID"));
                dto.setSendNm(res.getString("SEND_NM"));
                dto.setSubjectTitle(res.getString("SUBJECT_TITLE"));
                dto.setMailText(res.getString("MAIL_TEXT"));
                dto.setRecordVerNo(res.getInt("RECORD_VER_NO"));
                dto.setInsertDttm(res.getTimestamp("INSERT_DTTM"));
                dto.setInsertDt(dto.getInsertDttm().toString());
                dto.setInsertCd(res.getString("INSERT_CD"));
                dto.setUpdateDttm(res.getTimestamp("UPDATE_DTTM"));
                dto.setUpdateCd(res.getString("UPDATE_CD"));

                dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);

                list.add(dto);
            }

            if (list.size() < 1) {
                list = new ArrayList<DmHistoryTrnDto>();
                dto = new DmHistoryTrnDto();
                dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_NO);
                list.add(dto);
                return list;
            }

            return list;

        } catch (SQLException se) {
            throw new NaiException(se);
        } finally {
            try {
                if (res != null) {
                    res.close();
                }
            } catch (SQLException se) {
                throw new NaiException(se);
            }
        }
    }

    /**
     * DM履歴テーブル登録処理<br>
     * <br>
     * DM履歴テーブルの登録を行う<br>
     * <br>
     * @param DmHistoryTrnDto
     * @return insertRowCount
     * @throws NaiException
     */
    public int insert(DmHistoryTrnDto dto) throws NaiException {

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // 登録データ件数
        int insertRowCount = -1;

        // 受講者マスタのデータチェック
        if (!checkDto(dto)) {
            return insertRowCount;
        }

        StringBuffer sb = new StringBuffer();

        // データ登録処理
        sb.append("INSERT INTO ");
        sb.append("DM_HISTORY_TRN ");
        sb.append(" ( ");
        sb.append("  DM_CD");
        sb.append(" ,SEND_ID");
        sb.append(" ,SEND_NM");
        sb.append(" ,SUBJECT_TITLE");
        sb.append(" ,MAIL_TEXT");
        sb.append(" ,RECORD_VER_NO");
        sb.append(" ,INSERT_DTTM");
        sb.append(" ,INSERT_CD");
        sb.append(" ,UPDATE_DTTM");
        sb.append(" ,UPDATE_CD");
        sb.append(" )VALUES( ");
        sb.append(" ? ");
        sb.append(",? ");
        sb.append(",? ");
        sb.append(",? ");
        sb.append(",? ");
        sb.append(",? ");
        sb.append(",? ");
        sb.append(",? ");
        sb.append(",? ");
        sb.append(",? ");
        sb.append(" ) ");

        try {

            PreparedStatement ps = conn.prepareStatement(sb.toString());

            ps.setString(1, dto.getDmCd());
            ps.setString(2, dto.getSendId());
            ps.setString(3, dto.getSendNm());
            ps.setString(4, dto.getSubjectTitle());
            ps.setString(5, dto.getMailText());
            ps.setInt(6, dto.getRecordVerNo());
            ps.setString(7, String.valueOf(Timestamp.valueOf(sdf.format(cal.getTime()))));
            ps.setString(8, dto.getInsertCd());
            ps.setString(9, String.valueOf(Timestamp.valueOf(sdf.format(cal.getTime()))));
            ps.setString(10, dto.getUpdateCd());

            insertRowCount = ps.executeUpdate();

            return insertRowCount;

        } catch (SQLException se) {
            throw new NaiException(se);
        }
    }

    /**
     * DM履歴テーブル登録、更新データチェック<br>
     * <br>
     * DM履歴テーブルの登録、更新データをチェックし<br>
     * エラー有の場合は、falseを返す<br>
     * エラー無の場合は、trueを返す<br>
     * <br>
     * @param DmHistoryTrnDto
     * @return boolean
     */
    private boolean checkDto(DmHistoryTrnDto dto) throws NaiException {

        // DMコードのチェック：getDmCd
        if (dto.getDmCd() == null || "".equals(dto.getDmCd())) {
            return false;
        }

        // 送信者IDのチェック：getSendId
        if (dto.getSendId() == null || "".equals(dto.getSendId())) {
            return false;
        }

        // 送信者名のチェック：getSendNm
        if (dto.getSendNm() == null || "".equals(dto.getSendNm())) {
            return false;
        }

        // 件名のチェック：getSubjectTitle
        if (dto.getSubjectTitle() == null || "".equals(dto.getSubjectTitle())) {
            return false;
        }

        // 登録者コードのチェック：getInsertCd
        if (dto.getInsertCd() == null || "".equals(dto.getInsertCd())) {
            return false;
        }

        // 更新者コードのチェック：getUpdateCd
        if (dto.getUpdateCd() == null || "".equals(dto.getUpdateCd())) {
            return false;
        }

        return true;

    }

}
