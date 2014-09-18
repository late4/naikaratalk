package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.PaymentManagedListDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.PaymentManagedListLogic;
import com.naikara_talk.model.PaymentManagedListModel;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

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

public class PaymentManagedListUpdateService implements ActionService {

	/**
	 * 画面一覧のデータ取得処理。
	 * @param model 支払管理ページModel
	 * @return list
	 * @throws Exception
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
		// 支払予定年月
		dto.setPaymentPlanYyyyMm(NaikaraStringUtil.delSlash(model.getPayment_txt()));

		return dto;

	}

	/**
	 * Model値をDTOにセット。
	 * @param model 支払管理ページModel
	 * @param checkboxValus checkboxValus
	 * @return PaymentManagedListDto
	 */
	private PaymentManagedListDto modelToDtoForIsExist(PaymentManagedListModel model, String checkboxValus) throws NaiException {

		// DTOの初期化
		PaymentManagedListDto dto = new PaymentManagedListDto();
		// 支払予定年月
		dto.setPaymentPlanYyyyMm(NaikaraStringUtil.delSlash(model.getPayment_txt()));
		String split[] = null;
		if (checkboxValus.contains("_")) {
			split = checkboxValus.split("_");
		} else if (checkboxValus.contains("#")) {
			split = checkboxValus.split("#");
		}

		dto.setDataYyyyMm(split[0]);       // データ発生年月
		dto.setUserId(split[1]);           // 講師ID
		dto.setRecordVerNo(split[2]);      // レコードバージョン番号

		return dto;

	}

	/**
	 * 更新対象データの存在チェック。
	 * @param model 支払管理ページModel
	 * @return boolean
	 * @throws NaiException
	 */
	public boolean isExist(PaymentManagedListModel model) throws NaiException {

		// コネクション変数
		Connection conn = null;

		try {
			// コネクションを取得
			conn = DbUtil.getConnection();

			String checkboxValus[] = model.getCheckboxBaseValue();
			for (int i = 0; i < checkboxValus.length; i++) {
				// Model値をDTOにセット
				PaymentManagedListDto dto = modelToDtoForIsExist(model, checkboxValus[i]);
				PaymentManagedListLogic logic = new PaymentManagedListLogic(conn);
				boolean exist = logic.isExist(dto);
				if (!exist) {
					return false;
				}
			}
			return true;
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
	 * 更新処理。
	 * @param model 支払管理ページModel
	 * @return int
	 * @throws Exception
	 */
	public int update(PaymentManagedListModel model) throws NaiException {

        int cnt = NaikaraTalkConstants.RETURN_CD_ERR_UPD;
		// コネクション変数
		Connection conn = null;
        try {
			// コネクションを取得
			conn = DbUtil.getConnection();

        	List<PaymentManagedListDto> list = new ArrayList<PaymentManagedListDto>();
        	PaymentManagedListLogic logic = new PaymentManagedListLogic(conn);
            // DTOの初期化
        	PaymentManagedListDto dto = new PaymentManagedListDto();

        	// ｢支払対象｣＝ONのデータ分繰り返す
        	String checkboxValus[] = model.getCheckboxBaseValue();
    		for (int i = 0; i < checkboxValus.length; i++) {
    			// Model値をDTOにセット
    			dto = modelToDtoForIsExist(model, checkboxValus[i]);
    			list.add(dto);
    		}

            // 更新実行
            cnt = logic.update(list, NaikaraStringUtil.delSlash(model.getPayment_txt()));

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

}
