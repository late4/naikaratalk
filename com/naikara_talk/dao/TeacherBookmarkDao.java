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
import com.naikara_talk.dto.TeacherBookmarkMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DAOクラス<br>
 * <b>クラス名称　　　:</b>受講者別講師ブックマークテーブルアクセス<br>
 * <b>クラス概要　　　:</b>受講者別講師ブックマークテーブルの取得、登録、更新を行う<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/06/05 TECS 新規作成
 */
public class TeacherBookmarkDao extends AbstractDao {

    /** 受講者ID　条件項目　*/
    public static final String COND_STUDENT_ID = "STUDENT_ID";
    /** 講師ID (利用者ID)　条件項目　*/
    public static final String COND_USER_ID = "USER_ID";

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public TeacherBookmarkDao(Connection con) {
        this.conn = con;
    }

    /**
     * 受講者別講師ブックマークテーブルのデータ取得<br>
     * <br>
     * 受講者別講師ブックマークテーブルからデータを取得する<br>
     * <br>
     * @param conditions
     * @param OrderBy
     * @return TeacherBookmarkMstDto
     * @throws NaiException
     */
    public List<TeacherBookmarkMstDto> search(ConditionMapper conditions, OrderCondition OrderBy) throws NaiException {

        // 戻り値とリターンコードの初期化
        ArrayList<TeacherBookmarkMstDto> list = null; // DTOリスト
        TeacherBookmarkMstDto dto = null;             // DTO


		ResultSet res = null;

		StringBuffer sb = new StringBuffer();

		//データ取得処理
		sb.append(" SELECT ");
		sb.append("  STUDENT_ID");
		sb.append(" ,USER_ID");
		sb.append(" ,RECORD_VER_NO");
		sb.append(" ,INSERT_DTTM");
		sb.append(" ,INSERT_CD");
		sb.append(" ,UPDATE_DTTM");
		sb.append(" ,UPDATE_CD");
		sb.append(" FROM ");
		sb.append("   TEACHER_BOOKMARK_MST ");
		//抽出条件の設定
		if(!StringUtils.isEmpty(conditions.getConditionString())) {
			sb.append("where ");
			sb.append(conditions.getConditionString());
		}
		//並び順の設定
		if(!StringUtils.isEmpty(OrderBy.getOrderString())) {
			sb.append(OrderBy.getOrderString());
		}


		try{

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

            list = new ArrayList<TeacherBookmarkMstDto>();
            if (res.next()) {
                dto = new TeacherBookmarkMstDto();

                dto.setStudentId(res.getString("STUDENT_ID"));       // 受講者ID
                dto.setUserId(res.getString("USER_ID"));             // 講師ID (利用者ID)
                dto.setRecordVerNo(res.getInt("RECORD_VER_NO"));     // レコードバージョン番号
                dto.setInsertDttm(res.getTimestamp("INSERT_DTTM"));  // 登録日時
                dto.setInsertCd(res.getString("INSERT_CD"));         // 登録者コード
                dto.setUpdateDttm(res.getTimestamp("UPDATE_DTTM"));  // 更新日時
                dto.setUpdateCd(res.getString("UPDATE_CD"));         // 更新者コード

                dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);                                // リターンコード

                list.add(dto);
            }

            if (list.size() < 1) {
                dto = new TeacherBookmarkMstDto();
                dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_NO);
                list.add(dto);
                return list;
            }

            return list;

        } catch (SQLException se) {
            throw new NaiException(se);
        } finally {
        	try{
        		if ( res != null ) {
        			res.close();
        		}
        	}catch(SQLException se ){
        		throw new NaiException(se);
        	}
        }
    }

    /**
     * 受講者別講師ブックマークテーブルのデータ登録<br>
     * <br>
     * 受講者別講師ブックマークテーブルのデータ登録を行う<br>
     * <br>
     * @param TeacherBookmarkMstDto
     * @return insertRowCount
     * @throws NaiException
     */
    public int insert(TeacherBookmarkMstDto dto) throws NaiException {

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        int insertRowCount = NaikaraTalkConstants.RETURN_CD_ERR_INS; // 登録データ件数

        // 受講者別講師ブックマークテーブルのチェック
        if (!insCheckDto(dto)) {
            return insertRowCount;
        }

        // ◆◆◆データ存在チェック◆◆◆

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // 受講者IDを入力されている場合
        if (!StringUtils.isEmpty(dto.getStudentId())) {
            conditions.addCondition("STUDENT_ID", ConditionMapper.OPERATOR_EQUAL, dto.getStudentId());
        }
        // ユーザIDを入力されている場合
        if (!StringUtils.isEmpty(dto.getUserId())) {
            conditions.addCondition("USER_ID", ConditionMapper.OPERATOR_EQUAL, dto.getUserId());
        }

         TableCountDao tCnt = new TableCountDao(this.conn);
         int Count = tCnt.rowCount(NaikaraTalkConstants.TEACHER_BOOKMARK_MST, conditions);

        if (Count > 0) {
            // データが存在する場合は、登録処理を行わず、正常処理で終了する
            insertRowCount = 0;
            return insertRowCount;
        }

		StringBuffer sb = new StringBuffer();

		//データ登録処理
		sb.append(" INSERT INTO ");
		sb.append("   TEACHER_BOOKMARK_MST ");
		sb.append(" VALUES ");
		sb.append("   ( ? ");
		sb.append("    ,? ");
		sb.append("    ,? ");
		sb.append("    ,? ");
		sb.append("    ,? ");
		sb.append("    ,? ");
		sb.append("    ,? ) ");

		try{

			PreparedStatement ps = conn.prepareStatement(sb.toString());

            ps.setString(1, dto.getStudentId());
            ps.setString(2, dto.getUserId());
            ps.setString(3, String.valueOf(dto.getRecordVerNo()));

            ps.setString(4, String.valueOf(Timestamp.valueOf(sdf.format(cal.getTime()))));

            ps.setString(5, dto.getInsertCd());
            ps.setString(6, String.valueOf(Timestamp.valueOf(sdf.format(cal.getTime()))));

            ps.setString(7, dto.getUpdateCd());


			insertRowCount= ps.executeUpdate();

			return insertRowCount;

		} catch ( SQLException se ) {
				throw new NaiException(se);
		}
	}


    /**
     * 受講者別講師ブックマークテーブルのデータ削除<br>
     * <br>
     * 受講者別講師ブックマークテーブルのデータ削除を行う<br>
     * <br>
     * @param TeacherBookmarkMstDto
     * @return insertRowCount
     * @throws NaiException
     */
    public int delete(TeacherBookmarkMstDto dto) throws NaiException {

        int deleteRowCount = NaikaraTalkConstants.RETURN_CD_ERR_DEL; // 削除データ件数

        // 受講者別講師ブックマークテーブルのデータチェック
        if (!delCheckDto(dto)) {
            return deleteRowCount;
        }

			StringBuffer sb = new StringBuffer();

			//データ削除処理
			sb.append(" DELETE FROM ");
			sb.append("   TEACHER_BOOKMARK_MST ");
			sb.append(" WHERE ");
			sb.append("   STUDENT_ID = ? ");
			sb.append("  AND ");
			sb.append("   USER_ID = ? ");
			sb.append("  AND ");
			sb.append("   RECORD_VER_NO = ? ");

			try{

				PreparedStatement ps = conn.prepareStatement(sb.toString());

            ps.setString(1, dto.getStudentId());
            ps.setString(2, dto.getUserId());
            ps.setString(3, String.valueOf(dto.getRecordVerNo()));

				deleteRowCount= ps.executeUpdate();

				return deleteRowCount;

			} catch ( SQLException se ) {
				throw new NaiException(se);
			}
		}

    /**
     * 受講者別講師ブックマークテーブル登録データチェック<br>
     * <br>
     * 受講者別講師ブックマークの登録データをチェックし<br>
     * エラー有の場合は、falseを返す<br>
     * エラー無の場合は、trueを返す<br>
     * <br>
     * @param dto チェックデータ
     * @return boolean チェック結果
     * @exception NaiException
     */
    private boolean insCheckDto(TeacherBookmarkMstDto dto) throws NaiException {

        // 引数のパラメータチェック
        // 受講者ID
        // 必須チェック
        if (dto.getStudentId() == null || "".equals(dto.getStudentId())) {
            return false;
        }
        // 半角12バイト以下のチェック
        if (dto.getStudentId().getBytes().length > 12) {
            return false;
        }
        // 講師ID
        // 必須チェック
        if (dto.getUserId() == null || "".equals(dto.getUserId())) {
            return false;
        }
        // 半角12バイト以下のチェック
        if (dto.getUserId().getBytes().length > 12) {
            return false;
        }

        // セッションID
        // 必須チェック
        if (dto.getInsertCd() == null || "".equals(dto.getInsertCd())) {
            return false;
        }
        // 半角12バイト以下のチェック
        if (dto.getInsertCd().getBytes().length > 12) {
            return false;
        }

        return true;

    }

    /**
     * 受講者別講師ブックマークテーブル削除データチェック<br>
     * <br>
     * 受講者別講師ブックマークの削除データをチェックし<br>
     * エラー有の場合は、falseを返す<br>
     * エラー無の場合は、trueを返す<br>
     * <br>
     * @param dto チェックデータ
     * @return boolean チェック結果
     * @exception NaiException
     */
    private boolean delCheckDto(TeacherBookmarkMstDto dto) throws NaiException {

        // 引数のパラメータチェック
        // 受講者ID
        // 必須チェック
        if (dto.getStudentId() == null || "".equals(dto.getStudentId())) {
            return false;
        }
        // 講師ID
        // 必須チェック
        if (dto.getUserId() == null || "".equals(dto.getUserId())) {
            return false;
        }

        // データ存在チェック

        // 検索条件の作成
        ConditionMapper conditions = new ConditionMapper();

        // 受講者IDを入力されている場合
        if (!StringUtils.isEmpty(dto.getStudentId())) {
            conditions.addCondition("STUDENT_ID", ConditionMapper.OPERATOR_EQUAL, dto.getStudentId());
        }
        // ユーザIDを入力されている場合
        if (!StringUtils.isEmpty(dto.getUserId())) {
            conditions.addCondition("USER_ID", ConditionMapper.OPERATOR_EQUAL, dto.getUserId());
        }

        // 並び順:なし
        OrderCondition orderBy = new OrderCondition();

        List<TeacherBookmarkMstDto> dtoWk;

        dtoWk = search(conditions, orderBy);
        if (dtoWk == null) {
            // データが存在しない場合は、削除エラーとする
            return false;
        }

        return true;

    }
}
