package com.naikara_talk.action;

import java.text.ParseException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dto.PaymentManagedListDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.PaymentManagedListSearchService;
import com.naikara_talk.service.PaymentManagedListUpdateService;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>売上入金・支払管理。<br>
 * <b>クラス名称　　　:</b>支払管理ページクラス。<br>
 * <b>クラス概要　　　:</b>支払管理ページ更新Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴　　　　:</b>2013/07/23 TECS 新規作成。
 */

public class PaymentManagedListUpdateAction extends PaymentManagedListActionSupport {

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

		PaymentManagedListUpdateService service = new PaymentManagedListUpdateService();

		try {
			PaymentManagedListSearchService serviceS = new PaymentManagedListSearchService();
			// 日付チェック
			if (!serviceS.checkDate(model)) {
				this.addActionMessage(getMessage("EN0035",new String[] { "支払年月" }));
				// 画面を返す
				return SUCCESS;
			}
			// 更新対象データの存在チェックを行う
			if (!service.isExist(model)) {
				this.message = getMessage("EN0020", new String[] { "支払(予定)情報", "" });
			} else {
				// 更新処理
				int cnt = service.update(model);
				if (cnt == NaikaraTalkConstants.RETURN_CD_ERR_NO_UPD) {
		        	// 排他エラーメッセージの設定
					this.message = getMessage("ES0014", new String[] { "","" });
		        }else if (cnt == NaikaraTalkConstants.RETURN_CD_ERR_UPD) {
		        	// データベースの該当レコードが更新されない
					this.message = getMessage("ES0006", new String[] { "","" });
		        } else {
		        	// 更新完了メッセージの設定
					this.message = getMessage("IN0011", new String[] { "支払(予定)情報","" });
					return NEXTPAGE;
		        }
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

			// メッセージ情報に値が設定されているかどうかのチェックを行う
			if (!StringUtils.isEmpty(this.message)) {
				// メッセージ情報が存在する場合、メッセージ情報を設定する
	            this.addActionMessage(this.message);
	            // 画面を返す
	    		return SUCCESS;
	        }

		} catch (Exception e) {
			throw new NaiException(e);
		}

		// 画面を返す
		return SUCCESS;

	}
}
