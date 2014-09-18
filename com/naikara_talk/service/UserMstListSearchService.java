package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.UserMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.UserMstListLogic;
import com.naikara_talk.model.UserMstListModel;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守。<br>
 * <b>クラス名称　　　:</b>利用者マスタメンテナンス検索処理Serviceクラス。<br>
 * <b>クラス概要　　　:</b>利用者マスタメンテナンス検索処理Service。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/04 TECS 新規作成。
 */
public class UserMstListSearchService implements ActionService {

    /** 一覧 ZERO件 */
    private static final int LIST_ZERO_CNT = 0;

    /** 一覧の表示上限 */
    private static final int LIST_MAX_CNT = 100;

    /** 検索前チェック：処理区分と押下ボタンの不整合 */
    public static final int ERR_PROS_BTN_MISMATCH = 1;

    /** 検索前チェック：データ件数ZERO件 */
    public static final int ERR_ZERO_DATA = 2;

    /** 検索前チェック：データ件数上限以上の件数 */
    public static final int ERR_MAXOVER_DATA = 3;

    /**
     * チェック処理<br>
     * <br>
     * 検索前チェック処理<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @return int エラー有無フラグ<br>
     * @exception Exception
     */
    public int checkPreSelect(UserMstListModel model) throws Exception {

        // 処理区分のチェック、処理区分が[新規]の場合は、メッセージ情報を設定する
        // '0':'新規','1':'修正','2':'照会'
        if (StringUtils.equals(UserMstListModel.PROS_KBN_ADD, model.getProcessKbn())) {

            return ERR_PROS_BTN_MISMATCH;
        }

        // 入力チェック - DBアクセスありチェック
        // 共通部品：利用者マスタのデータ件数取得処理
        int count = getRowCount(model);

        // ZERO件の場合
        if (count == LIST_ZERO_CNT) {

            return ERR_ZERO_DATA;

        } else if (count > LIST_MAX_CNT) {

            return ERR_MAXOVER_DATA;

        }

        return UserMstListModel.CHECK_OK;
    }

    /**
     * 検索データ件数取得<br>
     * <br>
     * 検索データ件数取得を行う<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @return int 件数<br>
     * @exception Exception
     */
    public int getRowCount(UserMstListModel model) throws Exception {

        Connection conn = null;

        try {
            conn = DbUtil.getConnection();

            // ロジックの初期化
            UserMstListLogic userMstListLogic = new UserMstListLogic(conn);

            // Model値をDTOにセット
            UserMstDto dto = this.modelToDto(model);

            // データ件数を取得
            int count = userMstListLogic.getRowCount(dto);

            return count;
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
     * 利用者リスト取得<br>
     * <br>
     * 利用者リスト取得を行う<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @return List 検索結果<br>
     * @exception Exception
     */
    public List<UserMstDto> selectList(UserMstListModel model) throws Exception {

        Connection conn = null;

        try {
            conn = DbUtil.getConnection();

            // ロジックの初期化
            UserMstListLogic userMstListLogic = new UserMstListLogic(conn);

            // Model値をDTOにセット
            UserMstDto dto = this.modelToDto(model);

            // データを取得
            List<UserMstDto> userList = userMstListLogic.selectList(dto);

            return userList;

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
     * Model値をセット<br>
     * <br>
     * Model値をDTOにセット<br>
     * <br>
     * @param model 画面パラメータ<br>
     * @return prmDto 変換後結果<br>
     * @exception Exception
     */
    private UserMstDto modelToDto(UserMstListModel model) throws Exception {

        // DTOの初期化
        UserMstDto prmDto = new UserMstDto();

        // 利用者ID
        prmDto.setUserId(model.getUserId());
        // 利用者名(フリガナ)(姓)と(名）
        prmDto.setUserKnm(model.getUserKnm());
        // 利用状態
        prmDto.setRiyouKbn(model.getUseKbn());

        return prmDto;
    }
}