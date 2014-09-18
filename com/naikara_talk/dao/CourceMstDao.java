package com.naikara_talk.dao;

import java.io.FileInputStream;
import java.io.IOException;
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
import com.naikara_talk.dto.CourseMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DAOクラス<br>
 * <b>クラス名称　　　:</b>コースマスタデータ取得クラス<br>
 * <b>クラス概要　　　:</b>コースマスタデータ取得DAO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/05/30 TECS 新規作成
 *  　　　　　　　　　:</b>2014/04/22 TECS 項目の追加(短コース名)対応
 *  　　　　　　　　　:</b>2014/04/22 TECS 検索条件：コース名の追加対応(rowCountメソッド)
 */
public class CourceMstDao extends AbstractDao {

    /** コースコード　条件項目　*/
    public static final String COND_COURSE_CD = "COURSE_CD";

    /** 大分類コード　条件項目　*/
    public static final String COND_BIG_CLASSIFICATION_CD = "BIG_CLASSIFICATION_CD";

    /** 中分類コード　条件項目　*/
    public static final String COND_MIDDLE_CLASSIFICATION_CD = "MIDDLE_CLASSIFICATION_CD";

    /** 小分類コード　条件項目　*/
    public static final String COND_SMALL_CLASSIFICATION_CD = "SMALL_CLASSIFICATION_CD";

    /** コース名　条件項目　*/
    public static final String COND_COURSE_JNM = "COURSE_JNM";

    /** 提供開始日　条件項目　*/
    public static final String COND_USE_STR_DT = "USE_STR_DT";

    /** 提供終了日　条件項目　*/
    public static final String COND_USE_END_DT = "USE_END_DT";

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public CourceMstDao(Connection con) {
        this.conn = con;
    }

    /**
     * コースマスタデータ取得<br>
     * <br>
     * コースマスタリストを戻り値で返却する<br>
     * <br>
     * @param conditions
     * @param OrderBy
     * @return ArrayList<CourseMstDto>
     * @throws NaiException
     */
    public ArrayList<CourseMstDto> search(ConditionMapper conditions, OrderCondition OrderBy) throws NaiException {

        // 戻り値とリターンコードの初期化
        ArrayList<CourseMstDto> list = null; // DTOリスト
        CourseMstDto dto = new CourseMstDto();

        ResultSet res = null;

        StringBuffer sb = new StringBuffer();

        // コースマスタの全項目取得処理
        sb.append(" SELECT ");
        sb.append("  COURSE_CD ");
        sb.append(" ,BIG_CLASSIFICATION_CD ");
        sb.append(" ,MIDDLE_CLASSIFICATION_CD ");
        sb.append(" ,SMALL_CLASSIFICATION_CD ");
        sb.append(" ,COURSE_JNM ");

        // 2014/04/22 Add Start 検索条件の追加(短コース名)対応
        sb.append(" ,SHORT_COURSE_JNM ");
        // 2014/04/22 Add End   検索条件の追加(短コース名)対応

        sb.append(" ,COURSE_ENM ");
        sb.append(" ,ATTACHMENT_FLG ");
        sb.append(" ,KEYWORD1 ");
        sb.append(" ,KEYWORD2 ");
        sb.append(" ,KEYWORD3 ");
        sb.append(" ,KEYWORD4 ");
        sb.append(" ,KEYWORD5 ");
        sb.append(" ,SIMPLE_EXPLANATION ");
        sb.append(" ,EXPLANATION1 ");
        sb.append(" ,EXPLANATION2 ");
        sb.append(" ,EXPLANATION3 ");
        sb.append(" ,EXPLANATION4 ");
        sb.append(" ,EXPLANATION5 ");
        sb.append(" ,EXPLANATION6_NM ");
        sb.append(" ,EXPLANATION6 ");
        sb.append(" ,LESSON_TIME ");
        sb.append(" ,USE_STR_DT ");
        sb.append(" ,USE_END_DT ");
        sb.append(" ,BOOK_FLG ");
        sb.append(" ,REMARK ");
        sb.append(" ,RECORD_VER_NO ");
        sb.append(" ,INSERT_DTTM ");
        sb.append(" ,INSERT_CD ");
        sb.append(" ,UPDATE_DTTM ");
        sb.append(" ,UPDATE_CD ");
        sb.append(" FROM ");
        sb.append("   COURSE_MST ");
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

            list = new ArrayList<CourseMstDto>();
            while (res.next()) {
                dto = new CourseMstDto();

                dto.setCourseCd(res.getString("COURSE_CD"));
                dto.setBigClassificationCd(res.getString("BIG_CLASSIFICATION_CD"));
                dto.setMiddleClassificationCd(res.getString("MIDDLE_CLASSIFICATION_CD"));
                dto.setSmallClassificationCd(res.getString("SMALL_CLASSIFICATION_CD"));
                dto.setCourseJnm(res.getString("COURSE_JNM"));

                // 2014/04/22 Add Start 検索条件の追加(短コース名)対応
                dto.setCourseJnmShort(res.getString("SHORT_COURSE_JNM"));
                // 2014/04/22 Add End   検索条件の追加(短コース名)対応

                dto.setCourseEnm(res.getString("COURSE_ENM"));
                dto.setAttachmentFlg(res.getString("ATTACHMENT_FLG"));
                dto.setKeyword1(res.getString("KEYWORD1"));
                dto.setKeyword2(res.getString("KEYWORD2"));
                dto.setKeyword3(res.getString("KEYWORD3"));
                dto.setKeyword4(res.getString("KEYWORD4"));
                dto.setKeyword5(res.getString("KEYWORD5"));
                dto.setSimpleExplanation(res.getString("SIMPLE_EXPLANATION"));
                dto.setExplanation1(res.getString("EXPLANATION1"));
                dto.setExplanation2(res.getString("EXPLANATION2"));
                dto.setExplanation3(res.getString("EXPLANATION3"));
                dto.setExplanation4(res.getString("EXPLANATION4"));
                dto.setExplanation5(res.getString("EXPLANATION5"));
                dto.setExplanation6Nm(res.getString("EXPLANATION6_NM"));
                dto.setExplanation6(res.getBlob("EXPLANATION6"));
                dto.setLessonTime(res.getInt("LESSON_TIME"));
                dto.setUseStrDt(res.getString("USE_STR_DT"));
                dto.setUseEndDt(res.getString("USE_END_DT"));
                dto.setBookFlg(res.getString("BOOK_FLG"));
                dto.setRemark(res.getString("REMARK"));
                dto.setRecordVerNo(res.getInt("RECORD_VER_NO"));
                dto.setInsertDttm(res.getTimestamp("INSERT_DTTM"));
                dto.setInsertCd(res.getString("INSERT_CD"));
                dto.setUpdateDttm(res.getTimestamp("UPDATE_DTTM"));
                dto.setUpdateCd(res.getString("UPDATE_CD"));

                dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);

                list.add(dto);
            }

            if (list.size() < 1) {
                list = new ArrayList<CourseMstDto>();
                dto = new CourseMstDto();
                dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_NO);
                list.add(dto);
                return list;
            }

            ps.close();

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
     * コースマスタデータ取得<br>
     * <br>
     * コースマスタリストを戻り値で返却する<br>
     * <br>
     * @param conditions
     * @param OrderBy
     * @param dto 判定条件<br>
     * @param startDt 開始日<br>
     * @param endDt 終了日<br>
     * @return ArrayList<CourseMstDto>
     * @throws NaiException
     */
    public ArrayList<CourseMstDto> search(ConditionMapper conditions, OrderCondition OrderBy
    		, CourseMstDto conditionsDto, String startDt, String endDt)
            throws NaiException {

        // 戻り値とリターンコードの初期化
        ArrayList<CourseMstDto> list = null; // DTOリスト
        CourseMstDto dto = new CourseMstDto();

        // コースマスタの全項目取得処理
        ResultSet res = null;

        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT ");
        sb.append("  COURSE_CD ");
        sb.append(" ,BIG_CLASSIFICATION_CD ");
        sb.append(" ,MIDDLE_CLASSIFICATION_CD ");
        sb.append(" ,SMALL_CLASSIFICATION_CD ");
        sb.append(" ,COURSE_JNM ");

        // 2014/04/22 Add Start 検索条件の追加(短コース名)対応
        sb.append(" ,SHORT_COURSE_JNM ");
        // 2014/04/22 Add End   検索条件の追加(短コース名)対応

        sb.append(" ,COURSE_ENM ");
        sb.append(" ,ATTACHMENT_FLG ");
        sb.append(" ,KEYWORD1 ");
        sb.append(" ,KEYWORD2 ");
        sb.append(" ,KEYWORD3 ");
        sb.append(" ,KEYWORD4 ");
        sb.append(" ,KEYWORD5 ");
        sb.append(" ,SIMPLE_EXPLANATION ");
        sb.append(" ,EXPLANATION1 ");
        sb.append(" ,EXPLANATION2 ");
        sb.append(" ,EXPLANATION3 ");
        sb.append(" ,EXPLANATION4 ");
        sb.append(" ,EXPLANATION5 ");
        sb.append(" ,EXPLANATION6_NM ");
        sb.append(" ,EXPLANATION6 ");
        sb.append(" ,LESSON_TIME ");
        sb.append(" ,USE_STR_DT ");
        sb.append(" ,USE_END_DT ");
        sb.append(" ,BOOK_FLG ");
        sb.append(" ,REMARK ");
        sb.append(" ,RECORD_VER_NO ");
        sb.append(" ,INSERT_DTTM ");
        sb.append(" ,INSERT_CD ");
        sb.append(" ,UPDATE_DTTM ");
        sb.append(" ,UPDATE_CD ");
        sb.append(" FROM ");
        sb.append("   COURSE_MST ");
        // 抽出条件の設定
        sb.append("where (1=1) ");


        // 2014/01/16 日付範囲指定の変更対応 Start
        if (!StringUtils.isEmpty(startDt) || !StringUtils.isEmpty(endDt)) {
        	sb.append(" and not(USE_STR_DT <= ? and USE_END_DT <= ?) ");
        	sb.append(" and not(USE_STR_DT >= ? and USE_END_DT >= ?) ");
        }
        // 2014/01/16 日付範囲指定の変更対応 End


        if (!StringUtils.isEmpty(conditionsDto.getKeyword1()) || !StringUtils.isEmpty(conditionsDto.getKeyword2())
                || !StringUtils.isEmpty(conditionsDto.getKeyword3())
                || !StringUtils.isEmpty(conditionsDto.getKeyword4())
                || !StringUtils.isEmpty(conditionsDto.getKeyword5())) {
            sb.append("and ( 1=2 ");

            // キーワード１が入力されている場合
            sb.append(this.setConditions(conditionsDto.getKeyword1()));

            // キーワード2が入力されている場合
            sb.append(this.setConditions(conditionsDto.getKeyword2()));

            // キーワード3が入力されている場合
            sb.append(this.setConditions(conditionsDto.getKeyword3()));

            // キーワード4が入力されている場合
            sb.append(this.setConditions(conditionsDto.getKeyword4()));

            // キーワード5が入力されている場合
            sb.append(this.setConditions(conditionsDto.getKeyword5()));

            sb.append(") ");
        }

        if (!StringUtils.isEmpty(conditions.getConditionString())) {
            sb.append("and ");
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

            // 2014/01/16 日付範囲指定の変更対応 Start
            if (!StringUtils.isEmpty(startDt)) {
                ps.setString(i + 1, startDt);
                i++;
                ps.setString(i + 1, startDt);
                i++;
            }
            if (!StringUtils.isEmpty(endDt)) {
                ps.setString(i + 1, endDt);
                i++;
                ps.setString(i + 1, endDt);
                i++;
            }
            // 2014/01/16 日付範囲指定の変更対応 End

            // キーワード1が入力されている場合
            i = this.setConditionsValue(ps, conditionsDto.getKeyword1(), i);

            // キーワード2が入力されている場合
            i = this.setConditionsValue(ps, conditionsDto.getKeyword2(), i);

            // キーワード3が入力されている場合
            i = this.setConditionsValue(ps, conditionsDto.getKeyword3(), i);

            // キーワード4が入力されている場合
            i = this.setConditionsValue(ps, conditionsDto.getKeyword4(), i);

            // キーワード5が入力されている場合
            i = this.setConditionsValue(ps, conditionsDto.getKeyword5(), i);

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
            LinkedHashMap<String, CodeManagMstDto> bigClassificationCdList = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_BIG_CLASSIFICATION);
            LinkedHashMap<String, CodeManagMstDto> middleClassificationCdList = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_MIDDLE_CLASSIFICATION);
            LinkedHashMap<String, CodeManagMstDto> smallClassificationCdList = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_SMALL_CLASSIFICATION);
            LinkedHashMap<String, CodeManagMstDto> attachmentFlgList = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_ATTACHMENT_KBN);

            list = new ArrayList<CourseMstDto>();
            while (res.next()) {
                dto = new CourseMstDto();

                dto.setCourseCd(res.getString("COURSE_CD"));
                dto.setBigClassificationCd(res.getString("BIG_CLASSIFICATION_CD"));
                String bigClassificationCdNm = NaikaraTalkConstants.BRANK;
                try {
                    bigClassificationCdNm = bigClassificationCdList.get(res.getString("BIG_CLASSIFICATION_CD")).getManagerNm();
                } catch (Exception ex1) {
                }
                dto.setBigClassificationCdNm(bigClassificationCdNm);
                dto.setMiddleClassificationCd(res.getString("MIDDLE_CLASSIFICATION_CD"));
                String middleClassificationNm = NaikaraTalkConstants.BRANK;
                try {
                    middleClassificationNm = middleClassificationCdList.get(res.getString("MIDDLE_CLASSIFICATION_CD")).getManagerNm();
                } catch (Exception ex1) {
                }
                dto.setMiddleClassificationCdNm(middleClassificationNm);
                dto.setSmallClassificationCd(res.getString("SMALL_CLASSIFICATION_CD"));
                String smallClassificationNm = NaikaraTalkConstants.BRANK;
                try {
                	smallClassificationNm = smallClassificationCdList.get(res.getString("SMALL_CLASSIFICATION_CD")).getManagerNm();
                } catch (Exception ex1) {
                }
                dto.setSmallClassificationCdNm(smallClassificationNm);
                dto.setCourseJnm(res.getString("COURSE_JNM"));

                // 2014/04/22 Add Start 検索条件の追加(短コース名)対応
                dto.setCourseJnmShort(res.getString("SHORT_COURSE_JNM"));
                // 2014/04/22 Add End   検索条件の追加(短コース名)対応

                dto.setCourseEnm(res.getString("COURSE_ENM"));
                dto.setAttachmentFlg(res.getString("ATTACHMENT_FLG"));
                String attachmentFlgNm = NaikaraTalkConstants.BRANK;
                try {
                	attachmentFlgNm = attachmentFlgList.get(res.getString("ATTACHMENT_FLG")).getManagerNm();
                } catch (Exception ex1) {
                }
                dto.setAttachmentFlgNm(attachmentFlgNm);
                dto.setKeyword1(res.getString("KEYWORD1"));
                dto.setKeyword2(res.getString("KEYWORD2"));
                dto.setKeyword3(res.getString("KEYWORD3"));
                dto.setKeyword4(res.getString("KEYWORD4"));
                dto.setKeyword5(res.getString("KEYWORD5"));
                dto.setSimpleExplanation(res.getString("SIMPLE_EXPLANATION"));
                dto.setExplanation1(res.getString("EXPLANATION1"));
                dto.setExplanation2(res.getString("EXPLANATION2"));
                dto.setExplanation3(res.getString("EXPLANATION3"));
                dto.setExplanation4(res.getString("EXPLANATION4"));
                dto.setExplanation5(res.getString("EXPLANATION5"));
                dto.setExplanation6Nm(res.getString("EXPLANATION6_NM"));
                dto.setExplanation6(res.getBlob("EXPLANATION6"));
                dto.setLessonTime(res.getInt("LESSON_TIME"));
                dto.setUseStrDt(res.getString("USE_STR_DT"));
                dto.setUseEndDt(res.getString("USE_END_DT"));
                dto.setBookFlg(res.getString("BOOK_FLG"));
                dto.setRemark(res.getString("REMARK"));
                dto.setRecordVerNo(res.getInt("RECORD_VER_NO"));
                dto.setInsertDttm(res.getTimestamp("INSERT_DTTM"));
                dto.setInsertCd(res.getString("INSERT_CD"));
                dto.setUpdateDttm(res.getTimestamp("UPDATE_DTTM"));
                dto.setUpdateCd(res.getString("UPDATE_CD"));

                dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);

                list.add(dto);
            }

            if (list.size() < 1) {
                list = new ArrayList<CourseMstDto>();
                dto = new CourseMstDto();
                dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_NO);
                list.add(dto);
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
     * データ件数取得<br>
     * <br>
     * データ件数を戻り値で返却する<br>
     * <br>
     * @param conditions 検索条件<br>
     * @param dto 判定条件<br>
     * @return rowCount データ件数<br>
     * @exception NaiException
     */
    public int rowCount(ConditionMapper conditions, CourseMstDto conditionsDto) throws NaiException {

        // 戻り値の初期化
        int rowCount = NaikaraTalkConstants.RETURN_CD_DATA_ERR; // データ件数

        // データ件数の取得処理
        ResultSet res = null;

        StringBuffer sb = new StringBuffer();
        sb.append(" select ");
        sb.append(" COUNT(1) ");
        sb.append(" from COURSE_MST ");
        // 抽出条件の設定
        sb.append("where (1=1) ");

        if (!StringUtils.isEmpty(conditionsDto.getKeyword1()) || !StringUtils.isEmpty(conditionsDto.getKeyword2())
                || !StringUtils.isEmpty(conditionsDto.getKeyword3())
                || !StringUtils.isEmpty(conditionsDto.getKeyword4())
                || !StringUtils.isEmpty(conditionsDto.getKeyword5())) {
            sb.append("and ( 1=2 ");

            // キーワード１が入力されている場合
            sb.append(this.setConditions(conditionsDto.getKeyword1()));

            // キーワード2が入力されている場合
            sb.append(this.setConditions(conditionsDto.getKeyword2()));

            // キーワード3が入力されている場合
            sb.append(this.setConditions(conditionsDto.getKeyword3()));

            // キーワード4が入力されている場合
            sb.append(this.setConditions(conditionsDto.getKeyword4()));

            // キーワード5が入力されている場合
            sb.append(this.setConditions(conditionsDto.getKeyword5()));

            sb.append(") ");
        }

        // 2014/04/22 Add Start 検索条件の追加(コース名)対応
        if (!StringUtils.isEmpty(conditionsDto.getBigClassificationCd())
                && !StringUtils.equals(NaikaraTalkConstants.CHOICE_ALL_ZERO, conditionsDto.getBigClassificationCd()) ) {
            sb.append("and BIG_CLASSIFICATION_CD = ?");
        }
        if (!StringUtils.isEmpty(conditionsDto.getMiddleClassificationCd())
                && !StringUtils.equals(NaikaraTalkConstants.CHOICE_ALL_ZERO, conditionsDto.getMiddleClassificationCd()) ) {
            sb.append("and MIDDLE_CLASSIFICATION_CD = ?");
        }
        if (!StringUtils.isEmpty(conditionsDto.getSmallClassificationCd())
                && !StringUtils.equals(NaikaraTalkConstants.CHOICE_ALL_ZERO, conditionsDto.getSmallClassificationCd()) ) {
            sb.append("and SMALL_CLASSIFICATION_CD = ?");
        }
        if (!StringUtils.isEmpty(conditionsDto.getCourseJnm())) {
            sb.append("and COURSE_JNM LIKE ?");
        }
        // 2014/04/22 Add End   検索条件の追加(コース名)対応


        if (!StringUtils.isEmpty(conditions.getConditionString())) {
            sb.append("and ");
            sb.append(conditions.getConditionString());
        }

        try {
            PreparedStatement ps = conn.prepareStatement(sb.toString());

            // パラメタの設定
            int i = 0;

            // キーワード１が入力されている場合
            i = this.setConditionsValue(ps, conditionsDto.getKeyword1(), i);

            // キーワード2が入力されている場合
            i = this.setConditionsValue(ps, conditionsDto.getKeyword2(), i);

            // キーワード3が入力されている場合
            i = this.setConditionsValue(ps, conditionsDto.getKeyword3(), i);

            // キーワード4が入力されている場合
            i = this.setConditionsValue(ps, conditionsDto.getKeyword4(), i);

            // キーワード5が入力されている場合
            i = this.setConditionsValue(ps, conditionsDto.getKeyword5(), i);

            // 2014/04/22 Add Start 検索条件の追加(コース名)対応
            if (!StringUtils.isEmpty(conditionsDto.getBigClassificationCd())
                    && !StringUtils.equals(NaikaraTalkConstants.CHOICE_ALL_ZERO, conditionsDto.getBigClassificationCd()) ) {
                ps.setString(i + 1, conditionsDto.getBigClassificationCd());     // 大分類コード
                i++;
            }
            if (!StringUtils.isEmpty(conditionsDto.getMiddleClassificationCd())
                    && !StringUtils.equals(NaikaraTalkConstants.CHOICE_ALL_ZERO, conditionsDto.getMiddleClassificationCd()) ) {
                ps.setString(i + 1, conditionsDto.getMiddleClassificationCd());  // 中分類コード
                i++;
            }
            if (!StringUtils.isEmpty(conditionsDto.getSmallClassificationCd())
                    && !StringUtils.equals(NaikaraTalkConstants.CHOICE_ALL_ZERO, conditionsDto.getSmallClassificationCd()) ) {
                ps.setString(i + 1, conditionsDto.getSmallClassificationCd());   // 小分類コード
                i++;
            }
            if (!StringUtils.isEmpty(conditionsDto.getCourseJnm())) {
                StringBuffer courseNm = new StringBuffer();
                courseNm.append(NaikaraTalkConstants.OPERATOR_PERCENT);
                courseNm.append(conditionsDto.getCourseJnm());
                courseNm.append(NaikaraTalkConstants.OPERATOR_PERCENT);
                ps.setString(i + 1, courseNm.toString());                        // コース名
                i++;
            }
            // 2014/04/22 Add End   検索条件の追加(コース名)対応

            for (QueryCondition cond : conditions.getConditions()) {
                for (String val : cond.getValues()) {
                    ps.setString(i + 1, val);
                    i++;
                }
            }

            // 実行
            res = ps.executeQuery();

            rowCount = NaikaraTalkConstants.RETURN_CD_DATA_ERR; // データ件数
            if (res.next()) {
                rowCount = res.getInt(1);
            }

            return rowCount;

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
     * 検索条件の設定。<br>
     * <br>
     * 検索条件の設定。<br>
     * <br>
     * @param conditions<br>
     * @return String<br>
     * @exception なし
     */
    private String setConditions(String conditions) {
        if (!StringUtils.isEmpty(conditions)) {
            return "or KEYWORD1 like ? or KEYWORD2 like ? or KEYWORD3 like ? or KEYWORD4 like ? or KEYWORD5 like ? ";
        }
        return NaikaraTalkConstants.BRANK;
    }

    /**
     * 検索条件値の設定。<br>
     * <br>
     * 検索条件値の設定。<br>
     * <br>
     * @param ps <br>
     * @param conditions <br>
     * @param index <br>
     * @return int <br>
     * @throws SQLException
     */
    private int setConditionsValue(PreparedStatement ps, String conditions, int index) throws SQLException {
        if (!StringUtils.isEmpty(conditions)) {
            for (int j = 0; j < 5; j++) {
                ps.setString(
                        index + 1,
                        new StringBuffer().append(NaikaraTalkConstants.OPERATOR_PERCENT).append(conditions)
                                .append(NaikaraTalkConstants.OPERATOR_PERCENT).toString());
                index++;
            }
        }
        return index;
    }

    /**
     * コースマスタデータ取得<br>
     * <br>
     * コースマスタリストを戻り値で返却する<br>
     * <br>
     * @param conditions
     * @param OrderBy
     * @return ArrayList<CourseMstDto>
     * @throws NaiException
     */
    public ArrayList<CourseMstDto> searchWithNm(ConditionMapper conditions, OrderCondition OrderBy) throws NaiException {

        // 戻り値とリターンコードの初期化
        ArrayList<CourseMstDto> list = null; // DTOリスト
        CourseMstDto dto = new CourseMstDto();

        ResultSet res = null;

        StringBuffer sb = new StringBuffer();

        // コースマスタの全項目取得処理
        sb.append(" SELECT ");
        sb.append("  COURSE_CD ");
        sb.append(" ,BIG_CLASSIFICATION_CD ");
        sb.append(" ,MIDDLE_CLASSIFICATION_CD ");
        sb.append(" ,SMALL_CLASSIFICATION_CD ");
        sb.append(" ,COURSE_JNM ");
        sb.append(" ,COURSE_ENM ");
        sb.append(" ,ATTACHMENT_FLG ");
        sb.append(" ,KEYWORD1 ");
        sb.append(" ,KEYWORD2 ");
        sb.append(" ,KEYWORD3 ");
        sb.append(" ,KEYWORD4 ");
        sb.append(" ,KEYWORD5 ");
        sb.append(" ,SIMPLE_EXPLANATION ");
        sb.append(" ,EXPLANATION1 ");
        sb.append(" ,EXPLANATION2 ");
        sb.append(" ,EXPLANATION3 ");
        sb.append(" ,EXPLANATION4 ");
        sb.append(" ,EXPLANATION5 ");
        sb.append(" ,EXPLANATION6_NM ");
        sb.append(" ,EXPLANATION6 ");
        sb.append(" ,LESSON_TIME ");
        sb.append(" ,USE_STR_DT ");
        sb.append(" ,USE_END_DT ");
        sb.append(" ,BOOK_FLG ");
        sb.append(" ,REMARK ");
        sb.append(" ,RECORD_VER_NO ");
        sb.append(" ,INSERT_DTTM ");
        sb.append(" ,INSERT_CD ");
        sb.append(" ,UPDATE_DTTM ");
        sb.append(" ,UPDATE_CD ");
        sb.append(" FROM ");
        sb.append("   COURSE_MST ");
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
            LinkedHashMap<String, CodeManagMstDto> bigClassificationCdList = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_BIG_CLASSIFICATION);
            LinkedHashMap<String, CodeManagMstDto> middleClassificationCdList = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_MIDDLE_CLASSIFICATION);
            LinkedHashMap<String, CodeManagMstDto> smallClassificationCdList = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_SMALL_CLASSIFICATION);

            list = new ArrayList<CourseMstDto>();
            while (res.next()) {
                dto = new CourseMstDto();

                dto.setCourseCd(res.getString("COURSE_CD"));
                dto.setBigClassificationCd(res.getString("BIG_CLASSIFICATION_CD"));
                // 大分類名
                dto.setBigClassificationCdNm(bigClassificationCdList.get(res.getString("BIG_CLASSIFICATION_CD"))
                        .getManagerNm());
                dto.setMiddleClassificationCd(res.getString("MIDDLE_CLASSIFICATION_CD"));
                // 中分類名
                dto.setMiddleClassificationCdNm(middleClassificationCdList.get(
                        res.getString("MIDDLE_CLASSIFICATION_CD")).getManagerNm());
                dto.setSmallClassificationCd(res.getString("SMALL_CLASSIFICATION_CD"));
                // 小分類名
                dto.setSmallClassificationCdNm(smallClassificationCdList.get(res.getString("SMALL_CLASSIFICATION_CD"))
                        .getManagerNm());
                dto.setCourseJnm(res.getString("COURSE_JNM"));
                dto.setCourseEnm(res.getString("COURSE_ENM"));
                dto.setAttachmentFlg(res.getString("ATTACHMENT_FLG"));
                dto.setKeyword1(res.getString("KEYWORD1"));
                dto.setKeyword2(res.getString("KEYWORD2"));
                dto.setKeyword3(res.getString("KEYWORD3"));
                dto.setKeyword4(res.getString("KEYWORD4"));
                dto.setKeyword5(res.getString("KEYWORD5"));
                dto.setSimpleExplanation(res.getString("SIMPLE_EXPLANATION"));
                dto.setExplanation1(res.getString("EXPLANATION1"));
                dto.setExplanation2(res.getString("EXPLANATION2"));
                dto.setExplanation3(res.getString("EXPLANATION3"));
                dto.setExplanation4(res.getString("EXPLANATION4"));
                dto.setExplanation5(res.getString("EXPLANATION5"));
                dto.setExplanation6Nm(res.getString("EXPLANATION6_NM"));
                dto.setExplanation6(res.getBlob("EXPLANATION6"));
                dto.setLessonTime(res.getInt("LESSON_TIME"));
                dto.setUseStrDt(res.getString("USE_STR_DT"));
                dto.setUseEndDt(res.getString("USE_END_DT"));
                dto.setBookFlg(res.getString("BOOK_FLG"));
                dto.setRemark(res.getString("REMARK"));
                dto.setRecordVerNo(res.getInt("RECORD_VER_NO"));
                dto.setInsertDttm(res.getTimestamp("INSERT_DTTM"));
                dto.setInsertCd(res.getString("INSERT_CD"));
                dto.setUpdateDttm(res.getTimestamp("UPDATE_DTTM"));
                dto.setUpdateCd(res.getString("UPDATE_CD"));

                dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);

                list.add(dto);
            }

            if (list.size() < 1) {
                list = new ArrayList<CourseMstDto>();
                dto = new CourseMstDto();
                dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_NO);
                list.add(dto);
                return list;
            }

            ps.close();

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
    public int insert(CourseMstDto dto) throws NaiException {

        // この画面を開いたロールを取得します(この段階に到達した時点でユーザ情報は存在している)
        String userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();

        int insertRowCount = NaikaraTalkConstants.RETURN_CD_ERR_INS; // 追加データ件数

        // SQL文作成
        StringBuffer sb = new StringBuffer();
        sb.append(" insert into COURSE_MST");
        sb.append(" ( ");
        sb.append("  COURSE_CD ");
        sb.append(" ,BIG_CLASSIFICATION_CD ");
        sb.append(" ,MIDDLE_CLASSIFICATION_CD ");
        sb.append(" ,SMALL_CLASSIFICATION_CD ");
        sb.append(" ,COURSE_JNM ");
        sb.append(" ,COURSE_ENM ");
        sb.append(" ,ATTACHMENT_FLG ");
        sb.append(" ,KEYWORD1 ");
        sb.append(" ,KEYWORD2 ");
        sb.append(" ,KEYWORD3 ");
        sb.append(" ,KEYWORD4 ");
        sb.append(" ,KEYWORD5 ");
        sb.append(" ,SIMPLE_EXPLANATION ");
        sb.append(" ,EXPLANATION1 ");
        sb.append(" ,EXPLANATION2 ");
        sb.append(" ,EXPLANATION3 ");
        sb.append(" ,EXPLANATION4 ");
        sb.append(" ,EXPLANATION5 ");
        sb.append(" ,EXPLANATION6_NM ");
        sb.append(" ,EXPLANATION6 ");
        sb.append(" ,LESSON_TIME ");
        sb.append(" ,USE_STR_DT ");
        sb.append(" ,USE_END_DT ");
        sb.append(" ,BOOK_FLG ");
        sb.append(" ,REMARK ");
        sb.append(" ,RECORD_VER_NO ");
        sb.append(" ,INSERT_DTTM ");
        sb.append(" ,INSERT_CD ");
        sb.append(" ,UPDATE_DTTM ");

        // 2014/04/22 Add Start 検索条件の追加(短コース名)対応
        //sb.append(" ,UPDATE_CD) ");
        sb.append(" ,UPDATE_CD ");
        sb.append(" ,SHORT_COURSE_JNM ");
        sb.append(" ) ");
        // 2014/04/22 Add End   検索条件の追加(短コース名)対応

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
        // 2014/04/22 Add Start 検索条件の追加(短コース名)対応
        //sb.append(" ,?) ");
        sb.append(" ,? ");       // UPDATE_CD
        sb.append(" ,? ");       // SHORT_COURSE_JNM
        sb.append(" ) ");
        // 2014/04/22 Add End   検索条件の追加(短コース名)対応

        PreparedStatement ps = null;
        // BLOG用
        FileInputStream fis1 = null;

        try {

            ps = conn.prepareStatement(sb.toString());
            ps.setString(1, dto.getCourseCd());
            ps.setString(2, dto.getBigClassificationCd());
            ps.setString(3, dto.getMiddleClassificationCd());
            ps.setString(4, dto.getSmallClassificationCd());
            ps.setString(5, dto.getCourseJnm());
            ps.setString(6, dto.getCourseEnm());
            ps.setString(7, dto.getAttachmentFlg());
            ps.setString(8, dto.getKeyword1());
            ps.setString(9, dto.getKeyword2());
            ps.setString(10, dto.getKeyword3());
            ps.setString(11, dto.getKeyword4());
            ps.setString(12, dto.getKeyword5());
            ps.setString(13, dto.getSimpleExplanation());
            ps.setString(14, dto.getExplanation1());
            ps.setString(15, dto.getExplanation2());
            ps.setString(16, dto.getExplanation3());
            ps.setString(17, dto.getExplanation4());
            ps.setString(18, dto.getExplanation5());
            ps.setString(19, dto.getExplanation6Nm());
            if (dto.getExplanationPDF() == null) {
                ps.setNull(20, java.sql.Types.BLOB);
            } else {
                try {
                    fis1 = new FileInputStream(dto.getExplanationPDF());
                    ps.setBinaryStream(20, fis1, fis1.available());
                } catch (IOException se) {
                    throw new NaiException(se);
                }
            }
            ps.setInt(21, dto.getLessonTime());
            ps.setString(22, dto.getUseStrDt());
            ps.setString(23, dto.getUseEndDt());
            ps.setString(24, dto.getBookFlg());
            ps.setString(25, dto.getRemark());
            ps.setString(26, userId);
            ps.setString(27, userId);

            // 2014/04/22 Add Start 検索条件の追加(短コース名)対応
            ps.setString(28, dto.getCourseJnmShort());
            // 2014/04/22 Add End   検索条件の追加(短コース名)対応

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
     * @param CourseMstDto 画面のパラメータ<br>
     * @return updatedRowCount<br>
     * @throws NaiException
     */
    public int update(CourseMstDto dto) throws NaiException {

        // この画面を開いたロールを取得します(この段階に到達した時点でユーザ情報は存在している)
        String userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();

        int updatedRowCount = NaikaraTalkConstants.RETURN_CD_ERR_UPD; // 更新データ件数

        // 利用者マスタのデータチェック
        if (!chkDto(dto)) {
            return updatedRowCount;
        }

        StringBuffer sb = new StringBuffer();

        // データ更新処理
        sb.append("update ");
        sb.append("COURSE_MST ");
        sb.append("set ");
        sb.append(" BIG_CLASSIFICATION_CD = ? ");
        sb.append(" ,MIDDLE_CLASSIFICATION_CD = ? ");
        sb.append(" ,SMALL_CLASSIFICATION_CD = ? ");
        sb.append(" ,COURSE_JNM = ? ");
        sb.append(" ,COURSE_ENM = ? ");
        sb.append(" ,ATTACHMENT_FLG = ? ");
        sb.append(" ,KEYWORD1 = ? ");
        sb.append(" ,KEYWORD2 = ? ");
        sb.append(" ,KEYWORD3 = ? ");
        sb.append(" ,KEYWORD4 = ? ");
        sb.append(" ,KEYWORD5 = ? ");
        sb.append(" ,SIMPLE_EXPLANATION = ? ");
        sb.append(" ,EXPLANATION1 = ? ");
        sb.append(" ,EXPLANATION2 = ? ");
        sb.append(" ,EXPLANATION3 = ? ");
        sb.append(" ,EXPLANATION4 = ? ");
        sb.append(" ,EXPLANATION5 = ? ");
        if (StringUtils.equals(NaikaraTalkConstants.TRUE, dto.getFileChkn()) || dto.getExplanationPDF() != null) {
            sb.append(" ,EXPLANATION6_NM = ? ");
            sb.append(" ,EXPLANATION6 = ? ");
        }
        sb.append(" ,LESSON_TIME = ? ");
        sb.append(" ,USE_STR_DT = ? ");
        sb.append(" ,USE_END_DT = ? ");
        sb.append(" ,BOOK_FLG = ? ");
        sb.append(" ,REMARK = ? ");
        sb.append(" ,RECORD_VER_NO = RECORD_VER_NO + 1 ");
        sb.append(" ,UPDATE_DTTM = sysdate() ");
        sb.append(" ,UPDATE_CD = ? ");

        // 2014/04/22 Add Start 検索条件の追加(短コース名)対応
        sb.append(" ,SHORT_COURSE_JNM = ? ");
        // 2014/04/22 Add End   検索条件の追加(短コース名)対応

        sb.append("where ");
        sb.append("COURSE_CD = ? ");
        sb.append("and ");
        sb.append("RECORD_VER_NO = ? ");

        // BLOG用
        FileInputStream fis1 = null;
        try {

            PreparedStatement ps = conn.prepareStatement(sb.toString());

            int index = 1;
            ps.setString(index++, dto.getBigClassificationCd());
            ps.setString(index++, dto.getMiddleClassificationCd());
            ps.setString(index++, dto.getSmallClassificationCd());
            ps.setString(index++, dto.getCourseJnm());
            ps.setString(index++, dto.getCourseEnm());
            ps.setString(index++, dto.getAttachmentFlg());
            ps.setString(index++, dto.getKeyword1());
            ps.setString(index++, dto.getKeyword2());
            ps.setString(index++, dto.getKeyword3());
            ps.setString(index++, dto.getKeyword4());
            ps.setString(index++, dto.getKeyword5());
            ps.setString(index++, dto.getSimpleExplanation());
            ps.setString(index++, dto.getExplanation1());
            ps.setString(index++, dto.getExplanation2());
            ps.setString(index++, dto.getExplanation3());
            ps.setString(index++, dto.getExplanation4());
            ps.setString(index++, dto.getExplanation5());

            if (dto.getExplanationPDF() != null) {
                ps.setString(index++, dto.getExplanation6Nm());
                try {
                    fis1 = new FileInputStream(dto.getExplanationPDF());
                    ps.setBinaryStream(index++, fis1, fis1.available());
                } catch (IOException se) {
                    throw new NaiException(se);
                }
            }
            if (StringUtils.equals(NaikaraTalkConstants.TRUE, dto.getFileChkn()) && dto.getExplanationPDF() == null) {
                ps.setString(index++, NaikaraTalkConstants.BRANK);
                ps.setNull(index++, java.sql.Types.BLOB);
            }
            ps.setInt(index++, dto.getLessonTime());
            ps.setString(index++, dto.getUseStrDt());
            ps.setString(index++, dto.getUseEndDt());
            ps.setString(index++, dto.getBookFlg());
            ps.setString(index++, dto.getRemark());
            ps.setString(index++, userId);

            // 2014/04/22 Add Start 検索条件の追加(短コース名)対応
            ps.setString(index++, dto.getCourseJnmShort());
            // 2014/04/22 Add End   検索条件の追加(短コース名)対応

            ps.setString(index++, dto.getCourseCd());    // 検索条件：コース名
            ps.setInt(index++, dto.getRecordVerNo());    // 検索条件：レコードバージョン番号

            updatedRowCount = ps.executeUpdate();

            return updatedRowCount;

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
    }

    /**
     * コースマスタ更新データチェック<br>
     * <br>
     * コースマスタの更新データをチェックし<br>
     * エラー有の場合は、falseを返す<br>
     * エラー無の場合は、trueを返す<br>
     * <br>
     * @param UserMstDto
     * @return boolean
     */
    private boolean chkDto(CourseMstDto dto) throws NaiException {

        // コースコード
        if (StringUtils.isEmpty(dto.getCourseCd())) {
            return false;
        }

        // 大分類コード
        if (StringUtils.isEmpty(dto.getBigClassificationCd())) {
            return false;
        }

        // 中分類コード
        if (StringUtils.isEmpty(dto.getMiddleClassificationCd())) {
            return false;
        }

        // 小分類コード
        if (StringUtils.isEmpty(dto.getSmallClassificationCd())) {
            return false;
        }

        // コース名
        if (StringUtils.isEmpty(dto.getCourseJnm())) {
            return false;
        }

        // 2014/04/22 Add Start 検索条件の追加(短コース名)対応
        // 短コース名
        if (StringUtils.isEmpty(dto.getCourseJnmShort())) {
            return false;
        }
        // 2014/04/22 Add End   検索条件の追加(短コース名)対応

        // コース名(英語名)
        if (StringUtils.isEmpty(dto.getCourseEnm())) {
            return false;
        }

        // 添付付き有無フラグ
        if (StringUtils.isEmpty(dto.getAttachmentFlg())) {
            return false;
        }

        // 簡易説明
        if (StringUtils.isEmpty(dto.getSimpleExplanation())) {
            return false;
        }

        // 提供開始日
        if (StringUtils.isEmpty(dto.getUseStrDt())) {
            return false;
        }

        // 提供終了日
        if (StringUtils.isEmpty(dto.getUseEndDt())) {
            return false;
        }

        // 該当書籍有無フラグ
        if (StringUtils.isEmpty(dto.getBookFlg())) {
            return false;
        }

        return true;

    }
}