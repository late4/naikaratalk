package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.TimeZoneControlMstListModel;
import com.naikara_talk.service.TimeZoneControlMstUpdateService;
import com.naikara_talk.sessiondata.SessionTimeManagMst;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守<br>
 * <b>クラス名称　　　:</b>時差管理マスタメンテナンス【単票】更新Actionクラス。<br>
 * <b>クラス概要　　　:</b>時差管理マスタメンテナンス【単票】更新Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/09 TECS 新規作成。
 */
public class TimeZoneControlMstUpdateAction extends TimeZoneControlMstActionSupport {

    private static final long serialVersionUID = 1L;

    /** メッセージ用使用テーブル名 */
    private static final String GTIME_MANAG_MST_JNM = "時差管理マスタ";

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
     * 登録/更新/削除処理。<br>
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
            initSelect();
        } catch (Exception e) {
            throw new NaiException(e);
        }

        // モデル設定
        setupModel();

        // 処理区分＝新規の場合('0':'新規','1':'修正','2':'削除','3':'照会')
        if (StringUtils.equals(TimeZoneControlMstListModel.PROS_KBN_ADD, this.processKbn_rdl)) {
            try {
                // 新規処理を実行
                int rtn = this.insert();
                switch (rtn) {
                case ERR_CHECK_NG:
                    // 入力チェックエラー
                    transitionScreen = SUCCESS;
                    break;
                case NaikaraTalkConstants.RETURN_CD_DATA_YES:
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

        // 処理区分＝修正の場合('0':'新規','1':'修正','2':'削除','3':'照会')
        if (StringUtils.equals(TimeZoneControlMstListModel.PROS_KBN_UPD, this.processKbn_rdl)) {
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

        // 処理区分＝削除の場合('0':'新規','1':'修正','2':'削除','3':'照会')
        if (StringUtils.equals(TimeZoneControlMstListModel.PROS_KBN_DEL, this.processKbn_rdl)) {
            try {
                // 更新処理を実行
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
                case ERR_CHECK_EXCLUSION:
                    // 排他エラー
                    transitionScreen = INPUT;
                    break;
                case DELETE_OK:
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
            // sessionSessionTimeZoneControlMstのクリア
            SessionDataUtil.clearSessionData(SessionTimeManagMst.class.toString());
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
        TimeZoneControlMstUpdateService service = new TimeZoneControlMstUpdateService();

        // 存在チェック
        if (NaikaraTalkConstants.RETURN_CD_DATA_NO == service.getExist(model)) {

            // 画面のデータをmodelにセットする
            setupModel();

            // 新規処理のサービス呼び出し
            service.insert(this.model);

            StringBuffer sb = new StringBuffer();
            sb.append("国コードは").append(this.countryCd_sell.get(this.model.getCountryCd_sel())).append("です。");
            sb.append("時差地域Noは").append(this.areaNoCd_sell.get(this.model.getAreaNoCd_sel())).append("です。");

            // 登録完了メッセージの設定
            this.message = getMessage("IN0010", new String[] { GTIME_MANAG_MST_JNM, sb.toString() });

            return INSERT_OK;

        } else {

            // データが存在するメッセージの設定
            String countryNm = this.countryCd_sell.get(this.countryCd_sel);
            String areaNm = this.areaNoCd_sell.get(this.areaNoCd_sel);
            this.addActionMessage(getMessage("EN0019", new String[] { "時差管理マスタ", "【" + countryNm + "、" + areaNm + "】" }));
            return NaikaraTalkConstants.RETURN_CD_DATA_YES;
        }
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
        TimeZoneControlMstUpdateService service = new TimeZoneControlMstUpdateService();

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
            this.message = getMessage("IN0011", new String[] { GTIME_MANAG_MST_JNM, "" });

            // 一覧画面を戻る
            return UPDATE_OK;

        } else {

            // データが不存在するメッセージの設定
            this.addActionMessage(getMessage("EN0020", new String[] { "時差管理マスタ", "" }));
            return NaikaraTalkConstants.RETURN_CD_DATA_NO;
        }
    }

    /**
     * 削除処理。<br>
     * <br>
     * @param なし
     * @return int
     * @throws Exception
     */
    private int delete() throws Exception {

        // サービス生成
        TimeZoneControlMstUpdateService service = new TimeZoneControlMstUpdateService();

        // 存在チェック
        if (NaikaraTalkConstants.RETURN_CD_DATA_YES == service.getExist(model)) {

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

            // 削除完了メッセージの設定
            this.message = getMessage("IN0012", new String[] { GTIME_MANAG_MST_JNM, "" });

            // 一覧画面を戻る
            return DELETE_OK;

        } else {

            // データが不存在するメッセージの設定
            this.addActionMessage(getMessage("EN0020", new String[] { "時差管理マスタ", "" }));
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

        TimeZoneControlMstUpdateService service = new TimeZoneControlMstUpdateService();

        // 関連チェック
        int chkResult = service.errorCheck(model);

        switch (chkResult) {

        // ｢国コード｣="0000"の場合
        case TimeZoneControlMstUpdateService.ERR_CONY_ALL:
            this.addActionMessage(getMessage("EN0001", new String[] { "国コード", "" }));
            return true;

            // ｢時差地域No｣="0000"の場合
        case TimeZoneControlMstUpdateService.ERR_AREA_NO_ALL:
            this.addActionMessage(getMessage("EN0001", new String[] { "時差地域No", "" }));
            return true;

            // サマータイム開始日 is Empty
        case TimeZoneControlMstUpdateService.ERR_SUM_START_DATE_EMPTY:
            this.addActionMessage(getMessage("EN0001", new String[] { "サマータイム(開始)", "" }));
            return true;

            // サマータイム終了日 is Empty
        case TimeZoneControlMstUpdateService.ERR_SUM_END_DATE_EMPTY:
            this.addActionMessage(getMessage("EN0001", new String[] { "サマータイム(終了)", "" }));
            return true;

            // サマータイム(時間)(符号) is Empty
        case TimeZoneControlMstUpdateService.ERR_SUM_TIME_FLG_EMPTY:
            this.addActionMessage(getMessage("EN0001", new String[] { "サマータイム(時間)(符号)", "" }));
            return true;

            // サマータイム(時間)(分) is Empty
        case TimeZoneControlMstUpdateService.ERR_SUM_TIME_MINUTES_EMPTY:
            this.addActionMessage(getMessage("EN0001", new String[] { "サマータイム(時間)(分)", "" }));
            return true;

            // サマータイム開始日 is not Empty
        case TimeZoneControlMstUpdateService.ERR_SUM_START_DATE_NOT_EMPTY:
            this.addActionMessage(getMessage("EN0053", new String[] { "サマータイムフラグ＝無の場合はサマータイム(開始)", "" }));
            return true;

            // サマータイム終了日 is not Empty
        case TimeZoneControlMstUpdateService.ERR_SUM_END_DATE_NOT_EMPTY:
            this.addActionMessage(getMessage("EN0053", new String[] { "サマータイムフラグ＝無の場合はサマータイム(終了)", "" }));
            return true;

            // サマータイム(時間)(分) is not Empty
        case TimeZoneControlMstUpdateService.ERR_SUM_TIME_MINUTES_NOT_EMPTY:
            this.addActionMessage(getMessage("EN0053", new String[] { "サマータイムフラグ＝無の場合はサマータイム(時間)(分)", "" }));
            return true;

            // 日付の整合性チェック (日付)
        case TimeZoneControlMstUpdateService.ERR_INTEGRITY_DT:
            this.addActionMessage(getMessage("EN0011", new String[] { "サマータイム(開始)", "サマータイム(終了)" }));
            return true;
        }

        return false;
    }

}
