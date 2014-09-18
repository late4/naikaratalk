package com.naikara_talk.action;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.TeacherIntroductionMstLogic;
import com.naikara_talk.model.TeacherCategoryModel;
import com.naikara_talk.model.TeacherCourseListModel;
import com.naikara_talk.model.TeacherIntroductionModel;
import com.naikara_talk.model.TeacherIntroductionMstModel;
import com.naikara_talk.service.TeacherCourseListService;
import com.naikara_talk.service.TeacherIntroductionService;
import com.naikara_talk.sessiondata.SessionData;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * TOP画面ヘッダ２部分を表示します。
 * @author mira
 *
 */
public class IndexHeder2LoadAction extends NaikaraActionSupport {

	private static final long serialVersionUID = 1L;

    private boolean loginFlg = false;

    /**
     * TOP画面ヘッダ２部分表示のメイン処理
     */
    public String requestService() throws NaiException {

    	// 戻るボタンの設定　しかし現状はhtmlのため、設定不能
        //setCurrentActionName(NaikaraTalkConstants.TEACHER_INTORODUCTION);

    	// 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));
    	SessionUser SessionUserData = ((SessionUser)SessionDataUtil.getSessionData(SessionUser.class.toString()));
    	if (SessionUserData != null) {

            loginFlg = true;
    	}
        // ログインしていない場合は、設定
    	else  {
    		loginFlg = false;
    	}

        return "success";
    }
    public void setLoginFlg(boolean loginFlg){
    	this.loginFlg = loginFlg;
    }
    public boolean getLoginFlg() {
    	return loginFlg;
    }
}
