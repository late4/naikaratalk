package com.naikara_talk.action;

import java.io.File;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dto.TeacherCourseDto;
import com.naikara_talk.dto.TeacherCourseMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.TeacherMstCache;
import com.naikara_talk.service.TeacherMstUpdateService;
import com.naikara_talk.sessiondata.SessionTeacherMst;
import com.naikara_talk.sessiondata.SessionUserMstTeacherMst;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>講師マスタメンテナンス(単票)<br>
 * <b>クラス概要       :</b>講師マスタメンテナンス(単票)登録更新Action<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/07 TECS 新規作成
 */
public class TeacherMstUpdateAction extends TeacherMstActionSupport {

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

        // 性別、国籍、母国語、滞在国、時差地域Noの初期取得
        try {
            initRadio();
        } catch (Exception e) {
            throw new NaiException(e);
        }

        // モデル設定
        setupModel();

        // 処理判定＝新規処理の場合('新規処理','修正処理')
        if (StringUtils.equals(NaikaraTalkConstants.PROCESSKBN_INS, this.processDifference)) {
            try {
                // 新規処理を実行
                int rtn = this.insert();
                switch (rtn) {
                case ERR_CHECK_NG:
                    // 入力チェックエラー
                    transitionScreen = SUCCESS;
                    break;
                case ERR_CHECK_EXCLUSION:
                    // 排他エラー
                    transitionScreen = INPUT;
                case INSERT_OK:
                    // 追加処理(正常)
                    transitionScreen = NEXTPAGE;
                    break;
                }
            } catch (Exception e) {
                throw new NaiException(e);
            }

            // 処理判定＝修正処理の場合('新規処理','修正処理')
        } else if (StringUtils.equals(NaikaraTalkConstants.PROCESSKBN_UPD, this.processDifference)) {
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
        } else {
            try {
                this.addActionMessage(getMessage("EN0020", new String[] { "利用者マスタ、講師マスタ", "" }));
            } catch (Exception e) {
                throw new NaiException(e);
            }
            transitionScreen = INPUT;
        }

        // 更新成功の判断
        if (transitionScreen == NEXTPAGE) {
            SessionUserMstTeacherMst sessionData = (SessionUserMstTeacherMst) SessionDataUtil
                    .getSessionData(SessionUserMstTeacherMst.class.toString());
            // 講師画像はセッションに存在判断
            if (sessionData.getImgPhoto() != null) {
                File file = sessionData.getImgPhoto();
                file.delete();
            }
            // 正常の場合 MoveActionで登録した画面遷移を削除
            try {
                removeLatestActionList();
            } catch (Exception e) {
                throw new NaiException(e);
            }
            // キャッシュの内容をリフレッシュする
            TeacherMstCache mst = TeacherMstCache.getInstance();
            mst.reload();
            // 戻る用のsession情報の初期化
            // SessionTeacherMstのクリア
            SessionDataUtil.clearSessionData(SessionTeacherMst.class.toString());
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

        int cnt = NaikaraTalkConstants.RETURN_CD_ERR_INS;

        // 関連チェック
        if (errorCheck()) {
            return ERR_CHECK_NG;
        }

        // サービス生成
        TeacherMstUpdateService service = new TeacherMstUpdateService();

        // 画面のデータをmodelにセットする
        setupModel();

        // 新規処理のサービス呼び出し
        cnt = service.insert(this.model);
        if (NaikaraTalkConstants.RETURN_CD_ERR_NO_UPD == cnt) {
            // 排他エラーメッセージの設定
            String msg = getMessage("ES0014", new String[] { "", "" });
            this.addActionMessage(msg);
            return ERR_CHECK_EXCLUSION;
        }
        StringBuffer sb = new StringBuffer();
        sb.append("（講師＝").append(this.model.getUserId()).append(")");

        // メッセージ判定
        boolean teacherCourseYesFlg = false;
        for (TeacherCourseDto teacherCourseDto : model.getTeacherCourseDtoList()) {
            if (!StringUtils.isEmpty(teacherCourseDto.getCourseCd())) {
                teacherCourseYesFlg = true;
                break;
            }
        }

        // 登録完了メッセージの設定
        StringBuffer sbTblNm = new StringBuffer();
        sbTblNm.append("講師マスタ、講師別支払比率マスタ");
        if (teacherCourseYesFlg){
            sbTblNm.append("、講師別コースマスタ ");
        }

        this.message = getMessage("IN0010", new String[] { sbTblNm.toString(), sb.toString() });

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
        TeacherMstUpdateService service = new TeacherMstUpdateService();

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

        // メッセージ判定
        boolean teacherCourseYesFlg = false;
        for (TeacherCourseDto teacherCourseDto : model.getTeacherCourseDtoList()) {
            if (!StringUtils.isEmpty(teacherCourseDto.getCourseCd())) {
                teacherCourseYesFlg = true;
                break;
            }
        }

        // 更新完了メッセージの設定
        StringBuffer sbTblNm = new StringBuffer();
        sbTblNm.append("講師マスタ、講師別支払比率マスタ");
        if (teacherCourseYesFlg){
            sbTblNm.append("、講師別コースマスタ ");
        }

        this.message = getMessage("IN0010", new String[] { sbTblNm.toString(), "" });

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

        TeacherMstUpdateService service = new TeacherMstUpdateService();

        // 関連チェック
        int chkResult = service.errorCheck(model);

        switch (chkResult) {

        // 国籍のコード値＝”0000”の場合
        case TeacherMstUpdateService.ERR_NOT_SELECT_NATIONALIY_SEL:
            this.addActionMessage(getMessage("EN0001", new String[] { "国籍" }));
            return true;

            // 母国語のコード値＝”0000”の場合
        case TeacherMstUpdateService.ERR_NOT_SELECT_TONGUE_SEL:
            this.addActionMessage(getMessage("EN0001", new String[] { "母国語" }));
            return true;

            // 滞在国のコード値＝”0000”の場合
        case TeacherMstUpdateService.ERR_NOT_SELECT_RESIDENCE_SEL:
            this.addActionMessage(getMessage("EN0001", new String[] { "滞在国" }));
            return true;

            // 時差地域NOのコード値＝”0000”の場合
        case TeacherMstUpdateService.ERR_NOT_SELECT_REGIONNO_SEL:
            this.addActionMessage(getMessage("EN0001", new String[] { "時差地域NO" }));
            return true;

            // 生年月日：月 が日付ではない場合
        case TeacherMstUpdateService.ERR_NG_MONTH:
            this.addActionMessage(getMessage("EN0035", new String[] { "生年月日：月" }));
            return true;

            // 生年月日：日 が日付ではない場合
        case TeacherMstUpdateService.ERR_NG_DATE:
            this.addActionMessage(getMessage("EN0035", new String[] { "生年月日：日" }));
            return true;

            // 生年月日　＞　サーバーのシステム日付
        case TeacherMstUpdateService.ERR_FUTURE_DATE:
            this.addActionMessage(getMessage("EN0014", new String[] { "生年月日" }));
            return true;

            // ｢契約日｣　＞　｢契約期間：開始日｣　の場合
        case TeacherMstUpdateService.ERR_INTEGRITY_DT:
            this.addActionMessage(getMessage("EN0011", new String[] { "契約日", "契約期間：開始日" }));
            return true;

            // ｢契約期間：開始日｣　＞　｢契約期間：終了日｣　の場合
        case TeacherMstUpdateService.ERR_INTEGRITY_DATE:
            this.addActionMessage(getMessage("EN0011", new String[] { "契約期間：開始日", "契約期間：終了日" }));
            return true;

            // ｢提供可能コース｣のリスト内に重複するコースコードが存在する場合
        case TeacherMstUpdateService.ERR_COURSE_REPEAT:
            this.addActionMessage(getMessage("EN0018", new String[] { this.model.getCheckMessage() }));
            return true;

            // 講師画像ファイル名==なし && 講師画像：削除==true
        case TeacherMstUpdateService.ERR_IMGPHOTO:
            this.addActionMessage(getMessage("EN0002", new String[] { "講師画像：削除", "講師画像：ファイル名" }));
            return true;

            // ファイルサイズが５００．０ＫＢ以上の場合
        case TeacherMstUpdateService.ERR_IMGPHOTO_FIL:
            this.addActionMessage(getMessage("EN0065", new String[] { "500KByte" }));
            return true;

            // 時差管理マスタへのデータの存在しない場合
        case TeacherMstUpdateService.ERR_NOT_TIME_ZONE:
            this.addActionMessage(getMessage("EN0020", new String[] { "時差管理マスタ", "滞在国、時差地域No" }));
            return true;

            // (隠し項目：利用者マスタの) 利用期間と契約期間の範囲チェック
        case TeacherMstUpdateService.ERR_USE_DT:
            this.addActionMessage(getMessage("EN0054", new String[] { "契約期間", "利用者マスタの 利用期間" }));
            return true;

            // 講師支払比率の設定画面にて、1件もデータが保存されていない場合
        case TeacherMstUpdateService.ERR_ZERO_COUNT:
            this.addActionMessage(getMessage("EN0034", new String[] { "講師支払比率の設定不足" }));
            return true;

            // 契約期間と講師の歩合(支払比率)の範囲チェック
        case TeacherMstUpdateService.ERR_CONTRACT_DT:
            this.addActionMessage(getMessage("EN0054", new String[] { "講師の支払比率適用期間", "契約期間" }));
            return true;

            // コードが存在しない場合
        case TeacherMstUpdateService.ERR_COURSE_NOT_EXIST:
            this.addActionMessage(getMessage("EN0054", new String[] { "契約期間", "コースの期間" }));
            return true;

            // データが存在しない場合
        case TeacherMstUpdateService.ERR_USER_NOT_EXIST:
            this.addActionMessage(getMessage("EN0020", new String[] { "利用者マスタ", "" }));
            return true;

            // 処理判定：”新規” 且つ 講師マスタ．利用者ID ≠ NULL の場合
        case TeacherMstUpdateService.ERR_TEACHER_EXIST:
            this.addActionMessage(getMessage("EN0019", new String[] { "講師マスタ", "" }));
            return true;

            // 処理判定：”修正” 且つ 講師マスタ．利用者IDが NULL の場合
        case TeacherMstUpdateService.ERR_TEACHER_NOT_EXIST:
            this.addActionMessage(getMessage("EN0020", new String[] { "講師マスタ", "" }));
            return true;

            // 処理判定：”修正” 且つ 講師の契約開始日～終了日外に講師スケジュールにデータが存在する 場合
        case TeacherMstUpdateService.ERR_SCHEDULE_RESERVATION_TRN_YES:
            this.addActionMessage(getMessage("EN0034", new String[] { "講師スケジュールに契約開始日～終了日の期間外のデータが存在します。講師スケジュールを事前に修正してください。", "" }));
            return true;

        }

        return false;
    }

}
