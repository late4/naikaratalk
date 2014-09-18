package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dao.SalesManagedDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.SalesManagedDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>入金管理ページ詳細<br>
 * <b>クラス名称　　　:</b>入金管理ページ詳細クラス。<br>
 * <b>クラス概要　　　:</b>入金管理ページ詳細Logic。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/09 TECS 新規作成。
 */

public class SalesManagedLogic {

	// コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public SalesManagedLogic(Connection con) {
        this.conn = con;
    }

    /**
     * コード管理マスタキャッシュからデータ取得処理。<br>
     * <br>
     * コード管理マスタキャッシュからデータ取得処理。<br>
     * <br>
     * @param String 汎用コード<br>
     * @return LinkedHashMap<String, String><br>
     * @exception NaiException
     */
    public LinkedHashMap<String, String> selectCodeMst(String category) throws NaiException {

        LinkedHashMap<String, String> hashMap = new LinkedHashMap<String, String>();

        CodeManagMstCache cache = CodeManagMstCache.getInstance();
        LinkedHashMap<String, CodeManagMstDto> list = cache.getList(category);

        Iterator<String> iter = list.keySet().iterator();
        while (iter.hasNext()) {
            Object key = iter.next();
            CodeManagMstDto dto = list.get(key);
            hashMap.put(dto.getManagerCd(), dto.getManagerNm());
        }

        return hashMap;
    }

    /**
	 * データ(明細)を取得する
	 * @param objectYyyyMm 年月
	 * @param studentOrganizationId 受講者ID／組織ID
	 * @param costomerKbn 顧客区分
	 * @return list
	 * @throws NaiException
	 */
	public List<SalesManagedDto> searchDetail(SalesManagedDto dto) throws NaiException {

        // DAOの初期化
		SalesManagedDao dao = new SalesManagedDao(this.conn);

		ConditionMapper  cm = new ConditionMapper();
		OrderCondition  oc = new OrderCondition();

		// 年月
		cm.addCondition(SalesManagedDao.COND_YYYY_MM, ConditionMapper.OPERATOR_EQUAL, dto.getObjectYyyyMm());

		if (StringUtils.equals(NaikaraTalkConstants.CUSTOMER_KBN_ORGANIZATION, dto.getCostomerKbn())) {

			// 法人の場合
			// 組織ID
			cm.addCondition(SalesManagedDao.COND_ORGANIZATION_ID, ConditionMapper.OPERATOR_EQUAL, dto.getStudentOrganizationId());

		} else if (StringUtils.equals(NaikaraTalkConstants.CUSTOMER_KBN_PERSON, dto.getCostomerKbn())) {

			// 個人の場合
			// 受講者ID
			cm.addCondition(SalesManagedDao.COND_OSTUDENT_ID, ConditionMapper.OPERATOR_EQUAL, dto.getStudentOrganizationId());

		}

		// 並び順を設定

		// 受講者IDの昇順
		oc.addCondition(SalesManagedDao.COND_STUDENT_ID, OrderCondition.ASC);
		// 購入日／利用日の降順
		oc.addCondition(SalesManagedDao.COND_PURCHASE_USE_DT, OrderCondition.DESC);
		// レッスン時刻の降順
		oc.addCondition(SalesManagedDao.COND_LESSON_TM_NM, OrderCondition.DESC);
		// 購入ID の 降順
		oc.addCondition(SalesManagedDao.COND_PURCHASE_ID, OrderCondition.DESC);

		return dao.searchDetail(cm, oc, dto.getCostomerKbn());
    }

}
