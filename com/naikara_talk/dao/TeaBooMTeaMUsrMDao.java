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
import com.naikara_talk.dto.TeaBooMTeaMUsrMDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DAOクラス<br>
 * <b>クラス名称　　　:</b>受講者別講師ブックマークテーブル、講師マスタ、利用者マスタデータアクセス<br>
 * <b>クラス概要　　　:</b>受講者別講師ブックマークテーブル、講師マスタ、利用者マスタの結合データを取得する<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/13 TECS 新規作成
 */
public class TeaBooMTeaMUsrMDao extends AbstractDao {

    /** 受講者ID（受講者別講師ブックマークテーブル）　条件項目　*/
    public static final String COND_TBM_STUDENT_ID = "TBM.STUDENT_ID";

    /** ニックネーム（講師マスタ）　並び順　*/
    public static final String COND_TM_NICK_ANM = "TM.NICK_ANM";

    /** 利用者ID（講師マスタ）　並び順　*/
    public static final String COND_TM_USER_ID = "TM.USER_ID";

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public TeaBooMTeaMUsrMDao(Connection con) {
        this.conn = con;
    }

    /**
     * 受講者別講師ブックマークテーブルのデータ取得<br>
     * <br>
     * 受講者別講師ブックマークテーブルからデータを取得する<br>
     * <br>
     * @param conditions
     * @param OrderBy
     * @return TeaBooMTeaMUsrMDto
     * @throws NaiException
     */
    public List<TeaBooMTeaMUsrMDto> search(ConditionMapper conditions, OrderCondition OrderBy) throws NaiException {

        // 戻り値とリターンコードの初期化
        ArrayList<TeaBooMTeaMUsrMDto> list = null; // DTOリスト
        TeaBooMTeaMUsrMDto dto = null;             // DTO

        ResultSet res = null;

        StringBuffer sb = new StringBuffer();

        // データ取得処理
        sb.append(" SELECT  TBM.USER_ID ");
        sb.append("        ,TM.NICK_ANM ");
        sb.append("        ,UM.GENDER_KBN ");
        sb.append("        ,TM.SELLING_POINT ");
        sb.append("        ,TM.SELF_RECOMMENDATION ");
        sb.append("        ,TBM.RECORD_VER_NO ");
        sb.append(" FROM       TEACHER_BOOKMARK_MST TBM ");          // 受講者別講師ブックマークテーブル
        sb.append(" INNER JOIN TEACHER_MST TM ");                    // 講師マスタ
        sb.append("         ON TBM.USER_ID = TM.USER_ID ");          // 受講者別講師ブックマークテーブル．講師ID (利用者ID) ＝ 講師マスタ．利用者ID
        sb.append(" INNER JOIN USER_MST UM ");                       // 利用者マスタ
        sb.append("         ON TBM.USER_ID = UM.USER_ID ");          // 受講者別講師ブックマークテーブル．講師ID (利用者ID) ＝ 利用者マスタ．利用者ID

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

            list = new ArrayList<TeaBooMTeaMUsrMDto>();

            // コード管理マスタのキャッシュ読み込み
            CodeManagMstCache cache = CodeManagMstCache.getInstance();
            LinkedHashMap<String, CodeManagMstDto> genderKbnList = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_GENDER);

            while (res.next()) {
                dto = new TeaBooMTeaMUsrMDto();

                dto.setUserId(res.getString("USER_ID"));                                            // 講師ID (利用者ID)
                dto.setNickAnm(res.getString("NICK_ANM"));                                          // 講師名(ニックネーム）
                dto.setGenderKbnNm(genderKbnList.get(res.getString("GENDER_KBN")).getManagerNm());  // 性別区分名
                dto.setSellingPoint(res.getString("SELLING_POINT"));                                // セールスポイント(スクール記入)
                dto.setSelfRecommendation(res.getString("SELF_RECOMMENDATION"));                    // 受講生へのアピールポイント
                dto.setRecordVerNo(res.getInt("RECORD_VER_NO"));                                    // レコードバージョン番号
                dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);                         // リターンコード

                list.add(dto);
            }

            if (list.size() < 1) {
                dto = new TeaBooMTeaMUsrMDto();
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

}
