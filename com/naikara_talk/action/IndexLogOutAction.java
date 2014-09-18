package com.naikara_talk.action;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.SessionDataUtil;

/**
 * ログアウトしのアクション
 * @author nos
 *
 */
public class IndexLogOutAction extends NaikaraActionSupport {

	/**
	 *サービス呼び出し
	 *
	 *@return
	 */
	@Override
	protected String requestService() throws NaiException {

        log.info(NaikaraStringUtil.unionProcesslog("Start"));

    	SessionUser SessionUserData = ((SessionUser)SessionDataUtil.getSessionData(SessionUser.class.toString()));
    	if (SessionUserData != null) {
    		SessionDataUtil.clearSessionData(SessionUser.class.toString());
//        	SessionDataUtil.setSessionData(SessionUserData);
    	}
		return "success";
	}
}
