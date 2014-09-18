package com.naikara_talk.logic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.naikara_talk.dao.TeacherIntroductionMstDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dto.ResultStateDto;
import com.naikara_talk.dto.TeacherIntroductionMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.NaikaraTalkConstants;

public class TeacherIntroductionMstLogic {

	/** 講師紹介マスタ　ステータス：仮登録 */
	public static String PROCESS_CD_PROVISIONAL = "01";
	/** 講師紹介マスタ　ステータス：本登録 */
	public static String PROCESS_CD_EFFECTIVE = "02";

	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public TeacherIntroductionMstLogic(Connection con) {
		this.conn = con;
	}

	/**
	 * 講師紹介マスタ検索処理
	 *
	 * @param processCd
	 * @return
	 * @throws NaiException
	 */
	public List<TeacherIntroductionMstDto> getList(String processCd) throws NaiException {

		List<TeacherIntroductionMstDto> retList = new ArrayList<TeacherIntroductionMstDto>();

		TeacherIntroductionMstDao dao = new TeacherIntroductionMstDao(this.conn);

		ConditionMapper conditions = new ConditionMapper();

		conditions.addCondition(TeacherIntroductionMstDao.COND_PROCESS_CD, ConditionMapper.OPERATOR_EQUAL, processCd);

		List<TeacherIntroductionMstDto> list = dao.search(conditions);
		if( list.get(0).getReturnCode() != NaikaraTalkConstants.RETURN_CD_DATA_NO ) {
			retList = list;
		}

		return retList;

	}


	/**
	 * 本登録処理。
	 *
	 * @param listDto
	 * @return 処理結果DTO
	 * @throws SQLException
	 */
	public ResultStateDto updateEffective( List<TeacherIntroductionMstDto> listDto )
			throws NaiException {

		TeacherIntroductionMstDao dao = new TeacherIntroductionMstDao(this.conn);

		// 削除パラメタ用
		ConditionMapper conditions = new ConditionMapper();

		int cnt = 0;

		// processCdがnullのまま実行なので全部消える
		dao.delete(conditions);

		// 仮登録分を更新
		for (TeacherIntroductionMstDto dto: listDto) {
			dto.setProcessCd("01");
			cnt += dao.add(dto);
		}

		// 本登録分を追加
		for (TeacherIntroductionMstDto dto: listDto) {
			dto.setProcessCd("02");
			cnt += dao.add(dto);
		}

		ResultStateDto resDto = new ResultStateDto();
		resDto.setRecCount(cnt);

		return resDto;

	}

	/**
	 * 仮登録処理
	 *
	 * @param listDto 講師紹介マスタDTO
	 * @return 処理結果DTO
	 * @throws SQLException
	 */
	public ResultStateDto updateProvisional( List<TeacherIntroductionMstDto> listDto )
			throws NaiException {

		TeacherIntroductionMstDao dao = new TeacherIntroductionMstDao(this.conn);

		// 削除パラメタ用
		ConditionMapper conditions = new ConditionMapper();

		conditions.addCondition(TeacherIntroductionMstDao.COND_PROCESS_CD, ConditionMapper.OPERATOR_EQUAL, PROCESS_CD_PROVISIONAL);

		int cnt = 0;

//		// 仮登録のレコードが消える
		dao.delete(conditions);

//		dto.setProcessCd("01");
		// 仮登録分を更新
		for (TeacherIntroductionMstDto dto: listDto) {
			dto.setProcessCd("01");
			cnt += dao.add(dto);
		}

		ResultStateDto resDto = new ResultStateDto();
		resDto.setRecCount(cnt);

		return resDto;

	}
}
