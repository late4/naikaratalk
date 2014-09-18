package com.naikara_talk.dto;


/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DTOクラス<br>
 * <b>クラス名称　　　:</b>販売商品マスタクラス<br>
 * <b>クラス概要　　　:</b>販売商品マスタDTO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴　　　　:</b>2013/05/30 TECS 新規作成
 */
public class GoodsMstImgDto extends AbstractDto {

    private String goodsCd;              // 商品コード
    private String goodsNm;              // 商品名
    private String imgPhotoNm;           // サンプル画像名
    private byte[] imgPhoto;             // サンプル画像
    private String contentType;
    private int returnCode;             // リターンコード

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
     * サンプル画像の取得<br>
     * <br>
     * サンプル画像を戻り値で返却する<br>
     * <br>
     *
     * @return imgPhoto
     */
    public byte[] getImgPhoto() {
        return imgPhoto;
    }

    /**
     * サンプル画像の設定<br>
     * <br>
     * サンプル画像を引数で設定する<br>
     * <br>
     *
     * @param imgPhoto
     */
    public void setImgPhoto(byte[] imgPhoto) {
        this.imgPhoto = imgPhoto;
    }


    /**
     * コンテンツ種類の取得<br>
     * <br>
     * コンテンツ種類を戻り値で返却する<br>
     * <br>
     *
     * @param imgPhoto
     */
    public String getContentType() {
        return contentType;
    }


    /**
     * コンテンツ種類の設定<br>
     * <br>
     * コンテンツ種類を引数で設定する<br>
     * <br>
     *
     * @param imgPhoto
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
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
