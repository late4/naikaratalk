package com.naikara_talk.action;

import java.util.ArrayList;
import java.util.List;

import com.naikara_talk.dto.SchResTLesResPerTDto;
import com.naikara_talk.dto.TeacherCourseDto;
import com.naikara_talk.model.ReservationCancellationTeacherScheduleModel;
import com.naikara_talk.service.ReservationCancellationTeacherScheduleLoadService;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_予約処理<br>
 * <b>クラス名称       :</b>予約スケジュールActionスーパークラス<br>
 * <b>クラス概要       :</b>受講者が指定した講師の予約スケジュールを表示して、レッスン予約の登録／取消ができる。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/25 TECS 新規作成
 */
public abstract class ReservationCancellationTeacherScheduleActionSupport extends NaikaraActionSupport {

    private static final long serialVersionUID = 1L;

    // 画面表示のタイトル
    protected String title = "予約スケジュール";

    // Help画面名
    protected String helpPageId = "HelpReservationCancellationTeacherSchedule.html";

    /**
     * 初期処理、表示データの取得。<br>
     * <br>
     * 初期処理、表示データを取得する。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @throws Exception
     */
    protected void load() throws Exception {

        ReservationCancellationTeacherScheduleLoadService service = new ReservationCancellationTeacherScheduleLoadService();

        // 講師ID
        this.model.setTeacherId(this.teacherId);
        // 日数
        this.model.setDayCnt(this.dayCnt);

        // 利用者マスタ、講師マスタから対象データの取得処理を行う
        this.model = service.selectUserMstTeacherMst(this.model);

        // 講師単位のコースリスト取得処理
        List<TeacherCourseDto> teacherCourseDtoList = service.selectTeacherCourseList(model.getTeacherId(),
                DateUtil.getSysDate(), DateUtil.getSysDateAddDay(27));

        // 返却されたデータが存在しない場合
        if (teacherCourseDtoList.get(0).getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_NO) {
            // メッセージ情報を設定する
            this.addActionMessage(getMessage("EC0042", new String[] { "提供可能コースの取得処理 " }));
        } else {
            this.model.setTeacherCourseDtoList(teacherCourseDtoList);
        }

        // 一覧のヘッダ部と明細部の取得
        this.model = service.select(this.model);

        // 講師ID
        this.teacherId = model.getTeacherId();
        // 講師名（ニックネーム
        this.teacherNm = model.getTeacherNm();
        // 性別
        this.genderKbnNm = model.getGenderKbnNm();
        // 母国語
        this.nativeLang = model.getNativeLang();
        // 国籍
        this.nationality = model.getNationality();
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
        // 深夜・早朝のレッスンチェックBOX
        this.model.setDetails0(this.details0_chkn);
        // 朝・午前のレッスンチェックBOX
        this.model.setDetails1(this.details1_chkn);
        // 午後・夕方のレッスンチェックBOX
        this.model.setDetails2(this.details2_chkn);
        // 夕方・夜のレッスンチェックBOX
        this.model.setDetails3(this.details3_chkn);
    }

    /** 講師ID */
    protected String teacherId;

    /** 講師名（ニックネーム） */
    protected String teacherNm;

    /** 性別 */
    protected String genderKbnNm;

    /** 母国語 */
    protected String nativeLang;

    /** 国籍 */
    protected String nationality;

    /** コースコード */
    protected String courseCd;

    /** コース名 */
    protected String courseNm;

    /** 日数 */
    protected int dayCnt;

    /** 深夜・早朝のレッスンチェックBOX */
    protected String[] details0_chkn;

    /** 朝・午前のレッスンチェックBOX */
    protected String[] details1_chkn;

    /** 午後・夕方のレッスンチェックBOX */
    protected String[] details2_chkn;

    /** 夕方・夜のレッスンチェックBOX */
    protected String[] details3_chkn;

    /** 検索結果 */
    protected ReservationCancellationTeacherScheduleModel model = new ReservationCancellationTeacherScheduleModel();

    /** 一覧から選択されたチェックBOXのデータ */
    protected List<SchResTLesResPerTDto> schResTLesResPerTDtoList = new ArrayList<SchResTLesResPerTDto>();

    /** 深夜・早朝のレッスン表示フラグ */
    protected boolean displayFlg0;

    /** 朝・午前のレッスン表示フラグ */
    protected boolean displayFlg1;

    /** 午後・夕方のレッスン表示フラグ */
    protected boolean displayFlg2;

    /** 夕方・夜のレッスン表示フラグ */
    protected boolean displayFlg3;

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
     * @return genderKbnNm
     */
    public String getGenderKbnNm() {
        return genderKbnNm;
    }

    /**
     * @param genderKbnNm セットする genderKbnNm
     */
    public void setGenderKbnNm(String genderKbnNm) {
        this.genderKbnNm = genderKbnNm;
    }

    /**
     * @return nativeLang
     */
    public String getNativeLang() {
        return nativeLang;
    }

    /**
     * @param nativeLang セットする nativeLang
     */
    public void setNativeLang(String nativeLang) {
        this.nativeLang = nativeLang;
    }

    /**
     * @return nationality
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * @param nationality セットする nationality
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    /**
     * @return courseCd
     */
    public String getCourseCd() {
        return courseCd;
    }

    /**
     * @param courseCd セットする courseCd
     */
    public void setCourseCd(String courseCd) {
        this.courseCd = courseCd;
    }

    /**
     * @return courseNm
     */
    public String getCourseNm() {
        return courseNm;
    }

    /**
     * @param courseNm セットする courseNm
     */
    public void setCourseNm(String courseNm) {
        this.courseNm = courseNm;
    }

    /**
     * @return dayCnt
     */
    public int getDayCnt() {
        return dayCnt;
    }

    /**
     * @param dayCnt セットする dayCnt
     */
    public void setDayCnt(int dayCnt) {
        this.dayCnt = dayCnt;
    }

    /**
     * @return details0_chkn
     */
    public String[] getDetails0_chkn() {
        return details0_chkn;
    }

    /**
     * @param details0_chkn セットする details0_chkn
     */
    public void setDetails0_chkn(String[] details0_chkn) {
        this.details0_chkn = details0_chkn;
    }

    /**
     * @return details1_chkn
     */
    public String[] getDetails1_chkn() {
        return details1_chkn;
    }

    /**
     * @param details1_chkn セットする details1_chkn
     */
    public void setDetails1_chkn(String[] details1_chkn) {
        this.details1_chkn = details1_chkn;
    }

    /**
     * @return details2_chkn
     */
    public String[] getDetails2_chkn() {
        return details2_chkn;
    }

    /**
     * @param details2_chkn セットする details2_chkn
     */
    public void setDetails2_chkn(String[] details2_chkn) {
        this.details2_chkn = details2_chkn;
    }

    /**
     * @return details3_chkn
     */
    public String[] getDetails3_chkn() {
        return details3_chkn;
    }

    /**
     * @param details3_chkn セットする details3_chkn
     */
    public void setDetails3_chkn(String[] details3_chkn) {
        this.details3_chkn = details3_chkn;
    }

    /**
     * @return model
     */
    public ReservationCancellationTeacherScheduleModel getModel() {
        return model;
    }

    /**
     * @param model セットする model
     */
    public void setModel(ReservationCancellationTeacherScheduleModel model) {
        this.model = model;
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
     * @return displayFlg0
     */
    public boolean getDisplayFlg0() {
        return displayFlg0;
    }

    /**
     * @param displayFlg0 セットする displayFlg0
     */
    public void setDisplayFlg0(boolean displayFlg0) {
        this.displayFlg0 = displayFlg0;
    }

    /**
     * @return displayFlg1
     */
    public boolean getDisplayFlg1() {
        return displayFlg1;
    }

    /**
     * @param displayFlg1 セットする displayFlg1
     */
    public void setDisplayFlg1(boolean displayFlg1) {
        this.displayFlg1 = displayFlg1;
    }

    /**
     * @return displayFlg2
     */
    public boolean getDisplayFlg2() {
        return displayFlg2;
    }

    /**
     * @param displayFlg2 セットする displayFlg2
     */
    public void setDisplayFlg2(boolean displayFlg2) {
        this.displayFlg2 = displayFlg2;
    }

    /**
     * @return displayFlg3
     */
    public boolean getDisplayFlg3() {
        return displayFlg3;
    }

    /**
     * @param displayFlg3 セットする displayFlg3
     */
    public void setDisplayFlg3(boolean displayFlg3) {
        this.displayFlg3 = displayFlg3;
    }

}
