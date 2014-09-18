package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dao.SalesManagedListDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.SalesManagedListDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>入金管理ページ<br>
 * <b>クラス名称　　　:</b>入金管理ページクラス。<br>
 * <b>クラス概要　　　:</b>入金管理ページlogic。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/09 TECS 新規作成。
 */

public class SalesManagedListLogic {

	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public SalesManagedListLogic(Connection con) {
		this.conn = con;
	}

	/**
	 * コード管理マスタキャッシュからデータ取得処理。<br>
	 * <br>
     * @param String 汎用コード
     * @return LinkedHashMap<String, String>
     * @throws NaiException
	 */
    public LinkedHashMap<String, String> selectCodeMst(String category) throws NaiException {

       	LinkedHashMap<String, String> hashMap = new LinkedHashMap<String, String>();

       	// インスタンスを取得
       	CodeManagMstCache cache = CodeManagMstCache.getInstance();
       	LinkedHashMap<String, CodeManagMstDto> list = cache.getList(category);

       	// 全てを設定
       	hashMap.put(NaikaraTalkConstants.CHOICE_ALL_ZERO, NaikaraTalkConstants.CHOICE_ALL);

       	// 顧客区分を設定
       	Iterator<String> iter = list.keySet().iterator();
       	while (iter.hasNext()) {
	       	Object key = iter.next();
	       	CodeManagMstDto dto = list.get(key);
	       	hashMap.put(dto.getManagerCd(),dto.getManagerNm());
       	}

       	// 顧客区分を返す
        return hashMap;
    }

    /**
	 * データ(集計)件数を取得
	 * @param dto 入金管理ページDTO
	 * @return int
	 * @throws NaiException
	 */
	public int getRowCount(SalesManagedListDto dto) throws NaiException {

        // DAOの初期化
		SalesManagedListDao dao = new SalesManagedListDao(this.conn);

        // データ件数を取得
		int dataCount = NaikaraTalkConstants.RETURN_CD_DATA_ERR; // データ件数

		dataCount = dao.rowCount(dto);

        // 戻り値
        return dataCount;
    }

	/**
	 * データ(集計)を取得する
	 * @param dto 入金管理ページDTO
	 * @return list
	 * @throws NaiException
	 */
	public List<SalesManagedListDto> search(SalesManagedListDto dto) throws NaiException {

        // DAOの初期化
		SalesManagedListDao dao = new SalesManagedListDao(this.conn);
		ArrayList<SalesManagedListDto> retList = new ArrayList<SalesManagedListDto>();

		// データ(集計)を取得する
		List<SalesManagedListDto> list = dao.search(dto);
		SalesManagedListDto retdto;

		// 表示データの付帯情報の取得と設定
		for (int i = 0; i < list.size(); i++) {
			retdto = list.get(i);

			// RadioValueを設定
			StringBuffer sb = new StringBuffer();
			sb.append(retdto.getCostomerKbn()).append("#")
			.append(retdto.getStudentOrganizationId()).append("#")
			.append(retdto.getStudentOrganizationJnm()).append("#")
			.append(retdto.getCompensationBeforePoint()).append("#")
			.append(retdto.getCompensationPurchasePoint()).append("#")
			.append(retdto.getFreeUsePoint()).append("#")
			.append(retdto.getCompensationUsePoint());
			retdto.setRadioValue(sb.toString());
			retList.add(retdto);
		}

		return retList;
    }

	/**
	 * データ(明細)を取得する
	 * @param objectYyyyMm 年月
	 * @param studentOrganizationId 受講者ID／組織ID
	 * @param costomerKbn 顧客区分
	 * @return list
	 * @throws NaiException
	 */
	public List<SalesManagedListDto> searchDetail(String objectYyyyMm,
			String studentOrganizationId, String costomerKbn) throws NaiException {

        // DAOの初期化
		SalesManagedListDao dao = new SalesManagedListDao(this.conn);

		ConditionMapper  cm = new ConditionMapper();
		OrderCondition  oc = new OrderCondition();

		// 年月
		cm.addCondition(SalesManagedListDao.COND_YYYY_MM, ConditionMapper.OPERATOR_EQUAL, objectYyyyMm);

		if (StringUtils.equals(NaikaraTalkConstants.CUSTOMER_KBN_ORGANIZATION, costomerKbn)) {

			// 法人の場合
			// 組織ID
			cm.addCondition(SalesManagedListDao.COND_ORGANIZATION_ID, ConditionMapper.OPERATOR_EQUAL, studentOrganizationId);

		} else if (StringUtils.equals(NaikaraTalkConstants.CUSTOMER_KBN_PERSON, costomerKbn)) {

			// 個人の場合
			// 受講者ID
			cm.addCondition(SalesManagedListDao.COND_OSTUDENT_ID, ConditionMapper.OPERATOR_EQUAL, studentOrganizationId);

		}

		// 並び順を設定

		// 受講者IDの昇順
		oc.addCondition(SalesManagedListDao.COND_STUDENT_ID, OrderCondition.ASC);
		// 購入日／利用日の降順
		oc.addCondition(SalesManagedListDao.COND_PURCHASE_USE_DT, OrderCondition.DESC);
		// レッスン時刻の降順
		oc.addCondition(SalesManagedListDao.COND_LESSON_TM_NM, OrderCondition.DESC);
		// 購入ID の 降順
		oc.addCondition(SalesManagedListDao.COND_PURCHASE_ID, OrderCondition.DESC);

		// データ(明細)を取得する
		return dao.searchDetail(cm, oc);
    }

}
