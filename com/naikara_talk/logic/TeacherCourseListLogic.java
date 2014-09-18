package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.naikara_talk.dao.TeacherCourseListDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dto.CourseMstDto;
import com.naikara_talk.dto.TeacherCourseListDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * 講師コースマスタDao
 * @author nos
 *
 */
public class TeacherCourseListLogic {

	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public TeacherCourseListLogic(Connection con) {
		this.conn = con;
	}

	/**
	 * 講師ごとのコースリスト情報を取得
	 * @param userIdList
	 * @return
	 * @throws NaiException
	 */
	public List<TeacherCourseListDto> get(String[] userIdList) throws NaiException {

		List<TeacherCourseListDto> modelList = new ArrayList<TeacherCourseListDto>();

		if(userIdList != null && userIdList.length > 0){
			TeacherCourseListDao dao = new TeacherCourseListDao(conn);

			ConditionMapper conditions = new ConditionMapper();

			conditions.addCondition(TeacherCourseListDao.COND_USER_ID, userIdList);

			List<TeacherCourseListDto> list = dao.search(conditions);

			if(list.size() > 0) {
				if(list.get(0).getReturnCode() != NaikaraTalkConstants.RETURN_CD_DATA_NO) {
					modelList = list;
				}
			}
		}

		return modelList;
	}

}
