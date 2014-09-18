package com.naikara_talk.action;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.model.TeacherListModel;
import com.naikara_talk.service.TeacherListLoadService;
import com.naikara_talk.sessiondata.SessionTeacher;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_予約処理<br>
 * <b>クラス名称       :</b>講師一覧（Pop）Actionスーパークラス<br>
 * <b>クラス概要       :</b>登録済みの講師情報の検索処理を行い。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/19 TECS 新規作成
 *                     :</b>2014/02/18 TECS 商品購入ページからの呼出追加に伴う修正
 *                     :</b>2014/04/22 TECS 検索条件の追加(コース名)対応
 */
public abstract class TeacherListActionSupport extends NaikaraActionSupport {

    private static final long serialVersionUID = 1L;

    // 画面表示のタイトル
    protected String title = "講師一覧";

    // Help画面名
    protected String helpPageId = "HelpTeacherList.html";

    /**
     * チェック。<br>
     * <br>
     * 画面ラジオとコンボを初期化する<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception なし
     */
    @Override
    public void validate() {

        // チェックエラーの場合、初期データ再取得。
        try {
            initRadio();

            // 2014/04/22 Add Start 検索条件の追加(コース名)対応
            this.initSelect();
            // 2014/04/22 Add End   検索条件の追加(コース名)対応

        } catch (Exception e) {
            e.printStackTrace();
        }
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
        // 講師ID
        this.model.setTeacherId(this.teacherId_txt);
        // 講師名(ニックネーム)
        this.model.setTeacherNm(this.teacherNm_txt);
        // 性別
        this.model.setSexKbn(this.sexKbn_rdl);

        // 2014/04/22 Add Start 検索条件の追加(コース名)対応
        // 大分類
        this.model.setCourseLargeCd(this.courseLarge_sel);
        // 中分類
        this.model.setCourseMediumCd(this.courseMedium_sel);
        // 小分類
        this.model.setCourseSmallCd(this.courseSmall_sel);
        // コース名：キーワード
        this.model.setCourseJNm(this.courseName_txt);
        // 2014/04/22 Add End   検索条件の追加(コース名)対応

        // 一覧選択のデータ
        if (!StringUtils.isEmpty(this.getSelect_rdl())) {
            // 一覧選択の講師ID
            this.model.setTeacherIdSel(this.teacherId);
            // 一覧選択の講師名(ニックネーム)
            this.model.setTeacherNmSel(this.teacherNm);
        }
    }

    /**
     * 一覧から選択された明細データの取得。<br>
     * <br>
     * 一覧から選択された明細データの取得。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @throws Exception
     */
    public void load() {
        if (!StringUtils.isEmpty(this.select_rdl)) {
            String[] selected = this.getSelect_rdl().substring(1, this.getSelect_rdl().length() - 1)
                    .replaceAll(NaikaraTalkConstants.HALF_SPACE, NaikaraTalkConstants.BRANK)
                    .split(String.valueOf(NaikaraTalkConstants.COMMA));
            // 一覧選択の講師ID
            this.teacherId = selected[0];
            // 一覧選択の講師名(ニックネーム)
            this.teacherNm = selected[1];
        }
    }

    /**
     * ラジオボタンリスト取得。<br>
     * <br>
     * ラジオボタンリストを取得する。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @throws Exception
     */
    public void initRadio() throws Exception {

        TeacherListLoadService service = new TeacherListLoadService();
        // 性別を取得する
        this.sexKbn_rdll = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_GENDER);
    }


    // 2014/04/22 Add Start 検索条件の追加(コース名)対応
    /**
     * セレクトボックス取得。<br>
     * <br>
     * セレクトボックスを取得する。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @throws Exception
     */
    public void initSelect() throws Exception {

    	TeacherListLoadService service = new TeacherListLoadService();
        // 大分類を取得する
        this.courseLarge_sell = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_BIG_CLASSIFICATION);
        // 中分類を取得する
        this.courseMedium_sell = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_MIDDLE_CLASSIFICATION);
        // 小分類を取得する
        this.courseSmall_sell = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_SMALL_CLASSIFICATION);
    }
    // 2014/04/22 Add End   検索条件の追加(コース名)対応


    /**
     * Model値 To Session<br>
     * <br>
     * Model値をSessionTeacherにセット。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    protected void modelToSessionTeacher() throws Exception {

        // 戻る用に必要な情報を格納
        SessionTeacher sessionData = new SessionTeacher();

        if ((SessionTeacher) SessionDataUtil.getSessionData(SessionTeacher.class.toString()) != null) {
            // 戻る用に必要な情報を取得
            SessionTeacher seTeacher = (SessionTeacher) SessionDataUtil.getSessionData(SessionTeacher.class.toString());

            // 検索Key：講師ID
            sessionData.setSearchTeacherId(seTeacher.getSearchTeacherId());
            // 検索Key：講師名(ニックネーム）
            sessionData.setSearchTeacherNm(seTeacher.getSearchTeacherNm());
            // 検索Key：性別
            sessionData.setSearchSexKbn(seTeacher.getSearchSexKbn());

            // 2014/04/22 Add Start 検索条件の追加(コース名)対応
            // 検索Key：大分類
            sessionData.setSearchCourseLargeCd(seTeacher.getSearchCourseLargeCd());
            // 検索Key：中分類
            sessionData.setSearchCourseMediumCd(seTeacher.getSearchCourseMediumCd());
            // 検索Key：小分類
            sessionData.setSearchCourseSmallCd(seTeacher.getSearchCourseSmallCd());
            // 検索Key：コース名(テキスト欄)
            sessionData.setSearchCourseJNm(seTeacher.getSearchCourseJNm());
            // 2014/04/22 Add End   検索条件の追加(コース名)対応

        }

        // 戻る判定Onフラグ
        sessionData.setReturnOnFlg(true);
        // 講師ID
        sessionData.setTeacherId(this.model.getTeacherId());
        // 講師名(ニックネーム）
        sessionData.setTeacherNm(this.model.getTeacherNm());
        // 性別
        sessionData.setSexKbn(this.model.getSexKbn());
        // 検索Key：選択された明細のKey-商品コード
        sessionData.setSearchTeacherIdKey(this.getSelect_rdl());
        // 検索判断フラグ
        sessionData.setHasSearchFlg(this.getHasSearchFlg());

        // 2014/04/22 Add Start 検索条件の追加(コース名)対応
        // 大分類
        sessionData.setCourseLargeCd(this.model.getCourseLarge());
        // 中分類
        sessionData.setCourseMediumCd(this.model.getCourseMedium());
        // 小分類
        sessionData.setCourseSmallCd(this.model.getCourseSmall());
        // コース名(テキスト欄)
        sessionData.setCourseJNm(this.model.getCourseJNm());
        // 2014/04/22 Add End   検索条件の追加(コース名)対応


        SessionDataUtil.setSessionData(sessionData);
    }

    /** メッセージ */
    protected String message;

    /** 講師ID */
    protected String teacherId_txt;

    /** 講師名(ニックネーム) */
    protected String teacherNm_txt;

    /** 性別 */
    protected String sexKbn_rdl;
    protected Map<String, String> sexKbn_rdll = new LinkedHashMap<String, String>();

    // 2014/04/22 Add Start 検索条件の追加(コース名)対応
    /** コース名：大分類 */
    protected String courseLarge_sel;
    protected Map<String, String> courseLarge_sell = new LinkedHashMap<String, String>();

    /** コース名：中分類 */
    protected String courseMedium_sel;
    protected Map<String, String> courseMedium_sell = new LinkedHashMap<String, String>();

    /** コース名：小分類 */
    protected String courseSmall_sel;
    protected Map<String, String> courseSmall_sell = new LinkedHashMap<String, String>();

    /** コース名：キーワード */
    protected String courseName_txt;
    // 2014/04/22 Add End   検索条件の追加(コース名)対応


    /** 検索結果 */
    protected TeacherListModel model = new TeacherListModel();

    /** 一覧から選択された明細データ(jsp) */
    protected String select_rdl;

    /** チェックエラー場合、検索判断フラグ */
    protected String hasSearchFlg;

    /** 一覧選択の講師ID */
    protected String teacherId;

    /** 一覧選択の講師名(ニックネーム) */
    protected String teacherNm;

    /** closeFlg */
    protected String closeFlg;

    // 2014/02/18 Add Start 商品購入ページからの呼出追加に伴う修正
    /** 呼出元：押下されたボタンのIndex */
    protected String buttonIndex;

    /** 呼出元：商品購入ページかどうかの判断フラグ */
    protected String hasSearchTcontactFlg;

    /** 一覧部の「選択」が選択されていない場合のエラーメッセージ保持用 */
    protected String error_noSelect;
    // 2014/02/18 Add End 商品購入ページからの呼出追加に伴う修正


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
     * @return teacherNm_txt
     */
    public String getTeacherNm_txt() {
        return teacherNm_txt;
    }

    /**
     * @param teacherNm_txt セットする teacherNm_txt
     */
    public void setTeacherNm_txt(String teacherNm_txt) {
        this.teacherNm_txt = teacherNm_txt;
    }

    /**
     * @return sexKbn_rdl
     */
    public String getSexKbn_rdl() {
        return sexKbn_rdl;
    }

    /**
     * @param sexKbn_rdl セットする sexKbn_rdl
     */
    public void setSexKbn_rdl(String sexKbn_rdl) {
        this.sexKbn_rdl = sexKbn_rdl;
    }

    /**
     * @return sexKbn_rdll
     */
    public Map<String, String> getSexKbn_rdll() {
        return sexKbn_rdll;
    }

    /**
     * @param sexKbn_rdll セットする sexKbn_rdll
     */
    public void setSexKbn_rdll(Map<String, String> sexKbn_rdll) {
        this.sexKbn_rdll = sexKbn_rdll;
    }

    // 2014/04/22 Add Start 検索条件の追加(コース名)対応
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
    // 2014/04/22 Add End   検索条件の追加(コース名)対応

    /**
     * @return model
     */
    public TeacherListModel getModel() {
        return model;
    }

    /**
     * @param model セットする model
     */
    public void setModel(TeacherListModel model) {
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

    /**
     * @return teacherId
     */
    public String getTeacherId() {
        return teacherId;
    }

    /**
     * @param teacherId セットする teacherId
     */
    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * @return teacherNm
     */
    public String getTeacherNm() {
        return teacherNm;
    }

    /**
     * @param teacherNm セットする teacherNm
     */
    public void setTeacherNm(String teacherNm) {
        this.teacherNm = teacherNm;
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


    // 2014/02/18 Add Start 商品購入ページからの呼出追加に伴う修正
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
     * @return hasSearchTcontactFlg
     */
    public String getHasSearchTcontactFlg() {
        return hasSearchTcontactFlg;
    }

    /**
     * @param hasSearchTcontactFlg セットする hasSearchTcontactFlg
     */
    public void setHasSearchTcontactFlg(String hasSearchTcontactFlg) {
        this.hasSearchTcontactFlg = hasSearchTcontactFlg;
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
    // 2014/02/18 Add End 商品購入ページからの呼出追加に伴う修正


}
