package com.naikara_talk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;
import java.util.Calendar;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;

//import javax.sql.DataSource;
import com.naikara_talk.dbutil.ConditionMapper;
//import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dbutil.QueryCondition;
import com.naikara_talk.dto.TeacherMstDto;
import com.naikara_talk.dto.TeacherMstForCacheDto;
import com.naikara_talk.exception.NaiException;


/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DAOクラス<br>
 * <b>クラス名称　　　:</b>講師マスタデータ取得クラス<br>
 * <b>クラス概要　　　:</b>講師マスタデータ取得DAO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/06/10 TECS 新規作成
 */
public class TeacherMstForCacheDao extends AbstractDao {

	/** 利用者ID　条件項目　*/
	public static final String COND_USER_ID = "USER_ID";

	/** 適用開始日　条件項目　*/
	public static final String COND_CONTRACT_START_DT = "CONTRACT_START_DT";

	/** 適用終了日　条件項目　*/
	public static final String COND_CONTRACT_END_DT = "CONTRACT_END_DT";


	public static final String COND_PROCESS_CD = "PROCESS_CD";

	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public TeacherMstForCacheDao(Connection con) {
		this.conn = con;
	}

	public List<TeacherMstForCacheDto> search(ConditionMapper conditions, OrderCondition orderConditions) throws NaiException {


//		DataSource ds = DbUtil.getDataSource();
//		Connection conn = null;

		ResultSet res = null;

		StringBuffer sb = new StringBuffer();
		sb.append("select ");
		sb.append("  USER_ID ");
		sb.append(", NICK_ANM ");
		sb.append(", NATIONALITY_CD ");
		sb.append(", NATIVE_LANG_CD ");
		sb.append(", COUNTRY_CD ");
		sb.append(", AREA_NO_CD ");
		sb.append(", CONTRACT_DT ");
		sb.append(", CONTRACT_START_DT ");
		sb.append(", CONTRACT_END_DT ");
		sb.append(", BANK_JPN_BANK_NM ");
		sb.append(", BANK_JPN_BRANCH_NM ");
		sb.append(", BANK_JPN_TYPE_OF_ACCOUNT ");
		sb.append(", BANK_JPN_ACCOUNT_HOLDERS_KNM ");
		sb.append(", BANK_JPN_ACCOUNT_HOLDERS_NM ");
		sb.append(", BANK_JPN_ACCOUNT_NUMBER ");
		sb.append(", BANK_JPN_ADDITIONAL_INFO ");
		sb.append(", JPN_PBANK_BRANCH_NM ");
		sb.append(", JPN_PBANK_TYPE_OF_ACCOUNT ");
		sb.append(", JPN_PBANK_ACCOUNT_HOLDERS_KNM ");
		sb.append(", JPN_PBANK_ACCOUNT_HOLDERS_NM ");
		sb.append(", JPN_PBANK_ACCOUNT_NUMBER ");
		sb.append(", JPN_PBANK_ADDITIONAL_INFO ");
		sb.append(", OVERSEA_ACCOUNT_H_NM ");
		sb.append(", OVERSEA_ACCOUNT_H_R_ADDRESS1 ");
		sb.append(", OVERSEA_ACCOUNT_H_R_ADDRESS2 ");
		sb.append(", OVERSEA_ACCOUNT_NUMBER_IBAN ");
		sb.append(", OVERSEA_ABANO_SWIFTCD_BICCD ");
		sb.append(", OVERSEA_ETC ");
		sb.append(", OVERSEA_BANK_NM ");
		sb.append(", OVERSEA_BRANCH_NM ");
		sb.append(", OVERSEA_BRANCH_ADDRESS1 ");
		sb.append(", OVERSEA_BRANCH_ADDRESS2 ");
		sb.append(", OVERSEA_COUNTRY_BRANCH_EXISTS ");
		sb.append(", OVERSEA_ADDITIONAL_INFO ");
		sb.append(", OTHER_REMITTANCE_FEE_KBN ");
		sb.append(", OVERSEA_PL_PAYPAL_ADDRESS ");
		sb.append(", OVERSEA_PL_ADDITIONAL_INFO ");
		sb.append(", SELLING_POINT ");
		sb.append(", SELF_RECOMMENDATION ");
		sb.append(", IMG_PHOTO_NM ");
		sb.append(", IMG_PHOTO ");
		sb.append(", EVALUATION_FROM_SCHOOL_CMT1 ");
		sb.append(", EVALUATION_FROM_SCHOOL_CMT2 ");
		sb.append(", LATEST_EVALUATION_FROM_STUDENT_CMT ");
		sb.append(", REMARK ");
		sb.append(", RECORD_VER_NO ");
		sb.append(", INSERT_DTTM ");
		sb.append(", INSERT_CD ");
		sb.append(", UPDATE_DTTM ");
		sb.append(", UPDATE_CD ");
		sb.append("from ");
		sb.append(" TEACHER_MST ");

		// 抽出条件の設定
		if(!StringUtils.isEmpty(conditions.getConditionString())) {
				sb.append("where ");
				sb.append(conditions.getConditionString());
		}
		// 並び順の設定
		if(!StringUtils.isEmpty(orderConditions.getOrderString())) {
			sb.append(orderConditions.getOrderString());
		}

		try{
//			conn = ds.getConnection();

			PreparedStatement ps = conn.prepareStatement(sb.toString());


            // パラメタの設定
            int i = 0;
            for( QueryCondition cond : conditions.getConditions()){
            	for(String val : cond.getValues()){
            		ps.setString(i + 1, val);
            		i++;
            	}
            }

            // SQL文実行
			res = ps.executeQuery();

			ArrayList<TeacherMstForCacheDto> list = new ArrayList<TeacherMstForCacheDto>();

			while (res.next()){

				TeacherMstForCacheDto dto = new TeacherMstForCacheDto();

				dto.setUserId(res.getString("USER_ID"));
				dto.setNickAnm(res.getString("NICK_ANM"));
				dto.setNationalityCd(res.getString("NATIONALITY_CD"));
				dto.setNativeLangCd(res.getString("NATIVE_LANG_CD"));
				dto.setCountryCd(res.getString("COUNTRY_CD"));
				dto.setAreaNoCd(res.getString("AREA_NO_CD"));
				dto.setContractDt(res.getString("CONTRACT_DT"));
				dto.setContractStartDt(res.getString("CONTRACT_START_DT"));
				dto.setContractEndDt(res.getString("CONTRACT_END_DT"));
				dto.setBankJpnBankNm(res.getString("BANK_JPN_BANK_NM"));
				dto.setBankJpnBranchNm(res.getString("BANK_JPN_BRANCH_NM"));
				dto.setBankJpnTypeOfAccount(res.getString("BANK_JPN_TYPE_OF_ACCOUNT"));
				dto.setBankJpnAccountHoldersKnm(res.getString("BANK_JPN_ACCOUNT_HOLDERS_KNM"));
				dto.setBankJpnAccountHoldersNm(res.getString("BANK_JPN_ACCOUNT_HOLDERS_NM"));
				dto.setBankJpnAccountNumber(res.getString("BANK_JPN_ACCOUNT_NUMBER"));
				dto.setBankJpnAdditionalInfo(res.getString("BANK_JPN_ADDITIONAL_INFO"));
				dto.setJpnPbankBranchNm(res.getString("JPN_PBANK_BRANCH_NM"));
				dto.setJpnPbankTypeOfAccount(res.getString("JPN_PBANK_TYPE_OF_ACCOUNT"));
				dto.setJpnPbankAccountHoldersKnm(res.getString("JPN_PBANK_ACCOUNT_HOLDERS_KNM"));
				dto.setJpnPbankAccountHoldersNm(res.getString("JPN_PBANK_ACCOUNT_HOLDERS_NM"));
				dto.setJpnPbankAccountNumber(res.getString("JPN_PBANK_ACCOUNT_NUMBER"));
				dto.setJpnPbankAdditionalInfo(res.getString("JPN_PBANK_ADDITIONAL_INFO"));
				dto.setOverseaAccountHNm(res.getString("OVERSEA_ACCOUNT_H_NM"));
				dto.setOverseaAccountHRAddress1(res.getString("OVERSEA_ACCOUNT_H_R_ADDRESS1"));
				dto.setOverseaAccountHRAddress2(res.getString("OVERSEA_ACCOUNT_H_R_ADDRESS2"));
				dto.setOverseaAccountNumberIban(res.getString("OVERSEA_ACCOUNT_NUMBER_IBAN"));
				dto.setOverseaAbanoSwiftcdBiccd(res.getString("OVERSEA_ABANO_SWIFTCD_BICCD"));
				dto.setOverseaEtc(res.getString("OVERSEA_ETC"));
				dto.setOverseaBankNm(res.getString("OVERSEA_BANK_NM"));
				dto.setOverseaBranchNm(res.getString("OVERSEA_BRANCH_NM"));
				dto.setOverseaBranchAddress1(res.getString("OVERSEA_BRANCH_ADDRESS1"));
				dto.setOverseaBranchAddress2(res.getString("OVERSEA_BRANCH_ADDRESS2"));
				dto.setOverseaCountryBranchExists(res.getString("OVERSEA_COUNTRY_BRANCH_EXISTS"));
				dto.setOverseaAdditionalInfo(res.getString("OVERSEA_ADDITIONAL_INFO"));
				dto.setOtherRemittanceFeeKbn(res.getString("OTHER_REMITTANCE_FEE_KBN"));
				dto.setOverseaPlPaypalAddress(res.getString("OVERSEA_PL_PAYPAL_ADDRESS"));
				dto.setOverseaPlAdditionalInfo(res.getString("OVERSEA_PL_ADDITIONAL_INFO"));
				dto.setSellingPoint(res.getString("SELLING_POINT"));
				dto.setSelfRecommendation(res.getString("SELF_RECOMMENDATION"));
				dto.setImgPhotoNm(res.getString("IMG_PHOTO_NM"));
				dto.setImgPhoto(res.getBytes("IMG_PHOTO"));
				dto.setEvaluationFromSchoolCmt1(res.getString("EVALUATION_FROM_SCHOOL_CMT1"));
				dto.setEvaluationFromSchoolCmt2(res.getString("EVALUATION_FROM_SCHOOL_CMT2"));
				dto.setLatestEvaluationFromStudentCmt(res.getString("LATEST_EVALUATION_FROM_STUDENT_CMT"));
				dto.setRemark(res.getString("REMARK"));
				dto.setRecordVerNo(res.getInt("RECORD_VER_NO"));
				dto.setInsertDttm(res.getTimestamp("INSERT_DTTM"));
				dto.setInsertCd(res.getString("INSERT_CD"));
				dto.setUpdateDttm(res.getTimestamp("UPDATE_DTTM"));
				dto.setUpdateCd(res.getString("UPDATE_CD"));

				list.add(dto);
			}

			return list;

		} catch ( SQLException se ) {
			throw new NaiException(se);
		} finally {
			try {
				if(res != null) {
					res.close();
				}
			} catch (SQLException e) {
				throw new NaiException(e);
			}
		}
	}
}
