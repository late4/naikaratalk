package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.StudentLoginModel;
import com.naikara_talk.service.StudentLoginLoginService;
import com.naikara_talk.sessiondata.ScreenComInfo;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様（個人）<br>
 * <b>クラス名称　　　:</b>ログイン(お客様（個人）)登録Actionクラス。<br>
 * <b>クラス概要　　　:</b>お客様（個人）の登録処理 TOPページ画面遷移を行う。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/25 TECS 新規作成。
 */
public class StudentLoginLoginAction extends StudentLoginActionSupport {

    private static final long serialVersionUID = 1L;

    /** 排他エラー */
    private static final int ERR_CHECK_EXCLUSION = -2;

    /** 更新処理(正常) */
    private static final int UPDATE_OK = 5;

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
        StudentLoginLoginService service = new StudentLoginLoginService();

        // 登録前チェック
        int chkResult;
        try {
            chkResult = service.checkPreSelect(model);
            // エラーの場合、メッセージ設定
            switch (chkResult) {
            case StudentLoginLoginService.ERR_DATA_MIS:
                this.addActionMessage(getMessage("EC0002", new String[] { "ログインID", "パスワード" }));
                return SUCCESS;

            case StudentLoginLoginService.ERR_DATA_NG:
                this.addActionMessage(getMessage("EC0003", new String[] {}));
                return SUCCESS;
            }

            StudentLoginModel returnModel = null;
            try {
                // 受講者情報項目を取得する
                returnModel = service.selectList(model);

                // エラーが発生しない場合は、ログイン情報項目の更新処理を行う
                int rtn = this.update(returnModel);
                switch (rtn) {
                case ERR_CHECK_EXCLUSION:
                    // 排他エラー
                    return SUCCESS;
                }

            } catch (Exception e) {
                throw new NaiException(e);
            }

            // ログイン情報の初期化
            SessionUser sessionUser = new SessionUser();

            // 受講者ID
            sessionUser.setUserId(returnModel.getStudentId());

            // ユーザ表示名
            if (returnModel.getNickNm() == null || "".equals(returnModel.getNickNm())) {
                sessionUser.setUserNm(NaikaraStringUtil.unionName(returnModel.getFamilyJnm(), returnModel.getFirstJnm()));
            } else {
                sessionUser.setUserNm(returnModel.getNickNm());
            }

            // ロール
            sessionUser.setRole(SessionUser.ROLE_STUDENT);

            // 顧客区分
            sessionUser.setCustomerKbn(returnModel.getCustomerKbn());

            // ログイン情報をセッションに保存
            SessionDataUtil.setSessionData(sessionUser);

            // 画面共通情報の初期化
            ScreenComInfo screenComInfo = new ScreenComInfo();

            // 利用者ID
            screenComInfo.setUserId(returnModel.getStudentId());

            // 保護者の同意書の入手フラグ
            screenComInfo.setConsentDocumentAcquisitionFlg(returnModel.getConsentDocumentAcquisitionFlg());

            // ポイント購入済フラグ
            screenComInfo.setPointPurchaseFlg(returnModel.getPointPurchaseFlg());

            // 画面共通情報をセッションに保存
            SessionDataUtil.setSessionData(screenComInfo);

            // 認証ログを出力
            logC.info(unionCertificationlog(this.getClass().getSimpleName(), NaikaraTalkConstants.FRM_LOGIN_STUDENT,
                    "", "Start"));

        } catch (Exception e) {
            throw new NaiException(e);
        }

        return NEXTPAGE;
    }

    /**
     * 更新処理。<br>
     * <br>
     * 更新処理。<br>
     * <br>
     * @param StudentMstDto <br>
     * @return ERR_CHECK_EXCLUSION or UPDATE_OK <br>
     * @exception Exception
     */
    private int update(StudentLoginModel model) throws Exception {

        // サービス生成
        StudentLoginLoginService service = new StudentLoginLoginService();

        // 画面のデータをmodelにセットする
        setupModel(model);

        // 更新処理のサービス呼び出し
        int cnt = service.update(this.model);

        if (cnt == NaikaraTalkConstants.RETURN_CD_ERR_NO_UPD) {
            // 排他エラーメッセージの設定
            this.addActionMessage(getMessage("ES0014", new String[] { "", "" }));
            return ERR_CHECK_EXCLUSION;
        }
        // 一覧画面を戻る
        return UPDATE_OK;

    }

    /**
     * 画面のパラメータをモデルにセット。<br>
     * <br>
     * 画面のパラメータをモデルにセット。<br>
     * <br>
     * @param StudentMstDto <br>
     * @return なし<br>
     * @exception なし
     */
    public void setupModel(StudentLoginModel model) {
        // レコードバージョン番号
        this.model.setRecordVerNo(model.getRecordVerNo());
    }
}
