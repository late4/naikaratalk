package com.naikara_talk.dto;

import java.sql.Date;

public class TeacherIntroductionMstDto extends AbstractDto{

	private String processCd;
	private String userCategoryCd;
	private String userCategorysubCd;
	private String userId;
	private Date insertDttm;
	private String insertCd;
	private Date updateDttm;
	private String updateCd;
	private int returnCode;
	/**
	 * @return processCd
	 */
	public String getProcessCd() {
		return processCd;
	}
	/**
	 * @param processCd セットする processCd
	 */
	public void setProcessCd(String processCd) {
		this.processCd = processCd;
	}
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
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return insertDttm
	 */
	public Date getInsertDttm() {
		return insertDttm;
	}
	/**
	 * @param insertDttm セットする insertDttm
	 */
	public void setInsertDttm(Date insertDttm) {
		this.insertDttm = insertDttm;
	}
	/**
	 * @return insertCd
	 */
	public String getInsertCd() {
		return insertCd;
	}
	/**
	 * @param insertCd セットする insertCd
	 */
	public void setInsertCd(String insertCd) {
		this.insertCd = insertCd;
	}
	/**
	 * @return updateDttm
	 */
	public Date getUpdateDttm() {
		return updateDttm;
	}
	/**
	 * @param updateDttm セットする updateDttm
	 */
	public void setUpdateDttm(Date updateDttm) {
		this.updateDttm = updateDttm;
	}
	/**
	 * @return updateCd
	 */
	public String getUpdateCd() {
		return updateCd;
	}
	/**
	 * @param updateCd セットする updateCd
	 */
	public void setUpdateCd(String updateCd) {
		this.updateCd = updateCd;
	}

	/**
	 * リターンコードの取得
	 * @return
	 */
	public int getReturnCode() {
		return returnCode;
	}

	/**
	 * リターンコードの設定
	 * @param returnCode
	 */
	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}


}
