package com.naikara_talk.dto;

import java.sql.Timestamp;
import java.math.BigDecimal;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DTOクラス<br>
 * <b>クラス名称　　　:</b>売上集計テーブルクラス<br>
 * <b>クラス概要　　　:</b>売上集計テーブルDTO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/01 TECS 新規作成
 */
public class SalesTrnDto extends AbstractDto{

	private String yyyyMm;                         //年月
	private String studentId;                      //受講者ID
	private String customerKbn;                    //顧客区分
	private String organizationId;                 //組織ID
	private int burdenNum;                         //自己負担率
	private String studentPosition;                //受講者所属部署
	private String positionOrganizationId;         //所属組織内ID
	private String familyKnm;                      //受講者名フリガナ(姓)
	private String firstKnm;                       //受講者名フリガナ(名)
	private String familyRomaji;                   //受講者名ローマ字(姓)
	private String firstRomaji;                    //受講者名ローマ字(名)
	private String nickNm;                         //受講者名ニックネーム
	private String familyFirstJnm;                 //受講者名
	private String mailAddress1;                   //受講者メールアドレス1
	private BigDecimal compensationBeforePoint;    //前受有償ポイント（合計）
	private BigDecimal compensationPurchasePoint;  //購入有償ポイント（合計）
	private BigDecimal compensationUsePoint;       //利用有償ポイント（合計）
	private BigDecimal goodsPurchaseYen;           //ポイント外商品購入（合計）
	private BigDecimal freeBeforePoint;            //前受無償ポイント（合計）
	private BigDecimal freePurchasePoint;          //購入無償ポイント（合計）
	private BigDecimal freeUsePoint;               //利用無償ポイント（合計）
	private String billNo;                         //請求書番号
	private String billStudentId;                  //請求書受講者番号
	private int lessonNum;                         //受講済みレッスン回数
	private String lastUseDt;                      //最終利用日
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
	 * 顧客区分取得<br>
	 * <br>
	 * 顧客区分を戻り値で返却する<br>
	 * <br>
	 * @return customerKbn
	 */
	public String getCustomerKbn(){
		return customerKbn;
	}

	/**
	 * 顧客区分設定<br>
	 * <br>
	 * 顧客区分を引数で設定する<br>
	 * <br>
	 * @param customerKbn
	 */
	public void setCustomerKbn(String customerKbn){
		this.customerKbn = customerKbn;
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
	 * 自己負担率取得<br>
	 * <br>
	 * 自己負担率を戻り値で返却する<br>
	 * <br>
	 * @return burdenNum
	 */
	public int getBurdenNum(){
		return burdenNum;
	}

	/**
	 * 自己負担率設定<br>
	 * <br>
	 * 自己負担率を引数で設定する<br>
	 * <br>
	 * @param burdenNum
	 */
	public void setBurdenNum(int burdenNum){
		this.burdenNum = burdenNum;
	}

	/**
	 * 受講者所属部署取得<br>
	 * <br>
	 * 受講者所属部署を戻り値で返却する<br>
	 * <br>
	 * @return studentPosition
	 */
	public String getStudentPosition(){
		return studentPosition;
	}

	/**
	 * 受講者所属部署設定<br>
	 * <br>
	 * 受講者所属部署を引数で設定する<br>
	 * <br>
	 * @param studentPosition
	 */
	public void setStudentPosition(String studentPosition){
		this.studentPosition = studentPosition;
	}

	/**
	 * 所属組織内ID取得<br>
	 * <br>
	 * 所属組織内IDを戻り値で返却する<br>
	 * <br>
	 * @return positionOrganizationId
	 */
	public String getPositionOrganizationId(){
		return positionOrganizationId;
	}

	/**
	 * 所属組織内ID設定<br>
	 * <br>
	 * 所属組織内IDを引数で設定する<br>
	 * <br>
	 * @param positionOrganizationId
	 */
	public void setPositionOrganizationId(String positionOrganizationId){
		this.positionOrganizationId = positionOrganizationId;
	}

	/**
	 * 受講者名フリガナ(姓)取得<br>
	 * <br>
	 * 受講者名フリガナ(姓)を戻り値で返却する<br>
	 * <br>
	 * @return familyKnm
	 */
	public String getFamilyKnm(){
		return familyKnm;
	}

	/**
	 * 受講者名フリガナ(姓)設定<br>
	 * <br>
	 * 受講者名フリガナ(姓)を引数で設定する<br>
	 * <br>
	 * @param familyKnm
	 */
	public void setFamilyKnm(String familyKnm){
		this.familyKnm = familyKnm;
	}

	/**
	 * 受講者名フリガナ(名)取得<br>
	 * <br>
	 * 受講者名フリガナ(名)を戻り値で返却する<br>
	 * <br>
	 * @return firstKnm
	 */
	public String getFirstKnm(){
		return firstKnm;
	}

	/**
	 * 受講者名フリガナ(名)設定<br>
	 * <br>
	 * 受講者名フリガナ(名)を引数で設定する<br>
	 * <br>
	 * @param firstKnm
	 */
	public void setFirstKnm(String firstKnm){
		this.firstKnm = firstKnm;
	}

	/**
	 * 受講者名ローマ字(姓)取得<br>
	 * <br>
	 * 受講者名ローマ字(姓)を戻り値で返却する<br>
	 * <br>
	 * @return familyRomaji
	 */
	public String getFamilyRomaji(){
		return familyRomaji;
	}

	/**
	 * 受講者名ローマ字(姓)設定<br>
	 * <br>
	 * 受講者名ローマ字(姓)を引数で設定する<br>
	 * <br>
	 * @param familyRomaji
	 */
	public void setFamilyRomaji(String familyRomaji){
		this.familyRomaji = familyRomaji;
	}

	/**
	 * 受講者名ローマ字(名)取得<br>
	 * <br>
	 * 受講者名ローマ字(名)を戻り値で返却する<br>
	 * <br>
	 * @return firstRomaji
	 */
	public String getFirstRomaji(){
		return firstRomaji;
	}

	/**
	 * 受講者名ローマ字(名)設定<br>
	 * <br>
	 * 受講者名ローマ字(名)を引数で設定する<br>
	 * <br>
	 * @param firstRomaji
	 */
	public void setFirstRomaji(String firstRomaji){
		this.firstRomaji = firstRomaji;
	}

	/**
	 * 受講者名ニックネーム取得<br>
	 * <br>
	 * 受講者名ニックネームを戻り値で返却する<br>
	 * <br>
	 * @return nickNm
	 */
	public String getNickNm(){
		return nickNm;
	}

	/**
	 * 受講者名ニックネーム設定<br>
	 * <br>
	 * 受講者名ニックネームを引数で設定する<br>
	 * <br>
	 * @param nickNm
	 */
	public void setNickNm(String nickNm){
		this.nickNm = nickNm;
	}

	/**
	 * 受講者名取得<br>
	 * <br>
	 * 受講者名を戻り値で返却する<br>
	 * <br>
	 * @return familyFirstJnm
	 */
	public String getFamilyFirstJnm(){
		return familyFirstJnm;
	}

	/**
	 * 受講者名設定<br>
	 * <br>
	 * 受講者名を引数で設定する<br>
	 * <br>
	 * @param familyFirstJnm
	 */
	public void setFamilyFirstJnm(String familyFirstJnm){
		this.familyFirstJnm = familyFirstJnm;
	}

	/**
	 * 受講者メールアドレス1取得<br>
	 * <br>
	 * 受講者メールアドレス1を戻り値で返却する<br>
	 * <br>
	 * @return mailAddress1
	 */
	public String getMailAddress1(){
		return mailAddress1;
	}

	/**
	 * 受講者メールアドレス1設定<br>
	 * <br>
	 * 受講者メールアドレス1を引数で設定する<br>
	 * <br>
	 * @param mailAddress1
	 */
	public void setMailAddress1(String mailAddress1){
		this.mailAddress1 = mailAddress1;
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
	 * 請求書受講者番号取得<br>
	 * <br>
	 * 請求書受講者番号を戻り値で返却する<br>
	 * <br>
	 * @return billStudentId
	 */
	public String getBillStudentId(){
		return billStudentId;
	}

	/**
	 * 請求書受講者番号設定<br>
	 * <br>
	 * 請求書受講者番号を引数で設定する<br>
	 * <br>
	 * @param billStudentId
	 */
	public void setBillStudentId(String billStudentId){
		this.billStudentId = billStudentId;
	}

	/**
	 * 受講済みレッスン回数取得<br>
	 * <br>
	 * 受講済みレッスン回数を戻り値で返却する<br>
	 * <br>
	 * @return lessonNum
	 */
	public int getLessonNum(){
		return lessonNum;
	}

	/**
	 * 受講済みレッスン回数設定<br>
	 * <br>
	 * 受講済みレッスン回数を引数で設定する<br>
	 * <br>
	 * @param lessonNum
	 */
	public void setLessonNum(int lessonNum){
		this.lessonNum = lessonNum;
	}

	/**
	 * 最終利用日取得<br>
	 * <br>
	 * 最終利用日を戻り値で返却する<br>
	 * <br>
	 * @return lastUseDt
	 */
	public String getLastUseDt(){
		return lastUseDt;
	}

	/**
	 * 最終利用日設定<br>
	 * <br>
	 * 最終利用日を引数で設定する<br>
	 * <br>
	 * @param lastUseDt
	 */
	public void setLastUseDt(String lastUseDt){
		this.lastUseDt = lastUseDt;
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
