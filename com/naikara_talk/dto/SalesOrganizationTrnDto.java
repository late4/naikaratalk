package com.naikara_talk.dto;

import java.sql.Timestamp;
import java.math.BigDecimal;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DTOクラス<br>
 * <b>クラス名称　　　:</b>売上集計法人テーブルクラス<br>
 * <b>クラス概要　　　:</b>売上集計法人テーブルDTO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/01 TECS 新規作成
 */
public class SalesOrganizationTrnDto extends AbstractDto{

	private String yyyyMm;                         //年月
	private String organizationId;                 //組織ID
	private String organizationJnm;                //組織名
	private BigDecimal compensationBeforePoint;    //前受有償ポイント（合計）
	private BigDecimal compensationPurchasePoint;  //購入有償ポイント（合計）
	private BigDecimal compensationUsePoint;       //利用有償ポイント（合計）
	private BigDecimal goodsPurchaseYen;           //ポイント外商品購入（合計）
	private BigDecimal freeBeforePoint;            //前受無償ポイント（合計）
	private BigDecimal freePurchasePoint;          //購入無償ポイント（合計）
	private BigDecimal freeUsePoint;               //利用無償ポイント（合計）
	private String billNo;                         //請求書番号
	private String requestOrganizationJnm;         //請求先情報：組織名
	private String requestTel;                     //請求先情報：電話番号
	private String requestZipCd;                   //請求先情報：郵便番号
	private String requestAddressAreaCd;           //請求先情報：住所(地域)コード
	private String requestAddressAreaNm;           //請求先情報：住所(地域)名
	private String requestAddressPrefectureCd;     //請求先情報：住所(都道府県)コード
	private String requestAddressPrefectureNm;     //請求先情報：住所(都道府県)名
	private String requestAddressCity;             //請求先情報：住所(市区町村)
	private String requestAddressOthers;           //請求先情報：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)
	private String requestManagFamilyJnm;          //請求先情報：責任者名(姓)
	private String requestManagFirstJnm;           //請求先情報：責任者名(名)
	private String requestManagPosition;           //請求先情報：責任者所属
	private String requestPaymentSiteKbn;          //請求先情報：支払サイト区分
	private String requestPaymentSiteNm;           //請求先情報：支払サイト
	private int recordVerNo;                       //レコードバージョン番号
	private Timestamp insertDttm;                  //登録日時
	private String insertCd;                       //登録者コード
	private Timestamp updateDttm;                  //更新日時
	private String updateCd;                       //更新者コード
	private int returnCode;                        //リターンコード

	/**
	 * 年月取得<br>
	 * <br>
	 * 年月を戻り値で返却する<br>
	 * <br>
	 * @return yyyyMm
	 */
	public String getYyyyMm(){
		return yyyyMm;
	}

	/**
	 * 年月設定<br>
	 * <br>
	 * 年月を引数で設定する<br>
	 * <br>
	 * @param yyyyMm
	 */
	public void setYyyyMm(String yyyyMm){
		this.yyyyMm = yyyyMm;
	}

	/**
	 * 組織ID取得<br>
	 * <br>
	 * 組織IDを戻り値で返却する<br>
	 * <br>
	 * @return organizationId
	 */
	public String getOrganizationId(){
		return organizationId;
	}

	/**
	 * 組織ID設定<br>
	 * <br>
	 * 組織IDを引数で設定する<br>
	 * <br>
	 * @param organizationId
	 */
	public void setOrganizationId(String organizationId){
		this.organizationId = organizationId;
	}

	/**
	 * 組織名取得<br>
	 * <br>
	 * 組織名を戻り値で返却する<br>
	 * <br>
	 * @return organizationJnm
	 */
	public String getOrganizationJnm(){
		return organizationJnm;
	}

	/**
	 * 組織名設定<br>
	 * <br>
	 * 組織名を引数で設定する<br>
	 * <br>
	 * @param organizationJnm
	 */
	public void setOrganizationJnm(String organizationJnm){
		this.organizationJnm = organizationJnm;
	}

	/**
	 * 前受有償ポイント（合計）取得<br>
	 * <br>
	 * 前受有償ポイント（合計）を戻り値で返却する<br>
	 * <br>
	 * @return compensationBeforePoint
	 */
	public BigDecimal getCompensationBeforePoint(){
		return compensationBeforePoint;
	}

	/**
	 * 前受有償ポイント（合計）設定<br>
	 * <br>
	 * 前受有償ポイント（合計）を引数で設定する<br>
	 * <br>
	 * @param compensationBeforePoint
	 */
	public void setCompensationBeforePoint(BigDecimal compensationBeforePoint){
		this.compensationBeforePoint = compensationBeforePoint;
	}

	/**
	 * 購入有償ポイント（合計）取得<br>
	 * <br>
	 * 購入有償ポイント（合計）を戻り値で返却する<br>
	 * <br>
	 * @return compensationPurchasePoint
	 */
	public BigDecimal getCompensationPurchasePoint(){
		return compensationPurchasePoint;
	}

	/**
	 * 購入有償ポイント（合計）設定<br>
	 * <br>
	 * 購入有償ポイント（合計）を引数で設定する<br>
	 * <br>
	 * @param compensationPurchasePoint
	 */
	public void setCompensationPurchasePoint(BigDecimal compensationPurchasePoint){
		this.compensationPurchasePoint = compensationPurchasePoint;
	}

	/**
	 * 利用有償ポイント（合計）取得<br>
	 * <br>
	 * 利用有償ポイント（合計）を戻り値で返却する<br>
	 * <br>
	 * @return compensationUsePoint
	 */
	public BigDecimal getCompensationUsePoint(){
		return compensationUsePoint;
	}

	/**
	 * 利用有償ポイント（合計）設定<br>
	 * <br>
	 * 利用有償ポイント（合計）を引数で設定する<br>
	 * <br>
	 * @param compensationUsePoint
	 */
	public void setCompensationUsePoint(BigDecimal compensationUsePoint){
		this.compensationUsePoint = compensationUsePoint;
	}

	/**
	 * ポイント外商品購入（合計）取得<br>
	 * <br>
	 * ポイント外商品購入（合計）を戻り値で返却する<br>
	 * <br>
	 * @return goodsPurchaseYen
	 */
	public BigDecimal getGoodsPurchaseYen(){
		return goodsPurchaseYen;
	}

	/**
	 * ポイント外商品購入（合計）設定<br>
	 * <br>
	 * ポイント外商品購入（合計）を引数で設定する<br>
	 * <br>
	 * @param goodsPurchaseYen
	 */
	public void setGoodsPurchaseYen(BigDecimal goodsPurchaseYen){
		this.goodsPurchaseYen = goodsPurchaseYen;
	}

	/**
	 * 前受無償ポイント（合計）取得<br>
	 * <br>
	 * 前受無償ポイント（合計）を戻り値で返却する<br>
	 * <br>
	 * @return freeBeforePoint
	 */
	public BigDecimal getFreeBeforePoint(){
		return freeBeforePoint;
	}

	/**
	 * 前受無償ポイント（合計）設定<br>
	 * <br>
	 * 前受無償ポイント（合計）を引数で設定する<br>
	 * <br>
	 * @param freeBeforePoint
	 */
	public void setFreeBeforePoint(BigDecimal freeBeforePoint){
		this.freeBeforePoint = freeBeforePoint;
	}

	/**
	 * 購入無償ポイント（合計）取得<br>
	 * <br>
	 * 購入無償ポイント（合計）を戻り値で返却する<br>
	 * <br>
	 * @return freePurchasePoint
	 */
	public BigDecimal getFreePurchasePoint(){
		return freePurchasePoint;
	}

	/**
	 * 購入無償ポイント（合計）設定<br>
	 * <br>
	 * 購入無償ポイント（合計）を引数で設定する<br>
	 * <br>
	 * @param freePurchasePoint
	 */
	public void setFreePurchasePoint(BigDecimal freePurchasePoint){
		this.freePurchasePoint = freePurchasePoint;
	}

	/**
	 * 利用無償ポイント（合計）取得<br>
	 * <br>
	 * 利用無償ポイント（合計）を戻り値で返却する<br>
	 * <br>
	 * @return freeUsePoint
	 */
	public BigDecimal getFreeUsePoint(){
		return freeUsePoint;
	}

	/**
	 * 利用無償ポイント（合計）設定<br>
	 * <br>
	 * 利用無償ポイント（合計）を引数で設定する<br>
	 * <br>
	 * @param freeUsePoint
	 */
	public void setFreeUsePoint(BigDecimal freeUsePoint){
		this.freeUsePoint = freeUsePoint;
	}

	/**
	 * 請求書番号取得<br>
	 * <br>
	 * 請求書番号を戻り値で返却する<br>
	 * <br>
	 * @return billNo
	 */
	public String getBillNo(){
		return billNo;
	}

	/**
	 * 請求書番号設定<br>
	 * <br>
	 * 請求書番号を引数で設定する<br>
	 * <br>
	 * @param billNo
	 */
	public void setBillNo(String billNo){
		this.billNo = billNo;
	}

	/**
	 * 請求先情報：組織名取得<br>
	 * <br>
	 * 請求先情報：組織名を戻り値で返却する<br>
	 * <br>
	 * @return requestOrganizationJnm
	 */
	public String getRequestOrganizationJnm(){
		return requestOrganizationJnm;
	}

	/**
	 * 請求先情報：組織名設定<br>
	 * <br>
	 * 請求先情報：組織名を引数で設定する<br>
	 * <br>
	 * @param requestOrganizationJnm
	 */
	public void setRequestOrganizationJnm(String requestOrganizationJnm){
		this.requestOrganizationJnm = requestOrganizationJnm;
	}

	/**
	 * 請求先情報：電話番号取得<br>
	 * <br>
	 * 請求先情報：電話番号を戻り値で返却する<br>
	 * <br>
	 * @return requestTel
	 */
	public String getRequestTel(){
		return requestTel;
	}

	/**
	 * 請求先情報：電話番号設定<br>
	 * <br>
	 * 請求先情報：電話番号を引数で設定する<br>
	 * <br>
	 * @param requestTel
	 */
	public void setRequestTel(String requestTel){
		this.requestTel = requestTel;
	}

	/**
	 * 請求先情報：郵便番号取得<br>
	 * <br>
	 * 請求先情報：郵便番号を戻り値で返却する<br>
	 * <br>
	 * @return requestZipCd
	 */
	public String getRequestZipCd(){
		return requestZipCd;
	}

	/**
	 * 請求先情報：郵便番号設定<br>
	 * <br>
	 * 請求先情報：郵便番号を引数で設定する<br>
	 * <br>
	 * @param requestZipCd
	 */
	public void setRequestZipCd(String requestZipCd){
		this.requestZipCd = requestZipCd;
	}

	/**
	 * 請求先情報：住所(地域)コード取得<br>
	 * <br>
	 * 請求先情報：住所(地域)コードを戻り値で返却する<br>
	 * <br>
	 * @return requestAddressAreaCd
	 */
	public String getRequestAddressAreaCd(){
		return requestAddressAreaCd;
	}

	/**
	 * 請求先情報：住所(地域)コード設定<br>
	 * <br>
	 * 請求先情報：住所(地域)コードを引数で設定する<br>
	 * <br>
	 * @param requestAddressAreaCd
	 */
	public void setRequestAddressAreaCd(String requestAddressAreaCd){
		this.requestAddressAreaCd = requestAddressAreaCd;
	}

	/**
	 * 請求先情報：住所(地域)名取得<br>
	 * <br>
	 * 請求先情報：住所(地域)名を戻り値で返却する<br>
	 * <br>
	 * @return requestAddressAreaNm
	 */
	public String getRequestAddressAreaNm(){
		return requestAddressAreaNm;
	}

	/**
	 * 請求先情報：住所(地域)名設定<br>
	 * <br>
	 * 請求先情報：住所(地域)名を引数で設定する<br>
	 * <br>
	 * @param requestAddressAreaNm
	 */
	public void setRequestAddressAreaNm(String requestAddressAreaNm){
		this.requestAddressAreaNm = requestAddressAreaNm;
	}

	/**
	 * 請求先情報：住所(都道府県)コード取得<br>
	 * <br>
	 * 請求先情報：住所(都道府県)コードを戻り値で返却する<br>
	 * <br>
	 * @return requestAddressPrefectureCd
	 */
	public String getRequestAddressPrefectureCd(){
		return requestAddressPrefectureCd;
	}

	/**
	 * 請求先情報：住所(都道府県)コード設定<br>
	 * <br>
	 * 請求先情報：住所(都道府県)コードを引数で設定する<br>
	 * <br>
	 * @param requestAddressPrefectureCd
	 */
	public void setRequestAddressPrefectureCd(String requestAddressPrefectureCd){
		this.requestAddressPrefectureCd = requestAddressPrefectureCd;
	}

	/**
	 * 請求先情報：住所(都道府県)名取得<br>
	 * <br>
	 * 請求先情報：住所(都道府県)名を戻り値で返却する<br>
	 * <br>
	 * @return requestAddressPrefectureNm
	 */
	public String getRequestAddressPrefectureNm(){
		return requestAddressPrefectureNm;
	}

	/**
	 * 請求先情報：住所(都道府県)名設定<br>
	 * <br>
	 * 請求先情報：住所(都道府県)名を引数で設定する<br>
	 * <br>
	 * @param requestAddressPrefectureNm
	 */
	public void setRequestAddressPrefectureNm(String requestAddressPrefectureNm){
		this.requestAddressPrefectureNm = requestAddressPrefectureNm;
	}

	/**
	 * 請求先情報：住所(市区町村)取得<br>
	 * <br>
	 * 請求先情報：住所(市区町村)を戻り値で返却する<br>
	 * <br>
	 * @return requestAddressCity
	 */
	public String getRequestAddressCity(){
		return requestAddressCity;
	}

	/**
	 * 請求先情報：住所(市区町村)設定<br>
	 * <br>
	 * 請求先情報：住所(市区町村)を引数で設定する<br>
	 * <br>
	 * @param requestAddressCity
	 */
	public void setRequestAddressCity(String requestAddressCity){
		this.requestAddressCity = requestAddressCity;
	}

	/**
	 * 請求先情報：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)取得<br>
	 * <br>
	 * 請求先情報：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)を戻り値で返却する<br>
	 * <br>
	 * @return requestAddressOthers
	 */
	public String getRequestAddressOthers(){
		return requestAddressOthers;
	}

	/**
	 * 請求先情報：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)設定<br>
	 * <br>
	 * 請求先情報：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)を引数で設定する<br>
	 * <br>
	 * @param requestAddressOthers
	 */
	public void setRequestAddressOthers(String requestAddressOthers){
		this.requestAddressOthers = requestAddressOthers;
	}

	/**
	 * 請求先情報：責任者名(姓)取得<br>
	 * <br>
	 * 請求先情報：責任者名(姓)を戻り値で返却する<br>
	 * <br>
	 * @return requestManagFamilyJnm
	 */
	public String getRequestManagFamilyJnm(){
		return requestManagFamilyJnm;
	}

	/**
	 * 請求先情報：責任者名(姓)設定<br>
	 * <br>
	 * 請求先情報：責任者名(姓)を引数で設定する<br>
	 * <br>
	 * @param requestManagFamilyJnm
	 */
	public void setRequestManagFamilyJnm(String requestManagFamilyJnm){
		this.requestManagFamilyJnm = requestManagFamilyJnm;
	}

	/**
	 * 請求先情報：責任者名(名)取得<br>
	 * <br>
	 * 請求先情報：責任者名(名)を戻り値で返却する<br>
	 * <br>
	 * @return requestManagFirstJnm
	 */
	public String getRequestManagFirstJnm(){
		return requestManagFirstJnm;
	}

	/**
	 * 請求先情報：責任者名(名)設定<br>
	 * <br>
	 * 請求先情報：責任者名(名)を引数で設定する<br>
	 * <br>
	 * @param requestManagFirstJnm
	 */
	public void setRequestManagFirstJnm(String requestManagFirstJnm){
		this.requestManagFirstJnm = requestManagFirstJnm;
	}

	/**
	 * 請求先情報：責任者所属取得<br>
	 * <br>
	 * 請求先情報：責任者所属を戻り値で返却する<br>
	 * <br>
	 * @return requestManagPosition
	 */
	public String getRequestManagPosition(){
		return requestManagPosition;
	}

	/**
	 * 請求先情報：責任者所属設定<br>
	 * <br>
	 * 請求先情報：責任者所属を引数で設定する<br>
	 * <br>
	 * @param requestManagPosition
	 */
	public void setRequestManagPosition(String requestManagPosition){
		this.requestManagPosition = requestManagPosition;
	}

	/**
	 * 請求先情報：支払サイト区分取得<br>
	 * <br>
	 * 請求先情報：支払サイト区分を戻り値で返却する<br>
	 * <br>
	 * @return requestPaymentSiteKbn
	 */
	public String getRequestPaymentSiteKbn(){
		return requestPaymentSiteKbn;
	}

	/**
	 * 請求先情報：支払サイト区分設定<br>
	 * <br>
	 * 請求先情報：支払サイト区分を引数で設定する<br>
	 * <br>
	 * @param requestPaymentSiteKbn
	 */
	public void setRequestPaymentSiteKbn(String requestPaymentSiteKbn){
		this.requestPaymentSiteKbn = requestPaymentSiteKbn;
	}

	/**
	 * 請求先情報：支払サイト取得<br>
	 * <br>
	 * 請求先情報：支払サイトを戻り値で返却する<br>
	 * <br>
	 * @return requestPaymentSiteNm
	 */
	public String getRequestPaymentSiteNm(){
		return requestPaymentSiteNm;
	}

	/**
	 * 請求先情報：支払サイト設定<br>
	 * <br>
	 * 請求先情報：支払サイトを引数で設定する<br>
	 * <br>
	 * @param requestPaymentSiteNm
	 */
	public void setRequestPaymentSiteNm(String requestPaymentSiteNm){
		this.requestPaymentSiteNm = requestPaymentSiteNm;
	}

	/**
	 * レコードバージョン番号取得<br>
	 * <br>
	 * レコードバージョン番号を戻り値で返却する<br>
	 * <br>
	 * @return recordVerNo
	 */
	public int getRecordVerNo(){
		return recordVerNo;
	}

	/**
	 * レコードバージョン番号設定<br>
	 * <br>
	 * レコードバージョン番号を引数で設定する<br>
	 * <br>
	 * @param recordVerNo
	 */
	public void setRecordVerNo(int recordVerNo){
		this.recordVerNo = recordVerNo;
	}

	/**
	 * 登録日時取得<br>
	 * <br>
	 * 登録日時を戻り値で返却する<br>
	 * <br>
	 * @return insertDttm
	 */
	public Timestamp getInsertDttm(){
		return insertDttm;
	}

	/**
	 * 登録日時設定<br>
	 * <br>
	 * 登録日時を引数で設定する<br>
	 * <br>
	 * @param insertDttm
	 */
	public void setInsertDttm(Timestamp insertDttm){
		this.insertDttm = insertDttm;
	}

	/**
	 * 登録者コード取得<br>
	 * <br>
	 * 登録者コードを戻り値で返却する<br>
	 * <br>
	 * @return insertCd
	 */
	public String getInsertCd(){
		return insertCd;
	}

	/**
	 * 登録者コード設定<br>
	 * <br>
	 * 登録者コードを引数で設定する<br>
	 * <br>
	 * @param insertCd
	 */
	public void setInsertCd(String insertCd){
		this.insertCd = insertCd;
	}

	/**
	 * 更新日時取得<br>
	 * <br>
	 * 更新日時を戻り値で返却する<br>
	 * <br>
	 * @return updateDttm
	 */
	public Timestamp getUpdateDttm(){
		return updateDttm;
	}

	/**
	 * 更新日時設定<br>
	 * <br>
	 * 更新日時を引数で設定する<br>
	 * <br>
	 * @param updateDttm
	 */
	public void setUpdateDttm(Timestamp updateDttm){
		this.updateDttm = updateDttm;
	}

	/**
	 * 更新者コード取得<br>
	 * <br>
	 * 更新者コードを戻り値で返却する<br>
	 * <br>
	 * @return updateCd
	 */
	public String getUpdateCd(){
		return updateCd;
	}

	/**
	 * 更新者コード設定<br>
	 * <br>
	 * 更新者コードを引数で設定する<br>
	 * <br>
	 * @param updateCd
	 */
	public void setUpdateCd(String updateCd){
		this.updateCd = updateCd;
	}

	/**
	 * リターンコード取得<br>
	 * <br>
	 * リターンコードを戻り値で返却する<br>
	 * <br>
	 * @return returnCode
	 */
	public int getReturnCode(){
		return returnCode;
	}

	/**
	 * リターンコード設定<br>
	 * <br>
	 * リターンコードを引数で設定する<br>
	 * <br>
	 * @param returnCode
	 */
	public void setReturnCode(int returnCode){
		this.returnCode = returnCode;
	}

}
