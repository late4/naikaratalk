package com.naikara_talk.action;

import java.text.ParseException;
import java.util.List;

import com.naikara_talk.dto.PaymentManagedListDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.PaymentManagedListAllOnService;
import com.naikara_talk.service.PaymentManagedListSearchService;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>売上入金・支払管理。<br>
 * <b>クラス名称　　　:</b>支払管理ページクラス。<br>
 * <b>クラス概要　　　:</b>支払管理ページ全選択Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴　　　　:</b>2013/07/23 TECS 新規作成。
 */

public class PaymentManagedListAllOnAction extends PaymentManagedListActionSupport {

	private static final long serialVersionUID = 1L;

	/* 一覧 ZERO件 */
    private static final int LIST_ZERO_CNT = 0;

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

		// Modelクラス設定
		setupModel();

		PaymentManagedListAllOnService service = new PaymentManagedListAllOnService();

		try {
			PaymentManagedListSearchService serviceS = new PaymentManagedListSearchService();
			// 日付チェック
			if (!serviceS.checkDate(model)) {
				this.addActionMessage(getMessage("EN0035",new String[] { "支払年月" }));
				// 画面を返す
				return SUCCESS;
			}

			// 画面一覧のデータ取得処理
			List<PaymentManagedListDto> list = service.getPaymentTrn(this.model);
			// 0件の場合メッセージ情報を設定する
			if (list == null || list.size() == LIST_ZERO_CNT) {
				this.addActionMessage(getMessage("EN0020", new String[] { "支払(予定)情報", "" }));
				// 画面を返す
				return SUCCESS;
			}

			// 画面表示データに設定する
			this.model.setResultList(list);

			// ｢支払対象｣欄の使用不可・使用可は以下の判定を行い、制御を行う
			this.checkBoxFlag = serviceS.dateCompare(DateUtil.getSysDateYM(),this.payment_txt);

		} catch (Exception e) {
			throw new NaiException(e);
		}

		// 画面を返す
		return SUCCESS;

	}
}
