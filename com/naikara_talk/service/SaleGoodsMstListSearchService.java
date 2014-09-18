package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.GoodsMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.SaleGoodsMstListLogic;
import com.naikara_talk.model.SaleGoodsMstListModel;
import com.naikara_talk.util.NaikaraTalkConstants;


/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>販売商品マスタメンテナンス(一覧)。<br>
 * <b>クラス名称　　　:</b>販売商品マスタメンテナンス検索処理Serviceクラス。<br>
 * <b>クラス概要　　　:</b>販売商品マスタメンテナンス検索処理Service。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/06/18 TECS 新規作成。
 */
public class SaleGoodsMstListSearchService implements ActionService {

    /* 一覧 ZERO件 */
    private static final int LIST_ZERO_CNT = 0;

    /* 一覧の表示上限 */
    public static final int LIST_MAX_CNT = 100;

    /**
     * 検索前チェック処理<br>
     * <br>
     * @param model
     * @param returnOnFlg
     * @param hasSearchFlg
     * @return int
     * @exception Exception
     */
    public int checkPreSelect(SaleGoodsMstListModel model, Boolean returnOnFlg, String hasSearchFlg) throws NaiException {
        int rturnCd;    // 初期値の設定

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();    // DBの接続

            if (!returnOnFlg || StringUtils.equals(NaikaraTalkConstants.FALSE, hasSearchFlg)) {
                // ヘッダ部からの「戻る」を除く場合 又は 画面状態フラグ == (初期状態ではない) の場合

                if (!returnOnFlg) {
                    // ヘッダ部からの「戻る」を除く場合

                    // ◆◆◆処理区分のチェック◆◆◆
                    // 処理区分のチェック、処理区分が[新規]の場合は、(「検索ボタン押下」)メッセージ情報を設定する
                    if (StringUtils.equals(SaleGoodsMstListModel.PROS_KBN_ADD, model.getProcessKbn())) {
                        return SaleGoodsMstListModel.ERR_PROS_BTN_MISMATCH;
                        }
                    }

                // ◆◆◆権限チェック◆◆◆

                // SaleGoodsMstListLogicクラスの生成
                SaleGoodsMstListLogic logic = new SaleGoodsMstListLogic(conn);

                // 権限チェック
                rturnCd = logic.checkRole(model);

                if (SaleGoodsMstListModel.CHECK_OK != rturnCd) {
                    // エラー発生時は、返却
                    return rturnCd;
                }

                // ◆◆◆入力チェック - DBアクセスありチェック◆◆◆
                // 共通部品：データ件数取得処理　(※販売商品マスタ)
                int count = this.getRowCount(model);

                if (count == LIST_ZERO_CNT) {
                    // データ件数＝Zero件の場合
                    return SaleGoodsMstListModel.ERR_ZERO_DATA;
                } else if (count > LIST_MAX_CNT) {
                    // データ件数が最大件数以上の場合
                    return SaleGoodsMstListModel.ERR_MAXOVER_DATA;
                }

            }

            // 正常
            return SaleGoodsMstListModel.CHECK_OK;

            } catch ( SQLException se ) {
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
	 * 検索データ件数取得。<br>
	 * <br>
     * @param SaleGoodsMstListModel
     *            画面のパラメータ
     * @return int
     * @throws Exception
	 */
    public int getRowCount(SaleGoodsMstListModel model) throws NaiException {

        Connection conn = null;
        try{
            conn = DbUtil.getConnection();    // DBの接続

            // SaleGoodsMstListLogicクラスの生成
            SaleGoodsMstListLogic logic = new SaleGoodsMstListLogic(conn);

            // GoodsMstDtoクラスの生成
            GoodsMstDto dto = new GoodsMstDto();

            // Model値をDTOにセット
            dto = this.modelToDto(model);

            // データの取得件数＆リターン
            return logic.getRowCount(dto);

        } catch ( SQLException se ) {
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
     * 画面データの取得処理<br>
     * <br>
     * @param GoodsMstDto
     *            画面のパラメータ
     * @return List<GoodsMstDto>
     * @throws Exception
     */
    public List<GoodsMstDto> selectList(SaleGoodsMstListModel model) throws NaiException {

        Connection conn = null;
        try{
            conn = DbUtil.getConnection();    // DBの接続

            // SaleGoodsMstListLogicクラスの生成
            SaleGoodsMstListLogic logic = new SaleGoodsMstListLogic(conn);

            // GoodsMstDtoクラスの生成
            GoodsMstDto dto = new GoodsMstDto();

            // Model値をDTOにセット
            dto = this.modelToDto(model);

            // データの取得＆リターン
            return logic.selectList(dto);

        } catch ( SQLException se ) {
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
     * Model値をDTOにセット。<br>
     * <br>
     * @param model
     *           SaleGoodsMstListModel
     * @return GoodsMstDto
     * @throws Exception
	 */
    private GoodsMstDto modelToDto(SaleGoodsMstListModel model)
            throws NaiException {

        // DTOの初期化
        GoodsMstDto prmDto = new GoodsMstDto();

        prmDto.setGoodsCd(model.getGoodsCd());    // 商品コード
        prmDto.setGoodsNm(model.getGoodsNm());    // 商品名
        prmDto.setUseKbn(model.getUseKbn());      // 利用状態

        return prmDto;

    }


}
