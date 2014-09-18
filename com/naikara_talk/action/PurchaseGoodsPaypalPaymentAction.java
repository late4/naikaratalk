package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dto.PaypalReturnDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

import com.naikara_talk.sessiondata.ScreenComInfo;
import com.naikara_talk.sessiondata.SessionPurchaseGoods;
import com.naikara_talk.sessiondata.SessionPayPal;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.service.PurchaseGoodsPaypalPaymentService;
import com.naikara_talk.logic.PurchaseGoodsLogic;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称 :</b>お客様利用画面<br>
 * <b>クラス名称       :</b>商品購入ページペイパル決済<br>
 * <b>クラス概要       :</b>商品購入ページペイパル決済Action<br>
 * <br>
 * <b>著作権           :</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/16 TECS 新規作成
 */
public class PurchaseGoodsPaypalPaymentAction extends PurchaseGoodsActionSupport {

    private static final long serialVersionUID = 1L;

    /* マイページへ遷移 */
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

        // 実行判定
		if (((ScreenComInfo) SessionDataUtil.getSessionData(ScreenComInfo.class.toString())) == null) {
			return ACCESS_DENIED;
		}
		if (StringUtils.equals(((ScreenComInfo) SessionDataUtil.getSessionData
				(ScreenComInfo.class.toString())).getPointPurchaseFlg(),
				NaikaraTalkConstants.POINT_PURCHASE_FLG_NO)) {
			return ACCESS_DENIED;
		}

        // ヘッダの戻るリンク用
        SessionDataUtil.clearSessionData(SessionPurchaseGoods.class.toString());
		setCurrentActionName(NaikaraTalkConstants.STUDENT_MY_PAGE_LOAD);

        // ポイント購入ページ決済Service
        PurchaseGoodsPaypalPaymentService pgppSrvc = new PurchaseGoodsPaypalPaymentService();
		PaypalReturnDto prDto = new PaypalReturnDto();

		try {
			prDto = pgppSrvc.paypalOrderConfirm();
	        if(prDto.getReturnCode() != PurchaseGoodsPaypalPaymentService.RESULT_OK) {
	        	if (StringUtils.equals(prDto.getErrorMessage(), NaikaraTalkConstants.BRANK)) {
		        	this.message = getMessage("EC0042", new String[] { "ペイパルの決済処理" });
	        	} else {
		        	this.message = prDto.getErrorMessage();
	        	}
	            // 詳細画面遷移
	            return MYPAGE;
	        }

			// 受講者ＩＤ、商品コード、利用ポイントのセット
			String studentId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();
			String[] goodCd = this.goodsCd;

	        // 商品購入テーブル登録
			if (pgppSrvc.goodsPurchaseRegister(studentId, goodCd) == PurchaseGoodsPaypalPaymentService.RESULT_NG){
				this.message = getMessage("EC0042", new String[] { "商品購入テーブルの登録" });
				return SUCCESS;
	        }

	        this.message = getMessage("IC0014", new String[] { "商品購入" });

	        // ペイパルセッションクリア
	        SessionDataUtil.clearSessionData(SessionPayPal.class.toString());

		} catch (Exception e) {
			throw new NaiException(e);
		}

        // 詳細画面遷移
        return NEXTPAGE;

    }

}