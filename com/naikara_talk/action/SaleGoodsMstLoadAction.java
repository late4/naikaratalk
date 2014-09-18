package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.SaleGoodsMstListModel;
import com.naikara_talk.model.SaleGoodsMstModel;
import com.naikara_talk.service.SaleGoodsMstLoadService;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>販売商品マスタメンテナンス(単票)。<br>
 * <b>クラス名称　　　:</b>販売商品マスタメンテナンス初期処理Actionクラス。<br>
 * <b>クラス概要　　　:</b>販売商品マスタメンテナンス初期処理Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/06/18 TECS 新規作成。
 *                     </b>2014/02/18 TECS 改造 項目追加「講師への購入メール連絡付き」対応
 */
public class SaleGoodsMstLoadAction extends SaleGoodsMstActionSupport {

    private static final long serialVersionUID = 1L;


    /**
     * 画面の初期表示。<br>
     * <br>
     * @param なし
     * @return String
     * @throws NaiException
     */
    @SkipValidation
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // 画面のパラメータをモデルに設定
        this.setupModel();


        // SaleGoodsMstLoadServiceクラスの生成
        SaleGoodsMstLoadService service = new SaleGoodsMstLoadService();

        // 前画面から処理区分を基にした処理区分名の判定、コード種別名をModelに設定
        SaleGoodsMstModel workModel= service.initLoad(model);

        // 前画面から処理区分を画面に設定
        this.processKbn_rdl = workModel.getProcessKbn_Rdl();
        this.processKbn_txt = workModel.getProcessKbn_Txt();

        // 受取方法、商品形態の初期取得。
        // 2014.02.18 Add 項目追加「講師への購入メール連絡付き」対応
        try {
            this.initRadio();
        } catch (Exception e) {
            throw new NaiException(e);
        }

        //******************************
        //新規・修正・照会の処理
        //******************************

        // 処理区分＝新規の場合('0':'新規','1':'修正','2':'照会')
        if (StringUtils.equals(SaleGoodsMstListModel.PROS_KBN_ADD, this.processKbn_rdl)) {
            // 前画面からの商品コードをクリアする
            this.goodsCd_txt = NaikaraTalkConstants.BRANK;
            // 受取方法は未選択とする
            this.saleKbn_rdl = "";
            // 商品形態は未選択とする
            this.productKbn_rdl = "";

            // 2014.02.18 Add 項目追加「講師への購入メール連絡付き」対応
            this.teacherContactKbn_rdl = "";

            return SUCCESS;
        }

        // 処理区分＝照会の場合('0':'新規','1':'修正','2':'照会')
        if (StringUtils.equals(SaleGoodsMstListModel.PROS_KBN_REF, this.processKbn_rdl)) {
            // 照会用のコメントを設定
            this.setComment(NaikaraTalkConstants.PROCESSKBN_REF_COMMENT);
        }


        // 処理区分＝修正、照会の場合('0':'新規','1':'修正','2':'照会')
        if (StringUtils.equals(SaleGoodsMstListModel.PROS_KBN_UPD, this.processKbn_rdl)
        		|| StringUtils.equals(SaleGoodsMstListModel.PROS_KBN_REF, this.processKbn_rdl) ) {

            try {
               if (service.Exists(model) < 1) {
                   // データが存在しない場合 (データ件数 < 1 の場合)
                   StringBuffer work = new StringBuffer();
                   work.append("(商品コード=");
                   work.append(model.getGoodsCd());
                   work.append(")");
                   this.message = getMessage("EN0020", new String[] { SaleGoodsMstListActionSupport.GOODS_MST_TBL_JNM, work.toString() });
                   // 前画面(一覧)へ戻り、エラーメッセージを表示
                   return ERROR;
                } else {
                    // データが存在する場合

                    // 表示データの取得処理
                    this.load();

                    // 返却値
                    return SUCCESS;

                 }
            } catch (Exception e) {
                throw new NaiException(e);
            }
        }

        // 画面を返す
        return SUCCESS;

    }


    /**
     * 初期処理、表示データの取得。<br>
     * <br>
     * @param なし
     * @return なし
     * @throws Exception
     */
    private void load() throws NaiException {

        // 表示データを取得する
        SaleGoodsMstLoadService service = new SaleGoodsMstLoadService();

        // 前画面の情報をmodelへ設定
        this.formToModel();

        try {
            // 画面初期表示用のデータ取得処理
            model = service.select(this.model);

            // 取得結果をmodelへ設定
            this.modelToForm();

        } catch (Exception se) {
            throw new NaiException(se);
        }

    }


    /**
     * Form値をCodeControlMstModelに設定。<br>
     * <br>
     * @throws Exception
     */
    private void formToModel() throws NaiException {

        // 前画面の情報をmodelへ設定
        this.model.setGoodsCd(this.goodsCd_txt);
        this.model.setProcessKbn_Rdl(this.processKbn_rdl);
        this.model.setProcessKbn_Txt(this.processKbn_txt);

    }


    /**
     * SaleGoodsMstModel値をFormに設定。<br>
     * <br>
     * @throws Exception
     */
    private void modelToForm() throws NaiException {

        // 前画面の情報をmodelへ設定
        this.goodsCd_txt = model.getGoodsCd();                          // 商品コード
        this.goodsNm_txt = model.getGoodsNm();                          // 商品名
        this.explanation_txa = model.getExplanation();                  // 詳細説明
        this.saleKbn_rdl = model.getSaleKbn();                          // 受取方法
        this.imgPhoto_filFileName = model.getImgPhotoNm();              // サンプル画像名
        this.productKbn_rdl = model.getProductKbn();                    // 商品形態
        this.goodsFile_filFileName = model.getGoodsFileNm();            // 商品ファイル名
        this.salesPrice_txt = NaikaraStringUtil.addComma(model.getSalesPrice()
                .toString());                                           // 販売価格/ポイント
        this.purchaseDescription_txt = model.getPurchaseDescription();  // 購入先（説明）
        this.purchaseUrl_txt = model.getPurchaseUrl();                  // 購入先URL
        this.useStrDt_txt = model.getUseStrDt();                        // 提供期間開始日
        this.useEndDt_txt = model.getUseEndDt();                        // 提供期間終了日

        // 2014.02.18 Add 項目追加「講師への購入メール連絡付き」対応
        this.teacherContactKbn_rdl = model.getTeacherContactKbn();      // 講師への購入メール連絡付き

        this.useCourseMemo_txt = model.getUseCourseMemo();              // 利用コース メモ
        this.remark_txa = model.getRemark();                            // 備考
        this.recordVerNo = String.valueOf(model.getRecordVerNo());      // レコードバージョン番号

    }

}
