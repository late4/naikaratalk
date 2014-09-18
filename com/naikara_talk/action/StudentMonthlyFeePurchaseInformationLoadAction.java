package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.StudentMonthlyFeePurchaseInformationLoadService;
import com.naikara_talk.sessiondata.SessionStudentMst;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>マスタ保守<br>
 * <b>クラス名称　　　:</b>受講者別月謝購入情報初期処理Actionクラス。<br>
 * <b>クラス概要　　　:</b>受講者別月謝購入情報初期処理Action。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/19 TECS 新規作成
 */
public class StudentMonthlyFeePurchaseInformationLoadAction extends StudentMonthlyFeePurchaseInformationActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 画面の初期表示。<br>
     * <br>
     * @param なし
     * @return String
     * @throws NaiException
     */
    protected String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // 画面情報初期化
        this.useStartDt = NaikaraStringUtil.converToYYYY_MM_DD(this.useStartDt);
        this.useEndDt = NaikaraStringUtil.converToYYYY_MM_DD(this.useEndDt);

        this.model
                .setResultList(((SessionStudentMst) SessionDataUtil.getSessionData(SessionStudentMst.class.toString()))
                        .getMonthlyReturnList());
        this.resultList = ((SessionStudentMst) SessionDataUtil.getSessionData(SessionStudentMst.class.toString()))
                .getMonthlyReturnList();

        try {
            if (this.model.getResultList().size() == StudentMonthlyFeePurchaseInformationLoadService.LIST_ZERO_CNT) {

                this.addActionMessage(getMessage("EN0020", new String[] { "月謝購入情報", "" }));
                this.noData = NaikaraTalkConstants.RETURN_CD_DATA_NO;
                return SUCCESS;
            }

        } catch (Exception e) {
            throw new NaiException(e);
        }

        // 画面を返す
        return SUCCESS;
    }
}
