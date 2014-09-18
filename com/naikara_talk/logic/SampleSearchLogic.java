package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.naikara_talk.dao.SampleDao;
import com.naikara_talk.dto.SampleDto;
import com.naikara_talk.dto.SampleParamDto;

public class SampleSearchLogic {

	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public SampleSearchLogic(Connection con) {
		this.conn = con;
	}

	SampleDao dao = new SampleDao(this.conn);

	// 何かのデータを検索する
	// 何かのデータを更新する　更新するに当たってのもろもろを含む

	public List<SampleDto> execute(SampleParamDto dto) throws Exception{

	try{

		return dao.search(dto);

	} catch (Exception e) {
		// TODO: handle exception
		throw e;
	}
	}

}
