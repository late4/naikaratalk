package com.naikara_talk.action;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>ポイント割振り<br>
 * <b>クラス名称　　　:</b>ポイント割振りActionクラス。<br>
 * <b>クラス概要　　　:</b>ポイント割振り初期処理Action。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴　　　　:</b>2013/07/17 TECS 新規作成。
 */
public class OrganizationPointAssignmentListLoadAction extends OrganizationPointAssignmentListActionSupport {

	private static final long serialVersionUID = 1L;


	/**
	 * 画面の初期表示。<br>
	 * <br>
	 *
	 * @param なし
	 * @return String
	 * @throws NaiException
	 */
	public String requestService() throws NaiException {

		// 開始ログ
		log.info(NaikaraStringUtil.unionProcesslog("Start"));

		// ロゴイン情報の組織IDの取得
		this.organizationId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();

		// 画面のパラメータをモデルにセット
		setupModel();

		// 表示データの取得処理
		try {
			// 初期処理、表示データの取得
			load();
		} catch (Exception e) {
			throw new NaiException(e);
		}
		// 組織IDの設定
		this.setOrganizationId(model.getOrganizationId());

		if (!StringUtils.isEmpty(this.message)) {
            this.addActionMessage(this.message);
        }

		// 画面を返す
		return SUCCESS;

	}

}
