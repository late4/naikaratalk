package com.naikara_talk.service;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>入金管理ページ<br>
 * <b>クラス名称　　　:</b>入金管理ページクラス。<br>
 * <b>クラス概要　　　:</b>入金管理ページMoveService。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/09 TECS 新規作成。
 */

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.SalesManagedListModel;

public class SalesManagedListMoveService implements ActionService {

	/**
	 * 選択が必須チェック
	 * @param model 入金管理ページModel
	 * @return boolean
	 */
	public boolean checkNull(SalesManagedListModel model) throws NaiException {

		if (StringUtils.isEmpty(model.getSelect_rdl())) {
			// nullの場合
			return false;
		}
		return true;

	}

}
