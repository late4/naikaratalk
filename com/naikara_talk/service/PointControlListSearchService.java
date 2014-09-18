package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.PointManagMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.PointControlListLogic;
import com.naikara_talk.model.PointControlListModel;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>ポイント管理マスタメンテナンス(一覧)<br>
 * <b>クラス概要       :</b>ポイント管理マスタメンテナンス(一覧)検索処理Service<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/01 TECS 新規作成
 */
public class PointControlListSearchService implements ActionService {

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
     * 検索前チェック処理<br>
     * <br>
     * 検索前チェック処理<br>
     * <br>
     * @param PointControlListModel<br>
     * @return int チェック結果<br>
     * @exception Exception
     */
    public int checkPreSelect(PointControlListModel model) throws Exception {

        // 処理区分のチェック、処理区分が[新規]の場合は、メッセージ情報を設定する
        // '0':'新規','1':'修正','2':'照会'
        if (StringUtils.equals(PointControlListModel.PROS_KBN_ADD, model.getProcessKbn())) {
            return ERR_PROS_BTN_MISMATCH;
        }

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
     * @param PointControlListModel 画面のパラメータ<br>
     * @return int 検索データ件数<br>
     * @exception NaiException
     */
    public int getRowCount(PointControlListModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            // 初期化処理
            PointControlListLogic pointControlListLogic = new PointControlListLogic(conn);

            // DTOの初期化
            PointManagMstDto dto = new PointManagMstDto();

            // Model値をDTOにセット
            dto = this.modelToDto(model);

            // データの取得件数＆リターン
            return pointControlListLogic.getRowCount(dto);
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
     * @param PointControlListModel 画面のパラメータ<br>
     * @return List<PointManagMstDto><br>
     * @exception NaiException
     */
    public List<PointManagMstDto> selectList(PointControlListModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            // 初期化処理
            PointControlListLogic pointControlListLogic = new PointControlListLogic(conn);

            // DTOの初期化
            PointManagMstDto dto = new PointManagMstDto();

            // Model値をDTOにセット
            dto = this.modelToDto(model);

            // データの取得＆リターン
            return pointControlListLogic.selectList(dto);
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
     * @param PointControlListModel 画面のパラメータ<br>
     * @return PointManagMstDto<br>
     * @exception なし
     */
    private PointManagMstDto modelToDto(PointControlListModel model) {

        // DTOの初期化
        PointManagMstDto dto = new PointManagMstDto();

        // ポイントコード
        dto.setPointCd(model.getPointCd());
        // 通常月謝区分
        dto.setFeeKbn(model.getFeeKbn());

        return dto;

    }

}
