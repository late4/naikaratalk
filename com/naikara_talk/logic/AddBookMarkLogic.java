package com.naikara_talk.logic;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dao.TeacherBookmarkDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.TeacherBookmarkMstDto;
import com.naikara_talk.exception.NaiException;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 LOGICクラス<br>
 * <b>クラス名称　　　:</b>ブックマーク登録クラス<br>
 * <b>クラス概要　　　:</b>ブックマークを登録します<br>
 * <br>
 * <b>著作権　　　　　:</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author nos!!@mira
 * <b>変更履歴　　　　:</b>2013/06/12 TECS 新規作成
 */
public class AddBookMarkLogic {

	/** 返却ステータス：正常*/
	public static final int STATUS_SUCCESS = 0;
	/** 返却ステータス：登録済み*/
	public static final int STATUS_EXIST = 1;
	/** 返却ステータス：最大値超え*/
	public static final int STATUS_EXCEEDED = 2;


	private Connection conn = null;

	public AddBookMarkLogic(Connection conn) {
		this.conn = conn;
	}

	/**
	 * ブックマークを登録します。
	 * @param teacherId
	 * @param userId
	 * @return ステータス ステータスはクラスの定数で返却
	 * @throws NaiException
	 */
	public int add(String teacherId, String userId) throws NaiException {

		// DAOのインスタンス化
		TeacherBookmarkDao dao = new TeacherBookmarkDao(conn);

		// ユーザIDでブックマークテーブルの検索を行う。

		ConditionMapper cond = new ConditionMapper();
		cond.addCondition(TeacherBookmarkDao.COND_USER_ID, ConditionMapper.OPERATOR_EQUAL, userId);

		List<TeacherBookmarkMstDto> dtoList = dao.search(cond, new OrderCondition());

		// すでに50件を超えていた場合
		if (dtoList.size() >= 50) {
			// 登録せずに処理を抜ける
			return STATUS_EXCEEDED;
		}

		// 検索結果の中に同じ講師が含まれていた場合
		if (dtoList.size() >= 50) {
			// 登録せずに処理を抜ける

			for(TeacherBookmarkMstDto dto : dtoList ){

				if(StringUtils.equals(dto.getUserId(),teacherId)){
					return STATUS_EXIST;
				}
			}

		}

		try {
			// 登録する
			TeacherBookmarkMstDto dto=new TeacherBookmarkMstDto();
	 		Calendar cal = Calendar.getInstance();
	 		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			dto.setStudentId(userId);
			dto.setUserId(teacherId);
			dto.setRecordVerNo(0);
			dto.setInsertDttm(Timestamp.valueOf(sdf.format(cal.getTime())));
			dto.setInsertCd(userId);
			dto.setUpdateDttm(Timestamp.valueOf(sdf.format(cal.getTime())));
			dto.setUpdateCd(userId);

			if(dao.insert(dto)<1){
				return STATUS_EXIST;
			}

		} catch (NaiException e) {
			// 一意制約違反の場合
			if(((SQLException)e.getCause()).getErrorCode() == 1062){
				return STATUS_EXIST;

			} else {
				throw e;
			}
		}

		return STATUS_SUCCESS;
	}
}