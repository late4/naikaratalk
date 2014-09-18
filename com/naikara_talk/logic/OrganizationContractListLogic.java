package com.naikara_talk.logic;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.naikara_talk.dao.OrganizationContractListDao;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.OrganizationContractListDto;
import com.naikara_talk.dto.OrganizationMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraStringUtil;


/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 DAOクラス<br>
 * <b>クラス名称　　　:</b>組織契約期間の一覧取得クラス<br>
 * <b>クラス概要　　　:</b>組織契約期間の一覧取得DAO<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/05/28 TECS 新規作成
  */
public class OrganizationContractListLogic {

	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public OrganizationContractListLogic(Connection con) {
		this.conn = con;
	}

	/**
	 * 組織契約期間の一覧取得<br>
	 * <br>
	 * 組織契約期間をリストで返却する<br>
	 * <br>
	 * @param sysDate
	 * @param organizationId
	 * @return ArrayList<OrganizationContractListDto>
	 * @throws NaiException
	 */
	public ArrayList<OrganizationContractListDto> getContractList(String sysDate,String organizationId) throws NaiException {

		//戻り値とリターンコードの初期化
		ArrayList<OrganizationContractListDto> contractList = null; //組織契約期間リスト
		OrganizationContractListDto workDto=null;
		//int returnCode = 9; //リターンコード
    	String startDate;  // 契約開始日
    	String endDate;    // 契約終了日
		StringBuffer sbD = new StringBuffer();

		//引数のパラメータチェック
		//システム日付のyyyyMMdd書式チェック
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		sdf.setLenient(false);
		try {
			sdf.parse(sysDate);
		} catch (Exception e) {
			contractList=new ArrayList<OrganizationContractListDto>();
			workDto=new OrganizationContractListDto();
			workDto.setReturnCode(1);
			contractList.add(workDto);
			return contractList;
		}
		//組織IDの必須チェック
		if(organizationId==null || "".equals(organizationId)){
			contractList=new ArrayList<OrganizationContractListDto>();
			workDto=new OrganizationContractListDto();
			workDto.setReturnCode(1);
			contractList.add(workDto);
			return contractList;
		}


		OrganizationContractListDao dao = new OrganizationContractListDao(this.conn);

		OrderCondition orderBy = new OrderCondition();
		// 契約開始日の昇順
        orderBy.addCondition(OrganizationContractListDao.COND_CONTRACT_STR_DT, OrderCondition.ASC);
		// 契約終了日の昇順
        orderBy.addCondition(OrganizationContractListDao.COND_CONTRACT_END_DT, OrderCondition.ASC);

    	// データの取得
    	List<OrganizationMstDto> list = dao.search(sysDate, organizationId, orderBy);
    	OrganizationMstDto dto = new OrganizationMstDto();
		contractList=new ArrayList<OrganizationContractListDto>();  //返却用Dto：組織契約期間リスト

    	// データが存在しない場合
		if(list.size()==0){
			contractList=new ArrayList<OrganizationContractListDto>();
			workDto=new OrganizationContractListDto();
			workDto.setReturnCode(2);
			contractList.add(workDto);
			return contractList;
		}

    	// データが存在する場合
		for(int i=0;list.size()>i;i++){
			workDto=new OrganizationContractListDto();

			dto=list.get(i);
			startDate=dto.getContractStrDt();  // 契約開始日
			// 変数の初期化
			sbD.setLength(0);
			if (NaikaraStringUtil.nvlString(startDate).toString().length() == 8) {
				sbD = sbD.append(startDate.substring(0, 4)).append("/")
						.append(startDate.substring(4, 6)).append("/")
						.append(startDate.substring(6, 8));
			}
			startDate=sbD.toString();

			endDate=dto.getContractEndDt();  // 契約終了日
			// 変数の初期化
			sbD.setLength(0);
			if (NaikaraStringUtil.nvlString(endDate).toString().length() == 8) {
				sbD = sbD.append(endDate.substring(0, 4)).append("/")
						.append(endDate.substring(4, 6)).append("/")
						.append(endDate.substring(6, 8));
			}
			endDate=sbD.toString();

			// 変数の初期化
			sbD.setLength(0);
			// 契約開始日-契約終了日の編集
			sbD = sbD.append(startDate).append("-").append(endDate);

			// 返却Dtoへ値の設定
			workDto.setContract(sbD.toString());  // 契約期間
			workDto.setReturnCode(0);             // リターンコード

			contractList.add(workDto);
		}

		return contractList;

	}
}