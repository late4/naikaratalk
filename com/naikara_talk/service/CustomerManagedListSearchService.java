package com.naikara_talk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.naikara_talk.dbutil.DbUtil;
import com.naikara_talk.dto.CustomerManagedListDto;
import com.naikara_talk.exception.NaiException;
import com.naikara_talk.logic.CustomerManagedListLogic;
import com.naikara_talk.model.CustomerManagedListModel;
import com.naikara_talk.util.DateUtil;
import com.naikara_talk.util.NaikaraStringUtil;
import com.naikara_talk.util.NaikaraTalkConstants;

/**
 * <b>システム名称　　:</b>NAIKARA Talkシステム。<br>
 * <b>サブシステム名称:</b>お客様管理ページ<br>
 * <b>クラス名称　　　:</b>お客様管理ページクラス。<br>
 * <b>クラス概要　　　:</b>お客様管理ページ検索処理Service。<br>
 * <br>
 * <b>著作権　　　　　:</b>All rights recerved, Copyright(C), nai INDUSTRIES, LTD.
 * @author TECS
 * <b>変更履歴　　　　:</b>2013/08/20 TECS 新規作成。
 */

public class CustomerManagedListSearchService implements ActionService {

    /* 一覧 ZERO件 */
	public static final int LIST_ZERO_CNT = 0;

    /* 一覧の表示上限 */
    public static final int LIST_MAX_CNT = 300;

    /**
	 * 検索前チェック処理。
	 * @param model お客様管理ページModel
	 * @return DataCount
	 * @throws NaiException
	 */
	public int checkPreSelect(CustomerManagedListModel model, Boolean returnOnFlg, String hasSearchFlg) throws NaiException {

		if (!returnOnFlg || StringUtils.equals(NaikaraTalkConstants.FALSE, hasSearchFlg)) {
			// ヘッダ部からの「戻る」を除く場合 又は 画面状態フラグ == (初期状態ではない) の場合

			// 対象期間の整合性チェック
			if (StringUtils.equals(CustomerManagedListModel.OBJECT_PERIOD_ZERO, model.getObjectPeriod())) {
				// 期間指定である場合は、開始日必須
				if(StringUtils.isEmpty(model.getPeriodFrom())) {
					return CustomerManagedListModel.ERR_PERIOD_FROM;
				}
				// 期間指定である場合は、終了日必須
				if(StringUtils.isEmpty(model.getPeriodTo())) {
					return CustomerManagedListModel.ERR_PERIOD_TO;
				}
				// 開始日≦終了日となるように
				if(DateUtil.dateCompare2(NaikaraStringUtil.delSlash(model.getPeriodFrom()), NaikaraStringUtil.delSlash(model.getPeriodTo()))) {
					return CustomerManagedListModel.ERR_FROM_TO;
				}
			} else {
				// 期間指定ではない場合は、開始日と終了日入力できません
				if(StringUtils.isNotEmpty(model.getPeriodFrom()) || StringUtils.isNotEmpty(model.getPeriodTo())) {
					return CustomerManagedListModel.ERR_OBJECT_PERIOD;
				}
			}

			// 件数のチェック
			int count = this.getRowCount(model);

	        if (count == LIST_ZERO_CNT || count == NaikaraTalkConstants.RETURN_CD_DATA_ERR) {
	            // データ件数＝Zero件の場合
	            return CustomerManagedListModel.ERR_ZERO_DATA;
	        } else if (count > LIST_MAX_CNT) {
	            // データ件数が最大件数以上の場合
	            return CustomerManagedListModel.ERR_MAXOVER_DATA;
	        }
		}

		return CustomerManagedListModel.CHECK_OK;

	}

	/**
	 * 検索データ件数取得。<br>
	 * <br>
     * @param CustomerManagedListModel
     *            画面のパラメータ
     * @return int
     * @throws Exception
	 */
    public int getRowCount(CustomerManagedListModel model) throws NaiException {

        Connection conn = null;
        try{
            conn = DbUtil.getConnection();    // DBの接続

            // CustomerManagedListLogicクラスの生成
            CustomerManagedListLogic logic = new CustomerManagedListLogic(conn);

            // CustomerManagedListDtoクラスの生成
            CustomerManagedListDto dto = new CustomerManagedListDto();

            // Model値をDTOにセット
            dto = this.modelToDto(model);

            // データの取得件数＆リターン
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
     * 画面データの取得処理<br>
     * <br>
     * @param CustomerManagedListModel
     *            画面のパラメータ
     * @return List<CustomerManagedListDto>
     * @throws Exception
     */
    public List<CustomerManagedListDto> selectList(CustomerManagedListModel model) throws NaiException {

        Connection conn = null;
        try{
            conn = DbUtil.getConnection();    // DBの接続

            // CustomerManagedListLogicクラスの生成
            CustomerManagedListLogic logic = new CustomerManagedListLogic(conn);

            // CustomerManagedListDtoクラスの生成
            CustomerManagedListDto dto = new CustomerManagedListDto();

            // Model値をDTOにセット
            dto = this.modelToDto(model);

            // データの取得＆リターン
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
     * Model値をDTOにセット。<br>
     * <br>
     * @param model
     *           CustomerManagedListModel
     * @return CustomerManagedListDto
     * @throws Exception
	 */
    private CustomerManagedListDto modelToDto(CustomerManagedListModel model) throws NaiException {

        // DTOの初期化
    	CustomerManagedListDto prmDto = new CustomerManagedListDto();

        prmDto.setCostomerKbn(model.getCostomerKbn());                  // 顧客区分
        prmDto.setStudentId(model.getStudentId());                      // 受講者ID
        prmDto.setFamilyFirstJnm(model.getFamilyFirstJnm());            // 受講者名(漢字)
        prmDto.setFamilyFirstKnm(model.getFamilyFirstKnm());            // 受講者名(フリガナ)
        prmDto.setFamilyFirstRomaji(model.getFamilyFirstRomaji());      // 受講者名(ローマ字)
        prmDto.setNickNm(model.getNikeNm());                            // 受講者名(ニックネーム)
        prmDto.setOrganizationJnm(model.getOrganizationJnm());          // 組織名
        prmDto.setObjectPeriod(model.getObjectPeriod());                // 対象期間
        prmDto.setSystemDate(DateUtil.getSysDate());                    // サーバーのシステム日付

        // 画面の｢対象期間｣＝”0” (期間指定) の場合
        if (StringUtils.equals(CustomerManagedListModel.OBJECT_PERIOD_ZERO, model.getObjectPeriod())){

        	prmDto.setPeriodFrom(NaikaraStringUtil.delSlash(model.getPeriodFrom()));  // 開始日
        	prmDto.setPeriodTo(NaikaraStringUtil.delSlash(model.getPeriodTo()));      // 終了日

        } else {
        	// 画面の｢対象期間｣≠”0” (期間指定) の場合
        	// 対象期間：過去３ヶ月の場合
            if (StringUtils.equals(CustomerManagedListModel.OBJECT_PERIOD_ONE, model.getObjectPeriod())){

            	prmDto.setObjectPeriodFrom(DateUtil.getSysDateAddMonth1(CustomerManagedListModel.OBJECT_PERIOD_INT_ONE));
            	prmDto.setObjectPeriodTo(DateUtil.getSysDateAddMonth1(CustomerManagedListModel.OBJECT_PERIOD_INT_MONE));

            } else if (StringUtils.equals(CustomerManagedListModel.OBJECT_PERIOD_TWO, model.getObjectPeriod())){

            	// 対象期間：過去６ヶ月の場合
            	prmDto.setObjectPeriodFrom(DateUtil.getSysDateAddMonth1(CustomerManagedListModel.OBJECT_PERIOD_INT_TWO));
            	prmDto.setObjectPeriodTo(DateUtil.getSysDateAddMonth1(CustomerManagedListModel.OBJECT_PERIOD_INT_MONE));

            } else if (StringUtils.equals(CustomerManagedListModel.OBJECT_PERIOD_THREE, model.getObjectPeriod())){

            	// 対象期間：過去１２ヶ月の場合
            	prmDto.setObjectPeriodFrom(DateUtil.getSysDateAddMonth1(CustomerManagedListModel.OBJECT_PERIOD_INT_THREE));
            	prmDto.setObjectPeriodTo(DateUtil.getSysDateAddMonth1(CustomerManagedListModel.OBJECT_PERIOD_INT_MONE));

            }

            StringBuffer sb = new StringBuffer();
            sb.append(DateUtil.getSysDateYMNoSplit()).append("01");
            prmDto.setPeriodFrom(sb.toString());            // 開始日
        	prmDto.setPeriodTo(DateUtil.getSysDate());      // 終了日
        }

        return prmDto;

    }

}
