package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.TeacherLessonManagementMoveService;
import com.naikara_talk.service.TeacherLessonManagementSearchService;
import com.naikara_talk.sessiondata.SessionTeacherLesson;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>講師。<br>
 * <b>クラス名称　　　:</b>講師側の視点から_1-1_レッスン実績登録選択Actionクラス。<br>
 * <b>クラス概要　　　:</b>講師側の視点から_1-1_レッスン実績登録選択Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/12 TECS 新規作成。
 */
public class TeacherLessonManagementMoveAction extends TeacherLessonManagementActionSupport {

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
        // 画面のパラメータセット
        setupModel();
        try {

            if (StringUtils.equals(pageId, "teacherLessonReportLoad")) {

                // サービスの初期化
                TeacherLessonManagementMoveService service = new TeacherLessonManagementMoveService();

                // 単票をチェック
                int chkResult = service.nextPageRequest(this.getSelect_rdl(), this.getHasSearchFlg());

                switch (chkResult) {

                case TeacherLessonManagementMoveService.ERR_NO_SELECT:

                    this.addActionMessage(getMessage("ET0015", new String[] { "", "一覧部の左の選択" }));

                    // サービスの初期化
                    TeacherLessonManagementSearchService searchService = new TeacherLessonManagementSearchService();

                    // データ有無をチェック
                    chkResult = searchService.checkPreSelect(this.model);

                    switch (chkResult) {

                    case TeacherLessonManagementSearchService.ERR_ZERO_DATA:

                        return SUCCESS;

                    case TeacherLessonManagementSearchService.ERR_MAXOVER_DATA:

                        return SUCCESS;

                    }

                    // 検索結果一覧
                    this.model.setResultList(searchService.selectList(this.model));

                    // 一覧から選択された明細データ(jsp)
                    this.select_rdl = NaikaraTalkConstants.BRANK;

                    // チェックエラー場合、検索判断フラグ
                    this.hasSearchFlg = NaikaraTalkConstants.TRUE;

                    return SUCCESS;

                case TeacherLessonManagementMoveService.ERR_LIST_BACK:

                    this.addActionMessage(getMessage("ET0015", new String[] { "", "一覧部の左の選択" }));

                    return SUCCESS;
                }

            }

            // 戻る用に必要な情報を取得/格納
            this.modelToSessionTeacherLessonManagement();
        } catch (Exception e) {

            throw new NaiException(e);

        }
        // ヘッダの戻るリンク用
        setCurrentActionName(NaikaraTalkConstants.TEACHER_LESSON_MANAGEMENT_SEARCH);


        if (!StringUtils.equals(role, SessionUser.ROLE_TEACHER)) {
            // this.frontPageId = "TeacherLessonManagement";
            this.frontPageId = NaikaraTalkConstants.TEACHER_LESSON_MANAGEMENT_LOAD;
        }

        return NEXTPAGE;
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

        if ((SessionTeacherLesson) SessionDataUtil.getSessionData(SessionTeacherLesson.class.toString()) != null) {

            String searchPeriodDtFr = ((SessionTeacherLesson) SessionDataUtil.getSessionData(SessionTeacherLesson.class
                    .toString())).getSearchPeriodDtFr();

            String searchPeriodDtTo = ((SessionTeacherLesson) SessionDataUtil.getSessionData(SessionTeacherLesson.class
                    .toString())).getSearchPeriodDtTo();

            String searchTeacherId = ((SessionTeacherLesson) SessionDataUtil.getSessionData(SessionTeacherLesson.class
                    .toString())).getSearchTeacherId();

            String searchTeacherNickname = ((SessionTeacherLesson) SessionDataUtil
                    .getSessionData(SessionTeacherLesson.class.toString())).getSearchTeacherNickname();

            String searchStudentId = ((SessionTeacherLesson) SessionDataUtil.getSessionData(SessionTeacherLesson.class
                    .toString())).getSearchStudentId();

            String searchStudentNickname = ((SessionTeacherLesson) SessionDataUtil
                    .getSessionData(SessionTeacherLesson.class.toString())).getSearchStudentNickname();

            String searchCourseLarge = ((SessionTeacherLesson) SessionDataUtil
                    .getSessionData(SessionTeacherLesson.class.toString())).getSearchCourseLarge();

            String searchCourseMedium = ((SessionTeacherLesson) SessionDataUtil
                    .getSessionData(SessionTeacherLesson.class.toString())).getSearchCourseMedium();

            String searchCourseSmall = ((SessionTeacherLesson) SessionDataUtil
                    .getSessionData(SessionTeacherLesson.class.toString())).getSearchCourseSmall();

            String searchCourseName = ((SessionTeacherLesson) SessionDataUtil.getSessionData(SessionTeacherLesson.class
                    .toString())).getSearchCourseName();

            // 検索Key：抽出開始日
            sessionData.setSearchPeriodDtFr(searchPeriodDtFr);
            // 検索Key：抽出終了日
            sessionData.setSearchPeriodDtTo(searchPeriodDtTo);
            // 検索Key：講師ID
            sessionData.setSearchTeacherId(searchTeacherId);
            // 検索Key：講師ニックネーム
            sessionData.setSearchTeacherNickname(searchTeacherNickname);
            // 検索Key：受講者ID
            sessionData.setSearchStudentId(searchStudentId);
            // 検索Key：受講者ニックネーム
            sessionData.setSearchStudentNickname(searchStudentNickname);
            // 検索Key：大分類
            sessionData.setSearchCourseLarge(searchCourseLarge);
            // 検索Key：中分類
            sessionData.setSearchCourseMedium(searchCourseMedium);
            // 検索Key：小分類
            sessionData.setSearchCourseSmall(searchCourseSmall);
            // 検索Key：コース名
            sessionData.setSearchCourseName(searchCourseName);
        }

        // 戻る判定Onフラグ
        sessionData.setReturnOnFlg(true);
        // 抽出開始日
        sessionData.setPeriodDtFr(this.model.getPeriodDtFr());
        // 抽出終了日
        sessionData.setPeriodDtTo(this.model.getPeriodDtTo());
        // 講師ID
        sessionData.setTeacherId(this.model.getTeacherId());
        // 講師ニックネーム
        sessionData.setTeacherNickname(this.model.getTeacherNickname());
        // 受講者ID
        sessionData.setStudentId(this.model.getStudentId());
        // 受講者ニックネーム
        sessionData.setStudentNickname(this.model.getStudentNickname());
        // 大分類
        sessionData.setCourseLarge(this.model.getCourseLarge());
        // 中分類
        sessionData.setCourseMedium(this.model.getCourseMedium());
        // 小分類
        sessionData.setCourseSmall(this.model.getCourseSmall());
        // コース名
        sessionData.setCourseName(this.model.getCourseName());
        // 検索Key：選択された明細のKey-予約No
        sessionData.setSearchReservationNoKey(this.getSelect_rdl());
        // 検索判断フラグ
        sessionData.setHasSearchFlg(this.getHasSearchFlg());
        // データをセット
        SessionDataUtil.setSessionData(sessionData);
    }

}