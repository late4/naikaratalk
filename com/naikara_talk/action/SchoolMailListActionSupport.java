package com.naikara_talk.action;

import java.util.LinkedHashMap;
import java.util.Map;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.SchoolMailListModel;
import com.naikara_talk.service.CustomerManagedLoadService;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>スクールメール送信・受信履歴照会<br>
 * <b>クラス名称　　　:</b>スクールメール送信・受信履歴照会クラス。<br>
 * <b>クラス概要　　　:</b>スクールメール送信・受信履歴照会共通Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/12/16 TECS 新規作成。
 */

public abstract class SchoolMailListActionSupport extends NaikaraActionSupport {

    private static final long serialVersionUID = 1L;

    /** 画面表示のタイトル */
    protected String title = "スクールのメール送信・受信履歴照会";

    /** Help画面名 */
    protected String helpPageId = "HelpSchoolMailList.html";

    /** メッセージ */
    protected String message;

    /** 処理結果 */
    protected SchoolMailListModel model = new SchoolMailListModel();

    /** メール送信日(From) */
    protected String mailSendDtFr_txt;

    /** メール送信日(To) */
    protected String mailSendDtTo_txt;

    /** メールパターン */
    protected String mail_pattern_sel;
    protected Map<String, String> mail_pattern_sell = new LinkedHashMap<String, String>();

    /** 顧客区分 */
    protected String customer_rdl;
    protected Map<String, String> customer_rdll = new LinkedHashMap<String, String>();

    /** 受講者ID */
    protected String studentId_txt;

    /** 受講者名(ニックネーム) */
    protected String studentNm_txt;

    /** 受講者名(フリガナ) */
    protected String studentFuri_txt;

    /** 組織名 */
    protected String organization_txt;

    /**
     * メールパターン客区分、顧客区分の再取得<br>
     * <br>
     * チェックエラーの場合、メールパターン客区分、顧客区分の再取得。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception なし
     */
    @Override
    public void validate() {

        // チェックエラーの場合、メールパターン客区分、顧客区分の再取得。
        try {
            initRadio();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * サービスの呼び出しの実装。
     */
    abstract protected String requestService() throws NaiException;

    /**
     * メールパターン客区分、顧客区分の初期取得。<br>
     * <br>
     * @param なし
     * @return void
     * @throws Exception
     */
    public void initRadio() throws NaiException {

        CustomerManagedLoadService service = new CustomerManagedLoadService();

        try {

            // メールパターンを取得する
            this.mail_pattern_sell = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_TMAIL_PATTERN_NM);

            // (顧客区分 + 法人責任者 + 講師)を取得する
            this.customer_rdll = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_CUSTOMER_OTHER_KBN);
        } catch (Exception e) {
            throw new NaiException(e);
        }
    }

    /**
     * 画面のパラメータをモデルにセット。<br>
     * <br>
     * @param なし
     * @return なし
     */
    public void setupModel() {
        // メール送信日 (From)
        this.model.setMailSendDtFr(NaikaraStringUtil.delSlash(this.mailSendDtFr_txt));
        // メール送信日(To)
        this.model.setMailSendDtTo(NaikaraStringUtil.delSlash(this.mailSendDtTo_txt));
        // メールパターン
        this.model.setMailPatternCd(this.mail_pattern_sel);
        // 顧客区分
        this.model.setCustomerKbn(this.customer_rdl);
        // 受講者ID
        this.model.setStudentId(this.studentId_txt);
        // 受講者名(ニックネーム)
        this.model.setNickNm(this.studentNm_txt);
        // 受講者名(フリガナ)
        this.model.setStudentKnm(this.studentFuri_txt);
        // 組織名
        this.model.setOrganizationNm(this.organization_txt);
    }

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title セットする title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return helpPageId
     */
    public String getHelpPageId() {
        return helpPageId;
    }

    /**
     * @param helpPageId セットする helpPageId
     */
    public void setHelpPageId(String helpPageId) {
        this.helpPageId = helpPageId;
    }

    /**
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message セットする message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return model
     */
    public SchoolMailListModel getModel() {
        return model;
    }

    /**
     * @param model セットする model
     */
    public void setModel(SchoolMailListModel model) {
        this.model = model;
    }

    /**
     * @return mailSendDtFr_txt
     */
    public String getMailSendDtFr_txt() {
        return mailSendDtFr_txt;
    }

    /**
     * @param mailSendDtFr_txt セットする mailSendDtFr_txt
     */
    public void setMailSendDtFr_txt(String mailSendDtFr_txt) {
        this.mailSendDtFr_txt = mailSendDtFr_txt;
    }

    /**
     * @return mailSendDtTo_txt
     */
    public String getMailSendDtTo_txt() {
        return mailSendDtTo_txt;
    }

    /**
     * @param mailSendDtTo_txt セットする mailSendDtTo_txt
     */
    public void setMailSendDtTo_txt(String mailSendDtTo_txt) {
        this.mailSendDtTo_txt = mailSendDtTo_txt;
    }

    /**
     * @return mail_pattern_sel
     */
    public String getMail_pattern_sel() {
        return mail_pattern_sel;
    }

    /**
     * @param mail_pattern_sel セットする mail_pattern_sel
     */
    public void setMail_pattern_sel(String mail_pattern_sel) {
        this.mail_pattern_sel = mail_pattern_sel;
    }

    /**
     * @return mail_pattern_sell
     */
    public Map<String, String> getMail_pattern_sell() {
        return mail_pattern_sell;
    }

    /**
     * @param mail_pattern_sell セットする mail_pattern_sell
     */
    public void setMail_pattern_sell(Map<String, String> mail_pattern_sell) {
        this.mail_pattern_sell = mail_pattern_sell;
    }

    /**
     * @return customer_rdl
     */
    public String getCustomer_rdl() {
        return customer_rdl;
    }

    /**
     * @param customer_rdl セットする customer_rdl
     */
    public void setCustomer_rdl(String customer_rdl) {
        this.customer_rdl = customer_rdl;
    }

    /**
     * @return customer_rdll
     */
    public Map<String, String> getCustomer_rdll() {
        return customer_rdll;
    }

    /**
     * @param customer_rdll セットする customer_rdll
     */
    public void setCustomer_rdll(Map<String, String> customer_rdll) {
        this.customer_rdll = customer_rdll;
    }

    /**
     * @return studentId_txt
     */
    public String getStudentId_txt() {
        return studentId_txt;
    }

    /**
     * @param studentId_txt セットする studentId_txt
     */
    public void setStudentId_txt(String studentId_txt) {
        this.studentId_txt = studentId_txt;
    }

    /**
     * @return studentNm_txt
     */
    public String getStudentNm_txt() {
        return studentNm_txt;
    }

    /**
     * @param studentNm_txt セットする studentNm_txt
     */
    public void setStudentNm_txt(String studentNm_txt) {
        this.studentNm_txt = studentNm_txt;
    }

    /**
     * @return studentFuri_txt
     */
    public String getStudentFuri_txt() {
        return studentFuri_txt;
    }

    /**
     * @param studentFuri_txt セットする studentFuri_txt
     */
    public void setStudentFuri_txt(String studentFuri_txt) {
        this.studentFuri_txt = studentFuri_txt;
    }

    /**
     * @return organization_txt
     */
    public String getOrganization_txt() {
        return organization_txt;
    }

    /**
     * @param organization_txt セットする organization_txt
     */
    public void setOrganization_txt(String organization_txt) {
        this.organization_txt = organization_txt;
    }
}
