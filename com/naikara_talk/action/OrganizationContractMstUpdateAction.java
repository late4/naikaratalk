package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.OrganizationContractMstListModel;
import com.naikara_talk.service.OrganizationContractMstUpdateService;
import com.naikara_talk.sessiondata.SessionOrganizationContractMstList;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>組織契約情報登録。<br>
 * <b>クラス名称       :</b>組織契約情報登録の登録、更新、削除処理Actionクラス。<br>
 * <b>クラス概要       :</b>組織契約情報登録登録、更新処理を行う<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/09 TECS 新規作成
 */
public class OrganizationContractMstUpdateAction extends OrganizationContractMstActionSupport {

    private static final long serialVersionUID = 1L;
    /** 入力チェックエラー */
    private static final int ERR_CHECK_NG = -1;

    /** 排他エラー */
    private static final int ERR_CHECK_EXCLUSION = -2;

    /** 追加処理(正常) */
    private static final int INSERT_OK = 1;

    /** 更新処理(正常) */
    private static final int UPDATE_OK = 2;

    /** 削除処理(正常) */
    private static final int DELETE_OK = 3;

    /**
     * 画面の初期値を保持<br>
     * <br>
     *
     * @param なし<br>
     * @return なし <br>
     * @exception なし
     */
    @Override
    public void validate() {
        try {
            // 住所(地域)と住所(都道府県)等の初期取得。
            initSelect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.validate();
    }

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

        // モデル設定
        setupModel();

        // 処理区分＝新規の場合('0':'新規','1':'流用作成','2':'修正','3':'削除')
        if (StringUtils.equals(OrganizationContractMstListModel.PROS_KBN_ADD, this.processKbn_rdl)) {
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

        // 処理区分＝流用作成の場合('0':'新規','1':'流用作成','2':'修正','3':'削除')
        if (StringUtils.equals(OrganizationContractMstListModel.PROS_KBN_MAKE, this.processKbn_rdl)) {
            try {
                // 新規処理を実行
                int rtn = this.updateWithConsSeq();
                switch (rtn) {
                case ERR_CHECK_NG:
                    // 入力チェックエラー
                    transitionScreen = SUCCESS;
                    break;
                case INSERT_OK:
                    // 流用作成処理(正常)
                    transitionScreen = NEXTPAGE;
                    break;
                }
            } catch (Exception e) {
                throw new NaiException(e);
            }
        }

        // 処理区分＝修正の場合('0':'新規','1':'流用作成','2':'修正','3':'削除')
        if (StringUtils.equals(OrganizationContractMstListModel.PROS_KBN_UPD, this.processKbn_rdl)) {
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
                case UPDATE_OK:
                    // 更新処理(正常)
                    transitionScreen = NEXTPAGE;
                    break;
                }
            } catch (Exception e) {
                throw new NaiException(e);
            }
        }

        // 処理区分＝削除の場合('0':'新規','1':'流用作成','2':'修正','3':'削除')
        if (StringUtils.equals(OrganizationContractMstListModel.PROS_KBN_DEL, this.processKbn_rdl)) {
            try {
                // 新規処理を実行
                int rtn = this.delete();
                switch (rtn) {
                case ERR_CHECK_NG:
                    // 入力チェックエラー
                    transitionScreen = SUCCESS;
                    break;
                case DELETE_OK:
                    // 排他エラー
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
                // 戻る用のsession情報の初期化
                // SessionOrganizationContractMstListのクリア
                SessionDataUtil.clearSessionData(SessionOrganizationContractMstList.class.toString());
            } catch (Exception e) {
                throw new NaiException(e);
            }
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
        OrganizationContractMstUpdateService service = new OrganizationContractMstUpdateService();

        // 画面のデータをmodelにセットする
        setupModel();

        // 新規処理のサービス呼び出し
        service.insert(this.model);

        StringBuffer sb = new StringBuffer();
        sb.append("組織IDは").append(this.model.getOrganizationId()).append("です。");
        sb.append("連番は").append(this.model.getConsSeq()).append("です。");

        // 登録完了メッセージの設定
        this.message = getMessage("IN0010", new String[] { "組織マスタ", sb.toString() });

        return INSERT_OK;
    }

    /**
     * 流用作成処理。<br>
     * <br>
     * @param なし
     * @return int
     * @throws Exception
     */
    private int updateWithConsSeq() throws Exception {

        // 関連チェック
        if (errorCheck()) {
            return ERR_CHECK_NG;
        }

        // サービス生成
        OrganizationContractMstUpdateService service = new OrganizationContractMstUpdateService();

        // 画面のデータをmodelにセットする
        setupModel();

        // 流用作成処理のサービス呼び出し
        int cnt = service.updateWithConsSeq(this.model);

        if (cnt == ERR_CHECK_NG) {
            // データが存在しない場合
            this.message = getMessage("IN0020", new String[] { "組織マスタ", "" });
            return ERR_CHECK_NG;
        } else {
            StringBuffer sb = new StringBuffer();
            sb.append("組織IDは").append(this.model.getOrganizationId()).append("です。");
            sb.append("連番は").append(this.model.getConsSeq()).append("です。");

            // 登録完了メッセージの設定
            this.message = getMessage("IN0010", new String[] { "組織マスタ", sb.toString() });
        }

        // 一覧画面を戻る
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
        OrganizationContractMstUpdateService service = new OrganizationContractMstUpdateService();

        // 画面のデータをmodelにセットする
        setupModel();

        // 更新処理のサービス呼び出し
        int cnt = service.update(this.model);

        if (cnt == 0) {
            // 排他エラーメッセージの設定
            String msg = getMessage("ES0014", new String[] { "", "" });
            this.addActionMessage(msg);
            return ERR_CHECK_EXCLUSION;
        }

        // 更新完了メッセージの設定
        this.message = getMessage("IN0011", new String[] { "組織マスタ", "" });

        // 一覧画面を戻る
        return UPDATE_OK;
    }

    /**
     * 削除処理。<br>
     * <br>
     * @param なし
     * @return int
     * @throws Exception
     */
    private int delete() throws Exception {

        // 関連チェック
        if (errorCheck()) {
            return ERR_CHECK_NG;
        }

        // サービス生成
        OrganizationContractMstUpdateService service = new OrganizationContractMstUpdateService();

        // 画面のデータをmodelにセットする
        setupModel();

        // 削除処理のサービス呼び出し
        service.delete(this.model);

        // 削除完了メッセージの設定
        this.message = getMessage("IN0012", new String[] { "組織マスタ", "" });

        // 一覧画面を戻る
        return DELETE_OK;

    }

    /**
     * 登録/更新前チェック。<br>
     * <br>
     * @param なし
     * @return boolean
     * @throws Exception
     */
    private boolean errorCheck() throws Exception {

        OrganizationContractMstUpdateService service = new OrganizationContractMstUpdateService();

        // 関連チェック
        int chkResult = service.errorCheck(model);

        switch (chkResult) {
        // ｢住所（都道府県）｣＝”0000”の場合
        case OrganizationContractMstUpdateService.ERR_ADDRESS_PREFECTURE:
            this.addActionMessage(getMessage("EN0001", new String[] { " 住所（都道府県）" }));
            return true;

            // パスワードの複雑性のチェック
        case OrganizationContractMstUpdateService.ERR_PASSWORD_NG:
            this.addActionMessage(getMessage("EN0039", new String[] { "" }));
            return true;

            // ｢パスワード｣と｢パスワード確認｣の値が違う場合
        case OrganizationContractMstUpdateService.ERR_COUNTRY_COMP:
            this.addActionMessage(getMessage("EN0017", new String[] { "パスワード確認" }));
            return true;

            // ｢契約開始日｣　＞　｢契約終了日｣　の場合
        case OrganizationContractMstUpdateService.ERR_DATE:
            this.addActionMessage(getMessage("EN0011", new String[] { "契約開始日", "契約終了日" }));
            return true;

            // データが存在しない場合
        case OrganizationContractMstUpdateService.ERR_DATA_NONE:
            this.addActionMessage(getMessage("EN0020", new String[] { "組織マスタ", "" }));
            return true;

            // 受講者マスタの存在チェックを行う　※処理区分[削除]の場合
        case OrganizationContractMstUpdateService.ERR_STUDENT_DATA:
            this.addActionMessage(getMessage("EN0022", new String[] { "受講者マスタ" }));
            return true;
        }

        // 期間のチェック
        String[] msg = service.errorDateCheck(model);
        if (msg[0] != "") {
            this.addActionMessage(getMessage("EN0034",
                    new String[] { "連番の前後の契約期間(" + NaikaraStringUtil.converToYYYY_MM_DD(msg[0]) + "～"
                            + NaikaraStringUtil.converToYYYY_MM_DD(msg[1]) + ") の範囲内としてください。" }));
            return true;
        }

        // ドロップダウンリストの必須チェック
        if (model.getRequestAddressKbn().equals(NaikaraTalkConstants.REQUEST_ADDRESS_KBN_OFF)) {
            // 必須チェック
            String chkResultMsg = service.requiredstringCheck(model);
            if (!StringUtils.isEmpty(chkResultMsg)) {
                this.addActionMessage(getMessage("EN0001", new String[] { chkResultMsg }));
                return true;
            }
        }

        return false;
    }
}
