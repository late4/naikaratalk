package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.TeacherLessonManagementSearchService;
import com.naikara_talk.sessiondata.SessionTeacherLesson;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>講師。<br>
 * <b>クラス名称　　　:</b>講師側の視点から_1-1_レッスン実績検索Actionクラス。<br>
 * <b>クラス概要　　　:</b>講師側の視点から_1-1_レッスン実績検索Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/12 TECS 新規作成。
 */
public class TeacherLessonManagementSearchAction extends TeacherLessonManagementActionSupport {

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
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // ロールを取得
        if ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString()) != null) {

            role = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getRole();
        }

        // 画面のパラメータセット
        this.periodDtFr_txt = NaikaraStringUtil.converToYYYY_MM_DD(this.periodDtFr_txt);
        this.periodDtTo_txt = NaikaraStringUtil.converToYYYY_MM_DD(this.periodDtTo_txt);
        setupModel();

        // サービスの初期化
        TeacherLessonManagementSearchService service = new TeacherLessonManagementSearchService();

        // 戻る判定Onフラグ
        boolean returnOnFlg = false;

        if ((SessionTeacherLesson) SessionDataUtil.getSessionData(SessionTeacherLesson.class.toString()) != null) {

            returnOnFlg = ((SessionTeacherLesson) SessionDataUtil.getSessionData(SessionTeacherLesson.class.toString()))
                    .getReturnOnFlg();

            // チェックエラー場合、検索判断フラグ
            this.hasSearchFlg = ((SessionTeacherLesson) SessionDataUtil.getSessionData(SessionTeacherLesson.class
                    .toString())).getHasSearchFlg();
        }

        if (!returnOnFlg) {

            int chkResult;

            try {

                // 検索前チェックを行う
                chkResult = service.checkPreSelect(this.model);

                switch (chkResult) {

                case TeacherLessonManagementSearchService.PERIOD_DATE_FROM_CHECK:

                    this.addActionMessage(getMessage("ET0001", new String[] { "\"From\" Period", "抽出日：開始日" }));

                    return SUCCESS;

                case TeacherLessonManagementSearchService.PERIOD_DATE_TO_CHECK:

                    this.addActionMessage(getMessage("ET0001", new String[] { "\"To\" Period", "抽出日：終了日" }));

                    return SUCCESS;

                case TeacherLessonManagementSearchService.ERR_ZERO_DATA:

                    this.addActionMessage(getMessage("ET0020", new String[] {}));

                    this.size = 0;

                    return SUCCESS;

                case TeacherLessonManagementSearchService.ERR_MAXOVER_DATA:

                    this.addActionMessage(getMessage("ET0023", new String[] { "101", "101" }));

                    this.size = 0;

                    return SUCCESS;

                }
            } catch (Exception e) {

                throw new NaiException(e);

            }
        }

        try {

            // ヘッダ部の戻るボタン押下の場合は値の入れ替え処理
            this.sessionTeacherLessonManagementToModelBefore();

            if (!returnOnFlg || StringUtils.equals(NaikaraTalkConstants.TRUE, this.hasSearchFlg)) {

                // 検索結果
                this.model.setResultList(service.selectList(this.model));

                if (NaikaraTalkConstants.RETURN_CD_DATA_NO == this.model.getResultList().get(0).getReturnCode()) {
                    this.model.getResultList().clear();
                }

                // チェックエラー場合、検索判断フラグ
                this.hasSearchFlg = Boolean.toString(Boolean.TRUE);

                // 件数
                this.size = 1;
            }

        } catch (Exception e) {

            throw new NaiException(e);

        }

        // 一覧から選択された明細データ(jsp)
        this.select_rdl = NaikaraTalkConstants.BRANK;

        try {

            // ヘッダ部の戻るボタン押下の場合は値の入れ替え処理
            this.sessionTeacherLessonManagementToModelAfter();

            // 戻る用に必要な情報を格納
            this.modelToSessionTeacherLessonManagement();

        } catch (Exception e) {

            throw new NaiException(e);

        }
        // メッセージの設定
        if (!StringUtils.isEmpty(this.message)) {
            this.addActionMessage(this.message);
        }

        return SUCCESS;
    }

    /**
     * SessionTeacherLessonManagement値をセット<br>
     * <br>
     * SessionTeacherLessonManagement値をModelにセット<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    private void sessionTeacherLessonManagementToModelBefore() throws Exception {

        if ((SessionTeacherLesson) SessionDataUtil.getSessionData(SessionTeacherLesson.class.toString()) != null) {

            boolean returnOnFlg = ((SessionTeacherLesson) SessionDataUtil.getSessionData(SessionTeacherLesson.class
                    .toString())).getReturnOnFlg();

            if (returnOnFlg == true) {

                String searchPeriodDtFr = ((SessionTeacherLesson) SessionDataUtil
                        .getSessionData(SessionTeacherLesson.class.toString())).getSearchPeriodDtFr();

                String searchPeriodDtTo = ((SessionTeacherLesson) SessionDataUtil
                        .getSessionData(SessionTeacherLesson.class.toString())).getSearchPeriodDtTo();

                String searchTeacherId = ((SessionTeacherLesson) SessionDataUtil
                        .getSessionData(SessionTeacherLesson.class.toString())).getSearchTeacherId();

                String searchTeacherNickname = ((SessionTeacherLesson) SessionDataUtil
                        .getSessionData(SessionTeacherLesson.class.toString())).getSearchTeacherNickname();

                String searchStudentId = ((SessionTeacherLesson) SessionDataUtil
                        .getSessionData(SessionTeacherLesson.class.toString())).getSearchStudentId();

                String searchStudentNickname = ((SessionTeacherLesson) SessionDataUtil
                        .getSessionData(SessionTeacherLesson.class.toString())).getSearchStudentNickname();

                String searchCourseLarge = ((SessionTeacherLesson) SessionDataUtil
                        .getSessionData(SessionTeacherLesson.class.toString())).getSearchCourseLarge();

                String searchCourseMedium = ((SessionTeacherLesson) SessionDataUtil
                        .getSessionData(SessionTeacherLesson.class.toString())).getSearchCourseMedium();

                String searchCourseSmall = ((SessionTeacherLesson) SessionDataUtil
                        .getSessionData(SessionTeacherLesson.class.toString())).getSearchCourseSmall();

                String searchCourseName = ((SessionTeacherLesson) SessionDataUtil
                        .getSessionData(SessionTeacherLesson.class.toString())).getSearchCourseName();

                // 抽出開始日
                this.model.setPeriodDtFr(searchPeriodDtFr);
                // 抽出終了日
                this.model.setPeriodDtTo(searchPeriodDtTo);
                // 講師ID
                this.model.setTeacherId(searchTeacherId);
                // 講師ニックネーム
                this.model.setTeacherNickname(searchTeacherNickname);
                // 受講者ID
                this.model.setStudentId(searchStudentId);
                // 受講者ニックネーム
                this.model.setStudentNickname(searchStudentNickname);
                // 大分類
                this.model.setCourseLarge(searchCourseLarge);
                // 中分類
                this.model.setCourseMedium(searchCourseMedium);
                // 小分類
                this.model.setCourseSmall(searchCourseSmall);
                // コース名
                this.model.setCourseName(searchCourseName);
            }
        }
    }

    /**
     * SessionTeacherLessonManagement値をセット<br>
     * <br>
     * SessionTeacherLessonManagement値をModelにセット<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    private void sessionTeacherLessonManagementToModelAfter() throws Exception {

        if ((SessionTeacherLesson) SessionDataUtil.getSessionData(SessionTeacherLesson.class.toString()) != null) {

            boolean returnOnFlg = ((SessionTeacherLesson) SessionDataUtil.getSessionData(SessionTeacherLesson.class
                    .toString())).getReturnOnFlg();

            if (returnOnFlg == true) {

                String periodDtFr = ((SessionTeacherLesson) SessionDataUtil.getSessionData(SessionTeacherLesson.class
                        .toString())).getPeriodDtFr();

                String periodDtTo = ((SessionTeacherLesson) SessionDataUtil.getSessionData(SessionTeacherLesson.class
                        .toString())).getPeriodDtTo();

                String teacherId = ((SessionTeacherLesson) SessionDataUtil.getSessionData(SessionTeacherLesson.class
                        .toString())).getTeacherId();

                String teacherNickname = ((SessionTeacherLesson) SessionDataUtil
                        .getSessionData(SessionTeacherLesson.class.toString())).getTeacherNickname();

                String studentId = ((SessionTeacherLesson) SessionDataUtil.getSessionData(SessionTeacherLesson.class
                        .toString())).getStudentId();

                String studentNickname = ((SessionTeacherLesson) SessionDataUtil
                        .getSessionData(SessionTeacherLesson.class.toString())).getStudentNickname();

                String courseLarge = ((SessionTeacherLesson) SessionDataUtil.getSessionData(SessionTeacherLesson.class
                        .toString())).getCourseLarge();

                String courseMedium = ((SessionTeacherLesson) SessionDataUtil.getSessionData(SessionTeacherLesson.class
                        .toString())).getCourseMedium();

                String courseSmall = ((SessionTeacherLesson) SessionDataUtil.getSessionData(SessionTeacherLesson.class
                        .toString())).getCourseSmall();

                String courseName = ((SessionTeacherLesson) SessionDataUtil.getSessionData(SessionTeacherLesson.class
                        .toString())).getCourseName();

                String select_rdl = ((SessionTeacherLesson) SessionDataUtil.getSessionData(SessionTeacherLesson.class
                        .toString())).getSearchReservationNoKey();

                // 抽出開始日(jsp)
                this.periodDtFr_txt = periodDtFr;
                // 抽出終了日(jsp)
                this.periodDtTo_txt = periodDtTo;
                // 講師ID(jsp)
                this.teacherId_txt = teacherId;
                // 講師ニックネーム(jsp)
                this.teacherNickname_txt = teacherNickname;
                // 受講者ID(jsp)
                this.studentId_txt = studentId;
                // 受講者ニックネーム(jsp)
                this.studentNickname_txt = studentNickname;
                // 大分類(jsp)
                this.courseLarge_sel = courseLarge;
                // 中分類(jsp)
                this.courseMedium_sel = courseMedium;
                // 小分類(jsp)
                this.courseSmall_sel = courseSmall;
                // コース名(jsp)
                this.courseName_txt = courseName;
                // 一覧から選択された明細データ(jsp)
                this.select_rdl = select_rdl;
            }

            // sessionSessionTeacherLessonManagementのクリア
            SessionDataUtil.clearSessionData(SessionTeacherLesson.class.toString());
        }
    }

    /**
     * Model値をセット<br>
     * <br>
     * Model値・SessionTeacherLessonManagement値をSessionTeacherLessonManagementにセット<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    private void modelToSessionTeacherLessonManagement() throws Exception {

        // セッション情報の初期化
        SessionTeacherLesson sessionData = new SessionTeacherLesson();
        // 戻る判定Onフラグ
        sessionData.setReturnOnFlg(false);
        // 検索Key：抽出開始日
        sessionData.setSearchPeriodDtFr(this.model.getPeriodDtFr());
        // 検索Key：抽出終了日
        sessionData.setSearchPeriodDtTo(this.model.getPeriodDtTo());
        // 検索Key：講師ID
        sessionData.setSearchTeacherId(this.model.getTeacherId());
        // 検索Key：講師ニックネーム
        sessionData.setSearchTeacherNickname(this.model.getTeacherNickname());
        // 検索Key：受講者ID
        sessionData.setSearchStudentId(this.model.getStudentId());
        // 検索Key：受講者ニックネーム
        sessionData.setSearchStudentNickname(this.model.getStudentNickname());
        // 検索Key：大分類
        sessionData.setSearchCourseLarge(this.model.getCourseLarge());
        // 検索Key：中分類
        sessionData.setSearchCourseMedium(this.model.getCourseMedium());
        // 検索Key：小分類
        sessionData.setSearchCourseSmall(this.model.getCourseSmall());
        // 検索Key：コース名
        sessionData.setSearchCourseName(this.model.getCourseName());
        // データをセット
        SessionDataUtil.setSessionData(sessionData);
    }
}