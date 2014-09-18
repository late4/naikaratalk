package com.naikara_talk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dbutil.QueryCondition;
import com.naikara_talk.dto.MailManagMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DAOクラス<br>
 * <b>クラス名称　　　:</b>メール管理マスタのデータ取得クラス<br>
 * <b>クラス概要　　　:</b>メール管理マスタのデータ取得DAO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/06/05 TECS 新規作成
 */
public class MailManagMstDao extends AbstractDao {

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public MailManagMstDao(Connection con) {
        this.conn = con;
    }

    /** メールパターンコード　条件項目 */
    public static final String COND_MAIL_PATTERN_CD = "MAIL_PATTERN_CD";

    /**
     * メール管理マスタからデータを検索する。
     *
     * @param condition
     *            抽出条件
     * @param OrderBy
     *            並び順
     * @return List<MailManagMstDto>
     * @throws NaiException
     *             データベース異常
     */
    public List<MailManagMstDto> search(ConditionMapper conditions, OrderCondition OrderBy) throws NaiException {

        // 戻り値とリターンコードの初期化
        ArrayList<MailManagMstDto> list = null;  // メール管理マスタDTOリスト
        MailManagMstDto retDto = null;           // メール管理マスタDTO

        ResultSet res = null;

        // SQL文を作成
        StringBuffer sb = new StringBuffer();
        sb.append(" select ");
        sb.append("  MAIL_PATTERN_CD ");
        sb.append(" ,SENT_MAIL_ADDRESS ");
        sb.append(" ,ADDRESS ");
        sb.append(" ,CC ");
        sb.append(" ,BCC ");
        sb.append(" ,SUBJECT_TITLE ");
        sb.append(" ,TEXT1 ");
        sb.append(" ,TEXT2 ");
        sb.append(" ,TEXT3 ");
        sb.append(" ,TEXT4 ");
        sb.append(" ,TEXT5 ");
        sb.append(" ,TEXT6 ");
        sb.append(" ,TEXT7 ");
        sb.append(" ,TEXT8 ");
        sb.append(" ,TEXT9 ");
        sb.append(" ,TEXT10 ");
        sb.append(" ,TEXT11 ");
        sb.append(" ,TEXT12 ");
        sb.append(" ,TEXT13 ");
        sb.append(" ,TEXT14 ");
        sb.append(" ,TEXT15 ");
        sb.append(" ,TEXT16 ");
        sb.append(" ,TEXT17 ");
        sb.append(" ,TEXT18 ");
        sb.append(" ,TEXT19 ");
        sb.append(" ,TEXT20 ");
        sb.append(" ,TEXT21 ");
        sb.append(" ,TEXT22 ");
        sb.append(" ,TEXT23 ");
        sb.append(" ,TEXT24 ");
        sb.append(" ,TEXT25 ");
        sb.append(" ,TEXT26 ");
        sb.append(" ,TEXT27 ");
        sb.append(" ,TEXT28 ");
        sb.append(" ,TEXT29 ");
        sb.append(" ,TEXT30 ");
        sb.append(" ,TEXT31 ");
        sb.append(" ,TEXT32 ");
        sb.append(" ,TEXT33 ");
        sb.append(" ,TEXT34 ");
        sb.append(" ,TEXT35 ");
        sb.append(" ,INSERT_DTTM ");
        sb.append(" ,INSERT_CD ");
        sb.append(" ,UPDATE_DTTM ");
        sb.append(" ,UPDATE_CD ");
        sb.append(" FROM ");
        sb.append(" MAIL_MANAG_MST ");// メール管理マスタ
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

            // MailManagMstDtoを作成
            list = new ArrayList<MailManagMstDto>();

            // 検索結果をdtoにセットする
            while (res.next()) {
                retDto = new MailManagMstDto();

                retDto.setMailPatternCd(res.getString("MAIL_PATTERN_CD"));        // メールパターンコード
                retDto.setSentMailAddress(res.getString("SENT_MAIL_ADDRESS"));    // 送信者アドレス
                retDto.setAddress(res.getString("ADDRESS"));                      // 宛先
                retDto.setCc(res.getString("CC"));                                // CC
                retDto.setBcc(res.getString("BCC"));                              // BCC
                retDto.setSubjectTitle(res.getString("SUBJECT_TITLE"));           // 件名
                retDto.setText1(res.getString("TEXT1"));                          // 本文１
                retDto.setText2(res.getString("TEXT2"));                          // 本文２
                retDto.setText3(res.getString("TEXT3"));                          // 本文３
                retDto.setText4(res.getString("TEXT4"));                          // 本文４
                retDto.setText5(res.getString("TEXT5"));                          // 本文５
                retDto.setText6(res.getString("TEXT6"));                          // 本文６
                retDto.setText7(res.getString("TEXT7"));                          // 本文７
                retDto.setText8(res.getString("TEXT8"));                          // 本文８
                retDto.setText9(res.getString("TEXT9"));                          // 本文９
                retDto.setText10(res.getString("TEXT10"));                        // 本文１０
                retDto.setText11(res.getString("TEXT11"));                        // 本文１１
                retDto.setText12(res.getString("TEXT12"));                        // 本文１２
                retDto.setText13(res.getString("TEXT13"));                        // 本文１３
                retDto.setText14(res.getString("TEXT14"));                        // 本文１４
                retDto.setText15(res.getString("TEXT15"));                        // 本文１５
                retDto.setText16(res.getString("TEXT16"));                        // 本文１６
                retDto.setText17(res.getString("TEXT17"));                        // 本文１７
                retDto.setText18(res.getString("TEXT18"));                        // 本文１８
                retDto.setText19(res.getString("TEXT19"));                        // 本文１９
                retDto.setText20(res.getString("TEXT20"));                        // 本文２０
                retDto.setText21(res.getString("TEXT21"));                        // 本文２１
                retDto.setText22(res.getString("TEXT22"));                        // 本文２２
                retDto.setText23(res.getString("TEXT23"));                        // 本文２３
                retDto.setText24(res.getString("TEXT24"));                        // 本文２４
                retDto.setText25(res.getString("TEXT25"));                        // 本文２５
                retDto.setText26(res.getString("TEXT26"));                        // 本文２６
                retDto.setText27(res.getString("TEXT27"));                        // 本文２７
                retDto.setText28(res.getString("TEXT28"));                        // 本文２８
                retDto.setText29(res.getString("TEXT29"));                        // 本文２９
                retDto.setText30(res.getString("TEXT30"));                        // 本文３０
                retDto.setText31(res.getString("TEXT31"));                        // 本文３１
                retDto.setText32(res.getString("TEXT32"));                        // 本文３２
                retDto.setText33(res.getString("TEXT33"));                        // 本文３３
                retDto.setText34(res.getString("TEXT34"));                        // 本文３４
                retDto.setText35(res.getString("TEXT35"));                        // 本文３５
                retDto.setInsertDttm(res.getTimestamp("INSERT_DTTM"));            // 登録日時
                retDto.setInsertCd(res.getString("INSERT_CD"));                   // 登録者コード
                retDto.setUpdateDttm(res.getTimestamp("UPDATE_DTTM"));            // 更新日時
                retDto.setUpdateCd(res.getString("UPDATE_CD"));                   // 更新者コード

                retDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);

                list.add(retDto);
            }

            if (list.size() < 1) {
                list = new ArrayList<MailManagMstDto>();
                retDto = new MailManagMstDto();
                retDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_NO);
                list.add(retDto);
                return list;
            }

            // 検索結果listを返す
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

}
