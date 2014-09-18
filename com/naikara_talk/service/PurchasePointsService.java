package com.naikara_talk.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.model.PurchasePointsModel;
import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.PointManagMstDto;
import com.naikara_talk.dto.StudentMstDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.PurchasePointsLogic;

import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.sessiondata.SessionPurchasePoints;
import com.naikara_talk.sessiondata.SessionPayPal;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様利用画面<br>
 * <b>クラス名称       :</b>ポイント購入ページ<br>
 * <b>クラス概要       :</b>ポイント購入ページ初期処理Service<br>
 * <br>
 * <b>著作権           :</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/01 TECS 新規作成
 */
public class PurchasePointsService implements ActionService {

    /**
     * 初期処理<br>
     * <br>
     * 画面項目の初期処理を行う<br>
     * <br>
     * @param なし<br>
     * @return PurchasePointsModel<br>
     * @throws Exception
     * @exception なし
     */
    public PurchasePointsModel load(String pointCd) throws NaiException {

		Connection conn = null;

		try{
			conn = DbUtil.getConnection();

			PurchasePointsModel ppMdl = new PurchasePointsModel();
	        PurchasePointsLogic ppLogic = new PurchasePointsLogic(conn);

	        // 月謝（定額制）ポイント取得
	        ppMdl.setMonthlyList(ppLogic.selectList(pointCd, NaikaraTalkConstants.FEE_KBN_MONTHLY));
	        // 通常ポイント取得
	        ppMdl.setNormalList(ppLogic.selectList(pointCd, NaikaraTalkConstants.FEE_KBN_NORMAL));

	        return ppMdl;

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
     * 実行判定処理<br>
     * <br>
     * 実行可能判定処理を行う<br>
     * <br>
     * @param studentId:受講者ID<br>
     * @return booleam:判定結果<br>
     * @throws NaiException
     * @exception なし
     */
    public boolean execCheck(String studentId) throws NaiException {

		Connection conn = null;

		try{
			conn = DbUtil.getConnection();

			PurchasePointsLogic logic = new PurchasePointsLogic(conn);

			return logic.execCheck(studentId);

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
	public void sessionUserReset(String userId) throws NaiException {

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
	public void sessionPurchasePointsReset(String pointCd, String token, String payerId) throws NaiException {

		Connection conn = null;

		try{
			conn = DbUtil.getConnection();

			PurchasePointsLogic ppLogic = new PurchasePointsLogic(conn);

			List<PointManagMstDto> pmmDto = ppLogic.search(pointCd);

	        if(pmmDto.get(0).getReturnCode()==NaikaraTalkConstants.RETURN_CD_DATA_YES) {
	        	SessionPurchasePoints sessionPurchasePoints = new SessionPurchasePoints();
	        	sessionPurchasePoints.setReturnOnFlg(true);
	        	sessionPurchasePoints.setSelectPointCd(pmmDto.get(0).getPointCd());
	        	sessionPurchasePoints.setMoneyYen(pmmDto.get(0).getMoneyYen());
	        	sessionPurchasePoints.setFeeKbn(pmmDto.get(0).getFeeKbn());
	        	sessionPurchasePoints.setPaymentPoint(pmmDto.get(0).getPaymentPoint());
	        	sessionPurchasePoints.setPaymentPointTim(pmmDto.get(0).getPaymentPointTim());
	        	sessionPurchasePoints.setFreePoint(pmmDto.get(0).getFreePoint());
	        	sessionPurchasePoints.setFreePointTim(pmmDto.get(0).getFreePointTim());
	    		SessionDataUtil.setSessionData(sessionPurchasePoints);

	    		StringBuffer sb = new StringBuffer();
	        	SessionPayPal sessionPayPal = new SessionPayPal();
	            if (StringUtils.equals(pmmDto.get(0).getFeeKbn(), NaikaraTalkConstants.FEE_KBN_NORMAL)) {
	        		sb = new StringBuffer();
	        		sb.append("通常ポイント（ ");
	        		sb.append(NaikaraStringUtil.addComma(pmmDto.get(0).getMoneyYen().toString()));
	        		sb.append("円 ）");
	        		sessionPayPal.setGoodsDescription(sb.toString());
	            } else {
	        		sb = new StringBuffer();
	        		sb.append("月謝制（定額制）ポイント（ ");
	        		sb.append(NaikaraStringUtil.addComma(pmmDto.get(0).getMoneyYen().toString()));
	        		sb.append("円 ）");
	        		sessionPayPal.setGoodsDescription(sb.toString());
	            }
	            sessionPayPal.setPaypalToken(token);
	            sessionPayPal.setPayerId(payerId);
	    		SessionDataUtil.setSessionData(sessionPayPal);
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

}
