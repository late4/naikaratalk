package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.PaymentManagedListDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.PaymentManagedListLogic;
import com.naikara_talk.model.PaymentManagedListModel;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>売上入金・支払管理。<br>
 * <b>クラス名称　　　:</b>支払管理ページクラス。<br>
 * <b>クラス概要　　　:</b>支払管理ページService。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴　　　　:</b>2013/07/23 TECS 新規作成。
 */

public class PaymentManagedListSearchService implements ActionService {

    /**
	 * 検索データ件数取得。
	 * @param model 支払管理ページModel
	 * @return DataCount
	 * @throws NaiException
	 */
	public int getRowCount(PaymentManagedListModel model) throws NaiException {

		// コネクション変数
		Connection conn = null;

		try {
			// コネクションを取得
			conn = DbUtil.getConnection();

			// Model値をDTOにセット
			PaymentManagedListDto dto = modelToDto(model);
			PaymentManagedListLogic logic = new PaymentManagedListLogic(conn);
			return logic.getRowCount(dto);
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
	 * 画面一覧のデータ取得処理。
	 * @param model 支払管理ページModel
	 * @return list
	 * @throws NaiException
	 */
	public List<PaymentManagedListDto> getPaymentTrn(PaymentManagedListModel model) throws NaiException {

		// コネクション変数
		Connection conn = null;

		try {
			// コネクションを取得
			conn = DbUtil.getConnection();

			// Model値をDTOにセット
			PaymentManagedListDto dto = modelToDto(model);
			PaymentManagedListLogic logic = new PaymentManagedListLogic(conn);
			return logic.getPaymentTrn(dto, PaymentManagedListLogic.SEARCH);

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
	 * Model値をDTOにセット。
     * @param model 支払管理ページModel
     * @return GoodsMstDto
	 */
	private PaymentManagedListDto modelToDto(PaymentManagedListModel model) throws NaiException {

		// DTOの初期化
		PaymentManagedListDto dto = new PaymentManagedListDto();
		dto.setPaymentPlanYyyyMm(NaikaraStringUtil.delSlash(model.getPayment_txt()));

		return dto;

	}

	/**
	 * 支払年月が必須チェック
	 * @param model 支払管理ページModel
	 * @return boolean
	 */
	public boolean checkPayment(PaymentManagedListModel model) throws NaiException {
		if (model.getPayment_txt() == null || StringUtils.equals("", model.getPayment_txt())) {
			return false;
		}
		return true;

	}

	/**
	 * 日付チェック
	 * @param model 支払管理ページModel
	 * @return boolean
	 */
	public boolean checkDate(PaymentManagedListModel model) throws NaiException {
		String payment = model.getPayment_txt();
		// 桁数チェック
		if (payment.length() < 6) {
			return false;
		}

		StringBuffer sb = new StringBuffer();
		// 桁数=6桁
		if (payment.length() == 6) {
			sb.append(NaikaraStringUtil.converToYYYY_MM(payment));
		} else {
			sb.append(payment);
		}

		// yyyy/mm -> yyyy/mm/01に変更
		sb.append("/01");

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(sb.toString());
		} catch (Exception e) {
			return false;
		}
		return true;

	}


	/**
	 * ｢支払対象｣欄の使用不可・使用可
	 * @param dateFrom システム日付の先月
	 * @param dateTo 画面の支払年月
	 * @return boolean
	 */
	public boolean dateCompare(String dateFrom, String dateTo) throws NaiException {
		// システム日付の先月
		StringBuffer sbFrom = new StringBuffer();
		// 画面の支払年月
		StringBuffer sbTo = new StringBuffer();
		// yyyy/mm -> yyyymm01
		sbFrom.append(NaikaraStringUtil.delSlash(dateFrom)).append("01");
		sbTo.append(NaikaraStringUtil.delSlash(dateTo)).append("01");
		// 日付の比較
		return DateUtil.dateCompare2(sbFrom.toString(), sbTo.toString());
	}
}
