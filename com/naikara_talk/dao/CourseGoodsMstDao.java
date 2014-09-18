package com.naikara_talk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dbutil.QueryCondition;
import com.naikara_talk.dto.CourseGoodsMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DAOクラス<br>
 * <b>クラス名称　　　:</b>コース別商品マスタデータ取得クラス<br>
 * <b>クラス概要　　　:</b>コース別商品マスタデータ取得DAO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/06/10 TECS 新規作成
 */
public class CourseGoodsMstDao extends AbstractDao {

    /** コースコード 条件項目 */
    public static final String COND_COURSE_CD = "COURSE_CD";

    /** 該当商品コード 条件項目 */
    public static final String COND_GOODS_CD = "GOODS_CD";

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public CourseGoodsMstDao(Connection con) {
        this.conn = con;
    }

    /**
     * コース別商品マスタデータ取得<br>
     * <br>
     * コース別商品マスタリストを戻り値で返却する<br>
     * <br>
     * @param conditions
     * @param OrderBy
     * @return ArrayList<CourseGoodsMstDto>
     * @throws NaiException
     */
    public ArrayList<CourseGoodsMstDto> search(ConditionMapper conditions, OrderCondition OrderBy) throws NaiException {

        // 戻り値とリターンコードの初期化
        ArrayList<CourseGoodsMstDto> list = null; // DTOリスト
        CourseGoodsMstDto dto = null; // DTO

        ResultSet res = null;

        StringBuffer sb = new StringBuffer();

        // 取得処理
        sb.append(" SELECT ");
        sb.append("  COURSE_CD");
        sb.append(" ,GOODS_CD");
        sb.append(" ,RECORD_VER_NO");
        sb.append(" ,INSERT_DTTM");
        sb.append(" ,INSERT_CD");
        sb.append(" ,UPDATE_DTTM");
        sb.append(" ,UPDATE_CD");
        sb.append(" FROM ");
        sb.append("   COURSE_GOODS_MST ");
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

            list = new ArrayList<CourseGoodsMstDto>();
            while (res.next()) {

                dto = new CourseGoodsMstDto();

                dto.setCourseCd(res.getString("COURSE_CD"));
                dto.setGoodsCd(res.getString("GOODS_CD"));
                dto.setRecordVerNo(res.getInt("RECORD_VER_NO"));
                dto.setInsertDttm(res.getTimestamp("INSERT_DTTM"));
                dto.setInsertCd(res.getString("INSERT_CD"));
                dto.setUpdateDttm(res.getTimestamp("UPDATE_DTTM"));
                dto.setUpdateCd(res.getString("UPDATE_CD"));

                dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);

                list.add(dto);
            }

            if (list.size() < 1) {
                list = new ArrayList<CourseGoodsMstDto>();
                dto = new CourseGoodsMstDto();
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
    public int insert(CourseGoodsMstDto dto) throws NaiException {

        // この画面を開いたロールを取得します(この段階に到達した時点でユーザ情報は存在している)
        String userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();

        int insertRowCount = NaikaraTalkConstants.RETURN_CD_ERR_INS; // 追加データ件数

        // SQL文作成
        StringBuffer sb = new StringBuffer();
        sb.append(" insert into COURSE_GOODS_MST");
        sb.append(" ( ");
        sb.append("  COURSE_CD");
        sb.append(" ,GOODS_CD");
        sb.append(" ,RECORD_VER_NO");
        sb.append(" ,INSERT_DTTM");
        sb.append(" ,INSERT_CD");
        sb.append(" ,UPDATE_DTTM");
        sb.append(" ,UPDATE_CD)");
        sb.append(" values ");
        sb.append(" ( ");
        sb.append("  ? ");
        sb.append(" ,? ");
        sb.append(" ,0 ");
        sb.append(" ,sysdate() ");
        sb.append(" ,? ");
        sb.append(" ,sysdate() ");
        sb.append(" ,?) ");

        PreparedStatement ps = null;

        try {

            ps = conn.prepareStatement(sb.toString());
            ps.setString(1, dto.getCourseCd());
            ps.setString(2, dto.getGoodsCd());
            ps.setString(3, userId);
            ps.setString(4, userId);

            // SQL文を実行
            insertRowCount = ps.executeUpdate();

        } catch (SQLException se) {
            throw new NaiException(se);
        }

        return insertRowCount;
    }

    /**
     * 更新処理。<br>
     * <br>
     * 更新処理。<br>
     * <br>
     * @param CourseGoodsMstDto 画面のパラメータ<br>
     * @return int 更新レコード数<br>
     * @exception NaiException
     */
    public int update(CourseGoodsMstDto dto) throws NaiException {

        // この画面を開いたロールを取得します(この段階に到達した時点でユーザ情報は存在している)
        String userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();

        int updatedRowCount = NaikaraTalkConstants.RETURN_CD_ERR_UPD; // 更新データ件数

        StringBuffer sb = new StringBuffer();

        // SQL文作成
        sb.append(" update COURSE_GOODS_MST");
        sb.append(" set ");
        sb.append(" RECORD_VER_NO = RECORD_VER_NO + 1");
        sb.append(" ,UPDATE_DTTM = sysdate()");
        sb.append(" ,UPDATE_CD = ?");
        sb.append(" where ");
        sb.append(" COURSE_CD = ? ");
        sb.append(" and GOODS_CD = ?");

        try {

            PreparedStatement ps = conn.prepareStatement(sb.toString());

            // 更新者コード
            ps.setString(1, userId);
            // コースコード
            ps.setString(2, dto.getCourseCd());
            // 該当商品コード
            ps.setString(3, dto.getGoodsCd());

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
        sb.append("   COURSE_GOODS_MST ");

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

}
