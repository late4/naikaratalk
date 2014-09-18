package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.OrganizationStudentMstListModel;
import com.naikara_talk.model.OrganizationStudentMstModel;
import com.naikara_talk.service.OrganizationStudentMstLoadService;
import com.naikara_talk.sessiondata.SessionTimeManagMst;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>組織_初期処理。<br>
 * <b>クラス名称　　　:</b>システム受講者登録(単票)Actionクラス。<br>
 * <b>クラス概要　　　:</b>組織の社員情報(受講者)の新規アカウント(単票)。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/11 TECS 新規作成。
 */
public class OrganizationStudentMstLoadAction extends OrganizationStudentMstActionSupport {

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

        setupModel();
        OrganizationStudentMstLoadService service = new OrganizationStudentMstLoadService();

        OrganizationStudentMstModel workModel = service.initLoad(model);

        // 前画面から処理区分を画面にセット
        this.processKbn_rdl = workModel.getProcessKbn_rdl();
        this.processKbn_txt = workModel.getProcessKbn_txt();

        // 利用停止フラグの初期取得。
        try {
            initRadio();
        } catch (Exception e) {
            throw new NaiException(e);
        }

        // ******************************
        // 新規・修正・削除の処理
        // ******************************

        // 処理区分＝新規の場合('0':'新規','1':'修正','2':'削除')
        if (StringUtils.equals(OrganizationStudentMstListModel.PROS_KBN_ADD, this.processKbn_rdl)) {
            // 前画面からの受講者IDをクリア
            this.studentId = NaikaraTalkConstants.BRANK;
            this.burdenNum_txt = NaikaraTalkConstants.BURDENNUM;
            return SUCCESS;
        }

        // 処理区分＝修正、削除の場合('0':'新規','1':'修正','2':'削除')
        if (StringUtils.equals(OrganizationStudentMstListModel.PROS_KBN_UPD, this.processKbn_rdl)
                || StringUtils.equals(OrganizationStudentMstListModel.PROS_KBN_DEL, this.processKbn_rdl)) {
            try {
                // データが存在しない場合
                if (NaikaraTalkConstants.RETURN_CD_DATA_NO == service.isExists(model)) {
                    this.message = getMessage("EB0020", new String[] {});
                    removeLatestActionList();
                    return ERROR;
                } else {
                    // データが存在する場合
                    // 表示データの取得処理
                    this.load();
                    return SUCCESS;
                }
            } catch (Exception e) {
                throw new NaiException(e);
            }
        }

        // クリアセッション
        if (((SessionTimeManagMst) SessionDataUtil.getSessionData(SessionTimeManagMst.class.toString())) != null) {
            SessionDataUtil.clearSessionData(SessionTimeManagMst.class.toString());
        }

        // 画面を返す
        return SUCCESS;

    }

    /**
     * 初期処理、表示データの取得。<br>
     * <br>
     * 初期処理、表示データの取得。<br>
     * <br>
     * @param なし<br>
     * @return なし <br>
     * @exception Exception
     */
    private void load() throws Exception {

        // 表示データを取得する
        OrganizationStudentMstLoadService service = new OrganizationStudentMstLoadService();

        // 受講者ID
        this.model.setStudentId(this.studentId);
        // 処理区分(前画面情報)
        this.model.setProcessKbn_rdl(this.processKbn_rdl);
        // 画面表示処理区分
        this.model.setProcessKbn_txt(this.processKbn_txt);

        model = service.select(this.model);

        /** 処理区分 */
        this.processKbn_rdl = model.getProcessKbn_rdl();
        /** 処理区分名 */
        this.processKbn_txt = model.getProcessKbn_txt();
        /** 組織id */
        SessionUser sessionUserData = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString()));
        this.organizationId = sessionUserData.getUserId();
        /** 組織名 */
        this.organizationNm_txt = sessionUserData.getOrganizationNm();
        /** 受講者ID */
        this.studentId = model.getStudentId();
        /** パスワード */
        this.password_txt = model.getPassword_txt();
        /** 仮パスワード確認 */
        this.passworCon_txt = model.getPassworCon_txt();
        /** 受講者所属部署 */
        this.studentPosition_txt = model.getStudentPosition();
        /** 所属組織内ID */
        this.positionOrganizationId_txt = model.getPositionOrganizationId();
        /** 名前(姓) */
        this.familyJnm_txt = model.getFamilyJnm();
        /** 名前(名) */
        this.firstJnm_txt = model.getFirstJnm();
        /** 自己負担率 */
        this.burdenNum_txt = String.valueOf(model.getBurdenNum());
        /** 利用停止フラグ */
        this.useStopFlg_chkn = model.getUseStopFlg();
        if (StringUtils.equals(this.useStopFlg_chkn, NaikaraTalkConstants.USE_KBN_NG)) {
            this.useStopFlg_chkn = NaikaraTalkConstants.TRUE;
        } else {
            this.useStopFlg_chkn = NaikaraTalkConstants.FALSE;
        }
        /** 利用終了日 */
        if (StringUtils.equals(this.useStopFlg_chkn, NaikaraTalkConstants.TRUE)) {
            this.useEndDt_txt = model.getUseEndDt();
        } else {
            this.useEndDt_txt = NaikaraTalkConstants.BRANK;
        }

        /** レコードバージョン番号 */
        this.recordVerNo = String.valueOf(model.getRecordVerNo());

    }

}
