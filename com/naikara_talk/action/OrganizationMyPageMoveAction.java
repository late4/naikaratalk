package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>法人_初期処理<br>
 * <b>クラス名称       :</b>組織マイページ画面遷移Actionクラス<br>
 * <b>クラス概要       :</b>組織マイページ機能への画面遷移を行う。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/01 TECS 新規作成
 */
public class OrganizationMyPageMoveAction extends OrganizationMyPageActionSupport {

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

        // ヘッダの戻るリンク用
        setCurrentActionName(NaikaraTalkConstants.ORGANIZATION_MY_PAGE_LOAD);

        if (StringUtils.equals(NaikaraTalkConstants.ORGANIZATION_STUDENT_MST_LIST_LOAD, pageId)) {
            SessionUser sessionUserData = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString()));
            this.organizationId = sessionUserData.getUserId();
            this.organizationJnm = sessionUserData.getOrganizationNm();

        }
        if (StringUtils.equals(NaikaraTalkConstants.ORGANIZATION_POINT_ASSIGNMENT_LIST_LOAD, pageId)) {
            SessionUser sessionUserData = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString()));
            this.organizationId = sessionUserData.getUserId();

        }
        if (StringUtils.equals(NaikaraTalkConstants.ORGANIZATION_USAGE_SITUATION_LIST_LOAD, pageId)) {
            SessionUser sessionUserData = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString()));
            this.organizationId = sessionUserData.getUserId();

        }
        // 画面を返す
        return NEXTPAGE;
    }

    /** 画面ID */
    private String pageId;
    /** 組織ID */
    private String organizationId;
    /** 組織名 */
    private String organizationJnm;

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
     * @return organizationId
     */
    public String getOrganizationId() {
        return organizationId;
    }

    /**
     * @param organizationId セットする organizationId
     */
    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    /**
     * @return organizationJnm
     */
    public String getOrganizationJnm() {
        return organizationJnm;
    }

    /**
     * @param organizationJnm セットする organizationJnm
     */
    public void setOrganizationJnm(String organizationJnm) {
        this.organizationJnm = organizationJnm;
    }

}
