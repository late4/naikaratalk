package com.naikara_talk.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;

import com.naikara_talk.model.PurchaseGoodsListModel;
import com.naikara_talk.service.PurchaseGoodsListLoadService;

import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.sessiondata.ScreenComInfo;
import com.naikara_talk.sessiondata.SessionPurchaseGoods;
import com.naikara_talk.sessiondata.SessionPayPal;

import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;
import com.naikara_talk.util.NaikaraStringUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様利用画面<br>
 * <b>クラス名称       :</b>商品購入ページ<br>
 * <b>クラス概要       :</b>商品購入ページ初期処理Action<br>
 * <br>
 * <b>著作権           :</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/09 TECS 新規作成
 *                     :</b>2014/02/18 TECS 商品購入ページからの呼出追加に伴う修正
 */
public class PurchaseGoodsListLoadAction extends PurchaseGoodsListActionSupport {

	private static final long serialVersionUID = 1L;

	/**
	 * 画面の初期表示。<br>
	 * <br>
	 * 画面の初期表示。<br>
	 * <br>
	 * @param なし<br>
	 * @return String SUCCESS <br>
	 * @exception NaiException
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

		// 検索条件値の取得
		try {
			initCond();
		} catch (Exception e) {
			throw new NaiException(e);
		}


		if (this.getWinOpenTeacherListClickFlg() == null || StringUtils.equals(this.getWinOpenTeacherListClickFlg(), NaikaraTalkConstants.FALSE)) {
			/** 画面の初期値の設定 */
			PurchaseGoodsListLoadService service = new PurchaseGoodsListLoadService();

 			// モデルを取得
			PurchaseGoodsListModel pglModel = service.initLoad();

			// コース名：大分類コード
			this.setBigClassificationCd_sel(pglModel.getBigClassificationCdS());
			this.setDefaultBigClassificationCd(pglModel.getBigClassificationCdS());

			// コース名：中分類コード
			this.setMiddleClassificationCd_sel(pglModel.getMiddleClassificationCdS());
			this.setDefaultMiddleClassificationCd(pglModel.getMiddleClassificationCdS());

			// コース名：小分類コード
			this.setSmallClassificationCd_sel(pglModel.getSmallClassificationCdS());
			this.setDefaultSmallClassificationCd(pglModel.getSmallClassificationCdS());

			// 商品形態
			this.setProductKbn_rdl(pglModel.getProductKbnS());

			// 受取方法
			this.setSaleKbn_rdl(pglModel.getSaleKbnS());

			//2014.02.18 Add Start 商品購入ページからの呼出追加に伴う修正
			// 講師への商品購入確認通知メール
			this.setTMailKbn_rdl(pglModel.getTMailKbnS());
		} else {
			/** (「講師の選択」ボタン押下時)現状の画面の値を保持 */
			// 判定フラグの初期化
			this.setWinOpenTeacherListClickFlg(NaikaraTalkConstants.FALSE);

			// コース名：大分類コード
			this.setDefaultBigClassificationCd(this.getBigClassificationCd_sel());

			// コース名：中分類コード
			this.setDefaultMiddleClassificationCd(this.getMiddleClassificationCd_sel());

			// コース名：小分類コード
			this.setDefaultSmallClassificationCd(this.getSmallClassificationCd_sel());

			// 商品形態
			this.setProductKbn_rdl(this.getProductKbn_rdl());

			// 受取方法
			this.setSaleKbn_rdl(this.getSaleKbn_rdl());

			// 講師への商品購入確認通知メール
			this.setTMailKbn_rdl(this.getTMailKbn_rdl());
		}


		// 講師一覧が押下されたかどうかの判定
		this.setWinOpenTeacherListClickFlg(NaikaraTalkConstants.FALSE);
		//2014.02.18 Add End   商品購入ページからの呼出追加に伴う修正


		// メッセージがセットされていれば表示
		if (!StringUtils.isEmpty(this.message)) {
			this.addActionMessage(this.message);
		}

		// SessionPurchaseGoodsのクリア
		SessionDataUtil.clearSessionData(SessionPurchaseGoods.class.toString());

		// SessionPayPalのクリア
		SessionDataUtil.clearSessionData(SessionPayPal.class.toString());

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
