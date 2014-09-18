package com.naikara_talk.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dto.PaypalReturnDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.PurchaseGoodsPaypalConfirmationService;
import com.naikara_talk.service.PurchaseGoodsPointPaymentService;
import com.naikara_talk.sessiondata.ScreenComInfo;
import com.naikara_talk.sessiondata.SessionPurchaseGoods;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称 :</b>お客様利用画面<br>
 * <b>クラス名称       :</b>商品購入ページペイパル決済確認へ<br>
 * <b>クラス概要       :</b>商品購入ページペイパル決済確認へAction<br>
 * <br>
 * <b>著作権           :</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/15 TECS 新規作成
 */
public class PurchaseGoodsPaypalConfirmationAction extends PurchaseGoodsActionSupport {

    private static final long serialVersionUID = 1L;

    /* マイページへ遷移 */ // ← NaikaraActionSupportに定義する？
    public static final String MYPAGE = "mypage";
    /* ペイパルログインへ遷移 */ // ← NaikaraActionSupportに定義する？
    public static final String PAYPAL = "paypal";

    /**
     * ペイパル決済確認へボタンの処理。<br>
     * <br>
     * @param なし
     * @return String
     * @throws NaiException
     */
    public String requestService() throws NaiException {

        // 開始ログ
        log.info(NaikaraStringUtil.unionProcesslog("Start"));

        // 実行判定
		if (((ScreenComInfo) SessionDataUtil.getSessionData(ScreenComInfo.class.toString())) == null) {
			return ACCESS_DENIED;
		}
		if (StringUtils.equals(((ScreenComInfo) SessionDataUtil.getSessionData
				(ScreenComInfo.class.toString())).getPointPurchaseFlg(),
				NaikaraTalkConstants.POINT_PURCHASE_FLG_NO)) {
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
				if (!StringUtils.equals(action, NaikaraTalkConstants.PURCHASE_GOODS_CONFIRMATION_LOAD)) {
					setCurrentActionName(NaikaraTalkConstants.PURCHASE_GOODS_CONFIRMATION_LOAD);
				}
			}
		} else {
			SessionDataUtil.clearSessionData(SessionPurchaseGoods.class.toString());
			setCurrentActionName(NaikaraTalkConstants.STUDENT_MY_PAGE_LOAD);
		}

		// ペイパル証明処理
		try {
			PurchaseGoodsPaypalConfirmationService pppSrvc = new PurchaseGoodsPaypalConfirmationService();
			PaypalReturnDto prDto = new PaypalReturnDto();

	    	//エラーの場合、メッセージ設定
			if (ArrayUtils.isEmpty(this.goodsNm)) {
                this.message = getMessage("EC0002", new String[] { "入力値（商品未選択）", "操作" });
                return MYPAGE;
			}

			// エラー発生時の同一画面での商品表示用
			SessionPurchaseGoods sessionData = new SessionPurchaseGoods();
			if ((SessionPurchaseGoods) SessionDataUtil.getSessionData(SessionPurchaseGoods.class.toString()) == null) {
				sessionData.setSelect_chk(this.goodsCd);
				SessionDataUtil.setSessionData(sessionData);
			}

			prDto = pppSrvc.expressCheckout(this.goodsNm, this.usePointTotalB);
            switch (prDto.getReturnCode()) {
            case PurchaseGoodsPaypalConfirmationService.ERR_NO_DATA:  // EC0042 : %1が失敗しました。システム管理者へ問合せください。
                this.message = getMessage("EC0042", new String[] { "ポイント購入情報の取得" });
                return MYPAGE;
            case PurchaseGoodsPaypalConfirmationService.ERR_PAYPAL_CHECKOUT1:
                this.message = getMessage("EC0042", new String[] { "ペイパルの取引開始(1)" });
                return MYPAGE;
            case PurchaseGoodsPaypalConfirmationService.ERR_PAYPAL_CHECKOUT2:
                this.message = getMessage("EC0042", new String[] { "ペイパルの取引開始(2)" });
                return MYPAGE;
            case PurchaseGoodsPaypalConfirmationService.ERR_PAYPAL_CHECKOUT3:
                this.message = prDto.getErrorMessage();
                return MYPAGE;
            case PurchaseGoodsPaypalConfirmationService.ERR_PAYPAL_CHECKOUT4:
                this.message = getMessage("EC0042", new String[] { "ペイパルの取引開始(3)" });
                return MYPAGE;
                // 結果NG：受講者情報の不足エラー(メール送信時に必要な情報の不足)
            case PurchaseGoodsPaypalConfirmationService.RESULT_STUDENT_DATA_SHORTAGE_ERR :
                this.message = getMessage("EC0076", new String[] {""});
                return SUCCESS;

            }
		} catch (Exception e) {
			throw new NaiException(e);
		}

        // ペイパルログイン画面遷移
        return PAYPAL;

    }

}
