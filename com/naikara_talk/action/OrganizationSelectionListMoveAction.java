package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.OrganizationSelectionListModel;
import com.naikara_talk.service.OrganizationSelectionListMoveService;
import com.naikara_talk.service.OrganizationSelectionListSearchService;
import com.naikara_talk.sessiondata.ScreenComInfo;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>法人_初期処理<br>
 * <b>クラス名称       :</b>組織マイページ(組織 選択)登録選択Action。<br>
 * <b>クラス概要       :</b>組織マイページ(組織 選択)。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/01 TECS 新規作成
 */
public class OrganizationSelectionListMoveAction extends OrganizationSelectionListActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 登録/選択ボタンの処理。<br>
     * <br>
     * 登録/選択ボタンの処理。<br>
     * <br>
     * @param なし<br>
     * @return String SUCCESS or NEXTPAGE<br>
     * @exception NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // 書き換える前のログインユーザID
        String beforeId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();

        // Modelクラス設定
        setupModel();
        // Serviceクラス生成
        OrganizationSelectionListMoveService service = new OrganizationSelectionListMoveService();
        OrganizationSelectionListSearchService searchService = new OrganizationSelectionListSearchService();

        int chkResult = service.nextPageRequest(this.model, this.getSelect_rdl());

        // エラーの場合、メッセージ設定
        try {
            switch (chkResult) {
            case OrganizationSelectionListMoveService.ERR_NO_SELECT:

                this.addActionMessage(getMessage("EN0015", new String[] { "一覧部の左の選択" }));

                // 検索前チェック
                chkResult = searchService.checkPreSelect(model);

                // エラーの場合、メッセージ設定
                switch (chkResult) {
                case OrganizationSelectionListSearchService.ERR_ZERO_DATA:
                    return SUCCESS;
                case OrganizationSelectionListSearchService.ERR_MAXOVER_DATA:
                    return SUCCESS;
                }
                // 表示データの取得
                this.model.setResultList(searchService.selectList(this.model));
                // 選択したラジオボタンをクリアする
                this.select_rdl = NaikaraTalkConstants.BRANK;

                return SUCCESS;

            case OrganizationSelectionListMoveService.ERR_LIST_BACK:
                this.addActionMessage(getMessage("EN0015", new String[] { "一覧部の左の選択" }));
                return SUCCESS;
            }

        } catch (Exception e) {
            throw new NaiException(e);
        }

        try {

            OrganizationSelectionListModel prmModel = new OrganizationSelectionListModel();

            prmModel.setOrganizationId(this.select_rdl);

            OrganizationSelectionListModel organizationInfo = service.select(prmModel);

            // ログイン情報の初期化
            SessionUser sessionUser = new SessionUser();

            // ログイン中のユーザID
            sessionUser.setUserId(organizationInfo.getOrganizationId());
            // ログイン中のユーザ表示名
            sessionUser.setUserNm(organizationInfo.getManagJnm());
            // ログイン中のユーザロール
            sessionUser.setRole(SessionUser.ROLE_ORGANIZATION);
            // ログイン中の組織マスタ．連番
            sessionUser.setOrganizationSeq(organizationInfo.getConsSeq());
            // ログイン中の組織マスタ．組織名
            sessionUser.setOrganizationNm(organizationInfo.getOrganizationNm());
            // ログイン情報をセッションに保存
            SessionDataUtil.setSessionData(sessionUser);
            // 画面共通情報の初期化
            ScreenComInfo screenComInfo = new ScreenComInfo();
            // 組織ID
            screenComInfo.setUserId(organizationInfo.getOrganizationId());
            // 画面共通情報をセッションに保存
            SessionDataUtil.setSessionData(screenComInfo);

        } catch (Exception e) {

            throw new NaiException(e);

        }

        // 認証ログを出力
        logC.info(unionCertificationlog(this.getClass().getSimpleName(),
                NaikaraTalkConstants.FRM_STUDENT_SELECTIONLIST, beforeId, "Start"));

        // 詳細画面遷移
        return NEXTPAGE;
    }

}
