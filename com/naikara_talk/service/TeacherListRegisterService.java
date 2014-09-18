package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.TeacherBookmarkMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.TeacherListLogic;
import com.naikara_talk.model.TeacherListModel;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_予約処理<br>
 * <b>クラス名称       :</b>講師一覧（Pop）画面遷移Serviceクラス<br>
 * <b>クラス概要       :</b>講師一覧（Pop）画面より講師のブックマークの登録処理を行う。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/19 TECS 新規作成
 */
public class TeacherListRegisterService implements ActionService {

    /** チェック：一覧部の項目が選択なし */
    public static final int ERR_NO_SELECT = 1;

    /** チェック：一覧画面へ戻る */
    public static final int ERR_LIST_BACK = 2;

    /** チェック：問題なし(正常) */
    public static final int CHECK_OK = 0;

    /**
     * 画面遷移の制御処理<br>
     * <br>
     * 画面遷移の制御処理<br>
     * <br>
     * @param model モデル<br>
     * @param select_rdl 一覧から選択された明細データ<br>
     * @param hasSearchFlg 検索判断フラグ<br>
     * @return int チェック結果<br>
     * @throws NaiException
     */
    public int nextPageRequest(TeacherListModel model, String select_rdl, String hasSearchFlg) throws NaiException {

        // 一覧部の項目が選択されていない場合は、メッセージ情報を設定する
        if (StringUtils.isEmpty(select_rdl)) {
            // エラー場合の再検索判断
            if (NaikaraTalkConstants.TRUE.equals(hasSearchFlg)) {
                return ERR_NO_SELECT;
            }
            // 一覧画面戻る
            return ERR_LIST_BACK;
        }

        return CHECK_OK;
    }

    /**
     * 受講者別講師ブックマークテーブルのデータ件数取得処理<br>
     * <br>
     * 受講者別講師ブックマークテーブルのデータ件数取得処理を行う。<br>
     * <br>
     * @param なし<br>
     * @return int 件数<br>
     * @throws NaiException
     */
    public int selectTeacherBookmarkMstCount(TeacherListModel model) throws NaiException {

        // ユーザIDを取得
        String userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();

        Connection conn = null;

        try {
            conn = DbUtil.getConnection();

            TeacherListLogic logic = new TeacherListLogic(conn);

            // 初期化
            TeacherBookmarkMstDto dto = new TeacherBookmarkMstDto();
            // 受講者ID
            dto.setStudentId(userId);

            // 講師ID⇒(隠し項目) 講師ID
            dto.setUserId(model.getTeacherIdSel());


            return logic.selectTeacherBookmarkMstCount(dto);

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
     * 講師ブックマーク登録処理<br>
     * <br>
     * 受講者別講師ブックマークテーブルへ追加処理を行う。<br>
     * <br>
     * @param model モデル<br>
     * @return なし<br>
     * @throws NaiException
     */
    public void insertTeacherBookmarkMst(TeacherListModel model) throws NaiException {

        // ユーザIDを取得
        String userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();

        Connection conn = null;

        try {
            conn = DbUtil.getConnection();

            TeacherListLogic logic = new TeacherListLogic(conn);

            TeacherBookmarkMstDto dto = new TeacherBookmarkMstDto();

            // 受講者ID⇒ログイン情報：受講者ID
            dto.setStudentId(userId);
            // 講師ID⇒(隠し項目) 講師ID
            dto.setUserId(model.getTeacherIdSel());
            // 登録者コード
            dto.setInsertCd(userId);
            // 更新者コード
            dto.setUpdateCd(userId);
            // レコードバージョン番号
            dto.setRecordVerNo(0);

            logic.insertTeacherBookmarkMst(dto);

            // コミット
            conn.commit();

        } catch (Exception e) {
            try {
                // ロールバック
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
