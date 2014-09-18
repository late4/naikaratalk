package com.naikara_talk.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.TeacherIntroductionMstLogic;
import com.naikara_talk.model.TeacherCategoryModel;
import com.naikara_talk.model.TeacherCourseListModel;
import com.naikara_talk.model.TeacherIntroductionModel;
import com.naikara_talk.model.TeacherIntroductionMstModel;
import com.naikara_talk.service.TeacherCourseListService;
import com.naikara_talk.service.TeacherIntroductionService;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * 講師紹介画面を表示します。
 * @author nos
 *
 */
public class TeacherIntroductionAction extends NaikaraActionSupport {

	private static final long serialVersionUID = 1L;

    // Help画面名
    protected String helpPageId = "HelpTeacherIntroduction.html";

    public String getHelpPageId() {
        return helpPageId;
    }

    public void setHelpPageId(String helpPageId) {
        this.helpPageId = helpPageId;
    }

    /**
     * 講師紹介一覧表示のメイン処理
     */
    public String requestService() throws NaiException {

 //       setCurrentActionName(NaikaraTalkConstants.TEACHER_INTORODUCTION);

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

    	/** 講師紹介マスタのサービス	*/
    	TeacherIntroductionService introService = new TeacherIntroductionService();
    	TeacherIntroductionMstModel model = introService.load(TeacherIntroductionMstLogic.PROCESS_CD_EFFECTIVE);

    	// 講師の対応コースのサービス
    	TeacherCourseListService cService = new TeacherCourseListService();
    	HashSet<String> teachers = new HashSet<String>();

		for(List<TeacherIntroductionModel> tList : model.getintroductionMap().values()){
    		for (TeacherIntroductionModel tModel : tList ){
    			teachers.add(tModel.getUserId());
    		}
    	}
		// 講師別コースマップを設定する
		setCourseMap(cService.get((String[])teachers.toArray(new String[teachers.size()])));

		// 講師カテゴリリストを設定する
    	setCategoryList(model.getCategoryList());

    	// 講師紹介マスタリストを設定する
    	setIntroductionMap(model.getintroductionMap());

    	// メッセージ出力処理
        if (!StringUtils.isEmpty(getMessage())) {
            this.addActionMessage(getMessage());
        }

        return "success";
    }

    /** カテゴリリスト */
	private List<TeacherCategoryModel> categoryList;

	/** 講師紹介マスタリスト */
	private Map<String, List<TeacherIntroductionModel>> introductionMap;

	/** 講師別コースのマップ */
	private Map<String,List<TeacherCourseListModel>> courseMap;

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

	private String message;

    public void setMessage(String message) throws UnsupportedEncodingException{
        this.message = URLDecoder.decode(message,"UTF-8");
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

	/**
	 * @return courseMap
	 */
	public Map<String, List<TeacherCourseListModel>> getCourseMap() {
		return courseMap;
	}
	/**
	 * @param courseMap セットする courseMap
	 */
	public void setCourseMap(Map<String, List<TeacherCourseListModel>> courseMap) {
		this.courseMap = courseMap;
	}

}
