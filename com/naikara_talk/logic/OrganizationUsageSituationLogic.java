package com.naikara_talk.logic;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.naikara_talk.dao.OrganizationUsageSituationDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.OrganizationUsageSituationDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>利用状況照会詳細<br>
 * <b>クラス名称　　　:</b>利用状況照会詳細クラス。<br>
 * <b>クラス概要　　　:</b>利用状況照会詳細Logic。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴　　　　:</b>2013/08/06 TECS 新規作成。
 */

public class OrganizationUsageSituationLogic {

	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public OrganizationUsageSituationLogic(Connection con) {
		this.conn = con;
	}

	/**
	 * 利用状況照会詳細のデータを取得
	 * @param dto 利用状況照会詳細DTO
	 * @return list
	 * @throws NaiException
	 */
	public List<OrganizationUsageSituationDto> search(OrganizationUsageSituationDto dto) throws NaiException {

		ConditionMapper  cm = new ConditionMapper();
		OrderCondition  oc = new OrderCondition();

		// 年月
		cm.addCondition(OrganizationUsageSituationDao.COND_YYYY_MM, ConditionMapper.OPERATOR_EQUAL, dto.getObjectYyyyMm());
		// 受講者ID
		cm.addCondition(OrganizationUsageSituationDao.COND_STUDENT_ID, ConditionMapper.OPERATOR_EQUAL, dto.getStudentId());

		// 並び順を設定

		// 購入日／利用日
        oc.addCondition(OrganizationUsageSituationDao.COND_PURCHASE_USE_DT, OrderCondition.DESC);
        //レッスン時刻名
        oc.addCondition(OrganizationUsageSituationDao.COND_LESSON_TM_NM, OrderCondition.DESC);

		// DAOの初期化
		OrganizationUsageSituationDao dao = new OrganizationUsageSituationDao(this.conn);

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

			// レッスン予実テーブル データを取得
			List<OrganizationUsageSituationDto> listLesson = searchLesson(dto);

			// 商品購入テーブル データを取得
			List<OrganizationUsageSituationDto> listGoods = searchGoods(dto);

			// レッスン予実テーブルと商品購入テーブルは、マージして並び替え
			return listPlus(listLesson, listGoods);

		} else {
			// 画面の｢対象年月｣がシステム日付-1ヶ月以前の場合

			// 売上明細テーブル データを取得
			List<OrganizationUsageSituationDto> list = dao.searchBefore(cm, oc);

			OrganizationUsageSituationDto dtoFlag = list.get(0);

			// データなしの場合
			if (dtoFlag.getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_NO) {
				return new ArrayList<OrganizationUsageSituationDto>();
			}

			return list;

		}

    }

	/**
	 * レッスン予実テーブル データを取得
	 * @param dto
	 * @return list
	 * @throws NaiException
	 */
	public List<OrganizationUsageSituationDto> searchLesson(OrganizationUsageSituationDto dto) throws NaiException {

		ConditionMapper  cm = new ConditionMapper();
		OrderCondition  oc = new OrderCondition();

		// レッスン日
		cm.addCondition(OrganizationUsageSituationDao.COND_LESSON_DT, ConditionMapper.OPERATOR_LARGE_EQUAL, dto.getSysdateYyyymm01());
		cm.addCondition(OrganizationUsageSituationDao.COND_LESSON_DT, ConditionMapper.OPERATOR_LESS_EQUAL, dto.getSysdateYyyymmdd());
		// 受講者ID
		cm.addCondition(OrganizationUsageSituationDao.COND_STUDENT_ID, ConditionMapper.OPERATOR_EQUAL, dto.getStudentId());

		// 並び順を設定

		// レッスン日
        oc.addCondition(OrganizationUsageSituationDao.COND_LESSON_DT, OrderCondition.DESC);
        // レッスン時刻名
        oc.addCondition(OrganizationUsageSituationDao.COND_LESSON_TM_NM, OrderCondition.DESC);

		 // DAOの初期化
		OrganizationUsageSituationDao dao = new OrganizationUsageSituationDao(this.conn);

		// レッスン予実テーブル データを取得
		return dao.searchLesson(cm, oc);
    }

	/**
	 * 商品購入テーブル データを取得
	 * @param dto
	 * @return list
	 * @throws NaiException
	 */
	public List<OrganizationUsageSituationDto> searchGoods(OrganizationUsageSituationDto dto) throws NaiException {

		ConditionMapper  cm = new ConditionMapper();
		OrderCondition  oc = new OrderCondition();

		// 購入日
		cm.addCondition(OrganizationUsageSituationDao.COND_PURCHASE_DT, ConditionMapper.OPERATOR_LARGE_EQUAL, dto.getSysdateYyyymm01());
		cm.addCondition(OrganizationUsageSituationDao.COND_PURCHASE_DT, ConditionMapper.OPERATOR_LESS_EQUAL, dto.getSysdateYyyymmdd());
		// 受講者ID
		cm.addCondition(OrganizationUsageSituationDao.COND_STUDENT_ID, ConditionMapper.OPERATOR_EQUAL, dto.getStudentId());

		// 並び順を設定

		// 購入日
        oc.addCondition(OrganizationUsageSituationDao.COND_PURCHASE_DT, OrderCondition.DESC);

		 // DAOの初期化
		OrganizationUsageSituationDao dao = new OrganizationUsageSituationDao(this.conn);

		// 商品購入テーブル データを取得
		return dao.searchGoods(cm, oc);
    }

	/**
	 * レッスン予実テーブルと商品購入テーブルは、マージして並び替え
	 * @param listLesson
	 * @param listGoods
	 * @return
	 * @throws NaiException
	 */
	public List<OrganizationUsageSituationDto> listPlus(
			List<OrganizationUsageSituationDto> listLesson,
			List<OrganizationUsageSituationDto> listGoods) throws NaiException {

		ArrayList<OrganizationUsageSituationDto> list = new ArrayList<OrganizationUsageSituationDto>();
		OrganizationUsageSituationDto dto;
		String lessonDt = null;  // レッスン日
		String goodsDt = null;   // 購入日

		// listLessonはnullの場合、listGoodsを返す
		if (listLesson.get(0).getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_NO) {
			return listGoods;
		}

		// listGoodsはnullの場合、listLessonを返す
		if (listGoods.get(0).getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_NO) {
			return listLesson;
		}


		// マージして並び替え
		int j = 0;
		// レッスン予実の繰り返す
		for (int i = 0; i < listLesson.size();) {

			if ( j < listGoods.size() ) {
				// listGoodsある場合

				// 購入日を取得
				goodsDt = NaikaraStringUtil.delSlash(listGoods.get(j).getLessonDt());

				// レッスン日を取得
				lessonDt = NaikaraStringUtil.delSlash(listLesson.get(i).getLessonDt());

				// 日付の比較
				if (DateUtil.dateCompare3(lessonDt, goodsDt)) {
					// レッスン日が大きい
					list.add(listLesson.get(i));
					i ++;
				} else {
					// 購入日が大きい
					list.add(listGoods.get(j));
					j ++;
				}

			} else {
				// listGoodsもうない場合 listLessonを追加
				list.add(listLesson.get(i));
				i ++;
			}

		}

		// listGoodsまだある場合、商品購入の繰り返す
		for (int i = j; i < listGoods.size(); i++) {
			dto = listGoods.get(i);
			list.add(dto);
		}
		return list;

    }

}
