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

import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.sessiondata.SessionPurchasePoints;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様利用画面<br>
 * <b>クラス名称       :</b>商品購入確認ページ<br>
 * <b>クラス概要       :</b>商品購入確認ページ初期処理Service<br>
 * <br>
 * <b>著作権           :</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/14 TECS 新規作成
 */
public class PurchaseGoodsConfirmationLoadService implements ActionService {

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

	        // ポイント残高
	        BigDecimal pointBalance = pgLogic.getPointBalance();
	        pgModel.setPointBalanceB(pointBalance);
	        pgModel.setPointBalance(NaikaraStringUtil.addComma(pointBalance.toString()));

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

	/**
	 * セッション情報リセット（PayPal画面から戻った場合にセット）<br>
	 * <br>
	 * @param なし
	 * @return なし
	 * @throws NaiException
	 */
/*	public void sessionUserReset(String userId) throws NaiException {

		Connection conn = null;

		try{
			conn = DbUtil.getConnection();

			PurchasePointsLogic ppLogic = new PurchasePointsLogic(conn);

	        List<StudentMstDto> smDto = ppLogic.searchStudent(userId);

	        if(smDto.get(0).getReturnCode()==NaikaraTalkConstants.RETURN_CD_DATA_YES) {
	            SessionUser SessionUser = new SessionUser();
	    		SessionUser.setRole(com.naikara_talk.sessiondata.SessionUser.ROLE_STUDENT);
	    		SessionUser.setUserId(smDto.get(0).getStudentId());
	    		SessionUser.setUserNm(NaikaraStringUtil.unionName(smDto.get(0).getFamilyJnm(), smDto.get(0).getFirstJnm()));
	    		SessionDataUtil.setSessionData(SessionUser);
	        }

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
	 * セッション情報リセット（PayPal画面から戻った場合にセット）<br>
	 * <br>
	 * @param なし
	 * @return なし
	 * @throws NaiException
	 */
/*	public void sessionPurchasePointsReset(String pointCd, String token, String payerId) throws NaiException {

		Connection conn = null;

		try{
			conn = DbUtil.getConnection();

			PurchasePointsLogic ppLogic = new PurchasePointsLogic(conn);

			List<PointManagMstDto> pmmDto = ppLogic.search(pointCd);

	        if(pmmDto.get(0).getReturnCode()==NaikaraTalkConstants.RETURN_CD_DATA_YES) {
	        	SessionPurchasePoints sessionPurchasePoints = new SessionPurchasePoints();
	    		StringBuffer sb = new StringBuffer();
	        	sessionPurchasePoints.setReturnOnFlg(true);
	        	sessionPurchasePoints.setSelectPointCd(pmmDto.get(0).getPointCd());
	        	sessionPurchasePoints.setMoneyYen(pmmDto.get(0).getMoneyYen());
	        	sessionPurchasePoints.setFeeKbn(pmmDto.get(0).getFeeKbn());
	        	sessionPurchasePoints.setPaymentPoint(pmmDto.get(0).getPaymentPoint());
	        	sessionPurchasePoints.setPaymentPointTim(pmmDto.get(0).getPaymentPointTim());
	        	sessionPurchasePoints.setFreePoint(pmmDto.get(0).getFreePoint());
	        	sessionPurchasePoints.setFreePointTim(pmmDto.get(0).getFreePointTim());
	            if (StringUtils.equals(pmmDto.get(0).getFeeKbn(), NaikaraTalkConstants.FEE_KBN_NORMAL)) {
	//            	sessionPurchasePoints.setGoodsDescription("購入ポイント：通常ポイント（ "
	//            			+ NaikaraStringUtil.addComma(pmmDto.get(0).getMoneyYen().toString()) + "円 ）");
	        		sb = new StringBuffer();
	        		sb.append("購入ポイント：通常ポイント（ ");
	        		sb.append(NaikaraStringUtil.addComma(pmmDto.get(0).getMoneyYen().toString()));
	        		sb.append("円 ）");
	            	sessionPurchasePoints.setGoodsDescription(sb.toString());
	            } else {
	//            	sessionPurchasePoints.setGoodsDescription("購入ポイント：月謝制（定額制）ポイント（ "
	//    			+ NaikaraStringUtil.addComma(pmmDto.get(0).getMoneyYen().toString()) + "円 ）");
	        		sb = new StringBuffer();
	        		sb.append("購入ポイント：月謝制（定額制）ポイント（ ");
	        		sb.append(NaikaraStringUtil.addComma(pmmDto.get(0).getMoneyYen().toString()));
	        		sb.append("円 ）");
	            	sessionPurchasePoints.setGoodsDescription(sb.toString());
	            }
	        	sessionPurchasePoints.setPaypalToken(token);
	        	sessionPurchasePoints.setPayerId(payerId);
	    		SessionDataUtil.setSessionData(sessionPurchasePoints);
	        }

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
*/
}
