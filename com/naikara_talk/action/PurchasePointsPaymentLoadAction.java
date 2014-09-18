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
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様利用画面<br>
 * <b>クラス名称       :</b>ポイント購入確認ページ初期処理Actionクラス<br>
 * <b>クラス概要       :</b>ポイント購入確認ページ初期処理Action<br>
 * <br>
 * <b>著作権           :</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/22 TECS 新規作成
 */
public class PurchasePointsPaymentLoadAction extends PurchasePointsActionSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 画面の初期表示。<br>
     * <br>
     * @param なし
     * @return String
     * @throws NaiException
     */
    public String requestService() throws NaiException {

    	PurchasePointsService service = new PurchasePointsService();

		if ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString()) == null) {
			service.sessionUserReset(this.userId);
			this.select_rdl = this.pointCd;
	        // ヘッダの戻るリンク用（ペイパルログイン画面後、購入ポイント支払画面から戻る）
			setCurrentActionName(NaikaraTalkConstants.STUDENT_MY_PAGE_LOAD);
			setCurrentActionName(NaikaraTalkConstants.PURCHASE_POINTS_LOAD);
	        setCurrentActionName(NaikaraTalkConstants.PURCHASE_POINTS_CONFIRMATION_LOAD);
		}

		service.sessionPurchasePointsReset(this.pointCd, this.token, this.payerID);

        // 開始ログ
    	log.info(NaikaraStringUtil.unionProcesslog("Start"));

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

		// ポイント管理マスタ設定
		try {
			PurchasePointsModel ppMdl = service.load(this.pointCd);

			this.setUserId(this.userId);
			this.setPointCd(this.pointCd);
			this.model.setMonthlyList(ppMdl.getMonthlyList());
			this.model.setNormalList(ppMdl.getNormalList());
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
