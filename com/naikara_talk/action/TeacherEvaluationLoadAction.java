package com.naikara_talk.action;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.TeacherEvaluationLoadService;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_受講処理<br>
 * <b>クラス名称       :</b>講師評価画面<br>
 * <b>クラス概要       :</b>講師評価画面初期処理Action<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/10 TECS 新規作成
 */
public class TeacherEvaluationLoadAction extends TeacherEvaluationActionSupport {

    private static final long serialVersionUID = 1L;

    /** 画面遷移用1 */
    private static final String ERRORPAGE_STUDENTMYPAGE = "error1";

    /** 画面遷移用2 */
    private static final String ERRORPAGE_STUDENTLESSONHISTORYSTUDENTUSESLIST = "error2";

    /**
     * 画面の初期表示。<br>
     * <br>
     * 画面の初期表示。<br>
     * <br>
     * @param なし<br>
     * @return String SUCCESS <br>
     * @exception NaiException
     */
    @SuppressWarnings("unchecked")
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        setupModel();
        // 受講者からの講師評価の初期取得。
        try {
            initRadio();
        } catch (Exception e) {
            throw new NaiException(e);
        }

        // 表示データを取得する
        TeacherEvaluationLoadService service = new TeacherEvaluationLoadService();

        // 前画面の情報
        this.model.setReservationNo(this.reservationNo);

        try {
            // データが存在しない場合
            if (NaikaraTalkConstants.RETURN_CD_DATA_NO == service.getExist(model)) {
                this.message = getMessage("EC0020", new String[] {});

                ArrayList<String> actionList = null;
                if (this.session.containsKey("actionList")) {
                    // sessionからactionlistを取得する
                    actionList = (ArrayList<String>) this.session.get("actionList");
                    if (actionList != null && actionList.size() > 0) {
                        // 前画面のactionを取得する
                        String action = actionList.get(actionList.size() - 1);
                        if (StringUtils.equals(NaikaraTalkConstants.STUDENT_MY_PAGE_LOAD, action)) {
                            removeLatestActionList();
                            return ERRORPAGE_STUDENTMYPAGE;
                        }
                    }
                }

                removeLatestActionList();
                // 前画面(一覧)へ戻り、エラーメッセージを表示
                return ERRORPAGE_STUDENTLESSONHISTORYSTUDENTUSESLIST;
            } else {
                // データが存在する場合

                // 表示データの取得処理
                this.load();
            }
        } catch (Exception e) {
            throw new NaiException(e);
        }
        if (!StringUtils.isEmpty(this.message)) {
            this.addActionMessage(this.message);
        } else {
            ArrayList<String> actionList = null;
            if (this.session.containsKey("actionList")) {
                // sessionからactionlistを取得する
                actionList = (ArrayList<String>) this.session.get("actionList");
                if (actionList != null && actionList.size() > 0) {
                    // 前画面のactionを取得する
                    String action = actionList.get(actionList.size() - 1);
                    if (StringUtils.equals(NaikaraTalkConstants.STUDENT_MY_PAGE_LOAD, action)) {
                        this.addActionMessage(NaikaraTalkConstants.LESSON_COMMENT_MESSAGE);
                    }
                }
            }
        }
        return SUCCESS;

    }

    /**
     * 初期処理、表示データの取得。<br>
     * <br>
     * 初期処理、表示データの取得。<br>
     * <br>
     * @param なし<br>
     * @return なし <br>
     * @exception Exception
     */
    private void load() throws Exception {

        // 表示データを取得する
        TeacherEvaluationLoadService service = new TeacherEvaluationLoadService();

        // 前画面の情報
        this.model.setReservationNo(this.reservationNo);

        this.model = service.select(this.model);

        /** 受講者ID */
        this.studentId_txt = model.getStudentId();

        /** 受講者ニックネーム */
        this.studentNickNm_txt = model.getStudentNickNm();

        /** レッスン日 */
        this.lessonDt_txt = NaikaraStringUtil.converToYYYY_MM_DD(model.getLessonDt());

        /** レッスン時刻名 */
        this.lessonTmNm_txt = model.getLessonTmNm();

        /** コース名 */
        this.courseJnm_txt = model.getCourseJnm();

        /** 講師ID*/
        this.teacherId_txt = model.getTeacherId();

        /** 講師名(ニックネーム)*/
        this.teacherNickNm_txt = model.getTeacherNickNm();

        /** 母国語*/
        this.nativeLangNm_txt = model.getNativeLangNm();

        /** 評価 */
        this.cevaluationKbn_rdl = model.getcEvaluationKbn();

        /** レッスンに対する講師への意見コメント */
        this.conTeacherCmt_txa = model.getcOnTeacherCmt();

        /** レコードバージョン番号 */
        this.recordVerNo = String.valueOf(model.getRecordVerNo());

        /** レッスンコメントテーブル レコードバージョン番号 */
        this.recordVerNoLCT = String.valueOf(model.getRecordVerNoLCT());

        /** 講師 レコードバージョン番号 */
        this.recordVerNoT = String.valueOf(model.getRecordVerNoT());

        /** 予約No*/
        this.reservationNo = model.getReservationNo();

    }
}
