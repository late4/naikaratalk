package com.naikara_talk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.CourseUsePointListDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DAOクラス<br>
 * <b>クラス名称　　　:</b>コース別利用ポイント一覧取得クラス<br>
 * <b>クラス概要　　　:</b>コース別利用ポイント一覧取得DAO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/06/04 TECS 新規作成
 * <b>　　　　　　　　:</b>2014/03/28 TECS 変更：searchメソッドに、コース名の編集時のNull回避処理を追加
 * <b>　　　　　　　　:</b>2014/04/22 TECS 検索条件：コース名の追加対応(search(lessonDt,keyword1,keyword2,keyword3,keyword4,keyword5)メソッド)
 *
 */
public class CourseUsePointListDao extends AbstractDao {

    protected Logger log = Logger.getLogger(this.getClass());

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public CourseUsePointListDao(Connection con) {
        this.conn = con;
    }

    /** 利用開始日 条件項目 */
    public static final String USE_STR_DT = "USE_STR_DT";
    /** 利用終了日 条件項目 */
    public static final String USE_END_DT = "USE_END_DT";
    /** キーワード検索１ 条件項目 */
    public static final String KEYWORD1 = "KEYWORD1";
    /** キーワード検索２ 条件項目 */
    public static final String KEYWORD2 = "KEYWORD2";
    /** キーワード検索３ 条件項目 */
    public static final String KEYWORD3 = "KEYWORD3";
    /** キーワード検索４ 条件項目 */
    public static final String KEYWORD4 = "KEYWORD4";
    /** キーワード検索５ 条件項目 */
    public static final String KEYWORD5 = "KEYWORD5";
    /** コースコード 　並び順　*/
    public static final String COURSE_CD = "COURSE_CD";

    /**
     * コース別利用ポイント一覧取得<br>
     * <br>
     * コース別利用ポイント一覧を戻り値で返却する<br>
     * <br>
     * @param lessonDt
     * @param keyword1
     * @param keyword2
     * @param keyword3
     * @param keyword4
     * @param keyword5
     * @param bigClassificationCd
     * @param middleClassificationCd
     * @param smallClassificationCd
     * @param courseJnm
     * @return ArrayList<CourseUsePointListDto>
     * @throws NaiException
     */
    public ArrayList<CourseUsePointListDto> search(String lessonDt, String keyword1, String keyword2, String keyword3,
            String keyword4, String keyword5,
            String bigClassificationCd, String middleClassificationCd, String smallClassificationCd, String courseJnm
        ) throws NaiException {

        // 戻り値とリターンコードの初期化
        ArrayList<CourseUsePointListDto> list = null; // DTOリスト
        CourseUsePointListDto dto = null; // DTO

        // キーワード1～5のnullを空文字に変換
        keyword1 = String.valueOf(NaikaraStringUtil.nvlString(keyword1));
        keyword2 = String.valueOf(NaikaraStringUtil.nvlString(keyword2));
        keyword3 = String.valueOf(NaikaraStringUtil.nvlString(keyword3));
        keyword4 = String.valueOf(NaikaraStringUtil.nvlString(keyword4));
        keyword5 = String.valueOf(NaikaraStringUtil.nvlString(keyword5));

        ResultSet res = null;

        StringBuffer sb = new StringBuffer();

        // データ取得処理
        sb.append(" SELECT ");
        sb.append("   CMT.COURSE_CD ");
        sb.append("  ,CMT.BIG_CLASSIFICATION_CD ");
        sb.append("  ,CMT.MIDDLE_CLASSIFICATION_CD ");
        sb.append("  ,CMT.SMALL_CLASSIFICATION_CD ");
        sb.append("  ,CMT.COURSE_JNM ");
        sb.append("  ,CMT.SIMPLE_EXPLANATION ");
        sb.append("  ,CUP.USE_POINT ");
        sb.append("  ,CUP.INFORMATION ");
        sb.append(" FROM COURSE_MST  AS CMT ");

        sb.append("   LEFT JOIN (SELECT COURSE_CD, USE_POINT, INFORMATION ");
        sb.append("               FROM COURSE_USE_POINT_MST ");
        sb.append("              WHERE USE_POINT_STR_DT <= ? ");
        sb.append("                AND USE_POINT_END_DT >= ? ");
        sb.append("             ) AS CUP ");

        sb.append(" ON CMT.COURSE_CD = CUP.COURSE_CD ");

        sb.append("  WHERE ");
        sb.append("    CMT.USE_STR_DT <= ? ");
        sb.append("  AND ");
        sb.append("    CMT.USE_END_DT >= ? ");

        boolean inFlg = false;
        if (!StringUtils.isEmpty(keyword1)
                || !StringUtils.isEmpty(keyword2)
                || !StringUtils.isEmpty(keyword3)
                || !StringUtils.isEmpty(keyword4)
                || !StringUtils.isEmpty(keyword5)) {
            sb.append("  AND ");
            sb.append("   ( ");
            inFlg = true;
        }

        boolean firstFlg = false;
        if (!StringUtils.isEmpty(keyword1)) {
            sb = this.orUnion(firstFlg, sb);
            firstFlg = true;
        }

        if (!StringUtils.isEmpty(keyword2)) {
            sb = this.orUnion(firstFlg, sb);
            firstFlg = true;
        }

        if (!StringUtils.isEmpty(keyword3)) {
            sb = this.orUnion(firstFlg, sb);
            firstFlg = true;
        }

        if (!StringUtils.isEmpty(keyword4)) {
            sb = this.orUnion(firstFlg, sb);
            firstFlg = true;
        }

        if (!StringUtils.isEmpty(keyword5)) {
            sb = this.orUnion(firstFlg, sb);
            firstFlg = true;
        }

        if (inFlg) {
            sb.append("  ) ");
        }

        // 2014/04/22 Add Start 検索条件の追加(コース名)対応
        if (!StringUtils.isEmpty(bigClassificationCd)
                && !StringUtils.equals(NaikaraTalkConstants.CHOICE_ALL_ZERO, bigClassificationCd) ) {
            sb.append(" and BIG_CLASSIFICATION_CD = ? ");
        }
        if (!StringUtils.isEmpty(middleClassificationCd)
                && !StringUtils.equals(NaikaraTalkConstants.CHOICE_ALL_ZERO, middleClassificationCd) ) {
            sb.append(" and MIDDLE_CLASSIFICATION_CD = ? ");
        }
        if (!StringUtils.isEmpty(smallClassificationCd)
                && !StringUtils.equals(NaikaraTalkConstants.CHOICE_ALL_ZERO, smallClassificationCd) ) {
            sb.append(" and SMALL_CLASSIFICATION_CD = ? ");
        }
        if (!StringUtils.isEmpty(courseJnm)) {
            sb.append(" and COURSE_JNM LIKE ? ");
        }
        // 2014/04/22 Add End   検索条件の追加(コース名)対応


/*
        sb.append("   (CONCAT(CMT.KEYWORD1,'/',CMT.KEYWORD2,'/',CMT.KEYWORD3,'/',CMT.KEYWORD4,'/',CMT.KEYWORD5) LIKE ?  ");
        sb.append("   OR ");
        sb.append("    CONCAT(CMT.KEYWORD1,'/',CMT.KEYWORD2,'/',CMT.KEYWORD3,'/',CMT.KEYWORD4,'/',CMT.KEYWORD5) LIKE ?  ");
        sb.append("   OR ");
        sb.append("    CONCAT(CMT.KEYWORD1,'/',CMT.KEYWORD2,'/',CMT.KEYWORD3,'/',CMT.KEYWORD4,'/',CMT.KEYWORD5) LIKE ?  ");
        sb.append("   OR ");
        sb.append("    CONCAT(CMT.KEYWORD1,'/',CMT.KEYWORD2,'/',CMT.KEYWORD3,'/',CMT.KEYWORD4,'/',CMT.KEYWORD5) LIKE ?  ");
        sb.append("   OR ");
        sb.append("    CONCAT(CMT.KEYWORD1,'/',CMT.KEYWORD2,'/',CMT.KEYWORD3,'/',CMT.KEYWORD4,'/',CMT.KEYWORD5) LIKE ? ) ");
*/
        sb.append("  ORDER BY CMT.COURSE_CD ");

        try {

            PreparedStatement ps = conn.prepareStatement(sb.toString());

            ps.setString(1, lessonDt);
            ps.setString(2, lessonDt);

            ps.setString(3, lessonDt);
            ps.setString(4, lessonDt);

            int j = 4;
            StringBuffer sbP = new StringBuffer();
            for (int i = 0; i < 5; i++){
                if (!StringUtils.isEmpty(keyword1)) {
                    j++;
                    sbP = new StringBuffer();
                    sbP = sbP.append(NaikaraTalkConstants.OPERATOR_PERCENT).append(keyword1)
                        .append(NaikaraTalkConstants.OPERATOR_PERCENT);
                    ps.setString(j, sbP.toString());
                }
            }

            for (int i = 0; i < 5; i++){
                if (!StringUtils.isEmpty(keyword2)) {
                    j++;
                    sbP = new StringBuffer();
                    sbP = sbP.append(NaikaraTalkConstants.OPERATOR_PERCENT).append(keyword2)
                            .append(NaikaraTalkConstants.OPERATOR_PERCENT);
                    ps.setString(j, sbP.toString());
                }
            }

            for (int i = 0; i < 5; i++){
                if (!StringUtils.isEmpty(keyword3)) {
                    j++;
                    sbP = new StringBuffer();
                    sbP = sbP.append(NaikaraTalkConstants.OPERATOR_PERCENT).append(keyword3)
                            .append(NaikaraTalkConstants.OPERATOR_PERCENT);
                    ps.setString(j, sbP.toString());
                }
            }

            for (int i = 0; i < 5; i++){
                if (!StringUtils.isEmpty(keyword4)) {
                    j++;
                    sbP = new StringBuffer();
                    sbP = sbP.append(NaikaraTalkConstants.OPERATOR_PERCENT).append(keyword4)
                            .append(NaikaraTalkConstants.OPERATOR_PERCENT);
                    ps.setString(j, sbP.toString());
                }
            }

            for (int i = 0; i < 5; i++){
                if (!StringUtils.isEmpty(keyword5)) {
                    j++;
                    sbP = new StringBuffer();
                    sbP = sbP.append(NaikaraTalkConstants.OPERATOR_PERCENT).append(keyword5)
                            .append(NaikaraTalkConstants.OPERATOR_PERCENT);
                    ps.setString(j, sbP.toString());
                }
            }

            // 2014/04/22 Add Start 検索条件の追加(コース名)対応
            if (!StringUtils.isEmpty(bigClassificationCd)
                    && !StringUtils.equals(NaikaraTalkConstants.CHOICE_ALL_ZERO, bigClassificationCd) ) {
                j++;
                ps.setString(j, bigClassificationCd);            // 大分類コード
            }
            if (!StringUtils.isEmpty(middleClassificationCd)
                    && !StringUtils.equals(NaikaraTalkConstants.CHOICE_ALL_ZERO, middleClassificationCd) ) {
                j++;
                ps.setString(j, middleClassificationCd);         // 中分類コード
            }
            if (!StringUtils.isEmpty(smallClassificationCd)
                    && !StringUtils.equals(NaikaraTalkConstants.CHOICE_ALL_ZERO, smallClassificationCd) ) {
                j++;
                ps.setString(j, smallClassificationCd);          // 小分類コード
            }
            if (!StringUtils.isEmpty(courseJnm)) {
                j++;
                sbP = new StringBuffer();
                sbP = sbP.append(NaikaraTalkConstants.OPERATOR_PERCENT).append(courseJnm)
                        .append(NaikaraTalkConstants.OPERATOR_PERCENT);
                ps.setString(j, sbP.toString());                 // コース名
            }
            // 2014/04/22 Add End   検索条件の追加(コース名)対応

            res = ps.executeQuery();

            list = new ArrayList<CourseUsePointListDto>();
            while (res.next()) {

                dto = new CourseUsePointListDto();

                dto.setCourseCd(res.getString("COURSE_CD"));
                dto.setBigClassificationCd(res.getString("BIG_CLASSIFICATION_CD"));
                dto.setMiddleClassificationCd(res.getString("MIDDLE_CLASSIFICATION_CD"));
                dto.setSmallClassificationCd(res.getString("SMALL_CLASSIFICATION_CD"));
                dto.setCourseJnm(res.getString("COURSE_JNM"));
                dto.setSimpleExplanation(res.getString("SIMPLE_EXPLANATION"));
                dto.setUsePoint(res.getBigDecimal("USE_POINT"));
                dto.setInformation(res.getString("INFORMATION"));

                list.add(dto);
            }

            if (list.size() < 1) {
                list = new ArrayList<CourseUsePointListDto>();
                dto = new CourseUsePointListDto();
                dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_NO);
                list.add(dto);
                return list;
            }

            // データ編集を行う
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

            String strCourseNM;
            for (int i = 0; list.size() > i; i++) {
                dto = list.get(i);

                String BigNm = NaikaraTalkConstants.BRANK;
                String MiddleNm = NaikaraTalkConstants.BRANK;
                String SmallNm = NaikaraTalkConstants.BRANK;
                String CourseJnm = NaikaraTalkConstants.BRANK;

                // コース名の編集
                try {
                    BigNm = workBigList.get(dto.getBigClassificationCd()).getManagerNm();
                    MiddleNm = workMidList.get(dto.getMiddleClassificationCd()).getManagerNm();
                    SmallNm = workSmoList.get(dto.getSmallClassificationCd()).getManagerNm();
                    CourseJnm = dto.getCourseJnm();
                } catch (Exception ex1) {
                    log.info(ex1);
                    StringBuffer sbErr = new StringBuffer();
                    sbErr.append("CourseUsePointListDao.search:");
                    sbErr.append("dto.getBigClassificationCd()=").append(dto.getBigClassificationCd()).append(",");
                    sbErr.append("dto.getMiddleClassificationCd()=").append(dto.getMiddleClassificationCd()).append(",");
                    sbErr.append("dto.getSmallClassificationCd()=").append(dto.getSmallClassificationCd()).append(",");
                    sbErr.append("BigClassificationNm=").append(BigNm).append(",");
                    sbErr.append("getMiddleClassificationNm=").append(MiddleNm).append(",");
                    sbErr.append("getSmallClassificationNm=").append(SmallNm).append(",");
                    sbErr.append("CourseJnm=").append(CourseJnm);
                    log.info(sbErr.toString());
                }
                strCourseNM = NaikaraStringUtil.unionString4(BigNm, MiddleNm, SmallNm, CourseJnm);

                dto.setCourseJnm(strCourseNM);

                dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_YES);

                list.set(i, dto);
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
     * Or判定<br>
     * <br>
     * Or判定を行い、文字列結合する<br>
     * <br>
     * @param firstFlg
     * @param sb
     * @return StringBuffer
     */
    private StringBuffer orUnion(boolean firstFlg, StringBuffer sb) {

        if (firstFlg) {
            sb.append(" OR ");
        }else {
            sb.append(" ");
        }
        sb.append("     CMT.KEYWORD1 LIKE ?");
        sb.append(" OR  CMT.KEYWORD2 LIKE ?");
        sb.append(" OR  CMT.KEYWORD3 LIKE ?");
        sb.append(" OR  CMT.KEYWORD4 LIKE ?");
        sb.append(" OR  CMT.KEYWORD5 LIKE ?");

        return sb;
    }

}