package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dto.CourseUsePointMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.sessiondata.SessionCourseMstGoodsMst;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>コースマスタメンテナンス(コース利用ポイント)<br>
 * <b>クラス概要       :</b>コースマスタメンテナンス(コース利用ポイント)修正選択Action<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/13 TECS 新規作成
 */
public class GoodsCourseUsePointMstSelectAction extends GoodsCourseUsePointMstActionSupport {

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

        // コース名の初期取得
        try {
            initRadio();
        } catch (Exception e) {
            throw new NaiException(e);
        }

        // モデル設定
        setupModel();
        SessionCourseMstGoodsMst sessionData = (SessionCourseMstGoodsMst) SessionDataUtil
                .getSessionData(SessionCourseMstGoodsMst.class.toString());
        try {
            if (StringUtils.isEmpty(this.select_rdl)) {
                this.addActionMessage(getMessage("EN0015", new String[] { "一覧部の左の修正" }));
            } else {
                CourseUsePointMstDto dto = sessionData.getTempCourseUsePointMstList().get(
                        Integer.parseInt(this.select_rdl));
                this.numberNo = String.valueOf(Integer.parseInt(this.select_rdl) + 1);
                this.utilizationStart_txt = NaikaraStringUtil.converToYYYY_MM_DD(dto.getUsePointStrDt());
                this.utilizationEnd_txt = NaikaraStringUtil.converToYYYY_MM_DD(dto.getUsePointEndDt());
                this.utilizationPoint_txt = NaikaraStringUtil.addComma(String.valueOf(dto.getUsePoint()));
                this.notice_txt = dto.getInformation();
                this.isUpdateChk = NaikaraTalkConstants.TRUE;
            }
        } catch (Exception e) {
            throw new NaiException(e);
        }
        this.model.setResultList(sessionData.getTempCourseUsePointMstList());
        return SUCCESS;
    }
}
