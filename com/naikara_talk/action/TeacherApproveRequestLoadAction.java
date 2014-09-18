package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.TeacherApproveRequestLoadService;
import com.naikara_talk.sessiondata.SessionTeacherApproveRequest;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>講師<br>
 * <b>クラス名称       :</b>応相談回答ページ初期処理Actionクラス。<br>
 * <b>クラス概要       :</b>応相談回答ページの初期表示<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2014/06/02 TECS 新規作成
 */
public class TeacherApproveRequestLoadAction extends TeacherApproveRequestActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 画面の初期表示。<br>
     * <br>
     *
     * @param なし<br>
     * @return String SUCCESS<br>
     * @exception NaiException
     */
    public String requestService() throws NaiException {

        // ◆◆◆開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // TeacherApproveRequestLoadServiceの生成
        TeacherApproveRequestLoadService service = new TeacherApproveRequestLoadService();

        // 使用者(操作者)情報の取得
        SessionUser sessionUser = (SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString());
        String userId = "";
        String userNm = "";
        if (sessionUser != null) {
            userId = sessionUser.getUserId();
            userNm = sessionUser.getUserNm();
        }

        // ◆◆◆ modelへの値の設定
        this.model.setUserId(userId);
        this.model.setUserNm(userNm);

        // ◆◆◆ 講師予定予約テーブルデータの取得処理
        try {
            this.model = service.select(this.model);

            if (model.getTargetList().size() <= 0) {
                // 対象データなし
                this.message = getMessage("ET0020", new String[] {});
                // ◆◆◆ 講師マイページを表示
                return "previous";
            }

        } catch (Exception e) {
            throw new NaiException(e);
        }

        // ◆◆◆ メッセージ領域表示用
        if (!StringUtils.isEmpty(this.message)) {
            this.addActionMessage(this.message);
        }

        // 情報を取得/格納
        this.modelToSessionTeacherApproveRequest();

        // ◆◆◆ 返却
        return SUCCESS;
    }

    /**
     * Model値をSessionTeacherApproveRequestに設定。<br>
     * <br>
     * @throws Exception
     */
    private void modelToSessionTeacherApproveRequest() throws NaiException {

        // 必要な情報を格納
        SessionTeacherApproveRequest sessionData = new SessionTeacherApproveRequest();

        sessionData.setUserId(this.model.getUserId());                // 講師ID
        sessionData.setUserNm(this.model.getUserNm());                // 講師ニックネーム

        sessionData.setReplyKbn(this.model.getReplyKbn());            // 回答区分
        sessionData.setSubject(this.model.getSubject());              // 件名
        sessionData.setEmailText(this.model.getEmailText());          // メール本文
        sessionData.setTargetList(this.model.getTargetList());        // 一覧
        sessionData.setSelectReservationNo(this.getSelect_chk());     // 選択値

        SessionDataUtil.setSessionData(sessionData);

    }


}
