package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.ContactMailSendLoadService;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_初期登録<br>
 * <b>クラス名称       :</b>問合せ画面Actionクラス。<br>
 * <b>クラス概要       :</b>メール送信をおこなう。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/22 TECS 新規作成
 */
public class ContactMailSendLoadAction extends ContactMailSendActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 画面の初期表示。<br>
     * <br>
     * 画面の初期表示。<br>
     * <br>
     * @param なし<br>
     * @return String SUCCESS <br>
     * @exception NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        this.pageFlg = NaikaraTalkConstants.FALSE;

        ContactMailSendLoadService service = new ContactMailSendLoadService();

        SessionUser sessionUserData = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString()));
        if (!StringUtils.isEmpty(sessionUserData.getOrganizationSeq())) {
            // 連番
            this.consSeq = Integer.parseInt(sessionUserData.getOrganizationSeq());
        }

        setupModel();

        // お問合せ目的の初期取得。
        try {
            initRadio();
        } catch (Exception e) {
            throw new NaiException(e);
        }

        if (!StringUtils.isEmpty(this.message)) {
            this.addActionMessage(this.message);
        }
        if (!searchFlg) {
            // 前画面(引数)の｢遷移元画面ID｣ = ”StudentMyPage” (マイページ（お客様）) 又は
            // ”TeacherEvaluation”の場合
            if (StringUtils.equals(NaikaraTalkConstants.STUDENT_MY_PAGE, this.frontPageId)
                    || StringUtils.equals(NaikaraTalkConstants.TEACHER_EVALUATION, this.frontPageId)) {
                try {
                    // データが存在しない場合
                    if (NaikaraTalkConstants.RETURN_CD_DATA_NO == service.studentExists(model)) {
                        this.addActionMessage(getMessage("EB0020", new String[] {}));
                        return SUCCESS;
                    } else {
                        // データが存在する場合
                        // 表示データの取得処理
                        this.studentLoad();
                        return SUCCESS;
                    }
                } catch (Exception e) {
                    throw new NaiException(e);
                }
            }

            // 前画面(引数) の｢遷移元画面ID｣ = ”OrganizationMyPage” (組織マイページ) の場合
            if (StringUtils.equals(NaikaraTalkConstants.ORGANIZATION_MY_PAGE, this.frontPageId)) {
                try {

                    // データが存在しない場合
                    if (NaikaraTalkConstants.RETURN_CD_DATA_NO == service.organizationExists(model)) {
                        this.addActionMessage(getMessage("EB0020", new String[] {}));
                        return SUCCESS;
                    } else {
                        // データが存在する場合
                        // 表示データの取得処理
                        this.organizationLoad();
                        return SUCCESS;
                    }
                } catch (Exception e) {
                    throw new NaiException(e);
                }
            }
        }
        // 画面を返す
        return SUCCESS;

    }

    /**
     * 初期処理、表示データの取得。<br>
     * <br>
     * 初期処理、表示データの取得。<br>
     * <br>
     * @param なし<br>
     * @return なし <br>
     * @exception Exception
     */
    private void studentLoad() throws Exception {

        // 表示データを取得する
        ContactMailSendLoadService service = new ContactMailSendLoadService();
        this.model.setOrganizationId(this.organizationId);
        model = service.selectStudent(this.model);
        this.managFamilyJnm_txt = model.getManagFamilyJnm();
        this.managFirstJnm_txt = model.getManagFirstJnm();
        this.tel_txt = model.getTel();
        this.managMailAddress1_txt = model.getManagMailAddress1();

    }

    /**
     * 初期処理、表示データの取得。<br>
     * <br>
     * 初期処理、表示データの取得。<br>
     * <br>
     * @param なし<br>
     * @return なし <br>
     * @exception Exception
     */
    private void organizationLoad() throws Exception {

        // 表示データを取得する
        ContactMailSendLoadService service = new ContactMailSendLoadService();
        this.model.setOrganizationId(this.organizationId);
        model = service.selectOrganization(this.model);
        this.managFamilyJnm_txt = model.getManagFamilyJnm();
        this.managFirstJnm_txt = model.getManagFirstJnm();
        this.tel_txt = model.getTel();
        this.managMailAddress1_txt = model.getManagMailAddress1();

    }
}
