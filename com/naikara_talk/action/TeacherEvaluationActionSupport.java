package com.naikara_talk.action;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.TeacherEvaluationModel;
import com.naikara_talk.service.TeacherEvaluationLoadService;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_受講処理<br>
 * <b>クラス名称       :</b>講師評価画面<br>
 * <b>クラス概要       :</b>講師評価画面共通Action<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/10 TECS 新規作成
 */
public abstract class TeacherEvaluationActionSupport extends NaikaraActionSupport {

    private static final long serialVersionUID = 1L;

    // 画面表示のタイトル
    protected String title = "講師評価画面";

    // Help画面名
    protected String helpPageId = "HelpTeacherEvaluation.html";

    /**
     * 受講者からの講師評価の再取得<br>
     * <br>
     * チェックエラーの場合、受講者からの講師評価の再取得。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception なし
     */
    @Override
    public void validate() {

        // チェックエラーの場合、受講者からの講師評価の再取得。
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
     * 受講者からの講師評価を取得する。<br>
     * <br>
     * 受講者からの講師評価を取得する。<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    public void initRadio() throws Exception {

        TeacherEvaluationLoadService service = new TeacherEvaluationLoadService();
        // 受講者からの講師評価を取得する
        this.cevaluationKbn_rdll = service
                .selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_EVALUATION_FROM_PERS_TO_TEAC);
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

        // 受講者ID
        this.model.setStudentId(this.studentId_txt);
        // 受講者ニックネーム
        this.model.setStudentNickNm(this.studentNickNm_txt);
        // レッスン日
        this.model.setLessonDt(this.lessonDt_txt);
        // レッスン時刻名
        this.model.setLessonTmNm(this.lessonTmNm_txt);
        // コース名
        this.model.setCourseJnm(this.courseJnm_txt);
        // 講師ID
        this.model.setTeacherId(this.teacherId_txt);
        // 講師名(ニックネーム)
        this.model.setTeacherNickNm(this.teacherNickNm_txt);
        // 母国語
        this.model.setNativeLangNm(this.nativeLangNm_txt);
        // 評価
        this.model.setcEvaluationKbn(this.cevaluationKbn_rdl);
        // レッスンに対する講師への意見コメント
        this.model.setcOnTeacherCmt(this.conTeacherCmt_txa);
        // ブックマーク
        this.model.setBookmark(this.bookmark_chk);
        // レコードバージョン番号
        String recVerNo = StringUtils.isEmpty(this.recordVerNo) ? "0" : this.recordVerNo;
        this.model.setRecordVerNo(Integer.parseInt(recVerNo));
        // レッスンコメントテーブル レコードバージョン番号
        String recVerNoLCT = StringUtils.isEmpty(this.recordVerNoLCT) ? "0" : this.recordVerNoLCT;
        this.model.setRecordVerNoLCT(Integer.parseInt(recVerNoLCT));
        // 講師 レコードバージョン番号
        String recVerNoT = StringUtils.isEmpty(this.recordVerNoT) ? "0" : this.recordVerNoT;
        this.model.setRecordVerNoT(Integer.parseInt(recVerNoT));
        // 予約No
        this.model.setReservationNo(this.reservationNo);
        // コメント入力者区分
        this.model.setCmtInputsKbn(SessionUser.ROLE_STUDENT);

    }

    /** メッセージ */
    protected String message;

    /** 説明コメント */
    protected String comment;

    /** 受講者ID */
    protected String studentId_txt;

    /** 受講者ニックネーム */
    protected String studentNickNm_txt;

    /** レッスン日 */
    protected String lessonDt_txt;

    /** レッスン時刻名 */
    protected String lessonTmNm_txt;

    /** コース名 */
    protected String courseJnm_txt;

    /** 講師ID*/
    protected String teacherId_txt;

    /** 講師名(ニックネーム)*/
    protected String teacherNickNm_txt;

    /** 母国語*/
    protected String nativeLangNm_txt;

    /** 評価 */
    protected String cevaluationKbn_rdl;
    protected Map<String, String> cevaluationKbn_rdll = new LinkedHashMap<String, String>();

    /** レッスンに対する講師への意見コメント*/
    protected String conTeacherCmt_txa;

    /** ブックマーク */
    protected String bookmark_chk;

    /** 排他用レコードバージョン */
    protected String recordVerNo;

    /** レッスンコメントテーブル レコードバージョン番号 */
    protected String recordVerNoLCT;

    /** 講師 レコードバージョン番号 */
    protected String recordVerNoT;

    /** 予約No */
    protected String reservationNo;

    /** コメント入力者区分 */
    protected String cmtInputsKbn;

    /** 処理結果 */
    protected TeacherEvaluationModel model = new TeacherEvaluationModel();

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
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     *            セットする message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment
     *            セットする comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return studentId_txt
     */
    public String getStudentId_txt() {
        return studentId_txt;
    }

    /**
     * @param studentId_txt
     *            セットする studentId_txt
     */
    public void setStudentId_txt(String studentId_txt) {
        this.studentId_txt = studentId_txt;
    }

    /**
     * @return studentNickNm_txt
     */
    public String getStudentNickNm_txt() {
        return studentNickNm_txt;
    }

    /**
     * @param studentNickNm_txt
     *            セットする studentNickNm_txt
     */
    public void setStudentNickNm_txt(String studentNickNm_txt) {
        this.studentNickNm_txt = studentNickNm_txt;
    }

    /**
     * @return lessonDt_txt
     */
    public String getLessonDt_txt() {
        return lessonDt_txt;
    }

    /**
     * @param lessonDt_txt
     *            セットする lessonDt_txt
     */
    public void setLessonDt_txt(String lessonDt_txt) {
        this.lessonDt_txt = lessonDt_txt;
    }

    /**
     * @return lessonTmNm_txt
     */
    public String getLessonTmNm_txt() {
        return lessonTmNm_txt;
    }

    /**
     * @param lessonTmNm_txt
     *            セットする lessonTmNm_txt
     */
    public void setLessonTmNm_txt(String lessonTmNm_txt) {
        this.lessonTmNm_txt = lessonTmNm_txt;
    }

    /**
     * @return courseJnm_txt
     */
    public String getCourseJnm_txt() {
        return courseJnm_txt;
    }

    /**
     * @param courseJnm_txt
     *            セットする courseJnm_txt
     */
    public void setCourseJnm_txt(String courseJnm_txt) {
        this.courseJnm_txt = courseJnm_txt;
    }

    /**
     * @return teacherId_txt
     */
    public String getTeacherId_txt() {
        return teacherId_txt;
    }

    /**
     * @param teacherId_txt
     *            セットする teacherId_txt
     */
    public void setTeacherId_txt(String teacherId_txt) {
        this.teacherId_txt = teacherId_txt;
    }

    /**
     * @return teacherNickNm_txt
     */
    public String getTeacherNickNm_txt() {
        return teacherNickNm_txt;
    }

    /**
     * @param teacherNickNm_txt
     *            セットする teacherNickNm_txt
     */
    public void setTeacherNickNm_txt(String teacherNickNm_txt) {
        this.teacherNickNm_txt = teacherNickNm_txt;
    }

    /**
     * @return nativeLangNm_txt
     */
    public String getNativeLangNm_txt() {
        return nativeLangNm_txt;
    }

    /**
     * @param nativeLangNm_txt
     *            セットする nativeLangNm_txt
     */
    public void setNativeLangNm_txt(String nativeLangNm_txt) {
        this.nativeLangNm_txt = nativeLangNm_txt;
    }

    /**
     * @return cevaluationKbn_rdl
     */
    public String getCevaluationKbn_rdl() {
        return cevaluationKbn_rdl;
    }

    /**
     * @param cevaluationKbn_rdl
     *            セットする cevaluationKbn_rdl
     */
    public void setCevaluationKbn_rdl(String cevaluationKbn_rdl) {
        this.cevaluationKbn_rdl = cevaluationKbn_rdl;
    }

    /**
     * @return cevaluationKbn_rdll
     */
    public Map<String, String> getCevaluationKbn_rdll() {
        return cevaluationKbn_rdll;
    }

    /**
     * @param cevaluationKbn_rdll
     *            セットする cevaluationKbn_rdll
     */
    public void setCevaluationKbn_rdll(Map<String, String> cevaluationKbn_rdll) {
        this.cevaluationKbn_rdll = cevaluationKbn_rdll;
    }

    /**
     * @return conTeacherCmt_txa
     */
    public String getConTeacherCmt_txa() {
        return conTeacherCmt_txa;
    }

    /**
     * @param conTeacherCmt_txa
     *            セットする conTeacherCmt_txa
     */
    public void setConTeacherCmt_txa(String conTeacherCmt_txa) {
        this.conTeacherCmt_txa = conTeacherCmt_txa;
    }

    /**
     * @return bookmark_chk
     */
    public String getBookmark_chk() {
        return bookmark_chk;
    }

    /**
     * @param bookmark_chk
     *            セットする bookmark_chk
     */
    public void setBookmark_chk(String bookmark_chk) {
        this.bookmark_chk = bookmark_chk;
    }

    /**
     * @return recordVerNo
     */
    public String getRecordVerNo() {
        return recordVerNo;
    }

    /**
     * @param recordVerNo
     *            セットする recordVerNo
     */
    public void setRecordVerNo(String recordVerNo) {
        this.recordVerNo = recordVerNo;
    }

    /**
     * @return recordVerNoLCT
     */
    public String getRecordVerNoLCT() {
        return recordVerNoLCT;
    }

    /**
     * @param recordVerNoLCT セットする recordVerNoLCT
     */
    public void setRecordVerNoLCT(String recordVerNoLCT) {
        this.recordVerNoLCT = recordVerNoLCT;
    }

    /**
     * @return recordVerNoT
     */
    public String getRecordVerNoT() {
        return recordVerNoT;
    }

    /**
     * @param recordVerNoT セットする recordVerNoT
     */
    public void setRecordVerNoT(String recordVerNoT) {
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

    /**
     * @return model
     */
    public TeacherEvaluationModel getModel() {
        return model;
    }

    /**
     * @param model
     *            セットする model
     */
    public void setModel(TeacherEvaluationModel model) {
        this.model = model;
    }

}
