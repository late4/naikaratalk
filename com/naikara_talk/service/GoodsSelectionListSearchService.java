package com.naikara_talk.service;

import java.sql.Connection;
import java.util.List;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.GoodsMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.GoodsSelectionListLogic;
import com.naikara_talk.model.GoodsSelectionListModel;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>コースマスタメンテナンス(商品選択)<br>
 * <b>クラス概要       :</b>コースマスタメンテナンス(商品選択)検索処理Service<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/05 TECS 新規作成
 */
public class GoodsSelectionListSearchService implements ActionService {

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
     * @param GoodsSelectionListModel<br>
     * @return int チェック結果<br>
     * @exception NaiException
     */
    public int checkPreSelect(GoodsSelectionListModel model) throws NaiException {

        // 入力チェック - DBアクセスありチェック
        // 共通部品：ポイント管理マスタのデータ件数取得処理
        int count = getRowCount(model);
        if (LIST_ZERO_CNT == count) {
            return ERR_ZERO_DATA;
        } else if (LIST_MAX_CNT < count) {
            return ERR_MAXOVER_DATA;
        }

        // 正常
        return GoodsSelectionListModel.CHECK_OK;

    }

    /**
     * 検索データ件数取得。<br>
     * <br>
     * 検索データ件数取得。<br>
     * <br>
     * @param GoodsSelectionListModel 画面のパラメータ<br>
     * @return int 検索データ件数<br>
     * @exception NaiException
     */
    public int getRowCount(GoodsSelectionListModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // 初期化処理
            GoodsSelectionListLogic logic = new GoodsSelectionListLogic(conn);

            // DTOの初期化
            GoodsMstDto dto = new GoodsMstDto();

            // Model値をDTOにセット
            dto = this.modelToDto(model);

            // データの取得件数＆リターン
            return logic.getRowCount(dto);
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

    /**
     * 画面データの取得、初回表示の場合。<br>
     * <br>
     * 画面データの取得、初回表示の場合。<br>
     * <br>
     * @param GoodsSelectionListModel 画面のパラメータ<br>
     * @return List<GoodsMstDto><br>
     * @exception NaiException
     */
    public List<GoodsMstDto> selectList(GoodsSelectionListModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            // 初期化処理
            GoodsSelectionListLogic logic = new GoodsSelectionListLogic(conn);

            // DTOの初期化
            GoodsMstDto dto = new GoodsMstDto();

            // Model値をDTOにセット
            dto = this.modelToDto(model);

            // データの取得＆リターン
            return logic.selectList(dto);
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

    /**
     * Model値をDTOにセット。<br>
     * <br>
     * Model値をDTOにセット。<br>
     * <br>
     * @param GoodsSelectionListModel 画面のパラメータ<br>
     * @return GoodsMstDto<br>
     * @exception なし
     */
    private GoodsMstDto modelToDto(GoodsSelectionListModel model) {

        // DTOの初期化
        GoodsMstDto dto = new GoodsMstDto();

        // 商品コード
        dto.setGoodsCd(model.getGoodsCd());

        // 商品名
        dto.setGoodsNm(model.getGoodsNm());

        // 提供開始日
        dto.setUseStrDt(model.getUseStrDt());

        // 提供終了日
        dto.setUseEndDt(model.getUseEndDt());

        return dto;

    }
}