package com.naikara_talk.action;

import java.math.BigDecimal;
import java.util.List;

import com.naikara_talk.dto.PaymentManagedDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.PaymentManagedLoadService;
import com.naikara_talk.util.NaikaraStringUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>売上入金・支払管理。<br>
 * <b>クラス名称　　　:</b>支払入力・修正クラス。<br>
 * <b>クラス概要　　　:</b>支払入力・修正LoadAction。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴　　　　:</b>2013/07/24 TECS 新規作成。
 */

public class PaymentManagedLoadAction extends PaymentManagedActionSupport {

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
		// 開始ログ
		log.info(NaikaraStringUtil.unionProcesslog("Start"));

		// 前画面の情報が引継
		String split[] = this.select_rdl.split("_");
		this.dataYyyyMm = NaikaraStringUtil.converToYYYY_MM(split[0]);   // データ発生年月
		this.userId = split[1];                                          // 講師ID (利用者ID)
		this.recordVerNo = split[2];                                     // レコードバージョン番号
		this.userName = split[3];                                        // 受講者名 名前の編集(名前(姓)、名前(名))
		this.endPaymentYen = new BigDecimal(split[4]);                   // 支払予定額：調整後金額(円)

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
				this.addActionMessage(getMessage("EN0020", new String[] { "支払明細(予定)情報", "" }));
				// 調整額合計と新支払予定額合計を表示する
				this.adjustmentYenSum = new BigDecimal(0);
			    this.newPaymentYenSum = new BigDecimal(0);
				// 画面を返す
				return SUCCESS;
			}

			// データが存在する場合、処理を続行する

			// 画面一覧のデータ取得処理
			List<PaymentManagedDto> list = loadService.search(this.model);
			// 画面表示データに設定する
			this.model.setResultList(list);

			// 一覧下の各合計欄(調整額 合計、新支払予定額 合計)を計算して表示する
			this.compute(list);

		} catch (Exception e) {
            throw new NaiException(e);
        }

		// 画面を返す
		return SUCCESS;
	}

}
