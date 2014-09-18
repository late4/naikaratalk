package com.naikara_talk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dbutil.QueryCondition;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.PointManagMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DAOクラス<br>
 * <b>クラス名称　　　:</b>ポイント管理マスタデータアクセスクラス<br>
 * <b>クラス概要　　　:</b>ポイント管理マスタの取得、登録、更新を行う<br>
 * <br>
 * <b>著作権　　　　　:</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/06/27 TECS 新規作成
 */
public class PointManagMstDao extends AbstractDao {

    /** ポイントコード 条件項目 */
    public static final String COND_POINT_CD = "POINT_CD";
    /** 通常月謝区分 条件項目 */
    public static final String COND_FEE_KBN = "FEE_KBN";
    /** 提供開始日 条件項目 */
    public static final String COND_USE_STR_DT = "USE_STR_DT";
    /** 提供終了日 条件項目 */
    public static final String COND_USE_END_DT = "USE_END_DT";
    /** 金額 　並び順　*/
    public static final String COND_MONEY_YEN = "MONEY_YEN";
    /** 有効ポイント期限　並び順　*/
    public static final String COND_PAYMENT_POINT_TIM = "PAYMENT_POINT_TIM";

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public PointManagMstDao(Connection con) {
        this.conn = con;
    }

    /**
     * ポイント管理マスタデータ取得<br>
     * <br>
     * ポイント管理マスタリストを戻り値で返却する<br>
     * <br>
     * @param conditions
     * @param orderConditions
     * @return List<PointManagMstDto>
     * @throws NaiException
     */
    public List<PointManagMstDto> search(ConditionMapper conditions, OrderCondition orderConditions)
            throws NaiException {

        ResultSet res = null;

        StringBuffer sb = new StringBuffer();

        // ポイント管理マスタの全項目取得
        sb.append("select");
        sb.append(" POINT_CD ");
        sb.append(",FEE_KBN ");
        sb.append(",MONEY_YEN ");
        sb.append(",PAYMENT_POINT ");
        sb.append(",PAYMENT_POINT_TIM ");
        sb.append(",FREE_POINT ");
        sb.append(",FREE_POINT_TIM ");
        sb.append(",USE_STR_DT ");
        sb.append(",USE_END_DT ");
        sb.append(",REMARKS ");
        sb.append(",RECORD_VER_NO ");
        sb.append(",INSERT_DTTM ");
        sb.append(",INSERT_CD ");
        sb.append(",UPDATE_DTTM ");
        sb.append(",UPDATE_CD ");
        sb.append("from ");
        sb.append("POINT_MANAG_MST ");

        if (!StringUtils.isEmpty(conditions.getConditionString())) {
            sb.append("where ");
            sb.append(conditions.getConditionString());
        }
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

            res = ps.executeQuery();

            // コード管理マスタのキャッシュ読み込み
            CodeManagMstCache cache = CodeManagMstCache.getInstance();
            LinkedHashMap<String, CodeManagMstDto> feeKbnList = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_FEE_KBN);

            ArrayList<PointManagMstDto> list = new ArrayList<PointManagMstDto>();

            while (res.next()) {

                PointManagMstDto retDto = new PointManagMstDto();

                retDto.setPointCd(res.getString("POINT_CD"));
                retDto.setFeeKbn(res.getString("FEE_KBN"));
                retDto.setFeeKbnNm(feeKbnList.get(res.getString("FEE_KBN")).getManagerNm()); // 通常月謝区分名
                retDto.setMoneyYen(res.getBigDecimal("MONEY_YEN"));
                retDto.setPaymentPoint(res.getBigDecimal("PAYMENT_POINT"));
                retDto.setPaymentPointTim(res.getInt("PAYMENT_POINT_TIM"));
                retDto.setFreePoint(res.getBigDecimal("FREE_POINT"));
                retDto.setFreePointTim(res.getInt("FREE_POINT_TIM"));
                retDto.setUseStrDt(res.getString("USE_STR_DT"));
                retDto.setUseEndDt(res.getString("USE_END_DT"));
                retDto.setRemarks(res.getString("REMARKS"));
                retDto.setRecordVerNo(res.getInt("RECORD_VER_NO"));
                retDto.setInsertDttm(res.getTimestamp("INSERT_DTTM"));
                retDto.setInsertCd(res.getString("INSERT_CD"));
                retDto.setUpdateDttm(res.getTimestamp("UPDATE_DTTM"));
                retDto.setUpdateCd(res.getString("UPDATE_CD"));

                retDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);

                list.add(retDto);
            }

            if (list.size() < 1) {
                PointManagMstDto retDto = new PointManagMstDto();
                retDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_NO);
                list.add(retDto);
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
     * 登録処理。<br>
     * <br>
     * 登録処理。<br>
     * <br>
     * @param PointManagMstDto 画面のパラメータ<br>
     * @return なし<br>
     * @exception NaiException
     */
    public int insert(PointManagMstDto dto) throws NaiException {

        // この画面を開いたロールを取得します(この段階に到達した時点でユーザ情報は存在している)
        String userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();

        int insertRowCount = NaikaraTalkConstants.RETURN_CD_ERR_INS; // 追加データ件数

        StringBuffer sb = new StringBuffer();

        // SQL文作成
        sb.append(" insert into POINT_MANAG_MST");
        sb.append(" ( ");
        sb.append(" POINT_CD ");
        sb.append(" ,FEE_KBN ");
        sb.append(" ,MONEY_YEN ");
        sb.append(" ,PAYMENT_POINT ");
        sb.append(" ,PAYMENT_POINT_TIM ");
        sb.append(" ,FREE_POINT ");
        sb.append(" ,FREE_POINT_TIM ");
        sb.append(" ,USE_STR_DT ");
        sb.append(" ,USE_END_DT ");
        sb.append(" ,REMARKS ");
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
        sb.append(" ,0 ");
        sb.append(" ,sysdate() ");
        sb.append(" ,? ");
        sb.append(" ,sysdate() ");
        sb.append(" ,?) ");

        try {

            PreparedStatement ps = conn.prepareStatement(sb.toString());

            // ポイントコード
            ps.setString(1, dto.getPointCd());
            // 通常月謝区分
            ps.setString(2, dto.getFeeKbn());
            // 金額(税込)
            ps.setBigDecimal(3, dto.getMoneyYen());
            // 有償ポイント
            ps.setBigDecimal(4, dto.getPaymentPoint());
            // 有償ポイント期限
            ps.setInt(5, dto.getPaymentPointTim());
            // 無償ポイント
            ps.setBigDecimal(6, dto.getFreePoint());
            // 無償ポイント期限
            ps.setInt(7, dto.getFreePointTim());
            // 提供開始日
            ps.setString(8, NaikaraStringUtil.converToYYYYMMDD(dto.getUseStrDt()));
            // 提供終了日
            ps.setString(9, NaikaraStringUtil.converToYYYYMMDD(dto.getUseEndDt()));
            // 備考
            ps.setString(10, dto.getRemarks());
            // 登録者コード
            ps.setString(11, userId);
            // 更新者コード
            ps.setString(12, userId);

            // SQL文を実行
            insertRowCount = ps.executeUpdate();

            return insertRowCount;

        } catch (SQLException se) {
            throw new NaiException(se);
        }
    }

    /**
     * 更新処理。<br>
     * <br>
     * 更新処理。<br>
     * <br>
     * @param PointManagMstDto 画面のパラメータ<br>
     * @return int 更新レコード数<br>
     * @exception NaiException
     */
    public int update(PointManagMstDto dto) throws NaiException {

        // この画面を開いたロールを取得します(この段階に到達した時点でユーザ情報は存在している)
        String userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();

        int updatedRowCount = NaikaraTalkConstants.RETURN_CD_ERR_UPD; // 更新データ件数
        // ポイント管理マスタのデータチェック
        if (!chkDto(dto)) {
            return updatedRowCount;
        }

        StringBuffer sb = new StringBuffer();

        // SQL文作成
        sb.append(" update POINT_MANAG_MST");
        sb.append(" set ");
        sb.append(" FEE_KBN = ?");
        sb.append(" ,MONEY_YEN = ?");
        sb.append(" ,PAYMENT_POINT = ?");
        sb.append(" ,PAYMENT_POINT_TIM = ?");
        sb.append(" ,FREE_POINT = ?");
        sb.append(" ,FREE_POINT_TIM = ?");
        sb.append(" ,USE_STR_DT = ?");
        sb.append(" ,USE_END_DT = ?");
        sb.append(" ,REMARKS = ?");
        sb.append(" ,RECORD_VER_NO = RECORD_VER_NO + 1");
        sb.append(" ,UPDATE_DTTM = sysdate()");
        sb.append(" ,UPDATE_CD = ?");
        sb.append(" where ");
        sb.append(" POINT_CD = ? ");
        sb.append(" and RECORD_VER_NO = ?");

        try {

            PreparedStatement ps = conn.prepareStatement(sb.toString());
            // 通常月謝区分
            ps.setString(1, dto.getFeeKbn());
            // 金額(税込)
            ps.setBigDecimal(2, dto.getMoneyYen());
            // 有償ポイント
            ps.setBigDecimal(3, dto.getPaymentPoint());
            // 有償ポイント期限
            ps.setInt(4, dto.getPaymentPointTim());
            // 無償ポイント
            ps.setBigDecimal(5, dto.getFreePoint());
            // 無償ポイント期限
            ps.setInt(6, dto.getFreePointTim());
            // 提供開始日
            ps.setString(7, NaikaraStringUtil.converToYYYYMMDD(dto.getUseStrDt()));
            // 提供終了日
            ps.setString(8, NaikaraStringUtil.converToYYYYMMDD(dto.getUseEndDt()));
            // 備考
            ps.setString(9, dto.getRemarks());
            // 更新者コード
            ps.setString(10, userId);
            // ポイントコード
            ps.setString(11, dto.getPointCd());
            // レコードバージョン番号
            ps.setInt(12, dto.getRecordVerNo());

            // SQL文を実行
            updatedRowCount = ps.executeUpdate();

            return updatedRowCount;

        } catch (SQLException se) {
            throw new NaiException(se);
        }
    }

    /**
     * ポイント管理マスタ更新データチェック<br>
     * <br>
     * ポイント管理マスタの更新データをチェックし<br>
     * エラー有の場合は、falseを返す<br>
     * エラー無の場合は、trueを返す<br>
     * <br>
     * @param UserMstDto
     * @return boolean
     */
    private boolean chkDto(PointManagMstDto dto) throws NaiException {

        // ポイントコードのチェック
        if (StringUtils.isEmpty(dto.getPointCd())) {
            return false;
        }

        // 通常月謝区分のチェック
        if (StringUtils.isEmpty(dto.getFeeKbn())) {
            return false;
        }

        // 提供開始日のチェック
        if (StringUtils.isEmpty(dto.getUseStrDt())) {
            return false;
        }

        // 提供終了日のチェック
        if (StringUtils.isEmpty(dto.getUseEndDt())) {
            return false;
        }

        return true;

    }
}
