package com.naikara_talk.action;

import java.util.ArrayList;
import java.util.List;

import com.naikara_talk.dto.PointProvisionDataListDto;
import com.naikara_talk.dto.PointReleaseDataListDto;
import com.naikara_talk.dto.SchResTLesResPerTDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.SComReservationCancellationConfirmationModel;
import com.naikara_talk.service.ReservationCancellationCourseSelectionListMoveService;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_予約処理<br>
 * <b>クラス名称       :</b>予約／取消確認ページActionスーパークラス<br>
 * <b>クラス概要       :</b>受講者が指定した講師の予約スケジュールを表示して、レッスン予約の登録／取消ができる。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/02 TECS 新規作成
 */
public abstract class SComReservationCancellationConfirmationActionSupport extends NaikaraActionSupport {

    private static final long serialVersionUID = 1L;

    // 画面表示のタイトル
    protected String title = "予約／取消確認ページ";

    // Help画面名
    protected String helpPageId = "HelpSComReservationCancellationConfirmation.html";

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

        // ログイン情報を取得
        SessionUser sessionUser = (SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString());

        // ログインID
        this.model.setLoginId(sessionUser.getUserId());
        // ログイン名
        this.model.setLoginNm(sessionUser.getUserNm());
        // 遷移元画面ID
        this.model.setPageId(this.pageId);
        // 講師ID
        this.model.setTeacherId(this.teacherId);
        // 講師名（ニックネーム）
        this.model.setTeacherNm(this.teacherNm);
        // 一覧のデータ
        this.model.setResultList(this.getSchResTLesResPerTDtoList());
        // 引当データリスト
        this.model.setPpdlDtoList(this.ppdlDtoList);
        // 解除データリスト
        this.model.setPrdlDtoList(this.prdlDtoList);
    }

    /**
     * ポイント合計の設定。<br>
     * <br>
     * ポイント合計の設定する。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @throws NaiException
     */
    protected void setPointSum() throws NaiException {

        ReservationCancellationCourseSelectionListMoveService service = new ReservationCancellationCourseSelectionListMoveService();

        // 現ポイント残高の取得
        this.balancePointOld = service.getBalancePointOldSum();

        // ご利用ポイント合計の取得
        this.usePointSum = service.getUserPointSum(this.schResTLesResPerTDtoList);

        // お取消ポイント合計の取得
        this.cancelPointSum = service.getCancelPointSum(this.schResTLesResPerTDtoList);

        // 新ポイント残高の取得
        this.balancePointNew = service.getBalancePointNewSum(this.balancePointOld, this.usePointSum,
                this.cancelPointSum);
    }

    /** メッセージ */
    protected String message;

    /** 遷移元画面ID */
    protected String pageId;

    /** 講師ID */
    protected String teacherId;

    /** 講師名（ニックネーム） */
    protected String teacherNm;

    /** コース名 */
    protected String[] course_sel;

    /** 一覧のDTOリスト */
    protected List<SchResTLesResPerTDto> schResTLesResPerTDtoList = new ArrayList<SchResTLesResPerTDto>();

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

    /** モデル */
    protected SComReservationCancellationConfirmationModel model = new SComReservationCancellationConfirmationModel();

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
     * @return model
     */
    public SComReservationCancellationConfirmationModel getModel() {
        return model;
    }

    /**
     * @param model セットする model
     */
    public void setModel(SComReservationCancellationConfirmationModel model) {
        this.model = model;
    }

}
