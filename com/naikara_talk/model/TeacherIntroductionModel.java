package com.naikara_talk.model;

/**
 * 講師紹介情報モデル
 * @author nos
 *
 */
public class TeacherIntroductionModel implements Model {

	/** カテゴリID	*/
	private String userCategoryCd;
	/** カテゴリ枝番	*/
	private String userCategorysubCd;
	/** 講師ユーザID */
	private String userId;

	/** 選択ボタンを押された */
	private String selected;

	/** カテゴリ名称	*/
	private String userCategoryNm;

	/** ニックネーム */
	private String nickAnm;

	/** 国籍 */
	private String nationality;
	/** 母国語 */
	private String nativeLang;
	/** 滞在国 */
	private String country;
	private String sellingPoint;
	private String selfRecommendation;

	/** ファミリーネーム */
	private String familyNm;
	/** ファーストネーム */
	private String firstNm;



	/**
	 * @return userCategoryCd
	 */
	public String getUserCategoryCd() {
		return userCategoryCd;
	}
	/**
	 * @param userCategoryCd セットする userCategoryCd
	 */
	public void setUserCategoryCd(String userCategoryCd) {
		this.userCategoryCd = userCategoryCd;
	}
	/**
	 * @return userCategorysubCd
	 */
	public String getUserCategorysubCd() {
		return userCategorysubCd;
	}
	/**
	 * @param userCategorysubCd セットする userCategorysubCd
	 */
	public void setUserCategorysubCd(String userCategorysubCd) {
		this.userCategorysubCd = userCategorysubCd;
	}
	/**
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId セットする userId
	 */
	public void setUserId(String userCd) {
		this.userId = userCd;
	}
	/**
	 * @return selected
	 */
	public String getSelected() {
		return selected;
	}
	/**
	 * @param selected セットする selected
	 */
	public void setSelected(String selected) {
		this.selected = selected;
	}
	/**
	 * @return nickAnm
	 */
	public String getNickAnm() {
		return nickAnm;
	}
	/**
	 * @param nickAnm セットする nickAnm
	 */
	public void setNickAnm(String nickAnm) {
		this.nickAnm = nickAnm;
	}
	/**
	 * @return nationality
	 */
	public String getNationality() {
		return nationality;
	}
	/**
	 * @param nationality セットする nationality
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	/**
	 * @return nativeLang
	 */
	public String getNativeLang() {
		return nativeLang;
	}
	/**
	 * @param nativeLang セットする nativeLang
	 */
	public void setNativeLang(String nativeLang) {
		this.nativeLang = nativeLang;
	}
	/**
	 * @return country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country セットする country
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return userCategoryNm
	 */
	public String getUserCategoryNm() {
		return userCategoryNm;
	}
	/**
	 * @param userCategoryNm セットする userCategoryNm
	 */
	public void setUserCategoryNm(String userCategoryNm) {
		this.userCategoryNm = userCategoryNm;
	}

	/**
	 * @return selfRecommendation
	 */
	public String getSelfRecommendation() {
		return selfRecommendation;
	}
	/**
	 * @param selfRecommendation セットする selfRecommendation
	 */
	public void setSelfRecommendation(String selfRecommendation) {
		this.selfRecommendation = selfRecommendation;
	}
	/**
	 * セールスポイントの設定
	 * @param sellingPoint
	 */
	public void setSellingPoint(String sellingPoint) {
		this.sellingPoint = sellingPoint;
	}
	/**
	 * セールスポイントの返却
	 * @return sellingPoint
	 */
	public String getSellingPoint() {
		return sellingPoint;
	}

	/**
	 * ファミリーネームの返却
	 * @return
	 */
	public String getFamilyNm() {
		return familyNm;
	}
	/**
	 * ファミリーネームの設定
	 * @param familyNm
	 */
	public void setFamilyNm(String familyNm) {
		this.familyNm = familyNm;
	}
	/**
	 * ファーストネームの返却
	 * @return
	 */
	public String getFirstNm() {
		return firstNm;
	}
	/**
	 * ファーストネームの設定
	 * @param firstNm
	 */
	public void setFirstNm(String firstNm) {
		this.firstNm = firstNm;
	}

//	/**
//	 * @return nickAnm
//	 */
//	public String getNickNm() {
//		return nickAnm;
//	}
//	/**
//	 * @param nickAnm セットする nickAnm
//	 */
//	public void setNickNm(String nickAnm) {
//		this.nickNm = nickAnm;
//	}
//	/**
//	 * @return imgPhoto
//	 */
//	public byte[] getImgPhoto() {
//		return imgPhoto;
//	}
//	/**
//	 * @param imgPhoto セットする imgPhoto
//	 */
//	public void setImgPhoto(byte[] imgPhoto) {
//		this.imgPhoto = imgPhoto;
//	}
//	/**
//	 * @return nationality
//	 */
//	public String getNationality() {
//		return nationality;
//	}
//	/**
//	 * @param nationality セットする nationality
//	 */
//	public void setNationality(String nationality) {
//		this.nationality = nationality;
//	}
//	/**
//	 * @return nativeLang
//	 */
//	public String getNativeLang() {
//		return nativeLang;
//	}
//	/**
//	 * @param nativeLang セットする nativeLang
//	 */
//	public void setNativeLang(String nativeLang) {
//		this.nativeLang = nativeLang;
//	}
//	/**
//	 * @return country
//	 */
//	public String getCountry() {
//		return country;
//	}
//	/**
//	 * @param country セットする country
//	 */
//	public void setCountry(String country) {
//		this.country = country;
//	}
}
