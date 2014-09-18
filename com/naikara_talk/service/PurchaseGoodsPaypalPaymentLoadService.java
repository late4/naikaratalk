package com.naikara_talk.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.model.PurchaseGoodsModel;
import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.PointManagMstDto;
import com.naikara_talk.dto.StudentMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.PurchaseGoodsLogic;

import com.naikara_talk.sessiondata.SessionPayPal;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.sessiondata.SessionPurchasePoints;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様利用画面<br>
 * <b>クラス名称       :</b>商品購入ペイパル決済ページ<br>
 * <b>クラス概要       :</b>商品購入ペイパル決済ページ初期処理Service<br>
 * <br>
 * <b>著作権           :</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/16 TECS 新規作成
 */
public class PurchaseGoodsPaypalPaymentLoadService implements ActionService {

    /**
     * 初期処理<br>
     * <br>
     * 画面項目の初期処理を行う<br>
     * <br>
     * @param なし<br>
     * @return PurchaseGoodsModel<br>
     * @throws Exception
     * @exception なし
     */
    public PurchaseGoodsModel load() throws Exception {

		Connection conn = null;

		try{
			conn = DbUtil.getConnection();

			PurchaseGoodsModel pgModel = new PurchaseGoodsModel();
	        PurchaseGoodsLogic pgLogic = new PurchaseGoodsLogic(conn);

	        // 購入商品一覧
	        pgModel.setResultList(pgLogic.goodsList());

	        // 利用ポイント合計
	        BigDecimal usePointTotal = new BigDecimal(0);
	        for (int i = 0; i < pgModel.getResultList().size(); i++) {
	        	usePointTotal = usePointTotal.add(pgModel.getResultList().get(i).getBdUsePoint());
	        }
	        pgModel.setUsePointTotalB(usePointTotal);
	        pgModel.setUsePointTotal(NaikaraStringUtil.addComma(usePointTotal.toString()));

	        return pgModel;

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

}