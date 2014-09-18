package com.naikara_talk.service;

import java.sql.Connection;
import java.util.List;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.PaymentManagedDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.PaymentManagedLogic;
import com.naikara_talk.model.PaymentManagedModel;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>売上入金・支払管理。<br>
 * <b>クラス名称　　　:</b>支払入力・修正クラス。<br>
 * <b>クラス概要　　　:</b>支払入力・修正updateService。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 *
 * @author TECS <b>変更履歴　　　　:</b>2013/07/31 TECS 新規作成。
 */

public class PaymentManagedUpdateService implements ActionService {

	/**
	 * 更新処理
	 * @param list 画面一覧list
	 * @param model 支払入力・修正Model
	 * @return int
	 * @throws NaiException
	 */
	public int update(List<PaymentManagedDto> list, PaymentManagedModel model) throws NaiException {

		// コネクション変数
		Connection conn = null;

        int cnt = NaikaraTalkConstants.RETURN_CD_ERR_UPD;
        try {
			// コネクションを取得
			conn = DbUtil.getConnection();

        	PaymentManagedLogic logic = new PaymentManagedLogic(conn);
            // Model値をDTOにセット
        	PaymentManagedDto dto = modelToDto(model);

            // 更新実行
            cnt = logic.update(list, dto);

            if (cnt > NaikaraTalkConstants.RETURN_CD_ERR_NO_UPD) {
            	// 正常に更新
            	conn.commit();
            } else {
            	conn.rollback();
            }

        } catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
				throw new NaiException(e1);
			}
			throw new NaiException(e);
		}  finally {
			try {
				conn.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
        return cnt;
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
		// 調整額(合計)
		dto.setAdjustmentYenSum(model.getAdjustmentYenSum());
		// 新支払予定額(合計)
		dto.setNewPaymentYenSum(model.getNewPaymentYenSum());
		// レコードバージョン番号
		dto.setRecordVerNo(model.getRecordVerNo());

		return dto;

	}

}
