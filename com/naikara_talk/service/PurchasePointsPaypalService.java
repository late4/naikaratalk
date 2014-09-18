package com.naikara_talk.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dto.PointManagMstDto;
import com.naikara_talk.dto.PaypalReturnDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.PurchasePointsLogic;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.sessiondata.SessionPurchasePoints;
import com.naikara_talk.sessiondata.SessionPayPal;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;
import com.naikara_talk.paypal.PaypalFunctions;
import com.naikara_talk.dbutil.DbUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称 :</b>お客様利用画面<br>
 * <b>クラス名称       :</b>ポイント購入ページ確認<br>
 * <b>クラス概要       :</b>ポイント購入ページ確認Service<br>
 * <br>
 * <b>著作権           :</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/24 TECS 新規作成
 */
public class PurchasePointsPaypalService implements ActionService {

	// チェック：エラーなし
    public static final int RESULT_OK = 0;

    // チェック：選択ポイントでのポイント管理マスタ取得エラー
    public static final int ERR_NO_DATA = 1;

    // チェック：ペイパル取引開始エラー
    public static final int ERR_PAYPAL_CHECKOUT1 = 2;
    public static final int ERR_PAYPAL_CHECKOUT2 = 3;
    public static final int ERR_PAYPAL_CHECKOUT3 = 4;
    public static final int ERR_PAYPAL_CHECKOUT4 = 5;

	/**
	 * 確認画面へ遷移する制御処理<br>
	 * <br>
	 * @param
	 * @return 結果
	 */
    public PaypalReturnDto expressCheckout(String userId, String pointCd) throws NaiException {

		Connection conn = null;

		try{
			conn = DbUtil.getConnection();

			PurchasePointsLogic ppLogic = new PurchasePointsLogic(conn);
			List<PointManagMstDto> pmmDto = new ArrayList<PointManagMstDto>();
			PaypalReturnDto prDto = new PaypalReturnDto();

	    	// ポイント管理マスタの情報取得
			pmmDto = ppLogic.search(pointCd);

			// ポイント管理マスタの情報取得チェック
			if(pmmDto.size()!=1) {
				prDto.setReturnCode(ERR_NO_DATA);
				prDto.setErrorMessage(NaikaraTalkConstants.BRANK);
				return prDto;
			}
			if(pmmDto.get(0).getReturnCode()==NaikaraTalkConstants.RETURN_CD_DATA_NO) {
				prDto.setReturnCode(ERR_NO_DATA);
				prDto.setErrorMessage(NaikaraTalkConstants.BRANK);
				return prDto;
			}

			// ペイパル情報取得（ReturnURL,CancelURL）
			CodeManagMstCache cache = CodeManagMstCache.getInstance();

			// ペイパル取引開始処理（setExpressCheckoutの呼び出し）
			PaypalFunctions ppf = new PaypalFunctions();
			String paymentAmount = pmmDto.get(0).getMoneyYen().toString();
//			String returnURL = "http://localhost:8080/naikaratalk/purchasePointsPaymentLoad.action?userId="
//				+ userId + "&pointCd=" + pointCd;
			StringBuffer sb = new StringBuffer();
			if (StringUtils.equals(cache.decode(NaikaraTalkConstants.CODE_CATEGORY_PAYPAL_INFO,
					NaikaraTalkConstants.PAYPAL_ENV), NaikaraTalkConstants.PAYPAL_ENV_L)) {
				// LIVE
				sb.append(cache.decode(NaikaraTalkConstants.CODE_CATEGORY_PAYPAL_INFO,
						NaikaraTalkConstants.PAYPAL_POINTS_RETURN_URL));
			} else {
				// sandbox
				sb.append(cache.decode(NaikaraTalkConstants.CODE_CATEGORY_PAYPAL_INFO,
						NaikaraTalkConstants.PAYPAL_POINTS_RETURN_URL_T));
			}
			sb.append("?userId=");
			sb.append(userId);
			sb.append("&pointCd=");
			sb.append(pointCd);
			String returnURL = sb.toString();
//			String cancelURL = "http://localhost:8080/naikaratalk/purchasePointsConfirmationLoad.action?userId="
//				+ userId + "&pointCd=" + pointCd;
			sb = new StringBuffer();
			if (StringUtils.equals(cache.decode(NaikaraTalkConstants.CODE_CATEGORY_PAYPAL_INFO,
					NaikaraTalkConstants.PAYPAL_ENV), NaikaraTalkConstants.PAYPAL_ENV_L)) {
				// LIVE
				sb.append(cache.decode(NaikaraTalkConstants.CODE_CATEGORY_PAYPAL_INFO,
						NaikaraTalkConstants.PAYPAL_POINTS_CANCEL_URL));
			} else {
				// sandbox
				sb.append(cache.decode(NaikaraTalkConstants.CODE_CATEGORY_PAYPAL_INFO,
						NaikaraTalkConstants.PAYPAL_POINTS_CANCEL_URL_T));
			}
			sb.append("?userId=");
			sb.append(userId);
			sb.append("&pointCd=");
			sb.append(pointCd);
			String cancelURL = sb.toString();
	        String desc = "";
	        if (StringUtils.equals(pmmDto.get(0).getFeeKbn(), NaikaraTalkConstants.FEE_KBN_NORMAL)) {
	//          desc = "購入ポイント：通常ポイント（ " + NaikaraStringUtil.addComma(paymentAmount) + "円 ）";
	    		sb = new StringBuffer();
	    		sb.append("通常ポイント（ ");
	    		sb.append(NaikaraStringUtil.addComma(paymentAmount));
	    		sb.append("円 ）");
	    		desc = sb.toString();
	        } else {
	//          desc = "購入ポイント：月謝制（定額制）ポイント（ " + NaikaraStringUtil.addComma(paymentAmount) + "円 ）";
	    		sb = new StringBuffer();
	    		sb.append("月謝制（定額制）ポイント（ ");
	    		sb.append(NaikaraStringUtil.addComma(paymentAmount));
	    		sb.append("円 ）");
	    		desc = sb.toString();
	        }

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

	        // ポイント管理マスタの情報退避
			SessionPurchasePoints sessionData = new SessionPurchasePoints();
			// 戻る制御
			sessionData.setReturnOnFlg(true);
			// 選択された金額-ポイントコード
			sessionData.setSelectPointCd(pointCd);
	        // 金額
			sessionData.setMoneyYen(pmmDto.get(0).getMoneyYen());
	        // 通常月謝区分
			sessionData.setFeeKbn(pmmDto.get(0).getFeeKbn());
	        // 有償ポイント
			sessionData.setPaymentPoint(pmmDto.get(0).getPaymentPoint());
	        // 有償ポイント期限
			sessionData.setPaymentPointTim(pmmDto.get(0).getPaymentPointTim());
	        // 無償ポイント
			sessionData.setFreePoint(pmmDto.get(0).getFreePoint());
	        // 無償ポイント期限
			sessionData.setFreePointTim(pmmDto.get(0).getFreePointTim());

			SessionDataUtil.setSessionData(sessionData);

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
