package com.naikara_talk.action;

import java.util.LinkedHashMap;
import java.util.Map;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.StudentSelectionListModel;
import com.naikara_talk.service.StudentSelectionListLoadService;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様(個人)_初期登録。<br>
 * <b>クラス名称　　　:</b>マイページ(お客様_個人)Actionスーパークラス。<br>
 * <b>クラス概要　　　:</b>マイページ(お客様_個人)Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/31 TECS 新規作成。
 */
public abstract class StudentSelectionListActionSupport extends CertificationActionSupport {

    private static final long serialVersionUID = 1L;

    /** 画面表示のタイトル */
    protected String title = "受講者選択";

    /** Help画面名 */
    protected String helpPageId = "HelpStudentSelectionList.html";

    /**
     * ラジオを初期化<br>
     * <br>
     * 画面ラジオを初期化する<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception なし
     */
    @Override
    public void validate() {

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
     * ラジオを取得<br>
     * <br>
     * 顧客区分を取得する<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    public void initRadio() throws Exception {

        StudentSelectionListLoadService service = new StudentSelectionListLoadService();

        // 顧客区分を取得する
        this.customer_rdll = service
                .selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_CUSTOMER_KBN);
    }

    /**
     * 画面のパラメータセット<br>
     * <br>
     * 画面のパラメータをモデルにセット<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception なし
     */
    public void setupModel() {

        // 顧客区分(jsp)
        this.model.setCustomerKbn(this.customer_rdl);
        // 受講者ID(jsp)
        this.model.setStudentId(this.studentId_txt);
        // 受講者名(ニックネーム)(jsp)
        this.model.setStudentNm(this.studentNm_txt);
        // 受講者名(フリガナ)(jsp)
        this.model.setStudentFuri(this.studentFuri_txt);
        // 組織名(jsp)
        this.model.setOrganization(this.organization_txt);
    }

    /** メッセージ */
    protected String message;

    /** 顧客区分(jsp) */
    protected String customer_rdl;

    /** 顧客区分(jsp)リスト */
    protected Map<String, String> customer_rdll = new LinkedHashMap<String, String>();

    /** 受講者ID(jsp) */
    protected String studentId_txt;

    /** 受講者名(ニックネーム)(jsp) */
    protected String studentNm_txt;

    /** 受講者名(フリガナ)(jsp) */
    protected String studentFuri_txt;

    /** 組織名(jsp) */
    protected String organization_txt;

    /** 検索結果 */
    protected StudentSelectionListModel model = new StudentSelectionListModel();

    /** 一覧から選択された明細データ(jsp) */
    protected String select_rdl;

    /** チェックエラー場合、検索判断フラグ */
    protected String hasSearchFlg;

    /**
     * 組織名(jsp)取得<br>
     * <br>
     * 組織名(jsp)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return organization_txt 組織名(jsp)<br>
     * @exception なし
     */
    public String getOrganization_txt() {
        return organization_txt;
    }

    /**
     * 組織名(jsp)設定<br>
     * <br>
     * 組織名(jsp)設定を行う<br>
     * <br>
     * @param organization_txt 組織名(jsp)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setOrganization_txt(String organization_txt) {
        this.organization_txt = organization_txt;
    }

    /**
     * Help画面名取得<br>
     * <br>
     * Help画面名取得を行う<br>
     * <br>
     * @param なし<br>
     * @return helpPageId Help画面名<br>
     * @exception なし
     */
    public String getHelpPageId() {
        return helpPageId;
    }

    /**
     * Help画面名設定<br>
     * <br>
     * Help画面名設定を行う<br>
     * <br>
     * @param helpPageId Help画面名<br>
     * @return なし<br>
     * @exception なし
     */
    public void setHelpPageId(String helpPageId) {
        this.helpPageId = helpPageId;
    }

    /**
     * 画面表示のタイトル取得<br>
     * <br>
     * 画面表示のタイトル取得を行う<br>
     * <br>
     * @param なし<br>
     * @return title 画面表示のタイトル<br>
     * @exception なし
     */
    public String getTitle() {
        return title;
    }

    /**
     * 画面表示のタイトル設定<br>
     * <br>
     * 画面表示のタイトル設定を行う<br>
     * <br>
     * @param title 画面表示のタイトル<br>
     * @return なし<br>
     * @exception なし
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * メッセージ取得<br>
     * <br>
     * メッセージ取得を行う<br>
     * <br>
     * @param なし<br>
     * @return message メッセージ<br>
     * @exception なし
     */
    public String getMessage() {
        return message;
    }

    /**
     * メッセージ設定<br>
     * <br>
     * メッセージ設定を行う<br>
     * <br>
     * @param message メッセージ<br>
     * @return なし<br>
     * @exception なし
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 顧客区分(jsp)取得<br>
     * <br>
     * 顧客区分(jsp)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return customer_rdl 顧客区分(jsp)<br>
     * @exception なし
     */
    public String getCustomer_rdl() {
        return customer_rdl;
    }

    /**
     * 顧客区分(jsp)設定<br>
     * <br>
     * 顧客区分(jsp)設定を行う<br>
     * <br>
     * @param customer_rdl 顧客区分(jsp)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setCustomer_rdl(String customer_rdl) {
        this.customer_rdl = customer_rdl;
    }

    /**
     * 受講者ID(jsp)取得<br>
     * <br>
     * 受講者ID(jsp)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return studentId_txt 受講者ID(jsp)<br>
     * @exception なし
     */
    public String getStudentId_txt() {
        return studentId_txt;
    }

    /**
     * 受講者ID(jsp)設定<br>
     * <br>
     * 受講者ID(jsp)設定を行う<br>
     * <br>
     * @param studentId_txt 受講者ID(jsp)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setStudentId_txt(String studentId_txt) {
        this.studentId_txt = studentId_txt;
    }

    /**
     * 受講者名(ニックネーム)(jsp)取得<br>
     * <br>
     * 受講者名(ニックネーム)(jsp)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return studentNm_txt 受講者名(ニックネーム)(jsp)<br>
     * @exception なし
     */
    public String getStudentNm_txt() {
        return studentNm_txt;
    }

    /**
     * 受講者名(ニックネーム)(jsp)設定<br>
     * <br>
     * 受講者名(ニックネーム)(jsp)設定を行う<br>
     * <br>
     * @param studentNm_txt 受講者名(ニックネーム)(jsp)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setStudentNm_txt(String studentNm_txt) {
        this.studentNm_txt = studentNm_txt;
    }

    /**
     * 受講者名(フリガナ)(jsp)取得<br>
     * <br>
     * 受講者名(フリガナ)(jsp)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return studentFuri_txt 受講者名(フリガナ)(jsp)<br>
     * @exception なし
     */
    public String getStudentFuri_txt() {
        return studentFuri_txt;
    }

    /**
     * 受講者名(フリガナ)(jsp)設定<br>
     * <br>
     * 受講者名(フリガナ)(jsp)設定を行う<br>
     * <br>
     * @param studentFuri_txt 受講者名(フリガナ)(jsp)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setStudentFuri_txt(String studentFuri_txt) {
        this.studentFuri_txt = studentFuri_txt;
    }

    /**
     * 顧客区分(jsp)リスト取得<br>
     * <br>
     * 顧客区分(jsp)リスト取得を行う<br>
     * <br>
     * @param なし<br>
     * @return customer_rdll 顧客区分(jsp)リスト<br>
     * @exception なし
     */
    public Map<String, String> getCustomer_rdll() {
        return customer_rdll;
    }

    /**
     * 顧客区分(jsp)リスト設定<br>
     * <br>
     * 顧客区分(jsp)リスト設定を行う<br>
     * <br>
     * @param customer_rdll 顧客区分(jsp)リスト<br>
     * @return なし<br>
     * @exception なし
     */
    public void setCustomer_rdll(Map<String, String> customer_rdll) {
        this.customer_rdll = customer_rdll;
    }

    /**
     * 画面モデル取得<br>
     * <br>
     * 画面モデル取得を行う<br>
     * <br>
     * @param なし<br>
     * @return model 画面モデル<br>
     * @exception なし
     */
    public StudentSelectionListModel getModel() {
        return model;
    }

    /**
     * 画面モデル設定<br>
     * <br>
     * 画面モデル設定を行う<br>
     * <br>
     * @param model 画面モデル<br>
     * @return なし<br>
     * @exception なし
     */
    public void setModel(StudentSelectionListModel model) {
        this.model = model;
    }

    /**
     * 一覧から選択された明細データ(jsp)取得<br>
     * <br>
     * 一覧から選択された明細データ(jsp)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return select_rdl 一覧から選択された明細データ(jsp)<br>
     * @exception なし
     */
    public String getSelect_rdl() {
        return select_rdl;
    }

    /**
     * 一覧から選択された明細データ(jsp)設定<br>
     * <br>
     * 一覧から選択された明細データ(jsp)設定を行う<br>
     * <br>
     * @param select_rdl 一覧から選択された明細データ(jsp)<br>
     * @return なし<br>
     * @exception なし
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