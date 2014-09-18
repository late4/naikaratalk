package com.naikara_talk.logic;

import java.util.Iterator;
import java.util.LinkedHashMap;

import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>共通<br>
 * <b>クラス名称       :</b>コード管理マスタキャッシュ取得<br>
 * <b>クラス概要       :</b>コード管理マスタキャッシュ取得Logic<br>
 * <br>
 * <b>著作権           :</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/09 TECS 新規作成
 */
public class CodeManagMstLogic {

	/**
	 * コード管理マスタキャッシュからデータ取得処理。<br>
	 * <br>
	 * コード管理マスタキャッシュからデータ取得処理。<br>
	 * <br>
	 * @param String 汎用コード<br>
	 * @return LinkedHashMap<String, String><br>
	 * @exception NaiException
	 */
	public LinkedHashMap<String, String> selectCdMst(String category) throws NaiException {

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

}