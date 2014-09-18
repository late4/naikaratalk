package com.naikara_talk.action;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>認証ログ<br>
 * <b>クラス名称       :</b>認証ログActionクラス<br>
 * <b>クラス概要       :</b>認証ログの初期化<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/01 TECS 新規作成
 */
public abstract class CertificationActionSupport extends NaikaraActionSupport {

    private static final long serialVersionUID = 1L;

    protected Logger logC = Logger.getLogger(NaikaraTalkConstants.LOG_CERTIFICATION);

    /**
     * 認証ログ。
     *
     * @param callClass クラス名
     * @param changeForm 画面名
     * @param beforeId 変更前のログインID
     * @param memo メモ
     * @return slog 返却値
     * @throws SQLException
     * @throws IOException
     */
    public static String unionCertificationlog(String callClass, String changeForm, String beforeId, String memo) {

        // ユーザIDを取得
        String userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();

        // セッションIDを取得
        String sessionId = SessionDataUtil.getSessionId();

        String category = "";
        if (changeForm == NaikaraTalkConstants.FRM_TEACHER_SELECTIONLIST) {
            category = NaikaraTalkConstants.LOG_CATEGORY_CHANGE_T;

        } else if (changeForm == NaikaraTalkConstants.FRM_STUDENT_SELECTIONLIST) {
            category = NaikaraTalkConstants.LOG_CATEGORY_CHANGE_C;

        } else if (changeForm == NaikaraTalkConstants.FRM_ORGANIZATION_SELECTIONLIST) {
            category = NaikaraTalkConstants.LOG_CATEGORY_CHANGE_B;

        } else {
            // NaikaraTalkConstants.FRM_LOGIN_TEACHER
            // NaikaraTalkConstants.FRM_LOGIN_STUDENT:
            // NaikaraTalkConstants.FRM_LOGIN_ORGANIZATION:
            category = NaikaraTalkConstants.LOG_CATEGORY_LOGIN;
        }

        StringBuffer slog = new StringBuffer();

        slog.append(NaikaraTalkConstants.LOG_PAR_START).append(NaikaraTalkConstants.LOG_CLASS_NM).append(callClass)
                .append(NaikaraTalkConstants.LOG_PAR_END); //ユーザID
        slog.append(NaikaraTalkConstants.LOG_COMMA).append(NaikaraTalkConstants.LOG_PAR_START)
                .append(NaikaraTalkConstants.LOG_USER_ID_NM).append(userId).append(NaikaraTalkConstants.LOG_PAR_END); //ユーザID
        slog.append(NaikaraTalkConstants.LOG_COMMA).append(NaikaraTalkConstants.LOG_PAR_START)
                .append(NaikaraTalkConstants.LOG_SESSION_ID_NM).append(sessionId)
                .append(NaikaraTalkConstants.LOG_PAR_END); //セッションID
        slog.append(NaikaraTalkConstants.LOG_COMMA).append(NaikaraTalkConstants.LOG_PAR_START).append(category)
                .append(NaikaraTalkConstants.LOG_PAR_END); //カテゴリ
        slog.append(NaikaraTalkConstants.LOG_COMMA).append(NaikaraTalkConstants.LOG_PAR_START)
                .append(NaikaraTalkConstants.LOG_BEFORE_ID).append(beforeId).append(NaikaraTalkConstants.LOG_PAR_END);
        slog.append(NaikaraTalkConstants.LOG_COMMA).append(NaikaraTalkConstants.LOG_PAR_START).append(memo)
                .append(NaikaraTalkConstants.LOG_PAR_END);

        return slog.toString();
    }

}