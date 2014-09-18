package com.naikara_talk.action;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dto.PointOwnershipTrnDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.StudentSpecialFreePointMstModel;
import com.naikara_talk.sessiondata.SessionStudentMst;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>受講者マスタメンテナンス 特別無償ポイント設定一覧反映Actionクラス<br>
 * <b>クラス概要       :</b>受講者マスタメンテナンス 特別無償ポイント設定一覧反映を行う。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/30 TECS 新規作成
 */
public class StudentSpecialFreePointMstListAddAction extends StudentSpecialFreePointMstActionSupport {

    private static final long serialVersionUID = 1L;

    /** 無償ポイント:上限チェック */
    private static final int BALANCE_POINT_MAX = 100000;

    /**
     * 画面遷移前処理。<br>
     * <br>
     * 画面遷移前処理を行う。<br>
     * <br>
     * @param なし<br>
     * @return String<br>
     * @throws NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // Modelクラス設定
        setupModel();

        try {
            if (errorCheck()) {
                return SUCCESS;
            } else {
                SessionStudentMst sessionData = (SessionStudentMst) SessionDataUtil
                        .getSessionData(SessionStudentMst.class.toString());
                setupModel();
                this.model.setResultList(sessionData.getTempSpecialFreeReturnList());
                // 一覧の最終行に追加する
                PointOwnershipTrnDto pointOwnershipTrnDto = new PointOwnershipTrnDto();
                BigDecimal balancePoint = new BigDecimal(NaikaraStringUtil.delComma(StringUtils
                        .isEmpty(this.balancePoint_txt) ? "0" : this.balancePoint_txt));
                pointOwnershipTrnDto.setBalancePoint(balancePoint);
                pointOwnershipTrnDto.setPurchaseFreePoint(balancePoint);
                pointOwnershipTrnDto.setEffectiveStrDt(this.effectStartDt_txt);
                pointOwnershipTrnDto.setEffectiveEndDt(this.effectEndDt_txt);
                pointOwnershipTrnDto.setPointAdditionReasonCd(this.pointAdditionReason_sel);
                pointOwnershipTrnDto.setUpdateKbn(StudentSpecialFreePointMstModel.UPDATE_KBN_ADD);
                pointOwnershipTrnDto.setOwnershipId(NaikaraTalkConstants.BRANK);
                // ポイント付加理由を取得する
                pointOwnershipTrnDto.setPointAdditionReason(this.pointAdditionReason_sell
                        .get(this.pointAdditionReason_sel));
                this.model.getResultList().add(pointOwnershipTrnDto);
                sessionData.setTempSpecialFreeReturnList(this.model.getResultList());

                // ◆◆◆追加◆◆◆行の内容をクリアする
                this.effectStartDt_txt = NaikaraTalkConstants.BRANK;
                this.effectEndDt_txt = NaikaraTalkConstants.BRANK;
                this.balancePoint_txt = NaikaraTalkConstants.BRANK;
                this.pointAdditionReason_sel = NaikaraTalkConstants.CHOICE_ALL_ZERO;
            }

        } catch (Exception ex) {
            throw new NaiException(ex);
        }

        // 画面を返す
        return SUCCESS;
    }

    /**
     * 遷移前チェック。<br>
     * <br>
     * @param なし
     * @return boolean
     * @throws Exception
     */
    private boolean errorCheck() throws Exception {

        // 関連チェック
        // 「有効開始日」は 日付の過去日チェック (日付)の場合
        String today = DateUtil.getSysDate();
        if (DateUtil.dateCompare2(today, NaikaraStringUtil.converToYYYYMMDD(model.getEffectStartDt()))) {
            this.addActionMessage(getMessage("EN0012", new String[] { "有効開始日", "" }));
            return true;
        }

        // 「有効開始日」と「有効終了日」日付の整合性チェック (日付)
        if (DateUtil.dateCompare2(NaikaraStringUtil.converToYYYYMMDD(model.getEffectStartDt()),
                NaikaraStringUtil.converToYYYYMMDD(model.getEffectEndDt()))) {
            this.addActionMessage(getMessage("EN0011", new String[] { "有効開始日", "有効終了日" }));
            return true;
        }

        // 「利用開始日」と「利用終了日」日付の整合性チェック (日付)
        if (DateUtil.dateCompare2(NaikaraStringUtil.converToYYYYMMDD(model.getUseStartDt()),
                NaikaraStringUtil.converToYYYYMMDD(model.getUseEndDt()))) {
            this.addActionMessage(getMessage("EN0011", new String[] { "利用開始日", "利用終了日" }));
            return true;
        }

        // 有効開始日 < 前画面の「利用開始日」エラーチェック
        if (DateUtil.dateCompare2(NaikaraStringUtil.converToYYYYMMDD(model.getUseStartDt()),
                NaikaraStringUtil.converToYYYYMMDD(model.getEffectStartDt()))) {
            this.addActionMessage(getMessage("EN0011", new String[] { "前画面の「利用開始日」", "有効開始日" }));
            return true;
        }

        // 有効終了日 > 前画面の「利用終了日」エラーチェック
        if (DateUtil.dateCompare2(NaikaraStringUtil.converToYYYYMMDD(model.getEffectEndDt()),
                NaikaraStringUtil.converToYYYYMMDD(model.getUseEndDt()))) {
            this.addActionMessage(getMessage("EN0011", new String[] { "有効終了日", "前画面の「利用終了日」" }));
            return true;
        }

        String balancePoint = NaikaraStringUtil.delComma(model.getBalancePoint());

        // ｢無償ポイント｣ >= 100000 の場合
        if (Double.parseDouble(balancePoint) >= BALANCE_POINT_MAX) {
            this.addActionMessage(getMessage("EN0011", new String[] { "無償ポイント", "99,999" }));
            return true;
        }

        return false;
    }

    /** 画面Id */
    protected String pageId;

    /**
     * @return pageId
     */
    public String getPageId() {
        return pageId;
    }

    /**
     * @param pageId セットする pageId
     */
    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

}
