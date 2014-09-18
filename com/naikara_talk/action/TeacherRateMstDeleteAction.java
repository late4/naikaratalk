package com.naikara_talk.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dto.TeacherRateMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.sessiondata.SessionUserMstTeacherMst;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>講師支払比率の設定<br>
 * <b>クラス概要       :</b>講師支払比率の設定行削除Action<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/30 TECS 新規作成
 */
public class TeacherRateMstDeleteAction extends TeacherRateMstActionSupport {

    private static final long serialVersionUID = 1L;

    /** りすとサイズ　下限 */
    private static final int LIST_MIN_SIZE = 0;

    /**
     * 行削除処理。<br>
     * <br>
     * 行削除処理。<br>
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
        String tempSelect_rdl = this.select_rdl;
        String tempNumberNo = this.numberNo;
        try {
            if (this.startDtList.size() == LIST_MIN_SIZE) {
                this.addActionMessage(getMessage("EN0015", new String[] { "一覧部の左の削除" }));
            } else {
                List<TeacherRateMstDto> dtoList = new ArrayList<TeacherRateMstDto>();
                for (int i = 0; i < sessionData.getTempResultList().size(); i++) {
                    TeacherRateMstDto tempDto = sessionData.getTempResultList().get(i);
                    if (this.startDtList.contains(String.valueOf(i))) {
                        if (!StringUtils.isEmpty(this.numberNo) && (i + 1) == Integer.parseInt(this.numberNo)) {
                            tempNumberNo = null;
                            this.buaiStart_txt = null;
                            this.buaiEnd_txt = null;
                            this.buaiRitsu_txt = null;
                            this.paymentCycle_sel = null;
                            this.source_rdl = null;
                            this.isUpdateChk = null;
                            if (!StringUtils.isEmpty(this.select_rdl) && i == Integer.parseInt(tempSelect_rdl)) {
                                tempSelect_rdl = null;
                            }
                        } else if (!StringUtils.isEmpty(this.numberNo)) {
                            if (!StringUtils.isEmpty(this.select_rdl) && i < Integer.parseInt(this.select_rdl)) {
                                tempSelect_rdl = String.valueOf(Integer.parseInt(tempSelect_rdl) - 1);
                            }
                            if ((i + 1) < Integer.parseInt(this.numberNo)) {
                                tempNumberNo = String.valueOf(Integer.parseInt(tempNumberNo) - 1);
                            }
                        }
                        continue;
                    }
                    dtoList.add(tempDto);
                }
                sessionData.setTempResultList(dtoList);
            }
        } catch (Exception e) {
            throw new NaiException(e);
        }
        this.model.setResultList(sessionData.getTempResultList());
        this.select_rdl = tempSelect_rdl;
        this.numberNo = tempNumberNo;
        this.startDtList = null;
        return SUCCESS;
    }
}
