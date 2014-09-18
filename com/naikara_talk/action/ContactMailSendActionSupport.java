package com.naikara_talk.action;

import java.util.LinkedHashMap;
import java.util.Map;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.ContactMailSendModel;
import com.naikara_talk.service.ContactMailSendLoadService;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_初期登録<br>
 * <b>クラス名称       :</b>問合せ画面Actionスーパークラス。<br>
 * <b>クラス概要       :</b>メール送信をおこなう。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/22 TECS 新規作成
 */
/**
 * @author s13001
 *
 */
public abstract class ContactMailSendActionSupport extends NaikaraActionSupport {

    private static final long serialVersionUID = 1L;

    // 画面表示のタイトル
    protected String title = "お問合せ";

    // Help画面名
    protected String helpPageId = "HelpContactMailSend.html";

    /**
     * お問合せ目的の再取得<br>
     * <br>
     * チェックエラーの場合、お問合せ目的の再取得。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception なし
     */
    @Override
    public void validate() {

        // チェックエラーの場合、お問合せ目的の再取得。
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
     * お問合せ目的を取得する。<br>
     * <br>
     * お問合せ目的を取得する。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    public void initRadio() throws Exception {

        ContactMailSendLoadService service = new ContactMailSendLoadService();
        // お問合せ目的を取得する
        this.codeCategoryContact_rdll = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_CONTACT);
    }

    /**
     * 画面のパラメータをモデルにセット。<br>
     * <br>
     * 画面のパラメータをモデルにセット。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception なし
     */
    public void setupModel() {
        this.model.setSubject(this.subject_txt);
        this.model.setManagFamilyJnm(this.managFamilyJnm_txt);
        this.model.setManagFirstJnm(this.managFirstJnm_txt);
        this.model.setTel(this.tel_txt);
        this.model.setManagMailAddress1(this.managMailAddress1_txt);
        this.model.setCodeCategoryContact(this.codeCategoryContact_rdl);
        this.model.setContactText(this.contactText_txa);
        this.model.setOrganizationId(this.organizationId);
        this.model.setFrontPageId(this.frontPageId);
        this.model.setConsSeq(this.consSeq);

    }

    /** メッセージ */
    protected String message;

    /** 組織ID／受講者ID(jsp) */
    protected String organizationId;

    /** お問合せの目的(jsp) */
    protected String codeCategoryContact_rdl;
    protected Map<String, String> codeCategoryContact_rdll = new LinkedHashMap<String, String>();

    /** 件名(jsp) */
    protected String subject_txt;

    /** お名前(姓)(jsp) */
    protected String managFamilyJnm_txt;

    /** お名前(名)(jsp) */
    protected String managFirstJnm_txt;

    /** 電話番号(jsp) */
    protected String tel_txt;

    /** メールアドレス(jsp) */
    protected String managMailAddress1_txt;

    /** ご意見・ご要望・お問合せ内容(jsp) */
    protected String contactText_txa;

    /** 遷移元画面ID(jsp) */
    protected String frontPageId;

    /** 戻るフラグ(jsp) */
    protected String pageFlg;

    /** 検索フラグ(jsp) */
    protected boolean searchFlg;

    /** 検索結果 */
    protected ContactMailSendModel model = new ContactMailSendModel();

    /** 連番 */
    protected int consSeq;

    /** メッセージ */
    protected String mailSendMessage;

    /** closeFlg */
    protected String closeFlg;

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            セットする title
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
     * @param helpPageId
     *            セットする helpPageId
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
     * @param message
     *            セットする message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return organizationId
     */
    public String getOrganizationId() {
        return organizationId;
    }

    /**
     * @param organizationId
     *            セットする organizationId
     */
    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    /**
     * @return codeCategoryContact_rdl
     */
    public String getCodeCategoryContact_rdl() {
        return codeCategoryContact_rdl;
    }

    /**
     * @param codeCategoryContact_rdl
     *            セットする codeCategoryContact_rdl
     */
    public void setCodeCategoryContact_rdl(String codeCategoryContact_rdl) {
        this.codeCategoryContact_rdl = codeCategoryContact_rdl;
    }

    /**
     * @return codeCategoryContact_rdll
     */
    public Map<String, String> getCodeCategoryContact_rdll() {
        return codeCategoryContact_rdll;
    }

    /**
     * @param codeCategoryContact_rdll
     *            セットする codeCategoryContact_rdll
     */
    public void setCodeCategoryContact_rdll(Map<String, String> codeCategoryContact_rdll) {
        this.codeCategoryContact_rdll = codeCategoryContact_rdll;
    }

    /**
     * @return subject_txt
     */
    public String getSubject_txt() {
        return subject_txt;
    }

    /**
     * @param subject_txt
     *            セットする subject_txt
     */
    public void setSubject_txt(String subject_txt) {
        this.subject_txt = subject_txt;
    }

    /**
     * @return managFamilyJnm_txt
     */
    public String getManagFamilyJnm_txt() {
        return managFamilyJnm_txt;
    }

    /**
     * @param managFamilyJnm_txt
     *            セットする managFamilyJnm_txt
     */
    public void setManagFamilyJnm_txt(String managFamilyJnm_txt) {
        this.managFamilyJnm_txt = managFamilyJnm_txt;
    }

    /**
     * @return managFirstJnm_txt
     */
    public String getManagFirstJnm_txt() {
        return managFirstJnm_txt;
    }

    /**
     * @param managFirstJnm_txt
     *            セットする managFirstJnm_txt
     */
    public void setManagFirstJnm_txt(String managFirstJnm_txt) {
        this.managFirstJnm_txt = managFirstJnm_txt;
    }

    /**
     * @return tel_txt
     */
    public String getTel_txt() {
        return tel_txt;
    }

    /**
     * @param tel_txt
     *            セットする tel_txt
     */
    public void setTel_txt(String tel_txt) {
        this.tel_txt = tel_txt;
    }

    /**
     * @return managMailAddress1_txt
     */
    public String getManagMailAddress1_txt() {
        return managMailAddress1_txt;
    }

    /**
     * @param managMailAddress1_txt
     *            セットする managMailAddress1_txt
     */
    public void setManagMailAddress1_txt(String managMailAddress1_txt) {
        this.managMailAddress1_txt = managMailAddress1_txt;
    }

    /**
     * @return contactText_txa
     */
    public String getContactText_txa() {
        return contactText_txa;
    }

    /**
     * @param contactText_txa
     *            セットする contactText_txa
     */
    public void setContactText_txa(String contactText_txa) {
        this.contactText_txa = contactText_txa;
    }

    /**
     * @return model
     */
    public ContactMailSendModel getModel() {
        return model;
    }

    /**
     * @param model
     *            セットする model
     */
    public void setModel(ContactMailSendModel model) {
        this.model = model;
    }

    /**
     * @return frontPageId
     */
    public String getFrontPageId() {
        return frontPageId;
    }

    /**
     * @param frontPageId
     *            セットする frontPageId
     */
    public void setFrontPageId(String frontPageId) {
        this.frontPageId = frontPageId;
    }

    /**
     * @return pageFlg
     */
    public String getPageFlg() {
        return pageFlg;
    }

    /**
     * @param pageFlg
     *            セットする pageFlg
     */
    public void setPageFlg(String pageFlg) {
        this.pageFlg = pageFlg;
    }

    /**
     * @return consSeq
     */
    public int getConsSeq() {
        return consSeq;
    }

    /**
     * @param consSeq セットする consSeq
     */
    public void setConsSeq(int consSeq) {
        this.consSeq = consSeq;
    }

    /**
     * @return searchFlg
     */
    public boolean getSearchFlg() {
        return searchFlg;
    }

    /**
     * @param searchFlg セットする searchFlg
     */
    public void setSearchFlg(boolean searchFlg) {
        this.searchFlg = searchFlg;
    }

    /**
     * @return mailSendMessage
     */
    public String getMailSendMessage() {
        return mailSendMessage;
    }

    /**
     * @param mailSendMessage セットする mailSendMessage
     */
    public void setMailSendMessage(String mailSendMessage) {
        this.mailSendMessage = mailSendMessage;
    }

    /**
     * @return closeFlg
     */
    public String getCloseFlg() {
        return closeFlg;
    }

    /**
     * @param closeFlg セットする closeFlg
     */
    public void setCloseFlg(String closeFlg) {
        this.closeFlg = closeFlg;
    }

}
