package com.naikara_talk.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dbutil.QueryCondition;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.PointOwnershipTrnDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.util.DateUtil;
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
 *                         2013/11/14 TECS getPointBalanceメソッドの修正
 *                         2014/06/02 TECS レッスン予約に関する「応相談」対応
 */
public class PointOwnershipTrnDao extends AbstractDao {

    /** 所有ID  条件項目  */
    public static final String COND_OWNERSHIP_ID = "OWNERSHIP_ID";

    /** 有償無償区分  条件項目  */
    public static final String COND_COMPENSATION_FREE_KBN = "COMPENSATION_FREE_KBN";

    /** 受講者ID  条件項目  */
    public static final String COND_STUDENT_ID = "STUDENT_ID";

    /** 有効開始日  条件項目  */
    public static final String COND_EFFECTIVE_STR_DT = "EFFECTIVE_STR_DT";

    /** 有効終了日  条件項目  */
    public static final String COND_EFFECTIVE_END_DT = "EFFECTIVE_END_DT";

    /** 通常月謝区分  条件項目  */
    public static final String COND_FEE_KBN = "FEE_KBN";

    /** ポイント残高  条件項目  */
    public static final String COND_BALANCE_POINT = "BALANCE_POINT";

    /** 購入日  条件項目  */
    public static final String COND_PURCHASE_DT = "PURCHASE_DT";

    /** 月謝停止日  条件項目  */
    public static final String COND_END_DT = "END_DT";

    /** 月謝停止フラグ  条件項目  */
    public static final String COND_END_FLG = "END_FLG";

    /** ポイントコード */
    public static final String COND_POINT_CD = "POINT_CD";

    /** ポイント残高  条件項目(JOIN)  */
    public static final String COND_BALANCE_POINT_P = "P.BALANCE_POINT";

    /** ポイント残高  条件項目(JOIN)  */
    public static final String COND_EFFECTIVE_END_DT_P = "P.EFFECTIVE_END_DT";

    /** 利用終了日  条件項目(JOIN)  */
    public static final String COND_USE_END_DT_S = "S.USE_END_DT";

    /** 顧客区分  条件項目(JOIN)  */
    public static final String COND_CUSTOMER_KBN_S = "S.CUSTOMER_KBN";


    /** 購入日（上6桁）  条件項目  */
    public static final String COND_PURCHASE_DT6 = "SUBSTR(PURCHASE_DT, 1, 6)";


	int calcSignP = 0;
	int calcSignM = 1;

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public PointOwnershipTrnDao(Connection con) {
        this.conn = con;
    }

    /**
     * ポイント所有テーブルデータ取得<br>
     * <br>
     * ポイント所有テーブルリストを戻り値で返却する<br>
     * <br>
     * @param conditions
     * @param OrderBy
     * @return ArrayList<PointOwnershipTrnDto>
     * @throws NaiException
     */
    public ArrayList<PointOwnershipTrnDto> search(ConditionMapper conditions, OrderCondition OrderBy)
            throws NaiException {

        // 戻り値とリターンコードの初期化
        ArrayList<PointOwnershipTrnDto> list = null; // DTOリスト
        PointOwnershipTrnDto dto = null;             // DTO

        ResultSet res = null;

        StringBuffer sb = new StringBuffer();

        // 取得処理
        sb.append(" SELECT ");
        sb.append("   OWNERSHIP_ID ");
        sb.append("  ,COMPENSATION_FREE_KBN ");
        sb.append("  ,STUDENT_ID ");
        sb.append("  ,EFFECTIVE_STR_DT ");
        sb.append("  ,EFFECTIVE_END_DT ");
        sb.append("  ,FEE_KBN ");
        sb.append("  ,PURCHASE_YEN ");
        sb.append("  ,PURCHASE_FREE_POINT ");
        sb.append("  ,BALANCE_POINT ");
        sb.append("  ,POINT_ADDITION_REASON_CD ");
        sb.append("  ,PURCHASE_DT ");
        sb.append("  ,PURCHASE_TM ");
        sb.append("  ,POINT_CD ");
        sb.append("  ,END_DT ");
        sb.append("  ,BEGINNING_PURCHASE_DT ");
        sb.append("  ,BEGINNING_PURCHASE_TM ");
        sb.append("  ,SCREEN_SYSTEM_KBN ");
        sb.append("  ,PROFILE_ID ");
        sb.append("  ,END_FLG ");
        sb.append("  ,RECORD_VER_NO ");
        sb.append("  ,INSERT_DTTM ");
        sb.append("  ,INSERT_CD ");
        sb.append("  ,UPDATE_DTTM ");
        sb.append("  ,UPDATE_CD ");
        sb.append(" FROM ");
        sb.append("   POINT_OWNERSHIP_TRN ");
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

            // コード管理マスタのキャッシュ読み込み
            CodeManagMstCache cache = CodeManagMstCache.getInstance();
            LinkedHashMap<String, CodeManagMstDto> reasonList = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_POINT_ADDITIONAL_REASON);
            LinkedHashMap<String, CodeManagMstDto> feeKbnList = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_FEE_KBN);
            LinkedHashMap<String, CodeManagMstDto> compensationFreeKbnList = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_COMPENSATION_FREE_KBN);

            list = new ArrayList<PointOwnershipTrnDto>();
            while (res.next()) {

                dto = new PointOwnershipTrnDto();

                dto.setOwnershipId(res.getString("OWNERSHIP_ID"));
                dto.setCompensationFreeKbn(res.getString("COMPENSATION_FREE_KBN"));
                // 有償無償区分名
                if (compensationFreeKbnList.get(res.getString("COMPENSATION_FREE_KBN")) != null) {
                    dto.setCompensationFreeKbnNm(StringUtils.substring(
                            compensationFreeKbnList.get(res.getString("COMPENSATION_FREE_KBN")).getManagerNm(), 0, 2));
                }
                dto.setStudentId(res.getString("STUDENT_ID"));
                dto.setEffectiveStrDt(res.getString("EFFECTIVE_STR_DT"));
                dto.setEffectiveEndDt(res.getString("EFFECTIVE_END_DT"));
                dto.setFeeKbn(res.getString("FEE_KBN"));
                // 通常月謝区分名
                if (feeKbnList.get(res.getString("FEE_KBN")) != null) {
                    dto.setFeeKbnNm(feeKbnList.get(res.getString("FEE_KBN")).getManagerNm());
                }
                dto.setPurchaseYen(res.getBigDecimal("PURCHASE_YEN"));
                dto.setPurchaseFreePoint(res.getBigDecimal("PURCHASE_FREE_POINT"));
                dto.setBalancePoint(res.getBigDecimal("BALANCE_POINT"));
                dto.setUsePoint(res.getBigDecimal("PURCHASE_FREE_POINT").subtract(res.getBigDecimal("BALANCE_POINT")));
                dto.setPointAdditionReasonCd(res.getString("POINT_ADDITION_REASON_CD"));
                if (reasonList.get(res.getString("POINT_ADDITION_REASON_CD")) != null) {
                    dto.setPointAdditionReason(reasonList.get(res.getString("POINT_ADDITION_REASON_CD")).getManagerNm());
                }
                dto.setPurchaseDt(res.getString("PURCHASE_DT"));
                dto.setPurchaseTm(res.getString("PURCHASE_TM"));
                dto.setPointCd(res.getString("POINT_CD"));
                dto.setEndDt(res.getString("END_DT"));
                dto.setBeginningPurchaseDt(res.getString("BEGINNING_PURCHASE_DT"));
                dto.setBeginningPurchaseTm(res.getString("BEGINNING_PURCHASE_TM"));
                dto.setScreenSystemKbn(res.getString("SCREEN_SYSTEM_KBN"));
                dto.setProfileId(res.getString("PROFILE_ID"));
                dto.setEndFlg(res.getString("END_FLG"));
                dto.setRecordVerNo(res.getInt("RECORD_VER_NO"));
                dto.setInsertDttm(res.getTimestamp("INSERT_DTTM"));
                dto.setInsertCd(res.getString("INSERT_CD"));
                dto.setUpdateDttm(res.getTimestamp("UPDATE_DTTM"));
                dto.setUpdateCd(res.getString("UPDATE_CD"));

                dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);

                list.add(dto);
            }

            if (list.size() < 1) {
                list = new ArrayList<PointOwnershipTrnDto>();
                dto = new PointOwnershipTrnDto();
                dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_NO);
                list.add(dto);
                return list;
            }

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
    /**
     * ポイント所有テーブルデータ取得<br>
     * <br>
     * 受講者マスタの条件でポイント所有テーブルリストを戻り値で返却する<br>
     * <br>
     * @param conditions
     * @param OrderBy
     * @return ArrayList<PointOwnershipTrnDto>
     * @throws NaiException
     */
    public ArrayList<PointOwnershipTrnDto> searchJoin(ConditionMapper conditions, OrderCondition OrderBy)
            throws NaiException {

        // 戻り値とリターンコードの初期化
        ArrayList<PointOwnershipTrnDto> list = null; // DTOリスト
        PointOwnershipTrnDto dto = null;             // DTO

        ResultSet res = null;

        StringBuffer sb = new StringBuffer();

        // 取得処理
        sb.append(" SELECT ");
        sb.append("   P.OWNERSHIP_ID ");
        sb.append("  ,P.COMPENSATION_FREE_KBN ");
        sb.append("  ,P.STUDENT_ID ");
        sb.append("  ,P.EFFECTIVE_STR_DT ");
        sb.append("  ,P.EFFECTIVE_END_DT ");
        sb.append("  ,P.FEE_KBN ");
        sb.append("  ,P.PURCHASE_YEN ");
        sb.append("  ,P.PURCHASE_FREE_POINT ");
        sb.append("  ,P.BALANCE_POINT ");
        sb.append("  ,P.POINT_ADDITION_REASON_CD ");
        sb.append("  ,P.PURCHASE_DT ");
        sb.append("  ,P.PURCHASE_TM ");
        sb.append("  ,P.POINT_CD ");
        sb.append("  ,P.END_DT ");
        sb.append("  ,P.BEGINNING_PURCHASE_DT ");
        sb.append("  ,P.BEGINNING_PURCHASE_TM ");
        sb.append("  ,P.SCREEN_SYSTEM_KBN ");
        sb.append("  ,P.PROFILE_ID ");
        sb.append("  ,P.END_FLG ");
        sb.append("  ,P.RECORD_VER_NO ");
        sb.append("  ,P.INSERT_DTTM ");
        sb.append("  ,P.INSERT_CD ");
        sb.append("  ,P.UPDATE_DTTM ");
        sb.append("  ,P.UPDATE_CD ");
        sb.append(" FROM POINT_OWNERSHIP_TRN P ");
        sb.append(" INNER JOIN STUDENT_MST S ");
        sb.append(" ON S.STUDENT_ID = P.STUDENT_ID ");
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

            list = new ArrayList<PointOwnershipTrnDto>();
            while (res.next()) {

                dto = new PointOwnershipTrnDto();

                dto.setOwnershipId(res.getString("P.OWNERSHIP_ID"));
                dto.setCompensationFreeKbn(res.getString("P.COMPENSATION_FREE_KBN"));
                dto.setStudentId(res.getString("P.STUDENT_ID"));
                dto.setEffectiveStrDt(res.getString("P.EFFECTIVE_STR_DT"));
                dto.setEffectiveEndDt(res.getString("P.EFFECTIVE_END_DT"));
                dto.setFeeKbn(res.getString("P.FEE_KBN"));
                dto.setPurchaseYen(res.getBigDecimal("P.PURCHASE_YEN"));
                dto.setPurchaseFreePoint(res.getBigDecimal("P.PURCHASE_FREE_POINT"));
                dto.setBalancePoint(res.getBigDecimal("P.BALANCE_POINT"));
                dto.setPointAdditionReasonCd(res.getString("P.POINT_ADDITION_REASON_CD"));
                dto.setPurchaseDt(res.getString("P.PURCHASE_DT"));
                dto.setPurchaseTm(res.getString("P.PURCHASE_TM"));
                dto.setPointCd(res.getString("P.POINT_CD"));
                dto.setEndDt(res.getString("P.END_DT"));
                dto.setBeginningPurchaseDt(res.getString("P.BEGINNING_PURCHASE_DT"));
                dto.setBeginningPurchaseTm(res.getString("P.BEGINNING_PURCHASE_TM"));
                dto.setScreenSystemKbn(res.getString("P.SCREEN_SYSTEM_KBN"));
                dto.setProfileId(res.getString("P.PROFILE_ID"));
                dto.setEndFlg(res.getString("P.END_FLG"));
                dto.setRecordVerNo(res.getInt("P.RECORD_VER_NO"));
                dto.setInsertDttm(res.getTimestamp("P.INSERT_DTTM"));
                dto.setInsertCd(res.getString("P.INSERT_CD"));
                dto.setUpdateDttm(res.getTimestamp("P.UPDATE_DTTM"));
                dto.setUpdateCd(res.getString("P.UPDATE_CD"));

                dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);

                list.add(dto);
            }

            if (list.size() < 1) {
                list = new ArrayList<PointOwnershipTrnDto>();
                dto = new PointOwnershipTrnDto();
                dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_NO);
                list.add(dto);
                return list;
            }

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

    /**
     * ポイント所有テーブル登録処理<br>
     * <br>
     * ポイント所有テーブルの登録を行う<br>
     * <br>
     * @param PointOwnershipTrnDto
     * @return insertRowCount
     * @throws NaiException
     */
    public int insert(PointOwnershipTrnDto dto) throws NaiException {

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        int insertRowCount = NaikaraTalkConstants.RETURN_CD_ERR_INS; // 登録データ件数

        // ポイント所有テーブルのデータチェック
        if (!checkDto(dto)) {
            return insertRowCount;
        }

        StringBuffer sb = new StringBuffer();

        // データ登録処理
        sb.append("insert into ");
        sb.append("POINT_OWNERSHIP_TRN ");
        sb.append(" ( ");
        sb.append(" OWNERSHIP_ID ");
        sb.append(",COMPENSATION_FREE_KBN ");
        sb.append(",STUDENT_ID ");
        sb.append(",EFFECTIVE_STR_DT ");
        sb.append(",EFFECTIVE_END_DT ");
        sb.append(",FEE_KBN ");
        sb.append(",PURCHASE_YEN ");
        sb.append(",PURCHASE_FREE_POINT ");
        sb.append(",BALANCE_POINT ");
        sb.append(",POINT_ADDITION_REASON_CD ");
        sb.append(",PURCHASE_DT ");
        sb.append(",PURCHASE_TM ");
        sb.append(",POINT_CD ");
        sb.append(",END_DT ");
        sb.append(",BEGINNING_PURCHASE_DT ");
        sb.append(",BEGINNING_PURCHASE_TM ");
        sb.append(",SCREEN_SYSTEM_KBN ");
        sb.append(",PROFILE_ID ");
        sb.append(",END_FLG ");
        sb.append(",RECORD_VER_NO ");
        sb.append(",INSERT_DTTM ");
        sb.append(",INSERT_CD ");
        sb.append(",UPDATE_DTTM ");
        sb.append(",UPDATE_CD ");
        sb.append(" ) value ( ");
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
        sb.append(",? ");
        sb.append(",? ");
        sb.append(",? ");
        sb.append(",? ");
        sb.append(",? ");
        sb.append(",? ");
        sb.append(" ) ");

        try {

            PreparedStatement ps = conn.prepareStatement(sb.toString());

            ps.setString(1, dto.getOwnershipId());
            ps.setString(2, dto.getCompensationFreeKbn());
            ps.setString(3, dto.getStudentId());
            ps.setString(4, dto.getEffectiveStrDt());
            ps.setString(5, dto.getEffectiveEndDt());
            ps.setString(6, dto.getFeeKbn());
            ps.setString(7, String.valueOf(dto.getPurchaseYen()));
            ps.setString(8, String.valueOf(dto.getPurchaseFreePoint()));
            ps.setString(9, String.valueOf(dto.getBalancePoint()));
            ps.setString(10, dto.getPointAdditionReasonCd());
            ps.setString(11, dto.getPurchaseDt());
            ps.setString(12, dto.getPurchaseTm());
            ps.setString(13, dto.getPointCd());
            ps.setString(14, dto.getEndDt());
            ps.setString(15, dto.getBeginningPurchaseDt());
            ps.setString(16, dto.getBeginningPurchaseTm());
            ps.setString(17, dto.getScreenSystemKbn());
            ps.setString(18, dto.getProfileId());
            ps.setString(19, dto.getEndFlg());
            ps.setString(20, String.valueOf(dto.getRecordVerNo()));
            ps.setString(21, String.valueOf(Timestamp.valueOf(sdf.format(cal.getTime()))));
            ps.setString(22, dto.getInsertCd());
            ps.setString(23, String.valueOf(Timestamp.valueOf(sdf.format(cal.getTime()))));
            ps.setString(24, dto.getUpdateCd());

            insertRowCount = ps.executeUpdate();

            return insertRowCount;

        } catch (SQLException se) {
            throw new NaiException(se);
        }
    }

    /**
     * ポイント所有テーブル更新処理<br>
     * <br>
     * ポイント所有テーブルの更新を行う<br>
     * <br>
     * @param PointOwnershipTrnDto
     * @return insertRowCount
     * @throws NaiException
     */
    public int update(PointOwnershipTrnDto dto) throws NaiException {

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        int updateRowCount = NaikaraTalkConstants.RETURN_CD_ERR_UPD; // 登録データ件数

        // ポイント所有テーブルのデータチェック
        if (!checkDto(dto)) {
            return updateRowCount;
        }

        StringBuffer sb = new StringBuffer();

        // データ登録処理
        sb.append("update ");
        sb.append("POINT_OWNERSHIP_TRN ");
        sb.append("set ");
        sb.append(" OWNERSHIP_ID = ? ");
        sb.append(",COMPENSATION_FREE_KBN = ? ");
        sb.append(",STUDENT_ID = ? ");
        sb.append(",EFFECTIVE_STR_DT = ? ");
        sb.append(",EFFECTIVE_END_DT = ? ");
        sb.append(",FEE_KBN = ? ");
        sb.append(",PURCHASE_YEN = ? ");
        sb.append(",PURCHASE_FREE_POINT = ? ");
        sb.append(",BALANCE_POINT = ? ");
        sb.append(",POINT_ADDITION_REASON_CD = ? ");
        sb.append(",PURCHASE_DT = ? ");
        sb.append(",PURCHASE_TM = ? ");
        sb.append(",POINT_CD = ? ");
        sb.append(",END_DT = ? ");
        sb.append(",BEGINNING_PURCHASE_DT = ? ");
        sb.append(",BEGINNING_PURCHASE_TM = ? ");
        sb.append(",SCREEN_SYSTEM_KBN = ? ");
        sb.append(",PROFILE_ID = ? ");
        sb.append(",END_FLG = ? ");
        sb.append(",RECORD_VER_NO = ? ");
        sb.append(",UPDATE_DTTM = ? ");
        sb.append(",UPDATE_CD = ? ");
        sb.append("where ");
        sb.append("OWNERSHIP_ID = ? ");
        sb.append("and ");
        sb.append("COMPENSATION_FREE_KBN = ? ");
        sb.append("and ");
        sb.append("RECORD_VER_NO = ? ");

        try {

            PreparedStatement ps = conn.prepareStatement(sb.toString());

            ps.setString(1, dto.getOwnershipId());
            ps.setString(2, dto.getCompensationFreeKbn());
            ps.setString(3, dto.getStudentId());
            ps.setString(4, dto.getEffectiveStrDt());
            ps.setString(5, dto.getEffectiveEndDt());
            ps.setString(6, dto.getFeeKbn());
            ps.setString(7, String.valueOf(dto.getPurchaseYen()));
            ps.setString(8, String.valueOf(dto.getPurchaseFreePoint()));
            ps.setString(9, String.valueOf(dto.getBalancePoint()));
            ps.setString(10, dto.getPointAdditionReasonCd());
            ps.setString(11, dto.getPurchaseDt());
            ps.setString(12, dto.getPurchaseTm());
            ps.setString(13, dto.getPointCd());
            ps.setString(14, dto.getEndDt());
            ps.setString(15, dto.getBeginningPurchaseDt());
            ps.setString(16, dto.getBeginningPurchaseTm());
            ps.setString(17, dto.getScreenSystemKbn());
            ps.setString(18, dto.getProfileId());
            ps.setString(19, dto.getEndFlg());
            ps.setString(20, String.valueOf(dto.getRecordVerNo() + 1));
            ps.setString(21, String.valueOf(Timestamp.valueOf(sdf.format(cal.getTime()))));
            ps.setString(22, dto.getUpdateCd());
            ps.setString(23, dto.getOwnershipId());
            ps.setString(24, dto.getCompensationFreeKbn());
            ps.setString(25, String.valueOf(dto.getRecordVerNo()));

            updateRowCount = ps.executeUpdate();

            return updateRowCount;

        } catch (SQLException se) {
            throw new NaiException(se);
        }
    }

    /**
     * ポイント所有テーブル更新処理(月謝停止)<br>
     * <br>
     * ポイント所有テーブルの更新を行う(月謝停止)<br>
     * <br>
     * @param PointOwnershipTrnDto
     * @return insertRowCount
     * @throws NaiException
     */
    public int updateMonthly(PointOwnershipTrnDto dto) throws NaiException {

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        int updateRowCount = NaikaraTalkConstants.RETURN_CD_ERR_UPD; // 登録データ件数

        // ポイント所有テーブルのデータチェック
        if (!checkDto(dto)) {
            return updateRowCount;
        }

        StringBuffer sb = new StringBuffer();

        // データ登録処理
        sb.append("update ");
        sb.append("POINT_OWNERSHIP_TRN ");
        sb.append("set ");
        sb.append(" END_DT = ? ");                   // 月謝停止日
        sb.append(",RECORD_VER_NO = ? ");
        sb.append(",UPDATE_DTTM = ? ");
        sb.append(",UPDATE_CD = ? ");
        sb.append("where ");
        sb.append("OWNERSHIP_ID = ? ");

        try {

            PreparedStatement ps = conn.prepareStatement(sb.toString());

            ps.setString(1, dto.getEndDt());
            ps.setString(2, String.valueOf(dto.getRecordVerNo() + 1));
            ps.setString(3, String.valueOf(Timestamp.valueOf(sdf.format(cal.getTime()))));
            ps.setString(4, dto.getUpdateCd());
            ps.setString(5, dto.getOwnershipId());

            updateRowCount = ps.executeUpdate();

            return updateRowCount;

        } catch (SQLException se) {
            throw new NaiException(se);
        }
    }

    /**
     * ポイント所有テーブル更新処理<br>
     * <br>
     * ポイント所有テーブルの更新を行う<br>
     * <br>
     * @param PointOwnershipTrnDto
     * @return deleteRowCount
     * @throws NaiException
     */
    public int delete(String pointOwnerShip, String compensationFreeKbn, int recordVerNo) throws NaiException {

        int deleteRowCount = NaikaraTalkConstants.RETURN_CD_ERR_DEL; // 削除データ件数

        StringBuffer sb = new StringBuffer();

        // データ削除処理
        sb.append("delete ");
        sb.append("from POINT_OWNERSHIP_TRN ");
        sb.append("where ");
        sb.append("OWNERSHIP_ID = ? ");
        sb.append("and ");
        sb.append("COMPENSATION_FREE_KBN = ? ");
        sb.append("and ");
        sb.append("RECORD_VER_NO = ? ");

        try {

            PreparedStatement ps = conn.prepareStatement(sb.toString());

            ps.setString(1, pointOwnerShip);
            ps.setString(2, compensationFreeKbn);
            ps.setString(3, String.valueOf(recordVerNo));

            deleteRowCount = ps.executeUpdate();

            return deleteRowCount;

        } catch (SQLException se) {
            throw new NaiException(se);
        }
    }

    /**
     * ポイント所有テーブル登録、更新データチェック<br>
     * <br>
     * ポイント所有テーブルの登録、更新データをチェックし<br>
     * エラー有の場合は、falseを返す<br>
     * エラー無の場合は、trueを返す<br>
     * <br>
     * @param UserMstDto
     * @return boolean
     */
    private boolean checkDto(PointOwnershipTrnDto dto) throws NaiException {

        // 所有IDのチェック：OwnershipId
        if (dto.getOwnershipId() == null || "".equals(dto.getOwnershipId())) {
            return false;
        }

        // 有償無償区分のチェック：CompensationFreeKbn
        if (dto.getCompensationFreeKbn() == null || "".equals(dto.getCompensationFreeKbn())) {
            return false;
        }

        // 受講者IDのチェック：StudentId
        if (dto.getStudentId() == null || "".equals(dto.getStudentId())) {
            return false;
        }

        // 有効開始日のチェック：EffectiveStrDt
        if (dto.getEffectiveStrDt() == null || "".equals(dto.getEffectiveStrDt())) {
            return false;
        }

        // 有効終了日のチェック：EffectiveEndDt
        if (dto.getEffectiveEndDt() == null || "".equals(dto.getEffectiveEndDt())) {
            return false;
        }

        // 通常月謝区分のチェック：FeeKbn
        if (dto.getFeeKbn() == null || "".equals(dto.getFeeKbn())) {
            return false;
        }

        // 購入金額(税込)のチェック：PurchaseYen
        if (dto.getPurchaseYen() == null || "".equals(dto.getPurchaseYen())) {
            return false;
        }

        // 購入／無償ポイントのチェック：PurchaseFreePoint
        if (dto.getPurchaseFreePoint() == null || "".equals(dto.getPurchaseFreePoint())) {
            return false;
        }

        // ポイント残高のチェック：BalancePoint
        if (dto.getBalancePoint() == null || "".equals(dto.getBalancePoint())) {
            return false;
        }

        // 購入日のチェック：PurchaseDt
        if (dto.getPurchaseDt() == null || "".equals(dto.getPurchaseDt())) {
            return false;
        }

        // 購入時刻のチェック：PurchaseTm
        if (dto.getPurchaseTm() == null || "".equals(dto.getPurchaseTm())) {
            return false;
        }

        // 画面システム作成区分のチェック：ScreenSystemKbn
        if (dto.getScreenSystemKbn() == null || "".equals(dto.getScreenSystemKbn())) {
            return false;
        }

        return true;

    }

    /**
     * ポイント所有テーブルデータ取得<br>
     * <br>
     * ポイント所有テーブルリストを戻り値で返却する<br>
     * <br>
     * @param conditions
     * @param OrderBy
     * @return ArrayList<PointOwnershipTrnDto>
     * @throws NaiException
     */
    public ArrayList<PointOwnershipTrnDto> searchWithOr(ConditionMapper conditions, OrderCondition OrderBy)
            throws NaiException {

        // 戻り値とリターンコードの初期化
        ArrayList<PointOwnershipTrnDto> list = null; // DTOリスト
        PointOwnershipTrnDto dto = null;             // DTO

        ResultSet res = null;

        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT ");
        sb.append("   OWNERSHIP_ID ");
        sb.append("  ,COMPENSATION_FREE_KBN ");
        sb.append("  ,STUDENT_ID ");
        sb.append("  ,EFFECTIVE_STR_DT ");
        sb.append("  ,EFFECTIVE_END_DT ");
        sb.append("  ,FEE_KBN ");
        sb.append("  ,PURCHASE_YEN ");
        sb.append("  ,PURCHASE_FREE_POINT ");
        sb.append("  ,BALANCE_POINT ");
        sb.append("  ,POINT_ADDITION_REASON_CD ");
        sb.append("  ,PURCHASE_DT ");
        sb.append("  ,PURCHASE_TM ");
        sb.append("  ,POINT_CD ");
        sb.append("  ,END_DT ");
        sb.append("  ,BEGINNING_PURCHASE_DT ");
        sb.append("  ,BEGINNING_PURCHASE_TM ");
        sb.append("  ,SCREEN_SYSTEM_KBN ");
        sb.append("  ,PROFILE_ID ");
        sb.append("  ,END_FLG ");
        sb.append("  ,RECORD_VER_NO ");
        sb.append("  ,INSERT_DTTM ");
        sb.append("  ,INSERT_CD ");
        sb.append("  ,UPDATE_DTTM ");
        sb.append("  ,UPDATE_CD ");
        sb.append(" FROM ");
        sb.append("   POINT_OWNERSHIP_TRN ");
        // 抽出条件の設定
        if (!StringUtils.isEmpty(conditions.getConditionString())) {
            sb.append("where ");
            sb.append(conditions.getConditionString());
            sb.append(" and (EFFECTIVE_END_DT is null or EFFECTIVE_END_DT >= ?) ");
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
            ps.setString(i + 1, DateUtil.getSysDate());
            // SQL文を実行
            res = ps.executeQuery();

            list = new ArrayList<PointOwnershipTrnDto>();
            while (res.next()) {

                dto = new PointOwnershipTrnDto();

                dto.setOwnershipId(res.getString("OWNERSHIP_ID"));
                dto.setCompensationFreeKbn(res.getString("COMPENSATION_FREE_KBN"));
                dto.setStudentId(res.getString("STUDENT_ID"));
                dto.setEffectiveStrDt(res.getString("EFFECTIVE_STR_DT"));
                dto.setEffectiveEndDt(res.getString("EFFECTIVE_END_DT"));
                dto.setFeeKbn(res.getString("FEE_KBN"));
                dto.setPurchaseYen(res.getBigDecimal("PURCHASE_YEN"));
                dto.setPurchaseFreePoint(res.getBigDecimal("PURCHASE_FREE_POINT"));
                dto.setBalancePoint(res.getBigDecimal("BALANCE_POINT"));
                dto.setPointAdditionReasonCd(res.getString("POINT_ADDITION_REASON_CD"));
                dto.setPurchaseDt(res.getString("PURCHASE_DT"));
                dto.setPurchaseTm(res.getString("PURCHASE_TM"));
                dto.setPointCd(res.getString("POINT_CD"));
                dto.setEndDt(res.getString("END_DT"));
                dto.setBeginningPurchaseDt(res.getString("BEGINNING_PURCHASE_DT"));
                dto.setBeginningPurchaseTm(res.getString("BEGINNING_PURCHASE_TM"));
                dto.setScreenSystemKbn(res.getString("SCREEN_SYSTEM_KBN"));
                dto.setProfileId(res.getString("PROFILE_ID"));
                dto.setEndFlg(res.getString("END_FLG"));
                dto.setRecordVerNo(res.getInt("RECORD_VER_NO"));
                dto.setInsertDttm(res.getTimestamp("INSERT_DTTM"));
                dto.setInsertCd(res.getString("INSERT_CD"));
                dto.setUpdateDttm(res.getTimestamp("UPDATE_DTTM"));
                dto.setUpdateCd(res.getString("UPDATE_CD"));

                dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);

                list.add(dto);
            }

            if (list.size() < 1) {
                list = new ArrayList<PointOwnershipTrnDto>();
                dto = new PointOwnershipTrnDto();
                dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_NO);
                list.add(dto);
                return list;
            }

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

    /**
     * ポイント所有テーブル削除処理<br>
     * <br>
     * ポイント所有テーブルの削除を行う<br>
     * <br>
     * @param PointOwnershipTrnDto
     * @return deleteRowCount
     * @throws NaiException
     */
    public int delete(PointOwnershipTrnDto dto) throws NaiException {

        // 削除データ件数
        int deleteRowCount = NaikaraTalkConstants.RETURN_CD_ERR_DEL; // 削除データ件数

        // SQL文作成
        StringBuffer sb = new StringBuffer();
        sb.append("delete ");
        sb.append("from POINT_OWNERSHIP_TRN ");
        sb.append("where ");
        sb.append("OWNERSHIP_ID = ? ");
        sb.append("and ");
        sb.append("COMPENSATION_FREE_KBN = ? ");
        sb.append("and ");
        sb.append("RECORD_VER_NO = ? ");

        try {
            PreparedStatement ps = conn.prepareStatement(sb.toString());

            ps.setString(1, dto.getOwnershipId());
            ps.setString(2, dto.getCompensationFreeKbn());
            ps.setString(3, String.valueOf(dto.getRecordVerNo()));

            conn.setAutoCommit(false);
            deleteRowCount = ps.executeUpdate();
            conn.commit();

        } catch (SQLException se) {
            se.printStackTrace();
            throw new NaiException(se);
        }
        return deleteRowCount;
    }

    /**
     * ポイント所有テーブルのポイント残高合計取得<br>
     * <br>
     * ポイント残高合計を戻り値で返却する<br>
     * <br>
     *
     * @param conditions
     * @return BigDecimal:sum(BALANCE_POINT)
     * @throws NaiException
     */
    public BigDecimal getPointBalance(ConditionMapper conditions) throws NaiException {

        // 戻り値の初期化
        BigDecimal pointBalance = new BigDecimal(0);

        ResultSet res = null;

        StringBuffer sb = new StringBuffer();

        // データ件数の取得処理
        sb.append("select count(*) CNT, sum(BALANCE_POINT) POINT_BALANCE ");
        sb.append("from POINT_OWNERSHIP_TRN ");
        // 抽出条件の設定
        if (!StringUtils.isEmpty(conditions.getConditionString())) {
            sb.append("where ");
            sb.append(conditions.getConditionString());
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

            // 実行
            res = ps.executeQuery();

            if (res.next()) {
                if (res.getLong("CNT") != 0) {
                    pointBalance = res.getBigDecimal("POINT_BALANCE");
                }
            }

            return pointBalance;

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

    /**
     * ポイント所有テーブル更新（レッスン予約・取消専用）<br>
     * <br>
     * 解除ポイントをポイント所有テーブルに戻す<br>
     * <br>
     * @param studentId：受講者ID
     * @param ownershipId：所有ID
     * @param compensationFreeKbn：有償無償区分
     * @param usePoint：利用ポイント
     * @param calcSign：加算/減算判定
     * @param updateId：更新者ID
     * @return int：0(更新成功),1(更新失敗)
     * @throws NaiException
     */
    public int pointOwnershipTrnUpdate(String studentId, String ownershipId, String compensationFreeKbn,
            BigDecimal usePoint, int calcSign, String updateId) throws NaiException {

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // 戻り値初期化
        int result = NaikaraTalkConstants.RETURN_CD_DATA_YES;

        // データ更新処理
        StringBuffer sb = new StringBuffer();
        sb.append("update POINT_OWNERSHIP_TRN ");
        if (calcSign == calcSignP) {
            sb.append("set BALANCE_POINT = BALANCE_POINT + ? ");
        } else {
            sb.append("set BALANCE_POINT = BALANCE_POINT - ? ");
        }
        sb.append(",RECORD_VER_NO = RECORD_VER_NO + 1 ");
        sb.append(",UPDATE_DTTM = ? ");
        sb.append(",UPDATE_CD = ? ");
        sb.append("where OWNERSHIP_ID = ? ");
        sb.append("and COMPENSATION_FREE_KBN = ? ");

        try {

            PreparedStatement ps = conn.prepareStatement(sb.toString());

            ps.setString(1, String.valueOf(usePoint));
            ps.setString(2, String.valueOf(Timestamp.valueOf(sdf.format(cal.getTime()))));

            // 2014/06/02 レッスン予約に関する「応相談」対応 Upd Start
            // ps.setString(3, studentId);
            ps.setString(3, updateId);
            // 2014/06/02 レッスン予約に関する「応相談」対応 Upd End

            ps.setString(4, ownershipId);
            ps.setString(5, compensationFreeKbn);

            if (ps.executeUpdate() == 0) {
                result = NaikaraTalkConstants.RETURN_CD_DATA_NO;
            }

        } catch (SQLException se) {
            throw new NaiException(se);
        }
        return result;
    }

    /**
     * ポイント所有テーブルデータ取得<br>
     * <br>
     * ポイント所有テーブルリストを戻り値で返却する<br>
     * <br>
     * @param conditions
     * @param OrderBy
     * @return ArrayList<PointOwnershipTrnDto>
     * @throws NaiException
     */
    public ArrayList<PointOwnershipTrnDto> searchWithGroupBy(ConditionMapper conditions, OrderCondition OrderBy)
            throws NaiException {

        // 戻り値とリターンコードの初期化
        ArrayList<PointOwnershipTrnDto> list = null; // DTOリスト
        PointOwnershipTrnDto dto = null;             // DTO

        ResultSet res = null;

        StringBuffer sb = new StringBuffer();

        // 取得処理
        sb.append(" SELECT ");
        sb.append("   OWNERSHIP_ID ");
        sb.append("  ,COMPENSATION_FREE_KBN ");
        sb.append("  ,STUDENT_ID ");
        sb.append("  ,EFFECTIVE_STR_DT ");
        sb.append("  ,max(EFFECTIVE_END_DT)  EFFECTIVE_END_DT ");
        sb.append("  ,FEE_KBN ");
        sb.append("  ,PURCHASE_YEN ");
        sb.append("  ,PURCHASE_FREE_POINT ");
        sb.append("  ,BALANCE_POINT ");
        sb.append("  ,POINT_ADDITION_REASON_CD ");
        sb.append("  ,PURCHASE_DT ");
        sb.append("  ,PURCHASE_TM ");
        sb.append("  ,POINT_CD ");
        sb.append("  ,END_DT ");
        sb.append("  ,BEGINNING_PURCHASE_DT ");
        sb.append("  ,BEGINNING_PURCHASE_TM ");
        sb.append("  ,SCREEN_SYSTEM_KBN ");
        sb.append("  ,PROFILE_ID ");
        sb.append("  ,END_FLG ");
        sb.append("  ,RECORD_VER_NO ");
        sb.append("  ,INSERT_DTTM ");
        sb.append("  ,INSERT_CD ");
        sb.append("  ,UPDATE_DTTM ");
        sb.append("  ,UPDATE_CD ");
        sb.append(" FROM ");
        sb.append("   POINT_OWNERSHIP_TRN ");
        // 抽出条件の設定
        if (!StringUtils.isEmpty(conditions.getConditionString())) {
            sb.append("where ");
            sb.append(conditions.getConditionString());
            sb.append(" and (EFFECTIVE_END_DT is null or EFFECTIVE_END_DT >= ?) ");
        }
        sb.append(" group by BEGINNING_PURCHASE_DT, BEGINNING_PURCHASE_TM ");

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
            ps.setString(i + 1, DateUtil.getSysDate());
            // SQL文を実行
            res = ps.executeQuery();

            list = new ArrayList<PointOwnershipTrnDto>();
            while (res.next()) {

                dto = new PointOwnershipTrnDto();

                dto.setOwnershipId(res.getString("OWNERSHIP_ID"));
                dto.setCompensationFreeKbn(res.getString("COMPENSATION_FREE_KBN"));
                dto.setStudentId(res.getString("STUDENT_ID"));
                dto.setEffectiveStrDt(res.getString("EFFECTIVE_STR_DT"));
                dto.setEffectiveEndDt(res.getString("EFFECTIVE_END_DT"));
                dto.setFeeKbn(res.getString("FEE_KBN"));
                dto.setPurchaseYen(res.getBigDecimal("PURCHASE_YEN"));
                dto.setPurchaseFreePoint(res.getBigDecimal("PURCHASE_FREE_POINT"));
                dto.setBalancePoint(res.getBigDecimal("BALANCE_POINT"));
                dto.setPointAdditionReasonCd(res.getString("POINT_ADDITION_REASON_CD"));
                dto.setPurchaseDt(res.getString("PURCHASE_DT"));
                dto.setPurchaseTm(res.getString("PURCHASE_TM"));
                dto.setPointCd(res.getString("POINT_CD"));
                dto.setEndDt(res.getString("END_DT"));
                dto.setBeginningPurchaseDt(res.getString("BEGINNING_PURCHASE_DT"));
                dto.setBeginningPurchaseTm(res.getString("BEGINNING_PURCHASE_TM"));
                dto.setScreenSystemKbn(res.getString("SCREEN_SYSTEM_KBN"));
                dto.setProfileId(res.getString("PROFILE_ID"));
                dto.setEndFlg(res.getString("END_FLG"));
                dto.setRecordVerNo(res.getInt("RECORD_VER_NO"));
                dto.setInsertDttm(res.getTimestamp("INSERT_DTTM"));
                dto.setInsertCd(res.getString("INSERT_CD"));
                dto.setUpdateDttm(res.getTimestamp("UPDATE_DTTM"));
                dto.setUpdateCd(res.getString("UPDATE_CD"));

                dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);

                list.add(dto);
            }

            if (list.size() < 1) {
                list = new ArrayList<PointOwnershipTrnDto>();
                dto = new PointOwnershipTrnDto();
                dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_NO);
                list.add(dto);
                return list;
            }

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


    /**
     * ポイント所有テーブルデータ取得<br>
     * <br>
     * ポイント所有テーブルリストを戻り値で返却する<br>
     * <br>
     * @param conditions
     * @param OrderBy
     * @return ArrayList<PointOwnershipTrnDto>
     * @throws NaiException
     */
    public ArrayList<PointOwnershipTrnDto> searchWithGroupBy(PointOwnershipTrnDto conditionsDto, OrderCondition OrderBy)
            throws NaiException {

        // 戻り値とリターンコードの初期化
        ArrayList<PointOwnershipTrnDto> list = null; // DTOリスト
        PointOwnershipTrnDto dto = null;             // DTO

        ResultSet res = null;

        StringBuffer sb = new StringBuffer();

        // 取得処理
        sb.append(" SELECT ");
        sb.append("   POT_1.OWNERSHIP_ID ");
        sb.append("  ,POT_1.COMPENSATION_FREE_KBN ");
        sb.append("  ,POT_1.STUDENT_ID ");
        sb.append("  ,POT_1.EFFECTIVE_STR_DT ");
        sb.append("  ,POT_1.EFFECTIVE_END_DT ");
        sb.append("  ,POT_1.FEE_KBN ");
        sb.append("  ,POT_1.PURCHASE_YEN ");
        sb.append("  ,POT_1.PURCHASE_FREE_POINT ");
        sb.append("  ,POT_1.BALANCE_POINT ");
        sb.append("  ,POT_1.POINT_ADDITION_REASON_CD ");
        sb.append("  ,POT_1.PURCHASE_DT ");
        sb.append("  ,POT_1.PURCHASE_TM ");
        sb.append("  ,POT_1.POINT_CD ");
        sb.append("  ,POT_1.END_DT ");
        sb.append("  ,POT_1.BEGINNING_PURCHASE_DT ");
        sb.append("  ,POT_1.BEGINNING_PURCHASE_TM ");
        sb.append("  ,POT_1.SCREEN_SYSTEM_KBN ");
        sb.append("  ,POT_1.PROFILE_ID ");
        sb.append("  ,POT_1.END_FLG ");
        sb.append("  ,POT_1.RECORD_VER_NO ");
        sb.append("  ,POT_1.INSERT_DTTM ");
        sb.append("  ,POT_1.INSERT_CD ");
        sb.append("  ,POT_1.UPDATE_DTTM ");
        sb.append("  ,POT_1.UPDATE_CD ");
        sb.append(" FROM ");
        sb.append("   POINT_OWNERSHIP_TRN POT_1 ");
        sb.append(" JOIN ");
        sb.append("  (SELECT ");
        sb.append("     MAX(POT_2.EFFECTIVE_END_DT) EFFECTIVE_END_DT ");
        sb.append("     ,POT_2.BEGINNING_PURCHASE_DT ");
        sb.append("     ,POT_2.BEGINNING_PURCHASE_TM ");
        sb.append("   FROM ");
        sb.append("     POINT_OWNERSHIP_TRN POT_2 ");
        sb.append("   WHERE ");
        sb.append("     POT_2.STUDENT_ID= ? ");
        sb.append("     AND POT_2.FEE_KBN= ?");
        sb.append("     AND POT_2.COMPENSATION_FREE_KBN= ?");
        sb.append("     AND ");
        sb.append("       (POT_2.EFFECTIVE_END_DT IS NULL ");
        sb.append("       OR POT_2.EFFECTIVE_END_DT >= ? )");
        sb.append("     GROUP BY");
        sb.append("       POT_2.BEGINNING_PURCHASE_DT");
        sb.append("       ,POT_2.BEGINNING_PURCHASE_TM");
        sb.append("       ) POT_3 ");
        sb.append("   ON ");
        sb.append("    (");
        sb.append("    POT_1.EFFECTIVE_END_DT = POT_3.EFFECTIVE_END_DT");
        sb.append("    AND POT_1.BEGINNING_PURCHASE_DT = POT_3.BEGINNING_PURCHASE_DT");
        sb.append("    AND POT_1.BEGINNING_PURCHASE_TM = POT_3.BEGINNING_PURCHASE_TM");
        sb.append("    )");
        sb.append("   WHERE ");
        sb.append("     POT_1.STUDENT_ID= ? ");
        sb.append("     AND POT_1.FEE_KBN= ?");
        sb.append("     AND POT_1.COMPENSATION_FREE_KBN= ?");
        sb.append("     AND ");
        sb.append("       (POT_1.EFFECTIVE_END_DT IS NULL ");
        sb.append("       OR POT_1.EFFECTIVE_END_DT >= ? )");

        // 並び順の設定
        if (!StringUtils.isEmpty(OrderBy.getOrderString())) {
            sb.append(OrderBy.getOrderString());
        }

        try {

            PreparedStatement ps = conn.prepareStatement(sb.toString());

            ps.setString(1, conditionsDto.getStudentId());
            ps.setString(2, conditionsDto.getFeeKbn());
            ps.setString(3, NaikaraTalkConstants.COMPENSATION_FREE_KBN_YES);
            ps.setString(4, DateUtil.getSysDate());
            ps.setString(5, conditionsDto.getStudentId());
            ps.setString(6, conditionsDto.getFeeKbn());
            ps.setString(7, NaikaraTalkConstants.COMPENSATION_FREE_KBN_YES);
            ps.setString(8, DateUtil.getSysDate());
            // SQL文を実行
            res = ps.executeQuery();

            list = new ArrayList<PointOwnershipTrnDto>();
            while (res.next()) {

                dto = new PointOwnershipTrnDto();

                dto.setOwnershipId(res.getString("POT_1.OWNERSHIP_ID"));
                dto.setCompensationFreeKbn(res.getString("POT_1.COMPENSATION_FREE_KBN"));
                dto.setStudentId(res.getString("POT_1.STUDENT_ID"));
                dto.setEffectiveStrDt(res.getString("POT_1.EFFECTIVE_STR_DT"));
                dto.setEffectiveEndDt(res.getString("POT_1.EFFECTIVE_END_DT"));
                dto.setFeeKbn(res.getString("POT_1.FEE_KBN"));
                dto.setPurchaseYen(res.getBigDecimal("POT_1.PURCHASE_YEN"));
                dto.setPurchaseFreePoint(res.getBigDecimal("POT_1.PURCHASE_FREE_POINT"));
                dto.setBalancePoint(res.getBigDecimal("POT_1.BALANCE_POINT"));
                dto.setPointAdditionReasonCd(res.getString("POT_1.POINT_ADDITION_REASON_CD"));
                dto.setPurchaseDt(res.getString("POT_1.PURCHASE_DT"));
                dto.setPurchaseTm(res.getString("POT_1.PURCHASE_TM"));
                dto.setPointCd(res.getString("POT_1.POINT_CD"));
                dto.setEndDt(res.getString("POT_1.END_DT"));
                dto.setBeginningPurchaseDt(res.getString("POT_1.BEGINNING_PURCHASE_DT"));
                dto.setBeginningPurchaseTm(res.getString("POT_1.BEGINNING_PURCHASE_TM"));
                dto.setScreenSystemKbn(res.getString("POT_1.SCREEN_SYSTEM_KBN"));
                dto.setProfileId(res.getString("POT_1.PROFILE_ID"));
                dto.setEndFlg(res.getString("POT_1.END_FLG"));
                dto.setRecordVerNo(res.getInt("POT_1.RECORD_VER_NO"));
                dto.setInsertDttm(res.getTimestamp("POT_1.INSERT_DTTM"));
                dto.setInsertCd(res.getString("POT_1.INSERT_CD"));
                dto.setUpdateDttm(res.getTimestamp("POT_1.UPDATE_DTTM"));
                dto.setUpdateCd(res.getString("POT_1.UPDATE_CD"));

                dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);

                list.add(dto);
            }

            if (list.size() < 1) {
                list = new ArrayList<PointOwnershipTrnDto>();
                dto = new PointOwnershipTrnDto();
                dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_NO);
                list.add(dto);
                return list;
            }

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


    /**
     * ポイント所有テーブルデータ取得<br>
     * <br>
     * ポイント所有テーブルリストを戻り値で返却する<br>
     * <br>
     * @param conditions
     * @param OrderBy
     * @return ArrayList<PointOwnershipTrnDto>
     * @throws NaiException
     */
    public ArrayList<PointOwnershipTrnDto> searchProfile(ConditionMapper conditions, OrderCondition OrderBy)
            throws NaiException {

        // 戻り値とリターンコードの初期化
        ArrayList<PointOwnershipTrnDto> list = null; // DTOリスト
        PointOwnershipTrnDto dto = null;             // DTO

        ResultSet res = null;

        StringBuffer sb = new StringBuffer();

        // 取得処理
        sb.append("SELECT ");
        sb.append(" STUDENT_ID ");
        sb.append(",PURCHASE_FREE_POINT ");
        sb.append(",BEGINNING_PURCHASE_DT ");
        sb.append(",BEGINNING_PURCHASE_TM ");
        sb.append(",PROFILE_ID ");
        sb.append("FROM POINT_OWNERSHIP_TRN ");
        // 抽出条件の設定
        if (!StringUtils.isEmpty(conditions.getConditionString())) {
            sb.append("where ");
            sb.append(conditions.getConditionString());
        }
        sb.append(" GROUP BY STUDENT_ID, PURCHASE_FREE_POINT,");
        sb.append(" BEGINNING_PURCHASE_DT, BEGINNING_PURCHASE_TM, PROFILE_ID ");

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

            list = new ArrayList<PointOwnershipTrnDto>();
            while (res.next()) {

                dto = new PointOwnershipTrnDto();

                dto.setStudentId(res.getString("STUDENT_ID"));
                dto.setPurchaseFreePoint(res.getBigDecimal("PURCHASE_FREE_POINT"));
                dto.setBeginningPurchaseDt(res.getString("BEGINNING_PURCHASE_DT"));
                dto.setBeginningPurchaseTm(res.getString("BEGINNING_PURCHASE_TM"));
                dto.setProfileId(res.getString("PROFILE_ID"));

                dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);

                list.add(dto);
            }

            if (list.size() < 1) {
                list = new ArrayList<PointOwnershipTrnDto>();
                dto = new PointOwnershipTrnDto();
                dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_NO);
                list.add(dto);
                return list;
            }

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

    /**
     * 月謝停止ポイント所有テーブルデータ取得<br>
     * <br>
     * 月謝停止ポイント所有テーブルリストを戻り値で返却する<br>
     * <br>
     * @param conditions
     * @param OrderBy
     * @return ArrayList<PointOwnershipTrnDto>
     * @throws NaiException
     */
    public ArrayList<PointOwnershipTrnDto> searchCancel(ConditionMapper conditions, OrderCondition OrderBy)
            throws NaiException {

        // 戻り値とリターンコードの初期化
        ArrayList<PointOwnershipTrnDto> list = null; // DTOリスト
        PointOwnershipTrnDto dto = null;             // DTO

        ResultSet res = null;

        StringBuffer sb = new StringBuffer();

        // 取得処理
        sb.append(" SELECT ");
        sb.append("   STUDENT_ID ");
        sb.append("  ,PROFILE_ID ");
        sb.append(" FROM ");
        sb.append("   POINT_OWNERSHIP_TRN ");
        sb.append(" WHERE NOT PROFILE_ID IS NULL ");
        sb.append(" and ");
        if (!StringUtils.isEmpty(conditions.getConditionString())) {
            sb.append(conditions.getConditionString());
        }
        sb.append(" GROUP BY STUDENT_ID, PROFILE_ID ");

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

            list = new ArrayList<PointOwnershipTrnDto>();
            while (res.next()) {

                dto = new PointOwnershipTrnDto();

                dto.setStudentId(res.getString("STUDENT_ID"));
                dto.setProfileId(res.getString("PROFILE_ID"));

                dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);

                list.add(dto);
            }

            if (list.size() < 1) {
                list = new ArrayList<PointOwnershipTrnDto>();
                dto = new PointOwnershipTrnDto();
                dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_NO);
                list.add(dto);
                return list;
            }

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

    /**
     * 翌月月謝データ作成対象ポイント所有テーブルデータ取得<br>
     * <br>
     * ポイント所有テーブルリストを戻り値で返却する<br>
     * <br>
     * @param strDate:処理日-1日
     * @param strAddMonth:処理日+1ヶ月
     * @return ArrayList<PointOwnershipTrnDto>
     * @throws NaiException
     */
    public List<PointOwnershipTrnDto> searchMonthly(String strDate, String strAddMonth) throws NaiException {

        // 戻り値とリターンコードの初期化
        List<PointOwnershipTrnDto> list = new ArrayList<PointOwnershipTrnDto>();
        PointOwnershipTrnDto dto = new PointOwnershipTrnDto();

        ResultSet res = null;

        StringBuffer sb = new StringBuffer();

        // 取得処理
        sb.append(" SELECT ");
        sb.append("   OWNERSHIP_ID ");
        sb.append("  ,COMPENSATION_FREE_KBN ");
        sb.append("  ,STUDENT_ID ");
        sb.append("  ,EFFECTIVE_STR_DT ");
        sb.append("  ,EFFECTIVE_END_DT ");
        sb.append("  ,FEE_KBN ");
        sb.append("  ,PURCHASE_YEN ");
        sb.append("  ,PURCHASE_FREE_POINT ");
        sb.append("  ,BALANCE_POINT ");
        sb.append("  ,POINT_ADDITION_REASON_CD ");
        sb.append("  ,PURCHASE_DT ");
        sb.append("  ,PURCHASE_TM ");
        sb.append("  ,POINT_CD ");
        sb.append("  ,END_DT ");
        sb.append("  ,BEGINNING_PURCHASE_DT ");
        sb.append("  ,BEGINNING_PURCHASE_TM ");
        sb.append("  ,SCREEN_SYSTEM_KBN ");
        sb.append("  ,PROFILE_ID ");
        sb.append("  ,END_FLG ");
        sb.append("  ,RECORD_VER_NO ");
        sb.append("  ,INSERT_DTTM ");
        sb.append("  ,INSERT_CD ");
        sb.append("  ,UPDATE_DTTM ");
        sb.append("  ,UPDATE_CD ");
        sb.append(" FROM ");
        sb.append("   POINT_OWNERSHIP_TRN ");
        sb.append(" where FEE_KBN = ? ");
        sb.append(" and EFFECTIVE_STR_DT = ? ");
        sb.append(" and EFFECTIVE_STR_DT <> BEGINNING_PURCHASE_DT ");
        sb.append(" and ( END_DT is null or END_DT = '' or END_DT >= ? ) ");
        sb.append(" Order by OWNERSHIP_ID, COMPENSATION_FREE_KBN ");

        try {

            PreparedStatement ps = conn.prepareStatement(sb.toString());

            ps.setString(1, NaikaraTalkConstants.FEE_KBN_MONTHLY);
            ps.setString(2, strDate);
            ps.setString(3, strAddMonth);

            // SQL文を実行
            res = ps.executeQuery();

            list = new ArrayList<PointOwnershipTrnDto>();
            while (res.next()) {

                dto = new PointOwnershipTrnDto();

                dto.setOwnershipId(res.getString("OWNERSHIP_ID"));
                dto.setCompensationFreeKbn(res.getString("COMPENSATION_FREE_KBN"));
                dto.setStudentId(res.getString("STUDENT_ID"));
                dto.setEffectiveStrDt(res.getString("EFFECTIVE_STR_DT"));
                dto.setEffectiveEndDt(res.getString("EFFECTIVE_END_DT"));
                dto.setFeeKbn(res.getString("FEE_KBN"));
                dto.setPurchaseYen(res.getBigDecimal("PURCHASE_YEN"));
                dto.setPurchaseFreePoint(res.getBigDecimal("PURCHASE_FREE_POINT"));
                dto.setBalancePoint(res.getBigDecimal("BALANCE_POINT"));
                dto.setPointAdditionReasonCd(res.getString("POINT_ADDITION_REASON_CD"));
                dto.setPurchaseDt(res.getString("PURCHASE_DT"));
                dto.setPurchaseTm(res.getString("PURCHASE_TM"));
                dto.setPointCd(res.getString("POINT_CD"));
                dto.setEndDt(res.getString("END_DT"));
                dto.setBeginningPurchaseDt(res.getString("BEGINNING_PURCHASE_DT"));
                dto.setBeginningPurchaseTm(res.getString("BEGINNING_PURCHASE_TM"));
                dto.setScreenSystemKbn(res.getString("SCREEN_SYSTEM_KBN"));
                dto.setProfileId(res.getString("PROFILE_ID"));
                dto.setEndFlg(res.getString("END_FLG"));
                dto.setRecordVerNo(res.getInt("RECORD_VER_NO"));
                dto.setInsertDttm(res.getTimestamp("INSERT_DTTM"));
                dto.setInsertCd(res.getString("INSERT_CD"));
                dto.setUpdateDttm(res.getTimestamp("UPDATE_DTTM"));
                dto.setUpdateCd(res.getString("UPDATE_CD"));

                dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);

                list.add(dto);
            }

            if (list.size() < 1) {
                list = new ArrayList<PointOwnershipTrnDto>();
                dto = new PointOwnershipTrnDto();
                dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_NO);
                list.add(dto);
                return list;
            }

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

    /**
     * ポイント所有テーブルの月謝停止フラグ更新<br>
     * <br>
     * ポイント所有テーブルの月謝停止フラグ更新を行う<br>
     * <br>
     * @param PointOwnershipTrnDto
     * @return int：0(更新成功),-1(更新失敗)
     * @throws NaiException
     */
    public int updateEndFlg(PointOwnershipTrnDto dto) throws NaiException {

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // 戻り値初期化
        int result = NaikaraTalkConstants.RETURN_CD_DATA_YES;

        // データ更新処理
        StringBuffer sb = new StringBuffer();
        sb.append("update POINT_OWNERSHIP_TRN ");
        sb.append("set END_FLG = ? ");
        sb.append(",RECORD_VER_NO = RECORD_VER_NO + 1 ");
        sb.append(",UPDATE_DTTM = ? ");
        sb.append(",UPDATE_CD = ? ");
        sb.append("where STUDENT_ID = ? ");
        sb.append("and PROFILE_ID = ? ");

        try {

            PreparedStatement ps = conn.prepareStatement(sb.toString());

            ps.setString(1, NaikaraTalkConstants.END_FLG_YES);
            ps.setString(2, String.valueOf(Timestamp.valueOf(sdf.format(cal.getTime()))));
            ps.setString(3, NaikaraTalkConstants.UPDATE_CD);
            ps.setString(4, dto.getStudentId());
            ps.setString(5, dto.getProfileId());

            if (ps.executeUpdate() == 0) {
                result = NaikaraTalkConstants.RETURN_CD_ERR_UPD;
            }

        } catch (SQLException se) {
            throw new NaiException(se);
        }
        return result;
    }

}