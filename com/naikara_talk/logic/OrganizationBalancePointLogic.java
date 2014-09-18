package com.naikara_talk.logic;

import java.sql.Connection;
import java.text.SimpleDateFormat;

import com.naikara_talk.dao.OrganizationBalancePointDao;
import com.naikara_talk.dto.OrginationBalancePointDto;
import com.naikara_talk.exception.NaiException;


/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DAOクラス<br>
 * <b>クラス名称　　　:</b>組織別未使用ポイント算出処理クラス<br>
 * <b>クラス概要　　　:</b>組織別未使用ポイント算出処理DAO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/05/28 TECS 新規作成
 */
public class OrganizationBalancePointLogic {

	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public OrganizationBalancePointLogic(Connection con) {
		this.conn = con;
	}

	/**
	 * 組織別未使用ポイント算出処理<br>
	 * <br>
	 * 組織別の残高ポイントを集計して、返却する<br>
	 * <br>
	 * @param organizationId       組織ID
	 * @param endDate              契約終了日
	 * @return BigDecimal:SumPoint 組織別の残高ポイント
	 * @throws NaiException
	 */
	public OrginationBalancePointDto getBalancePoint(String organizationId,String endDate) throws NaiException {

		//戻り値とリターンコードの初期化
		OrginationBalancePointDto dto=null;


		//引数のパラメータチェック
		//組織IDの必須チェック
		if(organizationId==null || "".equals(organizationId)){
			dto=new OrginationBalancePointDto();
			dto.setReturnCode(1);
			return dto;
		}
		//契約終了日の書式チェック
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		sdf.setLenient(false);
		try {
			sdf.parse(endDate);
		} catch (Exception e) {
			e.printStackTrace();
			dto=new OrginationBalancePointDto();
			dto.setReturnCode(1);
			return dto;
		}

		// データ取得処理
		OrganizationBalancePointDao dao = new OrganizationBalancePointDao(this.conn);
		dto = dao.search(organizationId, endDate);

        // 戻り値
        return dto;


	}
}