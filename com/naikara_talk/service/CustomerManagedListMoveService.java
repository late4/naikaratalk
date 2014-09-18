package com.naikara_talk.service;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.exception.NaiException;
import com.naikara_talk.model.CustomerManagedListModel;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様管理ページ<br>
 * <b>クラス名称　　　:</b>お客様管理ページクラス。<br>
 * <b>クラス概要　　　:</b>お客様管理ページ選択Service。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/20 TECS 新規作成。
 */

public class CustomerManagedListMoveService implements ActionService {

    /**
	 * 単票へ画面遷移する制御処理<br>
	 * <br>
	 * @param SaleGoodsMstListModel
	 * @param select_rdl
	 * @param hasSearchFlg
	 * @return SaleGoodsMstListModel
	 */
    public int nextPageRequest(CustomerManagedListModel model,String select_rdl, String hasSearchFlg) throws NaiException{

    	// 必須選択のチェック
        if ( StringUtils.isEmpty(select_rdl)) {

           // 一覧部の項目が選択されていない場合は、メッセージ情報を設定する
           if (NaikaraTalkConstants.TRUE.equals(hasSearchFlg)) {
               // エラー場合の再検索判断
               return CustomerManagedListModel.ERR_NO_SELECT;

           }
           // 一覧画面戻る
           return CustomerManagedListModel.ERR_LIST_BACK;
        }

        // 返却値の設定
        return CustomerManagedListModel.CHECK_OK;

    }

}
