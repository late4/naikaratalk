package com.naikara_talk.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;

import com.naikara_talk.model.PurchasePointsModel;
import com.naikara_talk.service.PurchasePointsService;

import com.naikara_talk.sessiondata.SessionPurchaseGoods;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.sessiondata.SessionPurchasePoints;
import com.naikara_talk.sessiondata.SessionPayPal;

import com.naikara_talk.util.SessionDataUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様利用画面<br>
 * <b>クラス名称       :</b>ポイント購入ページ<br>
 * <b>クラス概要       :</b>ポイント購入ページ初期処理Action<br>
 * <br>
 * <b>著作権           :</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/19 TECS 新規作成
 */
public class PurchasePointsLoadAction extends PurchasePointsActionSupport {

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

		PurchasePointsService service = new PurchasePointsService();

		// ユーザIDのチェック
		if (StringUtils.isEmpty(((SessionUser) SessionDataUtil.getSessionData
				(SessionUser.class.toString())).getUserId())) {
			return ACCESS_DENIED;
		}

		// 実行判定
		String strStudentId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();
		if (!service.execCheck(strStudentId)) {
			return ACCESS_DENIED;
		}

		// ポイント管理マスタ取得
		PurchasePointsModel ppMdl;
		try {
			ppMdl = service.load(NaikaraTalkConstants.BRANK);
			if(ppMdl.getMonthlyList().size()==0 && ppMdl.getNormalList().size()==0) {
				this.message = getMessage("IC0017", new String[] { "現在、購入出来るポイントがありません。" });
			}
		} catch (Exception e) {
			throw new NaiException(e);
		}

		// 月謝（定額制）ポイント一覧セット
		this.model.setMonthlyList(ppMdl.getMonthlyList());

		// 通常ポイント一覧セット
		this.model.setNormalList(ppMdl.getNormalList());

		// 選択ラジオボタンのクリア
		this.select_rdl = NaikaraTalkConstants.BRANK;

		// 購入確認ページから戻るで戻った場合
		if ((SessionPurchasePoints) SessionDataUtil.getSessionData
				(SessionPurchasePoints.class.toString()) != null) {
			if (((SessionPurchasePoints) SessionDataUtil.getSessionData
					(SessionPurchasePoints.class.toString())).getReturnOnFlg()) {
				// SessionPurchasePointsのポイントコードをセット
				this.setSelect_rdl(((SessionPurchasePoints) SessionDataUtil.getSessionData
						(SessionPurchasePoints.class.toString())).getSelectPointCd());
			}
		}

		// ユーザＩＤセット
		this.setUserId(((SessionUser) SessionDataUtil.getSessionData
				(SessionUser.class.toString())).getUserId());

		// メッセージがセットされていれば表示
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

		// SessionPurchasePointsのクリア
		SessionDataUtil.clearSessionData(SessionPurchasePoints.class.toString());

		// SessionPayPalのクリア
		SessionDataUtil.clearSessionData(SessionPayPal.class.toString());

		// 画面を返す
		return SUCCESS;

	}

}
