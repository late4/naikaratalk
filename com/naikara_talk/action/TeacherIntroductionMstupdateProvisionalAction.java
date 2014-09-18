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
import com.naikara_talk.util.SessionDataUtil;

/**
 * 講師紹介マスタ本登録処理
 *
 * @author nos
 *
 */
public class TeacherIntroductionMstupdateProvisionalAction extends TeacherIntroductionMstAction implements SessionAware{

	public String requestService() throws NaiException {

    	String item = getSelectedItem();
    	String[] formData = getTeacherIntroData();

    	TeacherIntorductionData ss = new TeacherIntorductionData();
    	ss.setIntroArr(formData);
    	ss.setSelectedIdtem(item);
    	SessionDataUtil.setSessionData(ss);

    	/** 講師紹介マスタのサービス	*/
    	TeacherIntroductionService introService = new TeacherIntroductionService();

    	/** 更新処理 */
    	TeacherIntroductionMstModel model = introService.updateProvisional(introService.reload(formData));

		// 講師カテゴリリストを設定する
    	setCategoryList(model.getCategoryList());

    	// 講師紹介マスタリストを設定する
    	setIntroductionMap(model.getintroductionMap());

		try{
			this.message = getMessage("IC0010", new String[] { "講師紹介マスタ","" });
		} catch (Exception e) {
			throw new NaiException(e);
		}

		// メッセージ出力処理
        if (!StringUtils.isEmpty(getMessage())) {
            this.addActionMessage(getMessage());
        }

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
