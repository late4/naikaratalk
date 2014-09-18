package com.naikara_talk.dto;

import java.sql.Timestamp;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DTOクラス<br>
 * <b>クラス名称　　　:</b>祝日マスタクラス<br>
 * <b>クラス概要　　　:</b>祝日マスタDTO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/05/27 TECS 新規作成
 */
public class HolidayMstDto extends AbstractDto{

	private String holidayDt;      //祝日
	private String holiday;        //祝日内容
	private Timestamp insertDttm;  //登録日時
	private String insertCd;       //登録者コード
	private Timestamp updateDttm;  //更新日時
	private String updateCd;       //更新者コード
    private int returnCode;        // リターンコード

	/**
	 * 祝日取得<br>
	 * <br>
	 * 祝日を戻り値で返却する<br>
	 * <br>
	 * @return holidayDt
	 */
	public String getHolidayDt(){
		return holidayDt;
	}

	/**
	 * 祝日設定<br>
	 * <br>
	 * 祝日を引数で設定する<br>
	 * <br>
	 * @param holidayDt
	 */
	public void setHolidayDt(String holidayDt){
		this.holidayDt = holidayDt;
	}

	/**
	 * 祝日内容取得<br>
	 * <br>
	 * 祝日内容を戻り値で返却する<br>
	 * <br>
	 * @return holiday
	 */
	public String getHoliday(){
		return holiday;
	}

	/**
	 * 祝日内容設定<br>
	 * <br>
	 * 祝日内容を引数で設定する<br>
	 * <br>
	 * @param holiday
	 */
	public void setHoliday(String holiday){
		this.holiday = holiday;
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
