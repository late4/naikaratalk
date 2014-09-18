package com.naikara_talk.action;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.TeacherCourseSelectionListModel;
import com.naikara_talk.service.TeacherCourseSelectionListLoadService;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>講師マスタメンテナンス(コース 選択)<br>
 * <b>クラス概要       :</b>講師マスタメンテナンス(コース 選択)共通Action<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/01 TECS 新規作成
 */
public abstract class TeacherCourseSelectionListActionSupport extends NaikaraActionSupport {

    private static final long serialVersionUID = 1L;

    // 画面表示のタイトル
    protected String title = "コース 選択";

    // Help画面名
    protected String helpPageId = "HelpTeacherCourseSelectionList.html";

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
        // ボタン区分
        this.model.setButtonIndex(this.buttonIndex);
        // 契約期間：開始日
        this.model.setContractStart(this.contractStart);
        // 契約期間：終了日
        this.model.setContractEnd(this.contractEnd);

    }

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
    protected TeacherCourseSelectionListModel model = new TeacherCourseSelectionListModel();

    /** 一覧部の「選択」 */
    protected List<String> select_chk_list = new ArrayList<String>();

    /** ボタン区分 */
    protected String buttonIndex;

    /** 契約期間：開始日 */
    protected String contractStart;

    /** 契約期間：終了日 */
    protected String contractEnd;

    /** 一覧部の「選択」が選択されていない場合 */
    protected String error_noSelect;

    /** 前画面の　「複数選択」　ボタンで遷移した場合は、最大１０個。 */
    protected String error_gtTen;

    /** 前画面の　「検索」　ボタンで遷移した場合は、最大１個 */
    protected String error_gtOne;

    /** チェックエラー場合、検索判断フラグ */
    protected String hasSearchFlg;

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
    public TeacherCourseSelectionListModel getModel() {
        return model;
    }

    /**
     * @param model セットする model
     */
    public void setModel(TeacherCourseSelectionListModel model) {
        this.model = model;
    }

    /**
     * @return select_chk_list
     */
    public List<String> getSelect_chk_list() {
        return select_chk_list;
    }

    /**
     * @param select_chk_list セットする select_chk_list
     */
    public void setSelect_chk_list(List<String> select_chk_list) {
        this.select_chk_list = select_chk_list;
    }

    /**
     * @return buttonIndex
     */
    public String getButtonIndex() {
        return buttonIndex;
    }

    /**
     * @param buttonIndex セットする buttonIndex
     */
    public void setButtonIndex(String buttonIndex) {
        this.buttonIndex = buttonIndex;
    }

    /**
     * @return contractStart
     */
    public String getContractStart() {
        return contractStart;
    }

    /**
     * @param contractStart セットする contractStart
     */
    public void setContractStart(String contractStart) {
        this.contractStart = contractStart;
    }

    /**
     * @return contractEnd
     */
    public String getContractEnd() {
        return contractEnd;
    }

    /**
     * @param contractEnd セットする contractEnd
     */
    public void setContractEnd(String contractEnd) {
        this.contractEnd = contractEnd;
    }

    /**
     * @return error_noSelect
     */
    public String getError_noSelect() {
        return error_noSelect;
    }

    /**
     * @param error_noSelect セットする error_noSelect
     */
    public void setError_noSelect(String error_noSelect) {
        this.error_noSelect = error_noSelect;
    }

    /**
     * @return error_gtTen
     */
    public String getError_gtTen() {
        return error_gtTen;
    }

    /**
     * @param error_gtTen セットする error_gtTen
     */
    public void setError_gtTen(String error_gtTen) {
        this.error_gtTen = error_gtTen;
    }

    /**
     * @return error_gtOne
     */
    public String getError_gtOne() {
        return error_gtOne;
    }

    /**
     * @param error_gtOne セットする error_gtOne
     */
    public void setError_gtOne(String error_gtOne) {
        this.error_gtOne = error_gtOne;
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
