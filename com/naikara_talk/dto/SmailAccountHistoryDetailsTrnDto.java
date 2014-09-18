package com.naikara_talk.dto;

import java.sql.Timestamp;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DTOクラス<br>
 * <b>クラス名称　　　:</b>スクールメール・アカウント変更履歴明細テーブルクラス<br>
 * <b>クラス概要　　　:</b>スクールメール・アカウント変更履歴明細テーブルDTO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/12/07 TECS 新規作成
 */
public class SmailAccountHistoryDetailsTrnDto extends AbstractDto{

	private Timestamp sendDttm;                      //送信日時
	private int sendDttmSeq;                         //連番
	private String studentId;                        //受講者ID
	private String bFamilyJnm;                       //変更前：名前(姓)
	private String bFirstJnm;                        //変更前：名前(名)
	private String bFamilyKnm;                       //変更前：フリガナ(姓)
	private String bFirstKnm;                        //変更前：フリガナ(名)
	private String bFamilyRomaji;                    //変更前：ローマ字(姓)
	private String bFirstRomaji;                     //変更前：ローマ字(名)
	private String bNickNm;                          //変更前：ニックネーム
	private String bPassword;                        //変更前：パスワード
	private String bTel1;                            //変更前：電話番号1
	private String bTel2;                            //変更前：電話番号2
	private String bBirthDt;                         //変更前：生年月日
	private String bZipCd;                           //変更前：郵便番号
	private String bAddressAreaCd;                   //変更前：住所(地域)コード
	private String bAddressAreaJnm;                  //変更前：住所(地域)名
	private String bAddressPrefectureCd;             //変更前：住所(都道府県)コード
	private String bAddressPrefectureJnm;            //変更前：住所(都道府県)名
	private String bAddressCity;                     //変更前：住所(市区町村 等)
	private String bAddressOthers;                   //変更前：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)
	private String bGenderKbn;                       //変更前：性別区分
	private String bGenderJnm;                       //変更前：性別名
	private String bMailAddress1;                    //変更前：メールアドレス1
	private String bMailAddress2;                    //変更前：メールアドレス2
	private String bMailAddress3;                    //変更前：メールアドレス3
	private String bGuardianFamilyJnm;               //変更前：保護者：名前(姓)
	private String bGuardianFirstJnm;                //変更前：保護者：名前(名）
	private String bGuardianFamilyKnm;               //変更前：保護者：フリガナ(姓)
	private String bGuardianFirstKnm;                //変更前：保護者：フリガナ(名）
	private String bGuardianFamilyRelationship;      //変更前：あなたとの続柄
	private String bGuardianTel1;                    //変更前：保護者：電話番号1
	private String bGuardianTel2;                    //変更前：保護者：電話番号2
	private String bGuardianBirthDt;                 //変更前：保護者：生年月日
	private String bGuardianZipCd;                   //変更前：保護者：郵便番号
	private String bGuardianAddressAreaCd;           //変更前：保護者：住所(地域)コード
	private String bGuardianAddressAreaJnm;          //変更前：保護者：住所(地域)名
	private String bGuardianAddressPrefectureCd;     //変更前：保護者：住所(都道府県)コード
	private String bGuardianAddressPrefectureJnm;    //変更前：保護者：住所(都道府県)名
	private String bGuardianAddressCity;             //変更前：保護者：住所(市区町村 等)
	private String bGuardianAddressOthers;           //変更前：保護者：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)
	private String bGuardianGenderKbn;               //変更前：保護者：性別区分
	private String bGuardianGenderJnm;               //変更前：保護者：性別名
	private String bGuardianMailAddress1;            //変更前：保護者：メールアドレス1
	private String bGuardianMailAddress2;            //変更前：保護者：メールアドレス2
	private String bGuardianMailAddress3;            //変更前：保護者：メールアドレス3
	private String aFamilyJnm;                       //変更後：名前(姓)
	private String aFirstJnm;                        //変更後：名前(名)
	private String aFamilyKnm;                       //変更後：フリガナ(姓)
	private String aFirstKnm;                        //変更後：フリガナ(名)
	private String aFamilyRomaji;                    //変更後：ローマ字(姓)
	private String aFirstRomaji;                     //変更後：ローマ字(名)
	private String aNickNm;                          //変更後：ニックネーム
	private String aPassword;                        //変更後：パスワード
	private String aTel1;                            //変更後：電話番号1
	private String aTel2;                            //変更後：電話番号2
	private String aBirthDt;                         //変更後：生年月日
	private String aZipCd;                           //変更後：郵便番号
	private String aAddressAreaCd;                   //変更後：住所(地域)コード
	private String aAddressAreaJnm;                  //変更後：住所(地域)名
	private String aAddressPrefectureCd;             //変更後：住所(都道府県)コード
	private String aAddressPrefectureJnm;            //変更後：住所(都道府県)名
	private String aAddressCity;                     //変更後：住所(市区町村 等)
	private String aAddressOthers;                   //変更後：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)
	private String aGenderKbn;                       //変更後：性別区分
	private String aGenderJnm;                       //変更後：性別名
	private String aMailAddress1;                    //変更後：メールアドレス1
	private String aMailAddress2;                    //変更後：メールアドレス2
	private String aMailAddress3;                    //変更後：メールアドレス3
	private String aGuardianFamilyJnm;               //変更後：保護者：名前(姓)
	private String aGuardianFirstJnm;                //変更後：保護者：名前(名）
	private String aGuardianFamilyKnm;               //変更後：保護者：フリガナ(姓)
	private String aGuardianFirstKnm;                //変更後：保護者：フリガナ(名）
	private String aGuardianFamilyRelationship;      //変更後：あなたとの続柄
	private String aGuardianTel1;                    //変更後：保護者：電話番号1
	private String aGuardianTel2;                    //変更後：保護者：電話番号2
	private String aGuardianBirthDt;                 //変更後：保護者：生年月日
	private String aGuardianZipCd;                   //変更後：保護者：郵便番号
	private String aGuardianAddressAreaCd;           //変更後：保護者：住所(地域)コード
	private String aGuardianAddressAreaJnm;          //変更後：保護者：住所(地域)名
	private String aGuardianAddressPrefectureCd;     //変更後：保護者：住所(都道府県)コード
	private String aGuardianAddressPrefectureJnm;    //変更後：保護者：住所(都道府県)名
	private String aGuardianAddressCity;             //変更後：保護者：住所(市区町村 等)
	private String aGuardianAddressOthers;           //変更後：保護者：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)
	private String aGuardianGenderKbn;               //変更後：保護者：性別区分
	private String aGuardianGenderJnm;               //変更後：保護者：性別名
	private String aGuardianMailAddress1;            //変更後：保護者：メールアドレス1
	private String aGuardianMailAddress2;            //変更後：保護者：メールアドレス2
	private String aGuardianMailAddress3;            //変更後：保護者：メールアドレス3
	private int recordVerNo;                         //レコードバージョン番号
	private Timestamp insertDttm;                    //登録日時
	private String insertCd;                         //登録者コード
	private Timestamp updateDttm;                    //更新日時
	private String updateCd;                         //更新者コード
	private int returnCode;                          //リターンコード

	private boolean changeYesFlg = false;            //変更有フラグ


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
	 * 変更前：名前(姓)取得<br>
	 * <br>
	 * 変更前：名前(姓)を戻り値で返却する<br>
	 * <br>
	 * @return bFamilyJnm
	 */
	public String getBFamilyJnm(){
		return bFamilyJnm;
	}

	/**
	 * 変更前：名前(姓)設定<br>
	 * <br>
	 * 変更前：名前(姓)を引数で設定する<br>
	 * <br>
	 * @param bFamilyJnm
	 */
	public void setBFamilyJnm(String bFamilyJnm){
		this.bFamilyJnm = bFamilyJnm;
	}

	/**
	 * 変更前：名前(名)取得<br>
	 * <br>
	 * 変更前：名前(名)を戻り値で返却する<br>
	 * <br>
	 * @return bFirstJnm
	 */
	public String getBFirstJnm(){
		return bFirstJnm;
	}

	/**
	 * 変更前：名前(名)設定<br>
	 * <br>
	 * 変更前：名前(名)を引数で設定する<br>
	 * <br>
	 * @param bFirstJnm
	 */
	public void setBFirstJnm(String bFirstJnm){
		this.bFirstJnm = bFirstJnm;
	}

	/**
	 * 変更前：フリガナ(姓)取得<br>
	 * <br>
	 * 変更前：フリガナ(姓)を戻り値で返却する<br>
	 * <br>
	 * @return bFamilyKnm
	 */
	public String getBFamilyKnm(){
		return bFamilyKnm;
	}

	/**
	 * 変更前：フリガナ(姓)設定<br>
	 * <br>
	 * 変更前：フリガナ(姓)を引数で設定する<br>
	 * <br>
	 * @param bFamilyKnm
	 */
	public void setBFamilyKnm(String bFamilyKnm){
		this.bFamilyKnm = bFamilyKnm;
	}

	/**
	 * 変更前：フリガナ(名)取得<br>
	 * <br>
	 * 変更前：フリガナ(名)を戻り値で返却する<br>
	 * <br>
	 * @return bFirstKnm
	 */
	public String getBFirstKnm(){
		return bFirstKnm;
	}

	/**
	 * 変更前：フリガナ(名)設定<br>
	 * <br>
	 * 変更前：フリガナ(名)を引数で設定する<br>
	 * <br>
	 * @param bFirstKnm
	 */
	public void setBFirstKnm(String bFirstKnm){
		this.bFirstKnm = bFirstKnm;
	}

	/**
	 * 変更前：ローマ字(姓)取得<br>
	 * <br>
	 * 変更前：ローマ字(姓)を戻り値で返却する<br>
	 * <br>
	 * @return bFamilyRomaji
	 */
	public String getBFamilyRomaji(){
		return bFamilyRomaji;
	}

	/**
	 * 変更前：ローマ字(姓)設定<br>
	 * <br>
	 * 変更前：ローマ字(姓)を引数で設定する<br>
	 * <br>
	 * @param bFamilyRomaji
	 */
	public void setBFamilyRomaji(String bFamilyRomaji){
		this.bFamilyRomaji = bFamilyRomaji;
	}

	/**
	 * 変更前：ローマ字(名)取得<br>
	 * <br>
	 * 変更前：ローマ字(名)を戻り値で返却する<br>
	 * <br>
	 * @return bFirstRomaji
	 */
	public String getBFirstRomaji(){
		return bFirstRomaji;
	}

	/**
	 * 変更前：ローマ字(名)設定<br>
	 * <br>
	 * 変更前：ローマ字(名)を引数で設定する<br>
	 * <br>
	 * @param bFirstRomaji
	 */
	public void setBFirstRomaji(String bFirstRomaji){
		this.bFirstRomaji = bFirstRomaji;
	}

	/**
	 * 変更前：ニックネーム取得<br>
	 * <br>
	 * 変更前：ニックネームを戻り値で返却する<br>
	 * <br>
	 * @return bNickNm
	 */
	public String getBNickNm(){
		return bNickNm;
	}

	/**
	 * 変更前：ニックネーム設定<br>
	 * <br>
	 * 変更前：ニックネームを引数で設定する<br>
	 * <br>
	 * @param bNickNm
	 */
	public void setBNickNm(String bNickNm){
		this.bNickNm = bNickNm;
	}

	/**
	 * 変更前：パスワード取得<br>
	 * <br>
	 * 変更前：パスワードを戻り値で返却する<br>
	 * <br>
	 * @return bPassword
	 */
	public String getBPassword(){
		return bPassword;
	}

	/**
	 * 変更前：パスワード設定<br>
	 * <br>
	 * 変更前：パスワードを引数で設定する<br>
	 * <br>
	 * @param bPassword
	 */
	public void setBPassword(String bPassword){
		this.bPassword = bPassword;
	}

	/**
	 * 変更前：電話番号1取得<br>
	 * <br>
	 * 変更前：電話番号1を戻り値で返却する<br>
	 * <br>
	 * @return bTel1
	 */
	public String getBTel1(){
		return bTel1;
	}

	/**
	 * 変更前：電話番号1設定<br>
	 * <br>
	 * 変更前：電話番号1を引数で設定する<br>
	 * <br>
	 * @param bTel1
	 */
	public void setBTel1(String bTel1){
		this.bTel1 = bTel1;
	}

	/**
	 * 変更前：電話番号2取得<br>
	 * <br>
	 * 変更前：電話番号2を戻り値で返却する<br>
	 * <br>
	 * @return bTel2
	 */
	public String getBTel2(){
		return bTel2;
	}

	/**
	 * 変更前：電話番号2設定<br>
	 * <br>
	 * 変更前：電話番号2を引数で設定する<br>
	 * <br>
	 * @param bTel2
	 */
	public void setBTel2(String bTel2){
		this.bTel2 = bTel2;
	}

	/**
	 * 変更前：生年月日取得<br>
	 * <br>
	 * 変更前：生年月日を戻り値で返却する<br>
	 * <br>
	 * @return bBirthDt
	 */
	public String getBBirthDt(){
		return bBirthDt;
	}

	/**
	 * 変更前：生年月日設定<br>
	 * <br>
	 * 変更前：生年月日を引数で設定する<br>
	 * <br>
	 * @param bBirthDt
	 */
	public void setBBirthDt(String bBirthDt){
		this.bBirthDt = bBirthDt;
	}

	/**
	 * 変更前：郵便番号取得<br>
	 * <br>
	 * 変更前：郵便番号を戻り値で返却する<br>
	 * <br>
	 * @return bZipCd
	 */
	public String getBZipCd(){
		return bZipCd;
	}

	/**
	 * 変更前：郵便番号設定<br>
	 * <br>
	 * 変更前：郵便番号を引数で設定する<br>
	 * <br>
	 * @param bZipCd
	 */
	public void setBZipCd(String bZipCd){
		this.bZipCd = bZipCd;
	}

	/**
	 * 変更前：住所(地域)コード取得<br>
	 * <br>
	 * 変更前：住所(地域)コードを戻り値で返却する<br>
	 * <br>
	 * @return bAddressAreaCd
	 */
	public String getBAddressAreaCd(){
		return bAddressAreaCd;
	}

	/**
	 * 変更前：住所(地域)コード設定<br>
	 * <br>
	 * 変更前：住所(地域)コードを引数で設定する<br>
	 * <br>
	 * @param bAddressAreaCd
	 */
	public void setBAddressAreaCd(String bAddressAreaCd){
		this.bAddressAreaCd = bAddressAreaCd;
	}

	/**
	 * 変更前：住所(地域)名取得<br>
	 * <br>
	 * 変更前：住所(地域)名を戻り値で返却する<br>
	 * <br>
	 * @return bAddressAreaJnm
	 */
	public String getBAddressAreaJnm(){
		return bAddressAreaJnm;
	}

	/**
	 * 変更前：住所(地域)名設定<br>
	 * <br>
	 * 変更前：住所(地域)名を引数で設定する<br>
	 * <br>
	 * @param bAddressAreaJnm
	 */
	public void setBAddressAreaJnm(String bAddressAreaJnm){
		this.bAddressAreaJnm = bAddressAreaJnm;
	}

	/**
	 * 変更前：住所(都道府県)コード取得<br>
	 * <br>
	 * 変更前：住所(都道府県)コードを戻り値で返却する<br>
	 * <br>
	 * @return bAddressPrefectureCd
	 */
	public String getBAddressPrefectureCd(){
		return bAddressPrefectureCd;
	}

	/**
	 * 変更前：住所(都道府県)コード設定<br>
	 * <br>
	 * 変更前：住所(都道府県)コードを引数で設定する<br>
	 * <br>
	 * @param bAddressPrefectureCd
	 */
	public void setBAddressPrefectureCd(String bAddressPrefectureCd){
		this.bAddressPrefectureCd = bAddressPrefectureCd;
	}

	/**
	 * 変更前：住所(都道府県)名取得<br>
	 * <br>
	 * 変更前：住所(都道府県)名を戻り値で返却する<br>
	 * <br>
	 * @return bAddressPrefectureJnm
	 */
	public String getBAddressPrefectureJnm(){
		return bAddressPrefectureJnm;
	}

	/**
	 * 変更前：住所(都道府県)名設定<br>
	 * <br>
	 * 変更前：住所(都道府県)名を引数で設定する<br>
	 * <br>
	 * @param bAddressPrefectureJnm
	 */
	public void setBAddressPrefectureJnm(String bAddressPrefectureJnm){
		this.bAddressPrefectureJnm = bAddressPrefectureJnm;
	}

	/**
	 * 変更前：住所(市区町村 等)取得<br>
	 * <br>
	 * 変更前：住所(市区町村 等)を戻り値で返却する<br>
	 * <br>
	 * @return bAddressCity
	 */
	public String getBAddressCity(){
		return bAddressCity;
	}

	/**
	 * 変更前：住所(市区町村 等)設定<br>
	 * <br>
	 * 変更前：住所(市区町村 等)を引数で設定する<br>
	 * <br>
	 * @param bAddressCity
	 */
	public void setBAddressCity(String bAddressCity){
		this.bAddressCity = bAddressCity;
	}

	/**
	 * 変更前：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)取得<br>
	 * <br>
	 * 変更前：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)を戻り値で返却する<br>
	 * <br>
	 * @return bAddressOthers
	 */
	public String getBAddressOthers(){
		return bAddressOthers;
	}

	/**
	 * 変更前：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)設定<br>
	 * <br>
	 * 変更前：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)を引数で設定する<br>
	 * <br>
	 * @param bAddressOthers
	 */
	public void setBAddressOthers(String bAddressOthers){
		this.bAddressOthers = bAddressOthers;
	}

	/**
	 * 変更前：性別区分取得<br>
	 * <br>
	 * 変更前：性別区分を戻り値で返却する<br>
	 * <br>
	 * @return bGenderKbn
	 */
	public String getBGenderKbn(){
		return bGenderKbn;
	}

	/**
	 * 変更前：性別区分設定<br>
	 * <br>
	 * 変更前：性別区分を引数で設定する<br>
	 * <br>
	 * @param bGenderKbn
	 */
	public void setBGenderKbn(String bGenderKbn){
		this.bGenderKbn = bGenderKbn;
	}

	/**
	 * 変更前：性別名取得<br>
	 * <br>
	 * 変更前：性別名を戻り値で返却する<br>
	 * <br>
	 * @return bGenderJnm
	 */
	public String getBGenderJnm(){
		return bGenderJnm;
	}

	/**
	 * 変更前：性別名設定<br>
	 * <br>
	 * 変更前：性別名を引数で設定する<br>
	 * <br>
	 * @param bGenderJnm
	 */
	public void setBGenderJnm(String bGenderJnm){
		this.bGenderJnm = bGenderJnm;
	}

	/**
	 * 変更前：メールアドレス1取得<br>
	 * <br>
	 * 変更前：メールアドレス1を戻り値で返却する<br>
	 * <br>
	 * @return bMailAddress1
	 */
	public String getBMailAddress1(){
		return bMailAddress1;
	}

	/**
	 * 変更前：メールアドレス1設定<br>
	 * <br>
	 * 変更前：メールアドレス1を引数で設定する<br>
	 * <br>
	 * @param bMailAddress1
	 */
	public void setBMailAddress1(String bMailAddress1){
		this.bMailAddress1 = bMailAddress1;
	}

	/**
	 * 変更前：メールアドレス2取得<br>
	 * <br>
	 * 変更前：メールアドレス2を戻り値で返却する<br>
	 * <br>
	 * @return bMailAddress2
	 */
	public String getBMailAddress2(){
		return bMailAddress2;
	}

	/**
	 * 変更前：メールアドレス2設定<br>
	 * <br>
	 * 変更前：メールアドレス2を引数で設定する<br>
	 * <br>
	 * @param bMailAddress2
	 */
	public void setBMailAddress2(String bMailAddress2){
		this.bMailAddress2 = bMailAddress2;
	}

	/**
	 * 変更前：メールアドレス3取得<br>
	 * <br>
	 * 変更前：メールアドレス3を戻り値で返却する<br>
	 * <br>
	 * @return bMailAddress3
	 */
	public String getBMailAddress3(){
		return bMailAddress3;
	}

	/**
	 * 変更前：メールアドレス3設定<br>
	 * <br>
	 * 変更前：メールアドレス3を引数で設定する<br>
	 * <br>
	 * @param bMailAddress3
	 */
	public void setBMailAddress3(String bMailAddress3){
		this.bMailAddress3 = bMailAddress3;
	}

	/**
	 * 変更前：保護者：名前(姓)取得<br>
	 * <br>
	 * 変更前：保護者：名前(姓)を戻り値で返却する<br>
	 * <br>
	 * @return bGuardianFamilyJnm
	 */
	public String getBGuardianFamilyJnm(){
		return bGuardianFamilyJnm;
	}

	/**
	 * 変更前：保護者：名前(姓)設定<br>
	 * <br>
	 * 変更前：保護者：名前(姓)を引数で設定する<br>
	 * <br>
	 * @param bGuardianFamilyJnm
	 */
	public void setBGuardianFamilyJnm(String bGuardianFamilyJnm){
		this.bGuardianFamilyJnm = bGuardianFamilyJnm;
	}

	/**
	 * 変更前：保護者：名前(名）取得<br>
	 * <br>
	 * 変更前：保護者：名前(名）を戻り値で返却する<br>
	 * <br>
	 * @return bGuardianFirstJnm
	 */
	public String getBGuardianFirstJnm(){
		return bGuardianFirstJnm;
	}

	/**
	 * 変更前：保護者：名前(名）設定<br>
	 * <br>
	 * 変更前：保護者：名前(名）を引数で設定する<br>
	 * <br>
	 * @param bGuardianFirstJnm
	 */
	public void setBGuardianFirstJnm(String bGuardianFirstJnm){
		this.bGuardianFirstJnm = bGuardianFirstJnm;
	}

	/**
	 * 変更前：保護者：フリガナ(姓)取得<br>
	 * <br>
	 * 変更前：保護者：フリガナ(姓)を戻り値で返却する<br>
	 * <br>
	 * @return bGuardianFamilyKnm
	 */
	public String getBGuardianFamilyKnm(){
		return bGuardianFamilyKnm;
	}

	/**
	 * 変更前：保護者：フリガナ(姓)設定<br>
	 * <br>
	 * 変更前：保護者：フリガナ(姓)を引数で設定する<br>
	 * <br>
	 * @param bGuardianFamilyKnm
	 */
	public void setBGuardianFamilyKnm(String bGuardianFamilyKnm){
		this.bGuardianFamilyKnm = bGuardianFamilyKnm;
	}

	/**
	 * 変更前：保護者：フリガナ(名）取得<br>
	 * <br>
	 * 変更前：保護者：フリガナ(名）を戻り値で返却する<br>
	 * <br>
	 * @return bGuardianFirstKnm
	 */
	public String getBGuardianFirstKnm(){
		return bGuardianFirstKnm;
	}

	/**
	 * 変更前：保護者：フリガナ(名）設定<br>
	 * <br>
	 * 変更前：保護者：フリガナ(名）を引数で設定する<br>
	 * <br>
	 * @param bGuardianFirstKnm
	 */
	public void setBGuardianFirstKnm(String bGuardianFirstKnm){
		this.bGuardianFirstKnm = bGuardianFirstKnm;
	}

	/**
	 * 変更前：あなたとの続柄取得<br>
	 * <br>
	 * 変更前：あなたとの続柄を戻り値で返却する<br>
	 * <br>
	 * @return bGuardianFamilyRelationship
	 */
	public String getBGuardianFamilyRelationship(){
		return bGuardianFamilyRelationship;
	}

	/**
	 * 変更前：あなたとの続柄設定<br>
	 * <br>
	 * 変更前：あなたとの続柄を引数で設定する<br>
	 * <br>
	 * @param bGuardianFamilyRelationship
	 */
	public void setBGuardianFamilyRelationship(String bGuardianFamilyRelationship){
		this.bGuardianFamilyRelationship = bGuardianFamilyRelationship;
	}

	/**
	 * 変更前：保護者：電話番号1取得<br>
	 * <br>
	 * 変更前：保護者：電話番号1を戻り値で返却する<br>
	 * <br>
	 * @return bGuardianTel1
	 */
	public String getBGuardianTel1(){
		return bGuardianTel1;
	}

	/**
	 * 変更前：保護者：電話番号1設定<br>
	 * <br>
	 * 変更前：保護者：電話番号1を引数で設定する<br>
	 * <br>
	 * @param bGuardianTel1
	 */
	public void setBGuardianTel1(String bGuardianTel1){
		this.bGuardianTel1 = bGuardianTel1;
	}

	/**
	 * 変更前：保護者：電話番号2取得<br>
	 * <br>
	 * 変更前：保護者：電話番号2を戻り値で返却する<br>
	 * <br>
	 * @return bGuardianTel2
	 */
	public String getBGuardianTel2(){
		return bGuardianTel2;
	}

	/**
	 * 変更前：保護者：電話番号2設定<br>
	 * <br>
	 * 変更前：保護者：電話番号2を引数で設定する<br>
	 * <br>
	 * @param bGuardianTel2
	 */
	public void setBGuardianTel2(String bGuardianTel2){
		this.bGuardianTel2 = bGuardianTel2;
	}

	/**
	 * 変更前：保護者：生年月日取得<br>
	 * <br>
	 * 変更前：保護者：生年月日を戻り値で返却する<br>
	 * <br>
	 * @return bGuardianBirthDt
	 */
	public String getBGuardianBirthDt(){
		return bGuardianBirthDt;
	}

	/**
	 * 変更前：保護者：生年月日設定<br>
	 * <br>
	 * 変更前：保護者：生年月日を引数で設定する<br>
	 * <br>
	 * @param bGuardianBirthDt
	 */
	public void setBGuardianBirthDt(String bGuardianBirthDt){
		this.bGuardianBirthDt = bGuardianBirthDt;
	}

	/**
	 * 変更前：保護者：郵便番号取得<br>
	 * <br>
	 * 変更前：保護者：郵便番号を戻り値で返却する<br>
	 * <br>
	 * @return bGuardianZipCd
	 */
	public String getBGuardianZipCd(){
		return bGuardianZipCd;
	}

	/**
	 * 変更前：保護者：郵便番号設定<br>
	 * <br>
	 * 変更前：保護者：郵便番号を引数で設定する<br>
	 * <br>
	 * @param bGuardianZipCd
	 */
	public void setBGuardianZipCd(String bGuardianZipCd){
		this.bGuardianZipCd = bGuardianZipCd;
	}

	/**
	 * 変更前：保護者：住所(地域)コード取得<br>
	 * <br>
	 * 変更前：保護者：住所(地域)コードを戻り値で返却する<br>
	 * <br>
	 * @return bGuardianAddressAreaCd
	 */
	public String getBGuardianAddressAreaCd(){
		return bGuardianAddressAreaCd;
	}

	/**
	 * 変更前：保護者：住所(地域)コード設定<br>
	 * <br>
	 * 変更前：保護者：住所(地域)コードを引数で設定する<br>
	 * <br>
	 * @param bGuardianAddressAreaCd
	 */
	public void setBGuardianAddressAreaCd(String bGuardianAddressAreaCd){
		this.bGuardianAddressAreaCd = bGuardianAddressAreaCd;
	}

	/**
	 * 変更前：保護者：住所(地域)名取得<br>
	 * <br>
	 * 変更前：保護者：住所(地域)名を戻り値で返却する<br>
	 * <br>
	 * @return bGuardianAddressAreaJnm
	 */
	public String getBGuardianAddressAreaJnm(){
		return bGuardianAddressAreaJnm;
	}

	/**
	 * 変更前：保護者：住所(地域)名設定<br>
	 * <br>
	 * 変更前：保護者：住所(地域)名を引数で設定する<br>
	 * <br>
	 * @param bGuardianAddressAreaJnm
	 */
	public void setBGuardianAddressAreaJnm(String bGuardianAddressAreaJnm){
		this.bGuardianAddressAreaJnm = bGuardianAddressAreaJnm;
	}

	/**
	 * 変更前：保護者：住所(都道府県)コード取得<br>
	 * <br>
	 * 変更前：保護者：住所(都道府県)コードを戻り値で返却する<br>
	 * <br>
	 * @return bGuardianAddressPrefectureCd
	 */
	public String getBGuardianAddressPrefectureCd(){
		return bGuardianAddressPrefectureCd;
	}

	/**
	 * 変更前：保護者：住所(都道府県)コード設定<br>
	 * <br>
	 * 変更前：保護者：住所(都道府県)コードを引数で設定する<br>
	 * <br>
	 * @param bGuardianAddressPrefectureCd
	 */
	public void setBGuardianAddressPrefectureCd(String bGuardianAddressPrefectureCd){
		this.bGuardianAddressPrefectureCd = bGuardianAddressPrefectureCd;
	}

	/**
	 * 変更前：保護者：住所(都道府県)名取得<br>
	 * <br>
	 * 変更前：保護者：住所(都道府県)名を戻り値で返却する<br>
	 * <br>
	 * @return bGuardianAddressPrefectureJnm
	 */
	public String getBGuardianAddressPrefectureJnm(){
		return bGuardianAddressPrefectureJnm;
	}

	/**
	 * 変更前：保護者：住所(都道府県)名設定<br>
	 * <br>
	 * 変更前：保護者：住所(都道府県)名を引数で設定する<br>
	 * <br>
	 * @param bGuardianAddressPrefectureJnm
	 */
	public void setBGuardianAddressPrefectureJnm(String bGuardianAddressPrefectureJnm){
		this.bGuardianAddressPrefectureJnm = bGuardianAddressPrefectureJnm;
	}

	/**
	 * 変更前：保護者：住所(市区町村 等)取得<br>
	 * <br>
	 * 変更前：保護者：住所(市区町村 等)を戻り値で返却する<br>
	 * <br>
	 * @return bGuardianAddressCity
	 */
	public String getBGuardianAddressCity(){
		return bGuardianAddressCity;
	}

	/**
	 * 変更前：保護者：住所(市区町村 等)設定<br>
	 * <br>
	 * 変更前：保護者：住所(市区町村 等)を引数で設定する<br>
	 * <br>
	 * @param bGuardianAddressCity
	 */
	public void setBGuardianAddressCity(String bGuardianAddressCity){
		this.bGuardianAddressCity = bGuardianAddressCity;
	}

	/**
	 * 変更前：保護者：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)取得<br>
	 * <br>
	 * 変更前：保護者：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)を戻り値で返却する<br>
	 * <br>
	 * @return bGuardianAddressOthers
	 */
	public String getBGuardianAddressOthers(){
		return bGuardianAddressOthers;
	}

	/**
	 * 変更前：保護者：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)設定<br>
	 * <br>
	 * 変更前：保護者：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)を引数で設定する<br>
	 * <br>
	 * @param bGuardianAddressOthers
	 */
	public void setBGuardianAddressOthers(String bGuardianAddressOthers){
		this.bGuardianAddressOthers = bGuardianAddressOthers;
	}

	/**
	 * 変更前：保護者：性別区分取得<br>
	 * <br>
	 * 変更前：保護者：性別区分を戻り値で返却する<br>
	 * <br>
	 * @return bGuardianGenderKbn
	 */
	public String getBGuardianGenderKbn(){
		return bGuardianGenderKbn;
	}

	/**
	 * 変更前：保護者：性別区分設定<br>
	 * <br>
	 * 変更前：保護者：性別区分を引数で設定する<br>
	 * <br>
	 * @param bGuardianGenderKbn
	 */
	public void setBGuardianGenderKbn(String bGuardianGenderKbn){
		this.bGuardianGenderKbn = bGuardianGenderKbn;
	}

	/**
	 * 変更前：保護者：性別名取得<br>
	 * <br>
	 * 変更前：保護者：性別名を戻り値で返却する<br>
	 * <br>
	 * @return bGuardianGenderJnm
	 */
	public String getBGuardianGenderJnm(){
		return bGuardianGenderJnm;
	}

	/**
	 * 変更前：保護者：性別名設定<br>
	 * <br>
	 * 変更前：保護者：性別名を引数で設定する<br>
	 * <br>
	 * @param bGuardianGenderJnm
	 */
	public void setBGuardianGenderJnm(String bGuardianGenderJnm){
		this.bGuardianGenderJnm = bGuardianGenderJnm;
	}

	/**
	 * 変更前：保護者：メールアドレス1取得<br>
	 * <br>
	 * 変更前：保護者：メールアドレス1を戻り値で返却する<br>
	 * <br>
	 * @return bGuardianMailAddress1
	 */
	public String getBGuardianMailAddress1(){
		return bGuardianMailAddress1;
	}

	/**
	 * 変更前：保護者：メールアドレス1設定<br>
	 * <br>
	 * 変更前：保護者：メールアドレス1を引数で設定する<br>
	 * <br>
	 * @param bGuardianMailAddress1
	 */
	public void setBGuardianMailAddress1(String bGuardianMailAddress1){
		this.bGuardianMailAddress1 = bGuardianMailAddress1;
	}

	/**
	 * 変更前：保護者：メールアドレス2取得<br>
	 * <br>
	 * 変更前：保護者：メールアドレス2を戻り値で返却する<br>
	 * <br>
	 * @return bGuardianMailAddress2
	 */
	public String getBGuardianMailAddress2(){
		return bGuardianMailAddress2;
	}

	/**
	 * 変更前：保護者：メールアドレス2設定<br>
	 * <br>
	 * 変更前：保護者：メールアドレス2を引数で設定する<br>
	 * <br>
	 * @param bGuardianMailAddress2
	 */
	public void setBGuardianMailAddress2(String bGuardianMailAddress2){
		this.bGuardianMailAddress2 = bGuardianMailAddress2;
	}

	/**
	 * 変更前：保護者：メールアドレス3取得<br>
	 * <br>
	 * 変更前：保護者：メールアドレス3を戻り値で返却する<br>
	 * <br>
	 * @return bGuardianMailAddress3
	 */
	public String getBGuardianMailAddress3(){
		return bGuardianMailAddress3;
	}

	/**
	 * 変更前：保護者：メールアドレス3設定<br>
	 * <br>
	 * 変更前：保護者：メールアドレス3を引数で設定する<br>
	 * <br>
	 * @param bGuardianMailAddress3
	 */
	public void setBGuardianMailAddress3(String bGuardianMailAddress3){
		this.bGuardianMailAddress3 = bGuardianMailAddress3;
	}

	/**
	 * 変更後：名前(姓)取得<br>
	 * <br>
	 * 変更後：名前(姓)を戻り値で返却する<br>
	 * <br>
	 * @return aFamilyJnm
	 */
	public String getAFamilyJnm(){
		return aFamilyJnm;
	}

	/**
	 * 変更後：名前(姓)設定<br>
	 * <br>
	 * 変更後：名前(姓)を引数で設定する<br>
	 * <br>
	 * @param aFamilyJnm
	 */
	public void setAFamilyJnm(String aFamilyJnm){
		this.aFamilyJnm = aFamilyJnm;
	}

	/**
	 * 変更後：名前(名)取得<br>
	 * <br>
	 * 変更後：名前(名)を戻り値で返却する<br>
	 * <br>
	 * @return aFirstJnm
	 */
	public String getAFirstJnm(){
		return aFirstJnm;
	}

	/**
	 * 変更後：名前(名)設定<br>
	 * <br>
	 * 変更後：名前(名)を引数で設定する<br>
	 * <br>
	 * @param aFirstJnm
	 */
	public void setAFirstJnm(String aFirstJnm){
		this.aFirstJnm = aFirstJnm;
	}

	/**
	 * 変更後：フリガナ(姓)取得<br>
	 * <br>
	 * 変更後：フリガナ(姓)を戻り値で返却する<br>
	 * <br>
	 * @return aFamilyKnm
	 */
	public String getAFamilyKnm(){
		return aFamilyKnm;
	}

	/**
	 * 変更後：フリガナ(姓)設定<br>
	 * <br>
	 * 変更後：フリガナ(姓)を引数で設定する<br>
	 * <br>
	 * @param aFamilyKnm
	 */
	public void setAFamilyKnm(String aFamilyKnm){
		this.aFamilyKnm = aFamilyKnm;
	}

	/**
	 * 変更後：フリガナ(名)取得<br>
	 * <br>
	 * 変更後：フリガナ(名)を戻り値で返却する<br>
	 * <br>
	 * @return aFirstKnm
	 */
	public String getAFirstKnm(){
		return aFirstKnm;
	}

	/**
	 * 変更後：フリガナ(名)設定<br>
	 * <br>
	 * 変更後：フリガナ(名)を引数で設定する<br>
	 * <br>
	 * @param aFirstKnm
	 */
	public void setAFirstKnm(String aFirstKnm){
		this.aFirstKnm = aFirstKnm;
	}

	/**
	 * 変更後：ローマ字(姓)取得<br>
	 * <br>
	 * 変更後：ローマ字(姓)を戻り値で返却する<br>
	 * <br>
	 * @return aFamilyRomaji
	 */
	public String getAFamilyRomaji(){
		return aFamilyRomaji;
	}

	/**
	 * 変更後：ローマ字(姓)設定<br>
	 * <br>
	 * 変更後：ローマ字(姓)を引数で設定する<br>
	 * <br>
	 * @param aFamilyRomaji
	 */
	public void setAFamilyRomaji(String aFamilyRomaji){
		this.aFamilyRomaji = aFamilyRomaji;
	}

	/**
	 * 変更後：ローマ字(名)取得<br>
	 * <br>
	 * 変更後：ローマ字(名)を戻り値で返却する<br>
	 * <br>
	 * @return aFirstRomaji
	 */
	public String getAFirstRomaji(){
		return aFirstRomaji;
	}

	/**
	 * 変更後：ローマ字(名)設定<br>
	 * <br>
	 * 変更後：ローマ字(名)を引数で設定する<br>
	 * <br>
	 * @param aFirstRomaji
	 */
	public void setAFirstRomaji(String aFirstRomaji){
		this.aFirstRomaji = aFirstRomaji;
	}

	/**
	 * 変更後：ニックネーム取得<br>
	 * <br>
	 * 変更後：ニックネームを戻り値で返却する<br>
	 * <br>
	 * @return aNickNm
	 */
	public String getANickNm(){
		return aNickNm;
	}

	/**
	 * 変更後：ニックネーム設定<br>
	 * <br>
	 * 変更後：ニックネームを引数で設定する<br>
	 * <br>
	 * @param aNickNm
	 */
	public void setANickNm(String aNickNm){
		this.aNickNm = aNickNm;
	}

	/**
	 * 変更後：パスワード取得<br>
	 * <br>
	 * 変更後：パスワードを戻り値で返却する<br>
	 * <br>
	 * @return aPassword
	 */
	public String getAPassword(){
		return aPassword;
	}

	/**
	 * 変更後：パスワード設定<br>
	 * <br>
	 * 変更後：パスワードを引数で設定する<br>
	 * <br>
	 * @param aPassword
	 */
	public void setAPassword(String aPassword){
		this.aPassword = aPassword;
	}

	/**
	 * 変更後：電話番号1取得<br>
	 * <br>
	 * 変更後：電話番号1を戻り値で返却する<br>
	 * <br>
	 * @return aTel1
	 */
	public String getATel1(){
		return aTel1;
	}

	/**
	 * 変更後：電話番号1設定<br>
	 * <br>
	 * 変更後：電話番号1を引数で設定する<br>
	 * <br>
	 * @param aTel1
	 */
	public void setATel1(String aTel1){
		this.aTel1 = aTel1;
	}

	/**
	 * 変更後：電話番号2取得<br>
	 * <br>
	 * 変更後：電話番号2を戻り値で返却する<br>
	 * <br>
	 * @return aTel2
	 */
	public String getATel2(){
		return aTel2;
	}

	/**
	 * 変更後：電話番号2設定<br>
	 * <br>
	 * 変更後：電話番号2を引数で設定する<br>
	 * <br>
	 * @param aTel2
	 */
	public void setATel2(String aTel2){
		this.aTel2 = aTel2;
	}

	/**
	 * 変更後：生年月日取得<br>
	 * <br>
	 * 変更後：生年月日を戻り値で返却する<br>
	 * <br>
	 * @return aBirthDt
	 */
	public String getABirthDt(){
		return aBirthDt;
	}

	/**
	 * 変更後：生年月日設定<br>
	 * <br>
	 * 変更後：生年月日を引数で設定する<br>
	 * <br>
	 * @param aBirthDt
	 */
	public void setABirthDt(String aBirthDt){
		this.aBirthDt = aBirthDt;
	}

	/**
	 * 変更後：郵便番号取得<br>
	 * <br>
	 * 変更後：郵便番号を戻り値で返却する<br>
	 * <br>
	 * @return aZipCd
	 */
	public String getAZipCd(){
		return aZipCd;
	}

	/**
	 * 変更後：郵便番号設定<br>
	 * <br>
	 * 変更後：郵便番号を引数で設定する<br>
	 * <br>
	 * @param aZipCd
	 */
	public void setAZipCd(String aZipCd){
		this.aZipCd = aZipCd;
	}

	/**
	 * 変更後：住所(地域)コード取得<br>
	 * <br>
	 * 変更後：住所(地域)コードを戻り値で返却する<br>
	 * <br>
	 * @return aAddressAreaCd
	 */
	public String getAAddressAreaCd(){
		return aAddressAreaCd;
	}

	/**
	 * 変更後：住所(地域)コード設定<br>
	 * <br>
	 * 変更後：住所(地域)コードを引数で設定する<br>
	 * <br>
	 * @param aAddressAreaCd
	 */
	public void setAAddressAreaCd(String aAddressAreaCd){
		this.aAddressAreaCd = aAddressAreaCd;
	}

	/**
	 * 変更後：住所(地域)名取得<br>
	 * <br>
	 * 変更後：住所(地域)名を戻り値で返却する<br>
	 * <br>
	 * @return aAddressAreaJnm
	 */
	public String getAAddressAreaJnm(){
		return aAddressAreaJnm;
	}

	/**
	 * 変更後：住所(地域)名設定<br>
	 * <br>
	 * 変更後：住所(地域)名を引数で設定する<br>
	 * <br>
	 * @param aAddressAreaJnm
	 */
	public void setAAddressAreaJnm(String aAddressAreaJnm){
		this.aAddressAreaJnm = aAddressAreaJnm;
	}

	/**
	 * 変更後：住所(都道府県)コード取得<br>
	 * <br>
	 * 変更後：住所(都道府県)コードを戻り値で返却する<br>
	 * <br>
	 * @return aAddressPrefectureCd
	 */
	public String getAAddressPrefectureCd(){
		return aAddressPrefectureCd;
	}

	/**
	 * 変更後：住所(都道府県)コード設定<br>
	 * <br>
	 * 変更後：住所(都道府県)コードを引数で設定する<br>
	 * <br>
	 * @param aAddressPrefectureCd
	 */
	public void setAAddressPrefectureCd(String aAddressPrefectureCd){
		this.aAddressPrefectureCd = aAddressPrefectureCd;
	}

	/**
	 * 変更後：住所(都道府県)名取得<br>
	 * <br>
	 * 変更後：住所(都道府県)名を戻り値で返却する<br>
	 * <br>
	 * @return aAddressPrefectureJnm
	 */
	public String getAAddressPrefectureJnm(){
		return aAddressPrefectureJnm;
	}

	/**
	 * 変更後：住所(都道府県)名設定<br>
	 * <br>
	 * 変更後：住所(都道府県)名を引数で設定する<br>
	 * <br>
	 * @param aAddressPrefectureJnm
	 */
	public void setAAddressPrefectureJnm(String aAddressPrefectureJnm){
		this.aAddressPrefectureJnm = aAddressPrefectureJnm;
	}

	/**
	 * 変更後：住所(市区町村 等)取得<br>
	 * <br>
	 * 変更後：住所(市区町村 等)を戻り値で返却する<br>
	 * <br>
	 * @return aAddressCity
	 */
	public String getAAddressCity(){
		return aAddressCity;
	}

	/**
	 * 変更後：住所(市区町村 等)設定<br>
	 * <br>
	 * 変更後：住所(市区町村 等)を引数で設定する<br>
	 * <br>
	 * @param aAddressCity
	 */
	public void setAAddressCity(String aAddressCity){
		this.aAddressCity = aAddressCity;
	}

	/**
	 * 変更後：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)取得<br>
	 * <br>
	 * 変更後：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)を戻り値で返却する<br>
	 * <br>
	 * @return aAddressOthers
	 */
	public String getAAddressOthers(){
		return aAddressOthers;
	}

	/**
	 * 変更後：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)設定<br>
	 * <br>
	 * 変更後：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)を引数で設定する<br>
	 * <br>
	 * @param aAddressOthers
	 */
	public void setAAddressOthers(String aAddressOthers){
		this.aAddressOthers = aAddressOthers;
	}

	/**
	 * 変更後：性別区分取得<br>
	 * <br>
	 * 変更後：性別区分を戻り値で返却する<br>
	 * <br>
	 * @return aGenderKbn
	 */
	public String getAGenderKbn(){
		return aGenderKbn;
	}

	/**
	 * 変更後：性別区分設定<br>
	 * <br>
	 * 変更後：性別区分を引数で設定する<br>
	 * <br>
	 * @param aGenderKbn
	 */
	public void setAGenderKbn(String aGenderKbn){
		this.aGenderKbn = aGenderKbn;
	}

	/**
	 * 変更後：性別名取得<br>
	 * <br>
	 * 変更後：性別名を戻り値で返却する<br>
	 * <br>
	 * @return aGenderJnm
	 */
	public String getAGenderJnm(){
		return aGenderJnm;
	}

	/**
	 * 変更後：性別名設定<br>
	 * <br>
	 * 変更後：性別名を引数で設定する<br>
	 * <br>
	 * @param aGenderJnm
	 */
	public void setAGenderJnm(String aGenderJnm){
		this.aGenderJnm = aGenderJnm;
	}

	/**
	 * 変更後：メールアドレス1取得<br>
	 * <br>
	 * 変更後：メールアドレス1を戻り値で返却する<br>
	 * <br>
	 * @return aMailAddress1
	 */
	public String getAMailAddress1(){
		return aMailAddress1;
	}

	/**
	 * 変更後：メールアドレス1設定<br>
	 * <br>
	 * 変更後：メールアドレス1を引数で設定する<br>
	 * <br>
	 * @param aMailAddress1
	 */
	public void setAMailAddress1(String aMailAddress1){
		this.aMailAddress1 = aMailAddress1;
	}

	/**
	 * 変更後：メールアドレス2取得<br>
	 * <br>
	 * 変更後：メールアドレス2を戻り値で返却する<br>
	 * <br>
	 * @return aMailAddress2
	 */
	public String getAMailAddress2(){
		return aMailAddress2;
	}

	/**
	 * 変更後：メールアドレス2設定<br>
	 * <br>
	 * 変更後：メールアドレス2を引数で設定する<br>
	 * <br>
	 * @param aMailAddress2
	 */
	public void setAMailAddress2(String aMailAddress2){
		this.aMailAddress2 = aMailAddress2;
	}

	/**
	 * 変更後：メールアドレス3取得<br>
	 * <br>
	 * 変更後：メールアドレス3を戻り値で返却する<br>
	 * <br>
	 * @return aMailAddress3
	 */
	public String getAMailAddress3(){
		return aMailAddress3;
	}

	/**
	 * 変更後：メールアドレス3設定<br>
	 * <br>
	 * 変更後：メールアドレス3を引数で設定する<br>
	 * <br>
	 * @param aMailAddress3
	 */
	public void setAMailAddress3(String aMailAddress3){
		this.aMailAddress3 = aMailAddress3;
	}

	/**
	 * 変更後：保護者：名前(姓)取得<br>
	 * <br>
	 * 変更後：保護者：名前(姓)を戻り値で返却する<br>
	 * <br>
	 * @return aGuardianFamilyJnm
	 */
	public String getAGuardianFamilyJnm(){
		return aGuardianFamilyJnm;
	}

	/**
	 * 変更後：保護者：名前(姓)設定<br>
	 * <br>
	 * 変更後：保護者：名前(姓)を引数で設定する<br>
	 * <br>
	 * @param aGuardianFamilyJnm
	 */
	public void setAGuardianFamilyJnm(String aGuardianFamilyJnm){
		this.aGuardianFamilyJnm = aGuardianFamilyJnm;
	}

	/**
	 * 変更後：保護者：名前(名）取得<br>
	 * <br>
	 * 変更後：保護者：名前(名）を戻り値で返却する<br>
	 * <br>
	 * @return aGuardianFirstJnm
	 */
	public String getAGuardianFirstJnm(){
		return aGuardianFirstJnm;
	}

	/**
	 * 変更後：保護者：名前(名）設定<br>
	 * <br>
	 * 変更後：保護者：名前(名）を引数で設定する<br>
	 * <br>
	 * @param aGuardianFirstJnm
	 */
	public void setAGuardianFirstJnm(String aGuardianFirstJnm){
		this.aGuardianFirstJnm = aGuardianFirstJnm;
	}

	/**
	 * 変更後：保護者：フリガナ(姓)取得<br>
	 * <br>
	 * 変更後：保護者：フリガナ(姓)を戻り値で返却する<br>
	 * <br>
	 * @return aGuardianFamilyKnm
	 */
	public String getAGuardianFamilyKnm(){
		return aGuardianFamilyKnm;
	}

	/**
	 * 変更後：保護者：フリガナ(姓)設定<br>
	 * <br>
	 * 変更後：保護者：フリガナ(姓)を引数で設定する<br>
	 * <br>
	 * @param aGuardianFamilyKnm
	 */
	public void setAGuardianFamilyKnm(String aGuardianFamilyKnm){
		this.aGuardianFamilyKnm = aGuardianFamilyKnm;
	}

	/**
	 * 変更後：保護者：フリガナ(名）取得<br>
	 * <br>
	 * 変更後：保護者：フリガナ(名）を戻り値で返却する<br>
	 * <br>
	 * @return aGuardianFirstKnm
	 */
	public String getAGuardianFirstKnm(){
		return aGuardianFirstKnm;
	}

	/**
	 * 変更後：保護者：フリガナ(名）設定<br>
	 * <br>
	 * 変更後：保護者：フリガナ(名）を引数で設定する<br>
	 * <br>
	 * @param aGuardianFirstKnm
	 */
	public void setAGuardianFirstKnm(String aGuardianFirstKnm){
		this.aGuardianFirstKnm = aGuardianFirstKnm;
	}

	/**
	 * 変更後：あなたとの続柄取得<br>
	 * <br>
	 * 変更後：あなたとの続柄を戻り値で返却する<br>
	 * <br>
	 * @return aGuardianFamilyRelationship
	 */
	public String getAGuardianFamilyRelationship(){
		return aGuardianFamilyRelationship;
	}

	/**
	 * 変更後：あなたとの続柄設定<br>
	 * <br>
	 * 変更後：あなたとの続柄を引数で設定する<br>
	 * <br>
	 * @param aGuardianFamilyRelationship
	 */
	public void setAGuardianFamilyRelationship(String aGuardianFamilyRelationship){
		this.aGuardianFamilyRelationship = aGuardianFamilyRelationship;
	}

	/**
	 * 変更後：保護者：電話番号1取得<br>
	 * <br>
	 * 変更後：保護者：電話番号1を戻り値で返却する<br>
	 * <br>
	 * @return aGuardianTel1
	 */
	public String getAGuardianTel1(){
		return aGuardianTel1;
	}

	/**
	 * 変更後：保護者：電話番号1設定<br>
	 * <br>
	 * 変更後：保護者：電話番号1を引数で設定する<br>
	 * <br>
	 * @param aGuardianTel1
	 */
	public void setAGuardianTel1(String aGuardianTel1){
		this.aGuardianTel1 = aGuardianTel1;
	}

	/**
	 * 変更後：保護者：電話番号2取得<br>
	 * <br>
	 * 変更後：保護者：電話番号2を戻り値で返却する<br>
	 * <br>
	 * @return aGuardianTel2
	 */
	public String getAGuardianTel2(){
		return aGuardianTel2;
	}

	/**
	 * 変更後：保護者：電話番号2設定<br>
	 * <br>
	 * 変更後：保護者：電話番号2を引数で設定する<br>
	 * <br>
	 * @param aGuardianTel2
	 */
	public void setAGuardianTel2(String aGuardianTel2){
		this.aGuardianTel2 = aGuardianTel2;
	}

	/**
	 * 変更後：保護者：生年月日取得<br>
	 * <br>
	 * 変更後：保護者：生年月日を戻り値で返却する<br>
	 * <br>
	 * @return aGuardianBirthDt
	 */
	public String getAGuardianBirthDt(){
		return aGuardianBirthDt;
	}

	/**
	 * 変更後：保護者：生年月日設定<br>
	 * <br>
	 * 変更後：保護者：生年月日を引数で設定する<br>
	 * <br>
	 * @param aGuardianBirthDt
	 */
	public void setAGuardianBirthDt(String aGuardianBirthDt){
		this.aGuardianBirthDt = aGuardianBirthDt;
	}

	/**
	 * 変更後：保護者：郵便番号取得<br>
	 * <br>
	 * 変更後：保護者：郵便番号を戻り値で返却する<br>
	 * <br>
	 * @return aGuardianZipCd
	 */
	public String getAGuardianZipCd(){
		return aGuardianZipCd;
	}

	/**
	 * 変更後：保護者：郵便番号設定<br>
	 * <br>
	 * 変更後：保護者：郵便番号を引数で設定する<br>
	 * <br>
	 * @param aGuardianZipCd
	 */
	public void setAGuardianZipCd(String aGuardianZipCd){
		this.aGuardianZipCd = aGuardianZipCd;
	}

	/**
	 * 変更後：保護者：住所(地域)コード取得<br>
	 * <br>
	 * 変更後：保護者：住所(地域)コードを戻り値で返却する<br>
	 * <br>
	 * @return aGuardianAddressAreaCd
	 */
	public String getAGuardianAddressAreaCd(){
		return aGuardianAddressAreaCd;
	}

	/**
	 * 変更後：保護者：住所(地域)コード設定<br>
	 * <br>
	 * 変更後：保護者：住所(地域)コードを引数で設定する<br>
	 * <br>
	 * @param aGuardianAddressAreaCd
	 */
	public void setAGuardianAddressAreaCd(String aGuardianAddressAreaCd){
		this.aGuardianAddressAreaCd = aGuardianAddressAreaCd;
	}

	/**
	 * 変更後：保護者：住所(地域)名取得<br>
	 * <br>
	 * 変更後：保護者：住所(地域)名を戻り値で返却する<br>
	 * <br>
	 * @return aGuardianAddressAreaJnm
	 */
	public String getAGuardianAddressAreaJnm(){
		return aGuardianAddressAreaJnm;
	}

	/**
	 * 変更後：保護者：住所(地域)名設定<br>
	 * <br>
	 * 変更後：保護者：住所(地域)名を引数で設定する<br>
	 * <br>
	 * @param aGuardianAddressAreaJnm
	 */
	public void setAGuardianAddressAreaJnm(String aGuardianAddressAreaJnm){
		this.aGuardianAddressAreaJnm = aGuardianAddressAreaJnm;
	}

	/**
	 * 変更後：保護者：住所(都道府県)コード取得<br>
	 * <br>
	 * 変更後：保護者：住所(都道府県)コードを戻り値で返却する<br>
	 * <br>
	 * @return aGuardianAddressPrefectureCd
	 */
	public String getAGuardianAddressPrefectureCd(){
		return aGuardianAddressPrefectureCd;
	}

	/**
	 * 変更後：保護者：住所(都道府県)コード設定<br>
	 * <br>
	 * 変更後：保護者：住所(都道府県)コードを引数で設定する<br>
	 * <br>
	 * @param aGuardianAddressPrefectureCd
	 */
	public void setAGuardianAddressPrefectureCd(String aGuardianAddressPrefectureCd){
		this.aGuardianAddressPrefectureCd = aGuardianAddressPrefectureCd;
	}

	/**
	 * 変更後：保護者：住所(都道府県)名取得<br>
	 * <br>
	 * 変更後：保護者：住所(都道府県)名を戻り値で返却する<br>
	 * <br>
	 * @return aGuardianAddressPrefectureJnm
	 */
	public String getAGuardianAddressPrefectureJnm(){
		return aGuardianAddressPrefectureJnm;
	}

	/**
	 * 変更後：保護者：住所(都道府県)名設定<br>
	 * <br>
	 * 変更後：保護者：住所(都道府県)名を引数で設定する<br>
	 * <br>
	 * @param aGuardianAddressPrefectureJnm
	 */
	public void setAGuardianAddressPrefectureJnm(String aGuardianAddressPrefectureJnm){
		this.aGuardianAddressPrefectureJnm = aGuardianAddressPrefectureJnm;
	}

	/**
	 * 変更後：保護者：住所(市区町村 等)取得<br>
	 * <br>
	 * 変更後：保護者：住所(市区町村 等)を戻り値で返却する<br>
	 * <br>
	 * @return aGuardianAddressCity
	 */
	public String getAGuardianAddressCity(){
		return aGuardianAddressCity;
	}

	/**
	 * 変更後：保護者：住所(市区町村 等)設定<br>
	 * <br>
	 * 変更後：保護者：住所(市区町村 等)を引数で設定する<br>
	 * <br>
	 * @param aGuardianAddressCity
	 */
	public void setAGuardianAddressCity(String aGuardianAddressCity){
		this.aGuardianAddressCity = aGuardianAddressCity;
	}

	/**
	 * 変更後：保護者：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)取得<br>
	 * <br>
	 * 変更後：保護者：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)を戻り値で返却する<br>
	 * <br>
	 * @return aGuardianAddressOthers
	 */
	public String getAGuardianAddressOthers(){
		return aGuardianAddressOthers;
	}

	/**
	 * 変更後：保護者：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)設定<br>
	 * <br>
	 * 変更後：保護者：住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)を引数で設定する<br>
	 * <br>
	 * @param aGuardianAddressOthers
	 */
	public void setAGuardianAddressOthers(String aGuardianAddressOthers){
		this.aGuardianAddressOthers = aGuardianAddressOthers;
	}

	/**
	 * 変更後：保護者：性別区分取得<br>
	 * <br>
	 * 変更後：保護者：性別区分を戻り値で返却する<br>
	 * <br>
	 * @return aGuardianGenderKbn
	 */
	public String getAGuardianGenderKbn(){
		return aGuardianGenderKbn;
	}

	/**
	 * 変更後：保護者：性別区分設定<br>
	 * <br>
	 * 変更後：保護者：性別区分を引数で設定する<br>
	 * <br>
	 * @param aGuardianGenderKbn
	 */
	public void setAGuardianGenderKbn(String aGuardianGenderKbn){
		this.aGuardianGenderKbn = aGuardianGenderKbn;
	}

	/**
	 * 変更後：保護者：性別名取得<br>
	 * <br>
	 * 変更後：保護者：性別名を戻り値で返却する<br>
	 * <br>
	 * @return aGuardianGenderJnm
	 */
	public String getAGuardianGenderJnm(){
		return aGuardianGenderJnm;
	}

	/**
	 * 変更後：保護者：性別名設定<br>
	 * <br>
	 * 変更後：保護者：性別名を引数で設定する<br>
	 * <br>
	 * @param aGuardianGenderJnm
	 */
	public void setAGuardianGenderJnm(String aGuardianGenderJnm){
		this.aGuardianGenderJnm = aGuardianGenderJnm;
	}

	/**
	 * 変更後：保護者：メールアドレス1取得<br>
	 * <br>
	 * 変更後：保護者：メールアドレス1を戻り値で返却する<br>
	 * <br>
	 * @return aGuardianMailAddress1
	 */
	public String getAGuardianMailAddress1(){
		return aGuardianMailAddress1;
	}

	/**
	 * 変更後：保護者：メールアドレス1設定<br>
	 * <br>
	 * 変更後：保護者：メールアドレス1を引数で設定する<br>
	 * <br>
	 * @param aGuardianMailAddress1
	 */
	public void setAGuardianMailAddress1(String aGuardianMailAddress1){
		this.aGuardianMailAddress1 = aGuardianMailAddress1;
	}

	/**
	 * 変更後：保護者：メールアドレス2取得<br>
	 * <br>
	 * 変更後：保護者：メールアドレス2を戻り値で返却する<br>
	 * <br>
	 * @return aGuardianMailAddress2
	 */
	public String getAGuardianMailAddress2(){
		return aGuardianMailAddress2;
	}

	/**
	 * 変更後：保護者：メールアドレス2設定<br>
	 * <br>
	 * 変更後：保護者：メールアドレス2を引数で設定する<br>
	 * <br>
	 * @param aGuardianMailAddress2
	 */
	public void setAGuardianMailAddress2(String aGuardianMailAddress2){
		this.aGuardianMailAddress2 = aGuardianMailAddress2;
	}

	/**
	 * 変更後：保護者：メールアドレス3取得<br>
	 * <br>
	 * 変更後：保護者：メールアドレス3を戻り値で返却する<br>
	 * <br>
	 * @return aGuardianMailAddress3
	 */
	public String getAGuardianMailAddress3(){
		return aGuardianMailAddress3;
	}

	/**
	 * 変更後：保護者：メールアドレス3設定<br>
	 * <br>
	 * 変更後：保護者：メールアドレス3を引数で設定する<br>
	 * <br>
	 * @param aGuardianMailAddress3
	 */
	public void setAGuardianMailAddress3(String aGuardianMailAddress3){
		this.aGuardianMailAddress3 = aGuardianMailAddress3;
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


	/**
	 * 変更有フラグ取得<br>
	 * <br>
	 * 変更有フラグを戻り値で返却する<br>
	 * <br>
	 * @return changeYesFlg
	 */
	public boolean getChangeYesFlg(){
		return changeYesFlg;
	}

	/**
	 * 変更有フラグ設定<br>
	 * <br>
	 * 変更有フラグを引数で設定する<br>
	 * <br>
	 * @param changeYesFlg
	 */
	public void setChangeYesFlg(boolean changeYesFlg){
		this.changeYesFlg = changeYesFlg;
	}



}
