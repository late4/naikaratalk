package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.TeacherLoginModel;
import com.naikara_talk.service.TeacherLoginLoginService;
import com.naikara_talk.sessiondata.ScreenComInfo;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>初期処理<br>
 * <b>クラス名称　　　:</b>サービス提供ページ登録Actionクラス。<br>
 * <b>クラス概要　　　:</b>講師の登録処理 講師用個別マイページへ画面遷移を行う。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/02 TECS 新規作成。
 */
public class TeacherLoginLoginAction extends TeacherLoginActionSupport {

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
        TeacherLoginLoginService service = new TeacherLoginLoginService();

        // 登録前チェック
        int chkResult;
        try {
            chkResult = service.checkPreSelect(model);
            // エラーの場合、メッセージ設定
            switch (chkResult) {
            case TeacherLoginLoginService.ERR_DATA_MIS:
                this.addActionMessage(getMessage("ET0002", new String[] { "Login ID", "Password", "ログインID", "パスワード" }));
                return SUCCESS;
            case TeacherLoginLoginService.ERR_DATA_OVERDUE:
                this.addActionMessage(getMessage("ET0003", new String[] {}));
                return SUCCESS;
            case TeacherLoginLoginService.ERR_PER_MIS:
                this.addActionMessage(getMessage("ET0038", new String[] {}));
                return SUCCESS;

            }

            TeacherLoginModel returnModel = service.selectList(model);

            // ログイン情報の初期化
            SessionUser sessionUser = new SessionUser();

            // 名前(姓)と名前(名)
            TeacherLoginModel returnNmModel = service.selectTeacherList(model);
            if (!StringUtils.isEmpty(returnNmModel.getNickAnm())) {
                sessionUser.setUserNm(returnNmModel.getNickAnm());
            } else {
                sessionUser
                        .setUserNm(NaikaraStringUtil.unionName(returnModel.getFamilyJnm(), returnModel.getFirstJnm()));
            }

            // 利用者ID
            sessionUser.setUserId(returnModel.getUserId());

            // 種別区分
            sessionUser.setRole(returnModel.getClassificationKbn());

            // ログイン情報をセッションに保存
            SessionDataUtil.setSessionData(sessionUser);

            // 画面共通情報の初期化
            ScreenComInfo screenComInfo = new ScreenComInfo();

            // 利用者ID
            screenComInfo.setUserId(returnModel.getUserId());

            // 画面共通情報をセッションに保存
            SessionDataUtil.setSessionData(screenComInfo);

            // 認証ログを出力
            logC.info(unionCertificationlog(this.getClass().getSimpleName(), NaikaraTalkConstants.FRM_LOGIN_TEACHER,
                    "", "Start"));

        } catch (Exception e) {
            throw new NaiException(e);
        }

        return NEXTPAGE;
    }
}
