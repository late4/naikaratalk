package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.SchoolLoginModel;
import com.naikara_talk.service.SchoolLoginLoginService;
import com.naikara_talk.sessiondata.ScreenComInfo;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>ログイン・メニュー。<br>
 * <b>クラス名称　　　:</b>会社側のログイン処理ログインActionクラス。<br>
 * <b>クラス概要　　　:</b>会社側のログイン処理ログインAction。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/02 TECS 新規作成。
 */
public class SchoolLoginLoginAction extends SchoolLoginActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * ログインボタンの処理<br>
     * <br>
     * ログインボタンの処理を行う<br>
     * <br>
     * @param なし<br>
     * @return SUCCESS/INPUT 画面遷移フラグ<br>
     * @exception NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // 画面のパラメータセット
        setupModel();

        // サービスの初期化
        SchoolLoginLoginService service = new SchoolLoginLoginService();

        try {
            // 検索前チェック処理
            int chkResult = service.checkPreSelect(this.model);

            // 検索結果判定
            switch (chkResult) {
            case SchoolLoginLoginService.ERR_DATA_MIS:
                this.addActionMessage(getMessage("EN0002", new String[] { "ログインID", "パスワード" }));
                return SUCCESS;
            case SchoolLoginLoginService.ERR_DATA_OVERDUE:
                this.addActionMessage(getMessage("EN0003", new String[] {}));
                return SUCCESS;
            case SchoolLoginLoginService.ERR_PER_MIS:
                this.addActionMessage(getMessage("EN0038", new String[] {}));
                return SUCCESS;

            }
            SchoolLoginModel returnmodel = service.selectList(model);
            // ログイン情報の初期化
            SessionUser sessionUser = new SessionUser();
            // 利用者ID
            sessionUser.setUserId(returnmodel.getUserId());
            // 名前(姓)と名前(名)
            sessionUser.setUserNm(NaikaraStringUtil.unionName(returnmodel.getFamilyJnm(), returnmodel.getFirstJnm()));
            // 種別区分
            sessionUser.setRole(returnmodel.getClassificationKbn());
            // ログイン情報をセッションに保存
            SessionDataUtil.setSessionData(sessionUser);

            // 画面共通情報の初期化
            ScreenComInfo screenComInfo = new ScreenComInfo();
            // 利用者ID
            screenComInfo.setUserId(returnmodel.getUserId());
            // 画面共通情報をセッションに保存
            SessionDataUtil.setSessionData(screenComInfo);

            // 認証ログを出力
            logC.info(unionCertificationlog(this.getClass().getSimpleName(), NaikaraTalkConstants.FRM_LOGIN_SCHOOL, "",
                    "Start"));

        } catch (Exception e) {
            throw new NaiException(e);
        }
        return NEXTPAGE;

    }
}