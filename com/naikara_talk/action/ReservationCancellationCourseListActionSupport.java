package com.naikara_talk.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dto.PointProvisionDataListDto;
import com.naikara_talk.dto.PointReleaseDataListDto;
import com.naikara_talk.dto.SchResTLesResPerTDto;
import com.naikara_talk.model.ReservationCancellationCourseListModel;
import com.naikara_talk.service.ReservationCancellationCourseListLoadService;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_予約処理<br>
 * <b>クラス名称       :</b>予約申込ページActionスーパークラス<br>
 * <b>クラス概要       :</b>受講者がレッスンを予約登録、取消ができる。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/18 TECS 新規作成
 * <b>                 :</b>2014/04/22 TECS 検索条件：希望日のコントロールをテキストからプルダウンへ変更対応
 */
public abstract class ReservationCancellationCourseListActionSupport extends NaikaraActionSupport {

    private static final long serialVersionUID = 1L;

    // 画面表示のタイトル
    protected String title = "予約申込ページ";

    // Help画面名
    protected String helpPageId = "HelpReservationCancellationCourseList.html";

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
            initSelect();
        } catch (Exception e) {
            e.printStackTrace();
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

        ReservationCancellationCourseListLoadService service = new ReservationCancellationCourseListLoadService();
        // 予約状況を取得する
        this.reservationKbn_rdll = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_RESERV_KBN);
        // 性別を取得する
        this.sexKbn_rdll = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_GENDER);
    }

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

        ReservationCancellationCourseListLoadService service = new ReservationCancellationCourseListLoadService();

        // 2014/04/22 Add Start 希望日をテキストからプルダウンへ変更対応
        LinkedHashMap<String, String> dtMap = new LinkedHashMap<String, String>();

        // 書式設定
        DateFormat df1 = new SimpleDateFormat(DateUtil.DATE_FORMAT_yyyy_MM_dd);

        // カレンダーインスタンスの取得
        Calendar calendar = Calendar.getInstance();

        String setDt = df1.format(calendar.getTime());
        dtMap.put(setDt, setDt);                       // LinkedHashMapへの追加(当日)
        for (int i = 1 ; i < 28 ; i++){
            calendar.add(Calendar.DATE, 1);            // 日付の加算(+1日)
            setDt = df1.format(calendar.getTime());
            dtMap.put(setDt, setDt);                   // LinkedHashMapへの追加
        }
        this.hopeDt_sell = dtMap;
        // 2014/04/22 Add End   希望日をテキストからプルダウンへ変更対応

        // 希望時刻(From)を取得する
        this.hopeTimeFr_sell = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_LESSON_TM_S);
        // 希望時刻(To)を取得する
        this.hopeTimeTo_sell = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_LESSON_TM_S);
        // 大分類を取得する
        this.courseLarge_sell = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_BIG_CLASSIFICATION);
        // 中分類を取得する
        this.courseMedium_sell = service.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_MIDDLE_CLASSIFICATION);
        // 小分類を取得する
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

        // 2014/04/22 Add Start 希望日をテキストからプルダウンへ変更対応
        // 希望日
        //this.model.setHopeDt(this.hopeDt_txt);
        this.model.setHopeDt(this.hopeDt_sel);
        // 2014/04/22 Add End   希望日をテキストからプルダウンへ変更対応

        // 希望時刻(From)
        this.model.setHopeTimeFr(this.hopeTimeFr_sel);
        // 希望時刻(To)
        this.model.setHopeTimeTo(this.hopeTimeTo_sel);
        // 予約状況
        this.model.setReservationKbn(this.reservationKbn_rdl);
        // 性別
        this.model.setSexKbn(this.sexKbn_rdl);
        // コースコード
        this.model.setCourseCode(this.courseCode_txt);
        // 大分類
        this.model.setCourseLarge(this.courseLarge_sel);
        // 中分類
        this.model.setCourseMedium(this.courseMedium_sel);
        // 小分類
        this.model.setCourseSmall(this.courseSmall_sel);
        // コース名：キーワード
        this.model.setCourseName(this.courseName_txt);

        // 一覧選択のデータ
        if (!StringUtils.isEmpty(this.getSelect_rdl())) {
            // 講師ID
            this.model.setTeacherId(this.teacherId);
            // 講師予定予約テーブル、レッスン予実テーブルDTO
            if (!this.schResTLesResPerTDtoList.isEmpty()) {
                this.model.setSchResTLesResPerTDto(this.schResTLesResPerTDtoList.get(0));
            }
        }
    }

    /** 画面ID */
    protected String pageId = NaikaraTalkConstants.RESERVATION_CANCELLATION_COURSE_LIST;

    // 2014/04/22 Upd Start 希望日をテキストからプルダウンへ変更対応
    /** 希望日 */
    //protected String hopeDt_txt;
    protected String hopeDt_sel;
    protected Map<String, String> hopeDt_sell = new LinkedHashMap<String, String>();
    // 2014/04/22 Upd End 希望日をテキストからプルダウンへ変更対応

    /** 希望時刻(From) */
    protected String hopeTimeFr_sel;
    protected Map<String, String> hopeTimeFr_sell = new LinkedHashMap<String, String>();

    /** 希望時刻(To) */
    protected String hopeTimeTo_sel;
    protected Map<String, String> hopeTimeTo_sell = new LinkedHashMap<String, String>();

    /** 予約状況 */
    protected String reservationKbn_rdl;
    protected Map<String, String> reservationKbn_rdll = new LinkedHashMap<String, String>();

    /** 性別 */
    protected String sexKbn_rdl;
    protected Map<String, String> sexKbn_rdll = new LinkedHashMap<String, String>();

    /** コースコード */
    protected String courseCode_txt;

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

    /** 検索結果 */
    protected ReservationCancellationCourseListModel model = new ReservationCancellationCourseListModel();

    /** 一覧から選択された明細データ(jsp) */
    protected String select_rdl;

    /** チェックエラー場合、検索判断フラグ */
    protected String hasSearchFlg;

    /** 一覧選択の講師ID */
    protected String teacherId;

    /** 一覧選択の講師名 */
    protected String teacherNm;

    /** 講師予定予約テーブル、レッスン予実テーブルDTOリスト */
    protected List<SchResTLesResPerTDto> schResTLesResPerTDtoList = new ArrayList<SchResTLesResPerTDto>();

    /** 引当データリスト */
    protected List<PointProvisionDataListDto> ppdlDtoList = new ArrayList<PointProvisionDataListDto>();

    /** 解除データリスト */
    protected List<PointReleaseDataListDto> prdlDtoList = new ArrayList<PointReleaseDataListDto>();

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
     * @return pageId
     */
    public String getPageId() {
        return pageId;
    }

    /**
     * @param pageId セットする pageId
     */
    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    // 2014/04/22 Upd Start 希望日をテキストからプルダウンへ変更対応
    /**
     * @return hopeDt_txt
     */
//    public String getHopeDt_txt() {
//        return hopeDt_txt;
//    }

    /**
     * @param hopeDt_txt セットする hopeDt_txt
     */
//    public void setHopeDt_txt(String hopeDt_txt) {
//        this.hopeDt_txt = hopeDt_txt;
//    }

    /**
     * @return hopeDt_sel
     */
    public String getHopeDt_sel() {
        return hopeDt_sel;
    }

    /**
     * @param hopeDt_sel セットする hopeDt_sel
     */
    public void setHopeDt_sel(String hopeDt_sel) {
        this.hopeDt_sel = hopeDt_sel;
    }

    /**
     * @return hopeDt_sell
     */
    public Map<String, String> getHopeDt_sell() {
        return hopeDt_sell;
    }

    /**
     * @param hopeDt_sell セットする hopeDt_sell
     */
    public void setHopeDt_sell(Map<String, String> hopeDt_sell) {
        this.hopeDt_sell = hopeDt_sell;
    }
    // 2014/04/22 Upd End   希望日をテキストからプルダウンへ変更対応

    /**
     * @return hopeTimeFr_sel
     */
    public String getHopeTimeFr_sel() {
        return hopeTimeFr_sel;
    }

    /**
     * @param hopeTimeFr_sel セットする hopeTimeFr_sel
     */
    public void setHopeTimeFr_sel(String hopeTimeFr_sel) {
        this.hopeTimeFr_sel = hopeTimeFr_sel;
    }

    /**
     * @return hopeTimeFr_sell
     */
    public Map<String, String> getHopeTimeFr_sell() {
        return hopeTimeFr_sell;
    }

    /**
     * @param hopeTimeFr_sell セットする hopeTimeFr_sell
     */
    public void setHopeTimeFr_sell(Map<String, String> hopeTimeFr_sell) {
        this.hopeTimeFr_sell = hopeTimeFr_sell;
    }

    /**
     * @return hopeTimeTo_sel
     */
    public String getHopeTimeTo_sel() {
        return hopeTimeTo_sel;
    }

    /**
     * @param hopeTimeTo_sel セットする hopeTimeTo_sel
     */
    public void setHopeTimeTo_sel(String hopeTimeTo_sel) {
        this.hopeTimeTo_sel = hopeTimeTo_sel;
    }

    /**
     * @return hopeTimeTo_sell
     */
    public Map<String, String> getHopeTimeTo_sell() {
        return hopeTimeTo_sell;
    }

    /**
     * @param hopeTimeTo_sell セットする hopeTimeTo_sell
     */
    public void setHopeTimeTo_sell(Map<String, String> hopeTimeTo_sell) {
        this.hopeTimeTo_sell = hopeTimeTo_sell;
    }

    /**
     * @return reservationKbn_rdl
     */
    public String getReservationKbn_rdl() {
        return reservationKbn_rdl;
    }

    /**
     * @param reservationKbn_rdl セットする reservationKbn_rdl
     */
    public void setReservationKbn_rdl(String reservationKbn_rdl) {
        this.reservationKbn_rdl = reservationKbn_rdl;
    }

    /**
     * @return reservationKbn_rdll
     */
    public Map<String, String> getReservationKbn_rdll() {
        return reservationKbn_rdll;
    }

    /**
     * @param reservationKbn_rdll セットする reservationKbn_rdll
     */
    public void setReservationKbn_rdll(Map<String, String> reservationKbn_rdll) {
        this.reservationKbn_rdll = reservationKbn_rdll;
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

    /**
     * @return courseCode_txt
     */
    public String getCourseCode_txt() {
        return courseCode_txt;
    }

    /**
     * @param courseCode_txt セットする courseCode_txt
     */
    public void setCourseCode_txt(String courseCode_txt) {
        this.courseCode_txt = courseCode_txt;
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
     * @return model
     */
    public ReservationCancellationCourseListModel getModel() {
        return model;
    }

    /**
     * @param model セットする model
     */
    public void setModel(ReservationCancellationCourseListModel model) {
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
     * @return schResTLesResPerTDtoList
     */
    public List<SchResTLesResPerTDto> getSchResTLesResPerTDtoList() {
        return schResTLesResPerTDtoList;
    }

    /**
     * @param schResTLesResPerTDtoList セットする schResTLesResPerTDtoList
     */
    public void setSchResTLesResPerTDtoList(List<SchResTLesResPerTDto> schResTLesResPerTDtoList) {
        this.schResTLesResPerTDtoList = schResTLesResPerTDtoList;
    }

    /**
     * @return ppdlDtoList
     */
    public List<PointProvisionDataListDto> getPpdlDtoList() {
        return ppdlDtoList;
    }

    /**
     * @param ppdlDtoList セットする ppdlDtoList
     */
    public void setPpdlDtoList(List<PointProvisionDataListDto> ppdlDtoList) {
        this.ppdlDtoList = ppdlDtoList;
    }

    /**
     * @return prdlDtoList
     */
    public List<PointReleaseDataListDto> getPrdlDtoList() {
        return prdlDtoList;
    }

    /**
     * @param prdlDtoList セットする prdlDtoList
     */
    public void setPrdlDtoList(List<PointReleaseDataListDto> prdlDtoList) {
        this.prdlDtoList = prdlDtoList;
    }

}
