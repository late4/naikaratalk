package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.ContactMailSendSendService;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_初期登録<br>
 * <b>クラス名称       :</b>問合せ画面送信するActionクラス<br>
 * <b>クラス概要       :</b>メール送信をおこなう。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/30 TECS 新規作成
 */
public class ContactMailSendSendAction extends ContactMailSendActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 画面送信処理。<br>
     * <br>
     * 画面送信処理を行う。<br>
     * <br>
     * @param なし<br>
     * @return String<br>
     * @throws NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // Modelクラス設定
        setupModel();

        // Serviceクラス生成
        ContactMailSendSendService service = new ContactMailSendSendService();

        try {
            service.sendMail(model);
            // 前画面(引数)の｢遷移元画面ID｣ = ”StudentMyPage” 又は ”TeacherEvaluation” の場合
            // 送信が正常に完了した
            if (StringUtils.equals(NaikaraTalkConstants.STUDENT_MY_PAGE, this.frontPageId)
                    || StringUtils.equals(NaikaraTalkConstants.TEACHER_EVALUATION, this.frontPageId)) {
                this.message = (getMessage("IC0015", new String[] { "スクール" }));
            }
            // 前画面(引数)の｢遷移元画面ID｣ = ”OrganizationMyPage” の場合 送信が正常に完了した
            if (StringUtils.equals(NaikaraTalkConstants.ORGANIZATION_MY_PAGE, this.frontPageId)) {
                this.message = (getMessage("IB0015", new String[] { "スクール" }));
            }
        } catch (Exception e) {
            try {
                // 前画面(引数)の｢遷移元画面ID｣ = ”StudentMyPage” 又は ”TeacherEvaluation”
                // の場合 送信が異常終了した場合
                if (StringUtils.equals(NaikaraTalkConstants.STUDENT_MY_PAGE, this.frontPageId)
                        || StringUtils.equals(NaikaraTalkConstants.TEACHER_EVALUATION, this.frontPageId)) {
                    this.addActionMessage(getMessage("EC0042", new String[] { "メール送信処理" }));
                    return SUCCESS;
                }
                // 前画面(引数)の｢遷移元画面ID｣ = ”OrganizationMyPage” の場合 送信が異常終了した場合
                if (StringUtils.equals(NaikaraTalkConstants.ORGANIZATION_MY_PAGE, this.frontPageId)) {
                    this.addActionMessage(getMessage("EB0042", new String[] { "メール送信処理" }));
                    return SUCCESS;
                }
            } catch (Exception ex) {
                throw new NaiException(ex);
            }
        }

        this.closeFlg = NaikaraTalkConstants.TRUE;

        // 画面を返す
        return SUCCESS;
    }
}
