package com.naikara_talk.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
import org.apache.struts2.ServletActionContext;

import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dbutil.QueryCondition;
import com.naikara_talk.dto.TeacherMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

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
public class TeacherMstDao extends AbstractDao {

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
    public TeacherMstDao(Connection con) {
        this.conn = con;
    }

    public List<TeacherMstDto> search(ConditionMapper conditions, OrderCondition orderConditions) throws NaiException {

        // 戻り値とリターンコードの初期化
        ArrayList<TeacherMstDto> list = null;     // DTOリスト
        TeacherMstDto dto = new TeacherMstDto();  // DTO

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
        if (!StringUtils.isEmpty(conditions.getConditionString())) {
            sb.append("where ");
            sb.append(conditions.getConditionString());
        }
        // 並び順の設定
        if (!StringUtils.isEmpty(orderConditions.getOrderString())) {
            sb.append(orderConditions.getOrderString());
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

            // SQL文実行
            res = ps.executeQuery();

            list = new ArrayList<TeacherMstDto>();

            while (res.next()) {

                dto = new TeacherMstDto();

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
                dto.setImgPhoto(res.getBlob("IMG_PHOTO"));
                dto.setEvaluationFromSchoolCmt1(res.getString("EVALUATION_FROM_SCHOOL_CMT1"));
                dto.setEvaluationFromSchoolCmt2(res.getString("EVALUATION_FROM_SCHOOL_CMT2"));
                dto.setLatestEvaluationFromStudentCmt(res.getString("LATEST_EVALUATION_FROM_STUDENT_CMT"));
                dto.setRemark(res.getString("REMARK"));
                dto.setRecordVerNo(res.getInt("RECORD_VER_NO"));
                dto.setInsertDttm(res.getTimestamp("INSERT_DTTM"));
                dto.setInsertCd(res.getString("INSERT_CD"));
                dto.setUpdateDttm(res.getTimestamp("UPDATE_DTTM"));
                dto.setUpdateCd(res.getString("UPDATE_CD"));

                dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES); // リターンコード

                list.add(dto);
            }

            if (list.size() < 1) {
                dto = new TeacherMstDto();
                dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_NO);
                list.add(dto);
            }

            return list;

        } catch (SQLException se) {
            throw new NaiException(se);
        } finally {
            try {
                if (res != null) {
                    res.close();
                }
            } catch (SQLException e) {
                throw new NaiException(e);
            }
        }
    }

    /**
     * 講師マスタ更新<br>
     * <br>
     * 講師マスタの更新を行う<br>
     * <br>
     * @param TeacherMstDto
     * @return updatedRowCount
     * @throws NaiException
     */
    public int update(TeacherMstDto dto) throws NaiException {

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        int updatedRowCount = NaikaraTalkConstants.RETURN_CD_ERR_UPD; // 更新データ件数

        // 利用者マスタのデータチェック
        if (!chkDto(dto)) {
            return updatedRowCount;
        }

        StringBuffer sb = new StringBuffer();

        // データ更新処理
        sb.append("update ");
        sb.append("TEACHER_MST ");
        sb.append("set ");
        sb.append(" NICK_ANM = ? ");
        sb.append(",NATIONALITY_CD = ? ");
        sb.append(",NATIVE_LANG_CD = ? ");
        sb.append(",COUNTRY_CD = ? ");
        sb.append(",AREA_NO_CD = ? ");
        sb.append(",CONTRACT_DT = ? ");
        sb.append(",CONTRACT_START_DT = ? ");
        sb.append(",CONTRACT_END_DT = ? ");
        sb.append(",BANK_JPN_BANK_NM = ? ");
        sb.append(",BANK_JPN_BRANCH_NM = ? ");
        sb.append(",BANK_JPN_TYPE_OF_ACCOUNT = ? ");
        sb.append(",BANK_JPN_ACCOUNT_HOLDERS_KNM = ? ");
        sb.append(",BANK_JPN_ACCOUNT_HOLDERS_NM = ? ");
        sb.append(",BANK_JPN_ACCOUNT_NUMBER = ? ");
        sb.append(",BANK_JPN_ADDITIONAL_INFO = ? ");
        sb.append(",JPN_PBANK_BRANCH_NM = ? ");
        sb.append(",JPN_PBANK_TYPE_OF_ACCOUNT = ? ");
        sb.append(",JPN_PBANK_ACCOUNT_HOLDERS_KNM = ? ");
        sb.append(",JPN_PBANK_ACCOUNT_HOLDERS_NM = ? ");
        sb.append(",JPN_PBANK_ACCOUNT_NUMBER = ? ");
        sb.append(",JPN_PBANK_ADDITIONAL_INFO = ? ");
        sb.append(",OVERSEA_ACCOUNT_H_NM = ? ");
        sb.append(",OVERSEA_ACCOUNT_H_R_ADDRESS1 = ? ");
        sb.append(",OVERSEA_ACCOUNT_H_R_ADDRESS2 = ? ");
        sb.append(",OVERSEA_ACCOUNT_NUMBER_IBAN = ? ");
        sb.append(",OVERSEA_ABANO_SWIFTCD_BICCD = ? ");
        sb.append(",OVERSEA_ETC = ? ");
        sb.append(",OVERSEA_BANK_NM = ? ");
        sb.append(",OVERSEA_BRANCH_NM = ? ");
        sb.append(",OVERSEA_BRANCH_ADDRESS1 = ? ");
        sb.append(",OVERSEA_BRANCH_ADDRESS2 = ? ");
        sb.append(",OVERSEA_COUNTRY_BRANCH_EXISTS = ? ");
        sb.append(",OVERSEA_ADDITIONAL_INFO = ? ");
        sb.append(",OTHER_REMITTANCE_FEE_KBN = ? ");
        sb.append(",OVERSEA_PL_PAYPAL_ADDRESS = ? ");
        sb.append(",OVERSEA_PL_ADDITIONAL_INFO = ? ");
        sb.append(",SELLING_POINT = ? ");
        sb.append(",SELF_RECOMMENDATION = ? ");
        if (StringUtils.equals(NaikaraTalkConstants.TRUE, dto.getImgPhotoChkn()) || dto.getImgPhotoPage() != null) {
            sb.append(",IMG_PHOTO_NM = ? ");
            sb.append(",IMG_PHOTO = ? ");
        }
        sb.append(",EVALUATION_FROM_SCHOOL_CMT1 = ? ");
        sb.append(",EVALUATION_FROM_SCHOOL_CMT2 = ? ");
        sb.append(",LATEST_EVALUATION_FROM_STUDENT_CMT = ? ");
        sb.append(",REMARK = ? ");
        sb.append(",RECORD_VER_NO = ? ");
        sb.append(",UPDATE_DTTM = ? ");
        sb.append(",UPDATE_CD = ? ");
        sb.append("where ");
        sb.append("USER_ID = ? ");
        sb.append("and ");
        sb.append("RECORD_VER_NO = ? ");

        // BLOG用
        FileInputStream fis1 = null;
        try {

            PreparedStatement ps = conn.prepareStatement(sb.toString());

            int index = 1;
            ps.setString(index++, dto.getNickAnm());
            ps.setString(index++, dto.getNationalityCd());
            ps.setString(index++, dto.getNativeLangCd());
            ps.setString(index++, dto.getCountryCd());
            ps.setString(index++, dto.getAreaNoCd());
            ps.setString(index++, NaikaraStringUtil.converToYYYYMMDD(dto.getContractDt()));
            ps.setString(index++, NaikaraStringUtil.converToYYYYMMDD(dto.getContractStartDt()));
            ps.setString(index++, NaikaraStringUtil.converToYYYYMMDD(dto.getContractEndDt()));
            ps.setString(index++, dto.getBankJpnBankNm());
            ps.setString(index++, dto.getBankJpnBranchNm());
            ps.setString(index++, dto.getBankJpnTypeOfAccount());
            ps.setString(index++, dto.getBankJpnAccountHoldersKnm());
            ps.setString(index++, dto.getBankJpnAccountHoldersNm());
            ps.setString(index++, dto.getBankJpnAccountNumber());
            ps.setString(index++, dto.getBankJpnAdditionalInfo());
            ps.setString(index++, dto.getJpnPbankBranchNm());
            ps.setString(index++, dto.getJpnPbankTypeOfAccount());
            ps.setString(index++, dto.getJpnPbankAccountHoldersKnm());
            ps.setString(index++, dto.getJpnPbankAccountHoldersNm());
            ps.setString(index++, dto.getJpnPbankAccountNumber());
            ps.setString(index++, dto.getJpnPbankAdditionalInfo());
            ps.setString(index++, dto.getOverseaAccountHNm());
            ps.setString(index++, dto.getOverseaAccountHRAddress1());
            ps.setString(index++, dto.getOverseaAccountHRAddress2());
            ps.setString(index++, dto.getOverseaAccountNumberIban());
            ps.setString(index++, dto.getOverseaAbanoSwiftcdBiccd());
            ps.setString(index++, dto.getOverseaEtc());
            ps.setString(index++, dto.getOverseaBankNm());
            ps.setString(index++, dto.getOverseaBranchNm());
            ps.setString(index++, dto.getOverseaBranchAddress1());
            ps.setString(index++, dto.getOverseaBranchAddress2());
            ps.setString(index++, dto.getOverseaCountryBranchExists());
            ps.setString(index++, dto.getOverseaAdditionalInfo());
            ps.setString(index++, dto.getOtherRemittanceFeeKbn());
            ps.setString(index++, dto.getOverseaPlPaypalAddress());
            ps.setString(index++, dto.getOverseaPlAdditionalInfo());
            ps.setString(index++, dto.getSellingPoint());
            ps.setString(index++, dto.getSelfRecommendation());
            if (dto.getImgPhotoPage() != null) {
                ps.setString(index++, dto.getImgPhotoNm());
                try {
                    fis1 = new FileInputStream(dto.getImgPhotoPage());
                    ps.setBinaryStream(index++, fis1, fis1.available());
                } catch (IOException se) {
                    throw new NaiException(se);
                }
            }
            if (StringUtils.equals(NaikaraTalkConstants.TRUE, dto.getImgPhotoChkn()) && dto.getImgPhotoPage() == null) {
                ps.setString(index++, NaikaraTalkConstants.IMG_NODATA);
                try {
                    fis1 = new FileInputStream(new File(ServletActionContext.getServletContext().getRealPath(
                            NaikaraTalkConstants.IMG_NODATA_PATH), NaikaraTalkConstants.IMG_NODATA));
                    ps.setBinaryStream(index++, fis1, fis1.available());
                } catch (IOException se) {
                    throw new NaiException(se);
                }
            }
            ps.setString(index++, dto.getEvaluationFromSchoolCmt1());
            ps.setString(index++, dto.getEvaluationFromSchoolCmt2());
            ps.setString(index++, dto.getLatestEvaluationFromStudentCmt());
            ps.setString(index++, dto.getRemark());
            ps.setString(index++, String.valueOf(dto.getRecordVerNo() + 1));
            ps.setString(index++, String.valueOf(Timestamp.valueOf(sdf.format(cal.getTime()))));
            ps.setString(index++, dto.getUpdateCd());
            ps.setString(index++, dto.getUserId());
            ps.setInt(index++, dto.getRecordVerNo());

            updatedRowCount = ps.executeUpdate();

            return updatedRowCount;

        } catch (SQLException se) {
            throw new NaiException(se);
        } finally {
            try {
                if (fis1 != null) {
                    fis1.close();
                }
            } catch (IOException se) {
                throw new NaiException(se);
            }
        }
    }

    /**
     * 登録処理。<br>
     * <br>
     * 登録処理。<br>
     * <br>
     * @param TeacherMstDto 画面のパラメータ<br>
     * @return なし<br>
     * @throws NaiException
     */
    public int insert(TeacherMstDto dto) throws NaiException {

        // この画面を開いたロールを取得します(この段階に到達した時点でユーザ情報は存在している)
        String userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();

        int insertRowCount = NaikaraTalkConstants.RETURN_CD_ERR_INS; // 追加データ件数

        // SQL文作成
        StringBuffer sb = new StringBuffer();
        sb.append(" insert into TEACHER_MST");
        sb.append(" ( ");
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
        sb.append(" ,REMARK ");
        sb.append(" ,RECORD_VER_NO ");
        sb.append(" ,INSERT_DTTM ");
        sb.append(" ,INSERT_CD ");
        sb.append(" ,UPDATE_DTTM ");
        sb.append(" ,UPDATE_CD )");
        sb.append(" values ");
        sb.append(" ( ");
        sb.append("  ? ");
        sb.append(" ,? ");
        sb.append(" ,? ");
        sb.append(" ,? ");
        sb.append(" ,? ");
        sb.append(" ,? ");
        sb.append(" ,? ");
        sb.append(" ,? ");
        sb.append(" ,? ");
        sb.append(" ,? ");
        sb.append(" ,? ");
        sb.append(" ,? ");
        sb.append(" ,? ");
        sb.append(" ,? ");
        sb.append(" ,? ");
        sb.append(" ,? ");
        sb.append(" ,? ");
        sb.append(" ,? ");
        sb.append(" ,? ");
        sb.append(" ,? ");
        sb.append(" ,? ");
        sb.append(" ,? ");
        sb.append(" ,? ");
        sb.append(" ,? ");
        sb.append(" ,? ");
        sb.append(" ,? ");
        sb.append(" ,? ");
        sb.append(" ,? ");
        sb.append(" ,? ");
        sb.append(" ,? ");
        sb.append(" ,? ");
        sb.append(" ,? ");
        sb.append(" ,? ");
        sb.append(" ,? ");
        sb.append(" ,? ");
        sb.append(" ,? ");
        sb.append(" ,? ");
        sb.append(" ,? ");
        sb.append(" ,? ");
        sb.append(" ,? ");
        sb.append(" ,? ");
        sb.append(" ,? ");
        sb.append(" ,? ");
        sb.append(" ,? ");
        sb.append(" ,? ");
        sb.append(" ,0 ");
        sb.append(" ,sysdate() ");
        sb.append(" ,? ");
        sb.append(" ,sysdate() ");
        sb.append(" ,?) ");

        PreparedStatement ps = null;
        // BLOG用
        FileInputStream fis1 = null;

        try {

            ps = conn.prepareStatement(sb.toString());
            ps.setString(1, dto.getUserId());
            ps.setString(2, dto.getNickAnm());
            ps.setString(3, dto.getNationalityCd());
            ps.setString(4, dto.getNativeLangCd());
            ps.setString(5, dto.getCountryCd());
            ps.setString(6, dto.getAreaNoCd());
            ps.setString(7, dto.getContractDt());
            ps.setString(8, dto.getContractStartDt());
            ps.setString(9, dto.getContractEndDt());
            ps.setString(10, dto.getBankJpnBankNm());
            ps.setString(11, dto.getBankJpnBranchNm());
            ps.setString(12, dto.getBankJpnTypeOfAccount());
            ps.setString(13, dto.getBankJpnAccountHoldersKnm());
            ps.setString(14, dto.getBankJpnAccountHoldersNm());
            ps.setString(15, dto.getBankJpnAccountNumber());
            ps.setString(16, dto.getBankJpnAdditionalInfo());
            ps.setString(17, dto.getJpnPbankBranchNm());
            ps.setString(18, dto.getJpnPbankTypeOfAccount());
            ps.setString(19, dto.getJpnPbankAccountHoldersKnm());
            ps.setString(20, dto.getJpnPbankAccountHoldersNm());
            ps.setString(21, dto.getJpnPbankAccountNumber());
            ps.setString(22, dto.getJpnPbankAdditionalInfo());
            ps.setString(23, dto.getOverseaAccountHNm());
            ps.setString(24, dto.getOverseaAccountHRAddress1());
            ps.setString(25, dto.getOverseaAccountHRAddress2());
            ps.setString(26, dto.getOverseaAccountNumberIban());
            ps.setString(27, dto.getOverseaAbanoSwiftcdBiccd());
            ps.setString(28, dto.getOverseaEtc());
            ps.setString(29, dto.getOverseaBankNm());
            ps.setString(30, dto.getOverseaBranchNm());
            ps.setString(31, dto.getOverseaBranchAddress1());
            ps.setString(32, dto.getOverseaBranchAddress2());
            ps.setString(33, dto.getOverseaCountryBranchExists());
            ps.setString(34, dto.getOverseaAdditionalInfo());
            ps.setString(35, dto.getOtherRemittanceFeeKbn());
            ps.setString(36, dto.getOverseaPlPaypalAddress());
            ps.setString(37, dto.getOverseaPlAdditionalInfo());
            ps.setString(38, dto.getSellingPoint());
            ps.setString(39, dto.getSelfRecommendation());
            ps.setString(40, dto.getImgPhotoNm());
            if (dto.getImgPhotoPage() == null) {
                ps.setString(40, NaikaraTalkConstants.IMG_NODATA);
                try {
                    fis1 = new FileInputStream(new File(ServletActionContext.getServletContext().getRealPath(
                            NaikaraTalkConstants.IMG_NODATA_PATH), NaikaraTalkConstants.IMG_NODATA));
                    ps.setBinaryStream(41, fis1, fis1.available());
                } catch (IOException se) {
                    throw new NaiException(se);
                }
            } else {
                ps.setString(40, dto.getImgPhotoNm());
                try {
                    fis1 = new FileInputStream(dto.getImgPhotoPage());
                    ps.setBinaryStream(41, fis1, fis1.available());
                } catch (IOException se) {
                    throw new NaiException(se);
                }
            }
            ps.setString(42, dto.getEvaluationFromSchoolCmt1());
            ps.setString(43, dto.getEvaluationFromSchoolCmt2());
            ps.setString(44, dto.getLatestEvaluationFromStudentCmt());
            ps.setString(45, dto.getRemark());
            ps.setString(46, userId);
            ps.setString(47, userId);

            // SQL文を実行
            insertRowCount = ps.executeUpdate();

        } catch (SQLException se) {
            throw new NaiException(se);
        } finally {
            try {
                if (fis1 != null) {
                    fis1.close();
                }
            } catch (IOException se) {
                throw new NaiException(se);
            }
        }

        return insertRowCount;
    }

    /**
     * 講師マスタ更新データチェック<br>
     * <br>
     * 講師マスタの更新データをチェックし<br>
     * エラー有の場合は、falseを返す<br>
     * エラー無の場合は、trueを返す<br>
     * <br>
     * @param UserMstDto
     * @return boolean
     */
    private boolean chkDto(TeacherMstDto dto) throws NaiException {

        // 利用者IDのチェック
        if (StringUtils.isEmpty(dto.getUserId())) {
            return false;
        }

        // ニックネームのチェック
        if (StringUtils.isEmpty(dto.getNickAnm())) {
            return false;
        }

        // 国籍コードのチェック
        if (StringUtils.isEmpty(dto.getNationalityCd())) {
            return false;
        }

        // 母国語コードのチェック
        if (StringUtils.isEmpty(dto.getNativeLangCd())) {
            return false;
        }

        // 滞在国コードのチェック
        if (StringUtils.isEmpty(dto.getCountryCd())) {
            return false;
        }

        // 時差地域NOコードのチェック
        if (StringUtils.isEmpty(dto.getAreaNoCd())) {
            return false;
        }

        // 契約日のチェック
        if (StringUtils.isEmpty(dto.getContractDt())) {
            return false;
        }

        // 契約開始日のチェック
        if (StringUtils.isEmpty(dto.getContractStartDt())) {
            return false;
        }

        // 契約終了日のチェック
        if (StringUtils.isEmpty(dto.getContractEndDt())) {
            return false;
        }

        // 更新者コードのチェック
        if (StringUtils.isEmpty(dto.getUpdateCd())) {
            return false;
        }

        return true;

    }

}
