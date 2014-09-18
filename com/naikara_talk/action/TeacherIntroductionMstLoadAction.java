package com.naikara_talk.action;

import java.util.HashSet;

import org.apache.struts2.interceptor.SessionAware;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.TeacherIntroductionMstLogic;
import com.naikara_talk.model.TeacherCourseListModel;
import com.naikara_talk.model.TeacherIntroductionMstModel;
import com.naikara_talk.service.TeacherIntroductionService;
import com.naikara_talk.sessiondata.TeacherIntorductionData;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * 講師紹介マスタメンテナンス画面を初期表示します。
 * @author nos
 *
 */
public class TeacherIntroductionMstLoadAction extends TeacherIntroductionMstAction implements SessionAware{

	public String requestService() throws NaiException {

		// 戻るボタンの登録
		//setCurrentActionName(NaikaraTalkConstants.TEACHER_INTRODUCTION_MST_LOAD);
        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

    	/** 講師紹介マスタのサービス	*/
    	TeacherIntroductionService introService = new TeacherIntroductionService();
    	TeacherIntroductionMstModel model = introService.load(TeacherIntroductionMstLogic.PROCESS_CD_PROVISIONAL);

		// 講師カテゴリリストを設定すsる
    	setCategoryList(model.getCategoryList());

    	// 講師紹介マスタリストを設定sする
    	setIntroductionMap(model.getintroductionMap());

        return "success";
    }

}
