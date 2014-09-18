package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.StudentMonthlyFeePurchaseInformationSaveService;
import com.naikara_talk.sessiondata.SessionStudentMst;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守<br>
 * <b>クラス名称　　　:</b>受講者マスタメンテナンス 受講者別月謝購入情報保存Actionクラス。<br>
 * <b>クラス概要　　　:</b>受講者マスタメンテナンス 受講者別月謝購入情報保存Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b> 2013/07/30 TECS 新規作成。
 */
public class StudentMonthlyFeePurchaseInformationSaveAction extends StudentMonthlyFeePurchaseInformationActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 保存ボタンの処理。<br>
     * <br>
     * @param なし
     * @return String
     * @throws NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        setupModel();

        this.model
                .setResultList(((SessionStudentMst) SessionDataUtil.getSessionData(SessionStudentMst.class.toString()))
                        .getMonthlyReturnList());

        // 月謝停止年月のセット
        for (int i = 0; i < model.getResultList().size(); i++) {
            model.getResultList().get(i).setEndDt(this.endDt[i]);
        }

        // 戻るリスト設定
        if (((SessionStudentMst) SessionDataUtil.getSessionData(SessionStudentMst.class.toString())) != null) {
            ((SessionStudentMst) SessionDataUtil.getSessionData(SessionStudentMst.class.toString()))
                    .setMonthlyReturnList(this.model.getResultList());
        }

        // 関連チェック
        try {
            if (errorCheck()) {
                return SUCCESS;
            }
        } catch (Exception ex) {
            throw new NaiException(ex);
        }
        // 月謝停止年月のセット
        for (int i = 0; i < model.getResultList().size(); i++) {
            model.getResultList().get(i).setEndDt(this.endDt[i]);
        }
        // 戻るリスト設定
        if (((SessionStudentMst) SessionDataUtil.getSessionData(SessionStudentMst.class.toString())) != null) {
            ((SessionStudentMst) SessionDataUtil.getSessionData(SessionStudentMst.class.toString()))
                    .setMonthlyReturnList(this.model.getResultList());
        }
        try {
            removeLatestActionList();
        } catch (Exception e) {
            throw new NaiException(e);
        }
        // 詳細画面遷移
        return NEXTPAGE;
    }

    /**
     * 保存前チェック。<br>
     * <br>
     * @param なし
     * @return boolean
     * @throws Exception
     */
    private boolean errorCheck() throws Exception {

        StudentMonthlyFeePurchaseInformationSaveService service = new StudentMonthlyFeePurchaseInformationSaveService();

        // 関連チェック
        int chkResult = service.errorCheck(model);

        switch (chkResult) {

        // 月謝停止年月 フォーマット不正
        case StudentMonthlyFeePurchaseInformationSaveService.ERR_DATE_FORMAT:
            this.addActionMessage(getMessage("EN0075", new String[] { "月謝停止年月(YYYY/MM)", "" }));  //2013/09/25-Upd
            return true;

            // 未来日付がない
        case StudentMonthlyFeePurchaseInformationSaveService.ERR_FUTURE_DATE:
            this.addActionMessage(getMessage("EN0012", new String[] { "月謝停止年月", "" }));
            return true;

            // 月謝停止の最短月以上チェック
        case StudentMonthlyFeePurchaseInformationSaveService.ERR_SHORTEST_MONTH:
            this.addActionMessage(getMessage("EN0011", new String[] { "ポイント所有テーブル．有償ポイント期限", "月謝停止年月" }));
            return true;
            // データ不存在チェック
        case StudentMonthlyFeePurchaseInformationSaveService.ERR_DATA_NASI:
            this.addActionMessage(getMessage("EN0020", new String[] { "ポイント所有テーブル", "" }));
            return true;

        }

        return false;
    }
}
