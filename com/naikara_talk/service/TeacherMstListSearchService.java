package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.UserMstTeacherMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.TeacherMstListLogic;
import com.naikara_talk.model.PointControlListModel;
import com.naikara_talk.model.TeacherMstListModel;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>講師マスタメンテナンス(一覧)<br>
 * <b>クラス概要       :</b>講師マスタメンテナンス(一覧)検索処理Service<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/22 TECS 新規作成
 */
public class TeacherMstListSearchService implements ActionService {

    /** 一覧 ZERO件 */
    private static final int LIST_ZERO_CNT = 0;

    /** 一覧の表示上限 */
    private static final int LIST_MAX_CNT = 100;

    /** 検索前チェック：データ件数ZERO件 */
    public static final int ERR_ZERO_DATA = 2;

    /** 検索前チェック：データ件数上限以上の件数 */
    public static final int ERR_MAXOVER_DATA = 3;

    /**
     * 検索前チェック処理<br>
     * <br>
     * 検索前チェック処理<br>
     * <br>
     * @param TeacherMstListModel<br>
     * @return int チェック結果<br>
     * @exception NaiException
     */
    public int checkPreSelect(TeacherMstListModel model) throws NaiException {

        // 入力チェック - DBアクセスありチェック
        // 共通部品：ポイント管理マスタのデータ件数取得処理
        int count = getRowCount(model);
        if (LIST_ZERO_CNT == count) {
            return ERR_ZERO_DATA;
        } else if (LIST_MAX_CNT < count) {
            return ERR_MAXOVER_DATA;
        }

        // 正常
        return PointControlListModel.CHECK_OK;

    }

    /**
     * 検索データ件数取得。<br>
     * <br>
     * 検索データ件数取得。<br>
     * <br>
     * @param TeacherMstListModel 画面のパラメータ<br>
     * @return int 検索データ件数<br>
     * @exception NaiException
     */
    public int getRowCount(TeacherMstListModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // 初期化処理
            TeacherMstListLogic teacherMstListLogic = new TeacherMstListLogic(conn);

            // DTOの初期化
            UserMstTeacherMstDto dto = new UserMstTeacherMstDto();

            // Model値をDTOにセット
            dto = this.modelToDto(model);

            // データの取得件数＆リターン
            return teacherMstListLogic.getRowCount(dto);
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
     * 画面データの取得、初回表示の場合。<br>
     * <br>
     * 画面データの取得、初回表示の場合。<br>
     * <br>
     * @param TeacherMstListModel 画面のパラメータ<br>
     * @return List<UserMstTeacherMstDto><br>
     * @exception NaiException
     */
    public List<UserMstTeacherMstDto> selectList(TeacherMstListModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // 初期化処理
            TeacherMstListLogic teacherMstListLogic = new TeacherMstListLogic(conn);

            // DTOの初期化
            UserMstTeacherMstDto dto = new UserMstTeacherMstDto();

            // Model値をDTOにセット
            dto = this.modelToDto(model);

            // データの取得＆リターン
            return teacherMstListLogic.selectList(dto);
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
     * Model値をDTOにセット。<br>
     * <br>
     * Model値をDTOにセット。<br>
     * <br>
     * @param TeacherMstListModel 画面のパラメータ<br>
     * @return UserMstTeacherMstDto<br>
     * @exception なし
     */
    private UserMstTeacherMstDto modelToDto(TeacherMstListModel model) {

        // DTOの初期化
        UserMstTeacherMstDto dto = new UserMstTeacherMstDto();

        // 講師ID
        dto.setUserId(model.getUserId());
        // 講師名(フリガナ)
        dto.setUserKnm(model.getUserKnm());

        return dto;

    }

}
