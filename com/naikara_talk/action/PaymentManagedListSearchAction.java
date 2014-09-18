package com.naikara_talk.action;

import java.text.ParseException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dto.PaymentManagedListDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.PaymentManagedListSearchService;
import com.naikara_talk.sessiondata.SessionPaymentManaged;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>売上入金・支払管理。<br>
 * <b>クラス名称　　　:</b>支払管理ページクラス。<br>
 * <b>クラス概要　　　:</b>支払管理ページ検索Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴　　　　:</b>2013/07/23 TECS 新規作成。
 */

public class PaymentManagedListSearchAction extends PaymentManagedListActionSupport {

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
		// 選択したラジオボタンをクリアする
		this.select_rdl = NaikaraTalkConstants.BRANK;

		// 戻る判定Onフラグ
        boolean returnOnFlg = false;
        try {
			if ((SessionPaymentManaged) SessionDataUtil.getSessionData(SessionPaymentManaged.class.toString()) != null) {

                // 戻る判定Onフラグ
                returnOnFlg = ((SessionPaymentManaged) SessionDataUtil.getSessionData(SessionPaymentManaged.class
                        .toString())).getReturnOnFlg();
            }

			if (returnOnFlg == true) {
				// 支払年月
				this.payment_txt = ((SessionPaymentManaged) SessionDataUtil.getSessionData(SessionPaymentManaged.class
                        .toString())).getPayment_txt();
				// 選択されたデータ
				this.select_rdl = ((SessionPaymentManaged) SessionDataUtil.getSessionData(SessionPaymentManaged.class
                        .toString())).getSelect_rdl();

				// SessionPaymentManagedのクリア
		        SessionDataUtil.clearSessionData(SessionPaymentManaged.class.toString());
			}
		} catch (Exception e) {
            throw new NaiException(e);
        }

		// Modelクラス設定
		setupModel();

		PaymentManagedListSearchService service = new PaymentManagedListSearchService();

		try {
			// 検索前チェック
			// 支払年月が必須チェック
			if (!service.checkPayment(model)) {
				this.addActionMessage(getMessage("EN0001",new String[] { "支払年月" }));
				// 画面を返す
				return SUCCESS;
			}
			// 日付チェック
			if (!service.checkDate(model)) {
				this.addActionMessage(getMessage("EN0035",new String[] { "支払年月" }));
				// 画面を返す
				return SUCCESS;
			}

			// 共通部品：各テーブルのデータ件数取得処理
			int count = service.getRowCount(model);

			// データが存在しない場合
			if (count == LIST_ZERO_CNT) {
				this.addActionMessage(getMessage("EN0020", new String[] {"支払(予定)情報", "" }));
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
			this.setPayment_txt(NaikaraStringUtil.converToYYYY_MM(this.model.getPayment_txt()));

			// ｢支払対象｣欄の使用不可・使用可は以下の判定を行い、制御を行う
			this.checkBoxFlag = service.dateCompare(DateUtil.getSysDateYM(),this.payment_txt);

			// メッセージ情報が存在する場合、メッセージ情報を設定する
			if (!StringUtils.isEmpty(this.message)) {
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
