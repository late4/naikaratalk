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
import com.naikara_talk.dto.LessonCommentTrnDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DAOクラス<br>
 * <b>クラス名称　　　:</b>レッスンコメントテーブルデータ取得クラス<br>
 * <b>クラス概要　　　:</b>レッスンコメントテーブルの取得、登録、更新を行う<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/06/10 TECS 新規作成
 */
public class LessonCommentTrnDao extends AbstractDao {

    /** 予約番号　条件項目　*/
    public static final String RESERVATION_NO = "RESERVATION_NO";

    /** コメント入力者区分　条件項目　*/
    public static final String CMT_INPUTS_KBN = "CMT_INPUTS_KBN";

    /** コメント入力者区分　条件項目　*/
    public static final String COND_CMT_INPUTS_KBN = "CMT_INPUTS_KBN";

    /** 予約No　条件項目　*/
    public static final String COND_RESERVATION_NO = "RESERVATION_NO";

	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public LessonCommentTrnDao(Connection con) {
		this.conn = con;
	}

    /**
     * レッスンコメントテーブルデータ取得<br>
     * <br>
     * レッスンコメントテーブルリストを戻り値で返却する<br>
     * <br>
     * @param conditions
     * @param OrderBy
     * @return ArrayList<LessonCommentTrnDto>
     * @throws NaiException
     */
    public ArrayList<LessonCommentTrnDto> search(ConditionMapper conditions, OrderCondition OrderBy)
            throws NaiException {

        // 戻り値とリターンコードの初期化
        ArrayList<LessonCommentTrnDto> list = null; // DTOリスト
        LessonCommentTrnDto dto = null; // DTO

		ResultSet res = null;

		StringBuffer sb = new StringBuffer();

		//取得処理
		sb.append(" SELECT ");
		sb.append("  RESERVATION_NO");
		sb.append(" ,CMT_INPUTS_KBN");
		sb.append(" ,T_SELF_EVALUATION1_KBN");
		sb.append(" ,T_SELF_EVALUATION1_ENM");
		sb.append(" ,T_SELF_EVALUATION2_KBN");
		sb.append(" ,T_SELF_EVALUATION2_ENM");
		sb.append(" ,T_POSITIVE_CMT");
		sb.append(" ,T_ON_SCHOOL_CMT");
		sb.append(" ,T_RECOMMENDED_LEVEL_KBN");
		sb.append(" ,T_RECOMMENDED_LEVEL_ENM");
		sb.append(" ,T_NEXT_TEACHER_CMT");
		sb.append(" ,C_EVALUATION_KBN");
		sb.append(" ,C_EVALUATION_JNM");
		sb.append(" ,C_ON_TEACHER_CMT");
		sb.append(" ,S_ON_TEACHER_CMT");
		sb.append(" ,RECORD_VER_NO");
		sb.append(" ,INSERT_DTTM");
		sb.append(" ,INSERT_CD");
		sb.append(" ,UPDATE_DTTM");
		sb.append(" ,UPDATE_CD");
		sb.append(" FROM ");
		sb.append("   LESSON_COMMENT_TRN ");
		// 抽出条件の設定
		if(!StringUtils.isEmpty(conditions.getConditionString())) {
			sb.append("where ");
			sb.append(conditions.getConditionString());
		}
		// 並び順の設定
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

            list = new ArrayList<LessonCommentTrnDto>();
            while (res.next()) {

                dto = new LessonCommentTrnDto();

                dto.setReservationNo(res.getString("RESERVATION_NO"));
                dto.setCmtInputsKbn(res.getString("CMT_INPUTS_KBN"));
                dto.setTSelfEvaluation1Kbn(res.getString("T_SELF_EVALUATION1_KBN"));
                dto.setTSelfEvaluation1Enm(res.getString("T_SELF_EVALUATION1_ENM"));
                dto.setTSelfEvaluation2Kbn(res.getString("T_SELF_EVALUATION2_KBN"));
                dto.setTSelfEvaluation2Enm(res.getString("T_SELF_EVALUATION2_ENM"));
                dto.setTPositiveCmt(res.getString("T_POSITIVE_CMT"));
                dto.setTOnSchoolCmt(res.getString("T_ON_SCHOOL_CMT"));
                dto.setTRecommendedLevelKbn(res.getString("T_RECOMMENDED_LEVEL_KBN"));
                dto.setTRecommendedLevelEnm(res.getString("T_RECOMMENDED_LEVEL_ENM"));
                dto.setTNextTeacherCmt(res.getString("T_NEXT_TEACHER_CMT"));
                dto.setCEvaluationKbn(res.getString("C_EVALUATION_KBN"));
                dto.setCEvaluationJnm(res.getString("C_EVALUATION_JNM"));
                dto.setCOnTeacherCmt(res.getString("C_ON_TEACHER_CMT"));
                dto.setSOnTeacherCmt(res.getString("S_ON_TEACHER_CMT"));
                dto.setRecordVerNo(res.getInt("RECORD_VER_NO"));
                dto.setInsertDttm(res.getTimestamp("INSERT_DTTM"));
                dto.setInsertCd(res.getString("INSERT_CD"));
                dto.setUpdateDttm(res.getTimestamp("UPDATE_DTTM"));
                dto.setUpdateCd(res.getString("UPDATE_CD"));

                dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);

                list.add(dto);
            }

            if (list.size() < 1) {
                list = new ArrayList<LessonCommentTrnDto>();
                dto = new LessonCommentTrnDto();
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
     * 登録処理。<br>
     * <br>
     * 登録処理。<br>
     * <br>
     * @param LessonCommentTrnDto 画面のパラメータ<br>
     * @return なし<br>
     * @exception NaiException
     */
    public int insert(LessonCommentTrnDto dto) throws NaiException {

        // この画面を開いたロールを取得します(この段階に到達した時点でユーザ情報は存在している)
        String userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();

        int insertRowCount = NaikaraTalkConstants.RETURN_CD_ERR_INS; // 追加データ件数

        // SQL文作成
        StringBuffer sb = new StringBuffer();
        sb.append(" insert into LESSON_COMMENT_TRN");
        sb.append(" ( ");
        sb.append(" RESERVATION_NO ");
        sb.append(" ,CMT_INPUTS_KBN ");
        sb.append(" ,T_SELF_EVALUATION1_KBN ");
        sb.append(" ,T_SELF_EVALUATION1_ENM ");
        sb.append(" ,T_SELF_EVALUATION2_KBN ");
        sb.append(" ,T_SELF_EVALUATION2_ENM ");
        sb.append(" ,T_POSITIVE_CMT ");
        sb.append(" ,T_ON_SCHOOL_CMT ");
        sb.append(" ,T_RECOMMENDED_LEVEL_KBN ");
        sb.append(" ,T_RECOMMENDED_LEVEL_ENM");
        sb.append(" ,T_NEXT_TEACHER_CMT");
        sb.append(" ,C_EVALUATION_KBN");
        sb.append(" ,C_EVALUATION_JNM");
        sb.append(" ,C_ON_TEACHER_CMT");
        sb.append(" ,S_ON_TEACHER_CMT");
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

        try {
            ps = conn.prepareStatement(sb.toString());
            // 予約No
            ps.setString(1, dto.getReservationNo());
            // コメント入力者区分
            ps.setString(2, dto.getCmtInputsKbn());
            // 講師：講師のレッスン自己分析１区分
            ps.setString(3, dto.getTSelfEvaluation1Kbn());
            // 講師：講師のレッスン自己分析１
            ps.setString(4, dto.getTSelfEvaluation1Enm());
            // 講師：講師のレッスン自己分析２区分
            ps.setString(5, dto.getTSelfEvaluation2Kbn());
            // 講師：講師のレッスン自己分析２
            ps.setString(6, dto.getTSelfEvaluation2Enm());
            // 講師：受講者への前向きコメント
            ps.setString(7, dto.getTPositiveCmt());
            // 講師：講師からスクールへのコメント
            ps.setString(8, dto.getTOnSchoolCmt());
            // 講師：推奨レベル区分
            ps.setString(9, dto.getTRecommendedLevelKbn());
            // 講師：推奨レベル
            ps.setString(10, dto.getTRecommendedLevelEnm());
            // 講師：受講者を引継ぎするコメント
            ps.setString(11, dto.getTNextTeacherCmt());
            // 受講者：講師への評価区分
            ps.setString(12, dto.getCEvaluationKbn());
            // 受講者：講師への評価
            ps.setString(13, dto.getCEvaluationJnm());
            // 受講者：レッスンに対する講師への意見コメント
            ps.setString(14, dto.getCOnTeacherCmt());
            // スクール：スクール側から講師へのコメント
            ps.setString(15, dto.getSOnTeacherCmt());
            // 登録者コード
            ps.setString(16, userId);
            // 更新者コード
            ps.setString(17, userId);

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
     * @param LessonCommentTrnDto 画面のパラメータ<br>
     * @return int 更新レコード数<br>
     * @exception NaiException
     */
    public int update(LessonCommentTrnDto dto) throws NaiException {

        // この画面を開いたロールを取得します(この段階に到達した時点でユーザ情報は存在している)
        String userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();

        int updatedRowCount = NaikaraTalkConstants.RETURN_CD_ERR_UPD; // 更新データ件数

        // 利用者マスタのデータチェック
        if (!chkDto(dto)) {
            return updatedRowCount;
        }

        // SQL文作成
        StringBuffer sb = new StringBuffer();
        sb.append(" update LESSON_COMMENT_TRN");
        sb.append(" set ");
        sb.append(" T_SELF_EVALUATION1_KBN = ?");
        sb.append(" ,T_SELF_EVALUATION1_ENM = ?");
        sb.append(" ,T_SELF_EVALUATION2_KBN = ?");
        sb.append(" ,T_SELF_EVALUATION2_ENM = ?");
        sb.append(" ,T_POSITIVE_CMT = ?");
        sb.append(" ,T_ON_SCHOOL_CMT = ?");
        sb.append(" ,T_RECOMMENDED_LEVEL_KBN = ?");
        sb.append(" ,T_RECOMMENDED_LEVEL_ENM = ?");
        sb.append(" ,T_NEXT_TEACHER_CMT = ?");
        sb.append(" ,C_EVALUATION_KBN = ?");
        sb.append(" ,C_EVALUATION_JNM = ?");
        sb.append(" ,C_ON_TEACHER_CMT = ?");
        sb.append(" ,S_ON_TEACHER_CMT = ?");
        sb.append(" ,RECORD_VER_NO = RECORD_VER_NO + 1");
        sb.append(" ,UPDATE_DTTM = sysdate()");
        sb.append(" ,UPDATE_CD = ?");
        sb.append(" where ");
        sb.append(" RESERVATION_NO = ?");
        sb.append(" and CMT_INPUTS_KBN = ?");
        sb.append(" and RECORD_VER_NO = ?");

        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sb.toString());
            // 講師：講師のレッスン自己分析１区分
            ps.setString(1, dto.getTSelfEvaluation1Kbn());
            // 講師：講師のレッスン自己分析１
            ps.setString(2, dto.getTSelfEvaluation1Enm());
            // 講師：講師のレッスン自己分析２区分
            ps.setString(3, dto.getTSelfEvaluation2Kbn());
            // 講師：講師のレッスン自己分析２
            ps.setString(4, dto.getTSelfEvaluation2Enm());
            // 講師：受講者への前向きコメント
            ps.setString(5, dto.getTPositiveCmt());
            // 講師：講師からスクールへのコメント
            ps.setString(6, dto.getTOnSchoolCmt());
            // 講師：推奨レベル区分
            ps.setString(7, dto.getTRecommendedLevelKbn());
            // 講師：推奨レベル
            ps.setString(8, dto.getTRecommendedLevelEnm());
            // 講師：受講者を引継ぎするコメント
            ps.setString(9, dto.getTNextTeacherCmt());
            // 受講者：講師への評価区分
            ps.setString(10, dto.getCEvaluationKbn());
            // 受講者：講師への評価
            ps.setString(11, dto.getCEvaluationJnm());
            // 受講者：レッスンに対する講師への意見コメント
            ps.setString(12, dto.getCOnTeacherCmt());
            // スクール：スクール側から講師へのコメント
            ps.setString(13, dto.getSOnTeacherCmt());
            // 更新者コード
            ps.setString(14, userId);
            // 予約No
            ps.setString(15, dto.getReservationNo());
            // コメント入力者区分
            ps.setString(16, dto.getCmtInputsKbn());
            // レコードバージョン番号
            ps.setInt(17, dto.getRecordVerNo());

            updatedRowCount = ps.executeUpdate();

        } catch (SQLException se) {

            throw new NaiException(se);

        }
        return updatedRowCount;

    }

    /**
     * レッスンコメントテーブル更新データチェック<br>
     * <br>
     * レッスンコメントテーブルの更新データをチェックし<br>
     * エラー有の場合は、falseを返す<br>
     * エラー無の場合は、trueを返す<br>
     * <br>
     * @param UserMstDto
     * @return boolean
     */
    private boolean chkDto(LessonCommentTrnDto dto) throws NaiException {

        // 予約Noのチェック
        if (StringUtils.isEmpty(dto.getReservationNo())) {
            return false;
        }

        // コメント入力者区分のチェック
        if (StringUtils.isEmpty(dto.getCmtInputsKbn())) {
            return false;
        }

        return true;

    }
}
