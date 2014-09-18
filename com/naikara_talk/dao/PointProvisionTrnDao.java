package com.naikara_talk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.naikara_talk.dto.PointProvisionTrnDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DAOクラス<br>
 * <b>クラス名称　　　:</b>ポイント所有テーブルデータアクセスクラスクラス<br>
 * <b>クラス概要　　　:</b>ポイント所有テーブルの取得、登録、更新を行う<br>
 * <br>
 * <b>著作権　　　　　:</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/06/10 TECS 新規作成
 */
public class PointProvisionTrnDao extends AbstractDao {

	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public PointProvisionTrnDao(Connection con) {
		this.conn = con;
	}

	/**
	 * ポイント引当テーブル削除（レッスン予約・取消専用）<br>
	 * <br>
	 * 解除ポイントのポイント引当テーブルを削除する<br>
	 * <br>
	 * @param rsvNoPurchaseId：予約No
	 * @param pptCnt：ポイント引当テーブル削除件数
	 * @param consSeq：連番
	 * @return int：0(削除成功),1(削除失敗)
	 * @throws NaiException
	 */
	public int pointProvisionTrnDelete(String rsvNoPurchaseId, int consSeq) throws NaiException {

		// 戻り値初期化
		int result = NaikaraTalkConstants.RETURN_CD_DATA_YES;

		// データ削除処理
		StringBuffer sb = new StringBuffer();
		sb.append("delete from ");
		sb.append("POINT_PROVISION_TRN ");
		sb.append("where RSV_NO_PURCHASE_ID = ? ");
		sb.append("and CONS_SEQ = ? ");

		try{

			PreparedStatement ps = conn.prepareStatement(sb.toString());

			ps.setString(1, rsvNoPurchaseId);
			ps.setInt(2, consSeq);

			if(ps.executeUpdate()==0){
				result = NaikaraTalkConstants.RETURN_CD_DATA_NO;
			}

		} catch ( SQLException se ) {
			throw new NaiException(se);
		}
		return result;
	}

	/**
	 * ポイント引当テーブル登録（レッスン予約・取消専用）<br>
	 * <br>
	 * 引当データのポイント引当テーブルの登録を行う<br>
	 * <br>
	 * @param PointProvisionTrnDto：ポイント引当テーブルDTO
	 * @return int：0(登録成功),1(登録失敗)
	 * @throws NaiException
	 */
	public int pointProvisionTrnInsert(PointProvisionTrnDto pptDto) throws NaiException {

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// 戻り値初期化
		int result = NaikaraTalkConstants.RETURN_CD_DATA_YES;

		// データ削除処理
		StringBuffer sb = new StringBuffer();
		sb.append("insert into ");
		sb.append("POINT_PROVISION_TRN ");
		sb.append("(RSV_NO_PURCHASE_ID ");
		sb.append(",CONS_SEQ ");
		sb.append(",RSV_PURCHASE_KBN ");
		sb.append(",USE_POINT ");
		sb.append(",OWN_ID ");
		sb.append(",COMPENSATION_FREE_KBN ");
		sb.append(",RECORD_VER_NO ");
		sb.append(",INSERT_DTTM ");
		sb.append(",INSERT_CD ");
		sb.append(",UPDATE_DTTM ");
		sb.append(",UPDATE_CD ");
		sb.append(") value (");
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
		sb.append(",? ");
		sb.append(") ");

		try{

			PreparedStatement ps = conn.prepareStatement(sb.toString());

			ps.setString(1, pptDto.getRsvNoPurchaseId());
			ps.setInt(2, pptDto.getConsSeq());
			ps.setString(3, pptDto.getRsvPurchaseKbn());
			ps.setBigDecimal(4, pptDto.getUsePoint());
			ps.setString(5, pptDto.getOwnId());
			ps.setString(6, pptDto.getCompensationFreeKbn());
			ps.setInt(7, pptDto.getRecordVerNo());
			ps.setString(8, String.valueOf(Timestamp.valueOf(sdf.format(cal.getTime()))));
			ps.setString(9, pptDto.getInsertCd());
			ps.setString(10, String.valueOf(Timestamp.valueOf(sdf.format(cal.getTime()))));
			ps.setString(11, pptDto.getUpdateCd());

			if(ps.executeUpdate()==0){
				result = NaikaraTalkConstants.RETURN_CD_DATA_NO;
			}

		} catch ( SQLException se ) {
			throw new NaiException(se);
		}
		return result;
	}
}
