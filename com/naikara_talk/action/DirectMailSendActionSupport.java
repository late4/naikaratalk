package com.naikara_talk.action;

import java.util.ArrayList;
import java.util.List;

import com.naikara_talk.dto.StuMPoiOwnTDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.DirectMailSendModel;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>ダイレクトメール送信共通Actionクラス。<br>
 * <b>クラス概要       :</b>ダイレクトメール送信共通Action。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/22 TECS 新規作成
 */
public abstract class DirectMailSendActionSupport extends NaikaraActionSupport {

    private static final long serialVersionUID = 1L;

    // 画面表示のタイトル
    protected String title = "ダイレクトメール送信";

    // Help画面名
    protected String helpPageId = "HelpDirectMailSend.html";

    /**
     * サービスの呼び出しの実装。
     */
    abstract protected String requestService() throws NaiException;

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
        this.model.setSubject(this.subject);
        this.model.setMailContent(this.mailContent);
    }

    /** メッセージ */
    protected String message;

    /** 件名 */
    private String subject;

    /** 画面の｢メール本文｣ */
    private String mailContent;

    /** 戻るフラグ(jsp) */
    protected String pageFlg;

    /** 遷移元画面引数 resultList */
    protected List<StuMPoiOwnTDto> resultList = new ArrayList<StuMPoiOwnTDto>();

    /** 受講者IDリスト */
    protected List<String> studentIdLst = new ArrayList<String>();

    /** メールアドレス1リスト */
    protected List<String> mailAddrLst = new ArrayList<String>();

    /** 検索結果 */
    protected DirectMailSendModel model = new DirectMailSendModel();

    /** 遷移元画面引数 mailCheck_lst */
    protected String mailCheck_lst_tmp;

    /** closeFlg */
    protected String closeFlg;

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
     * @return subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject セットする subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return mailContent
     */
    public String getMailContent() {
        return mailContent;
    }

    /**
     * @param mailContent セットする mailContent
     */
    public void setMailContent(String mailContent) {
        this.mailContent = mailContent;
    }

    /**
     * @return pageFlg
     */
    public String getPageFlg() {
        return pageFlg;
    }

    /**
     * @param pageFlg セットする pageFlg
     */
    public void setPageFlg(String pageFlg) {
        this.pageFlg = pageFlg;
    }

    /**
     * @return resultList
     */
    public List<StuMPoiOwnTDto> getResultList() {
        return resultList;
    }

    /**
     * @param resultList セットする resultList
     */
    public void setResultList(List<StuMPoiOwnTDto> resultList) {
        this.resultList = resultList;
    }

    /**
     * @return studentIdLst
     */
    public List<String> getStudentIdLst() {
        return studentIdLst;
    }

    /**
     * @param studentIdLst セットする studentIdLst
     */
    public void setStudentIdLst(List<String> studentIdLst) {
        this.studentIdLst = studentIdLst;
    }

    /**
     * @return mailAddrLst
     */
    public List<String> getMailAddrLst() {
        return mailAddrLst;
    }

    /**
     * @param mailAddrLst セットする mailAddrLst
     */
    public void setMailAddrLst(List<String> mailAddrLst) {
        this.mailAddrLst = mailAddrLst;
    }

    /**
     * @return model
     */
    public DirectMailSendModel getModel() {
        return model;
    }

    /**
     * @param model セットする model
     */
    public void setModel(DirectMailSendModel model) {
        this.model = model;
    }

    /**
     * @return mailCheck_lst_tmp
     */
    public String getMailCheck_lst_tmp() {
        return mailCheck_lst_tmp;
    }

    /**
     * @param mailCheck_lst_tmp セットする mailCheck_lst_tmp
     */
    public void setMailCheck_lst_tmp(String mailCheck_lst_tmp) {
        this.mailCheck_lst_tmp = mailCheck_lst_tmp;
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
