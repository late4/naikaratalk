package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.PaymentManagedDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.PaymentManagedLogic;
import com.naikara_talk.model.PaymentManagedModel;
import com.naikara_talk.util.NaikaraStringUtil;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>売上入金・支払管理。<br>
 * <b>クラス名称　　　:</b>支払入力・修正クラス。<br>
 * <b>クラス概要　　　:</b>支払入力・修正LoadService。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴　　　　:</b>2013/07/30 TECS 新規作成。
 */

public class PaymentManagedLoadService implements ActionService {

	/**
	 * 検索データ件数取得。
	 * @param model 支払入力・修正Model
	 * @return DataCount
	 * @throws NaiException
	 */
	public int getRowCount(PaymentManagedModel model) throws NaiException {

		// コネクション変数
		Connection conn = null;

		try {
			// コネクションを取得
			conn = DbUtil.getConnection();

			// 初期化処理
			PaymentManagedLogic logic = new PaymentManagedLogic(conn);

			// Model値をDTOにセット
			PaymentManagedDto dto = modelToDto(model);

			// 検索データ件数取得
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
	 * @param model 支払入力・修正Model
	 * @return list
	 * @throws NaiException
	 */
	public List<PaymentManagedDto> search(PaymentManagedModel model) throws NaiException {

		// コネクション変数
		Connection conn = null;

		try {
			// コネクションを取得
			conn = DbUtil.getConnection();

			// 初期化処理
			PaymentManagedLogic logic = new PaymentManagedLogic(conn);

			// Model値をDTOにセット
			PaymentManagedDto dto = modelToDto(model);

			// 画面一覧のデータ取得
			return logic.search(dto);

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
	 * @param model 支払入力・修正Model
	 * @return 支払入力・修正DTO
	 */
	private PaymentManagedDto modelToDto(PaymentManagedModel model) throws NaiException {

		// DTOの初期化
		PaymentManagedDto dto = new PaymentManagedDto();
		// 支払予定年月
		dto.setPaymentPlanYyyyMm(NaikaraStringUtil.delSlash(model.getPayment_txt()));
		// データ発生年月
		dto.setDataYyyyMm(NaikaraStringUtil.delSlash(model.getDataYyyyMm()));
		// 講師ID (利用者ID)
		dto.setUserId(model.getUserId());

		return dto;

	}

}
