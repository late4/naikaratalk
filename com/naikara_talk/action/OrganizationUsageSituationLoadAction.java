package com.naikara_talk.action;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.service.OrganizationUsageSituationLoadService;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>利用状況照会詳細。<br>
 * <b>クラス名称　　　:</b>利用状況照会詳細。<br>
 * <b>クラス概要　　　:</b>利用状況照会詳細LoadAction。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴　　　　:</b>2013/08/07 TECS 新規作成。
 */

public class OrganizationUsageSituationLoadAction extends OrganizationUsageSituationActionSupport {

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

		// 前画面の情報が引継
		String split[] = this.select_rdl.split("#");
		this.studentPosition = split[0];              // 受講者所属部署
		this.positionOrganizationId = split[1];       // 所属組織内ID
		this.studentId = split[2];                    // 受講者ID
		this.familyFirstJnm = split[3];               // 受講者名
		// 受講者名 フリガナ
		if (split.length > 4) {
			StringBuffer sb = new StringBuffer();
			this.familyFirstKnm = sb.append("(").append(split[4]).append(")").toString();
		} else {
			this.familyFirstKnm = NaikaraTalkConstants.BRANK;
		}


		// 画面のパラメータをモデルにセット
		setupModel();

		OrganizationUsageSituationLoadService service = new OrganizationUsageSituationLoadService();

		// 利用状況照会詳細のデータを取得
		this.model.setResultList(service.search(model));

		// 画面を返す
		return SUCCESS;
	}

}
