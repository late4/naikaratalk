package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.OrganizationLoginModel;
import com.naikara_talk.service.OrganizationLoginLoginService;
import com.naikara_talk.sessiondata.ScreenComInfo;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>法人<br>
 * <b>クラス名称　　　:</b>ログイン(組織)登録Actionクラス。<br>
 * <b>クラス概要　　　:</b>組織責任者の登録処理 マイページ(組織) 画面遷移を行う。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/05 TECS 新規作成。
 */
public class OrganizationLoginLoginAction extends OrganizationLoginActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * ログインボタンの処理<br>
     * <br>
     * ログインボタンの処理を行う<br>
     * <br>
     * @param なし<br>
     * @return SUCCESS/NEXTPAGE 画面遷移フラグ<br>
     * @exception NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));
        // Modelクラス設定
        setupModel();
        // Serviceクラス生成
        OrganizationLoginLoginService service = new OrganizationLoginLoginService();

        // 登録前チェック
        int chkResult;
        try {
            chkResult = service.checkPreSelect(model);
            // エラーの場合、メッセージ設定
            switch (chkResult) {
            case OrganizationLoginLoginService.ERR_DATA_MIS:
                this.addActionMessage(getMessage("EB0002", new String[] { "ログインID", "パスワード" }));
                return SUCCESS;
            }

            OrganizationLoginModel returnModel = service.selectList(model);

            // ログイン情報の初期化
            SessionUser sessionUser = new SessionUser();

            // 責任者名(姓)と責任者名(名)
            sessionUser.setUserNm(NaikaraStringUtil.unionName(returnModel.getManagFamilyJnm(),
                    returnModel.getManagFirstJnm()));

            // 組織ID
            sessionUser.setUserId(returnModel.getOrganizationId());

            // 連番
            sessionUser.setOrganizationSeq(String.valueOf(returnModel.getConsSeq()));

            // 組織名
            sessionUser.setOrganizationNm(returnModel.getOrganizationJnm());

            // 種別区分
            sessionUser.setRole(SessionUser.ROLE_ORGANIZATION);

            // ログイン情報をセッションに保存
            SessionDataUtil.setSessionData(sessionUser);

            // 画面共通情報の初期化
            ScreenComInfo screenComInfo = new ScreenComInfo();

            // 利用者ID
            screenComInfo.setUserId(returnModel.getOrganizationId());

            // 画面共通情報をセッションに保存
            SessionDataUtil.setSessionData(screenComInfo);

            // 認証ログを出力
            logC.info(unionCertificationlog(this.getClass().getSimpleName(),
                    NaikaraTalkConstants.FRM_LOGIN_ORGANIZATION, "", "Start"));

        } catch (Exception e) {
            throw new NaiException(e);
        }

        return NEXTPAGE;
    }
}
