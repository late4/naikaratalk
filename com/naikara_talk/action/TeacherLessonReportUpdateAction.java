package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.TeacherLessonReportUpdateService;
import com.naikara_talk.sessiondata.SessionTeacherLesson;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>講師。<br>
 * <b>クラス名称　　　:</b>講師側の視点から_1-2_レッスン実績登録更新Actionクラス。<br>
 * <b>クラス概要　　　:</b>講師側の視点から_1-2_レッスン実績登録更新Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/12 TECS 新規作成。
 */
public class TeacherLessonReportUpdateAction extends TeacherLessonReportActionSupport {

    private static final long serialVersionUID = 1L;

    /** 入力チェックエラー */
    private static final int ERR_CHECK_NG = -1;

    /** 排他エラー */
    private static final int ERR_CHECK_EXCLUSION = -2;

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

        try {

            String mode = NaikaraTalkConstants.BRANK;

            int rtn = Integer.MIN_VALUE;

            // サービスの初期化
            TeacherLessonReportUpdateService service = new TeacherLessonReportUpdateService();

            // ******************************
            // スクールおよび講師
            // ******************************

            // コメント入力者区分
            this.model.setCmtInputsKbn(SessionUser.ROLE_TEACHER);

            int st = service.getExists(this.model);

            // データが存在しない場合
            if (st == NaikaraTalkConstants.RETURN_CD_DATA_NO) {

                mode = TeacherLessonReportUpdateService.INSERT_KBN;

                // モード
                this.model.setMode(mode);

                // 新規処理を実行
                rtn = this.insert();

                switch (rtn) {

                case ERR_CHECK_NG:

                    // 入力チェックエラー
                    transitionScreen = SUCCESS;

                    return transitionScreen;

                case INSERT_OK:

                    // 追加処理(正常)
                    transitionScreen = NEXT_PAGE_1;

                    break;

                }

            } else {

                mode = TeacherLessonReportUpdateService.UPDATE_KBN;

                // モード
                this.model.setMode(mode);

                // 更新処理を実行
                rtn = this.update();

                switch (rtn) {

                case ERR_CHECK_NG:

                    // 入力チェックエラー
                    transitionScreen = SUCCESS;

                    return transitionScreen;

                case ERR_CHECK_EXCLUSION:

                    // 排他エラー
                    transitionScreen = INPUT;

                    return transitionScreen;

                case UPDATE_OK:

                    // 更新処理(正常)1
                    transitionScreen = NEXT_PAGE_1;

                    break;

                }
            }

            // ******************************
            // スクールのみ
            // ******************************

            if (!StringUtils.equals(role, SessionUser.ROLE_TEACHER)) {

                // コメント入力者区分
                this.model.setCmtInputsKbn(NaikaraTalkConstants.AUTHORITY_S);

                int s = service.getExists(this.model);

                // データが存在しない場合
                if (s == NaikaraTalkConstants.RETURN_CD_DATA_NO) {

                    mode = TeacherLessonReportUpdateService.INSERT_KBN;

                    // モード
                    this.model.setMode(mode);

                    // 新規処理を実行
                    rtn = this.insert();

                    switch (rtn) {

                    case ERR_CHECK_NG:

                        // 入力チェックエラー
                        transitionScreen = SUCCESS;

                        return transitionScreen;

                    case INSERT_OK:

                        // 追加処理(正常)
                        transitionScreen = NEXT_PAGE_1;

                        break;

                    }

                } else {

                    mode = TeacherLessonReportUpdateService.UPDATE_KBN;

                    // モード
                    this.model.setMode(mode);

                    // 更新処理を実行
                    rtn = this.update();

                    switch (rtn) {

                    case ERR_CHECK_NG:

                        // 入力チェックエラー
                        transitionScreen = SUCCESS;

                        return transitionScreen;

                    case ERR_CHECK_EXCLUSION:

                        // 排他エラー
                        transitionScreen = INPUT;

                        return transitionScreen;

                    case UPDATE_OK:

                        // 更新処理(正常)1
                        transitionScreen = NEXT_PAGE_1;

                        break;

                    }
                }
            }

            // 更新完了メッセージの設定
            this.message = getMessage("IT0011", new String[] {});

        } catch (Exception e) {

            throw new NaiException(e);

        }

        if (transitionScreen == NEXT_PAGE_1) {

            // 正常の場合、MoveActionで登録した画面遷移を削除
            try {

                removeLatestActionList();

            } catch (Exception e) {

                throw new NaiException(e);
            }

            // 戻る用のsession情報の初期化
            // sessionSessionTeacherLessonのクリア
            SessionDataUtil.clearSessionData(SessionTeacherLesson.class.toString());
        }

        if (StringUtils.equals(pageId, pageId_hid)) {

            transitionScreen = NEXT_PAGE_1;

        } else {

            transitionScreen = NEXT_PAGE_2;
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
        TeacherLessonReportUpdateService service = new TeacherLessonReportUpdateService();

        // 新規処理のサービス呼び出し
        service.insert(this.model);

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
        TeacherLessonReportUpdateService service = new TeacherLessonReportUpdateService();

        // 更新処理のサービス呼び出し
        int cnt = service.update(this.model);

        if (cnt == 0) {

            String msg = NaikaraTalkConstants.BRANK;

            if (StringUtils.equals(role, SessionUser.ROLE_TEACHER)) {

                // 排他エラーメッセージの設定
                msg = getMessage("EE0014", new String[] {});

            } else {

                // 排他エラーメッセージの設定
                msg = getMessage("ES0014", new String[] {});
            }

            this.addActionMessage(msg);

            return ERR_CHECK_EXCLUSION;
        }

        return UPDATE_OK;
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
        TeacherLessonReportUpdateService service = new TeacherLessonReportUpdateService();

        // 関連チェック
        int chkResult = service.errorCheck(this.model);

        switch (chkResult) {

        case TeacherLessonReportUpdateService.NG_RADIO_1:

            this.addActionMessage(getMessage("ET0001", new String[] { "Self-Evaluation1", "自己評価１" }));

            return true;

        case TeacherLessonReportUpdateService.NG_RADIO_2:

            this.addActionMessage(getMessage("ET0001", new String[] { "Self-Evaluation2", "自己評価２" }));

            return true;

        case TeacherLessonReportUpdateService.NG_RADIO_3:

            this.addActionMessage(getMessage("ET0001", new String[] { "Recommended Level", "推奨レベル" }));

            return true;

        }

        return false;
    }
}