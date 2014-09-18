package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.sessiondata.SessionStudentLessonHistoryUsesList;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様(個人)_実績照会<br>
 * <b>クラス名称　　　:</b>受講者側の視点から_1-2_レッスン実績戻るActionクラス。<br>
 * <b>クラス概要　　　:</b>受講者側の視点から_1-2_レッスン実績戻るAction。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/05 TECS 新規作成。
 */
public class StudentLessonHistoryStudentUsesListReturnAction extends StudentLessonHistoryStudentUsesListActionSupport {

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
        String searchLessonDtFr = ((SessionStudentLessonHistoryUsesList) SessionDataUtil
                .getSessionData(SessionStudentLessonHistoryUsesList.class.toString())).getSearchLessonDtFr();

        // 検索Key：抽出終了日
        String searchLessonDtTo = ((SessionStudentLessonHistoryUsesList) SessionDataUtil
                .getSessionData(SessionStudentLessonHistoryUsesList.class.toString())).getSearchLessonDtTo();

        // 抽出開始日(jsp)
        this.lessonDtFr_txt = searchLessonDtFr;

        // 抽出終了日(jsp)
        this.lessonDtTo_txt = searchLessonDtTo;

        return NEXTPAGE;
    }
}