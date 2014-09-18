package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.TeacherLessonManagementModel;
import com.naikara_talk.service.TeacherLessonManagementLoadService;
import com.naikara_talk.sessiondata.SessionTeacherLesson;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>講師。<br>
 * <b>クラス名称　　　:</b>講師側の視点から_1-1_レッスン実績初期処理Actionクラス。<br>
 * <b>クラス概要　　　:</b>講師側の視点から_1-1_レッスン実績初期処理Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/12 TECS 新規作成。
 */
public class TeacherLessonManagementLoadAction extends TeacherLessonManagementActionSupport {

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

        // 講師側の視点から_1-1_レッスン実績のセッション情報をクリア
        if ((SessionTeacherLesson) SessionDataUtil
                .getSessionData(SessionTeacherLesson.class.toString()) != null) {
            SessionDataUtil.clearSessionData(SessionTeacherLesson.class
                    .toString());
        }

        // ロールを取得
        if ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class
                .toString()) != null) {

            role = ((SessionUser) SessionDataUtil
                    .getSessionData(SessionUser.class.toString()))
                    .getRole();
        }

        try {

            initRadio();

        } catch (Exception e) {

            throw new NaiException(e);

        }

        // サービスの初期化
        TeacherLessonManagementLoadService service = new TeacherLessonManagementLoadService();

        // モデルを取得
        TeacherLessonManagementModel model = service.load();

        // 抽出開始日(jsp)はシステム日付を設定
        this.setPeriodDtFr_txt(model.getPeriodDtFr());

        // 抽出終了日(jsp)はシステム日付を設定
        this.setPeriodDtTo_txt(model.getPeriodDtTo());

        // 大分類(jsp)は一件目を設定
        this.setCourseLarge_sel(model.getCourseLarge());

        // 中分類(jsp)は一件目を設定
        this.setCourseMedium_sel(model.getCourseMedium());

        // 小分類(jsp)は一件目を設定
        this.setCourseSmall_sel(model.getCourseSmall());

        // エラーメッセージありの場合
        if (!StringUtils.isEmpty(this.message)) {

            this.addActionMessage(this.message);
        }

        return SUCCESS;
    }
}