package com.naikara_talk.action;

import java.math.BigDecimal;
import java.util.List;

import com.naikara_talk.dto.PaymentManagedDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.PaymentManagedLoadService;
import com.naikara_talk.service.PaymentManagedReflectService;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>売上入金・支払管理。<br>
 * <b>クラス名称　　　:</b>支払入力・修正クラス。<br>
 * <b>クラス概要　　　:</b>支払入力・修正reflectAction。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴　　　　:</b>2013/07/30 TECS 新規作成。
 */

public class PaymentManagedReflectAction extends PaymentManagedActionSupport {

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
	 */
	public String requestService() throws NaiException {

		// 画面のパラメータをモデルにセット
		setupModel();

		// 初期化処理
		PaymentManagedLoadService loadService = new PaymentManagedLoadService();

		try {
			// 共通部品：各テーブルのデータ件数取得処理
			int count = loadService.getRowCount(model);

			// データが存在しない場合
			if (count == LIST_ZERO_CNT) {
				// メッセージ情報を設定する
				this.addActionMessage(getMessage("EN0020", new String[] {"支払明細(予定)情報", "" }));
				// 調整額合計と新支払予定額合計を表示する
				this.adjustmentYenSum = new BigDecimal(0);
				this.newPaymentYenSum = new BigDecimal(0);
				// 画面を返す
				return SUCCESS;
			}

			// データが存在する場合、処理を続行する

			// 画面一覧のデータ取得処理
			List<PaymentManagedDto> getlist = loadService.search(this.model);

			PaymentManagedReflectService reflectService = new PaymentManagedReflectService();
			// 表示データの付帯情報の取得と設定
			List<PaymentManagedDto> retlist = reflectService.getInfo(getlist, this.model);

			// 画面表示データに設定する
			this.model.setResultList(retlist);

			// 一覧下の各合計欄(調整額 合計、新支払予定額 合計)を計算して表示する
			this.compute(retlist);

			//  文字種欄チェック、一覧上の｢調整額｣が全て未入力チェック、一覧上の｢調整額｣がマイナス入力の場合の整合性チェック
			int chkResult = reflectService.paymentManagedCheck(retlist);
			switch (chkResult) {
			case PaymentManagedReflectService.ERR_HALH_NUM:
				// 文字種欄チェックエラー
				this.addActionMessage(getMessage("EN0004", new String[] {"調整額", "10" }));
				break;
			case PaymentManagedReflectService.ERR_ALL_NULL:
				// 一覧上の｢調整額｣が全て未入力チェックエラー
				this.addActionMessage(getMessage("EN0044", new String[] {"調整額"}));
				break;
			case PaymentManagedReflectService.ERR_MAENAD:
				// 一覧上の｢調整額｣がマイナス入力の場合の整合性チェックエラー
				this.addActionMessage(getMessage("EN0046", new String[] {"調整額", "現支払予定額" }));
				break;
			}

		} catch (Exception e) {
			throw new NaiException(e);
		}

		// 画面を返す
		return SUCCESS;
	}

}
