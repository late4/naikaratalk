package com.naikara_talk.dto;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DTOクラス<br>
 * <b>クラス名称　　　:</b>講師単位のコースクラス<br>
 * <b>クラス概要　　　:</b>講師単位のコースDTO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/06/05 TECS 新規作成
 * $Date$
 * $Author$
 * $Revision$
 */
public class TeacherCourseListDto extends AbstractDto{

	private String courseCd; //コースコード
	private String bigClassificationCd; //大分類コード
	private String middleClassificationCd; //中分類コード
	private String smallClassificationCd; //小分類コード
	private String courseJnm; //コース名
	private String courseEnm; //コース名(英語名)
	private int returnCode; //リターンコード
	private String userId;


	/**
	 * コースコード取得<br>
	 * <br>
	 * コースコードを戻り値で返却する<br>
	 * <br>
	 * @return courseCd
	 */
	public String getCourseId(){
		return courseCd;
	}

	/**
	 * コースコード設定<br>
	 * <br>
	 * コースコードを引数で設定する<br>
	 * <br>
	 * @param courseCd
	 */
	public void setCourseId(String courseCd){
		this.courseCd = courseCd;
	}

	/**
	 * 大分類コード取得<br>
	 * <br>
	 * 大分類コードを戻り値で返却する<br>
	 * <br>
	 * @return bigClassificationCd
	 */
	public String getBigClassificationCd(){
		return bigClassificationCd;
	}

	/**
	 * 大分類コード設定<br>
	 * <br>
	 * 大分類コードを引数で設定する<br>
	 * <br>
	 * @param bigClassificationCd
	 */
	public void setBigClassificationCd(String bigClassificationCd){
		this.bigClassificationCd = bigClassificationCd;
	}

	/**
	 * 中分類コード取得<br>
	 * <br>
	 * 中分類コードを戻り値で返却する<br>
	 * <br>
	 * @return middleClassificationCd
	 */
	public String getMiddleClassificationCd(){
		return middleClassificationCd;
	}

	/**
	 * 中分類コード設定<br>
	 * <br>
	 * 中分類コードを引数で設定する<br>
	 * <br>
	 * @param middleClassificationCd
	 */
	public void setMiddleClassificationCd(String middleClassificationCd){
		this.middleClassificationCd = middleClassificationCd;
	}

	/**
	 * 小分類コード取得<br>
	 * <br>
	 * 小分類コードを戻り値で返却する<br>
	 * <br>
	 * @return smallClassificationCd
	 */
	public String getSmallClassificationCd(){
		return smallClassificationCd;
	}

	/**
	 * 小分類コード設定<br>
	 * <br>
	 * 小分類コードを引数で設定する<br>
	 * <br>
	 * @param middleClassificationCd
	 */
	public void setSmallClassificationCd(String smallClassificationCd){
		this.smallClassificationCd = smallClassificationCd;
	}

	/**
	 * コース名取得<br>
	 * <br>
	 * コース名を戻り値で返却する<br>
	 * <br>
	 * @return courseJnm
	 */
	public String getCourseJnm(){
		return courseJnm;
	}

	/**
	 * コース名設定<br>
	 * <br>
	 * コース名を引数で設定する<br>
	 * <br>
	 * @param courseJnm
	 */
	public void setCourseJnm(String courseJnm){
		this.courseJnm = courseJnm;
	}

	/**
	 * コース名(英語名)取得<br>
	 * <br>
	 * コース名(英語名)を戻り値で返却する<br>
	 * <br>
	 * @return courseEnm
	 */
	public String getCourseEnm(){
		return courseEnm;
	}

	/**
	 * コース名(英語名)設定<br>
	 * <br>
	 * コース名(英語名)を引数で設定する<br>
	 * <br>
	 * @param courseEnm
	 */
	public void setCourseEnm(String courseEnm){
		this.courseEnm = courseEnm;
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

	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserId(){
		return userId;
	}
}
