package com.naikara_talk.action;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.mapper.ActionMapping;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.TeacherApproveRequestModel;
import com.naikara_talk.service.TeacherApproveRequestLoadService;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>講師<br>
 * <b>クラス名称       :</b>応相談回答ページ初期処理Actionクラス。<br>
 * <b>クラス概要       :</b>応相談回答ページの初期表示<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2014/06/02 TECS 新規作成
 */
public abstract class TeacherApproveRequestActionSupport extends NaikaraActionSupport {
    private static final long serialVersionUID = 1L;

    // 画面表示のタイトル
    protected String title = "Teacher Approve Request";

    // Help画面名
    protected String helpPageId = "HelpTeacherApproveRequest.html";

    /** 利用者ID(講師ID) */
    protected String userId;

    /** 講師ニックネーム */
    protected String userNm;

    /** メッセージ */
    protected String message;

    /** 回答区分 */
    protected String replyKbn_rdl;

    /** 回答区分リスト */
    protected Map<String, String> replyKbn_rdll = new LinkedHashMap<String, String>();

    /** 件名 */
    protected String subject_txt;

    /** メール本文 */
    protected String emailText_txa;

    /** 検索結果 */
    protected TeacherApproveRequestModel model = new TeacherApproveRequestModel();

    /** 一覧から選択された明細 */
    protected String[] select_chk;

    /** checkboxlist の Value値 */
    protected String[] checkValue;

    /**
     * ラジオボタンを初期化<br>
     * <br>
     * 画面ラジオボタンを初期化する<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception なし
     */
    @Override
    public void validate() {
        try {
            // 回答区分(OK/NG)
            this.initRadio();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * サービスの呼び出しの実装。
     */
    abstract protected String requestService() throws NaiException;

    /**
     * ラジオボタンの内容を取得<br>
     * <br>
     * 回答区分(OK/NG)を取得する<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    public void initRadio() throws Exception {

        // TeacherApproveRequestLoadServiceの生成
        TeacherApproveRequestLoadService service = new TeacherApproveRequestLoadService();

        // 回答区分(OK/NG)を取得
        this.replyKbn_rdll = service.getReplyKbn(
                NaikaraTalkConstants.CODE_CATEGORY_REPLY_KBN_T,
                NaikaraTalkConstants.CODE_CATEGORY_REPLY_KBN);

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
        // 使用者情報の取得
        SessionUser sessionUser = (SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString());
        String userId = "";
        String userNm = "";
        if (sessionUser != null) {
            userId = sessionUser.getUserId();
            userNm = sessionUser.getUserNm();
        }
        this.model.setUserId(userId);
        this.model.setUserNm(userNm);

        this.model.setReplyKbn(this.replyKbn_rdl);             // 回答(OK/NG)
        this.model.setSubject(this.subject_txt);               // 件名
        this.model.setEmailText(this.emailText_txa);           // メール本文
        this.model.setSelectReservationNo(this.select_chk);    // 一覧から選択された明細

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
     * @param message
     *            セットする message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId セットする userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return userNm
     */
    public String getUserNm() {
        return userNm;
    }

    /**
     * @param userNm セットする userNm
     */
    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    /**
     * @return replyKbn_rdl
     */
    public String getReplyKbn_rdl() {
        return replyKbn_rdl;
    }

    /**
     * @param replyKbn_rdl セットする replyKbn_rdl
     */
    public void setReplyKbn_rdl(String replyKbn_rdl) {
        this.replyKbn_rdl = replyKbn_rdl;
    }

    /**
     * @return replyKbn_rdll
     */
    public Map<String, String> getReplyKbn_rdll() {
        return replyKbn_rdll;
    }

    /**
     * @param replyKbn_rdll セットする replyKbn_rdll
     */
    public void setReplyKbn_rdll(Map<String, String> replyKbn_rdll) {
        this.replyKbn_rdll = replyKbn_rdll;
    }

    /**
     * @return subject_txt
     */
    public String getSubject_txt() {
        return subject_txt;
    }

    /**
     * @param subject_txt セットする subject_txt
     */
    public void setSubject_txt(String subject_txt) {
        this.subject_txt = subject_txt;
    }

    /**
     * @return emailText_txa
     */
    public String getEmailText_txa() {
        return emailText_txa;
    }

    /**
     * @param emailText_txa セットする emailText_txa
     */
    public void setEmailText_txa(String emailText_txa) {
        this.emailText_txa = emailText_txa;
    }

    /**
     * @return model
     */
    public TeacherApproveRequestModel getModel() {
        return model;
    }

    /**
     * @param model セットする model
     */
    public void setModel(TeacherApproveRequestModel model) {
        this.model = model;
    }

    /**
     * @return select_chk
     */
    public String[] getSelect_chk() {
        setCheckValue(this.select_chk);  // 選択があれば、その対象を選択した状態とする(Check ON)
        return select_chk;
    }

    /**
     * @param select_chk セットする select_chk
     */
    public void setSelect_chk(String[] select_chk) {
        this.select_chk = select_chk;
    }

    /**
     * 選択リセット（プロパティを初期化するためのメソッド「reset」）<br>
     * <br>
     * チェックBOXリセット<br>
     * <br>
     * @return select_chk
     */
    public void reset(ActionMapping mapping, HttpServletRequest request){
        this.select_chk = null;
    }

    /**
     * @return checkValue
     */
    public String[] getCheckValue() {
        return select_chk;
    }

    /**
     * @param checkValue セットする checkValue
     */
    public void setCheckValue(String[] checkValue) {
        this.checkValue = select_chk;
    }



}
