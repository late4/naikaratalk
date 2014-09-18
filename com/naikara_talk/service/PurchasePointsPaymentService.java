package com.naikara_talk.service;

import java.math.BigDecimal;
import java.util.HashMap;

import java.sql.Connection;

import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;
import com.naikara_talk.paypal.PaypalFunctions;
import com.naikara_talk.sessiondata.SessionPurchasePoints;
import com.naikara_talk.sessiondata.SessionPayPal;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.PointExpirationDto;
import com.naikara_talk.dto.PaypalReturnDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.PointExpirationLogic;
import com.naikara_talk.logic.PurchasePointsLogic;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称 :</b>お客様利用画面<br>
 * <b>クラス名称       :</b>ポイント購入ページ決済<br>
 * <b>クラス概要       :</b>ポイント購入ページ決済Service<br>
 * <br>
 * <b>著作権           :</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/24 TECS 新規作成
 */
public class PurchasePointsPaymentService implements ActionService {

	/* チェック：エラーなし */
	public static final int RESULT_OK = 0;
	/* チェック：一覧部の項目が選択なし */
	public static final int RESULT_NG = 1;

	/**
	 * 確認画面へ遷移する制御処理<br>
	 * <br>
	 * @param Point Code
	 * @return result:結果
	 * @throws NaiException
	 */
	public PaypalReturnDto paypalOrderConfirm() throws NaiException {

		// ペイパル購入決済結果戻り値DTO
		PaypalReturnDto prDto = new PaypalReturnDto();

		// 検索Key：選択された明細のKey-ポイントコード
		String pointCd = "";
		// 金額
		BigDecimal moneyYen = BigDecimal.ZERO;
		// 通常月謝区分
		String feeKbn = "";
		// 有償ポイント
		BigDecimal paymentPoint = BigDecimal.ZERO;
		// 有償ポイント期限
		int paymentPointTim = 0;
		// 無償ポイント
		BigDecimal freePoint = BigDecimal.ZERO;
		// 無償ポイント期限
		int freePointTim = 0;
		// ペイパルトークン
		String paypalToken = "";
		// PayerID
		String payerId = "";
		// 商品説明
		String goodsDescription = "";
		// プロファイルID：必要
		String profileId = "";

		if (((SessionPurchasePoints) SessionDataUtil.
				getSessionData(SessionPurchasePoints.class.toString())) != null) {
			// 検索Key：選択された明細のKey-ポイントコード
			pointCd = ((SessionPurchasePoints) SessionDataUtil.
					getSessionData(SessionPurchasePoints.class.toString())).getSelectPointCd();
			// 金額
			moneyYen = ((SessionPurchasePoints) SessionDataUtil.
					getSessionData(SessionPurchasePoints.class.toString())).getMoneyYen();
			// 通常月謝区分
			feeKbn = ((SessionPurchasePoints) SessionDataUtil.
					getSessionData(SessionPurchasePoints.class.toString())).getFeeKbn();
			// 有償ポイント
			paymentPoint = ((SessionPurchasePoints) SessionDataUtil.
					getSessionData(SessionPurchasePoints.class.toString())).getPaymentPoint();
			// 有償ポイント期限
			paymentPointTim = ((SessionPurchasePoints) SessionDataUtil.
					getSessionData(SessionPurchasePoints.class.toString())).getPaymentPointTim();
			// 無償ポイント
			freePoint = ((SessionPurchasePoints) SessionDataUtil.
					getSessionData(SessionPurchasePoints.class.toString())).getFreePoint();
			// 無償ポイント期限
			freePointTim = ((SessionPurchasePoints) SessionDataUtil.
					getSessionData(SessionPurchasePoints.class.toString())).getFreePointTim();
			// ペイパルトークン
			paypalToken = ((SessionPayPal) SessionDataUtil.
					getSessionData(SessionPayPal.class.toString())).getPaypalToken();
			// PayerID
			payerId = ((SessionPayPal) SessionDataUtil.
					getSessionData(SessionPayPal.class.toString())).getPayerId();
			// 商品説明
			goodsDescription = ((SessionPayPal) SessionDataUtil.
					getSessionData(SessionPayPal.class.toString())).getGoodsDescription();
			// プロファイルID：必要
			profileId = ((SessionPayPal) SessionDataUtil.
					getSessionData(SessionPayPal.class.toString())).getProfileId();
		}

		StringBuffer sb = new StringBuffer();
		PaypalFunctions ppf = new PaypalFunctions();

		HashMap nvp = ppf.getExpressCheckoutDetails(paypalToken);

		if (nvp == null) {
			prDto.setReturnCode(RESULT_NG);
			prDto.setErrorMessage(NaikaraTalkConstants.BRANK);
			return prDto;
		}

		String strAck = nvp.get("ACK").toString();
		if (strAck == null) {
			prDto.setReturnCode(RESULT_NG);
			prDto.setErrorMessage(NaikaraTalkConstants.BRANK);
			return prDto;
		}

		if (!strAck.equalsIgnoreCase("Success") && !strAck.equalsIgnoreCase("SuccessWithWarning")) {
			prDto.setReturnCode(RESULT_NG);
			sb = new StringBuffer();
			sb.append(nvp.get("L_ERRORCODE0"));
			sb.append(NaikaraTalkConstants.HALF_SPACE);
			sb.append(nvp.get("L_LONGMESSAGE0"));
			prDto.setErrorMessage(sb.toString());
			return prDto;
		}

		nvp = ppf.doExpressCheckoutPayment(paypalToken, payerId, moneyYen.toString());

		if (nvp == null) {
			prDto.setReturnCode(RESULT_NG);
			prDto.setErrorMessage(NaikaraTalkConstants.BRANK);
			return prDto;
		}

		strAck = nvp.get("ACK").toString();
		if (strAck == null) {
			prDto.setReturnCode(RESULT_NG);
			prDto.setErrorMessage(NaikaraTalkConstants.BRANK);
			return prDto;
		}

		if (!strAck.equalsIgnoreCase("Success") && !strAck.equalsIgnoreCase("SuccessWithWarning")) {
			prDto.setReturnCode(RESULT_NG);
			sb = new StringBuffer();
			sb.append(nvp.get("L_ERRORCODE0"));
			sb.append(NaikaraTalkConstants.HALF_SPACE);
			sb.append(nvp.get("L_LONGMESSAGE0"));
			prDto.setErrorMessage(sb.toString());
			return prDto;
		}

		if (StringUtils.equals(feeKbn, NaikaraTalkConstants.FEE_KBN_MONTHLY)) {
			String strDate = DateUtil.getSysDate();
			PointExpirationLogic pxLogic = new PointExpirationLogic();
			PointExpirationDto pxDto = pxLogic.monthCalculation(strDate, 1);
			if(pxDto.getReturnCode()==NaikaraTalkConstants.RETURN_CD_DATA_YES) {
				strDate = NaikaraStringUtil.addDateHyphen(pxDto.getAge()) + "T00:00:00Z";  //  "2013-09-01T00:00:00Z" 形式
			}
			nvp = ppf.createRecurringPaymentsProfile(paypalToken, moneyYen.toString(), strDate, goodsDescription);

			if (nvp == null) {
				prDto.setReturnCode(RESULT_NG);
				prDto.setErrorMessage(NaikaraTalkConstants.BRANK);
				return prDto;
			}

			strAck = nvp.get("ACK").toString();
			if (strAck == null) {
				prDto.setReturnCode(RESULT_NG);
				prDto.setErrorMessage(NaikaraTalkConstants.BRANK);
				return prDto;
			}

			if (!strAck.equalsIgnoreCase("Success") && !strAck.equalsIgnoreCase("SuccessWithWarning")) {
				prDto.setReturnCode(RESULT_NG);
				sb = new StringBuffer();
				sb.append(nvp.get("L_ERRORCODE0"));
				sb.append(NaikaraTalkConstants.HALF_SPACE);
				sb.append(nvp.get("L_LONGMESSAGE0"));
				prDto.setErrorMessage(sb.toString());
				return prDto;
			}

			profileId = nvp.get("PROFILEID").toString();
			if (profileId == null) {
				prDto.setReturnCode(RESULT_NG);
				prDto.setErrorMessage(NaikaraTalkConstants.BRANK);
				return prDto;
			}
		}

		SessionPurchasePoints sessionPurchasePoints = new SessionPurchasePoints();
		sessionPurchasePoints.setReturnOnFlg(true);
		sessionPurchasePoints.setSelectPointCd(pointCd);
		sessionPurchasePoints.setMoneyYen(moneyYen);
		sessionPurchasePoints.setFeeKbn(feeKbn);
		sessionPurchasePoints.setPaymentPoint(paymentPoint);
		sessionPurchasePoints.setPaymentPointTim(paymentPointTim);
		sessionPurchasePoints.setFreePoint(freePoint);
		sessionPurchasePoints.setFreePointTim(freePointTim);
		SessionDataUtil.setSessionData(sessionPurchasePoints);

		SessionPayPal sessionPayPal = new SessionPayPal();
		sessionPayPal.setGoodsDescription(goodsDescription);
		sessionPayPal.setPaypalToken(paypalToken);
		sessionPayPal.setPayerId(payerId);
		sessionPayPal.setProfileId(profileId);
		SessionDataUtil.setSessionData(sessionPayPal);

		prDto.setReturnCode(RESULT_OK);
		prDto.setErrorMessage(NaikaraTalkConstants.BRANK);

		return prDto;
	}

    /**
	 * ポイント所有テーブル登録<br>
	 * <br>
	 * @param -
	 * @return result:結果
	 * @throws NaiException
	 */
	public int pointRegister() throws NaiException {

		Connection conn = null;

		try{
			conn = DbUtil.getConnection();

			PurchasePointsLogic ppLogic = new PurchasePointsLogic(conn);

			int result = RESULT_OK;

			// ポイント所有テーブル登録
			result = ppLogic.pointOwnershipTrnInsert();
			if(result == RESULT_NG) {
				conn.rollback();
				conn.close();
				return result;
		}

		conn.commit();

		return RESULT_OK;

		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
				throw new NaiException(e1);
			}
			throw new NaiException(e);
		} finally {
			try {
				conn.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
    }

}