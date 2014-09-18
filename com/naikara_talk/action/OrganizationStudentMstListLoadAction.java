package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.OrganizationStudentMstListModel;
import com.naikara_talk.service.OrganizationStudentMstListLoadService;
import com.naikara_talk.sessiondata.SessionOrganizationStudentMst;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>組織_初期処理<br>
 * <b>クラス名称       :</b>システム受講者登録(一覧)Actionクラス。<br>
 * <b>クラス概要       :</b>組織の社員情報(受講者)の新規アカウント(一覧)。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/09 TECS 新規作成
 */
public class OrganizationStudentMstListLoadAction extends OrganizationStudentMstListActionSupport {

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

        OrganizationStudentMstListLoadService service = new OrganizationStudentMstListLoadService();

        setupModel();

        OrganizationStudentMstListModel model = service.load();

        // 処理区分は「新規」を選択する
        this.setProcessKbn_rdl(model.getProcessKbn());

        // 利用状態の初期取得。
        try {
            initRadio();
        } catch (Exception e) {
            throw new NaiException(e);
        }

        // 利用状態は「利用可」を選択する
        this.setUseKbn_rdl(model.getUseKbn());

        if (!StringUtils.isEmpty(this.message)) {
            this.addActionMessage(this.message);
        }

        if ((SessionOrganizationStudentMst) SessionDataUtil.getSessionData(SessionOrganizationStudentMst.class
                .toString()) != null) {
            SessionDataUtil.clearSessionData(SessionOrganizationStudentMst.class.toString());
        }

        // 戻る用に必要な情報を取得
        SessionOrganizationStudentMst sessionData = new SessionOrganizationStudentMst();

        sessionData.setOrganizationId(this.model.getOrganizationId());                // 組織ID
        sessionData.setOrganizationNm(this.model.getOrganizationNm());                // 組織名
        SessionDataUtil.setSessionData(sessionData);

        // 画面を返す
        return SUCCESS;

    }

}
