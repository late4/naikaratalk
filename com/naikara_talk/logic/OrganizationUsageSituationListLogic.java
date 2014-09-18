package com.naikara_talk.logic;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dao.OrganizationUsageSituationListDao;
import com.naikara_talk.dto.OrganizationUsageSituationListDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>利用状況照会<br>
 * <b>クラス名称　　　:</b>利用状況照会クラス。<br>
 * <b>クラス概要　　　:</b>利用状況照会Logic。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴　　　　:</b>2013/08/06 TECS 新規作成。
 */

public class OrganizationUsageSituationListLogic {

	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public OrganizationUsageSituationListLogic(Connection con) {
		this.conn = con;
	}

	/**
	 * 利用状況照会のデータ件数を取得
	 * @param dto 利用状況照会DTO
	 * @return int
	 * @throws NaiException
	 */
	public int getRowCount(OrganizationUsageSituationListDto dto) throws NaiException {

        // DAOの初期化
		OrganizationUsageSituationListDao dao = new OrganizationUsageSituationListDao(this.conn);

        // データ件数を取得
		int dataCount = NaikaraTalkConstants.RETURN_CD_DATA_ERR; // データ件数

		// 画面の対象年月
		StringBuffer sbFrom = new StringBuffer();
		// システム日付の当月取得(yyyy/MM)
		StringBuffer sbTo = new StringBuffer();
		// yyyy/mm -> yyyymm01
		sbFrom.append(dto.getObjectYyyyMm()).append("01");
		sbTo.append(NaikaraStringUtil.delSlash(DateUtil.getSysDateYMNow())).append("01");

		// 日付の比較
		if (DateUtil.dateCompare3(sbFrom.toString(), sbTo.toString())) {

			// 画面の｢対象年月｣がシステム日付の年月が当月以降の場合
			dataCount = dao.rowCountNow(dto);

		} else {
			// 画面の｢対象年月｣がシステム日付-1ヶ月以前の場合
			dataCount = dao.rowCountBefore(dto);

		}

        // 戻り値
        return dataCount;
    }

	/**
	 * 利用状況照会のデータを取得
	 * @param dto 利用状況照会DTO
	 * @return int
	 * @throws NaiException
	 */
	public List<OrganizationUsageSituationListDto> search(OrganizationUsageSituationListDto dto) throws NaiException {

        // DAOの初期化
		OrganizationUsageSituationListDao dao = new OrganizationUsageSituationListDao(this.conn);

		// 画面の対象年月
		StringBuffer sbFrom = new StringBuffer();
		// システム日付の当月取得(yyyy/MM)
		StringBuffer sbTo = new StringBuffer();
		// yyyy/mm -> yyyymm01
		sbFrom.append(dto.getObjectYyyyMm()).append("01");
		sbTo.append(NaikaraStringUtil.delSlash(DateUtil.getSysDateYMNow())).append("01");

		// 日付の比較
		if (DateUtil.dateCompare3(sbFrom.toString(), sbTo.toString())) {

			// 画面の｢対象年月｣がシステム日付の年月が当月以降の場合
			return changeList(dao.searchNow(dto));

		} else {
			// 画面の｢対象年月｣がシステム日付-1ヶ月以前の場合
			return changeList(dao.searchBefore(dto));

		}
    }

	/**
	 * DBから画面へlistの変更
	 * @param list
	 * @return list
	 */
	private List<OrganizationUsageSituationListDto> changeList(List<OrganizationUsageSituationListDto> list) {

		// 変数初期化
		ArrayList<OrganizationUsageSituationListDto> retList = new ArrayList<OrganizationUsageSituationListDto>();
		OrganizationUsageSituationListDto getDto;
		OrganizationUsageSituationListDto retDto;

		for (int i = 0; i < list.size(); i++) {
			getDto = list.get(i);

			retDto = new OrganizationUsageSituationListDto();
			// 受講者所属部署
			retDto.setStudentPosition(getDto.getStudentPosition());
			// 所属組織内ID
			retDto.setPositionOrganizationId(getDto.getPositionOrganizationId());

			// 受講者名
			if (StringUtils.isEmpty(getDto.getFamilyFirstKnm())) {
				// 実績のない受講者の場合  共通部品：名前の編集(名前(姓)、名前(名))
				retDto.setFamilyFirstKnm(NaikaraStringUtil.unionName(getDto.getFamilyJnm(), getDto.getFirstJnm()));
			} else {
				retDto.setFamilyFirstKnm(getDto.getFamilyFirstKnm());
			}

			// ポイント外商品購入
			retDto.setPurchaseYen(getDto.getPurchaseYen());

			// ご利用済ポイント
			BigDecimal userPoint = getDto.getUserPoint();
			retDto.setUserPoint(userPoint);

			// 自己負担率を取得
			BigDecimal burdenNum = new BigDecimal(getDto.getBurdenNum());
			// 自己負担ポイント＝利用ポイント(合計)×自己負担率/100 ※切り捨てし、組織側が端数は持つ
			BigDecimal burdenPoint = userPoint.multiply(burdenNum).divide(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_DOWN);
			retDto.setBurdenPoint(burdenPoint);

			// 組織負担ポイント  利用ポイント(合計) - 自己負担ポイント
			retDto.setOrganizationPoint(userPoint.subtract(burdenPoint));

			// 受講済ﾚｯｽﾝ回数
			retDto.setLessonNum(getDto.getLessonNum());

			// 最終利用日
			if (StringUtils.isEmpty(getDto.getLastUseDt())) {
				// 当月の場合 レッスン日又は購入日で大きい値を表示
				retDto.setLastUseDt(NaikaraStringUtil.converToYYYY_MM_DD(
						dateCompare(dateCompare(getDto.getLessonDt(), getDto.getPurchaseDt1()), getDto.getPurchaseDt2())));
			} else {
				// 先月以前の場合
				retDto.setLastUseDt(NaikaraStringUtil.converToYYYY_MM_DD(getDto.getLastUseDt()));
			}

			// 利用実績のない受講者の明細は一覧の｢選択｣は使用不可とする
			if (StringUtils.isEmpty(retDto.getLastUseDt())) {
				retDto.setRadioFlag(true);
			}
			// 受講者ID
			retDto.setStudentId(getDto.getStudentId());
			// フリガナ(姓)
			retDto.setFamilyKnm(getDto.getFamilyKnm());
			// フリガナ(名)
			retDto.setFirstKnm(getDto.getFirstKnm());

			// 画面遷移の引数
			StringBuffer sb = new StringBuffer();
			sb.append(retDto.getStudentPosition()).append("#")
			.append(retDto.getPositionOrganizationId()).append("#")
			.append(retDto.getStudentId()).append("#")
			.append(retDto.getFamilyFirstKnm()).append("#")
			.append(NaikaraStringUtil.unionName(retDto.getFamilyKnm(), retDto.getFirstKnm()));
			retDto.setRadioValue(sb.toString());

			retList.add(retDto);

		}

		return retList;
	}

	/**
	 * 日付の比較
	 * @param str1
	 * @param str2
	 * @return string
	 */
	private String dateCompare(String str1, String str2) {

		// 二つNULLの場合 NULLを返す
		if (StringUtils.isEmpty(str1) && StringUtils.isEmpty(str2)) {
			return null;
		}

		// str1NULLの場合 str2を返す
		if (StringUtils.isEmpty(str1) ){
			return str2;
		}

		// str2の場合 str1を返す
		if (StringUtils.isEmpty(str2) ){
			return str1;
		}

		// 大きい日付を返す
		if (DateUtil.dateCompare2(str1, str2)) {
			return str1;
		} else {
			return str2;
		}

	}

}
