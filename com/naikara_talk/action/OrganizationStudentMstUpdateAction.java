package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.OrganizationStudentMstListModel;
import com.naikara_talk.service.OrganizationStudentMstUpdateService;
import com.naikara_talk.sessiondata.SessionOrganizationStudentMst;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>組織_初期処理。<br>
 * <b>クラス名称　　　:</b>システム受講者登録(単票)更新Actionクラス。<br>
 * <b>クラス概要　　　:</b>組織の社員情報(受講者)の新規アカウント(単票)。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/11 TECS 新規作成。
 */
public class OrganizationStudentMstUpdateAction extends OrganizationStudentMstActionSupport {

    private static final long serialVersionUID = 1L;

    /** メッセージ用使用テーブル名 */
    private static final String Student_MST_JNM = "受講者";

    /** 入力チェックエラー */
    private static final int ERR_CHECK_NG = -1;

    /** 排他エラー */
    private static final int ERR_CHECK_EXCLUSION = -2;

    /** 追加処理(正常) */
    private static final int INSERT_OK = 4;

    /** 更新処理(正常) */
    private static final int UPDATE_OK = 5;

    /** 削除処理(正常) */
    private static final int DELETE_OK = 6;

    /**
     * 登録/更新処理。<br>
     * <br>
     * 登録/更新処理。<br>
     * <br>
     * @param なし<br>
     * @return String transitionScreen 画面遷移先<br>
     * @exception NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // 画面遷移先
        String transitionScreen = null;

        // 利用停止状態フラグの初期取得
        try {
            initRadio();
        } catch (Exception e) {
            throw new NaiException(e);
        }

        // モデル設定
        setupModel();

        // 処理区分＝新規の場合('0':'新規','1':'修正','2':'削除')
        if (StringUtils.equals(OrganizationStudentMstListModel.PROS_KBN_ADD, this.processKbn_rdl)) {
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
                    transitionScreen = NEXTPAGE;
                    break;
                }
            } catch (Exception e) {
                throw new NaiException(e);
            }
        }

        // 処理区分＝修正の場合('0':'新規','1':'修正','2':'削除')
        if (StringUtils.equals(OrganizationStudentMstListModel.PROS_KBN_UPD, this.processKbn_rdl)) {
            try {
                // 更新処理を実行
                int rtn = this.update();
                switch (rtn) {
                case ERR_CHECK_NG:
                    // 入力チェックエラー
                    transitionScreen = SUCCESS;
                    break;
                case NaikaraTalkConstants.RETURN_CD_DATA_NO:
                    // データが存在チェックエラー
                    transitionScreen = INPUT;
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

        // 処理区分＝削除の場合('0':'新規','1':'修正','2':'削除')
        if (StringUtils.equals(OrganizationStudentMstListModel.PROS_KBN_DEL, this.processKbn_rdl)) {
            try {
                // 削除処理を実行
                int rtn = this.delete();
                switch (rtn) {
                case ERR_CHECK_NG:
                    // 入力チェックエラー
                    transitionScreen = SUCCESS;
                    break;
                case NaikaraTalkConstants.RETURN_CD_DATA_NO:
                    // データ不存在エラー
                    transitionScreen = SUCCESS;
                    break;
                case DELETE_OK:
                    // 削除処理(正常)
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
            // sessionSessionSaleGoodsMstのクリア
            SessionDataUtil.clearSessionData(SessionOrganizationStudentMst.class.toString());
        }

        // 一覧画面を戻る
        return transitionScreen;

    }

    /**
     * 登録処理。<br>
     * <br>
     * 登録処理。<br>
     * <br>
     * @param なし<br>
     * @return int ERR_CHECK_NG or データ取得正常  <br>
     * @exception Exception
     */
    private int insert() throws Exception {

        // 関連チェック
        if (insertCheck()) {
            return ERR_CHECK_NG;
        }

        // サービス生成
        OrganizationStudentMstUpdateService service = new OrganizationStudentMstUpdateService();

        // 画面のデータをmodelにセットする
        setupModel();
        // 新規処理のサービス呼び出し
        service.insert(this.model);

        StringBuffer sb = new StringBuffer();
        sb.append("受講者IDは").append(this.model.getStudentId()).append("です。");
        // 登録完了メッセージの設定
        this.message = getMessage("IB0010", new String[] { Student_MST_JNM, sb.toString() });

        return INSERT_OK;
    }

    /**
     * 更新処理。<br>
     * <br>
     * 更新処理。<br>
     * <br>
     * @param なし<br>
     * @return int ERR_CHECK_NG or 排他エラー or UPDATE_OK or データなし <br>
     * @exception Exception
     */
    private int update() throws Exception {

        // 関連チェック
        if (updateCheck()) {
            return ERR_CHECK_NG;
        }
        // 更新処理を実行
        OrganizationStudentMstUpdateService service = new OrganizationStudentMstUpdateService();

        // 存在チェック
        if (NaikaraTalkConstants.RETURN_CD_DATA_YES == service.getExists(model)) {

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
            // 更新後メッセージ
            this.message = getMessage("IB0011", new String[] { Student_MST_JNM, "" });

            // 一覧画面を戻る
            return UPDATE_OK;
        } else {

            // データが不存在するメッセージの設定
            this.addActionMessage(getMessage("EB0020", new String[] {}));
            return NaikaraTalkConstants.RETURN_CD_DATA_NO;
        }
    }

    /**
     * 削除処理。<br>
     * <br>
     * 削除処理。<br>
     * <br>
     * @param なし<br>
     * @return int ERR_CHECK_NG or 排他エラー or UPDATE_OK or データなし <br>
     * @exception Exception
     */
    private int delete() throws Exception {

        // 関連チェック
        if (deleteCheck()) {
            return ERR_CHECK_NG;
        }
        // 更新処理を実行
        OrganizationStudentMstUpdateService service = new OrganizationStudentMstUpdateService();
        // 画面のデータをmodelにセットする

        // 存在チェック
        if (NaikaraTalkConstants.RETURN_CD_DATA_YES == service.getExists(model)) {
            // 画面のデータをmodelにセットする
            setupModel();

            // 削除処理のサービス呼び出し
            int cnt = service.delete(this.model);

            if (cnt == NaikaraTalkConstants.RETURN_CD_ERR_NO_UPD) {
                // 排他エラーメッセージの設定
                String msg = getMessage("ES0014", new String[] { "", "" });
                this.addActionMessage(msg);
                return ERR_CHECK_EXCLUSION;
            }

            // 削除後メッセージ
            this.message = getMessage("IB0012", new String[] { Student_MST_JNM });

            // 一覧画面を戻る
            return DELETE_OK;

        } else {
            // データが不存在するメッセージの設定
            this.addActionMessage(getMessage("EB0020", new String[] {}));
            return NaikaraTalkConstants.RETURN_CD_DATA_NO;
        }

    }

    /**
     * 登録前チェック。<br>
     * <br>
     * 登録前チェック。<br>
     * <br>
     * @param なし<br>
     * @return boolean チェック結果 <br>
     * @exception Exception
     */
    private boolean insertCheck() throws Exception {

        OrganizationStudentMstUpdateService service = new OrganizationStudentMstUpdateService();

        // 関連チェック
        int chkResult = service.insertCheck(model);

        switch (chkResult) {

        // パスワード複雑性チェックのチェック結果がエラーの場合
        case OrganizationStudentMstUpdateService.ERR_CHECK_1:
            this.addActionMessage(getMessage("EB0039", new String[] { "" }));
            return true;

            // ｢パスワード｣と｢パスワード確認｣の値が違う場合(エラーの場合)
        case OrganizationStudentMstUpdateService.ERR_CHECK_2:
            this.addActionMessage(getMessage("EB0017", new String[] { "仮パスワード確認" }));
            return true;

            // 自己負担率 ＜ 0 場合
        case OrganizationStudentMstUpdateService.ERR_CHECK_3:
            this.addActionMessage(getMessage("EB0011", new String[] { "0", "自己負担率" }));
            return true;

            // 自己負担率 ＞ 100 場合
        case OrganizationStudentMstUpdateService.ERR_CHECK_4:
            this.addActionMessage(getMessage("EB0011", new String[] { "自己負担率", "100" }));
            return true;
        }

        return false;
    }

    /**
     * 更新前チェック。<br>
     * <br>
     * 更新前チェック。<br>
     * <br>
     * @param なし<br>
     * @return boolean チェック結果 <br>
     * @exception Exception
     */
    private boolean updateCheck() throws Exception {

        OrganizationStudentMstUpdateService service = new OrganizationStudentMstUpdateService();

        // 関連チェック
        int chkResult = service.updateCheck(model);

        switch (chkResult) {

        // パスワード複雑性チェックのチェック結果がエラーの場合
        case OrganizationStudentMstUpdateService.ERR_CHECK_1:
            this.addActionMessage(getMessage("EB0039", new String[] { "" }));
            return true;

            // ｢パスワード｣と｢パスワード確認｣の値が違う場合(エラーの場合)
        case OrganizationStudentMstUpdateService.ERR_CHECK_2:
            this.addActionMessage(getMessage("EB0017", new String[] { "仮パスワード確認" }));
            return true;

            // 自己負担率 ＜ 0 場合
        case OrganizationStudentMstUpdateService.ERR_CHECK_3:
            this.addActionMessage(getMessage("EB0011", new String[] { "0", "自己負担率" }));
            return true;

            // 自己負担率 ＞ 100 場合
        case OrganizationStudentMstUpdateService.ERR_CHECK_4:
            this.addActionMessage(getMessage("EB0011", new String[] { "自己負担率", "100" }));
            return true;
            // 利用停止状態＝チェックOFF （利用可）の場合
            // 利用停止日≠空欄の場合
        case OrganizationStudentMstUpdateService.ERR_CHECK_5:
            this.addActionMessage(getMessage("EB0053", new String[] { "利用停止状態が未チェックの場合に利用停止日" }));
            return true;

            // 利用停止状態＝チェックON （利用不可）の場合
            // 利用停止日＝空欄の場合
        case OrganizationStudentMstUpdateService.ERR_CHECK_6:
            this.addActionMessage(getMessage("EB0001", new String[] { "利用停止状態がチェック済の場合に利用停止日" }));
            return true;

            // 利用停止日≠日付の場合
        case OrganizationStudentMstUpdateService.ERR_CHECK_7:
            this.addActionMessage(getMessage("EB0010", new String[] { "利用停止日" }));
            return true;

            // 組織マスタより契約開始日データが存在しない場合
        case OrganizationStudentMstUpdateService.ERR_CHECK_8:
            this.addActionMessage(getMessage("EB0042", new String[] { "御社の契約情報の取得処理" }));
            return true;

            // サーバーのシステム日付≦画面の｢利用停止日｣ 且つ 組織マスタ．契約開始日 ≦ 画面の｢利用停止日｣
            // 且つ 画面の｢利用停止日｣ ≦組織マスタ．契約終了日 を除く場合は、エラーメッセージ情報を設定する
        case OrganizationStudentMstUpdateService.ERR_CHECK_9:
            this.addActionMessage(getMessage("EB0054", new String[] { "利用停止日", "組織の契約期間 [組織マスタ．契約開始日]～ [組織マスタ．契約終了日]" }));
            return true;
            // レッスン予約のデータ存在しない場合
        case OrganizationStudentMstUpdateService.ERR_CHECK_10:
            this.addActionMessage(getMessage("EB0019", new String[] { "レッスン予約", "" }));
            return true;
        }

        return false;
    }

    /**
     * 削除前チェック。<br>
     * <br>
     * 削除前チェック。<br>
     * <br>
     * @param なし<br>
     * @return boolean チェック結果 <br>
     * @exception Exception
     */
    private boolean deleteCheck() throws Exception {

        OrganizationStudentMstUpdateService service = new OrganizationStudentMstUpdateService();

        // 関連チェック
        int chkResult = service.deleteCheck(model);

        switch (chkResult) {

        // ポイント所有テーブルのデータ存在しない場合
        case OrganizationStudentMstUpdateService.ERR_CHECK_11:
            this.addActionMessage(getMessage("EB0019",
                    new String[] { "ポイント割振", "修正処理で、利用停止状態と利用停止日の入力をして利用不可としてください。" }));
            return true;
        }

        return false;
    }
}
