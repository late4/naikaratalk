package com.naikara_talk.dto;

import java.sql.Blob;

/**
 * 講師マスタキャッシュDTO。
 * @author nos
 *
 */
public class TeacherMstCacheDto extends AbstractDto{

	/** 講師ユーザID */
	private String userId;
	/** ニックネーム */
	private String nickAnm;
	/** 国籍コード */
	private String nationalityCd;
	/** 国籍 */
	private String nationality;
	/** 母国語コード */
	private String nativeLangCd;
	/** 母国語 */
	private String nativeLang;
	/** 滞在国コード */
	private String countryCd;
	/** 滞在国 */
	private String country;

	private String areaNoCd;
	private String contractDt;
	private String contractStartDt;
	private String contractEndDt;
	private String sellingPoint;
	private String selfRecommendation;
	/** 講師画像データ */
	private byte[] imgPhoto;

	private String firstNm;
	private String familyNm;

	/**
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId セットする userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
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
	 * @return nationalityCd
	 */
	public String getNationalityCd() {
		return nationalityCd;
	}
	/**
	 * @param nationalityCd セットする nationalityCd
	 */
	public void setNationalityCd(String nationalityCd) {
		this.nationalityCd = nationalityCd;
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
	 * @return nativeLangCd
	 */
	public String getNativeLangCd() {
		return nativeLangCd;
	}
	/**
	 * @param nativeLangCd セットする nativeLangCd
	 */
	public void setNativeLangCd(String nativeLangCd) {
		this.nativeLangCd = nativeLangCd;
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
	 * @return countryCd
	 */
	public String getCountryCd() {
		return countryCd;
	}
	/**
	 * @param countryCd セットする countryCd
	 */
	public void setCountryCd(String countryCd) {
		this.countryCd = countryCd;
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
	 * @return areaNoCd
	 */
	public String getAreaNo() {
		return areaNoCd;
	}
	/**
	 * @param areaNoCd セットする areaNoCd
	 */
	public void setAreaNoCd(String areaNoCd) {
		this.areaNoCd = areaNoCd;
	}
	/**
	 * @return contractDt
	 */
	public String getContractDt() {
		return contractDt;
	}
	/**
	 * @param contractDt セットする contractDt
	 */
	public void setContractDt(String contractDt) {
		this.contractDt = contractDt;
	}
	/**
	 * @return contractStartDt
	 */
	public String getContractStartDt() {
		return contractStartDt;
	}
	/**
	 * @param contractStartDt セットする contractStartDt
	 */
	public void setContractStartDt(String contractStartDt) {
		this.contractStartDt = contractStartDt;
	}
	/**
	 * @return contractEndDt
	 */
	public String getContractEndDt() {
		return contractEndDt;
	}
	/**
	 * @param contractEndDt セットする contractEndDt
	 */
	public void setContractEndDt(String contractEndDt) {
		this.contractEndDt = contractEndDt;
	}
	/**
	 * @return sellingPoint
	 */
	public String getSellingPoint() {
		return sellingPoint;
	}
	/**
	 * @param sellingPoint セットする sellingPoint
	 */
	public void setSellingPoint(String sellingPoint) {
		this.sellingPoint = sellingPoint;
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
	 * @return imgPhoto
	 */
	public byte[] getImgPhoto() {
		return imgPhoto;
	}
	/**
	 * @param imgPhoto セットする imgPhoto
	 */
	public void setImgPhoto(byte[] imgPhoto) {
		this.imgPhoto = imgPhoto;
	}

	public String getFirstNm() {
		return firstNm;
	}
	public void setFirstNm(String firstNm) {
		this.firstNm = firstNm;
	}
	public String getFamilyNm() {
		return familyNm;
	}
	public void setFamilyNm(String familyNm) {
		this.familyNm = familyNm;
	}


}
