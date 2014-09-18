package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dto.TeacherRateMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.sessiondata.SessionUserMstTeacherMst;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>講師支払比率の設定<br>
 * <b>クラス概要       :</b>講師支払比率の設定修正選択Action<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/30 TECS 新規作成
 */
public class TeacherRateMstSelectAction extends TeacherRateMstActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 修正選択処理。<br>
     * <br>
     * 修正選択処理。<br>
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
        SessionUserMstTeacherMst sessionData = (SessionUserMstTeacherMst) SessionDataUtil
                .getSessionData(SessionUserMstTeacherMst.class.toString());
        try {
            if (StringUtils.isEmpty(this.select_rdl)) {
                this.addActionMessage(getMessage("EN0015", new String[] { "一覧部の左の修正" }));
            } else {
                TeacherRateMstDto dto = sessionData.getTempResultList().get(Integer.parseInt(this.select_rdl));
                this.numberNo = String.valueOf(Integer.parseInt(this.select_rdl) + 1);
                this.buaiStart_txt = NaikaraStringUtil.converToYYYY_MM_DD(dto.getStartDt());
                this.buaiEnd_txt = NaikaraStringUtil.converToYYYY_MM_DD(dto.getEndDt());
                this.buaiRitsu_txt = String.valueOf(dto.getPaymentRate());
                this.paymentCycle_sel = dto.getPaymentCycleCd();
                this.source_rdl = dto.getWithholdingTaxKbn();
                this.isUpdateChk = NaikaraTalkConstants.TRUE;
            }
        } catch (Exception e) {
            throw new NaiException(e);
        }
        this.model.setResultList(sessionData.getTempResultList());
        return SUCCESS;
    }
}
