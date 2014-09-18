package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.OrganizationUsageSituationDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.OrganizationUsageSituationLogic;
import com.naikara_talk.model.OrganizationUsageSituationModel;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>利用状況照会詳細<br>
 * <b>クラス名称　　　:</b>利用状況照会詳細クラス。<br>
 * <b>クラス概要　　　:</b>利用状況照会詳細LoadService。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴　　　　:</b>2013/08/06 TECS 新規作成。
 */

public class OrganizationUsageSituationLoadService implements ActionService {

	/**
	 * 利用状況照会詳細のデータを取得。
	 * @param model 利用状況照会詳細Model
	 * @return DataCount
	 * @throws NaiException
	 */
	public List<OrganizationUsageSituationDto> search(OrganizationUsageSituationModel model) throws NaiException {

		// コネクション変数
		Connection conn = null;

		try {
			// コネクションを取得
			conn = DbUtil.getConnection();

			// Model値をDTOにセット
			OrganizationUsageSituationDto dto = modelToDto(model);
			OrganizationUsageSituationLogic logic = new OrganizationUsageSituationLogic(conn);

			// 利用状況照会詳細のデータを取得
			return logic.search(dto);

		} catch ( SQLException se ) {
			se.printStackTrace();
			throw new NaiException(se);
		} finally {
			try {
				conn.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}

	/**
	 * Model値をDTOにセット。
     * @param model 利用状況照会Model
     * @return OrganizationUsageSituationListDto
	 */
	private OrganizationUsageSituationDto modelToDto(OrganizationUsageSituationModel model) throws NaiException {

		// DTOの初期化
		OrganizationUsageSituationDto dto = new OrganizationUsageSituationDto();

		// 対象年月
		dto.setObjectYyyyMm(NaikaraStringUtil.delSlash(model.getObjectYyyyMm()));
		// 受講者ID
		dto.setStudentId(model.getStudentId());
		// システム日付の年月 & '01'
		StringBuffer sb = new StringBuffer();
		sb.append(NaikaraStringUtil.delSlash(DateUtil.getSysDateYMNow())).append("01");
		dto.setSysdateYyyymm01(sb.toString());
		// システム日付
		dto.setSysdateYyyymmdd(DateUtil.getSysDate());

		return dto;

	}

}
