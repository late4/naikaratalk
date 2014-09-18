package com.naikara_talk.dto;

import java.sql.Timestamp;
import java.math.BigDecimal;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DTOクラス<br>
 * <b>クラス名称　　　:</b>ポイント引当テーブルクラス<br>
 * <b>クラス概要　　　:</b>ポイント引当テーブルDTO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/01 TECS 新規作成
 */
public class PointProvisionTrnDto extends AbstractDto{

	private String rsvNoPurchaseId;      //予約No／購入ID
	private int consSeq;                 //連番
	private String rsvPurchaseKbn;       //予約購入区分
	private BigDecimal usePoint;         //利用ポイント
	private String ownId;                //所有ID
	private String compensationFreeKbn;  //有償無償区分
	private int recordVerNo;             //レコードバージョン番号
	private Timestamp insertDttm;        //登録日時
	private String insertCd;             //登録者コード
	private Timestamp updateDttm;        //更新日時
	private String updateCd;             //更新者コード
	private int returnCode;              //リターンコード

	/**
	 * 予約No／購入ID取得<br>
	 * <br>
	 * 予約No／購入IDを戻り値で返却する<br>
	 * <br>
	 * @return rsvNoPurchaseId
	 */
	public String getRsvNoPurchaseId(){
		return rsvNoPurchaseId;
	}

	/**
	 * 予約No／購入ID設定<br>
	 * <br>
	 * 予約No／購入IDを引数で設定する<br>
	 * <br>
	 * @param rsvNoPurchaseId
	 */
	public void setRsvNoPurchaseId(String rsvNoPurchaseId){
		this.rsvNoPurchaseId = rsvNoPurchaseId;
	}

	/**
	 * 連番取得<br>
	 * <br>
	 * 連番を戻り値で返却する<br>
	 * <br>
	 * @return consSeq
	 */
	public int getConsSeq(){
		return consSeq;
	}

	/**
	 * 連番設定<br>
	 * <br>
	 * 連番を引数で設定する<br>
	 * <br>
	 * @param consSeq
	 */
	public void setConsSeq(int consSeq){
		this.consSeq = consSeq;
	}

	/**
	 * 予約購入区分取得<br>
	 * <br>
	 * 予約購入区分を戻り値で返却する<br>
	 * <br>
	 * @return rsvPurchaseKbn
	 */
	public String getRsvPurchaseKbn(){
		return rsvPurchaseKbn;
	}

	/**
	 * 予約購入区分設定<br>
	 * <br>
	 * 予約購入区分を引数で設定する<br>
	 * <br>
	 * @param rsvPurchaseKbn
	 */
	public void setRsvPurchaseKbn(String rsvPurchaseKbn){
		this.rsvPurchaseKbn = rsvPurchaseKbn;
	}

	/**
	 * 利用ポイント取得<br>
	 * <br>
	 * 利用ポイントを戻り値で返却する<br>
	 * <br>
	 * @return usePoint
	 */
	public BigDecimal getUsePoint(){
		return usePoint;
	}

	/**
	 * 利用ポイント設定<br>
	 * <br>
	 * 利用ポイントを引数で設定する<br>
	 * <br>
	 * @param usePoint
	 */
	public void setUsePoint(BigDecimal usePoint){
		this.usePoint = usePoint;
	}

	/**
	 * 所有ID取得<br>
	 * <br>
	 * 所有IDを戻り値で返却する<br>
	 * <br>
	 * @return ownId
	 */
	public String getOwnId(){
		return ownId;
	}

	/**
	 * 所有ID設定<br>
	 * <br>
	 * 所有IDを引数で設定する<br>
	 * <br>
	 * @param ownId
	 */
	public void setOwnId(String ownId){
		this.ownId = ownId;
	}

	/**
	 * 有償無償区分取得<br>
	 * <br>
	 * 有償無償区分を戻り値で返却する<br>
	 * <br>
	 * @return compensationFreeKbn
	 */
	public String getCompensationFreeKbn(){
		return compensationFreeKbn;
	}

	/**
	 * 有償無償区分設定<br>
	 * <br>
	 * 有償無償区分を引数で設定する<br>
	 * <br>
	 * @param compensationFreeKbn
	 */
	public void setCompensationFreeKbn(String compensationFreeKbn){
		this.compensationFreeKbn = compensationFreeKbn;
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
