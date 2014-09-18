package com.naikara_talk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dbutil.QueryCondition;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.TeacherCourseDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DAOクラス<br>
 * <b>クラス名称　　　:</b>講師別コースマスタ(+コースマスタ)取得クラス<br>
 * <b>クラス概要　　　:</b>講師別コースマスタ(+コースマスタ)リスト取得DAO<br>
 * <br>
 * <b>著作権　　　　　:</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/03 TECS 新規作成
 *  　　　　　　　　　:</b>2014/04/22 TECS 検索条件：短コース名の追加対応(searchメソッド)

 */
public class TeacherCourseDao extends AbstractDao {

    protected Logger log = Logger.getLogger(this.getClass());

    public static final String COND_USER_ID = "TEACHER_COURSE_MST.USER_ID";
    public static final String COND_USE_STR_DT = "COURSE_MST.USE_STR_DT";
    public static final String COND_USE_END_DT = "COURSE_MST.USE_END_DT";

	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public TeacherCourseDao(Connection con) {
		this.conn = con;
	}

    /**
     * 講師別コースマスタ(+コースマスタ)リスト取得<br>
     * <br>
     * 講師別コースマスタ(+コースマスタ)リスト取得を行う<br>
     * <br>
     * @param conditions
     * @param orderConditions
     * @return TeacherCourceDto
     * @throws NaiException
     */
    public List<TeacherCourseDto> search(ConditionMapper conditions, OrderCondition orderConditions)
            throws NaiException {

        ResultSet res = null;

        // 講師別コースマスタ(+コースマスタ)の全項目取得
        StringBuffer sb = new StringBuffer();
        sb.append("select ");
        sb.append(" TEACHER_COURSE_MST.USER_ID ");
        sb.append(",TEACHER_COURSE_MST.COURSE_CD ");
        sb.append(",TEACHER_COURSE_MST.RECORD_VER_NO ");
        sb.append(",TEACHER_COURSE_MST.INSERT_DTTM ");
        sb.append(",TEACHER_COURSE_MST.INSERT_CD ");
        sb.append(",TEACHER_COURSE_MST.UPDATE_DTTM ");
        sb.append(",TEACHER_COURSE_MST.UPDATE_CD ");
        sb.append(",COURSE_MST.BIG_CLASSIFICATION_CD ");
        sb.append(",COURSE_MST.MIDDLE_CLASSIFICATION_CD ");
        sb.append(",COURSE_MST.SMALL_CLASSIFICATION_CD ");
        sb.append(",COURSE_MST.COURSE_JNM ");

        // 2014/04/22 Add Start 検索条件の追加(短コース名)対応
        sb.append(",COURSE_MST.SHORT_COURSE_JNM  ");
        // 2014/04/22 Add End   検索条件の追加(短コース名)対応

        sb.append(",COURSE_MST.COURSE_ENM ");
        sb.append(",COURSE_MST.ATTACHMENT_FLG ");
        sb.append(",COURSE_MST.KEYWORD1 ");
        sb.append(",COURSE_MST.KEYWORD2 ");
        sb.append(",COURSE_MST.KEYWORD3 ");
        sb.append(",COURSE_MST.KEYWORD4 ");
        sb.append(",COURSE_MST.KEYWORD5 ");
        sb.append(",COURSE_MST.SIMPLE_EXPLANATION ");
        sb.append(",COURSE_MST.EXPLANATION1 ");
        sb.append(",COURSE_MST.EXPLANATION2 ");
        sb.append(",COURSE_MST.EXPLANATION3 ");
        sb.append(",COURSE_MST.EXPLANATION4 ");
        sb.append(",COURSE_MST.EXPLANATION5 ");
        sb.append(",COURSE_MST.EXPLANATION6_NM ");
        sb.append(",COURSE_MST.EXPLANATION6 ");
        sb.append(",COURSE_MST.LESSON_TIME ");
        sb.append(",COURSE_MST.USE_STR_DT ");
        sb.append(",COURSE_MST.USE_END_DT ");
        sb.append(",COURSE_MST.BOOK_FLG ");
        sb.append(",COURSE_MST.REMARK ");
        sb.append(",COURSE_MST.RECORD_VER_NO ");
        sb.append(",COURSE_MST.INSERT_DTTM ");
        sb.append(",COURSE_MST.INSERT_CD ");
        sb.append(",COURSE_MST.UPDATE_DTTM ");
        sb.append(",COURSE_MST.UPDATE_CD ");
        sb.append("from TEACHER_COURSE_MST ");
        sb.append("inner join COURSE_MST ");
        sb.append("on TEACHER_COURSE_MST.COURSE_CD = COURSE_MST.COURSE_CD ");

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
            int j = 0;
            for (QueryCondition cond : conditions.getConditions()) {
                for (String val : cond.getValues()) {
                    ps.setString(j + 1, val);
                    j++;
                }
            }

            res = ps.executeQuery();

            ArrayList<TeacherCourseDto> list = new ArrayList<TeacherCourseDto>();

            TeacherCourseDto retDto;

            while (res.next()) {

                retDto = new TeacherCourseDto();

                retDto.setUserId(res.getString("TEACHER_COURSE_MST.USER_ID"));
                retDto.setCourseCd(res.getString("TEACHER_COURSE_MST.COURSE_CD"));
                retDto.setRecordVerNoT(res.getInt("TEACHER_COURSE_MST.RECORD_VER_NO"));
                retDto.setInsertDttmT(res.getTimestamp("TEACHER_COURSE_MST.INSERT_DTTM"));
                retDto.setInsertCdT(res.getString("TEACHER_COURSE_MST.INSERT_CD"));
                retDto.setUpdateDttmT(res.getTimestamp("TEACHER_COURSE_MST.UPDATE_DTTM"));
                retDto.setUpdateCdT(res.getString("TEACHER_COURSE_MST.UPDATE_CD"));

                retDto.setBigClassificationCd(res.getString("COURSE_MST.BIG_CLASSIFICATION_CD"));
                retDto.setMiddleClassificationCd(res.getString("COURSE_MST.MIDDLE_CLASSIFICATION_CD"));
                retDto.setSmallClassificationCd(res.getString("COURSE_MST.SMALL_CLASSIFICATION_CD"));
                retDto.setCourseJnm(res.getString("COURSE_MST.COURSE_JNM"));

                // 2014/04/22 Add Start 検索条件の追加(短コース名)対応
                retDto.setCourseJnmShort(res.getString("COURSE_MST.SHORT_COURSE_JNM"));
                // 2014/04/22 Add End   検索条件の追加(短コース名)対応

                retDto.setCourseEnm(res.getString("COURSE_MST.COURSE_ENM"));
                retDto.setAttachmentFlg(res.getString("COURSE_MST.ATTACHMENT_FLG"));
                retDto.setKeyword1(res.getString("COURSE_MST.KEYWORD1"));
                retDto.setKeyword2(res.getString("COURSE_MST.KEYWORD2"));
                retDto.setKeyword3(res.getString("COURSE_MST.KEYWORD3"));
                retDto.setKeyword4(res.getString("COURSE_MST.KEYWORD4"));
                retDto.setKeyword5(res.getString("COURSE_MST.KEYWORD5"));
                retDto.setSimpleExplanation(res.getString("COURSE_MST.SIMPLE_EXPLANATION"));
                retDto.setExplanation1(res.getString("COURSE_MST.EXPLANATION1"));
                retDto.setExplanation2(res.getString("COURSE_MST.EXPLANATION2"));
                retDto.setExplanation3(res.getString("COURSE_MST.EXPLANATION3"));
                retDto.setExplanation4(res.getString("COURSE_MST.EXPLANATION4"));
                retDto.setExplanation5(res.getString("COURSE_MST.EXPLANATION5"));
                retDto.setExplanation6Nm(res.getString("COURSE_MST.EXPLANATION6_NM"));
                retDto.setExplanation6(res.getBlob("COURSE_MST.EXPLANATION6"));
                retDto.setLessonTime(res.getInt("COURSE_MST.LESSON_TIME"));
                retDto.setUseStrDt(res.getString("COURSE_MST.USE_STR_DT"));
                retDto.setUseEndDt(res.getString("COURSE_MST.USE_END_DT"));
                retDto.setBookFlg(res.getString("COURSE_MST.BOOK_FLG"));
                retDto.setRemark(res.getString("COURSE_MST.REMARK"));
                retDto.setRecordVerNoC(res.getInt("COURSE_MST.RECORD_VER_NO"));
                retDto.setInsertDttmC(res.getTimestamp("COURSE_MST.INSERT_DTTM"));
                retDto.setInsertCdC(res.getString("COURSE_MST.INSERT_CD"));
                retDto.setUpdateDttmC(res.getTimestamp("COURSE_MST.UPDATE_DTTM"));
                retDto.setUpdateCdC(res.getString("COURSE_MST.UPDATE_CD"));

                retDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);

                list.add(retDto);
            }

            if (list.size() < 1) {
                retDto = new TeacherCourseDto();
                retDto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_NO);
                list.add(retDto);
                return list;
            }

            // 汎用フィールド名の取得
            CodeManagMstCache cache = CodeManagMstCache.getInstance();
            // 大分類の名称一覧取得
            LinkedHashMap<String, CodeManagMstDto> workBigList = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_BIG_CLASSIFICATION);

            // 中分類の名称一覧取得
            LinkedHashMap<String, CodeManagMstDto> workMidList = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_MIDDLE_CLASSIFICATION);

            // 小分類の名称一覧取得
            LinkedHashMap<String, CodeManagMstDto> workSmoList = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_SMALL_CLASSIFICATION);

            // 大分類(英語)の名称一覧取得
            LinkedHashMap<String, CodeManagMstDto> workBigListE = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_BIG_CLASSIFICATION_T);

            // 中分類(英語)の名称一覧取得
            LinkedHashMap<String, CodeManagMstDto> workMidListE = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_MIDDLE_CLASSIFICATION_T);

            // 小分類(英語)の名称一覧取得
            LinkedHashMap<String, CodeManagMstDto> workSmoListE = cache
                    .getList(NaikaraTalkConstants.CODE_CATEGORY_SMALL_CLASSIFICATION_T);

            // データ編集
            ArrayList<TeacherCourseDto> rtnList = new ArrayList<TeacherCourseDto>();

            for (int i = 0; list.size() > i; i++) {
                retDto = list.get(i);

                // 各分類コード
                if (workBigList.get(retDto.getBigClassificationCd()) == null) {
                    retDto.setBigClassificationJnm("");
                } else {
                    retDto.setBigClassificationJnm(workBigList.get(retDto.getBigClassificationCd()).getManagerNm());
                }

                if (workMidList.get(retDto.getMiddleClassificationCd()) == null) {
                    retDto.setMiddleClassificationJnm("");
                } else {
                    retDto.setMiddleClassificationJnm(workMidList.get(retDto.getMiddleClassificationCd())
                            .getManagerNm());
                }

                if (workSmoList.get(retDto.getSmallClassificationCd()) == null) {
                    retDto.setSmallClassificationJnm("");
                } else {
                    retDto.setSmallClassificationJnm(workSmoList.get(retDto.getSmallClassificationCd()).getManagerNm());
                }

                if (workBigListE.get(retDto.getBigClassificationCd()) == null) {
                    retDto.setBigClassificationEnm("");
                } else {
                    retDto.setBigClassificationEnm(workBigListE.get(retDto.getBigClassificationCd()).getManagerNm());
                }

                if (workMidListE.get(retDto.getMiddleClassificationCd()) == null) {
                    retDto.setMiddleClassificationEnm("");
                } else {
                    retDto.setMiddleClassificationEnm(workMidListE.get(retDto.getMiddleClassificationCd())
                            .getManagerNm());
                }

                if (workSmoListE.get(retDto.getSmallClassificationCd()) == null) {
                    retDto.setSmallClassificationEnm("");
                } else {
                    retDto.setSmallClassificationEnm(workSmoList.get(retDto.getSmallClassificationCd()).getManagerNm());
                }

                rtnList.add(retDto);
            }

            return rtnList;

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
