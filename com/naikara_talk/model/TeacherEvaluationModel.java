package com.naikara_talk.model;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_受講処理<br>
 * <b>クラス名称       :</b>講師評価画面<br>
 * <b>クラス概要       :</b>講師評価画面Model<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/10 TECS 新規作成
 */
public class TeacherEvaluationModel implements Model {

    private static final long serialVersionUID = 1L;

    /** 正常 */
    public static final int PROCESS_NORMAL = 0;

    /** 受講者ID */
    private String studentId;

    /** 受講者ニックネーム */
    private String studentNickNm;

    /** レッスン日 */
    private String lessonDt;

    /** レッスン時刻名 */
    private String lessonTmNm;

    /** コース名 */
    private String courseJnm;

    /** 講師ID*/
    private String teacherId;

    /** 講師名(ニックネーム)*/
    private String teacherNickNm;

    /** 母国語*/
    private String nativeLangNm;

    /** 評価 */
    private String cEvaluationKbn;

    /** レッスンに対する講師への意見コメント*/
    private String cOnTeacherCmt;

    /** ブックマーク */
    private String bookmark;

    /** 排他用レコードバージョン */
    private int recordVerNo;

    /** レッスンコメントテーブル レコードバージョン番号 */
    private int recordVerNoLCT;

    /** 講師 レコードバージョン番号 */
    private int recordVerNoT;

    /** 予約No */
    private String reservationNo;

    /** コメント入力者区分 */
    protected String cmtInputsKbn;

    /**
     * @return studentId
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * @param studentId
     *            セットする studentId
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * @return studentNickNm
     */
    public String getStudentNickNm() {
        return studentNickNm;
    }

    /**
     * @param studentNickNm
     *            セットする studentNickNm
     */
    public void setStudentNickNm(String studentNickNm) {
        this.studentNickNm = studentNickNm;
    }

    /**
     * @return lessonDt
     */
    public String getLessonDt() {
        return lessonDt;
    }

    /**
     * @param lessonDt
     *            セットする lessonDt
     */
    public void setLessonDt(String lessonDt) {
        this.lessonDt = lessonDt;
    }

    /**
     * @return lessonTmNm
     */
    public String getLessonTmNm() {
        return lessonTmNm;
    }

    /**
     * @param lessonTmNm
     *            セットする lessonTmNm
     */
    public void setLessonTmNm(String lessonTmNm) {
        this.lessonTmNm = lessonTmNm;
    }

    /**
     * @return courseJnm
     */
    public String getCourseJnm() {
        return courseJnm;
    }

    /**
     * @param courseJnm
     *            セットする courseJnm
     */
    public void setCourseJnm(String courseJnm) {
        this.courseJnm = courseJnm;
    }

    /**
     * @return teacherId
     */
    public String getTeacherId() {
        return teacherId;
    }

    /**
     * @param teacherId
     *            セットする teacherId
     */
    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * @return teacherNickNm
     */
    public String getTeacherNickNm() {
        return teacherNickNm;
    }

    /**
     * @param teacherNickNm
     *            セットする teacherNickNm
     */
    public void setTeacherNickNm(String teacherNickNm) {
        this.teacherNickNm = teacherNickNm;
    }

    /**
     * @return nativeLangNm
     */
    public String getNativeLangNm() {
        return nativeLangNm;
    }

    /**
     * @param nativeLangNm
     *            セットする nativeLangNm
     */
    public void setNativeLangNm(String nativeLangNm) {
        this.nativeLangNm = nativeLangNm;
    }

    /**
     * @return cEvaluationKbn
     */
    public String getcEvaluationKbn() {
        return cEvaluationKbn;
    }

    /**
     * @param cEvaluationKbn
     *            セットする cEvaluationKbn
     */
    public void setcEvaluationKbn(String cEvaluationKbn) {
        this.cEvaluationKbn = cEvaluationKbn;
    }

    /**
     * @return cOnTeacherCmt
     */
    public String getcOnTeacherCmt() {
        return cOnTeacherCmt;
    }

    /**
     * @param cOnTeacherCmt
     *            セットする cOnTeacherCmt
     */
    public void setcOnTeacherCmt(String cOnTeacherCmt) {
        this.cOnTeacherCmt = cOnTeacherCmt;
    }

    /**
     * @return bookmark
     */
    public String getBookmark() {
        return bookmark;
    }

    /**
     * @param bookmark
     *            セットする bookmark
     */
    public void setBookmark(String bookmark) {
        this.bookmark = bookmark;
    }

    /**
     * @return recordVerNo
     */
    public int getRecordVerNo() {
        return recordVerNo;
    }

    /**
     * @param recordVerNo
     *            セットする recordVerNo
     */
    public void setRecordVerNo(int recordVerNo) {
        this.recordVerNo = recordVerNo;
    }

    /**
     * @return recordVerNoLCT
     */
    public int getRecordVerNoLCT() {
        return recordVerNoLCT;
    }

    /**
     * @param recordVerNoLCT セットする recordVerNoLCT
     */
    public void setRecordVerNoLCT(int recordVerNoLCT) {
        this.recordVerNoLCT = recordVerNoLCT;
    }

    /**
     * @return recordVerNoT
     */
    public int getRecordVerNoT() {
        return recordVerNoT;
    }

    /**
     * @param recordVerNoT セットする recordVerNoT
     */
    public void setRecordVerNoT(int recordVerNoT) {
        this.recordVerNoT = recordVerNoT;
    }

    /**
     * @return reservationNo
     */
    public String getReservationNo() {
        return reservationNo;
    }

    /**
     * @param reservationNo
     *            セットする reservationNo
     */
    public void setReservationNo(String reservationNo) {
        this.reservationNo = reservationNo;
    }

    /**
     * @return cmtInputsKbn
     */
    public String getCmtInputsKbn() {
        return cmtInputsKbn;
    }

    /**
     * @param cmtInputsKbn
     *            セットする cmtInputsKbn
     */
    public void setCmtInputsKbn(String cmtInputsKbn) {
        this.cmtInputsKbn = cmtInputsKbn;
    }

}