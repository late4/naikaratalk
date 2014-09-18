package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dto.PurchaseGoodsPointPaymentDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

import com.naikara_talk.sessiondata.ScreenComInfo;
import com.naikara_talk.sessiondata.SessionPayPal;
import com.naikara_talk.sessiondata.SessionPurchaseGoods;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.service.PurchaseGoodsPointPaymentService;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称 :</b>お客様利用画面<br>
 * <b>クラス名称       :</b>商品購入確認ページポイント決済<br>
 * <b>クラス概要       :</b>商品購入確認ページポイント決済Action<br>
 * <br>
 * <b>著作権           :</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/14 TECS 新規作成
 */
public class PurchaseGoodsPointPaymentAction extends PurchaseGoodsActionSupport {

	private static final long serialVersionUID = 1L;

    /* マイページへ遷移 */
    public static final String MYPAGE = "mypage";

    /**
	 * ポイントでお支払いボタンの処理。<br>
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

		// 商品購入ポイント決済Service
		PurchaseGoodsPointPaymentService pgppSrvc = new PurchaseGoodsPointPaymentService();
		PurchaseGoodsPointPaymentDto pgppDto = new PurchaseGoodsPointPaymentDto();

		try {
			// 受講者ＩＤ、商品コード、利用ポイントのセット
			pgppDto.setStudentId(((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId());
			pgppDto.setGoodsCd(this.goodsCd);
			pgppDto.setUsePoint(this.bdUsePoint);

			// エラー発生時の同一画面での商品表示用
			SessionPurchaseGoods sessionData = new SessionPurchaseGoods();
			if ((SessionPurchaseGoods) SessionDataUtil.getSessionData(SessionPurchaseGoods.class.toString()) == null) {
				sessionData.setSelect_chk(this.goodsCd);
				SessionDataUtil.setSessionData(sessionData);
			}

			// ポイント所有テーブル更新チェック
			switch(pgppSrvc.pointProvision(pgppDto)){

				// 結果NG：商品コード確認エラー
				case PurchaseGoodsPointPaymentService.RESULT_GOODS_CODE_CONFIRM_ERR :
					this.message = getMessage("EC0042", new String[] { "購入商品の商品コード取得" });
					return SUCCESS;

				// 結果NG：利用ポイント確認エラー
				case PurchaseGoodsPointPaymentService.RESULT_USE_POINT_CONFIRM_ERR :
					this.message = getMessage("EC0042", new String[] { "購入商品の利用ポイント取得" });
					return SUCCESS;

				// 結果NG：商品コード、利用ポイント整合性エラー
				case PurchaseGoodsPointPaymentService.RESULT_GOODS_POINT_INTEGRITY_ERR :
					this.message = getMessage("EC0042", new String[] { "購入商品の商品コードと利用ポイントの取得" });
					return SUCCESS;

				// 結果NG：所有ポイント確認エラー
				case PurchaseGoodsPointPaymentService.RESULT_POINT_CHECK_ERR :
					this.message = getMessage("EC0042", new String[] { "所有ポイントの取得" });
					return SUCCESS;

				// 結果NG：所有ポイント更新エラー
				case PurchaseGoodsPointPaymentService.RESULT_POINT_UPDATE_ERR :
					this.message = getMessage("EC0042", new String[] { "所有ポイントの更新" });
					return SUCCESS;

				// 結果NG：商品購入テーブル登録エラー
				case PurchaseGoodsPointPaymentService.RESULT_GOODS_INSERT_ERR :
					this.message = getMessage("EC0042", new String[] { "商品購入テーブルの登録" });
					return SUCCESS;

				// 結果NG：受講者情報の不足エラー(メール送信時に必要な情報の不足)
				case PurchaseGoodsPointPaymentService.RESULT_STUDENT_DATA_SHORTAGE_ERR :
					this.message = getMessage("EC0076", new String[] {""});
					return SUCCESS;
			}

			this.message = getMessage("IC0014", new String[] { "商品購入" });

			// ヘッダの戻るリンク用
			SessionDataUtil.clearSessionData(SessionPurchaseGoods.class.toString());
			setCurrentActionName(NaikaraTalkConstants.STUDENT_MY_PAGE_LOAD);

			// SessionPayPalのクリア
			SessionDataUtil.clearSessionData(SessionPayPal.class.toString());

		} catch (Exception e) {
			throw new NaiException(e);
		}

		// 商品購入ページ一覧に戻る
		return NEXTPAGE;

	}

}