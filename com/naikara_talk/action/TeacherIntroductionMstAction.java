package com.naikara_talk.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.naikara_talk.model.TeacherCategoryModel;
import com.naikara_talk.model.TeacherIntroductionModel;

public abstract class TeacherIntroductionMstAction extends NaikaraActionSupport {

	private static final long serialVersionUID = 1L;
	/** カテゴリリスト */
	private List<TeacherCategoryModel> categoryList;
	/** 講師紹介マスタリスト */
	private Map<String, List<TeacherIntroductionModel>> introductionMap;
	protected String message;

    // Help画面名
    protected String helpPageId = "HelpTeacherIntroductionMstMent.html";

    public String getHelpPageId() {
        return helpPageId;
    }

    public void setHelpPageId(String helpPageId) {
        this.helpPageId = helpPageId;
    }


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

	public TeacherIntroductionMstAction() {
		super();
	}

	public void setMessage(String message) {
	    this.message = message;
	}

	public String getMessage() {
	    return message;
	}

	/**
	 * @return introductionMstList
	 */
	public Map<String, List<TeacherIntroductionModel>> getIntroductionMap() {
		return introductionMap;
	}

	/**
	 * @param introductionMstList セットする introductionMap
	 */
	public void setIntroductionMap(Map<String, List<TeacherIntroductionModel>> map) {
		this.introductionMap = map;
	}

}