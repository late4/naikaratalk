package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.UserMstListModel;
import com.naikara_talk.service.UserMstUpdateService;
import com.naikara_talk.sessiondata.SessionUserMst;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守。<br>
 * <b>クラス名称　　　:</b>利用者マスタメンテナンス登録更新Actionクラス。<br>
 * <b>クラス概要　　　:</b>利用者マスタメンテナンス登録更新Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/04 TECS 新規作成。
 */
public class UserMstUpdateAction extends UserMstActionSupport {

    private static final long serialVersionUID = 1L;

    /** メッセージ用使用テーブル名 */
    private static final String USER_MST_TBL_JNM = "利用者マスタ";

    /** 入力チェックエラー */
    private static final int ERR_CHECK_NG = -1;

    /** 排他エラー */
    private static final int ERR_CHECK_EXCLUSION = -2;

    /** 存在チェックエラー */
    private static final int ERR_CHECK_EXISTS = -3;

    /** 追加処理(正常) */
    private static final int INSERT_OK = 1;

    /** 更新処理(正常)1 */
    private static final int UPDATE_OK_1 = 2;

    /** 更新処理(正常)2 */
    private static final int UPDATE_OK_2 = 3;

    /** 画面遷移用1 */
    private static final String NEXT_PAGE_1 = "next1";

    /** 画面遷移用2 */
    private static final String NEXT_PAGE_2 = "next2";

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

        // 画面遷移先
        String transitionScreen = null;

        try {

            initRadio();

        } catch (Exception e) {

            throw new NaiException(e);

        }

        // 画面のパラメータセット
        setupModel();

        // 処理区分＝新規の場合('0':'新規','1':'修正','2':'照会')
        if (StringUtils.equals(UserMstListModel.PROS_KBN_ADD, this.processKbn_rdl)) {

            try {

                // 新規処理を実行
                int rtn = this.insert();

                switch (rtn) {

                case ERR_CHECK_NG:

                    // 入力チェックエラー
                    transitionScreen = SUCCESS;

                    break;

                case INSERT_OK:

                    // 追加処理(正常)
                    transitionScreen = NEXT_PAGE_1;

                    break;

                }
            } catch (Exception e) {

                throw new NaiException(e);

            }
        }

        // 処理区分＝修正の場合('0':'新規','1':'修正','2':'照会')
        if (StringUtils.equals(UserMstListModel.PROS_KBN_UPD, this.processKbn_rdl)) {

            try {

                // 更新処理を実行
                int rtn = this.update();

                switch (rtn) {

                case ERR_CHECK_NG:

                    // 入力チェックエラー
                    transitionScreen = SUCCESS;

                    break;

                case ERR_CHECK_EXCLUSION:

                    // 排他エラー
                    transitionScreen = INPUT;

                    break;

                case ERR_CHECK_EXISTS:

                    // 存在チェックエラー
                    transitionScreen = INPUT;

                    break;

                case UPDATE_OK_1:

                    // 更新処理(正常)1
                    transitionScreen = NEXT_PAGE_1;

                    break;

                case UPDATE_OK_2:

                    // 更新処理(正常)2
                    transitionScreen = NEXT_PAGE_2;

                    break;

                }

            } catch (Exception e) {

                throw new NaiException(e);

            }
        }

        if (transitionScreen == NEXT_PAGE_1) {

            // 正常の場合、MoveActionで登録した画面遷移を削除
            try {

                removeLatestActionList();

            } catch (Exception e) {

                throw new NaiException(e);
            }

            // 戻る用のsession情報の初期化
            // sessionSessionUserMstのクリア
            SessionDataUtil.clearSessionData(SessionUserMst.class.toString());
        }

        return transitionScreen;
    }

    /**
     * 登録処理<br>
     * <br>
     * 登録処理を行う<br>
     * <br>
     * @param なし<br>
     * @return int 登録フラグ<br>
     * @exception Exception
     */
    private int insert() throws Exception {

        // 関連チェック
        if (errorCheck()) {

            return ERR_CHECK_NG;
        }

        // サービスの初期化
        UserMstUpdateService service = new UserMstUpdateService();

        // 画面のパラメータセット
        setupModel();

        // 新規処理のサービス呼び出し
        service.insert(this.model);

        StringBuffer sb = new StringBuffer();
        sb.append("利用者IDは").append(this.model.getUserId_lbl()).append("です。");

        // 登録完了メッセージの設定
        this.message = getMessage("IN0010", new String[] { USER_MST_TBL_JNM, sb.toString() });

        return INSERT_OK;
    }

    /**
     * 更新処理<br>
     * <br>
     * 更新処理を行う<br>
     * <br>
     * @param なし<br>
     * @return int 更新フラグ<br>
     * @exception Exception
     */
    private int update() throws Exception {

        // 関連チェック
        if (errorCheck()) {

            return ERR_CHECK_NG;
        }

        // サービスの初期化
        UserMstUpdateService service = new UserMstUpdateService();

        // 画面のパラメータセット
        setupModel();

        // データが存在しない場合
        if (NaikaraTalkConstants.RETURN_CD_DATA_NO == service.getExists(model)) {

            this.addActionMessage(getMessage("EN0020", new String[] { "利用者マスタ", "" }));

            return ERR_CHECK_EXISTS;

        }

        // 更新処理のサービス呼び出し
        int cnt = service.update(this.model);

        if (cnt == NaikaraTalkConstants.RETURN_CD_ERR_NO_UPD) {

            // 排他エラーメッセージの設定
            String msg = getMessage("ES0014", new String[] { "", "" });

            this.addActionMessage(msg);

            return ERR_CHECK_EXCLUSION;
        }

        // 遷移先をチェック
        boolean flg = service.redirectCheck(this.model);

        // 更新完了メッセージの設定
        this.message = getMessage("IN0011", new String[] { USER_MST_TBL_JNM, "" });

        if (flg) {

            return UPDATE_OK_1;
        }

        return UPDATE_OK_2;
    }

    /**
     * 登録/更新前チェック<br>
     * <br>
     * 登録/更新前チェックを行う<br>
     * <br>
     * @param なし<br>
     * @return boolean エラー有無フラグ<br>
     * @exception Exception
     */
    private boolean errorCheck() throws Exception {

        // サービスの初期化
        UserMstUpdateService service = new UserMstUpdateService();

        // 関連チェック
        int chkResult = service.errorCheck(model);

        switch (chkResult) {

        case UserMstUpdateService.NG_TEACHER_2:

            this.addActionMessage(getMessage("EN0055", new String[] { "種別", "講師", "スクール" }));

            return true;

        case UserMstUpdateService.NG_TEACHER_3:

            this.addActionMessage(getMessage("EN0055", new String[] { "種別", "スクール", "講師" }));

            return true;

        case UserMstUpdateService.NG_DATE_1:

            this.addActionMessage(getMessage("EN0014", new String[] { "生年月日" }));

            return true;

        case UserMstUpdateService.NG_DATE_2:

            this.addActionMessage(getMessage("EN0011", new String[] { "利用期間：開始日", "利用期間：終了日" }));

            return true;

        case UserMstUpdateService.NG_PASSWORD:

            this.addActionMessage(getMessage("EN0039", new String[] {}));

            return true;

        case UserMstUpdateService.NG_MONTH:

            this.addActionMessage(getMessage("EN0035", new String[] { "生年月日：月" }));

            return true;

        case UserMstUpdateService.NG_DATE:

            this.addActionMessage(getMessage("EN0035", new String[] { "生年月日：日" }));

            return true;

        case UserMstUpdateService.NG_ADDRESS:

            this.addActionMessage(getMessage("EN0001", new String[] { "住所(都道府県)" }));

            return true;

        }

        return false;
    }
}