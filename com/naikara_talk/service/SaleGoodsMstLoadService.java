package com.naikara_talk.service;

//import java.io.File;
//import java.math.BigDecimal;
//import java.sql.Blob;
//import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.GoodsMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.SaleGoodsMstLogic;
import com.naikara_talk.model.SaleGoodsMstModel;
import com.naikara_talk.model.SaleGoodsMstListModel;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>販売商品マスタメンテナンス(単票)。<br>
 * <b>クラス名称　　　:</b>販売商品マスタメンテナンス初期処理Serviceクラス。<br>
 * <b>クラス概要　　　:</b>販売商品マスタメンテナンス初期処理Service。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/06/18 TECS 新規作成。
 *                     </b>2014/02/18 TECS 改造 項目追加「講師への購入メール連絡付き区分」対応
 */
public class SaleGoodsMstLoadService implements ActionService {


	/**
	 * 初期処理<br>
	 * <br>
	 * 画面項目の初期処理を行う<br>
	 * <br>
	 * @return SaleGoodsMstModel
	 */
    public SaleGoodsMstModel initLoad(SaleGoodsMstModel model){
        SaleGoodsMstModel workModel=new SaleGoodsMstModel();

        // 前画面から処理区分を画面にセット
        workModel.setProcessKbn_Rdl(model.getProcessKbn_Rdl());
        if (StringUtils.equals(SaleGoodsMstListModel.PROS_KBN_ADD, model.getProcessKbn_Rdl())) {
            workModel.setProcessKbn_Txt(NaikaraTalkConstants.PROCESSKBN_INS);
        } else if (StringUtils.equals(SaleGoodsMstListModel.PROS_KBN_UPD, model.getProcessKbn_Rdl())) {
            workModel.setProcessKbn_Txt(NaikaraTalkConstants.PROCESSKBN_UPD);
        } else {
            workModel.setProcessKbn_Txt(NaikaraTalkConstants.PROCESSKBN_REF);
        }

        // 受取方法、商品形態の初期取得。
        // 受取方法は「ダウンロード」を選択する
        workModel.setSaleKbn(NaikaraTalkConstants.SALE_KBN_DWL);

        // 商品形態は「電子書籍」を選択する
        workModel.setProductKbn(NaikaraTalkConstants.PRODUCT_KBN_ELECTRON);

        // 2014.02.18 Add 項目追加「講師への購入メール連絡付き区分」対応
        workModel.setTeacherContactKbn(NaikaraTalkConstants.T_CONTACT_KBN_NO);

        return workModel;
    }


    /**
     * データの存在チェック。<br>
     * <br>
     * @param SaleGoodsMstModel
     *            画面のパラメータ
     * @return int
     * @throws Exception
     */
    public int Exists(SaleGoodsMstModel model) throws NaiException {

        Connection conn = null;
        try{
            conn = DbUtil.getConnection();    // DBの接続

            //SaleGoodsMstLogicクラスの生成
            SaleGoodsMstLogic logic = new SaleGoodsMstLogic(conn);

            //GoodsMstDtoクラスの生成
            GoodsMstDto prmDto = new GoodsMstDto();

            // Model値をDTOに設定
            prmDto = this.modelToDto(model);

            // 検索実行
            return logic.Exists(prmDto);

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
	 * 画面初期表示。<br>
	 * <br>
     * @param SaleGoodsMstModel
     *            画面のパラメータ
     * @return なし
     * @throws Exception
	 */
    public SaleGoodsMstModel select(SaleGoodsMstModel model) throws NaiException {

        Connection conn = null;
        try{
            conn = DbUtil.getConnection();    // DBの接続

            //SaleGoodsMstLogicクラスの生成
            SaleGoodsMstLogic logic = new SaleGoodsMstLogic(conn);

            //GoodsMstDtoクラスの生成
            GoodsMstDto prmDto = new GoodsMstDto();

            // Model値をDTOにセット
            prmDto = this.modelToDto(model);

            // 検索実行
            GoodsMstDto resultDto = logic.select(prmDto);

            // DTO値をModelにセット
            model = this.dtoToModel(resultDto, model);

            return model;

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
     * コード管理マスタからデータ取得処理。<br>
     * <br>
     * @param category
     *            汎用コード
     * @return LinkedHashMap<String, String>
     * @throws Exception
     */
    public LinkedHashMap<String, String> selectCodeMst(String category)
            throws NaiException {

        Connection conn = null;
        try{
            conn = DbUtil.getConnection();

        SaleGoodsMstLogic saleGoodsMstLogic = new SaleGoodsMstLogic(conn);

        // コード管理マスタ検索
        return saleGoodsMstLogic.selectCodeMst(category);

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
     *           SaleGoodsMstModel
     * @return GoodsMstDto
     * @throws Exception
	 */
    private GoodsMstDto modelToDto(SaleGoodsMstModel model)
            throws NaiException {

        // DTOの初期化
        GoodsMstDto prmDto = new GoodsMstDto();

        prmDto.setGoodsCd(model.getGoodsCd());                          // 商品コード
        prmDto.setGoodsNm(model.getGoodsNm());                          // 商品名
        prmDto.setExplanation(model.getExplanation());                  // 詳細説明
        prmDto.setSaleKbn(model.getSaleKbn());                          // 受取方法区分
        prmDto.setImgPhotoNm(model.getImgPhotoNm());                    // サンプル画像名
        prmDto.setProductKbn(model.getProductKbn());                    // 商品形態区分
        prmDto.setGoodsFileNm(model.getGoodsFileNm());                  // 商品ファイル名
        prmDto.setSalesPrice(model.getSalesPrice());                    // 販売価格
        prmDto.setPurchaseDescription(model.getPurchaseDescription());  // 購入先説明
        prmDto.setPurchaseUrl(model.getPurchaseUrl());                  // 購入先URL
        prmDto.setUseStrDt(model.getUseStrDt());                        // 提供開始日
        prmDto.setUseEndDt(model.getUseEndDt());                        // 提供終了日

        // 2014.02.18 Add 項目追加「講師への購入メール連絡付き区分」対応
        prmDto.setTeacherContactKbn(model.getTeacherContactKbn());      // 講師への購入メール連絡付き区分

        prmDto.setUseCourseMemo(model.getUseCourseMemo());              // 利用コースメモ
        prmDto.setRemark(model.getRemark());                            // 備考
        prmDto.setRecordVerNo(model.getRecordVerNo());                  // レコードバージョン番号

        // 画面処理必要項目
        prmDto.setImgPhotoPage(model.getImgPhoto());                    // サンプル画像
        prmDto.setImgPhoto_chkn(model.getImgPhoto_chkn());              // 削除1
        prmDto.setGoodsFilePage(model.getGoodsFile());                  // 商品ファイル
        prmDto.setGoodsFile_chkn(model.getGoodsFile_chkn());            // 削除2


    	return prmDto;

    }

	/**
	 * DTO値をModelにセット。<br>
	 * <br>
     * @param prmDto
     *           GoodsMstDto
     * @param model
     *           SaleGoodsMstModel
     * @return SaleGoodsMstModel
     * @throws Exception
	 */
    private SaleGoodsMstModel dtoToModel(GoodsMstDto prmDto, SaleGoodsMstModel model)
            throws NaiException {

        model.setProcessKbn_Rdl(model.getProcessKbn_Rdl());             // 処理区分
        model.setProcessKbn_Txt( model.getProcessKbn_Txt());            // 処理区分名

        model.setGoodsCd(prmDto.getGoodsCd());                          // 商品コード
        model.setGoodsNm(prmDto.getGoodsNm());                          // 商品名
        model.setExplanation(prmDto.getExplanation());                  // 詳細説明
        model.setSaleKbn(prmDto.getSaleKbn());                          // 受取方法区分
        model.setImgPhotoNm(prmDto.getImgPhotoNm());                    // サンプル画像名
        model.setProductKbn(prmDto.getProductKbn());                    // 商品形態区分
        model.setGoodsFileNm(prmDto.getGoodsFileNm());                  // 商品ファイル名
        model.setSalesPrice(prmDto.getSalesPrice());                    // 販売価格
        model.setPurchaseDescription(prmDto.getPurchaseDescription());  // 購入先説明
        model.setPurchaseUrl(prmDto.getPurchaseUrl());                  // 購入先URL
        model.setUseStrDt(prmDto.getUseStrDt());                        // 提供開始日
        model.setUseEndDt(prmDto.getUseEndDt());                        // 提供終了日

        // 2014.02.18 Add 項目追加「講師への購入メール連絡付き区分」対応
        model.setTeacherContactKbn(prmDto.getTeacherContactKbn());      // 講師への購入メール連絡付き区分

////System.out.println("model.getTeacherContactKbn()=" + model.getTeacherContactKbn());

        model.setUseCourseMemo(prmDto.getUseCourseMemo());              // 利用コースメモ
        model.setRemark(prmDto.getRemark());                            // 備考
        model.setRecordVerNo(prmDto.getRecordVerNo());                  // レコードバージョン番号

        // 画面処理必要項目
        model.setImgPhoto(prmDto.getImgPhotoPage());                    // サンプル画像
        model.setImgPhoto_chkn(prmDto.getImgPhoto_chkn());              // 削除1
        model.setGoodsFile(prmDto.getGoodsFilePage());                  // 商品ファイル
        model.setGoodsFile_chkn(prmDto.getGoodsFile_chkn());            // 削除2

        return model;

    }

}
