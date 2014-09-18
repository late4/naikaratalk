package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dao.CustomerManagedListDao;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.CustomerManagedListDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様管理ページ<br>
 * <b>クラス名称　　　:</b>お客様管理ページクラス。<br>
 * <b>クラス概要　　　:</b>お客様管理ページLogic。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/20 TECS 新規作成。
 */

public class CustomerManagedListLogic {

    // コネクション変数
    public Connection conn = null;

    // コンストラクタ
    public CustomerManagedListLogic(Connection con) {
        this.conn = con;
    }

    /**
	 * データ件数を取得
	 * @param dto お客様管理ページDTO
	 * @return int
	 * @throws NaiException
	 */
	public int getRowCount(CustomerManagedListDto dto) throws NaiException {

        // DAOの初期化
		CustomerManagedListDao dao = new CustomerManagedListDao(this.conn);

        // データ件数を取得
		int dataCount = NaikaraTalkConstants.RETURN_CD_DATA_ERR; // データ件数

		dataCount = dao.rowCount(dto);

        // 戻り値
        return dataCount;
    }

	/**
	 * データを取得する
	 * @param dto お客様管理ページDTO
	 * @return list
	 * @throws NaiException
	 */
	public List<CustomerManagedListDto> search(CustomerManagedListDto dto) throws NaiException {

        // DAOの初期化
		CustomerManagedListDao dao = new CustomerManagedListDao(this.conn);
		ArrayList<CustomerManagedListDto> retList = new ArrayList<CustomerManagedListDto>();

		// データ(集計)を取得する
		List<CustomerManagedListDto> list = dao.search(dto);
		CustomerManagedListDto retdto;

		// 表示データの付帯情報の取得と設定
		for (int i = 0; i < list.size(); i++) {
			retdto = list.get(i);

			// 共通部品：名前の編集(名前(姓)、名前(名))
			StringBuffer sb = new StringBuffer();
			if(StringUtils.isEmpty(retdto.getFamilyFirstJnm())) {
				retdto.setFamilyFirstJnm(NaikaraStringUtil.unionName(retdto.getFamilyJnm(), retdto.getFirstJnm()));
			}
			// RadioValueを設定
			sb.append(retdto.getStudentId()).append("#")
			.append(retdto.getFamilyFirstJnm()).append("#")
			.append(retdto.getFamilyFirstKnm()).append("#")
			.append(retdto.getFamilyFirstRomaji()).append("#")
			.append(changeNull(retdto.getNickNm())).append("#")
			.append(changeNull(retdto.getMailAddress1())).append("#")
			.append(changeNull(retdto.getOrganizationJnm())).append("#")
			.append(retdto.getFreeUsePoint()).append("#")
			.append(retdto.getCompensationUsePoint()).append("#")
			.append(retdto.getGoodsPurchaseYen()).append("#")
			.append(retdto.getCostomerKbn());

			retdto.setRadioValue(sb.toString());
			retList.add(retdto);
		}

		return retList;
    }

	/**
     * null変換
     *
     * @param string str1 パラメータ
     * @return string 返却値
     */
    public static String changeNull(String str1) {

        if (StringUtils.isEmpty(str1)) {
            return "";
        }

        return str1;
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
