package com.naikara_talk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.SampleParamDto;
import com.naikara_talk.dto.SampleDto;


public class SampleDao extends AbstractDao {

	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public SampleDao(Connection con) {
		this.conn = con;
	}

	public int getMaxType(SampleParamDto dto) throws Exception {

//		DataSource ds = DbUtil.getDataSource();
		//		Connection conn = ds.getConnection();

		ResultSet res = null;
		try{

			PreparedStatement ps = conn.prepareStatement("select max(type) from commodity");

			res = ps.executeQuery();

			int a = 0;
			while (res.next()){
				a = res.getInt(1);
			}

			return a;

		} catch ( SQLException se ) {
			se.printStackTrace();
			throw new Exception();
		} finally {
			res.close();
//		conn.close();
		}

	}

	public int add(SampleParamDto dto) throws Exception {

		String id = "sample";

//		DataSource ds = DbUtil.getDataSource();
//		Connection conn = ds.getConnection();

		try{

			PreparedStatement ps  = conn.prepareStatement(
	                "insert into commodity (type,name,price) values (?, ?, ? )");

			ps.setInt(1, dto.getType());
			ps.setString(2, id);
			ps.setInt(3, 101);

			return ps.executeUpdate();

		} catch ( SQLException se ) {
			se.printStackTrace();
			throw new Exception();
		} finally {
			conn.close();
		}
	}

	public List<SampleDto> search(SampleParamDto dto) throws Exception {

//		DataSource ds = DbUtil.getDataSource();
//		Connection conn = ds.getConnection();

		ResultSet res = null;

		try{
			PreparedStatement ps = conn.prepareStatement(
                "select type,name,price from commodity");

			res = ps.executeQuery();

			ArrayList<SampleDto> list = new ArrayList<SampleDto>();

			while (res.next()){

				SampleDto retDto = new SampleDto();
				retDto.setType(res.getInt(1));
				retDto.setName(res.getString(2));
				retDto.setPrice(res.getInt(3));

				list.add(retDto);
			}

			return list;

		} catch ( SQLException se ) {
			se.printStackTrace();
			throw new Exception();
		} finally {
			res.close();
//			conn.close();
		}

	}

}
