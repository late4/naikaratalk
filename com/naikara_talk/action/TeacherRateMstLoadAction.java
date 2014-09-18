package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.sessiondata.SessionUserMstTeacherMst;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>講師支払比率の設定<br>
 * <b>クラス概要       :</b>講師支払比率の設定初期処理Action<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/30 TECS 新規作成
 */
public class TeacherRateMstLoadAction extends TeacherRateMstActionSupport {

    private static final long serialVersionUID = 1L;

    /** りすとサイズ　下限 */
    private static final int LIST_MIN_SIZE = 0;

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

        setupModel();

        // 支払サイクル、源泉税有無区分の初期取得。
        try {
            initRadio();
        } catch (Exception e) {
            throw new NaiException(e);
        }

        SessionUserMstTeacherMst sessionData = (SessionUserMstTeacherMst) SessionDataUtil
                .getSessionData(SessionUserMstTeacherMst.class.toString());
        this.model.setResultList(sessionData.getResultList());
        sessionData.getTempResultList().clear();
        sessionData.getTempResultList().addAll(this.model.getResultList());

        if (LIST_MIN_SIZE == this.model.getResultList().size()
                && StringUtils.equals(NaikaraTalkConstants.PROCESSKBN_UPD, this.processDifference)) {
            try {
                this.addActionMessage(getMessage("EN0020", new String[] { "講師別支払比率マスタ", "" }));
            } catch (Exception e) {
                throw new NaiException(e);
            }
        }
        // 画面を返す
        return SUCCESS;
    }
}