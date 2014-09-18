package com.naikara_talk.model;

import java.util.List;
import java.util.Map;

public class TeacherIntroductionMstModel implements Model {

	/** 講師カテゴリのリスト	*/
	private List<TeacherCategoryModel> categoryList;
	/** 講師紹介データのリスト	*/
	private Map<String, List<TeacherIntroductionModel>> introductionMap;

	// 画面のイベント
	private int iventId;

	/**
	 * @return categoryList
	 */
	public List<TeacherCategoryModel> getCategoryList() {
		return categoryList;
	}
	/**
	 * @param categoryList セットする categoryList
	 */
	public void setCategoryList(List<TeacherCategoryModel> categoryList) {
		this.categoryList = categoryList;
	}
	/**
	 * @return teacherList
	 */
	public Map<String, List<TeacherIntroductionModel>> getintroductionMap() {
		return introductionMap;
	}
	/**
	 * @param teacherIntroductionMap セットする teacherList
	 */
	public void setintroductionMap(Map<String, List<TeacherIntroductionModel>> teacherIntroductionMap) {
		this.introductionMap = teacherIntroductionMap;
	}
	/**
	 * @return iventId
	 */
	public int getIventId() {
		return iventId;
	}
	/**
	 * @param iventId セットする iventId
	 */
	public void setIventId(int iventId) {
		this.iventId = iventId;
	}

}
