package com.naikara_talk.logic;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

//import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dao.PointOwnershipTrnDao;
import com.naikara_talk.dao.PointProvisionTrnDao;
import com.naikara_talk.dao.ScheduleReservationTrnDao;
import com.naikara_talk.dao.LessonReservationPerformanceTrnDao;

import com.naikara_talk.dao.CourceMstDao;
import com.naikara_talk.dbutil.ConditionMapper;
import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dbutil.OrderCondition;
import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.CourseMstDto;
import com.naikara_talk.dto.LessonReservationPerformanceTrnDto;
import com.naikara_talk.dto.LessonReserveCancelResultListDto;
import com.naikara_talk.dto.OrderNumberDto;
import com.naikara_talk.dto.PointOwnershipTrnListDto;
import com.naikara_talk.dto.PointProvisionDataListDto;
import com.naikara_talk.dto.PointProvisionTrnDto;
import com.naikara_talk.dto.PointProvisionTrnListDto;
import com.naikara_talk.dto.PointReleaseDataListDto;
//import com.naikara_talk.dto.ScheduleReservationTrnDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.mstcache.TeacherMstCache;
import com.naikara_talk.sessiondata.SessionUser;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraTalkConstants;
import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称:</b>共通部品 Logicクラス<br>
 * <b>クラス名称　　　:</b>レッスン予約・取消クラス<br>
 * <b>クラス概要　　　:</b>レッスン予約・取消Logic<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/07/09 TECS 新規作成
 *  　　　　　　　　　:</b>2013/06/02 TECS レッスン予約に関する「応相談」対応
 */
public class LessonReserveCancelLogic {

//	DataSource ds = DbUtil.getDataSource();
//	Connection conn = null;

	// コネクション変数
	public Connection conn = null;

	// コンストラクタ
	public LessonReserveCancelLogic(Connection con) {
		this.conn = con;
	}


	//レッスン予約・取消正常
	int LESSON_RESERVE_CANCEL_NORMAL = 0;

	//コースマスタ取得エラー
	int COURSE_MST_SELECT_ERROR = 1;

	//予約No／購入ID取得エラー
	int ORDER_NUMBERS_MST_NUMBERING_ERROR = 2;

	//ポイント引当テーブル削除エラー
	int POINT_PROVISION_TRN_DELETE_ERROR = 3;

	//ポイント引当テーブル登録エラー
	int POINT_PROVISION_TRN_INSERT_ERROR = 4;

	//ポイント所有テーブル更新エラー*
	int POINT_OWNERSHIP_TRN_UPDATE_ERROR = 5;

	//ポイント引当エラー（ポイント不足）
	int POINT_SHORTAGE_ERROR = 6;

	//レッスン予実テーブル削除エラー
	int LESSON_RESERVASION_PERFORMANCE_TRN_DELETE_ERROR = 7;

	//レッスン予実テーブル登録エラー
	int LESSON_RESERVASION_PERFORMANCE_TRN_INSERT_ERROR = 8;

	//講師予定予約テーブル更新エラー*
	int SCHEDULE_RESERVASION_TRN_UPDATE_ERROR = 9;

	int calcSignP = 0;
	int calcSignM = 1;

	/**
	 * レッスン予約・取消<br>
	 * <br>
	 * レッスン予約・取消を行う<br>
	 * <br>
	 * @param studentId：受講者ID
	 * @param ppdlDto：ポイント引当データリスト
	 * @return prdlDto：ポイント解除データリスト
	 * @throws NaiException
	 */

	public List<LessonReserveCancelResultListDto> lessonReserveCancel(String studentId,
			List<PointProvisionDataListDto> ppdlDto, List<PointReleaseDataListDto> prdlDto) throws NaiException {

		List<LessonReserveCancelResultListDto> lrcrlDto = new ArrayList<LessonReserveCancelResultListDto>();
		List<LessonReserveCancelResultListDto> lwklrcrlDto = new ArrayList<LessonReserveCancelResultListDto>();
		LessonReserveCancelResultListDto wklrcrlDto = new LessonReserveCancelResultListDto();
		List<PointProvisionTrnListDto> pptlDto = new ArrayList<PointProvisionTrnListDto>();
		List<PointReleaseDataListDto> lwkprdlDto = new ArrayList<PointReleaseDataListDto>();
		List<PointOwnershipTrnListDto> potlDto = new ArrayList<PointOwnershipTrnListDto>();
		PointReleaseDataListDto wkprdlDto = new PointReleaseDataListDto();
		PointProvisionDataListDto wkppdlDto = new PointProvisionDataListDto();

		try {

			PointProvisionReleaseLogic pprLogic = new PointProvisionReleaseLogic(this.conn);

			//◆◆◆ポイント解除データリストからポイント所有テーブル等を更新する
			if(prdlDto.size()>0){
				for (int i = 0, n = prdlDto.size(); i < n; i++) {

					PointReleaseDataListDto prdMDto = prdlDto.get(i);

					pptlDto = new ArrayList<PointProvisionTrnListDto>();
					wkprdlDto = new PointReleaseDataListDto();
					lwkprdlDto = new ArrayList<PointReleaseDataListDto>();

					wkprdlDto.setRsvNoPurchaseId(prdlDto.get(i).getRsvNoPurchaseId());
					wkprdlDto.setTeacherId(prdlDto.get(i).getTeacherId());
					wkprdlDto.setLessonDt(prdlDto.get(i).getLessonDt());
					wkprdlDto.setLessonTmCd(prdlDto.get(i).getLessonTmCd());
					wkprdlDto.setCourseCd(prdlDto.get(i).getCourseCd());

					// 2014/06/02 レッスン予約に関する「応相談」対応 Add Start
					wkprdlDto.setReservationKbnSet(prdMDto.getReservationKbnSet());        // 予約区分(予約状況)[更新値用]
					wkprdlDto.setReservationKbnWhere(prdMDto.getReservationKbnWhere());    // 予約区分(予約状況)[条件句用]
					wkprdlDto.setUpdateCd(prdMDto.getUpdateCd());                          // 更新者ID
					// 2014/06/02 レッスン予約に関する「応相談」対応 Add End

					// リストへ追加
					lwkprdlDto.add(wkprdlDto);

					//ポイント引当テーブルリスト取得
					pptlDto = pprLogic.pointProvisionTrnListGet(lwkprdlDto);

					if(pptlDto.size()<1){
						//ポイント引当テーブルなし
						wklrcrlDto = new LessonReserveCancelResultListDto();
						wklrcrlDto.setRsvNoPurchaseId(prdlDto.get(i).getRsvNoPurchaseId());
						wklrcrlDto.setTeacherId(prdlDto.get(i).getTeacherId());
						wklrcrlDto.setLessonDt(prdlDto.get(i).getLessonDt());
						wklrcrlDto.setLessonTmCd(prdlDto.get(i).getLessonTmCd());
						wklrcrlDto.setCourseCd(prdlDto.get(i).getCourseCd());
						wklrcrlDto.setPaymentUsePoint(BigDecimal.ZERO);
						wklrcrlDto.setFreeUsePoint(BigDecimal.ZERO);
						wklrcrlDto.setReturnCode(1);
						wklrcrlDto.setReserveCancelFlg(NaikaraTalkConstants.LESSON_CANCEL);
						lrcrlDto.add(wklrcrlDto);
					} else {
						//◆◆◆ ポイント解除 ◆◆◆
						lwklrcrlDto = this.lessonCancel(studentId, pptlDto, prdMDto);
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
							wklrcrlDto.setReserveCancelFlg(NaikaraTalkConstants.LESSON_CANCEL);

							lrcrlDto.add(wklrcrlDto);
						}
					}
				}
			}

			//◆◆◆ポイント引当データリストからポイント所有テーブル等を更新する
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
						wklrcrlDto.setReturnCode(3);
						wklrcrlDto.setReserveCancelFlg(NaikaraTalkConstants.LESSON_RESERVE);
						lrcrlDto.add(wklrcrlDto);
					} else {
						wkppdlDto = new PointProvisionDataListDto();
						wkppdlDto.setTeacherId(ppdlDto.get(i).getTeacherId());
						wkppdlDto.setLessonDt(ppdlDto.get(i).getLessonDt());
						wkppdlDto.setLessonTmCd(ppdlDto.get(i).getLessonTmCd());
						wkppdlDto.setCourseCd(ppdlDto.get(i).getCourseCd());
						wkppdlDto.setUsePoint(ppdlDto.get(i).getUsePoint());

						// 2014/06/02 レッスン予約に関する「応相談」対応 Add Start
						wkppdlDto.setReservationKbnSet(ppdlDto.get(i).getReservationKbnSet());      // 予約状況：更新値
						wkppdlDto.setReservationKbnWhere(ppdlDto.get(i).getReservationKbnWhere());  // 予約状況：Where条件
						wkppdlDto.setUpdateCd(ppdlDto.get(i).getUpdateCd());                        // 更新者ID
						// 2014/06/02 レッスン予約に関する「応相談」対応 Add End

						//◆◆◆ ポイント引当 ◆◆◆
						lwklrcrlDto = this.lessonReserve(studentId, wkppdlDto, potlDto);

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
							wklrcrlDto.setReserveCancelFlg(NaikaraTalkConstants.LESSON_RESERVE);
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
	 * レッスン取消<br>
	 * <br>
	 * レッスン取消を行い引当ポイントを所有ポイントに戻す<br>
	 * <br>
	 * @param studentId：受講者ID
	 * @param pptlDto：ポイント引当テーブルリスト
	 * @param prdMDto：ポイント解除データリスト
	 * @return List：LessonReserveCancelResultListDto
	 * @throws NaiException
	 */
	public List<LessonReserveCancelResultListDto> lessonCancel(String studentId,
			List<PointProvisionTrnListDto> pptlDto, PointReleaseDataListDto prdMDto) throws NaiException {

		// コネクション変数：レッスン取消専用
		Connection connCan = null;

		List<LessonReserveCancelResultListDto> lrcrlDto = new ArrayList<LessonReserveCancelResultListDto>();
		LessonReserveCancelResultListDto wklrcrlDto = new LessonReserveCancelResultListDto();

		//初期化
		int wkErrFlg = LESSON_RESERVE_CANCEL_NORMAL;
		BigDecimal wkPP = new BigDecimal(0);
		BigDecimal wkFP = new BigDecimal(0);

		try {
			connCan = DbUtil.getConnection();

			PointOwnershipTrnDao potDao = new PointOwnershipTrnDao(connCan);
			PointProvisionTrnDao pptDao = new PointProvisionTrnDao(connCan);
			ScheduleReservationTrnDao srtDao = new ScheduleReservationTrnDao(connCan);
			LessonReservationPerformanceTrnDao lrptDao = new LessonReservationPerformanceTrnDao(connCan);

			// ◆◆◆ 引当ポイントの解除（ポイント所有に戻す）◆◆◆
			if(pptlDto.size()>0){
				wkErrFlg = LESSON_RESERVE_CANCEL_NORMAL;

				for (int i = 0, n = pptlDto.size(); i < n; i++) {

					// ◆◆◆ ポイント所有テーブル更新
					// 2014/06/02 レッスン予約に関する「応相談」対応 Upd Start
					//if(potDao.pointOwnershipTrnUpdate(studentId, pptlDto.get(i).getOwnId(),
					//		pptlDto.get(i).getCompensationFreeKbn(), pptlDto.get(i).getUsePoint(), calcSignP)==0) {
					if(potDao.pointOwnershipTrnUpdate(studentId, pptlDto.get(i).getOwnId(),
							pptlDto.get(i).getCompensationFreeKbn(), pptlDto.get(i).getUsePoint(), calcSignP,
							prdMDto.getUpdateCd())==0) {
					// 2014/06/02 レッスン予約に関する「応相談」対応 Upd End

						//ポイント引当テーブル削除
						if(pptDao.pointProvisionTrnDelete(pptlDto.get(i).getRsvNoPurchaseId(), pptlDto.get(i).getConsSeq())==0) {
							if(NaikaraTalkConstants.COMPENSATION_FREE_KBN_YES.equals
									(pptlDto.get(i).getCompensationFreeKbn())) {
								wkPP = wkPP.add(pptlDto.get(i).getUsePoint());
							} else {
								wkFP = wkFP.add(pptlDto.get(i).getUsePoint());
							}
						} else {
							//ポイント引当テーブル削除エラー
							wkErrFlg = POINT_PROVISION_TRN_DELETE_ERROR;
							break;
						}
					} else {
						//ポイント所有テーブル更新エラー
						wkErrFlg = POINT_OWNERSHIP_TRN_UPDATE_ERROR;
						break;
					}
				}
				if(wkErrFlg==LESSON_RESERVE_CANCEL_NORMAL) {
					//◆◆◆ 講師予定予約テーブルの更新 <取消>

					// 2014/06/02 レッスン予約に関する「応相談」対応 Upd Start
					//if(srtDao.scheduleReservationTrnUpdate(NaikaraTalkConstants.BRANK, pptlDto.get(0).getTeacherId(),
					//		pptlDto.get(0).getLessonDt(), pptlDto.get(0).getLessonTmCd(),
					//		NaikaraTalkConstants.RESERV_KBN_YES, NaikaraTalkConstants.RESERV_KBN_ALREADY,
					//		NaikaraTalkConstants.BRANK , NaikaraTalkConstants.BRANK, studentId)==0) {

					if(srtDao.scheduleReservationTrnUpdate(
							NaikaraTalkConstants.BRANK,
							prdMDto.getTeacherId(),
							prdMDto.getLessonDt(),
							prdMDto.getLessonTmCd(),
							prdMDto.getReservationKbnSet(),
							prdMDto.getReservationKbnWhere(),
							NaikaraTalkConstants.BRANK , NaikaraTalkConstants.BRANK, prdMDto.getUpdateCd())==0) {
						// 2014/06/02 レッスン予約に関する「応相談」対応 Upd End

						//◆◆◆ レッスン予実テーブルの削除
						if(lrptDao.lessonReservasionPerformanceTrnDelete(pptlDto.get(0).getRsvNoPurchaseId())!=0) {
							//レッスン予実テーブル削除エラー
							wkErrFlg = LESSON_RESERVASION_PERFORMANCE_TRN_DELETE_ERROR;
						}
					} else {
						//講師予定予約テーブル更新エラー
						wkErrFlg = SCHEDULE_RESERVASION_TRN_UPDATE_ERROR;
					}

				}
				wklrcrlDto = new LessonReserveCancelResultListDto();
				wklrcrlDto.setRsvNoPurchaseId(pptlDto.get(0).getRsvNoPurchaseId());
				wklrcrlDto.setTeacherId(pptlDto.get(0).getTeacherId());
				wklrcrlDto.setLessonDt(pptlDto.get(0).getLessonDt());
				wklrcrlDto.setLessonTmCd(pptlDto.get(0).getLessonTmCd());
				wklrcrlDto.setCourseCd(pptlDto.get(0).getCourseCd());
				wklrcrlDto.setPaymentUsePoint(wkPP);
				wklrcrlDto.setFreeUsePoint(wkFP);
				if(wkErrFlg==LESSON_RESERVE_CANCEL_NORMAL) {
					wklrcrlDto.setReturnCode(LESSON_RESERVE_CANCEL_NORMAL);
					if (null != connCan) {
						connCan.commit();
					}
				} else {
					//解除エラー
					wklrcrlDto.setReturnCode(wkErrFlg);
					if (null != connCan) {
						connCan.rollback();
					}
				}
				lrcrlDto.add(wklrcrlDto);
			}
		} catch ( SQLException se ) {
			se.printStackTrace();
			throw new NaiException(se);
		} finally {
			try {
				if (null != connCan) {
					connCan.close();
		        }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return lrcrlDto;

	}

	/**
	 * レッスン予約<br>
	 * <br>
	 * レッスン予約を行い各テーブルを更新する<br>
	 * <br>
	 * @param studentId：受講者ID
	 * @param PointProvisionTrnListDto：ポイント引当データリスト
	 * @param PointOwnershipTrnListDto：ポイント所有テーブルリスト
	 * @return List：LessonReserveCancelResultListDto
	 * @throws NaiException
	 */
	public List<LessonReserveCancelResultListDto> lessonReserve(String studentId,
			PointProvisionDataListDto ppdlDto, List<PointOwnershipTrnListDto> potlDto) throws NaiException {

		// コネクション変数
		Connection connRes = null;

		List<LessonReserveCancelResultListDto> lrcrlDto = new ArrayList<LessonReserveCancelResultListDto>();
		LessonReserveCancelResultListDto wklrcrlDto = new LessonReserveCancelResultListDto();
		OrderNumbersMstLogic onmLogic = new OrderNumbersMstLogic();    // 共通部品でConnectする！！
		OrderNumberDto onDto = new OrderNumberDto();
		PointProvisionTrnDto pptDto = new PointProvisionTrnDto();
		LessonReservationPerformanceTrnDto lrptDto = new LessonReservationPerformanceTrnDto();
		List<CourseMstDto> cmDto = new ArrayList<CourseMstDto>();
		CourceMstDao cmDao = new CourceMstDao(this.conn);

		ConditionMapper cm = new ConditionMapper();
		OrderCondition oc = new OrderCondition();

	    //初期化
		int wkErrFlg = LESSON_RESERVE_CANCEL_NORMAL;
		int wkConsSeq = 0;
		BigDecimal wkBP = new BigDecimal(0);
		BigDecimal wkPP = new BigDecimal(0);
		BigDecimal wkFP = new BigDecimal(0);
		BigDecimal wkUsePoint = new BigDecimal(0);
		String wkRsvNoPurchaseId = "";
		String wkRsvPurchaseKbn = "";

		try {
			connRes = DbUtil.getConnection();

			PointOwnershipTrnDao potDao = new PointOwnershipTrnDao(connRes);
			PointProvisionTrnDao pptDao = new PointProvisionTrnDao(connRes);
			ScheduleReservationTrnDao srtDao = new ScheduleReservationTrnDao(connRes);
			LessonReservationPerformanceTrnDao lrptDao = new LessonReservationPerformanceTrnDao(connRes);

			//利用ポイントセット
			wkBP = wkBP.add(ppdlDto.getUsePoint());

			//予約No／購入ID取得
			if(ppdlDto.getTeacherId()==null || NaikaraTalkConstants.BRANK.equals(ppdlDto.getTeacherId())) {
				wkRsvPurchaseKbn = NaikaraTalkConstants.RSV_PURCHASE_KBN_PRCHS;
				//購入ID取得
				onDto = onmLogic.getOrderNumber(NaikaraTalkConstants.ORDER_NUMBERS_BY, DateUtil.getSysDate());
			} else {
				wkRsvPurchaseKbn = NaikaraTalkConstants.RSV_PURCHASE_KBN_RSV;
				//予約No取得
				onDto = onmLogic.getOrderNumber(NaikaraTalkConstants.ORDER_NUMBERS_RE, DateUtil.getSysDate());

				//コースマスタ取得
				if(onDto.getReturnCode()==NaikaraTalkConstants.RETURN_CD_DATA_YES) {

					cm.addCondition(CourceMstDao.COND_COURSE_CD, ConditionMapper.OPERATOR_EQUAL, ppdlDto.getCourseCd());
					cmDto = cmDao.search(cm, oc);

					if(cmDto.get(0).getReturnCode()==NaikaraTalkConstants.RETURN_CD_DATA_NO) {
						//コースマスタ取得エラー
						wkErrFlg = COURSE_MST_SELECT_ERROR;
					}
				} else {
					//予約No／購入ID取得エラー
					wkErrFlg = ORDER_NUMBERS_MST_NUMBERING_ERROR;
				}
			}
			if(wkErrFlg==LESSON_RESERVE_CANCEL_NORMAL) {

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

						// 2014/06/02 レッスン予約に関する「応相談」対応 Upd Start
						//if(potDao.pointOwnershipTrnUpdate(studentId, potlDto.get(i).getOwnershipID(),
						//		potlDto.get(i).getCompensationFreeKbn(), wkUsePoint, calcSignM)==0) {
						if(potDao.pointOwnershipTrnUpdate(studentId, potlDto.get(i).getOwnershipID(),
								potlDto.get(i).getCompensationFreeKbn(), wkUsePoint, calcSignM, ppdlDto.getUpdateCd())==0) {
						// 2014/06/02 レッスン予約に関する「応相談」対応 Upd End

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

							// 2014/06/02 レッスン予約に関する「応相談」対応 Upd Start
							//pptDto.setInsertCd(studentId);
							// pptDto.setUpdateCd(studentId);
							pptDto.setInsertCd(ppdlDto.getUpdateCd());
							pptDto.setUpdateCd(ppdlDto.getUpdateCd());
							// 2014/06/02 レッスン予約に関する「応相談」対応 Upd End

							if(pptDao.pointProvisionTrnInsert(pptDto)!=0) {
								//ポイント引当テーブル登録エラー
								wkErrFlg = POINT_PROVISION_TRN_INSERT_ERROR;
								break;
							}
						} else {
							//ポイント所有テーブル更新エラー
							wkErrFlg = POINT_OWNERSHIP_TRN_UPDATE_ERROR;
							break;
						}
					}
				}
				//ポイント引当完了チェック
				if(wkBP.compareTo(BigDecimal.ZERO)>0){
					//ポイント引当エラー（ポイント不足）
					wkErrFlg = POINT_SHORTAGE_ERROR;
				}
			}

			if(wkErrFlg==LESSON_RESERVE_CANCEL_NORMAL &&
					NaikaraTalkConstants.RSV_PURCHASE_KBN_RSV.equals(wkRsvPurchaseKbn)) {

				//◆◆◆講師予定予約テーブルの更新 <予約>

				// 2014/06/02 レッスン予約に関する「応相談」対応 Upd Start

				//if(srtDao.scheduleReservationTrnUpdate(studentId, ppdlDto.getTeacherId(), ppdlDto.getLessonDt(),
				//		ppdlDto.getLessonTmCd(), NaikaraTalkConstants.RESERV_KBN_ALREADY,
				//		NaikaraTalkConstants.RESERV_KBN_YES,
				//		wkRsvNoPurchaseId, ppdlDto.getCourseCd(), studentId) == LESSON_RESERVE_CANCEL_NORMAL) {

				if(srtDao.scheduleReservationTrnUpdate(studentId, ppdlDto.getTeacherId(), ppdlDto.getLessonDt(),
						ppdlDto.getLessonTmCd(), ppdlDto.getReservationKbnSet(),
						ppdlDto.getReservationKbnWhere(),
						wkRsvNoPurchaseId, ppdlDto.getCourseCd(), ppdlDto.getUpdateCd()) == LESSON_RESERVE_CANCEL_NORMAL) {
				// 2014/06/02 レッスン予約に関する「応相談」対応 Upd End

					//講師マスタのキャッシュ読み込み
					TeacherMstCache tmCache = TeacherMstCache.getInstance();
		            //コード管理マスタのキャッシュ読み込み
					CodeManagMstCache cmmCache = CodeManagMstCache.getInstance();

					LinkedHashMap<String, CodeManagMstDto> ltnList =
							cmmCache.getList(NaikaraTalkConstants.CODE_CATEGORY_LESSON_TM_S);
					LinkedHashMap<String, CodeManagMstDto> jbccList =
							cmmCache.getList(NaikaraTalkConstants.CODE_CATEGORY_BIG_CLASSIFICATION);
					LinkedHashMap<String, CodeManagMstDto> jmccList =
							cmmCache.getList(NaikaraTalkConstants.CODE_CATEGORY_MIDDLE_CLASSIFICATION);
					LinkedHashMap<String, CodeManagMstDto> jsccList =
							cmmCache.getList(NaikaraTalkConstants.CODE_CATEGORY_SMALL_CLASSIFICATION);
					LinkedHashMap<String, CodeManagMstDto> ebccList =
							cmmCache.getList(NaikaraTalkConstants.CODE_CATEGORY_BIG_CLASSIFICATION_T);
					LinkedHashMap<String, CodeManagMstDto> emccList =
							cmmCache.getList(NaikaraTalkConstants.CODE_CATEGORY_MIDDLE_CLASSIFICATION_T);
					LinkedHashMap<String, CodeManagMstDto> esccList =
							cmmCache.getList(NaikaraTalkConstants.CODE_CATEGORY_SMALL_CLASSIFICATION_T);

					lrptDto = new LessonReservationPerformanceTrnDto();
					lrptDto.setReservationNo(wkRsvNoPurchaseId);
					lrptDto.setStateKbn(NaikaraTalkConstants.RSV_PURCHASE_KBN_RSV);
					lrptDto.setStudentId(studentId);
					lrptDto.setStudentNickNm(((SessionUser) SessionDataUtil.getSessionData(SessionUser.class.toString())).getUserNm());
					lrptDto.setTeacherId(ppdlDto.getTeacherId());
					lrptDto.setTeacherNickNm(tmCache.get(ppdlDto.getTeacherId()).getNickAnm());
					lrptDto.setLessonDt(ppdlDto.getLessonDt());
					lrptDto.setLessonTmCd(ppdlDto.getLessonTmCd());
					lrptDto.setLessonTmNm(ltnList.get(ppdlDto.getLessonTmCd()).getManagerNm());
					lrptDto.setBigClassificationJcd(cmDto.get(0).getBigClassificationCd());
					lrptDto.setBigClassificationJnm(jbccList.get(cmDto.get(0).getBigClassificationCd()).getManagerNm());
					lrptDto.setMiddleClassificationJcd(cmDto.get(0).getMiddleClassificationCd());
					lrptDto.setMiddleClassificationJnm(jmccList.get(cmDto.get(0).getMiddleClassificationCd()).getManagerNm());
					lrptDto.setSmallClassificationJcd(cmDto.get(0).getSmallClassificationCd());
					lrptDto.setSmallClassificationJnm(jsccList.get(cmDto.get(0).getSmallClassificationCd()).getManagerNm());
					lrptDto.setBigClassificationEcd(cmDto.get(0).getBigClassificationCd());
					lrptDto.setBigClassificationEnm(ebccList.get(cmDto.get(0).getBigClassificationCd()).getManagerNm());
					lrptDto.setMiddleClassificationEcd(cmDto.get(0).getMiddleClassificationCd());
					lrptDto.setMiddleClassificationEnm(emccList.get(cmDto.get(0).getMiddleClassificationCd()).getManagerNm());
					lrptDto.setSmallClassificationEcd(cmDto.get(0).getSmallClassificationCd());
					lrptDto.setSmallClassificationEnm(esccList.get(cmDto.get(0).getSmallClassificationCd()).getManagerNm());
					lrptDto.setCourseCd(ppdlDto.getCourseCd());
					lrptDto.setCourseJnm(cmDto.get(0).getCourseJnm());
					lrptDto.setCourseEnm(cmDto.get(0).getCourseEnm());
					lrptDto.setPaymentUsePoint(wkPP);
					lrptDto.setFreeUsePoint(wkFP);
					lrptDto.setUsePointSum(ppdlDto.getUsePoint());
					if(NaikaraTalkConstants.ATTACHMENT_KBN_YES.equals
							(cmDto.get(0).getAttachmentFlg())) {
						lrptDto.setMailKbn(NaikaraTalkConstants.MAIL_KBN_NOTHING);
					} else {
						lrptDto.setMailKbn(NaikaraTalkConstants.MAIL_KBN_NOSEND);
					}
					lrptDto.setSalesAccruedFlg(NaikaraTalkConstants.SALES_ACCRUED_FLG_NO);
					lrptDto.setStudentLessonKbn(NaikaraTalkConstants.LESSON_KBN_NON_ATTENDANCE);
					lrptDto.setTeacherLessonKbn(NaikaraTalkConstants.LESSON_KBN_NON_ATTENDANCE);
					lrptDto.setRecordVerNo(0);

					// 2014/06/02 レッスン予約に関する「応相談」対応 Upd Start
					//lrptDto.setInsertCd(studentId);
					//lrptDto.setUpdateCd(studentId);
					lrptDto.setInsertCd(ppdlDto.getUpdateCd());
					lrptDto.setUpdateCd(ppdlDto.getUpdateCd());
					// 2014/06/02 レッスン予約に関する「応相談」対応 Upd End
					//レッスン予実テーブルの登録
					if(lrptDao.lessonReservasionPerformanceTrnInsert(lrptDto)!=LESSON_RESERVE_CANCEL_NORMAL) {
						//レッスン予実テーブル登録エラー
						wkErrFlg = LESSON_RESERVASION_PERFORMANCE_TRN_INSERT_ERROR;
					}
				} else {
					//講師予定予約テーブル更新エラー
					wkErrFlg = SCHEDULE_RESERVASION_TRN_UPDATE_ERROR;
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

			if(wkErrFlg==LESSON_RESERVE_CANCEL_NORMAL) {
				if (null != connRes) {
					connRes.commit();
				}
			} else {
				if (null != connRes) {
					connRes.rollback();
				}
			}

			lrcrlDto.add(wklrcrlDto);

		} catch ( SQLException se ) {
			se.printStackTrace();
			throw new NaiException(se);
		} finally {
			try {
				if (null != connRes) {
					connRes.close();
		        }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return lrcrlDto;

	}

}
