package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.OrganizationContractListDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.OrganizationContractListLogic;
import com.naikara_talk.model.OrganizationPointAssignmentListModel;
import com.naikara_talk.util.DateUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>ポイント割振り<br>
 * <b>クラス名称　　　:</b>ポイント割振り初期処理クラス。<br>
 * <b>クラス概要　　　:</b>ポイント割振り初期処理Service。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴　　　　:</b>2013/07/17 TECS 新規作成。
 */
public class OrganizationPointAssignmentListLoadService implements ActionService {
	/**
	 * 組織契約期間の一覧取得。
	 * @param model ポイント割振り初期処理Model
	 * @return list
	 * @throws Exception
	 */
	public ArrayList<OrganizationContractListDto> getContractList(
			OrganizationPointAssignmentListModel model) throws NaiException {

		// コネクション変数
		Connection conn = null;

		try {
			// コネクションを取得
			conn = DbUtil.getConnection();

			OrganizationContractListLogic organizationContractListLogic = new OrganizationContractListLogic(conn);
			// サーバーのシステム日付を取得
			String sysDate = DateUtil.getSysDate();

			// 組織契約期間の一覧取得
			return organizationContractListLogic.getContractList(sysDate, model.getOrganizationId());

		} catch ( SQLException se ) {
			se.printStackTrace();
			throw new NaiException(se);
		} finally {
			try {
				conn.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}

}
