package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.sessiondata.SessionOrganizationUsageSituation;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>利用状況照会<br>
 * <b>クラス名称　　　:</b>利用状況照会Actionクラス。<br>
 * <b>クラス概要　　　:</b>利用状況照会初期処理Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴　　　　:</b>2013/08/02 TECS 新規作成。
 */

public class OrganizationUsageSituationListLoadAction extends OrganizationUsageSituationListActionSupport {

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

		// SessionPaymentManagedのクリア
        SessionDataUtil.clearSessionData(SessionOrganizationUsageSituation.class.toString());

		// 対象年月を取得 システム日付の当月取得
		this.objectYyyyMm_txt = DateUtil.getSysDateYMNow();

		// 画面を返す
		return SUCCESS;

	}


}
