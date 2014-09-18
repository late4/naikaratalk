package com.naikara_talk.dto;

import java.sql.Timestamp;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DTOクラス<br>
 * <b>クラス名称　　　:</b>採番マスタクラス<br>
 * <b>クラス概要　　　:</b>採番マスタDTO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/05/30 TECS 新規作成
 */
public class OrderNumbersMstDto extends AbstractDto{

	private String orderNumbersId;   //採番ID
	private String yymm;             //年月
	private int seq;                 //シーケンス
	private int effectiveDigitNum;   //有効桁数
	private int recordVerNo;         //レコードバージョン番号
	private Timestamp insertDttm;    //登録日時
	private String insertCd;         //登録者コード
	private Timestamp updateDttm;    //更新日時
	private String updateCd;         //更新者コード
	private int recordVersionNum;    //レコードバージョン番号
    private int returnCode;             // リターンコード

	/**
	 * 採番ID取得<br>
	 * <br>
	 * 採番IDを戻り値で返却する<br>
	 * <br>
	 * @return orderNumbersId
	 */
	public String getOrderNumbersId(){
		return orderNumbersId;
	}

	/**
	 * 採番ID設定<br>
	 * <br>
	 * 採番IDを引数で設定する<br>
	 * <br>
	 * @param orderNumbersId
	 */
	public void setOrderNumbersId(String orderNumbersId){
		this.orderNumbersId = orderNumbersId;
	}

	/**
	 * 年月取得<br>
	 * <br>
	 * 年月を戻り値で返却する<br>
	 * <br>
	 * @return yymm
	 */
	public String getYymm(){
		return yymm;
	}

	/**
	 * 年月設定<br>
	 * <br>
	 * 年月を引数で設定する<br>
	 * <br>
	 * @param yymm
	 */
	public void setYymm(String yymm){
		this.yymm = yymm;
	}

	/**
	 * シーケンス取得<br>
	 * <br>
	 * シーケンスを戻り値で返却する<br>
	 * <br>
	 * @return seq
	 */
	public int getSeq(){
		return seq;
	}

	/**
	 * シーケンス設定<br>
	 * <br>
	 * シーケンスを引数で設定する<br>
	 * <br>
	 * @param seq
	 */
	public void setSeq(int seq){
		this.seq = seq;
	}

	/**
	 * 有効桁数取得<br>
	 * <br>
	 * 有効桁数を戻り値で返却する<br>
	 * <br>
	 * @return effectiveDigitNum
	 */
	public int getEffectiveDigitNum(){
		return effectiveDigitNum;
	}

	/**
	 * 有効桁数設定<br>
	 * <br>
	 * 有効桁数を引数で設定する<br>
	 * <br>
	 * @param effectiveDigitNum
	 */
	public void setEffectiveDigitNum(int effectiveDigitNum){
		this.effectiveDigitNum = effectiveDigitNum;
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
	 * レコードバージョン番号取得<br>
	 * <br>
	 * レコードバージョン番号を戻り値で返却する<br>
	 * <br>
	 * @return recordVersionNum
	 */
	public int getRecordVersionNum(){
		return recordVersionNum;
	}

	/**
	 * レコードバージョン番号設定<br>
	 * <br>
	 * レコードバージョン番号を引数で設定する<br>
	 * <br>
	 * @param recordVersionNum
	 */
	public void setRecordVersionNum(int recordVersionNum){
		this.recordVersionNum = recordVersionNum;
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
