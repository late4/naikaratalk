package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.AccountUpdateService;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様_初期処理。<br>
 * <b>クラス名称　　　:</b>アカウント取得処理登録更新Actionクラス。<br>
 * <b>クラス概要　　　:</b>アカウント取得処理登録更新Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/08 TECS 新規作成。
 */
public class AccountUpdateAction extends AccountActionSupport {

    private static final long serialVersionUID = 1L;

    /** 入力チェックエラー */
    private static final int ERR_CHECK_NG = -1;

    /** 排他エラー */
    private static final int ERR_CHECK_EXCLUSION = -2;

    /** 存在チェックエラー */
    private static final int ERR_CHECK_EXISTS = -3;

    /** 追加処理(正常) */
    private static final int INSERT_OK = 1;

    /** 更新処理(正常) */
    private static final int UPDATE_OK = 2;

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

        if (StringUtils.isEmpty(this.userId)) {

            // 新規の場合
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
        } else {

            // 修正の場合
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

                case UPDATE_OK:

                    // 更新処理(正常)1
                    transitionScreen = NEXT_PAGE_1;

                    break;

                }

            } catch (Exception e) {

                throw new NaiException(e);

            }
        }

        if (StringUtils.equals(transitionScreen, NEXT_PAGE_1) || StringUtils.equals(transitionScreen, NEXT_PAGE_2)) {

            if (StringUtils.isEmpty(this.userId)) {

                transitionScreen = NEXT_PAGE_1;

            } else {

                transitionScreen = NEXT_PAGE_2;
            }
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
        AccountUpdateService service = new AccountUpdateService();

        // 画面のパラメータセット
        setupModel();

        // 新規処理のサービス呼び出し
        service.insert(this.model);

        if ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString()) != null) {

            userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();
        }
        if (!StringUtils.isEmpty(userId)) {

            // 登録完了メッセージの設定
            this.message = getMessage("IC0011", new String[] { "プロフィール", "" });
        }

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
        AccountUpdateService service = new AccountUpdateService();

        this.model.setStudentId_lbl(this.userId);

        if (NaikaraTalkConstants.RETURN_CD_DATA_YES == service.isExists(this.model)) {

            // 画面のパラメータセット
            setupModel();

            // 更新処理のサービス呼び出し
            int cnt = service.update(this.model);

            if (cnt == NaikaraTalkConstants.RETURN_CD_ERR_NO_UPD) {

                // 排他エラーメッセージの設定
                String msg = getMessage("ES0014", new String[] {});

                this.addActionMessage(msg);

                return ERR_CHECK_EXCLUSION;
            }

            String sessionCustomerKbn = null;
            SessionUser SessionUserData = ((SessionUser)SessionDataUtil.getSessionData(SessionUser.class.toString()));
            if ( SessionUserData != null ) {
            	sessionCustomerKbn = NaikaraStringUtil.nvlString(SessionUserData.getCustomerKbn()).toString();  // ログイン中の顧客区分(受講者の場合のみ設定される)
            }

            if (!(sessionCustomerKbn == null || "".equals(sessionCustomerKbn))) {
                // 顧客区分＝法人の場合
                if (StringUtils.equals(sessionCustomerKbn, NaikaraTalkConstants.CUSTOMER_KBN_ORGANIZATION)) {
                    // セッション情報にニックネームを設定する
                    SessionUserData.setUserNm(this.model.getNickNm_txt());
                }
            }

            // 更新完了メッセージの設定
            this.message = getMessage("IC0011", new String[] { "プロフィール", "" });

            return UPDATE_OK;

        } else {
            // データが存在しない場合
            this.addActionMessage(getMessage("EC0020", new String[] {}));

            return NaikaraTalkConstants.RETURN_CD_DATA_NO;
        }

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
        AccountUpdateService service = new AccountUpdateService();

        // 関連チェック
        int chkResult = service.errorCheck(this.model);

        switch (chkResult) {

        case AccountUpdateService.PREFECTURE_NOT_SELECT:

            this.addActionMessage(getMessage("EC0001", new String[] { "住所(都道府県)" }));

            return true;

        case AccountUpdateService.BIRTH_MM:

            this.addActionMessage(getMessage("EN0035", new String[] { "生年月日：月" }));

            return true;

        case AccountUpdateService.BIRTH_DD:

            this.addActionMessage(getMessage("EN0035", new String[] { "生年月日：日" }));

            return true;

        case AccountUpdateService.BIRTH_YYYYMMDD_FUTURE:

            this.addActionMessage(getMessage("EC0014", new String[] { "生年月日" }));

            return true;

        case AccountUpdateService.PASSWORD_CHK:

            this.addActionMessage(getMessage("EC0039", new String[] {}));

            return true;

        case AccountUpdateService.PASSWORD_CONFIRM:

            this.addActionMessage(getMessage("EC0017", new String[] { "パスワード確認" }));

            return true;

        case AccountUpdateService.GUARD_FAMILY_JNM_NOT_INPUT:

            this.addActionMessage(getMessage("EC0001", new String[] { "保護者：お名前(姓)" }));

            return true;

        case AccountUpdateService.GUARD_FIRST_JNM_NOT_INPUT:

            this.addActionMessage(getMessage("EC0001", new String[] { "保護者：お名前(名)" }));

            return true;

        case AccountUpdateService.GUARD_FAMILY_KNM_NOT_INPUT:

            this.addActionMessage(getMessage("EC0001", new String[] { "保護者：フリガナ(姓)" }));

            return true;

        case AccountUpdateService.GUARD_FIRST_KNM_NOT_INPUT:

            this.addActionMessage(getMessage("EC0001", new String[] { "保護者：フリガナ(名)" }));

            return true;

        case AccountUpdateService.GUARD_RELAT_NOT_INPUT:

            this.addActionMessage(getMessage("EC0001", new String[] { "あなたとの続柄" }));

            return true;

        case AccountUpdateService.GUARD_TEL1_NOT_INPUT:

            this.addActionMessage(getMessage("EC0001", new String[] { "保護者：電話番号" }));

            return true;

        case AccountUpdateService.GUARD_BIRTH_YY_NOT_INPUT:

            this.addActionMessage(getMessage("EC0001", new String[] { "保護者：生年月日：年" }));

            return true;

        case AccountUpdateService.GUARD_BIRTH_MM_NOT_INPUT:

            this.addActionMessage(getMessage("EC0001", new String[] { "保護者：生年月日：月" }));

            return true;

        case AccountUpdateService.GUARD_BIRTH_DD_NOT_INPUT:

            this.addActionMessage(getMessage("EC0001", new String[] { "保護者：生年月日：日" }));

            return true;

        case AccountUpdateService.GUARD_BIRTH_MM:

            this.addActionMessage(getMessage("EN0035", new String[] { "保護者：生年月日：月" }));

            return true;

        case AccountUpdateService.GUARD_BIRTH_DD:

            this.addActionMessage(getMessage("EN0035", new String[] { "保護者：生年月日：日" }));

            return true;

        case AccountUpdateService.GUARD_BIRTH_YYYYMMDD_FUTURE:

            this.addActionMessage(getMessage("EC0014", new String[] { "保護者：生年月日" }));

            return true;

        case AccountUpdateService.GUARD_ZIP_CD_NOT_INPUT:

            this.addActionMessage(getMessage("EC0001", new String[] { "保護者：郵便番号" }));

            return true;

        case AccountUpdateService.GUARD_PREFECTURE_NOT_SELECT:

            this.addActionMessage(getMessage("EC0001", new String[] { "保護者：住所(都道府県)" }));

            return true;

        case AccountUpdateService.GUARD_CITY_NOT_INPUT:

            this.addActionMessage(getMessage("EC0001", new String[] { "保護者：住所(市区町村等)" }));

            return true;

        case AccountUpdateService.GUARD_GENDER_NOT_SELECT:

            this.addActionMessage(getMessage("EC0001", new String[] { "保護者：性別" }));

            return true;

        case AccountUpdateService.GUARD_MAIL_ADD1_NOT_INPUT:

            this.addActionMessage(getMessage("EC0001", new String[] { "保護者：メールアドレス１" }));

            return true;

        case AccountUpdateService.MAIL_ADD_REPEAT:

            this.addActionMessage(getMessage("EC0018", new String[] { "メールアドレス１～３" }));

            return true;

        case AccountUpdateService.MAIL_ADD1_DB_REPEAT:

            this.addActionMessage(getMessage("EC0059", new String[] { "メールアドレス", "メールアドレス１" }));

            return true;

        case AccountUpdateService.MAIL_ADD2_DB_REPEAT:

            this.addActionMessage(getMessage("EC0059", new String[] { "メールアドレス", "メールアドレス２" }));

            return true;

        case AccountUpdateService.MAIL_ADD3_DB_REPEAT:

            this.addActionMessage(getMessage("EC0059", new String[] { "メールアドレス", "メールアドレス３" }));

            return true;

        case AccountUpdateService.STUDENT_POSITION_NOT_INPUT:

            this.addActionMessage(getMessage("EC0001", new String[] { "受講者所属部署" }));

            return true;

        case AccountUpdateService.USE_AGREE_NOT_SELECT:

            this.addActionMessage(getMessage("EC0001", new String[] { "ご利用規約に同意する" }));

            return true;

        case AccountUpdateService.INDIV_AGREE_NOT_SELECT:

            this.addActionMessage(getMessage("EC0001", new String[] { "個人情報の同意" }));

            return true;

        }

        return false;
    }
}