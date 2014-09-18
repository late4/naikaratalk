package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import com.naikara_talk.dao.CustomerManagedDao;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.CustomerManagedDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様管理ページ詳細<br>
 * <b>クラス名称　　　:</b>お客様管理ページ詳細クラス。<br>
 * <b>クラス概要　　　:</b>お客様管理ページ詳細Logic。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/20 TECS 新規作成。
 */

public class CustomerManagedLogic {

	// コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public CustomerManagedLogic(Connection con) {
        this.conn = con;
    }

    /**
	 * データを取得する
	 * @param dto お客様管理ページDTO
	 * @return list
	 * @throws NaiException
	 */
	public List<CustomerManagedDto> search(CustomerManagedDto dto) throws NaiException {

        // DAOの初期化
		CustomerManagedDao dao = new CustomerManagedDao(this.conn);

		// データ(集計)を取得する
		List<CustomerManagedDto> list = dao.search(dto);
		if(NaikaraTalkConstants.RETURN_CD_DATA_YES == list.get(0).getReturnCode()){
			return list;
		} else {
			return null;
		}

    }

	/**
     * コード管理マスタキャッシュからデータ取得処理。<br>
     * <br>
     * @param String
     *            汎用コード
     * @return LinkedHashMap<String, String>
     * @throws NaiException
	 */
    public LinkedHashMap<String, String> selectCodeMst(String category) throws NaiException {

       	LinkedHashMap<String, String> hashMap = new LinkedHashMap<String, String>();

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

        return hashMap;
    }

}
