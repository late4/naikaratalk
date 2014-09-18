package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_初期登録<br>
 * <b>クラス名称       :</b>詳細説明画面遷移Actionクラス<br>
 * <b>クラス概要       :</b>詳細説明機能への画面遷移を行う。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/09 TECS 新規作成
 */
public class DetailDescriptionMoveAction extends DetailDescriptionActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 画面遷移前処理。<br>
     * <br>
     * 画面遷移前処理を行う。<br>
     * <br>
     * @param なし<br>
     * @return String<br>
     * @throws NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        SessionUser sessionUserData = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString()));
        // ログイン情報．受講者ID
        this.studentId = sessionUserData.getUserId();
        if (!StringUtils.isEmpty(this.studentId)) {
            this.pageId = NaikaraTalkConstants.RESERVATION_CANCELLATION_COURSE_LIST_LOAD;

        } else {
            this.pageId = NaikaraTalkConstants.ACCOUNT_LOAD;
        }

        // ヘッダの戻るリンク用
        setCurrentActionName(NaikaraTalkConstants.STUDENT_MY_PAGE_LOAD);

        // 画面を返す
        return NEXTPAGE;
    }

    /** 画面ID */
    private String pageId;
    /** 受講者ID */
    private String studentId;
    /**  */
    private String courseCode_txt;

    /**
     * @return pageId
     */
    public String getPageId() {
        return pageId;
    }

    /**
     * @param pageId セットする pageId
     */
    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    /**
     * @return studentId
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * @param studentId セットする studentId
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * @return courseCode_txt
     */
    public String getCourseCode_txt() {
        return courseCode_txt;
    }

    /**
     * @param courseCode_txt セットする courseCode_txt
     */
    public void setCourseCode_txt(String courseCode_txt) {
        this.courseCode_txt = courseCode_txt;
    }

}
