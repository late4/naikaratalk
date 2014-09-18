package com.naikara_talk.dto;

import java.sql.Timestamp;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DTOクラス<br>
 * <b>クラス名称　　　:</b>スクールメール・アカウント変更履歴テーブルクラス<br>
 * <b>クラス概要　　　:</b>スクールメール・アカウント変更履歴テーブルDTO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/12/07 TECS 新規作成
 */
public class SmailAccountHistoryTrnDto extends AbstractDto{

	private Timestamp sendDttm;    //送信日時
	private int sendDttmSeq;       //連番
	private String mailPatternCd;  //メールパターンコード
	private String studentId;      //受講者ID
	private int recordVerNo;       //レコードバージョン番号
	private Timestamp insertDttm;  //登録日時
	private String insertCd;       //登録者コード
	private Timestamp updateDttm;  //更新日時
	private String updateCd;       //更新者コード
	private int returnCode;        //リターンコード

	/**
	 * 送信日時取得<br>
	 * <br>
	 * 送信日時を戻り値で返却する<br>
	 * <br>
	 * @return sendDttm
	 */
	public Timestamp getSendDttm(){
		return sendDttm;
	}

	/**
	 * 送信日時設定<br>
	 * <br>
	 * 送信日時を引数で設定する<br>
	 * <br>
	 * @param sendDttm
	 */
	public void setSendDttm(Timestamp sendDttm){
		this.sendDttm = sendDttm;
	}

	/**
	 * 連番取得<br>
	 * <br>
	 * 連番を戻り値で返却する<br>
	 * <br>
	 * @return sendDttmSeq
	 */
	public int getSendDttmSeq(){
		return sendDttmSeq;
	}

	/**
	 * 連番設定<br>
	 * <br>
	 * 連番を引数で設定する<br>
	 * <br>
	 * @param sendDttmSeq
	 */
	public void setSendDttmSeq(int sendDttmSeq){
		this.sendDttmSeq = sendDttmSeq;
	}

	/**
	 * メールパターンコード取得<br>
	 * <br>
	 * メールパターンコードを戻り値で返却する<br>
	 * <br>
	 * @return mailPatternCd
	 */
	public String getMailPatternCd(){
		return mailPatternCd;
	}

	/**
	 * メールパターンコード設定<br>
	 * <br>
	 * メールパターンコードを引数で設定する<br>
	 * <br>
	 * @param mailPatternCd
	 */
	public void setMailPatternCd(String mailPatternCd){
		this.mailPatternCd = mailPatternCd;
	}

	/**
	 * 受講者ID取得<br>
	 * <br>
	 * 受講者IDを戻り値で返却する<br>
	 * <br>
	 * @return studentId
	 */
	public String getStudentId(){
		return studentId;
	}

	/**
	 * 受講者ID設定<br>
	 * <br>
	 * 受講者IDを引数で設定する<br>
	 * <br>
	 * @param studentId
	 */
	public void setStudentId(String studentId){
		this.studentId = studentId;
	}

    /**
     * レコードバージョン番号取得<br>
     * <br>
     * レコードバージョン番号を戻り値で返却する<br>
     * <br>
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
