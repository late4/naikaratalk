package com.naikara_talk.action;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.naikara_talk.dto.PointProvisionDataListDto;
import com.naikara_talk.dto.PointReleaseDataListDto;
import com.naikara_talk.dto.SchResTLesResPerTDto;
import com.naikara_talk.model.ReservationCancellationCourseSelectionListModel;
import com.naikara_talk.service.ReservationCancellationCourseSelectionListLoadService;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_予約処理<br>
 * <b>クラス名称       :</b>コース名選択ページActionスーパークラス<br>
 * <b>クラス概要       :</b>受講者が指定した講師の予約スケジュールを表示して、レッスン予約の登録／取消ができる。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/29 TECS 新規作成
 */
public abstract class ReservationCancellationCourseSelectionListActionSupport extends NaikaraActionSupport {

    private static final long serialVersionUID = 1L;

    // 画面表示のタイトル
    protected String title = "コース名選択";

    // Help画面名
    protected String helpPageId = "HelpReservationCancellationCourseSelectionList.html";

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
        this.model.setTeacherId(this.teacherId);
        // 一覧のDTOリスト
        this.model.setResultList(this.schResTLesResPerTDtoList);
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

        // 初期化
        ReservationCancellationCourseSelectionListLoadService service = new ReservationCancellationCourseSelectionListLoadService();

        // ｢コース名｣のドロップダウンリストのデータの取得
        this.course_sell = service.getCourseLinkedHashMap(this.model);
    }

    /** 画面ID */
    protected String pageId = NaikaraTalkConstants.RESERVATION_CANCELLATION_COURSE_SELECTION_LIST;

    /** 講師ID */
    protected String teacherId;

    /** 講師名（ニックネーム） */
    protected String teacherNm;

    /** 一覧のDTOリスト */
    protected List<SchResTLesResPerTDto> schResTLesResPerTDtoList = new ArrayList<SchResTLesResPerTDto>();

    /** 検索結果 */
    protected ReservationCancellationCourseSelectionListModel model = new ReservationCancellationCourseSelectionListModel();

    /** コース名のドロップダウンリスト */
    protected String[] course_sel;
    protected Map<String, String> course_sell = new LinkedHashMap<String, String>();

    /** 引当データリスト */
    protected List<PointProvisionDataListDto> ppdlDtoList = new ArrayList<PointProvisionDataListDto>();

    /** 解除データリスト */
    protected List<PointReleaseDataListDto> prdlDtoList = new ArrayList<PointReleaseDataListDto>();

    /** 現ポイント残高 */
    protected String balancePointOld;

    /** ご利用ポイント合計 */
    protected String usePointSum;

    /** お取消ポイント合計 */
    protected String cancelPointSum;

    /** 新ポイント残高 */
    protected String balancePointNew;

    /** 戻る判定Onフラグ */
    protected boolean returnOnFlg;

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return helpPageId
     */
    public String getHelpPageId() {
        return helpPageId;
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
     * @return model
     */
    public ReservationCancellationCourseSelectionListModel getModel() {
        return model;
    }

    /**
     * @param model セットする model
     */
    public void setModel(ReservationCancellationCourseSelectionListModel model) {
        this.model = model;
    }

    /**
     * @return course_sel
     */
    public String[] getCourse_sel() {
        return course_sel;
    }

    /**
     * @param course_sel セットする course_sel
     */
    public void setCourse_sel(String[] course_sel) {
        this.course_sel = course_sel;
    }

    /**
     * @return course_sell
     */
    public Map<String, String> getCourse_sell() {
        return course_sell;
    }

    /**
     * @param course_sell セットする course_sell
     */
    public void setCourse_sell(Map<String, String> course_sell) {
        this.course_sell = course_sell;
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

    /**
     * @return balancePointOld
     */
    public String getBalancePointOld() {
        return balancePointOld;
    }

    /**
     * @param balancePointOld セットする balancePointOld
     */
    public void setBalancePointOld(String balancePointOld) {
        this.balancePointOld = balancePointOld;
    }

    /**
     * @return usePointSum
     */
    public String getUsePointSum() {
        return usePointSum;
    }

    /**
     * @param usePointSum セットする usePointSum
     */
    public void setUsePointSum(String usePointSum) {
        this.usePointSum = usePointSum;
    }

    /**
     * @return cancelPointSum
     */
    public String getCancelPointSum() {
        return cancelPointSum;
    }

    /**
     * @param cancelPointSum セットする cancelPointSum
     */
    public void setCancelPointSum(String cancelPointSum) {
        this.cancelPointSum = cancelPointSum;
    }

    /**
     * @return balancePointNew
     */
    public String getBalancePointNew() {
        return balancePointNew;
    }

    /**
     * @param balancePointNew セットする balancePointNew
     */
    public void setBalancePointNew(String balancePointNew) {
        this.balancePointNew = balancePointNew;
    }

    /**
     * @return returnOnFlg
     */
    public boolean isReturnOnFlg() {
        return returnOnFlg;
    }

    /**
     * @param returnOnFlg セットする returnOnFlg
     */
    public void setReturnOnFlg(boolean returnOnFlg) {
        this.returnOnFlg = returnOnFlg;
    }

}
