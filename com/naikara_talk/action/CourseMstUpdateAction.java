package com.naikara_talk.action;

import java.io.File;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.CourseMstListModel;
import com.naikara_talk.service.CourseMstUpdateService;
import com.naikara_talk.sessiondata.SessionCourseMst;
import com.naikara_talk.sessiondata.SessionCourseMstGoodsMst;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>コースマスタメンテナンス(単票)<br>
 * <b>クラス概要       :</b>コースマスタメンテナンス(単票)登録更新Action<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/14 TECS 新規作成
 */
public class CourseMstUpdateAction extends CourseMstActionSupport {

    private static final long serialVersionUID = 1L;

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

        // 大分類、中分類、小分類、添付付き有無フラグ、該当書籍有無フラグの初期取得
        try {
            initRadio();
        } catch (Exception e) {
            throw new NaiException(e);
        }

        // モデル設定
        setupModel();

        // 処理区分＝新規の場合('0':'新規','1':'修正','2':'照会')
        if (StringUtils.equals(CourseMstListModel.PROS_KBN_ADD, this.processKbn_rdl)) {
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
        if (StringUtils.equals(CourseMstListModel.PROS_KBN_UPD, this.processKbn_rdl)) {
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

        // 更新成功の判断
        if (transitionScreen == NEXTPAGE) {
            SessionCourseMstGoodsMst sessionData = (SessionCourseMstGoodsMst) SessionDataUtil
                    .getSessionData(SessionCourseMstGoodsMst.class.toString());
            // 詳細説明6(PDF)はセッションに存在判断
            if (sessionData.getExplanation6() != null) {
                File file = sessionData.getExplanation6();
                file.delete();
            }
            // 正常の場合 MoveActionで登録した画面遷移を削除
            try {
                removeLatestActionList();
            } catch (Exception e) {
                throw new NaiException(e);
            }
            // 戻る用のsession情報の初期化
            // SessionCourseMstのクリア
            SessionDataUtil.clearSessionData(SessionCourseMst.class.toString());
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
        CourseMstUpdateService service = new CourseMstUpdateService();

        // 画面のデータをmodelにセットする
        setupModel();

        // 新規処理のサービス呼び出し
        service.insert(this.model);

        StringBuffer sb = new StringBuffer();
        sb.append("（コース名＝").append(this.model.getCourseJnm()).append(")");
        // 登録完了メッセージの設定
        this.message = getMessage("IN0010", new String[] { "コースマスタ", sb.toString() });

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
        CourseMstUpdateService service = new CourseMstUpdateService();

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
        this.message = getMessage("IN0011", new String[] { "コースマスタ", "" });

        // 一覧画面を戻る
        return UPDATE_OK;
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

        CourseMstUpdateService service = new CourseMstUpdateService();

        // 関連チェック
        int chkResult = service.errorCheck(model);

        switch (chkResult) {

        // 大分類のコード値＝”0000”の場合
        case CourseMstUpdateService.ERR_NOT_SELECT_COURSELARGE_SEL:
            this.addActionMessage(getMessage("EN0001", new String[] { "大分類" }));
            return true;

            // 中分類のコード値＝”0000”の場合
        case CourseMstUpdateService.ERR_NOT_SELECT_COURSEMEDIUM_SEL:
            this.addActionMessage(getMessage("EN0001", new String[] { "中分類" }));
            return true;

            // 小分類のコード値＝”0000”の場合
        case CourseMstUpdateService.ERR_NOT_SELECT_COURSESMALL_SEL:
            this.addActionMessage(getMessage("EN0001", new String[] { "小分類" }));
            return true;

            // ｢提供期間：開始日｣　＞　｢提供期間：終了日｣　の場合
        case CourseMstUpdateService.ERR_INTEGRITY_DT:
            this.addActionMessage(getMessage("EN0011", new String[] { "提供期間：開始日", "提供期間：終了日" }));
            return true;

            // (更新前)｢提供期間：開始日｣　＜　サーバーのシステム日付＋２８日　　の場合
        case CourseMstUpdateService.ERR_OLD_USESTRDT:
            this.addActionMessage(getMessage(
                    "EN0074",
                    new String[] { "提供期間：開始日（" + this.old_utilizationStart_txt + ")",
                            NaikaraStringUtil.converToYYYY_MM_DD(DateUtil.getDateAddDay(DateUtil.getSysDate(), 28)) }));
            return true;

            // ｢提供期間：終了日｣　＜　サーバーのシステム日付＋２８日　の場合
        case CourseMstUpdateService.ERR_USEENDDT:
            this.addActionMessage(getMessage(
                    "EN0011",
                    new String[] {
                            NaikaraStringUtil.converToYYYY_MM_DD(DateUtil.getDateAddDay(DateUtil.getSysDate(), 28)),
                            "提供期間：終了日（" + this.utilizationEnd_txt + ")" }));
            return true;

            // コース別利用ポイント設定画面にて、1件もデータが保存されていない場合
        case CourseMstUpdateService.ERR_ZERO_COUNT:
            this.addActionMessage(getMessage("EN0034", new String[] { "コース別利用ポイントの設定不足" }));
            return true;

            // 提供期間とコース別利用ポイントの整合性チェック
        case CourseMstUpdateService.ERR_CONTRACT_DT:
            this.addActionMessage(getMessage("EN0034", new String[] { this.model.getCheckMessage() }));
            return true;

            // 提供期間と該当商品の期間の範囲チェック
            // 販売商品マスタ．提供開始日≦画面の「提供終了日」
        case CourseMstUpdateService.ERR_GOODS_STR_DATE:
            this.addActionMessage(getMessage("EN0011", new String[] {
                    "該当商品" + this.model.getCheckMessage() + "の提供期間：開始日", "コースマスタメンテナンス画面の提供期間：終了日" }));
            return true;

            // 販売商品マスタ．提供終了日≧画面の「提供開始日」
        case CourseMstUpdateService.ERR_GOODS_END_DATE:
            this.addActionMessage(getMessage("EN0011",
                    new String[] { "コースマスタメンテナンス画面の提供期間：開始日", "該当商品" + this.model.getCheckMessage() + "の提供期間：終了日" }));
            return true;

            // データの存在チェック(新規)
        case CourseMstUpdateService.ERR_CHECK_COURSECD_INS:
            this.addActionMessage(getMessage("EN0019", new String[] { "コースマスタ", this.courseCode_txt }));
            return true;

            // データの存在チェック(修正)
        case CourseMstUpdateService.ERR_CHECK_COURSECD_UPD:
            this.addActionMessage(getMessage("EN0020", new String[] { "コースマスタ", "" }));
            return true;

            // 詳細説明６：ファイル名==なし && 詳細説明６：削除==true
        case CourseMstUpdateService.ERR_FILE:
            this.addActionMessage(getMessage("EN0002", new String[] { "詳細説明６：削除", "詳細説明６：ファイル名" }));
            return true;

            // ファイルサイズが１５．０ＭＢ以上の場合
        case CourseMstUpdateService.ERR_FILE_MAX:
            this.addActionMessage(getMessage("EN0065", new String[] { "15MByte" }));
            return true;

            // ｢該当商品｣が複数設定されている場合は、重複チェック
        case CourseMstUpdateService.ERR_GOODS_REPEAT:
            this.addActionMessage(getMessage("EN0018", new String[] { this.model.getCheckMessage() }));
            return true;
        }

        return false;
    }

}
