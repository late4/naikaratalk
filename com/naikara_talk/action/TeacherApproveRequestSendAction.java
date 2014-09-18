package com.naikara_talk.action;

import org.apache.commons.lang3.ArrayUtils;

import com.naikara_talk.exception.NaiException;

import com.naikara_talk.service.TeacherApproveRequestLoadService;
import com.naikara_talk.service.TeacherApproveRequestSendService;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>講師<br>
 * <b>クラス名称       :</b>応相談回答ページメール送信およびＤＢ更新Actionクラス<br>
 * <b>クラス概要       :</b>応相談回答ページメール送信およびＤＢ更新処理を行う。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2014/06/02 TECS 新規作成
 */
public class TeacherApproveRequestSendAction extends TeacherApproveRequestActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * メール送信およびＤＢ更新処理。<br>
     * <br>
     * メール送信およびＤＢ更新処理を行う。<br>
     * <br>
     * @param なし<br>
     * @return String<br>
     * @throws NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // ◆◆◆ 画面からModelへ値の格納
        this.setupModel();

        // Serviceクラス生成
        TeacherApproveRequestSendService service = new TeacherApproveRequestSendService();

        try {

            // ◆◆◆ 回答送信対象者の選択チェック
            if (ArrayUtils.isEmpty(this.model.getSelectReservationNo())) {
                // 回答送信対象者の未選択エラー
                this.addActionMessage(getMessage("ET0015", new String[] { "Student", "受講者" }));
                return SUCCESS;
            } else {
                String[] sekectChk = this.model.getSelectReservationNo();
                if (sekectChk == null) {
                    // 回答送信対象者の未選択エラー
                    this.addActionMessage(getMessage("ET0015", new String[] { "Student", "受講者" }));
                    return SUCCESS;
                } else {
                    boolean selectYesFlg = false;
                    String selectChkStr = NaikaraTalkConstants.BRANK;
                    for (int i = 0, n = this.model.getSelectReservationNo().length; i < n; i++) {
                        selectChkStr = this.model.getSelectReservationNo()[i].trim();
                        if (selectChkStr != null && !NaikaraTalkConstants.BRANK.equals(selectChkStr)) {
                            selectYesFlg = true;
                            break;
                        }
                    }
                    if (selectYesFlg == false) {
                        // 回答送信対象者の未選択エラー
                        //this.setMessage(getMessage("ET0015", new String[] { "Student", "受講者" }));
                        this.addActionMessage(getMessage("ET0015", new String[] { "Sel(Student)", "選択(受講者)" }));
                        return SUCCESS;
                    }
                }
            }

            // ◆◆◆ メール送信と講師予定予約テーブルの更新処理
            int chkResult = service.sendMailAndDbUpdate(model);

            switch (chkResult) {

            case TeacherApproveRequestSendService.NO_SETTING_TEACHER_MAIL_ADDRESS:
                // 利用者マスタ＜講師＞：メール送信先なし
                this.addActionMessage(getMessage("ET0035", new String[] { "Teacher E-mail Address", "講師メールアドレス" }));
                return SUCCESS;

            case TeacherApproveRequestSendService.NO_SETTING_STUDENT_MAIL_ADDRESS:
                // 受講者マスタ＜受講者＞：メール送信先なし
                this.addActionMessage(getMessage("ET0035", new String[] { "Student E-mail Address", "受講者メールアドレス" }));
                return SUCCESS;

            case TeacherApproveRequestSendService.NO_DATA_SCHEDULE_RESERVATION_TRN:
                // 講師予定予約テーブル更新対象なし
                this.addActionMessage(getMessage("ET0020", new String[] {}));
                return SUCCESS;

            case TeacherApproveRequestSendService.NO_UPD_SCHEDULE_RESERVATION_TRN:
                // 講師予定予約テーブル更新処理失敗
                this.addActionMessage(getMessage("EE0006", new String[] {}));
                return SUCCESS;

            case TeacherApproveRequestSendService.COM_LESSON_RESERVE_CANCEL:
                // 共通部品：レッスンの予約・取消処理 失敗
                this.addActionMessage(getMessage("EE0006", new String[] {}));
                return SUCCESS;

            case TeacherApproveRequestSendService.PROCCES1_YES:
                // ◆◆◆ 初期処理
                this.initRadio();           // 回答区分-選択値
                this.replyKbn_rdl = "";     // 回答区分-選択なし
                this.subject_txt = "";      // 件名
                this.emailText_txa = "";    // メール本文
                // ◆◆◆ 講師予定予約テーブルより再検索
                // TeacherApproveRequestLoadServiceの生成
                TeacherApproveRequestLoadService loadService = new TeacherApproveRequestLoadService();
                this.model = loadService.select(this.model);
                // ◆◆◆ 処理正常メッセージ
                this.addActionMessage(getMessage("IT0014", new String[] { "Send", "送信" }));
                return SUCCESS;
            }

        } catch (Exception e) {
            try {
                // 異常終了した場合
                this.addActionMessage(getMessage("EE0015", new String[] {}));
            } catch (Exception ex) {
                throw new NaiException(ex);
            }
        }

        // ◆◆◆ 返却
        return SUCCESS;
    }
}
