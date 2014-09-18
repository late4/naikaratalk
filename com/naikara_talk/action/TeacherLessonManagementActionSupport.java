package com.naikara_talk.action;

import java.util.LinkedHashMap;
import java.util.Map;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.TeacherLessonManagementModel;
import com.naikara_talk.service.TeacherLessonManagementLoadService;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>講師。<br>
 * <b>クラス名称　　　:</b>講師側の視点から_1-1_レッスン実績Actionスーパークラス。<br>
 * <b>クラス概要　　　:</b>講師側の視点から_1-1_レッスン実績Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/12 TECS 新規作成。
 *  　　　　　　　　　:</b>2014/04/10 TECS 変更　予約Noの追加(添付付メール送信画面用)
 */
public abstract class TeacherLessonManagementActionSupport extends NaikaraActionSupport {

    private static final long serialVersionUID = 1L;

    /** 画面表示のタイトル */
    protected String title = "Lesson Management";

    /** Help画面名 */
    protected String helpPageId = "HelpTeacherLessonManagement.html";

    /** 遷移画面ID */
    protected String pageId;

    /** ロール */
    protected String role = NaikaraTalkConstants.BRANK;

    /** 件数 */
    protected int size = 0;

    /** 遷移元画面ID */
    protected String frontPageId;

    /** 一覧の選択された｢レッスン日｣ */
    protected String lessonDt;

    /** 一覧の選択された｢レッスン時刻｣ */
    protected String lessonTmNm;

    /** 一覧の選択された｢コース名｣ */
    protected String courseEnmAll;

    /** 一覧の選択された｢受講者ID｣ */
    protected String studentId;

    /** 一覧の選択された｢受講者名(ニックネーム)｣ */
    protected String studentNickNm;

    /** 一覧の選択された講師名(ニックネーム) */
    protected String teacherNickNm;

    /** 一覧の選択された講師ID */
    protected String teacherId;

    /** 一覧の選択された｢コース名｣の日本語の行 */
    protected String courseJnmAll;

    /** 一覧の選択された｢予約No｣ */
    protected String reservationNo;

    /**
     * コンボを初期化<br>
     * <br>
     * 画面コンボを初期化する<br>
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
     * コンボを取得<br>
     * <br>
     * 各分類を取得する<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    public void initRadio() throws Exception {

        TeacherLessonManagementLoadService service = new TeacherLessonManagementLoadService();

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

        // 検索条件部：抽出開始日(jsp)
        this.model.setPeriodDtFr(this.periodDtFr_txt);
        // 検索条件部：抽出終了日(jsp)
        this.model.setPeriodDtTo(this.periodDtTo_txt);
        // 検索条件部：講師ID(jsp)
        this.model.setTeacherId(this.teacherId_txt);
        // 検索条件部：講師ニックネーム(jsp)
        this.model.setTeacherNickname(this.teacherNickname_txt);
        // 検索条件部：受講者ID(jsp)
        this.model.setStudentId(this.studentId_txt);
        // 検索条件部：受講者ニックネーム(jsp)
        this.model.setStudentNickname(this.studentNickname_txt);
        // 検索条件部：大分類(jsp)
        this.model.setCourseLarge(this.courseLarge_sel);
        // 検索条件部：中分類(jsp)
        this.model.setCourseMedium(this.courseMedium_sel);
        // 検索条件部：小分類(jsp)
        this.model.setCourseSmall(this.courseSmall_sel);
        // 検索条件部：コース名(jsp)
        this.model.setCourseName(this.courseName_txt);
    }

    /** メッセージ */
    protected String message;

    /** 抽出開始日(jsp) */
    protected String periodDtFr_txt;

    /** 抽出終了日(jsp) */
    protected String periodDtTo_txt;

    /** 講師ID(jsp) */
    protected String teacherId_txt;

    /** 講師ニックネーム(jsp) */
    protected String teacherNickname_txt;

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
    protected TeacherLessonManagementModel model = new TeacherLessonManagementModel();

    /** 一覧から選択された明細データ(jsp) */
    protected String select_rdl;

    /** チェックエラー場合、検索判断フラグ */
    protected String hasSearchFlg;

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
    public String getPageId() {
        return pageId;
    }

    /**
     * 遷移元画面ID設定<br>
     * <br>
     * 遷移元画面ID設定を行う<br>
     * <br>
     * @param pageId 遷移元画面ID<br>
     * @return なし<br>
     * @exception なし
     */
    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    /**
     * 件数取得<br>
     * <br>
     * 件数取得を行う<br>
     * <br>
     * @param なし<br>
     * @return size 件数<br>
     * @exception なし
     */
    public int getSize() {
        return size;
    }

    /**
     * 件数設定<br>
     * <br>
     * 件数設定を行う<br>
     * <br>
     * @param size 件数<br>
     * @return なし<br>
     * @exception なし
     */
    public void setSize(int size) {
        this.size = size;
    }

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
     * 抽出開始日(jsp)取得<br>
     * <br>
     * 抽出開始日(jsp)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return periodDtFr_txt 抽出開始日(jsp)<br>
     * @exception なし
     */
    public String getPeriodDtFr_txt() {
        return periodDtFr_txt;
    }

    /**
     * 抽出開始日(jsp)設定<br>
     * <br>
     * 抽出開始日(jsp)設定を行う<br>
     * <br>
     * @param periodDtFr_txt 抽出開始日(jsp)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setPeriodDtFr_txt(String periodDtFr_txt) {
        this.periodDtFr_txt = periodDtFr_txt;
    }

    /**
     * 抽出終了日(jsp)取得<br>
     * <br>
     * 抽出終了日(jsp)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return periodDtTo_txt 抽出終了日(jsp)<br>
     * @exception なし
     */
    public String getPeriodDtTo_txt() {
        return periodDtTo_txt;
    }

    /**
     * 抽出終了日(jsp)設定<br>
     * <br>
     * 抽出終了日(jsp)設定を行う<br>
     * <br>
     * @param periodDtTo_txt 抽出終了日(jsp)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setPeriodDtTo_txt(String periodDtTo_txt) {
        this.periodDtTo_txt = periodDtTo_txt;
    }

    /**
     * 講師ID(jsp)取得<br>
     * <br>
     * 講師ID(jsp)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return teacherId_txt 講師ID(jsp)<br>
     * @exception なし
     */
    public String getTeacherId_txt() {
        return teacherId_txt;
    }

    /**
     * 講師ID(jsp)設定<br>
     * <br>
     * 講師ID(jsp)設定を行う<br>
     * <br>
     * @param teacherId_txt 講師ID(jsp)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setTeacherId_txt(String teacherId_txt) {
        this.teacherId_txt = teacherId_txt;
    }

    /**
     * 講師ニックネーム(jsp)取得<br>
     * <br>
     * 講師ニックネーム(jsp)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return teacherNickname_txt 講師ニックネーム(jsp)<br>
     * @exception なし
     */
    public String getTeacherNickname_txt() {
        return teacherNickname_txt;
    }

    /**
     * 講師ニックネーム(jsp)設定<br>
     * <br>
     * 講師ニックネーム(jsp)設定を行う<br>
     * <br>
     * @param teacherNickname_txt 講師ニックネーム(jsp)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setTeacherNickname_txt(String teacherNickname_txt) {
        this.teacherNickname_txt = teacherNickname_txt;
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
    public TeacherLessonManagementModel getModel() {
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
    public void setModel(TeacherLessonManagementModel model) {
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

    /**
     * 遷移元画面ID(jsp)取得<br>
     * <br>
     * 遷移元画面ID(jsp)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return frontPageId 遷移元画面ID(jsp)<br>
     * @exception なし
     */
    public String getFrontPageId() {
        return frontPageId;
    }

    /**
     * 遷移元画面ID(jsp)設定<br>
     * <br>
     * 遷移元画面ID(jsp)設定を行う<br>
     * <br>
     * @param frontPageId 遷移元画面ID(jsp)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setFrontPageId(String frontPageId) {
        this.frontPageId = frontPageId;
    }

    /**
     * 一覧の選択された｢レッスン日｣(jsp)取得<br>
     * <br>
     * 一覧の選択された｢レッスン日｣(jsp)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return lessonDt 一覧の選択された｢レッスン日｣(jsp)<br>
     * @exception なし
     */
    public String getLessonDt() {
        return lessonDt;
    }

    /**
     * 一覧の選択された｢レッスン日｣(jsp)リスト設定<br>
     * <br>
     * 一覧の選択された｢レッスン日｣(jsp)リスト設定を行う<br>
     * <br>
     * @param lessonDt 一覧の選択された｢レッスン日｣(jsp)リスト<br>
     * @return なし<br>
     * @exception なし
     */
    public void setLessonDt(String lessonDt) {
        this.lessonDt = lessonDt;
    }

    /**
     * 一覧の選択された｢レッスン時刻｣(jsp)取得<br>
     * <br>
     * 一覧の選択された｢レッスン時刻｣(jsp)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return lessonTmNm 一覧の選択された｢レッスン時刻｣(jsp)<br>
     * @exception なし
     */
    public String getLessonTmNm() {
        return lessonTmNm;
    }

    /**
     * 一覧の選択された｢レッスン時刻｣(jsp)リスト設定<br>
     * <br>
     * 一覧の選択された｢レッスン時刻｣(jsp)リスト設定を行う<br>
     * <br>
     * @param lessonTmNm 一覧の選択された｢レッスン時刻｣(jsp)リスト<br>
     * @return なし<br>
     * @exception なし
     */
    public void setLessonTmNm(String lessonTmNm) {
        this.lessonTmNm = lessonTmNm;
    }

    /**
     * 一覧の選択された｢コース名｣(jsp)取得<br>
     * <br>
     * 一覧の選択された｢コース名｣(jsp)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return courseEnmAll 一覧の選択された｢コース名｣(jsp)<br>
     * @exception なし
     */
    public String getCourseEnmAll() {
        return courseEnmAll;
    }

    /**
     * 一覧の選択された｢コース名｣(jsp)リスト設定<br>
     * <br>
     * 一覧の選択された｢コース名｣(jsp)リスト設定を行う<br>
     * <br>
     * @param courseEnmAll 一覧の選択された｢コース名｣(jsp)リスト<br>
     * @return なし<br>
     * @exception なし
     */
    public void setCourseEnmAll(String courseEnmAll) {
        this.courseEnmAll = courseEnmAll;
    }

    /**
     * 一覧の選択された｢受講者ID｣(jsp)取得<br>
     * <br>
     * 一覧の選択された｢受講者ID｣(jsp)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return studentId 一覧の選択された｢受講者ID｣(jsp)<br>
     * @exception なし
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * 一覧の選択された｢受講者ID｣(jsp)リスト設定<br>
     * <br>
     * 一覧の選択された｢受講者ID｣(jsp)リスト設定を行う<br>
     * <br>
     * @param studentId 一覧の選択された｢受講者ID｣(jsp)リスト<br>
     * @return なし<br>
     * @exception なし
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * 一覧の選択された｢受講者名(ニックネーム)｣(jsp)取得<br>
     * <br>
     * 一覧の選択された｢受講者名(ニックネーム)｣(jsp)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return studentNickNm 一覧の選択された｢受講者名(ニックネーム)｣(jsp)<br>
     * @exception なし
     */
    public String getStudentNickNm() {
        return studentNickNm;
    }

    /**
     * 一覧の選択された｢受講者名(ニックネーム)｣(jsp)リスト設定<br>
     * <br>
     * 一覧の選択された｢受講者名(ニックネーム)｣(jsp)リスト設定を行う<br>
     * <br>
     * @param studentNickNm 一覧の選択された｢受講者名(ニックネーム)｣(jsp)リスト<br>
     * @return なし<br>
     * @exception なし
     */
    public void setStudentNickNm(String studentNickNm) {
        this.studentNickNm = studentNickNm;
    }

    /**
     * 一覧の選択された講師名(ニックネーム)(jsp)取得<br>
     * <br>
     * 一覧の選択された講師名(ニックネーム)(jsp)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return teacherNickNm 一覧の選択された講師名(ニックネーム)(jsp)<br>
     * @exception なし
     */
    public String getTeacherNickNm() {
        return teacherNickNm;
    }

    /**
     * 一覧の選択された講師名(ニックネーム)(jsp)リスト設定<br>
     * <br>
     * 一覧の選択された講師名(ニックネーム)(jsp)リスト設定を行う<br>
     * <br>
     * @param teacherNickNm 一覧の選択された講師名(ニックネーム)(jsp)リスト<br>
     * @return なし<br>
     * @exception なし
     */
    public void setTeacherNickNm(String teacherNickNm) {
        this.teacherNickNm = teacherNickNm;
    }

    /**
     * 一覧の選択された講師ID(jsp)取得<br>
     * <br>
     * 一覧の選択された講師ID(jsp)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return teacherId 一覧の選択された講師ID(jsp)<br>
     * @exception なし
     */
    public String getTeacherId() {
        return teacherId;
    }

    /**
     * 一覧の選択された講師ID(jsp)リスト設定<br>
     * <br>
     * 一覧の選択された講師ID(jsp)リスト設定を行う<br>
     * <br>
     * @param teacherId 一覧の選択された講師ID(jsp)リスト<br>
     * @return なし<br>
     * @exception なし
     */
    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * 一覧の選択された｢コース名｣の日本語の行(jsp)取得<br>
     * <br>
     * 一覧の選択された｢コース名｣の日本語の行(jsp)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return courseJnmAll 一覧の選択された｢コース名｣の日本語の行(jsp)<br>
     * @exception なし
     */
    public String getCourseJnmAll() {
        return courseJnmAll;
    }

    /**
     * 一覧の選択された｢コース名｣の日本語の行(jsp)リスト設定<br>
     * <br>
     * 一覧の選択された｢コース名｣の日本語の行(jsp)リスト設定を行う<br>
     * <br>
     * @param courseJnmAll 一覧の選択された｢コース名｣の日本語の行(jsp)リスト<br>
     * @return なし<br>
     * @exception なし
     */
    public void setCourseJnmAll(String courseJnmAll) {
        this.courseJnmAll = courseJnmAll;
    }

    /**
     * 一覧の選択された｢予約No｣(jsp)取得<br>
     * <br>
     * 一覧の選択された｢予約No｣(jsp)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return reservationNo 一覧の選択された｢予約No｣(jsp)<br>
     * @exception なし
     */
    public String getReservationNo() {
        return reservationNo;
    }

    /**
     * 一覧の選択された｢予約No｣(jsp)リスト設定<br>
     * <br>
     * 一覧の選択された｢予約No｣(jsp)リスト設定を行う<br>
     * <br>
     * @param reservationNo 一覧の選択された｢予約No｣(jsp)リスト<br>
     * @return なし<br>
     * @exception なし
     */
    public void setReservationNo(String reservationNo) {
        this.reservationNo = reservationNo;
    }

}