package com.naikara_talk.logic;

import java.util.List;
import java.util.ArrayList;

import java.math.BigDecimal;

import java.sql.Connection;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dao.PointOwnershipTrnDao;
import com.naikara_talk.dao.SaleGoodsMstDao;
import com.naikara_talk.dao.GoodsPurchaseTrnDao;
import com.naikara_talk.dao.StudentMstDao;
import com.naikara_talk.dao.UserMstDao;
import com.naikara_talk.dto.OrderNumberDto;
import com.naikara_talk.dto.GoodsMstDto;
import com.naikara_talk.dto.GoodsPurchaseTrnDto;
import com.naikara_talk.dto.StudentMstDto;
import com.naikara_talk.dto.UserMstDto;
import com.naikara_talk.model.GoodsListModel;
import com.naikara_talk.mstcache.CodeManagMstCache;

import com.naikara_talk.batch.SendMailBatch;

import com.naikara_talk.dto.LessonReserveCancelResultListDto;
import com.naikara_talk.dto.PointProvisionDataListDto;
import com.naikara_talk.dto.PointOwnershipTrnListDto;
import com.naikara_talk.dto.PointProvisionTrnDto;

import com.naikara_talk.dao.PointProvisionTrnDao;


import com.naikara_talk.sessiondata.SessionPurchaseGoods;
import com.naikara_talk.sessiondata.SessionPurchaseGoodsTContactInfo;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.exception.NaiException;
//import com.naikara_talk.batch.SendMailBatch;

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
 *                     :</b>2014/02/18 TECS 商品購入ページからの呼出追加に伴う修正
 */
public class PurchaseGoodsLogic {

	/* 結果OK */
    public static final int RESULT_OK = 0;

    // ポイント引当テーブルなしエラー
    public static final int POINT_PROVISION_TRN_ERR = 1;

	// 購入ID取得エラー
    public static final int ORDER_NUMBERING_ERR = 2;

	//ポイント引当テーブル登録エラー
    public static final int POINT_INSERT_ERR = 3;

	//ポイント所有テーブル更新エラー*
    public static final int POINT_UPDATE_ERR = 4;

	// ポイント引当エラー（ポイント不足）
    public static final int POINT_SHORTAGE_ERR = 5;

	// メール用の単位（ポイント）
    public static final String UNIT_POINT = "ポイント";

	// メール用の単位（円）
    public static final String UNIT_YEN = "円";

    // 2014/02/18 Add Start 商品購入ページからの呼出追加に伴う修正
    /* 講師情報出力時の固定文字列 */
    public static final String FIX_TEACHER_INFO = "(講師への商品購入確認通知メール) ";

    /* 講師IDと講師ニックネームを結合する際に利用する */
    public static final String STRING_COLON = ":";
    // 2014/02/18 Add End   商品購入ページからの呼出追加に伴う修正

    // コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public PurchaseGoodsLogic(Connection con) {
		this.conn = con;
	}

	/**
	 * ポイント管理マスタポイント残高取得処理<br>
	 * <br>
     * @param nothing
     * @return BigDecimal:pointBalanceTotal
     * @throws NaiException
	 */
    public BigDecimal getPointBalance() throws NaiException {

    	PointOwnershipTrnDao postDao = new PointOwnershipTrnDao(this.conn);
		// 購入日
		String PurchaseDate = DateUtil.getSysDate();

		// 抽出条件の設定
		ConditionMapper cm = new ConditionMapper();

		// 受講者ID
		String studentId = ((SessionUser)SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();
		cm.addCondition(PointOwnershipTrnDao.COND_STUDENT_ID, ConditionMapper.OPERATOR_EQUAL, studentId);

		// 有効開始日
		cm.addCondition(PointOwnershipTrnDao.COND_EFFECTIVE_STR_DT, ConditionMapper.OPERATOR_LESS_EQUAL, PurchaseDate);

		// 有効終了日
		cm.addCondition(PointOwnershipTrnDao.COND_EFFECTIVE_END_DT, ConditionMapper.OPERATOR_LARGE_EQUAL, PurchaseDate);

        // ポイント残高を取得する
        return postDao.getPointBalance(cm);

    }

	/**
	 * 販売商品マスタ取得処理<br>
	 * <br>
	 * @param nothing
	 * @return List<GoodsListModel>
	 * @throws NaiException
	 */
    public List<GoodsListModel> goodsList() throws NaiException {

    	List<GoodsMstDto> gmDto = new ArrayList<GoodsMstDto>();
    	SaleGoodsMstDao sgmDao = new SaleGoodsMstDao(this.conn);
		// 購入日
		String PurchaseDate = DateUtil.getSysDate();

		// 抽出条件の設定
		ConditionMapper cm = new ConditionMapper();

		// 商品コード
		String[] strGoodsCd = {""};
		if (((SessionPurchaseGoods)SessionDataUtil.getSessionData(SessionPurchaseGoods.class.toString())) != null) {
			String[] wkGoodsCdCourseCd =((SessionPurchaseGoods)SessionDataUtil.getSessionData(SessionPurchaseGoods.class.toString())).getSelectChk();
			strGoodsCd = new String[wkGoodsCdCourseCd.length];
			for (int i=0; i < wkGoodsCdCourseCd.length; i++) {
				String[] wkGoodsCdCourseCdSplit = wkGoodsCdCourseCd[i].split(NaikaraTalkConstants.HALF_SPACE);
				strGoodsCd[i] = wkGoodsCdCourseCdSplit[0];
			}
		}
		cm.addCondition(SaleGoodsMstDao.COND_GOODS_CD, strGoodsCd);

		// 提供開始日≦購入日≦終了日
		cm.addCondition(SaleGoodsMstDao.COND_USE_STR_DT, ConditionMapper.OPERATOR_LESS_EQUAL, PurchaseDate);
		cm.addCondition(SaleGoodsMstDao.COND_USE_END_DT, ConditionMapper.OPERATOR_LARGE_EQUAL, PurchaseDate);

		// 並び順：金額の昇順
		OrderCondition oc = new OrderCondition();
		oc.addCondition(SaleGoodsMstDao.COND_GOODS_CD, OrderCondition.ASC);

		// 一覧データを取得する
		gmDto = sgmDao.search(cm, oc);

		// 戻り値 (取得した値を編集して返却する)
		return this.goodsListEdit(gmDto);

    }

	/**
	 * Goods List Edit　画面表示用の編集処理<br>
	 * <br>
	 * @param  GoodsMstDto
	 * @return GoodsListModel
	 * @throws NaiException
	 */
    private List<GoodsListModel> goodsListEdit(List<GoodsMstDto> gmDto) throws NaiException {

        GoodsListModel glModel = new GoodsListModel();
        List<GoodsListModel> rsltGlModel = new ArrayList<GoodsListModel>();

        String goodCd = "";
        if(gmDto.size()>0) {
            for (int i=0; i < gmDto.size(); i++) {
            	if(gmDto.get(0).getReturnCode()==0) {
            		glModel = new GoodsListModel();
            		goodCd = gmDto.get(i).getGoodsCd();
            		glModel.setGoodsCd(goodCd);                                                                 // 商品コード
            		glModel.setGoodsNm(gmDto.get(i).getGoodsNm());                                              // 商品名
            		glModel.setExplanation(gmDto.get(i).getExplanation());                                      // 詳細説明
            		glModel.setProductKbn(gmDto.get(i).getProductKbnNm());                                      // 商品形態区分名
            		glModel.setStrUsePoint(NaikaraStringUtil.addComma(gmDto.get(i).getUsePoint().toString()));  // 利用ポイント：文字列
            		glModel.setBdUsePoint(gmDto.get(i).getUsePoint());                                          // 利用ポイントBigDecimal
            		glModel.setSaleKbn(gmDto.get(i).getSaleKbnNm());                                            // 受取方法区分名

            		// 2014/02/18 Add Start 商品購入ページからの呼出追加に伴う修正
            		glModel.setTContactKbn(gmDto.get(i).getTeacherContactKbn());                                // 講師への購入連絡メール付き区分

                    StringBuffer teacherId = new StringBuffer();
                    StringBuffer teacherNickNm = new StringBuffer();
                    StringBuffer teacherInfo = new StringBuffer();

            		if (((SessionPurchaseGoods)SessionDataUtil.getSessionData(SessionPurchaseGoods.class.toString())) != null) {
            			String[] selectChk =((SessionPurchaseGoods)SessionDataUtil.getSessionData(SessionPurchaseGoods.class.toString())).getSelectChk();
            			List<GoodsListModel> resultList =((SessionPurchaseGoods)SessionDataUtil.getSessionData(SessionPurchaseGoods.class.toString())).getResultList();

        				// 講師への購入メール連絡付き区分=送信ありの場合

						String[] wkGoodsCd = {""};
        				if (StringUtils.equals(gmDto.get(i).getTeacherContactKbn(), NaikaraTalkConstants.T_CONTACT_KBN_YES)) {
        					for (int k = 0, n = selectChk.length; k < n; k++) {
        						// 商品コード
        						String[] wkGoodsCdCourseCdSplit = selectChk[k].split(NaikaraTalkConstants.HALF_SPACE);
        						wkGoodsCd[0] = wkGoodsCdCourseCdSplit[0];

        						/** 商品購入一覧 */
        						GoodsListModel pgModel = new GoodsListModel();
        						for (int j = 0, m = resultList.size(); j < m; j++) {

        							if (!StringUtils.equals(goodCd, wkGoodsCd[0])) {
        								// 商品コードが違う場合は次のデータを読み込む
        								continue;
        							}

        							pgModel = resultList.get(j);
        							if (StringUtils.equals(selectChk[k], pgModel.getGoodsCdcourseCd())) {
        								// 選択されている商品の場合
        								if (!pgModel.getTeacherChk()) {
            								if (pgModel.getTeacherId() != null || !StringUtils.equals(NaikaraTalkConstants.BRANK, pgModel.getTeacherId())) {
            									// 講師IDが設定されている場合
            									teacherId.append(pgModel.getTeacherId()).append(NaikaraTalkConstants.COMMA);            // 講師ID
            									teacherNickNm.append(pgModel.getTeacherNickNm()).append(NaikaraTalkConstants.COMMA);    // 講師ニックネーム
            									// 画面表示用の編集処理
            									teacherInfo.append(pgModel.getTeacherId()).append(STRING_COLON);                        // (画面表示用)講師ID
            									teacherInfo.append(pgModel.getTeacherNickNm()).append(NaikaraTalkConstants.COMMA);      // (画面表示用)講師ニックネーム
            								}
        								}
        							}
        						}
        					}
        				}

            		}

            		StringBuffer endTeacherInfo = new StringBuffer();
            		String wkTeacherInfo = teacherInfo.toString();
            		String wkTeacherId = teacherId.toString();
            		String wkTeacherNickNm = teacherNickNm.toString();
            		if (wkTeacherInfo.length() > 1) {
                		char endChar = wkTeacherInfo.charAt(wkTeacherInfo.length()-1);
                		if (endChar == NaikaraTalkConstants.COMMA) {
                			// 最終文字が”,”の場合は削除
                			wkTeacherInfo = wkTeacherInfo.substring(0, wkTeacherInfo.length()-1);
                			endTeacherInfo.append(FIX_TEACHER_INFO).append(wkTeacherInfo);
                			wkTeacherId = wkTeacherId.substring(0, wkTeacherId.length()-1);
                			wkTeacherNickNm = wkTeacherNickNm.substring(0, wkTeacherNickNm.length()-1);
                		}
            		}

            		glModel.setTeacherId(String.valueOf(wkTeacherId));                // 講師ID
            		glModel.setTeacherNickNm(String.valueOf(wkTeacherNickNm));        // 講師ニックネーム
            		glModel.setTeacherInfo(String.valueOf(endTeacherInfo));           // 講師ID + 講師ニックネーム
            		// 2014/02/18 Add End   商品購入ページからの呼出追加に伴う修正

            		// 設定
            		rsltGlModel.add(glModel);
        		}
        	}
        }

		// 2014/02/18 Add Start 商品購入ページからの呼出追加に伴う修正
        SessionPurchaseGoodsTContactInfo sessionData = new SessionPurchaseGoodsTContactInfo();
		sessionData.setResultList(rsltGlModel);
		SessionDataUtil.setSessionData(sessionData);
		// 2014/02/18 Add End   商品購入ページからの呼出追加に伴う修正

    	return rsltGlModel;

    }
	/**
	 * ポイント引当<br>
	 * <br>
	 * ポイント引当を行う<br>
	 * <br>
	 * @param studentId：受講者ID
	 * @param PointProvisionDataListDto：購入商品データリスト
	 * @return list：LessonReserveCancelResultListDto
	 * @throws NaiException
	 */

	public List<LessonReserveCancelResultListDto> pointProvision(String studentId,
			List<PointProvisionDataListDto> ppdlDto) throws NaiException {

		List<LessonReserveCancelResultListDto> lrcrlDto = new ArrayList<LessonReserveCancelResultListDto>();
		List<LessonReserveCancelResultListDto> lwklrcrlDto = new ArrayList<LessonReserveCancelResultListDto>();
		LessonReserveCancelResultListDto wklrcrlDto = new LessonReserveCancelResultListDto();
		List<PointOwnershipTrnListDto> potlDto = new ArrayList<PointOwnershipTrnListDto>();
		PointProvisionDataListDto wkppdlDto = new PointProvisionDataListDto();

		try {
			PointProvisionReleaseLogic pprLogic = new PointProvisionReleaseLogic(this.conn);

			//ポイント引当データリストからポイント所有テーブル等を更新する
			if(ppdlDto.size()>0){
				for (int i = 0, n = ppdlDto.size(); i < n; i++) {
					//ポイント所有テーブルリスト取得
					potlDto = pprLogic.pointOwnershipTrnListGet(studentId, ppdlDto.get(i).getLessonDt());

					if(potlDto.size()<1){
						//ポイント引当テーブルなし
						wklrcrlDto = new LessonReserveCancelResultListDto();
						wklrcrlDto.setRsvNoPurchaseId("");
						wklrcrlDto.setTeacherId(ppdlDto.get(i).getTeacherId());
						wklrcrlDto.setLessonDt(ppdlDto.get(i).getLessonDt());
						wklrcrlDto.setLessonTmCd(ppdlDto.get(i).getLessonTmCd());
						wklrcrlDto.setCourseCd(ppdlDto.get(i).getCourseCd());
						wklrcrlDto.setPaymentUsePoint(ppdlDto.get(i).getUsePoint());
						wklrcrlDto.setFreeUsePoint(BigDecimal.ZERO);
						wklrcrlDto.setReturnCode(POINT_PROVISION_TRN_ERR);
						lrcrlDto.add(wklrcrlDto);
					} else {
						wkppdlDto = new PointProvisionDataListDto();
						wkppdlDto.setTeacherId(ppdlDto.get(i).getTeacherId());
						wkppdlDto.setLessonDt(ppdlDto.get(i).getLessonDt());
						wkppdlDto.setLessonTmCd(ppdlDto.get(i).getLessonTmCd());
						wkppdlDto.setCourseCd(ppdlDto.get(i).getCourseCd());
						wkppdlDto.setUsePoint(ppdlDto.get(i).getUsePoint());

						// 2014/06/02 レッスン予約に関する「応相談」対応 Upd Start
						wkppdlDto.setUpdateCd(studentId);
						// 2014/06/02 レッスン予約に関する「応相談」対応 Upd End

						//ポイント更新
						lwklrcrlDto = this.pointUpdate(studentId, wkppdlDto, potlDto);

						for (int j = 0, m = lwklrcrlDto.size(); j < m; j++) {
							wklrcrlDto = new LessonReserveCancelResultListDto();
							wklrcrlDto.setRsvNoPurchaseId(lwklrcrlDto.get(j).getRsvNoPurchaseId());
							wklrcrlDto.setTeacherId(lwklrcrlDto.get(j).getTeacherId());
							wklrcrlDto.setLessonDt(lwklrcrlDto.get(j).getLessonDt());
							wklrcrlDto.setLessonTmCd(lwklrcrlDto.get(j).getLessonTmCd());
							wklrcrlDto.setCourseCd(lwklrcrlDto.get(j).getCourseCd());
							wklrcrlDto.setPaymentUsePoint(lwklrcrlDto.get(j).getPaymentUsePoint());
							wklrcrlDto.setFreeUsePoint(lwklrcrlDto.get(j).getFreeUsePoint());
							wklrcrlDto.setReturnCode(lwklrcrlDto.get(j).getReturnCode());
							lrcrlDto.add(wklrcrlDto);
						}
					}
				}
			}

			return lrcrlDto;

		} catch (Exception e) {
			throw new NaiException(e);
		}

	}

	/**
	 * ポイント引当<br>
	 * <br>
	 * ポイント引当を行い各テーブルを更新する<br>
	 * <br>
	 * @param studentId：受講者ID
	 * @param PointProvisionTrnListDto：ポイント引当データリスト
	 * @param PointOwnershipTrnListDto：ポイント所有テーブルリスト
	 * @return List：LessonReserveCancelResultListDto
	 * @throws NaiException
	 */
	private List<LessonReserveCancelResultListDto> pointUpdate(String studentId,
			PointProvisionDataListDto ppdlDto, List<PointOwnershipTrnListDto> potlDto) throws NaiException {

		List<LessonReserveCancelResultListDto> lrcrlDto = new ArrayList<LessonReserveCancelResultListDto>();
		LessonReserveCancelResultListDto wklrcrlDto = new LessonReserveCancelResultListDto();
		OrderNumbersMstLogic onmLogic = new OrderNumbersMstLogic();
		OrderNumberDto onDto = new OrderNumberDto();
		PointProvisionTrnDto pptDto = new PointProvisionTrnDto();

	    //初期化
		int wkErrFlg = RESULT_OK;
		int wkConsSeq = 0;
		int calcSignM = 1;
		BigDecimal wkBP = new BigDecimal(0);
		BigDecimal wkPP = new BigDecimal(0);
		BigDecimal wkFP = new BigDecimal(0);
		BigDecimal wkUsePoint = new BigDecimal(0);
		String wkRsvNoPurchaseId = "";
		String wkRsvPurchaseKbn = "";

		try {
			PointOwnershipTrnDao potDao = new PointOwnershipTrnDao(this.conn);
			PointProvisionTrnDao pptDao = new PointProvisionTrnDao(this.conn);

			// 利用ポイントセット
			wkBP = wkBP.add(ppdlDto.getUsePoint());

			// 購入ID取得
			wkRsvPurchaseKbn = NaikaraTalkConstants.RSV_PURCHASE_KBN_PRCHS;
			// 購入ID取得
			onDto = onmLogic.getOrderNumber(NaikaraTalkConstants.ORDER_NUMBERS_BY, DateUtil.getSysDate());
			//コースマスタ取得
			if(onDto.getReturnCode()==NaikaraTalkConstants.RETURN_CD_DATA_NO) {
				// 購入ID取得エラー
				wkErrFlg = ORDER_NUMBERING_ERR;
			} else {
				wkRsvNoPurchaseId = onDto.getOrderNumber();
				for (int i = 0, n = potlDto.size(); i < n; i++) {
					if(ppdlDto.getLessonDt().compareTo(potlDto.get(i).getEffectiveEndDt())<=0 &&
							potlDto.get(i).getBalancePoint().compareTo(BigDecimal.ZERO)>0 &&
								wkBP.compareTo(BigDecimal.ZERO)>0){
						wkUsePoint = BigDecimal.ZERO;
						//ポイント所有．ポイント残高（i）- ポイント引当．利用ポイント
						if(wkBP.compareTo(potlDto.get(i).getBalancePoint())<=0) {
							wkUsePoint = wkBP;
							wkBP = BigDecimal.ZERO;
						} else {
							wkUsePoint = potlDto.get(i).getBalancePoint();
							wkBP = wkBP.subtract(potlDto.get(i).getBalancePoint());
						}
						//有償無償区分=有償("9")
						if(NaikaraTalkConstants.COMPENSATION_FREE_KBN_YES.equals
								(potlDto.get(i).getCompensationFreeKbn())){
							wkPP = wkPP.add(wkUsePoint);
						} else {
							wkFP = wkFP.add(wkUsePoint);
						}
						//ポイント所有テーブル更新（ポイント引当）
						// 2014/06/02 レッスン予約に関する「応相談」対応 Add Start
						//if(potDao.pointOwnershipTrnUpdate(studentId, potlDto.get(i).getOwnershipID(),
						//		potlDto.get(i).getCompensationFreeKbn(), wkUsePoint, calcSignM)==0) {
						if(potDao.pointOwnershipTrnUpdate(studentId, potlDto.get(i).getOwnershipID(),
								potlDto.get(i).getCompensationFreeKbn(), wkUsePoint, calcSignM, ppdlDto.getUpdateCd())==0) {
						// 2014/06/02 レッスン予約に関する「応相談」対応 Add End

							//ポイント引当テーブルの登録
							wkConsSeq ++;
							pptDto = new PointProvisionTrnDto();
							pptDto.setRsvNoPurchaseId(wkRsvNoPurchaseId);
							pptDto.setConsSeq(wkConsSeq);
							pptDto.setRsvPurchaseKbn(wkRsvPurchaseKbn);
							pptDto.setUsePoint(wkUsePoint);
							pptDto.setOwnId(potlDto.get(i).getOwnershipID());
							pptDto.setCompensationFreeKbn(potlDto.get(i).getCompensationFreeKbn());
							pptDto.setRecordVerNo(0);
							// 2014/06/02 レッスン予約に関する「応相談」対応 Add Start
							//pptDto.setInsertCd(studentId);
							//pptDto.setUpdateCd(studentId);
							pptDto.setInsertCd(ppdlDto.getUpdateCd());
							pptDto.setUpdateCd(ppdlDto.getUpdateCd());
							// 2014/06/02 レッスン予約に関する「応相談」対応 Add End
							if(pptDao.pointProvisionTrnInsert(pptDto)!=0) {
								//ポイント引当テーブル登録エラー
								wkErrFlg = POINT_INSERT_ERR;
								break;
							}
						} else {
							//ポイント所有テーブル更新エラー
							wkErrFlg = POINT_UPDATE_ERR;
							break;
						}
					}
				}
				//ポイント引当完了チェック
				if(wkBP.compareTo(BigDecimal.ZERO)>0){
					//ポイント引当エラー（ポイント不足）
					wkErrFlg = POINT_SHORTAGE_ERR;
				}
			}

			wklrcrlDto = new LessonReserveCancelResultListDto();
			wklrcrlDto.setRsvNoPurchaseId(wkRsvNoPurchaseId);
			wklrcrlDto.setTeacherId(ppdlDto.getTeacherId());
			wklrcrlDto.setLessonDt(ppdlDto.getLessonDt());
			wklrcrlDto.setLessonTmCd(ppdlDto.getLessonTmCd());
			wklrcrlDto.setCourseCd(ppdlDto.getCourseCd());
			wklrcrlDto.setPaymentUsePoint(wkPP);
			wklrcrlDto.setFreeUsePoint(wkFP);
			wklrcrlDto.setReturnCode(wkErrFlg);

			lrcrlDto.add(wklrcrlDto);

		} catch (Exception e) {
			throw new NaiException(e);
		}

		return lrcrlDto;

	}

	/**
	 * 商品購入テーブル登録<br>
	 * <br>
	 * ポイント購入時、商品購入テーブル登録<br>
	 * <br>
	 * @param studentId：受講者ＩＤ
	 * @param LessonReserveCancelResultListDto：ポイント引当結果リスト
	 * @return boolean：result
	 * @throws NaiException
	 */
	public boolean pointPurchaseGoodsInsert(String studentId, List<LessonReserveCancelResultListDto> lrcrlDto) throws NaiException {

		GoodsPurchaseTrnDao gptDao = new GoodsPurchaseTrnDao(this.conn);
		GoodsPurchaseTrnDto gptDto = new GoodsPurchaseTrnDto();
		SaleGoodsMstDao sgmDao = new SaleGoodsMstDao(this.conn);
		List<GoodsMstDto> gmDto = new ArrayList<GoodsMstDto>();

		// 購入時間
		String purchaseTime = DateUtil.getSysTime();

		// ご利用ポイント
		BigDecimal usePointTotal = BigDecimal.ZERO;
		// 購入商品リスト
		List<String> purchaseGoodsList = new ArrayList<String>();
		// 購入商品名リスト
		StringBuffer sbPurchaseGoodsNm = new StringBuffer();
		// 購入受取方法リスト
		StringBuffer sbPurchaseSaleKbnNm = new StringBuffer();
		// 購入金額リスト
		StringBuffer sbPurchaseSalesPrice = new StringBuffer();
		// 配送商品リスト
		List<String> deliveryGoodsList = new ArrayList<String>();
		// 配送商品名リスト
		StringBuffer sbDeliveryGoodsNm = new StringBuffer();
		// 配送受取方法リスト
		StringBuffer sbDeliverySaleKbnNm = new StringBuffer();

		ConditionMapper cm = new ConditionMapper();
		OrderCondition oc = new OrderCondition();

		try {
			for (int i = 0; i < lrcrlDto.size(); i++) {
				cm = new ConditionMapper();
				oc = new OrderCondition();
				cm.addCondition(SaleGoodsMstDao.COND_GOODS_CD, ConditionMapper.OPERATOR_EQUAL, lrcrlDto.get(i).getCourseCd());
				cm.addCondition(SaleGoodsMstDao.COND_USE_STR_DT, ConditionMapper.OPERATOR_LESS_EQUAL, lrcrlDto.get(i).getLessonDt());
				cm.addCondition(SaleGoodsMstDao.COND_USE_END_DT, ConditionMapper.OPERATOR_LARGE_EQUAL, lrcrlDto.get(i).getLessonDt());
				gmDto = sgmDao.search(cm, oc);
				if (gmDto.size() != 1) {
					return false;
				}
				gptDto = new GoodsPurchaseTrnDto();
				gptDto.setPurchaseId(lrcrlDto.get(i).getRsvNoPurchaseId());                   // 購入ID
				gptDto.setPaymentMethodKbn(NaikaraTalkConstants.PAYMENT_METHOD_KBN_POINT);    // 支払方法区分
				gptDto.setStudentId(studentId);                                               // 受講者ID (購入者ID)
				gptDto.setPurchaseDt(lrcrlDto.get(i).getLessonDt());                          // 購入日
				gptDto.setPurchaseTm(purchaseTime);                                           // 購入時刻
				gptDto.setGoodsCd(lrcrlDto.get(i).getCourseCd());                             // 商品コード
				gptDto.setGoodsNm(gmDto.get(0).getGoodsNm());                                 // 商品名*
				gptDto.setSaleKbn(gmDto.get(0).getSaleKbn());                                 // 受取方法区分
				gptDto.setSaleNm(gmDto.get(0).getSaleKbnNm());                                // 受取方法*
				gptDto.setPurchaseYen(gmDto.get(0).getSalesPrice());                          // 購入金額(税込)*
				gptDto.setPaymentUsePoint(lrcrlDto.get(i).getPaymentUsePoint());              // 有償利用ポイント
				gptDto.setFreeUsePoint(lrcrlDto.get(i).getFreeUsePoint());                    // 無償利用ポイント
				gptDto.setRecordVerNo(0);                                                     // レコードバージョン番号
				gptDto.setInsertCd(studentId);                                                // 登録者コード
				gptDto.setUpdateCd(studentId);                                                // 更新者コード
				if (gptDao.insert(gptDto) != 1) {
					return false;
				}

				// 利用ポイント合計
				usePointTotal = usePointTotal.add(gmDto.get(0).getSalesPrice());


				// 購入商品編集
				if (StringUtils.isEmpty(sbPurchaseGoodsNm)) {
					sbPurchaseGoodsNm.append(gmDto.get(0).getGoodsNm());
					sbPurchaseSaleKbnNm.append(gmDto.get(0).getSaleKbnNm());
					sbPurchaseSalesPrice.append(gmDto.get(0).getSalesPrice().toString());
					sbPurchaseSalesPrice.append(UNIT_POINT);
				} else  {
					sbPurchaseGoodsNm.append(NaikaraTalkConstants.COMMA);
					sbPurchaseGoodsNm.append(gmDto.get(0).getGoodsNm());
					sbPurchaseSaleKbnNm.append(NaikaraTalkConstants.COMMA);
					sbPurchaseSaleKbnNm.append(gmDto.get(0).getSaleKbnNm());
					sbPurchaseSalesPrice.append(NaikaraTalkConstants.COMMA);
					sbPurchaseSalesPrice.append(gmDto.get(0).getSalesPrice().toString());
					sbPurchaseSalesPrice.append(UNIT_POINT);
				}
				// 配送商品編集
				if (StringUtils.equals(gmDto.get(0).getSaleKbn(), NaikaraTalkConstants.SALE_KBN_DELIVERY)) {
					if (StringUtils.isEmpty(sbDeliveryGoodsNm)) {
						sbDeliveryGoodsNm.append(gmDto.get(0).getGoodsNm());
						sbDeliverySaleKbnNm.append(gmDto.get(0).getSaleKbnNm());
					} else {
						sbDeliveryGoodsNm.append(NaikaraTalkConstants.COMMA);
						sbDeliveryGoodsNm.append(gmDto.get(0).getGoodsNm());
						sbDeliverySaleKbnNm.append(NaikaraTalkConstants.COMMA);
						sbDeliverySaleKbnNm.append(gmDto.get(0).getSaleKbnNm());
					}
				}
			}

			boolean callpurchasePointFlg = true;  // ポイント購入

			// メール送信処理
			this.purchaseSendMail(callpurchasePointFlg, studentId, purchaseGoodsList, sbPurchaseGoodsNm,
					sbPurchaseSaleKbnNm, sbPurchaseSalesPrice, deliveryGoodsList, sbDeliveryGoodsNm,
					sbDeliverySaleKbnNm,usePointTotal);

		} catch (Exception e) {
			throw new NaiException(e);
		}

		return true;

	}


	/**
	 * 商品購入テーブル登録<br>
	 * <br>
	 * ペイパル購入時、商品購入テーブル登録<br>
	 * <br>
	 * @param studentId：受講者ＩＤ
	 * @param goodsCd：商品コード
	 * @return boolean：result
	 * @throws NaiException
	 */
	public boolean paypalPurchaseGoodsInsert(String studentId, String[] goodsCd) throws NaiException {

		GoodsPurchaseTrnDao gptDao = new GoodsPurchaseTrnDao(this.conn);
		GoodsPurchaseTrnDto gptDto = new GoodsPurchaseTrnDto();
		SaleGoodsMstDao sgmDao = new SaleGoodsMstDao(this.conn);
		List<GoodsMstDto> gmDto = new ArrayList<GoodsMstDto>();
		OrderNumbersMstLogic onmLogic = new OrderNumbersMstLogic();
		OrderNumberDto onDto = new OrderNumberDto();

		// 購入時間
		String purchaseDate = DateUtil.getSysDate();
		String purchaseTime = DateUtil.getSysTime();

		// ご利用ポイント
		BigDecimal usePointTotal = BigDecimal.ZERO;
		// 購入商品リスト
		List<String> purchaseGoodsList = new ArrayList<String>();
		// 購入商品名リスト
		StringBuffer sbPurchaseGoodsNm = new StringBuffer();
		// 購入受取方法リスト
		StringBuffer sbPurchaseSaleKbnNm = new StringBuffer();
		// 購入金額リスト
		StringBuffer sbPurchaseSalesPrice = new StringBuffer();
		// 配送商品リスト
		List<String> deliveryGoodsList = new ArrayList<String>();
		// 配送商品名リスト
		StringBuffer sbDeliveryGoodsNm = new StringBuffer();
		// 配送受取方法リスト
		StringBuffer sbDeliverySaleKbnNm = new StringBuffer();

		ConditionMapper cm = new ConditionMapper();
		OrderCondition oc = new OrderCondition();

		try {
			for (int i = 0; i < goodsCd.length; i++) {
				cm = new ConditionMapper();
				oc = new OrderCondition();
				cm.addCondition(SaleGoodsMstDao.COND_GOODS_CD, ConditionMapper.OPERATOR_EQUAL, goodsCd[i]);
				cm.addCondition(SaleGoodsMstDao.COND_USE_STR_DT, ConditionMapper.OPERATOR_LESS_EQUAL, purchaseDate);
				cm.addCondition(SaleGoodsMstDao.COND_USE_END_DT, ConditionMapper.OPERATOR_LARGE_EQUAL, purchaseDate);
				gmDto = sgmDao.search(cm, oc);
				if (gmDto.size() != 1) {
					return false;
				}
				// 購入ID取得
				onDto = onmLogic.getOrderNumber(NaikaraTalkConstants.ORDER_NUMBERS_BY, DateUtil.getSysDate());
				//コースマスタ取得
				if(onDto.getReturnCode()==NaikaraTalkConstants.RETURN_CD_DATA_NO) {
					return false;
				} else {
					gptDto = new GoodsPurchaseTrnDto();
					gptDto.setPurchaseId(onDto.getOrderNumber());                                 // 購入ID
					gptDto.setPaymentMethodKbn(NaikaraTalkConstants.PAYMENT_METHOD_KBN_PAYPAL);   // 支払方法区分
					gptDto.setStudentId(studentId);                                               // 受講者ID (購入者ID)
					gptDto.setPurchaseDt(purchaseDate);                                           // 購入日
					gptDto.setPurchaseTm(purchaseTime);                                           // 購入時刻
					gptDto.setGoodsCd(goodsCd[i]);                                                // 商品コード
					gptDto.setGoodsNm(gmDto.get(0).getGoodsNm());                                 // 商品名
					gptDto.setSaleKbn(gmDto.get(0).getSaleKbn());                                 // 受取方法区分
					gptDto.setSaleNm(gmDto.get(0).getSaleKbnNm());                                // 受取方法
					gptDto.setPurchaseYen(gmDto.get(0).getSalesPrice());                          // 購入金額(税込)
					gptDto.setPaymentUsePoint(BigDecimal.ZERO);                                   // 有償利用ポイント
					gptDto.setFreeUsePoint(BigDecimal.ZERO);                                     // 無償利用ポイント
					gptDto.setRecordVerNo(0);                                                     // レコードバージョン番号
					gptDto.setInsertCd(studentId);                                                // 登録者コード
					gptDto.setUpdateCd(studentId);                                                // 更新者コード
					if (gptDao.insert(gptDto) != 1) {
						return false;
					}
				}

				// メールの購入金額欄
				usePointTotal = usePointTotal.add(gmDto.get(0).getSalesPrice());

				// 購入商品編集
				if (StringUtils.isEmpty(sbPurchaseGoodsNm)) {
					sbPurchaseGoodsNm.append(gmDto.get(0).getGoodsNm());
					sbPurchaseSaleKbnNm.append(gmDto.get(0).getSaleKbnNm());
					sbPurchaseSalesPrice.append(gmDto.get(0).getSalesPrice().toString());
					sbPurchaseSalesPrice.append(UNIT_YEN);
				} else  {
					sbPurchaseGoodsNm.append(NaikaraTalkConstants.COMMA);
					sbPurchaseGoodsNm.append(gmDto.get(0).getGoodsNm());
					sbPurchaseSaleKbnNm.append(NaikaraTalkConstants.COMMA);
					sbPurchaseSaleKbnNm.append(gmDto.get(0).getSaleKbnNm());
					sbPurchaseSalesPrice.append(NaikaraTalkConstants.COMMA);
					sbPurchaseSalesPrice.append(gmDto.get(0).getSalesPrice().toString());
					sbPurchaseSalesPrice.append(UNIT_YEN);
				}
				// 配送商品編集
				if (StringUtils.equals(gmDto.get(0).getSaleKbn(), NaikaraTalkConstants.SALE_KBN_DELIVERY)) {
					if (StringUtils.isEmpty(sbDeliveryGoodsNm)) {
						sbDeliveryGoodsNm.append(gmDto.get(0).getGoodsNm());
						sbDeliverySaleKbnNm.append(gmDto.get(0).getSaleKbnNm());
					} else {
						sbDeliveryGoodsNm.append(NaikaraTalkConstants.COMMA);
						sbDeliveryGoodsNm.append(gmDto.get(0).getGoodsNm());
						sbDeliverySaleKbnNm.append(NaikaraTalkConstants.COMMA);
						sbDeliverySaleKbnNm.append(gmDto.get(0).getSaleKbnNm());
					}
				}

			}

			boolean callpurchasePointFlg = false;  // PayPal購入

			// メール送信処理
			this.purchaseSendMail(callpurchasePointFlg, studentId, purchaseGoodsList, sbPurchaseGoodsNm,
					sbPurchaseSaleKbnNm, sbPurchaseSalesPrice, deliveryGoodsList, sbDeliveryGoodsNm,
					sbDeliverySaleKbnNm,usePointTotal);


		} catch (Exception e) {
			throw new NaiException(e);
		}

		return true;

	}

	/**
	 * 商品購入のメール送信処理（共通：ポイント購入ボタン押下、PayPalでお支払いボタン押下）<br>
	 * <br>
	 * メール送信処理<br>
	 * <br>
	 * @param callpurchasePointFlg：呼出元ポイント購入フラグ(True:ポイント購入、False：PayPal購入)
	 * @param studentId           ：受講者ID
	 * @param purchaseGoodsList   ：購入商品リスト
	 * @param sbPurchaseGoodsNm   ：購入商品名リスト
	 * @param sbPurchaseSaleKbnNm ：購入受取方法リスト
	 * @param sbPurchaseSalesPrice：購入金額リスト
	 * @param deliveryGoodsList   ：配送商品リスト
	 * @param sbDeliveryGoodsNm   ：配送商品名リスト
	 * @param sbDeliverySaleKbnNm ：配送受取方法リスト
	 * @param usePointTotal       ：ポイント合計
	 * @return boolean            ：result
	 * @throws NaiException
	 */
	public boolean purchaseSendMail(boolean callpurchasePointFlg, String studentId,
			List<String> purchaseGoodsList, StringBuffer sbPurchaseGoodsNm,
			StringBuffer sbPurchaseSaleKbnNm, StringBuffer sbPurchaseSalesPrice,
			List<String> deliveryGoodsList, StringBuffer sbDeliveryGoodsNm,
			StringBuffer sbDeliverySaleKbnNm, BigDecimal usePointTotal) throws NaiException {

		try {


			// 受講者情報取得
			StudentMstDao smDao = new StudentMstDao(this.conn);
			List<StudentMstDto> smDto = new ArrayList<StudentMstDto>();
			ConditionMapper cm = new ConditionMapper();
			OrderCondition oc = new OrderCondition();

			cm.addCondition(StudentMstDao.COND_STUDENT_ID, ConditionMapper.OPERATOR_EQUAL, studentId);

			smDto = smDao.search(cm, oc);
			if (smDto.size() != 1) {
				return false;
			}
			String studentNickNm = smDto.get(0).getNickNm();


			//◆◆◆◆購入メール◆◆◆◆

			// 購入者名
			String stPurchaser = NaikaraStringUtil.unionName(smDto.get(0).getFamilyJnm(), smDto.get(0).getFirstJnm());

			// メール送信処理
			SendMailBatch smBatch = new SendMailBatch(this.conn);

			// メール送信リスト編集
			// メールパターンコード:GBC
			String mailPatternCd = NaikaraTalkConstants.MAIL_PATTERN_CODE_GBC;

			// 送信者:nothing
			String sendFrom = "";

			// 送信者名:nothing
			String sendFromNm = "";

			// 宛先IDリスト:受講者ID
			List<String> sendIdList = new ArrayList<String>();
			sendIdList.add(studentId);

			// 宛先リスト:受講者マスタ．メールアドレス1
			List<String> sendToList = new ArrayList<String>();
			sendToList.add(smDto.get(0).getMailAddress1());

			// CC:nothing
			String cc = "";

			// BCC:nothing
			String bcc = "";

			// 件名リスト:nothing
			List<String> subjectTitleList = new ArrayList<String>();
			subjectTitleList.add("");

			// 本文リスト:1-22
			List<String> list = new ArrayList<String>();
			List<List<String>> mailTextList = new ArrayList<List<String>>();

			// 本文リスト:1（名前(姓) & 名前(名））（受講者ID）
			list = new ArrayList<String>();
			list.add(stPurchaser);
			list.add(studentId);
			mailTextList.add(0, list);

			// 本文リスト:2-6（""）
			list = new ArrayList<String>();
			list.add(NaikaraTalkConstants.BRANK);
			mailTextList.add(1, list);
			mailTextList.add(2, list);
			mailTextList.add(3, list);
			mailTextList.add(4, list);
			mailTextList.add(5, list);

			// 本文リスト:7（購入商品）
			purchaseGoodsList.add(sbPurchaseGoodsNm.toString());
			purchaseGoodsList.add(sbPurchaseSaleKbnNm.toString());
			purchaseGoodsList.add(sbPurchaseSalesPrice.toString());
			mailTextList.add(6, purchaseGoodsList);

			// 本文リスト:8（""）
			list = new ArrayList<String>();
			list.add(NaikaraTalkConstants.BRANK);
			mailTextList.add(7, list);

			// 本文リスト:9（郵便番号）
			list = new ArrayList<String>();
			list.add(smDto.get(0).getZipCd());
			mailTextList.add(8, list);

			// 本文リスト:10（住所(都道府県) & 住所(市区町村 等)）
			CodeManagMstCache cache = CodeManagMstCache.getInstance();
			StringBuffer sbAdd = new StringBuffer();
			sbAdd.append(cache.decode(NaikaraTalkConstants.CODE_CATEGORY_STATE, smDto.get(0).getAddressPrefectureCd()));
			sbAdd.append(smDto.get(0).getAddressCity());
			list = new ArrayList<String>();
			list.add(sbAdd.toString());
			mailTextList.add(9, list);

			// 本文リスト:11（住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)）
			list = new ArrayList<String>();
			if (smDto.get(0).getAddressOthers() == null ) {
				list.add(NaikaraTalkConstants.BRANK);
			} else {
				list.add(smDto.get(0).getAddressOthers());
			}
			mailTextList.add(10, list);

			// 本文リスト:12（電話番号1）
			list = new ArrayList<String>();
			list.add(smDto.get(0).getTel1());
			mailTextList.add(11, list);

			// 本文リスト:13（ご利用ポイント）
			list = new ArrayList<String>();
			StringBuffer total = new StringBuffer();
			total.append(NaikaraStringUtil.addZenkakuComma(usePointTotal.toString()));
			if (callpurchasePointFlg) {
				total.append(UNIT_POINT);
			} else {
				total.append(UNIT_YEN);
			}
			list.add(total.toString());
			mailTextList.add(12, list);

			// 本文リスト:13-22（""）
			list = new ArrayList<String>();
			list.add(NaikaraTalkConstants.BRANK);
			mailTextList.add(13, list);
			mailTextList.add(14, list);
			mailTextList.add(15, list);
			mailTextList.add(16, list);
			mailTextList.add(17, list);
			mailTextList.add(18, list);
			mailTextList.add(19, list);
			mailTextList.add(20, list);
			mailTextList.add(21, list);

			// 添付:nothing
			String file = "";

			// 予約No:nothing
			String reservationNo = "";

			// メール送信処理
			smBatch.sendMail(mailPatternCd, sendFrom, sendFromNm, sendIdList,
					sendToList, cc, bcc, subjectTitleList, mailTextList, file, reservationNo);

            // 2014/01/03-スクールのメール送信・受信履歴照会に伴う対応-Start
            // 共通：スクールのメール送信・受信履歴データ作成Logicクラスの生成
            SmailAccountHistoryLogic sAMHiLogic = new SmailAccountHistoryLogic(this.conn);

            // ユーザIDを取得
            String userId = NaikaraTalkConstants.BRANK;
            if ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString()) != null) {
                userId = ((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserId();
            }

            // スクールメール・アカウント変更履歴テーブルの更新処理
            sAMHiLogic.smailActHistoryInsertMain(NaikaraTalkConstants.MAIL_PATTERN_GOODS_PURCHASE,
            		userId, studentId);
            // 2014/01/03-スクールのメール送信・受信履歴照会に伴う対応-End


			//◆◆◆◆配送商品メール◆◆◆◆

			// 配送商品メール送信処理
			if (!StringUtils.isEmpty(sbDeliveryGoodsNm)) {
				// メール送信リスト編集
				// メールパターンコード:GBC
				mailPatternCd = NaikaraTalkConstants.MAIL_PATTERN_CODE_GBS;

				// 送信者:nothing
				sendFrom = "";

				// 送信者名:nothing
				sendFromNm = "";

				// 宛先IDリスト:nothing
				sendIdList = new ArrayList<String>();
				sendIdList.add("");

				// 宛先リスト:nothing
				sendToList = new ArrayList<String>();
				sendToList.add("");

				// CC:nothing
				cc = "";

				// BCC:nothing
				bcc = "";

				// 件名リスト:nothing
				subjectTitleList = new ArrayList<String>();
				subjectTitleList.add(stPurchaser);

				// 本文リスト:0-11
				list = new ArrayList<String>();
				mailTextList = new ArrayList<List<String>>();

				// 本文リスト:1-3（""）
				list = new ArrayList<String>();
				list.add(NaikaraTalkConstants.BRANK);
				mailTextList.add(0, list);
				mailTextList.add(1, list);
				mailTextList.add(2, list);

				// 本文リスト:4（名前(姓) & 名前(名））（受講者ID）
				list = new ArrayList<String>();
				list.add(stPurchaser);
				list.add(studentId);
				mailTextList.add(3, list);

				// 本文リスト:5（""）
				list = new ArrayList<String>();
				list.add(NaikaraTalkConstants.BRANK);
				mailTextList.add(4, list);

				// 本文リスト:6（メールアドレス１）
				list = new ArrayList<String>();
				list.add(smDto.get(0).getMailAddress1());
				mailTextList.add(5, list);

				// 本文リスト:7（配送商品）
				deliveryGoodsList.add(sbDeliveryGoodsNm.toString());
				deliveryGoodsList.add(sbDeliverySaleKbnNm.toString());
				mailTextList.add(6, deliveryGoodsList);

				// 本文リスト:8（""）
				list = new ArrayList<String>();
				list.add(NaikaraTalkConstants.BRANK);
				mailTextList.add(7, list);

				// 本文リスト:9（郵便番号）
				list = new ArrayList<String>();
				list.add(smDto.get(0).getZipCd());
				mailTextList.add(8, list);

				// 本文リスト:10（住所(都道府県) & 住所(市区町村 等)）
				list = new ArrayList<String>();
				list.add(sbAdd.toString());
				mailTextList.add(9, list);

				// 本文リスト:11（住所(ﾋﾞﾙ、ﾏﾝｼｮﾝ名 等)）
				list = new ArrayList<String>();
				if (smDto.get(0).getAddressOthers() == null ) {
					list.add(NaikaraTalkConstants.BRANK);
				} else {
					list.add(smDto.get(0).getAddressOthers());
				}
				mailTextList.add(10, list);

				// 本文リスト:12（電話番号1）
				list = new ArrayList<String>();
				list.add(smDto.get(0).getTel1());
				mailTextList.add(11, list);

				// 添付:nothing
				file = "";

				// 予約No:nothing
				reservationNo = "";

				// メール送信処理
				smBatch.sendMail(mailPatternCd, sendFrom, sendFromNm, sendIdList,
						sendToList, cc, bcc, subjectTitleList, mailTextList, file, reservationNo);

	            // 2014/01/03-スクールのメール送信・受信履歴照会に伴う対応-Start
	            // スクールメール・アカウント変更履歴テーブルの更新処理
	            sAMHiLogic.smailActHistoryInsertMain(NaikaraTalkConstants.MAIL_PATTERN_GOODS_DELIVERY,
	            		userId, studentId);
	            // 2014/01/03-スクールのメール送信・受信履歴照会に伴う対応-End

			}

			//2014.02.18 Add Start 商品購入ページからの呼出追加に伴う修正
			//◆◆◆◆商品購入-講師への通知メール◆◆◆◆
    		if (((SessionPurchaseGoodsTContactInfo)SessionDataUtil.getSessionData(SessionPurchaseGoodsTContactInfo.class.toString())) != null) {
    			List<GoodsListModel> resultList =((SessionPurchaseGoodsTContactInfo)SessionDataUtil.getSessionData(SessionPurchaseGoodsTContactInfo.class.toString())).getResultList();

				String goodsCd = NaikaraTalkConstants.BRANK;        // 商品コード
				String goodsNm = NaikaraTalkConstants.BRANK;        // 商品名
				// String usePointStr = NaikaraTalkConstants.BRANK;    // 利用ポイント：文字列
				// BigDecimal usePoint = new BigDecimal("0");          // 利用ポイント：BigDecimal
				String teacherId = NaikaraTalkConstants.BRANK;      // 講師ID
				String teacherNickNm = NaikaraTalkConstants.BRANK;  // 講師ニックネーム
				List<String> brankList = new ArrayList<String>();   // メール本文：空行用
				brankList.add(NaikaraTalkConstants.BRANK);          // メール本文：空行の設定

				// 共通部品：システム日付取得(yyyy/MM/dd HH:mm:ss)
				String nowTime = DateUtil.getSysDateTimestamp();
				String nowYyMmDd = nowTime.substring(2, 10);
				String nowHhmm = nowTime.substring(10, 16).trim();

				// 共通部品：日付編集（講師用）(システム日付)
				String systemDt = NaikaraStringUtil.converToDDMMYY(nowYyMmDd);
				StringBuffer systemDtTm = new StringBuffer();
				systemDtTm.append(systemDt).append(" ").append(nowHhmm);


				/** 選択された商品購入一覧 */
				GoodsListModel model = new GoodsListModel();
				for (int j = 0, m = resultList.size(); j < m; j++) {

					model = resultList.get(j);
					if (StringUtils.equals(model.getTContactKbn(), NaikaraTalkConstants.T_CONTACT_KBN_YES)) {
						// 講師への購入メール連絡付き区分＝送信ありの場合

						goodsCd = model.getGoodsCd();             // 商品コード
						goodsNm = model.getGoodsNm();             // 商品名
						// usePointStr = model.getStrUsePoint();     // 利用ポイント：文字列
						// usePoint = model.getBdUsePoint();         // 利用ポイント：BigDecimal

						String[] wkTeacherId = model.getTeacherId().split(String.valueOf(NaikaraTalkConstants.COMMA));            // 講師ID リスト
						String[] wkTeacherNickNm = model.getTeacherNickNm().split(String.valueOf(NaikaraTalkConstants.COMMA));    // 講師ニックネーム リスト

						for (int k = 0, n = wkTeacherId.length; k < n; k++) {
							teacherId = wkTeacherId[k];           // 講師ID
							teacherNickNm = wkTeacherNickNm[k];   // 講師ニックネーム

							// 利用者マスタの取得（講師のメールアドレスの取得）
							UserMstDto userDto = this.selectUserMst(teacherId);

							// メール送信リスト編集
							// メールパターンコード:GBT
							mailPatternCd = NaikaraTalkConstants.MAIL_PATTERN_CODE_GBT;

							// 送信者:nothing（メール管理マスタに設定済）
							sendFrom = "";

							// 送信者名:nothing
							sendFromNm = "";

							// 宛先IDリスト:講師ID
							sendIdList = new ArrayList<String>();
							sendIdList.add(teacherId);

							// 宛先リスト:講師マスタ．メールアドレス1
							sendToList = new ArrayList<String>();
							sendToList.add(userDto.getMailAddress1());

							// CC:nothing（メール管理マスタに設定済）
							cc = "";

							// BCC:nothing
							bcc = "";

							// 件名リスト
							subjectTitleList = new ArrayList<String>();
							subjectTitleList.add(0, goodsNm);    // パラメタ1：商品名(英文用)
							subjectTitleList.add(1, goodsNm);    // パラメタ2：商品名(和文用)


							// 本文リスト 初期処理
							list = new ArrayList<String>();
							mailTextList = new ArrayList<List<String>>();

							// 本文リスト:1-2
							list = new ArrayList<String>();
							list.add(0, teacherNickNm);          // 講師ニックネーム
							mailTextList.add(0, list);           // 本文リスト:（1）:パラメタ1：講師ニックネーム
							mailTextList.add(1, list);           // 本文リスト:（2）:パラメタ1：講師ニックネーム

							// 本文リスト:3（""）
							mailTextList.add(2, brankList);

							// 本文リスト:4 <英文>
							list = new ArrayList<String>();
							list.add(0, studentNickNm);          // 受講者ニックネーム
							list.add(1, goodsCd);                // 商品コード
							list.add(2, goodsNm);                // 商品名
							list.add(3, systemDtTm.toString());  // システム日付(英文用編集後)
							mailTextList.add(3, list);           // 本文リスト:（4）:<英文>

							// 本文リスト:5 <和文>
							list = new ArrayList<String>();
							list.add(0, studentNickNm);          // 受講者ニックネーム
							list.add(1, systemDtTm.toString());  // システム日付(英文用編集後)
							list.add(2, goodsNm);                // 商品名
							mailTextList.add(4, list);           // 本文リスト:（5）:<和文>

							// 本文リスト:6（""）
							mailTextList.add(5, brankList);

							// 本文リスト:7 <英文>
							list = new ArrayList<String>();
							list.add(0, goodsCd);                // 商品コード
							list.add(1, goodsNm);                // 商品名
							mailTextList.add(6, list);           // 本文リスト:（6）:<英文>

							// 本文リスト:8 <和文>
							mailTextList.add(7, list);           // 本文リスト:（7）:<和文>

							// 本文リスト:9（""）
							mailTextList.add(8, brankList);

							// 本文リスト:10-22（パラメタ なし）
							mailTextList.add(9, brankList);
							mailTextList.add(10, brankList);
							mailTextList.add(11, brankList);
							mailTextList.add(12, brankList);
							mailTextList.add(13, brankList);
							mailTextList.add(14, brankList);
							mailTextList.add(15, brankList);
							mailTextList.add(16, brankList);
							mailTextList.add(17, brankList);
							mailTextList.add(18, brankList);
							mailTextList.add(19, brankList);
							mailTextList.add(20, brankList);
							mailTextList.add(21, brankList);


							// 添付:nothing
							file = "";

							// 予約No:nothing
							reservationNo = "";

							// メール送信処理
							smBatch.sendMail(mailPatternCd, sendFrom, sendFromNm, sendIdList,
									sendToList, cc, bcc, subjectTitleList, mailTextList, file, reservationNo);

						}	// for (int k = 0, n = wkTeacherId.length; k < n; k++) {

					}  // if (StringUtils.equals(model.getTContactKbn(), NaikaraTalkConstants.T_CONTACT_KBN_YES)) {
				}	// for (int j = 0, m = resultList.size(); j < m; j++) {
			}	// if (((SessionPurchaseGoodsTContactInfo)SessionDataUtil.getSessionData(SessionPurchaseGoodsTContactInfo.class.toString())) != null) {

			//2014.02.18 Add End   商品購入ページからの呼出追加に伴う修正


		} catch (Exception e) {
			throw new NaiException(e);
		}

		return true;

	}


	/**
	 * 受講者マスタ取得処理とメール送信に必要な項目値のチェック<br>
	 * <br>
	 * @param studentId
	 * @return true:値設定あり/false:値設定なし
	 * @throws NaiException
	 */
    public boolean checkStudentMst(String studentId) throws NaiException {

    	boolean rtn = false;

		StudentMstDao smDao = new StudentMstDao(this.conn);
		List<StudentMstDto> smDto = new ArrayList<StudentMstDto>();
		ConditionMapper cm = new ConditionMapper();
		OrderCondition oc = new OrderCondition();

		cm.addCondition(StudentMstDao.COND_STUDENT_ID, ConditionMapper.OPERATOR_EQUAL, studentId);

		// データを取得する
		smDto = smDao.search(cm, oc);
		if (smDto.size() != 1) {
			return rtn;
		}

		// 名前(姓)
		if (smDto.get(0).getFamilyJnm() == null ) {
			return rtn;
		}

		// 名前(名)
		if (smDto.get(0).getFirstJnm() == null ) {
			return rtn;
		}

		// メールアドレス１
		if (smDto.get(0).getMailAddress1() == null ) {
			return rtn;
		}

		// 郵便番号
		if (smDto.get(0).getZipCd() == null ) {
			return rtn;
		}

		// 住所(都道府県)
		if (smDto.get(0).getAddressPrefectureCd() == null ) {
			return rtn;
		}

		// 住所(市区町村 等)
		if (smDto.get(0).getAddressCity() == null ) {
			return rtn;
		}

		// 電話番号1
		if (smDto.get(0).getTel1() == null ) {
			return rtn;
		}

		rtn = true;

		// 戻り値
		return rtn;

    }


    //2014.02.18 Add Start 商品購入ページからの呼出追加に伴う修正
    /**
     * 利用者マスタデータ取得処理<br>
     * <br>
     * 利用者マスタより対象データを取得する処理<br>
     * <br>
     * @param teacherId 講師ID<br>
     * @return UserMstDto<br>
     * @exception NaiException
     */
    public UserMstDto selectUserMst(String teacherId) throws NaiException {

        // DAOの初期化
        UserMstDao dao = new UserMstDao(this.conn);

        // 検索条件：利用者ID
        ConditionMapper conditions = new ConditionMapper();
        conditions.addCondition(UserMstDao.COND_USER_ID, ConditionMapper.OPERATOR_EQUAL, teacherId);

        // 並び順:なし
        OrderCondition orderBy = new OrderCondition();

        // 検索処理
        ArrayList<UserMstDto> resultDto = (ArrayList<UserMstDto>) dao.search(conditions, orderBy);

        // DTOの初期化
        UserMstDto dtoResult = new UserMstDto();

        // データありの場合
        if (resultDto.get(0).getReturnCode() != NaikaraTalkConstants.RETURN_CD_DATA_NO) {
            // 取得結果をDtoへ保持
            dtoResult = resultDto.get(0);
        }

        return dtoResult;
    }
    //2014.02.18 Add End   商品購入ページからの呼出追加に伴う修正


}
