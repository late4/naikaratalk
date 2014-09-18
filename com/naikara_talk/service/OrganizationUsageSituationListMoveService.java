package com.naikara_talk.service;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.OrganizationUsageSituationListModel;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>利用状況照会<br>
 * <b>クラス名称　　　:</b>利用状況照会Actionクラス。<br>
 * <b>クラス概要　　　:</b>利用状況照会moveService。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴　　　　:</b>2013/08/06 TECS 新規作成。
 */

public class OrganizationUsageSituationListMoveService implements ActionService {

	/* 対象年月が必須チェック */
    public static final int OBJECT_YYYYMM_NULL = -1;

    /* 対象年月の日付チェック */
    public static final int OBJECT_YYYYMM_DATE = -2;

    /* 正常 */
    public static final int CHECK_OK = 0;

    /**
	 * 検索前チェック処理。
	 * @param model 利用状況照会Model
	 * @return DataCount
	 * @throws NaiException
	 */
	public int checkPreSelect(OrganizationUsageSituationListModel model) throws NaiException {

		OrganizationUsageSituationListSearchService searchService = new OrganizationUsageSituationListSearchService();

		if (!searchService.checkNull(model)) {
			// 対象年月が必須チェック エラー
			return OBJECT_YYYYMM_NULL;
		}

		if (!searchService.checkDate(model)) {
			// 対象年月の日付チェック エラー
			return OBJECT_YYYYMM_DATE;
		}

		return CHECK_OK;

	}

	/**
	 * 選択が必須チェック
	 * @param model 用状況照会Model
	 * @return boolean
	 */
	public boolean checkNull(OrganizationUsageSituationListModel model) throws NaiException {

		if (StringUtils.isEmpty(model.getSelect_rdl())) {
			// nullの場合
			return false;
		}
		return true;

	}

}
