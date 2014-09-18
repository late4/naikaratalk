package com.naikara_talk.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.sql.Connection;

import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;
import com.naikara_talk.paypal.PaypalFunctions;
import com.naikara_talk.sessiondata.SessionPurchaseGoods;
import com.naikara_talk.sessiondata.SessionPayPal;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.LessonReserveCancelResultListDto;
import com.naikara_talk.dto.PointExpirationDto;
import com.naikara_talk.dto.PaypalReturnDto;
import com.naikara_talk.dto.PointProvisionDataListDto;
import com.naikara_talk.dto.PointReleaseDataListDto;
import com.naikara_talk.dto.PurchaseGoodsPointPaymentDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.PointExpirationLogic;
import com.naikara_talk.logic.PointProvisionReleaseLogic;
import com.naikara_talk.logic.PurchaseGoodsLogic;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称 :</b>お客様利用画面<br>
 * <b>クラス名称       :</b>商品購入ページペイパル決済<br>
 * <b>クラス概要       :</b>商品購入ページペイパル決済Service<br>
 * <br>
 * <b>著作権           :</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/16 TECS 新規作成
 */
public class PurchaseGoodsPaypalPaymentService implements ActionService {

    /* チェック：エラーなし */
    public static final int RESULT_OK = 0;
    /* チェック：一覧部の項目が選択なし */
    public static final int RESULT_NG = 1;

	/**
	 * ペイパル決済処理<br>
	 * <br>
	 * @param Point Code
	 * @return result:結果
     * @throws NaiException
	 */
    public PaypalReturnDto paypalOrderConfirm() throws NaiException {

		// ペイパル購入決済結果戻り値DTO
		PaypalReturnDto prDto = new PaypalReturnDto();

		// ペイパルトークン
        String token = "";
        // PayerID
        String payerId = "";
        // 利用ポイント
        String amount = "";

        if (((SessionPayPal) SessionDataUtil.getSessionData(SessionPayPal.class.toString())) != null) {
    		// ペイパルトークン
            token = ((SessionPayPal) SessionDataUtil.
            		getSessionData(SessionPayPal.class.toString())).getPaypalToken();
            // PayerID
            payerId = ((SessionPayPal) SessionDataUtil.
            		getSessionData(SessionPayPal.class.toString())).getPayerId();
            // 利用ポイント
            amount = ((SessionPayPal) SessionDataUtil.
            		getSessionData(SessionPayPal.class.toString())).getProfileId();
        }

        StringBuffer sb = new StringBuffer();
    	PaypalFunctions ppf = new PaypalFunctions();

    	HashMap nvp = ppf.getExpressCheckoutDetails(token);

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

    	nvp = ppf.doExpressCheckoutPayment(token, payerId, amount);

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

        prDto.setReturnCode(RESULT_OK);
		prDto.setErrorMessage(NaikaraTalkConstants.BRANK);

        return prDto;
    }

	/**
	 * 商品購入テーブル登録処理<br>
	 * <br>
	 * @param PurchaseGoodsPointPaymentDto
	 * @return int
	 * @exception Exception
	 */
	public int goodsPurchaseRegister(String studentId, String[] goodsCd) throws Exception {

		Connection conn = null;

    	try {
			// 商品コード確認
			if (goodsCd.length == 0) {
				return RESULT_NG;
			}

			conn = DbUtil.getConnection();    // DBの接続

			// ポイント所有テーブルの更新
			PurchaseGoodsLogic pgLogic = new PurchaseGoodsLogic(conn);

			// 商品購入テーブルの登録
			if (!pgLogic.paypalPurchaseGoodsInsert(studentId, goodsCd)) {
				if (conn != null) {
					conn.rollback();
					conn.close();
				}
				return RESULT_NG;
			}

			if (conn != null) {
				conn.commit();
				conn.close();
			}

			// 正常
			return RESULT_OK;

		} catch (Exception e) {
			try {
				if (conn != null) {
					conn.rollback();
					conn.close();
				}
			} catch (Exception e1) {
				e1.printStackTrace();
				throw new NaiException(e1);
			}
			throw new NaiException(e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}
}