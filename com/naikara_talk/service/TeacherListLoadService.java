package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.TeacherListLogic;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_予約処理<br>
 * <b>クラス名称       :</b>講師一覧（Pop）Serviceクラス<br>
 * <b>クラス概要       :</b>登録済みの講師情報の検索処理を行い。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/19 TECS 新規作成
 */
public class TeacherListLoadService implements ActionService {

    /** 初期化前チェック：コース名：大分類 */
    public static final int ERR_COURSE_LARGE = 1;

    /** 初期化前チェック：コース名：中分類 */
    public static final int ERR_COURSE_MEDIUM = 2;

    /** 初期化前チェック：コース名：小分類 */
    public static final int ERR_COURSE_SMALL = 3;

    /** チェック：問題なし(正常) */
    public static final int CHECK_OK = 0;

    /**
     * コード管理マスタからデータ取得処理。<br>
     * <br>
     * コード管理マスタからデータを取得処理する。<br>
     * <br>
     * @param category 汎用コード<br>
     * @return LinkedHashMap<String, String> ハッシュマップ<br>
     * @throws NaiException
     */
    public LinkedHashMap<String, String> selectCodeMst(String category) throws NaiException {

        Connection conn = null;

        try {
            conn = DbUtil.getConnection();

            TeacherListLogic logic = new TeacherListLogic(conn);
            // コード管理マスタ検索
            return logic.selectCodeMst(category);

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
