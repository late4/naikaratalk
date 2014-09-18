package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.OrganizationContractMstListModel;
import com.naikara_talk.sessiondata.SessionOrganizationContractMstList;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>組織契約情報登録一覧。<br>
 * <b>クラス名称       :</b>組織契約情報登録一覧初期処理Actionクラス。<br>
 * <b>クラス概要       :</b>組織契約情報登録一覧表示を行う<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/01 TECS 新規作成
 */
public class OrganizationContractMstListLoadAction extends OrganizationContractMstListActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 画面の初期表示。<br>
     * <br>
     *
     * @param なし<br>
     * @return String SUCCESS <br>
     * @exception NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // 処理区分は「新規」を選択する
        this.processKbn_rdl = OrganizationContractMstListModel.PROS_KBN_ADD;

        if (!StringUtils.isEmpty(this.message)) {
            this.addActionMessage(this.message);
        }

        // SessionOrganizationContractMstListのクリア
        if ((SessionOrganizationContractMstList) SessionDataUtil
                .getSessionData(SessionOrganizationContractMstList.class.toString()) != null) {
            SessionDataUtil.clearSessionData(SessionOrganizationContractMstList.class.toString());
        }
        return SUCCESS;
    }

}
