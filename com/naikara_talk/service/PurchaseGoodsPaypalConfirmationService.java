package com.naikara_talk.service;

import java.util.HashMap;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dto.PaypalReturnDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.PurchaseGoodsLogic;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.sessiondata.SessionPayPal;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;
import com.naikara_talk.paypal.PaypalFunctions;
import com.naikara_talk.dbutil.DbUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称 :</b>お客様利用画面<br>
 * <b>クラス名称       :</b>商品購入ページペイパル決済確認へ<br>
 * <b>クラス概要       :</b>商品購入ページペイパル決済確認へService<br>
 * <br>
 * <b>著作権           :</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/24 TECS 新規作成
 */
public class PurchaseGoodsPaypalConfirmationService implements ActionService {

	// チェック：エラーなし
    public static final int RESULT_OK = 0;

    // チェック：選択ポイントでのポイント管理マスタ取得エラー
    public static final int ERR_NO_DATA = 1;

    // チェック：ペイパル取引開始エラー
    public static final int ERR_PAYPAL_CHECKOUT1 = 2;
    public static final int ERR_PAYPAL_CHECKOUT2 = 3;
    public static final int ERR_PAYPAL_CHECKOUT3 = 4;
    public static final int ERR_PAYPAL_CHECKOUT4 = 5;
	/* 戻り値：異常（受講者情報の不足エラー(メール送信時に必要な情報の不足) ） */
	public static final int RESULT_STUDENT_DATA_SHORTAGE_ERR = 7;

	/**
	 * 確認画面へ遷移する制御処理<br>
	 * <br>
	 * @param
	 * @return 結果
	 * @throws UnsupportedEncodingException
	 */
    public PaypalReturnDto expressCheckout(String[] goodsNm, BigDecimal usePointTotal) throws NaiException, UnsupportedEncodingException {

		Connection conn = null;

		try{
			conn = DbUtil.getConnection();

			PaypalReturnDto prDto = new PaypalReturnDto();


			//◆◆受講者マスタ(メール送信)の項目値の設定有無チェック◆◆
			String userId = "";
	        SessionUser SessionUserData = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString()));
	        if (SessionUserData != null) {
	            // ユーザIDを取得
	            userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();
	        }

			PurchaseGoodsLogic pgLogic = new PurchaseGoodsLogic(conn);
			boolean checkSMrtn = pgLogic.checkStudentMst(userId);
			if (!checkSMrtn) {
	        	prDto.setReturnCode(RESULT_STUDENT_DATA_SHORTAGE_ERR);
	        	prDto.setErrorMessage(NaikaraTalkConstants.BRANK);
				return prDto;
			}
			//◆◆◆◆


			// ペイパル情報取得（ReturnURL,CancelURL）
			CodeManagMstCache cache = CodeManagMstCache.getInstance();

			// ペイパル取引開始処理（setExpressCheckoutの呼び出し）
	    	PaypalFunctions ppf = new PaypalFunctions();

	    	String paymentAmount = usePointTotal.toString();

	    	// http://localhost:8080/naikaratalk/purchaseGoodsPaypalPaymentLoad.action"
			StringBuffer sb = new StringBuffer();    // URLにパラメータ追加の可能性があるのでStringBuffer
			if (StringUtils.equals(cache.decode(NaikaraTalkConstants.CODE_CATEGORY_PAYPAL_INFO,
					NaikaraTalkConstants.PAYPAL_ENV), NaikaraTalkConstants.PAYPAL_ENV_L)) {
				// LIVE
				sb.append(cache.decode(NaikaraTalkConstants.CODE_CATEGORY_PAYPAL_INFO,
						NaikaraTalkConstants.PAYPAL_GOODS_RETURN_URL));
			} else {
				// sandbox
				sb.append(cache.decode(NaikaraTalkConstants.CODE_CATEGORY_PAYPAL_INFO,
						NaikaraTalkConstants.PAYPAL_GOODS_RETURN_URL_T));
			}
			String returnURL = sb.toString();

			// http://localhost:8080/naikaratalk/purchaseGoodsConfirmationLoad.action"
			sb = new StringBuffer();
			if (StringUtils.equals(cache.decode(NaikaraTalkConstants.CODE_CATEGORY_PAYPAL_INFO,
					NaikaraTalkConstants.PAYPAL_ENV), NaikaraTalkConstants.PAYPAL_ENV_L)) {
				// LIVE
				sb.append(cache.decode(NaikaraTalkConstants.CODE_CATEGORY_PAYPAL_INFO,
						NaikaraTalkConstants.PAYPAL_GOODS_CANCEL_URL));
			} else {
				// sandbox
				sb.append(cache.decode(NaikaraTalkConstants.CODE_CATEGORY_PAYPAL_INFO,
						NaikaraTalkConstants.PAYPAL_GOODS_CANCEL_URL_T));
			}
			String cancelURL = sb.toString();

			String desc = NaikaraStringUtil.descEdit(goodsNm, paymentAmount);

	        HashMap nvp = ppf.setExpressCheckout(paymentAmount, returnURL, cancelURL, desc);
	    	if (nvp == null) {
	    		prDto.setReturnCode(ERR_PAYPAL_CHECKOUT1);
	    		prDto.setErrorMessage(NaikaraTalkConstants.BRANK);
				return prDto;
	    	}
	    	String strAck = nvp.get("ACK").toString();
	        if (strAck == null) {
	        	prDto.setReturnCode(ERR_PAYPAL_CHECKOUT2);
	        	prDto.setErrorMessage(NaikaraTalkConstants.BRANK);
				return prDto;
	        }
	        if (!strAck.equalsIgnoreCase("Success") && !strAck.equalsIgnoreCase("SuccessWithWarning")) {
	        	prDto.setReturnCode(ERR_PAYPAL_CHECKOUT3);
				sb = new StringBuffer();
				sb.append(nvp.get("L_ERRORCODE0"));
				sb.append(NaikaraTalkConstants.HALF_SPACE);
				sb.append(nvp.get("L_LONGMESSAGE0"));
				prDto.setErrorMessage(sb.toString());
				return prDto;
	        }
	    	String strToken = nvp.get("TOKEN").toString();
	    	if (strToken == null) {
	    		prDto.setReturnCode(ERR_PAYPAL_CHECKOUT4);
	    		prDto.setErrorMessage(NaikaraTalkConstants.BRANK);
				return prDto;
	    	}

	    	SessionPayPal sessionPayPal = new SessionPayPal();
			// ペイパルトークン
			sessionPayPal.setPaypalToken(strToken);
	        // 商品説明
			sessionPayPal.setGoodsDescription(desc);

			SessionDataUtil.setSessionData(sessionPayPal);

			prDto.setReturnCode(RESULT_OK);
			prDto.setErrorMessage(NaikaraTalkConstants.BRANK);
			return prDto;

		} catch ( SQLException se ) {
			se.printStackTrace();
			throw new NaiException(se);
		} finally {
			try {
				conn.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

    }

}
