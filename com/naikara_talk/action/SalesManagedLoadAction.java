package com.naikara_talk.action;

import java.math.BigDecimal;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.SalesManagedLoadService;
import com.naikara_talk.util.NaikaraStringUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>入金管理ページ詳細<br>
 * <b>クラス名称　　　:</b>入金管理ページ詳細クラス。<br>
 * <b>クラス概要　　　:</b>入金管理ページ詳細LoadAction。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/09 TECS 新規作成。
 */

public class SalesManagedLoadAction extends SalesManagedActionSupport{

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

		// 前画面の情報が引継
		String split[] = this.select_rdl.split("#");
		this.costomerKbn = split[0];                                // 顧客区分
		this.studentOrganizationId = split[1];                      // 組織ID/受講者ID
		this.studentOrganizationJnm = split[2];                     // 受講者ID
		this.compensationBeforePoint = new BigDecimal(split[3]);    // 前受有償ポイント(合計)
		this.compensationPurchasePoint = new BigDecimal(split[4]);  // 購入金額(合計)
		this.freeUsePoint = new BigDecimal(split[5]);               // 利用無償ポイント(合計)
		this.compensationUsePoint = new BigDecimal(split[6]);       // 利用有償ポイント(合計)

		SalesManagedLoadService service = new SalesManagedLoadService();
		// コード管理マスタからデータ取得処理 顧客区分名称の取得
		this.costomerKbnName = service.getCostomerKbnName(this.costomerKbn);

		// 画面のパラメータをモデルにセット
		setupModel();

		// 画面一覧のlistを設定
		this.model.setResultList(service.search(model));

		// 画面を返す
		return SUCCESS;
	}

}
