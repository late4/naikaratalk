package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.StudentSelectionListModel;
import com.naikara_talk.service.StudentSelectionListMoveService;
import com.naikara_talk.service.StudentSelectionListSearchService;
import com.naikara_talk.sessiondata.ScreenComInfo;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様(個人)_初期登録。<br>
 * <b>クラス名称　　　:</b>マイページ(お客様_個人)登録選択Actionクラス。<br>
 * <b>クラス概要　　　:</b>マイページ(お客様_個人)登録選択Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/31 TECS 新規作成。
 */
public class StudentSelectionListMoveAction extends StudentSelectionListActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 初期処理<br>
     * <br>
     * 画面項目の初期処理を行う<br>
     * <br>
     * @param なし<br>
     * @return SUCCESS 画面遷移フラグ<br>
     * @exception NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // 書き換える前のログインユーザID
        String beforeId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();

        // 画面のパラメータセット
        setupModel();

        // サービスの初期化
        StudentSelectionListMoveService service = new StudentSelectionListMoveService();

        // サービスの初期化
        StudentSelectionListSearchService searchService = new StudentSelectionListSearchService();

        // 単票をチェック
        int chkResult = service.nextPageRequest(this.model, this.getSelect_rdl());

        try {

            switch (chkResult) {

            case StudentSelectionListMoveService.ERR_NO_SELECT:

                this.addActionMessage(getMessage("EN0015", new String[] { "一覧部の左の選択" }));

                // データ有無をチェック
                chkResult = searchService.checkPreSelect(model);

                switch (chkResult) {

                case StudentSelectionListSearchService.ERR_ZERO_DATA:

                    return SUCCESS;

                case StudentSelectionListSearchService.ERR_MAXOVER_DATA:

                    return SUCCESS;

                }

                // 検索結果一覧
                this.model.setResultList(searchService.selectList(this.model));

                // 一覧から選択された明細データ(jsp)
                this.select_rdl = NaikaraTalkConstants.BRANK;

                return SUCCESS;

            case StudentSelectionListMoveService.ERR_LIST_BACK:

                this.addActionMessage(getMessage("EN0015", new String[] { "一覧部の左の選択" }));

                return SUCCESS;
            }

        } catch (Exception e) {

            throw new NaiException(e);

        }

        try {

            StudentSelectionListModel prmModel = new StudentSelectionListModel();

            prmModel.setStudentId(this.select_rdl);

            StudentSelectionListModel studentInfo = searchService.select(prmModel);

            // ログイン情報の初期化
            SessionUser sessionUser = new SessionUser();

            // ログイン中のユーザID
            sessionUser.setUserId(studentInfo.getStudentId());
            // ログイン中のユーザ表示名
            sessionUser.setUserNm(studentInfo.getStudentNm());
            // ログイン中のユーザロール
            sessionUser.setRole(SessionUser.ROLE_STUDENT);
            // ログイン中の顧客区分(受講者の場合のみ設定される)
            sessionUser.setCustomerKbn(studentInfo.getCustomerKbn());
            // ログイン中の組織IDの連番(組織の場合のみ設定される)
            sessionUser.setOrganizationSeq(NaikaraTalkConstants.BRANK);
            // ログイン中の組織名(組織の場合のみ設定される)
            sessionUser.setOrganizationNm(NaikaraTalkConstants.BRANK);

            // ログイン情報をセッションに保存
            SessionDataUtil.setSessionData(sessionUser);

            // 画面共通情報の初期化
            ScreenComInfo screenComInfo = new ScreenComInfo();

            // ログイン中のユーザID
            screenComInfo.setUserId(studentInfo.getStudentId());
            // 保護者の同意書の入手フラグ
            screenComInfo.setConsentDocumentAcquisitionFlg(studentInfo.getConsentDocumentAcquisitionFlg());
            // ポイント購入済フラグ
            screenComInfo.setPointPurchaseFlg(studentInfo.getPointPurchaseFlg());

            // 画面共通情報をセッションに保存
            SessionDataUtil.setSessionData(screenComInfo);

        } catch (Exception e) {

            throw new NaiException(e);

        }

        // 認証ログを出力
        logC.info(unionCertificationlog(this.getClass().getSimpleName(),
                NaikaraTalkConstants.FRM_ORGANIZATION_SELECTIONLIST, beforeId, "Start"));

        return NEXTPAGE;
    }
}