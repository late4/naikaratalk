package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedHashMap;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.TeacherLessonManagementLogic;
import com.naikara_talk.model.TeacherLessonManagementModel;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>講師。<br>
 * <b>クラス名称　　　:</b>講師側の視点から_1-1_レッスン実績初期処理Serviceクラス。<br>
 * <b>クラス概要　　　:</b>講師側の視点から_1-1_レッスン実績初期処理Service。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/12 TECS 新規作成。
 */
public class TeacherLessonManagementLoadService implements ActionService {

    /**
     * 初期処理<br>
     * <br>
     * 画面項目の初期処理を行う<br>
     * <br>
     * @param なし<br>
     * @return model 画面パラメータ<br>
     * @exception なし
     */
    public TeacherLessonManagementModel load() {

        // モデルの初期化
        TeacherLessonManagementModel model = new TeacherLessonManagementModel();

        // 抽出開始日はシステム日付を設定
        model.setPeriodDtFr(NaikaraStringUtil.converToYYYY_MM_DD(DateUtil.getSysDate()));

        // 抽出終了日はシステム日付を設定
        model.setPeriodDtTo(NaikaraStringUtil.converToYYYY_MM_DD(DateUtil.getSysDate()));

        // 大分類は一件目を設定
        model.setCourseLarge(NaikaraTalkConstants.CHOICE_ALL_ZERO);

        // 中分類は一件目を設定
        model.setCourseMedium(NaikaraTalkConstants.CHOICE_ALL_ZERO);

        // 小分類は一件目を設定
        model.setCourseSmall(NaikaraTalkConstants.CHOICE_ALL_ZERO);

        return model;
    }

    /**
     * コード取得<br>
     * <br>
     * コード管理マスタからデータ取得処理<br>
     * <br>
     * @param category 汎用コード<br>
     * @return LinkedHashMap 取得結果<br>
     * @exception NaiException
     */
    public LinkedHashMap<String, String> selectCodeMst(String category) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // ロジックの初期化
            TeacherLessonManagementLogic teacherLessonManagementLogic = new TeacherLessonManagementLogic(conn);

            // コード管理マスタを検索
            LinkedHashMap<String, String> codeMap = teacherLessonManagementLogic.selectCodeMst(category);

            conn.commit();
            return codeMap;
        } catch (SQLException se) {
            se.printStackTrace();
            throw new NaiException(se);
        } finally {
            try {
                conn.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * コード取得<br>
     * <br>
     * コード管理マスタからデータ取得処理<br>
     * <br>
     * @param category 汎用コード<br>
     * @return LinkedHashMap 取得結果<br>
     * @exception NaiException
     */
    public LinkedHashMap<String, String> getCourseSell(String eCategory, String jCategory) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // ロジックの初期化
            TeacherLessonManagementLogic teacherLessonManagementLogic = new TeacherLessonManagementLogic(conn);

            // コード管理マスタを検索
            LinkedHashMap<String, String> eMap = teacherLessonManagementLogic.selectCodeMst(eCategory);

            // コード管理マスタを検索
            LinkedHashMap<String, String> jMap = teacherLessonManagementLogic.selectCodeMst(jCategory);

            LinkedHashMap<String, String> hashMap = new LinkedHashMap<String, String>();

            Iterator<String> eIter = eMap.keySet().iterator();

            Iterator<String> jIter = jMap.keySet().iterator();

            while (eIter.hasNext()) {

                jIter.hasNext();

                String key = eIter.next();

                hashMap.put(key, NaikaraStringUtil.unionString2(eMap.get(key), jMap.get(key)));
            }

            conn.commit();
            return hashMap;
        } catch (SQLException se) {
            se.printStackTrace();
            throw new NaiException(se);
        } finally {
            try {
                conn.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}