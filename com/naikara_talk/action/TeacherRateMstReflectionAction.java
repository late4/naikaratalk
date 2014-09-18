package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.TeacherRateMstReflectionService;
import com.naikara_talk.sessiondata.SessionUserMstTeacherMst;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>講師支払比率の設定<br>
 * <b>クラス概要       :</b>講師支払比率の設定一覧へ反映Action<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/30 TECS 新規作成
 *                          2013/11/14 TECS 要望対応 No1022-6(講師支払比率99⇒99.999)
 */
public class TeacherRateMstReflectionAction extends TeacherRateMstActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 一覧へ反映処理。<br>
     * <br>
     * 一覧へ反映処理。<br>
     * <br>
     * @param なし<br>
     * @return String <br>
     * @exception NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // 支払サイクル、源泉税有無区分の初期取得
        try {
            initRadio();
        } catch (Exception e) {
            throw new NaiException(e);
        }

        // モデル設定
        setupModel();

        // 関連チェック
        try {
            if (errorCheck()) {
                return SUCCESS;
            } else {
                SessionUserMstTeacherMst sessionData = (SessionUserMstTeacherMst) SessionDataUtil
                        .getSessionData(SessionUserMstTeacherMst.class.toString());
                TeacherRateMstReflectionService service = new TeacherRateMstReflectionService();
                // 画面のデータをmodelにセットする
                setupModel();
                this.model.setResultList(sessionData.getTempResultList());
                service.reflection(model);
                sessionData.setTempResultList(this.model.getResultList());
            }
        } catch (Exception e1) {
            throw new NaiException(e1);
        }
        this.numberNo = null;
        this.buaiStart_txt = null;
        this.buaiEnd_txt = null;
        this.buaiRitsu_txt = null;
        this.paymentCycle_sel = null;
        this.source_rdl = null;
        this.select_rdl = null;
        this.startDtList = null;
        this.isUpdateChk = null;
        return SUCCESS;

    }

    /**
     * 一覧へ反映処理前チェック。<br>
     * <br>
     * 一覧へ反映処理前チェック。<br>
     * <br>
     * @param なし<br>
     * @return boolean チェック結果 <br>
     * @exception Exception
     */
    private boolean errorCheck() throws Exception {

        TeacherRateMstReflectionService service = new TeacherRateMstReflectionService();

        // 関連チェック
        int chkResult = service.errorCheck(model);

        switch (chkResult) {

        // ｢適用開始日｣　＞　｢適用終了日｣　の場合
        case TeacherRateMstReflectionService.ERR_INTEGRITY_DT:
            this.addActionMessage(getMessage("EN0011", new String[] { "適用開始日", "適用終了日" }));
            return true;

            // 下記を全て満たさない場合は、エラーとする
            // 適用開始日　≧前画面の「契約期間：開始日」
            // 適用終了日　≦前画面の「契約期間：終了日」
        case TeacherRateMstReflectionService.ERR_INTEGRITY_CONTRACT_DT:
            StringBuffer sb = new StringBuffer();
            sb.append("講師マスタメンテナンス画面の提供期間(").append(this.contractStartDt).append("~").append(this.contractEndDt)
                    .append(")と適用開始日(").append(this.buaiStart_txt).append(")・終了日(").append(this.buaiEnd_txt)
                    .append(")");
            this.addActionMessage(getMessage("EN0034", new String[] { sb.toString() }));
            return true;

            // 支払比率 > 100 の場合
        case TeacherRateMstReflectionService.ERR_MAX_MONEY:
            this.addActionMessage(getMessage("EN0007", new String[] { "支払比率", "100  以下" }));
            return true;

            // 支払比率 < 0 の場合
        case TeacherRateMstReflectionService.ERR_MIN_MONEY:
            this.addActionMessage(getMessage("EN0007", new String[] { "支払比率", "0.000  以上" }));
            return true;

        case TeacherRateMstReflectionService.ERR_PAYMENTRATE_NUM:
            // %1は%2で入力してください。
            this.addActionMessage(getMessage("EN0009", new String[] { "支払比率", "半角数字(小数点含む)" }));
            return true;

            // 更新前チェック：支払比率の小数点以下の桁数チェックエラー
        case TeacherRateMstReflectionService.ERR_PAYMENTRATE_SCALE:
            // %1の数値の小数点桁数は%2桁で入力してください。
            this.addActionMessage(getMessage("EN0008", new String[] { "支払比率", "3" }));
            return true;


            // 支払サイクルのコード値＝”0000”の場合
        case TeacherRateMstReflectionService.ERR_NOT_SELECT_PAYMENTCYCLE_SEL:
            this.addActionMessage(getMessage("EN0001", new String[] { "支払サイクル" }));
            return true;

        }

        return false;
    }

}
