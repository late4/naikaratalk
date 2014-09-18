package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.UserMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.TeacherBookmarkListLogic;
import com.naikara_talk.model.TeacherBookmarkListModel;
import com.naikara_talk.util.DateUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_予約処理<br>
 * <b>クラス名称       :</b>ブックマーク済の講師一覧（Pop）画面遷移Serviceクラス<br>
 * <b>クラス概要       :</b>ブックマーク済の講師一覧情報を処理する。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/13 TECS 新規作成
 */
public class TeacherBookmarkListMoveService implements ActionService {

    /**
     * 必須チェック。<br>
     * <br>
     * 必須チェックを行う。<br>
     * <br>
     * @param select_rdl 一覧から選択された明細データ<br>
     * @return boolean チェック結果<br>
     * @throws NaiException
     */
    public boolean requiredCheck(String select_rdl) throws NaiException {

        // 一覧部の項目が選択されていない場合は、メッセージ情報を設定する
        if (StringUtils.isEmpty(select_rdl)) {
            return false;
        }

        return true;
    }

    /**
     * 利用者マスタデータ件数取得。<br>
     * <br>
     * 利用者マスタデータ件数取得を行う。<br>
     * <br>
     * @param model モデル<br>
     * @return int 件数<br>
     * @throws NaiException
     */
    public int selectUserMstCount(TeacherBookmarkListModel model) throws NaiException {

        Connection conn = null;

        try {
            conn = DbUtil.getConnection();

            // 初期化
            TeacherBookmarkListLogic logic = new TeacherBookmarkListLogic(conn);

            UserMstDto dto = new UserMstDto();
            // 講師ブックマーク画面の｢講師ID｣
            dto.setUserId(model.getTeacherId());
            // サーバーのシステム日付
            dto.setUseEndDt(DateUtil.getSysDate());

            return logic.selectUserMstCount(dto);

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
