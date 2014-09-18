package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.CustomerManagedListModel;
import com.naikara_talk.service.CustomerManagedListLoadService;
import com.naikara_talk.sessiondata.SessionCustomerManaged;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様管理ページ<br>
 * <b>クラス名称　　　:</b>お客様管理ページクラス。<br>
 * <b>クラス概要　　　:</b>お客様管理ページ初期処理Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/20 TECS 新規作成。
 */

public class CustomerManagedListLoadAction extends CustomerManagedListActionSupport{

	private static final long serialVersionUID = 1L;

	/**
	 * 画面の初期表示。<br>
	 * <br>
	 *
	 * @param なし
	 * @return String
	 * @throws NaiException
	 */
	public String requestService() throws NaiException {

		// 開始ログ
		log.info(NaikaraStringUtil.unionProcesslog("Start"));

		// SessionSalesManagedのクリア
        SessionDataUtil.clearSessionData(SessionCustomerManaged.class.toString());

		CustomerManagedListLoadService service = new CustomerManagedListLoadService();

		// 顧客区分は「全て」を取得    対象期間は「過去３ヶ月」を取得
		CustomerManagedListModel model = service.load();

		// 顧客区分は「全て」を選択する
		this.costomerKbn_rdl = model.getCostomerKbn();

		// 対象期間は「過去３ヶ月」を選択する
		this.objectPeriod_rdl = model.getObjectPeriod();

        try {
        	// 顧客区分と対象期間の初期取得
			initRadio();
		} catch (Exception e) {
			throw new NaiException(e);
		}

		// 画面を返す
		return SUCCESS;

	}

}
