package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.TimeZoneControlMstListModel;
import com.naikara_talk.service.TimeZoneControlMstListLoadService;
import com.naikara_talk.sessiondata.SessionTimeManagMst;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>時差管理マスタメンテナンス【一覧】初期処理Actionクラス。<br>
 * <b>クラス概要       :</b>時差管理マスタメンテナンス【一覧】初期処理Action。<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/09 TECS 新規作成
 */
public class TimeZoneControlMstListLoadAction extends TimeZoneControlMstListActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * サービスの呼び出しの実装。<br>
     * <br>
     * サービスの呼び出しの実装<br>
     * <br>
     * @param なし <br>
     * @return String <br>
     * @exception NaiException
     */
    protected String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // 国コード、時差地域Noの初期取得。
        try {
            initSelect();
        } catch (Exception e) {
            throw new NaiException(e);
        }

        TimeZoneControlMstListLoadService service = new TimeZoneControlMstListLoadService();

        TimeZoneControlMstListModel model = service.load();

        // 処理区分は「照会」を選択する
        this.setProcessKbn_rdl(model.getProcessKbn_rdl());

        // 国コードは「全て」を選択する
        this.setAreaNoCd_sel(model.getAreaNoCd_sel());

        // 時差地域Noは「全て」を選択する
        this.setCountryCd_sel(model.getCountryCd_sel());

        if (!StringUtils.isEmpty(this.message)) {
            this.addActionMessage(this.message);
        }

        // クリアセッション
        if (((SessionTimeManagMst) SessionDataUtil.getSessionData(SessionTimeManagMst.class.toString())) != null) {
            SessionDataUtil.clearSessionData(SessionTimeManagMst.class.toString());
        }

        // 画面を返す
        return SUCCESS;
    }
}
