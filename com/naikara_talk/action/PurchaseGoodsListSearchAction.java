package com.naikara_talk.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.GoodsListModel;
import com.naikara_talk.model.PurchaseGoodsListModel;
import com.naikara_talk.service.PurchaseGoodsListSearchService;
import com.naikara_talk.sessiondata.ScreenComInfo;
import com.naikara_talk.sessiondata.SessionPurchaseGoods;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様利用画面<br>
 * <b>クラス名称       :</b>商品購入ページ<br>
 * <b>クラス概要       :</b>商品購入ページ検索処理Action<br>
 * <br>
 * <b>著作権           :</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/09 TECS 新規作成
 *                     :</b>2014/02/18 TECS 商品購入ページからの呼出追加に伴う修正

 */
public class PurchaseGoodsListSearchAction extends PurchaseGoodsListActionSupport {

	private static final long serialVersionUID = 1L;

	/**
	 * 検索処理。<br>
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

		// 戻るアクションリストが設定されていない場合、マイページに戻る
		List<String> actionList = new ArrayList<String>();
		String action = "";
		if (this.session.containsKey("actionList")) {
			actionList = (List<String>) this.session.get("actionList");
			if (actionList.size() < 1) {
				SessionDataUtil.clearSessionData(SessionPurchaseGoods.class.toString());
				setCurrentActionName(NaikaraTalkConstants.STUDENT_MY_PAGE_LOAD);
			} else {
				action = actionList.get(actionList.size() - 1);
				if (!StringUtils.equals(action, NaikaraTalkConstants.PURCHASE_GOODS_LIST_LOAD)) {
					setCurrentActionName(NaikaraTalkConstants.PURCHASE_GOODS_LIST_LOAD);
				}
			}
		} else {
			SessionDataUtil.clearSessionData(SessionPurchaseGoods.class.toString());
			setCurrentActionName(NaikaraTalkConstants.STUDENT_MY_PAGE_LOAD);
		}

		//Serviceクラス生成
		PurchaseGoodsListSearchService service = new PurchaseGoodsListSearchService();

		try {
			// 戻るボタン押下時、Session⇒画面（ActionSupport）又は、再表示
			this.SessionPurchaseGoodsToModelAs();

			// 一覧データのクリア
			List<GoodsListModel> dataList = null;
			this.model.setResultList(dataList);                                 // 2014/02/18 Add 商品購入ページからの呼出追加に伴う修正


			//////////////////////////////////////////////////////////////////////////////////////////
			boolean returnOnFlg = false;          // 戻る判定Onフラグ
			if ((SessionPurchaseGoods) SessionDataUtil.getSessionData(SessionPurchaseGoods.class.toString()) != null) {
				// 戻る判定Onフラグ
				returnOnFlg = ((SessionPurchaseGoods) SessionDataUtil.getSessionData
						(SessionPurchaseGoods.class.toString())).getReturnOnFlg();
				dataList =  ((SessionPurchaseGoods) SessionDataUtil.getSessionData
						(SessionPurchaseGoods.class.toString())).getResultList();

			}
			//////////////////////////////////////////////////////////////////////////////////////////


			if (returnOnFlg) {
				// 確認画面から戻ってきた場合は、保持していたリストを表示する
				this.model.setResultList(dataList);
			} else {
				// 入力条件Model設定
				PurchaseGoodsListModel pglModel = this.SessionPurchaseGoodsToModel();

				// err パターンもあるのでこのタイミングで「戻る判定Onフラグ」のクリア・session保持
				// 画面（ActionSupport）⇒Session
				this.ModelAsToSessionPurchaseGoods();

				// 検索前チェック
				switch (service.checkPreSelect(pglModel)) {
				case PurchaseGoodsListModel.ERR_ZERO_DATA:
					// 対象データZero件エラー
					////this.addActionMessage(getMessage("EN0020", new String[] { PurchaseGoodsListActionSupport.GOODS_MST_JNM, "" }));
					this.addActionMessage(getMessage("EC0020", new String[] { "" }));
					return SUCCESS;
				case PurchaseGoodsListModel.ERR_MAXOVER_DATA:
					// 対象データMax件以上エラー
					int MaxCnt = PurchaseGoodsListSearchService.LIST_MAX_CNT + 1;
					this.addActionMessage(getMessage("EC0023", new String[] { String.valueOf(MaxCnt) }));
					return SUCCESS;
				}

				// 表示データの取得と設定
				this.model.setResultList(service.selectList(pglModel));
				// 確認押下可否設定（選択可能明細がない場合、確認押下不可）
				this.model.setInitFlg(false);
				for (int i = 0; i < this.model.getResultList().size(); i++) {
					if (this.model.getResultList().get(0).isCheckboxFlg()) {
						this.model.setInitFlg(true);
					}
				}
			}


			// 画面（ActionSupport）⇒Session
			this.ModelAsToSessionPurchaseGoods();

			// メッセージがセットされていれば表示
			if (!StringUtils.isEmpty(this.message)) {
				this.addActionMessage(this.message);
			}

		} catch (Exception e) {
			throw new NaiException(e);
		}

		return SUCCESS;
	}

	/**
	 * 画面項目：検索値をSessionPurchaseGoodsにセット<br>
	 * <br>
	 * @throws Exception
	 */
	private void ModelAsToSessionPurchaseGoods() throws Exception {

		SessionPurchaseGoods sessionData = new SessionPurchaseGoods();

		boolean returnOnFlg = false;          // 戻る判定Onフラグ
		String bigClassificationCdD = "";     // コース名：大分類コード(表示用)
		String middleClassificationCdD = "";  // コース名：中分類コード(表示用)
		String smallClassificationCdD = "";   // コース名：小分類コード(表示用)
		String courseJnmD = "";               // コース名(表示用)
		String goodsNmD = "";                 // 商品名(表示用)
		String productKbnD = "";              // 商品形態(表示用)
		String saleKbnD = "";                 // 受取方法(表示用)

		//2014.02.18 Add Start 商品購入ページからの呼出追加に伴う修正
		String tMailKbnD = "";                // 講師への商品購入確認通知メール(表示用)
		//2014.02.18 Add End   商品購入ページからの呼出追加に伴う修正

		String bigClassificationCdS = "";     // コース名：大分類コード(検索用)
		String middleClassificationCdS = "";  // コース名：中分類コード(検索用)
		String smallClassificationCdS = "";   // コース名：小分類コード(検索用)
		String courseJnmS = "";               // コース名(検索用)
		String goodsNmS = "";                 // 商品名(検索用)
		String productKbnS = "";              // 商品形態(検索用)
		String saleKbnS = "";                 // 受取方法(検索用)

		//2014.02.18 Add Start 商品購入ページからの呼出追加に伴う修正
		String tMailKbnS = "";                                           // 講師への商品購入確認通知メール(表示用)
		//String winOpenTeacherListClickFlg = NaikaraTalkConstants.FALSE;  // 講師一覧が押下されたかどうかの判定
		//2014.02.18 Add End   商品購入ページからの呼出追加に伴う修正

		String[] selectChk = {""};            // 一覧から選択された明細


		if ((SessionPurchaseGoods) SessionDataUtil.getSessionData(SessionPurchaseGoods.class.toString()) != null) {
			returnOnFlg = ((SessionPurchaseGoods) SessionDataUtil.getSessionData
					(SessionPurchaseGoods.class.toString())).getReturnOnFlg();

			bigClassificationCdD = ((SessionPurchaseGoods) SessionDataUtil.getSessionData
					(SessionPurchaseGoods.class.toString())).getBigClassificationCdD();

			middleClassificationCdD = ((SessionPurchaseGoods) SessionDataUtil.getSessionData
					(SessionPurchaseGoods.class.toString())).getMiddleClassificationCdD();

			smallClassificationCdD = ((SessionPurchaseGoods) SessionDataUtil.getSessionData
					(SessionPurchaseGoods.class.toString())).getSmallClassificationCdD();

			courseJnmD = ((SessionPurchaseGoods) SessionDataUtil.getSessionData
					(SessionPurchaseGoods.class.toString())).getCourseJnmD();

			goodsNmD = ((SessionPurchaseGoods) SessionDataUtil.getSessionData
					(SessionPurchaseGoods.class.toString())).getGoodsNmD();

			productKbnD = ((SessionPurchaseGoods) SessionDataUtil.getSessionData
					(SessionPurchaseGoods.class.toString())).getProductKbnD();

			saleKbnD = ((SessionPurchaseGoods) SessionDataUtil.getSessionData
					(SessionPurchaseGoods.class.toString())).getSaleKbnD();

			//2014.02.18 Add Start 商品購入ページからの呼出追加に伴う修正
			tMailKbnD = ((SessionPurchaseGoods) SessionDataUtil.getSessionData
					(SessionPurchaseGoods.class.toString())).getTMailKbnD();
			//2014.02.18 Add Start 商品購入ページからの呼出追加に伴う修正


			bigClassificationCdS = ((SessionPurchaseGoods) SessionDataUtil.getSessionData
					(SessionPurchaseGoods.class.toString())).getBigClassificationCdS();

			middleClassificationCdS = ((SessionPurchaseGoods) SessionDataUtil.getSessionData
					(SessionPurchaseGoods.class.toString())).getMiddleClassificationCdS();

			smallClassificationCdS = ((SessionPurchaseGoods) SessionDataUtil.getSessionData
					(SessionPurchaseGoods.class.toString())).getSmallClassificationCdS();

			courseJnmS = ((SessionPurchaseGoods) SessionDataUtil.getSessionData
					(SessionPurchaseGoods.class.toString())).getCourseJnmS();

			goodsNmS = ((SessionPurchaseGoods) SessionDataUtil.getSessionData
					(SessionPurchaseGoods.class.toString())).getGoodsNmS();

			productKbnS = ((SessionPurchaseGoods) SessionDataUtil.getSessionData
					(SessionPurchaseGoods.class.toString())).getProductKbnS();

			saleKbnS = ((SessionPurchaseGoods) SessionDataUtil.getSessionData
					(SessionPurchaseGoods.class.toString())).getSaleKbnS();

			//2014.02.18 Add Start 商品購入ページからの呼出追加に伴う修正
			tMailKbnS = ((SessionPurchaseGoods) SessionDataUtil.getSessionData
					(SessionPurchaseGoods.class.toString())).getTMailKbnS();  // 講師への商品購入確認通知メール(表示用)
			//2014.02.18 Add End   商品購入ページからの呼出追加に伴う修正

			selectChk = ((SessionPurchaseGoods) SessionDataUtil.getSessionData
					(SessionPurchaseGoods.class.toString())).getSelectChk();


		}

		// returnOnFlg に true が設定されるのは、確認Actionクラス(ConfirmationAction)のみ
		if (returnOnFlg) {
			returnOnFlg = false;
		} else {
			bigClassificationCdD = this.bigClassificationCd_sel;
			middleClassificationCdD = this.middleClassificationCd_sel;
			smallClassificationCdD = this.smallClassificationCd_sel;
			courseJnmD = this.courseName_txt;
			goodsNmD = this.goodsName_txt;
			productKbnD = this.productKbn_rdl;
			saleKbnD = this.saleKbn_rdl;

			//2014.02.18 Add Start 商品購入ページからの呼出追加に伴う修正
			tMailKbnD = this.tMailKbn_rdl;
			//2014.02.18 Add Start 商品購入ページからの呼出追加に伴う修正

			bigClassificationCdS = this.bigClassificationCd_sel;
			middleClassificationCdS = this.middleClassificationCd_sel;
			smallClassificationCdS = this.smallClassificationCd_sel;
			courseJnmS = this.courseName_txt;
			goodsNmS = this.goodsName_txt;
			productKbnS = this.productKbn_rdl;
			saleKbnS = this.saleKbn_rdl;

			//2014.02.18 Add Start 商品購入ページからの呼出追加に伴う修正
			tMailKbnS = this.tMailKbn_rdl;
			//2014.02.18 Add Start 商品購入ページからの呼出追加に伴う修正

			selectChk = this.select_chk;

		}

		sessionData.setReturnOnFlg(returnOnFlg);
		sessionData.setBigClassificationCdD(bigClassificationCdD);
		sessionData.setMiddleClassificationCdD(middleClassificationCdD);
		sessionData.setSmallClassificationCdD(smallClassificationCdD);
		sessionData.setCourseJnmD(courseJnmD);
		sessionData.setGoodsNmD(goodsNmD);
		sessionData.setProductKbnD(productKbnD);
		sessionData.setSaleKbnD(saleKbnD);

		//2014.02.18 Add Start 商品購入ページからの呼出追加に伴う修正
		sessionData.setTMailKbnD(tMailKbnD);
		//2014.02.18 Add Start 商品購入ページからの呼出追加に伴う修正

		sessionData.setBigClassificationCdS(bigClassificationCdS);
		sessionData.setMiddleClassificationCdS(middleClassificationCdS);
		sessionData.setSmallClassificationCdS(smallClassificationCdS);
		sessionData.setCourseJnmS(courseJnmS);
		sessionData.setGoodsNmS(goodsNmS);
		sessionData.setProductKbnS(productKbnS);
		sessionData.setSaleKbnS(saleKbnS);

		//2014.02.18 Add Start 商品購入ページからの呼出追加に伴う修正
		sessionData.setTMailKbnS(tMailKbnS);
		//2014.02.18 Add Start 商品購入ページからの呼出追加に伴う修正


		sessionData.setSelect_chk(selectChk);

		SessionDataUtil.setSessionData(sessionData);

	}

}