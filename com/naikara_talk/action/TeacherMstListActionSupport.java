package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.TeacherMstListModel;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>講師マスタメンテナンス(一覧)<br>
 * <b>クラス概要       :</b>講師マスタメンテナンス(一覧)共通Action<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/26 TECS 新規作成
 */
public abstract class TeacherMstListActionSupport extends NaikaraActionSupport {

    private static final long serialVersionUID = 1L;

    // 画面表示のタイトル
    protected String title = "講師マスタメンテナンス";

    // Help画面名
    protected String helpPageId = "HelpTeacherMstList.html";

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
        this.model.setProcessKbn(this.processKbn_rdl);
        this.model.setUserId(this.teacherId_txt);
        this.model.setUserKnm(this.teacherNm_txt);
    }

    /** メッセージ */
    protected String message;

    /** 処理区分(jsp) */
    protected String processKbn_rdl;

    /** 講師ID(jsp) */
    protected String teacherId_txt;

    /** 講師名(フリガナ)(jsp) */
    protected String teacherNm_txt;

    /** 検索結果 */
    protected TeacherMstListModel model = new TeacherMstListModel();

    /** 一覧から選択された明細データ(jsp) */
    protected String select_rdl;

    /** チェックエラー場合、検索判断フラグ */
    protected String hasSearchFlg;

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
     * @return processKbn_rdl
     */
    public String getProcessKbn_rdl() {
        return processKbn_rdl;
    }

    /**
     * @param processKbn_rdl
     *            セットする processKbn
     */
    public void setProcessKbn_rdl(String processKbn_rdl) {
        this.processKbn_rdl = processKbn_rdl;
    }

    /**
     * @return teacherId_txt
     */
    public String getTeacherId_txt() {
        return teacherId_txt;
    }

    /**
     * @param teacherId_txt
     *            セットする teacherId_txt
     */
    public void setTeacherId_txt(String teacherId_txt) {
        this.teacherId_txt = teacherId_txt;
    }

    /**
     * @return teacherNm_txt
     */
    public String getTeacherNm_txt() {
        return teacherNm_txt;
    }

    /**
     * @param teacherNm_txt
     *            セットする teacherNm_txt
     */
    public void setTeacherNm_txt(String teacherNm_txt) {
        this.teacherNm_txt = teacherNm_txt;
    }

    /**
     * @return model
     */
    public TeacherMstListModel getModel() {
        return model;
    }

    /**
     * @param model
     *            セットする model
     */
    public void setModel(TeacherMstListModel model) {
        this.model = model;
    }

    /**
     * @return select_rdl
     */
    public String getSelect_rdl() {
        return select_rdl;
    }

    /**
     * @param select_rdl
     *            セットする select_rdl
     */
    public void setSelect_rdl(String select_rdl) {
        this.select_rdl = select_rdl;
    }

    /**
     * @return hasSearchFlg
     */
    public String getHasSearchFlg() {
        return hasSearchFlg;
    }

    /**
     * @param hasSearchFlg
     *            セットする hasSearchFlg
     */
    public void setHasSearchFlg(String hasSearchFlg) {
        this.hasSearchFlg = hasSearchFlg;
    }
}
