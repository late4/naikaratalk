package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.GoodsCourseUsePointMstReflectionService;
import com.naikara_talk.sessiondata.SessionCourseMstGoodsMst;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>コースマスタメンテナンス(コース利用ポイント)<br>
 * <b>クラス概要       :</b>コースマスタメンテナンス(コース利用ポイント)一覧へ反映Action<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/13 TECS 新規作成
 */
public class GoodsCourseUsePointMstReflectionAction extends GoodsCourseUsePointMstActionSupport {

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

        // コース名の初期取得
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
                SessionCourseMstGoodsMst sessionData = (SessionCourseMstGoodsMst) SessionDataUtil
                        .getSessionData(SessionCourseMstGoodsMst.class.toString());
                GoodsCourseUsePointMstReflectionService service = new GoodsCourseUsePointMstReflectionService();
                // 画面のデータをmodelにセットする
                setupModel();
                this.model.setResultList(sessionData.getTempCourseUsePointMstList());
                service.reflection(model);
                sessionData.setTempCourseUsePointMstList(this.model.getResultList());
            }
        } catch (Exception e1) {
            throw new NaiException(e1);
        }
        this.numberNo = null;
        this.utilizationStart_txt = null;
        this.utilizationEnd_txt = null;
        this.utilizationPoint_txt = null;
        this.notice_txt = null;
        this.select_rdl = null;
        this.select_chk_list = null;
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

        GoodsCourseUsePointMstReflectionService service = new GoodsCourseUsePointMstReflectionService();

        // 関連チェック
        int chkResult = service.errorCheck(model);

        switch (chkResult) {

        // ｢適用開始日｣　＞　｢適用終了日｣　の場合
        case GoodsCourseUsePointMstReflectionService.ERR_INTEGRITY_DT:
            this.addActionMessage(getMessage("EN0011", new String[] { "利用ポイント開始日", "利用ポイント終了日" }));
            return true;

            // 下記を全て満たさない場合は、エラーとする
            // 適用開始日　≧前画面の「契約期間：開始日」
            // 適用終了日　≦前画面の「契約期間：終了日」
        case GoodsCourseUsePointMstReflectionService.ERR_INTEGRITY_CONTRACT_DT:
            StringBuffer sb = new StringBuffer();
            sb.append("コースマスタメンテナンス画面の提供期間(").append(this.useStrDt).append("~").append(this.useEndDt)
                    .append(")と利用ポイント開始日(").append(this.utilizationStart_txt).append(")・終了日(")
                    .append(this.utilizationEnd_txt).append(")");
            this.addActionMessage(getMessage("EN0034", new String[] { sb.toString() }));
            return true;
        }

        return false;
    }

}
