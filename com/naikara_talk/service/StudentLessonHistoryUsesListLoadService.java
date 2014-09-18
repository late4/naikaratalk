package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedHashMap;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.StudentLessonHistoryUsesListLogic;
import com.naikara_talk.model.StudentLessonHistoryUsesListModel;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様(個人)_実績照会<br>
 * <b>クラス名称　　　:</b>受講者側の視点から_1-2_レッスン実績初期処理Serviceクラス。<br>
 * <b>クラス概要　　　:</b>受講者側の視点から_1-2_レッスン実績初期処理Service。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/05 TECS 新規作成。
 */
public class StudentLessonHistoryUsesListLoadService implements ActionService {

    /**
     * 初期処理<br>
     * <br>
     * 画面項目の初期処理を行う<br>
     * <br>
     * @param なし<br>
     * @return model 画面パラメータ<br>
     * @exception なし
     */
    public StudentLessonHistoryUsesListModel load() {

        // モデルの初期化
        StudentLessonHistoryUsesListModel model = new StudentLessonHistoryUsesListModel();

        // レッスン開始日はシステム日付を設定
        model.setLessonDtFr(NaikaraStringUtil.converToYYYY_MM_DD(DateUtil
                .getSysDateAddMonth(NaikaraTalkConstants.SYS_MONTH_LAST)));

        // レッスン終了日はシステム日付を設定
        model.setLessonDtTo(NaikaraStringUtil.converToYYYY_MM_DD(DateUtil.getSysDate()));

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
            StudentLessonHistoryUsesListLogic studentLessonHistoryTeacherUsesListLogic = new StudentLessonHistoryUsesListLogic(
                    conn);

            // コード管理マスタを検索
            return studentLessonHistoryTeacherUsesListLogic.selectCodeMst(category);
        } catch (SQLException e) {
            throw new NaiException(e);
        } catch (Exception e) {
            throw new NaiException(e);
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
            StudentLessonHistoryUsesListLogic studentLessonHistoryUsesListLogic = new StudentLessonHistoryUsesListLogic(
                    conn);

            // コード管理マスタを検索
            LinkedHashMap<String, String> eMap = studentLessonHistoryUsesListLogic.selectCodeMst(eCategory);

            // コード管理マスタを検索
            LinkedHashMap<String, String> jMap = studentLessonHistoryUsesListLogic.selectCodeMst(jCategory);

            LinkedHashMap<String, String> hashMap = new LinkedHashMap<String, String>();

            Iterator<String> eIter = eMap.keySet().iterator();

            Iterator<String> jIter = jMap.keySet().iterator();

            while (eIter.hasNext()) {

                jIter.hasNext();

                String key = eIter.next();

                hashMap.put(key, NaikaraStringUtil.unionString2(eMap.get(key), jMap.get(key)));
            }

            return hashMap;
        } catch (SQLException e) {
            throw new NaiException(e);
        } catch (Exception e) {
            throw new NaiException(e);
        } finally {
            try {
                conn.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

    }

}