package com.naikara_talk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.naikara_talk.dto.GooPurTStuMDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DAOクラス<br>
 * <b>クラス名称　　　:</b>商品購入の有償利用ポイント算出処理クラス<br>
 * <b>クラス概要　　　:</b>商品購入の有償利用ポイント算出処理DAO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/01 TECS 新規作成
 */
public class GooPurTStuMDao extends AbstractDao {

    /** 受講者ID (購入者ID) 条件項目 */
    public static final String COND_STUDENT_ID = "STUDENT_ID";
    /** 購入日の年月  条件項目  */
    public static final String COND_PURCHASE_DT = "PURCHASE_DT";

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public GooPurTStuMDao(Connection con) {
        this.conn = con;
    }

    /**
     * 商品購入の有償利用ポイント算出処理<br>
     * <br>
     * 商品購入の有償利用ポイントを集計して、返却する<br>
     * <br>
     * @param organizationId
     * @param endDate
     * @return BigDecimal:SumPoint
     * @throws NaiException
     */
    public GooPurTStuMDto search(String organizationId, String purchaseDate) throws NaiException {

        // 戻り値とリターンコードの初期化
        GooPurTStuMDto dto = null;

        // 残高ポイントの合計取得
        ResultSet res = null;

        StringBuffer sb = new StringBuffer();
        sb.append(" select ");
        sb.append("   SUM(GPT.PAYMENT_USE_POINT) AS SUM_POINT ");
        sb.append(" from ");

        sb.append(" GOODS_PURCHASE_TRN  AS GPT ");
        sb.append(" left join STUDENT_MST AS SM ");
        sb.append(" on SM.STUDENT_ID = GPT.STUDENT_ID ");
        sb.append(" where ");
        sb.append("   SM.ORGANIZATION_ID = ? ");
        sb.append("  and ");
        sb.append("   GPT.PURCHASE_DT like ? ");

        try {
            PreparedStatement ps = conn.prepareStatement(sb.toString());
            ps.setString(1, organizationId);
            ps.setString(2, purchaseDate + NaikaraTalkConstants.MESSAGE_PARAM_PREFIX);
            res = ps.executeQuery();

            if (res.next())

                dto = new GooPurTStuMDto();
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