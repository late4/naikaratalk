package com.naikara_talk.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dto.PaypalReturnDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.PurchasePointsPaypalService;
import com.naikara_talk.service.PurchasePointsService;
import com.naikara_talk.sessiondata.SessionPurchaseGoods;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称 :</b>お客様利用画面<br>
 * <b>クラス名称       :</b>ポイント購入ページ確認へ<br>
 * <b>クラス概要       :</b>ポイント購入ページ確認へAction<br>
 * <br>
 * <b>著作権           :</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/22 TECS 新規作成
 */
public class PurchasePointsPaypalAction extends PurchasePointsActionSupport {

	private static final long serialVersionUID = 1L;

	/* マイページへ遷移 */
	public static final String MYPAGE = "mypage";
	/* ペイパルログインへ遷移 */
	public static final String PAYPAL = "paypal";

	/**
	 * 登録/選択ボタンの処理。<br>
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

		// 戻るアクションリストが設定されていない場合、マイページに戻る
		List<String> actionList = new ArrayList<String>();
		String action = "";
		if (this.session.containsKey("actionList")) {
			actionList = (List<String>) this.session.get("actionList");
			if (actionList.size() < 1) {
				SessionDataUtil.clearSessionData(SessionPurchaseGoods.class.toString());
				setCurrentActionName(NaikaraTalkConstants.STUDENT_MY_PAGE_LOAD);
			} else {
				action = actionList.get(actionList.size() - 1);
				if (!StringUtils.equals(action, NaikaraTalkConstants.PURCHASE_POINTS_LOAD)) {
					setCurrentActionName(NaikaraTalkConstants.PURCHASE_POINTS_CONFIRMATION_LOAD);
				}
			}
		} else {
			SessionDataUtil.clearSessionData(SessionPurchaseGoods.class.toString());
			setCurrentActionName(NaikaraTalkConstants.STUDENT_MY_PAGE_LOAD);
		}

		// ペイパル証明処理
		try {
			PurchasePointsPaypalService pppSrvc = new PurchasePointsPaypalService();
			PaypalReturnDto prDto = new PaypalReturnDto();

	    	//エラーの場合、メッセージ設定
			prDto = pppSrvc.expressCheckout(this.userId, this.pointCd);
			switch (prDto.getReturnCode()) {
			case PurchasePointsPaypalService.ERR_NO_DATA:  // EC0042 : %1が失敗しました。システム管理者へ問合せください。
				this.message = getMessage("EC0042", new String[] { "ポイント購入情報の取得" });
				return MYPAGE;
			case PurchasePointsPaypalService.ERR_PAYPAL_CHECKOUT1:
				this.message = getMessage("EC0042", new String[] { "ペイパルの取引開始(1)" });
				return MYPAGE;
			case PurchasePointsPaypalService.ERR_PAYPAL_CHECKOUT2:
				this.message = getMessage("EC0042", new String[] { "ペイパルの取引開始(2)" });
				return MYPAGE;
			case PurchasePointsPaypalService.ERR_PAYPAL_CHECKOUT3:
				this.message = prDto.getErrorMessage();
				return MYPAGE;
			case PurchasePointsPaypalService.ERR_PAYPAL_CHECKOUT4:
				this.message = getMessage("EC0042", new String[] { "ペイパルの取引開始(3)" });
				return MYPAGE;
			}
		} catch (Exception e) {
			throw new NaiException(e);
		}

		// ペイパルログイン画面遷移
		return PAYPAL;

	}

}