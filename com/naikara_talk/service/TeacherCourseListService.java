package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.SystemException;
//import javax.transaction.UserTransaction;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.CourseMstDto;
import com.naikara_talk.dto.TeacherCourseListDto;
import com.naikara_talk.dto.TeacherMstCacheDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.TeacherCourseListLogic;
import com.naikara_talk.model.TeacherCourseListModel;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.mstcache.TeacherMstCache;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * 講師対応コースを検索します。
 * @author nos
 *
 */
public class TeacherCourseListService implements ActionService {

	/**
	 * 講師マスタに登録されている全員分のコース情報を構築する
	 * @return
	 * @throws NaiException
	 */
	public Map<String,List<TeacherCourseListModel>> get() throws NaiException {

		TeacherMstCache mst = TeacherMstCache.getInstance();

		ArrayList<String> arr = new ArrayList<String>();
		for(TeacherMstCacheDto dto : mst.getList()){
			arr.add(dto.getUserId());
		}
		return get((String[])arr.toArray());
	}

	/**
	 * リスト指定された講師分のコース情報を構築する
	 * @param userIdList
	 * @return
	 * @throws NaiException
	 */
	public Map<String,List<TeacherCourseListModel>> get(String[] userIdList) throws NaiException {
		Map<String,List<TeacherCourseListModel>> listMap = new HashMap<String,List<TeacherCourseListModel>>();

		Connection conn = null;

		try {
			conn = DbUtil.getConnection();

		TeacherCourseListLogic logic = new TeacherCourseListLogic(conn);
		for( TeacherCourseListDto dto : logic.get(userIdList) ) {
			if (!listMap.containsKey(dto.getUserId())){
				listMap.put(dto.getUserId(), new ArrayList<TeacherCourseListModel>());
			}
			listMap.get(dto.getUserId()).add(editCourse(dto));
		}
		return listMap;

		} catch (SQLException e) {

			throw new NaiException(e);
		} finally {
            try {
        		if ( !conn.isClosed() ) conn.close();
            } catch (SQLException e) {
                throw new NaiException(e);
            }
		}
	}

	/**
	 * コース説明の情報を編集する。
	 * @param dto
	 * @return
	 * @throws NaiException
	 */
	private TeacherCourseListModel editCourse(TeacherCourseListDto dto) throws NaiException {

		//汎用フィールド名の取得
		CodeManagMstCache cache = CodeManagMstCache.getInstance();

			TeacherCourseListModel model = new TeacherCourseListModel();

			//コースの編集
			//コースコード
			model.setCourseId(dto.getCourseId());

			//各分類コード
			model.setBigClassificationCd(dto.getBigClassificationCd());
			model.setMiddleClassificationCd(dto.getMiddleClassificationCd());
			model.setSmallClassificationCd(dto.getSmallClassificationCd());

			//データ編集
			String strCourseNM;

			//日本語コース名
			strCourseNM=cache.decode(NaikaraTalkConstants.CODE_CATEGORY_BIG_CLASSIFICATION
					,dto.getBigClassificationCd()) + "/";
			strCourseNM=strCourseNM + cache.decode(NaikaraTalkConstants.CODE_CATEGORY_MIDDLE_CLASSIFICATION
					,dto.getMiddleClassificationCd()) + "/";
			strCourseNM=strCourseNM + cache.decode(NaikaraTalkConstants.CODE_CATEGORY_SMALL_CLASSIFICATION
					,dto.getSmallClassificationCd()) + "/";
			strCourseNM=strCourseNM + dto.getCourseJnm();
			model.setCourseJnm(strCourseNM);

			//英語コース名
			String strCourseENM;
			strCourseENM=cache.decode(NaikaraTalkConstants.CODE_CATEGORY_BIG_CLASSIFICATION_T
					,dto.getBigClassificationCd()) + "/";
			strCourseENM=strCourseNM + cache.decode(NaikaraTalkConstants.CODE_CATEGORY_MIDDLE_CLASSIFICATION_T
					,dto.getMiddleClassificationCd()) + "/";
			strCourseENM=strCourseNM + cache.decode(NaikaraTalkConstants.CODE_CATEGORY_SMALL_CLASSIFICATION_T
					,dto.getSmallClassificationCd()) + "/";
			strCourseENM=strCourseNM + dto.getCourseEnm();
			model.setCourseEnm(strCourseENM);

			return model;
	}
}
