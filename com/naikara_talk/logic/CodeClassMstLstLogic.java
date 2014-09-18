package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.List;

import com.naikara_talk.dao.CodeClassMstDao;
import com.naikara_talk.dto.CodeClassMstDto;
import com.naikara_talk.exception.NaiException;

public class CodeClassMstLstLogic {

	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public CodeClassMstLstLogic(Connection con) {
		this.conn = con;
	}

	/**
	 * コード管理マスタから特定コード種別のリストを作成する。
	 * @param category
	 * @return
	 * @throws NaiException
	 */
	public List<CodeClassMstDto> getList(String category) throws NaiException {

		CodeClassMstDto dto = new CodeClassMstDto();

		dto.setCdCategory(category);

		CodeClassMstDao dao = new CodeClassMstDao(this.conn);
		return dao.search(dto);

	}
}
