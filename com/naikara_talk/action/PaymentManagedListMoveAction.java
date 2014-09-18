package com.naikara_talk.action;

import java.text.ParseException;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.PaymentManagedListSearchService;
import com.naikara_talk.sessiondata.SessionPaymentManaged;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>売上入金・支払管理。<br>
 * <b>クラス名称　　　:</b>支払管理ページクラス。<br>
 * <b>クラス概要　　　:</b>支払管理ページMoveAction。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴　　　　:</b>2013/07/23 TECS 新規作成。
 */

public class PaymentManagedListMoveAction extends PaymentManagedListActionSupport {

	private static final long serialVersionUID = 1L;

	/**
	 * 画面の初期表示。<br>
	 * <br>
	 *
	 * @param なし
	 * @return String
	 * @throws NaiException
	 * @throws ParseException
	 */
	public String requestService() throws NaiException {

		// 開始ログ
		log.info(NaikaraStringUtil.unionProcesslog("Start"));

		// 画面のパラメータをモデルにセット
		setupModel();
		try {
			// 日付チェック
			PaymentManagedListSearchService serviceS = new PaymentManagedListSearchService();
			if (!serviceS.checkDate(model)) {
				this.addActionMessage(getMessage("EN0035",new String[] { "支払年月" }));
				// 画面を返す
				return SUCCESS;
			}

			// 「修正選択」の値が選択されていない場合
			if (this.select_rdl == null || StringUtils.equals("", this.select_rdl)) {
				this.message = getMessage("EN0015", new String[] {"一覧部の左の修正選択", "" });
				// 画面を返す
				return SUCCESS;
			}
		} catch (Exception e) {
			throw new NaiException(e);
		}

		try {
			// 戻る用に必要な情報を取得/格納
	        this.modelToSessionPaymentManaged();

			// ヘッダの戻るリンク用
	        setCurrentActionName(NaikaraTalkConstants.PAYMENT_MANAGED_LIST_SEARCH);

        } catch (Exception e) {
            throw new NaiException(e);
        }

		// 支払入力・修正遷移
		return NEXTPAGE;

	}

	/**
	 * 戻る用に必要な情報を取得/格納。
	 * Model値・SessionPaymentManaged値をSessionPaymentManagedにセット。
	 * @param なし
	 * @throws Exception
	 */
	private void modelToSessionPaymentManaged() throws Exception {

        // 戻る用に必要な情報を格納
		SessionPaymentManaged sessionData = new SessionPaymentManaged();

		sessionData.setPayment_txt(payment_txt);               // 支払年月
		sessionData.setSelect_rdl(select_rdl);                 // 修正選択
        sessionData.setReturnOnFlg(true);                      // 戻る判定Onフラグ
        SessionDataUtil.setSessionData(sessionData);

    }

}
