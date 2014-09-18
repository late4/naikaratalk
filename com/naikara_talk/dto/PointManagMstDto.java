package com.naikara_talk.dto;

import java.sql.Timestamp;
import java.math.BigDecimal;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DTOクラス<br>
 * <b>クラス名称　　　:</b>ポイント管理マスタクラス<br>
 * <b>クラス概要　　　:</b>ポイント管理マスタDTO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/01 TECS 新規作成
 */
public class PointManagMstDto extends AbstractDto{

	private String pointCd;           // ポイントコード
	private String feeKbn;            // 通常月謝区分
    private String feeKbnNm;          // 通常月謝区分名
	private BigDecimal moneyYen;      // 金額(税込)
	private BigDecimal paymentPoint;  // 有償ポイント
	private int paymentPointTim;      // 有償ポイント期限
	private BigDecimal freePoint;     // 無償ポイント
	private int freePointTim;         // 無償ポイント期限
	private String useStrDt;          // 提供開始日
	private String useEndDt;          // 提供終了日
	private String remarks;           // 備考
	private int recordVerNo;          // レコードバージョン番号
	private Timestamp insertDttm;     // 登録日時
	private String insertCd;          // 登録者コード
	private Timestamp updateDttm;     // 更新日時
	private String updateCd;          // 更新者コード
	private int returnCode;           // リターンコード

	/**
	 * ポイントコード取得<br>
	 * <br>
	 * ポイントコードを戻り値で返却する<br>
	 * <br>
	 * @return pointCd
	 */
	public String getPointCd(){
		return pointCd;
	}

	/**
	 * ポイントコード設定<br>
	 * <br>
	 * ポイントコードを引数で設定する<br>
	 * <br>
	 * @param pointCd
	 */
	public void setPointCd(String pointCd){
		this.pointCd = pointCd;
	}

	/**
	 * 通常月謝区分取得<br>
	 * <br>
	 * 通常月謝区分を戻り値で返却する<br>
	 * <br>
	 * @return feeKbn
	 */
	public String getFeeKbn(){
		return feeKbn;
	}

	/**
	 * 通常月謝区分設定<br>
	 * <br>
	 * 通常月謝区分を引数で設定する<br>
	 * <br>
	 * @param feeKbn
	 */
	public void setFeeKbn(String feeKbn){
		this.feeKbn = feeKbn;
	}

    /**
     * 通常月謝区分名取得<br>
     * <br>
     * 通常月謝区分名を戻り値で返却する<br>
     * <br>
     * @return feeKbnNm
     */
	public String getFeeKbnNm() {
        return feeKbnNm;
    }

    /**
     * 通常月謝区分名設定<br>
     * <br>
     * 通常月謝区分名を引数で設定する<br>
     * <br>
     * @param feeKbnNm
     */
    public void setFeeKbnNm(String feeKbnNm) {
        this.feeKbnNm = feeKbnNm;
    }

    /**
	 * 金額(税込)取得<br>
	 * <br>
	 * 金額(税込)を戻り値で返却する<br>
	 * <br>
	 * @return moneyYen
	 */
	public BigDecimal getMoneyYen(){
		return moneyYen;
	}

	/**
	 * 金額(税込)設定<br>
	 * <br>
	 * 金額(税込)を引数で設定する<br>
	 * <br>
	 * @param moneyYen
	 */
	public void setMoneyYen(BigDecimal moneyYen){
		this.moneyYen = moneyYen;
	}

	/**
	 * 有償ポイント取得<br>
	 * <br>
	 * 有償ポイントを戻り値で返却する<br>
	 * <br>
	 * @return paymentPoint
	 */
	public BigDecimal getPaymentPoint(){
		return paymentPoint;
	}

	/**
	 * 有償ポイント設定<br>
	 * <br>
	 * 有償ポイントを引数で設定する<br>
	 * <br>
	 * @param paymentPoint
	 */
	public void setPaymentPoint(BigDecimal paymentPoint){
		this.paymentPoint = paymentPoint;
	}

	/**
	 * 有償ポイント期限取得<br>
	 * <br>
	 * 有償ポイント期限を戻り値で返却する<br>
	 * <br>
	 * @return paymentPointTim
	 */
	public int getPaymentPointTim(){
		return paymentPointTim;
	}

	/**
	 * 有償ポイント期限設定<br>
	 * <br>
	 * 有償ポイント期限を引数で設定する<br>
	 * <br>
	 * @param paymentPointTim
	 */
	public void setPaymentPointTim(int paymentPointTim){
		this.paymentPointTim = paymentPointTim;
	}

	/**
	 * 無償ポイント取得<br>
	 * <br>
	 * 無償ポイントを戻り値で返却する<br>
	 * <br>
	 * @return freePoint
	 */
	public BigDecimal getFreePoint(){
		return freePoint;
	}

	/**
	 * 無償ポイント設定<br>
	 * <br>
	 * 無償ポイントを引数で設定する<br>
	 * <br>
	 * @param freePoint
	 */
	public void setFreePoint(BigDecimal freePoint){
		this.freePoint = freePoint;
	}

	/**
	 * 無償ポイント期限取得<br>
	 * <br>
	 * 無償ポイント期限を戻り値で返却する<br>
	 * <br>
	 * @return freePointTim
	 */
	public int getFreePointTim(){
		return freePointTim;
	}

	/**
	 * 無償ポイント期限設定<br>
	 * <br>
	 * 無償ポイント期限を引数で設定する<br>
	 * <br>
	 * @param freePointTim
	 */
	public void setFreePointTim(int freePointTim){
		this.freePointTim = freePointTim;
	}

	/**
	 * 提供開始日取得<br>
	 * <br>
	 * 提供開始日を戻り値で返却する<br>
	 * <br>
	 * @return useStrDt
	 */
	public String getUseStrDt(){
		return useStrDt;
	}

	/**
	 * 提供開始日設定<br>
	 * <br>
	 * 提供開始日を引数で設定する<br>
	 * <br>
	 * @param useStrDt
	 */
	public void setUseStrDt(String useStrDt){
		this.useStrDt = useStrDt;
	}

	/**
	 * 提供終了日取得<br>
	 * <br>
	 * 提供終了日を戻り値で返却する<br>
	 * <br>
	 * @return useEndDt
	 */
	public String getUseEndDt(){
		return useEndDt;
	}

	/**
	 * 提供終了日設定<br>
	 * <br>
	 * 提供終了日を引数で設定する<br>
	 * <br>
	 * @param useEndDt
	 */
	public void setUseEndDt(String useEndDt){
		this.useEndDt = useEndDt;
	}

	/**
	 * 備考取得<br>
	 * <br>
	 * 備考を戻り値で返却する<br>
	 * <br>
	 * @return remarks
	 */
	public String getRemarks(){
		return remarks;
	}

	/**
	 * 備考設定<br>
	 * <br>
	 * 備考を引数で設定する<br>
	 * <br>
	 * @param remarks
	 */
	public void setRemarks(String remarks){
		this.remarks = remarks;
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
