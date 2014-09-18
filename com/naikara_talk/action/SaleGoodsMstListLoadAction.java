package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.SaleGoodsMstListModel;
import com.naikara_talk.service.SaleGoodsMstListLoadService;
import com.naikara_talk.sessiondata.SessionSaleGoodsMst;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>販売商品マスタメンテナンス(一覧)。<br>
 * <b>クラス名称　　　:</b>販売商品マスタメンテナンス初期処理Actionクラス。<br>
 * <b>クラス概要　　　:</b>販売商品マスタメンテナンス初期処理Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>
 * 2013/06/18 TECS 新規作成。
 */
public class SaleGoodsMstListLoadAction extends SaleGoodsMstListActionSupport {

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

        //SessionSaleGoodsMstのクリア
        SessionDataUtil.clearSessionData(SessionSaleGoodsMst.class.toString());

    	SaleGoodsMstListLoadService service = new SaleGoodsMstListLoadService();

    	SaleGoodsMstListModel model = service.load();

    	// 処理区分は「照会」を選択する
    	this.setProcessKbn_rdl(model.getProcessKbn());

    	// 利用状態は「利用可」を選択する
    	this.setUseKbn_rdl(model.getUseKbn());

    	// 利用状態の初期取得。
        try {
			initRadio();
		} catch (Exception e) {
			throw new NaiException(e);
		}

    	// メッセージの設定
    	if (!StringUtils.isEmpty(this.message)) {
            this.addActionMessage(this.message);
        }

    	// 画面を返す
        return SUCCESS;

    }


}
