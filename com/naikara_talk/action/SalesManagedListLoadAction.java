package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.CsvFileDeleteService;
import com.naikara_talk.sessiondata.SessionSalesManaged;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>入金管理ページ<br>
 * <b>クラス名称　　　:</b>入金管理ページクラス。<br>
 * <b>クラス概要　　　:</b>入金管理ページ初期処理Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/09 TECS 新規作成。
 */

public class SalesManagedListLoadAction extends SalesManagedListActionSupport{

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
        SessionDataUtil.clearSessionData(SessionSalesManaged.class.toString());

        // 過去に作成したCSVファイルの削除処理
        CsvFileDeleteService.removeCsvFile();

        try {
        	// 顧客区分の初期取得
			initRadio();

			// 対象年月を取得 ｼｽﾃﾑ年月-1ヶ月
			this.objectYyyyMm_txt = DateUtil.getSysDateYM();

			// 顧客区分 は「全て」を設定する
			this.costomerKbn_rdl = NaikaraTalkConstants.CHOICE_ALL_ZERO;
		} catch (Exception e) {
			throw new NaiException(e);
		}

		// 画面を返す
		return SUCCESS;

	}

}
