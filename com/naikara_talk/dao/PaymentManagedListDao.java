package com.naikara_talk.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.naikara_talk.dto.PaymentManagedListDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>売上入金・支払管理。<br>
 * <b>クラス名称　　　:</b>支払管理ページのデータ取得処理クラス。<br>
 * <b>クラス概要　　　:</b>支払管理ページのデータ取得処理DAO。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴　　　　:</b>2013/07/23 TECS 新規作成。
 */

public class PaymentManagedListDao extends AbstractDao {

	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public PaymentManagedListDao(Connection con) {
		this.conn = con;
	}

	/**
	 * 更新
	 * @param list 「支払対象」＝ONのデータ
	 * @param managerCdOne 支払
	 * @param managerCdZero 未支払
	 * @param payment_txt 支払年月
	 * @return int
	 * @throws NaiException
	 * @throws IOException
	 */
	public int update(List<PaymentManagedListDto> list, String managerCdOne, String managerCdZero, String payment_txt)
			throws NaiException {

		// この画面を開いたロールを取得します(この段階に到達した時点でユーザ情報は存在している)
		String userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();

		Calendar cal = Calendar.getInstance();
 		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		int updatedRowCount = NaikaraTalkConstants.RETURN_CD_ERR_UPD; // 更新データ件数

		int index = 1;

        // 支払対象区分名は未支払にするsql文
        StringBuffer sbOff = new StringBuffer();
        sbOff.append(" UPDATE PAYMENT_TRN ");
        sbOff.append(" SET ");
        sbOff.append("   PAYMENT_KBN = 0 ");
        sbOff.append("  ,PAYMENT_NM = ? ");
        sbOff.append("  ,UPDATE_DTTM = ?");
        sbOff.append("  ,UPDATE_CD = ? ");
        sbOff.append(" WHERE ");
        sbOff.append(" PAYMENT_PLAN_YYYY_MM = ? ");

        // 支払対象区分名は支払にするsql文
        StringBuffer sbU = new StringBuffer();
        sbU.append(" UPDATE PAYMENT_TRN ");
        sbU.append(" SET ");
        sbU.append("   PAYMENT_KBN = 1 ");
        sbU.append("  ,PAYMENT_NM = ? ");
        sbU.append("  ,RECORD_VER_NO = RECORD_VER_NO + 1 ");
        sbU.append("  ,UPDATE_DTTM = ?");
        sbU.append("  ,UPDATE_CD = ? ");
        sbU.append(" WHERE ");
        sbU.append(" PAYMENT_PLAN_YYYY_MM = ? ");
        sbU.append(" AND DATA_YYYY_MM = ?");
        sbU.append(" AND USER_ID = ?");
        sbU.append(" AND RECORD_VER_NO = ?");

        PreparedStatement ps = null;
        try {
			PaymentManagedListDto dto;

			// 画面で入力した支払年月のデータ、全てOFFにします。
			ps = conn.prepareStatement(sbOff.toString());
			ps.setString(index++, managerCdZero);                                                  // 支払対象区分名 未支払
			ps.setString(index++, String.valueOf(Timestamp.valueOf(sdf.format(cal.getTime()))));   // 更新日時
			ps.setString(index++, userId);                                                         // 更新者コード
			ps.setString(index++, payment_txt);                                                    // 支払予定年月
			// SQL文を実行
			updatedRowCount = ps.executeUpdate();

			// 更新処理は「支払対象」＝ONのデータ分繰り返す
			for (int i = 0; i < list.size(); i++) {
				dto = list.get(i);
				index = 1;
				ps = conn.prepareStatement(sbU.toString());
				ps.setString(index++, managerCdOne);                                                   // 支払対象区分名
				ps.setString(index++, String.valueOf(Timestamp.valueOf(sdf.format(cal.getTime()))));   // 更新日時
				ps.setString(index++, userId);                                                         // 更新者コード
				ps.setString(index++, dto.getPaymentPlanYyyyMm());                                     // 支払予定年月
				ps.setString(index++, dto.getDataYyyyMm());                                            // データ発生年月
				ps.setString(index++, dto.getUserId());                                                // 講師ID (利用者ID)
				ps.setInt(index++, Integer.valueOf(dto.getRecordVerNo()));                             // レコードバージョン番号

				// SQL文を実行
				updatedRowCount = ps.executeUpdate();

				// 更新データ件数 未更新の場合return
				if (updatedRowCount == NaikaraTalkConstants.RETURN_CD_ERR_NO_UPD) {
					// DB更新なし(排他エラー)
					return updatedRowCount;
				}
			}
        } catch (SQLException se) {
	        throw new NaiException(se);
	    } finally {
	    	try{
	            if (ps != null) {
	                ps.close();
	            }
	    	}catch(SQLException se ){
	    		throw new NaiException(se);
	    	}
	    }

		return updatedRowCount;

	}

}
