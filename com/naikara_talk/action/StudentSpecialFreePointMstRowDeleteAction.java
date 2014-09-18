package com.naikara_talk.action;

import java.util.ArrayList;
import java.util.List;

import com.naikara_talk.dto.PointOwnershipTrnDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.StudentSpecialFreePointMstModel;
import com.naikara_talk.sessiondata.SessionStudentMst;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>受講者マスタメンテナンス 特別無償ポイント設定行削除Actionクラス<br>
 * <b>クラス概要       :</b>受講者マスタメンテナンス 特別無償ポイント設定行削除を行う。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/30 TECS 新規作成
 */
public class StudentSpecialFreePointMstRowDeleteAction extends StudentSpecialFreePointMstActionSupport {

    private static final long serialVersionUID = 1L;

    /** 行削除に選択下限チェック */
    private static final int SELECTED_MIN = 1;

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

        SessionStudentMst sessionData = (SessionStudentMst) SessionDataUtil.getSessionData(SessionStudentMst.class
                .toString());

        // 一覧の行削除に選択リスト
        try {
            if (this.select_chk.size() < SELECTED_MIN) {
                this.addActionMessage(getMessage("EN0015", new String[] { "一覧部の「削除」", "" }));
                return SUCCESS;
            } else {
                List<PointOwnershipTrnDto> tempPointOwnershipTrnDtoList = new ArrayList<PointOwnershipTrnDto>();
                tempPointOwnershipTrnDtoList.addAll(sessionData.getTempSpecialFreeReturnList());
                for (int i = 0; i < this.select_chk.size(); i++) {

                    PointOwnershipTrnDto oldDto = sessionData.getTempSpecialFreeReturnList().get(select_chk.get(i));
                    PointOwnershipTrnDto tempDto = new PointOwnershipTrnDto();
                    tempDto.setBalancePoint(oldDto.getBalancePoint());
                    tempDto.setBeginningPurchaseDt(oldDto.getBeginningPurchaseDt());
                    tempDto.setBeginningPurchaseTm(oldDto.getBeginningPurchaseTm());
                    tempDto.setCompensationFreeKbn(oldDto.getCompensationFreeKbn());
                    tempDto.setCompensationFreeKbnNm(oldDto.getCompensationFreeKbnNm());
                    tempDto.setEffectiveEndDt(oldDto.getEffectiveEndDt());
                    tempDto.setEffectiveStrDt(oldDto.getEffectiveStrDt());
                    tempDto.setEndDt(oldDto.getEndDt());
                    tempDto.setEndFlg(oldDto.getEndFlg());
                    tempDto.setFeeKbn(oldDto.getFeeKbn());
                    tempDto.setFeeKbnNm(oldDto.getFeeKbnNm());
                    tempDto.setInsertCd(oldDto.getInsertCd());
                    tempDto.setInsertDttm(oldDto.getInsertDttm());
                    tempDto.setOwnershipId(oldDto.getOwnershipId());
                    tempDto.setPointAdditionReason(oldDto.getPointAdditionReason());
                    tempDto.setPointAdditionReasonCd(oldDto.getPointAdditionReasonCd());
                    tempDto.setPointCd(oldDto.getPointCd());
                    tempDto.setProfileId(oldDto.getProfileId());
                    tempDto.setPurchaseDt(oldDto.getPurchaseDt());
                    tempDto.setPurchaseFreePoint(oldDto.getPurchaseFreePoint());
                    tempDto.setPurchaseTm(oldDto.getPurchaseTm());
                    tempDto.setPurchaseYen(oldDto.getPurchaseYen());
                    tempDto.setScreenSystemKbn(oldDto.getScreenSystemKbn());
                    tempDto.setStudentId(oldDto.getStudentId());
                    tempDto.setUpdateCd(oldDto.getUpdateCd());
                    tempDto.setUpdateDttm(oldDto.getUpdateDttm());
                    tempDto.setUsePoint(oldDto.getUsePoint());
                    tempDto.setUpdateKbn(StudentSpecialFreePointMstModel.UPDATE_KBN_DEL);
                    List<PointOwnershipTrnDto> tempList = new ArrayList<PointOwnershipTrnDto>();
                    tempList.addAll(tempPointOwnershipTrnDtoList);
                    tempPointOwnershipTrnDtoList.clear();
                    for (int j = 0; j < tempList.size(); j++) {
                        if (i == j) {
                            tempPointOwnershipTrnDtoList.add(tempDto);
                        } else {
                            tempPointOwnershipTrnDtoList.add(tempList.get(j));
                        }
                    }
                }
                sessionData.getTempSpecialFreeReturnList().clear();
                sessionData.getTempSpecialFreeReturnList().addAll(tempPointOwnershipTrnDtoList);
            }
        } catch (Exception ex) {

            throw new NaiException(ex);
        }

        this.select_chk = null;
        // 画面を返す
        return SUCCESS;
    }
}
