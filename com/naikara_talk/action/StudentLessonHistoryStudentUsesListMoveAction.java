package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.StudentLessonHistoryUsesListMoveService;
import com.naikara_talk.service.StudentLessonHistoryUsesListSearchService;
import com.naikara_talk.sessiondata.SessionStudentLessonHistoryUsesList;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様(個人)_実績照会<br>
 * <b>クラス名称　　　:</b>受講者側の視点から_1-2_レッスン実績登録選択Actionクラス。<br>
 * <b>クラス概要　　　:</b>受講者側の視点から_1-2_レッスン実績登録選択Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/05 TECS 新規作成。
 */
public class StudentLessonHistoryStudentUsesListMoveAction extends StudentLessonHistoryStudentUsesListActionSupport {

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

        // サービスの初期化
        StudentLessonHistoryUsesListMoveService service = new StudentLessonHistoryUsesListMoveService();

        // 単票をチェック
        int chkResult = service.nextPageRequest(this.model, this.getSelect_rdl(), this.getHasSearchFlg());

        try {

            switch (chkResult) {

            case StudentLessonHistoryUsesListMoveService.ERR_NO_SELECT:

                this.addActionMessage(getMessage("EC0015", new String[] { "一覧部の左の選択", "" }));

                // サービスの初期化
                StudentLessonHistoryUsesListSearchService searchService = new StudentLessonHistoryUsesListSearchService();

                // データ有無をチェック
                chkResult = searchService.checkPreSelectS(this.model);

                switch (chkResult) {

                case StudentLessonHistoryUsesListSearchService.ERR_ZERO_DATA:

                    return SUCCESS;

                case StudentLessonHistoryUsesListSearchService.ERR_MAXOVER_DATA:

                    return SUCCESS;

                }

                // 検索結果一覧
                this.model.setResultList(searchService.selectListS(this.model));

                // 一覧から選択された明細データ(jsp)
                this.select_rdl = NaikaraTalkConstants.BRANK;

                // チェックエラー場合、検索判断フラグ
                this.hasSearchFlg = NaikaraTalkConstants.TRUE;

                return SUCCESS;

            case StudentLessonHistoryUsesListMoveService.ERR_LIST_BACK:

                this.addActionMessage(getMessage("EC0015", new String[] { "一覧部の左の選択", "" }));

                return SUCCESS;
            }

            // 戻る用に必要な情報を取得/格納
            this.modelToSessionStudentLessonHistoryUsesList();

        } catch (Exception e) {

            throw new NaiException(e);

        }

        // ヘッダの戻るリンク用
        setCurrentActionName(NaikaraTalkConstants.STUDENT_LESSON_HISTORY_STUDENT_USES_LIST_SEARCH);

        return NEXTPAGE;
    }

    /**
     * Model値をセット<br>
     * <br>
     * Model値・SessionStudentLessonHistoryUsesList値をSessionStudentLessonHistoryUsesListにセット<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    private void modelToSessionStudentLessonHistoryUsesList() throws Exception {

        // セッション情報の初期化
        SessionStudentLessonHistoryUsesList sessionData = new SessionStudentLessonHistoryUsesList();

        if ((SessionStudentLessonHistoryUsesList) SessionDataUtil
                .getSessionData(SessionStudentLessonHistoryUsesList.class.toString()) != null) {

            String searchLessonDtFr = ((SessionStudentLessonHistoryUsesList) SessionDataUtil
                    .getSessionData(SessionStudentLessonHistoryUsesList.class.toString())).getSearchLessonDtFr();

            String searchLessonDtTo = ((SessionStudentLessonHistoryUsesList) SessionDataUtil
                    .getSessionData(SessionStudentLessonHistoryUsesList.class.toString())).getSearchLessonDtTo();

            String searchStudentId = ((SessionStudentLessonHistoryUsesList) SessionDataUtil
                    .getSessionData(SessionStudentLessonHistoryUsesList.class.toString())).getSearchStudentId();

            String searchStudentNickname = ((SessionStudentLessonHistoryUsesList) SessionDataUtil
                    .getSessionData(SessionStudentLessonHistoryUsesList.class.toString())).getSearchStudentNickname();

            String searchCourseLarge = ((SessionStudentLessonHistoryUsesList) SessionDataUtil
                    .getSessionData(SessionStudentLessonHistoryUsesList.class.toString())).getSearchCourseLarge();

            String searchCourseMedium = ((SessionStudentLessonHistoryUsesList) SessionDataUtil
                    .getSessionData(SessionStudentLessonHistoryUsesList.class.toString())).getSearchCourseMedium();

            String searchCourseSmall = ((SessionStudentLessonHistoryUsesList) SessionDataUtil
                    .getSessionData(SessionStudentLessonHistoryUsesList.class.toString())).getSearchCourseSmall();

            String searchCourseName = ((SessionStudentLessonHistoryUsesList) SessionDataUtil
                    .getSessionData(SessionStudentLessonHistoryUsesList.class.toString())).getSearchCourseName();

            // 検索Key：レッスン開始日
            sessionData.setSearchLessonDtFr(searchLessonDtFr);
            // 検索Key：レッスン終了日
            sessionData.setSearchLessonDtTo(searchLessonDtTo);
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
        // レッスン開始日
        sessionData.setLessonDtFr(this.model.getLessonDtFr());
        // レッスン終了日
        sessionData.setLessonDtTo(this.model.getLessonDtTo());
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