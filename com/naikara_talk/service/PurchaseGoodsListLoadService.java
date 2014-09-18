package com.naikara_talk.service;

import com.naikara_talk.model.PurchaseGoodsListModel;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様利用画面<br>
 * <b>クラス名称       :</b>商品購入ページ<br>
 * <b>クラス概要       :</b>商品購入ページ初期処理Service<br>
 * <br>
 * <b>著作権           :</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/09 TECS 新規作成
 *                     :</b>2014/02/18 TECS 商品購入ページからの呼出追加に伴う修正
 */
public class PurchaseGoodsListLoadService implements ActionService {

    /**
     * 初期処理<br>
     * <br>
     * 画面項目の初期処理を行う<br>
     * <br>
     * @param なし<br>
     * @return model 画面パラメータ<br>
     * @exception なし
     */
    public PurchaseGoodsListModel initLoad() {

        // モデルの初期化
    	PurchaseGoodsListModel model = new PurchaseGoodsListModel();

        // コース名：大分類コード
        model.setBigClassificationCdS(NaikaraTalkConstants.CHOICE_ALL_ZERO);

        // コース名：中分類コード
        model.setMiddleClassificationCdS(NaikaraTalkConstants.CHOICE_ALL_ZERO);

        // コース名：小分類コード
        model.setSmallClassificationCdS(NaikaraTalkConstants.CHOICE_ALL_ZERO);

        // 商品形態
        model.setProductKbnS(NaikaraTalkConstants.CHOICE_ALL_ZERO);

        // 受取方法
        model.setSaleKbnS(NaikaraTalkConstants.CHOICE_ALL_ZERO);

        //2014.02.　18 Add Start 商品購入ページからの呼出追加に伴う修正
        // 講師への購入メール連絡付き区分:送信なし
        model.setTMailKbnS(NaikaraTalkConstants.T_CONTACT_KBN_NO);
        //2014.02.18 Add End   商品購入ページからの呼出追加に伴う修正

        return model;
    }

}