package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.TeacherMstListModel;
import com.naikara_talk.service.TeacherMstListLoadService;
import com.naikara_talk.sessiondata.SessionTeacherMst;
import com.naikara_talk.sessiondata.SessionUserMstTeacherMst;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>講師マスタメンテナンス(一覧)<br>
 * <b>クラス概要       :</b>講師マスタメンテナンス(一覧)初期処理Action<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/22 TECS 新規作成
 */
public class TeacherMstListLoadAction extends TeacherMstListActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 画面の初期表示。<br>
     * <br>
     * 画面の初期表示。<br>
     * <br>
     * @param なし<br>
     * @return String SUCCESS <br>
     * @exception NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        TeacherMstListLoadService service = new TeacherMstListLoadService();

        TeacherMstListModel model = service.load();

        // 処理区分は「照会」を選択する
        this.setProcessKbn_rdl(model.getProcessKbn());

        if (!StringUtils.isEmpty(this.message)) {
            this.addActionMessage(this.message);
        }

        // SessionTeacherMstのクリア
        if ((SessionTeacherMst) SessionDataUtil.getSessionData(SessionTeacherMst.class.toString()) != null) {
            SessionDataUtil.clearSessionData(SessionTeacherMst.class.toString());
        }

        // SessionUserMstTeacherMstのクリア
        if ((SessionUserMstTeacherMst) SessionDataUtil.getSessionData(SessionUserMstTeacherMst.class.toString()) != null) {
            SessionDataUtil.clearSessionData(SessionUserMstTeacherMst.class.toString());
        }

        // 画面を返す
        return SUCCESS;

    }

}
