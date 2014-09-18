package com.naikara_talk.service;

//import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
//import java.sql.SQLException;

import org.apache.commons.lang3.ArrayUtils;
//import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
//import com.naikara_talk.dto.CodeManagMstDto;
import com.naikara_talk.dto.LessonReserveCancelResultListDto;
import com.naikara_talk.dto.PointProvisionDataListDto;
import com.naikara_talk.dto.PointReleaseDataListDto;
//import com.naikara_talk.dto.PurchaseGoodsDto;
import com.naikara_talk.dto.PurchaseGoodsPointPaymentDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.PurchaseGoodsLogic;
import com.naikara_talk.logic.PointProvisionReleaseLogic;
//import com.naikara_talk.logic.PurchaseGoodsListLogic;
//import com.naikara_talk.model.PurchaseGoodsListModel;
//import com.naikara_talk.model.GoodsListModel;
//import com.naikara_talk.mstcache.CodeManagMstCache;
import com.naikara_talk.util.DateUtil;
//import com.naikara_talk.util.NaikaraStringUtil;
//import com.naikara_talk.util.NaikaraTalkConstants;
//import com.naikara_talk.sessiondata.SessionPurchaseGoods;
//import com.naikara_talk.util.SessionDataUtil;

/**
 * <b>システム名称     :</b>NAIKARA Talkシステム<br>
 * <b>サブシステム名称 :</b>お客様利用画面<br>
 * <b>クラス名称       :</b>商品購入ページ確認（ポイント購入）<br>
 * <b>クラス概要       :</b>商品購入ページ確認（ポイント購入）処理Service<br>
 * <br>
 * <b>著作権           :</b>Copyright (C) 2013 NAI, Inc. All rights reserved.
 * @author TECS
 * <b>変更履歴         :</b>2013/08/15 TECS 新規作成
 */
public class PurchaseGoodsPointPaymentService implements ActionService {

	/* 戻り値：正常 */
	private static final int RESULT_OK = 0;

	/* 戻り値：異常（商品コード確認エラー） */
	public static final int RESULT_GOODS_CODE_CONFIRM_ERR = 1;

	/* 戻り値：異常（利用ポイント確認エラー） */
	public static final int RESULT_USE_POINT_CONFIRM_ERR = 2;

	/* 戻り値：異常（商品コード、利用ポイント整合性エラー） */
	public static final int RESULT_GOODS_POINT_INTEGRITY_ERR = 3;

	/* 戻り値：異常（所有ポイント確認エラー） */
	public static final int RESULT_POINT_CHECK_ERR = 4;

	/* 戻り値：異常（所有ポイント更新エラー） */
	public static final int RESULT_POINT_UPDATE_ERR = 5;

	/* 戻り値：異常（商品購入テーブル登録エラー） */
	public static final int RESULT_GOODS_INSERT_ERR = 6;

	/* 戻り値：異常（受講者情報の不足エラー(メール送信時に必要な情報の不足) ） */
	public static final int RESULT_STUDENT_DATA_SHORTAGE_ERR = 7;


	/**
	 * ポイント所有テーブル更新処理<br>
	 * <br>
	 * @param PurchaseGoodsPointPaymentDto
	 * @return int
	 * @exception Exception
	 */
	public int pointProvision(PurchaseGoodsPointPaymentDto pgppDto) throws Exception {

		Connection conn = null;

		// DTOクラスの生成
		List<LessonReserveCancelResultListDto> lrcrlDto = new ArrayList<LessonReserveCancelResultListDto>();
    	List<PointProvisionDataListDto> ppdlDto = new ArrayList<PointProvisionDataListDto>();
		List<PointReleaseDataListDto> prdlDto = new ArrayList<PointReleaseDataListDto>();
		PointProvisionDataListDto wkppdlDto = new PointProvisionDataListDto();
//		PointReleaseDataListDto wkprdlDto = new PointReleaseDataListDto();

    	try {
			// 商品コード確認
//			if (pgppDto.getGoodsCd().length == 0) {
			if (ArrayUtils.isEmpty(pgppDto.getGoodsCd())) {
				return RESULT_GOODS_CODE_CONFIRM_ERR;
			}
			// 利用ポイント確認
//			if (pgppDto.getUsePoint().length == 0) {
			if (ArrayUtils.isEmpty(pgppDto.getUsePoint())) {
				return RESULT_USE_POINT_CONFIRM_ERR;
			}
			// 商品コード、利用ポイント整合性
			if (pgppDto.getGoodsCd().length != pgppDto.getUsePoint().length) {
				return RESULT_GOODS_POINT_INTEGRITY_ERR;
			}

			conn = DbUtil.getConnection();    // DBの接続


			//◆◆受講者マスタ(メール送信)の項目値の設定有無チェック◆◆
			PurchaseGoodsLogic pgLogic = new PurchaseGoodsLogic(conn);
			boolean checkSMrtn = pgLogic.checkStudentMst(pgppDto.getStudentId());
			if (!checkSMrtn) {
				return RESULT_STUDENT_DATA_SHORTAGE_ERR;
			}
			//◆◆◆◆


			// Logicクラスの生成
			PointProvisionReleaseLogic pprLogic = new PointProvisionReleaseLogic(conn);
			String strPurchaseDate = DateUtil.getSysDate();;

			System.out.println("Student ID : "+pgppDto.getStudentId());
			System.out.println("Purchase Date : "+strPurchaseDate);
			for (int i = 0; i < pgppDto.getGoodsCd().length; i++) {
				System.out.println("Goods Code : "+pgppDto.getGoodsCd()[i]);
				System.out.println("User Point : "+pgppDto.getUsePoint()[i]);
				wkppdlDto = new PointProvisionDataListDto();
				wkppdlDto.setCourseCd(pgppDto.getGoodsCd()[i]);
				wkppdlDto.setLessonDt(strPurchaseDate);
				wkppdlDto.setLessonTmCd("");
				wkppdlDto.setTeacherId("");
				wkppdlDto.setUsePoint(pgppDto.getUsePoint()[i]);
				ppdlDto.add(wkppdlDto);
			}

			// ポイント所有テーブルのチェック
			lrcrlDto = pprLogic.pointCheck(pgppDto.getStudentId(), ppdlDto, prdlDto);

			// 結果チェック
			for (int i = 0; i < lrcrlDto.size(); i++) {
				if (lrcrlDto.get(i).getReturnCode() != RESULT_OK) {
					if (conn != null) {
						conn.close();
					}
					return RESULT_POINT_CHECK_ERR;
				}
			}

			// ポイント所有テーブルの更新
			pgLogic = new PurchaseGoodsLogic(conn);
	    	lrcrlDto = pgLogic.pointProvision(pgppDto.getStudentId(), ppdlDto);

			// 結果チェック
			for (int i = 0; i < lrcrlDto.size(); i++) {
				if (lrcrlDto.get(i).getReturnCode() != RESULT_OK) {
					if (conn != null) {
						conn.rollback();
						conn.close();
					}
					return RESULT_POINT_UPDATE_ERR;
				}
			}

			// 商品購入テーブルの登録
			if (!pgLogic.pointPurchaseGoodsInsert(pgppDto.getStudentId(), lrcrlDto)) {
				if (conn != null) {
					conn.rollback();
					conn.close();
				}
				return RESULT_GOODS_INSERT_ERR;
			}

			if (conn != null) {
				conn.commit();
				conn.close();
			}

			// 正常
			return RESULT_OK;

		} catch (Exception e) {
			try {
				conn.rollback();
				conn.close();
			} catch (Exception e1) {
				e1.printStackTrace();
				throw new NaiException(e1);
			}
			throw new NaiException(e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}

}