package com.naikara_talk.dto;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Timestamp;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DTOクラス<br>
 * <b>クラス名称　　　:</b>販売商品マスタクラス<br>
 * <b>クラス概要　　　:</b>販売商品マスタDTO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS <b>変更履歴　　　　:</b>2013/05/30 TECS 新規作成
 *                                  </b>2014/02/18 TECS 改造 項目追加「講師への購入メール連絡付き区分」対応
 *
 */
public class GoodsMstDto extends AbstractDto {

    private String goodsCd;              // 商品コード
    private String goodsNm;              // 商品名
    private String explanation;          // 詳細説明
    private String saleKbn;              // 受取方法区分
    private String saleKbnNm;            // 受取方法区分名
    private String imgPhotoNm;           // サンプル画像名
    private Blob imgPhoto;               // サンプル画像
    private String productKbn;           // 商品形態区分
    private String productKbnNm;         // 商品形態区分名
    private String goodsFileNm;          // 商品ファイル名
    private Blob goodsFile;              // 商品ファイル
    private BigDecimal salesPrice;       // 販売価格
    private BigDecimal usePoint;         // 利用ポイント
    private String purchaseDescription;  // 購入先説明
    private String purchaseUrl;          // 購入先URL
    private String useStrDt;             // 提供開始日
    private String useEndDt;             // 提供終了日

    // 2014.02.18 Add 項目追加「講師への購入メール連絡付き」対応
    private String teacherContactKbn;    // 講師への購入メール連絡付き区分

    private String useCourseMemo;        // 利用コースメモ
    private String remark;               // 備考
    private int recordVerNo;             // レコードバージョン番号
    private Timestamp insertDttm;        // 登録日時
    private String insertCd;             // 登録者コード
    private Timestamp updateDttm;        // 更新日時
    private String updateCd;             // 更新者コード

    // 画面処理必要項目
    private String useKbn;               // 利用区分
    private File imgPhotoPage;           // サンプル画像
    private String imgPhoto_chkn;        // 削除1
    private File goodsFilePage;          // 商品ファイル
    private String goodsFile_chkn;       // 削除2
    private int returnCode;              // リターンコード

    /**
     * @return imgPhotoPage
     */
    public File getImgPhotoPage() {
        return imgPhotoPage;
    }

    /**
     * @param imgPhotoPage
     *            セットする imgPhotoPage
     */
    public void setImgPhotoPage(File imgPhotoPage) {
        this.imgPhotoPage = imgPhotoPage;
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
     * @return goodsFilePage
     */
    public File getGoodsFilePage() {
        return goodsFilePage;
    }

    /**
     * @param goodsFilePage
     *            セットする goodsFilePage
     */
    public void setGoodsFilePage(File goodsFilePage) {
        this.goodsFilePage = goodsFilePage;
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
     * @return useKbn
     */
    public String getUseKbn() {
        return useKbn;
    }

    /**
     * @param useKbn
     *            セットする useKbn
     */
    public void setUseKbn(String useKbn) {
        this.useKbn = useKbn;
    }

     /**
     * 商品コード取得<br>
     * <br>
     * 商品コードを戻り値で返却する<br>
     * <br>
     *
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
     *
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
     *
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
     *
     * @param goodsNm
     */
    public void setGoodsNm(String goodsNm) {
        this.goodsNm = goodsNm;
    }

    /**
     * 詳細説明取得<br>
     * <br>
     * 詳細説明を戻り値で返却する<br>
     * <br>
     *
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
     *
     * @param explanation
     */
    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    /**
     * 受取方法区分取得<br>
     * <br>
     * 受取方法区分を戻り値で返却する<br>
     * <br>
     *
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
     *
     * @param saleKbn
     */
    public void setSaleKbn(String saleKbn) {
        this.saleKbn = saleKbn;
    }

    /**
     * 受取方法区分名取得<br>
     * <br>
     * 受取方法区分名を戻り値で返却する<br>
     * <br>
     *
     * @return saleKbnNm
     */
    public String getSaleKbnNm() {
        return saleKbnNm;
    }

    /**
     * 受取方法区分名設定<br>
     * <br>
     * 受取方法区分名を引数で設定する<br>
     * <br>
     *
     * @param saleKbnNm
     */
    public void setSaleKbnNm(String saleKbnNm) {
        this.saleKbnNm = saleKbnNm;
    }

    /**
     * サンプル画像名取得<br>
     * <br>
     * サンプル画像名を戻り値で返却する<br>
     * <br>
     *
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
     *
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
     *
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
     *
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
     *
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
     *
     * @param productKbn
     */
    public void setProductKbn(String productKbn) {
        this.productKbn = productKbn;
    }

    /**
     * 商品形態区分名取得<br>
     * <br>
     * 商品形態区分名を戻り値で返却する<br>
     * <br>
     *
     * @return productKbnNm
     */
    public String getProductKbnNm() {
        return productKbnNm;
    }

    /**
     * 商品形態区分名設定<br>
     * <br>
     * 商品形態区分名を引数で設定する<br>
     * <br>
     *
     * @param productKbnNm
     */
    public void setProductKbnNm(String productKbnNm) {
        this.productKbnNm = productKbnNm;
    }

    /**
     * 商品ファイル名取得<br>
     * <br>
     * 商品ファイル名を戻り値で返却する<br>
     * <br>
     *
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
     *
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
     *
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
     *
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
     *
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
     *
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
     *
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
     *
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
     *
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
     *
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
     *
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
     *
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
     *
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
     *
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
     *
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
     *
     * @param useEndDt
     */
    public void setUseEndDt(String useEndDt) {
        this.useEndDt = useEndDt;
    }


    // 2014.02.18 Add Start 項目追加「講師への購入メール連絡付き区分」対応
    /**
    * 講師への購入メール連絡付き区分取得<br>
    * <br>
    * 講師への購入メール連絡付き区分を戻り値で返却する<br>
    * <br>
    *
    * @return teacherContactKbn
    */
   public String getTeacherContactKbn() {
       return teacherContactKbn;
   }

   /**
    * 講師への購入メール連絡付き区分設定<br>
    * <br>
    * 講師への購入メール連絡付き区分を引数で設定する<br>
    * <br>
    *
    * @param teacherContactKbn
    */
   public void setTeacherContactKbn(String teacherContactKbn) {
       this.teacherContactKbn = teacherContactKbn;
   }
   // 2014.02.18 Add End 項目追加「講師への購入メール連絡付き区分」対応


    /**
     * 利用コースメモ取得<br>
     * <br>
     * 利用コースメモを戻り値で返却する<br>
     * <br>
     *
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
     *
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
     *
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
     *
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * レコードバージョン番号取得<br>
     * <br>
     * レコードバージョン番号を戻り値で返却する<br>
     * <br>
     *
     * @return recordVerNo
     */
    public int getRecordVerNo() {
        return recordVerNo;
    }

    /**
     * レコードバージョン番号設定<br>
     * <br>
     * レコードバージョン番号を引数で設定する<br>
     * <br>
     *
     * @param recordVerNo
     */
    public void setRecordVerNo(int recordVerNo) {
        this.recordVerNo = recordVerNo;
    }

    /**
     * 登録日時取得<br>
     * <br>
     * 登録日時を戻り値で返却する<br>
     * <br>
     *
     * @return insertDttm
     */
    public Timestamp getInsertDttm() {
        return insertDttm;
    }

    /**
     * 登録日時設定<br>
     * <br>
     * 登録日時を引数で設定する<br>
     * <br>
     *
     * @param insertDttm
     */
    public void setInsertDttm(Timestamp insertDttm) {
        this.insertDttm = insertDttm;
    }

    /**
     * 登録者コード取得<br>
     * <br>
     * 登録者コードを戻り値で返却する<br>
     * <br>
     *
     * @return insertCd
     */
    public String getInsertCd() {
        return insertCd;
    }

    /**
     * 登録者コード設定<br>
     * <br>
     * 登録者コードを引数で設定する<br>
     * <br>
     *
     * @param insertCd
     */
    public void setInsertCd(String insertCd) {
        this.insertCd = insertCd;
    }

    /**
     * 更新日時取得<br>
     * <br>
     * 更新日時を戻り値で返却する<br>
     * <br>
     *
     * @return updateDttm
     */
    public Timestamp getUpdateDttm() {
        return updateDttm;
    }

    /**
     * 更新日時設定<br>
     * <br>
     * 更新日時を引数で設定する<br>
     * <br>
     *
     * @param updateDttm
     */
    public void setUpdateDttm(Timestamp updateDttm) {
        this.updateDttm = updateDttm;
    }

    /**
     * 更新者コード取得<br>
     * <br>
     * 更新者コードを戻り値で返却する<br>
     * <br>
     *
     * @return updateCd
     */
    public String getUpdateCd() {
        return updateCd;
    }

    /**
     * 更新者コード設定<br>
     * <br>
     * 更新者コードを引数で設定する<br>
     * <br>
     *
     * @param updateCd
     */
    public void setUpdateCd(String updateCd) {
        this.updateCd = updateCd;
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

}
