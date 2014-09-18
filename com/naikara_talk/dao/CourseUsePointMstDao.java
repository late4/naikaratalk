package com.naikara_talk.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dbutil.QueryCondition;
import com.naikara_talk.dto.CourseUsePointMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DAOクラス<br>
 * <b>クラス名称　　　:</b>コース別利用ポイントマスタデータ取得クラス<br>
 * <b>クラス概要　　　:</b>コース別利用ポイントマスタデータ取得DAO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/06/10 TECS 新規作成
 */
public class CourseUsePointMstDao extends AbstractDao {

    /** コースコード　条件項目　*/
    public static final String COND_COURSE_CD = "COURSE_CD";
    /** 利用ポイント開始日　条件項目　*/
    public static final String COND_USE_POINT_STR_DT = "USE_POINT_STR_DT";
    /** 利用ポイント終了日　条件項目　*/
    public static final String COND_USE_POINT_END_DT = "USE_POINT_END_DT";

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public CourseUsePointMstDao(Connection con) {
        this.conn = con;
    }

    /**
     * コース別利用ポイントマスタデータ取得<br>
     * <br>
     * コース別利用ポイントマスタリストを戻り値で返却する<br>
     * <br>
     * @param conditions
     * @param OrderBy
     * @return ArrayList<CourseUsePointMstDto>
     * @throws NaiException
     */
    public ArrayList<CourseUsePointMstDto> search(ConditionMapper conditions, OrderCondition OrderBy)
            throws NaiException {

        // 戻り値とリターンコードの初期化
        ArrayList<CourseUsePointMstDto> list = null; // DTOリスト
        CourseUsePointMstDto dto = null; // DTO

        ResultSet res = null;

        StringBuffer sb = new StringBuffer();

        // 取得処理
        sb.append(" SELECT ");
        sb.append("  COURSE_CD");
        sb.append(" ,USE_POINT_STR_DT");
        sb.append(" ,USE_POINT_END_DT");
        sb.append(" ,USE_POINT");
        sb.append(" ,INFORMATION");
        sb.append(" ,RECORD_VER_NO");
        sb.append(" ,INSERT_DTTM");
        sb.append(" ,INSERT_CD");
        sb.append(" ,UPDATE_DTTM");
        sb.append(" ,UPDATE_CD");
        sb.append(" FROM ");
        sb.append("   COURSE_USE_POINT_MST ");
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

            list = new ArrayList<CourseUsePointMstDto>();
            while (res.next()) {

                dto = new CourseUsePointMstDto();

                dto.setCourseCd(res.getString("COURSE_CD"));
                dto.setUsePointStrDt(res.getString("USE_POINT_STR_DT"));
                dto.setUsePointEndDt(res.getString("USE_POINT_END_DT"));
                dto.setUsePoint(res.getBigDecimal("USE_POINT"));
                dto.setInformation(res.getString("INFORMATION"));
                dto.setRecordVerNo(res.getInt("RECORD_VER_NO"));
                dto.setInsertDttm(res.getTimestamp("INSERT_DTTM"));
                dto.setInsertCd(res.getString("INSERT_CD"));
                dto.setUpdateDttm(res.getTimestamp("UPDATE_DTTM"));
                dto.setUpdateCd(res.getString("UPDATE_CD"));

                dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);

                list.add(dto);
            }

            if (list.size() < 1) {
                list = new ArrayList<CourseUsePointMstDto>();
                dto = new CourseUsePointMstDto();
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
     * 登録処理。<br>
     * <br>
     * 登録処理。<br>
     * <br>
     * @param CourseMstDto 画面のパラメータ<br>
     * @return なし<br>
     * @throws NaiException
     */
    public int insert(CourseUsePointMstDto dto) throws NaiException {

        // この画面を開いたロールを取得します(この段階に到達した時点でユーザ情報は存在している)
        String userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();

        int insertRowCount = NaikaraTalkConstants.RETURN_CD_ERR_INS; // 追加データ件数

        // SQL文作成
        StringBuffer sb = new StringBuffer();
        sb.append(" insert into COURSE_USE_POINT_MST");
        sb.append(" ( ");
        sb.append("  COURSE_CD");
        sb.append(" ,USE_POINT_STR_DT");
        sb.append(" ,USE_POINT_END_DT");
        sb.append(" ,USE_POINT");
        sb.append(" ,INFORMATION");
        sb.append(" ,RECORD_VER_NO");
        sb.append(" ,INSERT_DTTM");
        sb.append(" ,INSERT_CD");
        sb.append(" ,UPDATE_DTTM");
        sb.append(" ,UPDATE_CD)");
        sb.append(" values ");
        sb.append(" ( ");
        sb.append("  ? ");
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
            ps.setString(1, dto.getCourseCd());
            ps.setString(2, NaikaraStringUtil.converToYYYYMMDD(dto.getUsePointStrDt()));
            ps.setString(3, NaikaraStringUtil.converToYYYYMMDD(dto.getUsePointEndDt()));
            ps.setBigDecimal(4, dto.getUsePoint());
            ps.setString(5, dto.getInformation());
            ps.setString(6, userId);
            ps.setString(7, userId);

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
     * 更新処理。<br>
     * <br>
     * 更新処理。<br>
     * <br>
     * @param PointManagMstDto 画面のパラメータ<br>
     * @return int 更新レコード数<br>
     * @exception NaiException
     */
    public int update(CourseUsePointMstDto dto) throws NaiException {

        // この画面を開いたロールを取得します(この段階に到達した時点でユーザ情報は存在している)
        String userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();

        int updatedRowCount = NaikaraTalkConstants.RETURN_CD_ERR_UPD; // 更新データ件数
        // ポイント管理マスタのデータチェック
        if (!chkDto(dto)) {
            return updatedRowCount;
        }

        StringBuffer sb = new StringBuffer();

        // SQL文作成
        sb.append(" update COURSE_USE_POINT_MST");
        sb.append(" set ");
        sb.append(" USE_POINT_END_DT = ?");
        sb.append(" ,USE_POINT = ?");
        sb.append(" ,INFORMATION = ?");
        sb.append(" ,RECORD_VER_NO = RECORD_VER_NO + 1");
        sb.append(" ,UPDATE_DTTM = sysdate()");
        sb.append(" ,UPDATE_CD = ?");
        sb.append(" where ");
        sb.append(" COURSE_CD = ? ");
        sb.append(" and USE_POINT_STR_DT = ?");

        try {

            PreparedStatement ps = conn.prepareStatement(sb.toString());

            ps.setString(1, NaikaraStringUtil.converToYYYYMMDD(dto.getUsePointEndDt()));
            ps.setBigDecimal(2, dto.getUsePoint());
            ps.setString(3, dto.getInformation());
            ps.setString(4, userId);
            ps.setString(5, dto.getCourseCd());
            ps.setString(6, NaikaraStringUtil.converToYYYYMMDD(dto.getUsePointStrDt()));

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
        sb.append("   COURSE_USE_POINT_MST ");

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
     * コース別利用ポイントマスタ更新データチェック<br>
     * <br>
     * コース別利用ポイントマスタの更新データをチェックし<br>
     * エラー有の場合は、falseを返す<br>
     * エラー無の場合は、trueを返す<br>
     * <br>
     * @param CourseUsePointMstDto
     * @return boolean
     */
    private boolean chkDto(CourseUsePointMstDto dto) throws NaiException {

        // コースコード
        if (StringUtils.isEmpty(dto.getCourseCd())) {
            return false;
        }

        // 利用ポイント開始日
        if (StringUtils.isEmpty(dto.getUsePointStrDt())) {
            return false;
        }

        // 利用ポイント終了日
        if (StringUtils.isEmpty(dto.getUsePointEndDt())) {
            return false;
        }

        return true;

    }

}
