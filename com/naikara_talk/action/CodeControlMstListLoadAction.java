package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.CodeControlMstListModel;
import com.naikara_talk.service.CodeControlMstListLoadService;
import com.naikara_talk.sessiondata.SessionCodeControlMst;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>コード管理マスタメンテナンス(一覧)。<br>
 * <b>クラス名称　　　:</b>コード管理マスタメンテナンス初期処理Actionクラス。<br>
 * <b>クラス概要　　　:</b>コード管理マスタメンテナンス初期処理Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>
 * 2013/07/16 TECS 新規作成。
 */
public class CodeControlMstListLoadAction extends CodeControlMstListActionSupport {

    private static final long serialVersionUID = 1L;


    /**
     * 画面の初期表示。<br>
     * <br>
     * @param なし
     * @return String
     * @throws NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        //SessionCodeControlMstのクリア
        SessionDataUtil.clearSessionData(SessionCodeControlMst.class.toString());

        // コード管理マスタメンテナンス(一覧)の初期表示のオブジェクトを生成
        CodeControlMstListLoadService service = new CodeControlMstListLoadService();

        // 初期値：処理区分は「照会」を選択
        CodeControlMstListModel model = service.load();

        // 初期値：上記の設定値を画面の処理区分へ設定
        this.setProcessKbn_rdl(model.getProcessKbn());

        try {
            // コード種別名(ドロップダウンリスト)の初期データの取得処理
            this.initSelectList();
        } catch (Exception e) {
            throw new NaiException(e);
        }

        // 初期値：コード種別名はデータ1件目(特に設定なし)
        this.setDefaultCdCategory("");

        // メッセージ領域へメッセージを設定
        if (!StringUtils.isEmpty(this.message)) {
            this.addActionMessage(this.message);
        }

        // 画面を返す
        return SUCCESS;

    }


}
