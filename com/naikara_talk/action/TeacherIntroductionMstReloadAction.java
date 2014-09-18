package com.naikara_talk.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpSession;
import javax.transaction.SystemException;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import sun.misc.FormattedFloatingDecimal.Form;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.TeacherCourseListModel;
import com.naikara_talk.model.TeacherIntroductionMstModel;
import com.naikara_talk.service.TeacherIntroductionService;
import com.naikara_talk.sessiondata.SessionData;
import com.naikara_talk.sessiondata.TeacherIntorductionData;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * 講師紹介マスタメンテナンス画面の情報を
 * セッション情報からリロードからの戻り。
 * @author nos
 *
 */
public class TeacherIntroductionMstReloadAction extends TeacherIntroductionMstAction implements SessionAware{

	public String requestService() throws NaiException {

    	 HttpSession ses = ServletActionContext.getRequest().getSession();
    	 this.request.getAttributeNames();
    	TeacherIntorductionData sessionData = new TeacherIntorductionData();
    	String id = setSelUserId();

    	TeacherIntorductionData introData = (TeacherIntorductionData)SessionDataUtil.getSessionData(TeacherIntorductionData.class.toString());

    	// 次画面用の講師紹介マスタデータ
    	if(StringUtils.isNotEmpty(id)){
    		for( int i = 0; i < introData.getIntroArr().length; i++ ){

    			String[] item = introData.getIntroArr()[i].split(",");
    			if( StringUtils.equalsIgnoreCase(item[0]+item[1], introData.getSelectedIdtem())){
    				introData.getIntroArr()[i] = item[0]+","+item[1]+","+ id;
    				break;
    			}
    		}
    	}

//    	sessionData.setIntroArr(formData);
//    	sessionData.setSelectedIdtem(selectedIdtem);

    	SessionDataUtil.setSessionData(sessionData);

    	/** 講師紹介マスタのサービス	*/
    	TeacherIntroductionService introService = new TeacherIntroductionService();
    	TeacherIntroductionMstModel model = introService.reload(introData.getIntroArr());

		// 講師カテゴリリストを設定する
    	setCategoryList(model.getCategoryList());

    	// 講師紹介マスタリストを設定する
    	setIntroductionMap(model.getintroductionMap());
        ArrayList<String> actionList = null;
        String modoKbun = "";
        if (this.session.containsKey("actionList")) {
            // sessionからactionlistを取得する
        	actionList = (ArrayList<String>) this.session.get("actionList");
            if (actionList != null && actionList.size() > 0) {
                // 前画面のactionを取得する
                modoKbun = actionList.get(actionList.size() - 1);
            }
        }
        if (modoKbun.equals(NaikaraTalkConstants.TEACHER_INTRODUCTION_MST_) ){
            actionList.remove(actionList.size() - 1);
        }

        return SUCCESS;
    }

    private String selUserId;

    public void setSelUserId(String selUserId){
		this.selUserId = selUserId;
    }

    public String setSelUserId(){
		return selUserId;
    }

    private String[] teacherIntroData;

    public void setTeacherIntroData(String[] teacherIntroData){
		this.teacherIntroData = teacherIntroData;
    }

    public String[] getTeacherIntroData(){
		return teacherIntroData;
    }

}
