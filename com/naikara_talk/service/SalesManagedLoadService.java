package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.SalesManagedDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.SalesManagedLogic;
import com.naikara_talk.model.SalesManagedModel;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>入金管理ページ詳細<br>
 * <b>クラス名称　　　:</b>入金管理ページ詳細クラス。<br>
 * <b>クラス概要　　　:</b>入金管理ページ詳細LoadService。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/09 TECS 新規作成。
 */

public class SalesManagedLoadService implements ActionService {

	/**
	 * コード管理マスタからデータ取得処理
	 * @param costomerKbn
	 * @return
	 * @throws NaiException
	 */
	public String getCostomerKbnName(String costomerKbn) throws NaiException {

		Connection conn = null;
        try {
            conn = DbUtil.getConnection();

            SalesManagedLogic logic = new SalesManagedLogic(conn);

            // コード管理マスタ検索
            return logic.selectCodeMst(NaikaraTalkConstants.CODE_CATEGORY_CUSTOMER_KBN).get(costomerKbn);
        } catch (SQLException e) {
            throw new NaiException(e);
        } catch (NaiException e) {
            throw new NaiException(e);
        } finally {
            try {
                conn.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

	}

	/**
	 * データ(明細)を取得する。
	 * @param model 入金管理ページ詳細Model
	 * @return list
	 * @throws NaiException
	 */
	public List<SalesManagedDto> search(SalesManagedModel model) throws NaiException {

		// コネクション変数
		Connection conn = null;

		try {
			// コネクションを取得
			conn = DbUtil.getConnection();

			// Model値をDTOにセット
			SalesManagedLogic logic = new SalesManagedLogic(conn);

			SalesManagedDto dto = modelToDto(model);

			// データ(明細)を取得する
			return logic.searchDetail(dto);

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
     * @param model 入金管理ページ詳細Model
     * @return SalesManagedDto
	 */
	private SalesManagedDto modelToDto(SalesManagedModel model) throws NaiException {

		// DTOの初期化
		SalesManagedDto dto = new SalesManagedDto();

		// 顧客区分
		dto.setCostomerKbn(model.getCostomerKbn());
		// 対象年月
		dto.setObjectYyyyMm(NaikaraStringUtil.delSlash(model.getObjectYyyyMm()));
		// 受講者ID／組織ID
		dto.setStudentOrganizationId(model.getStudentOrganizationId());

		return dto;

	}

}
