package com.naikara_talk.dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.QueryCondition;
import com.naikara_talk.dto.PaymentManagedDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>売上入金・支払管理。<br>
 * <b>クラス名称　　　:</b>支払入力・修正クラス。<br>
 * <b>クラス概要　　　:</b>支払入力・修正DAO。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴　　　　:</b>2013/07/30 TECS 新規作成。
 * 　　　　　　　　　　　　　　　　:</b>2014/04/11 TECS 変更 (updateメソッド)源泉税が減額されていない不具合対応
 */

public class PaymentManagedDao extends AbstractDao {

	/** 支払予定年月 条件項目 */
	public static final String COND_PAYMENT_PLAN_YYYY_MM = "PAYMENT_PLAN_YYYY_MM";
	/** データ発生年月 条件項目 */
	public static final String COND_DATA_YYYY_MM = "DATA_YYYY_MM";
	/** 講師ID(利用者ID) 条件項目 */
	public static final String COND_USER_ID = "USER_ID";
	/** データなし */
	private static final int LIST_SIZE = 1;
	/** 入力０判断値 */
    private static final int INSERT_ZERO = 0;

	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public PaymentManagedDao(Connection con) {
		this.conn = con;
	}

	/**
	 * 画面一覧のデータ取得処理
	 * @param conditions
	 * @return list
	 * @throws NaiException
	 */
	public List<PaymentManagedDto> search(ConditionMapper conditions) throws NaiException {

		PaymentManagedDto retDto = null;    //支払入力・修正DTO

		ResultSet res = null;

		// 支払明細テーブルの項目取得
		StringBuffer sb = new StringBuffer();
		sb.append("  SELECT ");
		sb.append("  PAYMENT_PLAN_YYYY_MM ");
		sb.append(" ,DATA_YYYY_MM ");
		sb.append(" ,USER_ID ");
		sb.append(" ,LESSON_DT ");
		sb.append(" ,LESSON_TM_CD ");
		sb.append(" ,LESSON_TM_NM ");
		sb.append(" ,COURSE_JNM ");
		sb.append(" ,STUDENT_ID ");
		sb.append(" ,FAMILY_JNM ");
		sb.append(" ,FIRST_JNM ");
		sb.append(" ,C_EVALUATION_KBN ");
		sb.append(" ,C_EVALUATION_JNM ");
		sb.append(" ,S_ON_TEACHER_CMT ");
		sb.append(" ,BAT_PAYMENT_YEN ");
		sb.append(" ,ADJUSTMENT_YEN ");
		sb.append(" ,END_PAYMENT_YEN ");
		sb.append(" ,RECORD_VER_NO ");
		sb.append("  FROM ");
		sb.append("  PAYMENT_DETAILS_TRN ");

		// 抽出条件の設定
		if(!StringUtils.isEmpty(conditions.getConditionString())) {
			sb.append("where ");
			sb.append(conditions.getConditionString());
		}

		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement(sb.toString());

			// パラメタの設定
            int i = 0;
            for( QueryCondition cond : conditions.getConditions()){
            	for(String val : cond.getValues()){
            		ps.setString(i + 1, val);
            		i++;
            	}
            }
            res = ps.executeQuery();

            ArrayList<PaymentManagedDto> list = new ArrayList<PaymentManagedDto>();

            while (res.next()){
            	retDto = new PaymentManagedDto();

            	retDto.setPaymentPlanYyyyMm(res.getString("PAYMENT_PLAN_YYYY_MM"));   // 支払予定年月
            	retDto.setDataYyyyMm(res.getString("DATA_YYYY_MM"));                  // データ発生年月
            	retDto.setUserId(res.getString("USER_ID"));                           // 講師ID (利用者ID)
            	retDto.setLessonDt(res.getString("LESSON_DT"));                       // レッスン日
            	retDto.setLessonTmCd(res.getString("LESSON_TM_CD"));                  // レッスン時刻コード
            	retDto.setLessonTmNm(res.getString("LESSON_TM_NM"));                  // レッスン時刻名
            	retDto.setCourseJnm(res.getString("COURSE_JNM"));                     // コース名
            	retDto.setStudentId(res.getString("STUDENT_ID"));                     // 受講者ID
            	retDto.setFamilyJnm(res.getString("FAMILY_JNM"));                     // 名前(姓)
            	retDto.setFirstJnm(res.getString("FIRST_JNM"));                       // 名前(名）
            	retDto.setEvaluationKbn(res.getString("C_EVALUATION_KBN"));           // 受講者：講師への評価区分
            	retDto.setEvaluationJnm(res.getString("C_EVALUATION_JNM"));           // 受講者：講師への評価
            	retDto.setOnTeacherCmt(res.getString("S_ON_TEACHER_CMT"));            // スクール：スクールから講師へのコメント
            	retDto.setBatPaymentYen(res.getBigDecimal("BAT_PAYMENT_YEN"));        // バッチ計算支払予定額
            	// 調整額
            	retDto.setAdjustmentYen(String.valueOf(res.getBigDecimal("ADJUSTMENT_YEN")));
            	retDto.setEndPaymentYen(res.getBigDecimal("END_PAYMENT_YEN"));        // 新支払予定額
            	retDto.setRecordVerNoM(String.valueOf(res.getInt("RECORD_VER_NO")));  // レコードバージョン番号

            	// データ取得：正常
            	retDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);

				list.add(retDto);
            }

            // データ取得：データなしの場合
            if(list.size() < LIST_SIZE){
            	retDto = new PaymentManagedDto();
				retDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_NO);
				list.add(retDto);
			}

			return list;

		} catch ( SQLException se ) {
			throw new NaiException(se);
		} finally {
			try {
				if ( res != null ) {
					res.close();
				}
				if (ps != null) {
	                ps.close();
	            }
			} catch (SQLException e) {
				e.printStackTrace();
				throw new NaiException(e);
			}
		}

	}

	/**
	 * 更新処理
	 * @param list 画面一覧list
	 * @param dto 支払入力・修正DTO
	 * @return int
	 * @throws NaiException
	 * @throws IOException
	 */
	public int update(List<PaymentManagedDto> list, PaymentManagedDto dto) throws NaiException {

		// この画面を開いたロールを取得します(この段階に到達した時点でユーザ情報は存在している)
		String userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();

		Calendar cal = Calendar.getInstance();
 		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		int updatedRowCount = NaikaraTalkConstants.RETURN_CD_ERR_UPD; // 更新データ件数

		int index = 1;

        // 支払明細テーブルの更新sql
        StringBuffer sbD = new StringBuffer();
        sbD.append("   UPDATE PAYMENT_DETAILS_TRN ");
        sbD.append("   SET ");
        sbD.append("   ADJUSTMENT_YEN = ADJUSTMENT_YEN + ? ");
        sbD.append("  ,END_PAYMENT_YEN = ? ");
        sbD.append("  ,RECORD_VER_NO = RECORD_VER_NO + 1 ");
        sbD.append("  ,UPDATE_DTTM = ?");
        sbD.append("  ,UPDATE_CD = ? ");
        sbD.append("   WHERE ");
        sbD.append("   PAYMENT_PLAN_YYYY_MM = ? ");
        sbD.append("   AND DATA_YYYY_MM = ?");
        sbD.append("   AND USER_ID = ?");
        sbD.append("   AND LESSON_DT = ?");
        sbD.append("   AND LESSON_TM_CD = ?");
        sbD.append("   AND RECORD_VER_NO = ?");

        // 支払集計テーブルの更新sql
        StringBuffer sb = new StringBuffer();
        sb.append("   UPDATE PAYMENT_TRN ");
        sb.append("   SET ");
        sb.append("   SOURCE_YEN = ? ");
        sb.append("  ,ADJUSTMENT_YEN = ADJUSTMENT_YEN + ? ");
        sb.append("  ,END_PAYMENT_YEN = ? ");
        sb.append("  ,RECORD_VER_NO = RECORD_VER_NO + 1 ");
        sb.append("  ,UPDATE_DTTM = ?");
        sb.append("  ,UPDATE_CD = ? ");
        sb.append("   WHERE ");
        sb.append("   PAYMENT_PLAN_YYYY_MM = ? ");
        sb.append("   AND DATA_YYYY_MM = ?");
        sb.append("   AND USER_ID = ?");
        sb.append("   AND RECORD_VER_NO = ?");

        PreparedStatement ps = null;
		try {
			PaymentManagedDto dtoD;

			// 支払明細テーブルの更新
			for (int i = 0; i < list.size(); i++) {
				dtoD = list.get(i);
				// 画面から入力された調整額はnullの場合
				if (dtoD.getAdjustmentYen() == null || StringUtils.equals("", dtoD.getAdjustmentYen())) {
					dtoD.setAdjustmentYen("0");
				}
				// 画面から入力された調整額はnullの場合、画面から入力された調整額は０の場合、更新しない
				if ((new BigDecimal(NaikaraStringUtil.delComma(dtoD.getAdjustmentYen())).abs())
						.compareTo(new BigDecimal(0)) > INSERT_ZERO) {
					index = 1;
					ps = conn.prepareStatement(sbD.toString());
					// 調整額
					ps.setBigDecimal(index++, new BigDecimal(NaikaraStringUtil.delComma(dtoD.getAdjustmentYen())));
					ps.setBigDecimal(index++, dtoD.getNewPaymentYen());                                     // 新支払予定額
					ps.setString(index++, String.valueOf(Timestamp.valueOf(sdf.format(cal.getTime()))));    // 更新日時
					ps.setString(index++, userId);                                                          // 更新者コード
					ps.setString(index++, dto.getPaymentPlanYyyyMm());                                      // 支払予定年月
					ps.setString(index++, dto.getDataYyyyMm());                                             // データ発生年月
					ps.setString(index++, dto.getUserId());                                                 // 講師ID (利用者ID)
					ps.setString(index++, NaikaraStringUtil.delSlash(dtoD.getLessonDt()));                  // レッスン日
					ps.setString(index++, dtoD.getLessonTmCd());                                            // レッスン時刻コード
					ps.setInt(index++, Integer.valueOf(dtoD.getRecordVerNoM()));                            // レコードバージョン番号

					// SQL文を実行
					updatedRowCount = ps.executeUpdate();

					// 更新データ件数 未更新の場合return
					if (updatedRowCount == NaikaraTalkConstants.RETURN_CD_ERR_NO_UPD) {
						// DB更新なし(排他エラー)
						return updatedRowCount;
					}
				}
			}

			// 支払明細テーブルの正常に更新する場合、支払集計テーブルの更新
			index = 1;
			ps = conn.prepareStatement(sb.toString());
			ps.setBigDecimal(index++, dto.getSourceYen());                                          // 源泉額(円)
			ps.setBigDecimal(index++, dto.getAdjustmentYenSum());                                   // 調整額

			// 2014.04.11 源泉税が減額されていない不具合対応 Upd-Start
			//ps.setBigDecimal(index++, dto.getNewPaymentYenSum());                                   // 支払予定額：調整後金額(円)
			ps.setBigDecimal(index++, dto.getEndPaymentYen());                                      // 支払予定額：調整後金額(円)
			// 2014.04.11 源泉税が減額されていない不具合対応 Upd-End

			ps.setString(index++, String.valueOf(Timestamp.valueOf(sdf.format(cal.getTime()))));    // 更新日時
			ps.setString(index++, userId);                                                          // 更新者コード
			ps.setString(index++, dto.getPaymentPlanYyyyMm());                                      // 支払予定年月
			ps.setString(index++, dto.getDataYyyyMm());                                             // データ発生年月
			ps.setString(index++, dto.getUserId());                                                 // 講師ID (利用者ID)
			ps.setInt(index++, Integer.valueOf(dto.getRecordVerNo()));                              // レコードバージョン番号

			// SQL文を実行
			updatedRowCount = ps.executeUpdate();

			// 更新データ件数 未更新の場合return
			if (updatedRowCount == NaikaraTalkConstants.RETURN_CD_ERR_NO_UPD) {
				return updatedRowCount;
			}

		} catch (SQLException se) {
			throw new NaiException(se);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException se) {
				throw new NaiException(se);
			}
		}

	return updatedRowCount;

	}

}
