package com.naikara_talk.dao;

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
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dbutil.QueryCondition;
import com.naikara_talk.dto.PaymentTrnDto;
import com.naikara_talk.dto.PaymentTrnSingleDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DAOクラス<br>
 * <b>クラス名称　　　:</b>支払集計テーブル(+利用者マスタ、講師マスタ)データアクセスクラス<br>
 * <b>クラス概要　　　:</b>支払集計テーブル(+利用者マスタ、講師マスタ)のデータを取得する<br>
 * <br>
 * <b>著作権　　　　　:</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/04 TECS 新規作成
 */
public class PaymentTrnDao extends AbstractDao {

	/** 支払予定年月 条件項目 */
	public static final String COND_PAYMENT_PLAN_YYYY_MM = "PAYMENT_TRN.PAYMENT_PLAN_YYYY_MM";
	/** データ発生年月 条件項目 */
	public static final String COND_DATA_YYYY_MM = "PAYMENT_TRN.DATA_YYYY_MM";
	/** 講師ID(利用者ID) 条件項目 */
	public static final String COND_USER_ID = "PAYMENT_TRN.USER_ID";
	/** 支払対象区分 条件項目 */
	public static final String COND_PAYMENT_KBN = "PAYMENT_TRN.PAYMENT_KBN";

	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public PaymentTrnDao(Connection con) {
		this.conn = con;
	}

	/**
	 * 支払集計テーブル(+利用者マスタ、講師マスタ)データ取得<br>
	 * <br>
	 * 支払集計テーブル(+利用者マスタ、講師マスタ)リストを戻り値で返却する<br>
	 * <br>
	 * @param conditional
	 * @param orderConditional
	 * @return PaymentTrnDto
	 * @throws NaiException
	 */
	public List<PaymentTrnDto> search(ConditionMapper conditions, OrderCondition orderConditions) throws NaiException {

		ResultSet res = null;

		StringBuffer sb = new StringBuffer();

		// 売上集計テーブルの全項目取得
		sb.append("select");
		sb.append(" PAYMENT_TRN.PAYMENT_PLAN_YYYY_MM ");
		sb.append(",PAYMENT_TRN.DATA_YYYY_MM ");
		sb.append(",PAYMENT_TRN.USER_ID ");
		sb.append(",PAYMENT_TRN.TEACHER_NICK_NM ");
		sb.append(",PAYMENT_TRN.FAMILY_JNM ");
		sb.append(",PAYMENT_TRN.FIRST_JNM ");
		sb.append(",PAYMENT_TRN.LESSON_NUM ");
		sb.append(",PAYMENT_TRN.BAT_PAYMENT_YEN ");
		sb.append(",PAYMENT_TRN.SOURCE_YEN ");
		sb.append(",PAYMENT_TRN.ADJUSTMENT_YEN ");
		sb.append(",PAYMENT_TRN.END_PAYMENT_YEN ");
		sb.append(",PAYMENT_TRN.PAYMENT_KBN ");
		sb.append(",PAYMENT_TRN.PAYMENT_NM ");
		sb.append(",PAYMENT_TRN.RECORD_VER_NO ");
		sb.append(",PAYMENT_TRN.INSERT_DTTM ");
		sb.append(",PAYMENT_TRN.INSERT_CD ");
		sb.append(",PAYMENT_TRN.UPDATE_DTTM ");
		sb.append(",PAYMENT_TRN.UPDATE_CD ");

		sb.append(",USER_MST.USER_ID ");
		sb.append(",USER_MST.PASSWORD ");
		sb.append(",USER_MST.FAMILY_JNM ");
		sb.append(",USER_MST.FIRST_JNM ");
		sb.append(",USER_MST.FAMILY_KNM ");
		sb.append(",USER_MST.FIRST_KNM ");
		sb.append(",USER_MST.FAMILY_ROMAJI ");
		sb.append(",USER_MST.FIRST_ROMAJI ");
		sb.append(",USER_MST.TEL1 ");
		sb.append(",USER_MST.TEL2 ");
		sb.append(",USER_MST.BIRTH_YYYY ");
		sb.append(",USER_MST.BIRTH_MM ");
		sb.append(",USER_MST.BIRTH_DD ");
		sb.append(",USER_MST.ZIP_CD ");
		sb.append(",USER_MST.ADDRESS_AREA_CD ");
		sb.append(",USER_MST.ADDRESS_PREFECTURE_CD ");
		sb.append(",USER_MST.ADDRESS_CITY ");
		sb.append(",USER_MST.ADDRESS_OTHERS ");
		sb.append(",USER_MST.GENDER_KBN ");
		sb.append(",USER_MST.MAIL_ADDRESS1 ");
		sb.append(",USER_MST.MAIL_ADDRESS2 ");
		sb.append(",USER_MST.MAIL_ADDRESS3 ");
		sb.append(",USER_MST.USE_START_DT ");
		sb.append(",USER_MST.USE_END_DT ");
		sb.append(",USER_MST.CITY_TOWN ");
		sb.append(",USER_MST.CLASSIFICATION_KBN ");
		sb.append(",USER_MST.REMARK ");
		sb.append(",USER_MST.RECORD_VER_NO ");
		sb.append(",USER_MST.INSERT_DTTM ");
		sb.append(",USER_MST.INSERT_CD ");
		sb.append(",USER_MST.UPDATE_DTTM ");
		sb.append(",USER_MST.UPDATE_CD ");

		sb.append(",TEACHER_MST.USER_ID ");
		sb.append(",TEACHER_MST.NICK_ANM ");
		sb.append(",TEACHER_MST.NATIONALITY_CD ");
		sb.append(",TEACHER_MST.NATIVE_LANG_CD ");
		sb.append(",TEACHER_MST.COUNTRY_CD ");
		sb.append(",TEACHER_MST.AREA_NO_CD ");
		sb.append(",TEACHER_MST.CONTRACT_DT ");
		sb.append(",TEACHER_MST.CONTRACT_START_DT ");
		sb.append(",TEACHER_MST.CONTRACT_END_DT ");
		sb.append(",TEACHER_MST.BANK_JPN_BANK_NM ");
		sb.append(",TEACHER_MST.BANK_JPN_BRANCH_NM ");
		sb.append(",TEACHER_MST.BANK_JPN_TYPE_OF_ACCOUNT ");
		sb.append(",TEACHER_MST.BANK_JPN_ACCOUNT_HOLDERS_KNM ");
		sb.append(",TEACHER_MST.BANK_JPN_ACCOUNT_HOLDERS_NM ");
		sb.append(",TEACHER_MST.BANK_JPN_ACCOUNT_NUMBER ");
		sb.append(",TEACHER_MST.BANK_JPN_ADDITIONAL_INFO ");
		sb.append(",TEACHER_MST.JPN_PBANK_BRANCH_NM ");
		sb.append(",TEACHER_MST.JPN_PBANK_TYPE_OF_ACCOUNT ");
		sb.append(",TEACHER_MST.JPN_PBANK_ACCOUNT_HOLDERS_KNM ");
		sb.append(",TEACHER_MST.JPN_PBANK_ACCOUNT_HOLDERS_NM ");
		sb.append(",TEACHER_MST.JPN_PBANK_ACCOUNT_NUMBER ");
		sb.append(",TEACHER_MST.JPN_PBANK_ADDITIONAL_INFO ");
		sb.append(",TEACHER_MST.OVERSEA_ACCOUNT_H_NM ");
		sb.append(",TEACHER_MST.OVERSEA_ACCOUNT_H_R_ADDRESS1 ");
		sb.append(",TEACHER_MST.OVERSEA_ACCOUNT_H_R_ADDRESS2 ");
		sb.append(",TEACHER_MST.OVERSEA_ACCOUNT_NUMBER_IBAN ");
		sb.append(",TEACHER_MST.OVERSEA_ABANO_SWIFTCD_BICCD ");
		sb.append(",TEACHER_MST.OVERSEA_ETC ");
		sb.append(",TEACHER_MST.OVERSEA_BANK_NM ");
		sb.append(",TEACHER_MST.OVERSEA_BRANCH_NM ");
		sb.append(",TEACHER_MST.OVERSEA_BRANCH_ADDRESS1 ");
		sb.append(",TEACHER_MST.OVERSEA_BRANCH_ADDRESS2 ");
		sb.append(",TEACHER_MST.OVERSEA_COUNTRY_BRANCH_EXISTS ");
		sb.append(",TEACHER_MST.OVERSEA_ADDITIONAL_INFO ");
		sb.append(",TEACHER_MST.OTHER_REMITTANCE_FEE_KBN ");
		sb.append(",TEACHER_MST.OVERSEA_PL_PAYPAL_ADDRESS ");
		sb.append(",TEACHER_MST.OVERSEA_PL_ADDITIONAL_INFO ");
		sb.append(",TEACHER_MST.SELLING_POINT ");
		sb.append(",TEACHER_MST.SELF_RECOMMENDATION ");
		sb.append(",TEACHER_MST.IMG_PHOTO_NM ");
		sb.append(",TEACHER_MST.IMG_PHOTO ");
		sb.append(",TEACHER_MST.EVALUATION_FROM_SCHOOL_CMT1 ");
		sb.append(",TEACHER_MST.EVALUATION_FROM_SCHOOL_CMT2 ");
		sb.append(",TEACHER_MST.LATEST_EVALUATION_FROM_STUDENT_CMT ");
		sb.append(",TEACHER_MST.REMARK ");
		sb.append(",TEACHER_MST.RECORD_VER_NO ");
		sb.append(",TEACHER_MST.INSERT_DTTM ");
		sb.append(",TEACHER_MST.INSERT_CD ");
		sb.append(",TEACHER_MST.UPDATE_DTTM ");
		sb.append(",TEACHER_MST.UPDATE_CD ");
		sb.append("from PAYMENT_TRN ");
		sb.append("inner join USER_MST ");
		sb.append("on PAYMENT_TRN.USER_ID = USER_MST.USER_ID ");
		sb.append("inner join TEACHER_MST ");
		sb.append("on PAYMENT_TRN.USER_ID = TEACHER_MST.USER_ID ");

		if(!StringUtils.isEmpty(conditions.getConditionString())) {
			sb.append("where ");
			sb.append(conditions.getConditionString());
		}
		if(!StringUtils.isEmpty(orderConditions.getOrderString())) {
			sb.append(orderConditions.getOrderString());
		}

		try{

			PreparedStatement ps = conn.prepareStatement(sb.toString());

            // パラメタの設定
            int i = 0;
            for( QueryCondition cond : conditions.getConditions()){
            	for(String val : cond.getValues()){
            		ps.setString(i + 1, val);
            		i++;
            	}
            }

			res = ps.executeQuery();

			ArrayList<PaymentTrnDto> list = new ArrayList<PaymentTrnDto>();

			while (res.next()){

				PaymentTrnDto retDto = new PaymentTrnDto();

				retDto.setPaymentPlanYyyyMm(res.getString("PAYMENT_TRN.PAYMENT_PLAN_YYYY_MM"));
				retDto.setDataYyyyMm(res.getString("PAYMENT_TRN.DATA_YYYY_MM"));
				retDto.setUserId(res.getString("PAYMENT_TRN.USER_ID"));
				retDto.setTeacherNickNm(res.getString("PAYMENT_TRN.TEACHER_NICK_NM"));
				retDto.setFamilyJnmP(res.getString("PAYMENT_TRN.FAMILY_JNM"));
				retDto.setFirstJnmP(res.getString("PAYMENT_TRN.FIRST_JNM"));
				retDto.setLessonNum(res.getInt("PAYMENT_TRN.LESSON_NUM"));
				retDto.setBatPaymentYen(res.getBigDecimal("PAYMENT_TRN.BAT_PAYMENT_YEN"));
				retDto.setSourceYen(res.getBigDecimal("PAYMENT_TRN.SOURCE_YEN"));
				retDto.setAdjustmentYen(res.getBigDecimal("PAYMENT_TRN.ADJUSTMENT_YEN"));
				retDto.setEndPaymentYen(res.getBigDecimal("PAYMENT_TRN.END_PAYMENT_YEN"));
				retDto.setPaymentKbn(res.getString("PAYMENT_TRN.PAYMENT_KBN"));
				retDto.setPaymentNm(res.getString("PAYMENT_TRN.PAYMENT_NM"));
				retDto.setRecordVerNoP(res.getInt("PAYMENT_TRN.RECORD_VER_NO"));
				retDto.setInsertDttmP(res.getTimestamp("PAYMENT_TRN.INSERT_DTTM"));
				retDto.setInsertCdP(res.getString("PAYMENT_TRN.INSERT_CD"));
				retDto.setUpdateDttmP(res.getTimestamp("PAYMENT_TRN.UPDATE_DTTM"));
				retDto.setUpdateCdP(res.getString("PAYMENT_TRN.UPDATE_CD"));

				retDto.setPassword(res.getString("USER_MST.PASSWORD"));
				retDto.setFamilyJnmU(res.getString("USER_MST.FAMILY_JNM"));
				retDto.setFirstJnmU(res.getString("USER_MST.FIRST_JNM"));
				retDto.setFamilyKnm(res.getString("USER_MST.FAMILY_KNM"));
				retDto.setFirstKnm(res.getString("USER_MST.FIRST_KNM"));
				retDto.setFamilyRomaji(res.getString("USER_MST.FAMILY_ROMAJI"));
				retDto.setFirstRomaji(res.getString("USER_MST.FIRST_ROMAJI"));
				retDto.setTel1(res.getString("USER_MST.TEL1"));
				retDto.setTel2(res.getString("USER_MST.TEL2"));
				retDto.setBirthYyyy(res.getString("USER_MST.BIRTH_YYYY"));
				retDto.setBirthMm(res.getString("USER_MST.BIRTH_MM"));
				retDto.setBirthDd(res.getString("USER_MST.BIRTH_DD"));
				retDto.setZipCd(res.getString("USER_MST.ZIP_CD"));
				retDto.setAddressAreaCd(res.getString("USER_MST.ADDRESS_AREA_CD"));
				retDto.setAddressPrefectureCd(res.getString("USER_MST.ADDRESS_PREFECTURE_CD"));
				retDto.setAddressCity(res.getString("USER_MST.ADDRESS_CITY"));
				retDto.setAddressOthers(res.getString("USER_MST.ADDRESS_OTHERS"));
				retDto.setGenderKbn(res.getString("USER_MST.GENDER_KBN"));
				retDto.setMailAddress1(res.getString("USER_MST.MAIL_ADDRESS1"));
				retDto.setMailAddress2(res.getString("USER_MST.MAIL_ADDRESS2"));
				retDto.setMailAddress3(res.getString("USER_MST.MAIL_ADDRESS3"));
				retDto.setUseStartDt(res.getString("USER_MST.USE_START_DT"));
				retDto.setUseEndDt(res.getString("USER_MST.USE_END_DT"));
				retDto.setCityTown(res.getString("USER_MST.CITY_TOWN"));
				retDto.setClassificationKbn(res.getString("USER_MST.CLASSIFICATION_KBN"));
				retDto.setRemarkU(res.getString("USER_MST.REMARK"));
				retDto.setRecordVerNoU(res.getInt("USER_MST.RECORD_VER_NO"));
				retDto.setInsertDttmU(res.getTimestamp("USER_MST.INSERT_DTTM"));
				retDto.setInsertCdU(res.getString("USER_MST.INSERT_CD"));
				retDto.setUpdateDttmU(res.getTimestamp("USER_MST.UPDATE_DTTM"));
				retDto.setUpdateCdU(res.getString("USER_MST.UPDATE_CD"));

				retDto.setNickAnm(res.getString("TEACHER_MST.NICK_ANM"));
				retDto.setNationalityCd(res.getString("TEACHER_MST.NATIONALITY_CD"));
				retDto.setNativeLangCd(res.getString("TEACHER_MST.NATIVE_LANG_CD"));
				retDto.setCountryCd(res.getString("TEACHER_MST.COUNTRY_CD"));
				retDto.setAreaNoCd(res.getString("TEACHER_MST.AREA_NO_CD"));
				retDto.setContractDt(res.getString("TEACHER_MST.CONTRACT_DT"));
				retDto.setContractStartDt(res.getString("TEACHER_MST.CONTRACT_START_DT"));
				retDto.setContractEndDt(res.getString("TEACHER_MST.CONTRACT_END_DT"));
				retDto.setBankJpnBankNm(res.getString("TEACHER_MST.BANK_JPN_BANK_NM"));
				retDto.setBankJpnBranchNm(res.getString("TEACHER_MST.BANK_JPN_BRANCH_NM"));
				retDto.setBankJpnTypeOfAccount(res.getString("TEACHER_MST.BANK_JPN_TYPE_OF_ACCOUNT"));
				retDto.setBankJpnAccountHoldersKnm(res.getString("TEACHER_MST.BANK_JPN_ACCOUNT_HOLDERS_KNM"));
				retDto.setBankJpnAccountHoldersNm(res.getString("TEACHER_MST.BANK_JPN_ACCOUNT_HOLDERS_NM"));
				retDto.setBankJpnAccountNumber(res.getString("TEACHER_MST.BANK_JPN_ACCOUNT_NUMBER"));
				retDto.setBankJpnAdditionalInfo(res.getString("TEACHER_MST.BANK_JPN_ADDITIONAL_INFO"));
				retDto.setJpnPbankBranchNm(res.getString("TEACHER_MST.JPN_PBANK_BRANCH_NM"));
				retDto.setJpnPbankTypeOfAccount(res.getString("TEACHER_MST.JPN_PBANK_TYPE_OF_ACCOUNT"));
				retDto.setJpnPbankAccountHoldersKnm(res.getString("TEACHER_MST.JPN_PBANK_ACCOUNT_HOLDERS_KNM"));
				retDto.setJpnPbankAccountHoldersNm(res.getString("TEACHER_MST.JPN_PBANK_ACCOUNT_HOLDERS_NM"));
				retDto.setJpnPbankAccountNumber(res.getString("TEACHER_MST.JPN_PBANK_ACCOUNT_NUMBER"));
				retDto.setJpnPbankAdditionalInfo(res.getString("TEACHER_MST.JPN_PBANK_ADDITIONAL_INFO"));
				retDto.setOverseaAccountHNm(res.getString("TEACHER_MST.OVERSEA_ACCOUNT_H_NM"));
				retDto.setOverseaAccountHRAddress1(res.getString("TEACHER_MST.OVERSEA_ACCOUNT_H_R_ADDRESS1"));
				retDto.setOverseaAccountHRAddress2(res.getString("TEACHER_MST.OVERSEA_ACCOUNT_H_R_ADDRESS2"));
				retDto.setOverseaAccountNumberIban(res.getString("TEACHER_MST.OVERSEA_ACCOUNT_NUMBER_IBAN"));
				retDto.setOverseaAbanoSwiftcdBiccd(res.getString("TEACHER_MST.OVERSEA_ABANO_SWIFTCD_BICCD"));
				retDto.setOverseaEtc(res.getString("TEACHER_MST.OVERSEA_ETC"));
				retDto.setOverseaBankNm(res.getString("TEACHER_MST.OVERSEA_BANK_NM"));
				retDto.setOverseaBranchNm(res.getString("TEACHER_MST.OVERSEA_BRANCH_NM"));
				retDto.setOverseaBranchAddress1(res.getString("TEACHER_MST.OVERSEA_BRANCH_ADDRESS1"));
				retDto.setOverseaBranchAddress2(res.getString("TEACHER_MST.OVERSEA_BRANCH_ADDRESS2"));
				retDto.setOverseaCountryBranchExists(res.getString("TEACHER_MST.OVERSEA_COUNTRY_BRANCH_EXISTS"));
				retDto.setOverseaAdditionalInfo(res.getString("TEACHER_MST.OVERSEA_ADDITIONAL_INFO"));
				retDto.setOtherRemittanceFeeKbn(res.getString("TEACHER_MST.OTHER_REMITTANCE_FEE_KBN"));
				retDto.setOverseaPlPaypalAddress(res.getString("TEACHER_MST.OVERSEA_PL_PAYPAL_ADDRESS"));
				retDto.setOverseaPlAdditionalInfo(res.getString("TEACHER_MST.OVERSEA_PL_ADDITIONAL_INFO"));
				retDto.setSellingPoint(res.getString("TEACHER_MST.SELLING_POINT"));
				retDto.setSelfRecommendation(res.getString("TEACHER_MST.SELF_RECOMMENDATION"));
				retDto.setImgPhotoNm(res.getString("TEACHER_MST.IMG_PHOTO_NM"));
				retDto.setImgPhoto(res.getBlob("TEACHER_MST.IMG_PHOTO"));
				retDto.setEvaluationFromSchoolCmt1(res.getString("TEACHER_MST.EVALUATION_FROM_SCHOOL_CMT1"));
				retDto.setEvaluationFromSchoolCmt2(res.getString("TEACHER_MST.EVALUATION_FROM_SCHOOL_CMT2"));
				retDto.setLatestEvaluationFromStudentCmt(res.getString("TEACHER_MST.LATEST_EVALUATION_FROM_STUDENT_CMT"));
				retDto.setRemarkT(res.getString("TEACHER_MST.REMARK"));
				retDto.setRecordVerNoT(res.getInt("TEACHER_MST.RECORD_VER_NO"));
				retDto.setInsertDttmT(res.getTimestamp("TEACHER_MST.INSERT_DTTM"));
				retDto.setInsertCdT(res.getString("TEACHER_MST.INSERT_CD"));
				retDto.setUpdateDttmT(res.getTimestamp("TEACHER_MST.UPDATE_DTTM"));
				retDto.setUpdateCdT(res.getString("TEACHER_MST.UPDATE_CD"));

				retDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);

				list.add(retDto);
			}

			if(list.size()<1){
				PaymentTrnDto retDto = new PaymentTrnDto();
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
			} catch (SQLException e) {
				e.printStackTrace();
				throw new NaiException(e);
			}
		}
	}

	/**
	 * 支払集計テーブルデータ取得<br>
	 * <br>
	 * 支払集計テーブルリストを戻り値で返却する<br>
	 * <br>
	 * @param conditional
	 * @param orderConditional
	 * @return PaymentTrnSingleDto
	 * @throws NaiException
	 */
	public List<PaymentTrnSingleDto> searchSingle(ConditionMapper conditions, OrderCondition orderConditions)
			throws NaiException {

		ResultSet res = null;

		StringBuffer sb = new StringBuffer();

		// 売上集計テーブルの全項目取得
		sb.append("select");
		sb.append(" PAYMENT_PLAN_YYYY_MM ");
		sb.append(",DATA_YYYY_MM ");
		sb.append(",USER_ID ");
		sb.append(",TEACHER_NICK_NM ");
		sb.append(",FAMILY_JNM ");
		sb.append(",FIRST_JNM ");
		sb.append(",LESSON_NUM ");
		sb.append(",BAT_PAYMENT_YEN ");
		sb.append(",SOURCE_YEN ");
		sb.append(",ADJUSTMENT_YEN ");
		sb.append(",END_PAYMENT_YEN ");
		sb.append(",PAYMENT_KBN ");
		sb.append(",PAYMENT_NM ");
		sb.append(",RECORD_VER_NO ");
		sb.append(",INSERT_DTTM ");
		sb.append(",INSERT_CD ");
		sb.append(",UPDATE_DTTM ");
		sb.append(",UPDATE_CD ");
		sb.append("from PAYMENT_TRN ");

		if(!StringUtils.isEmpty(conditions.getConditionString())) {
			sb.append("where ");
			sb.append(conditions.getConditionString());
		}
		if(!StringUtils.isEmpty(orderConditions.getOrderString())) {
			sb.append(orderConditions.getOrderString());
		}

		try{

			PreparedStatement ps = conn.prepareStatement(sb.toString());

            // パラメタの設定
            int i = 0;
            for( QueryCondition cond : conditions.getConditions()){
            	for(String val : cond.getValues()){
            		ps.setString(i + 1, val);
            		i++;
            	}
            }

			res = ps.executeQuery();

			List<PaymentTrnSingleDto> list = new ArrayList<PaymentTrnSingleDto>();
			PaymentTrnSingleDto retDto = new PaymentTrnSingleDto();

			while (res.next()){

				retDto = new PaymentTrnSingleDto();

				retDto.setPaymentPlanYyyyMm(res.getString("PAYMENT_PLAN_YYYY_MM"));
				retDto.setDataYyyyMm(res.getString("DATA_YYYY_MM"));
				retDto.setUserId(res.getString("USER_ID"));
				retDto.setTeacherNickNm(res.getString("TEACHER_NICK_NM"));
				retDto.setFamilyJnm(res.getString("FAMILY_JNM"));
				retDto.setFirstJnm(res.getString("FIRST_JNM"));
				retDto.setLessonNum(res.getInt("LESSON_NUM"));
				retDto.setBatPaymentYen(res.getBigDecimal("BAT_PAYMENT_YEN"));
				retDto.setSourceYen(res.getBigDecimal("SOURCE_YEN"));
				retDto.setAdjustmentYen(res.getBigDecimal("ADJUSTMENT_YEN"));
				retDto.setEndPaymentYen(res.getBigDecimal("END_PAYMENT_YEN"));
				retDto.setPaymentKbn(res.getString("PAYMENT_KBN"));
				retDto.setPaymentNm(res.getString("PAYMENT_NM"));
				retDto.setRecordVerNo(res.getInt("RECORD_VER_NO"));
				retDto.setInsertDttm(res.getTimestamp("INSERT_DTTM"));
				retDto.setInsertCd(res.getString("INSERT_CD"));
				retDto.setUpdateDttm(res.getTimestamp("UPDATE_DTTM"));
				retDto.setUpdateCd(res.getString("UPDATE_CD"));

				retDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);

				list.add(retDto);
			}

			if(list.size()<1){
				retDto = new PaymentTrnSingleDto();
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
			} catch (SQLException e) {
				e.printStackTrace();
				throw new NaiException(e);
			}
		}
	}

	/**
	 * 支払集計テーブル削除処理<br>
	 * <br>
	 * 支払集計テーブルの削除を行う<br>
	 * <br>
	 * @param conditional
	 * @return delRowCount:削除件数<br>
	 * @exception NaiException
	 */
	public int delete(ConditionMapper conditions) throws NaiException {

		int delRowCount = 0;

		StringBuffer sb = new StringBuffer();

		// データ削除処理
		sb.append("delete ");
		sb.append("from PAYMENT_TRN ");

		if(!StringUtils.isEmpty(conditions.getConditionString())) {
			sb.append("where ");
			sb.append(conditions.getConditionString());
		}

		try {

			PreparedStatement ps = conn.prepareStatement(sb.toString());

			// パラメタの設定
			int i = 0;
			for( QueryCondition cond : conditions.getConditions()){
				for(String val : cond.getValues()){
					ps.setString(i + 1, val);
					i++;
				}
			}

			delRowCount = ps.executeUpdate();
			if (delRowCount < 1) {
				delRowCount = NaikaraTalkConstants.RETURN_CD_ERR_DEL;
			}

			return delRowCount;

		} catch (SQLException se) {
			se.printStackTrace();
			throw new NaiException(se);
		}

	}

	/**
	 * 支払集計テーブル登録処理<br>
	 * <br>
	 * 支払集計テーブルの登録を行う<br>
	 * <br>
	 * @param conditional
	 * @return delRowCount:登録件数<br>
	 * @exception NaiException
	 */
	public int selectInsert(String strProcMonth, String strBeforeMonth) throws NaiException {

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		int insertRowCount = 0;

		StringBuffer sb = new StringBuffer();

		// データ登録処理
		sb.append("INSERT INTO PAYMENT_TRN ");
		sb.append("SELECT ");
		sb.append(" ? ");
		sb.append(",DATA_YYYY_MM ");
		sb.append(",USER_ID ");
		sb.append(",TEACHER_NICK_NM ");
		sb.append(",FAMILY_JNM ");
		sb.append(",FIRST_JNM ");
		sb.append(",LESSON_NUM ");
		sb.append(",BAT_PAYMENT_YEN ");
		sb.append(",SOURCE_YEN ");
		sb.append(",ADJUSTMENT_YEN ");
		sb.append(",END_PAYMENT_YEN ");
		sb.append(",PAYMENT_KBN ");
		sb.append(",PAYMENT_NM ");
		sb.append(",0 ");
		sb.append(",? ");
		sb.append(",? ");
		sb.append(",? ");
		sb.append(",? ");
		sb.append("FROM PAYMENT_TRN ");
		sb.append("WHERE PAYMENT_TRN.PAYMENT_PLAN_YYYY_MM = ? ");
		sb.append("AND PAYMENT_TRN.PAYMENT_KBN = ? ");

		try {

			PreparedStatement ps = conn.prepareStatement(sb.toString());

			// パラメタの設定
			ps.setString(1, strProcMonth);
			ps.setString(2, String.valueOf(Timestamp.valueOf(sdf.format(cal.getTime()))));
			ps.setString(3, NaikaraTalkConstants.INSERT_CD);
			ps.setString(4, String.valueOf(Timestamp.valueOf(sdf.format(cal.getTime()))));
			ps.setString(5, NaikaraTalkConstants.UPDATE_CD);
			ps.setString(6, strBeforeMonth);
			ps.setString(7, NaikaraTalkConstants.PAYMENT_KBN_NO);

			insertRowCount = ps.executeUpdate();
			if (insertRowCount < 1) {
				insertRowCount = NaikaraTalkConstants.RETURN_CD_ERR_DEL;
			}

			return insertRowCount;

		} catch (SQLException se) {
			se.printStackTrace();
			throw new NaiException(se);
		}

	}

	/**
	 * 支払集計テーブル登録処理<br>
	 * <br>
	 * 支払集計テーブルの登録を行う<br>
	 * <br>
	 * @param dto:PaymentTrnSingleDto
	 * @return delRowCount:登録件数<br>
	 * @exception NaiException
	 */
	public int insert(PaymentTrnSingleDto dto) throws NaiException {

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		int insertRowCount = 0;

		StringBuffer sb = new StringBuffer();

		// データ登録処理
		sb.append("INSERT INTO PAYMENT_TRN ");
		sb.append("VALUE ( ");
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

			// パラメタの設定
			ps.setString(1, dto.getPaymentPlanYyyyMm());
			ps.setString(2, dto.getDataYyyyMm());
			ps.setString(3, dto.getUserId());
			ps.setString(4, dto.getTeacherNickNm());
			ps.setString(5, dto.getFamilyJnm());
			ps.setString(6, dto.getFirstJnm());
			ps.setString(7, String.valueOf(dto.getLessonNum()));
			ps.setString(8, String.valueOf(dto.getBatPaymentYen()));
			ps.setString(9, String.valueOf(dto.getSourceYen()));
			ps.setString(10, String.valueOf(dto.getAdjustmentYen()));
			ps.setString(11, String.valueOf(dto.getEndPaymentYen()));
			ps.setString(12, dto.getPaymentKbn());
			ps.setString(13, dto.getPaymentNm());
			ps.setString(14, String.valueOf(dto.getRecordVerNo()));
			ps.setString(15, String.valueOf(Timestamp.valueOf(sdf.format(cal.getTime()))));
			ps.setString(16, NaikaraTalkConstants.INSERT_CD);
			ps.setString(17, String.valueOf(Timestamp.valueOf(sdf.format(cal.getTime()))));
			ps.setString(18, NaikaraTalkConstants.UPDATE_CD);

			insertRowCount = ps.executeUpdate();

			if (insertRowCount < 1) {
				insertRowCount = NaikaraTalkConstants.RETURN_CD_ERR_DEL;
			}

			return insertRowCount;

		} catch (SQLException se) {
			se.printStackTrace();
			throw new NaiException(se);
		}
	}
}