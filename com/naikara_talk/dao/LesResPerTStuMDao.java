package com.naikara_talk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.naikara_talk.dto.LesResPerTStuMDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DAOクラス<br>
 * <b>クラス名称　　　:</b>レッスン予実の有償利用ポイント算出処理クラス<br>
 * <b>クラス概要　　　:</b>レッスン予実の有償利用ポイント算出処理DAO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/01 TECS 新規作成
 */
public class LesResPerTStuMDao extends AbstractDao {

    /** 受講者ID (購入者ID) 条件項目 */
    public static final String COND_STUDENT_ID = "STUDENT_ID";
    /** レッスン日の年月  条件項目  */
    public static final String COND_LESSON_DT = "LESSON_DT";

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public LesResPerTStuMDao(Connection con) {
        this.conn = con;
    }

    /**
     * レッスン予実の有償利用ポイント算出処理<br>
     * <br>
     * レッスン予実の有償利用ポイントを集計して、返却する<br>
     * <br>
     * @param organizationId
     * @param endDate
     * @return BigDecimal:SumPoint
     * @throws NaiException
     */
    public LesResPerTStuMDto search(String organizationId, String lessonDate) throws NaiException {

        // 戻り値とリターンコードの初期化
        LesResPerTStuMDto dto = null;

        // 残高ポイントの合計取得
        ResultSet res = null;

        StringBuffer sb = new StringBuffer();
        sb.append(" select ");
        sb.append("   SUM(LRPT.PAYMENT_USE_POINT) AS SUM_POINT ");
        sb.append(" from ");

        sb.append(" LESSON_RESERVATION_PERFORMANCE_TRN  AS LRPT ");
        sb.append(" left join STUDENT_MST AS SM ");
        sb.append(" on SM.STUDENT_ID = LRPT.STUDENT_ID ");
        sb.append(" where ");
        sb.append("   SM.ORGANIZATION_ID = ? ");
        sb.append("  and ");
        sb.append("   LRPT.LESSON_DT like ? ");

        try {
            PreparedStatement ps = conn.prepareStatement(sb.toString());
            ps.setString(1, organizationId);
            ps.setString(2, lessonDate + NaikaraTalkConstants.MESSAGE_PARAM_PREFIX);
            res = ps.executeQuery();

            if (res.next())

                dto = new LesResPerTStuMDto();
            dto.setPaymentUsePoint(res.getBigDecimal("SUM_POINT"));

            return dto;

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
}