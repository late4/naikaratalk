package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.TeacherPaymentRateListModel;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>支払単価表<br>
 * <b>クラス名称       :</b>支払単価表初期処理共通Actionクラス。<br>
 * <b>クラス概要       :</b>支払単価表初期処理共通Action<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/28 TECS 新規作成
 */
public abstract class TeacherPaymentRateListActionSupport extends NaikaraActionSupport {

    private static final long serialVersionUID = 1L;

    // 画面表示のタイトル
    protected String title = "Teacher Payment Rate";

    // Help画面名
    protected String helpPageId = "HelpTeacherPaymentRateList.html";

    /**
    * サービスの呼び出しの実装。
    */
    abstract protected String requestService() throws NaiException;

    /** 利用者ID */
    protected String userId;

    /** ニックネーム  */
    protected String nickAnm;

    /** 計算時の現在日付  */
    protected String today;

    /** 検索結果 */
    protected TeacherPaymentRateListModel model = new TeacherPaymentRateListModel();

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
     * @return nickAnm
     */
    public String getNickAnm() {
        return nickAnm;
    }

    /**
     * @param nickAnm セットする nickAnm
     */
    public void setNickAnm(String nickAnm) {
        this.nickAnm = nickAnm;
    }

    /**
     * @return today
     */
    public String getToday() {
        return today;
    }

    /**
     * @param today セットする today
     */
    public void setToday(String today) {
        this.today = today;
    }

    /**
     * @return model
     */
    public TeacherPaymentRateListModel getModel() {
        return model;
    }

    /**
     * @param model セットする model
     */
    public void setModel(TeacherPaymentRateListModel model) {
        this.model = model;
    }
}
