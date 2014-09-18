package com.naikara_talk.logic;

import java.sql.Connection;

import com.naikara_talk.dao.SampleDao;
import com.naikara_talk.dto.SampleDto;
import com.naikara_talk.dto.SampleParamDto;


public class SampleAddLogic {

	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public SampleAddLogic(Connection con) {
		this.conn = con;
	}

	// 何かのデータを検索する
	// 何かのデータを更新する　更新するに当たってのもろもろを含む

	public SampleDto execute(SampleParamDto dto) throws Exception{

		SampleDao dao = new SampleDao(this.conn);

		SampleDto retDto = new SampleDto();

		SampleParamDto sDto = new SampleParamDto();
		try{

			sDto.setType(dao.getMaxType(null) + 1);

			retDto.setUpdaded(dao.add(sDto));

		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		return retDto;

	}
}
