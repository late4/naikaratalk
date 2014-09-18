package com.naikara_talk.action;

import java.io.File;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.SaleGoodsMstModel;
import com.naikara_talk.service.SaleGoodsMstLoadService;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>販売商品マスタメンテナンス(単票)。<br>
 * <b>クラス名称　　　:</b>販売商品マスタメンテナンスActionスーパークラス。<br>
 * <b>クラス概要　　　:</b>販売商品マスタメンテナンスAction。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/06/19 TECS 新規作成。
 *                     </b>2014/02/18 TECS 改造 項目追加「講師への購入メール連絡付き区分」対応
 */
public abstract class SaleGoodsMstActionSupport extends NaikaraActionSupport {

    private static final long serialVersionUID = 1L;

    // 画面表示のタイトル
    protected String title = "販売商品マスタメンテナンス";

    // Help画面名
    protected String helpPageId = "HelpSaleGoodsMst.html";


    /**
     * チェック。<br>
     * <br>
     * コースコードを戻り値で返却する<br>
     * <br>
     * @param なし
     * @return なし
     */
    @Override
    public void validate() {

        // チェックエラーの場合、受取方法、商品形態の再取得。
        try {
            initRadio();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * サービスの呼び出しの実装。
     */
    abstract protected String requestService() throws NaiException;

    /**
     * 受取方法、商品形態を取得する。<br>
     * <br>
     * @param なし
     * @return void
     * @throws Exception
     */
    public void initRadio() throws Exception {

        SaleGoodsMstLoadService service = new SaleGoodsMstLoadService();
        // 受取方法を取得する
        this.saleKbn_rdll =
        		service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_SALE_KBN);
        // 商品形態を取得する
        this.productKbn_rdll =
        		service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_PRODUCT_KBN);

        // 2014.02.18 Add 項目追加「講師への購入メール連絡付き区分」対応
        // 商品形態を取得する
        this.teacherContactKbn_rdll =
        		service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_T_CONTACT_KBN);

    }


    /**
     * 画面のパラメータをモデルにセット。<br>
     * <br>
     * @param なし
     * @return なし
     */
    public void setupModel() {

        // 商品コード
        this.model.setGoodsCd(this.goodsCd_txt);
        // 商品名
        this.model.setGoodsNm(this.goodsNm_txt);
        // 詳細説明
        this.model.setExplanation(this.explanation_txa);
        // 受取方法
        this.model.setSaleKbn(this.saleKbn_rdl);
        // サンプル画像名
        this.model.setImgPhotoNm(this.imgPhoto_filFileName);
        // サンプル画像
        this.model.setImgPhoto(this.imgPhoto_fil);
        // サンプル画像削除
        this.model.setImgPhoto_chkn(this.imgPhoto_chkn);

        // 商品形態
        this.model.setProductKbn(this.productKbn_rdl);
        // 商品ファイル名
        this.model.setGoodsFileNm(this.goodsFile_filFileName);
        // 商品ファイル
        this.model.setGoodsFile(this.goodsFile_fil);
        // 商品ファイル削除
        this.model.setGoodsFile_chkn(this.goodsFile_chkn);
        // 販売価格/ポイント
        BigDecimal salesPrice = StringUtils.isEmpty(this.salesPrice_txt) ? BigDecimal.ZERO
                : new BigDecimal(
                        NaikaraStringUtil.delComma(this.salesPrice_txt));
        this.model.setSalesPrice(salesPrice);
        // 購入先（説明）
        this.model.setPurchaseDescription(this.purchaseDescription_txt);
        // 購入先URL
        this.model.setPurchaseUrl(this.purchaseUrl_txt);
        // 提供期間開始日
        this.model.setUseStrDt(this.useStrDt_txt);
        // 提供期間終了日
        this.model.setUseEndDt(this.useEndDt_txt);

        // 2014.02.18 Add 項目追加「講師への購入メール連絡付き」対応
        // 講師への購入メール連絡付き
        this.model.setTeacherContactKbn(this.teacherContactKbn_rdl);

        // 利用コース メモ
        this.model.setUseCourseMemo(this.useCourseMemo_txt);
        // 備考
        this.model.setRemark(this.remark_txa);

        //処理区分(前画面の引き継ぎ情報)
        this.model.setProcessKbn_Rdl(this.processKbn_rdl);
        //画面表示処理区分
        this.model.setProcessKbn_Txt(this.processKbn_txt);


        // レコードバージョン番号
        String recVerNo = StringUtils.isEmpty(this.recordVerNo) ? "0"
                : this.recordVerNo;
        this.model.setRecordVerNo(Integer.parseInt(recVerNo));

    }

    /** メッセージ */
    protected String message;

    /** 処理区分(前画面の引き継ぎ情報) */
    protected String processKbn_rdl;

    /** 画面表示処理区分 */
    protected String processKbn_txt;

    /** 説明コメント */
    protected String comment;

    /** 商品コード */
    protected String goodsCd_txt;

    /** 商品名 */
    protected String goodsNm_txt;

    /** 詳細説明 */
    protected String explanation_txa;

    /** 受取方法 */
    protected String saleKbn_rdl;
    protected Map<String, String> saleKbn_rdll = new LinkedHashMap<String, String>();

    /** サンプル画像 */
    protected File imgPhoto_fil;
    protected String imgPhoto_filContentType;
    protected String imgPhoto_filFileName;

    /** 削除1 */
    protected String imgPhoto_chkn;

    /** 商品形態 */
    protected String productKbn_rdl;
    protected Map<String, String> productKbn_rdll = new LinkedHashMap<String, String>();

    /** 商品ファイル */
    protected File goodsFile_fil;
    protected String goodsFile_filContentType;
    protected String goodsFile_filFileName;

    /** 削除2 */
    protected String goodsFile_chkn;

    /** 販売価格/ポイント */
    protected String salesPrice_txt;

    /** 購入先（説明） */
    protected String purchaseDescription_txt;

    /** 購入先URL */
    protected String purchaseUrl_txt;

    /** 提供期間開始日 */
    protected String useStrDt_txt;

    /** 提供期間終了日 */
    protected String useEndDt_txt;


    // 2014.02.18 Add 項目追加「講師への購入メール連絡付き」対応
    /** 講師への購入メール連絡付き */
    protected String teacherContactKbn_rdl;
    protected Map<String, String> teacherContactKbn_rdll = new LinkedHashMap<String, String>();


    /** 利用コース メモ */
    protected String useCourseMemo_txt;

    /** 備考 */
    protected String remark_txa;

    /** 排他用レコードバージョン */
    protected String recordVerNo;

    /** 処理結果 */
    protected SaleGoodsMstModel model = new SaleGoodsMstModel();

    /**
     * @return helpPageId
     */
    public String getHelpPageId() {
        return helpPageId;
    }

    /**
     * @param helpPageId
     *            セットする helpPageId
     */
    public void setHelpPageId(String helpPageId) {
        this.helpPageId = helpPageId;
    }

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            セットする title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     *            セットする message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return processKbn_rdl
     */
    public String getProcessKbn_rdl() {
        return processKbn_rdl;
    }

    /**
     * @param processKbn_rdl
     *            セットする processKbn_rdl
     */
    public void setProcessKbn_rdl(String processKbn_rdl) {
        this.processKbn_rdl = processKbn_rdl;
    }

    /**
     * @return processKbn_txt
     */
    public String getProcessKbn_txt() {
        return processKbn_txt;
    }

    /**
     * @param processKbn_txt
     *            セットする processKbn_txt
     */
    public void setProcessKbn_txt(String processKbn_txt) {
        this.processKbn_txt = processKbn_txt;
    }

    /**
     * @return comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment
     *            セットする comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return goodsCd_txt
     */
    public String getGoodsCd_txt() {
        return goodsCd_txt;
    }

    /**
     * @param goodsCd_txt
     *            セットする goodsCd_txt
     */
    public void setGoodsCd_txt(String goodsCd_txt) {
        this.goodsCd_txt = goodsCd_txt;
    }

    /**
     * @return goodsNm_txt
     */
    public String getGoodsNm_txt() {
        return goodsNm_txt;
    }

    /**
     * @param goodsNm_txt
     *            セットする goodsNm_txt
     */
    public void setGoodsNm_txt(String goodsNm_txt) {
        this.goodsNm_txt = goodsNm_txt;
    }

    /**
     * @return explanation_txa
     */
    public String getExplanation_txa() {
        return explanation_txa;
    }

    /**
     * @param explanation_txa
     *            セットする explanation_txa
     */
    public void setExplanation_txa(String explanation_txa) {
        this.explanation_txa = explanation_txa;
    }

    /**
     * @return saleKbn_rdl
     */
    public String getSaleKbn_rdl() {
        return saleKbn_rdl;
    }

    /**
     * @param saleKbn_rdl
     *            セットする saleKbn_rdl
     */
    public void setSaleKbn_rdl(String saleKbn_rdl) {
        this.saleKbn_rdl = saleKbn_rdl;
    }

    /**
     * @return saleKbn_rdll
     */
    public Map<String, String> getSaleKbn_rdll() {
        return saleKbn_rdll;
    }

    /**
     * @param saleKbn_rdll
     *            セットする saleKbn_rdll
     */
    public void setSaleKbn_rdll(Map<String, String> saleKbn_rdll) {
        this.saleKbn_rdll = saleKbn_rdll;
    }

    /**
     * @return imgPhoto_fil
     */
    public File getImgPhoto_fil() {
        return imgPhoto_fil;
    }

    /**
     * @param imgPhoto_fil
     *            セットする imgPhoto_fil
     */
    public void setImgPhoto_fil(File imgPhoto_fil) {
        this.imgPhoto_fil = imgPhoto_fil;
    }

    /**
     * @return imgPhoto_filContentType
     */
    public String getImgPhoto_filContentType() {
        return imgPhoto_filContentType;
    }

    /**
     * @param imgPhoto_filContentType
     *            セットする imgPhoto_filContentType
     */
    public void setImgPhoto_filContentType(String imgPhoto_filContentType) {
        this.imgPhoto_filContentType = imgPhoto_filContentType;
    }

    /**
     * @return imgPhoto_filFileName
     */
    public String getImgPhoto_filFileName() {
        return imgPhoto_filFileName;
    }

    /**
     * @param imgPhoto_filFileName
     *            セットする imgPhoto_filFileName
     */
    public void setImgPhoto_filFileName(String imgPhoto_filFileName) {
        this.imgPhoto_filFileName = imgPhoto_filFileName;
    }

    /**
     * @return imgPhoto_chkn
     */
    public String getImgPhoto_chkn() {
        return imgPhoto_chkn;
    }

    /**
     * @param imgPhoto_chkn
     *            セットする imgPhoto_chkn
     */
    public void setImgPhoto_chkn(String imgPhoto_chkn) {
        this.imgPhoto_chkn = imgPhoto_chkn;
    }

    /**
     * @return productKbn_rdl
     */
    public String getProductKbn_rdl() {
        return productKbn_rdl;
    }

    /**
     * @param productKbn_rdl
     *            セットする productKbn_rdl
     */
    public void setProductKbn_rdl(String productKbn_rdl) {
        this.productKbn_rdl = productKbn_rdl;
    }

    /**
     * @return productKbn_rdll
     */
    public Map<String, String> getProductKbn_rdll() {
        return productKbn_rdll;
    }

    /**
     * @param productKbn_rdll
     *            セットする productKbn_rdll
     */
    public void setProductKbn_rdll(Map<String, String> productKbn_rdll) {
        this.productKbn_rdll = productKbn_rdll;
    }

    /**
     * @return goodsFile_fil
     */
    public File getGoodsFile_fil() {
        return goodsFile_fil;
    }

    /**
     * @param goodsFile_fil
     *            セットする goodsFile_fil
     */
    public void setGoodsFile_fil(File goodsFile_fil) {
        this.goodsFile_fil = goodsFile_fil;
    }

    /**
     * @return goodsFile_filContentType
     */
    public String getGoodsFile_filContentType() {
        return goodsFile_filContentType;
    }

    /**
     * @param goodsFile_filContentType
     *            セットする goodsFile_filContentType
     */
    public void setGoodsFile_filContentType(String goodsFile_filContentType) {
        this.goodsFile_filContentType = goodsFile_filContentType;
    }

    /**
     * @return goodsFile_filFileName
     */
    public String getGoodsFile_filFileName() {
        return goodsFile_filFileName;
    }

    /**
     * @param goodsFile_filFileName
     *            セットする goodsFile_filFileName
     */
    public void setGoodsFile_filFileName(String goodsFile_filFileName) {
        this.goodsFile_filFileName = goodsFile_filFileName;
    }

    /**
     * @return goodsFile_chkn
     */
    public String getGoodsFile_chkn() {
        return goodsFile_chkn;
    }

    /**
     * @param goodsFile_chkn
     *            セットする goodsFile_chkn
     */
    public void setGoodsFile_chkn(String goodsFile_chkn) {
        this.goodsFile_chkn = goodsFile_chkn;
    }

    /**
     * @return salesPrice_txt
     */
    public String getSalesPrice_txt() {
        return salesPrice_txt;
    }

    /**
     * @param salesPrice_txt
     *            セットする salesPrice_txt
     */
    public void setSalesPrice_txt(String salesPrice_txt) {
        this.salesPrice_txt = salesPrice_txt;
    }

    /**
     * @return purchaseDescription_txt
     */
    public String getPurchaseDescription_txt() {
        return purchaseDescription_txt;
    }

    /**
     * @param purchaseDescription_txt
     *            セットする purchaseDescription_txt
     */
    public void setPurchaseDescription_txt(String purchaseDescription_txt) {
        this.purchaseDescription_txt = purchaseDescription_txt;
    }

    /**
     * @return purchaseUrl_txt
     */
    public String getPurchaseUrl_txt() {
        return purchaseUrl_txt;
    }

    /**
     * @param purchaseUrl_txt
     *            セットする purchaseUrl_txt
     */
    public void setPurchaseUrl_txt(String purchaseUrl_txt) {
        this.purchaseUrl_txt = purchaseUrl_txt;
    }

    /**
     * @return useStrDt_txt
     */
    public String getUseStrDt_txt() {
        return useStrDt_txt;
    }

    /**
     * @param useStrDt_txt
     *            セットする useStrDt_txt
     */
    public void setUseStrDt_txt(String useStrDt_txt) {
        this.useStrDt_txt = useStrDt_txt;
    }

    /**
     * @return useEndDt_txt
     */
    public String getUseEndDt_txt() {
        return useEndDt_txt;
    }

    /**
     * @param useEndDt_txt
     *            セットする useEndDt_txt
     */
    public void setUseEndDt_txt(String useEndDt_txt) {
        this.useEndDt_txt = useEndDt_txt;
    }


    // 2014.02.18 Add Start 項目追加「講師への購入メール連絡付き」対応
    /**
     * @return teacherContactKbn_rdl
     */
    public String getTeacherContactKbn_rdl() {
        return teacherContactKbn_rdl;
    }

    /**
     * @param teacherContactKbn_rdl
     *            セットする teacherContactKbn_rdl
     */
    public void setTeacherContactKbn_rdl(String teacherContactKbn_rdl) {
        this.teacherContactKbn_rdl = teacherContactKbn_rdl;
    }

    /**
     * @return teacherContactKbn_rdll
     */
    public Map<String, String> getTeacherContactKbn_rdll() {
        return teacherContactKbn_rdll;
    }

    /**
     * @param teacherContactKbn_rdll
     *            セットする teacherContactKbn_rdll
     */
    public void setTeacherContactKbn_rdll(Map<String, String> teacherContactKbn_rdll) {
        this.teacherContactKbn_rdll = teacherContactKbn_rdll;
    }
    // 2014.02.18 Add End 項目追加「講師への購入メール連絡付き」対応


    /**
     * @return useCourseMemo_txt
     */
    public String getUseCourseMemo_txt() {
        return useCourseMemo_txt;
    }

    /**
     * @param useCourseMemo_txt
     *            セットする useCourseMemo_txt
     */
    public void setUseCourseMemo_txt(String useCourseMemo_txt) {
        this.useCourseMemo_txt = useCourseMemo_txt;
    }

    /**
     * @return remark_txa
     */
    public String getRemark_txa() {
        return remark_txa;
    }

    /**
     * @param remark_txa
     *            セットする remark_txa
     */
    public void setRemark_txa(String remark_txa) {
        this.remark_txa = remark_txa;
    }

    /**
     * @return recordVerNo
     */
    public String getRecordVerNo() {
        return recordVerNo;
    }

    /**
     * @param recordVerNo
     *            セットする recordVerNo
     */
    public void setRecordVerNo(String recordVerNo) {
        this.recordVerNo = recordVerNo;
    }

    /**
     * @return model
     */
    public SaleGoodsMstModel getModel() {
        return model;
    }

    /**
     * @param model
     *            セットする model
     */
    public void setModel(SaleGoodsMstModel model) {
        this.model = model;
    }

}
