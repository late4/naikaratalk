package com.naikara_talk.dto;

import java.math.BigDecimal;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DTOクラス<br>
 * <b>クラス名称　　　:</b>ポイント所有テーブルクラス<br>
 * <b>クラス概要　　　:</b>ポイント所有テーブルDTO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/05 TECS 新規作成
 */
public class PointOwnershipTrnListDto extends AbstractDto{

	private String ownershipID;          //所有ID
	private String compensationFreeKbn;  //有償無償区分
	private String effectiveStrDt;       //有効開始日
	private String effectiveEndDt;       //有効終了日
	private BigDecimal balancePoint;     //ポイント残高
    private int returnCode;              // リターンコード

	/**
	 * 所有ID取得<br>
	 * <br>
	 * 所有IDを戻り値で返却する<br>
	 * <br>
	 * @return ownershipID
	 */
	public String getOwnershipID(){
		return ownershipID;
	}

	/**
	 * 所有ID設定<br>
	 * <br>
	 * 所有IDを引数で設定する<br>
	 * <br>
	 * @param ownershipID
	 */
	public void setOwnershipID(String ownershipID){
		this.ownershipID = ownershipID;
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
	 * 有効開始日取得<br>
	 * <br>
	 * 有効開始日を戻り値で返却する<br>
	 * <br>
	 * @return effectiveStrDt
	 */
	public String getEffectiveStrDt(){
		return effectiveStrDt;
	}

	/**
	 * 有効開始日設定<br>
	 * <br>
	 * 有効開始日を引数で設定する<br>
	 * <br>
	 * @param effectiveStrDt
	 */
	public void setEffectiveStrDt(String effectiveStrDt){
		this.effectiveStrDt = effectiveStrDt;
	}

	/**
	 * 有効終了日取得<br>
	 * <br>
	 * 有効終了日を戻り値で返却する<br>
	 * <br>
	 * @return effectiveEndDt
	 */
	public String getEffectiveEndDt(){
		return effectiveEndDt;
	}

	/**
	 * 有効終了日設定<br>
	 * <br>
	 * 有効終了日を引数で設定する<br>
	 * <br>
	 * @param effectiveEndDt
	 */
	public void setEffectiveEndDt(String effectiveEndDt){
		this.effectiveEndDt = effectiveEndDt;
	}

	/**
	 * ポイント残高取得<br>
	 * <br>
	 * ポイント残高を戻り値で返却する<br>
	 * <br>
	 * @return balancePoint
	 */
	public BigDecimal getBalancePoint(){
		return balancePoint;
	}

	/**
	 * ポイント残高設定<br>
	 * <br>
	 * ポイント残高を引数で設定する<br>
	 * <br>
	 * @param balancePoint
	 */
	public void setBalancePoint(BigDecimal balancePoint){
		this.balancePoint = balancePoint;
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
