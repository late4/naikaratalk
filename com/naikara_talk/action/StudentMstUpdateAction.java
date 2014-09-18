package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.StudentMstListModel;
import com.naikara_talk.service.StudentMstUpdateService;
import com.naikara_talk.sessiondata.SessionStudentMst;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守<br>
 * <b>クラス名称　　　:</b>受講者マスタメンテナンス【単票】登録更新Actionクラス。<br>
 * <b>クラス概要　　　:</b>受講者マスタメンテナンス【単票】登録更新Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b> 2013/07/30 TECS 新規作成。
 */
public class StudentMstUpdateAction extends StudentMstActionSupport {

    private static final long serialVersionUID = 1L;

    /** メッセージ用使用テーブル名 */
    private static final String STUDENT_MST = "受講者マスタ";

    /** 入力チェックエラー */
    private static final int ERR_CHECK_NG = -1;

    /** 排他エラー */
    private static final int ERR_CHECK_EXCLUSION = -2;

    /** データ存在エラー */
    private static final int ERR_DATA_EXISTENCE = -3;

    /** 追加処理(正常) */
    private static final int INSERT_OK = 1;

    /** 更新処理(正常) */
    private static final int UPDATE_OK = 2;

    /**
     * 登録/更新処理。<br>
     * <br>
     * @param なし
     * @return String
     * @throws NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // 画面遷移先
        String transitionScreen = null;

        // 受取方法、商品形態の初期取得
        try {
            initRadio();
        } catch (Exception e) {
            throw new NaiException(e);
        }

        // モデル設定
        setupModel();

        // 処理区分＝新規の場合('0':'新規','1':'修正','2':'照会')
        if (StringUtils.equals(StudentMstListModel.PROS_KBN_ADD, this.processKbn_rdl)) {
            try {
                // 新規処理を実行
                int rtn = this.insert();
                switch (rtn) {
                case ERR_CHECK_NG:
                    // 入力チェックエラー
                    transitionScreen = SUCCESS;
                    break;
                case ERR_DATA_EXISTENCE:
                    // データ存在エラー
                    transitionScreen = SUCCESS;
                    break;
                case INSERT_OK:
                    // 追加処理(正常)
                    transitionScreen = NEXTPAGE;
                    break;
                }
            } catch (Exception e) {
                throw new NaiException(e);
            }
        }

        // 処理区分＝修正の場合('0':'新規','1':'修正','2':'照会')
        if (StringUtils.equals(StudentMstListModel.PROS_KBN_UPD, this.processKbn_rdl)) {
            try {
                // 更新処理を実行
                int rtn = this.update();
                switch (rtn) {
                case ERR_CHECK_NG:
                    // 入力チェックエラー
                    transitionScreen = SUCCESS;
                    break;
                case NaikaraTalkConstants.RETURN_CD_DATA_NO:
                    // データ不存在エラー
                    transitionScreen = SUCCESS;
                    break;
                case ERR_CHECK_EXCLUSION:
                    // 排他エラー
                    transitionScreen = INPUT;
                    break;
                case UPDATE_OK:
                    // 更新処理(正常)
                    transitionScreen = NEXTPAGE;
                    break;
                }

            } catch (Exception e) {
                throw new NaiException(e);
            }
        }

        if (transitionScreen == NEXTPAGE) {
            // 正常の場合 MoveActionで登録した画面遷移を削除
            try {
                removeLatestActionList();
            } catch (Exception e) {
                throw new NaiException(e);
            }
            // 戻る用のsession情報の初期化
            // sessionSessionStudentMstのクリア
            SessionDataUtil.clearSessionData(SessionStudentMst.class.toString());
        }

        // 一覧画面を戻る
        return transitionScreen;

    }

    /**
     * 登録処理。<br>
     * <br>
     * @param なし
     * @return int
     * @throws Exception
     */
    private int insert() throws Exception {

        // 関連チェック
        if (errorCheck()) {
            return ERR_CHECK_NG;
        }

        // サービス生成
        StudentMstUpdateService service = new StudentMstUpdateService();

        // 画面のデータをmodelにセットする
        setupModel();

        service.insert(this.model);

        StringBuffer sb = new StringBuffer();
        sb.append("受講者IDは").append(this.model.getStudentId_lbl()).append("です。");

        // 登録完了メッセージの設定
        this.message = getMessage("IN0010", new String[] { STUDENT_MST, sb.toString() });

        return INSERT_OK;

    }

    /**
     * 更新処理。<br>
     * <br>
     * @param なし
     * @return int
     * @throws Exception
     */
    private int update() throws Exception {

        // 関連チェック
        if (errorCheck()) {
            return ERR_CHECK_NG;
        }
        // サービス生成
        StudentMstUpdateService service = new StudentMstUpdateService();

        // 存在チェック
        if (NaikaraTalkConstants.RETURN_CD_DATA_YES == service.getExist(model)) {

            // 画面のデータをmodelにセットする
            setupModel();

            // 更新処理のサービス呼び出し
            int cnt = service.update(this.model);

            if (cnt == NaikaraTalkConstants.RETURN_CD_ERR_NO_UPD) {
                // 排他エラーメッセージの設定
                String msg = getMessage("ES0014", new String[] { "", "" });
                this.addActionMessage(msg);
                return ERR_CHECK_EXCLUSION;
            }

            // 更新完了メッセージの設定
            this.message = getMessage("IN0011", new String[] { STUDENT_MST, "" });

            // 一覧画面を戻る
            return UPDATE_OK;

        } else {

            // データが不存在するメッセージの設定
            this.addActionMessage(getMessage("EN0020", new String[] { "受講者マスタ", "" }));
            return NaikaraTalkConstants.RETURN_CD_DATA_NO;
        }
    }

    /**
     * 登録/更新前チェック。<br>
     * <br>
     * @param なし
     * @return boolean
     * @throws Exception
     */
    private boolean errorCheck() throws Exception {

        StudentMstUpdateService service = new StudentMstUpdateService();

        // 関連チェック
        int chkResult = service.errorCheck(model);

        switch (chkResult) {

        // ｢住所(都道府県)｣ ='0000'の場合
        case StudentMstUpdateService.ERR_CHK_ADDR_PREFECTURE_N0_SELECT:
            this.addActionMessage(getMessage("EN0001", new String[] { "住所(都道府県)", "" }));
            return true;

            // 性別のチェック
        case StudentMstUpdateService.ERR_CHK_GENDER_CHECK:
            this.addActionMessage(getMessage("EN0001", new String[] { "性別", "" }));
            return true;

            // ｢利用規約の同意する｣＝FALSEの場合
        case StudentMstUpdateService.ERR_CHK_USEAGREEMENT_FLG:
            this.addActionMessage(getMessage("EN0001", new String[] { "利用規約の同意する", "" }));
            return true;

            // ｢個人情報の同意｣＝FALSEの場合]
        case StudentMstUpdateService.ERR_CHK_INDIVIDUAL_AGREEMENT_FLG:
            this.addActionMessage(getMessage("EN0001", new String[] { "個人情報の同意する", "" }));
            return true;

            // 生年月日：月 が日付ではない場合
        case StudentMstUpdateService.ERR_STUDENT_NG_MONTH:
            this.addActionMessage(getMessage("EN0035", new String[] { "生年月日：月" }));
            return true;

            // 生年月日：日 が日付ではない場合
        case StudentMstUpdateService.ERR_STUDENT_NG_DAY:
            this.addActionMessage(getMessage("EN0035", new String[] { "生年月日：日" }));
            return true;

            // ｢生年月日｣　＞　サーバーのシステム日付　の場合
        case StudentMstUpdateService.ERR_CHK_BIRTHDATE_MORE_THAN_SYSDATE:
            this.addActionMessage(getMessage("EN0014", new String[] { "生年月日", "" }));
            return true;

            // ｢利用期間：開始日｣と｢利用期間：終了日｣の整合性チェック (日付)
        case StudentMstUpdateService.ERR_CHK_INTEGRITY_DT:
            this.addActionMessage(getMessage("EN0011", new String[] { "利用期間：開始日", "利用期間：終了日" }));
            return true;

            // 「パスワードの複雑性」のチェック
        case StudentMstUpdateService.ERR_CHK_PASS_CHECK:
            this.addActionMessage(getMessage("EN0039", new String[] { "パスワード", "" }));
            return true;

            // (未成年)の場合
            // 「保護者：お名前(姓)」がなし
        case StudentMstUpdateService.ERR_CHK_GETGUARDIAN_FAMILY_JNM_EMPTY:
            this.addActionMessage(getMessage("EN0001", new String[] { "受講者は未成年の場合、保護者：お名前(姓)", "" }));
            return true;

            // 「保護者：お名前(名)」がなし
        case StudentMstUpdateService.ERR_CHK_GETGUARDIAN_FIRST_JNM_EMPTY:
            this.addActionMessage(getMessage("EN0001", new String[] { "受講者は未成年の場合、保護者：お名前(名)", "" }));
            return true;

            // 「保護者：フリガナ(姓)」がなし
        case StudentMstUpdateService.ERR_CHK_GETGUARDIAN_FAMILY_KNM_EMPTY:
            this.addActionMessage(getMessage("EN0001", new String[] { "受講者は未成年の場合、保護者：フリガナ(姓)", "" }));
            return true;

            // 「保護者：フリガナ(名)」がなし
        case StudentMstUpdateService.ERR_CHK_GETGUARDIAN_FIRST_KNM_EMPTY:
            this.addActionMessage(getMessage("EN0001", new String[] { "受講者は未成年の場合、保護者：フリガナ(名)", "" }));
            return true;

            // 「あなたとの続柄」がなし
        case StudentMstUpdateService.ERR_CHK_GETGUARDIAN_RELATIONSHIP_EMPTY:
            this.addActionMessage(getMessage("EN0001", new String[] { "受講者は未成年の場合、あなたとの続柄", "" }));
            return true;

            // 「保護者：電話番号１」がなし
        case StudentMstUpdateService.ERR_CHK_GETGUARDIAN_TELE1_EMPTY:
            this.addActionMessage(getMessage("EN0001", new String[] { "受講者は未成年の場合、保護者：電話番号１", "" }));
            return true;

            // 「保護者：生年月日：年」がなし
        case StudentMstUpdateService.ERR_CHK_GETGUARDIAN_BIRTH_YYYY_EMPTY:
            this.addActionMessage(getMessage("EN0001", new String[] { "受講者は未成年の場合、保護者：生年月日：年", "" }));
            return true;

            // 「保護者：生年月日：月」がなし
        case StudentMstUpdateService.ERR_CHK_GETGUARDIAN_BIRTH_MM_EMPTY:
            this.addActionMessage(getMessage("EN0001", new String[] { "受講者は未成年の場合、保護者：生年月日：月", "" }));
            return true;

            // 「保護者：生年月日：日」がなし
        case StudentMstUpdateService.ERR_CHK_GETGUARDIAN_BIRTH_DD_EMPTY:
            this.addActionMessage(getMessage("EN0001", new String[] { "受講者は未成年の場合、保護者：生年月日：日", "" }));
            return true;

            // 「保護者：生年月日：月」 が日付ではない場合
        case StudentMstUpdateService.ERR_NG_MONTH:
            this.addActionMessage(getMessage("EN0035", new String[] { "受講者は未成年の場合、保護者：生年月日：月" }));
            return true;

            // 「保護者：生年月日：日」 が日付ではない場合
        case StudentMstUpdateService.ERR_NG_DAY:
            this.addActionMessage(getMessage("EN0035", new String[] { "受講者は未成年の場合、保護者：生年月日：日" }));
            return true;

            // 「保護者：生年月日｣　＞　サーバーのシステム日付　
        case StudentMstUpdateService.ERR_CHK_GUARDIAN_BIRTHDATE_MORE_THAN_SYSDATE:
            this.addActionMessage(getMessage("EN0014", new String[] { "受講者は未成年の場合、保護者：生年月日", "" }));
            return true;

            // ｢保護者：郵便番号｣ がなし
        case StudentMstUpdateService.ERR_CHK_GUARDIAN_ZIPCODE_EMPTY:
            this.addActionMessage(getMessage("EN0001", new String[] { "受講者は未成年の場合、保護者：郵便番号", "" }));
            return true;

            // ｢保護者：住所(都道府県)｣ ='0000'
        case StudentMstUpdateService.ERR_CHK_GUARDIAN_ADDR_PREFECTURE_N0_SELECT:
            this.addActionMessage(getMessage("EN0001", new String[] { "受講者は未成年の場合、保護者：住所(都道府県)", "" }));
            return true;

            // 「保護者：住所(市区町村 等)」がなし
        case StudentMstUpdateService.ERR_CHK_GUARDIAN_ADDR_CITY_EMPTY:
            this.addActionMessage(getMessage("EN0001", new String[] { "受講者は未成年の場合、保護者：住所(市区町村 等)", "" }));
            return true;

            // 「保護者：性別」がなし
        case StudentMstUpdateService.ERR_CHK_GUARDIAN_GENDER_N0_SELECT:
            this.addActionMessage(getMessage("EN0001", new String[] { "受講者は未成年の場合、保護者：性別", "" }));
            return true;

            // 「保護者：メールアドレス1」がなし
        case StudentMstUpdateService.ERR_CHK_GUARDIAN_MAIL1_EMPTY:
            this.addActionMessage(getMessage("EN0001", new String[] { "受講者は未成年の場合、保護者：メールアドレス１", "" }));
            return true;

            // 「メールアドレス1－3」の一意チェック
        case StudentMstUpdateService.ERR_CHK_MAIL:
            this.addActionMessage(getMessage("EN0018", new String[] { "メールアドレス１～３", "" }));
            return true;

            // 「月謝データが存在する場合 且つ 利用期間：終了日≠2999/12/31の整合性チェック
        case StudentMstUpdateService.ERR_CHK_CHK_INTEGRITY_MONTHLY_DATE:
            this.addActionMessage(getMessage("EN0054", new String[] { "利用期間：終了日(【利用期間：終了日の値】)",
                    "受講者別月謝購入情報画面の月謝停止年月(【受講者別月謝購入情報画面で設定されている明細の月謝停止年月の最大値】)" }));
            return true;

            // 「メールアドレス1」のDBありチェック
        case StudentMstUpdateService.ERR_CHK_MAIL_ARI_1:
            this.addActionMessage(getMessage("EN0072", new String[] { "メールアドレス１", "メールアドレス１" }));
            return true;

            // 「メールアドレス2」のDBありチェック
        case StudentMstUpdateService.ERR_CHK_MAIL_ARI_2:
            this.addActionMessage(getMessage("EN0072", new String[] { "メールアドレス２", "メールアドレス２" }));
            return true;

            // 「メールアドレス3」のDBありチェック
        case StudentMstUpdateService.ERR_CHK_MAIL_ARI_3:
            this.addActionMessage(getMessage("EN0072", new String[] { "メールアドレス３", "メールアドレス３" }));
            return true;

            // 「月謝用の実購入日」がNULLチェック
        case StudentMstUpdateService.ERR_CHK_BEGINNING_PURCHASEDT:
            this.addActionMessage(getMessage("EN0034", new String[] { "月謝用の実購入日がNULL", "" }));
            return true;

            // 「保護者の同意書の入手」が選択しないの場合
        case StudentMstUpdateService.ERR_CHK_CONSENT_DOCUMENT_FLG:
            this.addActionMessage(getMessage("EN0001", new String[] { "保護者の同意書の入手", "" }));
            return true;
        }
        return false;
    }
}
