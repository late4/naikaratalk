package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.StudentLessonHistoryUsesListModel;
import com.naikara_talk.service.StudentLessonHistoryUsesListLoadService;
import com.naikara_talk.sessiondata.SessionStudentLessonHistoryUsesList;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様(個人)_実績照会<br>
 * <b>クラス名称　　　:</b>受講者側の視点から_1-2_レッスン実績初期処理Actionクラス。<br>
 * <b>クラス概要　　　:</b>受講者側の視点から_1-2_レッスン実績初期処理Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/05 TECS 新規作成。
 */
public class StudentLessonHistoryStudentUsesListLoadAction extends StudentLessonHistoryStudentUsesListActionSupport {

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

        // コース分類の初期取得。
        try {
            initRadio();
        } catch (Exception e) {
            throw new NaiException(e);
        }

        StudentLessonHistoryUsesListLoadService service = new StudentLessonHistoryUsesListLoadService();

        StudentLessonHistoryUsesListModel model = service.load();

        // レッスン開始日 はシステム日付－１ヶ月を取得する
        this.setLessonDtFr_txt(model.getLessonDtFr());

        // レッスン終了日 はシステム日付を取得する
        this.setLessonDtTo_txt(model.getLessonDtTo());

        // 大分類は一件目を設定
        this.setCourseLarge_sel(model.getCourseLarge());

        // 中分類は一件目を設定
        this.setCourseMedium_sel(model.getCourseMedium());

        // 小分類は一件目を設定
        this.setCourseSmall_sel(model.getCourseSmall());

        if (!StringUtils.isEmpty(this.message)) {
            this.addActionMessage(this.message);
        }

        // SessionPointControlのクリア
        if ((SessionStudentLessonHistoryUsesList) SessionDataUtil
                .getSessionData(SessionStudentLessonHistoryUsesList.class.toString()) != null) {
            SessionDataUtil.clearSessionData(SessionStudentLessonHistoryUsesList.class.toString());
        }

        // 画面を返す
        return SUCCESS;

    }
}