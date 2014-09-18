package com.naikara_talk.model;


/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>講師。<br>
 * <b>クラス名称　　　:</b>講師側の視点から_1-2_レッスン実績Modelクラス。<br>
 * <b>クラス概要　　　:</b>講師側の視点から_1-2_レッスン実績Model。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/12 TECS 新規作成。
 */
public class TeacherLessonReportModel implements Model {

    private static final long serialVersionUID = 1L;

    /** 正常 */
    public static final int PROCESS_NORMAL = 0;

    /** レコードバージョン番号 */
    protected int recordVerNo;

    /** 自己評価1 */
    private String instruction_rdl;

    /** 自己評価2 */
    private String satisfied_rdl;

    /** 推奨レベル */
    private String level_rdl;

    /** レッスン日 */
    private String lessonDt_lbl;

    /** レッスン時刻 */
    private String lessonTmNm_lbl;

    /** 実績時刻(講師) */
    private String teacherStartDttm_lbl;

    /** 実績時刻(受講者) */
    private String studentStartDttm_lbl;

    /** コース名(英語) */
    private String courseEnm_lbl;

    /** コース名 */
    private String courseJnm_lbl;

    /** 受講者名 */
    private String studentNickNm_lbl;

    /** 受講者から講師へのコメント */
    private String cOnTeacherCmt_lbl;

    /** 講師から受講者への前向きなコメント */
    private String positiveComment_txa;

    /** スクールへのコメント */
    private String teacherComment_txa;

    /** 受講者を引継ぎするコメント */
    private String inheriting_txa;

    /** スクールから講師へのコメント */
    private String schoolComment_txa;

    /** 予約No(隠し) */
    private String reservationNo_hid;

    /** 遷移元画面ID(隠し) */
    private String pageId_hid;

    /** コメント入力者区分 */
    private String cmtInputsKbn;

    /** モード */
    private String mode;

    /** レコードバージョン番号(講師) */
    private int recordVerNoForTeacher;

    /** レコードバージョン番号(受講者) */
    private int recordVerNoForStudent;

    /** レコードバージョン番号(スクール) */
    private int recordVerNoForSchool;

    /**
     * レコードバージョン番号(スクール)取得<br>
     * <br>
     * レコードバージョン番号(スクール)取得を行う<br>
     * <br>
     * @param なし<br>
     * @return recordVerNoForSchool レコードバージョン番号(スクール)<br>
     * @exception なし
     */
    public int getRecordVerNoForSchool() {
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
    public void setRecordVerNoForSchool(int recordVerNoForSchool) {
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
    public int getRecordVerNoForStudent() {
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
    public void setRecordVerNoForStudent(int recordVerNoForStudent) {
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
    public int getRecordVerNoForTeacher() {
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
    public void setRecordVerNoForTeacher(int recordVerNoForTeacher) {
        this.recordVerNoForTeacher = recordVerNoForTeacher;
    }

    /**
     * モード取得<br>
     * <br>
     * モード取得を行う<br>
     * <br>
     * @param なし<br>
     * @return mode モード<br>
     * @exception なし
     */
    public String getMode() {
        return mode;
    }

    /**
     * モード設定<br>
     * <br>
     * モード設定を行う<br>
     * <br>
     * @param mode モード<br>
     * @return なし<br>
     * @exception なし
     */
    public void setMode(String mode) {
        this.mode = mode;
    }

    /**
     * コメント入力者区分取得<br>
     * <br>
     * コメント入力者区分を戻り値で返却する<br>
     * <br>
     * @return cmtInputsKbn
     */
    public String getCmtInputsKbn() {
        return cmtInputsKbn;
    }

    /**
     * コメント入力者区分設定<br>
     * <br>
     * コメント入力者区分を引数で設定する<br>
     * <br>
     * @param cmtInputsKbn
     */
    public void setCmtInputsKbn(String cmtInputsKbn) {
        this.cmtInputsKbn = cmtInputsKbn;
    }

    /**
     * 排他用レコードバージョン取得<br>
     * <br>
     * 排他用レコードバージョン取得を行う<br>
     * <br>
     * @param なし<br>
     * @return recordVerNo 排他用レコードバージョン<br>
     * @exception なし
     */
    public int getRecordVerNo() {
        return recordVerNo;
    }

    /**
     * 排他用レコードバージョン設定<br>
     * <br>
     * 排他用レコードバージョン設定を行う<br>
     * <br>
     * @param recordVerNo 排他用レコードバージョン<br>
     * @return なし<br>
     * @exception なし
     */
    public void setRecordVerNo(int recordVerNo) {
        this.recordVerNo = recordVerNo;
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
}