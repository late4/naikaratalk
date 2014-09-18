package com.naikara_talk.service;

import java.sql.Connection;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.TeacherBookmarkMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.TeacherBookmarkListLogic;
import com.naikara_talk.model.TeacherBookmarkListModel;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_予約処理<br>
 * <b>クラス名称       :</b>ブックマーク済の講師一覧（Pop）ブックマーク解除Serviceクラス<br>
 * <b>クラス概要       :</b>ブックマーク済の講師一覧情報を処理する。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/13 TECS 新規作成
 */
public class TeacherBookmarkListDeleteService implements ActionService {

    /**
     * 受講者別講師ブックマークテーブルのデータ削除<br>
     * <br>
     * 受講者別講師ブックマークテーブルのデータ削除を行う<br>
     * <br>
     * @param model モデル<br>
     * @return int 削除データ件数<br>
     * @throws NaiException
     */
    public int deleteTeacherBookmark(TeacherBookmarkListModel model) throws NaiException {

        Connection conn = null;

        try {
            conn = DbUtil.getConnection();

            // 初期化
            TeacherBookmarkListLogic logic = new TeacherBookmarkListLogic(conn);

            TeacherBookmarkMstDto dto = new TeacherBookmarkMstDto();
            // 前画面の（引数）｢受講者ID｣
            dto.setStudentId(model.getStudentId());
            // (隠し項目) 講師ID
            dto.setUserId(model.getTeacherId());
            // レコードバージョン番号
            dto.setRecordVerNo(model.getRecordVerNo());

            int deleteRowCount = logic.deleteTeacherBookmark(dto);

            conn.commit();

            return deleteRowCount;

        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
                throw new NaiException(e1);
            }
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
