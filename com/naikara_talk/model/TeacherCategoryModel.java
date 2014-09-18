package com.naikara_talk.model;

/**
 * 講師カテゴリモデル
 * @author nos
 *
 */
public class TeacherCategoryModel implements Model {

	private String categoryCd;
	private String categoryNm;

	/**
	 * @return categoryCd
	 */
	public String getCategoryCd() {
		return categoryCd;
	}
	/**
	 * @param categoryCd セットする categoryCd
	 */
	public void setCategoryCd(String categoryCd) {
		this.categoryCd = categoryCd;
	}
	/**
	 * @return categoryNm
	 */
	public String getCategoryNm() {
		return categoryNm;
	}
	/**
	 * @param categoryNm セットする categoryNm
	 */
	public void setCategoryNm(String categoryNm) {
		this.categoryNm = categoryNm;
	}
}
