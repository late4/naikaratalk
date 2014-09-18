package com.naikara_talk.dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dto.OrderNumberDto;
import com.naikara_talk.dto.OrganizationPointAssignmentListDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.OrderNumbersMstLogic;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>ポイント割振り<br>
 * <b>クラス名称　　　:</b>ポイント割振りのデータ取得処理クラス。<br>
 * <b>クラス概要　　　:</b>ポイント割振りのデータ取得処理DAO。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/17 TECS 新規作成。
 */
public class OrganizationPointAssignmentListDao extends AbstractDao {

	/** 入力０判断値 */
    private static final int INSERT_ZERO = 0;

	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public OrganizationPointAssignmentListDao(Connection con) {
		this.conn = con;
	}

	/**
	 * ポイント所有テーブルより残高ポイントの取得を行う (集計値)。
	 * @param dto
	 * @return OrganizationPointAssignmentListDto
	 * @throws NaiException
	 */
	public OrganizationPointAssignmentListDto getBalancePoint(OrganizationPointAssignmentListDto dto) throws NaiException{

		OrganizationPointAssignmentListDto returnDto = null;

		ResultSet res = null;

		StringBuffer sb = new StringBuffer();
		sb.append("   SELECT ");
		sb.append("   SUM(POT.BALANCE_POINT) BALANCE_POINT");
		sb.append("   FROM ");
		sb.append("   STUDENT_MST SM ");
		sb.append("   INNER JOIN POINT_OWNERSHIP_TRN POT ON SM.STUDENT_ID = POT.STUDENT_ID ");
		sb.append("   WHERE ");
		sb.append("   SM.ORGANIZATION_ID = ? ");
		sb.append("   AND POT.EFFECTIVE_STR_DT = ? ");
		sb.append("   AND POT.EFFECTIVE_END_DT = ? ");

		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement(sb.toString());
			int index = 1;
			ps.setString(index++, dto.getOrganizationId());    // 組織ID
			ps.setString(index++, dto.getContractFrom());      // 有効開始日
			ps.setString(index++, dto.getContractTo());        // 有効終了日
			res = ps.executeQuery();
			int rowCount = NaikaraTalkConstants.RETURN_CD_DATA_YES;

			while (res.next()){
				returnDto = new OrganizationPointAssignmentListDto();
				returnDto.setBalancePoint(res.getBigDecimal("BALANCE_POINT"));
				returnDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);
				rowCount ++;
			}
			// データなしの場合
			if(rowCount == NaikaraTalkConstants.RETURN_CD_DATA_YES) {
				returnDto = new OrganizationPointAssignmentListDto();
				dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_NO);
			}

			return returnDto;

        } catch (SQLException se) {
            throw new NaiException(se);
        } finally {
        	try{
        		if ( res != null ) {
            		res.close();
        		}
        		if (ps != null) {
					ps.close();
				}
        	}catch(SQLException se ){
        		throw new NaiException(se);
        	}
        }
	}

	/**
	 * 購入／無償ポイントと残高ポイントの取得。
	 * @param dto
	 * @return　OrganizationPointAssignmentListDto
	 * @throws NaiException
	 */
	public OrganizationPointAssignmentListDto getPointOwnershipTrn(OrganizationPointAssignmentListDto dto) throws NaiException{

		OrganizationPointAssignmentListDto returnDto = null;

		ResultSet res = null;

		StringBuffer sb = new StringBuffer();
		sb.append("   SELECT ");
		sb.append("   POT.OWNERSHIP_ID OWNERSHIP_ID ");
		sb.append("   ,POT.PURCHASE_FREE_POINT PURCHASE_FREE_POINT ");
		sb.append("   ,POT.BALANCE_POINT BALANCE_POINT ");
		sb.append("   ,POT.RECORD_VER_NO ");
		sb.append("   FROM POINT_OWNERSHIP_TRN POT ");
		sb.append("   WHERE ");
		sb.append("   POT.STUDENT_ID = ? ");
		sb.append("   AND POT.EFFECTIVE_STR_DT = ? ");
		sb.append("   AND POT.EFFECTIVE_END_DT = ? ");

		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement(sb.toString());
			ps.setString(1,dto.getStudentId());       // 受講者ID
			ps.setString(2,dto.getContractFrom());    // 有効開始日
			ps.setString(3,dto.getContractTo());      // 有効終了日
			res = ps.executeQuery();
			int rowCount = NaikaraTalkConstants.RETURN_CD_DATA_YES;

			while (res.next()){
				returnDto = new OrganizationPointAssignmentListDto();
				returnDto.setOwnershipId(res.getString("OWNERSHIP_ID"));
				returnDto.setPurchaseFreePoint(res.getBigDecimal("PURCHASE_FREE_POINT"));
				returnDto.setBalancePoint(res.getBigDecimal("BALANCE_POINT"));
				returnDto.setRecordVerNo(res.getInt("RECORD_VER_NO"));
				returnDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);
				rowCount ++;
			}
			// データなしの場合
			if (rowCount == NaikaraTalkConstants.RETURN_CD_DATA_YES) {
				returnDto = new OrganizationPointAssignmentListDto();
				returnDto.setPurchaseFreePoint(new BigDecimal(0));
				returnDto.setBalancePoint(new BigDecimal(0));
				dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_NO);
			}

			return returnDto;

        } catch (SQLException se) {
            throw new NaiException(se);
        } finally {
        	try{
        		if ( res != null ) {
            		res.close();
        		}
        		if (ps != null) {
					ps.close();
				}
        	}catch(SQLException se ){
        		throw new NaiException(se);
        	}
        }
	}

	/**
	 * 更新処理。<br>
	 * <br>
     * @param SaleGoodsMstDto
     *            画面のパラメータ
     * @return int 更新レコード数
     * @throws NaiException
     * @throws IOException
	 */
	public int update(OrganizationPointAssignmentListDto dto, List<OrganizationPointAssignmentListDto> list)
			throws NaiException {
		Calendar cal = Calendar.getInstance();
 		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// この画面を開いたロールを取得します(この段階に到達した時点でユーザ情報は存在している)
		String userId = ((SessionUser) SessionDataUtil
				.getSessionData(SessionUser.class.toString())).getUserId();

		int rowCount = NaikaraTalkConstants.RETURN_CD_ERR_UPD; // 更新データ件数

        // SQL文作成
        StringBuffer sb = new StringBuffer();
        sb.append(" UPDATE ORGANIZATION_MST ");
        sb.append(" SET ");
        sb.append("  BALANCE_POINT_NUM = ? ");
        sb.append(" ,RECORD_VER_NO = RECORD_VER_NO + 1 ");
        sb.append(" ,UPDATE_DTTM = ?");
        sb.append(" ,UPDATE_CD = ? ");
        sb.append(" WHERE ");
        sb.append(" ORGANIZATION_ID = ? ");
        sb.append(" AND CONS_SEQ = ?");
        sb.append(" AND RECORD_VER_NO = ?");

        StringBuffer sbU = new StringBuffer();
        sbU.append(" UPDATE POINT_OWNERSHIP_TRN ");
        sbU.append(" SET ");
        sbU.append("   PURCHASE_FREE_POINT = PURCHASE_FREE_POINT + ? ");
        sbU.append("  ,BALANCE_POINT = ? ");
        sbU.append("  ,RECORD_VER_NO = RECORD_VER_NO + 1 ");
        sbU.append("  ,UPDATE_DTTM = ?");
        sbU.append("  ,UPDATE_CD = ? ");
        sbU.append(" WHERE ");
        sbU.append(" OWNERSHIP_ID = ? ");
        sbU.append(" AND RECORD_VER_NO = ?");

        StringBuffer sbI = new StringBuffer();
        sbI.append(" INSERT INTO POINT_OWNERSHIP_TRN ");
        sbI.append(" ( ");
        sbI.append("   OWNERSHIP_ID ");
        sbI.append("  ,COMPENSATION_FREE_KBN ");
        sbI.append("  ,STUDENT_ID ");
        sbI.append("  ,EFFECTIVE_STR_DT ");
        sbI.append("  ,EFFECTIVE_END_DT ");
        sbI.append("  ,FEE_KBN ");
        sbI.append("  ,PURCHASE_YEN ");
        sbI.append("  ,PURCHASE_FREE_POINT ");
        sbI.append("  ,BALANCE_POINT ");
        sbI.append("  ,PURCHASE_DT ");
        sbI.append("  ,PURCHASE_TM ");
        sbI.append("  ,SCREEN_SYSTEM_KBN ");
        sbI.append("  ,END_FLG ");
        sbI.append("  ,RECORD_VER_NO ");
        sbI.append("  ,INSERT_DTTM ");
        sbI.append("  ,INSERT_CD ");
        sbI.append("  ,UPDATE_DTTM ");
        sbI.append("  ,UPDATE_CD ");
        sbI.append(" ) ");
        sbI.append(" VALUES ");
        sbI.append(" ( ");
        sbI.append("   ? ");
        sbI.append("  ,? ");
        sbI.append("  ,? ");
        sbI.append("  ,? ");
        sbI.append("  ,? ");
        sbI.append("  ,? ");
        sbI.append("  ,? ");
        sbI.append("  ,? ");
        sbI.append("  ,? ");
        sbI.append("  ,? ");
        sbI.append("  ,? ");
        sbI.append("  ,? ");
        sbI.append("  ,? ");
        sbI.append("  ,? ");
        sbI.append("  ,? ");
        sbI.append("  ,? ");
        sbI.append("  ,? ");
        sbI.append("  ,? ");
        sbI.append(" ) ");

        PreparedStatement ps = null;
		try {

			ps = conn.prepareStatement(sb.toString());

			int index = 1;
			// 未割振ポイント(残高)
			ps.setBigDecimal(index++, dto.getBalancePointNumDown());
			// 更新日時
			ps.setString(index++, String.valueOf(Timestamp.valueOf(sdf1.format(cal.getTime()))));
			// 更新者コード
			ps.setString(index++, userId);
			// 組織ID
			ps.setString(index++, dto.getOrganizationId());
			// 連番
			ps.setInt(index++, dto.getConsSeq());
			// レコードバージョン番号
			ps.setInt(index++, dto.getRecordVerNo());

			// SQL文を実行
			rowCount = ps.executeUpdate();

			if (rowCount > NaikaraTalkConstants.RETURN_CD_ERR_NO_UPD) {
				OrganizationPointAssignmentListDto changeDto;
				for (int i = 0; list.size() > i; i++) {
					changeDto = list.get(i);
					// 画面から入力された今回割振ポイントはnullの場合
					if (changeDto.getAllocatePoint() == null || StringUtils.equals("", changeDto.getAllocatePoint())) {
						changeDto.setAllocatePoint("0");
					}
					// 画面から入力された今回割振ポイントはnullの場合、画面から入力された今回割振ポイントは０の場合、更新しない
					if ((new BigDecimal(NaikaraStringUtil.delComma(changeDto.getAllocatePoint())).abs())
							.compareTo(new BigDecimal(0)) > INSERT_ZERO) {
						if (changeDto.getOwnershipId() == null) {
							// データが存在しない場合、追加処理を行う

							// 採番取得
							// サーバーのシステム日付を取得
							String sysDate = null;
							SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
							sysDate = sdf.format(new Date());
							OrderNumbersMstLogic logic = new OrderNumbersMstLogic();
							OrderNumberDto orderdto = logic.getOrderNumber("OW", sysDate);
							orderdto.getOrderNumber();
							ps = conn.prepareStatement(sbI.toString());
							index = 1;
							// 所有ID
				            ps.setString(index++, orderdto.getOrderNumber());
				            // 有償無償区分
				            ps.setString(index++, NaikaraTalkConstants.COMPENSATION_FREE_KBN_YES);
				            // 受講者ID
				            ps.setString(index++, changeDto.getStudentId());
				            // 有効開始日
				            ps.setString(index++, dto.getContractFrom());
				            // 有効終了日
							ps.setString(index++, dto.getContractTo());
							// 通常月謝区分
							ps.setString(index++, NaikaraTalkConstants.FEE_KBN_NORMAL);
							// 購入金額(税込)
							ps.setBigDecimal(index++, new BigDecimal(0));
							// 購入／無償ポイント
							ps.setBigDecimal(index++, new BigDecimal(NaikaraStringUtil.delComma(changeDto.getAllocatePoint())));
							// ポイント残高
							ps.setBigDecimal(index++, changeDto.getNewbalancePoint());
							// 購入日
							ps.setString(index++, sysDate);
							// 購入時刻
							ps.setString(index++, DateUtil.getSysDateTime().substring(8, 14));
							// 画面システム作成区分
							ps.setString(index++, NaikaraTalkConstants.SCREEN_SYSTEM_KBN_SCREEN);
							// 月謝停止フラグ
							ps.setString(index++, NaikaraTalkConstants.END_FLG_NO);
							// レコードバージョン番号
							ps.setInt(index++, Integer.valueOf("0"));
							// 登録日時
							ps.setString(index++, String.valueOf(Timestamp.valueOf(sdf1.format(cal.getTime()))));
							// 登録者コード
							ps.setString(index++, userId);
							// 更新日時
							ps.setString(index++, String.valueOf(Timestamp.valueOf(sdf1.format(cal.getTime()))));
							// 更新者コード
							ps.setString(index++, userId);
							// SQL文を実行
							rowCount = ps.executeUpdate();
							if (rowCount == NaikaraTalkConstants.RETURN_CD_ERR_NO_UPD) {
								// DB更新なし(排他エラー)
								return rowCount;
							}
						} else {
							// データが存在する場合、更新処理を行う
							ps = conn.prepareStatement(sbU.toString());
							index = 1;
							// 購入／無償ポイント
							ps.setBigDecimal(index++, new BigDecimal(NaikaraStringUtil.delComma(changeDto.getAllocatePoint())));
							// ポイント残高
							ps.setBigDecimal(index++, changeDto.getNewbalancePoint());
							// 更新日時
							ps.setString(index++, String.valueOf(Timestamp.valueOf(sdf1.format(cal.getTime()))));
							// 更新者コード
							ps.setString(index++, userId);
							// 所有ID
							ps.setString(index++, changeDto.getOwnershipId());
							// レコードバージョン番号
							ps.setInt(index++, Integer.valueOf(changeDto.getRecordVerNoM()));

							// SQL文を実行
							rowCount = ps.executeUpdate();

							if (rowCount == NaikaraTalkConstants.RETURN_CD_ERR_NO_UPD) {
								// DB更新なし(排他エラー)
								return rowCount;
							}
						}
					}
				}
			} else {
				// DB更新なし(排他エラー)
				return rowCount;
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

		return rowCount;

	}


}
