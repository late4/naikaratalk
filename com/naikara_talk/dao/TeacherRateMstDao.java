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
import com.naikara_talk.dto.TeacherRateMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>講師初期登録ページ。<br>
 * <b>クラス名称       :</b>講師別支払比率マスタデータアクセスクラス。<br>
 * <b>クラス概要       :</b>講師別支払比率マスタの取得、登録、更新を行う。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/01 TECS 新規作成
 *                          2013/11/14 TECS 要望対応 No1022-6(講師支払比率99⇒99.999)
 *                          2014/04/11 TECS 障害対応 insertメソッド：設定値（getWithholdingTaxKbn⇒getPaymentCycleCd）
 *
 */
public class TeacherRateMstDao extends AbstractDao {

    /** 利用者ID */
    public static final String COND_USER_ID = "USER_ID";
    /** 適用期間：開始日 */
    public static final String COND_START_DT = "START_DT";
    /** 適用期間：終了日 */
    public static final String COND_END_DT = "END_DT";

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public TeacherRateMstDao(Connection con) {
        this.conn = con;
    }

    /**
     * 初期検索処理。<br>
     * <br>
     * @param conditions 検索条件
     * @param OrderBy 並び順
     * @return UserMstTeacherMstTeacherCourseMstDto 検索結果
     * @throws NaiException
     */
    public ArrayList<TeacherRateMstDto> searchTeacherRate(ConditionMapper conditions, OrderCondition OrderBy)
            throws NaiException {

        // 戻り値とリターンコードの初期化
        ArrayList<TeacherRateMstDto> list = null; // DTOリスト

        ResultSet res = null;

        // SQL文作成
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT ");
        sb.append(" USER_ID, ");
        sb.append(" START_DT, ");
        sb.append(" END_DT, ");
        sb.append(" PAYMENT_CYCLE_CD, ");
        sb.append(" WITHHOLDING_TAX_KBN ");
        sb.append(" FROM TEACHER_RATE_MST ");
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

            // SQL文を実行
            res = ps.executeQuery();

            // コード管理マスタのキャッシュ読み込み
            CodeManagMstCache cache = CodeManagMstCache.getInstance();
            // 支払サイクル(Payment Cycle)
            LinkedHashMap<String, CodeManagMstDto> paymentCycleList = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_PAYMENT_CYCLE);
            // 源泉有無区分
            LinkedHashMap<String, CodeManagMstDto> withholdingTaxList = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_SOURCE_WHETHER);
            // 支払サイクル(講師用)(Payment Cycle)
            LinkedHashMap<String, CodeManagMstDto> paymentCycleTList = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_PAYMENT_CYCLE_T);
            // 源泉有無区分(講師用)
            LinkedHashMap<String, CodeManagMstDto> withholdingTaxTList = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_SOURCE_WHETHER_T);

            // 戻りdto作成
            list = new ArrayList<TeacherRateMstDto>();
            while (res.next()) {
                TeacherRateMstDto retDto = new TeacherRateMstDto();

                retDto.setUserId(res.getString("USER_ID"));
                retDto.setStartDt(res.getString("START_DT"));
                retDto.setEndDt(res.getString("END_DT"));
                retDto.setPaymentCycleCd(NaikaraStringUtil.unionString2(
                        paymentCycleTList.get(res.getString("PAYMENT_CYCLE_CD")).getManagerNm(),
                        paymentCycleList.get(res.getString("PAYMENT_CYCLE_CD")).getManagerNm()));
                retDto.setWithholdingTaxKbn(NaikaraStringUtil.unionString2(
                        withholdingTaxTList.get(res.getString("WITHHOLDING_TAX_KBN")).getManagerNm(),
                        withholdingTaxList.get(res.getString("WITHHOLDING_TAX_KBN")).getManagerNm()));

                list.add(retDto);
            }

            ps.close();

            // 戻りdto
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
     * 講師別支払比率マスタデータ取得<br>
     * <br>
     * 講師別支払比率マスタリストを戻り値で返却する<br>
     * <br>
     * @param conditions 検索条件
     * @param orderConditions 並び順
     * @return List<TeacherRateMstDto> 検索結果
     * @throws NaiException
     */
    public List<TeacherRateMstDto> searchTeacherRateMst(ConditionMapper conditions, OrderCondition orderConditions)
            throws NaiException {

        // 戻り値とリターンコードの初期化
        List<TeacherRateMstDto> list = null; // DTOリスト

        // データソース取得
        ResultSet res = null;

        // SQL文作成
        StringBuffer sb = new StringBuffer();
        sb.append("select ");
        sb.append("USER_ID ");
        sb.append(",START_DT ");
        sb.append(",END_DT ");
        sb.append(",PAYMENT_RATE ");
        sb.append(",PAYMENT_CYCLE_CD ");
        sb.append(",WITHHOLDING_TAX_KBN ");
        sb.append(",RECORD_VER_NO ");
        sb.append(",INSERT_DTTM ");
        sb.append(",INSERT_CD ");
        sb.append(",UPDATE_DTTM ");
        sb.append(",UPDATE_CD ");
        sb.append("from ");
        sb.append("TEACHER_RATE_MST ");
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

            // SQL文を実行
            res = ps.executeQuery();
            // コード管理マスタのキャッシュ読み込み
            CodeManagMstCache cache = CodeManagMstCache.getInstance();
            // 支払サイクル(Payment Cycle)
            LinkedHashMap<String, CodeManagMstDto> paymentCycleList = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_PAYMENT_CYCLE);
            // 源泉有無区分
            LinkedHashMap<String, CodeManagMstDto> withholdingTaxList = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_SOURCE_WHETHER);

            // 戻りdto作成
            list = new ArrayList<TeacherRateMstDto>();
            while (res.next()) {
                TeacherRateMstDto retDto = new TeacherRateMstDto();

                retDto.setUserId(res.getString("USER_ID"));
                retDto.setStartDt(res.getString("START_DT"));
                retDto.setEndDt(res.getString("END_DT"));

                //No1022-6 講師支払比率99⇒99.999へ変更対応-Start
                //retDto.setPaymentRate(res.getInt("PAYMENT_RATE"));
                retDto.setPaymentRate(res.getBigDecimal("PAYMENT_RATE"));
                //No1022-6 講師支払比率99⇒99.999へ変更対応-End

                retDto.setPaymentCycleCd(res.getString("PAYMENT_CYCLE_CD"));
                retDto.setWithholdingTaxKbn(res.getString("WITHHOLDING_TAX_KBN"));
                retDto.setPaymentCycleCdNm(paymentCycleList.get(res.getString("PAYMENT_CYCLE_CD")).getManagerNm());
                retDto.setWithholdingTaxKbnNm(withholdingTaxList.get(res.getString("WITHHOLDING_TAX_KBN"))
                        .getManagerNm());
                retDto.setRecordVerNo(res.getInt("RECORD_VER_NO"));
                retDto.setInsertDttm(res.getTimestamp("INSERT_DTTM"));
                retDto.setInsertCd(res.getString("INSERT_CD"));
                retDto.setUpdateDttm(res.getTimestamp("UPDATE_DTTM"));
                retDto.setUpdateCd(res.getString("UPDATE_CD"));

                list.add(retDto);
            }

            if (list.size() < 1) {
                TeacherRateMstDto retDto = new TeacherRateMstDto();
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
     * @param TeacherRateMstDto 画面のパラメータ<br>
     * @return なし<br>
     * @exception NaiException
     */
    public int insert(TeacherRateMstDto dto) throws NaiException {

        // この画面を開いたロールを取得します(この段階に到達した時点でユーザ情報は存在している)
        String userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();

        int insertRowCount = NaikaraTalkConstants.RETURN_CD_ERR_INS; // 追加データ件数

        StringBuffer sb = new StringBuffer();

        // SQL文作成
        sb.append(" insert into TEACHER_RATE_MST");
        sb.append(" ( ");
        sb.append(" USER_ID ");
        sb.append(" ,START_DT ");
        sb.append(" ,END_DT ");
        sb.append(" ,PAYMENT_RATE ");
        sb.append(" ,PAYMENT_CYCLE_CD ");
        sb.append(" ,WITHHOLDING_TAX_KBN ");
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
        sb.append(" ,0 ");
        sb.append(" ,sysdate() ");
        sb.append(" ,? ");
        sb.append(" ,sysdate() ");
        sb.append(" ,?) ");

        try {

            PreparedStatement ps = conn.prepareStatement(sb.toString());

            // 講師ID (利用者ID)
            ps.setString(1, dto.getUserId());
            // 適用期間：開始日
            ps.setString(2, NaikaraStringUtil.converToYYYYMMDD(dto.getStartDt()));
            // 適用期間：終了日
            ps.setString(3, NaikaraStringUtil.converToYYYYMMDD(dto.getEndDt()));

            //No1022-6 講師支払比率99⇒99.999へ変更対応-Start
            // 支払比率
            //ps.setInt(4, dto.getPaymentRate());
            ps.setBigDecimal(4, dto.getPaymentRate());
            //No1022-6 講師支払比率99⇒99.999へ変更対応-End


            //2014.04.11 間違い修正-Start
            // 支払サイクルコード
            ////ps.setString(5, dto.getWithholdingTaxKbn());
            ps.setString(5, dto.getPaymentCycleCd());
            //2014.04.11 間違い修正-End


            // 源泉税有無区分
            ps.setString(6, dto.getWithholdingTaxKbn());
            // 登録者コード
            ps.setString(7, userId);
            // 更新者コード
            ps.setString(8, userId);

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
     * @param TeacherRateMstDto 画面のパラメータ<br>
     * @return int 更新レコード数<br>
     * @exception NaiException
     */
    public int update(TeacherRateMstDto dto) throws NaiException {

        // この画面を開いたロールを取得します(この段階に到達した時点でユーザ情報は存在している)
        String userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();

        int updatedRowCount = NaikaraTalkConstants.RETURN_CD_ERR_UPD; // 更新データ件数
        // ポイント管理マスタのデータチェック
        if (!chkDto(dto)) {
            return updatedRowCount;
        }

        StringBuffer sb = new StringBuffer();

        // SQL文作成
        sb.append(" update TEACHER_RATE_MST");
        sb.append(" set ");
        sb.append(" END_DT = ?");
        sb.append(" ,PAYMENT_RATE = ?");
        sb.append(" ,PAYMENT_CYCLE_CD = ?");
        sb.append(" ,WITHHOLDING_TAX_KBN = ?");
        sb.append(" ,RECORD_VER_NO = RECORD_VER_NO + 1");
        sb.append(" ,UPDATE_DTTM = sysdate()");
        sb.append(" ,UPDATE_CD = ?");
        sb.append(" where ");
        sb.append(" USER_ID = ? ");
        sb.append(" and START_DT = ?");

        try {

            PreparedStatement ps = conn.prepareStatement(sb.toString());

            // 適用期間：終了日
            ps.setString(1, NaikaraStringUtil.converToYYYYMMDD(dto.getEndDt()));

            //No1022-6 講師支払比率99⇒99.999へ変更対応-End
            // 支払比率
            //ps.setInt(2, dto.getPaymentRate());
            ps.setBigDecimal(2, dto.getPaymentRate());
            //No1022-6 講師支払比率99⇒99.999へ変更対応-End

            // 支払サイクルコード
            ps.setString(3, dto.getPaymentCycleCd());
            // 源泉税有無区分
            ps.setString(4, dto.getWithholdingTaxKbn());
            // 更新者コード
            ps.setString(5, userId);
            // 講師ID (利用者ID)
            ps.setString(6, dto.getUserId());
            // 適用期間：開始日
            ps.setString(7, NaikaraStringUtil.converToYYYYMMDD(dto.getStartDt()));

            // SQL文を実行
            updatedRowCount = ps.executeUpdate();

            return updatedRowCount;

        } catch (SQLException se) {
            throw new NaiException(se);
        }
    }

    /**
     * 削除処理。<br>
     * <br>
     * 削除処理。<br>
     * <br>
     * @param ConditionMapper 画面のパラメータ<br>
     * @return なし<br>
     * @exception NaiException
     */
    public int delete(ConditionMapper conditions) throws NaiException {
        int deleteRowCount = NaikaraTalkConstants.RETURN_CD_ERR_DEL;
        StringBuilder sb = new StringBuilder();
        sb.append(" DELETE ");
        sb.append(" FROM ");
        sb.append("   TEACHER_RATE_MST ");

        if (!StringUtils.isEmpty(conditions.getConditionString())) {
            sb.append("where ");
            sb.append(conditions.getConditionString());
        }

        try {

            PreparedStatement ps = conn.prepareStatement(sb.toString());

            // パラメタの設定
            for (int i = 0; i < conditions.getConditions().size(); i++) {
                ps.setString(i + 1, conditions.getConditions().get(i).getValue());
            }

            deleteRowCount = ps.executeUpdate();

            return deleteRowCount;

        } catch (SQLException se) {
            se.printStackTrace();
            throw new NaiException(se);
        }
    }

    /**
     * 講師別支払比率マスタ更新データチェック<br>
     * <br>
     * 講師別支払比率マスタの更新データをチェックし<br>
     * エラー有の場合は、falseを返す<br>
     * エラー無の場合は、trueを返す<br>
     * <br>
     * @param TeacherRateMstDto
     * @return boolean
     */
    private boolean chkDto(TeacherRateMstDto dto) throws NaiException {

        // 講師ID (利用者ID)
        if (StringUtils.isEmpty(dto.getUserId())) {
            return false;
        }

        // 適用期間：開始日
        if (StringUtils.isEmpty(dto.getStartDt())) {
            return false;
        }

        // 適用期間：終了日
        if (StringUtils.isEmpty(dto.getEndDt())) {
            return false;
        }

        // 支払サイクルコード
        if (StringUtils.isEmpty(dto.getPaymentCycleCd())) {
            return false;
        }

        // 源泉税有無区分
        if (StringUtils.isEmpty(dto.getWithholdingTaxKbn())) {
            return false;
        }

        return true;

    }
}
