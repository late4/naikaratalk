package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.StudentSpecialFreePointMstModel;
import com.naikara_talk.sessiondata.SessionStudentMst;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守<br>
 * <b>クラス名称　　　:</b>受講者マスタメンテナンス 特別無償ポイント設定保存Actionクラス。<br>
 * <b>クラス概要　　　:</b>受講者マスタメンテナンス 特別無償ポイント設定保存Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b> 2013/07/30 TECS 新規作成。
 */
public class StudentSpecialFreePointMstToStudentMstSaveAction extends StudentSpecialFreePointMstActionSupport {

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

        for (int i = 0; i < this.model.getResultList().size(); i++) {
            String ownerShipId = this.model.getResultList().get(i).getOwnershipId();
            String updKbn = this.model.getResultList().get(i).getUpdateKbn();
            if (StringUtils.equals(NaikaraTalkConstants.BRANK, ownerShipId)
                    && StringUtils.equals(StudentSpecialFreePointMstModel.UPDATE_KBN_DEL, updKbn)) {
                this.model.getResultList().remove(i);
                i = -1;
                continue;
            }
        }

        SessionStudentMst sessionData = (SessionStudentMst) SessionDataUtil.getSessionData(SessionStudentMst.class
                .toString());
        sessionData.getSpecialFreeReturnList().clear();
        sessionData.getSpecialFreeReturnList().addAll(sessionData.getTempSpecialFreeReturnList());
        sessionData.getTempSpecialFreeReturnList().clear();

        // 正常の場合 MoveActionで登録した画面遷移を削除
        try {
            removeLatestActionList();
        } catch (Exception e) {
            throw new NaiException(e);
        }
        // 詳細画面遷移
        return NEXTPAGE;
    }
}
