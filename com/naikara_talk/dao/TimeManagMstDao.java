package com.naikara_talk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dbutil.QueryCondition;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.TimeManagMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DAOクラス<br>
 * <b>クラス名称　　　:</b>時差管理マスタデータ取得クラス<br>
 * <b>クラス概要　　　:</b>時差管理マスタの取得、登録、更新、削除を行う<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/05/21 TECS 新規作成
 */
public class TimeManagMstDao extends AbstractDao {

    /** 国コード　条件項目　*/
    public static final String COND_COUNTRY_CD = "COUNTRY_CD";

    /** 時差地域NO　条件項目　*/
    public static final String COND_AREA_NO_CD = "AREA_NO_CD";

    /** サマータイム開始日　条件項目　*/
    public static final String COND_SUM_TIME_STR_DT = "SUM_TIME_STR_DT";

    /** サマータイム終了日　条件項目　*/
    public static final String COND_SUM_TIME_END_DT = "SUM_TIME_END_DT";

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public TimeManagMstDao(Connection con) {
        this.conn = con;
    }

    /**
     * 時差管理マスタデータ取得<br>
     * <br>
     * 時差管理マスタリストを戻り値で返却する<br>
     * <br>
     * @param conditions
     * @param orderConditions
     * @return ArrayList<TimeManagMstDto>
     * @throws NaiException
     */
    public ArrayList<TimeManagMstDto> search(ConditionMapper conditions, OrderCondition orderConditions)
            throws NaiException {

        // 戻り値とリターンコードの初期化
        ArrayList<TimeManagMstDto> tmmlist = null; // 時差管理マスタDTOリスト
        TimeManagMstDto tmmDto = null; // 時差管理マスタDTO

        ResultSet res = null;

        StringBuffer sb = new StringBuffer();

        // 時差管理マスタの全項目取得処理
        sb.append(" SELECT ");
        sb.append("  COUNTRY_CD");
        sb.append(" ,AREA_NO_CD");
        sb.append(" ,TIME_MARK_KBN");
        sb.append(" ,TIME_MINUTES");
        sb.append(" ,SUM_TIME_FLG");
        sb.append(" ,SUM_TIME_STR_DT");
        sb.append(" ,SUM_TIME_END_DT");
        sb.append(" ,SUM_TIME_MARK_KBN");
        sb.append(" ,SUM_TIME_MINUTES");
        sb.append(" ,REMARK");
        sb.append(" ,RECORD_VER_NO");
        sb.append(" ,INSERT_DTTM");
        sb.append(" ,INSERT_CD");
        sb.append(" ,UPDATE_DTTM");
        sb.append(" ,UPDATE_CD");
        sb.append(" FROM ");
        sb.append("   TIME_MANAG_MST ");
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

            // コード管理マスタのキャッシュ読み込み
            CodeManagMstCache cache = CodeManagMstCache.getInstance();
            LinkedHashMap<String, CodeManagMstDto> signList = cache.getList(NaikaraTalkConstants.CODE_CATEGORY_SIGN);
            LinkedHashMap<String, CodeManagMstDto> countryNmList = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_COUNTRY);
            LinkedHashMap<String, CodeManagMstDto> areaNmList = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_AREA_NO);

            tmmlist = new ArrayList<TimeManagMstDto>();
            while (res.next()) {

                tmmDto = new TimeManagMstDto();

                tmmDto.setCountryCd(res.getString("COUNTRY_CD"));
                tmmDto.setCountryNm(countryNmList.get(res.getString("COUNTRY_CD")).getManagerNm());
                tmmDto.setAreaNoCd(res.getString("AREA_NO_CD"));
                tmmDto.setAreaNoNm(areaNmList.get(res.getString("AREA_NO_CD")).getManagerNm());
                tmmDto.setTimeMarkKbn(res.getString("TIME_MARK_KBN"));
                tmmDto.setTimeMarkKbnNm(signList.get(res.getString("TIME_MARK_KBN")).getManagerNm());
                tmmDto.setTimeMinutes(res.getInt("TIME_MINUTES"));
                tmmDto.setSumTimeFlg(res.getString("SUM_TIME_FLG"));
                tmmDto.setSumTimeStrDt(res.getString("SUM_TIME_STR_DT"));
                tmmDto.setSumTimeEndDt(res.getString("SUM_TIME_END_DT"));
                tmmDto.setSumTimeMarkKbn(res.getString("SUM_TIME_MARK_KBN"));
                if (StringUtils.isEmpty(res.getString("SUM_TIME_MARK_KBN"))) {
                    tmmDto.setSumTimeMarkKbnNm("");
                } else {
                    tmmDto.setSumTimeMarkKbnNm(signList.get(res.getString("SUM_TIME_MARK_KBN")).getManagerNm());
                }
                tmmDto.setSumTimeMinutes(res.getInt("SUM_TIME_MINUTES"));
                tmmDto.setRemark(res.getString("REMARK"));
                tmmDto.setRecordVerNo(res.getInt("RECORD_VER_NO"));
                tmmDto.setInsertDttm(res.getTimestamp("INSERT_DTTM"));
                tmmDto.setInsertCd(res.getString("INSERT_CD"));
                tmmDto.setUpdateDttm(res.getTimestamp("UPDATE_DTTM"));
                tmmDto.setUpdateCd(res.getString("UPDATE_CD"));
                tmmDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);

                tmmlist.add(tmmDto);
            }

            if (tmmlist.size() < 1) {
                tmmlist = new ArrayList<TimeManagMstDto>();
                tmmDto = new TimeManagMstDto();
                tmmDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_NO);
                tmmlist.add(tmmDto);
                return tmmlist;
            }

            return tmmlist;

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
     * 登録処理。<br>
     * <br>
     * @param TimeManagMstDto
     *            画面のパラメータ
     * @return int 登録レコード数
     * @throws NaiException
     */
    public int insert(TimeManagMstDto dto) throws NaiException {

        // この画面を開いたロールを取得します(この段階に到達した時点でユーザ情報は存在している)
        String userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();

        int insertRowCount = NaikaraTalkConstants.RETURN_CD_ERR_INS; // 追加データ件数

        // SQL文作成
        StringBuffer sb = new StringBuffer();
        sb.append(" insert into TIME_MANAG_MST");
        sb.append(" ( ");
        sb.append("  COUNTRY_CD");
        sb.append(" ,AREA_NO_CD");
        sb.append(" ,TIME_MARK_KBN");
        sb.append(" ,TIME_MINUTES");
        sb.append(" ,SUM_TIME_FLG");
        sb.append(" ,SUM_TIME_STR_DT");
        sb.append(" ,SUM_TIME_END_DT");
        sb.append(" ,SUM_TIME_MARK_KBN");
        sb.append(" ,SUM_TIME_MINUTES");
        sb.append(" ,REMARK");
        sb.append(" ,RECORD_VER_NO");
        sb.append(" ,INSERT_DTTM");
        sb.append(" ,INSERT_CD");
        sb.append(" ,UPDATE_DTTM");
        sb.append(" ,UPDATE_CD");
        sb.append(" ) ");
        sb.append(" VALUES ");
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
        sb.append(" ,sysdate() ");
        sb.append(" ,? ");
        sb.append(" ,sysdate() ");
        sb.append(" ,? ");
        sb.append(" ) ");

        PreparedStatement ps = null;

        try {

            ps = conn.prepareStatement(sb.toString());

            // 国コード
            ps.setString(1, dto.getCountryCd());

            // 時差地域NO
            ps.setString(2, dto.getAreaNoCd());

            // 時間（符号）
            ps.setString(3, dto.getTimeMarkKbn());

            // 時間(分)
            ps.setInt(4, dto.getTimeMinutes());

            // サマータイムフラグ
            ps.setString(5, dto.getSumTimeFlg());

            // サマータイム開始日
            ps.setString(6, dto.getSumTimeStrDt());

            // サマータイム終了日
            ps.setString(7, dto.getSumTimeEndDt());

            // サマータイム(時間)(符号)
            ps.setString(8, dto.getSumTimeMarkKbn());

            // サマータイム(時間)(分)
            ps.setInt(9, dto.getSumTimeMinutes());

            // 備考
            ps.setString(10, dto.getRemark());

            // レコードバージョン番号
            ps.setInt(11, 0);

            // 登録者コード
            ps.setString(12, userId);

            // 更新者コード
            ps.setString(13, userId);

            conn.setAutoCommit(false);

            // SQL文を実行
            insertRowCount = ps.executeUpdate();

            conn.commit();

        } catch (SQLException se) {
            se.printStackTrace();
            throw new NaiException(se);
        }
        return insertRowCount;
    }

    /**
     * 更新処理。<br>
     * <br>
     * @param TimeManagMstDto
     *            画面のパラメータ
     * @return int 更新レコード数
     * @throws NaiException
     */
    public int update(TimeManagMstDto dto) throws NaiException {

        // この画面を開いたロールを取得します(この段階に到達した時点でユーザ情報は存在している)
        String userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();

        int updatedRowCount = NaikaraTalkConstants.RETURN_CD_ERR_UPD; // 更新データ件数

        // 時差管理マスタのデータチェック
        if (!chkDto(dto)) {
            return updatedRowCount;
        }

        // SQL文作成
        StringBuffer sb = new StringBuffer();
        sb.append(" update TIME_MANAG_MST");
        sb.append(" set ");
        sb.append("  TIME_MARK_KBN = ?");
        sb.append(" ,TIME_MINUTES = ?");
        sb.append(" ,SUM_TIME_FLG = ?");
        sb.append(" ,SUM_TIME_STR_DT = ?");
        sb.append(" ,SUM_TIME_END_DT = ?");
        sb.append(" ,SUM_TIME_MARK_KBN = ?");
        sb.append(" ,SUM_TIME_MINUTES = ?");
        sb.append(" ,REMARK = ?");
        sb.append(" ,RECORD_VER_NO = RECORD_VER_NO + 1");
        sb.append(" ,UPDATE_DTTM = sysdate()");
        sb.append(" ,UPDATE_CD = ?");
        sb.append(" where ");
        sb.append(" COUNTRY_CD = ? ");
        sb.append(" and AREA_NO_CD = ? ");
        sb.append(" and RECORD_VER_NO = ?");

        try {

            PreparedStatement ps = conn.prepareStatement(sb.toString());

            int index = 1;

            // 時間（符号）
            ps.setString(index++, dto.getTimeMarkKbn());

            // 時間(分)
            ps.setInt(index++, dto.getTimeMinutes());

            // サマータイムフラグ
            ps.setString(index++, dto.getSumTimeFlg());
            if (StringUtils.equals(NaikaraTalkConstants.SUM_TIME_FLG_YES, dto.getSumTimeFlg())) {

                // サマータイム開始日
                ps.setString(index++, dto.getSumTimeStrDt().replace("/", ""));

                // サマータイム終了日
                ps.setString(index++, dto.getSumTimeEndDt().replace("/", ""));

                // サマータイム(時間)(符号)
                ps.setString(index++, dto.getSumTimeMarkKbn());

                // サマータイム(時間)(分)
                ps.setInt(index++, dto.getSumTimeMinutes());
            } else {

                // サマータイム開始日
                ps.setString(index++, "");

                // サマータイム終了日
                ps.setString(index++, "");

                // サマータイム(時間)(符号)
                ps.setString(index++, "");

                // サマータイム(時間)(分)
                ps.setInt(index++, 0);
            }

            // 備考
            ps.setString(index++, dto.getRemark());

            // 更新者コード
            ps.setString(index++, userId);

            // 国コード
            ps.setString(index++, dto.getCountryCd());

            // 時差地域NO
            ps.setString(index++, dto.getAreaNoCd());

            // レコードバージョン番号
            ps.setInt(index++, dto.getRecordVerNo());

            conn.setAutoCommit(false);

            // SQL文を実行
            updatedRowCount = ps.executeUpdate();

            conn.commit();

        } catch (SQLException se) {

            throw new NaiException(se);
        }
        return updatedRowCount;
    }

    /**
     * 削除処理。<br>
     * <br>
     * @param TimeManagMstDto
     *            画面のパラメータ
     * @return int 削除レコード数
     * @throws NaiException
     */
    public int delete(TimeManagMstDto dto) throws NaiException {

        // 削除データ件数
        int deleteRowCount = NaikaraTalkConstants.RETURN_CD_ERR_DEL; // 更新データ件数

        // SQL文作成
        StringBuffer sb = new StringBuffer();
        sb.append(" delete from TIME_MANAG_MST");
        sb.append(" where ");
        sb.append(" COUNTRY_CD = ? ");
        sb.append(" and AREA_NO_CD = ? ");
        sb.append(" and RECORD_VER_NO = ?");

        try {
            PreparedStatement ps = conn.prepareStatement(sb.toString());

            int index = 1;
            // 国コード
            ps.setString(index++, dto.getCountryCd());

            // 時差地域NO
            ps.setString(index++, dto.getAreaNoCd());

            // レコードバージョン番号
            ps.setInt(index++, dto.getRecordVerNo());

            // SQL文を実行
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
     * 時差管理マスタ更新データチェック<br>
     * <br>
     * 時差管理マスタの更新データをチェックし<br>
     * エラー有の場合は、falseを返す<br>
     * エラー無の場合は、trueを返す<br>
     * <br>
     * @param TimeManagMstDto
     * @return boolean
     */
    private boolean chkDto(TimeManagMstDto dto) {

        // 国コードのチェック
        if (dto.getCountryCd() == null || "".equals(dto.getCountryCd())) {
            return false;
        }

        // 時差地域NOのチェック
        if (dto.getAreaNoCd() == null || "".equals(dto.getAreaNoCd())) {
            return false;
        }

        // 時間（符号）のチェック
        if (dto.getTimeMarkKbn() == null || "".equals(dto.getTimeMarkKbn())) {
            return false;
        }

        // 時間(分)のチェック
        if (String.valueOf(dto.getTimeMinutes()) == null || "".equals(dto.getTimeMinutes())) {
            return false;
        }

        // サマータイムフラグのチェック
        if (dto.getSumTimeFlg() == null || "".equals(dto.getSumTimeFlg())) {
            return false;
        }

        return true;

    }
}
