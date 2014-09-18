package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.UserMstListModel;
import com.naikara_talk.service.UserMstListLoadService;
import com.naikara_talk.sessiondata.SessionUserMst;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>マスタ保守。<br>
 * <b>クラス名称　　　:</b>利用者マスタメンテナンス初期処理Actionクラス。<br>
 * <b>クラス概要　　　:</b>利用者マスタメンテナンス初期処理Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/04 TECS 新規作成。
 */
public class UserMstListLoadAction extends UserMstListActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 初期処理<br>
     * <br>
     * 画面項目の初期処理を行う<br>
     * <br>
     * @param なし<br>
     * @return SUCCESS 画面遷移フラグ<br>
     * @exception NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // 利用者マスタメンテナンスのセッション情報をクリア
        if ((SessionUserMst) SessionDataUtil
                .getSessionData(SessionUserMst.class.toString()) != null) {
            SessionDataUtil.clearSessionData(SessionUserMst.class.toString());
        }

        try {

            initRadio();

        } catch (Exception e) {

            throw new NaiException(e);

        }

        // サービスの初期化
        UserMstListLoadService service = new UserMstListLoadService();

        // モデルを取得
        UserMstListModel model = service.load();

        // 処理区分は「照会」を選択
        this.setProcessKbn_rdl(model.getProcessKbn());

        // 利用状態は「利用可」を選択
        this.setUseKbn_rdl(model.getUseKbn());

        // エラーメッセージありの場合
        if (!StringUtils.isEmpty(this.message)) {

            this.addActionMessage(this.message);
        }

        return SUCCESS;
    }
}