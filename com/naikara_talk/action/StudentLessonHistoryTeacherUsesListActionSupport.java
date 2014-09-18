package com.naikara_talk.action;

import java.util.LinkedHashMap;
import java.util.Map;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.StudentLessonHistoryUsesListModel;
import com.naikara_talk.service.StudentLessonHistoryUsesListLoadService;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様(個人)_実績照会<br>
 * <b>クラス名称　　　:</b>受講者側の視点から_1-2_レッスン実績Actionスーパークラス。<br>
 * <b>クラス概要　　　:</b>受講者側の視点から_1-2_レッスン実績実績Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/05 TECS 新規作成。
 */
public abstract class StudentLessonHistoryTeacherUsesListActionSupport extends NaikaraActionSupport {

    private static final long serialVersionUID = 1L;

    /** 画面表示のタイトル */
    protected String title = "Student Lesson History";

    /** Help画面名 */
    protected String helpPageId = "HelpStudentLessonHistoryTeacherUsesList.html";

    /**
     * コース分類再取得<br>
     * <br>
     * チェックエラーの場合、コース分類再取得。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception なし
     */
    @Override
    public void validate() {

        // チェックエラーの場合、コース名再取得。
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
     * コース分類を取得する。<br>
     * <br>
     * コース分類を取得する。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    public void initRadio() throws Exception {

        StudentLessonHistoryUsesListLoadService service = new StudentLessonHistoryUsesListLoadService();
        // 大分類を取得する
        this.courseLarge_sell = service.getCourseSell(NaikaraTalkConstants.CODE_CATEGORY_BIG_CLASSIFICATION_T,
                NaikaraTalkConstants.CODE_CATEGORY_BIG_CLASSIFICATION);

        // 中分類を取得する
        this.courseMedium_sell = service.getCourseSell(NaikaraTalkConstants.CODE_CATEGORY_MIDDLE_CLASSIFICATION_T,
                NaikaraTalkConstants.CODE_CATEGORY_MIDDLE_CLASSIFICATION);

        // 小分類を取得する
        this.courseSmall_sell = service.getCourseSell(NaikaraTalkConstants.CODE_CATEGORY_SMALL_CLASSIFICATION_T,
                NaikaraTalkConstants.CODE_CATEGORY_SMALL_CLASSIFICATION);

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

        // レッスン開始日(jsp)
        this.model.setLessonDtFr(this.lessonDtFr_txt);
        // レッスン終了日(jsp)
        this.model.setLessonDtTo(this.lessonDtTo_txt);
        // 受講者ID(jsp)
        this.model.setStudentId(this.studentId_txt);
        // 受講者ニックネーム(jsp)
        this.model.setStudentNickname(this.studentNickname_txt);
        // 大分類(jsp)
        this.model.setCourseLarge(this.courseLarge_sel);
        // 中分類(jsp)
        this.model.setCourseMedium(this.courseMedium_sel);
        // 小分類(jsp)
        this.model.setCourseSmall(this.courseSmall_sel);
        // コース名(jsp)
        this.model.setCourseName(this.courseName_txt);
    }

    /** メッセージ */
    protected String message;

    /** レッスン開始日(jsp) */
    protected String lessonDtFr_txt;

    /** レッスン終了日(jsp) */
    protected String lessonDtTo_txt;

    /** 受講者ID(jsp) */
    protected String studentId_txt;

    /** 受講者ニックネーム(jsp) */
    protected String studentNickname_txt;

    /** 大分類(jsp) */
    protected String courseLarge_sel;

    /** 大分類(jsp)リスト */
    protected Map<String, String> courseLarge_sell = new LinkedHashMap<String, String>();

    /** 中分類(jsp) */
    protected String courseMedium_sel;

    /** 中分類(jsp)リスト */
    protected Map<String, String> courseMedium_sell = new LinkedHashMap<String, String>();

    /** 小分類(jsp) */
    protected String courseSmall_sel;

    /** 小分類(jsp)リスト */
    protected Map<String, String> courseSmall_sell = new LinkedHashMap<String, String>();

    /** コース名(jsp) */
    protected String courseName_txt;

    /** 検索結果 */
    protected StudentLessonHistoryUsesListModel model = new StudentLessonHistoryUsesListModel();

    /** 一覧から選択された明細データ(jsp) */
    protected String select_rdl;

    /** チェックエラー場合、検索判断フラグ */
    protected String hasSearchFlg;

    /** ロール */
    protected String role = "";

    /**
     * チェックエラー場合、検索判断フラグ取得<br>
     * <br>
     * チェックエラー場合、検索判断フラグ取得を行う<br>
     * <br>
     * @param なし<br>
     * @return hasSearchFlg チェックエラー場合、検索判断フラグ<br>
     * @exception なし
     */
    public String getHasSearchFlg() {
        return hasSearchFlg;
    }

    /**
     * チェックエラー場合、検索判断フラグ設定<br>
     * <br>
     * チェックエラー場合、検索判断フラグ設定を行う<br>
     * <br>
     * @param hasSearchFlg チェックエラー場合、検索判断フラグ<br>
     * @return なし<br>
     * @exception なし
     */
    public void setHasSearchFlg(String hasSearchFlg) {
        this.hasSearchFlg = hasSearchFlg;
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
     * 遷移元画面ID取得<br>
     * <br>
     * 遷移元画面ID取得を行う<br>
     * <br>
     * @param なし<br>
     * @return pageId 遷移元画面ID<br>
     * @exception なし
     */

    /**
     * ロール取得<br>
     * <br>
     * ロール取得を行う<br>
     * <br>
     * @param なし<br>
     * @return role ロール<br>
     * @exception なし
     */
    public String getRole() {
        return role;
    }

    /**
     * ロール設定<br>
     * <br>
     * ロール設定を行う<br>
     * <br>
     * @param role ロール<br>
     * @return なし<br>
     * @exception なし
     */
    public void setRole(String role) {
        this.role = role;
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
     * レッスン開始日(jsp)取得<br>
     * <br>
     * レッスン開始日(jsp)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return lessonDtFr_txt レッスン(jsp)<br>
     * @exception なし
     */
    public String getLessonDtFr_txt() {
        return lessonDtFr_txt;
    }

    /**
     * レッスン開始日(jsp)設定<br>
     * <br>
     * レッスン開始日(jsp)設定を行う<br>
     * <br>
     * @param lessonDtFr_txt レッスン開始日(jsp)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setLessonDtFr_txt(String lessonDtFr_txt) {
        this.lessonDtFr_txt = lessonDtFr_txt;
    }

    /**
     * レッスン終了日(jsp)取得<br>
     * <br>
     * レッスン終了日(jsp)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return lessonDtTo_txt レッスン終了日(jsp)<br>
     * @exception なし
     */
    public String getLessonDtTo_txt() {
        return lessonDtTo_txt;
    }

    /**
     * レッスン終了日(jsp)設定<br>
     * <br>
     * レッスン終了日(jsp)設定を行う<br>
     * <br>
     * @param lessonDtTo_txt レッスン終了日(jsp)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setLessonDtTo_txt(String lessonDtTo_txt) {
        this.lessonDtTo_txt = lessonDtTo_txt;
    }

    /**
     * 大分類(jsp)リスト取得<br>
     * <br>
     * 大分類(jsp)リスト取得を行う<br>
     * <br>
     * @param なし<br>
     * @return courseLarge_sell 大分類(jsp)リスト<br>
     * @exception なし
     */
    public Map<String, String> getCourseLarge_sell() {
        return courseLarge_sell;
    }

    /**
     * 大分類(jsp)リスト設定<br>
     * <br>
     * 大分類(jsp)リスト設定を行う<br>
     * <br>
     * @param courseLarge_sell 大分類(jsp)リスト<br>
     * @return なし<br>
     * @exception なし
     */
    public void setCourseLarge_sell(Map<String, String> courseLarge_sell) {
        this.courseLarge_sell = courseLarge_sell;
    }

    /**
     * 中分類(jsp)リスト取得<br>
     * <br>
     * 中分類(jsp)リスト取得を行う<br>
     * <br>
     * @param なし<br>
     * @return courseMedium_sell 中分類(jsp)リスト<br>
     * @exception なし
     */
    public Map<String, String> getCourseMedium_sell() {
        return courseMedium_sell;
    }

    /**
     * 中分類(jsp)リスト設定<br>
     * <br>
     * 中分類(jsp)リスト設定を行う<br>
     * <br>
     * @param courseMedium_sell 中分類(jsp)リスト<br>
     * @return なし<br>
     * @exception なし
     */
    public void setCourseMedium_sell(Map<String, String> courseMedium_sell) {
        this.courseMedium_sell = courseMedium_sell;
    }

    /**
     * 小分類(jsp)リスト取得<br>
     * <br>
     * 小分類(jsp)リスト取得を行う<br>
     * <br>
     * @param なし<br>
     * @return courseSmall_sell 小分類(jsp)リスト<br>
     * @exception なし
     */
    public Map<String, String> getCourseSmall_sell() {
        return courseSmall_sell;
    }

    /**
     * 小分類(jsp)リスト設定<br>
     * <br>
     * 小分類(jsp)リスト設定を行う<br>
     * <br>
     * @param courseSmall_sell 小分類(jsp)リスト<br>
     * @return なし<br>
     * @exception なし
     */
    public void setCourseSmall_sell(Map<String, String> courseSmall_sell) {
        this.courseSmall_sell = courseSmall_sell;
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
    public StudentLessonHistoryUsesListModel getModel() {
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
    public void setModel(StudentLessonHistoryUsesListModel model) {
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
     * 受講者ニックネーム(jsp)取得<br>
     * <br>
     * 受講者ニックネーム(jsp)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return studentNickname_txt 受講者ニックネーム(jsp)<br>
     * @exception なし
     */
    public String getStudentNickname_txt() {
        return studentNickname_txt;
    }

    /**
     * 受講者ニックネーム(jsp)設定<br>
     * <br>
     * 受講者ニックネーム(jsp)設定を行う<br>
     * <br>
     * @param studentNickname_txt 受講者ニックネーム(jsp)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setStudentNickname_txt(String studentNickname_txt) {
        this.studentNickname_txt = studentNickname_txt;
    }

    /**
     * 大分類(jsp)取得<br>
     * <br>
     * 大分類(jsp)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return courseLarge_sel 大分類(jsp)<br>
     * @exception なし
     */
    public String getCourseLarge_sel() {
        return courseLarge_sel;
    }

    /**
     * 大分類(jsp)設定<br>
     * <br>
     * 大分類(jsp)設定を行う<br>
     * <br>
     * @param courseLarge_sel 大分類(jsp)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setCourseLarge_sel(String courseLarge_sel) {
        this.courseLarge_sel = courseLarge_sel;
    }

    /**
     * 中分類(jsp)取得<br>
     * <br>
     * 中分類(jsp)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return courseMedium_sel 中分類(jsp)<br>
     * @exception なし
     */
    public String getCourseMedium_sel() {
        return courseMedium_sel;
    }

    /**
     * 中分類(jsp)設定<br>
     * <br>
     * 中分類(jsp)設定を行う<br>
     * <br>
     * @param courseMedium_sel 中分類(jsp)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setCourseMedium_sel(String courseMedium_sel) {
        this.courseMedium_sel = courseMedium_sel;
    }

    /**
     * 小分類(jsp)取得<br>
     * <br>
     * 小分類(jsp)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return courseSmall_sel 小分類(jsp)<br>
     * @exception なし
     */
    public String getCourseSmall_sel() {
        return courseSmall_sel;
    }

    /**
     * 小分類(jsp)設定<br>
     * <br>
     * 小分類(jsp)設定を行う<br>
     * <br>
     * @param courseSmall_sel 小分類(jsp)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setCourseSmall_sel(String courseSmall_sel) {
        this.courseSmall_sel = courseSmall_sel;
    }

    /**
     * コース名(jsp)取得<br>
     * <br>
     * コース名(jsp)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return courseName_txt コース名(jsp)<br>
     * @exception なし
     */
    public String getCourseName_txt() {
        return courseName_txt;
    }

    /**
     * コース名(jsp)設定<br>
     * <br>
     * コース名(jsp)設定を行う<br>
     * <br>
     * @param courseName_txt コース名(jsp)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setCourseName_txt(String courseName_txt) {
        this.courseName_txt = courseName_txt;
    }
}