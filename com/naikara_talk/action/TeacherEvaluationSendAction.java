package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.TeacherEvaluationSendService;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様(個人)_受講処理<br>
 * <b>クラス名称       :</b>講師評価画面<br>
 * <b>クラス概要       :</b>講師評価画面送信するAction<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/10 TECS 新規作成
 */
public class TeacherEvaluationSendAction extends TeacherEvaluationActionSupport {

    private static final long serialVersionUID = 1L;

    /** 画面遷移用1 */
    private static final String NEXTPAGE_STUDENTMYPAGE = "next1";

    /** 画面遷移用2 */
    private static final String NEXTPAGE_STUDENTLESSONHISTORYSTUDENTUSESLIST = "next2";

    /**
     * 送信する処理。<br>
     * <br>
     * 送信する処理。<br>
     * <br>
     * @param なし<br>
     * @return String 画面遷移先<br>
     * @exception NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));
        // 画面遷移先
        String transitionScreen = null;

        // 受講者からの講師評価の初期取得
        try {
            initRadio();
        } catch (Exception e) {
            throw new NaiException(e);
        }

        // モデル設定
        setupModel();

        TeacherEvaluationSendService service = new TeacherEvaluationSendService();
        try {
            int rss = service.send(this.model);
            if (NaikaraTalkConstants.RETURN_CD_ERR_UPD == rss || NaikaraTalkConstants.RETURN_CD_ERR_NO_UPD == rss) {
                this.addActionMessage(getMessage("ES0014", new String[] { "", "" }));
                transitionScreen = SUCCESS;
            } else {
                this.message = getMessage("IC0011", new String[] { "講師評価", "" });
                if (service.redirectCheck()) {
                    transitionScreen = NEXTPAGE_STUDENTMYPAGE;
                } else {
                    transitionScreen = NEXTPAGE_STUDENTLESSONHISTORYSTUDENTUSESLIST;
                }
                removeLatestActionList();
            }
        } catch (Exception e) {
            throw new NaiException(e);
        }

        return transitionScreen;

    }
}
