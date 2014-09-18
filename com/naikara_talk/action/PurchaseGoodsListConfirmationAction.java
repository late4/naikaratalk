package com.naikara_talk.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dto.LessonReserveCancelResultListDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.GoodsListModel;
import com.naikara_talk.model.PurchaseGoodsListModel;
import com.naikara_talk.sessiondata.ScreenComInfo;
import com.naikara_talk.sessiondata.SessionPurchaseGoods;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様利用画面<br>
 * <b>クラス名称       :</b>商品購入ページ<br>
 * <b>クラス概要       :</b>商品購入ページ確認処理Action<br>
 * <br>
 * <b>著作権           :</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/13 TECS 新規作成
 *                     :</b>2014/02/18 TECS 商品購入ページからの呼出追加に伴う修正
 */
public class PurchaseGoodsListConfirmationAction extends PurchaseGoodsListActionSupport {

	private static final long serialVersionUID = 1L;

	/**
	 * 確認ボタンの処理。<br>
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
				if (!StringUtils.equals(action, NaikaraTalkConstants.PURCHASE_GOODS_LIST_SEARCH)) {
					setCurrentActionName(NaikaraTalkConstants.PURCHASE_GOODS_LIST_SEARCH);
				}
			}
		} else {
			SessionDataUtil.clearSessionData(SessionPurchaseGoods.class.toString());
			setCurrentActionName(NaikaraTalkConstants.STUDENT_MY_PAGE_LOAD);
		}

		try {

			// 検索ボタン押下時の講師への購入メール連絡付き区分の選択値をSessionから取得
			String tMailKbnS = NaikaraTalkConstants.BRANK;
			if ((SessionPurchaseGoods) SessionDataUtil.getSessionData(SessionPurchaseGoods.class.toString()) != null) {
				// 検索ボタン押下時の講師への購入メール連絡付き区分の選択値の取得
				tMailKbnS = ((SessionPurchaseGoods) SessionDataUtil.getSessionData
						(SessionPurchaseGoods.class.toString())).getTMailKbnS();
			}

			// 表示中の検索条件をSessionにセット
			this.modelAsToSessionPurchaseGoods();

			// 購入商品選択チェック
			if (ArrayUtils.isEmpty(this.select_chk)) {
				// 購入商品の未選択エラー
				this.setMessage(getMessage("EC0015", new String[] { "購入商品" }));
				return SUCCESS;
			} else {
				String[] sekectChk = this.select_chk;
				if (sekectChk == null) {
					// 購入商品の未選択エラー
					this.setMessage(getMessage("EC0015", new String[] { "購入商品" }));
					return SUCCESS;
				} else {
					boolean selectYesFlg = false;
					String sekectChkStr = NaikaraTalkConstants.BRANK;
					for (int i = 0, n = sekectChk.length; i < n; i++) {
						sekectChkStr = sekectChk[i].trim();
						if (sekectChkStr != null && !NaikaraTalkConstants.BRANK.equals(sekectChkStr)) {
							selectYesFlg = true;
							break;
						}
					}
					if (selectYesFlg == false) {
						// 購入商品の未選択エラー
						this.setMessage(getMessage("EC0015", new String[] { "購入商品" }));
						return SUCCESS;
					}
				}

				// <検索ボタン押下時の>講師への購入メール連絡付き区分=送信ありの場合
				StringBuffer wkMsg = new StringBuffer();
				if (StringUtils.equals(tMailKbnS, NaikaraTalkConstants.T_CONTACT_KBN_YES)) {
					for (int i = 0, n = sekectChk.length; i < n; i++) {
						if (sekectChk[i] == null || StringUtils.equals(NaikaraTalkConstants.BRANK, sekectChk[i].trim())) {
							continue;
						}

						/** 商品購入一覧 */
						GoodsListModel pgModel = new GoodsListModel();
						for (int j = 0, m = this.model.getResultList().size(); j < m; j++) {
							pgModel = this.model.getResultList().get(j);
							if (StringUtils.equals(sekectChk[i], pgModel.getGoodsCdcourseCd())) {
								// 選択されている商品の場合
								if (StringUtils.equals(NaikaraTalkConstants.BRANK, pgModel.getTeacherId()) ||
										pgModel.getTeacherChk() == true) {
									// 講師IDが設定されていない場合 又は 講師選択 削除の場合
									wkMsg.append("(商品名：").append(pgModel.getGoodsNm()).append(") 商品購入確認通知メールを送信する講師");
									this.setMessage(getMessage("EC0015", new String[] { wkMsg.toString() }));
									return SUCCESS;
								}
							}
						}
					}
				}
			}



		} catch (Exception e) {
			throw new NaiException(e);
		}

		// 詳細画面遷移
		return NEXTPAGE;
	}


	/**
	 * Model(ActionSuporrt)値・SessionPurchaseGoods値をSessionPurchaseGoodsにセット<br>
	 * <br>
	 * @throws Exception
	 */
	private void modelAsToSessionPurchaseGoods() throws Exception {

		SessionPurchaseGoods sessionData = new SessionPurchaseGoods();

		///////////////////////////////////////////////////////////////////////////////////////////////////////////
		String bigClassificationCdS = this.bigClassificationCd_sel;
		String middleClassificationCdS = this.middleClassificationCd_sel;
		String smallClassificationCdS = this.smallClassificationCd_sel;
		String courseJnmS = this.courseName_txt;
		String goodsNmS = this.goodsName_txt;
		String productKbnS = this.productKbn_rdl;
		String saleKbnS = this.saleKbn_rdl;
		String tMailKbnS = this.tMailKbn_rdl;
		///////////////////////////////////////////////////////////////////////////////////////////////////////////

		if ((SessionPurchaseGoods) SessionDataUtil.getSessionData(SessionPurchaseGoods.class.toString()) != null) {
			// 検索押下時の条件を取得
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

			// 2014/02/18 Add Start 商品購入ページからの呼出追加に伴う修正
			tMailKbnS = ((SessionPurchaseGoods) SessionDataUtil.getSessionData
					(SessionPurchaseGoods.class.toString())).getTMailKbnS();
			// 2014/02/18 Add End   商品購入ページからの呼出追加に伴う修正

        }

		sessionData.setBigClassificationCdS(bigClassificationCdS);
		sessionData.setMiddleClassificationCdS(middleClassificationCdS);
		sessionData.setSmallClassificationCdS(smallClassificationCdS);
		sessionData.setCourseJnmS(courseJnmS);
		sessionData.setGoodsNmS(goodsNmS);
		sessionData.setProductKbnS(productKbnS);
		sessionData.setSaleKbnS(saleKbnS);

		// 2014/02/18 Add Start 商品購入ページからの呼出追加に伴う修正
		sessionData.setTMailKbnS(tMailKbnS);
		// 2014/02/18 Add End   商品購入ページからの呼出追加に伴う修正

        sessionData.setReturnOnFlg(true);
		sessionData.setBigClassificationCdD(this.bigClassificationCd_sel);
		sessionData.setMiddleClassificationCdD(this.middleClassificationCd_sel);
		sessionData.setSmallClassificationCdD(this.smallClassificationCd_sel);
		sessionData.setCourseJnmD(this.courseName_txt);
		sessionData.setGoodsNmD(this.goodsName_txt);
		sessionData.setProductKbnD(this.productKbn_rdl);
		sessionData.setSaleKbnD(this.saleKbn_rdl);
		sessionData.setSelect_chk(this.select_chk);

		// 2014/02/18 Add Start 商品購入ページからの呼出追加に伴う修正
		sessionData.setResultList(this.model.getResultList());
		sessionData.setTMailKbnD(this.tMailKbn_rdl);
		// 2014/02/18 Add End   商品購入ページからの呼出追加に伴う修正

        SessionDataUtil.setSessionData(sessionData);

    }

}
