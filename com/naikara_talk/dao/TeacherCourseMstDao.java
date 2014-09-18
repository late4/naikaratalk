package com.naikara_talk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dbutil.QueryCondition;
import com.naikara_talk.dto.TeacherCourseMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DAOクラス<br>
 * <b>クラス名称　　　:</b>講師別コースマスタデータアクセスクラス<br>
 * <b>クラス概要　　　:</b>講師別コースマスタの登録、更新を行う<br>
 * <br>
 * <b>著作権　　　　　:</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/03 TECS 新規作成
 */
public class TeacherCourseMstDao extends AbstractDao {

    /** 講師ID (利用者ID) */
    public static final String COND_USER_ID = "USER_ID";

    /** コースコード */
    public static final String COND_COURSE_CD = "COURSE_CD";

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public TeacherCourseMstDao(Connection con) {
        this.conn = con;
    }

    /**
     * 講師別コースマスタデータ取得<br>
     * <br>
     * 講師別コースマスタリストを戻り値で返却する<br>
     * <br>
     * @param conditions
     * @param orderConditions
     * @return List<TeacherCourseMstDto>
     * @throws NaiException
     */
    public List<TeacherCourseMstDto> search(ConditionMapper conditions, OrderCondition orderConditions)
            throws NaiException {

        ResultSet res = null;

        StringBuffer sb = new StringBuffer();

        // ポイント管理マスタの全項目取得
        sb.append("select");
        sb.append(" USER_ID ");
        sb.append(" ,COURSE_CD ");
        sb.append(" ,RECORD_VER_NO ");
        sb.append(" ,INSERT_DTTM ");
        sb.append(" ,INSERT_CD ");
        sb.append(" ,UPDATE_DTTM ");
        sb.append(" ,UPDATE_CD ");
        sb.append(" from ");
        sb.append(" TEACHER_COURSE_MST ");

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

            List<TeacherCourseMstDto> list = new ArrayList<TeacherCourseMstDto>();

            while (res.next()) {

                TeacherCourseMstDto retDto = new TeacherCourseMstDto();
                retDto.setUserId(res.getString("USER_ID"));
                retDto.setCourseCd(res.getString("COURSE_CD"));
                retDto.setRecordVerNo(res.getInt("RECORD_VER_NO"));
                retDto.setInsertDttm(res.getTimestamp("INSERT_DTTM"));
                retDto.setInsertCd(res.getString("INSERT_CD"));
                retDto.setUpdateDttm(res.getTimestamp("UPDATE_DTTM"));
                retDto.setUpdateCd(res.getString("UPDATE_CD"));

                retDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);

                list.add(retDto);
            }

            if (list.size() < 1) {
                TeacherCourseMstDto retDto = new TeacherCourseMstDto();
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
     * @param TeacherCourseMstDto 画面のパラメータ<br>
     * @return なし<br>
     * @exception NaiException
     */
    public int insert(TeacherCourseMstDto dto) throws NaiException {

        // この画面を開いたロールを取得します(この段階に到達した時点でユーザ情報は存在している)
        String userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();

        int insertRowCount = NaikaraTalkConstants.RETURN_CD_ERR_INS; // 追加データ件数

        StringBuffer sb = new StringBuffer();

        // SQL文作成
        sb.append(" insert into TEACHER_COURSE_MST");
        sb.append(" ( ");
        sb.append(" USER_ID ");
        sb.append(" ,COURSE_CD ");
        sb.append(" ,RECORD_VER_NO ");
        sb.append(" ,INSERT_DTTM ");
        sb.append(" ,INSERT_CD ");
        sb.append(" ,UPDATE_DTTM ");
        sb.append(" ,UPDATE_CD )");
        sb.append(" values ");
        sb.append(" ( ");
        sb.append("  ? ");
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
            // コースコード
            ps.setString(2, dto.getCourseCd());
            // 登録者コード
            ps.setString(3, userId);
            // 更新者コード
            ps.setString(4, userId);

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
     * @param TeacherCourseMstDto 画面のパラメータ<br>
     * @return int 更新レコード数<br>
     * @exception NaiException
     */
    public int update(TeacherCourseMstDto dto) throws NaiException {

        // この画面を開いたロールを取得します(この段階に到達した時点でユーザ情報は存在している)
        String userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();

        int updatedRowCount = NaikaraTalkConstants.RETURN_CD_ERR_UPD; // 更新データ件数

        StringBuffer sb = new StringBuffer();

        // SQL文作成
        sb.append(" update TEACHER_COURSE_MST");
        sb.append(" set ");
        sb.append(" RECORD_VER_NO = RECORD_VER_NO + 1");
        sb.append(" ,UPDATE_DTTM = sysdate()");
        sb.append(" ,UPDATE_CD = ?");
        sb.append(" where ");
        sb.append(" USER_ID = ? ");
        sb.append(" and COURSE_CD = ?");

        try {

            PreparedStatement ps = conn.prepareStatement(sb.toString());

            // 更新者コード
            ps.setString(1, userId);
            // 講師ID (利用者ID)
            ps.setString(2, dto.getUserId());
            // コースコード
            ps.setString(3, dto.getCourseCd());

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
        sb.append("   TEACHER_COURSE_MST ");

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
