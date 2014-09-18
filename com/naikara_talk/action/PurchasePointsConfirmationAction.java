package com.naikara_talk.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.PurchasePointsModel;
import com.naikara_talk.service.PurchasePointsService;
import com.naikara_talk.sessiondata.SessionPurchaseGoods;
import com.naikara_talk.sessiondata.SessionPurchasePoints;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称 :</b>お客様利用画面<br>
 * <b>クラス名称       :</b>ポイント購入ページ確認へ<br>
 * <b>クラス概要       :</b>ポイント購入ページ確認へAction<br>
 * <br>
 * <b>著作権           :</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/22 TECS 新規作成
 */
public class PurchasePointsConfirmationAction extends PurchasePointsActionSupport {

	private static final long serialVersionUID = 1L;

	/**
	 * 確認へボタンの処理。<br>
	 * <br>
	 * @param なし
	 * @return String
	 * @throws NaiException
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
				if (!StringUtils.equals(action, NaikaraTalkConstants.PURCHASE_POINTS_LOAD)) {
					setCurrentActionName(NaikaraTalkConstants.PURCHASE_POINTS_LOAD);
				}
			}
		} else {
			SessionDataUtil.clearSessionData(SessionPurchaseGoods.class.toString());
			setCurrentActionName(NaikaraTalkConstants.STUDENT_MY_PAGE_LOAD);
		}

		//エラーの場合、メッセージ設定
		if(StringUtils.isEmpty(this.getSelect_rdl())) {
			try {
				this.addActionMessage(getMessage("EN0015", new String[] { "金額（税込）" }));

				// ポイント管理マスタ設定
				PurchasePointsModel ppMdl = service.load(this.getSelect_rdl());
				this.model.setMonthlyList(ppMdl.getMonthlyList());
				this.model.setNormalList(ppMdl.getNormalList());

			} catch (Exception e) {
				throw new NaiException(e);
			}

			return SUCCESS;

		}

		// 戻る用に必要な情報を格納
		SessionPurchasePoints sessionData = new SessionPurchasePoints();
		sessionData.setReturnOnFlg(true);
		SessionDataUtil.setSessionData(sessionData);

		// 詳細画面遷移
		return NEXTPAGE;
	}

}
