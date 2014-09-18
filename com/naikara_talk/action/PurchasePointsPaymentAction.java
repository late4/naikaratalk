package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dto.PaypalReturnDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

import com.naikara_talk.sessiondata.SessionPurchasePoints;
import com.naikara_talk.sessiondata.SessionPayPal;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.service.PurchasePointsPaymentService;
import com.naikara_talk.service.PurchasePointsService;
import com.naikara_talk.logic.PurchasePointsLogic;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称 :</b>お客様利用画面<br>
 * <b>クラス名称       :</b>ポイント購入ページ決済<br>
 * <b>クラス概要       :</b>ポイント購入ページ決済Action<br>
 * <br>
 * <b>著作権           :</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/24 TECS 新規作成
 */
public class PurchasePointsPaymentAction extends PurchasePointsActionSupport {

	private static final long serialVersionUID = 1L;

	/* マイページへ遷移 */ // ← NaikaraActionSupportに定義する？
	public static final String MYPAGE = "mypage";

	/**
	 * 支払ボタンの処理。<br>
	 * <br>
	 * @param なし
	 * @return String
	 * @throws NaiException
	 */
	public String requestService() throws NaiException {

		// 開始ログ
		log.info(NaikaraStringUtil.unionProcesslog("Start"));

		PurchasePointsService service = new PurchasePointsService();

		// ユーザIDのチェック
		if (StringUtils.isEmpty(((SessionUser) SessionDataUtil.getSessionData
				(SessionUser.class.toString())).getUserId())) {
			return ACCESS_DENIED;
		}

		// 実行判定
		String strStudentId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();
		if (!service.execCheck(strStudentId)) {
			return ACCESS_DENIED;
		}

		// ポイント購入ページ決済Service
		PurchasePointsPaymentService pppSrvc = new PurchasePointsPaymentService();
		PaypalReturnDto prDto = new PaypalReturnDto();

		try {
			prDto = pppSrvc.paypalOrderConfirm();
			if(prDto.getReturnCode() != PurchasePointsPaymentService.RESULT_OK) {
				if (StringUtils.equals(prDto.getErrorMessage(), NaikaraTalkConstants.BRANK)) {
					this.message = getMessage("EC0042", new String[] { "ペイパルの決済処理" });
				} else {
					this.message = prDto.getErrorMessage();
				}
				// 詳細画面遷移
				return MYPAGE;
			}

			// ポイント所有テーブル登録
			switch(pppSrvc.pointRegister()){
		    // 結果NG 採番ID取得エラー
			case PurchasePointsLogic.ORDER_NUMBER_MST_ERR :
				this.addActionMessage(getMessage("EC0042", new String[] { "ポイント購入（所有IDの取得）" }));
				return SUCCESS;
			// 結果NG ポイント所有テーブル登録エラー
			case PurchasePointsLogic.POINT_OWNER_INSRT_ERR :
				this.addActionMessage(getMessage("EC0042", new String[] { "ポイント購入（ポイント所有の登録）" }));
				return SUCCESS;
			// 結果NG 受講者マスタ更新エラー
			case PurchasePointsLogic.STUDENT_MST_UPDT_ERR :
				this.addActionMessage(getMessage("EC0042", new String[] { "受講者情報（受講者マスタ）の更新" }));
				return SUCCESS;
		    // 結果NG 受講者マスタ取得エラー
			case PurchasePointsLogic.STUDENT_MST_NO_DATA_ERR :
				this.addActionMessage(getMessage("EC0042", new String[] { "受講者情報（受講者マスタなし）の取得" }));
				return SUCCESS;
		    // 結果NG 受講者マスタ重複エラー
			case PurchasePointsLogic.STUDENT_MST_MULTIPLE_DATA_ERR :
				this.addActionMessage(getMessage("EC0042", new String[] { "受講者情報（受講者マスタ重複）の取得" }));
				return SUCCESS;
			}

			this.message = getMessage("IC0014", new String[] { "ポイント購入" });

			// ヘッダの戻るリンク用（MyPageに戻るのでクリア）
			SessionDataUtil.clearSessionData(SessionPurchasePoints.class.toString());

			// ペイパルセッションクリア
			SessionDataUtil.clearSessionData(SessionPayPal.class.toString());

		} catch (Exception e) {
			throw new NaiException(e);
		}

		// 詳細画面遷移
		return MYPAGE;

	}

}
