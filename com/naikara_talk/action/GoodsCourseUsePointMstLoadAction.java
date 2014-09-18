package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.CourseMstListModel;
import com.naikara_talk.sessiondata.SessionCourseMstGoodsMst;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>コースマスタメンテナンス(コース利用ポイント)<br>
 * <b>クラス概要       :</b>コースマスタメンテナンス(コース利用ポイント)初期処理Action<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/13 TECS 新規作成
 */
public class GoodsCourseUsePointMstLoadAction extends GoodsCourseUsePointMstActionSupport {

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

        SessionCourseMstGoodsMst sessionData = (SessionCourseMstGoodsMst) SessionDataUtil
                .getSessionData(SessionCourseMstGoodsMst.class.toString());

        this.model.setResultList(sessionData.getCourseUsePointMstList());
        sessionData.getTempCourseUsePointMstList().clear();
        sessionData.getTempCourseUsePointMstList().addAll(this.model.getResultList());

        if (LIST_MIN_SIZE == this.model.getResultList().size()
                && StringUtils.equals(CourseMstListModel.PROS_KBN_UPD, this.processKbn_rdl)) {
            try {
                this.addActionMessage(getMessage("EN0020", new String[] { "コース別利用ポイントマスタ", "" }));
            } catch (Exception e) {
                throw new NaiException(e);
            }
        }

        // 画面を返す
        return SUCCESS;
    }
}