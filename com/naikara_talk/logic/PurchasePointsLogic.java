package com.naikara_talk.logic;

import java.util.List;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.sql.Connection;

import com.naikara_talk.dao.StudentMstDao;
import com.naikara_talk.dao.TableCountDao;
import com.naikara_talk.dao.PointManagMstDao;
import com.naikara_talk.dao.PointOwnershipTrnDao;
import com.naikara_talk.dto.StudentMstDto;
import com.naikara_talk.dto.OrderNumberDto;
import com.naikara_talk.dto.PointOwnershipTrnDto;
import com.naikara_talk.dto.PointManagMstDto;
import com.naikara_talk.dto.PointExpirationDto;
import com.naikara_talk.dto.AgeCalculateDto;
import com.naikara_talk.model.PointsListModel;
import com.naikara_talk.sessiondata.ScreenComInfo;
import com.naikara_talk.sessiondata.SessionPurchasePoints;
import com.naikara_talk.sessiondata.SessionPayPal;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.batch.SendMailBatch;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様利用画面<br>
 * <b>クラス名称       :</b>ポイント購入ページ<br>
 * <b>クラス概要       :</b>ポイント購入ページ初期処理Logic<br>
 * <br>
 * <b>著作権           :</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴         :</b>2013/07/19 TECS 新規作成。
 *                      </b>2014/01/03 TECS 変更 スクールのメール送信・受信履歴照会に伴う対応
 */
public class PurchasePointsLogic {

    // ログのの初期化
    Logger log = Logger.getLogger(PurchasePointsLogic.class);

	/* 結果OK */
    public static final int RESULT_OK = 0;
    /* 結果NG 採番ID取得エラー*/
    public static final int ORDER_NUMBER_MST_ERR = 1;
    /* 結果NG ポイント所有テーブル登録エラー*/
    public static final int POINT_OWNER_INSRT_ERR = 2;
    /* 結果NG 受講者マスタ更新エラー*/
    public static final int STUDENT_MST_UPDT_ERR = 3;
    /* 結果NG 受講者マスタ取得エラー*/
    public static final int STUDENT_MST_NO_DATA_ERR = 4;
    /* 結果NG 受講者マスタ重複エラー*/
    public static final int STUDENT_MST_MULTIPLE_DATA_ERR = 5;

	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public PurchasePointsLogic(Connection con) {
		this.conn = con;
	}

	/**
	 * ポイント管理マスタ取得処理(Return:Dto)<br>
	 * <br>
     * @param point code
     * @return List<PointManagMstDto>
     * @throws NaiException
	 */
    public List<PointManagMstDto> search(String pointCd) throws NaiException {

        PointManagMstDao pmmDao = new PointManagMstDao(this.conn);
		// 購入日
		String PurchaseDate = DateUtil.getSysDate();

		// 抽出条件の設定
		ConditionMapper cm = new ConditionMapper();
		if(pointCd!=null && !StringUtils.equals(pointCd, NaikaraTalkConstants.BRANK)) {
			cm.addCondition(PointManagMstDao.COND_POINT_CD, ConditionMapper.OPERATOR_EQUAL, pointCd);
		}
		cm.addCondition(PointManagMstDao.COND_USE_STR_DT, ConditionMapper.OPERATOR_LESS_EQUAL, PurchaseDate);
		cm.addCondition(PointManagMstDao.COND_USE_END_DT, ConditionMapper.OPERATOR_LARGE_EQUAL, PurchaseDate);

		// 並び順：金額の昇順
        OrderCondition oc = new OrderCondition();
        oc.addCondition(PointManagMstDao.COND_MONEY_YEN, OrderCondition.ASC);

        // 一覧データを取得する
        return pmmDao.search(cm, oc);

    }

	/**
	 * ポイント管理マスタ取得処理(Return:Model)<br>
	 * <br>
     * @param feeKbn:通常月謝区分
     * @return List<PointsListModel>
     * @throws NaiException
	 */
    public List<PointsListModel> selectList(String pointCd, String feeKbn) throws NaiException {

        List<PointManagMstDto> pmmDto = new ArrayList<PointManagMstDto>();
        PointManagMstDao pmmDao = new PointManagMstDao(this.conn);
		// 購入日
		String PurchaseDate = DateUtil.getSysDate();

		// 抽出条件の設定
		ConditionMapper cm = new ConditionMapper();
		if(pointCd!=null && !StringUtils.equals(pointCd, NaikaraTalkConstants.BRANK)) {
			cm.addCondition(PointManagMstDao.COND_POINT_CD, ConditionMapper.OPERATOR_EQUAL, pointCd);
		}
		if(feeKbn!=null && !StringUtils.equals(feeKbn, NaikaraTalkConstants.BRANK)) {
			cm.addCondition(PointManagMstDao.COND_FEE_KBN, ConditionMapper.OPERATOR_EQUAL, feeKbn);
		}
		cm.addCondition(PointManagMstDao.COND_USE_STR_DT, ConditionMapper.OPERATOR_LESS_EQUAL, PurchaseDate);
		cm.addCondition(PointManagMstDao.COND_USE_END_DT, ConditionMapper.OPERATOR_LARGE_EQUAL, PurchaseDate);

		// 並び順：金額の昇順
        OrderCondition oc = new OrderCondition();
        oc.addCondition(PointManagMstDao.COND_MONEY_YEN, OrderCondition.ASC);

        // 一覧データを取得する
        pmmDto = pmmDao.search(cm, oc);

        // 戻り値
        return this.pointListEdit(pmmDto);

    }

	/**
	 * Point List Edit<br>
	 * <br>
     * @param  PointManagMstDto
     * @return PointsListModel
     * @throws NaiException
	 */
    private List<PointsListModel> pointListEdit(List<PointManagMstDto> pmmDto) throws NaiException {

        PointsListModel plMdl = new PointsListModel();
        List<PointsListModel> rsltMdl = new ArrayList<PointsListModel>();

        PointExpirationDto pxDto = new PointExpirationDto();
        PointExpirationLogic pxLogic = new PointExpirationLogic();

        BigDecimal wkBD = new BigDecimal(0);
		StringBuffer sb = new StringBuffer();
		// 購入日
		String PurchaseDate = DateUtil.getSysDate();

        if(pmmDto.size()>0) {
        	if(pmmDto.get(0).getReturnCode()==0) {
                for (int i=0; i < pmmDto.size(); i++) {
                    plMdl = new PointsListModel();
        			plMdl.setPointCd(pmmDto.get(i).getPointCd());
        			plMdl.setMoneyYen(NaikaraStringUtil.addComma(pmmDto.get(i).getMoneyYen().toString()));
        			plMdl.setPaymentPoint(NaikaraStringUtil.addComma(pmmDto.get(i).getPaymentPoint().toString()));
        			pxDto = new PointExpirationDto();
        			pxDto = pxLogic.pointExpiration(PurchaseDate, pmmDto.get(i).getPaymentPointTim());
        			if(pxDto.getReturnCode()==0) {
            			plMdl.setPaymentUseEndDt(pxDto.getAge());
        			}
        			if(StringUtils.equals(pmmDto.get(i).getFreePoint().toString(), BigDecimal.ZERO.toString())) {
            			plMdl.setSumPoint("(無償特典ポイントなし)");
            			plMdl.setFreePoint(pmmDto.get(i).getFreePoint().toString());
        			} else {
            			wkBD = BigDecimal.ZERO;
        				sb = new StringBuffer();
        				sb.append("(無償特典ポイントと合わせて");
            			wkBD = wkBD.add(pmmDto.get(i).getPaymentPoint());
            			wkBD = wkBD.add(pmmDto.get(i).getFreePoint());
        				sb.append(NaikaraStringUtil.addComma(wkBD.toString()));
        				sb.append("ポイント)");
            			plMdl.setSumPoint(sb.toString());
        				sb = new StringBuffer();
        				sb.append("+");
        				sb.append(NaikaraStringUtil.addComma(pmmDto.get(i).getFreePoint().toString()));
        				plMdl.setFreePoint(sb.toString());
        			}
        			if(!StringUtils.equals(pmmDto.get(i).getFreePoint().toString(), BigDecimal.ZERO.toString())) {
	        			pxDto = new PointExpirationDto();
	        			pxDto = pxLogic.pointExpiration(PurchaseDate, pmmDto.get(i).getFreePointTim());
	        			if(pxDto.getReturnCode()==0) {
	            			plMdl.setFreeUseEndDt(pxDto.getAge());
	        			}
        			}
        			rsltMdl.add(plMdl);
        		}
        	}
        }

    	return rsltMdl;

    }

	/**
	 * ポイント所有マスタ登録処理<br>
	 * <br>
     * @param -
     * @return int : result
     * @throws NaiException
	 */
    public int pointOwnershipTrnInsert() throws NaiException {

    	PointOwnershipTrnDao potDao = new PointOwnershipTrnDao(this.conn);
    	PointOwnershipTrnDto potDto = new PointOwnershipTrnDto();

    	OrderNumbersMstLogic onmLogic = new OrderNumbersMstLogic();
		OrderNumberDto onDto = new OrderNumberDto();

		PointExpirationLogic pxLogic = new PointExpirationLogic();
		PointExpirationDto pxDto = new PointExpirationDto();

        String OwnershipId = "";
        String OwnershipIdNext = "";

		// 受講者ID
		String studentId = ((SessionUser)SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();

		// ポイントコード
        String pointCd = ((SessionPurchasePoints) SessionDataUtil.
        		getSessionData(SessionPurchasePoints.class.toString())).getSelectPointCd();

        // 金額
        BigDecimal moneyYen = ((SessionPurchasePoints) SessionDataUtil.
        		getSessionData(SessionPurchasePoints.class.toString())).getMoneyYen();

        // 通常月謝区分
        String feeKbn = ((SessionPurchasePoints) SessionDataUtil.
        		getSessionData(SessionPurchasePoints.class.toString())).getFeeKbn();

        // 有償ポイント
        BigDecimal paymentPoint = ((SessionPurchasePoints) SessionDataUtil.
        		getSessionData(SessionPurchasePoints.class.toString())).getPaymentPoint();

        // 有償ポイント期限
		int paymentPointTim = ((SessionPurchasePoints) SessionDataUtil.
        		getSessionData(SessionPurchasePoints.class.toString())).getPaymentPointTim();

		// 無償ポイント
        BigDecimal freePoint = ((SessionPurchasePoints) SessionDataUtil.
        		getSessionData(SessionPurchasePoints.class.toString())).getFreePoint();

        // 無償ポイント期限
		int freePointTim = ((SessionPurchasePoints) SessionDataUtil.
        		getSessionData(SessionPurchasePoints.class.toString())).getFreePointTim();

		// プロファイルID（月謝制（定額制）ポイント購入）
		String profileId = ((SessionPayPal) SessionDataUtil.
        		getSessionData(SessionPayPal.class.toString())).getProfileId();

		// 購入日
		String purchaseDate = DateUtil.getSysDate();

		// 購入日(dd)
		String purchaseDay = DateUtil.getSysDatedd();

		// 購入日 + 1ヶ月 monthCalculation
		String purchaseDateAddMonth = "";
		pxDto = new PointExpirationDto();
		pxDto = pxLogic.monthCalculation(purchaseDate, 1);
		if(pxDto.getReturnCode()==NaikaraTalkConstants.RETURN_CD_DATA_YES) {
			purchaseDateAddMonth = pxDto.getAge();
		}

		// 購入時間
		String purchaseTime = DateUtil.getSysTime();

		// 所有IDの取得
		onDto = onmLogic.getOrderNumber(NaikaraTalkConstants.ORDER_NUMBERS_OW, purchaseDate);
		// ポイント所有ID取得
		if(onDto.getReturnCode()==NaikaraTalkConstants.RETURN_CD_DATA_NO) {
			return ORDER_NUMBER_MST_ERR;
		}
		// ポイント所有ID
		OwnershipId = onDto.getOrderNumber();

		// 月謝制（定額制）購入
    	if(StringUtils.equals(feeKbn, NaikaraTalkConstants.FEE_KBN_MONTHLY)) {
    		onDto = onmLogic.getOrderNumber(NaikaraTalkConstants.ORDER_NUMBERS_OW, purchaseDate);
    		// ポイント所有ID取得
    		if(onDto.getReturnCode()==NaikaraTalkConstants.RETURN_CD_DATA_NO) {
    			return ORDER_NUMBER_MST_ERR;
    		}
    		// ポイント所有ID（翌月分のID）
    		OwnershipIdNext = onDto.getOrderNumber();
		}

		// ポイント所有マスタデータ設定（有償ポイント）通常月謝共通
		potDto = new PointOwnershipTrnDto();
		potDto.setOwnershipId(OwnershipId);
    	potDto.setCompensationFreeKbn(NaikaraTalkConstants.COMPENSATION_FREE_KBN_YES);
    	potDto.setStudentId(studentId);
    	potDto.setEffectiveStrDt(purchaseDate);
		pxDto = new PointExpirationDto();
		pxDto = pxLogic.pointExpiration(purchaseDate, paymentPointTim);
		if(pxDto.getReturnCode()==0) {
			potDto.setEffectiveEndDt(NaikaraStringUtil.delSlash(pxDto.getAge()));
		}
    	potDto.setFeeKbn(feeKbn);
    	potDto.setPurchaseYen(moneyYen);
    	potDto.setPurchaseFreePoint(moneyYen);
    	potDto.setBalancePoint(paymentPoint);
    	potDto.setPurchaseDt(purchaseDate);
    	potDto.setPurchaseTm(purchaseTime);
    	potDto.setPointCd(pointCd);
    	potDto.setScreenSystemKbn(NaikaraTalkConstants.SCREEN_SYSTEM_KBN_SCREEN);
    	if(StringUtils.equals(feeKbn, NaikaraTalkConstants.FEE_KBN_MONTHLY)) {
        	potDto.setBeginningPurchaseDt(purchaseDate);
        	potDto.setBeginningPurchaseTm(purchaseTime);
        	potDto.setProfileId(profileId);
    	}
    	potDto.setEndFlg(NaikaraTalkConstants.END_FLG_NO);
    	potDto.setRecordVerNo(0);
    	potDto.setInsertCd(studentId);
    	potDto.setUpdateCd(studentId);

    	// ポイント所有テーブル登録結果（有償ポイント）通常月謝共通
        if(potDao.insert(potDto)!=1) {
			return POINT_OWNER_INSRT_ERR;
        } else {
    		// ポイント所有マスタデータ設定（有償ポイント）月謝制翌月
        	if(StringUtils.equals(feeKbn, NaikaraTalkConstants.FEE_KBN_MONTHLY)) {
        		potDto.setOwnershipId(OwnershipIdNext);
            	potDto.setEffectiveStrDt(purchaseDateAddMonth);
        		pxDto = new PointExpirationDto();
        		pxDto = pxLogic.pointExpiration(purchaseDate, paymentPointTim + 1);
        		if(pxDto.getReturnCode()==0) {
        			potDto.setEffectiveEndDt(NaikaraStringUtil.delSlash(pxDto.getAge()));
        		}
            	potDto.setPurchaseDt(purchaseDateAddMonth);
            	potDto.setBeginningPurchaseDt(purchaseDate);
            	potDto.setBeginningPurchaseTm(purchaseTime);

            	// ポイント所有テーブル登録結果（有償ポイント）月謝制翌月
                if(potDao.insert(potDto)!=1) {
        			return POINT_OWNER_INSRT_ERR;
                }
        	}
        }

		// ポイント所有マスタデータ設定（無償ポイント）
        if(freePoint.compareTo(BigDecimal.ZERO)>0){
	        potDto = new PointOwnershipTrnDto();
			potDto.setOwnershipId(OwnershipId);
	    	potDto.setCompensationFreeKbn(NaikaraTalkConstants.COMPENSATION_FREE_KBN_NO);
	    	potDto.setStudentId(studentId);
	    	potDto.setEffectiveStrDt(purchaseDate);
			pxDto = new PointExpirationDto();
			pxDto = pxLogic.pointExpiration(purchaseDate, freePointTim);
			if(pxDto.getReturnCode()==0) {
				potDto.setEffectiveEndDt(NaikaraStringUtil.delSlash(pxDto.getAge()));
			}
	    	potDto.setFeeKbn(feeKbn);
	    	potDto.setPurchaseYen(BigDecimal.ZERO);
	    	potDto.setPurchaseFreePoint(freePoint);
	    	potDto.setBalancePoint(freePoint);
	    	potDto.setPurchaseDt(purchaseDate);
	    	potDto.setPurchaseTm(purchaseTime);
	    	potDto.setPointCd(pointCd);
	    	potDto.setScreenSystemKbn(NaikaraTalkConstants.SCREEN_SYSTEM_KBN_SCREEN);
	    	if(StringUtils.equals(feeKbn, NaikaraTalkConstants.FEE_KBN_MONTHLY)) {
		    	potDto.setBeginningPurchaseDt(purchaseDate);
		    	potDto.setBeginningPurchaseTm(purchaseTime);
	        	potDto.setProfileId(profileId);
	    	}
	    	potDto.setEndFlg(NaikaraTalkConstants.END_FLG_NO);
	    	potDto.setRecordVerNo(0);
	    	potDto.setInsertCd(studentId);
	    	potDto.setUpdateCd(studentId);

	    	// ポイント所有テーブル登録結果（無償ポイント）
	        if(potDao.insert(potDto)!=1) {
				return POINT_OWNER_INSRT_ERR;
	        } else {
	    		// ポイント所有マスタデータ設定（無償ポイント）月謝制翌月
	        	if(StringUtils.equals(feeKbn, NaikaraTalkConstants.FEE_KBN_MONTHLY)) {
	    			potDto.setOwnershipId(OwnershipIdNext);
	    	    	potDto.setEffectiveStrDt(purchaseDateAddMonth);
	    			pxDto = new PointExpirationDto();
	    			pxDto = pxLogic.pointExpiration(purchaseDate, freePointTim + 1);
	    			if(pxDto.getReturnCode()==0) {
	    				potDto.setEffectiveEndDt(NaikaraStringUtil.delSlash(pxDto.getAge()));
	    			}
	            	potDto.setPurchaseDt(purchaseDateAddMonth);
	    	    	potDto.setBeginningPurchaseDt(purchaseDate);
	    	    	potDto.setBeginningPurchaseTm(purchaseTime);

	            	// ポイント所有テーブル登録結果（無償ポイント）月謝制翌月
	                if(potDao.insert(potDto)!=1) {
	        			return POINT_OWNER_INSRT_ERR;
	                }
        		}
        	}
        }

		// ポイント未購入受講者マスタ取得
        List<StudentMstDto> smlDto = new ArrayList<StudentMstDto>();
        StudentMstDto smDto = new  StudentMstDto();
        StudentMstDao smDao = new StudentMstDao(this.conn);
        TableCountDao tcDao = new TableCountDao(this.conn);

        ConditionMapper cm = new ConditionMapper();
        OrderCondition oc = new OrderCondition();

		// 抽出条件の設定
		cm.addCondition(StudentMstDao.COND_STUDENT_ID, ConditionMapper.OPERATOR_EQUAL, studentId);
		cm.addCondition(StudentMstDao.COND_POINT_PURCHASE_FLG, ConditionMapper.OPERATOR_EQUAL, NaikaraTalkConstants.POINT_PURCHASE_FLG_NO);
		cm.addCondition(StudentMstDao.COND_USE_STR_DT, ConditionMapper.OPERATOR_LESS_EQUAL, purchaseDate);
		cm.addCondition(StudentMstDao.COND_USE_END_DT, ConditionMapper.OPERATOR_LARGE_EQUAL, purchaseDate);

		// データを確認する
		int smCnt = tcDao.rowCount(NaikaraTalkConstants.STUDENT_MST, cm);
		if(smCnt==1){
            // データを取得する
			smlDto = smDao.search(cm, oc);

			if(smlDto.size()==1) {
				smDto = smlDto.get(0);
				smDto.setPointPurchaseFlg(NaikaraTalkConstants.POINT_PURCHASE_FLG_YES);
				smDto.setUpdateCd(studentId);

				// 受講者マスタの更新
				if(smDao.update(smDto)!=1) {
					return STUDENT_MST_UPDT_ERR;
				}

				log.info(NaikaraStringUtil.unionProcesslog("ScreenComInfo PointPurchaseFlg-Update"));

				// ScreenComInfoへのポイント購入済フラグの設定
				((ScreenComInfo) SessionDataUtil.getSessionData(ScreenComInfo.class.toString()))
				.setPointPurchaseFlg(smDto.getPointPurchaseFlg());

			} else {
				return STUDENT_MST_NO_DATA_ERR;
			}
        }
		if(smCnt>1){
			return STUDENT_MST_MULTIPLE_DATA_ERR;
		}

		// 受講者情報再取得
		smlDto = new ArrayList<StudentMstDto>();
		cm = new ConditionMapper();
		oc = new OrderCondition();

		cm.addCondition(StudentMstDao.COND_STUDENT_ID, ConditionMapper.OPERATOR_EQUAL, studentId);

		smlDto = smDao.search(cm, oc);
		if (smlDto.size() != 1) {
			return STUDENT_MST_NO_DATA_ERR;
		}

		// 購入者名
		String stPurchaser = NaikaraStringUtil.unionName(smlDto.get(0).getFamilyJnm(), smlDto.get(0).getFirstJnm());

		// メール送信処理
		SendMailBatch smBatch = new SendMailBatch(this.conn);

		// ###メール送信リスト編集###

		// メールパターンコード:PPU or PPM
        String mailPatternCd = "";
        String mailPatternCdHis = "";  // 2014/01/03-スクールのメール送信・受信履歴照会に伴う対応
    	if(StringUtils.equals(feeKbn, NaikaraTalkConstants.FEE_KBN_NORMAL)) {
    		// ポイント購入（通常）
    		mailPatternCd = NaikaraTalkConstants.MAIL_PATTERN_CODE_PPU;
    		mailPatternCdHis = NaikaraTalkConstants.MAIL_PATTERN_POINT_PURCHASE_NORMAL;   // 2014/01/03-スクールのメール送信・受信履歴照会に伴う対応
		} else {
    		// ポイント購入（月謝）
    		mailPatternCd = NaikaraTalkConstants.MAIL_PATTERN_CODE_PPM;
    		mailPatternCdHis = NaikaraTalkConstants.MAIL_PATTERN_POINT_PURCHASE_MONTHLY;  // 2014/01/03-スクールのメール送信・受信履歴照会に伴う対応
		}

		// 送信者:nothing
		String sendFrom = "";

		// 送信者名:nothing
		String sendFromNm = "";

		// 宛先IDリスト:受講者ID
		List<String> sendIdList = new ArrayList<String>();
    	sendIdList.add(studentId);

		// 宛先リスト:受講者マスタ．メールアドレス1
		List<String> sendToList = new ArrayList<String>();
    	sendToList.add(smlDto.get(0).getMailAddress1());

		// CC:nothing
		String cc = "";

		// BCC:nothing(MAIL_MANAG_MSTに設定される)
		String bcc = "";

		// 件名リスト:nothing
		List<String> subjectTitleList = new ArrayList<String>();
		subjectTitleList.add("");

		// 本文リスト:1-20,29
		List<String> list = new ArrayList<String>();
		List<List<String>> mailTextList = new ArrayList<List<String>>();

		// 本文リスト:1（名前(姓) & 名前(名））
		list = new ArrayList<String>();
		list.add(stPurchaser);
		mailTextList.add(0, list);

		// 本文リスト:2-6（""）
		list = new ArrayList<String>();
		list.add(NaikaraTalkConstants.BRANK);
		mailTextList.add(1, list);
		mailTextList.add(2, list);
		mailTextList.add(3, list);
		mailTextList.add(4, list);
		mailTextList.add(5, list);

		// 本文リスト:7（購入日 + " " + 購入時刻）
		list = new ArrayList<String>();
		list.add(NaikaraStringUtil.unionName(NaikaraStringUtil.converToYY_MM_DD(purchaseDate),
				NaikaraStringUtil.converToHH_MM(purchaseTime)));
		mailTextList.add(6, list);

		// 本文リスト:8（購入ポイント）
		list = new ArrayList<String>();
		list.add(NaikaraStringUtil.addZenkakuComma(paymentPoint.toString()));
		mailTextList.add(7, list);

		// 本文リスト:9（無料ポイント）
		list = new ArrayList<String>();
		list.add(NaikaraStringUtil.addZenkakuComma(freePoint.toString()));
		mailTextList.add(8, list);

		// 本文リスト:10（購入金額）
		list = new ArrayList<String>();
		list.add(NaikaraStringUtil.addZenkakuComma(moneyYen.toString()));
		mailTextList.add(9, list);

		if(StringUtils.equals(feeKbn, NaikaraTalkConstants.FEE_KBN_NORMAL)) {
			// 本文リスト:11-20（""）
			list = new ArrayList<String>();
			list.add(NaikaraTalkConstants.BRANK);
			mailTextList.add(10, list);
			mailTextList.add(11, list);
			mailTextList.add(12, list);
			mailTextList.add(13, list);
			mailTextList.add(14, list);
			mailTextList.add(15, list);
			mailTextList.add(16, list);
			mailTextList.add(17, list);
			mailTextList.add(18, list);
			mailTextList.add(19, list);
		} else {
			// 本文リスト:11-15（""）
			list = new ArrayList<String>();
			list.add(NaikaraTalkConstants.BRANK);
			mailTextList.add(10, list);
			mailTextList.add(11, list);
			mailTextList.add(12, list);
			mailTextList.add(13, list);
			mailTextList.add(14, list);
			// 本文リスト:16（購入日(dd)）
			list = new ArrayList<String>();
			list.add(purchaseDay);
			mailTextList.add(15, list);
			// 本文リスト:17-19（""）
			list = new ArrayList<String>();
			list.add(NaikaraTalkConstants.BRANK);
			mailTextList.add(16, list);
			mailTextList.add(17, list);
			mailTextList.add(18, list);
    		// 本文リスト:20（購入日(dd)）
			list = new ArrayList<String>();
			list.add(purchaseDay);
    		mailTextList.add(19, list);
			// 本文リスト:21-29（""）
			list = new ArrayList<String>();
			list.add(NaikaraTalkConstants.BRANK);
			mailTextList.add(20, list);
			mailTextList.add(21, list);
			mailTextList.add(22, list);
			mailTextList.add(23, list);
			mailTextList.add(24, list);
			mailTextList.add(25, list);
			mailTextList.add(26, list);
			mailTextList.add(27, list);
			mailTextList.add(28, list);
    	}

		// 添付:nothing
		String file = "";

		// 予約No:nothing
		String reservationNo = "";
/*
		System.out.println("---  メールパターンコード:["+mailPatternCd+"]   ---");
		System.out.println("---  送信者:nothing:["+sendFrom+"]  ---");
		System.out.println("---  送信者名:nothing:["+sendFromNm+"]  ---");
		System.out.println("---  宛先IDリスト:受講者ID:["+sendIdList+"]  ---");
		System.out.println("---  宛先リスト:受講者マスタ．メールアドレス1:["+sendToList+"]  ---");
		System.out.println("---  CC:nothing:["+cc+"]  ---");
		System.out.println("---  BCC:nothing:["+bcc+"]  ---");
		System.out.println("---  件名リスト:nothing:["+subjectTitleList+"]  ---");
		System.out.println("---  本文リスト:["+mailTextList+"]  ---");
		System.out.println("---  添付:nothing:["+file+"]  ---");
		System.out.println("---  予約No:nothing:["+reservationNo+"]  ---");
*/
		// メール送信処理
    	try {
			smBatch.sendMail(mailPatternCd, sendFrom, sendFromNm, sendIdList,
					sendToList, cc, bcc, subjectTitleList, mailTextList, file, reservationNo);

            // 2014/01/03-スクールのメール送信・受信履歴照会に伴う対応-Start
            // 共通：スクールのメール送信・受信履歴データ作成Logicクラスの生成
            SmailAccountHistoryLogic sAMHiLogic = new SmailAccountHistoryLogic(conn);

            // ユーザIDを取得
            String userId = NaikaraTalkConstants.BRANK;
            if ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString()) != null) {
                userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();
            }

            // スクールメール・アカウント変更履歴テーブルの更新処理
            sAMHiLogic.smailActHistoryInsertMain(mailPatternCdHis,
            		userId, studentId);
            // 2014/01/03-スクールのメール送信・受信履歴照会に伴う対応-End


		} catch (Exception e) {
			throw new NaiException(e);
		}

    	return RESULT_OK;
	}

    /**
     * 受講者情報取得<br>
     * <br>
     * 受講者情報取得を行う<br>
     * <br>
     * @param studentId ログインＩＤ<br>
     * @return 結果 StudentMstDto<br>
     * @exception Exception
     */
    public List<StudentMstDto> searchStudent(String studentId) throws NaiException {

        StudentMstDao smDao = new StudentMstDao(this.conn);
		// 購入日
		String useDate = DateUtil.getSysDate();

		// 抽出条件の設定
		ConditionMapper cm = new ConditionMapper();
		cm.addCondition(StudentMstDao.COND_STUDENT_ID, ConditionMapper.OPERATOR_EQUAL, studentId);
		cm.addCondition(StudentMstDao.COND_USE_STR_DT, ConditionMapper.OPERATOR_LESS_EQUAL, useDate);
		cm.addCondition(StudentMstDao.COND_USE_END_DT, ConditionMapper.OPERATOR_LARGE_EQUAL, useDate);

		// 並び順：金額の昇順
		OrderCondition oc = new OrderCondition();

		// 一覧データを取得する
		return smDao.search(cm, oc);

    }

    /**
     * 実行判定処理<br>
     * <br>
     * 実行可能判定処理を行う<br>
     * <br>
     * @param studentId:受講者ID<br>
     * @return boolean:判定結果<br>
     * @exception なし
     */
    public boolean execCheck(String studentId) throws NaiException {

    	StudentMstDao dao = new StudentMstDao(this.conn);
    	List<StudentMstDto> dto = new ArrayList<StudentMstDto>();

		// 抽出条件の設定
		ConditionMapper cm = new ConditionMapper();
		cm.addCondition(StudentMstDao.COND_STUDENT_ID, ConditionMapper.OPERATOR_EQUAL, studentId);

		// 並び順：金額の昇順
		OrderCondition oc = new OrderCondition();

		// 一覧データを取得する
		dto = dao.search(cm, oc);

		// 受講者情報取得のチェック：失敗はfalseを返す)
		if (dto.size() < 1) {
			return false;
		}
		if (dto.get(0).getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_NO) {
			// データ無の場合
			return false;
		}

		// 顧客区分チェック：0001(法人はfalseを返す)
		if (StringUtils.equals(dto.get(0).getCustomerKbn(), NaikaraTalkConstants.CUSTOMER_KBN_ORGANIZATION)) {
			return false;
		}

		// 年齢チェック：0002(個人)＆未成年＆保護者同意書なし(falseを返す)
		AgeCalculateLogic acLogic = new AgeCalculateLogic();
		AgeCalculateDto acDto = new AgeCalculateDto();

        String birthYyyy = NaikaraStringUtil.nvlString(dto.get(0).getBirthYyyy()).toString();
        String birthMm = NaikaraStringUtil.nvlString(dto.get(0).getBirthMm()).toString();
        String birthDd = NaikaraStringUtil.nvlString(dto.get(0).getBirthDd()).toString();
        String birth = NaikaraStringUtil.converToYYYYMMDD(NaikaraStringUtil.unionString2(birthYyyy,
                NaikaraStringUtil.unionString2(birthMm, birthDd)));

		String strDate = DateUtil.getSysDate();

		// 共通部品：年齢の算出
		acDto = acLogic.ageCalculate(birth, strDate);
		// 年齢チェックエラー
		if (acDto.getReturnCode() == NaikaraTalkConstants.RETURN_CD_DATA_NO) {
			// データ無の場合
			return false;
		}
		// 未成年＆保護者同意書なしチェック
		if (acDto.getAdult() == AgeCalculateLogic.ADULT_KBN_MINORITY  &&
			StringUtils.equals(dto.get(0).getConsentDocumentAcquisitionFlg(),
					NaikaraTalkConstants.PARENTAL_CONSENT_FLG_NO)) {
			return false;
		}

		return true;

    }

}