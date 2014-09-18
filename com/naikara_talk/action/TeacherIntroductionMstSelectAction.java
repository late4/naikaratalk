package com.naikara_talk.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;

import javax.transaction.SystemException;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.SessionAware;

import sun.misc.FormattedFloatingDecimal.Form;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.TeacherCourseListModel;
import com.naikara_talk.model.TeacherIntroductionMstModel;
import com.naikara_talk.service.TeacherIntroductionService;
import com.naikara_talk.sessiondata.TeacherIntorductionData;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * 講師選択画面呼び出し
 * 。
 * @author nos
 *
 */
public class TeacherIntroductionMstSelectAction extends TeacherIntroductionMstAction implements SessionAware{

	public String requestService() throws NaiException {

		// 講師選択一覧からの戻り先を登録
		setCurrentActionName(NaikaraTalkConstants.TEACHER_INTRODUCTION_MST_);

    	String item = getSelectedItem();
    	String[] formData = getTeacherIntroData();

    	TeacherIntorductionData ss = new TeacherIntorductionData();
    	ss.setIntroArr(formData);
    	ss.setSelectedIdtem(item);
    	SessionDataUtil.setSessionData(ss);

//    	setMessage("メッセージ");

        return "success";
    }

    private String selectedItem;

    public void setSelectedItem(String selectedItem){
		this.selectedItem = selectedItem;
    }

    public String getSelectedItem(){
		return selectedItem;
    }

    private String[] teacherIntroData;

    public void setTeacherIntroData(String[] teacherIntroData){
		this.teacherIntroData = teacherIntroData;
    }

    public String[] getTeacherIntroData(){
		return teacherIntroData;
    }

}
