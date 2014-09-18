package com.naikara_talk.action;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.OrganizationMyPageLoadService;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>法人_初期処理<br>
 * <b>クラス名称　　　:</b>組織マイページActionクラス。<br>
 * <b>クラス概要　　　:</b>組織責任者の情報を表示。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/01 TECS 新規作成。
 */
public class OrganizationMyPageLoadAction extends OrganizationMyPageActionSupport {

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

        SessionUser sessionUserData = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString()));
        // 組織id
        this.organizationId = sessionUserData.getUserId();
        // 連番
        this.consSeq = Integer.parseInt(sessionUserData.getOrganizationSeq());

        // 権限
        this.role = sessionUserData.getRole();

        if (!StringUtils.isEmpty(this.message)) {
            this.addActionMessage(this.message);
        }

        // お知らせの初期取得。
        try {
            initRadio();
        } catch (Exception e) {
            throw new NaiException(e);
        }

        try {
            this.load();
            return SUCCESS;
        } catch (Exception e) {
            throw new NaiException(e);
        }

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
        OrganizationMyPageLoadService service = new OrganizationMyPageLoadService();

        // 組織ID
        this.model.setOrganizationId(this.organizationId);
        // 連番
        this.model.setConsSeq(this.consSeq);

        model = service.select(this.model);

        /** 組織id */
        SessionUser sessionUserData = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString()));
        this.organizationId = sessionUserData.getUserId();
        /** 連番 */
        this.consSeq = Integer.parseInt(sessionUserData.getOrganizationSeq());
        /** 組織名(jsp) */
        this.organizationJnm = sessionUserData.getOrganizationNm();
        /** 責任者名(姓名) */
        this.managJnm = sessionUserData.getUserNm();
        /** メールアドレス */
        this.managMailAddress1 = model.getManagMailAddress1();
        /** 契約期間：開始日 */
        this.contractStrDt = model.getContractStrDt();
        /** 契約期間：終了日 */
        this.contractEndDt = model.getContractEndDt();
        /** 責任者性別区分 */
        this.managGenderKbn = model.getManagGenderKbn();
        /** 契約ポイント */
        this.tempPointNum = model.getTempPointNum();

        model = service.selectBalancePoint(this.model);
        /** ポイント残高 */
        BigDecimal bigDecimal = new BigDecimal(0);
        if (model.getBalancePoint() != null) {
            bigDecimal = bigDecimal.add(model.getBalancePoint());
        }
        if (model.getBalancePointNum() != null) {
            bigDecimal = bigDecimal.add(model.getBalancePointNum());
        }
        if (model.getBalancePoint() != null || model.getBalancePointNum() != null) {
            this.balancePoint = String.valueOf(bigDecimal);
        }

        model = service.selectPoint(this.model);
        BigDecimal decimal = new BigDecimal(0);
        if (model.getPaymentUsePoint() != null) {
            decimal = decimal.add(model.getPaymentUsePoint());
        }
        model = service.selectLesPoint(this.model);
        if (model.getLesPaymentUsePoint() != null) {
            decimal = decimal.add(model.getLesPaymentUsePoint());
        }
        if (model.getPaymentUsePoint() != null || model.getLesPaymentUsePoint() != null) {
            this.usePoint = String.valueOf(decimal);
        }

    }

}
