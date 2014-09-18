package com.naikara_talk.action;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.TeacherLessonReportLoadService;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>講師。<br>
 * <b>クラス名称　　　:</b>講師側の視点から_1-2_レッスン実績初期処理Actionクラス。<br>
 * <b>クラス概要　　　:</b>講師側の視点から_1-2_レッスン実績初期処理Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/12 TECS 新規作成。
 */
public class TeacherLessonReportLoadAction extends TeacherLessonReportActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 初期処理<br>
     * <br>
     * 画面項目の初期処理を行う<br>
     * <br>
     * @param なし<br>
     * @return SUCCESS 画面遷移フラグ<br>
     * @exception NaiException
     */
    @SkipValidation
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // ロールを取得
        if ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString()) != null) {

            role = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getRole();
        }

        // 画面のパラメータセット
        setupModel();

        // サービスの初期化
        TeacherLessonReportLoadService service = new TeacherLessonReportLoadService();

        try {

            initRadio();

        } catch (Exception e) {

            throw new NaiException(e);

        }

        try {

            int cnt = service.isExists(this.model);
            // データが存在しない場合
            if (cnt == NaikaraTalkConstants.RETURN_CD_DATA_NO) {

                this.message = getMessage("ET0020", new String[] {});

                removeLatestActionList();

                return ERROR;

            } else {

                // データが存在する場合
                // 表示データの取得処理
                this.load();

                return SUCCESS;

            }
        } catch (Exception e) {

            throw new NaiException(e);

        }
    }

    /**
     * データ取得<br>
     * <br>
     * 初期処理、表示データの取得<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    private void load() throws Exception {

        // サービスの初期化
        TeacherLessonReportLoadService service = new TeacherLessonReportLoadService();

        // 予約No
        this.model.setReservationNo_hid(this.reservationNo_hid);
        // 遷移元画面ID
        this.model.setPageId_hid(this.pageId_hid);

        // データを取得
        this.model = service.select(this.model);

        /** 自己評価1 */
        this.instruction_rdl = this.model.getInstruction_rdl();
        /** 自己評価2 */
        this.satisfied_rdl = this.model.getSatisfied_rdl();
        /** 推奨レベル */
        this.level_rdl = this.model.getLevel_rdl();
        /** レッスン日 */
        this.lessonDt_lbl = NaikaraStringUtil.converToYYYY_MM_DD(this.model.getLessonDt_lbl());
        /** レッスン時刻 */
        this.lessonTmNm_lbl = this.model.getLessonTmNm_lbl();
        /** 実績時刻(講師) */
        this.teacherStartDttm_lbl = this.model.getTeacherStartDttm_lbl();
        /** 実績時刻(受講者) */
        this.studentStartDttm_lbl = this.model.getStudentStartDttm_lbl();
        /** コース名(英語) */
        this.courseEnm_lbl = this.model.getCourseEnm_lbl();
        /** コース名 */
        this.courseJnm_lbl = this.model.getCourseJnm_lbl();
        /** 受講者名 */
        this.studentNickNm_lbl = this.model.getStudentNickNm_lbl();
        /** 受講者から講師へのコメント */
        this.cOnTeacherCmt_lbl = this.model.getCOnTeacherCmt_lbl();
        /** 講師から受講者への前向きなコメント */
        this.positiveComment_txa = this.model.getPositiveComment_txa();
        /** スクールへのコメント */
        this.teacherComment_txa = this.model.getTeacherComment_txa();
        /** 受講者を引継ぎするコメント */
        this.inheriting_txa = this.model.getInheriting_txa();
        /** スクールから講師へのコメント */
        this.schoolComment_txa = this.model.getSchoolComment_txa();
        /** 予約No */
        this.reservationNo_hid = this.model.getReservationNo_hid();
        /** 遷移元画面ID */
        this.pageId_hid = this.model.getPageId_hid();
        /** レコードバージョン番号 */
        this.recordVerNo_hid = String.valueOf(this.model.getRecordVerNo());
        /** レコードバージョン番号(講師) */
        this.recordVerNoForTeacher = String.valueOf(this.model.getRecordVerNoForTeacher());
        /** レコードバージョン番号(受講者) */
        this.recordVerNoForStudent = String.valueOf(this.model.getRecordVerNoForStudent());
        /** レコードバージョン番号(スクール) */
        this.recordVerNoForSchool = String.valueOf(this.model.getRecordVerNoForSchool());
    }
}