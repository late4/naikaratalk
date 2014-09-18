package com.naikara_talk.action;

import java.util.LinkedHashMap;
import java.util.Map;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.CourseMstListModel;
import com.naikara_talk.service.TeacherCourseSelectionListLoadService;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>コースマスタメンテナンス(一覧)<br>
 * <b>クラス概要       :</b>コースマスタメンテナンス(一覧)共通Action<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/02 TECS 新規作成
 */
public abstract class CourseMstListActionSupport extends NaikaraActionSupport {

    private static final long serialVersionUID = 1L;

    // 画面表示のタイトル
    protected String title = "コースマスタメンテナンス";

    // Help画面名
    protected String helpPageId = "HelpCourseMstList.html";

    /**
     * 大分類、中分類、小分類の再取得<br>
     * <br>
     * チェックエラーの場合、大分類、中分類、小分類の再取得。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception なし
     */
    @Override
    public void validate() {

        // チェックエラーの場合、大分類、中分類、小分類の再取得。
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
     * 大分類、中分類、小分類を取得する。<br>
     * <br>
     * 大分類、中分類、小分類を取得する。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    public void initRadio() throws Exception {

        TeacherCourseSelectionListLoadService service = new TeacherCourseSelectionListLoadService();
        // 大分類、中分類、小分類を取得する
        this.courseLarge_sell = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_BIG_CLASSIFICATION);
        this.courseMedium_sell = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_MIDDLE_CLASSIFICATION);
        this.courseSmall_sell = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_SMALL_CLASSIFICATION);
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

        // 処理区分
        this.model.setProcessKbn(this.processKbn_rdl);
        // 大分類
        this.model.setBigClassificationCd(this.courseLarge_sel);
        // 中分類
        this.model.setMiddleClassificationCd(this.courseMedium_sel);
        // 小分類
        this.model.setSmallClassificationCd(this.courseSmall_sel);
        // コース名
        this.model.setCourseJnm(this.courseName_txt);
        // キーワード1
        this.model.setKeyword1(this.keyword1_txt);
        // キーワード2
        this.model.setKeyword2(this.keyword2_txt);
        // キーワード3
        this.model.setKeyword3(this.keyword3_txt);
        // キーワード4
        this.model.setKeyword4(this.keyword4_txt);
        // キーワード5
        this.model.setKeyword5(this.keyword5_txt);
    }

    /** メッセージ */
    protected String message;

    /** 処理区分(jsp) */
    protected String processKbn_rdl;

    /** 大分類 */
    protected String courseLarge_sel;
    protected Map<String, String> courseLarge_sell = new LinkedHashMap<String, String>();

    /** 中分類 */
    protected String courseMedium_sel;
    protected Map<String, String> courseMedium_sell = new LinkedHashMap<String, String>();

    /** 小分類 */
    protected String courseSmall_sel;
    protected Map<String, String> courseSmall_sell = new LinkedHashMap<String, String>();

    /** コース名 */
    protected String courseName_txt;

    /** キーワード1 */
    protected String keyword1_txt;

    /** キーワード2 */
    protected String keyword2_txt;

    /** キーワード3 */
    protected String keyword3_txt;

    /** キーワード4 */
    protected String keyword4_txt;

    /** キーワード5 */
    protected String keyword5_txt;

    /** 処理結果 */
    protected CourseMstListModel model = new CourseMstListModel();

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
     * @return processKbn_rdl
     */
    public String getProcessKbn_rdl() {
        return processKbn_rdl;
    }

    /**
     * @param processKbn_rdl セットする processKbn_rdl
     */
    public void setProcessKbn_rdl(String processKbn_rdl) {
        this.processKbn_rdl = processKbn_rdl;
    }

    /**
     * @return courseLarge_sel
     */
    public String getCourseLarge_sel() {
        return courseLarge_sel;
    }

    /**
     * @param courseLarge_sel セットする courseLarge_sel
     */
    public void setCourseLarge_sel(String courseLarge_sel) {
        this.courseLarge_sel = courseLarge_sel;
    }

    /**
     * @return courseLarge_sell
     */
    public Map<String, String> getCourseLarge_sell() {
        return courseLarge_sell;
    }

    /**
     * @param courseLarge_sell セットする courseLarge_sell
     */
    public void setCourseLarge_sell(Map<String, String> courseLarge_sell) {
        this.courseLarge_sell = courseLarge_sell;
    }

    /**
     * @return courseMedium_sel
     */
    public String getCourseMedium_sel() {
        return courseMedium_sel;
    }

    /**
     * @param courseMedium_sel セットする courseMedium_sel
     */
    public void setCourseMedium_sel(String courseMedium_sel) {
        this.courseMedium_sel = courseMedium_sel;
    }

    /**
     * @return courseMedium_sell
     */
    public Map<String, String> getCourseMedium_sell() {
        return courseMedium_sell;
    }

    /**
     * @param courseMedium_sell セットする courseMedium_sell
     */
    public void setCourseMedium_sell(Map<String, String> courseMedium_sell) {
        this.courseMedium_sell = courseMedium_sell;
    }

    /**
     * @return courseSmall_sel
     */
    public String getCourseSmall_sel() {
        return courseSmall_sel;
    }

    /**
     * @param courseSmall_sel セットする courseSmall_sel
     */
    public void setCourseSmall_sel(String courseSmall_sel) {
        this.courseSmall_sel = courseSmall_sel;
    }

    /**
     * @return courseSmall_sell
     */
    public Map<String, String> getCourseSmall_sell() {
        return courseSmall_sell;
    }

    /**
     * @param courseSmall_sell セットする courseSmall_sell
     */
    public void setCourseSmall_sell(Map<String, String> courseSmall_sell) {
        this.courseSmall_sell = courseSmall_sell;
    }

    /**
     * @return courseName_txt
     */
    public String getCourseName_txt() {
        return courseName_txt;
    }

    /**
     * @param courseName_txt セットする courseName_txt
     */
    public void setCourseName_txt(String courseName_txt) {
        this.courseName_txt = courseName_txt;
    }

    /**
     * @return keyword1_txt
     */
    public String getKeyword1_txt() {
        return keyword1_txt;
    }

    /**
     * @param keyword1_txt セットする keyword1_txt
     */
    public void setKeyword1_txt(String keyword1_txt) {
        this.keyword1_txt = keyword1_txt;
    }

    /**
     * @return keyword2_txt
     */
    public String getKeyword2_txt() {
        return keyword2_txt;
    }

    /**
     * @param keyword2_txt セットする keyword2_txt
     */
    public void setKeyword2_txt(String keyword2_txt) {
        this.keyword2_txt = keyword2_txt;
    }

    /**
     * @return keyword3_txt
     */
    public String getKeyword3_txt() {
        return keyword3_txt;
    }

    /**
     * @param keyword3_txt セットする keyword3_txt
     */
    public void setKeyword3_txt(String keyword3_txt) {
        this.keyword3_txt = keyword3_txt;
    }

    /**
     * @return keyword4_txt
     */
    public String getKeyword4_txt() {
        return keyword4_txt;
    }

    /**
     * @param keyword4_txt セットする keyword4_txt
     */
    public void setKeyword4_txt(String keyword4_txt) {
        this.keyword4_txt = keyword4_txt;
    }

    /**
     * @return keyword5_txt
     */
    public String getKeyword5_txt() {
        return keyword5_txt;
    }

    /**
     * @param keyword5_txt セットする keyword5_txt
     */
    public void setKeyword5_txt(String keyword5_txt) {
        this.keyword5_txt = keyword5_txt;
    }

    /**
     * @return model
     */
    public CourseMstListModel getModel() {
        return model;
    }

    /**
     * @param model セットする model
     */
    public void setModel(CourseMstListModel model) {
        this.model = model;
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
