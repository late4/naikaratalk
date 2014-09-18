package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.PointControlListModel;
import com.naikara_talk.service.PointControlListLoadService;
import com.naikara_talk.sessiondata.SessionPointControl;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>マスタ保守<br>
 * <b>クラス名称       :</b>ポイント管理マスタメンテナンス(一覧)<br>
 * <b>クラス概要       :</b>ポイント管理マスタメンテナンス(一覧)初期処理Action<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/01 TECS 新規作成
 */
public class PointControlListLoadAction extends PointControlListActionSupport {

    private static final long serialVersionUID = 1L;

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

        // 通常月謝区分の初期取得。
        try {
            initRadio();
        } catch (Exception e) {
            throw new NaiException(e);
        }

        PointControlListLoadService service = new PointControlListLoadService();

        PointControlListModel model = service.load();

        // 処理区分は「照会」を選択する
        this.setProcessKbn_rdl(model.getProcessKbn());

        // 通常月謝区分 は「全て」を選択する
        this.setFeeKbn_rdl(model.getFeeKbn());

        if (!StringUtils.isEmpty(this.message)) {
            this.addActionMessage(this.message);
        }

        // SessionPointControlのクリア
        if ((SessionPointControl) SessionDataUtil.getSessionData(SessionPointControl.class.toString()) != null) {
            SessionDataUtil.clearSessionData(SessionPointControl.class.toString());
        }

        // 画面を返す
        return SUCCESS;

    }

}
