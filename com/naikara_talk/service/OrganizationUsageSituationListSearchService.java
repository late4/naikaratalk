package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.OrganizationUsageSituationListDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.OrganizationUsageSituationListLogic;
import com.naikara_talk.model.OrganizationUsageSituationListModel;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>利用状況照会<br>
 * <b>クラス名称　　　:</b>利用状況照会クラス。<br>
 * <b>クラス概要　　　:</b>利用状況照会SearchService。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴　　　　:</b>2013/08/06 TECS 新規作成。
 */

public class OrganizationUsageSituationListSearchService implements ActionService {

	/* 取得したデータ件数がZero件 */
    public static final int LIST_ZERO_CNT = 0;

    /* 取得したデータ件数が表示の上限値を超える */
    public static final int LIST_HUNDRED_CNT = 100;

    /* 対象年月が必須チェック */
    public static final int OBJECT_YYYYMM_NULL = -1;

    /* 対象年月の日付チェック */
    public static final int OBJECT_YYYYMM_DATE = -2;

    /**
	 * 検索前チェック処理。
	 * @param model 利用状況照会Model
	 * @return DataCount
	 * @throws NaiException
	 */
	public int checkPreSelect(OrganizationUsageSituationListModel model) throws NaiException {

		// コネクション変数
		Connection conn = null;

		// データ件数を取得
		int dataCount = NaikaraTalkConstants.RETURN_CD_DATA_ERR; // データ件数

		try {
			if (!checkNull(model)) {
				// 対象年月が必須チェック エラー
				return OBJECT_YYYYMM_NULL;
			}

			if (!checkDate(model)) {
				// 対象年月の日付チェック エラー
				return OBJECT_YYYYMM_DATE;
			}

			// コネクションを取得
			conn = DbUtil.getConnection();

			// Model値をDTOにセット
			OrganizationUsageSituationListDto dto = modelToDto(model);
			OrganizationUsageSituationListLogic logic = new OrganizationUsageSituationListLogic(conn);

			// 利用状況照会のデータ件数を取得
			dataCount = logic.getRowCount(dto);

			if (dataCount == LIST_ZERO_CNT) {
				// 取得したデータ件数がZero件の場合
				return LIST_ZERO_CNT;
			} else if (dataCount > LIST_HUNDRED_CNT) {
				// 取得したデータ件数が表示の上限値を超えるの場合
				return LIST_HUNDRED_CNT;
			}

			return dataCount;

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
	 * 利用状況照会のデータを取得。
	 * @param model 利用状況照会Model
	 * @return list
	 * @throws NaiException
	 */
	public List<OrganizationUsageSituationListDto> search(OrganizationUsageSituationListModel model) throws NaiException {

		// コネクション変数
		Connection conn = null;

		try {
			// コネクションを取得
			conn = DbUtil.getConnection();

			// Model値をDTOにセット
			OrganizationUsageSituationListDto dto = modelToDto(model);
			OrganizationUsageSituationListLogic logic = new OrganizationUsageSituationListLogic(conn);

			// 利用状況照会のデータを取得
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
	private OrganizationUsageSituationListDto modelToDto(OrganizationUsageSituationListModel model) throws NaiException {

		// DTOの初期化
		OrganizationUsageSituationListDto dto = new OrganizationUsageSituationListDto();
		// 組織ID
		dto.setOrganizationId(model.getOrganizationId());
		// 受講者所属部署
		dto.setStudentPosition(model.getStudentPosition());
		// 所属組織内ID From
		dto.setPositionOrganizationIdFrom(model.getPositionOrganizationIdFrom());
		// 所属組織内ID To
		dto.setPositionOrganizationIdTo(model.getPositionOrganizationIdTo());
		// 対象年月
		dto.setObjectYyyyMm(NaikaraStringUtil.delSlash(model.getObjectYyyyMm()));
		// 受講者名(フリガナ)
		dto.setFamilyFirstKnm(model.getFamilyFirstKnm());
		// システム日付の年月 & '01'
		StringBuffer sb = new StringBuffer();
		sb.append(NaikaraStringUtil.delSlash(DateUtil.getSysDateYMNow())).append("01");
		dto.setSysdateYyyymm01(sb.toString());
		// システム日付
		dto.setSysdateYyyymmdd(DateUtil.getSysDate());

		return dto;

	}

	/**
	 * 日付チェック
	 * @param model 用状況照会Model
	 * @return boolean
	 */
	public boolean checkDate(OrganizationUsageSituationListModel model) throws NaiException {

		String objectYyyyMm = model.getObjectYyyyMm();
		// 桁数チェック
		if (objectYyyyMm.length() < 6) {
			return false;
		}

		StringBuffer sb = new StringBuffer();
		// 桁数=6桁
		if (objectYyyyMm.length() == 6) {
			sb.append(NaikaraStringUtil.converToYYYY_MM(objectYyyyMm));
		} else {
			sb.append(objectYyyyMm);
		}

		// yyyy/mm -> yyyy/mm/01に変更
		sb.append("/01");

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(sb.toString());
		} catch (Exception e) {
			return false;
		}
		return true;

	}

	/**
	 * 対象年月が必須チェック
	 * @param model 用状況照会Model
	 * @return boolean
	 */
	public boolean checkNull(OrganizationUsageSituationListModel model) throws NaiException {

		if (StringUtils.isEmpty(model.getObjectYyyyMm())) {
			// nullの場合
			return false;
		}
		return true;

	}

}
