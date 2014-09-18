package com.naikara_talk.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.PurchaseGoodsModel;
import com.naikara_talk.service.PurchaseGoodsConfirmationLoadService;
import com.naikara_talk.sessiondata.ScreenComInfo;
import com.naikara_talk.sessiondata.SessionPurchaseGoods;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様利用画面<br>
 * <b>クラス名称       :</b>商品購入確認ページ初期処理Actionクラス<br>
 * <b>クラス概要       :</b>商品購入確認ページ初期処理Action<br>
 * <br>
 * <b>著作権           :</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/14 TECS 新規作成
 */
public class PurchaseGoodsConfirmationLoadAction extends PurchaseGoodsActionSupport {

	private static final long serialVersionUID = 1L;

	/**
	 * 画面の初期表示。<br>
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

		// 販売商品マスタ設定
		PurchaseGoodsConfirmationLoadService pgclSrvc = new PurchaseGoodsConfirmationLoadService();

		try {
			PurchaseGoodsModel pgModel = pgclSrvc.load();

			// 購入商品一覧
			this.model.setResultList(pgModel.getResultList());

			// 購入商品コード
			this.setGoodsCd(pgModel.getGoodsCd());

			// ポイント残高
			this.setPointBalance(pgModel.getPointBalance());

			// 利用ポイント
			this.setUsePointTotal(pgModel.getUsePointTotal());

			// ポイント残高(BigDecimal)
			this.setPointBalanceB(pgModel.getPointBalanceB());

			// 利用ポイント(BigDecimal)
			this.setUsePointTotalB(pgModel.getUsePointTotalB());

		} catch (Exception e) {
			throw new NaiException(e);
		}

		if (!StringUtils.isEmpty(this.message)) {
			this.addActionMessage(this.message);
		}

		// 戻るアクションリストが設定されていない場合、マイページに戻る
		List<String> actionList = new ArrayList<String>();
		if (this.session.containsKey("actionList")) {
			actionList = (List<String>) this.session.get("actionList");
			if (actionList.size() < 1) {
				SessionDataUtil.clearSessionData(SessionPurchaseGoods.class.toString());
				setCurrentActionName(NaikaraTalkConstants.STUDENT_MY_PAGE_LOAD);
			}
		} else {
			SessionDataUtil.clearSessionData(SessionPurchaseGoods.class.toString());
			setCurrentActionName(NaikaraTalkConstants.STUDENT_MY_PAGE_LOAD);
		}

		// 画面を返す
		return SUCCESS;

	}

}