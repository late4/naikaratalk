package com.naikara_talk.paypal;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.naikara_talk.batch.SendMailBatch;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称 :</b>ペイパルＡＰＩ<br>
 * <b>クラス名称       :</b>ペイパルＡＰＩ<br>
 * <b>クラス概要       :</b>ペイパルＡＰＩ<br>
 * <br>
 * <b>著作権           :</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/01 TECS 新規作成
 */

public class PaypalFunctions {

    // ログのの初期化
    Logger log = Logger.getLogger(PaypalFunctions.class);

	private String gv_APIUserName;
	private String gv_APIPassword;
	private String gv_APISignature;

	private String gv_APIEndpoint;
	private String gv_BNCode;

	private String gv_Version;
	private String gv_nvpHeader;
	private String gv_ProxyServer;
	private String gv_ProxyServerPort;
	private int gv_Proxy;
	private boolean gv_UseProxy;
	private String PAYPAL_URL;

	public PaypalFunctions() {

        if (log.isDebugEnabled()) {
            log.debug("PaypalFunctions PaypalFunctions Start");
        }

		boolean bSandbox = true;
		try {
			CodeManagMstCache cache = CodeManagMstCache.getInstance();

			if (StringUtils.equals(cache.decode(NaikaraTalkConstants.CODE_CATEGORY_PAYPAL_INFO,
					NaikaraTalkConstants.PAYPAL_ENV), NaikaraTalkConstants.PAYPAL_ENV_L)) {
				bSandbox = false;
			}

	        if (log.isDebugEnabled()) {
	            log.debug("PaypalFunctions PaypalFunctions bSandbox=" + bSandbox);
	        }

		} catch (NaiException e) {
			e.printStackTrace();
		}

		/*
		 * Servers for NVP API
		 * Sandbox: https://api-3t.sandbox.paypal.com/nvp
		 * Live: https://api-3t.paypal.com/nvp
		 *
		 * Redirect URLs for PayPal Login Screen
		 * Sandbox:https://www.sandbox.paypal.com/webscr&cmd=_express-checkout&token=XXXX
		 * Live:https://www.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token=XXXX
		 */
		String PAYPAL_DG_URL = "";

        if (log.isDebugEnabled()) {
            log.debug("bSandbox=" + bSandbox);
        }

		if (bSandbox) {
			gv_APIEndpoint = "https://api-3t.sandbox.paypal.com/nvp";
			PAYPAL_URL = "https://www.sandbox.paypal.com/webscr?cmd=_express-checkout&token=";
			PAYPAL_DG_URL = "https://www.sandbox.paypal.com/incontext?token=";
		} else {
			gv_APIEndpoint = "https://api-3t.paypal.com/nvp";
			PAYPAL_URL = "https://www.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token=";
			PAYPAL_DG_URL = "https://www.paypal.com/incontext?token=";
		}

       if (log.isDebugEnabled()) {
            log.debug("gv_APIEndpoint=" + gv_APIEndpoint);
            log.debug("PAYPAL_URL=" + PAYPAL_URL);
            log.debug("PAYPAL_DG_URL=" + PAYPAL_DG_URL);
        }

		String HTTPREQUEST_PROXYSETTING_SERVER = "";
		String HTTPREQUEST_PROXYSETTING_PORT = "";
		boolean USE_PROXY = false;

		gv_Version = "98";

		// WinObjHttp Request proxy settings.
		gv_ProxyServer = HTTPREQUEST_PROXYSETTING_SERVER;
		gv_ProxyServerPort = HTTPREQUEST_PROXYSETTING_PORT;
		gv_Proxy = 2; // 'setting for proxy activation
		gv_UseProxy = USE_PROXY;

        if (log.isDebugEnabled()) {
            log.debug("PaypalFunctions PaypalFunctions End");
        }

	}

	/*********************************************************************************
	 * SetExpressCheckout: Function to perform the SetExpressCheckout API call
	 *
	 * Inputs: paymentAmount: Total value of the purchase currencyCodeType:
	 * Currency code value the PayPal API paymentType: 'Sale' for Digital Goods
	 * returnURL: the page where buyers return to after they are done with the
	 * payment review on PayPal cancelURL: the page where buyers return to when
	 * they cancel the payment review on PayPal
	 *
	 * Output: Returns a HashMap object containing the response from the server.
	 *********************************************************************************/
	public HashMap setExpressCheckout(String paymentAmount, String returnURL,
			String cancelURL, String description) {

		String nvpstr = "&RETURNURL=" + URLEncoder.encode(returnURL)
				+ "&CANCELURL=" + URLEncoder.encode(cancelURL)
				+ "&AMT=" + paymentAmount
				+ "&CURRENCYCODE=" + NaikaraTalkConstants.CURRENCY_CODE_TYPE_JPY
				+ "&BILLINGTYPE=" + NaikaraTalkConstants.BILLING_TYPE_RP
				+ "&BILLINGAGREEMENTDESCRIPTION=" + URLEncoder.encode(description);

	       if (log.isDebugEnabled()) {
	            log.debug("setExpressCheckout nvpstr=" + nvpstr);
	        }


		HashMap nvp = httpcall("SetExpressCheckout", nvpstr);

		return nvp;
	}

	/*********************************************************************************
	 * GetShippingDetails: Function to perform the GetExpressCheckoutDetails API
	 * call
	 *
	 * Inputs: None
	 *
	 * Output: Returns a HashMap object containing the response from the server.
	 *********************************************************************************/
	public HashMap getExpressCheckoutDetails(String token) {

		String nvpstr = "&TOKEN=" + token;

       if (log.isDebugEnabled()) {
            log.debug("getExpressCheckoutDetails nvpstr=" + nvpstr);
        }

		HashMap nvp = httpcall("GetExpressCheckoutDetails", nvpstr);

		return nvp;
	}

	/*********************************************************************************
	 * ConfirmPayment: Function to perform the DoExpressCheckoutPayment API call
	 *
	 * Inputs: None
	 *
	 * Output: Returns a HashMap object containing the response from the server.
	 *********************************************************************************/
	public HashMap doExpressCheckoutPayment(String token, String payerID, String finalPaymentAmount) {

		String nvpstr = "&TOKEN=" + token + "&PAYERID=" + payerID
			  + "&AMT=" + finalPaymentAmount
			  + "&PAYMENTACTION=" + NaikaraTalkConstants.PAYMENT_ACTION_SALE
			  + "&CURRENCYCODE=" + NaikaraTalkConstants.CURRENCY_CODE_TYPE_JPY;

       if (log.isDebugEnabled()) {
            log.debug("doExpressCheckoutPayment nvpstr=" + nvpstr);
        }

		HashMap nvp = httpcall("DoExpressCheckoutPayment", nvpstr);

		return nvp;

	}

	/*********************************************************************************
	 * ConfirmPayment: Function to perform the DoExpressCheckoutPayment API call
	 *
	 * Inputs: None
	 *
	 * Output: Returns a HashMap object containing the response from the server.
	 *********************************************************************************/
	public HashMap createRecurringPaymentsProfile(String token, String finalPaymentAmount,
			String profileStartDate, String description) {

		String nvpstr = "&TOKEN=" + token
			  + "&AMT=" + finalPaymentAmount
			  + "&PROFILESTARTDATE=" + profileStartDate
			  + "&BILLINGPERIOD=" + NaikaraTalkConstants.BILLING_PERIOD_MONTH
			  + "&BILLINGFREQUENCY=" + NaikaraTalkConstants.BILLING_FREQUENCY_ONE
			  + "&CURRENCYCODE=" + NaikaraTalkConstants.CURRENCY_CODE_TYPE_JPY
			  + "&TOTALBILLINGCYCLES=" + NaikaraTalkConstants.TOTAL_BILLING_CYCLES_LIMITLESS
			  + "&DESC=" + URLEncoder.encode(description);

       if (log.isDebugEnabled()) {
            log.debug("createRecurringPaymentsProfile nvpstr=" + nvpstr);
        }

		HashMap nvp = httpcall("CreateRecurringPaymentsProfile", nvpstr);

		return nvp;

	}

	/*********************************************************************************
	 * ConfirmPayment: Function to perform the DoExpressCheckoutPayment API call
	 *
	 * Inputs: None
	 *
	 * Output: Returns a HashMap object containing the response from the server.
	 *********************************************************************************/
	public HashMap getRecurringPaymentsProfileDetails(String profileId) {

		String nvpstr = "&PROFILEID=" + profileId;

       if (log.isDebugEnabled()) {
            log.debug("getRecurringPaymentsProfileDetails nvpstr=" + nvpstr);
        }

		HashMap nvp = httpcall("GetRecurringPaymentsProfileDetails", nvpstr);

		return nvp;

	}

	/*********************************************************************************
	 * ConfirmPayment: Function to perform the DoExpressCheckoutPayment API call
	 *
	 * Inputs: None
	 *
	 * Output: Returns a HashMap object containing the response from the server.
	 *********************************************************************************/
	public HashMap manageRecurringPaymentsProfileStatus(String profileId, String note) {

		String nvpstr = "&PROFILEID=" + profileId
				+ "&ACTION=" + NaikaraTalkConstants.ACTION_CANCEL
				+ "&NOTE=" + note;

       if (log.isDebugEnabled()) {
            log.debug("manageRecurringPaymentsProfileStatus nvpstr=" + nvpstr);
        }

		HashMap nvp = httpcall("ManageRecurringPaymentsProfileStatus", nvpstr);

		return nvp;

	}

	/*********************************************************************************
	 * httpcall: Function to perform the API call to PayPal using API signature @
	 * methodName is name of API method. @ nvpStr is nvp string. returns a NVP
	 * string containing the response from the server.
	 *********************************************************************************/
	public HashMap httpcall(String methodName, String nvpStr) {

		String version = "2.3";
		String agent = "Mozilla/5.0";
		String respText = "";
		HashMap nvp = null; // lhuynh not used?

		try {
			CodeManagMstCache cache = CodeManagMstCache.getInstance();
			if (StringUtils.equals(cache.decode(NaikaraTalkConstants.CODE_CATEGORY_PAYPAL_INFO,
					NaikaraTalkConstants.PAYPAL_ENV), NaikaraTalkConstants.PAYPAL_ENV_L)) {

		       if (log.isDebugEnabled()) {
		            log.debug("httpcall LIVE-Server");
		        }

				// LIVE
				gv_APIUserName = cache.decode(NaikaraTalkConstants.CODE_CATEGORY_PAYPAL_INFO,
						NaikaraTalkConstants.PAYPAL_API_USER);
				gv_APIPassword = cache.decode(NaikaraTalkConstants.CODE_CATEGORY_PAYPAL_INFO,
						NaikaraTalkConstants.PAYPAL_API_PASSWORD);
				gv_APISignature = cache.decode(NaikaraTalkConstants.CODE_CATEGORY_PAYPAL_INFO,
						NaikaraTalkConstants.PAYPAL_API_SIGNATURE);
			} else {

		       if (log.isDebugEnabled()) {
		            log.debug("httpcall sandbox-Server");
		        }

				// sandbox
				gv_APIUserName = cache.decode(NaikaraTalkConstants.CODE_CATEGORY_PAYPAL_INFO,
						NaikaraTalkConstants.PAYPAL_API_USER_T);
				gv_APIPassword = cache.decode(NaikaraTalkConstants.CODE_CATEGORY_PAYPAL_INFO,
						NaikaraTalkConstants.PAYPAL_API_PASSWORD_T);
				gv_APISignature = cache.decode(NaikaraTalkConstants.CODE_CATEGORY_PAYPAL_INFO,
						NaikaraTalkConstants.PAYPAL_API_SIGNATURE_T);
			}

	       if (log.isDebugEnabled()) {
	            log.debug("httpcall gv_APIUserName=" + gv_APIUserName);
	            log.debug("httpcall gv_APIPassword=" + gv_APIPassword);
	            log.debug("httpcall gv_APISignature=" + gv_APISignature);
	        }

		} catch (NaiException e1) {
			e1.printStackTrace();

			if (log.isDebugEnabled()) {
	            log.debug("httpcall NaiException" + e1.getMessage());
	        }

			return null;
		}

		// deformatNVP( nvpStr );
		String encodedData = "METHOD=" + methodName
				+ "&VERSION=" + gv_Version
				+ "&PWD=" + gv_APIPassword
				+ "&USER=" + gv_APIUserName
				+ "&SIGNATURE=" + gv_APISignature
				+ nvpStr;

		if (log.isDebugEnabled()) {
            log.debug("httpcall encodedData" + encodedData);
        }

		try {

			// プロキシサーバ設定 - Start
			// プロキシサーバ設定 プロキシサーバ無い場合設定必要ない 2013/06/11
			//System.setProperty("https.proxySet", "true");
			//System.setProperty("https.proxyHost", "192.168.1.55");
			//System.setProperty("https.proxyPort", "8080");
			// プロキシサーバ設定 - End


			URL postURL = new URL(gv_APIEndpoint);
			HttpURLConnection conn = (HttpURLConnection) postURL
					.openConnection();

			if (log.isDebugEnabled()) {
	            log.debug("httpcall postURL" + postURL.toString());
	        }

			// Set Timeouts Appropriately as per the App needs add by lich
			conn.setConnectTimeout(60000);
			conn.setReadTimeout(60000);
			// add end

			// Set connection parameters. We need to perform input and output,
			// so set both as true.
			conn.setDoInput(true);
			conn.setDoOutput(true);

			// Set the content type we are POSTing. We impersonate it as
			// encoded form data
			conn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			conn.setRequestProperty("User-Agent", agent);

			// conn.setRequestProperty( "Content-Type", type );
			conn.setRequestProperty("Content-Length",
					String.valueOf(encodedData.length()));
			conn.setRequestMethod("POST");

			// get the output stream to POST to.
			DataOutputStream output = new DataOutputStream(
					conn.getOutputStream());
			output.writeBytes(encodedData);
			output.flush();
			output.close();

			// Read input from the input stream.
			DataInputStream in = new DataInputStream(conn.getInputStream());
			int rc = conn.getResponseCode();
			if (rc != -1) {
				BufferedReader is = new BufferedReader(new InputStreamReader(
						conn.getInputStream()));
				String _line = null;
				while (((_line = is.readLine()) != null)) {
					respText = respText + _line;
				}
				nvp = deformatNVP(respText);
			}
			return nvp;
		} catch (IOException e) {
			// handle the error here

			if (log.isDebugEnabled()) {
	            log.debug("httpcall IOException" + e.getMessage());
	        }

			return null;
		}
	}

	/*********************************************************************************
	 * deformatNVP: Function to break the NVP string into a HashMap pPayLoad is
	 * the NVP string. returns a HashMap object containing all the name value
	 * pairs of the string.
	 *********************************************************************************/
	public HashMap deformatNVP(String pPayload) {
		HashMap nvp = new HashMap();

		if (log.isDebugEnabled()) {
            log.debug("deformatNVP Start");
        }

		StringTokenizer stTok = new StringTokenizer(pPayload, "&");
		while (stTok.hasMoreTokens()) {
			StringTokenizer stInternalTokenizer = new StringTokenizer(
					stTok.nextToken(), "=");
			if (stInternalTokenizer.countTokens() == 2) {

				if (log.isDebugEnabled()) {
		            log.debug("deformatNVP stInternalTokenizer.countTokens() == 2");
		        }

				String key = URLDecoder.decode(stInternalTokenizer.nextToken());
				String value = URLDecoder.decode(stInternalTokenizer.nextToken());
				nvp.put(key.toUpperCase(), value);
			}
		}

		if (log.isDebugEnabled()) {
            log.debug("deformatNVP End");
        }

		return nvp;
	}

	/*********************************************************************************
	 * RedirectURL: Function to redirect the user to the PayPal site token is
	 * the parameter that was returned by PayPal returns a HashMap object
	 * containing all the name value pairs of the string.
	 *********************************************************************************/
	public void RedirectURL(HttpServletResponse response, String token) {
		String payPalURL = PAYPAL_URL + token;

		if (log.isDebugEnabled()) {
            log.debug("RedirectURL Start");
        }

		// response.sendRedirect( payPalURL );
		response.setStatus(302);
		response.setHeader("Location", payPalURL);
		response.setHeader("Connection", "close");

		if (log.isDebugEnabled()) {
            log.debug("RedirectURL Location payPalURL=" + payPalURL);
        }

		if (log.isDebugEnabled()) {
            log.debug("RedirectURL End");
        }

	}

	// end class
}
