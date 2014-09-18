package com.naikara_talk.action;

import java.util.ArrayList;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.TeacherSelectionListSearchService;
import com.naikara_talk.sessiondata.ScreenComInfo;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>一覧_講師選択ページ。<br>
 * <b>クラス名称       :</b>一覧_講師選択ページ遷移Actionクラス。<br>
 * <b>クラス概要       :</b>一覧_講師選択遷移を行う<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/09 TECS 新規作成
 */
public class TeacherSelectionListMoveAction extends TeacherSelectionListActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 登録/選択ボタンの処理。<br>
     * <br>
     * @param なし
     * @return String NEXTPAGE
     * @throws NaiException
     */
    @SuppressWarnings("unchecked")
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // Modelクラス設定
        setupModel();

        // エラーの場合、メッセージ設定
        try {
            if (this.select_rdl == null) {
                this.addActionMessage(getMessage("EN0015", new String[] { "一覧部の左の選択" }));
                // 表示データを取得する
                TeacherSelectionListSearchService service = new TeacherSelectionListSearchService();

                // 画面のデータをmodelにセットする
                setupModel();

                // ヘッダ部の戻るボタン押下の場合は値の入れ替え処理
                try {

                    // 表示データの取得
                    this.model = service.select(this.model);
                    this.hasSearchFlg = Boolean.toString(Boolean.TRUE);

                } catch (Exception e1) {
                    throw new NaiException(e1);
                }
                return SUCCESS;
            }
            ArrayList<String> actionList = null;
            if (this.session.containsKey("actionList")) {
                // sessionからactionlistを取得する
                actionList = (ArrayList<String>) this.session.get("actionList");
                if (actionList != null && actionList.size() > 0) {
                    // 前画面のactionを取得する
                    this.modoKbun = actionList.get(actionList.size() - 1);
                }
            }
            if (this.modoKbun.equals(NaikaraTalkConstants.SYSTEM_MENU_LOAD)) {
                // userInfo を選択された講師の情報で全て更新する
                this.modelToSessionUser();
                this.actionName = NaikaraTalkConstants.TEACHER_MY_PAGE_LOAD;
            } else {
                this.userId = (this.select_rdl.substring(1, this.select_rdl.length() - 1)).split(",")[0];
                this.actionName = NaikaraTalkConstants.TEACHER_INTRODUCTION_MST_;
            }

        } catch (Exception e) {
            throw new NaiException(e);
        }

        // 詳細画面遷移
        return NEXTPAGE;
    }

    /**
     * userInfo を選択された講師の情報で全て更新する。<br>
     * <br>
     * @throws Exception
     */
    private void modelToSessionUser() throws Exception {

        String[] values = this.select_rdl.substring(1, this.select_rdl.length() - 1)
                .replaceAll(NaikaraTalkConstants.HALF_SPACE, NaikaraTalkConstants.BRANK)
                .split(String.valueOf(NaikaraTalkConstants.COMMA));
        // 書き換える前のログインユーザID
        String beforeId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();

        // ログイン情報の更新
        SessionUser sessionUser = new SessionUser();
        // 利用者ID
        sessionUser.setUserId(values[0]);
        // 講師名
        sessionUser.setUserNm(NaikaraStringUtil.unionName(values[1], values[2]));
        // 種別区分
        sessionUser.setRole(NaikaraTalkConstants.AUTHORITY_T);

        // 顧客区分
        sessionUser.setCustomerKbn("");
        // 組織名
        sessionUser.setOrganizationNm("");
        // 組織IDの連番
        sessionUser.setOrganizationSeq("");
        // ログイン情報をセッションに保存
        SessionDataUtil.setSessionData(sessionUser);

        // 画面共通情報の初期化
        ScreenComInfo screenComInfo = new ScreenComInfo();
        // 利用者ID
        screenComInfo.setUserId(this.select_rdl);
        // 保護者の同意書の入手フラグ
        screenComInfo.setConsentDocumentAcquisitionFlg("");
        // ポイント購入済フラグ
        screenComInfo.setPointPurchaseFlg("");
        // 画面共通情報をセッションに保存
        SessionDataUtil.setSessionData(screenComInfo);

        // 認証ログを出力
        logC.info(unionCertificationlog(this.getClass().getSimpleName(),
                NaikaraTalkConstants.FRM_TEACHER_SELECTIONLIST, beforeId, "Start"));

    }
}
