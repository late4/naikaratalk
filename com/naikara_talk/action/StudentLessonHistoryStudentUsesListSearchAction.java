package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.StudentLessonHistoryUsesListSearchService;
import com.naikara_talk.sessiondata.SessionStudentLessonHistoryUsesList;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様(個人)_実績照会<br>
 * <b>クラス名称　　　:</b>受講者側の視点から_1-2_レッスン実績検索Actionクラス。<br>
 * <b>クラス概要　　　:</b>受講者側の視点から_1-2_レッスン実績検索Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/05 TECS 新規作成。
 */
public class StudentLessonHistoryStudentUsesListSearchAction extends StudentLessonHistoryStudentUsesListActionSupport {

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
        setupModel();

        this.lessonDtFr_txt = NaikaraStringUtil.converToYYYY_MM_DD(this.lessonDtFr_txt);

        this.lessonDtTo_txt = NaikaraStringUtil.converToYYYY_MM_DD(this.lessonDtTo_txt);

        // サービスの初期化
        StudentLessonHistoryUsesListSearchService service = new StudentLessonHistoryUsesListSearchService();

        // 戻る判定Onフラグ
        boolean returnOnFlg = false;

        if ((SessionStudentLessonHistoryUsesList) SessionDataUtil
                .getSessionData(SessionStudentLessonHistoryUsesList.class.toString()) != null) {

            returnOnFlg = ((SessionStudentLessonHistoryUsesList) SessionDataUtil
                    .getSessionData(SessionStudentLessonHistoryUsesList.class.toString())).getReturnOnFlg();

            // チェックエラー場合、検索判断フラグ
            this.hasSearchFlg = ((SessionStudentLessonHistoryUsesList) SessionDataUtil
                    .getSessionData(SessionStudentLessonHistoryUsesList.class.toString())).getHasSearchFlg();
        }

        if (!returnOnFlg) {

            int chkResult;

            try {

                chkResult = service.checkPreSelectS(this.model);

                switch (chkResult) {

                case StudentLessonHistoryUsesListSearchService.ERR_ZERO_DATA:
                    this.addActionMessage(getMessage("EC0020", new String[] {}));
                    return SUCCESS;

                case StudentLessonHistoryUsesListSearchService.ERR_MAXOVER_DATA:

                    this.addActionMessage(getMessage("EC0023", new String[] { "101" }));
                    return SUCCESS;
                }

            } catch (Exception e) {

                throw new NaiException(e);

            }
        }

        try {

            // ヘッダ部の戻るボタン押下の場合は値の入れ替え処理
            this.sessionStudentLessonHistoryUsesListToModelBefore();

            if (!returnOnFlg || StringUtils.equals(NaikaraTalkConstants.TRUE, this.hasSearchFlg)) {

                this.model.setResultList(service.selectListS(this.model));

                if (NaikaraTalkConstants.RETURN_CD_DATA_NO == this.model.getResultList().get(0).getReturnCode()) {
                    this.model.getResultList().clear();
                }

                // チェックエラー場合、検索判断フラグ
                this.hasSearchFlg = Boolean.toString(Boolean.TRUE);

            }

        } catch (Exception e) {

            throw new NaiException(e);

        }

        // 一覧から選択された明細データ(jsp)
        this.select_rdl = NaikaraTalkConstants.BRANK;

        try {

            // ヘッダ部の戻るボタン押下の場合は値の入れ替え処理
            this.sessionStudentLessonHistoryUsesListToModelAfter();

            // 戻る用に必要な情報を格納
            this.modelToSessionStudentLessonHistoryUsesListManagement();

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
     * SessionStudentLessonHistoryUsesListManagement値をセット<br>
     * <br>
     * SessionStudentLessonHistoryUsesListManagement値をModelにセット<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    private void sessionStudentLessonHistoryUsesListToModelBefore() throws Exception {

        if ((SessionStudentLessonHistoryUsesList) SessionDataUtil
                .getSessionData(SessionStudentLessonHistoryUsesList.class.toString()) != null) {

            boolean returnOnFlg = ((SessionStudentLessonHistoryUsesList) SessionDataUtil
                    .getSessionData(SessionStudentLessonHistoryUsesList.class.toString())).getReturnOnFlg();

            if (returnOnFlg == true) {

                String searchLessonDtFr = ((SessionStudentLessonHistoryUsesList) SessionDataUtil
                        .getSessionData(SessionStudentLessonHistoryUsesList.class.toString())).getSearchLessonDtFr();

                String searchLessonDtTo = ((SessionStudentLessonHistoryUsesList) SessionDataUtil
                        .getSessionData(SessionStudentLessonHistoryUsesList.class.toString())).getSearchLessonDtTo();

                String searchStudentId = ((SessionStudentLessonHistoryUsesList) SessionDataUtil
                        .getSessionData(SessionStudentLessonHistoryUsesList.class.toString())).getSearchStudentId();

                String searchStudentNickname = ((SessionStudentLessonHistoryUsesList) SessionDataUtil
                        .getSessionData(SessionStudentLessonHistoryUsesList.class.toString()))
                        .getSearchStudentNickname();

                String searchCourseLarge = ((SessionStudentLessonHistoryUsesList) SessionDataUtil
                        .getSessionData(SessionStudentLessonHistoryUsesList.class.toString())).getSearchCourseLarge();

                String searchCourseMedium = ((SessionStudentLessonHistoryUsesList) SessionDataUtil
                        .getSessionData(SessionStudentLessonHistoryUsesList.class.toString())).getSearchCourseMedium();

                String searchCourseSmall = ((SessionStudentLessonHistoryUsesList) SessionDataUtil
                        .getSessionData(SessionStudentLessonHistoryUsesList.class.toString())).getSearchCourseSmall();

                String searchCourseName = ((SessionStudentLessonHistoryUsesList) SessionDataUtil
                        .getSessionData(SessionStudentLessonHistoryUsesList.class.toString())).getSearchCourseName();

                // レッスン開始日
                this.model.setLessonDtFr(searchLessonDtFr);
                // レッスン終了日
                this.model.setLessonDtTo(searchLessonDtTo);
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
     * SessionStudentLessonHistoryUsesListManagement値をセット<br>
     * <br>
     * SessionStudentLessonHistoryUsesListManagement値をModelにセット<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    private void sessionStudentLessonHistoryUsesListToModelAfter() throws Exception {

        if ((SessionStudentLessonHistoryUsesList) SessionDataUtil
                .getSessionData(SessionStudentLessonHistoryUsesList.class.toString()) != null) {

            boolean returnOnFlg = ((SessionStudentLessonHistoryUsesList) SessionDataUtil
                    .getSessionData(SessionStudentLessonHistoryUsesList.class.toString())).getReturnOnFlg();

            if (returnOnFlg == true) {

                String lessonDtFr = ((SessionStudentLessonHistoryUsesList) SessionDataUtil
                        .getSessionData(SessionStudentLessonHistoryUsesList.class.toString())).getLessonDtFr();

                String lessonDtTo = ((SessionStudentLessonHistoryUsesList) SessionDataUtil
                        .getSessionData(SessionStudentLessonHistoryUsesList.class.toString())).getLessonDtTo();

                String studentId = ((SessionStudentLessonHistoryUsesList) SessionDataUtil
                        .getSessionData(SessionStudentLessonHistoryUsesList.class.toString())).getStudentId();

                String studentNickname = ((SessionStudentLessonHistoryUsesList) SessionDataUtil
                        .getSessionData(SessionStudentLessonHistoryUsesList.class.toString())).getStudentNickname();

                String courseLarge = ((SessionStudentLessonHistoryUsesList) SessionDataUtil
                        .getSessionData(SessionStudentLessonHistoryUsesList.class.toString())).getCourseLarge();

                String courseMedium = ((SessionStudentLessonHistoryUsesList) SessionDataUtil
                        .getSessionData(SessionStudentLessonHistoryUsesList.class.toString())).getCourseMedium();

                String courseSmall = ((SessionStudentLessonHistoryUsesList) SessionDataUtil
                        .getSessionData(SessionStudentLessonHistoryUsesList.class.toString())).getCourseSmall();

                String courseName = ((SessionStudentLessonHistoryUsesList) SessionDataUtil
                        .getSessionData(SessionStudentLessonHistoryUsesList.class.toString())).getCourseName();

                String select_rdl = ((SessionStudentLessonHistoryUsesList) SessionDataUtil
                        .getSessionData(SessionStudentLessonHistoryUsesList.class.toString()))
                        .getSearchReservationNoKey();

                // レッスン開始日(jsp)
                this.lessonDtFr_txt = lessonDtFr;
                // レッスン終了日(jsp)
                this.lessonDtTo_txt = lessonDtTo;
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

            // sessionSessionStudentLessonHistoryUsesListManagementのクリア
            SessionDataUtil.clearSessionData(SessionStudentLessonHistoryUsesList.class.toString());
        }
    }

    /**
     * Model値をセット<br>
     * <br>
     * Model値・SessionStudentLessonHistoryUsesListManagement値をSessionStudentLessonHistoryUsesListManagementにセット<br>
     * <br>
     * @param なし<br>
     * @return なし<br>
     * @exception Exception
     */
    private void modelToSessionStudentLessonHistoryUsesListManagement() throws Exception {

        // セッション情報の初期化
        SessionStudentLessonHistoryUsesList sessionData = new SessionStudentLessonHistoryUsesList();
        // 戻る判定Onフラグ
        sessionData.setReturnOnFlg(false);
        // 検索Key：レッスン開始日
        sessionData.setSearchLessonDtFr(this.model.getLessonDtFr());
        // 検索Key：レッスン終了日
        sessionData.setSearchLessonDtTo(this.model.getLessonDtTo());
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