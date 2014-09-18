package com.naikara_talk.dto;

import java.math.BigDecimal;
import java.sql.Blob;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DTOクラス<br>
 * <b>クラス名称　　　:</b>商品購入リストクラス<br>
 * <b>クラス概要　　　:</b>商品購入リストDTO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/06/14 TECS 新規作成
 */
public class GoodsPurchaseListDto extends AbstractDto {

    private String purchaseId;           // 購入ID
    private String paymentMethodKbn;     // 支払方法区分
    private String studentId;            // 受講者ID (購入者ID)
    private String purchaseDt;           // 購入日
    private String purchaseTm;           // 購入時刻
    private String goodsCd;              // 商品コード
    private String goodsNm;              // 商品名
    private String saleKbn;              // 受取方法区分
    private String saleNm;               // 受取方法
    private BigDecimal purchaseYen;      // 購入金額(税込)
    private BigDecimal paymentUsePoint;  // 有償利用ポイント
    private BigDecimal freeUsePoint;     // 無償利用ポイント
    private String explanation;          // 詳細説明
    private String imgPhotoNm;           // サンプル画像名
    private Blob imgPhoto;               // サンプル画像
    private String productKbn;           // 商品形態区分
    private String goodsFileNm;          // 商品ファイル名
    private Blob goodsFile;              // 商品ファイル
    private BigDecimal salesPrice;       // 販売価格
    private BigDecimal usePoint;         // 利用ポイント
    private String purchaseDescription;  // 購入先説明
    private String purchaseUrl;          // 購入先URL
    private String useStrDt;             // 提供開始日
    private String useEndDt;             // 提供終了日
    private String useCourseMemo;        // 利用コースメモ
    private String remark;               // 備考

    private int returnCode;              // リターンコード
    private String purchaseDtNext;       // 購入日+ 1年

    /**
     * 購入ID取得<br>
     * <br>
     * 購入IDを戻り値で返却する<br>
     * <br>
     * @return purchaseId
     */
    public String getPurchaseId() {
        return purchaseId;
    }

    /**
     * 購入ID設定<br>
     * <br>
     * 購入IDを引数で設定する<br>
     * <br>
     * @param purchaseId
     */
    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
    }

    /**
     * 支払方法区分取得<br>
     * <br>
     * 支払方法区分を戻り値で返却する<br>
     * <br>
     * @return paymentMethodKbn
     */
    public String getPaymentMethodKbn() {
        return paymentMethodKbn;
    }

    /**
     * 支払方法区分設定<br>
     * <br>
     * 支払方法区分を引数で設定する<br>
     * <br>
     * @param paymentMethodKbn
     */
    public void setPaymentMethodKbn(String paymentMethodKbn) {
        this.paymentMethodKbn = paymentMethodKbn;
    }

    /**
     * 受講者ID (購入者ID)取得<br>
     * <br>
     * 受講者ID (購入者ID)を戻り値で返却する<br>
     * <br>
     * @return studentId
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * 受講者ID (購入者ID)設定<br>
     * <br>
     * 受講者ID (購入者ID)を引数で設定する<br>
     * <br>
     * @param studentId
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * 購入日取得<br>
     * <br>
     * 購入日を戻り値で返却する<br>
     * <br>
     * @return purchaseDt
     */
    public String getPurchaseDt() {
        return purchaseDt;
    }

    /**
     * 購入日設定<br>
     * <br>
     * 購入日を引数で設定する<br>
     * <br>
     * @param purchaseDt
     */
    public void setPurchaseDt(String purchaseDt) {
        this.purchaseDt = purchaseDt;
    }

    /**
     * 購入時刻取得<br>
     * <br>
     * 購入時刻を戻り値で返却する<br>
     * <br>
     * @return purchaseTm
     */
    public String getPurchaseTm() {
        return purchaseTm;
    }

    /**
     * 購入時刻設定<br>
     * <br>
     * 購入時刻を引数で設定する<br>
     * <br>
     * @param purchaseTm
     */
    public void setPurchaseTm(String purchaseTm) {
        this.purchaseTm = purchaseTm;
    }

    /**
     * 商品コード取得<br>
     * <br>
     * 商品コードを戻り値で返却する<br>
     * <br>
     * @return goodsCd
     */
    public String getGoodsCd() {
        return goodsCd;
    }

    /**
     * 商品コード設定<br>
     * <br>
     * 商品コードを引数で設定する<br>
     * <br>
     * @param goodsCd
     */
    public void setGoodsCd(String goodsCd) {
        this.goodsCd = goodsCd;
    }

    /**
     * 商品名取得<br>
     * <br>
     * 商品名を戻り値で返却する<br>
     * <br>
     * @return goodsNm
     */
    public String getGoodsNm() {
        return goodsNm;
    }

    /**
     * 商品名設定<br>
     * <br>
     * 商品名を引数で設定する<br>
     * <br>
     * @param goodsNm
     */
    public void setGoodsNm(String goodsNm) {
        this.goodsNm = goodsNm;
    }

    /**
     * 受取方法区分取得<br>
     * <br>
     * 受取方法区分を戻り値で返却する<br>
     * <br>
     * @return saleKbn
     */
    public String getSaleKbn() {
        return saleKbn;
    }

    /**
     * 受取方法区分設定<br>
     * <br>
     * 受取方法区分を引数で設定する<br>
     * <br>
     * @param saleKbn
     */
    public void setSaleKbn(String saleKbn) {
        this.saleKbn = saleKbn;
    }

    /**
     * 受取方法取得<br>
     * <br>
     * 受取方法を戻り値で返却する<br>
     * <br>
     * @return saleNm
     */
    public String getSaleNm() {
        return saleNm;
    }

    /**
     * 受取方法設定<br>
     * <br>
     * 受取方法を引数で設定する<br>
     * <br>
     * @param saleNm
     */
    public void setSaleNm(String saleNm) {
        this.saleNm = saleNm;
    }

    /**
     * 購入金額(税込)取得<br>
     * <br>
     * 購入金額(税込)を戻り値で返却する<br>
     * <br>
     * @return purchaseYen
     */
    public BigDecimal getPurchaseYen() {
        return purchaseYen;
    }

    /**
     * 購入金額(税込)設定<br>
     * <br>
     * 購入金額(税込)を引数で設定する<br>
     * <br>
     * @param purchaseYen
     */
    public void setPurchaseYen(BigDecimal purchaseYen) {
        this.purchaseYen = purchaseYen;
    }

    /**
     * 有償利用ポイント取得<br>
     * <br>
     * 有償利用ポイントを戻り値で返却する<br>
     * <br>
     * @return paymentUsePoint
     */
    public BigDecimal getPaymentUsePoint() {
        return paymentUsePoint;
    }

    /**
     * 有償利用ポイント設定<br>
     * <br>
     * 有償利用ポイントを引数で設定する<br>
     * <br>
     * @param paymentUsePoint
     */
    public void setPaymentUsePoint(BigDecimal paymentUsePoint) {
        this.paymentUsePoint = paymentUsePoint;
    }

    /**
     * 無償利用ポイント取得<br>
     * <br>
     * 無償利用ポイントを戻り値で返却する<br>
     * <br>
     * @return freeUsePoint
     */
    public BigDecimal getFreeUsePoint() {
        return freeUsePoint;
    }

    /**
     * 無償利用ポイント設定<br>
     * <br>
     * 無償利用ポイントを引数で設定する<br>
     * <br>
     * @param freeUsePoint
     */
    public void setFreeUsePoint(BigDecimal freeUsePoint) {
        this.freeUsePoint = freeUsePoint;
    }

    /**
     * 詳細説明取得<br>
     * <br>
     * 詳細説明を戻り値で返却する<br>
     * <br>
     * @return explanation
     */
    public String getExplanation() {
        return explanation;
    }

    /**
     * 詳細説明設定<br>
     * <br>
     * 詳細説明を引数で設定する<br>
     * <br>
     * @param explanation
     */
    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    /**
     * サンプル画像名取得<br>
     * <br>
     * サンプル画像名を戻り値で返却する<br>
     * <br>
     * @return imgPhotoNm
     */
    public String getImgPhotoNm() {
        return imgPhotoNm;
    }

    /**
     * サンプル画像名設定<br>
     * <br>
     * サンプル画像名を引数で設定する<br>
     * <br>
     * @param imgPhotoNm
     */
    public void setImgPhotoNm(String imgPhotoNm) {
        this.imgPhotoNm = imgPhotoNm;
    }

    /**
     * サンプル画像取得<br>
     * <br>
     * サンプル画像を戻り値で返却する<br>
     * <br>
     * @return imgPhoto
     */
    public Blob getImgPhoto() {
        return imgPhoto;
    }

    /**
     * サンプル画像設定<br>
     * <br>
     * サンプル画像を引数で設定する<br>
     * <br>
     * @param imgPhoto
     */
    public void setImgPhoto(Blob imgPhoto) {
        this.imgPhoto = imgPhoto;
    }

    /**
     * 商品形態区分取得<br>
     * <br>
     * 商品形態区分を戻り値で返却する<br>
     * <br>
     * @return productKbn
     */
    public String getProductKbn() {
        return productKbn;
    }

    /**
     * 商品形態区分設定<br>
     * <br>
     * 商品形態区分を引数で設定する<br>
     * <br>
     * @param productKbn
     */
    public void setProductKbn(String productKbn) {
        this.productKbn = productKbn;
    }

    /**
     * 商品ファイル名取得<br>
     * <br>
     * 商品ファイル名を戻り値で返却する<br>
     * <br>
     * @return goodsFileNm
     */
    public String getGoodsFileNm() {
        return goodsFileNm;
    }

    /**
     * 商品ファイル名設定<br>
     * <br>
     * 商品ファイル名を引数で設定する<br>
     * <br>
     * @param goodsFileNm
     */
    public void setGoodsFileNm(String goodsFileNm) {
        this.goodsFileNm = goodsFileNm;
    }

    /**
     * 商品ファイル取得<br>
     * <br>
     * 商品ファイルを戻り値で返却する<br>
     * <br>
     * @return goodsFile
     */
    public Blob getGoodsFile() {
        return goodsFile;
    }

    /**
     * 商品ファイル設定<br>
     * <br>
     * 商品ファイルを引数で設定する<br>
     * <br>
     * @param goodsFile
     */
    public void setGoodsFile(Blob goodsFile) {
        this.goodsFile = goodsFile;
    }

    /**
     * 販売価格取得<br>
     * <br>
     * 販売価格を戻り値で返却する<br>
     * <br>
     * @return salesPrice
     */
    public BigDecimal getSalesPrice() {
        return salesPrice;
    }

    /**
     * 販売価格設定<br>
     * <br>
     * 販売価格を引数で設定する<br>
     * <br>
     * @param salesPrice
     */
    public void setSalesPrice(BigDecimal salesPrice) {
        this.salesPrice = salesPrice;
    }

    /**
     * 利用ポイント取得<br>
     * <br>
     * 利用ポイントを戻り値で返却する<br>
     * <br>
     * @return usePoint
     */
    public BigDecimal getUsePoint() {
        return usePoint;
    }

    /**
     * 利用ポイント設定<br>
     * <br>
     * 利用ポイントを引数で設定する<br>
     * <br>
     * @param usePoint
     */
    public void setUsePoint(BigDecimal usePoint) {
        this.usePoint = usePoint;
    }

    /**
     * 購入先説明取得<br>
     * <br>
     * 購入先説明を戻り値で返却する<br>
     * <br>
     * @return purchaseDescription
     */
    public String getPurchaseDescription() {
        return purchaseDescription;
    }

    /**
     * 購入先説明設定<br>
     * <br>
     * 購入先説明を引数で設定する<br>
     * <br>
     * @param purchaseDescription
     */
    public void setPurchaseDescription(String purchaseDescription) {
        this.purchaseDescription = purchaseDescription;
    }

    /**
     * 購入先URL取得<br>
     * <br>
     * 購入先URLを戻り値で返却する<br>
     * <br>
     * @return purchaseUrl
     */
    public String getPurchaseUrl() {
        return purchaseUrl;
    }

    /**
     * 購入先URL設定<br>
     * <br>
     * 購入先URLを引数で設定する<br>
     * <br>
     * @param purchaseUrl
     */
    public void setPurchaseUrl(String purchaseUrl) {
        this.purchaseUrl = purchaseUrl;
    }

    /**
     * 提供開始日取得<br>
     * <br>
     * 提供開始日を戻り値で返却する<br>
     * <br>
     * @return useStrDt
     */
    public String getUseStrDt() {
        return useStrDt;
    }

    /**
     * 提供開始日設定<br>
     * <br>
     * 提供開始日を引数で設定する<br>
     * <br>
     * @param useStrDt
     */
    public void setUseStrDt(String useStrDt) {
        this.useStrDt = useStrDt;
    }

    /**
     * 提供終了日取得<br>
     * <br>
     * 提供終了日を戻り値で返却する<br>
     * <br>
     * @return useEndDt
     */
    public String getUseEndDt() {
        return useEndDt;
    }

    /**
     * 提供終了日設定<br>
     * <br>
     * 提供終了日を引数で設定する<br>
     * <br>
     * @param useEndDt
     */
    public void setUseEndDt(String useEndDt) {
        this.useEndDt = useEndDt;
    }

    /**
     * 利用コースメモ取得<br>
     * <br>
     * 利用コースメモを戻り値で返却する<br>
     * <br>
     * @return useCourseMemo
     */
    public String getUseCourseMemo() {
        return useCourseMemo;
    }

    /**
     * 利用コースメモ設定<br>
     * <br>
     * 利用コースメモを引数で設定する<br>
     * <br>
     * @param useCourseMemo
     */
    public void setUseCourseMemo(String useCourseMemo) {
        this.useCourseMemo = useCourseMemo;
    }

    /**
     * 備考取得<br>
     * <br>
     * 備考を戻り値で返却する<br>
     * <br>
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 備考設定<br>
     * <br>
     * 備考を引数で設定する<br>
     * <br>
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * リターンコード取得<br>
     * <br>
     * リターンコードを戻り値で返却する<br>
     * <br>
     * @return returnCode
     */
    public int getReturnCode() {
        return returnCode;
    }

    /**
     * リターンコード設定<br>
     * <br>
     * リターンコードを引数で設定する<br>
     * <br>
     * @param returnCode
     */
    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

    /**
     * 購入日+ 1年取得<br>
     * <br>
     * 購入日+ 1年を戻り値で返却する<br>
     * <br>
     * @return purchaseDtNext
     */
    public String getPurchaseDtNext() {
        return purchaseDtNext;
    }

    /**
     * 購入日+ 1年設定<br>
     * <br>
     * 購入日+ 1年を引数で設定する<br>
     * <br>
     * @param purchaseDtNext
     */
    public void setPurchaseDtNext(String purchaseDtNext) {
        this.purchaseDtNext = purchaseDtNext;
    }

}
