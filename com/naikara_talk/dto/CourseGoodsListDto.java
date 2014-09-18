package com.naikara_talk.dto;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Timestamp;

import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DTOクラス<br>
 * <b>クラス名称　　　:</b>コース別商品マスタクラス<br>
 * <b>クラス概要　　　:</b>コース別商品マスタDTO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/06/03 TECS 新規作成
 */
public class CourseGoodsListDto extends AbstractDto {

    private String courseCd;             // コースコード
    private String goodsCd;              // 該当商品コード
    private int recordVerNoC;            // レコードバージョン番号
    private Timestamp insertDttmC;       // 登録日時
    private String insertCdC;            // 登録者コード
    private Timestamp updateDttmC;       // 更新日時
    private String updateCdC;            // 更新者コード

    private String goodsNm;              // 商品名
    private String explanation;          // 詳細説明
    private String saleKbn;              // 受取方法区分
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
    private int recordVerNoG;            // レコードバージョン番号
    private Timestamp insertDttmG;       // 登録日時
    private String insertCdG;            // 登録者コード
    private Timestamp updateDttmG;       // 更新日時
    private String updateCdG;            // 更新者コード

    private int returnCode;        // リターンコード

    // 画面処理必要項目
    private String course_chkn;             // 削除

    /**
     * コースコード取得<br>
     * <br>
     * コースコードを戻り値で返却する<br>
     * <br>
     * @return courseCd
     */
    public String getCourseCd() {
        return courseCd;
    }

    /**
     * コースコード設定<br>
     * <br>
     * コースコードを引数で設定する<br>
     * <br>
     * @param courseCd
     */
    public void setCourseCd(String courseCd) {
        this.courseCd = courseCd;
    }

    /**
     * 該当商品コード取得<br>
     * <br>
     * 該当商品コードを戻り値で返却する<br>
     * <br>
     * @return goodsCd
     */
    public String getGoodsCd() {
        return goodsCd;
    }

    /**
     * 該当商品コード設定<br>
     * <br>
     * 該当商品コードを引数で設定する<br>
     * <br>
     * @param goodsCd
     */
    public void setGoodsCd(String goodsCd) {
        this.goodsCd = goodsCd;
    }

    /**
     * レコードバージョン番号取得<br>
     * <br>
     * レコードバージョン番号を戻り値で返却する<br>
     * <br>
     * @return recordVerNoC
     */
    public int getRecordVerNoC() {
        return recordVerNoC;
    }

    /**
     * レコードバージョン番号設定<br>
     * <br>
     * レコードバージョン番号を引数で設定する<br>
     * <br>
     * @param recordVerNoC
     */
    public void setRecordVerNoC(int recordVerNoC) {
        this.recordVerNoC = recordVerNoC;
    }

    /**
     * 登録日時取得<br>
     * <br>
     * 登録日時を戻り値で返却する<br>
     * <br>
     * @return insertDttmC
     */
    public Timestamp getInsertDttmC() {
        return insertDttmC;
    }

    /**
     * 登録日時設定<br>
     * <br>
     * 登録日時を引数で設定する<br>
     * <br>
     * @param insertDttm
     */
    public void setInsertDttmC(Timestamp insertDttmC) {
        this.insertDttmC = insertDttmC;
    }

    /**
     * 登録者コード取得<br>
     * <br>
     * 登録者コードを戻り値で返却する<br>
     * <br>
     * @return insertCdC
     */
    public String getInsertCdC() {
        return insertCdC;
    }

    /**
     * 登録者コード設定<br>
     * <br>
     * 登録者コードを引数で設定する<br>
     * <br>
     * @param insertCdC
     */
    public void setInsertCdC(String insertCdC) {
        this.insertCdC = insertCdC;
    }

    /**
     * 更新日時取得<br>
     * <br>
     * 更新日時を戻り値で返却する<br>
     * <br>
     * @return updateDttmC
     */
    public Timestamp getUpdateDttmC() {
        return updateDttmC;
    }

    /**
     * 更新日時設定<br>
     * <br>
     * 更新日時を引数で設定する<br>
     * <br>
     * @param updateDttmC
     */
    public void setUpdateDttmC(Timestamp updateDttmC) {
        this.updateDttmC = updateDttmC;
    }

    /**
     * 更新者コード取得<br>
     * <br>
     * 更新者コードを戻り値で返却する<br>
     * <br>
     * @return updateCdC
     */
    public String getUpdateCdC() {
        return updateCdC;
    }

    /**
     * 更新者コード設定<br>
     * <br>
     * 更新者コードを引数で設定する<br>
     * <br>
     * @param updateCdC
     */
    public void setUpdateCdC(String updateCdC) {
        this.updateCdC = updateCdC;
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
     * @return recordVerNoG
     */
    public int getRecordVerNoG() {
        return recordVerNoG;
    }

    /**
     * レコードバージョン番号設定<br>
     * <br>
     * レコードバージョン番号を引数で設定する<br>
     * <br>
     *
     * @param recordVerNoG
     */
    public void setRecordVerNoG(int recordVerNoG) {
        this.recordVerNoG = recordVerNoG;
    }

    /**
     * 登録日時取得<br>
     * <br>
     * 登録日時を戻り値で返却する<br>
     * <br>
     *
     * @return insertDttmG
     */
    public Timestamp getInsertDttmG() {
        return insertDttmG;
    }

    /**
     * 登録日時設定<br>
     * <br>
     * 登録日時を引数で設定する<br>
     * <br>
     *
     * @param insertDttmG
     */
    public void setInsertDttmG(Timestamp insertDttmG) {
        this.insertDttmG = insertDttmG;
    }

    /**
     * 登録者コード取得<br>
     * <br>
     * 登録者コードを戻り値で返却する<br>
     * <br>
     *
     * @return insertCdG
     */
    public String getInsertCdG() {
        return insertCdG;
    }

    /**
     * 登録者コード設定<br>
     * <br>
     * 登録者コードを引数で設定する<br>
     * <br>
     *
     * @param insertCdG
     */
    public void setInsertCdG(String insertCdG) {
        this.insertCdG = insertCdG;
    }

    /**
     * 更新日時取得<br>
     * <br>
     * 更新日時を戻り値で返却する<br>
     * <br>
     *
     * @return updateDttmG
     */
    public Timestamp getUpdateDttmG() {
        return updateDttmG;
    }

    /**
     * 更新日時設定<br>
     * <br>
     * 更新日時を引数で設定する<br>
     * <br>
     *
     * @param updateDttmG
     */
    public void setUpdateDttmG(Timestamp updateDttmG) {
        this.updateDttmG = updateDttmG;
    }

    /**
     * 更新者コード取得<br>
     * <br>
     * 更新者コードを戻り値で返却する<br>
     * <br>
     *
     * @return updateCdG
     */
    public String getUpdateCdG() {
        return updateCdG;
    }

    /**
     * 更新者コード設定<br>
     * <br>
     * 更新者コードを引数で設定する<br>
     * <br>
     *
     * @param updateCdG
     */
    public void setUpdateCdG(String updateCdG) {
        this.updateCdG = updateCdG;
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
     * @return course_chkn
     */
    public String getCourse_chkn() {
        if (this.course_chkn == null) {
            this.course_chkn = NaikaraTalkConstants.FALSE;
        }
        return course_chkn;
    }

    /**
     * @param course_chkn セットする course_chkn
     */
    public void setCourse_chkn(String course_chkn) {
        this.course_chkn = course_chkn;
    }

}
