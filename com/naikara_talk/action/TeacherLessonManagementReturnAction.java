package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.sessiondata.SessionTeacherLesson;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>講師。<br>
 * <b>クラス名称　　　:</b>講師側の視点から_1-1_レッスン実績戻るActionクラス。<br>
 * <b>クラス概要　　　:</b>講師側の視点から_1-1_レッスン実績戻るAction。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/12 TECS 新規作成。
 */
public class TeacherLessonManagementReturnAction extends TeacherLessonManagementActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 戻る処理<br>
     * <br>
     * 戻る処理を行う<br>
     * <br>
     * @param なし<br>
     * @return SUCCESS 画面遷移フラグ<br>
     * @exception NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // 検索Key：抽出開始日
        String searchPeriodDtFr = ((SessionTeacherLesson) SessionDataUtil
                .getSessionData(SessionTeacherLesson.class.toString()))
                .getSearchPeriodDtFr();

        // 検索Key：抽出終了日
        String searchPeriodDtTo = ((SessionTeacherLesson) SessionDataUtil
                .getSessionData(SessionTeacherLesson.class.toString()))
                .getSearchPeriodDtTo();

        // 抽出開始日(jsp)
        this.periodDtFr_txt = searchPeriodDtFr;

        // 抽出終了日(jsp)
        this.periodDtTo_txt = searchPeriodDtTo;

        return NEXTPAGE;
    }
}