package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.PointControlListModel;
import com.naikara_talk.service.PointControlUpdateService;
import com.naikara_talk.sessiondata.SessionPointControl;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>ポイント管理マスタメンテナンス(単票)<br>
 * <b>クラス概要       :</b>ポイント管理マスタメンテナンス(単票)登録更新Action<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/01 TECS 新規作成
 */
public class PointControlUpdateAction extends PointControlActionSupport {

    private static final long serialVersionUID = 1L;

    /** メッセージ用使用テーブル名 */
    private static final String POINT_MST_TBL_JNM = "ポイント管理マスタ";

    /** 入力チェックエラー */
    private static final int ERR_CHECK_NG = -1;

    /** 排他エラー */
    private static final int ERR_CHECK_EXCLUSION = -2;

    /** 追加処理(正常) */
    private static final int INSERT_OK = 4;

    /** 更新処理(正常) */
    private static final int UPDATE_OK = 5;

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

        // 通常月謝区分の初期取得
        try {
            initRadio();
        } catch (Exception e) {
            throw new NaiException(e);
        }

        // モデル設定
        setupModel();

        // 処理区分＝新規の場合('0':'新規','1':'修正','2':'照会')
        if (StringUtils.equals(PointControlListModel.PROS_KBN_ADD, this.processKbn_rdl)) {
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

        // 処理区分＝修正の場合('0':'新規','1':'修正','2':'照会')
        if (StringUtils.equals(PointControlListModel.PROS_KBN_UPD, this.processKbn_rdl)) {
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
            // SessionPointControlのクリア
            SessionDataUtil.clearSessionData(SessionPointControl.class.toString());
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
     * @return int ERR_CHECK_NG or INSERT_OK <br>
     * @exception Exception
     */
    private int insert() throws Exception {

        // 関連チェック
        if (errorCheck()) {
            return ERR_CHECK_NG;
        }

        // サービス生成
        PointControlUpdateService service = new PointControlUpdateService();

        // 画面のデータをmodelにセットする
        setupModel();

        // 新規処理のサービス呼び出し
        service.insert(this.model);

        StringBuffer sb = new StringBuffer();
        sb.append("ポイントコードは").append(this.model.getPointCd()).append("です。");

        // 登録完了メッセージの設定
        this.message = getMessage("IN0010", new String[] { POINT_MST_TBL_JNM, sb.toString() });

        return INSERT_OK;
    }

    /**
     * 更新処理。<br>
     * <br>
     * 更新処理。<br>
     * <br>
     * @param なし<br>
     * @return int ERR_CHECK_NG or ERR_CHECK_EXCLUSION or INSERT_OK <br>
     * @exception Exception
     */
    private int update() throws Exception {

        // 関連チェック
        if (errorCheck()) {
            return ERR_CHECK_NG;
        }

        // サービス生成
        PointControlUpdateService service = new PointControlUpdateService();

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
            this.message = getMessage("IN0011", new String[] { POINT_MST_TBL_JNM, "" });

            // 一覧画面を戻る
            return UPDATE_OK;

        } else {

            // データが不存在するメッセージの設定
            this.addActionMessage(getMessage("EN0020", new String[] { "ポイント管理マスタ", "" }));
            return NaikaraTalkConstants.RETURN_CD_DATA_NO;
        }
    }

    /**
     * 登録/更新前チェック。<br>
     * <br>
     * 登録/更新前チェック。<br>
     * <br>
     * @param なし<br>
     * @return boolean チェック結果 <br>
     * @exception Exception
     */
    private boolean errorCheck() throws Exception {

        PointControlUpdateService service = new PointControlUpdateService();

        // 関連チェック
        int chkResult = service.errorCheck(model);

        switch (chkResult) {

        // 金額(税込)／有償ポイント < 1000 の場合
        case PointControlUpdateService.ERR_MIN_MONEY:
            this.addActionMessage(getMessage("EN0007", new String[] { "金額(税込)／有償ポイント", "1000円以上" }));
            return true;

            // 有償ポイント期限 < 1 の場合
        case PointControlUpdateService.ERR_MIN_TIM:
            this.addActionMessage(getMessage("EN0007", new String[] { "有償ポイント期限", "1ヶ月以上" }));
            return true;

            // 通常月謝区分＝月謝 有償ポイント期限 != 1 の場合
        case PointControlUpdateService.ERR_ONE_FEEKBN:
            this.addActionMessage(getMessage("EN0056",
                    new String[] { "通常月謝区分", this.monthlyKbn_rdll.get(this.monthlyKbn_rdl), "有償ポイント期限に1ヶ月を設定" }));
            return true;

            // 日付の整合性チェック (日付)
        case PointControlUpdateService.ERR_INTEGRITY_DT:
            this.addActionMessage(getMessage("EN0011", new String[] { "提供期間：開始日", "提供期間：終了日" }));
            return true;
        }

        return false;
    }

}
