package com.naikara_talk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.ConditionMapper;
//import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.TeacherCourseListDto;
import com.naikara_talk.dto.TeacherIntroductionMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

public class TeacherIntroductionMstDao {

	public static final String COND_PROCESS_CD = "PROCESS_CD";

	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public TeacherIntroductionMstDao(Connection con) {
		this.conn = con;
	}


	public List<TeacherIntroductionMstDto> search(ConditionMapper conditions) throws NaiException {

//		DataSource ds = DbUtil.getDataSource();
//		Connection conn = null;
		ResultSet res = null;

		StringBuilder sb = new StringBuilder();
		sb.append("select ");
		sb.append(" PROCESS_CD ");
		sb.append(" ,USER_CATEGORY_CD ");
		sb.append(" ,USER_CATEGORYSUB_CD ");
		sb.append(" ,USER_CD ");
		sb.append(" ,INSERT_DTTM ");
		sb.append(" ,INSERT_CD ");
		sb.append(" ,UPDATE_DTTM ");
		sb.append(" ,UPDATE_CD ");
		sb.append("from ");
		sb.append(" TEACHAR_INTRODUCTION_MST ");
		sb.append("where ");
		sb.append(conditions.getConditionString());

		try{
//			conn = ds.getConnection();

			PreparedStatement ps = conn.prepareStatement(sb.toString());

			// パラメタの設定
			for(int i = 0; i < conditions.getConditions().size(); i++){
				ps.setString(i + 1, conditions.getConditions().get(i).getValue());
			}

			res = ps.executeQuery();

			ArrayList<TeacherIntroductionMstDto> list = new ArrayList<TeacherIntroductionMstDto>();

			while (res.next()){

				TeacherIntroductionMstDto retDto = new TeacherIntroductionMstDto();
				retDto.setProcessCd(res.getString("PROCESS_CD"));
				retDto.setUserCategoryCd(res.getString("USER_CATEGORY_CD"));
				retDto.setUserCategorysubCd(res.getString("USER_CATEGORYSUB_CD"));
				retDto.setUserId(res.getString("USER_CD"));
				retDto.setInsertDttm(res.getDate("INSERT_DTTM"));
				retDto.setInsertCd(res.getString("INSERT_CD"));
				retDto.setUpdateDttm(res.getDate("UPDATE_DTTM"));
				retDto.setUpdateCd(res.getString("UPDATE_CD"));

				list.add(retDto);
			}

			//データが存在しない場合、エラー
			if(list.size()<1){
				TeacherIntroductionMstDto dto = new TeacherIntroductionMstDto();
				dto.setReturnCode(NaikaraTalkConstants.RETURN_CD_DATA_NO);
				list.add(dto);
			}

			return list;

		} catch ( SQLException e ) {
			e.printStackTrace();
			throw new NaiException(e);
		} finally {
			try {
				res.close();
//				conn.close();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				throw new NaiException(e);
			}
		}
	}


	/**
	 * 更新処理。
	 * よく考えたらこの処理では多分使用しない。
	 * @param valueDto	更新データDTO
	 * @param condDto	条件DTO
	 * @return 更新件数
	 * @throws NaiException
	 * @throws Exception
	 */
	public int update(TeacherIntroductionMstDto valueDto,TeacherIntroductionMstDto condDto) throws NaiException {

//		DataSource ds = DbUtil.getDataSource();
//		Connection conn = null;

		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE TEACHAR_INTRODUCTION_MST ");
		sb.append("SET ");
		sb.append("  PROCESS_CD = ? ");
		sb.append("  , USER_CATEGORY_CD = ? ");
		sb.append("  , USER_CATEGORYSUB_CD = ? ");
		sb.append("  , USER_CD` = ? ");
		sb.append("  , UPDATE_DTTM` = NOW() ");
		sb.append("  , UPDATE_CD` = ? ");
		sb.append("WHERE ");
		sb.append("  PROCESS_CD = ? ");
		sb.append("  and USER_CATEGORY_CD = ? ");
		sb.append("  and USER_CATEGORYSUB_CD = ? ");

		try{
//			conn = ds.getConnection();

			PreparedStatement ps = conn.prepareStatement(sb.toString());

			ps.setString(1, valueDto.getProcessCd());
			ps.setString(2, valueDto.getUserCategoryCd());
			ps.setString(3, valueDto.getUserCategorysubCd());
			ps.setString(4, valueDto.getUserId());
			ps.setString(5, valueDto.getUpdateCd());
			ps.setString(6, condDto.getProcessCd());
			ps.setString(7, condDto.getUserCategoryCd());
			ps.setString(8, condDto.getUserCategorysubCd());

			return  ps.executeUpdate();

		} catch ( SQLException se ) {
			//se.printStackTrace();

			throw new NaiException(se);
//		} finally {
//			try {
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//				throw new NaiException(e);
//			}
		}
	}


	public int add(TeacherIntroductionMstDto dto) throws NaiException  {

//		DataSource ds = DbUtil.getDataSource();
//		Connection conn = null;

		StringBuilder sb = new StringBuilder();
		sb.append(" INSERT  ");
		sb.append(" INTO TEACHAR_INTRODUCTION_MST(  ");
		sb.append("   PROCESS_CD ");
		sb.append("   , USER_CATEGORY_CD ");
		sb.append("   , USER_CATEGORYSUB_CD ");
		sb.append("   , USER_CD ");
		sb.append("   , INSERT_DTTM ");
		sb.append("   , INSERT_CD ");
		sb.append("   , UPDATE_DTTM ");
		sb.append("   , UPDATE_CD ");
		sb.append(" )  ");
		sb.append(" VALUES (  ");
		sb.append("   ? ");
		sb.append("   , ? ");
		sb.append("   , ? ");
		sb.append("   , ? ");
		sb.append("   , NOW() ");
		sb.append("   , ? ");
		sb.append("   , NOW() ");
		sb.append("   , ? ");
		sb.append(" ) ");

		try{
//			conn = ds.getConnection();

			PreparedStatement ps = conn.prepareStatement(sb.toString());

			ps.setString(1, dto.getProcessCd());
			ps.setString(2, dto.getUserCategoryCd());
			ps.setString(3, dto.getUserCategorysubCd());
			ps.setString(4, dto.getUserId());
			ps.setString(5, ((SessionUser)SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId());
			ps.setString(6, ((SessionUser)SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId());

			return  ps.executeUpdate();

		} catch ( SQLException se ) {
			se.printStackTrace();
			throw new NaiException(se);
//		} finally {
//			try {
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//				throw new NaiException(e);
//			}
		}
	}

	public int delete(ConditionMapper conditions) throws NaiException {

//		DataSource ds = DbUtil.getDataSource();
//		Connection conn = null;

		StringBuilder sb = new StringBuilder();
		sb.append(" DELETE ");
		sb.append(" FROM ");
		sb.append("   TEACHAR_INTRODUCTION_MST ");

		if (!StringUtils.isEmpty(conditions.getConditionString())){
			sb.append("where ");
			sb.append(conditions.getConditionString());
		}

		try{
//			conn = ds.getConnection();

			PreparedStatement ps = conn.prepareStatement(sb.toString());

			// パラメタの設定
			for(int i = 0; i < conditions.getConditions().size(); i++){
				ps.setString(i + 1, conditions.getConditions().get(i).getValue());
			}

			return  ps.executeUpdate();

		} catch ( SQLException se ) {
			se.printStackTrace();
			throw new NaiException(se);
//		} finally {
//			try {
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//				throw new NaiException(e);
//			}
		}
	}

}
