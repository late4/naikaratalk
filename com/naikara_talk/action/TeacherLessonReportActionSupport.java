package com.naikara_talk.action;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.TeacherLessonReportModel;
import com.naikara_talk.service.TeacherLessonReportLoadService;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>講師。<br>
 * <b>クラス名称　　　:</b>講師側の視点から_1-2_レッスン実績Actionスーパークラス。<br>
 * <b>クラス概要　　　:</b>講師側の視点から_1-2_レッスン実績Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/12 TECS 新規作成。
 */
public abstract class TeacherLessonReportActionSupport extends NaikaraActionSupport {

    private static final long serialVersionUID = 1L;

    /** 画面表示のタイトル */
    protected String title = "Lesson Report";

    /** Help画面名 */
    protected String helpPageId = "HelpTeacherLessonReport.html";

    /** 遷移元画面ID */
    // protected String pageId = "TeacherLessonManagement";
    protected String pageId = NaikaraTalkConstants.TEACHER_LESSON_MANAGEMENT_LOAD;

    /** ロール */
    protected String role = "";

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
     * 自己評価1、自己評価2、推奨レベルを取得する<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    public void initRadio() throws Exception {

        TeacherLessonReportLoadService service = new TeacherLessonReportLoadService();

        // 自己評価1を取得する
        this.instruction_rdll = service.getCourseSell(
                NaikaraTalkConstants.CODE_CATEGORY_SELF_EVALUATION_T,
                NaikaraTalkConstants.CODE_CATEGORY_SELF_EVALUATION);

        // 自己評価2を取得する
        this.satisfied_rdll = service.getCourseSell(
                NaikaraTalkConstants.CODE_CATEGORY_SELF_EVALUATION_T,
                NaikaraTalkConstants.CODE_CATEGORY_SELF_EVALUATION);

        // 推奨レベルを取得する
        this.level_rdll = service
                .getCourseSell(
                        NaikaraTalkConstants.CODE_CATEGORY_EVALUATION_FROM_TEAC_TO_PERS_T,
                        NaikaraTalkConstants.CODE_CATEGORY_EVALUATION_FROM_TEAC_TO_PERS);
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

        // 自己評価1
        this.model.setInstruction_rdl(this.instruction_rdl);
        // 自己評価2
        this.model.setSatisfied_rdl(this.satisfied_rdl);
        // 推奨レベル
        this.model.setLevel_rdl(this.level_rdl);
        // レッスン日
        this.model.setLessonDt_lbl(this.lessonDt_lbl);
        // レッスン時刻
        this.model.setLessonTmNm_lbl(this.lessonTmNm_lbl);
        // 実績時刻(講師)
        this.model.setTeacherStartDttm_lbl(this.teacherStartDttm_lbl);
        // 実績時刻(受講者)
        this.model.setStudentStartDttm_lbl(this.studentStartDttm_lbl);
        // コース名(英語)
        this.model.setCourseEnm_lbl(this.courseEnm_lbl);
        // コース名
        this.model.setCourseJnm_lbl(this.courseJnm_lbl);
        // 受講者名
        this.model.setStudentNickNm_lbl(this.studentNickNm_lbl);
        // 受講者から講師へのコメント
        this.model.setCOnTeacherCmt_lbl(this.cOnTeacherCmt_lbl);
        // 講師から受講者への前向きなコメント
        this.model.setPositiveComment_txa(this.positiveComment_txa);
        // スクールへのコメント
        this.model.setTeacherComment_txa(this.teacherComment_txa);
        // 受講者を引継ぎするコメント
        this.model.setInheriting_txa(this.inheriting_txa);
        // スクールから講師へのコメント
        this.model.setSchoolComment_txa(this.schoolComment_txa);
        // 予約No(隠し)
        this.model.setReservationNo_hid(this.reservationNo_hid);
        // 遷移元画面ID(隠し)
        this.model.setPageId_hid(this.pageId_hid);
        // 排他用レコードバージョン(隠し)
        String recVerNo = StringUtils.isEmpty(this.recordVerNo_hid) ? "0"
                : this.recordVerNo_hid;
        this.model.setRecordVerNo(Integer.parseInt(recVerNo));
        // レコードバージョン番号(講師)
        String recVerNoForT = StringUtils.isEmpty(this.recordVerNoForTeacher) ? "0"
                : this.recordVerNoForTeacher;
        this.model.setRecordVerNoForTeacher(Integer.parseInt(recVerNoForT));
        // レコードバージョン番号(受講者)
        String recVerNoForC = StringUtils.isEmpty(this.recordVerNoForStudent) ? "0"
                : this.recordVerNoForStudent;
        this.model.setRecordVerNoForStudent(Integer.parseInt(recVerNoForC));
        // レコードバージョン番号(スクール)
        String recVerNoForS = StringUtils.isEmpty(this.recordVerNoForSchool) ? "0"
                : this.recordVerNoForSchool;
        this.model.setRecordVerNoForSchool(Integer.parseInt(recVerNoForS));
    }

    /** メッセージ */
    protected String message;

    /** 自己評価1 */
    protected String instruction_rdl;

    /** 自己評価1リスト */
    protected Map<String, String> instruction_rdll = new LinkedHashMap<String, String>();

    /** 自己評価2 */
    protected String satisfied_rdl;

    /** 自己評価2リスト */
    protected Map<String, String> satisfied_rdll = new LinkedHashMap<String, String>();

    /** 推奨レベル */
    protected String level_rdl;

    /** 推奨レベルリスト */
    protected Map<String, String> level_rdll = new LinkedHashMap<String, String>();

    /** レッスン日 */
    protected String lessonDt_lbl;

    /** レッスン時刻 */
    protected String lessonTmNm_lbl;

    /** 実績時刻(講師) */
    protected String teacherStartDttm_lbl;

    /** 実績時刻(受講者) */
    protected String studentStartDttm_lbl;

    /** コース名(英語) */
    protected String courseEnm_lbl;

    /** コース名 */
    protected String courseJnm_lbl;

    /** 受講者名 */
    protected String studentNickNm_lbl;

    /** 受講者から講師へのコメント */
    protected String cOnTeacherCmt_lbl;

    /** 講師から受講者への前向きなコメント */
    protected String positiveComment_txa;

    /** スクールへのコメント */
    protected String teacherComment_txa;

    /** 受講者を引継ぎするコメント */
    protected String inheriting_txa;

    /** スクールから講師へのコメント */
    protected String schoolComment_txa;

    /** 予約No(隠し) */
    protected String reservationNo_hid;

    /** 遷移元画面ID(隠し) */
    protected String pageId_hid;

    /** 排他用レコードバージョン(隠し) */
    protected String recordVerNo_hid;

    /** 画面モデル */
    protected TeacherLessonReportModel model = new TeacherLessonReportModel();

    /** レコードバージョン番号(講師) */
    protected String recordVerNoForTeacher;

    /** レコードバージョン番号(受講者) */
    protected String recordVerNoForStudent;

    /** レコードバージョン番号(スクール) */
    protected String recordVerNoForSchool;

    /**
     * レコードバージョン番号(スクール)取得<br>
     * <br>
     * レコードバージョン番号(スクール)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return recordVerNoForSchool レコードバージョン番号(スクール)<br>
     * @exception なし
     */
    public String getRecordVerNoForSchool() {
        return recordVerNoForSchool;
    }

    /**
     * レコードバージョン番号(スクール)設定<br>
     * <br>
     * レコードバージョン番号(スクール)設定を行う<br>
     * <br>
     * @param recordVerNoForSchool レコードバージョン番号(スクール)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setRecordVerNoForSchool(String recordVerNoForSchool) {
        this.recordVerNoForSchool = recordVerNoForSchool;
    }

    /**
     * レコードバージョン番号(受講者)取得<br>
     * <br>
     * レコードバージョン番号(受講者)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return recordVerNoForStudent レコードバージョン番号(受講者)<br>
     * @exception なし
     */
    public String getRecordVerNoForStudent() {
        return recordVerNoForStudent;
    }

    /**
     * レコードバージョン番号(受講者)設定<br>
     * <br>
     * レコードバージョン番号(受講者)設定を行う<br>
     * <br>
     * @param recordVerNoForStudent レコードバージョン番号(受講者)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setRecordVerNoForStudent(String recordVerNoForStudent) {
        this.recordVerNoForStudent = recordVerNoForStudent;
    }

    /**
     * レコードバージョン番号(講師)取得<br>
     * <br>
     * レコードバージョン番号(講師)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return recordVerNoForTeacher レコードバージョン番号(講師)<br>
     * @exception なし
     */
    public String getRecordVerNoForTeacher() {
        return recordVerNoForTeacher;
    }

    /**
     * レコードバージョン番号(講師)設定<br>
     * <br>
     * レコードバージョン番号(講師)設定を行う<br>
     * <br>
     * @param recordVerNoForTeacher レコードバージョン番号(講師)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setRecordVerNoForTeacher(String recordVerNoForTeacher) {
        this.recordVerNoForTeacher = recordVerNoForTeacher;
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
     * 遷移元画面ID(隠し)取得<br>
     * <br>
     * 遷移元画面ID(隠し)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return pageId_hid 遷移元画面ID(隠し)<br>
     * @exception なし
     */
    public String getPageId_hid() {
        return pageId_hid;
    }

    /**
     * 遷移元画面ID(隠し)設定<br>
     * <br>
     * 遷移元画面ID(隠し)設定を行う<br>
     * <br>
     * @param pageId_hid 遷移元画面ID(隠し)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setPageId_hid(String pageId_hid) {
        this.pageId_hid = pageId_hid;
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
     * レッスン日取得<br>
     * <br>
     * レッスン日取得を行う<br>
     * <br>
     * @param なし<br>
     * @return lessonDt_lbl レッスン日<br>
     * @exception なし
     */
    public String getLessonDt_lbl() {
        return lessonDt_lbl;
    }

    /**
     * レッスン日設定<br>
     * <br>
     * レッスン日設定を行う<br>
     * <br>
     * @param lessonDt_lbl レッスン日<br>
     * @return なし<br>
     * @exception なし
     */
    public void setLessonDt_lbl(String lessonDt_lbl) {
        this.lessonDt_lbl = lessonDt_lbl;
    }

    /**
     * レッスン時刻取得<br>
     * <br>
     * レッスン時刻取得を行う<br>
     * <br>
     * @param なし<br>
     * @return lessonTmNm_lbl レッスン時刻<br>
     * @exception なし
     */
    public String getLessonTmNm_lbl() {
        return lessonTmNm_lbl;
    }

    /**
     * レッスン時刻設定<br>
     * <br>
     * レッスン時刻設定を行う<br>
     * <br>
     * @param lessonTmNm_lbl レッスン時刻<br>
     * @return なし<br>
     * @exception なし
     */
    public void setLessonTmNm_lbl(String lessonTmNm_lbl) {
        this.lessonTmNm_lbl = lessonTmNm_lbl;
    }

    /**
     * 実績時刻(講師)取得<br>
     * <br>
     * 実績時刻(講師)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return teacherStartDttm_lbl 実績時刻(講師)<br>
     * @exception なし
     */
    public String getTeacherStartDttm_lbl() {
        return teacherStartDttm_lbl;
    }

    /**
     * 実績時刻(講師)設定<br>
     * <br>
     * 実績時刻(講師)設定を行う<br>
     * <br>
     * @param teacherStartDttm_lbl 実績時刻(講師)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setTeacherStartDttm_lbl(String teacherStartDttm_lbl) {
        this.teacherStartDttm_lbl = teacherStartDttm_lbl;
    }

    /**
     * 実績時刻(受講者)取得<br>
     * <br>
     * 実績時刻(受講者)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return studentStartDttm_lbl 実績時刻(受講者)<br>
     * @exception なし
     */
    public String getStudentStartDttm_lbl() {
        return studentStartDttm_lbl;
    }

    /**
     * 実績時刻(受講者)設定<br>
     * <br>
     * 実績時刻(受講者)設定を行う<br>
     * <br>
     * @param studentStartDttm_lbl 実績時刻(受講者)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setStudentStartDttm_lbl(String studentStartDttm_lbl) {
        this.studentStartDttm_lbl = studentStartDttm_lbl;
    }

    /**
     * コース名(英語)取得<br>
     * <br>
     * コース名(英語)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return courseEnm_lbl コース名(英語)<br>
     * @exception なし
     */
    public String getCourseEnm_lbl() {
        return courseEnm_lbl;
    }

    /**
     * コース名(英語)設定<br>
     * <br>
     * コース名(英語)設定を行う<br>
     * <br>
     * @param courseEnm_lbl コース名(英語)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setCourseEnm_lbl(String courseEnm_lbl) {
        this.courseEnm_lbl = courseEnm_lbl;
    }

    /**
     * コース名取得<br>
     * <br>
     * コース名取得を行う<br>
     * <br>
     * @param なし<br>
     * @return courseJnm_lbl コース名<br>
     * @exception なし
     */
    public String getCourseJnm_lbl() {
        return courseJnm_lbl;
    }

    /**
     * コース名設定<br>
     * <br>
     * コース名設定を行う<br>
     * <br>
     * @param courseJnm_lbl コース名<br>
     * @return なし<br>
     * @exception なし
     */
    public void setCourseJnm_lbl(String courseJnm_lbl) {
        this.courseJnm_lbl = courseJnm_lbl;
    }

    /**
     * 受講者名取得<br>
     * <br>
     * 受講者名取得を行う<br>
     * <br>
     * @param なし<br>
     * @return studentNickNm_lbl 受講者名<br>
     * @exception なし
     */
    public String getStudentNickNm_lbl() {
        return studentNickNm_lbl;
    }

    /**
     * 受講者名設定<br>
     * <br>
     * 受講者名設定を行う<br>
     * <br>
     * @param studentNickNm_lbl 受講者名<br>
     * @return なし<br>
     * @exception なし
     */
    public void setStudentNickNm_lbl(String studentNickNm_lbl) {
        this.studentNickNm_lbl = studentNickNm_lbl;
    }

    /**
     * 受講者から講師へのコメント取得<br>
     * <br>
     * 受講者から講師へのコメント取得を行う<br>
     * <br>
     * @param なし<br>
     * @return cOnTeacherCmt_lbl 受講者から講師へのコメント<br>
     * @exception なし
     */
    public String getCOnTeacherCmt_lbl() {
        return cOnTeacherCmt_lbl;
    }

    /**
     * 受講者から講師へのコメント設定<br>
     * <br>
     * 受講者から講師へのコメント設定を行う<br>
     * <br>
     * @param cOnTeacherCmt_lbl 受講者から講師へのコメント<br>
     * @return なし<br>
     * @exception なし
     */
    public void setCOnTeacherCmt_lbl(String cOnTeacherCmt_lbl) {
        this.cOnTeacherCmt_lbl = cOnTeacherCmt_lbl;
    }

    /**
     * 講師から受講者への前向きなコメント取得<br>
     * <br>
     * 講師から受講者への前向きなコメント取得を行う<br>
     * <br>
     * @param なし<br>
     * @return positiveComment_txa 講師から受講者への前向きなコメント<br>
     * @exception なし
     */
    public String getPositiveComment_txa() {
        return positiveComment_txa;
    }

    /**
     * 講師から受講者への前向きなコメント設定<br>
     * <br>
     * 講師から受講者への前向きなコメント設定を行う<br>
     * <br>
     * @param positiveComment_txa 講師から受講者への前向きなコメント<br>
     * @return なし<br>
     * @exception なし
     */
    public void setPositiveComment_txa(String positiveComment_txa) {
        this.positiveComment_txa = positiveComment_txa;
    }

    /**
     * スクールへのコメント取得<br>
     * <br>
     * スクールへのコメント取得を行う<br>
     * <br>
     * @param なし<br>
     * @return teacherComment_txa スクールへのコメント<br>
     * @exception なし
     */
    public String getTeacherComment_txa() {
        return teacherComment_txa;
    }

    /**
     * スクールへのコメント設定<br>
     * <br>
     *スクールへのコメント設定を行う<br>
     * <br>
     * @param teacherComment_txa スクールへのコメント<br>
     * @return なし<br>
     * @exception なし
     */
    public void setTeacherComment_txa(String teacherComment_txa) {
        this.teacherComment_txa = teacherComment_txa;
    }

    /**
     * 受講者を引継ぎするコメント取得<br>
     * <br>
     * 受講者を引継ぎするコメント取得を行う<br>
     * <br>
     * @param なし<br>
     * @return inheriting_txa 受講者を引継ぎするコメント<br>
     * @exception なし
     */
    public String getInheriting_txa() {
        return inheriting_txa;
    }

    /**
     * 受講者を引継ぎするコメント設定<br>
     * <br>
     * 受講者を引継ぎするコメント設定を行う<br>
     * <br>
     * @param inheriting_txa 受講者を引継ぎするコメント<br>
     * @return なし<br>
     * @exception なし
     */
    public void setInheriting_txa(String inheriting_txa) {
        this.inheriting_txa = inheriting_txa;
    }

    /**
     * スクールから講師へのコメント取得<br>
     * <br>
     * スクールから講師へのコメント取得を行う<br>
     * <br>
     * @param なし<br>
     * @return schoolComment_txa スクールから講師へのコメント<br>
     * @exception なし
     */
    public String getSchoolComment_txa() {
        return schoolComment_txa;
    }

    /**
     * スクールから講師へのコメント設定<br>
     * <br>
     * スクールから講師へのコメント設定を行う<br>
     * <br>
     * @param schoolComment_txa スクールから講師へのコメント<br>
     * @return なし<br>
     * @exception なし
     */
    public void setSchoolComment_txa(String schoolComment_txa) {
        this.schoolComment_txa = schoolComment_txa;
    }

    /**
     * 予約No(隠し)取得<br>
     * <br>
     * 予約No(隠し)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return reservationNo_hid 予約No(隠し)<br>
     * @exception なし
     */
    public String getReservationNo_hid() {
        return reservationNo_hid;
    }

    /**
     * 予約No(隠し)設定<br>
     * <br>
     * 予約No(隠し)設定を行う<br>
     * <br>
     * @param reservationNo_hid 予約No(隠し)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setReservationNo_hid(String reservationNo_hid) {
        this.reservationNo_hid = reservationNo_hid;
    }

    /**
     * 自己評価1取得<br>
     * <br>
     * 自己評価1取得を行う<br>
     * <br>
     * @param なし<br>
     * @return instruction_rdl 自己評価1<br>
     * @exception なし
     */
    public String getInstruction_rdl() {
        return instruction_rdl;
    }

    /**
     * 自己評価1設定<br>
     * <br>
     * 自己評価1設定を行う<br>
     * <br>
     * @param instruction_rdl 自己評価1<br>
     * @return なし<br>
     * @exception なし
     */
    public void setInstruction_rdl(String instruction_rdl) {
        this.instruction_rdl = instruction_rdl;
    }

    /**
     * 自己評価1リスト取得<br>
     * <br>
     * 自己評価1リスト取得を行う<br>
     * <br>
     * @param なし<br>
     * @return instruction_rdll 自己評価1リスト<br>
     * @exception なし
     */
    public Map<String, String> getInstruction_rdll() {
        return instruction_rdll;
    }

    /**
     * 自己評価1リスト設定<br>
     * <br>
     * 自己評価1リスト設定を行う<br>
     * <br>
     * @param instruction_rdll 自己評価1リスト<br>
     * @return なし<br>
     * @exception なし
     */
    public void setInstruction_rdll(Map<String, String> instruction_rdll) {
        this.instruction_rdll = instruction_rdll;
    }

    /**
     * 自己評価2取得<br>
     * <br>
     * 自己評価2取得を行う<br>
     * <br>
     * @param なし<br>
     * @return satisfied_rdl 自己評価2<br>
     * @exception なし
     */
    public String getSatisfied_rdl() {
        return satisfied_rdl;
    }

    /**
     * 自己評価2設定<br>
     * <br>
     * 自己評価2設定を行う<br>
     * <br>
     * @param satisfied_rdl 自己評価2<br>
     * @return なし<br>
     * @exception なし
     */
    public void setSatisfied_rdl(String satisfied_rdl) {
        this.satisfied_rdl = satisfied_rdl;
    }

    /**
     * 自己評価2リスト取得<br>
     * <br>
     * 自己評価2リスト取得を行う<br>
     * <br>
     * @param なし<br>
     * @return satisfied_rdll 自己評価2リスト<br>
     * @exception なし
     */
    public Map<String, String> getSatisfied_rdll() {
        return satisfied_rdll;
    }

    /**
     * 自己評価2リスト設定<br>
     * <br>
     * 自己評価2リスト設定を行う<br>
     * <br>
     * @param satisfied_rdll 自己評価2リスト<br>
     * @return なし<br>
     * @exception なし
     */
    public void setSatisfied_rdll(Map<String, String> satisfied_rdll) {
        this.satisfied_rdll = satisfied_rdll;
    }

    /**
     * 推奨レベル取得<br>
     * <br>
     * 推奨レベル取得を行う<br>
     * <br>
     * @param なし<br>
     * @return level_rdl 推奨レベル<br>
     * @exception なし
     */
    public String getLevel_rdl() {
        return level_rdl;
    }

    /**
     * 推奨レベル設定<br>
     * <br>
     * 推奨レベル設定を行う<br>
     * <br>
     * @param level_rdl 推奨レベル<br>
     * @return なし<br>
     * @exception なし
     */
    public void setLevel_rdl(String level_rdl) {
        this.level_rdl = level_rdl;
    }

    /**
     * 推奨レベルリスト取得<br>
     * <br>
     * 推奨レベルリスト取得を行う<br>
     * <br>
     * @param なし<br>
     * @return level_rdll 推奨レベルリスト<br>
     * @exception なし
     */
    public Map<String, String> getLevel_rdll() {
        return level_rdll;
    }

    /**
     * 推奨レベルリスト設定<br>
     * <br>
     * 推奨レベルリスト設定を行う<br>
     * <br>
     * @param level_rdll 推奨レベルリスト<br>
     * @return なし<br>
     * @exception なし
     */
    public void setLevel_rdll(Map<String, String> level_rdll) {
        this.level_rdll = level_rdll;
    }

    /**
     * 排他用レコードバージョン(隠し)取得<br>
     * <br>
     * 排他用レコードバージョン(隠し)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return recordVerNo_hid 排他用レコードバージョン(隠し)<br>
     * @exception なし
     */
    public String getRecordVerNo_hid() {
        return recordVerNo_hid;
    }

    /**
     * 排他用レコードバージョン(隠し)設定<br>
     * <br>
     * 排他用レコードバージョン(隠し)設定を行う<br>
     * <br>
     * @param recordVerNo_hid 排他用レコードバージョン(隠し)<br>
     * @return なし<br>
     * @exception なし
     */
    public void setRecordVerNo_hid(String recordVerNo_hid) {
        this.recordVerNo_hid = recordVerNo_hid;
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
    public TeacherLessonReportModel getModel() {
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
    public void setModel(TeacherLessonReportModel model) {
        this.model = model;
    }
}