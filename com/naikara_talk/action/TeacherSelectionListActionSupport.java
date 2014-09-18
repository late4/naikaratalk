package com.naikara_talk.action;

import com.naikara_talk.model.TeacherSelectionListModel;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>一覧_講師選択。<br>
 * <b>クラス名称       :</b>一覧_講師選択Actionクラス。<br>
 * <b>クラス概要       :</b>一覧_講師の情報ができる<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/09 TECS 新規作成
 */
public abstract class TeacherSelectionListActionSupport extends CertificationActionSupport {
    private static final long serialVersionUID = 1L;

    // 画面表示のタイトル
    protected String title = "講師 選択";

    // Help画面名
    protected String helpPageId = "HelpTeacherSelectionList.html";

    /**
     * 画面のパラメータをモデルにセット。<br>
     * <br>
     * @param なし
     * @return なし
     */
    public void setupModel() {
        this.model.setTeacherId(teacherId_txt);
        this.model.setTeacherFurigana(teacherFurigana_txt);
    }

    /** 検索結果 */
    protected TeacherSelectionListModel model = new TeacherSelectionListModel();
    /** 利用者ID */
    protected String userId;
    /** 講師ID */
    protected String teacherId_txt;
    /** 講師名(フリガナ) */
    protected String teacherFurigana_txt;
    /** メッセージ */
    protected String message;
    /** 戻る区分 */
    protected String modoKbun;
    /** 戻るAction */
    protected String actionName;
    /** 一覧部の「選択」 */
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
     * @return model
     */
    public TeacherSelectionListModel getModel() {
        return model;
    }

    /**
     * @param model セットする model
     */
    public void setModel(TeacherSelectionListModel model) {
        this.model = model;
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
     * @return teacherId_txt
     */
    public String getTeacherId_txt() {
        return teacherId_txt;
    }

    /**
     * @param teacherId_txt セットする teacherId_txt
     */
    public void setTeacherId_txt(String teacherId_txt) {
        this.teacherId_txt = teacherId_txt;
    }

    /**
     * @return teacherFurigana_txt
     */
    public String getTeacherFurigana_txt() {
        return teacherFurigana_txt;
    }

    /**
     * @param teacherFurigana_txt セットする teacherFurigana_txt
     */
    public void setTeacherFurigana_txt(String teacherFurigana_txt) {
        this.teacherFurigana_txt = teacherFurigana_txt;
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
     * @return modoKbun
     */
    public String getModoKbun() {
        return modoKbun;
    }

    /**
     * @param modoKbun セットする modoKbun
     */
    public void setModoKbun(String modoKbun) {
        this.modoKbun = modoKbun;
    }

    /**
     * @return actionName
     */
    public String getActionName() {
        return actionName;
    }

    /**
     * @param actionName セットする actionName
     */
    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    /**
     * @return select_rdl
     */
    public String getSelect_rdl() {
        return select_rdl;
    }

    /**
     * @param select_rdl セットする select_rdl
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
     * @param hasSearchFlg セットする hasSearchFlg
     */
    public void setHasSearchFlg(String hasSearchFlg) {
        this.hasSearchFlg = hasSearchFlg;
    }

}
