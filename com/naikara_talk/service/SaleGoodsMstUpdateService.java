package com.naikara_talk.service;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.GoodsMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.SaleGoodsMstLogic;
import com.naikara_talk.model.SaleGoodsMstModel;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;


/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>販売商品マスタメンテナンス(単票)。<br>
 * <b>クラス名称　　　:</b>販売商品マスタメンテナンス登録更新Serviceクラス。<br>
 * <b>クラス概要　　　:</b>販売商品マスタメンテナンス登録更新Service。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/06/18 TECS 新規作成。
 *                     </b>2014/02/18 TECS 改造 項目追加「講師への購入メール連絡付き区分」対応
 */
public class SaleGoodsMstUpdateService implements ActionService {

    /* 更新前チェック：ファイルサイズチェック(サンプル画像)エラー */
    public static final int ERR_IMGPHOTO_FIL = 1;

    /* 更新前チェック：ファイルサイズチェック(商品ファイル)エラー */
    public static final int ERR_GOODS_FIL = 2;

    /* 更新前チェック：受取方法 (ダウンロード)のチェックエラー */
    public static final int ERR_NO_DWL = 3;

    /* 更新前チェック： 受取方法 (外部購入)のチェックエラー */
    public static final int ERR_CHK_OUTSIDE_1 = 4;

    /* 更新前チェック： 受取方法 (外部購入)のチェックエラー */
    public static final int ERR_CHK_OUTSIDE_2 = 5;

    /* 更新前チェック： 商品ファイルのチェックエラー */
    public static final int ERR_CHK_GOODSHILE_1 = 6;

    /* 更新前チェック： 商品ファイルのチェックエラー*/
    public static final int ERR_CHK_GOODSHILE_2 = 7;

    /* 更新前チェック： 商品ファイルなし*/
    public static final int ERR_NO_FILENM = 8;

    /* 更新前チェック： 価格ZERO*/
    public static final int ERR_ZERO_PRICE = 9;

    /* 更新前チェック：日付の整合性チェック (日付)エラー */
    public static final int ERR_INTEGRITY_DT = 10;



    /**
	 * 登録、更新のチェック。<br>
	 * <br>
     * @param なし
     * @return boolean
     * @throws Exception
	 */
    public int errorCheck(SaleGoodsMstModel model) throws NaiException {

        try {
            // ◆◆◆ファイルサイズのチェック◆◆◆
            // ファイルサイズのチェック(サンプル画像)
            if (model.getImgPhoto() != null) {
                FileInputStream fiStream = new FileInputStream(model.getImgPhoto());
                if (fiStream.available() > 65 * 1024) {  // available:推定バイト数
                    // 65KB以上の場合
                    return ERR_IMGPHOTO_FIL;
                }
            }

            // ファイルサイズのチェック(商品ファイル)
            if (model.getGoodsFile() != null) {
                FileInputStream fiStream = new FileInputStream(model.getGoodsFile());
                if (fiStream.available() > 15 * 1024 * 1024) {  // available:推定バイト数
                    // 15MB以上の場合
                    return ERR_GOODS_FIL;
                }
            }
        } catch (Exception e) {

            throw new NaiException(e);
        }


        // ◆◆◆関連チェック◆◆◆
        // ｢受取方法｣＝”1000” (ダウンロード) ｢商品形態｣≧2000 (書籍・その他) の場合
        if (StringUtils.equals(NaikaraTalkConstants.SALE_KBN_DWL, model.getSaleKbn())) {
            int productKbn = Integer.valueOf(model.getProductKbn()).intValue();
            try {
                int productKbnEtc = Integer.valueOf(NaikaraTalkConstants.PRODUCT_KBN_ETC).intValue();
                if (productKbn >= productKbnEtc ) {
                    return ERR_NO_DWL;
                }
            } catch (Exception e) {
                throw new NaiException(e);
            }
        }

        // ｢受取方法｣!＝”3000”の場合
        if (!StringUtils.equals(NaikaraTalkConstants.SALE_KBN_OUTSIDE, model.getSaleKbn())) {
            // ｢商品ファイル：削除｣＝ON　｢商品ファイル：ファイル名｣＝””の場合
            if (StringUtils.equals("true", model.getGoodsFile_chkn())
                    && model.getGoodsFile() == null) {
                return ERR_CHK_OUTSIDE_1;
            }
            // ｢商品ファイル：削除｣＝OFF　｢商品ファイル名｣＝””　且つ ｢商品ファイル：ファイル名｣≠””の場合
            if (StringUtils.equals("false", model.getGoodsFile_chkn())
                    && StringUtils.isEmpty(model.getGoodsFileNm())
                    && model.getGoodsFile() != null) {
                return ERR_CHK_OUTSIDE_2;
            }
        }

        // ｢受取方法｣＝”3000”(外部購入)の場合
        if (StringUtils.equals(NaikaraTalkConstants.SALE_KBN_OUTSIDE, model.getSaleKbn())) {
            // ｢商品ファイル名｣＝””　且つ ｢商品ファイル：削除｣＝ON の場合
            if (StringUtils.equals("true", model.getGoodsFile_chkn())
                    && StringUtils.isEmpty(model.getGoodsFileNm())) {
                return ERR_CHK_GOODSHILE_1;
            }
            // ｢商品ファイル名｣≠””　且つ ｢商品ファイル：削除｣＝OFFの場合
            if (StringUtils.equals("false", model.getGoodsFile_chkn())
                    && !StringUtils.isEmpty(model.getGoodsFileNm())) {
                return ERR_CHK_GOODSHILE_2;
            }
            // ｢商品ファイル：ファイル名｣≠””　場合
            if (model.getGoodsFile() != null) {
                return ERR_NO_FILENM;
            }
            // ｢販売価格／ポイント｣ !＝0 の場合
            if (!StringUtils.equals("0", String.valueOf(model.getSalesPrice()))) {
                return ERR_ZERO_PRICE;
            }
        }

        // 日付の整合性チェック (日付)
        if (DateUtil.dateCompare2(
                NaikaraStringUtil.converToYYYYMMDD(model.getUseStrDt()),
                NaikaraStringUtil.converToYYYYMMDD(model.getUseEndDt()))) {
            return ERR_INTEGRITY_DT;
        }


        return 0;
    }

    /**
     * データの取得処理。<br>
     * <br>
     * @param SaleGoodsMstModel
     *            画面のパラメータ
     * @return int
     * @throws Exception
     */
    public GoodsMstDto select(SaleGoodsMstModel model) throws NaiException {

        Connection conn = null;
        try{
            conn = DbUtil.getConnection();

            SaleGoodsMstLogic logic = new SaleGoodsMstLogic(conn);

            // DTOの初期化
            GoodsMstDto prmDto = new GoodsMstDto();

            // Model値をDTOにセット
            prmDto = this.modelToDto(model);

            // 検索実行
            return logic.select(prmDto);

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
     * 登録処理。<br>
     * <br>
     * @param SaleGoodsMstModel
     *            画面のパラメータ
     * @return なし
     * @throws Exception
     */
    public void insert(SaleGoodsMstModel model) throws NaiException {

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            SaleGoodsMstLogic logic = new SaleGoodsMstLogic(conn);
            // DTOの初期化
            GoodsMstDto prmDto = new GoodsMstDto();
            // Model値をDTOにセット
            prmDto = this.modelToDto(model);

            // 登録実行
            logic.insert(prmDto);

            //コミット
            conn.commit();

        } catch (Exception e) {
            try {
                // ロールバック処理
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
     * 更新処理。<br>
     * <br>
     * @param SaleGoodsMstModel
     *            画面のパラメータ
     * @return int
     * @throws Exception
     */
    public int update(SaleGoodsMstModel model) throws NaiException {
        int cnt = 0;

        Connection conn = null;
        try {
            conn = DbUtil.getConnection();


            SaleGoodsMstLogic logic = new SaleGoodsMstLogic(conn);
            // DTOの初期化
            GoodsMstDto prmDto = new GoodsMstDto();
            // Model値をDTOにセット
            prmDto = this.modelToDto(model);

            // 更新実行
            cnt = logic.update(prmDto);

            //コミット
            conn.commit();

            return cnt;

        } catch (Exception e) {
            try {
                // ロールバック処理
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
        prmDto.setProductKbn(model.getProductKbn());                    // 商品形態区分
        prmDto.setSalesPrice(model.getSalesPrice());                    // 販売価格
        prmDto.setPurchaseDescription(model.getPurchaseDescription());  // 購入先説明
        prmDto.setPurchaseUrl(model.getPurchaseUrl());                  // 購入先URL
        prmDto.setUseStrDt(model.getUseStrDt());                        // 提供開始日
        prmDto.setUseEndDt(model.getUseEndDt());                        // 提供終了日

        // 2014.02.18 Add 項目追加「講師への購入メール連絡付き」対応
        prmDto.setTeacherContactKbn(model.getTeacherContactKbn());      // 講師への購入メール連絡付き区分

        prmDto.setUseCourseMemo(model.getUseCourseMemo());              // 利用コースメモ
        prmDto.setRemark(model.getRemark());                            // 備考
        prmDto.setRecordVerNo(model.getRecordVerNo());                  // レコードバージョン番号

        // サンプル画像名
        prmDto.setImgPhotoNm(model.getImgPhotoNm());
        // サンプル画像
        prmDto.setImgPhotoPage(model.getImgPhoto());
        // サンプル画像削除1
        prmDto.setImgPhoto_chkn(model.getImgPhoto_chkn());

        if (model.getGoodsFile() == null) {
            // 商品ファイルが存在しない場合は商品ファイル名のクリア
            prmDto.setGoodsFileNm(NaikaraTalkConstants.BRANK);          // 商品ファイル名
        } else {
            prmDto.setGoodsFileNm(model.getGoodsFileNm());              // 商品ファイル名
        }

        prmDto.setGoodsFilePage(model.getGoodsFile());                  // 商品ファイル

        prmDto.setGoodsFile_chkn(model.getGoodsFile_chkn());            // 商品ファイル 削除2

        return prmDto;

    }

}
