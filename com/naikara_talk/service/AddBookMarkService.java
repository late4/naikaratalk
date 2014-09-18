package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.transaction.SystemException;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.AddBookMarkLogic;
import com.naikara_talk.logic.TeacherIntroductionMstLogic;
import com.naikara_talk.model.AddBookMarkModel;
import com.naikara_talk.model.TeacherIntroductionMstModel;
import com.naikara_talk.mstcache.CodeManagMstCache;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>ブックマーク登録サービス<br>
 * <b>クラス名称　　　:</b>ブックマーク登録Serviceクラス。<br>
 * <b>クラス概要　　　:</b>ブックマーク登録Service。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author mira
 * <b>変更履歴　　　　:</b>2013/08/15 TECS 新規作成。
 */
public class AddBookMarkService  implements ActionService {

	/**
	 * 画面データの作成
	 * 初回表示の場合
	 * @param proc TODO
	 * @return
	 * @throws SQLException
	 * @throws SystemException
	 * @throws NaiException
	 */
	public AddBookMarkModel add(String userId, String teacherId) throws  NaiException {

		int ret = 0;

		Connection conn = null;
		try{
			conn = DbUtil.getConnection();

			// 講師紹介マスタのロジックを取得
			AddBookMarkLogic logic = new AddBookMarkLogic(conn);
			AddBookMarkModel model = new AddBookMarkModel();

			ret = logic.add(teacherId,userId);

			model.setResult(ret);

			conn.commit();

			return model;

		} catch ( SQLException e ) {
			try {
				conn.rollback();
			} catch (Exception e1) {
				throw new NaiException(e1);
			}
			throw new NaiException(e);
		} finally {
			try {
				conn.close();
			} catch (Exception e1) {
				throw new NaiException(e1);
			}
		}
	}
}
