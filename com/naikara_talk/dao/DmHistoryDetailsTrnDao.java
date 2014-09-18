package com.naikara_talk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dbutil.QueryCondition;
import com.naikara_talk.dto.DmHistoryDetailsTrnDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DAOクラス<br>
 * <b>クラス名称　　　:</b>DM履歴明細テーブルデータアクセスクラス<br>
 * <b>クラス概要　　　:</b>DM履歴明細テーブルデータ取得、登録DAO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/06/03 TECS 新規作成
 */
public class DmHistoryDetailsTrnDao extends AbstractDao {

    /** 受講者ID　条件項目　*/
    public static final String COND_STUDENT_ID = "STUDENT_ID";
    /** DMコード　条件項目　*/
    public static final String COND_DM_CD = "DM_CD";

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public DmHistoryDetailsTrnDao(Connection con) {
        this.conn = con;
    }

    /**
     * DM履歴明細テーブルデータ取得<br>
     * <br>
     * DM履歴明細テーブルリストを戻り値で返却する<br>
     * <br>
     * @param conditions
     * @param OrderBy
     * @return ArrayList<DmHistoryDetailsTrnDto>
     * @throws NaiException
     */
    public ArrayList<DmHistoryDetailsTrnDto> search(ConditionMapper conditions, OrderCondition OrderBy)
            throws NaiException {

        // 戻り値とリターンコードの初期化
        ArrayList<DmHistoryDetailsTrnDto> list = null;  // DM履歴テーブルリスト
        DmHistoryDetailsTrnDto dto = null;              // DM履歴テーブルDTO

        ResultSet res = null;

        StringBuffer sb = new StringBuffer();

        // DM履歴明細テーブルの全項目取得処理
        sb.append(" SELECT ");
        sb.append("  DM_CD");
        sb.append(" ,STUDENT_ID");
        sb.append(" ,STUDENT_JNM");
        sb.append(" ,REMARK");
        sb.append(" ,RECORD_VER_NO");
        sb.append(" ,INSERT_DTTM");
        sb.append(" ,INSERT_CD");
        sb.append(" ,UPDATE_DTTM");
        sb.append(" ,UPDATE_CD");
        sb.append(" FROM ");
        sb.append("   DM_HISTORY_DETAILS_TRN ");
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

            list = new ArrayList<DmHistoryDetailsTrnDto>();
            while (res.next()) {

                dto = new DmHistoryDetailsTrnDto();

                dto.setDmCd(res.getString("DM_CD"));
                dto.setStudentId(res.getString("STUDENT_ID"));
                dto.setStudentJnm(res.getString("STUDENT_JNM"));
                dto.setRemark(res.getString("REMARK"));
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
                list = new ArrayList<DmHistoryDetailsTrnDto>();
                dto = new DmHistoryDetailsTrnDto();
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
     * DM履歴明細テーブル登録処理<br>
     * <br>
     * DM履歴明細テーブルの登録を行う<br>
     * <br>
     * @param DmHistoryDetailsTrnDto
     * @return insertRowCount
     * @throws NaiException
     */
    public int insert(DmHistoryDetailsTrnDto dto) throws NaiException {

        // 登録データ件数
        int insertRowCount = NaikaraTalkConstants.RETURN_CD_ERR_INS;

        // 受講者マスタのデータチェック
        if (!checkDto(dto)) {
            return insertRowCount;
        }

        StringBuffer sb = new StringBuffer();

        // データ登録処理
        sb.append("INSERT INTO ");
        sb.append("DM_HISTORY_DETAILS_TRN ");
        sb.append(" ( ");
        sb.append("  DM_CD ");
        sb.append(" ,STUDENT_ID ");
        sb.append(" ,STUDENT_JNM ");
        sb.append(" ,REMARK ");
        sb.append(" ,RECORD_VER_NO ");
        sb.append(" ,INSERT_DTTM ");
        sb.append(" ,INSERT_CD ");
        sb.append(" ,UPDATE_DTTM ");
        sb.append(" ,UPDATE_CD ");
        sb.append(" )VALUES( ");
        sb.append(" ? ");           // 01.DMコード
        sb.append(",? ");           // 02.受講者ID
        sb.append(",? ");           // 03.受講者名
        sb.append(",? ");           // 04.備考
        sb.append(",? ");           // 05.レコードバージョン番号
        sb.append(" ,sysdate() ");
        sb.append(" ,? ");          // 06.登録者コード
        sb.append(" ,sysdate() ");
        sb.append(" ,? ");          // 07.更新者コード
        sb.append(" ) ");

        try {

            PreparedStatement ps = conn.prepareStatement(sb.toString());

            ps.setString(1, dto.getDmCd());        // 01.DMコード
            ps.setString(2, dto.getStudentId());   // 02.受講者ID
            ps.setString(3, dto.getStudentJnm());  // 03.受講者名
            ps.setString(4, dto.getRemark());      // 04.備考
            ps.setInt(5, dto.getRecordVerNo());    // 05.レコードバージョン番号
            ps.setString(6, dto.getInsertCd());    // 06.登録者コード
            ps.setString(7, dto.getUpdateCd());    // 07.更新者コード

            insertRowCount = ps.executeUpdate();

            return insertRowCount;

        } catch (SQLException se) {
            throw new NaiException(se);
        }
    }

    /**
     * DM履歴明細テーブル登録、更新データチェック<br>
     * <br>
     * DM履歴明細テーブルの登録、更新データをチェックし<br>
     * エラー有の場合は、falseを返す<br>
     * エラー無の場合は、trueを返す<br>
     * <br>
     * @param DmHistoryDetailsTrnDto
     * @return boolean
     */
    private boolean checkDto(DmHistoryDetailsTrnDto dto) throws NaiException {

        // DMコードのチェック：getDmCd
        if (dto.getDmCd() == null || "".equals(dto.getDmCd())) {
            return false;
        }

        // 受講者IDのチェック：getStudentId
        if (dto.getStudentId() == null || "".equals(dto.getStudentId())) {
            return false;
        }

        // 受講者名のチェック：getStudentJnm
        if (dto.getStudentJnm() == null || "".equals(dto.getStudentJnm())) {
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
