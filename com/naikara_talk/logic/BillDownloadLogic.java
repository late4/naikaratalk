package com.naikara_talk.logic;

import java.io.InputStream;
import java.sql.Connection;
import java.util.List;

import com.naikara_talk.dao.BillDownloadDao;
import com.naikara_talk.dto.ApplicationsDto;
import com.naikara_talk.exception.NaiException;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>会社側_管理帳票<br>
 * <b>クラス名称       :</b>請求書のダウンロードLogicクラス<br>
 * <b>クラス概要       :</b>請求書のダウンロードLogic<br>
 * <br>
 * <b>著作権           :</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author MIRA
 * <b>変更履歴         :</b>2013/09/08 MIRA 新規作成
 */
public class BillDownloadLogic {

    // コネクション変数
    public Connection conn = null;

    // 請求書作成DAO
	private BillDownloadDao dao;

    // コンストラクタ
    public BillDownloadLogic(Connection con) {
        this.conn = con;
		this.dao = new BillDownloadDao(conn);
    }

	/**
	 * DBからZIPファイルを取得<br>
	 * <br>
	 * 請求書格納テーブルからデータを取得する<br>
	 * <br>
	 * @param String
	 * @return InputStream
	 * @throws NaiException
	 */
	public InputStream getApplications(String sBatchMonthly) throws NaiException {

		ApplicationsDto dto = new ApplicationsDto();
		InputStream is;
		// 更新項目の設定
		dto.setBillIssueYm(sBatchMonthly);
		dto.setApplications(null);
		this.dao.selectApplications(dto);
		is = dto.getApplications();

		return is;
	}

	/**
	 * DBからZIPファイルを取得<br>
	 * <br>
	 * 請求書格納テーブルからデータを取得する<br>
	 * <br>
	 * @return List<String>
	 * @throws NaiException
	 */
	public List<String> getBillIssueYyyymm() throws NaiException {
		List<String> retList;
		ApplicationsDto dto = new ApplicationsDto();
		// 更新項目の設定
		retList = this.dao.getBillIssueYyyymm(dto);

		return retList;
	}
}
