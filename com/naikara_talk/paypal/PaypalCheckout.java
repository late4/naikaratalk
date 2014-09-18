package com.naikara_talk.paypal;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.naikara_talk.batch.SendMailBatch;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.sessiondata.SessionPayPal;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様利用画面<br>
 * <b>クラス名称       :</b>ペイパルログイン画面呼出し<br>
 * <b>クラス概要       :</b>ペイパルログイン画面呼出し処理<br>
 * <br>
 * <b>著作権           :</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/19 TECS 新規作成。
 */
public class PaypalCheckout extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

        // ログのの初期化
        Logger log = Logger.getLogger(PaypalCheckout.class);

        if (log.isDebugEnabled()) {
            log.debug("PaypalCheckout doGet Start");
        }

		try {
			String strUrl = "";
			CodeManagMstCache cache = CodeManagMstCache.getInstance();
			if (StringUtils.equals(cache.decode(NaikaraTalkConstants.CODE_CATEGORY_PAYPAL_INFO,
					NaikaraTalkConstants.PAYPAL_ENV), NaikaraTalkConstants.PAYPAL_ENV_L)) {

	            if (log.isDebugEnabled()) {
	                log.debug("PaypalCheckout Paypal-Live-Server");
	            }

				// 本番環境の場合
				strUrl = cache.decode(NaikaraTalkConstants.CODE_CATEGORY_PAYPAL_INFO,
						NaikaraTalkConstants.PAYPAL_LOGIN_URL);

				if (log.isDebugEnabled()) {
	                log.debug("PaypalCheckout Paypal-Live-Server strUrl=" + strUrl);
	            }

			} else {

	            if (log.isDebugEnabled()) {
	                log.debug("PaypalCheckout Paypal-Sandbox-Server");
	            }

		        // テスト環境の場合
				strUrl = cache.decode(NaikaraTalkConstants.CODE_CATEGORY_PAYPAL_INFO,
						NaikaraTalkConstants.PAYPAL_LOGIN_URL_T);

				if (log.isDebugEnabled()) {
	                log.debug("PaypalCheckout Paypal-Sandbox-Server strUrl=" + strUrl);
	            }


			}

			String token = ((SessionPayPal) SessionDataUtil.getSessionData(SessionPayPal.class.toString())).getPaypalToken();

            if (log.isDebugEnabled()) {
                log.debug("token=" + token);
            }


			// ' Redirect to paypal.com
			// String redirectURL = "https://www.sandbox.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token="
			// + token + "&locale.x=ja_JP";
			// String redirectURL = "https://www.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token="
			// + token + "&locale.x=ja_JP";
			String redirectURL = strUrl + token + "&locale.x=ja_JP";

            if (log.isDebugEnabled()) {
                log.debug("response.sendRedirect before redirectURL=" + redirectURL);
            }

			response.sendRedirect(redirectURL);

            if (log.isDebugEnabled()) {
                log.debug("response.sendRedirect after redirectURL=" + redirectURL);
            }


	        if (log.isDebugEnabled()) {
	            log.debug("PaypalCheckout doGet End");
	        }

		} catch (NaiException e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}