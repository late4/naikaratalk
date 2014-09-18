package com.naikara_talk.model;

import java.io.File;
import java.math.BigDecimal;


/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>販売商品マスタメンテナンス(単票)。<br>
 * <b>クラス名称　　　:</b>販売商品マスタメンテナンスModelクラス。<br>
 * <b>クラス概要　　　:</b>販売商品マスタメンテナンスModel。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/06/18 TECS 新規作成。
 *                     </b>2014/02/18 TECS 改造 項目追加「講師への購入メール連絡付き区分」対応
 */
public class SaleGoodsMstModel implements Model {

    private static final long serialVersionUID = 1L;

    /** 正常 */
    public static final int PROCESS_NORMAL = 0;

    /** 商品コード */
    private String goodsCd;

    /** 商品名 */
    private String goodsNm;

    /** 詳細説明 */
    private String explanation;

    /** 受取方法 */
    private String saleKbn;

    /** サンプル画像 */
    private File imgPhoto;
    private String imgPhotoNm;
    private String noPhotoPath;  // 画像が登録されない場合用
    /** 削除1 */
    private String imgPhoto_chkn;

    /** 商品形態 */
    private String productKbn;

    /** 商品ファイル */
    private File goodsFile;
    private String goodsFileNm;
    /** 削除2 */
    private String goodsFile_chkn;

    /** 販売価格/ポイント */
    private BigDecimal salesPrice;

    /** 購入先（説明） */
    private String purchaseDescription;

    /** 購入先URL */
    private String purchaseUrl;

    /** 提供期間開始日 */
    private String useStrDt;

    /** 提供期間終了日 */
    private String useEndDt;


    // 2014.02.18 Add 項目追加「講師への購入メール連絡付き」対応
    // 講師への購入メール連絡付き
    private String teacherContactKbn;


    /** 利用コース メモ */
    private String useCourseMemo;

    /** 備考 */
    private String remark;

    /** 処理区分(前画面情報) */
    private String processKbn_rdl;

    /** 画面表示処理区分 */
    private String processKbn_txt;

    /** レコードバージョン番号 */
    protected int recordVerNo;



    /**
     * @return imgPhoto
     */
    public File getImgPhoto() {
        return imgPhoto;
    }

    /**
     * @param imgPhoto
     *            セットする imgPhoto
     */
    public void setImgPhoto(File imgPhoto) {
        this.imgPhoto = imgPhoto;
    }

    /**
     * @return goodsFile
     */
    public File getGoodsFile() {
        return goodsFile;
    }

    /**
     * @param goodsFile
     *            セットする goodsFile
     */
    public void setGoodsFile(File goodsFile) {
        this.goodsFile = goodsFile;
    }

    /**
     * @return goodsCd
     */
    public String getGoodsCd() {
        return goodsCd;
    }

    /**
     * @param goodsCd
     *            セットする goodsCd
     */
    public void setGoodsCd(String goodsCd) {
        this.goodsCd = goodsCd;
    }

    /**
     * @return goodsNm
     */
    public String getGoodsNm() {
        return goodsNm;
    }

    /**
     * @param goodsNm
     *            セットする goodsNm
     */
    public void setGoodsNm(String goodsNm) {
        this.goodsNm = goodsNm;
    }

    /**
     * @return explanation
     */
    public String getExplanation() {
        return explanation;
    }

    /**
     * @param explanation
     *            セットする explanation
     */
    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    /**
     * @return saleKbn
     */
    public String getSaleKbn() {
        return saleKbn;
    }

    /**
     * @param saleKbn
     *            セットする saleKbn
     */
    public void setSaleKbn(String saleKbn) {
        this.saleKbn = saleKbn;
    }

    /**
     * @return imgPhotoNm
     */
    public String getImgPhotoNm() {
        return imgPhotoNm;
    }

    /**
     * @param imgPhotoNm
     *            セットする imgPhotoNm
     */
    public void setImgPhotoNm(String imgPhotoNm) {
        this.imgPhotoNm = imgPhotoNm;
    }

    /**
     * @return noPhotoPath
     */
    public String getNoPhotoPath() {
        return noPhotoPath;
    }

    /**
     * @param noPhotoPath
     *            セットする noPhotoPath
     */
    public void setNoPhotoPath(String noPhotoPath) {
        this.noPhotoPath = noPhotoPath;
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
     * @return productKbn
     */
    public String getProductKbn() {
        return productKbn;
    }

    /**
     * @param productKbn
     *            セットする productKbn
     */
    public void setProductKbn(String productKbn) {
        this.productKbn = productKbn;
    }

    /**
     * @return goodsFileNm
     */
    public String getGoodsFileNm() {
        return goodsFileNm;
    }

    /**
     * @param goodsFileNm
     *            セットする goodsFileNm
     */
    public void setGoodsFileNm(String goodsFileNm) {
        this.goodsFileNm = goodsFileNm;
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
     * @return salesPrice
     */
    public BigDecimal getSalesPrice() {
        return salesPrice;
    }

    /**
     * @param salesPrice
     *            セットする salesPrice
     */
    public void setSalesPrice(BigDecimal salesPrice) {
        this.salesPrice = salesPrice;
    }

    /**
     * @return purchaseDescription
     */
    public String getPurchaseDescription() {
        return purchaseDescription;
    }

    /**
     * @param purchaseDescription
     *            セットする purchaseDescription
     */
    public void setPurchaseDescription(String purchaseDescription) {
        this.purchaseDescription = purchaseDescription;
    }

    /**
     * @return purchaseUrl
     */
    public String getPurchaseUrl() {
        return purchaseUrl;
    }

    /**
     * @param purchaseUrl
     *            セットする purchaseUrl
     */
    public void setPurchaseUrl(String purchaseUrl) {
        this.purchaseUrl = purchaseUrl;
    }

    /**
     * @return useStrDt
     */
    public String getUseStrDt() {
        return useStrDt;
    }

    /**
     * @param useStrDt
     *            セットする useStrDt
     */
    public void setUseStrDt(String useStrDt) {
        this.useStrDt = useStrDt;
    }

    /**
     * @return useEndDt
     */
    public String getUseEndDt() {
        return useEndDt;
    }

    /**
     * @param useEndDt
     *            セットする useEndDt
     */
    public void setUseEndDt(String useEndDt) {
        this.useEndDt = useEndDt;
    }


    // 2014.02.18 Add Start 項目追加「講師への購入メール連絡付き」対応
    /**
     * @return teacherContactKbn
     */
    public String getTeacherContactKbn() {
        return teacherContactKbn;
    }

    /**
     * @param teacherContactKbn
     *            セットする teacherContactKbn
     */
    public void setTeacherContactKbn(String teacherContactKbn) {
        this.teacherContactKbn = teacherContactKbn;
    }
    // 2014.02.18 Add End   項目追加「講師への購入メール連絡付き」対応


    /**
     * @return useCourseMemo
     */
    public String getUseCourseMemo() {
        return useCourseMemo;
    }

    /**
     * @param useCourseMemo
     *            セットする useCourseMemo
     */
    public void setUseCourseMemo(String useCourseMemo) {
        this.useCourseMemo = useCourseMemo;
    }

    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     *            セットする remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return processKbn_rdl
     */
    public String getProcessKbn_Rdl() {
        return processKbn_rdl;
    }

    /**
     * @param processKbn_rdl
     *            セットする processKbn_rdl
     */
    public void setProcessKbn_Rdl(String processKbn_rdl) {
        this.processKbn_rdl = processKbn_rdl;
    }

    /**
     * @return processKbn_txt
     */
    public String getProcessKbn_Txt() {
        return processKbn_txt;
    }

    /**
     * @param processKbn_txt
     *            セットする processKbn_txt
     */
    public void setProcessKbn_Txt(String processKbn_txt) {
        this.processKbn_txt = processKbn_txt;
    }

    /**
     * @return recordVerNo
     */
    public int getRecordVerNo() {
        return recordVerNo;
    }

    /**
     * @param recordVerNo
     *            セットする recordVerNo
     */
    public void setRecordVerNo(int recordVerNo) {
        this.recordVerNo = recordVerNo;
    }

}