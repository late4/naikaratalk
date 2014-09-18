package com.naikara_talk.action;

import java.net.URLEncoder;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.AddBookMarkModel;
import com.naikara_talk.service.AddBookMarkService;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

public class AddBookMarkAction extends NaikaraActionSupport {

	@Override
	public String requestService() throws NaiException {

		AddBookMarkService service = new AddBookMarkService();

		// セッション切れまたはログインなし
		if((SessionUser)SessionDataUtil.getSessionData(SessionUser.class.toString()) == null ){
			return notLogin();
		}

		String userId = ((SessionUser)SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();

		AddBookMarkModel model = service.add(userId, selectedTeacherId);

		try{
			if (model.getResult() == 0){
				this.message = getMessage("IC0010", new String[] { "ブックマーク" ,""});
			} else if(model.getResult() == 1) {
				this.message = getMessage("EC0074", new String[] {});
			} else if(model.getResult() == 2) {
				this.message = getMessage("EC0060", new String[] { "50" });
			}

            this.message = URLEncoder.encode(message,"UTF-8");

		} catch (Exception e) {
			throw new NaiException(e);
		}

		return SUCCESS;
	}

	private String message;
	private String selectedTeacherId;

	public void setMessage(String message) {
	    this.message = message;
	}

	public String getMessage() {
	    return message;
	}

	public String getSelectedTeacherId() {
		return selectedTeacherId;
	}

	public void setSelectedTeacherId(String selectedTeacherId) {
		this.selectedTeacherId = selectedTeacherId;
	}

}
