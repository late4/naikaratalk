package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.PaymentManagedListDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.PaymentManagedListLogic;
import com.naikara_talk.model.PaymentManagedListModel;
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

public class PaymentManagedListAllOnService implements ActionService {

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
			return logic.getPaymentTrn(dto, PaymentManagedListLogic.ALLON);

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

}
