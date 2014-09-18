package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraStringUtil;

public class LessonLaunchStudentLoadAction extends NaikaraActionSupport {

    private static final long serialVersionUID = 1L;

    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        return SUCCESS;

    }

}
