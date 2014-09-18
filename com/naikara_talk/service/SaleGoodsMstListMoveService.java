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
 * <b>クラス名称　　　:</b>販売商品マスタメンテナンス登録選択Serviceクラス。<br>
 * <b>クラス概要　　　:</b>販売商品マスタメンテナンス登録選択Service。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/06/18 TECS 新規作成。
 */
public class SaleGoodsMstListMoveService implements ActionService {

    /* チェック：一覧部の項目が選択なし */
    public static final int ERR_NO_SELECT = 1;

    /* チェック：一覧画面へ戻る */
    public static final int ERR_LIST_BACK = 2;

	/**
	 * 単票へ画面遷移する制御処理<br>
	 * <br>
	 * @param SaleGoodsMstListModel
	 * @param select_rdl
	 * @param hasSearchFlg
	 * @return SaleGoodsMstListModel
	 */
    public int nextPageRequest(SaleGoodsMstListModel model,String select_rdl,
    		String hasSearchFlg) throws NaiException{

        int rturnCd;    // 初期値の設定

        Connection conn = null;
        try {

            conn = DbUtil.getConnection();    // DBの接続

            // ◆◆◆処理区分のチェック◆◆◆
            // (処理区分が[新規]を除く場合) 必須選択のチェック
            if (!StringUtils.equals(SaleGoodsMstListModel.PROS_KBN_ADD, model.getProcessKbn())
               && StringUtils.isEmpty(select_rdl)) {
               // 処理区分が[新規]を除く場合、一覧部の項目が選択されていない場合は、メッセージ情報を設定する
               if (NaikaraTalkConstants.TRUE.equals(hasSearchFlg)) {
                   // エラー場合の再検索判断
                   return SaleGoodsMstListModel.ERR_NO_SELECT;

               }
               // 一覧画面戻る
               return SaleGoodsMstListModel.ERR_LIST_BACK;
            }

            // SaleGoodsMstListLogicクラスの生成
            SaleGoodsMstListLogic logic = new SaleGoodsMstListLogic(conn);

            // ◆◆◆権限チェック◆◆◆
            rturnCd = logic.checkRole(model);

            // 返却値の設定
            return rturnCd;

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
     * 画面データの取得処理(エラー発生時に既に一覧表示している場合は一覧表示する)<br>
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