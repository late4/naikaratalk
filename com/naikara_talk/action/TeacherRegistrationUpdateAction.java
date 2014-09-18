package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.TeacherMstCache;
import com.naikara_talk.service.TeacherRegistrationUpdateService;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>講師初期登録ページ。<br>
 * <b>クラス名称       :</b>講師初期登録ページ初期処理Actionクラス。<br>
 * <b>クラス概要       :</b>講師初期登録ページ初期処理Action。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/01 TECS 新規作成
 */
public class TeacherRegistrationUpdateAction extends TeacherRegistrationActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 更新ボタン押下<br>
     * <br>
     *
     * @param なし<br>
     * @return SUCCESS String <br>
     * @throws NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // 更新処理のサービス呼び出し
        TeacherRegistrationUpdateService service = new TeacherRegistrationUpdateService();

        // 画面のデータをmodelにセットする
        setupModel();

        int cnt;
        try {

            // 関連チェック
            if (errorCheck()) {
                return SUCCESS;
            }

            // 利用者マスタ更新
            cnt = service.update(this.model);

            if (cnt == NaikaraTalkConstants.RETURN_CD_ERR_NO_UPD) {
                // 排他エラーメッセージの設定
                String msg = "";
                if (SessionUser.ROLE_TEACHER == ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class
                        .toString())).getRole()) {
                    msg = getMessage("EE0014", new String[] { "", "" });
                } else {
                    msg = getMessage("ES0014", new String[] { "", "" });
                }
                this.addActionMessage(msg);
                return SUCCESS;
            } else {
                // キャッシュの内容をリフレッシュする
                TeacherMstCache mst = TeacherMstCache.getInstance();
                mst.reload();
                this.message = getMessage("IT0011", new String[] { "", "" });
            }
        } catch (Exception e) {
            throw new NaiException(e);
        }

        return NEXTPAGE;
    }

    /**
     * 更新前チェック。<br>
     * <br>
     * @param なし
     * @return boolean
     * @throws Exception
     */
    private boolean errorCheck() throws Exception {

        // 更新処理のサービス呼び出し
        TeacherRegistrationUpdateService service = new TeacherRegistrationUpdateService();

        // 関連チェック
        int chkResult = service.errorCheck(model);

        switch (chkResult) {

        // ｢滞在国｣＝”0000”の場合
        case TeacherRegistrationUpdateService.ERR_COUNTRY:
            this.addActionMessage(getMessage("ET0001", new String[] { "Country of Residence", " 滞在国" }));
            return true;

            // ｢時差地域No.｣＝”0000”の場合
        case TeacherRegistrationUpdateService.ERR_AREA_NO:
            this.addActionMessage(getMessage("ET0001", new String[] { "Time Zone Area No.", "時差地域No." }));
            return true;

            // ｢滞在国｣、「時差地域No」が入力されている場合、データの存在チェックを行う （組み合わせチェック）の場合
        case TeacherRegistrationUpdateService.ERR_COUNTRY_AREA:
            this.addActionMessage(getMessage("ET0058", new String[] { "", "" }));
            return true;

            // ｢滞在国｣、「時差地域No」が入力されている場合、データの存在チェックを行う （組み合わせチェック）の場合
        case TeacherRegistrationUpdateService.IMGPHOTO_FIL:
            this.addActionMessage(getMessage("ET0065", new String[] { "500KByte", "500KByte" }));
            return true;
        }

        return false;
    }
}
